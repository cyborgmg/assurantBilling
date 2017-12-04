package br.com.delphos.web.seguranca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.delphos.sca.wsclient.Empresa;
import br.com.delphos.sca.wsclient.SCAClient;

@WebFilter(filterName = "filtroControleAcesso", urlPatterns = {"/*"}, 
			initParams = @WebInitParam(name = "enderecosLivres", value = "/css;/img;/js;/index.jsp;/dwr;/autocomplete;/pdf;/uploadify;/ajuda;/emailArquivo;/Assurant_DBS_Guia_Utilizacao.pdf"))
public class FiltroControleAcesso implements Filter {

    private FilterConfig filterConfig = null;
    private String[] enderecosLivres = null;
    private SCAClient client = null;

    @Override
    public void init(FilterConfig filterConfig) {

        Locale.setDefault(new Locale("pt", "BR"));
        
        try {

            this.filterConfig = filterConfig;
            String sEndLivres = filterConfig.getInitParameter("enderecosLivres");
            if (sEndLivres != null) {

                this.enderecosLivres = sEndLivres.split(";");
            }
            client = new SCAClient();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse) response;
        HttpSession session = hrequest.getSession();

        boolean autorizado = false;

        String uid = (String) session.getAttribute("corporativo_uid");
String descUser = (String) session.getAttribute("corporativo_usuario");
        String token = (String) session.getAttribute("corporativo_token");
        String usr = (String) session.getAttribute("corporativo_usuario");

        if (uid == null) {

            usr = null;
            limpaSessao(session);
        }
        String URI = hrequest.getRequestURI();
        String URIUpp = hrequest.getRequestURI().toUpperCase();
        String ctx = hrequest.getContextPath();
        for (int i = 0; i < enderecosLivres.length; i++) {

            if (URI.startsWith(ctx + enderecosLivres[i] + "/")
                    || URI.equals(ctx + "/")
                    || URI.startsWith(ctx + "/login")
                    || URIUpp.endsWith("ICO")
                    || URIUpp.endsWith("GIF")
                    || URIUpp.endsWith("JPG")
                    || URIUpp.endsWith("PNG")) {

                autorizado = true;
                break;
            }
        }
        
        if (usr != null && uid != null && token != null && !autorizado) {
        	token = client.sincronizar(uid, token);
        	
        	if (token == null) {
        		session.invalidate();
        		autorizado = false;
        		
        	} else {
        		session.setAttribute("corporativo_token", token);
        		autorizado = client.autorizar(uid, token, URI);
        	}
        }
        
        String uriQueryString = hrequest.getQueryString();
        if (uriQueryString != null) {

            URI += "?" + hrequest.getQueryString();
        }
        
        if (autorizado) {
        	
/*    		List<Empresa> empresas = new ArrayList<Empresa>();

    		if (uid != null) {
    			if (token != null) {

    				empresas = client.obterEmpresasUsuario(uid, token);
    				session.setAttribute("empresas_usuario_logado", empresas);
    			}
    		}*/

            chain.doFilter(request, response);
        } else if (!autorizado && uid != null) {

            RequestDispatcher rd = hrequest.getRequestDispatcher("/login/nautor.jsp");
            rd.forward(request, response);

        } else {
            limpaSessao(session);
            session.setAttribute("msg", "login");
            session.setAttribute("URI", URI);
            hresponse.sendRedirect(ctx + "/login/login.jsp");
        }
    }

    private void limpaSessao(HttpSession session) {

        session.removeAttribute("corporativo_uid");
        session.removeAttribute("corporativo_token");
        session.removeAttribute("corporativo_usuario");
        session.removeAttribute("corporativo_empresa");
    }

    public FilterConfig getFilterConfig() {

        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {

        this.filterConfig = filterConfig;
    }

    @Override
    public String toString() {

        if (filterConfig == null) {

            return ("FiltroControleAcesso()");
        }
        StringBuilder sb = new StringBuilder("FiltroControleAcesso(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    @Override
    public void destroy() {
        //deixado propositalmente em branco
    }
}