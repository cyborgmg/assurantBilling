package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.excecoes.DLOException;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import br.com.delphos.sca.gruposInterfaces.GrupoInterfaceDLO;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface_;
import br.com.delphos.sca.usuarios.Usuario_;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarGrupoInterfaceAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("editarGrupoInterface");
//        ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;
		
		GrupoInterfaceDLO grupoInterfaceDLO = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
		
        String operacao = request.getParameter("operacao");
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");

		GrupoInterface grupoInterface = new GrupoInterface();
		
           if (uaf != null) {

                if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0) || (request.getParameter("idPesquisa") != null)) {
                    
                	grupoInterface = grupoInterfaceDLO.obterPorId(Long.parseLong(request.getParameter("idPesquisa")));
                	
                	uaf.setDescricaoGrupoInterface(grupoInterface.getDescricao());
                	uaf.setIdGrupoInterface(grupoInterface.getId().toString());
                	
                	grupoInterface = grupoInterfaceDLO.completar(grupoInterface, GrupoInterface_.gruposUsuario.getName());
                	
    	            session.setAttribute("gruposUsuariosNaoAssociados", grupoInterfaceDLO.obterGrupoUsuariosSemAssociacao(grupoInterface.getId().toString()));
    	            session.setAttribute("gruposUsuariosAssociados", grupoInterface.getGruposUsuario());
                }
            }

		return retorno;
	}
}