package br.com.delphos.web.configuracao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class UsuarioAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
		ActionForward retorno = mapping.findForward("listarUsuario");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		if (!vaf.getOpcao().equals("manterUsuario")) {
			if (Validador.vazio(vaf.getUsuario())) {
				vaf.setDescricaoUsuario("");
			}
			if (Validador.vazio(vaf.getEmpresa())) {
				vaf.setDescricaoEmpresa("");
			}
		}
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		
        String operacao = request.getParameter("operacao");
		
		EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		
		Usuario usuario = new Usuario();
		Usuario usuarioConsultaPrevia = null;
		
		if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarUsuario") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		}
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }
		
       if ("2".equalsIgnoreCase(operacao)) {
    	   
    	   retorno = mapping.findForward("listarUsuario");
           
           if (!vaf.getIdUsuario().trim().isEmpty()) {
        	   usuarioConsultaPrevia = usuarioDLO.obterPorId(Long.valueOf(vaf.getIdUsuario()));
           }

           if (usuarioConsultaPrevia == null) {
        	   usuario.setCodigo(vaf.getCodigoUsuario().toUpperCase());
               usuario.setDescricao(vaf.getDescricaoUsuario().toUpperCase());
               usuario.setEmail(vaf.getEmail());
               
        	   List<Empresa> empresasAssociadas = new ArrayList<Empresa>();
        	   if (vaf.getEmpresasAssociadas().isEmpty()) {
        		   usuario.setEmpresas((new ArrayList<Empresa>()));
        	   } else {
            	   String[] empresasAssociadasFromForm = vaf.getEmpresasAssociadas().split(",");
            	   for (int i = 0; i < empresasAssociadasFromForm.length; i++) {
					String string = empresasAssociadasFromForm[i];
					empresasAssociadas.add(empresaDLO.obterPorId(Long.valueOf(string)));
            	   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
            	   usuario.setEmpresas(empresasAssociadas);
        	   }
        	   
        	   List<GrupoUsuario> gruposUsuariosAssociados = new ArrayList<GrupoUsuario>();
        	   if (vaf.getGruposUsuariosAssociados().isEmpty()) {
        		   usuario.setGruposUsuario((new ArrayList<GrupoUsuario>()));
        	   } else {
        		   String[] gruposUsuariosAssociadosFromForm = vaf.getGruposUsuariosAssociados().split(",");
        		   for (int i = 0; i < gruposUsuariosAssociadosFromForm.length; i++) {
        			   String string = gruposUsuariosAssociadosFromForm[i];
        			   gruposUsuariosAssociados.add(grupoUsuarioDLO.obterPorId(Long.valueOf(string)));
        		   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
        		   usuario.setGruposUsuario(gruposUsuariosAssociados);
        	   }
               
        	   Usuario usuarioNovo = usuarioDLO.obterPorId(usuarioDLO.manter(usuario));
        	   
        	   empresaDLO.reassociar(usuarioNovo, empresasAssociadas);
        	   grupoUsuarioDLO.reassociar(usuarioNovo, gruposUsuariosAssociados);
        	   
        	   URL urlServidor = new URL(
		        		request.getScheme(),
		        		request.getServerName(),
		        		request.getServerPort(),
		        		request.getContextPath() + "/login/reiniciar.do");
        	   
        	   try{
        		   
       		   		usuarioDLO.enviarEmailConfirmacao(usuarioNovo, urlServidor);
       	   
       		   		ActionMessage ok = new ActionMessage("msg.usuario.incluido.sucesso");
              		messages.add("dialogoSucesso", ok);
              		this.addMessages(request, messages);
              
        	   }catch(Exception e){
        		   
        		   e.printStackTrace(); // TODO REMOVER DEPOIS
        		   
       		   		ActionMessage ok = new ActionMessage("msg.erro.envioEmail");
       		   		messages.add("dialogoSucesso", ok);
                  	this.addMessages(request, messages); 
       		   }
               
               vaf.setIdEmpresa(usuario.getId().toString());
               
               vaf.setListaAtiva("true");
           } else {
        	   
//            	   Usuario usuarioNovo = new Usuario();
//            	   usuarioNovo.setId(Long.valueOf(vaf.getIdUsuario()));
        	   usuarioConsultaPrevia.setCodigo(vaf.getCodigoUsuario().toUpperCase());
        	   usuarioConsultaPrevia.setDescricao(vaf.getDescricaoUsuario().toUpperCase());
        	   usuarioConsultaPrevia.setEmail(vaf.getEmail());
        	   usuarioConsultaPrevia.setDataUltimaAlteracao(new Date());
        	   
        	   List<Empresa> empresasAssociadas = new ArrayList<Empresa>();
        	   if (vaf.getEmpresasAssociadas().isEmpty()) {
        		   usuarioConsultaPrevia.setEmpresas((new ArrayList<Empresa>()));
        	   } else {
            	   String[] empresasAssociadasFromForm = vaf.getEmpresasAssociadas().split(",");
            	   for (int i = 0; i < empresasAssociadasFromForm.length; i++) {
					String string = empresasAssociadasFromForm[i];
					empresasAssociadas.add(empresaDLO.obterPorId(Long.valueOf(string)));
            	   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
            	   usuarioConsultaPrevia.setEmpresas(empresasAssociadas);
        	   }           	   
        	   
        	   usuarioConsultaPrevia = usuarioDLO.obterPorId((Long)usuarioDLO.manter(usuarioConsultaPrevia));
        	   
        	   empresaDLO.reassociar(usuarioConsultaPrevia, empresasAssociadas);
        	   
        	   List<GrupoUsuario> gruposUsuariosAssociados = new ArrayList<GrupoUsuario>();
        	   if (vaf.getGruposUsuariosAssociados().isEmpty()) {
        		   usuarioConsultaPrevia.setGruposUsuario((new ArrayList<GrupoUsuario>()));
        	   } else {
        		   String[] gruposUsuariosAssociadosFromForm = vaf.getGruposUsuariosAssociados().split(",");
        		   for (int i = 0; i < gruposUsuariosAssociadosFromForm.length; i++) {
        			   String string = gruposUsuariosAssociadosFromForm[i];
        			   gruposUsuariosAssociados.add(grupoUsuarioDLO.obterPorId(Long.valueOf(string)));
        		   }
//	            	   contratoCobranca.setProdutos(produtosAssociados);
        		   usuarioConsultaPrevia.setGruposUsuario(gruposUsuariosAssociados);
        	   }
        	   
        	   grupoUsuarioDLO.reassociar(usuarioConsultaPrevia, gruposUsuariosAssociados);
        	   
        	   ActionMessage ok = new ActionMessage("msg.usuario.alterado.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
               
        	   
           }
    	   
       }
       
       if ("3".equalsIgnoreCase(operacao)) {
    	   
    	   retorno = mapping.findForward("listarUsuario");
    	   
    	   usuarioConsultaPrevia = usuarioDLO.obterPorId(Long.valueOf(vaf.getIdUsuario()));
    	   
    		   usuarioDLO.excluir(usuarioConsultaPrevia);
    		   
        	   ActionMessage ok = new ActionMessage("msg.usuario.excluido.sucesso");
               messages.add("dialogoSucesso", ok);
               this.addMessages(request, messages);
           
       }

		return retorno;
	}
	
}