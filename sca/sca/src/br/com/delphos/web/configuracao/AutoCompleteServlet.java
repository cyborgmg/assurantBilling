package br.com.delphos.web.configuracao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.sistemas.SistemaDLO;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Validador;

/**
 * Servlet implementation class AutoCompleteServlet
 */
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final int LISTAR_USUARIOS = 1;
    private final int LISTAR_EMPRESAS = 2;
	
    private EmpresaDLO dloEmpresa;
	private SistemaDLO  dloSistema;
	private UsuarioDLO  dloUsuario;
       
    @Override
    public void init() {
        try {
        	
        	dloEmpresa = (EmpresaDLO) ServiceLocator
        			.lookup("java:global/sca-ear/sca-ejb/EmpresaDLOBean");
        	
        	dloSistema   = (SistemaDLO) ServiceLocator
        			.lookup("java:global/sca-ear/sca-ejb/SistemaDLOBean");
        	
        	dloUsuario   = (UsuarioDLO) ServiceLocator
        			.lookup("java:global/sca-ear/sca-ejb/UsuarioDLOBean");
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sOp = request.getParameter("op");
        
        if (Validador.inteiro(sOp)) {
            
            int op = Integer.parseInt(sOp);
            switch (op) {
                case LISTAR_USUARIOS:
                	listarUsuarios(request, response);
                	break;
                case LISTAR_EMPRESAS:
                	listarEmpresas(request, response);
                	break;
            }
        }
    }
    
    
    private void listarUsuarios (HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String descUsuario = request.getParameter("descUsuario");
    	
    	HttpSession sessao = request.getSession();
    	
    	List<Usuario> listUsuario = new ArrayList<Usuario>();
    	
    	if (!Validador.vazio(descUsuario)) {
    		
    		try {
    			
    			listUsuario = dloUsuario.listarPorParteDescricao(descUsuario);
    			
    			String retorno = "";
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			StringBuilder output = new StringBuilder();
    			output.append("[");
    			for (Iterator iterator = listUsuario.iterator(); iterator
    					.hasNext();) {
    				Usuario usuario = (Usuario) iterator.next();
    				
    				output.append(String.format("{\"value\":\"%s\", \"id\":\"%s\"},", usuario.getDescricao(), usuario.getId().toString()));
    				
    				
    			}
    			out.println(output.toString().substring(0, output.length() - 1) + "]");
    			//out.println(retorno);
    			out.close();
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private void listarEmpresas (HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String descEmpresa = request.getParameter("descEmpresa");
    	
    	HttpSession sessao = request.getSession();
    	
//    	List<Empresa> listEmpresasAssociadas = new ArrayList<Empresa>();
//    	listEmpresasAssociadas.addAll((List<Empresa>)sessao.getAttribute("empresas_usuario_logado"));
    	
    	List<Empresa> listEmpresa = new ArrayList<Empresa>();
    	
    	if (!Validador.vazio(descEmpresa)) {
    		
    		try {
    			
        		listEmpresa = dloEmpresa.listarEmpresasPorCriterio("", descEmpresa);
    			
    			String retorno = "";
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			StringBuilder output = new StringBuilder();
    			output.append("[");
//    			for (Iterator iterator = ((List<Empresa>)sessao.getAttribute("empresas_usuario_logado")).iterator(); iterator
				for (Iterator iterator = listEmpresa.iterator(); iterator
    					.hasNext();) {
    				Empresa empresa = (Empresa) iterator.next();
//    				br.com.delphos.sca.wsclient.Empresa empresa = (br.com.delphos.sca.wsclient.Empresa) iterator.next();
    				
//    				if (empresa != null) {
//    					
//    					if (empresa.getDescricao().toUpperCase().contains(descEmpresa.toUpperCase())) {
    					
//    						output.append(
//	    							String.format("{"
//	    							+ "\"retorno\":\"%s\" , "
//	    							+ "\"idEmpresa\":\"%s\" , "
//	    							+ "\"codigo\"   :\"%s\" , "
//	    							+ "\"descricao\"   :\"%s\"},",
//	    							"OK",
//	    							empresa.getId().toString(),
//	    							empresa.getCodigo(),
//	    							empresa.getDescricao())
//	    							);
    						
    						output.append(String.format("{\"value\":\"%s\", \"id\":\"%s\"},", empresa.getDescricao(), empresa.getId().toString()));
    					
//    					}
//    					
//    				}
    				
    			}
    			out.println(output.toString().substring(0, output.length() - 1) + "]");
    			//out.println(retorno);
    			out.close();
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
