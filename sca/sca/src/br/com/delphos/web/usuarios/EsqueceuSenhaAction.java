package br.com.delphos.web.usuarios;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.sca.constantes.CodigoMensagem;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.Mensagens;
import br.com.delphos.util.ServiceLocator;

public class EsqueceuSenhaAction extends Action {
	private final Logger LOGGER = LoggerFactory.getLogger(EsqueceuSenhaAction.class);
	
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.getInputForward();
        UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:app/sca-ejb/UsuarioDLOBean");

        String login = request.getParameter("usuario");
        String email = request.getParameter("email");

        URL url = new URL(
        		request.getScheme(),
        		request.getServerName(),
        		request.getServerPort(),
        		request.getContextPath() + "/login/reiniciar.do");
        
        Usuario usuario = new Usuario();
        usuario.setCodigo(login);
        boolean sucesso;
        
        try {
        	usuarioDLO.iniciarEsqueciSenha(usuario, email, url);
        	sucesso = true;
        	
        } catch (DelphosException e) {
        	LOGGER.debug(null, e);
        	sucesso = false;
        }

        if (sucesso) {
	        ActionMessage ok = new ActionMessage("msg.esqueceuSenha.email.sucesso");
	        messages.add("dialogoSucesso", ok);
	        this.addMessages(request, messages);
	        retorno = mapping.findForward("login");
	        
        } else {
            ActionMessage msg = new ActionMessage("erro.email.ou.usuario.invalidos");
            messages.add("dialogoErro", msg);
            this.addErrors(request, messages);
        }

        return retorno;
    }
}