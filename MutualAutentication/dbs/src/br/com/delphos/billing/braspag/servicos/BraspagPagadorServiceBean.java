package br.com.delphos.billing.braspag.servicos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.braspag.constantes.CodigoAdquirente;
import br.com.delphos.billing.braspag.constantes.Moeda;
import br.com.delphos.billing.braspag.constantes.Pais;
import br.com.delphos.billing.braspag.constantes.PlanoPagamento;
import br.com.delphos.billing.braspag.constantes.StatusTransacao;
import br.com.delphos.billing.braspag.constantes.StatusTransacaoCartaoCredito;
import br.com.delphos.billing.braspag.constantes.TipoTransacao;
import br.com.delphos.billing.braspag.pagador.cliente.ArrayOfPaymentDataRequest;
import br.com.delphos.billing.braspag.pagador.cliente.ArrayOfTransactionDataRequest;
import br.com.delphos.billing.braspag.pagador.cliente.AuthorizeTransactionRequest;
import br.com.delphos.billing.braspag.pagador.cliente.AuthorizeTransactionResponse2;
import br.com.delphos.billing.braspag.pagador.cliente.CreditCardDataRequest;
import br.com.delphos.billing.braspag.pagador.cliente.CreditCardDataResponse;
import br.com.delphos.billing.braspag.pagador.cliente.CustomerDataRequest;
import br.com.delphos.billing.braspag.pagador.cliente.ErrorReportDataResponse;
import br.com.delphos.billing.braspag.pagador.cliente.OrderDataRequest;
import br.com.delphos.billing.braspag.pagador.cliente.RefundCreditCardTransactionRequest;
import br.com.delphos.billing.braspag.pagador.cliente.RefundCreditCardTransactionResponse2;
import br.com.delphos.billing.braspag.pagador.cliente.TransactionDataRequest;
import br.com.delphos.billing.braspag.pagador.cliente.TransactionDataResponse;
import br.com.delphos.billing.braspag.pagador.cliente.VoidCreditCardTransactionRequest;
import br.com.delphos.billing.braspag.pagador.cliente.VoidCreditCardTransactionResponse2;
import br.com.delphos.billing.braspag.util.BraspagServiceFactories;
import br.com.delphos.billing.braspag.util.PagadorTransactionFactory;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.CodigoRetornoAutorizarVenda;
import br.com.delphos.billing.enumeracoes.CodigoRetornoUtilitario;
import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.enumeracoes.StatusCancelamento;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.servicos.PagamentoService;
import br.com.delphos.billing.servicos.retornos.RetornoAutorizarVendaCartaoCredito;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarCobrancaPendente;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.tentativas.TentativaDLO;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.CurrencyUtils;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.vendas.Venda;

@Stateless(name = "PagamentoServiceBean")
public class BraspagPagadorServiceBean implements PagamentoService, Serializable {
	private static final long serialVersionUID = 1L;
	public static Logger LOGGER = LoggerFactory.getLogger(BraspagPagadorServiceBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	private static final Moeda MoedaPadrao = Moeda.RealBrasileiro;
	private static final Pais PaisPadrao = Pais.Brasil;

	@EJB(lookup = "java:global/dbsdb/EventoDLOBean")
	private EventoDLO eventoDLO;
	
	@EJB(lookup = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;

	@EJB(lookup = "java:global/dbsdb/TentativaDLOBean")
	private TentativaDLO tentativaDLO;
	
	private PagadorTransactionFactory serviceFactory = BraspagServiceFactories.newPagadorTransactionFactory();

	public RetornoAutorizarVendaCartaoCredito autorizarVendaCartaoCredito(
			Cobranca cobranca,
			AdquirenteBandeira adquirenteBandeira,
			String cardNumber,
			String cardSecurityCode) {
		LoggerWrapper log = logger.entrando("autorizarVendaCartaoCredito");
		RetornoAutorizarVendaCartaoCredito retorno = new RetornoAutorizarVendaCartaoCredito();
		
		boolean sucesso = true;

		// DADOS DE DEPURAÇÃO E TESTE
		boolean modoDebug = Configuracoes.isDebug();
		String codigoVenda = cobranca.getVenda().getCodigoVendaOrigem();
		String prefixoNaoConfirmada = Configuracoes.getObjeto(PropriedadeConfiguracao.PrefixoVendaNaoConfirmada, String.class);
		String prefixoNaoChegou = Configuracoes.getObjeto(PropriedadeConfiguracao.PrefixoVendaNaoChegouNaAdquirente, String.class);
		String prefixoNula = Configuracoes.getObjeto(PropriedadeConfiguracao.PrefixoVendaRespostaNula, String.class);
		
		// DADOS DA VENDA
		Venda venda = cobranca.getVenda();
		TipoCobranca tipoCobranca = venda.getTipoCobranca();
		ContratoCobranca contratoCobranca = venda.getContratoCobranca();
		TipoTransacao tipoTransacao = 
				TipoTransacao.buscarPorValor(contratoCobranca.getTipoTransacaoProvedor());
		
		// DADOS DA REQUISIÇÃO

		// - AuthorizeTransactionRequest
		String requestId = cobranca.getIdRequisicaoProvedor();
		String version = cobranca.getContratoCobranca().getVersaoContratoWs();
		
		// - OrderData
		String merchantId = cobranca.getContratoCobranca().getCodigoEmpresaNoProvedor();
		String orderId = cobranca.getId().toString();
		String braspagOrderId = null;
		
		// - PaymentDataCollection / PaymentDataRequest
		short paymentMethod = adquirenteBandeira.getCodigoMetodoPagamento().shortValue();
		long amount = cobranca.getValorCobranca().multiply(BigDecimal.valueOf(100)).longValue();//CurrencyUtils.fromBigDecimal(cobranca.getValorCobranca());//
		String currency = MoedaPadrao.getValor();
		String country = PaisPadrao.getValor();
		
		// - CreditCardDataRequest
		boolean saveCreditCard = false;
		long serviceTaxAmount = 0;
		short paymentPlan = -1;
		short transactionType = -1;
		String cardExpirationDate = venda.getVencimentoCartao();
		short numberOfPayments = -1;
		String cardHolder = venda.getNomeImpressoCartao();
		
		// - CustomerDataRequest
		String nome = venda.getNome();
		String email = venda.getEmail();

		
		// CONVERSÕES
		
		
		if (tipoCobranca != null) {
			switch(tipoCobranca) {
			case SinglePremiumVista:
				paymentPlan = PlanoPagamento.Vista.getValor();
				numberOfPayments = 1;
				break;
				
			case SinglePremiumVistaOuParcelado:
				numberOfPayments = (short) venda.getQuantidadeParcelas();
				
				if (venda.getQuantidadeParcelas() == 1) {
					paymentPlan = PlanoPagamento.Vista.getValor();
					
				} else if (venda.getQuantidadeParcelas() > 1) {
					paymentPlan = PlanoPagamento.ParceladoEstabelecimento.getValor();
					
				} else {
					sucesso = false;
				}
				break;
				
			case SinglePremiumParcelamentoCartao:
				numberOfPayments = (short) venda.getQuantidadeParcelas();
				
				if (venda.getQuantidadeParcelas() == 1) {
					paymentPlan = PlanoPagamento.Vista.getValor();
					
				} else if (venda.getQuantidadeParcelas() > 1) {
					paymentPlan = PlanoPagamento.ParceladoCartao.getValor();
					
				} else {
					sucesso = false;
				}
				break;
				
			default:
				log.error("Não foi possível determinar o método de pagamento (PaymentMethod) para o Pagador.AuthorizeTransaction à partir do tipo de cobrança '{}'.", tipoCobranca);
				sucesso = false;
				break;
			}

			if (tipoCobranca.isRecorrencia()) {
				saveCreditCard = true;
			}
			
		} else {
			log.error("O método de pagamento (PaymentPlan) para o Pagador.AuthorizeTransaction não foi fornecido.");
			sucesso = false;
		}

		if (tipoTransacao != null) {
			// Para certificar que o tipo de transação é válido para a Braspag
			// para a entrega atual.
			switch(tipoTransacao) {
			case CapturaAutomatica:
			// case OutroTipoTransacao:
				transactionType = tipoTransacao.getValor();
				break;
				
			default:
				log.error("Tipo de transação '{}' desconsiderada para o provedor Braspag Pagador.", contratoCobranca.getTipoTransacaoProvedor());
				sucesso = false;
				break;
			}
			
		} else {
			log.error("Tipo de transação '{}' desconhecida para o provedor Braspag Pagador.", contratoCobranca.getTipoTransacaoProvedor());
			sucesso = false;
		}
		
		
		// VALIDAÇÕES
		
		if (requestId == null || requestId.isEmpty()
				|| version == null || version.isEmpty()
				|| merchantId == null || merchantId.isEmpty()
				|| orderId == null || orderId.isEmpty()
				// braspagOrderId
				// paymentMethod
				// amount
				// currency
				// country
				// saveCreditCard
				// serviceTaxAmount
				|| paymentPlan < 0
				|| transactionType < 0
				// cardExpirationDate
				|| numberOfPayments < 0
				// cardHolder
				) {
			sucesso = false;
		}

		
		// CHAMADA
		
		if (sucesso) {			
			// PREPARO DE DADOS DO PEDIDO
			
			OrderDataRequest dadosPedido = new OrderDataRequest();
			dadosPedido.setMerchantId(merchantId);
			dadosPedido.setOrderId(orderId);
			dadosPedido.setBraspagOrderId(braspagOrderId);
			
			
			// PREPARO DE DADOS DE PAGAMENTO
			
			ArrayOfPaymentDataRequest listaPagamentos = new ArrayOfPaymentDataRequest();
			CreditCardDataRequest dadosPagamento = new CreditCardDataRequest();
			// Dados gerais de pagamento
			dadosPagamento.setAmount(amount);
			dadosPagamento.setCountry(country);
			dadosPagamento.setCurrency(currency);
			dadosPagamento.setPaymentMethod(paymentMethod);
			// Dados de pagamento com cartão de crédito
			dadosPagamento.setCardHolder(cardHolder);
			dadosPagamento.setCardExpirationDate(cardExpirationDate);
			dadosPagamento.setCardNumber(cardNumber);
			dadosPagamento.setCardSecurityCode(cardSecurityCode);
			dadosPagamento.setNumberOfPayments(numberOfPayments);
			dadosPagamento.setSaveCreditCard(saveCreditCard);
			dadosPagamento.setServiceTaxAmount(serviceTaxAmount);
			dadosPagamento.setPaymentPlan(paymentPlan);
			dadosPagamento.setTransactionType(transactionType);
			listaPagamentos.getPaymentDataRequest().add(dadosPagamento);
			
	
			// PREPARO DOS DADOS DO CONSUMIDOR
	
			// Dados pessoais
			CustomerDataRequest dadosConsumidor = new CustomerDataRequest();
			dadosConsumidor.setCustomerEmail(email);
			dadosConsumidor.setCustomerName(nome);
			
			
			// PREPARO DO OBJETO FINAL DA REQUISIÇÃO
			
			AuthorizeTransactionRequest requisicaoAuth = new AuthorizeTransactionRequest();
			requisicaoAuth.setRequestId(requestId);
			requisicaoAuth.setVersion(version);
			requisicaoAuth.setOrderData(dadosPedido);
			requisicaoAuth.setPaymentDataCollection(listaPagamentos);
			requisicaoAuth.setCustomerData(dadosConsumidor);
			
			
			// CHAMANDO WEB SERVICE
			try {
				serviceFactory.verificarServico();

				if (modoDebug && codigoVenda.startsWith(prefixoNula)) {
					log.info("[MODODEBUG] Considerando que a transação à adquirente é nula (erro na transação).");
					throw new BillingWebServiceException("TST003");
				}
				
				AuthorizeTransactionResponse2 respostaAuth = serviceFactory.authorizeTransaction(requisicaoAuth);
				
				if (respostaAuth.getOrderData() != null) {
					retorno.setProvedorOrderId(respostaAuth.getOrderData().getBraspagOrderId());
				}
				
				boolean chegouNaAdquirente = respostaAuth.isSuccess();
				
				if (modoDebug && codigoVenda.startsWith(prefixoNaoChegou)) {
					log.info("[MODODEBUG] Considerando que a transação não chegou na adquirente.");
					chegouNaAdquirente = false;
				}

				if (respostaAuth.getErrorReportDataCollection() != null
						&& respostaAuth.getErrorReportDataCollection().getErrorReportDataResponse() != null
						&& respostaAuth.getErrorReportDataCollection().getErrorReportDataResponse().size() > 0) {
					ErrorReportDataResponse erro = respostaAuth.getErrorReportDataCollection().getErrorReportDataResponse().get(0);
					retorno.setRetornoProvedor(erro.getErrorCode());
					retorno.setMensagemProvedor(erro.getErrorMessage());
				}
				
				if (chegouNaAdquirente) {
					// Assume-se que só será recebido um PaymentDataResponse, pois apenas um PaymentDataRequest
					// foi enviado na requisição.
					CreditCardDataResponse creditoResponse = (CreditCardDataResponse) respostaAuth.getPaymentDataCollection().getPaymentDataResponse().get(0);
					StatusTransacaoCartaoCredito statusCartao = StatusTransacaoCartaoCredito.buscarPorValor(creditoResponse.getStatus());
					CodigoAdquirente codigoAdquirente = CodigoAdquirente.buscarPorValor(creditoResponse.getReturnCode());
					
					retorno.setIdTransacaoAdquirente(creditoResponse.getAcquirerTransactionId());
					retorno.setProvedorTransactionId(creditoResponse.getBraspagTransactionId());
					
					retorno.setRetornoAdquirente(creditoResponse.getReturnCode());
					retorno.setMensagemAdquirente(creditoResponse.getReturnMessage());
					
					if (modoDebug && codigoVenda.startsWith(prefixoNaoConfirmada)) {
						log.info("[MODODEBUG] Considerando transação não confirmada.");
						statusCartao = StatusTransacaoCartaoCredito.ErroDesqualificante;
					}

					switch (statusCartao) {
					case Capturada:
						retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.Autorizado);
						retorno.setCodigoAutorizacao(creditoResponse.getAuthorizationCode());
						retorno.setNumeroComprovanteVenda(creditoResponse.getProofOfSale());
						if (tipoCobranca.isRecorrencia()) {
							retorno.setTokenCartao(creditoResponse.getCreditCardToken());
						}
						break;
						
					case NaoAutorizada:
						if (codigoAdquirente == CodigoAdquirente.Timeout) {
							retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.TimeoutAdquirente);
							retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0011));
							
						} else {
							retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.NaoAutorizado);
							retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0010));
						}
						break;
						
					default:
						retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.NaoConfirmado);
						retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0011));
						break;
					}

				} else {
					retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.NaoChegouNaAdquirente);
					retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0011));
				}
				
			} catch (Exception ex) {
				log.error("Erro ao tentar comunicação com o AuthorizeTransaction do serviço Braspag PagadorTransaction.", ex);
				retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.Excecao);
				retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
				retorno.setTipoException(ex.getClass().getName());
				retorno.setMensagemException(ex.getMessage());
			}
			
		} else {
			retorno.setCodigoRetorno(CodigoRetornoAutorizarVenda.Excecao);
			retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0011));
			retorno.setTipoException("");
			retorno.setMensagemException("");
		}

		return log.saindo(retorno, false);
	}

	@Override
	public RetornoCancelarCobrancaPendente cancelarCobrancaPendente(Cobranca cobranca) {
		return cancelarCobrancaPendente(cobranca, null);
	}
	
	@Override
	public RetornoCancelarCobrancaPendente cancelarCobrancaPendente(Cobranca cobranca, Tentativa tentativa) {
		LoggerWrapper log = logger.entrando("cancelarCobrancaPendente", cobranca);
		RetornoCancelarCobrancaPendente retorno = null;
		Calendar calCaptura = DateUtils.getCalendarDataHoraAtual();
		Calendar calLimite = DateUtils.getCalendarDataHoraAtual();
		
		calCaptura.setTime(cobranca.getDataCobranca());
		DateUtils.removerHoras(calCaptura);
		DateUtils.removerHoras(calLimite);

		if (calCaptura.compareTo(calLimite) == 0) {
			// No mesmo dia, até às 23:59h.
			retorno = cancelarCobrancaPendentePorCancelamento(cobranca, tentativa);
			
		} else {
			retorno = cancelarCobrancaPendentePorEstorno(cobranca, tentativa);
		}
		
		return log.saindo(retorno);
	}
	
	private RetornoCancelarCobrancaPendente cancelarCobrancaPendentePorEstorno(Cobranca cobranca, Tentativa tentativa) {
		LoggerWrapper log = logger.entrando("cancelarCobrancaPendentePorEstorno", cobranca);
		IdentificadorProcesso identificadorProcesso = IdentificadorProcesso.EstornarCobranca;
		RetornoCancelarCobrancaPendente retorno = new RetornoCancelarCobrancaPendente();
		boolean sucesso = true;
		RefundCreditCardTransactionRequest requisicao = serviceFactory.newRefundCreditCardTransactionRequest();
		TransactionDataRequest dadosRequisicao = new TransactionDataRequest();
		ArrayOfTransactionDataRequest arrayDadosRequisicao = new ArrayOfTransactionDataRequest();
		BigDecimal valorBig = cobranca.getValorCobranca().multiply(new BigDecimal(100));
		ContratoCobranca contratoCobranca;
		cobranca = cobrancaDLO.completar(cobranca, "contratoCobranca");
		contratoCobranca = cobranca.getContratoCobranca();
		retorno.setEstorno(true);
		retorno.setStatus(StatusCancelamento.Erro);
		
		// Parâmetro desnecessário para nós, mas que é obrigatório nas
		// requisições feitas à Braspag. 
		String requestId = UUID.randomUUID().toString();
		
		String version = contratoCobranca.getVersaoContratoWs();
		String merchantId = contratoCobranca.getCodigoEmpresaNoProvedor();
		String braspagTransactionId = cobranca.getCodigoTransacaoProvedor();
		long amount = valorBig.longValue();

		if (version == null || version.isEmpty()
				|| merchantId == null || merchantId.isEmpty()
				|| braspagTransactionId == null || braspagTransactionId.isEmpty()) {

			sucesso = false;
			retorno.setCodigoRetorno(CodigoRetornoUtilitario.ErroValidacao);
			retorno.setMensagemRetorno(CodigoMensagem.Falha);
		}

		if (sucesso) {
			try {
				if (tentativa == null) {
					tentativa = cobrancaDLO.obterUltimaTentativa(cobranca);
				}
				
				gerarEventoInicialCancelamento(tentativa, identificadorProcesso);
				
				dadosRequisicao.setBraspagTransactionId(braspagTransactionId);
				dadosRequisicao.setAmount(amount);
				requisicao.setMerchantId(merchantId);
				requisicao.setRequestId(requestId);
				requisicao.setVersion(version);
				arrayDadosRequisicao.getTransactionDataRequest().add(dadosRequisicao);
				requisicao.setTransactionDataCollection(arrayDadosRequisicao);
			
				serviceFactory.verificarServico();
				
				RefundCreditCardTransactionResponse2 resposta = serviceFactory.refundCreditCardTransaction(requisicao);
				
				if (resposta.isSuccess() && resposta.getTransactionDataCollection().getTransactionDataResponse().size() > 0) {
					TransactionDataResponse dados = resposta.getTransactionDataCollection().getTransactionDataResponse().get(0);
					StatusTransacao status = StatusTransacao.buscarPorValor(dados.getStatus());
					if (status == null) {
						log.warn("Status nulo, desconhecido ou não mapeado retornado do método "
								+ "RefundCreditCardTransactionRequest do serviço PagadorTransaction. "
								+ "Valor: {}", dados.getStatus());
						retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
						retorno.setMensagemRetorno(CodigoMensagem.DPH0009);
						
					} else {
						retorno.setStatus(status.getStatusCancelamento());
						retorno.setCodigoRetorno(CodigoRetornoUtilitario.Sucesso);
						retorno.setMensagemRetorno(null);
						retorno.setCodigoRetornoAdquirente(dados.getReturnCode());
						retorno.setMensagemRetornoAdquirente(dados.getReturnMessage());
					}
					
				} else {
					ErrorReportDataResponse erro = resposta.getErrorReportDataCollection().getErrorReportDataResponse().get(0); 
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
					retorno.setMensagemRetorno(CodigoMensagem.DPH0011);
					retorno.setCodigoErroProvedor(erro.getErrorCode());
					retorno.setMensagemErroProvedor(erro.getErrorMessage());
				}

				gerarEventosCancelamento(tentativa, retorno, identificadorProcesso);
				
			} catch (Exception ex) {
				retorno.setCodigoRetorno(CodigoRetornoUtilitario.Excecao);
				retorno.setMensagemRetorno(CodigoMensagem.DPH0009);
				retorno.setTipoException(ex.getClass().getName());
				retorno.setMensagemException(ex.getLocalizedMessage());
			}
		}
		
		return log.saindo(retorno);
	}
	
	private RetornoCancelarCobrancaPendente cancelarCobrancaPendentePorCancelamento(Cobranca cobranca, Tentativa tentativa) {
		LoggerWrapper log = logger.entrando("cancelarCobrancaPendentePorCancelamento", cobranca);
		IdentificadorProcesso identificadorProcesso = IdentificadorProcesso.CancelarCobranca;
		RetornoCancelarCobrancaPendente retorno = new RetornoCancelarCobrancaPendente();
		boolean sucesso = true;
		VoidCreditCardTransactionRequest requisicao = new VoidCreditCardTransactionRequest();
		TransactionDataRequest dadosRequisicao = new TransactionDataRequest();
		ArrayOfTransactionDataRequest arrayDadosRequisicao = new ArrayOfTransactionDataRequest();
		BigDecimal valorBig = cobranca.getValorCobranca().multiply(new BigDecimal(100));
		ContratoCobranca contratoCobranca;
		cobranca = cobrancaDLO.completar(cobranca, "contratoCobranca");
		contratoCobranca = cobranca.getContratoCobranca();
		retorno.setEstorno(false);
		retorno.setStatus(StatusCancelamento.Erro);
		
		// Parâmetro desnecessário para nós, mas que é obrigatório nas
		// requisições feitas à Braspag. 
		String requestId = UUID.randomUUID().toString();
		
		String version = contratoCobranca.getVersaoContratoWs();
		String merchantId = contratoCobranca.getCodigoEmpresaNoProvedor();
		String braspagTransactionId = cobranca.getCodigoTransacaoProvedor();
		long amount = valorBig.longValue();

		if (version == null || version.isEmpty()
				|| merchantId == null || merchantId.isEmpty()
				|| braspagTransactionId == null || braspagTransactionId.isEmpty()) {

			sucesso = false;
			retorno.setCodigoRetorno(CodigoRetornoUtilitario.ErroValidacao);
			retorno.setMensagemRetorno(CodigoMensagem.Falha);
		}
		
		if (sucesso) {
			try {
				if (tentativa == null) {
					tentativa = cobrancaDLO.obterUltimaTentativa(cobranca);
				}
				
				gerarEventoInicialCancelamento(tentativa, identificadorProcesso);
				
				dadosRequisicao.setBraspagTransactionId(braspagTransactionId);
				dadosRequisicao.setAmount(amount);
				requisicao.setMerchantId(merchantId);
				requisicao.setRequestId(requestId);
				requisicao.setVersion(version);
				arrayDadosRequisicao.getTransactionDataRequest().add(dadosRequisicao);
				requisicao.setTransactionDataCollection(arrayDadosRequisicao);
				
				serviceFactory.verificarServico();
				
				VoidCreditCardTransactionResponse2 resposta = serviceFactory.voidCreditCardTransaction(requisicao);

				if (resposta.isSuccess() && resposta.getTransactionDataCollection().getTransactionDataResponse().size() > 0) {
					TransactionDataResponse dados = resposta.getTransactionDataCollection().getTransactionDataResponse().get(0);
					StatusTransacao status = StatusTransacao.buscarPorValor(dados.getStatus());
					
					if (status == null) {
						log.warn("Status nulo, desconhecido ou não mapeado retornado do método "
								+ "VoidCreditCardTransactionRequest do serviço PagadorTransaction. "
								+ "Valor: {}", dados.getStatus());
						retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
						retorno.setMensagemRetorno(CodigoMensagem.DPH0009);
						
					} else {
						retorno.setStatus(status.getStatusCancelamento());
						retorno.setCodigoRetorno(CodigoRetornoUtilitario.Sucesso);
						retorno.setMensagemRetorno(null);
						retorno.setCodigoRetornoAdquirente(dados.getReturnCode());
						retorno.setMensagemRetornoAdquirente(dados.getReturnMessage());
					}
					
				} else {
					ErrorReportDataResponse erro = resposta.getErrorReportDataCollection().getErrorReportDataResponse().get(0); 
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
					retorno.setMensagemRetorno(CodigoMensagem.DPH0011);
					retorno.setCodigoErroProvedor(erro.getErrorCode());
					retorno.setMensagemErroProvedor(erro.getErrorMessage());
				}
	
				gerarEventosCancelamento(tentativa, retorno, identificadorProcesso);
				
			} catch (Exception ex) {
				retorno.setCodigoRetorno(CodigoRetornoUtilitario.Excecao);
				retorno.setMensagemRetorno(CodigoMensagem.DPH0009);
				retorno.setTipoException(ex.getClass().getName());
				retorno.setMensagemException(ex.getLocalizedMessage());
			}
		}
		
		return log.saindo(retorno);
	}
	
	private void gerarEventoInicialCancelamento(Tentativa tentativa, IdentificadorProcesso identificadorProcessoCancelamento) throws DLOException {
		eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ChamadaMetodoGateway, identificadorProcessoCancelamento.getValor());
	}
	
	private void gerarEventosCancelamento(Tentativa tentativa, RetornoCancelarCobrancaPendente dadosCancelamento, IdentificadorProcesso identificadorProcessoCancelamento) throws DLOException {
		if (StatusCancelamento.Cancelado == dadosCancelamento.getStatus()) {
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.CobrancaCanceladaComSucesso, identificadorProcessoCancelamento.getValor());
			
		} else if (StatusCancelamento.Negado == dadosCancelamento.getStatus()) {
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.CobrancaNaoCancelada, identificadorProcessoCancelamento.getValor());
			
		} else if (StatusCancelamento.NaoEncontrado == dadosCancelamento.getStatus()) {
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.CobrancaNaoLocalizadaParaCancelamento, identificadorProcessoCancelamento.getValor());
			
		} else if (StatusCancelamento.Aceito == dadosCancelamento.getStatus()) {
			eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.SolicitacaoEstornoRecebida, identificadorProcessoCancelamento.getValor());
		}
	}
}
