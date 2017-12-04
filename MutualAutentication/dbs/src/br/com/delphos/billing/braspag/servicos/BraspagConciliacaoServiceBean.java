package br.com.delphos.billing.braspag.servicos;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.braspag.conciliador.ConciliatedTransaction;
import br.com.delphos.billing.braspag.conciliador.ConciliationFile;
import br.com.delphos.billing.braspag.conciliador.Event;
import br.com.delphos.billing.braspag.conciliador.ProcessingType;
import br.com.delphos.billing.braspag.conciliador.SaleTransactionData;
import br.com.delphos.billing.braspag.conciliador.cliente.ErrorReport;
import br.com.delphos.billing.braspag.conciliador.cliente.GetExportedFileV2Request;
import br.com.delphos.billing.braspag.conciliador.cliente.GetExportedFileV2Response2;
import br.com.delphos.billing.braspag.constantes.AdquirenteConciliacao;
import br.com.delphos.billing.braspag.util.BraspagServiceFactories;
import br.com.delphos.billing.braspag.util.PagadorReconciliationFactory;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.cobrancas.Cobranca_;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterface;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterfaceDLO;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ControleConciliacaoDLO;
import br.com.delphos.billing.conciliacoes.EventoConciliacaoDLO;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEventoDLO;
import br.com.delphos.billing.conciliacoes.ProcessamentoConciliacao;
import br.com.delphos.billing.conciliacoes.ProcessamentoConciliacaoDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.CodigoRetornoUtilitario;
import br.com.delphos.billing.enumeracoes.MotivoCancelamento;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.enumeracoes.StatusProcConciliacaoEvento;
import br.com.delphos.billing.enumeracoes.StatusVenda;
import br.com.delphos.billing.enumeracoes.TipoConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.enumeracoes.TipoMensagemConciliacao;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.servicos.ConciliacaoService;
import br.com.delphos.billing.servicos.retornos.RetornoBaixarArquivoConciliacao;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.util.CurrencyUtils;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.billing.vendas.Venda_;

@Stateless(name = "ConciliacaoServiceBean")
public class BraspagConciliacaoServiceBean implements ConciliacaoService, Serializable {
	private static final long serialVersionUID = 1L;
	public static Logger LOGGER = LoggerFactory.getLogger(BraspagConsultaServiceBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	@EJB(lookup = "java:global/dbsdb/ControleConciliacaoDLOBean")
	private ControleConciliacaoDLO controleConciliacaoDLO;
	
	@EJB(lookup = "java:global/dbsdb/AdquirenteDLOBean")
	private AdquirenteDLO adquirenteDLO;
	
	@EJB(lookup = "java:global/dbsdb/ContratoCobrancaDLOBean")
	private ContratoCobrancaDLO contratoCobrancaDLO;
	
	@EJB(lookup = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;
	
	@EJB(lookup = "java:global/dbsdb/ProcessamentoConciliacaoDLOBean")
	private ProcessamentoConciliacaoDLO processamentoConciliacaoDLO;

	@EJB(lookup = "java:global/dbsdb/ProcConciliacaoEventoDLOBean")
	private ProcConciliacaoEventoDLO procConciliacaoEventoDLO;

	@EJB(lookup = "java:global/dbsdb/EventoConciliacaoDLOBean")
	private EventoConciliacaoDLO eventoConciliacaoDLO;

	@EJB(lookup = "java:global/dbsdb/VendaDLOBean")
	private VendaDLO vendaDLO;

	@EJB(lookup = "java:global/dbsdb/BandeiraDLOBean")
	private BandeiraDLO bandeiraDLO;
	
	@EJB(lookup = "java:global/dbsdb/ConciliacaoInterfaceDLOBean")
	private ConciliacaoInterfaceDLO conciliacaoInterfaceDLO;
	
	private PagadorReconciliationFactory serviceFactory = BraspagServiceFactories.newPagadorReconciliationFactory();
	
	@Override
	public RetornoBaixarArquivoConciliacao baixarArquivoConciliacao(ContratoCobranca contratoCobranca,
			Adquirente adquirente, Date dataMovimento, ControleConciliacao controleConciliacao) throws BillingException {		
		LoggerWrapper log = logger.entrando("baixarArquivoConciliacao");
		boolean sucesso = true;
		RetornoBaixarArquivoConciliacao retorno = new RetornoBaixarArquivoConciliacao();
		Empresa empresa = contratoCobranca.getEmpresa();
		boolean novoControleConciliacao = controleConciliacao == null;
		
		if (dataMovimento == null && !novoControleConciliacao) {
			dataMovimento = controleConciliacao.getDataMovimento();
		}
		
		// Parâmetro desnecessário para nós, mas que é obrigatório nas
		// requisições feitas à Braspag. 
		String requestId = UUID.randomUUID().toString();
		
		String requestingUserName = empresa.getUsuarioConciliador();
		String requestingPassword = empresa.getSenhaConciliador();
		Integer merchantId = contratoCobranca.getCodigoEmpresaConciliador();
		Integer acquirerId = adquirente.getCodigoAfiliacaoConciliador();
		Calendar referenceDate = DateUtils.getCalendarDataAtual();
		AdquirenteConciliacao adquirenteConciliacao = AdquirenteConciliacao.buscarPorValor(acquirerId); 
		short fileExtensionType = 2; // XML
		int fileType = 1; // Arquivo de conciliação
		
		GetExportedFileV2Request requisicao = serviceFactory.newGetExportedFileV2Request();

		try {
			
			if (merchantId == null
					|| acquirerId == null
					|| adquirenteConciliacao == null
					|| (!novoControleConciliacao && controleConciliacao.getDataMovimento().compareTo(dataMovimento) != 0)) {
				sucesso = false;
				retorno.setCodigoRetorno(CodigoRetornoUtilitario.ErroValidacao);
				retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.Falha));
			}

			if (sucesso) {
				referenceDate.setTime(dataMovimento);
				referenceDate = DateUtils.removerHoras(referenceDate);
				
				requisicao.setAcquirerId(acquirerId.shortValue());
				requisicao.setFileExtensionType(fileExtensionType);
				requisicao.setFileType(fileType);
				requisicao.setMerchantId(merchantId);
				requisicao.setReferenceDate(referenceDate);
				requisicao.setRequestId(requestId);
				requisicao.setRequestingPassword(requestingPassword);
				requisicao.setRequestingUserName(requestingUserName);
				
				serviceFactory.verificarServico();				
				GetExportedFileV2Response2 resposta = serviceFactory.getExportedFileV2(requisicao);
				
				if (resposta.getErrorReportCollection() != null
						&& resposta.getErrorReportCollection().getErrorReport() != null
						&& resposta.getErrorReportCollection().getErrorReport().size() > 0) {
					ErrorReport erro = resposta.getErrorReportCollection().getErrorReport().get(0);
					retorno.setCodigoErroProvedor(String.valueOf(erro.getCode()));
					retorno.setMensagemErroProvedor(erro.getMessage());
				}					
				
				if (!resposta.isSuccess()) {
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Falha);
					retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
					
				} else {
					retorno.setCodigoRetorno(CodigoRetornoUtilitario.Sucesso);
					retorno.setMensagemRetorno(null);
					byte[] conteudoArquivo = resposta.getFileContent();
					boolean arquivoVazio = conteudoArquivo == null || conteudoArquivo.length == 0;
					
					if (arquivoVazio) {
						log.error("Método GetExportedFileV2 do serviço PagadorReconciliation retornou um arquivo sem dados."
								+ " Adquirente: {{}},"
								+ " Contrato de Cobrança: {{}},"
								+ " data da movimentação: {},"
								+ " controle de conciliação anterior: {{}}.",
								adquirente,
								contratoCobranca,
								dataMovimento,
								controleConciliacao);  
						conteudoArquivo = new byte[0];
					}

					if (novoControleConciliacao) {
						controleConciliacao = new ControleConciliacao();
						controleConciliacao.setAdquirente(adquirente.getNome());
						controleConciliacao.setDataMovimento(dataMovimento);
						
					} else if (controleConciliacao.getId() == null) {
						log.error("Passando Controle de Conciliação inválido (não persistido) para chamada ao método baixarArquivoConciliacao.");
						throw new BillingException(CodigoMensagem.Falha);
					}

					controleConciliacao.setArquivo(conteudoArquivo);
					controleConciliacao.setDataImportacaoArquivo(DateUtils.getDataHoraAtual());
					controleConciliacao.setStatus(StatusControleConciliacao.Pendente);
										
					retorno.setControleConciliacao(controleConciliacao);
				}
			}

		} catch (Exception ex) {
			log.error("Erro ao tentar comunicação com o GetExportedFileV2 do serviço Braspag PagadorReconciliation.", ex);
			retorno.setCodigoRetorno(CodigoRetornoUtilitario.Excecao);
			retorno.setMensagemRetorno(Mensagens.get(CodigoMensagem.DPH0009));
			retorno.setTipoException(ex.getClass().getName());
			retorno.setMensagemException(ex.getLocalizedMessage());
		}		
		
		return log.saindo(retorno);
	}
	
	@Override
	public ControleConciliacao validarArquivoConciliacao(ControleConciliacao controle) throws BillingException {
		boolean valido = controle != null && controle.getArquivo() != null;
		
		if (valido) {
			ConciliationFile arquivo = lerArquivoConciliacao(controle);
			Adquirente adquirente = new Adquirente();
			adquirente.setNome(controle.getAdquirente());
			adquirente = adquirenteDLO.obterPorIdentidade(adquirente);
			valido = valido && arquivo != null;
			valido = valido && isTipoArquivoValido(controle, arquivo);                   // 3
			valido = valido && existemEventosNoArquivo(controle, arquivo);               // 7
			valido = valido && isCodigoAfiliacaoValido(controle, arquivo, adquirente);   // 1
			valido = valido && isPeriodoValido(controle, arquivo);                       // 2
			valido = valido && isEventosConciliacaoValidos(controle, arquivo);           // 4
			valido = valido && existemAffiliationInformationalEvents(controle, arquivo); // 5
			valido = valido && isCondicaoFimDeSemanaValida(controle, arquivo);           // 6
		}
		
		return controle;
	}

	@Override
	public void processarArquivoConciliacao(ControleConciliacao controle) throws BillingException {
		ConciliationFile arquivo = lerArquivoConciliacao(controle);
		
		// Prossegue apenas se houver conciliação no arquivo.
		boolean possuiConciliacoes = arquivo.getConciliatedTransactions() != null
				&& arquivo.getConciliatedTransactions().getConciliatedTransaction() != null
				&& !arquivo.getConciliatedTransactions().getConciliatedTransaction().isEmpty();

		controle.setDataProcessamentoArquivo(DateUtils.getDataHoraAtual());
		controle.setStatus(StatusControleConciliacao.Processado);
		
		if (controle.isArquivoVazio()) {
			logger.info("Controle de conciliação {{}} está vazio.", controle);
			
		} else if (!possuiConciliacoes) {
			logger.warn("Não há conciliações no arquivo do controle de conciliação {{}}.", controle);
			
		} else {
			logger.info("Processando controle de conciliação {{}}...", controle);
			
			for (ConciliatedTransaction transacao : arquivo.getConciliatedTransactions().getConciliatedTransaction()) {
				ContratoCobranca contratoCobranca = controleConciliacaoDLO.obterContratoCobrancaAssociado(controle);
				ProcessamentoConciliacao processamento = persistirProcessamentoConciliacao(controle, arquivo, transacao, contratoCobranca);
				
				// Prossegue apenas se houver eventos no processo da transação
				boolean possuiAccountingEvents = transacao.getAccountingEvents() != null
						&& transacao.getAccountingEvents().getEvent() != null
						&& !transacao.getAccountingEvents().getEvent().isEmpty();

				boolean possuiInformationalEvents = transacao.getInformationalEvents() != null
						&& transacao.getInformationalEvents().getEvent() != null
						&& !transacao.getInformationalEvents().getEvent().isEmpty();
				
				if (possuiAccountingEvents) {
					processarEventosTransacao(controle, arquivo, transacao, processamento, transacao.getAccountingEvents().getEvent());
				}

				if (possuiInformationalEvents) {
					processarEventosTransacao(controle, arquivo, transacao, processamento, transacao.getInformationalEvents().getEvent());
				}
				
				if (!possuiAccountingEvents && !possuiInformationalEvents) {
					logger.warn("Não há eventos para serem processados pela transação de ID {} no controle de conciliação {{}}.",
							transacao.getSaleData().getTransactionId(), controle);
				}
			}
		}
		
		controleConciliacaoDLO.salvar(controle);
	}

	private void processarEventosTransacao(ControleConciliacao controle, ConciliationFile arquivo,
			ConciliatedTransaction transacao, ProcessamentoConciliacao processamento, List<Event> eventos)
					throws BillingException, DLOException {
		for (Event eventoTransacao : eventos) {
			
			if (typeIdsValidosParaProcessamento.contains(new Integer(eventoTransacao.getTypeId()))) {
				long orderId = Long.parseLong(transacao.getSaleData().getOrderId());
				Cobranca cobranca = cobrancaDLO.obter(orderId);
				ProcConciliacaoEvento eventoProcessamento = persistirProcConciliacaoEvento(controle, arquivo, processamento, eventoTransacao, "AccountingEvents");
										
				if (cobranca != null) {
					
					if (eventoTransacao.getCategoryId() == 3) {
						tratarEventoContabilComoCaptura(controle, eventoProcessamento, cobranca);
						
					} else if (eventoTransacao.getCategoryId() == 4) {
						tratarEventoContabilComoPagamento(controle, eventoProcessamento, cobranca);
						
					} else if (eventoTransacao.getCategoryId() == 6) {
						tratarEventoContabilComoAceleracao(controle,
								eventoProcessamento, cobranca);
						
					} else if (eventoTransacao.getCategoryId() == 8) {
						tratarEventoContabilComoEstorno(controle, eventoProcessamento, cobranca);
					
					} else if (eventoTransacao.getCategoryId() == 9) {
						tratarEventoContabilComoChargeback(controle, eventoProcessamento, cobranca);
						
					} else if (eventoTransacao.getCategoryId() == 13) {
						tratarEventoContabilComoReagendamento(controle, eventoProcessamento, cobranca);
						
					} else {
						persistirConciliacaoInterface(TipoConciliacaoInterface.Outros,
								controle, eventoProcessamento, cobranca);
						
						salvarEventoProcessamento(eventoProcessamento,
								StatusProcConciliacaoEvento.ANULADO, CodigoMensagem.DPH0030);
					}
					
				} else {
					logger.error("Cobrança de ID {} não localizada para transação de ID {} no controle de conciliação {{}}.",
							transacao.getSaleData().getOrderId(), transacao.getSaleData().getTransactionId(), controle);
					
					persistirConciliacaoInterface(TipoConciliacaoInterface.CobrancaNaoIdentificada,
							controle, eventoProcessamento, cobranca);
					
					salvarEventoProcessamento(eventoProcessamento,
							StatusProcConciliacaoEvento.REJEITADO, CodigoMensagem.DPH0031);
				}
				
			} else {
				logger.warn("EventId '{}'"
						+ " para o processamento de conciliação {{}}"
						+ " (Controle de conciliação {{}}) não possui um TypeId"
						+ " válido para processamento (TypeId real: {}).",
						eventoTransacao.getEventId(), 
						processamento, controle, eventoTransacao.getTypeId());
			}
		}
	}

	private boolean isCodigoAfiliacaoValido(ControleConciliacao controle, ConciliationFile arquivo, Adquirente adquirente) throws BillingException {
		boolean valido = adquirente != null && adquirente.getCodigoAfiliacaoConciliador() != null;
		
		if (valido
				&& arquivo.getConciliatedTransactions() != null
				&& arquivo.getConciliatedTransactions().getConciliatedTransaction() != null
				&& !arquivo.getConciliatedTransactions().getConciliatedTransaction().isEmpty()) {
			
			Iterator<ConciliatedTransaction> itConciliatedTransaction = 
					arquivo.getConciliatedTransactions().getConciliatedTransaction().iterator();
			
			while (valido && itConciliatedTransaction.hasNext()) {
				ConciliatedTransaction conciliatedTransaction = itConciliatedTransaction.next();
				SaleTransactionData saleData = conciliatedTransaction.getSaleData();
				String codigoNaVenda = new Long(saleData.getAffiliationCode()).toString();
				
				valido = valido 
						&& saleData != null 
						&& adquirente.getCodigoConvenio().compareTo(codigoNaVenda) == 0;
			}
		}
		
		if (!valido) {
			controle.setStatus(StatusControleConciliacao.Rejeitado);
			controle.setTipoMensagemConciliacao(TipoMensagemConciliacao.CodigoAfiliacaoInvalido);
		}
		
		return valido;
	}
	
	private boolean isPeriodoValido(ControleConciliacao controle, ConciliationFile arquivo) throws BillingException {
		Calendar periodoInicial = DateUtils.removerHoras((Calendar) arquivo.getStartPeriod().clone());
		Calendar periodoFinal = DateUtils.removerHoras((Calendar) arquivo.getEndPeriod().clone());
		boolean valido = periodoInicial.equals(periodoFinal);
		valido = valido && periodoInicial.getTime().equals(controle.getDataMovimento());
		
		if (!valido) {
			controle.setStatus(StatusControleConciliacao.Rejeitado);
			controle.setTipoMensagemConciliacao(TipoMensagemConciliacao.ArquivoMaisDeUmDiaMovimento);
		}
		
		return valido;
	}

	private boolean isTipoArquivoValido(ControleConciliacao controle, ConciliationFile arquivo) throws BillingException {
		boolean valido = arquivo.getProcessingType() == ProcessingType.DAILY
				|| arquivo.getProcessingType() == ProcessingType.REPROCESSED;
		
		if (!valido) {
			controle.setStatus(StatusControleConciliacao.Rejeitado);
			controle.setTipoMensagemConciliacao(TipoMensagemConciliacao.ArquivoComMaisDeUmDiaMovimento);
		}
		
		return valido;
	}

	private boolean isEventosConciliacaoValidos(ControleConciliacao controle, ConciliationFile arquivo) throws BillingException {
		boolean valido = true;
		boolean possuiAffiliationAccountingEvents =
				arquivo.getAffiliationAccountingEvents() != null
				&& arquivo.getAffiliationAccountingEvents().getEvent() != null
				&& !arquivo.getAffiliationAccountingEvents().getEvent().isEmpty();
		
		if (possuiAffiliationAccountingEvents) {
			Iterator<Event> it = arquivo.getAffiliationAccountingEvents().getEvent().iterator();
			
			while (valido && it.hasNext()) {
				Event event = it.next();
				valido = valido && categoryIdsValidosParaEventosAffiliationAccounting.contains(new Integer(event.getCategoryId()));
			}
		}

		if (!valido) {
			controle.setStatus(StatusControleConciliacao.Rejeitado);
			controle.setTipoMensagemConciliacao(TipoMensagemConciliacao.ArquivoPossuiMovimentoComErro);
		}
				
		return valido;
	}

	private boolean existemAffiliationInformationalEvents(ControleConciliacao controle, ConciliationFile arquivo) throws BillingException {
		boolean valido = arquivo.getAffiliationInformationalEvents() == null
				|| arquivo.getAffiliationInformationalEvents().getEvent() == null
				|| arquivo.getAffiliationInformationalEvents().getEvent().isEmpty();

		if (!valido) {
			controle.setStatus(StatusControleConciliacao.Rejeitado);
			controle.setTipoMensagemConciliacao(TipoMensagemConciliacao.ArquivoPossuiEventoAfiliacaoInformativo);
		}
		
		return valido;
	}

	private boolean isCondicaoFimDeSemanaValida(ControleConciliacao controle, ConciliationFile arquivo) throws BillingException {
		Calendar periodoInicial = DateUtils.removerHoras((Calendar) arquivo.getStartPeriod().clone());
		int diaSemana = periodoInicial.get(Calendar.DAY_OF_WEEK);
		boolean valido = true;
		controle.setValorArquivo(BigDecimal.ZERO);
		
		if (arquivo.getAffiliationAccountingEvents() != null
				&& arquivo.getAffiliationAccountingEvents().getEvent() != null
				&& !arquivo.getAffiliationAccountingEvents().getEvent().isEmpty()) {
			
			somarValoresAccountingEvents(controle, arquivo, 
					arquivo.getAffiliationAccountingEvents().getEvent());
		}
		
		if (arquivo.getConciliatedTransactions() != null
				&& arquivo.getConciliatedTransactions().getConciliatedTransaction() != null
				&& !arquivo.getConciliatedTransactions().getConciliatedTransaction().isEmpty()) {
			
			for (ConciliatedTransaction transacao : 
				arquivo.getConciliatedTransactions().getConciliatedTransaction()) {
				
				if (transacao.getAccountingEvents() != null
						&& transacao.getAccountingEvents().getEvent() != null
						&& !transacao.getAccountingEvents().getEvent().isEmpty()) {
					
					somarValoresAccountingEvents(controle, arquivo, 
							transacao.getAccountingEvents().getEvent());
				}
			}
		}
		
		if (diaSemana == Calendar.SUNDAY || diaSemana == Calendar.SATURDAY) {
			if (controle.getValorArquivo().compareTo(BigDecimal.ZERO) == 0) {
				controle.setStatus(StatusControleConciliacao.Liberado);
				
			} else {
				valido = false;
				controle.setStatus(StatusControleConciliacao.Rejeitado);
				controle.setTipoMensagemConciliacao(
						TipoMensagemConciliacao.ArquivoComMovimentoFinanceiroNoFimDeSemana);
			}
		}
				
		return valido;
	}

	private void somarValoresAccountingEvents(ControleConciliacao controle, ConciliationFile arquivo, List<Event> eventos) {
		for (Event event : eventos) {
			if (categoryIdsValidosParaNetAmount.contains(
					new Integer(event.getCategoryId()))) {
				BigDecimal valorArquivo = controle.getValorArquivo();
				BigDecimal valorEvento = CurrencyUtils.fromLong(event.getNetAmount());
				controle.setValorArquivo(valorArquivo.add(valorEvento));
			}
		}
	}
	
	private boolean existemEventosNoArquivo(ControleConciliacao controle, ConciliationFile arquivo) throws BillingException {
		boolean valido = true;
				
		// Primeiro, é necessário determinar se existem transações no arquivo.
		// Se não houverem, o arquivo está vazio.
		boolean possuiTransacoes = arquivo.getConciliatedTransactions() != null
			&& arquivo.getConciliatedTransactions().getConciliatedTransaction() != null
			&& !arquivo.getConciliatedTransactions().getConciliatedTransaction().isEmpty();
		
		boolean possuiAccountingEvents = false;
		boolean possuiInformationalEvents = false;
		
		if (possuiTransacoes) {
			// Havendo transações, iteramos uma a uma.
			Iterator<ConciliatedTransaction> iteradorTransacoes = 
					arquivo.getConciliatedTransactions().getConciliatedTransaction().iterator();
			boolean finalizado = !iteradorTransacoes.hasNext(); // Poderia ser false
			
			while (!finalizado) {
				ConciliatedTransaction transacao = iteradorTransacoes.next();
				
				// Determinamos de antemão se há eventos nas listas de eventos.
				boolean transacaoPossuiAccountingEvents = 
						transacao.getAccountingEvents() != null
						&& transacao.getAccountingEvents().getEvent() != null
						&& !transacao.getAccountingEvents().getEvent().isEmpty();
				
				boolean transacaoPossuiInformationalEvents = 
						transacao.getInformationalEvents() != null
						&& transacao.getInformationalEvents().getEvent() != null
						&& !transacao.getInformationalEvents().getEvent().isEmpty();
				
				// A verificação se dá através dos categoryIds dos eventos (em um outro método).
				// Não há a necessidade de verificar se existe um evento de accounting/informational
				// se já identificamos ao menos um deste em uma transação anterior.
				if (transacaoPossuiAccountingEvents && !possuiAccountingEvents) {
					possuiAccountingEvents = possuiAccountingEvents
							|| possuiEventosValidosParaNetAmount(transacao, transacao.getAccountingEvents().getEvent());
				}
				
				if (transacaoPossuiInformationalEvents && !possuiInformationalEvents) {
					possuiInformationalEvents = possuiInformationalEvents
							|| possuiEventosValidosParaNetAmount(transacao, transacao.getInformationalEvents().getEvent());
				}
				
				// Terminamos na exaustão da lista ou ao encontrarmos um evento de cada
				// tipo (não há sentido em continuar a busca, pois queremos achar pelo menos um
				// de cada tipo).
				finalizado = !iteradorTransacoes.hasNext() || possuiAccountingEvents || possuiInformationalEvents;
			}
		}
		
		if (!possuiAccountingEvents && !possuiInformationalEvents) {
			controle.setStatus(StatusControleConciliacao.Liberado);
			controle.setArquivoVazio(true);
			
		} else {
			controle.setArquivoVazio(false);
		}
		
		return valido;
	}
	
	private boolean possuiEventosValidosParaNetAmount(ConciliatedTransaction transacao, List<Event> eventos) {
		boolean possuiEventos = eventos.size() > 0;
//		Iterator<Event> it = eventos.iterator();
//		
//		while (!possuiEventos && it.hasNext()) {
//			Event evento = it.next();
//			
//			possuiEventos = possuiEventos 
//					|| categoryIdsValidosParaNetAmount.contains(evento.getCategoryId());
//		}
		
		return possuiEventos;
	}
	
	private ConciliationFile lerArquivoConciliacao(ControleConciliacao controle) {
		Reader reader = new BufferedReader(
				new InputStreamReader(
						new ByteArrayInputStream(controle.getArquivo())
				)
		);
		
		try {
			ConciliationFile retorno = JAXB.unmarshal(reader, ConciliationFile.class);
			return retorno;
			
		} catch (RuntimeException ex) {
			logger.error("Erro ao criar objeto ConciliationFile do"
					+ " controle de conciliação {{}}.", controle, ex);
			throw ex;
		}
	}
	private void tratarEventoContabilComoCaptura(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) 
					throws DLOException {
		salvarEventoProcessamento(eventoProcessamento, StatusProcConciliacaoEvento.ATIVO);

		eventoConciliacaoDLO.gerarHistoricoEvento(cobranca,
				TipoEvento.CapturaConciliada, null, eventoProcessamento.getTransactionInstallment());
	}

	private void tratarEventoContabilComoPagamento(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) throws DLOException {
		boolean eventoValido = isEventoContabilValidoPorMargemStatus(
				controle, eventoProcessamento, cobranca, statusesCobrancaValidosParaPagamento);
		
		if (eventoValido) {
			boolean movimentoConciliado = cobrancaDLO.isMovimentoConciliado(
					cobranca, eventoProcessamento.getTransactionInstallment());
			
			if (!movimentoConciliado) {
				switch (cobranca.getStatusCobranca()) {
				case Estornada:
				case EstornoConciliado:
					persistirConciliacaoInterface(
							TipoConciliacaoInterface.CobrancaComEstorno,	
							controle, eventoProcessamento, cobranca);
					break;
					
				case EstornoNegado:
					persistirConciliacaoInterface(
							TipoConciliacaoInterface.Cobranca,	
							controle, eventoProcessamento, cobranca);
					break;
					
				default:
					persistirConciliacaoInterface(
							TipoConciliacaoInterface.Cobranca,	
							controle, eventoProcessamento, cobranca);
					
					if (cobranca.getNumeroParcela() == cobranca.getVenda().getQuantidadeParcelas()) {
						salvarCobranca(cobranca, StatusCobranca.CobradaConciliada);
						
					} else {
						salvarCobranca(cobranca, StatusCobranca.ParcialmenteCobrada);
					}
					
					break;
				}

				salvarEventoProcessamento(eventoProcessamento, StatusProcConciliacaoEvento.ATIVO);
				
				eventoConciliacaoDLO.gerarHistoricoEvento(
						cobranca, TipoEvento.CobrancaPaga, null,
						eventoProcessamento.getTransactionInstallment());
				
			} else {
				salvarEventoProcessamento(eventoProcessamento,
						StatusProcConciliacaoEvento.REJEITADO, CodigoMensagem.DPH0036);
				
				persistirConciliacaoInterface(TipoConciliacaoInterface.CobrancaConciliada,	
						controle, eventoProcessamento, cobranca);
			}
		}			
	}

	private void tratarEventoContabilComoAceleracao(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) throws BillingException {
		boolean eventoValido = isEventoContabilValidoPorMargemStatus(controle, eventoProcessamento,
				cobranca, statusesCobrancaValidosParaAceleracao);
		
		if (eventoValido) {
			boolean movimentoConciliado = cobrancaDLO.isMovimentoConciliado(
					cobranca, eventoProcessamento.getTransactionInstallment());
			
			if (!movimentoConciliado) {
				persistirConciliacaoInterface(TipoConciliacaoInterface.CobrancaComEstorno,
						controle, eventoProcessamento, cobranca);
				
				salvarEventoProcessamento(eventoProcessamento, StatusProcConciliacaoEvento.ATIVO);
				
				eventoConciliacaoDLO.gerarHistoricoEvento(
						cobranca, TipoEvento.AceleracaoConciliada, null,
						eventoProcessamento.getTransactionInstallment());
				
			} else {

				persistirConciliacaoInterface(TipoConciliacaoInterface.CobrancaConciliada,
						controle, eventoProcessamento, cobranca);
				
				salvarEventoProcessamento(eventoProcessamento,
						StatusProcConciliacaoEvento.REJEITADO, CodigoMensagem.DPH0032);
				
				procConciliacaoEventoDLO.salvar(eventoProcessamento);
			}
		}		
	}

	private void tratarEventoContabilComoEstorno(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) throws DLOException {
		boolean eventoValido = isEventoContabilValidoPorMargemStatus(controle, eventoProcessamento,
				cobranca, statusesCobrancaValidosParaEstorno);
		
		if (eventoValido) {
			boolean movimentoConciliado = cobrancaDLO.isMovimentoConciliado(
					cobranca, eventoProcessamento.getTransactionInstallment());
			
			if (!movimentoConciliado) {
				persistirConciliacaoInterface(TipoConciliacaoInterface.Estorno,
						controle, eventoProcessamento, cobranca);
				
				salvarCobranca(cobranca, StatusCobranca.EstornoConciliado);
				
				salvarEventoProcessamento(eventoProcessamento, StatusProcConciliacaoEvento.REJEITADO);
				
				eventoConciliacaoDLO.gerarHistoricoEvento(
						cobranca, TipoEvento.EstornoConciliado, null,
						eventoProcessamento.getTransactionInstallment());
				
			} else {
				persistirConciliacaoInterface(TipoConciliacaoInterface.EstornoConciliado,
						controle, eventoProcessamento, cobranca);
				
				salvarEventoProcessamento(eventoProcessamento,
						StatusProcConciliacaoEvento.REJEITADO, CodigoMensagem.DPH0033);
			}
		}
	}

	private void tratarEventoContabilComoChargeback(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) throws DLOException {
		Venda venda = cobranca.getVenda();
		
		persistirConciliacaoInterface(TipoConciliacaoInterface.Chargeback,
				controle, eventoProcessamento, cobranca);

		salvarCobranca(cobranca, StatusCobranca.Cancelada);
		
		salvarVenda(venda, MotivoCancelamento.CancelamentoPorChargeBack);
		
		salvarEventoProcessamento(eventoProcessamento, StatusProcConciliacaoEvento.ATIVO);

		eventoConciliacaoDLO.gerarHistoricoEvento(
				cobranca, TipoEvento.ReagendamentoConciliado, null,
				eventoProcessamento.getTransactionInstallment());
	}

	private void tratarEventoContabilComoReagendamento(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) throws DLOException {

		salvarEventoProcessamento(eventoProcessamento, StatusProcConciliacaoEvento.ATIVO);
		
		eventoConciliacaoDLO.gerarHistoricoEvento(
				cobranca, TipoEvento.ReagendamentoConciliado, null,
				eventoProcessamento.getTransactionInstallment());
	}

	private boolean isEventoContabilValidoPorMargemAceitacao(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca)
					throws DLOException {

		BigDecimal valorConciliado = eventoProcessamento.getGrossAmount();
		boolean dentroDaMargemAceitacao = cobrancaDLO.isValorNaMargemAceitacao(
				cobranca, valorConciliado, eventoProcessamento.getTransactionInstallment());
		
		if (!dentroDaMargemAceitacao) {
			persistirConciliacaoInterface(TipoConciliacaoInterface.RejeitadoPeloDbs,	
					controle, eventoProcessamento, cobranca);
			salvarEventoProcessamento(eventoProcessamento,
					StatusProcConciliacaoEvento.REJEITADO, CodigoMensagem.DPH0034);
		}
		
		return dentroDaMargemAceitacao;
	}

	private boolean isEventoContabilValidoPorMargemStatus(ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca,
			Set<StatusCobranca> statusesValidos)
					throws DLOException {		
		StatusCobranca statusCobranca = cobranca.getStatusCobranca();
		boolean eventoValido = isEventoContabilValidoPorMargemAceitacao(
				controle, eventoProcessamento, cobranca);
		
		if (eventoValido && !statusesValidos.contains(statusCobranca)) {
			eventoValido = false;
			persistirConciliacaoInterface(TipoConciliacaoInterface.RejeitadoPeloDbs,	
					controle, eventoProcessamento, cobranca);
			salvarEventoProcessamento(eventoProcessamento,
					StatusProcConciliacaoEvento.REJEITADO, CodigoMensagem.DPH0035);
		}
		
		return eventoValido;
	}

	private void salvarEventoProcessamento(
			ProcConciliacaoEvento eventoProcessamento, 
			StatusProcConciliacaoEvento status) throws DLOException {
		salvarEventoProcessamento(eventoProcessamento, status, null);
	}
	
	private void salvarEventoProcessamento(
			ProcConciliacaoEvento eventoProcessamento, 
			StatusProcConciliacaoEvento status,
			CodigoMensagem mensagem) throws DLOException {
		eventoProcessamento.setStatus(status);
		if (mensagem != null) {
			eventoProcessamento.setCodigoMensagemProcessamento(mensagem);
		}
		procConciliacaoEventoDLO.salvar(eventoProcessamento);
	}
	
	private void salvarCobranca(Cobranca cobranca, StatusCobranca status) throws DLOException {
		cobranca.setStatusCobranca(status);
		cobrancaDLO.salvar(cobranca);
	}

	private void salvarVenda(Venda venda, MotivoCancelamento motivo) throws DLOException {
		salvarVenda(venda, null, motivo);
	}
	
	private void salvarVenda(Venda venda, StatusVenda status, MotivoCancelamento motivo) throws DLOException {
		if (status != null) {
			venda.setStatus(status);
		}
		if (motivo != null) {
			venda.setCodigoMotivoCancelamento(motivo);
		}
		vendaDLO.salvar(venda);
	}
	
	private ProcessamentoConciliacao persistirProcessamentoConciliacao(ControleConciliacao controle,
			ConciliationFile arquivo, 
			ConciliatedTransaction transacao,
			ContratoCobranca contratoCobranca) throws BillingException {
		ProcessamentoConciliacao processamento = new ProcessamentoConciliacao();
		
		// Brace yourselves.
		processamento.setAcquirerId(new Integer(arquivo.getAcquirerId()));
		processamento.setAffiliationCodeAcquirer(Long.toString(transacao.getAcquirerData().getAffiliationCode()));
		processamento.setAffiliationCodeSale(Long.toString(transacao.getSaleData().getAffiliationCode()));
		processamento.setAuthorizationCodeAcquirer(transacao.getAcquirerData().getAuthorizationCode());
		processamento.setAuthorizationCodeSale(transacao.getSaleData().getAuthorizationCode());
		processamento.setBranchId(transacao.getSaleData().getBranchId());
		processamento.setCaptureDateAcquirer(transacao.getAcquirerData().getCaptureDate().getTime());
		processamento.setCaptureDateSale(transacao.getSaleData().getCaptureDate().getTime());
		processamento.setCaptureMethodDescription(transacao.getAcquirerData().getCaptureMethodDescription());
		processamento.setCaptureMethodId(transacao.getAcquirerData().getCaptureMethodId().intValue());
		processamento.setCardNumberAcquirer(transacao.getSaleData().getCardNumber());
		processamento.setCardNumberSale(transacao.getSaleData().getCardNumber());
		processamento.setCardType(transacao.getAcquirerData().getCardType() != null ? transacao.getAcquirerData().getCardType().value() : null);
		processamento.setCardTypeId(transacao.getAcquirerData().getCardTypeId() != null ? transacao.getAcquirerData().getCardTypeId().intValue() : null);
		processamento.setConciliationDateTime(arquivo.getGenerationDateTime().getTime());
		processamento.setConciliationType(transacao.getConciliationType().value());
		processamento.setConciliationTypeId(new Integer(transacao.getConciliationTypeId()));
		processamento.setConciliationUserName(contratoCobranca.getEmpresa().getUsuarioConciliador());
		processamento.setCustomerName(transacao.getSaleData().getCustomerName());
		processamento.setEndPeriod(arquivo.getEndPeriod().getTime());
		processamento.setExternalId(transacao.getSaleData().getExternalId());
		processamento.setGenerationDateTime(arquivo.getGenerationDateTime().getTime());
		processamento.setInstallmentCount(new Integer(transacao.getAcquirerData().getInstallmentCount())); // TODO Confirmar
		processamento.setInstallmentCountAcquirer(new Integer(transacao.getAcquirerData().getInstallmentCount()));
		processamento.setInstallmentCountSale(new Integer(transacao.getSaleData().getInstallmentCount()));
		processamento.setInstallmentsGrossAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getInstallmentsGrossAmount()));
		processamento.setInstallmentsNetAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getInstallmentsNetAmount()));
		processamento.setInstallmentsTaxAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getInstallmentsTaxAmount()));
		processamento.setMerchantId(arquivo.getMerchantId());
		processamento.setNsuAcquirer(new Long(transacao.getAcquirerData().getNsu()).intValue());
		processamento.setNsuSale(transacao.getSaleData().getNsu().intValue());
		processamento.setOrderIdAcquiirer(transacao.getAcquirerData().getOrderId());
		processamento.setOrderIdSale(transacao.getSaleData().getOrderId());
		processamento.setPaymentMethodName(transacao.getSaleData().getPaymentMethodName());
		processamento.setProcessingType(arquivo.getProcessingType().value());
		processamento.setProcessingTypeId(arquivo.getProcessingTypeId());
		processamento.setProductIdentifierCode(new Integer(transacao.getAcquirerData().getProductIdentifierCode()));
		processamento.setProductIdentifierDescription(transacao.getAcquirerData().getProductIdentifierDescription());
		processamento.setRoundingInstallmentGrossAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getRoundingInstallmentGrossAmount()));
		processamento.setRoundingInstallmentNetAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getRoundingInstallmentNetAmount()));
		processamento.setRoundingInstallmentTaxAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getRoundingInstallmentTaxAmount()));
		processamento.setSaleDateAcquirer(transacao.getAcquirerData().getSaleDate().getTime());
		processamento.setSaleDateSale(transacao.getSaleData().getSaleDate().getTime());
		processamento.setSequentialNumber(arquivo.getSequentialNumber());
		processamento.setStartPeriod(arquivo.getStartPeriod().getTime());
		processamento.setSummaryIdentifierNumber(transacao.getAcquirerData().getSummaryIdentifierNumber().intValue());
		processamento.setSummaryNumber(transacao.getAcquirerData().getSummaryNumber());
		processamento.setTax(CurrencyUtils.fromTaxLong(transacao.getAcquirerData().getTax()));
		processamento.setTidAcquirer(transacao.getAcquirerData().getTid());
		processamento.setTidSale(transacao.getSaleData().getTid());
		processamento.setTransactionAmount(CurrencyUtils.fromLong(transacao.getSaleData().getTransactionAmount()));
		processamento.setTransactionGrossAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getTransactionGrossAmount()));
		processamento.setTransactionIdAcquirer(transacao.getAcquirerData().getTransactionId());
		processamento.setTransactionIdSale(transacao.getSaleData().getTransactionId());
		processamento.setTransactionNetAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getTransactionNetAmount()));
		processamento.setTransactionTaxAmount(CurrencyUtils.fromLong(transacao.getAcquirerData().getTransactionTaxAmount()));
		processamento.setVersion(arquivo.getVersion());
		
		Long id = (Long) processamentoConciliacaoDLO.manter(processamento);
		processamento = processamentoConciliacaoDLO.obter(id);
		return processamento;
	}

	private ProcConciliacaoEvento persistirProcConciliacaoEvento(ControleConciliacao controle, ConciliationFile arquivo,
			ProcessamentoConciliacao processamento, Event evento, String tagPai) throws BillingException {
		ProcConciliacaoEvento procEvento = new ProcConciliacaoEvento();
		procEvento.setProcessamentoConciliacao(processamento);
		
		// Brace yourselves (2).
		procEvento.setAccount(evento.getAccount());
		procEvento.setAcquirerAdjustCode(evento.getAcquirerAdjustCode() != null ? Integer.parseInt(evento.getAcquirerAdjustCode()) : null);
		procEvento.setAcquirerAdjustDescription(evento.getAcquirerAdjustDescription());
		procEvento.setAffiliationCode(Long.toString(evento.getAffiliationCode()));
		procEvento.setAgency(evento.getAgency());
		procEvento.setAnticipationOperationNumber(evento.getAnticipationOperationNumber());
		procEvento.setBank(new Integer(evento.getBank()));
		procEvento.setCategory(evento.getCategory().value());
		procEvento.setCategoryId(new Integer(evento.getCategoryId()));
		procEvento.setEventDate(evento.getEventDate().getTime());
		procEvento.setEventId(evento.getEventId());
		procEvento.setGrossAmount(CurrencyUtils.fromUnscaledLong(evento.getGrossAmount()));
		procEvento.setNetAmount(CurrencyUtils.fromUnscaledLong(evento.getNetAmount()));
		procEvento.setOriginalPaymentDate(evento.getOriginalPaymentDate() != null ? evento.getOriginalPaymentDate().getTime() : null);
		procEvento.setTaxAmount(CurrencyUtils.fromUnscaledLong(evento.getTaxAmount()));
		procEvento.setTransactionInstallment(evento.getTransactionInstallment() != null ? new Integer(evento.getTransactionInstallment()) : null);
		procEvento.setType(evento.getType().value());
		procEvento.setTypeId(new Integer(evento.getTypeId()));
		
		procEvento.setDataProcessamento(controle.getDataProcessamentoArquivo());
		procEvento.setTagRegistro(tagPai);
		
		Long id = (Long) procConciliacaoEventoDLO.manter(procEvento);
		procEvento = procConciliacaoEventoDLO.obter(id);
		return procEvento;
	}

	private void persistirConciliacaoInterface(TipoConciliacaoInterface tipo,
			ControleConciliacao controle, Cobranca cobranca, ProcessamentoConciliacao processamento,
			Event eventoTransacao) throws DLOException {
		persistirConciliacaoInterface(tipo, controle, cobranca, processamento, null, eventoTransacao);
	}

	private void persistirConciliacaoInterface(TipoConciliacaoInterface tipo, ControleConciliacao controle,
			ProcConciliacaoEvento eventoProcessamento, Cobranca cobranca) throws DLOException {
		persistirConciliacaoInterface(tipo, controle, cobranca, null, eventoProcessamento, null);
	}
	
	private ConciliacaoInterface persistirConciliacaoInterface(TipoConciliacaoInterface tipo,
			ControleConciliacao controle,
			Cobranca cobranca, ProcessamentoConciliacao processamento,
			ProcConciliacaoEvento eventoProcessamento, Event evento) throws DLOException {
		ConciliacaoInterface conciliacaoInterface = new ConciliacaoInterface();
		Venda venda = cobranca != null ? cobranca.getVenda() : null;
		
		if (cobranca != null) {
			cobranca = cobrancaDLO.completar(cobranca, Cobranca_.contratoCobranca);
		}
		
		if (venda != null) {
			venda = vendaDLO.completar(venda, Venda_.produto.getName(), Venda_.sistema.getName());
		}
		
		if (processamento == null && eventoProcessamento != null) {
			processamento = eventoProcessamento.getProcessamentoConciliacao();
		}
		
		ContratoCobranca contrato = cobranca != null ? cobranca.getContratoCobranca() : null;
		Empresa empresa = contrato != null ? contrato.getEmpresa() : null;
		Produto produto = venda != null ? venda.getProduto() : null;
		Sistema sistema = venda != null ? venda.getSistema() : null;
		Bandeira bandeira = venda != null ? bandeiraDLO.obterPorCodigo(venda.getCodigoBandeiraCartao()) : null;

		conciliacaoInterface.setTipo(tipo);
		// conciliacaoInterface.setStatus(??? preenchida pela interface ???);
		
		conciliacaoInterface.setAgencia(eventoProcessamento != null ? eventoProcessamento.getAgency().toString() : Integer.toString(evento.getAgency()));
		conciliacaoInterface.setBanco(eventoProcessamento != null ? eventoProcessamento.getBank().toString() : Short.toString(evento.getBank()));
		conciliacaoInterface.setCodigoAfiliacao(eventoProcessamento != null ? eventoProcessamento.getAffiliationCode() : Long.toString(evento.getAffiliationCode()));
		conciliacaoInterface.setCodigoCliente(venda != null ? venda.getCodigoVendaOrigem() : null);
		conciliacaoInterface.setCodigoEmpresa(empresa != null ? empresa.getCodigo() : null);
		conciliacaoInterface.setCodigoProduto(produto != null ? produto.getCodigo() : null);
		conciliacaoInterface.setCodigoSistema(sistema != null ? sistema.getCodigo() : null);
		conciliacaoInterface.setConta(eventoProcessamento != null ? eventoProcessamento.getAccount() : evento.getAccount());
		conciliacaoInterface.setDataAutorizacao(cobranca != null ? cobranca.getDataCobranca() : null);
		// conciliacaoInterface.setDataBanco(??? preenchida pela interface ???);
		// conciliacaoInterface.setDataContabil(??? preenchida pela interface ???);
		conciliacaoInterface.setDataGeracao(controle.getDataProcessamentoArquivo());
		conciliacaoInterface.setDataPagamento(eventoProcessamento != null ? eventoProcessamento.getEventDate() : evento.getEventDate().getTime());
		// conciliacaoInterface.setDataProcessamento(??? preenchida pela interface ???);
		conciliacaoInterface.setDataVencimento(cobranca != null ? cobranca.getDataCobranca() : null);
		conciliacaoInterface.setNomeBandeira(bandeira != null ? bandeira.getNomeBandeira() : null);
		conciliacaoInterface.setNsu(processamento.getNsuAcquirer().toString());
		conciliacaoInterface.setNumeroParcela(eventoProcessamento != null ? eventoProcessamento.getTransactionInstallment() : evento.getTransactionInstallment());
		conciliacaoInterface.setValorCobranca(eventoProcessamento != null ? eventoProcessamento.getGrossAmount() : CurrencyUtils.fromUnscaledLong(evento.getGrossAmount()));
		conciliacaoInterface.setValorTarifa(eventoProcessamento != null ? eventoProcessamento.getTaxAmount() : CurrencyUtils.fromUnscaledLong(evento.getTaxAmount()));
		
		Long id = (Long) conciliacaoInterfaceDLO.manter(conciliacaoInterface);
		return conciliacaoInterfaceDLO.obter(id);
	}

	static final Set<Integer> categoryIdsValidosParaEventosAffiliationAccounting;
	static final Set<Integer> categoryIdsValidosParaNetAmount;
	static final Set<Integer> typeIdsValidosParaProcessamento;
	static final Set<StatusCobranca> statusesCobrancaValidosParaPagamento;
	static final Set<StatusCobranca> statusesCobrancaValidosParaAceleracao;
	static final Set<StatusCobranca> statusesCobrancaValidosParaEstorno;
	
	static {
		Set<Integer> affiliationAccounting = new HashSet<Integer>();
		Collections.addAll(affiliationAccounting, new Integer[]{ 3, 4, 5, 6, 8, 9, 10, 11, 13, 19, 20 });
		categoryIdsValidosParaEventosAffiliationAccounting = Collections.unmodifiableSet(affiliationAccounting);

		Set<Integer> netAmount = new HashSet<Integer>();
		Collections.addAll(netAmount, new Integer[]{ 3, 13, 22 });
		categoryIdsValidosParaNetAmount = Collections.unmodifiableSet(netAmount);

		Set<Integer> processing = new HashSet<Integer>();
		Collections.addAll(processing, new Integer[]{ 1, 3 });
		typeIdsValidosParaProcessamento = Collections.unmodifiableSet(processing);

		Set<StatusCobranca> scp = EnumSet.noneOf(StatusCobranca.class);
		Collections.addAll(scp, new StatusCobranca[]{
				StatusCobranca.Capturada,
				StatusCobranca.ParcialmenteCobrada,
				StatusCobranca.Estornada,
				StatusCobranca.EstornoConciliado,
				StatusCobranca.EstornoNegado
		});
		statusesCobrancaValidosParaPagamento = Collections.unmodifiableSet(scp);

		Set<StatusCobranca> sca = EnumSet.noneOf(StatusCobranca.class);
		Collections.addAll(sca, new StatusCobranca[]{
				StatusCobranca.Estornada,
				StatusCobranca.EstornoConciliado,
				StatusCobranca.Cancelada,
				StatusCobranca.EstornoNegado
		});
		statusesCobrancaValidosParaAceleracao = Collections.unmodifiableSet(sca);

		Set<StatusCobranca> sce = EnumSet.noneOf(StatusCobranca.class);
		Collections.addAll(sce, new StatusCobranca[]{
				StatusCobranca.Capturada,
				StatusCobranca.ParcialmenteCobrada,
				StatusCobranca.EstornoConciliado
		});
		statusesCobrancaValidosParaEstorno = Collections.unmodifiableSet(sce);
	}
}

