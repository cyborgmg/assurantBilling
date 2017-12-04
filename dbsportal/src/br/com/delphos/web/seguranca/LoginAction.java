package br.com.delphos.web.seguranca;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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

import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.Empresa;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.sca.wsclient.Usuario;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ActionMessages messages = new ActionMessages();
		ActionForward retorno = new ActionForward();
		Locale.setDefault(new Locale("pt", "BR"));

		LoginActionForm loginActionForm = (LoginActionForm) form;
		if (loginActionForm != null) {

			SCAClient client = new SCAClient();

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

				String token = usuarioObj.getToken();
				
				session.setAttribute(
						"corporativo_token",
						token);
				
				List<Empresa> empresas = new ArrayList<Empresa>();

				if (uid != null) {
					if (token != null) {
						empresas = client.obterEmpresasUsuario(uid, token);
						session.setAttribute("empresas_usuario_logado", empresas);
					}
				}
				
				if (empresas.isEmpty()) {
					ActionMessage erro = new ActionMessage("erro.login.sem.empresa.associada");
					messages.add("dialogoAlerta", erro);
					this.addMessages(request, messages);
					retorno = mapping.getInputForward();	
					return retorno;
				}

				String uri = (String) session.getAttribute("URI");

				if (!Validador.vazio(uri)) {
					uri = uri.split("/dbsportal")[1];
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