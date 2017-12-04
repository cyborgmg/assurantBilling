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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import br.com.delphos.sca.gruposInterfaces.GrupoInterfaceDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class GrupoUsuarioAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("listarGrupoUsuario");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		
        String operacao = request.getParameter("operacao");
		
		EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		GrupoInterfaceDLO grupoInterfaceDLO = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
		
		GrupoUsuario grupoUsuario = new GrupoUsuario();
		GrupoUsuario grupoUsuarioConsultaPrevia = null;
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }
		
       if ("2".equalsIgnoreCase(operacao)) {
    	   
    	   retorno = mapping.findForward("listarGrupoUsuario");
           
           if (!vaf.getIdGrupoUsuario().trim().isEmpty()) {
        	   grupoUsuarioConsultaPrevia = grupoUsuarioDLO.obterPorId(Long.valueOf(vaf.getIdGrupoUsuario()));
           }

           if (grupoUsuarioConsultaPrevia == null) {
        	   grupoUsuario.setTipoGrupo(vaf.getTipoGrupo().toUpperCase());
               grupoUsuario.setDescricao(vaf.getDescricaoGrupoUsuario().toUpperCase());
               
//   	            session.setAttribute("usuariosNaoAssociados", usuarioDLO.listar());
//   	            session.setAttribute("usuariosAssociados", listaUsuariosAssociados);
//   	            
//   	            session.setAttribute("gruposInterfaceNaoAssociados", grupoInterfaceDLO.listar());
//   	            session.setAttribute("gruposInterfaceAssociados", listaGrupoInterface);
               
        	   List<Usuario> usuariosAssociados = new ArrayList<Usuario>();
        	   if (vaf.getUsuariosAssociados().isEmpty()) {
        		   grupoUsuario.setUsuarios((new ArrayList<Usuario>()));
        	   } else {
            	   String[] usuariosAssociadosFromForm = vaf.getUsuariosAssociados().split(",");
            	   for (int i = 0; i < usuariosAssociadosFromForm.length; i++) {
					String string = usuariosAssociadosFromForm[i];
					usuariosAssociados.add(usuarioDLO.obterPorId(Long.valueOf(string)));
            	   }
            	   grupoUsuario.setUsuarios(usuariosAssociados);
        	   }
        	   
        	   List<GrupoInterface> gruposInterfaceAssociados = new ArrayList<GrupoInterface>();
        	   if (vaf.getGruposInterfaceAssociados().isEmpty()) {
        		   grupoUsuario.setGruposInterface((new ArrayList<GrupoInterface>()));
        	   } else {
        		   String[] gruposInterfaceAssociadosFromForm = vaf.getGruposInterfaceAssociados().split(",");
        		   for (int i = 0; i < gruposInterfaceAssociadosFromForm.length; i++) {
        			   String string = gruposInterfaceAssociadosFromForm[i];
        			   gruposInterfaceAssociados.add(grupoInterfaceDLO.obterPorId(Long.valueOf(string)));
        		   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
        		   grupoUsuario.setGruposInterface(gruposInterfaceAssociados);
        	   }
               
        	   GrupoUsuario grupoUsuarioNovo = grupoUsuarioDLO.obterPorId(grupoUsuarioDLO.manter(grupoUsuario));
        	   
        	   grupoUsuarioDLO.reassociarUsuarios(grupoUsuarioNovo, usuariosAssociados);
        	   grupoUsuarioDLO.reassociarGrupoInterface(grupoUsuarioNovo, gruposInterfaceAssociados);
        	   
        	   ActionMessage ok = new ActionMessage("msg.grupoUsuario.incluido.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
               
               vaf.setIdGrupoUsuario(grupoUsuario.getId().toString());
               vaf.setTipoGrupo(grupoUsuarioNovo.getTipoGrupo());
               vaf.setDescricaoGrupoUsuario(grupoUsuarioNovo.getDescricao());
               
               vaf.setListaAtiva("true");
               vaf.setNovaBusca("true");
               
           } else {
        	   
        	   Usuario usuarioNovo = new Usuario();
//            	   usuarioNovo.setId(Long.valueOf(vaf.getIdUsuario()));
        	   grupoUsuarioConsultaPrevia.setTipoGrupo(vaf.getTipoGrupo().toUpperCase());
        	   grupoUsuarioConsultaPrevia.setDescricao(vaf.getDescricaoGrupoUsuario().toUpperCase());
        	   
        	   List<Usuario> usuariosAssociados = new ArrayList<Usuario>();
        	   if (vaf.getUsuariosAssociados().isEmpty()) {
        		   grupoUsuarioConsultaPrevia.setUsuarios((new ArrayList<Usuario>()));
        	   } else {
            	   String[] usuariosAssociadosFromForm = vaf.getUsuariosAssociados().split(",");
            	   for (int i = 0; i < usuariosAssociadosFromForm.length; i++) {
					String string = usuariosAssociadosFromForm[i];
					usuariosAssociados.add(usuarioDLO.obterPorId(Long.valueOf(string)));
            	   }
            	   grupoUsuarioConsultaPrevia.setUsuarios(usuariosAssociados);
        	   }         	   
        	   
        	   grupoUsuarioDLO.reassociarUsuarios(grupoUsuarioConsultaPrevia, usuariosAssociados);
        	   
        	   List<GrupoInterface> gruposInterfaceAssociados = new ArrayList<GrupoInterface>();
        	   if (vaf.getGruposInterfaceAssociados().isEmpty()) {
        		   grupoUsuarioConsultaPrevia.setGruposInterface((new ArrayList<GrupoInterface>()));
        	   } else {
        		   String[] gruposInterfaceAssociadosFromForm = vaf.getGruposInterfaceAssociados().split(",");
        		   for (int i = 0; i < gruposInterfaceAssociadosFromForm.length; i++) {
        			   String string = gruposInterfaceAssociadosFromForm[i];
        			   gruposInterfaceAssociados.add(grupoInterfaceDLO.obterPorId(Long.valueOf(string)));
        		   }
        		   grupoUsuarioConsultaPrevia.setGruposInterface(gruposInterfaceAssociados);
        	   }
        	   
        	   grupoUsuarioDLO.reassociarGrupoInterface(grupoUsuarioConsultaPrevia, gruposInterfaceAssociados);
        	   
        	   GrupoUsuario grupoUsuarioNovo = grupoUsuarioDLO.obterPorId(grupoUsuarioDLO.manter(grupoUsuarioConsultaPrevia));
        	   
        	   ActionMessage ok = new ActionMessage("msg.grupoUsuario.alterado.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
               
        	   
           }
    	   
       }
       
       if ("3".equalsIgnoreCase(operacao)) {
    	   
    	   retorno = mapping.findForward("listarGrupoUsuario");
    	   
    	   grupoUsuarioConsultaPrevia = grupoUsuarioDLO.obterPorId(Long.valueOf(vaf.getIdGrupoUsuario()));
    	   
    	   grupoUsuarioDLO.excluir(grupoUsuarioConsultaPrevia);
    		   
        	   ActionMessage ok = new ActionMessage("msg.grupoUsuario.excluido.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
           
       }


		return retorno;
	}
	
}