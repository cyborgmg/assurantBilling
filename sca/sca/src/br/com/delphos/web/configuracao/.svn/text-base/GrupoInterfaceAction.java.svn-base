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

public class GrupoInterfaceAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("listarGrupoInterface");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		
        String operacao = request.getParameter("operacao");
		
		EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		GrupoInterfaceDLO grupoInterfaceDLO = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
		
		GrupoInterface grupoInterface = new GrupoInterface();
		GrupoInterface grupoInterfaceConsultaPrevia = null;
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }
		
       if ("2".equalsIgnoreCase(operacao)) {
    	   
    	   retorno = mapping.findForward("listarGrupoInterface");
           
           if (!vaf.getIdGrupoInterface().trim().isEmpty()) {
        	   grupoInterfaceConsultaPrevia = grupoInterfaceDLO.obterPorId(Long.valueOf(vaf.getIdGrupoInterface()));
           }

           if (grupoInterfaceConsultaPrevia == null) {
               grupoInterface.setDescricao(vaf.getDescricaoGrupoInterface().toUpperCase());
               
        	   List<GrupoUsuario> gruposUsuariosAssociados = new ArrayList<GrupoUsuario>();
        	   if (vaf.getGruposUsuariosAssociados().isEmpty()) {
        		   grupoInterface.setGruposUsuario((new ArrayList<GrupoUsuario>()));
        	   } else {
        		   String[] gruposUsuariosAssociadosFromForm = vaf.getGruposUsuariosAssociados().split(",");
        		   for (int i = 0; i < gruposUsuariosAssociadosFromForm.length; i++) {
        			   String string = gruposUsuariosAssociadosFromForm[i];
        			   gruposUsuariosAssociados.add(grupoUsuarioDLO.obterPorId(Long.valueOf(string)));
        		   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
        		   grupoInterface.setGruposUsuario(gruposUsuariosAssociados);
        	   }
        	   
               GrupoInterface grupoInterfaceNovo = grupoInterfaceDLO.obterPorId(grupoInterfaceDLO.manter(grupoInterface));
               
               grupoInterfaceDLO.reassociar(grupoInterfaceNovo, gruposUsuariosAssociados);
               
               vaf.setListaAtiva("true");
               vaf.setNovaBusca("true");
        	   
        	   ActionMessage ok = new ActionMessage("msg.grupoInterface.incluido.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);

           } else {
        	   
        	   Usuario usuarioNovo = new Usuario();
//            	   usuarioNovo.setId(Long.valueOf(vaf.getIdUsuario()));
        	   grupoInterfaceConsultaPrevia.setDescricao(vaf.getDescricaoGrupoInterface().toUpperCase());
        	   
        	   List<GrupoUsuario> gruposUsuariosAssociados = new ArrayList<GrupoUsuario>();
        	   if (vaf.getGruposUsuariosAssociados().isEmpty()) {
        		   grupoInterfaceConsultaPrevia.setGruposUsuario((new ArrayList<GrupoUsuario>()));
        	   } else {
        		   String[] gruposUsuariosAssociadosFromForm = vaf.getGruposUsuariosAssociados().split(",");
        		   for (int i = 0; i < gruposUsuariosAssociadosFromForm.length; i++) {
        			   String string = gruposUsuariosAssociadosFromForm[i];
        			   gruposUsuariosAssociados.add(grupoUsuarioDLO.obterPorId(Long.valueOf(string)));
        		   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
        		   grupoInterfaceConsultaPrevia.setGruposUsuario(gruposUsuariosAssociados);
        	   }
        	   
        	   GrupoInterface grupoInterfaceNovo = grupoInterfaceDLO.obterPorId(grupoInterfaceDLO.manter(grupoInterfaceConsultaPrevia));
        	   
        	   grupoInterfaceDLO.reassociar(grupoInterfaceNovo, gruposUsuariosAssociados);
        	   
        	   ActionMessage ok = new ActionMessage("msg.grupoInterface.alterado.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
               
        	   
           }
    	   
       }
       
       if ("3".equalsIgnoreCase(operacao)) {
    	   
    	   retorno = mapping.findForward("listarGrupoInterface");
    	   
    	   grupoInterfaceConsultaPrevia = grupoInterfaceDLO.obterPorId(Long.valueOf(vaf.getIdGrupoInterface()));
    	   
    	   grupoInterfaceDLO.excluir(grupoInterfaceConsultaPrevia);
    		   
        	   ActionMessage ok = new ActionMessage("msg.grupoInterface.excluido.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
           
       }


		return retorno;
	}
	
}