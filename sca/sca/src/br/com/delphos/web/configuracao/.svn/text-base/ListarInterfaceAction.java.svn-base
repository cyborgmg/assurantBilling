package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class ListarInterfaceAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		
		if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarInterface") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		}
		
        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }

        ActionForward retorno = mapping.findForward("listarInterface");

		return retorno;
	}
	
}