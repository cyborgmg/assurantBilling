package br.com.delphos.servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.logs.LogProcessoBatch;
import br.com.delphos.billing.logs.LogProcessoBatchDLO;
import br.com.delphos.billing.servicos.ConsultaService;
import br.com.delphos.billing.servicos.PagamentoService;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.tentativas.TentativaDLO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.servicos.CancelamentoTimerTasks.ResultadoCancelamento;

@Stateless
public class CancelamentoTimerBean implements CancelamentoTimer {
	public static Logger LOGGER = LoggerFactory
			.getLogger(CancelamentoTimerBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER,
			this, "[TIMER] ");

	@EJB(lookup = "java:global/dbsdb/LogProcessoBatchDLOBean")
	private LogProcessoBatchDLO logProcessoBatchDLO;

	@EJB(lookup = "java:global/dbsdb/VendaDLOBean")
	private VendaDLO vendaDLO;
	
	@EJB(lookup = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;

	@EJB(lookup = "java:global/dbsdb/TentativaDLOBean")
	private TentativaDLO tentativaDLO;

	@EJB(lookup = "java:global/dbsdb/EventoDLOBean")
	private EventoDLO eventoDLO;
	
	@EJB(lookup = "java:global/dbs/ConsultaServiceBean")
	private ConsultaService consultaService;

	@EJB(lookup = "java:global/dbs/PagamentoServiceBean")
	private PagamentoService pagamentoService;

	@EJB
	private CancelamentoTimerTasks taskBean;
	
	@Schedule(minute = "*/15", hour = "*", persistent = false)
//	@Schedule(hour = "*/1", persistent = false)
	public void tratarCobrancasPendentes() throws BillingException {
		IdentificadorProcesso identificadorProcesso = IdentificadorProcesso.TratarCobrancasPendentes;
		LoggerWrapper log = logger.entrando("tratarCobrancasPendente");
		log.info("Iniciando tratamento de cobranças pendentes, data: {}", DateUtils.getDataHoraAtual());
		
		LogProcessoBatch logProcesso = taskBean.iniciarLogProcessoBatch(identificadorProcesso);

		//
		// Fluxo 1. Criação de uma nova entrada de log de processo.
		//
		
		int numSucessos = 0;
		int numErros = 0;
		boolean sucesso = true;
		List<Cobranca> cobrancasPendentes = null;
		
		try {
			//
			// 2. Obter lista de cobranças pendentes.
			// 3. Para cada cobrança, obter dados do provedor.
			//

			cobrancasPendentes = cobrancaDLO.listarCobrancasParcelaUnicaPendentes();
			
			if (cobrancasPendentes.isEmpty()) {
				log.info("Não há cobranças pendentes a serem tratadas.");
				
			} else {
	
				log.info("Iniciando processamento de {} cobrança(s)...", cobrancasPendentes.size());
				
				for (Cobranca cobranca : cobrancasPendentes) {
					ResultadoCancelamento resultado = taskBean.tratarCobrancaPendente(cobranca, logProcesso);
					
					switch (resultado.getResultado()) {
					case Sucesso:
						numSucessos++;
						break;
					case Falha:
						numErros++;
						break;
					case Excecao:
						numErros++;
	//					sucesso = false;
	//					logProcesso.setComplemento(resultado.getComplemento());
						break;
					}
				}
			}
			
		} catch (Exception ex) {
			log.error("Erro durante o processamento de cobranças pendentes!", ex);
			logProcesso.concatenarComplemento(ex.getMessage());
			sucesso = false;
		}
		
		if (sucesso) {
			taskBean.finalizarLogProcessoBatch(logProcesso, StatusLogProcesso.Processado);
			
		} else {
			taskBean.finalizarLogProcessoBatch(logProcesso, StatusLogProcesso.Erro);
		}
		
		if (cobrancasPendentes != null) {
			log.info("{} cobrança(s) processada(s) com sucesso, "
					+ "{} cobrança(s) pendente(s) por erros, "
					+ "{} cobranças a serem verificadas.", 
					numSucessos, numErros, cobrancasPendentes.size() - (numSucessos + numErros));
		}
		
		log.saindo();
	}
	
}
