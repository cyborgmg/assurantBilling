package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.web.seguranca.Criptografia;

public class AlterarSenhaAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializaões gerais
		ActionMessages messages = new ActionMessages();
		ActionForward retorno = mapping.getInputForward();

		SCAClient client = new SCAClient();
		SenhaUsuarioActionForm suaf = (SenhaUsuarioActionForm) form;

		try {
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
				messages.add("dialogoSucesso", ok);
				this.addMessages(request, messages);
			} else {
				ActionMessage erroSenha = new ActionMessage("erro.senha.atual.incorreta");
				messages.add("dialogoAlerta", erroSenha);
				this.addErrors(request, messages);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String msg = DLOException.obterMensagemDLO(e);
			ActionMessage erro = null;
			if (msg != null) {
				erro = new ActionMessage(msg);
				// ALTERAR: CASO SEJA ENCONTRADA OUTRA SOLUCAO PARA MARCAR OS
				// CAMPOS DE VERMELHO
				messages.add("senha", new ActionMessage(""));
				messages.add("senhaNova", new ActionMessage(""));
				messages.add("senhaConfirmacao", new ActionMessage(""));
			} else {
				erro = new ActionMessage("erro.dados.nao.atualizados");
			}
			messages.add("dialogoAlerta", erro);
			this.addErrors(request, messages);

		}
		return retorno;

	}
}
