package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.sca.wsclient.OperacaoUsuario;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.sca.wsclient.Usuario;
import br.com.delphos.web.seguranca.Criptografia;

public class ReiniciarSenhaAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializaões gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("reiniciarSenha");

		SCAClient client = new SCAClient();
		ReiniciarSenhaActionForm rsaf = (ReiniciarSenhaActionForm) form;

//		HttpSession session = request.getSession();
		String senha = rsaf.getSenhaNova();
		String token = rsaf.getToken();
		OperacaoUsuario operacao = OperacaoUsuario.buscarPorValor(rsaf.getOrigem());
		Usuario usuario = null;

		senha = Criptografia.geraHash(Criptografia.MD5, senha, false);
		
		if (operacao == OperacaoUsuario.ESQUECI_SENHA) {
			usuario = client.finalizarEsqueciSenha(token, senha);
			
		} else if (operacao == OperacaoUsuario.CONFIRMAR_EMAIL) {
			usuario = client.confirmarEmailUsuario(token, senha);
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
