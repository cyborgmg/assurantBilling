package br.com.delphos.web.configuracao;

import java.util.List;

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
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario_;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario_;
import br.com.delphos.util.ServiceLocator;

public class EditarGrupoUsuarioAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("editarGrupoUsuario");
//        ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;
		
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		GrupoInterfaceDLO grupoInterfaceDLO = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		
        String operacao = request.getParameter("operacao");
		
		try {
			HttpSession session = request.getSession();
			String uid = (String) session.getAttribute("corporativo_uid");

			GrupoUsuario grupoUsuario = new GrupoUsuario();
			
	           if (uaf != null) {

	                if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0) || (request.getParameter("idPesquisa") != null)) {
	                    
	                	grupoUsuario = grupoUsuarioDLO.obterPorId(Long.parseLong(request.getParameter("idPesquisa")));
	                	
	                	uaf.setTipoGrupo(grupoUsuario.getTipoGrupo());
	                	uaf.setDescricaoGrupoUsuario(grupoUsuario.getDescricao());         	
	                	uaf.setIdGrupoUsuario(grupoUsuario.getId().toString());
	                	
	                	grupoUsuario = grupoUsuarioDLO.completar(grupoUsuario, GrupoUsuario_.gruposInterface.getName(), GrupoUsuario_.usuarios.getName());
	                	
	    	            List<Usuario> listaUsuariosAssociados = grupoUsuario.getUsuarios();
	    	            List<GrupoInterface> listaGrupoInterface = grupoUsuario.getGruposInterface();
	    	
	    	            session.setAttribute("usuariosNaoAssociados", grupoUsuarioDLO.obterUsuariosSemAssociacao(grupoUsuario.getId().toString()));
	    	            session.setAttribute("usuariosAssociados", listaUsuariosAssociados);
	    	            
	    	            session.setAttribute("gruposInterfaceNaoAssociados", grupoInterfaceDLO.obterGrupoInterfaceSemAssociacao(grupoUsuario.getId()));
	    	            session.setAttribute("gruposInterfaceAssociados", listaGrupoInterface);
	                
	    	            uaf.setListaAtiva("true");
	                }
	                
		   			if (uaf.getListaAtiva() != null  && (!"true".equals(uaf.getListaAtiva()) || uaf.getListaAtiva().trim().length() == 0) 
		   					&& (uaf.getNovaBusca() != null && uaf.getNovaBusca().equals("true"))) {
		         	   uaf.setIdGrupoUsuario("");
		         	   uaf.setTipoGrupo("");
		         	   uaf.setDescricaoGrupoUsuario("");	
					}
	            }
	           
		} catch (Exception e) {
			e.printStackTrace();
			
			String msg = DLOException.obterMensagemDLO(e);
			ActionMessage erro = null;
			
			messages.add("dialogoAlerta", erro);
			this.addErrors(request, messages);

		}
		return retorno;
	}
}