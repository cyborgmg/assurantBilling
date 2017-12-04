package br.com.delphos.billing.braspag.servicos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.braspag.constantes.StatusTransacaoConsulta;
import br.com.delphos.billing.braspag.consulta.cliente.ErrorReportDataResponse;
import br.com.delphos.billing.braspag.consulta.cliente.OrderIdDataRequest;
import br.com.delphos.billing.braspag.consulta.cliente.OrderIdDataResponse;
import br.com.delphos.billing.braspag.consulta.cliente.OrderIdTransactionResponse;
import br.com.delphos.billing.braspag.consulta.cliente.TransactionDataRequest;
import br.com.delphos.billing.braspag.consulta.cliente.TransactionDataResponse;
import br.com.delphos.billing.braspag.util.BraspagServiceFactories;
import br.com.delphos.billing.braspag.util.PagadorQueryFactory;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.CodigoRetornoUtilitario;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.StatusAutorizacao;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.servicos.ConsultaService;
import br.com.delphos.billing.servicos.retornos.RetornoConsultarStatusCobranca;
import br.com.delphos.billing.servicos.retornos.RetornoObterCodigoTransacaoProvedor;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.util.Mensagens;

@Stateless(name = "ConsultaServiceBean")
public class BraspagConsultaServiceBean implements ConsultaService, Serializable {
	private static final long serialVersionUID = 1L;
	public static Logger LOGGER = LoggerFactory.getLogger(BraspagConsultaServiceBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	@EJB(lookup = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;

	private PagadorQueryFactory serviceFactory = BraspagServiceFactories.newPagadorQueryFactory();
	
	public RetornoConsultarStatusCobranca consultarStatusCobranca(
			Cobranca cobranca)
			throws BillingWebServiceException {
		LoggerWrapper log = logger.entrando("consultarStatusCobranca");
		boolean sucesso = true;
		RetornoConsultarStatusCobranca retorno = new RetornoConsultarStatusCobranca();
		ContratoCobranca contratoCobranca;
		cobranca = cobrancaDLO.completar(cobranca, "contratoCobranca");
		contratoCobranca = cobranca.getContratoCobranca();
		
		// Parâmetro desnecessário para nós, mas que é obrigatório nas
		// requisições feitas à Braspag. 
		String requestId = UUID.randomUUID().toString();
		
		String version = contratoCobranca.getVersaoContratoWs();
		String merchantId = contratoCobranca.getCodigoEmpresaNoProvedor();
		String braspagTransactionId = cobranca.getCodigoTransacaoProvedor();
		TransactionDataRequest requisicao = serviceFactory.newTransactionDataRequest();

		try {
			if (braspagTransactionId == null || braspagTransactionId.isEmpty()) {
				// Se a cobrança não possui ID de transação do provedor, tentamos
				// obter este valor automaticamente.
				log.debug("Cobrança \\{{}\\} não possui um BraspagTransactionId,"
						+ " tentando resgatá-lo a partir do OrderId (ID de cobrança) {}.",
						cobranca, cobranca.getId());
				RetornoObterCodigoTransacaoProvedor consultaBraspagTransactionId = obterCodigoTransacaoProvedor(cobranca);
				braspagTransactionId = consultaBraspagTransactionId.getCodigoTransacaoProvedor();
				
				if (braspagTransactionId == null || braspagTransactionId.isEmpty()) {
	
					sucesso = false;
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.ErroValidacao);
					retorno.setMensagemComplemento(consultaBraspagTransactionId.getMensagemErroProvedor());
					
					retorno.setMensagemRetorno(consultaBraspagTransactionId.getMensagemRetorno());
					retorno.setCodigoErroProvedor(consultaBraspagTransactionId.getCodigoErroProvedor());
					retorno.setMensagemErroProvedor(consultaBraspagTransactionId.getMensagemErroProvedor());
					retorno.setTipoException(consultaBraspagTransactionId.getTipoException());
					retorno.setMensagemException(consultaBraspagTransactionId.getMensagemException());
					
				} else {
					
					cobranca.setCodigoTransacaoProvedor(braspagTransactionId);
					cobrancaDLO.manter(cobranca);
				}
			}
			
			if (sucesso && (version == null || version.isEmpty()
					|| merchantId == null || merchantId.isEmpty())) {
	
				sucesso = false;
				retorno.setCodigoRetorno(CodigoRetornoUtilitario.ErroValidacao);
				retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.Falha));
			}
			
			if (sucesso) {
				requisicao.setBraspagTransactionId(braspagTransactionId);
				requisicao.setMerchantId(merchantId);
				requisicao.setRequestId(requestId);
				requisicao.setVersion(version);
				
				serviceFactory.verificarServico();				
				TransactionDataResponse resposta = serviceFactory.getTransactionData(requisicao);
				
				if (resposta.getErrorReportDataCollection() != null 
						&& resposta.getErrorReportDataCollection().getErrorReportDataResponse() != null
						&& !resposta.getErrorReportDataCollection().getErrorReportDataResponse().isEmpty()) {
					ErrorReportDataResponse erro = resposta.getErrorReportDataCollection().getErrorReportDataResponse().get(0);
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
					retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
					retorno.setCodigoErroProvedor(erro.getErrorCode());
					retorno.setMensagemErroProvedor(erro.getErrorMessage());
					retorno.setMensagemComplemento(erro.getErrorMessage());
					
				} else {
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Sucesso);
					retorno.setMensagemRetorno(null);
					StatusTransacaoConsulta status = StatusTransacaoConsulta.buscarPorValor(resposta.getStatus());
					retorno.setStatusCobranca(status.getStatusCobrancaEquivalente());
					retorno.setStatusAutorizacaoEfetiva(status.getStatusAutorizacaoEfetiva());
					retorno.setMensagemComplemento(resposta.getErrorMessage());
					
					if (cobranca.getStatusCobranca() == StatusCobranca.EstornoSolicitado 
							&& !(retorno.getStatusAutorizacaoEfetiva() == StatusAutorizacao.Estornado
									|| retorno.getStatusAutorizacaoEfetiva() == StatusAutorizacao.AguardandoResposta)) {
						retorno.setStatusAutorizacaoEfetiva(StatusAutorizacao.EstornoNegado);
					}
				}
				
			}

		} catch (Exception ex) {
			log.error("Erro ao tentar comunicação com o GetTransactionData do serviço Braspag PagadorQuery.", ex);
			retorno.setCodigoRetorno(CodigoRetornoUtilitario.Excecao);
			retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
			retorno.setTipoException(ex.getClass().getName());
			retorno.setMensagemException(ex.getMessage());
		}
		
		return log.saindo(retorno);
	}
	
	
	@Override
	public RetornoObterCodigoTransacaoProvedor obterCodigoTransacaoProvedor(
			Cobranca cobranca)
			throws BillingWebServiceException {
		LoggerWrapper log = logger.entrando("obterCodigoTransacaoProvedor");
		RetornoObterCodigoTransacaoProvedor retorno = new RetornoObterCodigoTransacaoProvedor();
		boolean sucesso = true;
		ContratoCobranca contratoCobranca;
		cobranca = cobrancaDLO.completar(cobranca, "contratoCobranca");
		contratoCobranca = cobranca.getContratoCobranca();
		
		// Parâmetro desnecessário para nós, mas que é obrigatório nas
		// requisições feitas à Braspag. 
		String requestId = UUID.randomUUID().toString();
		
		String version = contratoCobranca.getVersaoContratoWs();
		String merchantId = contratoCobranca.getCodigoEmpresaNoProvedor();
		String orderId = cobranca.getId() != null ? cobranca.getId().toString() : null;
		OrderIdDataRequest requisicao = serviceFactory.newOrderIdData();
		
		if (version == null || version.isEmpty()
				|| merchantId == null || merchantId.isEmpty()
				|| orderId == null || orderId.isEmpty()) {

			sucesso = false;
			retorno.setCodigoRetorno(CodigoRetornoUtilitario.ErroValidacao);
			retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.Falha));
		}
		
		requisicao.setOrderId(orderId);
		requisicao.setMerchantId(merchantId);
		requisicao.setRequestId(requestId);
		requisicao.setVersion(version);
		
		if (sucesso) {
			try {
				serviceFactory.verificarServico();				
				OrderIdDataResponse resposta = serviceFactory.getOrderIdData(requisicao);
				boolean chegouNaOperadora = resposta.isSuccess();
				
				retorno.setChegouNaOperadora(chegouNaOperadora);

				if (resposta.getOrderIdDataCollection() != null 
						&& resposta.getOrderIdDataCollection().getOrderIdTransactionResponse() != null
						&& !resposta.getOrderIdDataCollection().getOrderIdTransactionResponse().isEmpty()) {
					OrderIdTransactionResponse dados = resposta.getOrderIdDataCollection().getOrderIdTransactionResponse().get(0);
					List<String> idsTransacao = dados.getBraspagTransactionId().getGuid();
					
					retorno.setCodigoPedidoProvedor(dados.getBraspagOrderId());
					
					if (idsTransacao == null || cobranca.getNumeroParcela() != 1) {
						log.error("Cobrança \\{{}\\} possui BraspagOrderId"
								+ " mas não possui um BraspagTransactionId"
								+ " ou possui mais de um BraspagTransactionId.", cobranca);
						
					} else {
						retorno.setCodigoTransacaoProvedor(idsTransacao.get(0));
					}
				}
				
				if (resposta.getErrorReportDataCollection() != null 
						&& resposta.getErrorReportDataCollection().getErrorReportDataResponse() != null
						&& !resposta.getErrorReportDataCollection().getErrorReportDataResponse().isEmpty()) {
					ErrorReportDataResponse erro = resposta.getErrorReportDataCollection().getErrorReportDataResponse().get(0);
					retorno.setCodigoErroProvedor(erro.getErrorCode());
					retorno.setMensagemErroProvedor(erro.getErrorMessage());
				}
				
				if (chegouNaOperadora) {
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Sucesso);
					retorno.setMensagemRetorno(null);
					
					if (retorno.getCodigoTransacaoProvedor() == null
							|| retorno.getCodigoTransacaoProvedor().isEmpty()
							|| retorno.getCodigoPedidoProvedor() == null
							|| retorno.getCodigoPedidoProvedor().isEmpty()) {
						log.error("Cobrança \\{{}\\} chegou ao adquirente (success = true) mas"
								+ " não possui BraspagTransactionId ou BraspagOrderId."
								+ " BraspagOrderId: '{}', BraspagTransactionId: '{}'.",
								retorno.getCodigoPedidoProvedor(), retorno.getCodigoTransacaoProvedor());
					}
					
				} else {
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
					retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
				}
				
			} catch (Exception ex) {
				log.error("Erro ao tentar comunicação com o GetOrderIdData do serviço Braspag PagadorQuery.", ex);
				retorno.setCodigoRetorno(CodigoRetornoUtilitario.Excecao);
				retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
				retorno.setTipoException(ex.getClass().getName());
				retorno.setMensagemException(ex.getMessage());
			}
		}
		

		return log.saindo(retorno);
	}
}
