package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class PrepararContratoCobrancaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("listarContratoCobranca");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
		ProvedorDLO dloProvedor = (ProvedorDLO) ServiceLocator.lookup("java:global/dbsdb/ProvedorDLOBean");

		if (!Validador.vazio((String) session.getAttribute("codigoEmpresaContratoCobranca"))) {
			vaf.setCodigoEmpresaContratoCobranca((String) session.getAttribute("codigoEmpresaContratoCobranca"));
			vaf.setEmpresa((dloEmpresa.obterPorCodigo((String) session.getAttribute("codigoEmpresaContratoCobranca")))
					.getDescricao());
		}
		if (!Validador.vazio((String) session.getAttribute("codigoProvedorContratoCobranca"))) {
			vaf.setCodigoProvedorContratoCobranca((String) session.getAttribute("codigoProvedorContratoCobranca"));
			vaf.setProvedorContratoCobranca(
					(dloProvedor.obterPorCodigo((String) session.getAttribute("codigoProvedorContratoCobranca")))
							.getDescricaoProvedor());
		}
		vaf.setDescricaoContratoCobranca((String) session.getAttribute("descricaoContratoCobranca"));
		
		if (vaf.getOpcao().equals("listarContratoCobranca") || vaf.getOpcao().equals("1")) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if (!"true".equals(vaf.getListaAtiva()) || request.getParameter("opcao") == null
				|| (request.getParameter("opcao") != null && !request.getParameter("opcao").equals("prepararVoltar"))) {
			session.removeAttribute("codigoEmpresaContratoCobranca");
			session.removeAttribute("codigoProvedorContratoCobranca");
			session.removeAttribute("descricaoContratoCobranca");

			vaf.setCodigoEmpresaContratoCobranca("");
			vaf.setEmpresa("");
			vaf.setDescricaoContratoCobranca("");
			vaf.setCodigoProvedorContratoCobranca("");
			vaf.setProvedorContratoCobranca("");

		}
		session.removeAttribute("produtosAssociados");

		if (request.getParameter("opcao") != null && request.getParameter("opcao").equals("incluir")) {

			retorno = mapping.findForward("editarContratoCobranca");

			// List<Produto> listaProdutosNaoAssociados = produtoDLO.listar();
			// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
			// List<Produto> listaProdutosNaoAssociados = cvh.listarProduto();
			List<Produto> listaProdutosAssociados = new ArrayList<Produto>();

			session.setAttribute("produtosNaoAssociados", produtoDLO.listarProdutosSemAssociacao());
			session.setAttribute("produtosAssociados", listaProdutosAssociados);

		}
		
		if (request.getParameter("opcao") != null && request.getParameter("opcao").equals("manterContratoCobranca")) {
			
			List<Produto> listaProdutosAssociados = new ArrayList<Produto>();

			session.setAttribute("produtosNaoAssociados", produtoDLO.listarProdutosSemAssociacao());			
			
		}
		
		if (vaf.getNovaBusca() != null) {
	        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
	        	session.setAttribute("pesquisarSessaoForm", vaf);
	        } else {
	        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
	        	if (caf != null) {
	        		BeanUtils.copyProperties(vaf, caf);
	        	}
	        }
		}

		return retorno;
	}

}