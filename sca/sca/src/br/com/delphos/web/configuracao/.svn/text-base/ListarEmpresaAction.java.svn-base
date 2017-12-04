package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.util.Validador;

public class ListarEmpresaAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }
		
		if (vaf.getListaAtiva() != null  && (!"true".equals(vaf.getListaAtiva()) || vaf.getListaAtiva().trim().length() == 0)) {
     	   vaf.setIdEmpresa("");
     	   vaf.setCodigoEmpresa("");
     	   vaf.setDescricaoEmpresa("");	
		}

        ActionForward retorno = mapping.findForward("listarEmpresa");

		return retorno;
	}
	
}