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

import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterfaceDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.sca.wsclient.Usuario;
import br.com.delphos.util.ServiceLocator;

/**
 * Servlet implementation class AutoCompleteServlet
 */
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final int LISTAR_USUARIOS = 1;
    private final int LISTAR_EMPRESAS_ASSOCIADAS = 2;
    private final int LISTAR_PRODUTOS = 3;
    private final int LISTAR_PROVEDORES = 4;
	
    private EmpresaDLO dloEmpresa;
	private ProdutoDLO dloProduto;
	private SistemaDLO  dloSistema;
	private VendaDLO dloVenda;
	private CobrancaDLO dloCobranca;
	private EventoDLO dloEvento;
	private ItemListaDLO dloItemLista;
	private LogOperacaoUsuarioDLO dloLogOperacaoUsuario;
	private ConciliacaoInterfaceDLO dloConciliacaoInterface;
	private ProvedorDLO dloProvedor;
	private SCAClient client = null;
       
    @Override
    public void init() {
        try {
        	
        	dloEmpresa = (EmpresaDLO) ServiceLocator
        			.lookup("java:global/dbsdb/EmpresaDLOBean");
        	
        	dloProduto = (ProdutoDLO) ServiceLocator
        			.lookup("java:global/dbsdb/ProdutoDLOBean");

        	dloVenda = (VendaDLO) ServiceLocator
        			.lookup("java:global/dbsdb/VendaDLOBean");
        	
        	dloCobranca = (CobrancaDLO) ServiceLocator
        			.lookup("java:global/dbsdb/CobrancaDLOBean");
        	
        	dloEvento = (EventoDLO) ServiceLocator
        			.lookup("java:global/dbsdb/EventoDLOBean");
        	
        	dloSistema   = (SistemaDLO) ServiceLocator
        			.lookup("java:global/dbsdb/SistemaDLOBean");
        	
        	 dloItemLista = (ItemListaDLO) ServiceLocator
        			.lookup("java:global/dbsdb/ItemListaDLOBean");
        	 
        	 dloLogOperacaoUsuario = (LogOperacaoUsuarioDLO) ServiceLocator
        			 .lookup("java:global/dbsdb/LogOperacaoUsuarioDLOBean");
        	 
        	  dloConciliacaoInterface =(ConciliacaoInterfaceDLO)
         			 ServiceLocator.lookup("java:global/dbsdb/ConciliacaoInterfaceDLOBean");
        	  
          	dloProvedor = (ProvedorDLO) ServiceLocator
        			.lookup("java:global/dbsdb/ProvedorDLOBean");
        	  
				client = new SCAClient();
        	  
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
                case LISTAR_EMPRESAS_ASSOCIADAS:
                	listarEmpresasAssociadas(request, response);
                	break;
                case LISTAR_PRODUTOS:
                	listarProdutos(request, response);
                	break;
                case LISTAR_PROVEDORES:
                	listarProvedores(request, response);
                	break;
            }
        }
    }
    
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String nomeParcialUsuario = request.getParameter("nomeUsuario");

        if (!Validador.vazio(nomeParcialUsuario)) {

            try {
            	
            	String retorno = "";
        		response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        
//				List<Usuario> listaUsuario = client.obterPorNomeLogin(nomeParcialUsuario); // TODO DESCOMENTAR DEPOIS
				List<Usuario> listaUsuario = client.listarUsuarios();
		        
		        for (Iterator iterator = listaUsuario.iterator(); iterator
						.hasNext();) {
					Usuario usuario = (Usuario) iterator.next();
					
			        if (usuario != null) {
			        	
	                    out.println(String.format("{"
	                            + "\"idUsuario\":\"%s\" , "
	                            + "\"nomeUsuario\"   :\"%s\" , "
	                            + "\"codigo\"   :\"%s\"}",
	                            usuario.getId().toString(),
	                            usuario.getDescricao(),
	                            usuario.getCodigo()));
			        	
			        }
					
				}
		        
/*        		List<Produto> listProdutos = new ArrayList<Produto>();

    			Empresa empresa = dloEmpresa.obterPorCodigo(codEmpresa);
    			empresa = dloEmpresa.completar(empresa, "produtos");

    			listProdutos = empresa.getProdutos();
    			
    			request.getSession().setAttribute("produtos_filtro_venda", listProdutos);

	            if (listProdutos != null && listProdutos.size() > 0) {

	            	retorno += "<option value='0'>- "+Mensagens.get("msg.selecione")+" -</option>";

	            	for (Produto produto : listProdutos) {

						retorno += "<option value='" + produto.getId() + "'>" + produto.getDescricao() + "</option>";
					}
	            }*/
	            
	            out.println(retorno);
	            out.close();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void listarEmpresasAssociadas (HttpServletRequest request, HttpServletResponse response)
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
    						
    						output.append(String.format("{\"value\":\"%s\", \"id\":\"%s\"},", empresa.getDescricao(), empresa.getCodigo()));
    					
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
    
    private void listarProdutos (HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String descProduto = request.getParameter("descProduto");
    	
    	HttpSession sessao = request.getSession();
    	
    	List <Produto> listProdutos = dloProduto.listarProdutosPorCriterio("", descProduto, "");
    	
    	if (!Validador.vazio(descProduto)) {
    		
    		try {
    			
    			String retorno = "";
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			StringBuilder output = new StringBuilder();
    			output.append("[");
    			for (Iterator iterator = listProdutos.iterator(); iterator
    					.hasNext();) {
    				Produto produto = (Produto) iterator.next();
    				
    				if (produto != null) {
    					
						output.append(String.format("{\"value\":\"%s\", \"id\":\"%s\"},", produto.getDescricao(), produto.getCodigo()));
    						
    				}
    				
    			}
    			out.println(output.toString().substring(0, output.length() - 1) + "]");
    			//out.println(retorno);
    			out.close();
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    private void listarProvedores (HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String descProvedor = request.getParameter("descProvedor");
    	
    	HttpSession sessao = request.getSession();
    	
    	List <Provedor> listProvedores = dloProvedor.listarPorCriterio("", descProvedor);
    	
    	if (!Validador.vazio(descProvedor)) {
    		
    		try {
    			
    			String retorno = "";
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			StringBuilder output = new StringBuilder();
    			output.append("[");
    			for (Iterator iterator = listProvedores.iterator(); iterator
    					.hasNext();) {
    				Provedor provedor = (Provedor) iterator.next();
    				
    				if (provedor != null) {
    					
    					output.append(String.format("{\"value\":\"%s\", \"id\":\"%s\"},", provedor.getDescricaoProvedor(), provedor.getCodigoProvedor()));
    					
    				}
    				
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
