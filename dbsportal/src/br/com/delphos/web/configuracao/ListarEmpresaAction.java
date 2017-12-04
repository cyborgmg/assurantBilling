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

public class ListarEmpresaAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ActionForward retorno = mapping.findForward("listarEmpresa");
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
        
        //(vaf.getListaAtiva() != null && vaf.getListaAtiva().trim().isEmpty()) || 
        
        if (	(!"true".equals(vaf.getListaAtiva()) || vaf.getListaAtiva().trim().length() == 0) && 
        		 vaf.getNovaBusca() == null) {
			
        	vaf.setCodigoEmpresa("");
        	vaf.setDescricaoEmpresa("");
        	vaf.setIdEmpresa("");
			
		}

		return retorno;
	}
	
}