package br.com.delphos.billing.servicos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.CodigoRetornoAutorizarVenda;
import br.com.delphos.billing.enumeracoes.MotivoCancelamento;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.enumeracoes.StatusCancelamento;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusVenda;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.enumeracoes.TipoMeioPagamento;
import br.com.delphos.billing.enumeracoes.TipoPagamento;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.listasValores.ListaValor_;
import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.regras.RegraVendaCodigoEmpresa;
import br.com.delphos.billing.regras.RegraVendaCodigoProduto;
import br.com.delphos.billing.regras.RegraVendaCodigoSistema;
import br.com.delphos.billing.regras.RegraVendaCodigoVenda;
import br.com.delphos.billing.regras.RegraVendaCpf;
import br.com.delphos.billing.regras.RegraVendaTokenWebService;
import br.com.delphos.billing.servicos.retornos.RetornoAutorizarVendaCartaoCredito;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarCobrancaPendente;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarVenda;
import br.com.delphos.billing.servicos.retornos.RetornoIniciarVendaCartaoCredito;
import br.com.delphos.billing.servicos.util.DbsServiceHelpers;
import br.com.delphos.billing.servicos.util.RegrasNegocioBilling;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.tentativas.TentativaDLO;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.CurrencyUtils;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.util.ServiceHelper;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;

@Stateless(name = "BillingServiceBean")
@TransactionManagement(TransactionManagementType.BEAN)
public class BillingServiceBean implements BillingService, Serializable {
	private static final long serialVersionUID = 1L;

	public static Logger LOGGER = LoggerFactory.getLogger(BillingServiceBean.class);
	
	@EJB(lookup = "java:module/PagamentoServiceBean")
	private PagamentoService pagador;
	
	@EJB(lookup = "java:global/dbsdb/EmpresaDLOBean")
	private EmpresaDLO empresaDLO;
	
	@EJB(lookup = "java:global/dbsdb/ProdutoDLOBean")
	private ProdutoDLO produtoDLO;
	
	@EJB(lookup = "java:global/dbsdb/SistemaDLOBean")
	private SistemaDLO sistemaDLO;
	
	@EJB(lookup = "java:global/dbsdb/BandeiraDLOBean")
	private BandeiraDLO bandeiraDLO;
	
	@EJB(lookup = "java:global/dbsdb/VendaDLOBean")
	private VendaDLO vendaDLO;
	
	@EJB(lookup = "java:global/dbsdb/ListaValorDLOBean")
	private ListaValorDLO listaValorDLO;
	
	@EJB(lookup = "java:global/dbsdb/MeioPagamentoDLOBean")
	private MeioPagamentoDLO meioPagamentoDLO;
	
	@EJB(lookup = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;
	
	@EJB(lookup = "java:global/dbsdb/TentativaDLOBean")
	private TentativaDLO tentativaDLO;
	
	@EJB(lookup = "java:global/dbsdb/EventoDLOBean")
	private EventoDLO eventoDLO;
	
	@EJB(lookup = "java:global/dbsdb/ContratoCobrancaDLOBean")
	private ContratoCobrancaDLO contratoCobrancaDLO;
	
	@Resource
	private EJBContext ejbContext;
	
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);

	@Override
	public RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
			String token, String codigoEmpresa, String codigoProduto,
			String codigoSistema, String cpf, String nome, String email,
			String dddCelular, String telefoneCelular, String dddContato,
			String telefoneContato, String codigoVenda, String dataVenda,
			String bandeiraCartao, String cpfPortadorCartao,
			String nomePortadorCartao, String numeroCartao,
			String vencimentoCartao, String cvvCartao, String tipoCobranca,
			String quantidadeParcelas, String valorCobranca,
			String dataPrimeiraCobranca, String dataFimVigencia)
			throws BillingException {
		
		// Dados do procedimento
		ServiceHelper<RetornoIniciarVendaCartaoCredito> helper = 
				DbsServiceHelpers.entrando(logger, new RetornoIniciarVendaCartaoCredito());
		Calendar calendarioCorrente = DateUtils.getCalendarDataHoraAtual();;
		Date dataHoraCorrente = calendarioCorrente.getTime();
		Date dataCorrente = DateUtils.removerHoras(calendarioCorrente.getTime());
		Empresa empresa = null;
		Produto produto = null;
		List<ContratoCobranca> contratosVigentes = null;
		ContratoCobranca contratoCobranca = null;
		String provedorTransactionId = null;
		Sistema sistema = null;
		Venda venda = null;
		Bandeira bandeira = null;
		ListaValor listaValor = null;
		ItemLista itemLista = null;
		TipoCobranca tipoCobrancaFinal = null;
		MeioPagamento meioPagamento = null;
		Date dataVendaFinal = null;
		long valorCobrancaComoLong = 0;
		List<AdquirenteBandeira> adquirentesBandeiras = null;
		AdquirenteBandeira adquirenteBandeira = null;
		Date dataPrimeiraCobrancaComoDate = null;
		Date dataFimVigenciaComoDate = null;
		int quantidadeParcelasComoInt = -1;
		String ultimosDigitosCartao = null;

		// DADOS DE DEPURAÇÃO E TESTE
		boolean modoDebug = Configuracoes.isDebug();
		String prefixoExcecao = Configuracoes.getObjeto(PropriedadeConfiguracao.PrefixoVendaErroRotina, String.class);
		
		// Tratamento de espaços espúrios nas strings de entrada.
		token = token.trim();
		codigoEmpresa = codigoEmpresa.trim();
		codigoProduto = codigoProduto.trim();
		codigoSistema = codigoSistema.trim();
		cpf = cpf.trim();
		nome = nome.trim();
		email = email.trim();
		dddCelular = dddCelular.trim();
		telefoneCelular = telefoneCelular.trim();
		dddContato = dddContato.trim();
		telefoneContato = telefoneContato.trim();
		codigoVenda = codigoVenda.trim();
		dataVenda = dataVenda.trim();
		bandeiraCartao = bandeiraCartao.trim();
		cpfPortadorCartao = cpfPortadorCartao.trim();
		nomePortadorCartao = nomePortadorCartao.trim();
		vencimentoCartao = vencimentoCartao.trim();
		cvvCartao = cvvCartao.trim();
		tipoCobranca = tipoCobranca.trim();
		quantidadeParcelas = quantidadeParcelas.trim();
		valorCobranca = valorCobranca.trim();
		dataPrimeiraCobranca = dataPrimeiraCobranca.trim();
		dataFimVigencia = dataFimVigencia.trim();
		
		// Valores de retorno
		helper.sucesso();

		// Valores que são sempre retornados
		helper.getRetorno().setCodigoProduto(codigoProduto);
		helper.getRetorno().setCodigoVenda(codigoVenda);
		
		
		//
		// INÍCIO DA TRANSAÇÃO
		//

		
		// Sair de imediato caso não seja possível iniciar a transação.
		helper.begin(ejbContext, false);
		if (!helper.isSucesso()) {
			return helper.retorno();
		}
		
		try {
			
			/*
			 * ============ VALIDAÇÕES DOS PARÂMETROS ============
			 */
	
			// Token de validação
			if (helper.isSucesso()) {
				RegraVendaTokenWebService validacaoToken = 
						RegrasNegocioBilling.tokenValidacao(helper, token);
				
				validacaoToken.run();
			}
	
			// Código da empresa
			if (helper.isSucesso()) {
				RegraVendaCodigoEmpresa validacaoEmpresa = 
						RegrasNegocioBilling.codigoEmpresa(helper, codigoEmpresa, empresaDLO);

				if (validacaoEmpresa.isValida()) {
					empresa = validacaoEmpresa.getEmpresa();
					contratosVigentes = validacaoEmpresa.getContratosVigentes();
				}
			}
	
			// Código do produto
			if (helper.isSucesso()) {
				
				RegraVendaCodigoProduto validacaoProduto =
						RegrasNegocioBilling.codigoProduto(
								helper, codigoProduto, empresa, produtoDLO);
				
				if (validacaoProduto.isValida()) {
					produto = validacaoProduto.getProduto();
				}
			}
	
			// Código do sistema
			if (helper.isSucesso()) {
				
				RegraVendaCodigoSistema validacaoSistema = 
						RegrasNegocioBilling.codigoSistema(
								helper, codigoSistema, produto, sistemaDLO);
				
				if (validacaoSistema.isValida()) {
					sistema = validacaoSistema.getSistema();
				}
			}
	
			// CPF
			if (helper.isSucesso()) {

				RegraVendaCpf validacaoCpf = RegrasNegocioBilling.cpf(helper, cpf, "cpf");
				validacaoCpf.run();
			}
	
			// Nome
			if (helper.isSucesso()) {
				
				if (!Validador.nomeCliente(nome)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"nome");
				}
			}
	
			// Email
			if (helper.isSucesso()) {
				
				if (!Validador.emailCliente(email)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"email");
				}
			}
	
			// DDD do celular
			if (helper.isSucesso()) {
				
				if (!Validador.ddd(dddCelular)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"dddCelular");
				}
			}
	
			// Número do celular
			if (helper.isSucesso()) {
				
				if (!Validador.telefone(telefoneCelular)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"numeroCelular");
				}
			}
	
			// DDD telefone contato
			if (helper.isSucesso()) {
				
				if (!Validador.ddd(dddContato)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"dddTelContato");
				}
			}
	
			// Número telefone contato
			if (helper.isSucesso()) {
				
				if (!Validador.telefone(telefoneContato)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"numeroTelContato");
				}
			}
	
			// Código de Venda
			if (helper.isSucesso()) {

				RegraVendaCodigoVenda validacaoVenda = 
						RegrasNegocioBilling.codigoVendaNaoExiste(
								helper, codigoVenda, produto, empresa, vendaDLO);
				
				validacaoVenda.run();
			}
	
			// Data da Venda
			if (helper.isSucesso()) {
				
				if (!Validador.dataPadrao(dataVenda)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"dataVenda");
					
				} else {
					dataVendaFinal = DateUtils.removerHoras(Validador.obterDataPadrao(dataVenda));
					
					if (dataVendaFinal.compareTo(dataCorrente) != 0) {
						helper.setValorRetorno(
								CodigoMensagem.DPH0004,
								"dataVenda");
					}
				}
			}
	
			// Bandeira do cartão de crédito
			// RN - Modelo de dados
			// ??? Código utilizado na entidade Bandeira.
			if (helper.isSucesso()) {
				
				boolean codigoBandeiraValido = bandeiraDLO.isCodigoValido(bandeiraCartao);
				
				if (codigoBandeiraValido) {
					bandeira = bandeiraDLO.obterPorCodigo(bandeiraCartao);
				}
				
				if (!codigoBandeiraValido || bandeira == null) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"bandeiraCartao");
				}
			}
	
			// CPF do portador do cartão
			if (helper.isSucesso()) {

				RegraVendaCpf validacaoCpf = RegrasNegocioBilling.cpf(helper, cpfPortadorCartao, "cpfPortadorCartao");
				validacaoCpf.run();
			}
	
			// Nome do portador do cartão
			if (helper.isSucesso()) {
				
				if (!Validador.nomePortadorCartao(nomePortadorCartao)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"nomePortadorCartao");
				}
			}
	
			// Número do cartão de crédito
			if (helper.isSucesso()) {
				
				ultimosDigitosCartao = Validador.obterUltimosDigitosCartao(numeroCartao);
				
				if (!Validador.numeroCartaoCredito(numeroCartao) || ultimosDigitosCartao == null) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"numeroCartao");
				}
			}
	
			// Vencimento do cartão de crédito
			if (helper.isSucesso()) {
				
				if (!Validador.dataVencimentoCartaoCredito(vencimentoCartao)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"vencimentoCartao");
					
				} else {
					Date dataVencimentoCartao = Validador
							.obterDataVencimentoCartaoCredito(vencimentoCartao,
									DateUtils.getCalendarDataHoraAtual());
					
					if (dataCorrente.after(dataVencimentoCartao)) {
						helper.setCodigoRetornoParaParametro(
								CodigoMensagem.DPH0005,
								"vencimentoCartao");
					}
				}
			}
	
			// Código de autorização do cartão de crédito - CVV
			if (helper.isSucesso()) {
				
				if (!Validador.cvvCartaoCredito(cvvCartao)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"cvvCartao");
				}
			}
	
			// Tipo de cobrança
			// RN - Modelo de dados
			// ??? Deve existir na entidade LISTA DE VALORES, tipo TPCOB. Deve ser
			// o mesmo tipo cadastrado no produto. Se erro retornar MSG0001.
			if (helper.isSucesso()) {
				
				listaValor = listaValorDLO.obterPorCodigo(TipoListaValor.Cobranca.getValor());
				
				if (listaValor != null) {
					listaValor = listaValorDLO.completar(listaValor, ListaValor_.itensLista);
				}
				
				if (listaValor == null) {
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				
				for (ItemLista itemListaCandidato : listaValor.getItensLista()) {
					
					if (itemListaCandidato.getCodigo().equals(tipoCobranca)) {
						itemLista = itemListaCandidato;
						break;
					}
				}
				
				if (itemLista != null) {
					tipoCobrancaFinal = TipoCobranca.buscarPorValor(itemLista
							.getCodigo());
				}
				
				if (tipoCobrancaFinal == null) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"tipoCobranca");
					
				} else if (produto.getTipoCobranca() != tipoCobrancaFinal) {
					helper.setValorRetorno(CodigoMensagem.DPH0026);
				}
			}
	
			// Quantidade de parcelas
			// ???? Não pode ser superior ao Número Máximo de Parcelas cadastrado na
			// entidade Produto. Se erro retornar MSG0006.
			if (helper.isSucesso()) {
				
				if (Validador.vazio(quantidadeParcelas)
						|| !Validador.minChars(quantidadeParcelas, 1)
						|| !Validador.maxChars(quantidadeParcelas, 2)
						|| !Validador.inteiro(quantidadeParcelas)
						|| Integer.parseInt(quantidadeParcelas) <= 0) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"quantidadeParcelas");
					
				} else if (Integer.valueOf(quantidadeParcelas).compareTo(
						produto.getNumeroMaximoParcelas()) > 0) {
					helper.setValorRetorno(CodigoMensagem.DPH0006);
	
				} else if (tipoCobrancaFinal.getTipoPagamento() == TipoPagamento.Vista
						&& Integer.parseInt(quantidadeParcelas) != 1) {
					helper.setValorRetorno(CodigoMensagem.DPH0025);
					
				} else {
					quantidadeParcelasComoInt = Integer.parseInt(quantidadeParcelas);
				}
			}
	
			// Valor da cobrança
			if (helper.isSucesso()) {
				
				if (!Validador.valorCobrancaComoInteiro(valorCobranca)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"valorCobranca");
					
				} else {
					valorCobrancaComoLong = Long.parseLong(valorCobranca);
				}
			}
	
			// Data da primeira cobrança
			if (helper.isSucesso()) {
				
				if (!Validador.dataPadrao(dataPrimeiraCobranca)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"dataPrimeiraCobranca");
					
				} else {
					dataPrimeiraCobrancaComoDate = Validador.obterDataPadrao(dataPrimeiraCobranca);
					
					if (!dataPrimeiraCobranca.equals(dataVenda)) {
						helper.setCodigoRetornoParaParametro(
								CodigoMensagem.DPH0007,
								"dataPrimeiraCobranca");
					}
				}
			}
	
			// Data de fim de vigência
			if (helper.isSucesso()) {
				
				if (!Validador.dataPadrao(dataFimVigencia)) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"dataFimVigencia");
					
				} else {
					dataFimVigenciaComoDate = Validador.obterDataPadrao(dataFimVigencia);
					
					if (!dataFimVigenciaComoDate.after(dataPrimeiraCobrancaComoDate)
							|| !(dataFimVigenciaComoDate.compareTo(dataCorrente) >= 0)) {
						helper.setValorRetorno(CodigoMensagem.DPH0008);
					}
				}
			}
	
			/*
			 * ============ VALIDAÇÕES ADICIONAIS ============
			 */
	
			// Deve estar associado ao PRODUTO informado.
			if (helper.isSucesso()) {
				
				List<ContratoCobranca> contratosCandidatos = new ArrayList<ContratoCobranca>();
				
				for (ContratoCobranca contratoVigente : contratosVigentes) {
					contratoVigente = contratoCobrancaDLO.completar(contratoVigente, "produtos");
					
					if (contratoVigente == null) {
						helper.error("Não foi possível buscar informações sobre os produtos do contrato de cobrança.");
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
					
					for (Produto produtoContrato : contratoVigente.getProdutos()) {
						if (produtoContrato.equals(produto)) {
							contratosCandidatos.add(contratoVigente);
							break;
						}
					}
				}
				
				if (contratosCandidatos.isEmpty()) {
					helper.setValorRetorno(CodigoMensagem.DPH0014);
					
				} else {
					contratosVigentes = contratosCandidatos;
				}
			}
	
			// Deve estar associado a um MEIO_PAGAMENTO do tipo CARTÃO
			if (helper.isSucesso()) {
				
				List<ContratoCobranca> contratosCandidatos = new ArrayList<ContratoCobranca>();
				meioPagamento = meioPagamentoDLO
						.obterPorCodigo(TipoMeioPagamento.CartaoCredito.getValor());
				
				if (meioPagamento != null) {
					
					for (ContratoCobranca contratoVigente : contratosVigentes) {
						if (contratoVigente.getMeioPagamento()
								.equals(meioPagamento)) {
							contratosCandidatos.add(contratoVigente);
						}
					}
				}
				
				if (contratosCandidatos.isEmpty()) {
					helper.setValorRetorno(CodigoMensagem.DPH0015);
					
				} else {
					contratosVigentes = contratosCandidatos;
					// TODO Considerar provedores diferentes da Braspag.
					// NOTA: Aqui, podem existir mais de um contrato de cobrança,
					// todos com provedores diferentes. Para a entrega inicial,
					// o único provedor disponível é a Braspag. Por conta da
					// data de início de vigência do contrato fazer parte da
					// identidade da entidade, é possível, apenas de acordo com
					// o modelo, que tenhamos vários contratos com datas
					// diferentes nesta lista de contratos candidatos à seleção.
					// No entanto, é uma garantia especificada na documentação e
					// implementada nos cadastros que os períodos de vigência
					// não poderão estar sobrepostos uns aos outros, de forma
					// que apenas um contrato de vigência estará vigente para 
					// cada período e apenas um será retornado por provedor para
					// a data corrente.
					// Então, e apenas enquanto novos provedores não são
					// inclusos no sistema, é seguro assumir que a lista deverá
					// conter um único contrato com o provedor Braspag.
					contratoCobranca = contratosVigentes.get(0);
				}
			}
	
			// (Ao menos) Uma ligação entre ADQUIRENTE X BANDEIRA está associada a
			// um contrato.
			if (helper.isSucesso()) {
				
				adquirentesBandeiras = new ArrayList<AdquirenteBandeira>();
				contratoCobranca = contratoCobrancaDLO.completar(contratoCobranca, ContratoCobranca_.adquirentesBandeiras);
				
				if (contratoCobranca == null) {
					helper.error("Não foi possível buscar informações sobre as adquirentes e bandeiras do contrato de cobrança.");
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				
				for (AdquirenteBandeira adquirenteBandeiraCandidata : contratoCobranca
						.getAdquirentesBandeiras()) {
					if (adquirenteBandeiraCandidata.getBandeira().equals(bandeira)) {
						adquirentesBandeiras.add(adquirenteBandeiraCandidata);
					}
				}
				
				if (adquirentesBandeiras.isEmpty()) {
					helper.setValorRetorno(CodigoMensagem.DPH0016);
				}
			}
	
			if (helper.isSucesso()) {
//				
//				if (adquirentesBandeiras.size() == 1) {
//					adquirenteBandeira = adquirentesBandeiras.get(0);
//					
//				} else {
					for (AdquirenteBandeira adquirenteBandeiraCandidata : adquirentesBandeiras) {
						if (adquirenteBandeiraCandidata.isAdquirentePadrao()) {
							adquirenteBandeira = adquirenteBandeiraCandidata;
							break;
						}
					}
					
					if (adquirenteBandeira == null) {
						helper.setValorRetorno(CodigoMensagem.DPH0017);
					}
//				}
			}
	
			
			/*
			 * ============ EFETIVAÇÃO DA CHAMADA ============
			 */
			
			if (helper.isSucesso()) {	
				
				// Gravar Venda
				venda = new Venda();
				venda.setCodigoBandeiraCartao(bandeira.getCodigoBandeira());
				venda.setCodigoVendaOrigem(codigoVenda);
				venda.setContratoCobranca(contratoCobranca);
				venda.setCpf(cpf);
				venda.setCpfPortadorCartao(cpfPortadorCartao);
				venda.setDataFimVigencia(dataFimVigenciaComoDate);
				venda.setDataPrimeiraCobranca(dataPrimeiraCobrancaComoDate);
				venda.setDataVendaOrigem(dataVendaFinal);
				venda.setDddCelular(dddCelular);
				venda.setCelular(telefoneCelular);
				venda.setDddTelefone(dddContato);
				venda.setTelefone(telefoneContato);
				venda.setEmail(email);
				venda.setEmpresa(empresa);
				venda.setProduto(produto);
				venda.setNome(nome);
				venda.setNomeImpressoCartao(nomePortadorCartao);
				venda.setQuantidadeParcelas(quantidadeParcelasComoInt);
				venda.setSistema(sistema);
				venda.setStatus(StatusVenda.Pendente);
				venda.setTipoCobranca(tipoCobrancaFinal);
				venda.setUltimosDigitosCartao(ultimosDigitosCartao);
//				venda.setValorCobranca(BigDecimal.valueOf(valorCobrancaComoLong / 100.0));
				venda.setValorCobranca(CurrencyUtils.fromUnscaledLong(valorCobrancaComoLong));
				venda.setVencimentoCartao(vencimentoCartao);
				
				venda.setId((Long) vendaDLO.manter(venda));
				if (venda.getId() == null) {
					helper.error("Não foi possível persistir inicialmente a entidade \"{}\".", venda.toString());
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				
				// Gravar Cobrança
				String idRequisicao = cobrancaDLO.gerarIdRequisicao();
				Cobranca cobranca = new Cobranca();
				cobranca.setVenda(venda);
				cobranca.setCodigoBandeiraCartao(bandeira.getCodigoBandeira());
				cobranca.setContratoCobranca(contratoCobranca);
				cobranca.setDataCobranca(dataPrimeiraCobrancaComoDate);
				cobranca.setNumeroParcela(1);
				cobranca.setNumeroUltimaTentativa(0);
				cobranca.setStatusCobranca(StatusCobranca.Pendente);
				cobranca.setUltimosDigitosCartao(ultimosDigitosCartao);
				cobranca.setValorCobranca(venda.getValorCobranca());
				cobranca.setIdRequisicaoProvedor(idRequisicao);
				cobranca.setDataUltimaAlteracao(dataHoraCorrente);
	
				cobranca.setId((Long) cobrancaDLO.manter(cobranca));
				if (cobranca.getId() == null) {
					helper.error("Não foi possível persistir inicialmente a entidade \"{}\".", cobranca.toString());
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				
				// Gravar Evento (???)
				
				// Gravar Tentativa
				Tentativa tentativa = new Tentativa();
				tentativa.setCobranca(cobranca);
				tentativa.setNumeroTentativa(0);
				tentativa.setDataTentativa(dataHoraCorrente);
				tentativa.setTipoTentativa(TipoTentativa.Cobranca);
	
				tentativa.setId((Long) tentativaDLO.manter(tentativa));
				if (tentativa.getId() == null) {
					helper.error("Não foi possível persistir inicialmente a entidade \"{}\".", tentativa.toString());
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				
				// Gravar Evento
				eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.GeradaCobrancaPorVenda, null);
				
				
				//
				// 1st COMMIT
				//
				
				helper.commit();
				helper.begin(ejbContext, true);
				helper.getRetorno().setCodigoVendaBilling(venda.getId().toString());
				
				
				// Chamar pagador.autorizarVendaCartaoCredito(...);
				eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ChamadaMetodoAutorizacaoGateway, null);
				RetornoAutorizarVendaCartaoCredito retornoAutorizacao = pagador.autorizarVendaCartaoCredito(cobranca, adquirenteBandeira, numeroCartao, cvvCartao);
				provedorTransactionId = retornoAutorizacao.getProvedorTransactionId();
						
				cobranca.setCodigoTransacaoProvedor(retornoAutorizacao.getProvedorTransactionId());
				cobranca.setCodigoPedidoProvedor(retornoAutorizacao.getProvedorOrderId());
				tentativa.setCodigoTransacaoAdquirente(retornoAutorizacao.getIdTransacaoAdquirente());

				if (tentativaDLO.manter(tentativa) == null) {
					helper.error("Não foi possível persistir entidade \"{}\" para atualização, após autorização.", tentativa);
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				
				if (cobrancaDLO.manter(cobranca) == null) {
					helper.error("Não foi possível persistir entidade \"{}\" para atualização, após autorização.", cobranca.toString());
					throw new BillingWebServiceException(CodigoMensagem.Falha);
				}
				

				//
				// 2nd COMMIT
				//
				
				helper.commit();
				provedorTransactionId = null; // Os dados da transação já foram persistidos.
				helper.begin(ejbContext, true);
				
				
				// Verificar resultado da autorização
				CodigoRetornoAutorizarVenda codigoRetorno = retornoAutorizacao.getCodigoRetorno();

				try {

					tentativa.setCodigoRetornoProvedor(retornoAutorizacao.getRetornoProvedor());
					tentativa.setMensagemRetornoProvedor(retornoAutorizacao.getMensagemProvedor());
					tentativa.setCodigoRetornoAdquirente(retornoAutorizacao.getRetornoAdquirente());
					tentativa.setMensagemRetornoAdquirente(retornoAutorizacao.getMensagemAdquirente());
					
					if (codigoRetorno == CodigoRetornoAutorizarVenda.Autorizado) {
						
						venda.setStatus(StatusVenda.Efetivada);
						if (tipoCobrancaFinal.isRecorrencia()) {
							venda.setTokenCartao(retornoAutorizacao.getTokenCartao());
						}
						
						cobranca.setStatusCobranca(StatusCobranca.Capturada);
						cobranca.setCodigoTransacaoAdquirente(retornoAutorizacao.getIdTransacaoAdquirente());
						cobranca.setCodigoAutorizacao(retornoAutorizacao.getCodigoAutorizacao());
						cobranca.setNumeroComprovanteVenda(retornoAutorizacao.getNumeroComprovanteVenda());
						
						tentativa.setCodigoTransacaoAdquirente(retornoAutorizacao.getProvedorTransactionId());
						
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.TransacaoBemSucedida, null);
						
					} else if (codigoRetorno == CodigoRetornoAutorizarVenda.NaoAutorizado) {
						helper.falha();
						
						venda.setStatus(StatusVenda.NaoAutorizada);
						
						cobranca.setStatusCobranca(StatusCobranca.NaoAutorizada);
						
//						tentativa.setCodigoRetornoAdquirente(retornoAutorizacao.getRetornoAdquirente());
//						tentativa.setMensagemRetornoAdquirente(retornoAutorizacao.getMensagemAdquirente());
	
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.TransacaoNaoEfetuada, retornoAutorizacao.getMensagemRetorno());
						helper.getRetorno().setMensagemRetorno(retornoAutorizacao.getMensagemRetorno());
						
					} else if (codigoRetorno == CodigoRetornoAutorizarVenda.TimeoutAdquirente) {
						helper.falha();
						
						venda.setStatus(StatusVenda.Cancelada);
						venda.setDataCancelamento(DateUtils.getDataHoraAtual());
						venda.setCodigoMotivoCancelamento(MotivoCancelamento.CancelamentoAutomatico);
						
						cobranca.setStatusCobranca(StatusCobranca.Pendente);
						
//						tentativa.setCodigoRetornoAdquirente(retornoAutorizacao.getRetornoAdquirente());
//						tentativa.setMensagemRetornoAdquirente(retornoAutorizacao.getMensagemAdquirente());
	
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.TransacaoNaoConfirmada, retornoAutorizacao.getMensagemRetorno());
						helper.getRetorno().setMensagemRetorno(retornoAutorizacao.getMensagemRetorno());
						
					} else if (codigoRetorno == CodigoRetornoAutorizarVenda.NaoConfirmado) {
						helper.falha();
						
						venda.setStatus(StatusVenda.Cancelada);
						venda.setDataCancelamento(dataHoraCorrente);
						venda.setCodigoMotivoCancelamento(MotivoCancelamento.CancelamentoAutomatico);
						
						cobranca.setStatusCobranca(StatusCobranca.Pendente);
						cobranca.setCodigoTransacaoAdquirente(retornoAutorizacao.getIdTransacaoAdquirente());
						cobranca.setCodigoAutorizacao(retornoAutorizacao.getCodigoAutorizacao());
						cobranca.setNumeroComprovanteVenda(retornoAutorizacao.getNumeroComprovanteVenda());
						
//						tentativa.setCodigoRetornoAdquirente(retornoAutorizacao.getRetornoAdquirente());
//						tentativa.setMensagemRetornoAdquirente(retornoAutorizacao.getMensagemAdquirente());
//						tentativa.setCodigoRetornoProvedor(retornoAutorizacao.getRetornoProvedor());
//						tentativa.setMensagemRetornoProvedor(retornoAutorizacao.getMensagemProvedor());
	
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.TransacaoNaoConfirmada, retornoAutorizacao.getMensagemRetorno());
						helper.getRetorno().setMensagemRetorno(retornoAutorizacao.getMensagemRetorno());
						
					} else if (codigoRetorno == CodigoRetornoAutorizarVenda.NaoChegouNaAdquirente) {
						helper.falha();
						
						venda.setStatus(StatusVenda.Cancelada);
						venda.setDataCancelamento(dataHoraCorrente);
						venda.setCodigoMotivoCancelamento(MotivoCancelamento.CancelamentoAutomatico);
						
						cobranca.setStatusCobranca(StatusCobranca.Cancelada);
						
//						tentativa.setCodigoRetornoProvedor(retornoAutorizacao.getRetornoProvedor());
//						tentativa.setMensagemRetornoProvedor(retornoAutorizacao.getMensagemProvedor());
						
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.TransacaoNaoChegouAoAdquirente, retornoAutorizacao.getMensagemRetorno());
						helper.getRetorno().setMensagemRetorno(retornoAutorizacao.getMensagemRetorno());
	
					// Se o método de autorização não retornou um valor conhecido ou
					// válido, consideramos que houve erro durante a 
					} else { //if (codigoRetorno == RetornoAutorizarVendaCartaoCredito.CodigoRetorno.Excecao) {
						helper.falha();
						
						venda.setStatus(StatusVenda.Cancelada);
						venda.setDataCancelamento(dataHoraCorrente);
						venda.setCodigoMotivoCancelamento(MotivoCancelamento.CancelamentoAutomatico);
						
						cobranca.setStatusCobranca(StatusCobranca.Pendente);
	
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ErroRotinaProvedor, retornoAutorizacao.getMensagemRetorno());
						helper.getRetorno().setMensagemRetorno(retornoAutorizacao.getMensagemRetorno());
						
					}
	
					if (tentativaDLO.manter(tentativa) == null) {
						helper.error("Não foi possível persistir entidade \"{}\" para atualização.", tentativa.toString());
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
					
					if (cobrancaDLO.manter(cobranca) == null) {
						helper.error("Não foi possível persistir entidade \"{}\" para atualização.", cobranca.toString());
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
					
					if (vendaDLO.manter(venda) == null) {
						helper.error("Não foi possível persistir entidade \"{}\" para atualização.", venda.toString());
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
					
					// LANÇAR EXCEÇÃO DE TESTE
					if (modoDebug && codigoVenda.startsWith(prefixoExcecao)) {
						helper.info("[MODODEBUG] Lançando exceção para teste.");
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
					
				} catch (Exception ex) {
					helper.falha();
					
					venda.setStatus(StatusVenda.Cancelada);
					venda.setDataCancelamento(dataHoraCorrente);
					venda.setCodigoMotivoCancelamento(MotivoCancelamento.CancelamentoAutomatico);
					
					cobranca.setStatusCobranca(StatusCobranca.Pendente);
					
					eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.ErroRotinaPagamentoOuCaptura, ex.getLocalizedMessage());
					String mensagem = retornoAutorizacao.getMensagemRetorno();
//					if (mensagem == null || mensagem.isEmpty()) {
//						mensagem = ex.getLocalizedMessage();
//					}
					helper.getRetorno().setMensagemRetorno(mensagem);
					
					if (cobrancaDLO.manter(cobranca) == null) {
						helper.error("Não foi possível persistir entidade \"{}\" para atualização.", cobranca.toString());
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
					
					if (vendaDLO.manter(venda) == null) {
						helper.error("Não foi possível persistir entidade \"{}\" para atualização.", venda.toString());
						throw new BillingWebServiceException(CodigoMensagem.Falha);
					}
				}
			}
			
		} catch (Exception ex) {
			helper.falha();
			helper.error(null, ex);
			if (provedorTransactionId != null && contratoCobranca != null) {
				helper.error("Como o processo de persistência dos dados da venda falhou,"
						+ " a transação com ProvedorTransactionId \"{}\" do provedor {} ({}) poderá"
						+ " ter de ser cancelada externamente ao DBS.",
						provedorTransactionId,
						contratoCobranca.getProvedor().getCodigoProvedor(),
						contratoCobranca.getProvedor().getDescricaoProvedor());
			}
			helper.rollback();
			
		} finally {
			helper.commit();
		}

		return helper.retorno();
	}

	@Override
	public RetornoCancelarVenda cancelarVenda(
			String codigoEmpresa,
			String codigoProduto,
			String codigoSistema,
			String token,
			String cpf,
			String codigoVenda,
//			String dataSolicitacaoCancelamento,
			String codigoMotivoCancelamento,
			String valorEstorno,
			String flagCancelamentoGateway
			) throws BillingException {
		
		ServiceHelper<RetornoCancelarVenda> helper = 
				DbsServiceHelpers.entrando(logger, new RetornoCancelarVenda());
		Date dataHoraCorrente = DateUtils.getDataHoraAtual();
		Empresa empresa = null;
		Produto produto = null;
		Venda venda = null;
		ListaValor listaValor = null;
		ItemLista itemLista = null;
		BigDecimal valorEstornoComoDecimal = null;
//		Date dataSolicitacaoComoDate = null;
		TipoCobranca tipoCobrancaFinal = null;
		Calendar calDataHoje = DateUtils.getCalendarDataHoraAtual();
		boolean cancelandoNoGateway = false;
		
		// Tratamento de espaços espúrios nas strings de entrada.
		token = token.trim();
		codigoEmpresa = codigoEmpresa.trim();
		codigoProduto = codigoProduto.trim();
		codigoSistema = codigoSistema.trim();
		cpf = cpf.trim();
		codigoVenda = codigoVenda.trim();
//		dataSolicitacaoCancelamento = dataSolicitacaoCancelamento.trim();
		codigoMotivoCancelamento = codigoMotivoCancelamento.trim();

		// Valores que são sempre retornados
		helper.getRetorno().setCodigoProduto(codigoProduto);
		helper.getRetorno().setCodigoVenda(codigoVenda);

		
		helper.sucesso();
		helper.begin(ejbContext, false);
		if (!helper.isSucesso()) {
			return helper.retorno();
		}
		
		// VALIDAÇÕES
		
		
		try {
			
			// Código da empresa
			if (helper.isSucesso()) {
				RegraVendaCodigoEmpresa validacaoEmpresa = 
						RegrasNegocioBilling.codigoEmpresa(helper, codigoEmpresa, empresaDLO);
	
				if (validacaoEmpresa.isValida()) {
					empresa = validacaoEmpresa.getEmpresa();
				}
			}
	
			// Código do produto
			if (helper.isSucesso()) {
				
				RegraVendaCodigoProduto validacaoProduto =
						RegrasNegocioBilling.codigoProduto(
								helper, codigoProduto, empresa, produtoDLO);
				
				if (validacaoProduto.isValida()) {
					produto = validacaoProduto.getProduto();
					helper.getRetorno().setCodigoProduto(produto.getCodigo());
				}
			}
			
			// Código do sistema
			if (helper.isSucesso()) {
				
				RegraVendaCodigoSistema validacaoSistema = 
						RegrasNegocioBilling.codigoSistema(
								helper, codigoSistema, produto, sistemaDLO);
				
				validacaoSistema.run();
			}
			
			// Token de validação
			if (helper.isSucesso()) {
				RegraVendaTokenWebService validacaoToken = 
						RegrasNegocioBilling.tokenValidacao(helper, token);
				
				if (!validacaoToken.isValida()) {

					helper.setValorRetorno(CodigoMensagem.DPH0024);
				}
			}
			
			// CPF
			if (helper.isSucesso()) {

				RegraVendaCpf validacaoCpf = RegrasNegocioBilling.cpf(helper, cpf, "cpf");
				validacaoCpf.run();
			}
			
			// Código de Venda
			if (helper.isSucesso()) {

				RegraVendaCodigoVenda validacaoVenda = 
						RegrasNegocioBilling.codigoVendaExiste(
								helper, codigoVenda, produto, empresa, vendaDLO);
				
				if (validacaoVenda.isValida()) {
					venda = validacaoVenda.getVenda();
					tipoCobrancaFinal = venda.getTipoCobranca();
				}
			}
			
			// Data da solicitação de cancelamento
//			if (helper.isSucesso()) {
//				
//				if (!Validador.dataPadrao(dataSolicitacaoCancelamento)
//						|| Validador.obterDataPadrao(dataSolicitacaoCancelamento).after(dataCorrente)) {
//
//					helper.setValorRetorno(CodigoMensagem.DPH0021);
//				} else {
//					
//					dataSolicitacaoComoDate = Validador.obterDataPadrao(dataSolicitacaoCancelamento);
//					if (dataSolicitacaoComoDate.after(venda.getDataFimVigencia())) {
//						
//						helper.setValorRetorno(CodigoMensagem.DPH0022);
//					}
//				}
//			}
			
			// Código do motivo de cancelamento
			if (helper.isSucesso()) {
				
				listaValor = listaValorDLO.obterPorCodigo(TipoListaValor.MotivoCancelamento.getValor());
				
				if (listaValor != null) {
					listaValor = listaValorDLO.completar(listaValor, ListaValor_.itensLista);
				}
				
				if (listaValor != null) {
					for (ItemLista itemListaCandidato : listaValor.getItensLista()) {
						
						if (itemListaCandidato.getCodigo().equals(codigoMotivoCancelamento)) {
							itemLista = itemListaCandidato;
							break;
						}
					}
				}
				
				if (itemLista == null) {
					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"codigoMotivoCancelamento");
				}
			}
			
			if (helper.isSucesso()) {
				boolean valorEstornoValido = Validador.valorEstornoComoDecimal(valorEstorno);
				
				if (valorEstornoValido) {
					valorEstornoComoDecimal = CurrencyUtils.fromString(valorEstorno);
				}
				
				if (valorEstornoComoDecimal == null
						|| ((valorEstornoComoDecimal.compareTo(new BigDecimal(0)) < 0)
								&& (valorEstornoComoDecimal.compareTo(new BigDecimal(-1)) != 0))
						|| (valorEstornoComoDecimal.compareTo(venda.getValorCobranca()) > 0)) {

					helper.setValorRetorno(CodigoMensagem.DPH0028);
				}
			}
			
			if (helper.isSucesso()) {
				
				if (Validador.vazio(flagCancelamentoGateway)
						|| !(flagCancelamentoGateway.equalsIgnoreCase("S") 
								|| flagCancelamentoGateway.equalsIgnoreCase("N"))) {

					helper.setCodigoRetornoParaParametro(
							CodigoMensagem.DPH0001,
							"flagCancelamentoGateway");
				} else {
					
					cancelandoNoGateway = flagCancelamentoGateway.equalsIgnoreCase("S");
				}
			}
			
			// EFETIVAÇÃO
			
			
			if (helper.isSucesso()) {
				
				if (venda.isVigente(dataHoraCorrente)) {
					helper.debug("Cancelando venda vigente \\{{}\\}} com motivo {}.", venda, codigoMotivoCancelamento);
					
					Cobranca cobranca = vendaDLO.obterUltimaCobranca(venda);
					
					Tentativa tentativa = tentativaDLO.gerarTentativa(cobranca, TipoTentativa.Cancelamento);

//					helper.commit();
//					helper.begin(ejbContext, true);
					
					helper.debug("Encontrada última cobrança da venda, \\{{}\\}, gerada nova tentativa de cancelamento \\{{}\\}.", cobranca, tentativa);
					
					if (cancelandoNoGateway) {
						
						RetornoCancelarCobrancaPendente retornoCancelamento = null;
						
						if (valorEstornoComoDecimal.compareTo(new BigDecimal(0)) == 0) {
							tentativa.setTipoTentativa(TipoTentativa.Estorno);
							
							helper.debug("Cancelando cobrança \\{{}\\} no gateway sem estorno, tentativa \\{{}\\}.", cobranca, tentativa);
							
							eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.CobrancaCanceladaComEstornoZero, null);
							
						} else if (valorEstornoComoDecimal.compareTo(new BigDecimal(-1)) == 0) {
							helper.debug("Cancelando cobrança \\{{}\\} integralmente no gateway, tentativa \\{{}\\}.", cobranca, tentativa);
							
							retornoCancelamento = pagador.cancelarCobrancaPendente(cobranca);
							
						} else {
							BigDecimal valorCobrancaOriginal = cobranca.getValorCobranca();
							cobranca.setValorCobranca(valorEstornoComoDecimal);
							
							helper.debug("Cancelando cobrança \\{{}\\} parcialmente no gateway, tentativa \\{{}\\}.", cobranca, tentativa);
							
							retornoCancelamento = pagador.cancelarCobrancaPendente(cobranca);
							cobranca.setValorCobranca(valorCobrancaOriginal);
						}
						
						if (retornoCancelamento != null) {
							
							if (retornoCancelamento.getStatus() == StatusCancelamento.Cancelado) {

								if (retornoCancelamento.isCancelamento()) {									
									cobranca.setStatusCobranca(StatusCobranca.Cancelada);

									helper.debug("Cobrança \\{{}\\} cancelada no gateway.", cobranca);

								} else {
									
									cobranca.setStatusCobranca(StatusCobranca.Estornada);
									cobranca.setDataEstorno(DateUtils.getDataHoraAtual());
									
									if (valorEstornoComoDecimal.compareTo(BigDecimal.valueOf(-1)) == 0) {
										cobranca.setValorEstorno(cobranca.getValorCobranca());
										
									} else {
										cobranca.setValorEstorno(valorEstornoComoDecimal);
									}
									
									tentativa.setTipoTentativa(TipoTentativa.Estorno);

									helper.debug("Cobrança \\{{}\\} estornada no gateway.", cobranca);
								}
								
							} else if (retornoCancelamento.getStatus() == StatusCancelamento.Aceito) {
								
								cobranca.setStatusCobranca(StatusCobranca.EstornoSolicitado);
								cobranca.setDataEstorno(DateUtils.getDataHoraAtual());
								
								if (valorEstornoComoDecimal.compareTo(BigDecimal.valueOf(-1)) == 0) {
									cobranca.setValorEstorno(cobranca.getValorCobranca());
									
								} else {
									cobranca.setValorEstorno(valorEstornoComoDecimal);
								}
								
								tentativa.setTipoTentativa(TipoTentativa.Estorno);

								helper.debug("Cobrança \\{{}\\} com estorno solicitado no gateway.", cobranca);
								
							} else {

								helper.debug("Cobrança \\{{}\\} não cancelada no gateway, código de retorno do método: \\{{}\\}, status: \\{{}\\}.", cobranca, retornoCancelamento.getCodigoRetorno(), retornoCancelamento.getStatus().getValor());
								
								helper.setValorRetorno(CodigoMensagem.DPH0027);
							}
						}
					}

					if (helper.isSucesso()) {
						venda.setStatus(StatusVenda.Cancelada);
						venda.setDataCancelamento(DateUtils.getDataHoraAtual());
						venda.setCodigoMotivoCancelamento(codigoMotivoCancelamento);
	
						eventoDLO.gerarHistoricoEvento(tentativa, TipoEvento.CobrancaCanceladaPorCancelamentoVenda, null);
						
						helper.debug("Efetivando cancelamento da venda \\{{}\\}.", venda);
					}
					
					tentativaDLO.salvar(tentativa);
					cobrancaDLO.salvar(cobranca);
					vendaDLO.salvar(venda);
					
				} else {

					helper.setValorRetorno(CodigoMensagem.DPH0018);
				}
			}
			
		} catch (Exception ex) {
			helper.setValorRetorno(CodigoMensagem.DPH0009);
			helper.error(null, ex);
			helper.rollback();
			
		} finally {
			helper.commit();
		}

		return helper.retorno();
	}
	
}
