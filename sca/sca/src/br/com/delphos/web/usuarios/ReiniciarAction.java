package br.com.delphos.web.usuarios;

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
import br.com.delphos.sca.constantes.OperacaoUsuario;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.Mensagens;
import br.com.delphos.util.ServiceLocator;

public class ReiniciarAction extends Action {
	private final Logger LOGGER = LoggerFactory.getLogger(ReiniciarAction.class);

   @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //--- inicializações gerais
       	UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:app/sca-ejb/UsuarioDLOBean");
        ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("reiniciarSenha");

        String token = request.getParameter(OperacaoUsuario.NOME_PROPRIEDADE_TOKEN);
        String origem = request.getParameter(OperacaoUsuario.NOME_PROPRIEDADE_OPERACAO);
        OperacaoUsuario operacao = OperacaoUsuario.buscarPorValor(origem);
        boolean sucesso = token != null;
        
        //Testa se esta sendo passado algum token pela URL
        if (sucesso) {
        	Usuario usuario = null;
        	
        	try {
        		usuario = usuarioDLO.obterPorToken(token);
        	} catch (DelphosException e) {
            	LOGGER.debug(null, e);
        	}

        	sucesso = usuario != null;
        	
            //Testa se existe algum usuário 
            if (sucesso) {
                ReiniciarSenhaActionForm reiniciarSenhaActionForm = new ReiniciarSenhaActionForm();

                if (operacao == OperacaoUsuario.CONFIRMAR_EMAIL) {
                	reiniciarSenhaActionForm.setChaveTitulo("label.ativar.senha");
                	
                } else if (operacao == OperacaoUsuario.ESQUECI_SENHA) {
                	reiniciarSenhaActionForm.setChaveTitulo("label.ativar.nova.senha");
                }
                
                reiniciarSenhaActionForm.setOrigem(origem);
                reiniciarSenhaActionForm.setId(usuario.getId().toString());
                reiniciarSenhaActionForm.setUsuario(usuario.getCodigo());
                reiniciarSenhaActionForm.setEmail(usuario.getEmail());
                reiniciarSenhaActionForm.setToken(token);
                request.setAttribute("ReiniciarSenhaActionForm", reiniciarSenhaActionForm);
            }
        }

        if (!sucesso) {
	        ActionMessage msg = new ActionMessage("erro.token.invalido");
	        messages.add("dialogoErro", msg);
	        this.addErrors(request, messages);
	        retorno = mapping.findForward("login");
        }
        
        return retorno;
    }
}
