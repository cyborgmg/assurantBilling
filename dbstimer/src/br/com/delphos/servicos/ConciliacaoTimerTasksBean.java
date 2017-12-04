package br.com.delphos.servicos;


import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ControleConciliacaoDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.logs.LogProcessoBatch;
import br.com.delphos.billing.servicos.ConciliacaoService;
import br.com.delphos.billing.servicos.retornos.RetornoBaixarArquivoConciliacao;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

@Stateless
public class ConciliacaoTimerTasksBean extends AbstractTimerTasks implements ConciliacaoTimerTasks {
	public static Logger LOGGER = LoggerFactory
			.getLogger(CancelamentoTimerTasksBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER,
			this, "[CONCILIATION] ");

	@EJB(lookup = "java:global/dbs/ConciliacaoServiceBean")
	private ConciliacaoService conciliacaoService;

	@EJB(lookup = "java:global/dbsdb/ControleConciliacaoDLOBean")
	private ControleConciliacaoDLO controleConciliacaoDLO;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public boolean reprocessarDownloadArquivo(
			LogProcessoBatch logProcesso,
			Adquirente adquirente,
			ControleConciliacao controle) throws BillingException{
		try {
			boolean sucesso = true;

			ContratoCobranca contratoCobranca = 
					controleConciliacaoDLO.obterContratoCobrancaAssociado(controle);
			
			RetornoBaixarArquivoConciliacao retornoWs =
					conciliacaoService.baixarArquivoConciliacao(
							contratoCobranca,
							adquirente,
							controle.getDataMovimento(),
							controle);
			
			sucesso = doValidarArquivo(logProcesso, retornoWs, adquirente);
			return sucesso;
			
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public boolean efetuarDownloadArquivo(
			LogProcessoBatch logProcesso,
			Adquirente adquirente,
			Date dataMovimento) throws BillingException {
		
		try {
			boolean sucesso = true;
			
			ContratoCobranca contratoCobranca = 
					controleConciliacaoDLO.obterContratoCobrancaAssociado(adquirente);
			// Se já existir, será um update 
			ControleConciliacao controle = 
					controleConciliacaoDLO.buscarPorAdquirenteDataMovimento(adquirente, dataMovimento);
			
			RetornoBaixarArquivoConciliacao retornoWs =
					conciliacaoService.baixarArquivoConciliacao(
							contratoCobranca,
							adquirente,
							dataMovimento,
							controle);			

			sucesso = doValidarArquivo(logProcesso, retornoWs, adquirente);	
			return sucesso;
		
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public List<Adquirente> listarAdquirentesParaDownload() throws BillingException {
		try {
			return controleConciliacaoDLO.listarAdquirentesAtivosConciliacao(DateUtils.getDataHoraAtual());
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public List<Adquirente> listarAdquirentesParaProcessamento() throws BillingException {
		try {
			return controleConciliacaoDLO.listarAdquirentesParaProcessamento(DateUtils.getDataHoraAtual());
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public boolean processarArquivo(LogProcessoBatch logProcesso, ControleConciliacao controle) throws BillingException {
		try {
			conciliacaoService.processarArquivoConciliacao(controle);
			return true;
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ControleConciliacao buscarProximoArquivoParaProcessamento(LogProcessoBatch logProcesso, Adquirente adquirente) throws BillingException {
		try {
			return controleConciliacaoDLO.buscarProximoArquivoParaProcessamento(adquirente);
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Date obterUltimaDataMovimentoImportada(Adquirente adquirente) throws BillingException {
		try {
			return controleConciliacaoDLO.obterUltimaDataMovimentoImportada(adquirente);
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente)
			throws BillingException {
		try {
			return controleConciliacaoDLO.listarMarcadosParaReprocessamentoPorAdquirente(adquirente);
		} catch (BillingException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new BillingException(ex);
		}
	}
	
	private boolean doValidarArquivo(
			LogProcessoBatch logProcesso,
			RetornoBaixarArquivoConciliacao retornoWs,
			Adquirente adquirente) throws BillingException {
		
		boolean sucesso = true;
		ControleConciliacao controle = retornoWs.getControleConciliacao();
		
		if (retornoWs.isSucesso()) {
			controle = conciliacaoService.validarArquivoConciliacao(controle);
			
			if (controle.getStatus().isValidoParaProcesso()) {
				controle = controleConciliacaoDLO.validarPorValor(controle);
				
				if (controle.getStatus().isValidoParaProcesso()) {
					controleConciliacaoDLO.salvar(controle);
					logger.debug("Controle de conciliação {{}} importado com sucesso.", controle);
					
				} else {
					sucesso = false;
					logProcesso.concatenarComplemento("Validação valor arquivo"
					+ " adquirente " + adquirente.getNome()
					+ " - " + controle.getDataMovimento().toString()
					+ " arquivo não passou na validação de valor");
				}
				
			} else {
				sucesso = false;
				logProcesso.concatenarComplemento("Validação arquivo"
					+ " adquirente " + adquirente.getNome()
					+ " - " + controle.getDataMovimento().toString()
					+ " arquivo não passou na validação");
			}
							
		} else {
			sucesso = false;
			logProcesso.concatenarComplemento("Download arquivo"
					+ " adquirente " + adquirente.getNome()
					+ " falha no serviço");
		}
		
		return sucesso;
	}
}
