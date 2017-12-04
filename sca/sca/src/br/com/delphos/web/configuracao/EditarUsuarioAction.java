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
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario_;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarUsuarioAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("editarUsuario");
//        ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;
		
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
		
        String operacao = request.getParameter("operacao");
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");

		Usuario usuario = new Usuario();
		
//          List<Produto> listaProdutosNaoAssociados = dloProduto.listar();

           if (uaf != null && request.getParameter("idPesquisa") != null) {

                if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)) {
                    
//	                	sistema = dloSistema.obter(Long.parseLong(request.getParameter("idPesquisa")));
                	usuario = usuarioDLO.obterPorId(Long.valueOf(request.getParameter("idPesquisa")));
                	
                	uaf.setCodigoUsuario(usuario.getCodigo());
                	uaf.setDescricaoUsuario(usuario.getDescricao());	                	
                	uaf.setEmail(usuario.getEmail());                	
                	uaf.setIdUsuario(usuario.getId().toString());
                	
                	uaf.setListaAtiva("true");
                	
//	    	            List<Empresa> listaEmpresasAssociadas = new ArrayList<Empresa>();
    	        	
    	            session.setAttribute("empresasNaoAssociadas", dloEmpresa.obterEmpresasSemAssociacao(usuario.getId().toString()));
    	            usuario = usuarioDLO.completar(usuario, Usuario_.empresas.getName(), Usuario_.gruposUsuario.getName());
//	    	            usuario.getEmpresas().size();
    	            session.setAttribute("empresasAssociadas", usuario.getEmpresas());
    	            
    	            session.setAttribute("gruposUsuariosNaoAssociados", grupoUsuarioDLO.obterGrupoUsuariosSemAssociacao(usuario.getId().toString()));
    	            session.setAttribute("gruposUsuariosAssociados", usuario.getGruposUsuario());
                	
                }
            } else {
          	   limparForm(uaf, request);	
            }
           
		if (uaf.getListaAtiva() != null  && (!"true".equals(uaf.getListaAtiva()) || uaf.getListaAtiva().trim().length() == 0) 
				&& (uaf.getNovaBusca() != null && uaf.getNovaBusca().equals("true"))) {
     	   limparForm(uaf, request);	
		}

		return retorno;
	}

	private void limparForm(ConfiguracaoActionForm uaf, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		uaf.setIdUsuario("");
     	   uaf.setCodigoUsuario("");
     	   uaf.setEmail("");
     	   uaf.setDescricaoUsuario("");
     	   
           session.removeAttribute("empresasNaoAssociadas");
           session.removeAttribute("empresasAssociadas");
           
           session.removeAttribute("gruposUsuariosNaoAssociados");
           session.removeAttribute("gruposUsuariosAssociados");
	}
}