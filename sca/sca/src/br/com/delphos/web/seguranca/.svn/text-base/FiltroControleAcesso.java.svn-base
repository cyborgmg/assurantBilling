package br.com.delphos.web.seguranca;

import java.io.IOException;
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

import br.com.delphos.sca.servicos.ControleAcesso;
import br.com.delphos.util.ServiceLocator;

@WebFilter(filterName = "filtroControleAcesso", urlPatterns = {"/*"}, 
			initParams = @WebInitParam(name = "enderecosLivres", value = "/css;/img;/js;/index.jsp;/dwr;/autocomplete;/pdf;/uploadify;/ajuda;/emailArquivo;"))
public class FiltroControleAcesso implements Filter {

    private FilterConfig filterConfig = null;
    private String[] enderecosLivres = null;
    private ControleAcesso client = null;

    @Override
    public void init(FilterConfig filterConfig) {

        Locale.setDefault(new Locale("pt", "BR"));
        
        try {

            this.filterConfig = filterConfig;
            String sEndLivres = filterConfig.getInitParameter("enderecosLivres");
            if (sEndLivres != null) {

                this.enderecosLivres = sEndLivres.split(";");
            }
//            client = new SCAClient();
            client = (ControleAcesso) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/ControleAcessoBean");
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	
    	if (client == null) {
    		init(this.filterConfig); // TODO VER DEPOIS PQ Q VEM NULO NESSE PONTO
    	}

        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse) response;
        HttpSession session = hrequest.getSession();

        boolean autorizado = false;

        String uid = (String) session.getAttribute("corporativo_uid");
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

            autorizado = client.autorizar(uid, token, URI);
            
        }
        
        String uriQueryString = hrequest.getQueryString();
        if (uriQueryString != null) {

            URI += "?" + hrequest.getQueryString();
        }
        
        if (autorizado) {

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