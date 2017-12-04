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
import br.com.delphos.util.Mensagens;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.web.seguranca.Criptografia;

public class ReiniciarSenhaAction extends Action {
	private final Logger LOGGER = LoggerFactory.getLogger(ReiniciarSenhaAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializaões gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("reiniciarSenha");
       	UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:app/sca-ejb/UsuarioDLOBean");
		ReiniciarSenhaActionForm rsaf = (ReiniciarSenhaActionForm) form;

//		HttpSession session = request.getSession();
		String senha = rsaf.getSenhaNova();
		String token = rsaf.getToken();
		OperacaoUsuario operacao = OperacaoUsuario.buscarPorValor(rsaf.getOrigem());
		Usuario usuario = null;

		senha = Criptografia.geraHash(Criptografia.MD5, senha, false);
		
		try {
			if (operacao == OperacaoUsuario.ESQUECI_SENHA) {
				usuario = usuarioDLO.finalizarEsqueciSenha(token, senha);
				
			} else if (operacao == OperacaoUsuario.CONFIRMAR_EMAIL) {
				usuario = usuarioDLO.confirmarEmailUsuario(token, senha);
			}
			
		} catch (DelphosException e) {
        	LOGGER.debug(null, e);	
		}
		
		if (usuario != null) {
			ActionMessage ok = new ActionMessage("msg.dados.alterados.sucesso");
			messages.add("dialogoSucesso", ok);
			this.addMessages(request, messages);
            retorno = mapping.findForward("login");
			
		} else {
			ActionMessage erroSenha = new ActionMessage(Mensagens.get(CodigoMensagem.Falha), false);
			messages.add("dialogoAlerta", erroSenha);
			this.addErrors(request, messages);
            retorno = mapping.getInputForward();
		}
		
		return retorno;
	}
}
