package br.com.delphos.web.filtros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.delphos.sca.wsclient.Empresa;
import br.com.delphos.sca.wsclient.SCAClient;

@WebFilter(filterName = "controleAcessoFiltro", urlPatterns = { "/*" })
public class ControleAcessoFiltro implements Filter {

	private SCAClient client = null;

	@Override
	public void init(FilterConfig filterConfig) {
		client = new SCAClient();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpSession sessao = hRequest.getSession();

		String uid = (String) sessao.getAttribute("corporativo_uid");
		String sCodigoSistema = sessao.getServletContext().getInitParameter(
				"codSistemaPermissao");
		String token = (String) sessao.getAttribute("corporativo_token");

		sessao.setAttribute("CodigoSistema", sCodigoSistema);

		// TODO VER POIS NESSA CLASSE O TOKEN QUANDO VEM INVÁLIDO NÃO RECUPERA EMPRESA ALGUMA
		// empresas_usuario_logado colocado em FiltroControleAcesso
/*		List<Empresa> empresas = new ArrayList<Empresa>();

		if (uid != null) {
			if (token != null) {

				empresas = client.obterEmpresasUsuario(uid, token);
				sessao.setAttribute("empresas_usuario_logado", empresas);
			}
		}*/

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}