package br.com.delphos.web.seguranca;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.sca.servicos.ControleAcesso;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Validador;

public class LoginAction extends Action {
	
//	@EJB(lookup = "java:global/sca-ear/sca-ejb/ControleAcessoServiceBean")
//	private ControleAcessoService client;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		ActionForward retorno = new ActionForward();
		Locale.setDefault(new Locale("pt", "BR"));

		LoginActionForm loginActionForm = (LoginActionForm) form;
		if (loginActionForm != null) {

//			SCAClient client = new SCAClient();
			
//			java:global/sca-ear/sca-ejb/ControleAcessoServiceBean!br.com.delphos.sca.servicos.ControleAcessoService
//			java:app/sca-ejb/ControleAcessoServiceBean!br.com.delphos.sca.servicos.ControleAcessoService
//			java:module/ControleAcessoServiceBean!br.com.delphos.sca.servicos.ControleAcessoService
//			java:jboss/exported/sca-ear/sca-ejb/ControleAcessoServiceBean!br.com.delphos.sca.servicos.ControleAcessoService

//			ControleAcessoService client = (ControleAcessoService) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/ControleAcessoServiceBean");
			ControleAcesso client = (ControleAcesso) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/ControleAcessoBean");

			String usuario = loginActionForm.getUsuario().toUpperCase();
			String senha = loginActionForm.getSenha();

			try {
				senha = Criptografia.geraHash(Criptografia.MD5, senha, false);

			} catch (NoSuchAlgorithmException ex) {
			}

			Usuario usuarioObj = client.autenticar(usuario, senha);
			String uid = "";
			
			if (usuarioObj != null) {
				uid = String.valueOf(usuarioObj.getCodigo());
			}
			
			if (!Validador.vazio(uid)) {
				HttpSession session = request.getSession();

				session.setAttribute("corporativo_uid", uid);
				session.setAttribute("corporativo_empresa", request
						.getServletContext().getInitParameter("codEmpresa"));

				String codSistema = request.getServletContext()
						.getInitParameter("codSistema");
				session.setAttribute("corporativo_sistema", codSistema);

				String codSistemaPermissao = request.getServletContext()
						.getInitParameter("codSistemaPermissao");
				session.setAttribute("corporativo_sistema_permissao",
						codSistemaPermissao);

				String nomeUsuario = client.obterNomeUsuario(String.valueOf(usuarioObj.getId()));
				session.setAttribute("corporativo_usuario", nomeUsuario);

//				String token = client.sincronizar(uid);
				String token = client.sincronizar(uid, usuarioObj.getToken());
				
				session.setAttribute(
						"corporativo_token",
						token);
				
				String uri = (String) session.getAttribute("URI");

				if (!Validador.vazio(uri)) {
					uri = uri.split("/sca")[1];
					retorno = new ActionForward(uri, true);

				} else {
					retorno = mapping.findForward("inicio");
				}

			} else {
				ActionMessage erro = new ActionMessage("erro.login.invalido");
				messages.add("dialogoAlerta", erro);
				this.addMessages(request, messages);
				retorno = mapping.getInputForward();
			}
		}
		return retorno;
	}
}