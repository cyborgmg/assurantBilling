package br.com.delphos.web.usuarios;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.StrutsAction;

public class EsqueceuSenhaAction extends StrutsAction {

	private SCAClient scaClient = new SCAClient();
	
    @Override
    public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionForward retorno = mapping.findForward("login");

        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");

        URL url = new URL(
        		request.getScheme(),
        		request.getServerName(),
        		request.getServerPort(),
        		request.getContextPath() + "/login/reiniciar.do");
        boolean sucesso = scaClient.iniciarEsqueceuSenha(usuario, email, url.toString());

        if (sucesso) {
	        ActionMessage ok = new ActionMessage("msg.esqueceuSenha.email.sucesso");
	        setMensagemSucesso(ok);
	        
        } else {
        	throw new DLOException(CodigoMensagem.Falha);
        }

        return retorno;
    }
}