package br.com.delphos.web.conciliacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class ListarConciliacaoAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

        ActionForward retorno = mapping.findForward("listarConciliacao");
        ConciliacaoActionForm vaf = (ConciliacaoActionForm) form;
        
        HttpSession session = request.getSession();
        
		if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarConciliacao") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}
        
        if (!Validador.vazio(vaf.getNovaBusca()) && (vaf.getListaAtiva() == null || vaf.getListaAtiva().equals("false"))) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConciliacaoActionForm caf = (ConciliacaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }
        
        //(vaf.getListaAtiva() != null && vaf.getListaAtiva().trim().isEmpty()) || 
        
        if (	(vaf.getListaAtiva() == null || !"true".equals(vaf.getListaAtiva()) || vaf.getListaAtiva().trim().length() == 0) && 
        		 (request.getParameter("novaBusca") == null || request.getParameter("novaBusca").equals("false")) ) {
			
        	vaf.setDataMovimentoArquivo("");
        	vaf.setIdAdquirente("");
        	vaf.setValorArquivo("");
			
		}

		return retorno;
	}
	
}