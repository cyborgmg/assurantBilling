package br.com.delphos.web.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.delphos.sca.servicos.ControleAcesso;
import br.com.delphos.util.ServiceLocator;

@WebFilter(filterName = "controleAcessoFiltro", urlPatterns = { "/*" })
public class ControleAcessoFiltro implements Filter {

	private ControleAcesso client = null;

	@Override
	public void init(FilterConfig filterConfig) {
		client = (ControleAcesso) ServiceLocator.lookup("java:global/sca-ear/sca-ejb/ControleAcessoBean");
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

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}