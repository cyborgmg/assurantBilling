package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.produtos.Produto_;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarProdutoAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarProduto");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ProdutoDLO dloProduto = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
		SistemaDLO dloSistema = (SistemaDLO) ServiceLocator.lookup("java:/global/dbsdb/SistemaDLOBean");
		ContratoCobrancaDLO dloContratoCobranca = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");

		Produto produto = new Produto();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				List<Empresa> listaEmpresasNaoAssociadas = dloEmpresa.listar();
				// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
				// List<Empresa> listaEmpresasNaoAssociadas =
				// cvh.listarEmpresa();
				List<Empresa> listaEmpresasAssociadas = new ArrayList<Empresa>();

				session.setAttribute("empresasNaoAssociadas", listaEmpresasNaoAssociadas);
				session.setAttribute("empresasAssociadas", listaEmpresasAssociadas);

				produto = dloProduto.obter(Long.parseLong(request.getParameter("idPesquisa")));
				produto = dloProduto.completar(produto, Produto_.contratosCobranca.getName());
				// produto = cvh.listarProduto().get(0);

				// List<ContratoCobranca> listaContratosCobrancaNaoAssociados =
				// cvh.listarContratoCobranca();
				List<ContratoCobranca> listaContratosCobrancaAssociados = produto.getContratosCobranca();
				List<ContratoCobranca> listaContratosCobrancaNaoAssociados = dloContratoCobranca.listar();
				List<ContratoCobranca> listaContratosCobrancaNaoAssociadosTemp = new ArrayList<ContratoCobranca>(
						listaContratosCobrancaNaoAssociados);
						// listaContratosCobrancaNaoAssociados.removeAll(listaContratosCobrancaAssociados);

				// removendo as listas já associadas
				for (Iterator<ContratoCobranca> iteratorNaoAssociados = listaContratosCobrancaNaoAssociadosTemp
						.iterator(); iteratorNaoAssociados.hasNext();) {
					ContratoCobranca contratoCobranca = (ContratoCobranca) iteratorNaoAssociados.next();

					for (Iterator<ContratoCobranca> iteratorAssociados = listaContratosCobrancaAssociados.iterator(); iteratorAssociados
							.hasNext();) {
						ContratoCobranca contratoCobrancaAssociado = (ContratoCobranca) iteratorAssociados.next();

						if (contratoCobrancaAssociado.getId().longValue() == contratoCobranca.getId().longValue()) {
							listaContratosCobrancaNaoAssociados.remove(contratoCobranca);
						}

					}

				}

				session.setAttribute("contratosCobrancaNaoAssociados", listaContratosCobrancaNaoAssociados);
				session.setAttribute("contratosCobrancaAssociados", listaContratosCobrancaAssociados);

				uaf.setCodigoProduto(produto.getCodigo());
				uaf.setCodigoEmpresa(produto.getEmpresa().getCodigo());
				uaf.setDescricaoProduto(produto.getDescricao());
				uaf.setNumeroMaximoParcelasProduto(String.valueOf(produto.getNumeroMaximoParcelas()));
				uaf.setTipoCobrancaProduto(
						TipoCobranca.buscarPorValor(produto.getTipoCobranca().getValor()).getValor());

				uaf.setIdProduto(produto.getId().toString());
				
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}
}