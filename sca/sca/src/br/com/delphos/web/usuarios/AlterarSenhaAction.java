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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.web.seguranca.Criptografia;

public class AlterarSenhaAction extends Action {
	private final Logger LOGGER = LoggerFactory.getLogger(AlterarSenhaAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializaões gerais
		ActionMessages messages = new ActionMessages();
		ActionForward retorno = mapping.getInputForward();
       	UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:app/sca-ejb/UsuarioDLOBean");

		SenhaUsuarioActionForm suaf = (SenhaUsuarioActionForm) form;

		try {
			HttpSession session = request.getSession();
			String uid = (String) session.getAttribute("corporativo_uid");
			String atual = suaf.getSenha();
			String nova = suaf.getSenhaNova();

			atual = Criptografia.geraHash(Criptografia.MD5, atual, false);
			nova = Criptografia.geraHash(Criptografia.MD5, nova, false);
			Usuario usuario = new Usuario();
			usuario.setCodigo(uid);			
			
			boolean bResposta;
			
			try {
				usuarioDLO.alterarSenha(usuario, atual, nova);
				bResposta = true;
				
			} catch (DelphosException e) {
	        	LOGGER.debug(null, e);
				bResposta = false;
			}
			
			if (bResposta) {
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
			String msg = e.getLocalizedMessage();
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
