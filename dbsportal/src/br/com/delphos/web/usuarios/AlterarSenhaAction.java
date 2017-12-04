package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.web.seguranca.Criptografia;

public class AlterarSenhaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.getInputForward();

		SCAClient client = new SCAClient();
		SenhaUsuarioActionForm suaf = (SenhaUsuarioActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
String descUser = (String) session.getAttribute("corporativo_usuario");
		String atual = suaf.getSenha();
		String nova = suaf.getSenhaNova();

		atual = Criptografia.geraHash(Criptografia.MD5, atual, false);
		nova = Criptografia.geraHash(Criptografia.MD5, nova, false);

		boolean bReposta = client.alterarSenha(uid, atual, nova);

		if (bReposta) {
			ActionMessage ok = new ActionMessage("msg.dados.alterados.sucesso");
			setMensagemSucesso(ok);

		} else {
			ActionMessage erroSenha = new ActionMessage("erro.senha.atual.incorreta");
			setMensagemAlerta(erroSenha);
		}

		return retorno;

	}
}
