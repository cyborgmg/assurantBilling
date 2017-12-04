package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.excecoes.DLOException;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class PrepararUsuarioAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
		ActionForward retorno = mapping.findForward("listarUsuario");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		
        session.removeAttribute("empresasNaoAssociadas");
        session.removeAttribute("empresasAssociadas");
        
        session.removeAttribute("gruposUsuariosNaoAssociados");
        session.removeAttribute("gruposUsuariosAssociados");
        
		if (vaf.getListaAtiva() != null  && (!"true".equals(vaf.getListaAtiva()) || vaf.getListaAtiva().trim().length() == 0) 
				&& (vaf.getNovaBusca() != null && vaf.getNovaBusca().equals("true"))) {
     	   vaf.setIdUsuario("");
     	   vaf.setCodigoUsuario("");
     	   vaf.setEmail("");
     	   vaf.setDescricaoUsuario("");	
		}
		
		if (request.getParameter("opcao") != null && (request.getParameter("opcao").equals("1"))) {
			
			session.removeAttribute("pesquisarSessaoForm");
			
			retorno = mapping.findForward("listarUsuario");
		}
		
		if (vaf.getNovaBusca() != null && vaf.getOpcao() != null && !vaf.getOpcao().equals("incluir")) {
	        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
	        	session.setAttribute("pesquisarSessaoForm", vaf);
	        } else {
	        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
	        	if (caf != null) {
	        		BeanUtils.copyProperties(vaf, caf);
	        	}
	        }
		}
		
		if (request.getParameter("opcao") != null && request.getParameter("opcao").equals("incluir")) {
			
			retorno = mapping.findForward("prepararUsuario");
		
            List<Empresa> listaEmpresasAssociadas = new ArrayList<Empresa>();
            List<GrupoUsuario> listaGrupoUsuario = new ArrayList<GrupoUsuario>();

            session.setAttribute("empresasNaoAssociadas", dloEmpresa.listar());
            session.setAttribute("empresasAssociadas", listaEmpresasAssociadas);
            
            session.setAttribute("gruposUsuariosNaoAssociados", grupoUsuarioDLO.listar());
            session.setAttribute("gruposUsuariosAssociados", listaGrupoUsuario);
        
		}
            
		return retorno;
	}
	
}