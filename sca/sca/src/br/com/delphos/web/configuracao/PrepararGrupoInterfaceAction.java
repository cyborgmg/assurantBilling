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
import org.apache.struts.action.ActionMessages;

import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.gruposInterfaces.GrupoInterfaceDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class PrepararGrupoInterfaceAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
		ActionForward retorno = mapping.findForward("listarGrupoInterface");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
		GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
		GrupoInterfaceDLO grupoInterfaceDLO = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
		UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
		
        session.removeAttribute("gruposUsuariosNaoAssociados");
        session.removeAttribute("gruposUsuariosAssociados");
    
		
		if (request.getParameter("opcao") != null && request.getParameter("opcao").equals("incluir")) {
			
			retorno = mapping.findForward("prepararGrupoInterface");
			
			List<GrupoUsuario> listGrupoUsuario = new ArrayList<GrupoUsuario>();
		
            session.setAttribute("gruposUsuariosNaoAssociados", grupoUsuarioDLO.listar());
            session.setAttribute("gruposUsuariosAssociados", listGrupoUsuario);	
            
		}
		
		if (request.getParameter("opcao") != null && (request.getParameter("opcao").equals("1"))) {
			
			session.removeAttribute("pesquisarSessaoForm");
			
			retorno = mapping.findForward("listarGrupoInterface");
		}
		
		if (vaf.getNovaBusca() != null && vaf.getOpcao() != null && !vaf.getOpcao().equals("incluir")
				&& vaf.getOpcao().equals("listarGrupoInterface")) {
	        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
	        	session.setAttribute("pesquisarSessaoForm", vaf);
	        } else {
	        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
	        	if (caf != null) {
	        		BeanUtils.copyProperties(vaf, caf);
	        	}
	        }
		}
		
		return retorno;
	}
	
}