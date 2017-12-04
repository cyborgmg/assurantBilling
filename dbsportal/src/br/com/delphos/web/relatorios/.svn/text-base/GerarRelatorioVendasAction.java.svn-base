package br.com.delphos.web.relatorios;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.cobrancas.Cobranca_;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterface;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterfaceDLO;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEventoDLO;
import br.com.delphos.billing.conciliacoes.ProcessamentoConciliacao;
import br.com.delphos.billing.conciliacoes.ProcessamentoConciliacaoDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.StatusProcConciliacaoEvento;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.PesquisaVaziaException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.util.PropertyUtil;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Texto;
import br.com.delphos.util.Validador;

public class GerarRelatorioVendasAction extends org.apache.struts.action.Action {

	private VendaDLO vendaDLO = (VendaDLO) ServiceLocator.lookup("java:global/dbsdb/VendaDLOBean");

	private BandeiraDLO bandeiraDLO = (BandeiraDLO) ServiceLocator.lookup("java:global/dbsdb/BandeiraDLOBean");

	private EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:global/dbsdb/EmpresaDLOBean");

	private SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:global/dbsdb/SistemaDLOBean");

	private ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:global/dbsdb/ProdutoDLOBean");

	private ItemListaDLO itemListaDLO = (ItemListaDLO) ServiceLocator.lookup("java:global/dbsdb/ItemListaDLOBean");

	private CobrancaDLO cobrancaDLO = (CobrancaDLO) ServiceLocator.lookup("java:global/dbsdb/CobrancaDLOBean");

	private ConciliacaoInterfaceDLO conciliacaoInterfaceDLO = (ConciliacaoInterfaceDLO) ServiceLocator
			.lookup("java:global/dbsdb/ConciliacaoInterfaceDLOBean");

	private ProcessamentoConciliacaoDLO processamentoConciliacaoDLO = (ProcessamentoConciliacaoDLO) ServiceLocator
			.lookup("java:global/dbsdb/ProcessamentoConciliacaoDLOBean");

	private ProcConciliacaoEventoDLO procConciliacaoEventoDLO = (ProcConciliacaoEventoDLO) ServiceLocator
			.lookup("java:global/dbsdb/ProcConciliacaoEventoDLOBean");
	
	private String propertyFileClassPathName = "/br/com/delphos/web/ApplicationResource.properties";

	private String nomeRelatorio = null;

	private final int WIDTH_CHARACTER = 256;

	private static final Comparator<Tentativa> MAIOR_NUM_TENTATIVA = new Comparator<Tentativa>() {
		public int compare(Tentativa tentativa1, Tentativa tentativa2) {
			return tentativa2.getNumeroTentativa() - tentativa1.getNumeroTentativa();
		}
	};

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		ActionForward retorno = null;
		ActionMessages messages = new ActionMessages();
		GerarRelatorioVendasActionForm f = (GerarRelatorioVendasActionForm) form;

		String codEmpresa = f.getEmpresa();
		String idProduto = f.getProduto();
		String idSistema = f.getSistema();
		String idStatus = f.getStatus();
		String relatorio = f.getRelatorio();
		String periodoDe = f.getPeriodoDe();
		String periodoAte = f.getPeriodoAte();
		
		// Ao enviar o XLS/arquivo com os dados de um relatório, o ciclo de vida da 
		// página JSF é interrompido bruscamente. Uma vez que uma requisição tenha sido respondida
		// com getWriter() (como é o caso de arquivos JSP/JSF/HTML), é incorreto chamar
		// getOutputStream(), pois a resposta já foi definida como texto, e não binário.
		// Quando isto acontece, a requisição não é redirecionada, e o processamento posterior
		// da página é interrompido. O efeito para o usuário é a permanência da página em seu estado atual
		// (não há recarregamento ou redirecionamento). Portanto, quaisquer alterações que sejam esperadas
		// na página em decorrência de mudanças ou criações de atributos de sessão ou de requisição
		// não se aplicarão.

		try {

			if (f.getOp().equals("6"))
				retorno = executeGerarRelatorioPrimeiraCobranca(mapping, f, request, response);
			if (f.getOp().equals("7"))
				retorno = executeGerarRelatorioVendasEstornadas(mapping, f, request, response);
			if (f.getOp().equals("10"))
				retorno = executeGerarRelatorioCobrancasNaoConciliadas(mapping, f, request, response);
			if (f.getOp().equals("11"))
				retorno = executeGerarRelatorioCobrancaConciliadaInterface(mapping, f, request, response);
			if (f.getOp().equals("16"))
				retorno = executeGerarRelatorioConciliacao(mapping, f, request, response);

		} catch (PesquisaVaziaException ex) {
			// (Infelizmente), a página de relatórios usa atributos de sessão para carregar os dados
			// da pesquisa durante os redirecionamentos envolvidos na geração do relatório; uma
			// vez terminado o processamento no servidor, a intenção é que os dados na tela do
			// usuário não se alterem, de forma que o usuário possa fazer alterações pontuais nos
			// parâmetros de pesquisa caso e.g. a pesquisa seja vazia. Uma vez utilizados, os atributos
			// em sessão utilizados são removidos na própria página, como uma das últimas etapas da
			// renderização. Desta forma, a página irá ser carregada em seu estado inicial quando for
			// recarregada (e.g. navegar até relatórios após operações de cadastro).
			session.setAttribute("codEmpresa", codEmpresa);
			session.setAttribute("idProduto", idProduto);
			session.setAttribute("idSistema", idSistema);
			session.setAttribute("status", idStatus);
			session.setAttribute("relatorio", relatorio);
			session.setAttribute("periodoDe", periodoDe);
			session.setAttribute("periodoAte", periodoAte);
			
			session.setAttribute("pesquisaVazia", "pesquisaVazia");
			String msg = DLOException.obterMensagemDLO(ex);
			ActionMessage erro = new ActionMessage(msg, false);

			messages.add("dialogoErro", erro);
			this.addErrors(request, messages);
			
		} catch (Exception ex) {
			ex.printStackTrace();

			String msg = DLOException.obterMensagemDLO(ex);
			ActionMessage erro = new ActionMessage(msg, false);

			messages.add("dialogoErro", erro);
			this.addErrors(request, messages);
		}
		
		if (retorno != null) {
			return retorno;
		} else {
			return mapping.getInputForward();
		}
	}

	private ActionForward executeGerarRelatorioConciliacao(ActionMapping mapping, GerarRelatorioVendasActionForm f,
			HttpServletRequest request, HttpServletResponse response) throws BillingException {

		try {

			Locale.setDefault(new Locale("pt", "BR"));
			// filtro
			Cobranca cobrancaFiltro = new Cobranca();
			Venda venda = new Venda();
			cobrancaFiltro.setVenda(venda);

			Empresa e = null;

			e = getEmpresaFiltro(f.getEmpresa());

			if (e != null)
				cobrancaFiltro.getVenda().setEmpresa(e);

			// produto
			Produto p = null;
			p = getProdutoFiltro(f.getProduto());

			if (p != null)
				cobrancaFiltro.getVenda().setProduto(p);

			// sistema
			Sistema s = null;
			s = getSistemaFiltro(f.getSistema());

			if (s != null)
				cobrancaFiltro.getVenda().setSistema(s);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);

			Date dataInicial = null;
			Date dataFinal = null;

			if (!"".equals(f.getPeriodoDe()) && !"".equals(f.getPeriodoAte())) {
				dataFinal = sdf.parse(f.getPeriodoAte());
				dataInicial = sdf.parse(f.getPeriodoDe());
			}

			ItemLista itemLista = null;

			if (!f.getStatus().equals("0")) {

				ItemLista itemListaFiltro = new ItemLista();

				itemListaFiltro.setId(Long.parseLong(f.getStatus()));

				itemLista = itemListaDLO.obter(itemListaFiltro.getId());

			}

			List<Cobranca> cobrancasConciliacao = null;

			cobrancasConciliacao = cobrancaDLO.listarCobrancasConciliacao(cobrancaFiltro, dataInicial, dataFinal,
					itemLista);

			if (cobrancasConciliacao != null && !cobrancasConciliacao.isEmpty()) {
				geraPlanilhaCobrancasConciliacao(cobrancasConciliacao, response, f);
			} else {
				throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia");
			}

		} catch (IOException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		} catch (ParseException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		}

		return mapping.getInputForward();
	}

	private ActionForward executeGerarRelatorioPrimeiraCobranca(ActionMapping mapping, GerarRelatorioVendasActionForm f,
			HttpServletRequest request, HttpServletResponse response) throws BillingException {
		
		try {
			Locale.setDefault(new Locale("pt", "BR"));
	
			List<Venda> vendasConcretizadas = null;
	
			// filtro
			Venda venda = new Venda();
	
			Empresa e = null;
			e = getEmpresaFiltro(f.getEmpresa());
	
			if (e != null)
				venda.setEmpresa(e);
	
			// produto
			Produto p = null;
			p = getProdutoFiltro(f.getProduto());
	
			if (p != null)
				venda.setProduto(p);
	
			// sistema
			Sistema s = null;
			s = getSistemaFiltro(f.getSistema());
	
			if (s != null)
				venda.setSistema(s);
			
			ItemLista itemLista = null;
	
			if (!f.getStatus().equals("0")) {
	
				ItemLista itemListaFiltro = new ItemLista();
	
				itemListaFiltro.setId(Long.parseLong(f.getStatus()));
	
				itemLista = itemListaDLO.obter(itemListaFiltro.getId());
	
			}
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
	
			Date dataInicial = null;
			Date dataFinal = null;
	
			if (!"".equals(f.getPeriodoDe()) && !"".equals(f.getPeriodoAte())) {
				dataInicial = sdf.parse(f.getPeriodoDe());
				dataFinal = sdf.parse(f.getPeriodoAte());
			}
	
			vendasConcretizadas = vendaDLO.listarVendasConcretizadas(venda, dataInicial, dataFinal, itemLista);
	
			if (vendasConcretizadas != null && !vendasConcretizadas.isEmpty()) {
				geraPlanilhaVendasConcretizadas(vendasConcretizadas, response, f);
			} else {
				throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia");
				// request.setAttribute("msgErro",
				// Mensagens.get("msg.info.dados.pesquisa.vazia"));
				// throw new DLOException(
				// Mensagens.get("msg.info.dados.pesquisa.vazia"));
			}
		
		} catch (IOException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		} catch (ParseException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		}

		return mapping.getInputForward();

	}

	private ActionForward executeGerarRelatorioVendasEstornadas(ActionMapping mapping, GerarRelatorioVendasActionForm f,
			HttpServletRequest request, HttpServletResponse response) throws BillingException {

		try {
			Locale.setDefault(new Locale("pt", "BR"));
	
			List<Venda> vendasEstornadas = null;
	
			// filtro
			Venda venda = new Venda();
	
			Empresa e = null;
			e = getEmpresaFiltro(f.getEmpresa());
	
			if (e != null)
				venda.setEmpresa(e);
	
			// produto
			Produto p = null;
			p = getProdutoFiltro(f.getProduto());
	
			if (p != null)
				venda.setProduto(p);
	
			// sistema
			Sistema s = null;
			s = getSistemaFiltro(f.getSistema());
	
			if (s != null)
				venda.setSistema(s);
	
			ItemLista itemLista = null;
	
			if (!f.getStatus().equals("0")) {
	
				ItemLista itemListaFiltro = new ItemLista();
	
				itemListaFiltro.setId(Long.parseLong(f.getStatus()));
	
				itemLista = itemListaDLO.obter(itemListaFiltro.getId());
	
			}
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
	
			Date dataInicial = null;
			Date dataFinal = null;
	
			if (!"".equals(f.getPeriodoDe()) && !"".equals(f.getPeriodoAte())) {
				dataInicial = sdf.parse(f.getPeriodoDe());
				dataFinal = sdf.parse(f.getPeriodoAte());
			}
	
			vendasEstornadas = vendaDLO.listarVendasEstornadas(venda, dataInicial, dataFinal, itemLista);
	
			if (vendasEstornadas != null && !vendasEstornadas.isEmpty()) {
				geraPlanilhaVendasEstornadas(vendasEstornadas, response, f);
			} else {
				throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia");
			}

		} catch (IOException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		} catch (ParseException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		}

		return mapping.getInputForward();

	}

	private ActionForward executeGerarRelatorioCobrancasNaoConciliadas(ActionMapping mapping,
			GerarRelatorioVendasActionForm f, HttpServletRequest request, HttpServletResponse response)
					throws BillingException {

		try {
			Locale.setDefault(new Locale("pt", "BR"));
	
			List<Cobranca> cobrancasNaoConciliadas = null;
	
			// filtro
			Cobranca cobranca = new Cobranca();
			Venda venda = new Venda();
			cobranca.setVenda(venda);
	
			Empresa e = null;
			e = getEmpresaFiltro(f.getEmpresa());
	
			if (e != null)
				cobranca.getVenda().setEmpresa(e);
	
			// produto
			Produto p = null;
			p = getProdutoFiltro(f.getProduto());
	
			if (p != null)
				cobranca.getVenda().setProduto(p);
	
			// sistema
			Sistema s = null;
			s = getSistemaFiltro(f.getSistema());
	
			if (s != null)
				cobranca.getVenda().setSistema(s);
	
			cobrancasNaoConciliadas = cobrancaDLO.listarCobrancasNaoConciliadas(cobranca,
					Double.parseDouble(f.getDiasAtraso()));
	
			if (cobrancasNaoConciliadas != null && !cobrancasNaoConciliadas.isEmpty()) {
				geraPlanilhaCobrancasNaoConciliadas(cobrancasNaoConciliadas, response, f);
			} else {
				throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia");
			}

		} catch (IOException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		}

		return mapping.getInputForward();

	}

	private ActionForward executeGerarRelatorioCobrancaConciliadaInterface(ActionMapping mapping,
			GerarRelatorioVendasActionForm f, HttpServletRequest request, HttpServletResponse response)
					throws BillingException {

		try {
			Locale.setDefault(new Locale("pt", "BR"));
	
			// filtro
			Cobranca cobrancaFiltro = new Cobranca();
			Venda venda = new Venda();
			cobrancaFiltro.setVenda(venda);
	
			Empresa e = null;
			e = getEmpresaFiltro(f.getEmpresa());
	
			if (e != null)
				cobrancaFiltro.getVenda().setEmpresa(e);
	
			// produto
			Produto p = null;
			p = getProdutoFiltro(f.getProduto());
	
			if (p != null)
				cobrancaFiltro.getVenda().setProduto(p);
	
			// sistema
			Sistema s = null;
			s = getSistemaFiltro(f.getSistema());
	
			if (s != null)
				cobrancaFiltro.getVenda().setSistema(s);
	
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(false);
	
			Date dataInicial = null;
			Date dataFinal = null;
	
			if (!"".equals(f.getPeriodoDe()) && !"".equals(f.getPeriodoAte())) {
				dataInicial = sdf.parse(f.getPeriodoDe());
				dataFinal = sdf.parse(f.getPeriodoAte());
			}
	
			ItemLista itemLista = null;
	
			if (!f.getStatus().equals("0")) {
	
				ItemLista itemListaFiltro = new ItemLista();
	
				itemListaFiltro.setId(Long.parseLong(f.getStatus()));
	
				itemLista = itemListaDLO.obter(itemListaFiltro.getId());
	
			}
	
			List<Cobranca> cobrancasConciliadasInterface = null;
	
			cobrancasConciliadasInterface = cobrancaDLO.listarCobrancasConciliadasInterface(cobrancaFiltro, dataInicial,
					dataFinal, itemLista);
	
			if (cobrancasConciliadasInterface != null && !cobrancasConciliadasInterface.isEmpty()) {
				geraPlanilhaCobrancasConciliadasInterface(cobrancasConciliadasInterface, response, f);
			} else {
				throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia");
			}

		} catch (IOException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		} catch (ParseException e1) {
			throw new PesquisaVaziaException("msg.info.dados.pesquisa.vazia", e1);
		}

		return mapping.getInputForward();

	}

	private void geraPlanilhaCobrancasConciliacao(List<Cobranca> cobrancasNaoConciliadas, HttpServletResponse response,
			GerarRelatorioVendasActionForm form) throws IOException, DLOException {
		geraPlanilhaCobrancasConciliacaoXSSF(cobrancasNaoConciliadas, response, form);
	}

	private void geraPlanilhaCobrancasConciliadasInterface(List<Cobranca> cobrancasConciliadasInterface,
			HttpServletResponse response, GerarRelatorioVendasActionForm form) throws IOException, DLOException {
		geraPlanilhaCobrancasConciliadasInterfaceXSSF(cobrancasConciliadasInterface, response, form);
	}

	private void geraPlanilhaVendasConcretizadas(List<Venda> vendasConcretizadas, HttpServletResponse response,
			GerarRelatorioVendasActionForm form) throws IOException, DLOException {
		geraPlanilhaVendasConcretizadasXSSF(vendasConcretizadas, response, form);
	}

	private void geraPlanilhaVendasEstornadas(List<Venda> VendasEstornadas, HttpServletResponse response,
			GerarRelatorioVendasActionForm form) throws IOException, DLOException {
		geraPlanilhaVendasEstornadasXSSF(VendasEstornadas, response, form);
	}

	private void geraPlanilhaCobrancasNaoConciliadas(List<Cobranca> cobrancasNaoConciliadas,
			HttpServletResponse response, GerarRelatorioVendasActionForm form) throws IOException, DLOException {
		geraPlanilhaCobrancasNaoConciliadasXSSF(cobrancasNaoConciliadas, response, form);
	}

	private void geraPlanilhaCobrancasConciliadasInterfaceXSSF(List<Cobranca> cobrancasConciliadasInterface,
			HttpServletResponse response, GerarRelatorioVendasActionForm form) throws IOException, DLOException {

		nomeRelatorio = PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.conciliada.interface");

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheetCobrancasConciliadasInterface = workbook.createSheet(PropertyUtil
				.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.planilha.aba.nome"));

		int columnIndex = -1;

		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// EMPRESA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// SISTEMA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PRODUTO
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																									// DA
																									// VENDA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// CODIGO
																									// DA
																									// VENDA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// CPF
																									// DO
																									// CLIENTE
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// NOME
																									// DO
																									// CLIENTE
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PARCELA
																									// DE
																									// COBRANCA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																									// DA
																									// COBRANÇA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// MEIO
																									// DE
																									// PAGAMENTO
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 10));// BANDEIRA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// VALOR
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 22));// STATUS
																									// DA
																									// COBRANÇA
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// SITUAÇÃO
																									// CONCILIACAO
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																									// DE
																									// REPASSE
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// DATA
																									// DE
																									// PROCESSAMEMENTO
		sheetCobrancasConciliadasInterface.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// MENSAGEM
																									// DE
																									// PROCESSAMEMENTO

		CellStyle cellStyleCenter = workbook.createCellStyle();
		cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle cellStyleLeft = workbook.createCellStyle();
		cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);

		CellStyle cellStyleRight = workbook.createCellStyle();
		cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);

		String dataGeracao = Data.formatar(new Date(), Data.MASCARA_COM_HORA);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		int rownum = 0;

		XSSFRow headerrow1 = sheetCobrancasConciliadasInterface.createRow(rownum++);
		XSSFCell headerrow1cell0 = headerrow1.createCell(0);
		headerrow1cell0.setCellValue(nomeRelatorio);

		XSSFRow headerrow2 = sheetCobrancasConciliadasInterface.createRow(rownum++);
		XSSFCell headerrow2cell0 = headerrow2.createCell(0);
		headerrow2cell0.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.data.geracao") + dataGeracao);

		XSSFRow headerrow3 = sheetCobrancasConciliadasInterface.createRow(rownum++);

		XSSFCell headerrow3cell0 = headerrow3.createCell(0);
		headerrow3cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.empresa"));

		Empresa empresa = getEmpresaFiltro(form.getEmpresa());

		XSSFCell headerrow3cell1 = headerrow3.createCell(1);
		headerrow3cell1.setCellValue((empresa != null ? empresa.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.todas"))));

		XSSFRow headerrow4 = sheetCobrancasConciliadasInterface.createRow(rownum++);

		XSSFCell headerrow4cell0 = headerrow4.createCell(0);
		headerrow4cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.sistema")));

		Sistema sistema = getSistemaFiltro(form.getSistema());

		XSSFCell headerrow4cell1 = headerrow4.createCell(1);
		headerrow4cell1.setCellValue((sistema != null ? sistema.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.todos"))));

		XSSFRow headerrow5 = sheetCobrancasConciliadasInterface.createRow(rownum++);

		XSSFCell headerrow5cell0 = headerrow5.createCell(0);
		headerrow5cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.produto")));

		Produto produto = getProdutoFiltro(form.getProduto());

		XSSFCell headerrow5cell1 = headerrow5.createCell(1);
		headerrow5cell1.setCellValue((produto != null ? produto.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.todos"))));

		XSSFRow headerrow6 = sheetCobrancasConciliadasInterface.createRow(rownum++);
		XSSFCell headerrow6cell0 = headerrow6.createCell(0);
		headerrow6cell0.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.situacao.conciliacao")));

		XSSFCell headerrow6cell1 = headerrow6.createCell(1);

		ItemLista itemListaHeader = null;

		if (!form.getStatus().equals("0")) {

			ItemLista itemListaFiltro = new ItemLista();

			itemListaFiltro.setId(Long.parseLong(form.getStatus()));

			itemListaHeader = itemListaDLO.obter(itemListaFiltro.getId());
			headerrow6cell1.setCellValue(itemListaHeader.getDescricao());
		} else
			headerrow6cell1.setCellValue(
					(PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.todas")));

		XSSFRow headerrowSpace = sheetCobrancasConciliadasInterface.createRow(rownum++);

		XSSFRow headerrow7 = sheetCobrancasConciliadasInterface.createRow(rownum++);

		int headerColumnIndex = 0;

		// EMPRESA
		XSSFCell headerrow7cell0 = headerrow7.createCell(headerColumnIndex);
		headerrow7cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.empresa"));
		headerrow7cell0.setCellStyle(cellStyleCenter);

		// SISTEMA
		XSSFCell headerrow7cell1 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell1.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.sistema"));
		headerrow7cell1.setCellStyle(cellStyleCenter);

		// PRODUTO
		XSSFCell headerrow7cell2 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell2.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.conciliada.interface.produto"));
		headerrow7cell2.setCellStyle(cellStyleCenter);

		// DATA DA VENDA
		XSSFCell headerrow7cell3 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell3.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.data.venda"));
		headerrow7cell3.setCellStyle(cellStyleCenter);

		// CODIGO DA VENDA
		XSSFCell headerrow7cell4 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell4.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.codigo.venda"));
		headerrow7cell4.setCellStyle(cellStyleCenter);

		// CPF DO CLIENTE
		XSSFCell headerrow7cell5 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell5.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.cpf.cliente"));
		headerrow7cell5.setCellStyle(cellStyleCenter);

		// NOME DO CLIENTE
		XSSFCell headerrow7cell6 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell6.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.nome.cliente"));
		headerrow7cell6.setCellStyle(cellStyleCenter);

		// PARCELA DE COBRANCA
		XSSFCell headerrow7cell10 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell10.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.parcela"));
		headerrow7cell10.setCellStyle(cellStyleCenter);

		// DATA DA COBRANÇA
		XSSFCell headerrow7cell11 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell11.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.data.cobranca"));
		headerrow7cell11.setCellStyle(cellStyleCenter);

		// MEIO DE PAGAMENTO
		XSSFCell headerrow7cell7 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell7.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.meio.pagamento"));
		headerrow7cell7.setCellStyle(cellStyleCenter);

		// BANDEIRA
		XSSFCell headerrow7cell8 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell8.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.bandeira"));
		headerrow7cell8.setCellStyle(cellStyleCenter);

		// VALOR
		XSSFCell headerrow7cell9 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell9.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.valor"));
		headerrow7cell9.setCellStyle(cellStyleCenter);

		// STATUS DA COBRANÇA
		XSSFCell headerrow7cell12 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell12.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.status.cobranca"));
		headerrow7cell12.setCellStyle(cellStyleCenter);

		// SITUAÇÃO DA CONCILIACAO
		XSSFCell headerrow7cell14 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell14.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.situacao.conciliacao"));
		headerrow7cell14.setCellStyle(cellStyleCenter);

		// DATA DE REPASSE
		XSSFCell headerrow7cell13 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell13.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.planilha.data.repasse"));
		headerrow7cell13.setCellStyle(cellStyleCenter);

		// DATA DE PROCESSAMENTO
		XSSFCell headerrow7cell16 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell16.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.data.processamento"));
		headerrow7cell16.setCellStyle(cellStyleCenter);

		// MESAGEM DE PROCESSAMEMENTO
		XSSFCell headerrow7cell15 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell15.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.mensagem.processamento"));
		headerrow7cell15.setCellStyle(cellStyleCenter);

		Bandeira bandeira = null;

		for (Cobranca cobranca : cobrancasConciliadasInterface) {

			XSSFRow XSSFRow = sheetCobrancasConciliadasInterface.createRow(rownum++);

			int cellnum = 0;

			XSSFCell cellEmpresa = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getEmpresa() != null && cobranca.getVenda().getEmpresa().getDescricao() != null)
				cellEmpresa.setCellValue(cobranca.getVenda().getEmpresa().getDescricao());
			else
				cellEmpresa.setCellValue("");

			XSSFCell cellSistema = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getSistema() != null && cobranca.getVenda().getSistema().getDescricao() != null)
				cellSistema.setCellValue(cobranca.getVenda().getSistema().getDescricao());
			else
				cellSistema.setCellValue("");

			XSSFCell cellProduto = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getProduto() != null && cobranca.getVenda().getProduto().getDescricao() != null)
				cellProduto.setCellValue(cobranca.getVenda().getProduto().getDescricao());
			else
				cellProduto.setCellValue("");

			XSSFCell cellDataVenda = XSSFRow.createCell(cellnum++);
			cellDataVenda.setCellStyle(cellStyleCenter);
			if (cobranca.getVenda().getDataVendaOrigem() != null)
				cellDataVenda.setCellValue(sdf.format(cobranca.getVenda().getDataVendaOrigem()));
			else
				cellDataVenda.setCellValue("");

			XSSFCell cellCodigoVenda = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getCodigoVendaOrigem() != null)
				cellCodigoVenda.setCellValue(cobranca.getVenda().getCodigoVendaOrigem());
			else
				cellCodigoVenda.setCellValue("");

			XSSFCell cellCPFCliente = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getCpf() != null)
				cellCPFCliente.setCellValue(cobranca.getVenda().getCpf());
			else
				cellCPFCliente.setCellValue("");

			XSSFCell cellNomeCliente = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getNome() != null)
				cellNomeCliente.setCellValue(cobranca.getVenda().getNome());
			else
				cellNomeCliente.setCellValue("");

			XSSFCell cellParcelaCobranca = XSSFRow.createCell(cellnum++);
			cellParcelaCobranca.setCellValue(cobranca.getNumeroParcela());// Entidade
																			// Cobranca

			XSSFCell cellDataCobranca = XSSFRow.createCell(cellnum++);

			if (cobranca.getDataCobranca() != null) {
				cellDataCobranca.setCellValue(sdf.format(cobranca.getDataCobranca()));// Entidade
																						// Cobranca
				cellDataCobranca.setCellStyle(cellStyleCenter);
			} else
				cellDataCobranca.setCellValue("");

			XSSFCell cellMeioPag = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getContratoCobranca() != null
					&& cobranca.getVenda().getContratoCobranca().getMeioPagamento() != null
					&& cobranca.getVenda().getContratoCobranca().getMeioPagamento().getDescricao() != null)
				cellMeioPag.setCellValue(cobranca.getVenda().getContratoCobranca().getMeioPagamento().getDescricao());
			else
				cellMeioPag.setCellValue("");

			bandeira = bandeiraDLO.obterPorCodigo(cobranca.getVenda().getCodigoBandeiraCartao());
			XSSFCell cellBandeira = XSSFRow.createCell(cellnum++);
			if (bandeira != null && bandeira.getNomeBandeira() != null)
				cellBandeira.setCellValue(bandeira.getNomeBandeira());// Entidade
																		// Bandeira
			else
				cellBandeira.setCellValue("");

			XSSFCell cellValor = XSSFRow.createCell(cellnum++);
			cellValor.setCellStyle(cellStyleRight);
			if (cobranca.getVenda().getValorCobranca() != null)
				cellValor.setCellValue(Texto.doubleToMoeda(cobranca.getVenda().getValorCobranca().doubleValue(),
						"###,###,###,##0.00"));// Entidade Venda
			else
				cellValor.setCellValue("");

			XSSFCell cellStatus = null;
			ItemLista itemListaCellStatus = itemListaDLO.obterPorCodigo(cobranca.getStatusCobranca().getValor(),
					TipoListaValor.Status.getValor());

			if (cobranca.getStatusCobranca() != null && itemListaCellStatus != null
					&& itemListaCellStatus.getDescricao() != null) {
				cellStatus = XSSFRow.createCell(cellnum++);
				cellStatus.setCellValue(itemListaCellStatus.getDescricao());// Entidade
																			// Cobranca
			} else {
				cellStatus = XSSFRow.createCell(cellnum++);
				cellStatus.setCellValue("");
			}

			// SITUAÇÃO CONCILIACAO
			ConciliacaoInterface conciliacaoInterface = conciliacaoInterfaceDLO.obterPorCobranca(cobranca);

			XSSFCell cellStatusConciliacaoInterface = XSSFRow.createCell(cellnum++);

			if (conciliacaoInterface != null && conciliacaoInterface.getStatus().getValor() != null) {

				ItemLista itemListaCellStatusConciliacaoInterface = itemListaDLO.obterPorCodigo(
						conciliacaoInterface.getStatus().getValor(), TipoListaValor.SituacaoConciliacao.getValor());

				if (conciliacaoInterface.getStatus().getValor()
						.equals(StatusConciliacaoInterface.ATIVA.getValor())
						|| conciliacaoInterface.getStatus().getValor()
								.equals(StatusConciliacaoInterface.ANULADA.getValor()))
					cellStatusConciliacaoInterface.setCellValue(itemListaCellStatusConciliacaoInterface.getDescricao());
			} else
				cellStatusConciliacaoInterface.setCellValue("");

			// DATA REPASSE
			XSSFCell cellDataRepasse = XSSFRow.createCell(cellnum++);

			if (conciliacaoInterface != null && conciliacaoInterface.getDataPagamento() != null) {
				cellDataRepasse.setCellValue(sdf.format(conciliacaoInterface.getDataPagamento()));// Entidade
																									// Cobranca
				cellDataRepasse.setCellStyle(cellStyleCenter);
			} else
				cellDataRepasse.setCellValue("");

			// DATA PROCESSAMEMENTO
			XSSFCell cellDataProcesssamento = XSSFRow.createCell(cellnum++);

			if (conciliacaoInterface != null && conciliacaoInterface.getDataProcessamento() != null) {
				cellDataProcesssamento.setCellValue(sdf.format(conciliacaoInterface.getDataProcessamento()));// Entidade
																												// Cobranca
				cellDataProcesssamento.setCellStyle(cellStyleCenter);
			} else
				cellDataProcesssamento.setCellValue("");

			// MENSAGEM PROCESSAMEMENTO
			XSSFCell cellMensagemProcessamento = XSSFRow.createCell(cellnum++);

			if (conciliacaoInterface != null && conciliacaoInterface.getMensagemProcessamento() != null)
				cellMensagemProcessamento.setCellValue(conciliacaoInterface.getMensagemProcessamento());
			else
				cellMensagemProcessamento.setCellValue("");

		}

		String filePrefixName = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.arquivo.nome"));
		String fileExtension = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.conciliada.interface.arquivo.extensao.xssf"));
		String fileDateCreationName = dataGeracao;
		fileDateCreationName = (fileDateCreationName.replace("/", "").replace(":", "").replace(" ", "")).trim();
		String subheader = (filePrefixName + fileDateCreationName + fileExtension);
		response.setHeader("Content-Disposition", "attachment; filename=" + (subheader));

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}

	private void geraPlanilhaCobrancasNaoConciliadasXSSF(List<Cobranca> cobrancasNaoConciliadas,
			HttpServletResponse response, GerarRelatorioVendasActionForm form) throws IOException, DLOException {

		nomeRelatorio = PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.nao.conciliada");

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheetCobrancasNaoConciliadas = workbook.createSheet(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.planilha.aba.nome"));

		int columnIndex = -1;

		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// EMPRESA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// SISTEMA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PRODUTO
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																							// DA
																							// VENDA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// CODIGO
																							// DA
																							// VENDA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// CPF
																							// DO
																							// CLIENTE
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// NOME
																							// DO
																							// CLIENTE
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// MEIO
																							// DE
																							// PAGAMENTO
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 10));// BANDEIRA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// VALOR
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PARCELAS
																							// DE
																							// COBRANCA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																							// DA
																							// COBRANÇA
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 22));// STATUS
																							// DA
																							// COBRANÇA
		// sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex,
		// (WIDTH_CHARACTER*20));//DATA DE REPASSE
		sheetCobrancasNaoConciliadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// DIAS
																							// DE
																							// ATRASO

		CellStyle cellStyleCenter = workbook.createCellStyle();
		cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle cellStyleLeft = workbook.createCellStyle();
		cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);

		CellStyle cellStyleRight = workbook.createCellStyle();
		cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);

		String dataGeracao = Data.formatar(new Date(), Data.MASCARA_COM_HORA);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		int rownum = 0;

		XSSFRow headerrow1 = sheetCobrancasNaoConciliadas.createRow(rownum++);
		XSSFCell headerrow1cell0 = headerrow1.createCell(0);
		headerrow1cell0.setCellValue(nomeRelatorio);

		XSSFRow headerrow2 = sheetCobrancasNaoConciliadas.createRow(rownum++);
		XSSFCell headerrow2cell0 = headerrow2.createCell(0);
		headerrow2cell0.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.data.geracao") + dataGeracao);

		XSSFRow headerrow3 = sheetCobrancasNaoConciliadas.createRow(rownum++);

		XSSFCell headerrow3cell0 = headerrow3.createCell(0);
		headerrow3cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.empresa"));

		Empresa empresa = getEmpresaFiltro(form.getEmpresa());

		XSSFCell headerrow3cell1 = headerrow3.createCell(1);
		headerrow3cell1.setCellValue((empresa != null ? empresa.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.todas"))));

		XSSFRow headerrow4 = sheetCobrancasNaoConciliadas.createRow(rownum++);

		XSSFCell headerrow4cell0 = headerrow4.createCell(0);
		headerrow4cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.sistema")));

		Sistema sistema = getSistemaFiltro(form.getSistema());

		XSSFCell headerrow4cell1 = headerrow4.createCell(1);
		headerrow4cell1.setCellValue((sistema != null ? sistema.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.todos"))));

		XSSFRow headerrow5 = sheetCobrancasNaoConciliadas.createRow(rownum++);

		XSSFCell headerrow5cell0 = headerrow5.createCell(0);
		headerrow5cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.produto")));

		Produto produto = getProdutoFiltro(form.getProduto());

		XSSFCell headerrow5cell1 = headerrow5.createCell(1);
		headerrow5cell1.setCellValue((produto != null ? produto.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.todos"))));

		XSSFRow headerrow6 = sheetCobrancasNaoConciliadas.createRow(rownum++);
		XSSFCell headerrow6cell0 = headerrow6.createCell(0);
		headerrow6cell0.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.dias.atraso")));

		XSSFCell headerrow6cell1 = headerrow6.createCell(1);
		headerrow6cell1.setCellValue(form.getDiasAtraso());

		XSSFRow headerrowSpace = sheetCobrancasNaoConciliadas.createRow(rownum++);

		XSSFRow headerrow7 = sheetCobrancasNaoConciliadas.createRow(rownum++);

		int headerColumnIndex = 0;

		// Empresa
		XSSFCell headerrow7cell0 = headerrow7.createCell(headerColumnIndex);
		headerrow7cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.empresa"));
		headerrow7cell0.setCellStyle(cellStyleCenter);

		// Sistema
		XSSFCell headerrow7cell1 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell1.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.sistema"));
		headerrow7cell1.setCellStyle(cellStyleCenter);

		// Produto
		XSSFCell headerrow7cell2 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell2.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.produto"));
		headerrow7cell2.setCellStyle(cellStyleCenter);

		// Data da Venda
		XSSFCell headerrow7cell3 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell3.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.data.venda"));
		headerrow7cell3.setCellStyle(cellStyleCenter);

		// Código de Venda
		XSSFCell headerrow7cell4 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell4.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.codigo.venda"));
		headerrow7cell4.setCellStyle(cellStyleCenter);

		// CPF do Cliente
		XSSFCell headerrow7cell5 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell5.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.cpf.cliente"));
		headerrow7cell5.setCellStyle(cellStyleCenter);

		// Nome do Cliente
		XSSFCell headerrow7cell6 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell6.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.nome.cliente"));
		headerrow7cell6.setCellStyle(cellStyleCenter);

		// Meio de Pagamento
		XSSFCell headerrow7cell7 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell7.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.meio.pagamento"));
		headerrow7cell7.setCellStyle(cellStyleCenter);

		// Bandeira
		XSSFCell headerrow7cell8 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell8.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.planilha.bandeira"));
		headerrow7cell8.setCellStyle(cellStyleCenter);

		// Valor
		XSSFCell headerrow7cell9 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell9.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.planilha.valor"));
		headerrow7cell9.setCellStyle(cellStyleCenter);

		// parcela de cobranca
		XSSFCell headerrow7cell10 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell10.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "cobranca.nao.conciliada.planilha.parcela"));
		headerrow7cell10.setCellStyle(cellStyleCenter);

		// Data da Cobrança
		XSSFCell headerrow7cell11 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell11.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.data.cobranca"));
		headerrow7cell11.setCellStyle(cellStyleCenter);

		// STATUS DA COBRANÇA
		XSSFCell headerrow7cell12 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell12.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.status.cobranca"));
		headerrow7cell12.setCellStyle(cellStyleCenter);

		/*
		 * //DATA DE REPASSE XSSFCell headerrow7cell13 =
		 * headerrow7.createCell(++headerColumnIndex);
		 * headerrow7cell13.setCellValue(PropertyUtil.getPropertyValue(
		 * propertyFileClassPathName,
		 * "cobranca.nao.conciliada.planilha.data.repasse"));
		 * headerrow7cell13.setCellStyle(cellStyleCenter);
		 */

		// DIAS DE ATRASO
		XSSFCell headerrow7cell14 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell14.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.planilha.dias.atraso"));
		headerrow7cell14.setCellStyle(cellStyleCenter);

		Bandeira bandeira = null;

		for (Cobranca cobranca : cobrancasNaoConciliadas) {

			cobranca = cobrancaDLO.completar(cobranca, Cobranca_.contratoCobranca.toString());
			XSSFRow XSSFRow = sheetCobrancasNaoConciliadas.createRow(rownum++);

			int cellnum = 0;

			XSSFCell cellEmpresa = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getEmpresa() != null && cobranca.getVenda().getEmpresa().getDescricao() != null)
				cellEmpresa.setCellValue(cobranca.getVenda().getEmpresa().getDescricao());
			else
				cellEmpresa.setCellValue("");

			XSSFCell cellSistema = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getSistema() != null && cobranca.getVenda().getSistema().getDescricao() != null)
				cellSistema.setCellValue(cobranca.getVenda().getSistema().getDescricao());
			else
				cellSistema.setCellValue("");

			XSSFCell cellProduto = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getProduto() != null && cobranca.getVenda().getProduto().getDescricao() != null)
				cellProduto.setCellValue(cobranca.getVenda().getProduto().getDescricao());
			else
				cellProduto.setCellValue("");

			XSSFCell cellDataVenda = XSSFRow.createCell(cellnum++);
			cellDataVenda.setCellStyle(cellStyleCenter);
			if (cobranca.getVenda().getDataVendaOrigem() != null)
				cellDataVenda.setCellValue(sdf.format(cobranca.getVenda().getDataVendaOrigem()));
			else
				cellDataVenda.setCellValue("");

			XSSFCell cellCodigoVenda = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getCodigoVendaOrigem() != null)
				cellCodigoVenda.setCellValue(cobranca.getVenda().getCodigoVendaOrigem());
			else
				cellCodigoVenda.setCellValue("");

			XSSFCell cellCPFCliente = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getCpf() != null)
				cellCPFCliente.setCellValue(cobranca.getVenda().getCpf());
			else
				cellCPFCliente.setCellValue("");

			XSSFCell cellNomeCliente = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getNome() != null)
				cellNomeCliente.setCellValue(cobranca.getVenda().getNome());
			else
				cellNomeCliente.setCellValue("");

			XSSFCell cellMeioPag = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getContratoCobranca() != null
					&& cobranca.getVenda().getContratoCobranca().getMeioPagamento() != null
					&& cobranca.getVenda().getContratoCobranca().getMeioPagamento().getDescricao() != null)
				cellMeioPag.setCellValue(cobranca.getVenda().getContratoCobranca().getMeioPagamento().getDescricao());
			else
				cellMeioPag.setCellValue("");

			bandeira = bandeiraDLO.obterPorCodigo(cobranca.getVenda().getCodigoBandeiraCartao());
			XSSFCell cellBandeira = XSSFRow.createCell(cellnum++);
			if (bandeira != null && bandeira.getNomeBandeira() != null)
				cellBandeira.setCellValue(bandeira.getNomeBandeira());// Entidade
																		// Bandeira
			else
				cellBandeira.setCellValue("");

			XSSFCell cellValor = XSSFRow.createCell(cellnum++);
			cellValor.setCellStyle(cellStyleRight);
			if (cobranca.getVenda().getValorCobranca() != null)
				cellValor.setCellValue(Texto.doubleToMoeda(cobranca.getVenda().getValorCobranca().doubleValue(),
						"###,###,###,##0.00"));// Entidade Venda
			else
				cellValor.setCellValue("");

			XSSFCell cellParcelaCobranca = XSSFRow.createCell(cellnum++);
			cellParcelaCobranca.setCellValue(cobranca.getNumeroParcela());// Entidade
																			// Cobranca

			XSSFCell cellDataCobranca = XSSFRow.createCell(cellnum++);

			if (cobranca.getDataCobranca() != null) {
				cellDataCobranca.setCellValue(sdf.format(cobranca.getDataCobranca()));// Entidade
																						// Cobranca
				cellDataCobranca.setCellStyle(cellStyleCenter);
			} else
				cellDataCobranca.setCellValue("");

			XSSFCell cellStatus = null;
			if (cobranca.getStatusCobranca() != null) {
				cellStatus = XSSFRow.createCell(cellnum++);
				ItemLista itemLista = itemListaDLO.obterPorCodigo(cobranca.getStatusCobranca().getValor(),
						TipoListaValor.Status.getValor());
				cellStatus.setCellValue(itemLista.getDescricao());// Entidade
																	// Cobranca
			} else {
				cellStatus = XSSFRow.createCell(cellnum++);
				cellStatus.setCellValue("");
			}
			
			//CALCULO DE DIAS DE ATRASO. 
			long diasDeAtraso = (new Date().getTime() - cobranca.getDataCobranca().getTime()) / (24*60*60*1000);
			XSSFCell cellDiasAtraso = XSSFRow.createCell(cellnum++);
			cellDiasAtraso.setCellStyle(cellStyleRight);
			cellDiasAtraso.setCellValue(diasDeAtraso);

		}

		String filePrefixName = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.arquivo.nome"));
		String fileExtension = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"cobranca.nao.conciliada.arquivo.extensao.xssf"));
		String fileDateCreationName = dataGeracao;
		fileDateCreationName = (fileDateCreationName.replace("/", "").replace(":", "").replace(" ", "")).trim();
		String subheader = (filePrefixName + fileDateCreationName + fileExtension);
		response.setHeader("Content-Disposition", "attachment; filename=" + (subheader));

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}

	private void geraPlanilhaVendasConcretizadasXSSF(List<Venda> vendasConcretizadas, HttpServletResponse response,
			GerarRelatorioVendasActionForm form) throws IOException, DLOException {

		nomeRelatorio = PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.capturada.nao.autorizada");

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheetVendasConcretizadas = workbook.createSheet(PropertyUtil
				.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.planilha.aba.nome"));

		int columnIndex = -1;

		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 42));// EMPRESA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// SISTEMA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PRODUTO
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																						// DA
																						// VENDA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// DATA
																						// DE
																						// FIM
																						// DE
																						// VIGÊNCIA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// CERTIFICADO
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// CPF
																						// DO
																						// CLIENTE
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// NOME
																						// DO
																						// CLIENTE
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// MEIO
																						// DE
																						// PAGAMENTO
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 10));// BANDEIRA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// VALOR
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// TIPO
																						// DE
																						// COBRANÇA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// QUANTIDADE
																						// DE
																						// PARCELAS
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																						// DA
																						// COBRANÇA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 22));// STATUS
																						// DA
																						// COBRANÇA
		sheetVendasConcretizadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// MENSAGEM

		CellStyle cellStyleCenter = workbook.createCellStyle();
		cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle cellStyleLeft = workbook.createCellStyle();
		cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);

		CellStyle cellStyleRight = workbook.createCellStyle();
		cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);

		String dataGeracao = Data.formatar(new Date(), Data.MASCARA_COM_HORA);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		int rownum = 0;

		XSSFRow headerrow1 = sheetVendasConcretizadas.createRow(rownum++);
		XSSFCell headerrow1cell0 = headerrow1.createCell(0);
		headerrow1cell0.setCellValue(nomeRelatorio);

		XSSFRow headerrow2 = sheetVendasConcretizadas.createRow(rownum++);
		XSSFCell headerrow2cell0 = headerrow2.createCell(0);
		headerrow2cell0.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.data.geracao") + dataGeracao);

		XSSFRow headerrow3 = sheetVendasConcretizadas.createRow(rownum++);

		XSSFCell headerrow3cell0 = headerrow3.createCell(0);
		headerrow3cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.empresa"));

		Empresa empresa = getEmpresaFiltro(form.getEmpresa());

		XSSFCell headerrow3cell1 = headerrow3.createCell(1);
		headerrow3cell1.setCellValue((empresa != null ? empresa.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.todas"))));

		XSSFRow headerrow4 = sheetVendasConcretizadas.createRow(rownum++);

		XSSFCell headerrow4cell0 = headerrow4.createCell(0);
		headerrow4cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.sistema")));

		Sistema sistema = getSistemaFiltro(form.getSistema());

		XSSFCell headerrow4cell1 = headerrow4.createCell(1);
		headerrow4cell1.setCellValue((sistema != null ? sistema.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.todos"))));

		XSSFRow headerrow5 = sheetVendasConcretizadas.createRow(rownum++);

		XSSFCell headerrow5cell0 = headerrow5.createCell(0);
		headerrow5cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.produto")));

		Produto produto = getProdutoFiltro(form.getProduto());

		XSSFCell headerrow5cell1 = headerrow5.createCell(1);
		headerrow5cell1.setCellValue((produto != null ? produto.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.todos"))));

		XSSFRow headerrow6 = sheetVendasConcretizadas.createRow(rownum++);
		XSSFCell headerrow6cell0 = headerrow6.createCell(0);
		headerrow6cell0.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.Periodo")));

		XSSFCell headerrow6cell1 = headerrow6.createCell(1);
		headerrow6cell1.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.Periodo.inicio")) + " " + form.getPeriodoDe() + " "
				+ (PropertyUtil.getPropertyValue(propertyFileClassPathName,
						"relatorio.primeira.cobranca.planilha.Periodo.fim"))
				+ " " + form.getPeriodoAte());

		XSSFRow headerrowSpace = sheetVendasConcretizadas.createRow(rownum++);

		XSSFRow headerrow7 = sheetVendasConcretizadas.createRow(rownum++);

		int headerColumnIndex = 0;

		// Empresa
		XSSFCell headerrow7cell0 = headerrow7.createCell(headerColumnIndex);
		headerrow7cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.empresa"));
		headerrow7cell0.setCellStyle(cellStyleCenter);

		// Sistema
		XSSFCell headerrow7cell1 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell1.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.sistema"));
		headerrow7cell1.setCellStyle(cellStyleCenter);

		// Produto
		XSSFCell headerrow7cell2 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell2.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.produto"));
		headerrow7cell2.setCellStyle(cellStyleCenter);

		// Data da Venda
		XSSFCell headerrow7cell3 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell3.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.data.venda"));
		headerrow7cell3.setCellStyle(cellStyleCenter);

		// Data de Fim de Vigência
		XSSFCell headerrow7cell5 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell5.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.data.fim.vigencia"));
		headerrow7cell5.setCellStyle(cellStyleCenter);

		// Código de Venda
		XSSFCell headerrow7cell6 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell6.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.codigo.venda"));
		headerrow7cell6.setCellStyle(cellStyleCenter);

		// CPF do Cliente
		XSSFCell headerrow7cell7 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell7.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.cpf.cliente"));
		headerrow7cell7.setCellStyle(cellStyleCenter);

		// Nome do Cliente
		XSSFCell headerrow7cell8 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell8.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.nome.cliente"));
		headerrow7cell8.setCellStyle(cellStyleCenter);

		// Meio de Pagamento
		XSSFCell headerrow7cell9 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell9.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.meio.pagamento"));
		headerrow7cell9.setCellStyle(cellStyleCenter);

		// Bandeira
		XSSFCell headerrow7cell10 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell10.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.bandeira"));
		headerrow7cell10.setCellStyle(cellStyleCenter);

		// Valor
		XSSFCell headerrow7cell11 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell11.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.primeira.cobranca.planilha.valor"));
		headerrow7cell11.setCellStyle(cellStyleCenter);

		// Tipo de Cobrança
		XSSFCell headerrow7cell12 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell12.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.tipo.cobranca"));
		headerrow7cell12.setCellStyle(cellStyleCenter);

		// Quantidade de Parcelas
		XSSFCell headerrow7cell13 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell13.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.quantidade.parcelas"));
		headerrow7cell13.setCellStyle(cellStyleCenter);

		// Data da Cobrança
		XSSFCell headerrow7cell4 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell4.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.data.cobranca"));
		headerrow7cell4.setCellStyle(cellStyleCenter);

		// Status
		XSSFCell headerrow7cell14 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell14.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.status.cobranca"));
		headerrow7cell14.setCellStyle(cellStyleCenter);

		// Mensagem
		XSSFCell headerrow7cell15 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell15.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.planilha.mensagem"));
		headerrow7cell15.setCellStyle(cellStyleCenter);

		Bandeira bandeira = null;

		for (Venda venda : vendasConcretizadas) {

			XSSFRow XSSFRow = sheetVendasConcretizadas.createRow(rownum++);

			int cellnum = 0;

			XSSFCell cellEmpresa = XSSFRow.createCell(cellnum++);
			if (venda.getEmpresa() != null && venda.getEmpresa().getDescricao() != null)
				cellEmpresa.setCellValue(venda.getEmpresa().getDescricao());
			else
				cellEmpresa.setCellValue("");

			XSSFCell cellSistema = XSSFRow.createCell(cellnum++);
			if (venda.getSistema() != null && venda.getSistema().getDescricao() != null)
				cellSistema.setCellValue(venda.getSistema().getDescricao());
			else
				cellSistema.setCellValue("");

			XSSFCell cellProduto = XSSFRow.createCell(cellnum++);
			if (venda.getProduto() != null && venda.getProduto().getDescricao() != null)
				cellProduto.setCellValue(venda.getProduto().getDescricao());
			else
				cellProduto.setCellValue("");

			XSSFCell cellDataVenda = XSSFRow.createCell(cellnum++);
			cellDataVenda.setCellStyle(cellStyleCenter);
			if (venda.getDataVendaOrigem() != null)
				cellDataVenda.setCellValue(sdf.format(venda.getDataVendaOrigem()));
			else
				cellDataVenda.setCellValue("");

			XSSFCell cellDataFimVig = XSSFRow.createCell(cellnum++);
			cellDataFimVig.setCellStyle(cellStyleCenter);
			if (venda.getDataFimVigencia() != null)
				cellDataFimVig.setCellValue(sdf.format(venda.getDataFimVigencia()));
			else
				cellDataFimVig.setCellValue("");

			XSSFCell cellCodigoVenda = XSSFRow.createCell(cellnum++);
			if (venda.getCodigoVendaOrigem() != null)
				cellCodigoVenda.setCellValue(venda.getCodigoVendaOrigem());
			else
				cellCodigoVenda.setCellValue("");

			XSSFCell cellCPFCliente = XSSFRow.createCell(cellnum++);
			if (venda.getCpf() != null)
				cellCPFCliente.setCellValue(venda.getCpf());
			else
				cellCPFCliente.setCellValue("");

			XSSFCell cellNomeCliente = XSSFRow.createCell(cellnum++);
			if (venda.getNome() != null)
				cellNomeCliente.setCellValue(venda.getNome());
			else
				cellNomeCliente.setCellValue("");

			XSSFCell cellMeioPag = XSSFRow.createCell(cellnum++);
			if (venda.getContratoCobranca() != null && venda.getContratoCobranca().getMeioPagamento() != null
					&& venda.getContratoCobranca().getMeioPagamento().getDescricao() != null)
				cellMeioPag.setCellValue(venda.getContratoCobranca().getMeioPagamento().getDescricao());
			else
				cellMeioPag.setCellValue("");

			bandeira = bandeiraDLO.obterPorCodigo(venda.getCodigoBandeiraCartao());
			XSSFCell cellBandeira = XSSFRow.createCell(cellnum++);
			if (bandeira != null && bandeira.getNomeBandeira() != null)
				cellBandeira.setCellValue(bandeira.getNomeBandeira());// Entidade
																		// Bandeira
			else
				cellBandeira.setCellValue("");

			XSSFCell cellValor = XSSFRow.createCell(cellnum++);
			cellValor.setCellStyle(cellStyleRight);
			if (venda.getValorCobranca() != null)
				cellValor.setCellValue(
						Texto.doubleToMoeda(venda.getValorCobranca().doubleValue(), "###,###,###,##0.00"));// Entidade
																											// Venda
			else
				cellValor.setCellValue("");

			XSSFCell cellTipoCobranca = XSSFRow.createCell(cellnum++);

			ItemLista itemListaTipoCobanca = null;

			itemListaTipoCobanca = itemListaDLO.obterPorCodigo(venda.getTipoCobranca().getValor(),
					TipoListaValor.Cobranca.getValor());

			if (itemListaTipoCobanca != null && itemListaTipoCobanca.getDescricao() != null)
				cellTipoCobranca.setCellValue(itemListaTipoCobanca.getDescricao());// Entidade
																					// Cobrança
			else
				cellTipoCobranca.setCellValue("");

			XSSFCell cellQuantParcelas = XSSFRow.createCell(cellnum++);
			cellQuantParcelas.setCellValue(venda.getQuantidadeParcelas());// Entidade
																			// Venda

			XSSFCell cellDataCobranca = XSSFRow.createCell(cellnum++);

			for (Cobranca cob : venda.getCobrancas()) {
				if (cob.getNumeroParcela() == 1) {
					if (cob.getDataCobranca() != null) {
						cellDataCobranca.setCellValue(sdf.format(cob.getDataCobranca()));// Entidade
																							// Cobranca
						cellDataCobranca.setCellStyle(cellStyleCenter);
					} else
						cellDataCobranca.setCellValue("");
					break;
				}
			}

			XSSFCell cellStatus = XSSFRow.createCell(cellnum++);

			Cobranca lcob = null;

			ItemLista itemLista = null;

			for (Cobranca cob : venda.getCobrancas()) {
				if (cob.getNumeroParcela() == 1) {
					lcob = cob;
					if (cob.getStatusCobranca() != null) {
						itemLista = itemListaDLO.obterPorCodigo(cob.getStatusCobranca().getValor(),
								TipoListaValor.Status.getValor());
						cellStatus.setCellValue(itemLista.getDescricao());// Entidade
																			// Cobranca
					} else
						cellStatus.setCellValue("");
					break;
				}
			}

			if (lcob.getStatusCobranca().getValor() == StatusCobranca.NaoAutorizada.getValor()
					|| lcob.getStatusCobranca().getValor() == StatusCobranca.Capturada.getValor()// caso
																									// de
																									// sucesso.
			) {
				XSSFCell cellMensagem = XSSFRow.createCell(cellnum++);

				lcob = cobrancaDLO.completar(lcob, Cobranca_.tentativas);
				List<Tentativa> tentativas = lcob.getTentativas();

				Collections.sort(tentativas, MAIOR_NUM_TENTATIVA);

				Tentativa t = tentativas.get(0);

				// Caso ambas mensagens( do adquirente e do provedor) estiverem
				// populadas a preferência é do "Adquirente".
				if ((t.getMensagemRetornoAdquirente() != null && !t.getMensagemRetornoAdquirente().isEmpty())
						&& (t.getMensagemRetornoProvedor() != null && !t.getMensagemRetornoProvedor().isEmpty()))
					cellMensagem.setCellValue(t.getMensagemRetornoAdquirente());
				else// somente uma das duas mensagens populada
					if (t.getMensagemRetornoAdquirente() != null && !t.getMensagemRetornoAdquirente().isEmpty()) // somente
																													// a
																													// mensagem
																													// do
																													// adquirente
																													// populada
					cellMensagem.setCellValue(t.getMensagemRetornoAdquirente());
				else
					cellMensagem.setCellValue(t.getMensagemRetornoProvedor());// somente
																				// a
																				// mensagem
																				// do
																				// provedor
																				// populada
			}
		}

		String filePrefixName = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.capturada.nao.autorizada.arquivo.nome"));
		String fileExtension = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.primeira.cobranca.capturada.nao.autorizada.arquivo.extensao.xssf"));
		String fileDateCreationName = dataGeracao;
		fileDateCreationName = (fileDateCreationName.replace("/", "").replace(":", "").replace(" ", "")).trim();
		String subheader = (filePrefixName + fileDateCreationName + fileExtension);
		response.setHeader("Content-Disposition", "attachment; filename=" + (subheader));

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}

	private void geraPlanilhaVendasEstornadasXSSF(List<Venda> VendasEstornadas, HttpServletResponse response,
			GerarRelatorioVendasActionForm form) throws IOException, DLOException {

		nomeRelatorio = PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.cancelada");

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheetVendasEstornadas = workbook.createSheet(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.aba.nome"));

		int columnIndex = -1;

		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 31));// EMPRESA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// SISTEMA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PRODUTO
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// DATA
																					// DA
																					// VENDA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 24));// DATA
																					// DE
																					// FIM
																					// DE
																					// VIGÊNCIA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// CÓDIGO
																					// DA
																					// VENDA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 18));// CPF
																					// DO
																					// CLIENTE
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// NOME
																					// DO
																					// CLIENTE
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 24));// MEIO
																					// DE
																					// PAGAMENTO
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 18));// BANDEIRA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// VALOR
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 40));// TIPO
																					// DE
																					// COBRANÇA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 26));// QUANTIDADE
																					// DE
																					// PARCELAS
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																					// COBRANCA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 22));// STATUS
																					// DA
																					// COBRANÇA
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// DATA
																					// ESTORNO
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// VALOR
																					// DO
																					// ESTORNO
		sheetVendasEstornadas.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 32));// CÓDIGO
																					// MOTIVO
																					// CANCELAMENTO

		CellStyle cellStyleCenter = workbook.createCellStyle();
		cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle cellStyleLeft = workbook.createCellStyle();
		cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);

		CellStyle cellStyleRight = workbook.createCellStyle();
		cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);

		String dataGeracao = Data.formatar(new Date(), Data.MASCARA_COM_HORA);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		int rownum = 0;

		XSSFRow headerrow1 = sheetVendasEstornadas.createRow(rownum++);
		XSSFCell headerrow1cell0 = headerrow1.createCell(0);
		headerrow1cell0.setCellValue(nomeRelatorio);

		XSSFRow headerrow2 = sheetVendasEstornadas.createRow(rownum++);
		XSSFCell headerrow2cell0 = headerrow2.createCell(0);
		headerrow2cell0.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.data.geracao") + dataGeracao);

		XSSFRow headerrow3 = sheetVendasEstornadas.createRow(rownum++);

		XSSFCell headerrow3cell0 = headerrow3.createCell(0);
		headerrow3cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.empresa"));

		Empresa empresa = getEmpresaFiltro(form.getEmpresa());

		XSSFCell headerrow3cell1 = headerrow3.createCell(1);
		headerrow3cell1.setCellValue((empresa != null ? empresa.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.todas"))));

		XSSFRow headerrow4 = sheetVendasEstornadas.createRow(rownum++);

		XSSFCell headerrow4cell0 = headerrow4.createCell(0);
		headerrow4cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.sistema")));

		Sistema sistema = getSistemaFiltro(form.getSistema());

		XSSFCell headerrow4cell1 = headerrow4.createCell(1);
		headerrow4cell1.setCellValue((sistema != null ? sistema.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.todos"))));

		XSSFRow headerrow5 = sheetVendasEstornadas.createRow(rownum++);

		XSSFCell headerrow5cell0 = headerrow5.createCell(0);
		headerrow5cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.produto")));

		Produto produto = getProdutoFiltro(form.getProduto());

		XSSFCell headerrow5cell1 = headerrow5.createCell(1);
		headerrow5cell1.setCellValue((produto != null ? produto.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.todos"))));

		XSSFRow headerrow6 = sheetVendasEstornadas.createRow(rownum++);
		XSSFCell headerrow6cell0 = headerrow6.createCell(0);
		headerrow6cell0.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.Periodo")));

		XSSFCell headerrow6cell1 = headerrow6.createCell(1);
		headerrow6cell1.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.Periodo.inicio")) + " " + form.getPeriodoDe() + " "
				+ (PropertyUtil.getPropertyValue(propertyFileClassPathName,
						"relatorio.cobranca.estornada.planilha.Periodo.fim"))
				+ " " + form.getPeriodoAte());

		XSSFRow headerrowSpace = sheetVendasEstornadas.createRow(rownum++);

		XSSFRow headerrow7 = sheetVendasEstornadas.createRow(rownum++);

		int headerColumnIndex = 0;

		// Empresa
		XSSFCell headerrow7cell0 = headerrow7.createCell(headerColumnIndex);
		headerrow7cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.empresa"));
		headerrow7cell0.setCellStyle(cellStyleCenter);

		// Sistema
		XSSFCell headerrow7cell1 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell1.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.sistema"));
		headerrow7cell1.setCellStyle(cellStyleCenter);

		// Produto
		XSSFCell headerrow7cell2 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell2.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.cobranca.estornada.produto"));
		headerrow7cell2.setCellStyle(cellStyleCenter);

		// Data da Venda
		XSSFCell headerrow7cell3 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell3.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.data.venda"));
		headerrow7cell3.setCellStyle(cellStyleCenter);

		// Data de Fim de Vigência
		XSSFCell headerrow7cell4 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell4.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.data.fim.vigencia"));
		headerrow7cell4.setCellStyle(cellStyleCenter);

		// Código de Venda
		XSSFCell headerrow7cell5 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell5.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.codigo.venda"));
		headerrow7cell5.setCellStyle(cellStyleCenter);

		// CPF do Cliente
		XSSFCell headerrow7cell6 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell6.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.cpf.cliente"));
		headerrow7cell6.setCellStyle(cellStyleCenter);

		// Nome do Cliente
		XSSFCell headerrow7cell7 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell7.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.nome.cliente"));
		headerrow7cell7.setCellStyle(cellStyleCenter);

		// Meio de Pagamento
		XSSFCell headerrow7cell8 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell8.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.meio.pagamento"));
		headerrow7cell8.setCellStyle(cellStyleCenter);

		// Bandeira
		XSSFCell headerrow7cell9 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell9.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.bandeira"));
		headerrow7cell9.setCellStyle(cellStyleCenter);

		// Valor
		XSSFCell headerrow7cell10 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell10.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.valor"));
		headerrow7cell10.setCellStyle(cellStyleCenter);

		// Tipo de Cobrança
		XSSFCell headerrow7cell11 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell11.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.tipo.cobranca"));
		headerrow7cell11.setCellStyle(cellStyleCenter);

		// Quantidade de Parcelas
		XSSFCell headerrow7cell12 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell12.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.quantidade.parcelas"));
		headerrow7cell12.setCellStyle(cellStyleCenter);

		// DATA COBRANCA
		XSSFCell headerrow7cell14 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell14.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.data.cobranca"));
		headerrow7cell14.setCellStyle(cellStyleCenter);

		// Status
		XSSFCell headerrow7cell13 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell13.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.status.cobranca"));
		headerrow7cell13.setCellStyle(cellStyleCenter);

		// DATA ESTORNO
		XSSFCell headerrow7cell15 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell15.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.data.estorno"));
		headerrow7cell15.setCellStyle(cellStyleCenter);

		// VALOR DO ESTORNO
		XSSFCell headerrow7cell16 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell16.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.valor.estorno"));
		headerrow7cell16.setCellStyle(cellStyleCenter);

		XSSFCell headerrow7cell17 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell17.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.planilha.codigo.motivo.cancelamento"));
		headerrow7cell17.setCellStyle(cellStyleCenter);

		Bandeira bandeira = null;

		for (Venda venda : VendasEstornadas) {

			for (Cobranca cob : venda.getCobrancas()) {

				XSSFRow XSSFRow = sheetVendasEstornadas.createRow(rownum++);

				int cellnum = 0;

				XSSFCell cellEmpresa = XSSFRow.createCell(cellnum++);
				if (venda.getEmpresa() != null && venda.getEmpresa().getDescricao() != null)
					cellEmpresa.setCellValue(venda.getEmpresa().getDescricao());
				else
					cellEmpresa.setCellValue("");

				XSSFCell cellSistema = XSSFRow.createCell(cellnum++);
				if (venda.getSistema() != null && venda.getSistema().getDescricao() != null)
					cellSistema.setCellValue(venda.getSistema().getDescricao());
				else
					cellSistema.setCellValue("");

				XSSFCell cellProduto = XSSFRow.createCell(cellnum++);
				if (venda.getProduto() != null && venda.getProduto().getDescricao() != null)
					cellProduto.setCellValue(venda.getProduto().getDescricao());
				else
					cellProduto.setCellValue("");

				XSSFCell cellDataVenda = XSSFRow.createCell(cellnum++);
				cellDataVenda.setCellStyle(cellStyleCenter);
				if (venda.getDataVendaOrigem() != null)
					cellDataVenda.setCellValue(sdf.format(venda.getDataVendaOrigem()));
				else
					cellDataVenda.setCellValue("");

				XSSFCell cellDataFimVig = XSSFRow.createCell(cellnum++);
				cellDataFimVig.setCellStyle(cellStyleCenter);
				if (venda.getDataFimVigencia() != null)
					cellDataFimVig.setCellValue(sdf.format(venda.getDataFimVigencia()));
				else
					cellDataFimVig.setCellValue("");

				XSSFCell cellCodigoVenda = XSSFRow.createCell(cellnum++);
				if (venda.getCodigoVendaOrigem() != null)
					cellCodigoVenda.setCellValue(venda.getCodigoVendaOrigem());
				else
					cellCodigoVenda.setCellValue("");

				XSSFCell cellCPFCliente = XSSFRow.createCell(cellnum++);
				if (venda.getCpf() != null)
					cellCPFCliente.setCellValue(venda.getCpf());
				else
					cellCPFCliente.setCellValue("");

				XSSFCell cellNomeCliente = XSSFRow.createCell(cellnum++);
				if (venda.getNome() != null)
					cellNomeCliente.setCellValue(venda.getNome());
				else
					cellNomeCliente.setCellValue("");

				XSSFCell cellMeioPag = XSSFRow.createCell(cellnum++);
				if (venda.getContratoCobranca() != null && venda.getContratoCobranca().getMeioPagamento() != null
						&& venda.getContratoCobranca().getMeioPagamento().getDescricao() != null)
					cellMeioPag.setCellValue(venda.getContratoCobranca().getMeioPagamento().getDescricao());
				else
					cellMeioPag.setCellValue("");

				bandeira = bandeiraDLO.obterPorCodigo(venda.getCodigoBandeiraCartao());
				XSSFCell cellBandeira = XSSFRow.createCell(cellnum++);
				if (bandeira != null && bandeira.getNomeBandeira() != null)
					cellBandeira.setCellValue(bandeira.getNomeBandeira());// Entidade
																			// Bandeira
				else
					cellBandeira.setCellValue("");

				XSSFCell cellValor = XSSFRow.createCell(cellnum++);
				cellValor.setCellStyle(cellStyleRight);
				if (venda.getValorCobranca() != null)
					cellValor.setCellValue(
							Texto.doubleToMoeda(venda.getValorCobranca().doubleValue(), "###,###,###,##0.00"));// Entidade
																												// Venda
				else
					cellValor.setCellValue("");

				XSSFCell cellTipoCobranca = XSSFRow.createCell(cellnum++);

				ItemLista itemListaTipoCobanca = null;

				itemListaTipoCobanca = itemListaDLO.obterPorCodigo(venda.getTipoCobranca().getValor(),
						TipoListaValor.Cobranca.getValor());
				if (itemListaTipoCobanca != null && itemListaTipoCobanca.getDescricao() != null)
					cellTipoCobranca.setCellValue(itemListaTipoCobanca.getDescricao());// Entidade
																						// Cobrança
				else
					cellTipoCobranca.setCellValue("");

				XSSFCell cellQuantParcelas = XSSFRow.createCell(cellnum++);
				cellQuantParcelas.setCellValue(venda.getQuantidadeParcelas());// Entidade
																				// Venda

				XSSFCell cellDataCobranca = XSSFRow.createCell(cellnum++);
				cellDataCobranca.setCellStyle(cellStyleCenter);
				if (cob.getDataCobranca() != null)
					cellDataCobranca.setCellValue(sdf.format(cob.getDataCobranca()));// Entidade
																						// Cobranca
				else
					cellDataCobranca.setCellValue("");

				ItemLista itemLista = null;
				XSSFCell cellStatus = XSSFRow.createCell(cellnum++);
				if (cob.getStatusCobranca() != null) {
					itemLista = itemListaDLO.obterPorCodigo(cob.getStatusCobranca().getValor(),
							TipoListaValor.Status.getValor());
					cellStatus.setCellValue(itemLista.getDescricao());// Entidade
																		// Cobranca
				} else
					cellStatus.setCellValue("");

				XSSFCell cellDataEstorno = XSSFRow.createCell(cellnum++);
				cellDataEstorno.setCellStyle(cellStyleCenter);
				if (cob.getDataEstorno() != null)
					cellDataEstorno.setCellValue(sdf.format(cob.getDataEstorno()));// Entidade
																					// Cobranca
				else
					cellDataEstorno.setCellValue("");

				XSSFCell cellValorEstorno = XSSFRow.createCell(cellnum++);
				cellValorEstorno.setCellStyle(cellStyleRight);
				if (cob.getValorEstorno() != null)
					cellValorEstorno.setCellValue(
							Texto.doubleToMoeda(cob.getValorEstorno().doubleValue(), "###,###,###,##0.00"));// Entidade
																											// Venda
				else
					cellValorEstorno.setCellValue("");

				XSSFCell cellcodigoMotivoCancelamento = XSSFRow.createCell(cellnum++);
				cellcodigoMotivoCancelamento.setCellStyle(cellStyleLeft);
				ItemLista itemListaMotivoCancelamento = null;
				if (venda.getCodigoMotivoCancelamento() != null) {
					itemListaMotivoCancelamento = itemListaDLO.obterPorCodigo(venda.getCodigoMotivoCancelamento(),
							TipoListaValor.MotivoCancelamento.getValor());
					cellcodigoMotivoCancelamento.setCellValue(itemListaMotivoCancelamento.getDescricao());
				} else
					cellcodigoMotivoCancelamento.setCellValue("");
			}
		}

		String filePrefixName = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.arquivo.nome"));
		String fileExtension = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.cobranca.estornada.arquivo.extensao.xssf"));
		String fileDateCreationName = dataGeracao;
		fileDateCreationName = (fileDateCreationName.replace("/", "").replace(":", "").replace(" ", "")).trim();
		String subheader = (filePrefixName + fileDateCreationName + fileExtension);
		response.setHeader("Content-Disposition", "attachment; filename=" + (subheader));

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}

	private void geraPlanilhaCobrancasConciliacaoXSSF(List<Cobranca> cobrancasConciliacao, HttpServletResponse response,
			GerarRelatorioVendasActionForm form) throws DLOException, IOException {

		nomeRelatorio = PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.nome");

		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheetCobrancasConciliacao = workbook.createSheet(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.aba.nome"));

		int columnIndex = -1;

		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// EMPRESA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// SISTEMA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PRODUTO
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																						// DA
																						// VENDA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// CODIGO
																						// DA
																						// VENDA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// CPF
																						// DO
																						// CLIENTE
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// NOME
																						// DO
																						// CLIENTE
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// PARCELA
																						// DE
																						// COBRANCA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																						// DA
																						// COBRANÇA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// MEIO
																						// DE
																						// PAGAMENTO
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 10));// BANDEIRA
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 15));// VALOR
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// SITUAÇÃO
																						// CONCILIACAO
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 25));// EVENTO
																						// DE
																						// CONCILIACAO
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 20));// DATA
																						// DE
																						// REPASSE
		sheetCobrancasConciliacao.setColumnWidth(++columnIndex, (WIDTH_CHARACTER * 30));// MENSAGEM
																						// DE
																						// PROCESSAMEMENTO

		CellStyle cellStyleCenter = workbook.createCellStyle();
		cellStyleCenter.setAlignment(CellStyle.ALIGN_CENTER);

		CellStyle cellStyleLeft = workbook.createCellStyle();
		cellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT);

		CellStyle cellStyleRight = workbook.createCellStyle();
		cellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT);

		String dataGeracao = Data.formatar(new Date(), Data.MASCARA_COM_HORA);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		int rownum = 0;

		XSSFRow headerrow1 = sheetCobrancasConciliacao.createRow(rownum++);
		XSSFCell headerrow1cell0 = headerrow1.createCell(0);
		headerrow1cell0.setCellValue(nomeRelatorio);

		XSSFRow headerrow2 = sheetCobrancasConciliacao.createRow(rownum++);
		XSSFCell headerrow2cell0 = headerrow2.createCell(0);
		headerrow2cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.data.geracao")
						+ dataGeracao);

		XSSFRow headerrow3 = sheetCobrancasConciliacao.createRow(rownum++);

		XSSFCell headerrow3cell0 = headerrow3.createCell(0);
		headerrow3cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.empresa"));

		Empresa empresa = getEmpresaFiltro(form.getEmpresa());

		XSSFCell headerrow3cell1 = headerrow3.createCell(1);
		headerrow3cell1.setCellValue((empresa != null ? empresa.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.todas"))));

		XSSFRow headerrow4 = sheetCobrancasConciliacao.createRow(rownum++);

		XSSFCell headerrow4cell0 = headerrow4.createCell(0);
		headerrow4cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.sistema")));

		Sistema sistema = getSistemaFiltro(form.getSistema());

		XSSFCell headerrow4cell1 = headerrow4.createCell(1);
		headerrow4cell1.setCellValue((sistema != null ? sistema.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.todos"))));

		XSSFRow headerrow5 = sheetCobrancasConciliacao.createRow(rownum++);

		XSSFCell headerrow5cell0 = headerrow5.createCell(0);
		headerrow5cell0.setCellValue(
				(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.produto")));

		Produto produto = getProdutoFiltro(form.getProduto());

		XSSFCell headerrow5cell1 = headerrow5.createCell(1);
		headerrow5cell1.setCellValue((produto != null ? produto.getDescricao()
				: (PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.todos"))));

		XSSFRow headerrow6 = sheetCobrancasConciliacao.createRow(rownum++);
		XSSFCell headerrow6cell0 = headerrow6.createCell(0);
		headerrow6cell0.setCellValue((PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.situacao.conciliacao")));

		XSSFCell headerrow6cell1 = headerrow6.createCell(1);

		ItemLista itemListaHeader = null;

		if (!form.getStatus().equals("0")) {

			ItemLista itemListaFiltro = new ItemLista();

			itemListaFiltro.setId(Long.parseLong(form.getStatus()));

			itemListaHeader = itemListaDLO.obter(itemListaFiltro.getId());
			headerrow6cell1.setCellValue(itemListaHeader.getDescricao());
		} else
			headerrow6cell1.setCellValue(
					(PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.todas")));

		XSSFRow headerrowSpace = sheetCobrancasConciliacao.createRow(rownum++);

		XSSFRow headerrow7 = sheetCobrancasConciliacao.createRow(rownum++);

		int headerColumnIndex = 0;

		// EMPRESA
		XSSFCell headerrow7cell0 = headerrow7.createCell(headerColumnIndex);
		headerrow7cell0.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.empresa"));
		headerrow7cell0.setCellStyle(cellStyleCenter);

		// SISTEMA
		XSSFCell headerrow7cell1 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell1.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.sistema"));
		headerrow7cell1.setCellStyle(cellStyleCenter);

		// PRODUTO
		XSSFCell headerrow7cell2 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell2.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.produto"));
		headerrow7cell2.setCellStyle(cellStyleCenter);

		// DATA DA VENDA
		XSSFCell headerrow7cell3 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell3.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.data.venda"));
		headerrow7cell3.setCellStyle(cellStyleCenter);

		// CODIGO DA VENDA
		XSSFCell headerrow7cell4 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell4.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.codigo.venda"));
		headerrow7cell4.setCellStyle(cellStyleCenter);

		// CPF DO CLIENTE
		XSSFCell headerrow7cell5 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell5.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.cpf.cliente"));
		headerrow7cell5.setCellStyle(cellStyleCenter);

		// NOME DO CLIENTE
		XSSFCell headerrow7cell6 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell6.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.nome.cliente"));
		headerrow7cell6.setCellStyle(cellStyleCenter);

		// PARCELA DE COBRANCA
		XSSFCell headerrow7cell10 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell10.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.parcela"));
		headerrow7cell10.setCellStyle(cellStyleCenter);

		// DATA DA COBRANÇA
		XSSFCell headerrow7cell11 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell11.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.data.cobranca"));
		headerrow7cell11.setCellStyle(cellStyleCenter);

		// MEIO DE PAGAMENTO
		XSSFCell headerrow7cell7 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell7.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.meio.pagamento"));
		headerrow7cell7.setCellStyle(cellStyleCenter);

		// BANDEIRA
		XSSFCell headerrow7cell8 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell8.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.bandeira"));
		headerrow7cell8.setCellStyle(cellStyleCenter);

		// VALOR
		XSSFCell headerrow7cell9 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell9.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.planilha.valor"));
		headerrow7cell9.setCellStyle(cellStyleCenter);

		// SITUAÇÃO DA CONCILIACAO
		XSSFCell headerrow7cell14 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell14.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.situacao.conciliacao"));
		headerrow7cell14.setCellStyle(cellStyleCenter);

		//// EVENTO DE CONCILIACAO
		XSSFCell headerrow7cell16 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell16.setCellValue(
				PropertyUtil.getPropertyValue(propertyFileClassPathName, "relatorio.conciliacao.evento.conciliacao"));
		headerrow7cell16.setCellStyle(cellStyleCenter);

		// DATA DE REPASSE
		XSSFCell headerrow7cell13 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell13.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.planilha.data.repasse"));
		headerrow7cell13.setCellStyle(cellStyleCenter);

		// MESAGEM DE PROCESSAMEMENTO
		XSSFCell headerrow7cell15 = headerrow7.createCell(++headerColumnIndex);
		headerrow7cell15.setCellValue(PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.mensagem.processamento"));
		headerrow7cell15.setCellStyle(cellStyleCenter);

		Bandeira bandeira = null;

		for (Cobranca cobranca : cobrancasConciliacao) {

			XSSFRow XSSFRow = sheetCobrancasConciliacao.createRow(rownum++);

			int cellnum = 0;

			XSSFCell cellEmpresa = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getEmpresa() != null && cobranca.getVenda().getEmpresa().getDescricao() != null)
				cellEmpresa.setCellValue(cobranca.getVenda().getEmpresa().getDescricao());
			else
				cellEmpresa.setCellValue("");

			XSSFCell cellSistema = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getSistema() != null && cobranca.getVenda().getSistema().getDescricao() != null)
				cellSistema.setCellValue(cobranca.getVenda().getSistema().getDescricao());
			else
				cellSistema.setCellValue("");

			XSSFCell cellProduto = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getProduto() != null && cobranca.getVenda().getProduto().getDescricao() != null)
				cellProduto.setCellValue(cobranca.getVenda().getProduto().getDescricao());
			else
				cellProduto.setCellValue("");

			XSSFCell cellDataVenda = XSSFRow.createCell(cellnum++);
			cellDataVenda.setCellStyle(cellStyleCenter);
			if (cobranca.getVenda().getDataVendaOrigem() != null)
				cellDataVenda.setCellValue(sdf.format(cobranca.getVenda().getDataVendaOrigem()));
			else
				cellDataVenda.setCellValue("");

			XSSFCell cellCodigoVenda = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getCodigoVendaOrigem() != null)
				cellCodigoVenda.setCellValue(cobranca.getVenda().getCodigoVendaOrigem());
			else
				cellCodigoVenda.setCellValue("");

			XSSFCell cellCPFCliente = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getCpf() != null)
				cellCPFCliente.setCellValue(cobranca.getVenda().getCpf());
			else
				cellCPFCliente.setCellValue("");

			XSSFCell cellNomeCliente = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getNome() != null)
				cellNomeCliente.setCellValue(cobranca.getVenda().getNome());
			else
				cellNomeCliente.setCellValue("");

			XSSFCell cellParcelaCobranca = XSSFRow.createCell(cellnum++);
			cellParcelaCobranca.setCellValue(cobranca.getNumeroParcela());// Entidade
																			// Cobranca

			XSSFCell cellDataCobranca = XSSFRow.createCell(cellnum++);

			if (cobranca.getDataCobranca() != null) {
				cellDataCobranca.setCellValue(sdf.format(cobranca.getDataCobranca()));// Entidade
																						// Cobranca
				cellDataCobranca.setCellStyle(cellStyleCenter);
			} else
				cellDataCobranca.setCellValue("");

			XSSFCell cellMeioPag = XSSFRow.createCell(cellnum++);
			if (cobranca.getVenda().getContratoCobranca() != null
					&& cobranca.getVenda().getContratoCobranca().getMeioPagamento() != null
					&& cobranca.getVenda().getContratoCobranca().getMeioPagamento().getDescricao() != null)
				cellMeioPag.setCellValue(cobranca.getVenda().getContratoCobranca().getMeioPagamento().getDescricao());
			else
				cellMeioPag.setCellValue("");

			bandeira = bandeiraDLO.obterPorCodigo(cobranca.getVenda().getCodigoBandeiraCartao());
			XSSFCell cellBandeira = XSSFRow.createCell(cellnum++);
			if (bandeira != null && bandeira.getNomeBandeira() != null)
				cellBandeira.setCellValue(bandeira.getNomeBandeira());// Entidade
																		// Bandeira
			else
				cellBandeira.setCellValue("");

			XSSFCell cellValor = XSSFRow.createCell(cellnum++);
			cellValor.setCellStyle(cellStyleRight);
			if (cobranca.getVenda().getValorCobranca() != null)
				cellValor.setCellValue(Texto.doubleToMoeda(cobranca.getVenda().getValorCobranca().doubleValue(),
						"###,###,###,##0.00"));// Entidade Venda
			else
				cellValor.setCellValue("");

			// SITUAÇÃO CONCILIACAO
			ProcessamentoConciliacao processamentoConciliacao = processamentoConciliacaoDLO.obterPorCobranca(cobranca);
			ProcConciliacaoEvento procConciliacaoEvento = procConciliacaoEventoDLO.obterPorProcessamentoConciliacao(processamentoConciliacao);
			ItemLista itemListaCellStatusConciliacao = null;

			XSSFCell cellStatusConciliacao = XSSFRow.createCell(cellnum++);

			if (procConciliacaoEvento != null && procConciliacaoEvento.getEventId() != null) {

				// TODO verificar com o jean se estes status são realmente os corretos para o relatório de proc. conciliação (os usados aqui são de conciliação interface, e possuem valores diferentes)
				if (procConciliacaoEvento.getStatus().getValor() != null && procConciliacaoEvento.getStatus().getValor().trim() != "") {
					
					if (procConciliacaoEvento.getStatus().getValor().equals(StatusProcConciliacaoEvento.ATIVO.getValor())){
						
						itemListaCellStatusConciliacao = itemListaDLO.obterPorCodigo(StatusProcConciliacaoEvento.ATIVO.getValor(), TipoListaValor.EventoConciliacao.getValor());
						
					}else if (procConciliacaoEvento.getStatus().getValor().equals(StatusProcConciliacaoEvento.ANULADO.getValor())){
						
						itemListaCellStatusConciliacao = itemListaDLO.obterPorCodigo(StatusProcConciliacaoEvento.ANULADO.getValor(),TipoListaValor.EventoConciliacao.getValor());
						
					}else if(procConciliacaoEvento.getStatus().getValor().equals(StatusProcConciliacaoEvento.REJEITADO.getValor())){
						
						itemListaCellStatusConciliacao = itemListaDLO.obterPorCodigo(StatusProcConciliacaoEvento.REJEITADO.getValor(),TipoListaValor.EventoConciliacao.getValor());
					}
				}	
				
				if (itemListaCellStatusConciliacao != null && itemListaCellStatusConciliacao.getDescricao() != null)
					cellStatusConciliacao.setCellValue(itemListaCellStatusConciliacao.getDescricao());
				else
					cellStatusConciliacao.setCellValue("");
			} else
				cellStatusConciliacao.setCellValue("");

			// Evento Conciliacao
			XSSFCell cellEventoConciliacao = XSSFRow.createCell(cellnum++);

			if (procConciliacaoEvento != null && procConciliacaoEvento.getEventId() != null) {
				
				ItemLista itemListaCellEventoConciliacao = 
					itemListaDLO.obterPorCodigo(Texto.preencheString(String.valueOf(procConciliacaoEvento.getCategoryId()), 3, "0", "I"), TipoListaValor.EventoConciliacao.getValor());
				
				cellEventoConciliacao.setCellValue(itemListaCellEventoConciliacao.getDescricao());
				
			} else
				cellEventoConciliacao.setCellValue("");

			ConciliacaoInterface conciliacaoInterface = conciliacaoInterfaceDLO.obterPorCobranca(cobranca);
			XSSFCell cellDataRepasse = XSSFRow.createCell(cellnum++);

			if (conciliacaoInterface != null && conciliacaoInterface.getDataPagamento() != null) {
				cellDataRepasse.setCellValue(sdf.format(conciliacaoInterface.getDataPagamento()));// Entidade
																									// Cobranca
				cellDataRepasse.setCellStyle(cellStyleCenter);
			} else
				cellDataRepasse.setCellValue("");

			// MENSAGEM PROCESSAMEMENTO
			XSSFCell cellMensagemProcessamento = XSSFRow.createCell(cellnum++);
			if (procConciliacaoEvento != null && procConciliacaoEvento.getMensagemProcessamento() != null)
				cellMensagemProcessamento.setCellValue(procConciliacaoEvento.getMensagemProcessamento());
			else
				cellMensagemProcessamento.setCellValue("");

		}

		String filePrefixName = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.arquivo.nome"));
		String fileExtension = (PropertyUtil.getPropertyValue(propertyFileClassPathName,
				"relatorio.conciliacao.arquivo.extensao.xssf"));
		String fileDateCreationName = dataGeracao;
		fileDateCreationName = (fileDateCreationName.replace("/", "").replace(":", "").replace(" ", "")).trim();
		String subheader = (filePrefixName + fileDateCreationName + fileExtension);
		response.setHeader("Content-Disposition", "attachment; filename=" + (subheader));

		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();

	}

	private Empresa getEmpresaFiltro(String empresaFiltro) {
		if (empresaFiltro != null && !empresaFiltro.isEmpty())
			return empresaDLO.obterPorCodigo(empresaFiltro);
		else
			return null;
	}

	private Produto getProdutoFiltro(String produtoFiltro) {
		if (produtoFiltro != null && !produtoFiltro.isEmpty())
			return produtoDLO.obter(Long.parseLong(produtoFiltro));
		else
			return null;
	}

	private Sistema getSistemaFiltro(String sistemaFiltro) {
		if (sistemaFiltro != null && !sistemaFiltro.isEmpty())
			return sistemaDLO.obter(Long.parseLong(sistemaFiltro));
		else
			return null;
	}

}