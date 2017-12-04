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
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class PrepararSistemaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarSistema");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		if (!"true".equals(vaf.getListaAtiva()) && (vaf.getNovaBusca() != null && vaf.getNovaBusca().equals("true"))) {
			
			vaf.setCodigoSistema("");
			vaf.setDescricaoSistema("");
			
		}

		if (vaf.getOpcao() != null && !vaf.getOpcao().equals("listarSistema") && !vaf.getOpcao().equals("1")) {

			SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:global/dbsdb/SistemaDLOBean");
			EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:global/dbsdb/EmpresaDLOBean");
			ProdutoDLO dloProduto = (ProdutoDLO) ServiceLocator.lookup("java:global/dbsdb/ProdutoDLOBean");

			// List<Produto> listaProdutosNaoAssociados = dloProduto.listar();
			ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
			List<Produto> listaProdutosNaoAssociados = cvh.listarProduto();
			List<Produto> listaProdutosAssociados = new ArrayList<Produto>();

			session.setAttribute("produtosNaoAssociados", listaProdutosNaoAssociados);
			session.setAttribute("produtosAssociados", listaProdutosAssociados);
			
		} else if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarSistema") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
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
			
			retorno = mapping.findForward("listarSistema");
		}

		return retorno;
	}

}