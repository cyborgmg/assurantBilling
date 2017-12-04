package br.com.delphos.web.seguranca;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FiltroConfiguracao implements Filter {

	private FilterConfig filterConfig = null;
	private String[] diretoriosBloqueados = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		Locale.setDefault(new Locale("pt", "BR"));

		try {

			this.filterConfig = filterConfig;
			String sEndLivres = filterConfig
					.getInitParameter("diretoriosBloqueados");
			if (sEndLivres != null) {

				this.diretoriosBloqueados = sEndLivres.split(";");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpSession session = hrequest.getSession();

		boolean autorizado = true;

		String gestorSistema = (String) session
				.getAttribute("usuarioGestorSistema");

		String URI = hrequest.getRequestURI();
		String ctx = hrequest.getContextPath();

		if (gestorSistema != null) {

			if (gestorSistema.equalsIgnoreCase("N")) {
				for (int i = 0; i < diretoriosBloqueados.length; i++) {

					if (URI.startsWith(ctx + diretoriosBloqueados[i] + "/")) {
						autorizado = false;
						break;
					}
				}
			}
		}

		if (autorizado) {
			chain.doFilter(request, response);

		} else {
			RequestDispatcher rd = hrequest
					.getRequestDispatcher("/login/nautor.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}