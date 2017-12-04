package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class PrepararEmpresaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("listarEmpresa");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		
		if (request.getParameter("opcao") != null && request.getParameter("opcao").equals("incluir")) {
			retorno = mapping.findForward("editarEmpresa");
		}
		
		if (request.getParameter("opcao") != null && (request.getParameter("opcao").equals("1"))) {
			
			session.removeAttribute("pesquisarSessaoForm");
			
			retorno = mapping.findForward("listarEmpresa");
		}
		
//		if (uaf.getListaAtiva() != null
//				&& (!"true".equals(uaf.getListaAtiva()) || uaf.getListaAtiva().trim().length() == 0)
//				&& (uaf.getNovaBusca() != null && uaf.getNovaBusca().equals("true"))) {
		if (!"true".equals(vaf.getListaAtiva()) || request.getParameter("opcao") == null
				|| (request.getParameter("opcao") != null && !request.getParameter("opcao").equals("prepararVoltar"))) {
		
			vaf.setIdEmpresa("");
			vaf.setCodigoEmpresa("");
			vaf.setDescricaoEmpresa("");
			
		}
		
		if (request.getParameter("opcao") != null && (request.getParameter("opcao").equals("1"))) {
			
			session.removeAttribute("pesquisarSessaoForm");
			
			retorno = mapping.findForward("listarEmpresa");
		}
		
		if (vaf.getNovaBusca() != null && vaf.getOpcao() != null && !vaf.getOpcao().equals("incluir")) {
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
