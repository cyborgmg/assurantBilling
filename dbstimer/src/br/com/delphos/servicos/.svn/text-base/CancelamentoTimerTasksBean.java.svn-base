package br.com.delphos.servicos;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.enumeracoes.CodigoRetornoUtilitario;
import br.com.delphos.billing.enumeracoes.MotivoCancelamento;
import br.com.delphos.billing.enumeracoes.StatusAutorizacao;
import br.com.delphos.billing.enumeracoes.StatusCancelamento;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusVenda;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.logs.LogProcessoBatch;
import br.com.delphos.billing.logs.LogProcessoBatchDLO;
import br.com.delphos.billing.servicos.ConsultaService;
import br.com.delphos.billing.servicos.PagamentoService;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarCobrancaPendente;
import br.com.delphos.billing.servicos.retornos.RetornoConsultarStatusCobranca;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.tentativas.TentativaDLO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;

@Stateless
public class CancelamentoTimerTasksBean extends AbstractTimerTasks implements CancelamentoTimerTasks {
	public static Logger LOGGER = LoggerFactory
			.getLogger(CancelamentoTimerTasksBean.class);
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

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public ResultadoCancelamento tratarCobrancaPendente(Cobranca cobranca, LogProcessoBatch logProcesso) throws BillingException {
		LoggerWrapper log = logger.entrando("tratarCobrancaPendente");

		ResultadoCancelamento resultado = new ResultadoCancelamento();
		TipoResultadoCancelamento sucesso = TipoResultadoCancelamento.Sucesso;
		Venda venda = cobranca.getVenda();
		// É assumido que a transação já tenha sido iniciada anteriormente.
	
		RetornoConsultarStatusCobranca dadosCobranca = consultaService.consultarStatusCobranca(cobranca);
		CodigoRetornoUtilitario codigoRetorno = dadosCobranca.getCodigoRetorno();
		BigDecimal valorCobrancaOriginal = cobranca.getValorCobranca();
		
		log.info("Processando cobrança: \\{{}}, código de retorno da pesquisa: {}",	cobranca, codigoRetorno);
		//Tentativa tentativa = cobrancaDLO.obterUltimaTentativa(cobranca, TipoTentativa.Cobranca);
		Tentativa tentativa = tentativaDLO.gerarTentativa(cobranca, TipoTentativa.Cancelamento);
		
		venda.setStatus(StatusVenda.Cancelada);
		venda.setCodigoMotivoCancelamento(MotivoCancelamento.CancelamentoAutomatico);
		venda.setDataCancelamento(DateUtils.getDataHoraAtual());
		
		if (codigoRetorno == CodigoRetornoUtilitario.Sucesso) {
			StatusAutorizacao statusEfetivo = dadosCobranca.getStatusAutorizacaoEfetiva();

			if (cobranca.getStatusCobranca() == StatusCobranca.Pendente 
					&& (statusEfetivo == StatusAutorizacao.Autorizado
					|| statusEfetivo == StatusAutorizacao.Capturado)) {
				
				eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.RetornoMetodoConsultaTransacao, null);
				
				RetornoCancelarCobrancaPendente dadosCancelamento =	pagamentoService.cancelarCobrancaPendente(cobranca, tentativa);
				StatusCancelamento statusCancelamento = dadosCancelamento.getStatus();
				
				if (statusCancelamento == StatusCancelamento.Cancelado || statusCancelamento == StatusCancelamento.Erro) {
					if (dadosCancelamento.isCancelamento()) {
						cobranca.setStatusCobranca(StatusCobranca.Cancelada);
						
					} else if (dadosCancelamento.isEstorno()) {
						cobranca.setStatusCobranca(StatusCobranca.Estornada);
						cobranca.setDataEstorno(DateUtils.getDataHoraAtual());
						cobranca.setValorEstorno(cobranca.getValorCobranca());
					}

				} else if (statusCancelamento == StatusCancelamento.Negado && dadosCancelamento.isEstorno()) {
					cobranca.setStatusCobranca(StatusCobranca.EstornoNegado);
					
				} else if (statusCancelamento == StatusCancelamento.Aceito && dadosCancelamento.isEstorno()) {
					cobranca.setStatusCobranca(StatusCobranca.EstornoSolicitado);
					cobranca.setDataEstorno(DateUtils.getDataHoraAtual());
					cobranca.setValorEstorno(cobranca.getValorCobranca());

				} else {
					logProcesso.concatenarComplemento("COB: " + cobranca.getId() + ", retorno: " + dadosCancelamento.getRetorno());//statusCancelamento.getValor());
				}

				if (dadosCancelamento.isEstorno()) {
					tentativa.setTipoTentativa(TipoTentativa.Estorno);
				}				
				
			} else if (cobranca.getStatusCobranca() == StatusCobranca.Pendente
					&& (statusEfetivo == StatusAutorizacao.NaoAutorizado
					|| statusEfetivo == StatusAutorizacao.Cancelado)) {
				
				eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.RetornoMetodoConsultaTransacao, null);
				cobranca.setStatusCobranca(StatusCobranca.Cancelada);

			} else if (cobranca.getStatusCobranca() == StatusCobranca.EstornoSolicitado) {
				
				if (statusEfetivo == StatusAutorizacao.Estornado) {
					eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.RetornoMetodoConsultaTransacao, null);
					
					cobranca.setStatusCobranca(StatusCobranca.Estornada);
					
					tentativa.setTipoTentativa(TipoTentativa.Estorno);

				} else if (statusEfetivo == StatusAutorizacao.EstornoNegado) {
					eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.RetornoMetodoConsultaTransacao, null);
					
					cobranca.setStatusCobranca(StatusCobranca.EstornoNegado);

				} else if (statusEfetivo == StatusAutorizacao.AguardandoResposta) {
					eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.RetornoMetodoConsultaTransacao, null);
					
				} else {
					log.warn("Solicitado estorno para a Cobrança \\{{}\\}, mas o retorno da consulta indica um estado {} junto ao provedor que é desconsiderado para o status ESO."
							+ " Status efetivo da cobrança: '{}', código de retorno '{}', mensagem de retorno '{}', "
							+ " tipo Exception '{}', mensagem Exception '{}', código de erro no provedor '{}',"
							+ " mensagem de erro no provedor: '{}'.",
							cobranca, statusEfetivo, statusEfetivo, 
							dadosCobranca.getCodigoRetorno(), dadosCobranca.getMensagemRetorno(),
							dadosCobranca.getTipoException(), dadosCobranca.getMensagemException(),
							dadosCobranca.getCodigoErroProvedor(), dadosCobranca.getMensagemErroProvedor());
				}
				
			} else {
				log.warn("Não foi possível determinar o estado da cobrança \\{{}\\} junto ao provedor."
						+ " Status efetivo da cobrança: '{}', código de retorno '{}', mensagem de retorno '{}', "
						+ " tipo Exception '{}', mensagem Exception '{}', código de erro no provedor '{}',"
						+ " mensagem de erro no provedor: '{}'.",
						cobranca, statusEfetivo, 
						dadosCobranca.getCodigoRetorno(), dadosCobranca.getMensagemRetorno(),
						dadosCobranca.getTipoException(), dadosCobranca.getMensagemException(),
						dadosCobranca.getCodigoErroProvedor(), dadosCobranca.getMensagemErroProvedor());

				logProcesso.concatenarComplemento("COB: " + cobranca.getId() + " - " + dadosCobranca.getMensagemErroAdquirente());
			}

		} else if (codigoRetorno == CodigoRetornoUtilitario.Excecao) {
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ErroProcessamentoSolicitacao, dadosCobranca.getMensagemException());
			
			//atualizarCobranca(cobranca, StatusCobranca.Cancelada);
			sucesso = TipoResultadoCancelamento.Excecao;
			logProcesso.concatenarComplemento("COB: " + cobranca.getId() + ", excecao: " + dadosCobranca.getMensagemException());
			
		} else if (codigoRetorno == CodigoRetornoUtilitario.ErroValidacao) {
			log.warn("Procedimento de pesquisa do status da cobrança \\{{}\\} falhou, dados inválidos"
					+ " ou insuficientes para pesquisa e cancelamento junto ao provedor."
					+ " A venda será cancelada automaticamente."
					+ " Código de retorno '{}', mensagem de retorno '{}', tipo Exception '{}',"
					+ " mensagem Exception '{}', código de erro no provedor '{}',"
					+ " mensagem de erro no provedor: '{}'.", 
					cobranca, codigoRetorno, dadosCobranca.getMensagemRetorno(),
					dadosCobranca.getTipoException(), dadosCobranca.getMensagemException(),
					dadosCobranca.getCodigoErroProvedor(), dadosCobranca.getMensagemErroProvedor());
			
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ErroProcessamentoSolicitacao, codigoRetorno.getValor());
			
			logProcesso.concatenarComplemento("COB: " + cobranca.getId() + " (invalido)");
			sucesso = TipoResultadoCancelamento.Excecao;
			
		} else { // Quando falha
			log.warn("Procedimento de pesquisa do status da cobrança \\{{}\\} falhou, a tentativa de cancelamento"
					+ " será realizada novamente na próxima vez. "
					+ " Código de retorno '{}', mensagem de retorno '{}', tipo Exception '{}',"
					+ " mensagem Exception '{}', código de erro no provedor '{}',"
					+ " mensagem de erro no provedor: '{}'.", 
					cobranca, codigoRetorno, dadosCobranca.getMensagemRetorno(),
					dadosCobranca.getTipoException(), dadosCobranca.getMensagemException(),
					dadosCobranca.getCodigoErroProvedor(), dadosCobranca.getMensagemErroProvedor());
			
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ErroProcessamentoSolicitacao, codigoRetorno.getValor());
			
			sucesso = TipoResultadoCancelamento.Falha;
			logProcesso.concatenarComplemento("COB: " + cobranca.getId() + ", falha: " + dadosCobranca.getMensagemProvedor());
		}

		cobranca.setValorCobranca(valorCobrancaOriginal);
		
		tentativaDLO.salvar(tentativa);
		cobrancaDLO.salvar(cobranca);
		vendaDLO.salvar(venda);
		logProcessoBatchDLO.salvar(logProcesso);
	
		resultado.setResultado(sucesso);
		return log.saindo(resultado);
	}
}
