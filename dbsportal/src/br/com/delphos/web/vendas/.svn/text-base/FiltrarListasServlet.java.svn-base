package br.com.delphos.web.vendas;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterfaceDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.StatusProcConciliacaoEvento;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.tentativas.Evento;
import br.com.delphos.billing.tentativas.EventoDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.web.logs.LogViewHelper;

public class FiltrarListasServlet extends HttpServlet {

    private final int LISTAR_PRODUTOS  = 1;
    private final int LISTAR_SISTEMAS  = 2;
    private final int LISTAR_COBRANCAS = 4;
    private final int LISTAR_EVENTOS   = 5;
    private final int LISTAR_VENDAS_CONCRETIZADAS = 6;
    private final int LISTAR_VENDAS_ESTORNADAS = 7;
    private final int LISTAR_LOGS   = 8;
    private final int LISTAR_OBJETOS   = 9;
    private final int LISTAR_COBRANCAS_NAO_CONCILIADAS = 10;
    private final int LISTAR_COBRANCAS_CONCILIADAS_INTERFACE = 11;
    private final int LISTAR_STATUS_COBRANCA_VENDAS_CONCRETIZADAS=12;
    private final int LISTAR_STATUS_COBRANCA_VENDAS_ESTORNADAS=13;
    private final int LISTAR_STATUS_COBRANCA_CONCILIACAO_INTERFACE=14;
    private final int LISTAR_STATUS_CONCILIACAO=15;
    private final int LISTAR_CONCILIACAO=16;
    
    private EmpresaDLO dloEmpresa;
	private ProdutoDLO dloProduto;
	private SistemaDLO  dloSistema;
	private VendaDLO dloVenda;
	private CobrancaDLO dloCobranca;
	private EventoDLO dloEvento;
	private ItemListaDLO dloItemLista;
	private LogOperacaoUsuarioDLO dloLogOperacaoUsuario;
	private ConciliacaoInterfaceDLO dloConciliacaoInterface;
	private ListaValorDLO listaValorDLO;
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
        	  
      		  listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Empresa getEmpresaFiltro(String empresaFiltro) throws DLOException {
		if(empresaFiltro!=null&&!empresaFiltro.isEmpty())
			return dloEmpresa.obterPorCodigo(empresaFiltro);
		else
			return null;
	}
	
	private Produto getProdutoFiltro(String produtoFiltro){
		if(produtoFiltro!=null&&!produtoFiltro.isEmpty())
			return dloProduto.obter(Long.parseLong(produtoFiltro));
		else
			return null;
	}
	
	private Sistema getSistemaFiltro(String sistemaFiltro){
		if(sistemaFiltro!=null&&!sistemaFiltro.isEmpty())
			return dloSistema.obter(Long.parseLong(sistemaFiltro));
		else
			return null;
	}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String sOp = request.getParameter("op");
        
        if (Validador.inteiro(sOp)) {
            
            int op = Integer.parseInt(sOp);
            switch (op) {
                case LISTAR_PRODUTOS:
                    listarProdutos(request, response);
                    break;
                case LISTAR_SISTEMAS:
                    listarSistemas(request, response);
                    break;
                case LISTAR_COBRANCAS:
                	listarCobrancas(request, response);
                	break;
                case LISTAR_EVENTOS:
                	listarEventos(request, response);
                	break;
                case LISTAR_VENDAS_CONCRETIZADAS:
                	countVendasConcretizadas(request, response);
                	break;	
                case LISTAR_VENDAS_ESTORNADAS:
                	countVendasEstornadas(request, response);
                	break;
                case LISTAR_COBRANCAS_NAO_CONCILIADAS:
                	countCobrancasNaoConciliadas(request, response);
                	break;	
                case LISTAR_LOGS:
                	detalharLog(request, response);
                	break;
                case LISTAR_OBJETOS:
                	retornaValoresEntidade(request, response);
                	break;
                case LISTAR_COBRANCAS_CONCILIADAS_INTERFACE:
                	countCobrancasConciliadasInterface(request, response);
                	break;
                case LISTAR_STATUS_COBRANCA_VENDAS_CONCRETIZADAS:
                	listarStatusCobrancaVendasConcretizadas(request, response);
                	break;	
                case LISTAR_STATUS_COBRANCA_VENDAS_ESTORNADAS:
                	listarStatusCobrancaVendasEstornadas(request, response);
                	break;	
                case LISTAR_STATUS_COBRANCA_CONCILIACAO_INTERFACE:
                	listarStatusCobrancaConciliacaoInterface(request, response);
                	break;
                case LISTAR_STATUS_CONCILIACAO:
                	listarStatusConciliacao(request, response);
                	break;	
                case LISTAR_CONCILIACAO:
                	countConciliacao(request, response);
                	break;
            }
        }
    }
      
    private void countConciliacao(HttpServletRequest request,HttpServletResponse response) {

		PrintWriter out = null;
		List<Cobranca>cobrancasConciliacao=null;
 
    	String   empresa    = request.getParameter   ("empresa");
        String   produto    = request.getParameter   ("produto");
        String   sistema    = request.getParameter   ("sistema");
        String   status     = request.getParameter   ("status");
        String   periodoDe  = request.getParameter   ("periodoDe");
        String   periodoAte = request.getParameter   ("periodoAte");
		
    	String retorno = "";
    	response.setContentType("text/html;charset=UTF-8");
    	try {
    		out = response.getWriter();
    	} catch (IOException e2) {
    		e2.printStackTrace();
    	}

		
		try {

			Locale.setDefault(new Locale("pt", "BR"));
			//filtro
			Cobranca cobrancaFiltro =new Cobranca();
			Venda    venda   =new Venda();
			cobrancaFiltro.setVenda(venda);


			Empresa e = null; 
			try {
				e =getEmpresaFiltro(empresa);
			} catch (DLOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if(e!=null)
				cobrancaFiltro.getVenda().setEmpresa(e);

			//produto
			Produto p = null;
			p=getProdutoFiltro(produto);

			if(p!=null)
				cobrancaFiltro.getVenda().setProduto(p);

			//sistema
			Sistema s =null;
			s= getSistemaFiltro(sistema);

			if(s!=null)
				cobrancaFiltro.getVenda().setSistema(s);


			SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
			sdf.setLenient(false);

			Date dataInicial=null;
			Date dataFinal=null;

			if(!"".equals(periodoDe)&&!"".equals(periodoAte)){
				dataFinal  = sdf.parse(periodoAte);
				dataInicial= sdf.parse(periodoDe);
			}

			ItemLista itemLista      =null;

			if(!status.equals("0")){

				ItemLista itemListaFiltro=new ItemLista();

				itemListaFiltro.setId(Long.parseLong(status));

				itemLista=	dloItemLista.obter(itemListaFiltro.getId());	

			}

			cobrancasConciliacao=	dloCobranca .listarCobrancasConciliacao(cobrancaFiltro, dataInicial, dataFinal, itemLista);

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		if(cobrancasConciliacao==null)
    		retorno ="0";
    	else
    		retorno=new Integer(cobrancasConciliacao.size()).toString();					

    	retorno=retorno.trim();

    	out.println(retorno);
    	out.close();
		
	}


	private void countCobrancasNaoConciliadas(HttpServletRequest request,
    		HttpServletResponse response) {
    	PrintWriter out = null;
    	String   empresa    = request.getParameter   ("empresa"   );
    	String   produto    = request.getParameter   ("produto"   );
    	String   sistema    = request.getParameter   ("sistema"   );
    	String   diasAtraso = request.getParameter   ("diasAtraso");

    	String retorno = "";
    	response.setContentType("text/html;charset=UTF-8");
    	try {
    		out = response.getWriter();
    	} catch (IOException e2) {
    		e2.printStackTrace();
    	}

    	List<Cobranca>cobrancasNaoConciliadas=null;

    	//filtro
    	Cobranca cobranca =new Cobranca();
    	Venda    venda   =new Venda();
    	cobranca.setVenda(venda);



    	Empresa e = null; 
    	try {
    		e =getEmpresaFiltro(empresa);
    	} catch (DLOException e1) {
    		e1.printStackTrace();
    	}


    	if(e!=null)
    		cobranca.getVenda().setEmpresa(e);

    	//produto
    	Produto p = null;
    	p=getProdutoFiltro(produto);

    	if(p!=null)
    		cobranca.getVenda().setProduto(p);

    	//sistema
    	Sistema s =null;
    	s= getSistemaFiltro(sistema);

    	if(s!=null)
    		cobranca.getVenda().setSistema(s);

    	try {
    		cobrancasNaoConciliadas=	dloCobranca.listarCobrancasNaoConciliadas(cobranca, Double.parseDouble(diasAtraso));
    	} catch (NumberFormatException e1) {
    		e1.printStackTrace();
    	} 

    	if(cobrancasNaoConciliadas==null)
    		retorno ="0";
    	else
    		retorno=new Integer(cobrancasNaoConciliadas.size()).toString();					

    	retorno=retorno.trim();

    	out.println(retorno);
    	out.close();

    }

	private void countVendasConcretizadas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String   empresa    = request.getParameter   ("empresa");
        String   produto    = request.getParameter   ("produto");
        String   sistema    = request.getParameter   ("sistema");
        String   status     = request.getParameter   ("status");
        String   periodoDe  = request.getParameter   ("periodoDe");
        String   periodoAte = request.getParameter   ("periodoAte");
        
            try {
            	
            	String retorno = "";
        		response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
        
		        List<Venda>vendasConcretizadas=null;

				//filtro
				Venda venda =new Venda();
				
				Empresa e = null; 
				 e =getEmpresaFiltro(empresa);
				 
				if(e!=null)
					venda.setEmpresa(e);
			
				//produto
				Produto p = null;
				p=getProdutoFiltro(produto);
				
				if(p!=null)
					venda.setProduto(p);

				//sistema
				Sistema s =null;
				s= getSistemaFiltro(sistema);
				
				if(s!=null)
					venda.setSistema(s);
			
							
				ItemLista itemLista      =null;
				
				if(!status.equals("0")){
					
					ItemLista itemListaFiltro=new ItemLista();
					
					itemListaFiltro.setId(Long.parseLong(status));
					
					itemLista=	dloItemLista.obter(itemListaFiltro.getId());	
				 	
				}
				
				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
				sdf.setLenient(false);

				Date dataInicial=null;
				Date dataFinal=null;
				
				if(!"".equals(periodoDe)&&!"".equals(periodoAte)){
					 dataInicial= sdf.parse(periodoDe); 
					 dataFinal  = sdf.parse(periodoAte);
				}
			
				vendasConcretizadas=	dloVenda .listarVendasConcretizadas(venda, dataInicial, dataFinal,itemLista);
				
				
				if(vendasConcretizadas==null)
					retorno ="0";
				else
					retorno=new Integer(vendasConcretizadas.size()).toString();					
	
					retorno=retorno.trim();
			
	            out.println(retorno);
	            out.close();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }      
    
    private void countVendasEstornadas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String   empresa    = request.getParameter   ("empresa");
        String   produto    = request.getParameter   ("produto");
        String   sistema    = request.getParameter   ("sistema");
        String   status     = request.getParameter   ("status");
        String   periodoDe  = request.getParameter   ("periodoDe");
        String   periodoAte = request.getParameter   ("periodoAte");
        
            try {
            	
            	String retorno = "";
        		response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
        
		        List<Venda>vendasEstornadas=null;

				//filtro
				Venda venda =new Venda();
				
				Empresa e = null; 
				 e =getEmpresaFiltro(empresa);
				 
				if(e!=null)
					venda.setEmpresa(e);
			
				//produto
				Produto p = null;
				p=getProdutoFiltro(produto);
				
				if(p!=null)
					venda.setProduto(p);

				//sistema
				Sistema s =null;
				s= getSistemaFiltro(sistema);
				
				if(s!=null)
					venda.setSistema(s);
				
				ItemLista itemLista      =null;
				
				if(!status.equals("0")){
					
					ItemLista itemListaFiltro=new ItemLista();
					
					itemListaFiltro.setId(Long.parseLong(status));
					
					itemLista=	dloItemLista.obter(itemListaFiltro.getId());	
				 	
				}
				
				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
				sdf.setLenient(false);

				Date dataInicial=null;
				Date dataFinal=null;
				
				if(!"".equals(periodoDe)&&!"".equals(periodoAte)){
					 dataInicial= sdf.parse(periodoDe); 
					 dataFinal  = sdf.parse(periodoAte);
				}
				
				vendasEstornadas=	dloVenda .listarVendasEstornadas(venda, dataInicial, dataFinal,itemLista);
		        
				if(vendasEstornadas==null)
					retorno ="0";
				else
					retorno=new Integer(vendasEstornadas.size()).toString();					
	
					retorno=retorno.trim();
			
	            out.println(retorno);
	            out.close();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }      
        
	private void countCobrancasConciliadasInterface(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    
	    	PrintWriter out = null;
	       
	    	String   empresa    = request.getParameter   ("empresa");
	        String   produto    = request.getParameter   ("produto");
	        String   sistema    = request.getParameter   ("sistema");
	        String   status     = request.getParameter   ("status");
	        String   periodoDe  = request.getParameter   ("periodoDe");
	        String   periodoAte = request.getParameter   ("periodoAte");
	        
	        Locale.setDefault(new Locale("pt", "BR"));

	        String retorno = "";
	    	response.setContentType("text/html;charset=UTF-8");
	    	try {
	    		out = response.getWriter();
	    	} catch (IOException e2) {
	    		e2.printStackTrace();
	    	}
	        
	    	//filtro
			Cobranca cobrancaFiltro =new Cobranca();
			Venda    venda   =new Venda();
			cobrancaFiltro.setVenda(venda);


			Empresa e = null; 
			try {
				e =getEmpresaFiltro(empresa);
			} catch (DLOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			if(e!=null)
				cobrancaFiltro.getVenda().setEmpresa(e);

			//produto
			Produto p = null;
			p=getProdutoFiltro(produto);

			if(p!=null)
				cobrancaFiltro.getVenda().setProduto(p);

			//sistema
			Sistema s =null;
			s= getSistemaFiltro(sistema);

			if(s!=null)
				cobrancaFiltro.getVenda().setSistema(s);

			
			
			SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
			sdf.setLenient(false);

			Date dataInicial=null;
			Date dataFinal=null;

			if(!"".equals(periodoDe)&&!"".equals(periodoAte)){
				try {
					dataInicial= sdf.parse(periodoDe);
					dataFinal  = sdf.parse(periodoAte);
				} catch (ParseException e1) {
					e1.printStackTrace();
				} 
			}
			
		
			ItemLista itemLista      =null;
			
			if(!Validador.vazio(status) && !status.equals("0")){
				
				ItemLista itemListaFiltro=new ItemLista();
				
				itemListaFiltro.setId(Long.parseLong(status));
				
				itemLista=	dloItemLista.obter(itemListaFiltro.getId());	
			 	
			}
			
			
			List<Cobranca>cobrancasConciliadasInterface=null;
			
				cobrancasConciliadasInterface=	dloCobranca .listarCobrancasConciliadasInterface(cobrancaFiltro, dataInicial, dataFinal, itemLista);
	        			retorno=new Integer(cobrancasConciliadasInterface.size()).toString();					
		
						retorno=retorno.trim();
				
		            out.println(retorno);
		            out.close();
	        }      
       
	private void listarStatusCobrancaVendasEstornadas(
				HttpServletRequest request, HttpServletResponse response) {
				
				String   status_selecionado =null;
	    	    
				String codListaValor = request.getParameter("codListaValor");

	    	   status_selecionado= (String) request.getSession().getAttribute("status_selecionado");

	    	   
	           if (!Validador.vazio(codListaValor)) {

	               try {
	               	
	               	String retorno = "";
	           		response.setContentType("text/html;charset=UTF-8");
	   		        PrintWriter out = response.getWriter();
	           		
	   		        List<ItemLista> listItemLista = new ArrayList<ItemLista>();

	   		        listItemLista= dloItemLista.listarItemListaPorListaValor(codListaValor);
	       		       			
	       			request.getSession().setAttribute("lista_item_lista", listItemLista);

	   	            if (listItemLista != null && listItemLista.size() > 0) {
	   	            	retorno += "<option value='0'> "+Mensagens.get("msg.todos")+" </option>";

	   	            	for (ItemLista itemLista : listItemLista)
	   	            		if(itemLista.getCodigo().equals(StatusCobranca.EstornoConciliado.getValor())
	   	            		||
	   	            		itemLista.getCodigo().equals(StatusCobranca.Estornada.getValor())
	   	            		||
	   	            		itemLista.getCodigo().equals(StatusCobranca.EstornoSolicitado.getValor())
	   	            		||
	   	            		itemLista.getCodigo().equals(StatusCobranca.EstornoNegado.getValor()))
	   	            		
	   	            			
	   	            			if(itemLista.getId().toString().equals(status_selecionado))
	   	            				retorno += "<option selected value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
	   	            			else
	   	            				retorno += "<option  value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
	   	            	
	   	            }
	   	            status_selecionado= null;
	   	    	    request.getSession().removeAttribute("status_selecionado");
	   	           
	   	    	    out.println(retorno);
	   	            out.close();
	       			
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	           }
		}

	private void listarStatusCobrancaConciliacaoInterface(
			HttpServletRequest request, HttpServletResponse response) {
		   
		   String   status_selecionado =null;

    	   String codListaValor = request.getParameter("codListaValor");

    	   status_selecionado= (String) request.getSession().getAttribute("status_selecionado");
    	   
           if (!Validador.vazio(codListaValor)) {

               try {
               	
               	String retorno = "";
           		response.setContentType("text/html;charset=UTF-8");
   		        PrintWriter out = response.getWriter();
           		
   		        List<ItemLista> listItemLista = new ArrayList<ItemLista>();

   		        listItemLista= dloItemLista.listarItemListaPorListaValor(codListaValor);
       		       			
       			request.getSession().setAttribute("lista_item_lista", listItemLista);

   	            if (listItemLista != null && listItemLista.size() > 0) {
   	            	retorno += "<option value='0'> "+Mensagens.get("msg.todos")+" </option>";

   	            	for (ItemLista itemLista : listItemLista){
   	            		if(itemLista.getCodigo().equals(StatusConciliacaoInterface.ATIVA.getValor())
   	   	            		   || itemLista.getCodigo().equals(StatusConciliacaoInterface.ANULADA.getValor())){
   	            		
   	   	            			if(itemLista.getId().toString().equals(status_selecionado))
   	   	            				retorno += "<option selected value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
   	   	            			else
   	   	            				retorno += "<option  value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
   	            			
   	            		}
   	   	         	}
   	            		
   	            				
   	            }
   	            status_selecionado= null;
 	    	    request.getSession().removeAttribute("status_selecionado");
   	           
 	    	    out.println(retorno);
   	            out.close();
       			
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
	}
	
	private void listarStatusCobrancaVendasConcretizadas(
			HttpServletRequest request, HttpServletResponse response) {
		String   status_selecionado =null;
    	   String codListaValor = request.getParameter("codListaValor");

    	   status_selecionado= (String) request.getSession().getAttribute("status_selecionado");
    	   
           if (!Validador.vazio(codListaValor)) {

               try {
               	
               	String retorno = "";
           		response.setContentType("text/html;charset=UTF-8");
   		        PrintWriter out = response.getWriter();
           		
   		        List<ItemLista> listItemLista = new ArrayList<ItemLista>();

   		        listItemLista= dloItemLista.listarItemListaPorListaValor(codListaValor);
       		       			
       			request.getSession().setAttribute("lista_item_lista", listItemLista);

   	            if (listItemLista != null && listItemLista.size() > 0) {
   	            	retorno += "<option value='0'> "+Mensagens.get("msg.todos")+" </option>";
   	            
   	            	for (ItemLista itemLista : listItemLista)
   	            		if(itemLista.getCodigo().equals(StatusCobranca.Capturada.getValor())||itemLista.getCodigo().equals(StatusCobranca.NaoAutorizada.getValor()))
   	            			if(itemLista.getId().toString().equals(status_selecionado))
   	            				retorno += "<option selected value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
   	            			else
   	            				retorno += "<option  value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
   	            }
   	            
   	            
   	    	   status_selecionado= null;
   	    	   request.getSession().removeAttribute("status_selecionado");

   	            
   	            out.println(retorno);
   	            out.close();
       			
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
		
	}
	
	private void listarStatusConciliacao(HttpServletRequest request, HttpServletResponse response) {
		
		String   status_selecionado =null;
		
		String codListaValor = request.getParameter("codListaValor");

 	    status_selecionado= (String) request.getSession().getAttribute("status_selecionado");

		
         if (!Validador.vazio(codListaValor)) {

             try {
             	
             	String retorno = "";
         		response.setContentType("text/html;charset=UTF-8");
 		        PrintWriter out = response.getWriter();
         		
 		        List<ItemLista> listItemLista = new ArrayList<ItemLista>();

 		        listItemLista= dloItemLista.listarItemListaPorListaValor(codListaValor);
     		       			
     			request.getSession().setAttribute("lista_item_lista", listItemLista);

 	            if (listItemLista != null && listItemLista.size() > 0) {
 	            	retorno += "<option value='0'> "+Mensagens.get("msg.todos")+" </option>";

 	            	for (ItemLista itemLista : listItemLista){
 	            		
 	            		if(itemLista.getCodigo().equals(StatusProcConciliacaoEvento.ATIVO.getValor())
 	 	            		   || itemLista.getCodigo().equals(StatusProcConciliacaoEvento.REJEITADO.getValor())
 	 	            		   || itemLista.getCodigo().equals(StatusProcConciliacaoEvento.ANULADO.getValor())){
 	            			
 	            			if(itemLista.getId().toString().equals(status_selecionado))
	   	            				retorno += "<option selected value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
	   	            			else
	   	            				retorno += "<option  value='" + itemLista.getId() + "'>" + itemLista.getDescricao() + "</option>";
 	            		}
 	 	           }
 	            		 	            
 	            }
 	            status_selecionado= null;
   	    	    request.getSession().removeAttribute("status_selecionado");
 	            
   	    	    out.println(retorno);
 	            out.close();
     			
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
	}
		
    private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codEmpresa = request.getParameter("cod");
    	String tela = request.getParameter("tela");

        if (!Validador.vazio(codEmpresa)) {

            try {
            	
            	String retorno = "";
        		response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
        		List<Produto> listProdutos = new ArrayList<Produto>();

    			Empresa empresa = dloEmpresa.obterPorCodigo(codEmpresa);
    			empresa = dloEmpresa.completar(empresa, "produtos");

    			listProdutos = empresa.getProdutos();
    			
    			request.getSession().setAttribute("produtos_filtro_venda", listProdutos);

	            if (listProdutos != null && listProdutos.size() > 0) {

	            	if (tela == null || !tela.equals("contratoCobranca")) {
	            		retorno += "<option value='0'>- "+Mensagens.get("msg.selecione")+" -</option>";
	            	}

	            	for (Produto produto : listProdutos) {

						retorno += "<option value='" + produto.getId() + "'>" + produto.getDescricao() + "</option>";
					}
	            }
	            
	            out.println(retorno);
	            out.close();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void listarSistemas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String sIdProduto = request.getParameter("id");
    	
	    if(!Validador.vazio(sIdProduto)){
	    	
	        long idProduto = Long.parseLong(sIdProduto);
	
	        if (idProduto > 0) {
	
	            try {
	
	            	String retorno = "";
	        		response.setContentType("text/html;charset=UTF-8");
			        PrintWriter out = response.getWriter();
	        		List<Sistema> listSistemas = new ArrayList<Sistema>();
	
	        		Produto produto = dloProduto.obter(idProduto);
	        		produto = dloProduto.completar(produto, "sistemas");
	
	        		//listSistemas = produto.getSistemas();
	        		listSistemas = dloSistema.listar();
	        		
	        		request.getSession().setAttribute("produtos_filtro_sistema", listSistemas);
	
		            if (listSistemas != null && listSistemas.size() > 0) {
	
		            	retorno += "<option value='0'>- "+Mensagens.get("msg.selecione")+" -</option>";
	
		            	for (Sistema sistema : listSistemas) {
		            		
							retorno += "<option value='" + sistema.getId() + "'>" + sistema.getDescricao() + "</option>";
						}
		            }
		            
		            out.println(retorno);
		            out.close();
	    			
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
    	}
    }
    
    private void listarCobrancas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idVenda = request.getParameter("id");

        if (!Validador.vazio(idVenda)) {

            try {

            	String retorno = "";
        		response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        
		        VendasActionForm vaf = (VendasActionForm) request.getSession().getAttribute("vaf");
		        
            	List<Cobranca> listCobrancas = new ArrayList<Cobranca>();
		        listCobrancas = dloCobranca.listarCobrancasPorVenda(Long.parseLong(idVenda));
        		
	            if (listCobrancas != null && listCobrancas.size() > 0) {
	            	for (Cobranca cobranca : listCobrancas) {
	            		String numParcela = "";
	            		
	            		if(cobranca.getNumeroParcela() > 0){
	        				MaskFormatter numeroParcela = new MaskFormatter("##");
	    	                JFormattedTextField txtNumeroParcela = new JFormattedTextField(numeroParcela);
	    	                txtNumeroParcela.setText(String.valueOf(cobranca.getNumeroParcela()));
	    	                numParcela = txtNumeroParcela.getText().trim();
	        			}
	            		
	            		ItemLista itemLista=dloItemLista.obterPorCodigo(cobranca.getStatusCobranca().getValor(), TipoListaValor.Status.getValor());
	            		
	            		if(Integer.parseInt(vaf.getIdCobranca()) == cobranca.getId()){
	            			retorno += "<option selected value='" + cobranca.getId() + "'> " + String.format("%02d", Integer.valueOf(numParcela).intValue()) + " - " + Data.formatar(cobranca.getDataCobranca(), Data.MASCARA_SEM_HORA) + " - " + itemLista.getDescricao().toUpperCase() + "</option>";
	            		} else {
	            			retorno += "<option value='" + cobranca.getId() + "'> " + String.format("%02d", Integer.valueOf(numParcela).intValue()) + " - " + Data.formatar(cobranca.getDataCobranca(), Data.MASCARA_SEM_HORA) + " - " + itemLista.getDescricao().toUpperCase() + "</option>";
	            		}
					}
	            }

	            out.println(retorno);
	            out.close();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    	
    }
    
    private void listarEventos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idTentativa = request.getParameter("cod");

        if (!Validador.vazio(idTentativa)) {

            try {

            	String retorno = "";
        		response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
        		List<Evento> listEventos = new ArrayList<Evento>();
        		ItemLista itemLista = null;

        		listEventos = dloEvento.listarEventosPorTentativa(Long.valueOf(idTentativa));

	            if (listEventos != null && listEventos.size() > 0) {

	            	for (Evento evento : listEventos) {
	            		itemLista = dloItemLista.obterPorCodigo(evento.getTipoEvento().getValor(), "TPEVT");
	            		retorno += "<tr class='odd' style='background-color: rgb(255, 255, 255);'>";	            		
	            		retorno += "<td style='text-align:center; width:20%;'>"+Data.formatar(evento.getDataEvento(), Data.MASCARA_COM_HORA)+"</td>";
	            		retorno += "<td style='text-align:left; width:50%;'>"+itemLista.getDescricao()+"</td>";
	            		retorno += "<td style='text-align:left; width:30%; word-break: break-word;'>"+evento.getComplemento()+"</td>";
	    	            retorno += "</tr>";
					}
	            }
		
	            out.println(retorno);
	            out.close();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    	
    }
    
    private String buscarPorValor(List<ItemLista> listItemLista, String valor) {
    
		for (Iterator iterator = listItemLista.iterator(); iterator
				.hasNext();) {
			ItemLista itemLista = (ItemLista) iterator.next();
			
			if (itemLista.getCodigo().equals(valor)) {
				return itemLista.getDescricao();
			}
			
		}
		return "";
	
    }
    
    private void detalharLog(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String idLog = request.getParameter("cod");
    	
    	if (!Validador.vazio(idLog)) {
    		
    		try {
    			
    			String retorno = "";
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
//    			List<Evento> listEventos = new ArrayList<Evento>();
//    			ItemLista itemLista = null;
    			
    			LogOperacaoUsuario logOperacaoUsuario = dloLogOperacaoUsuario.obter(Long.valueOf(idLog));
    			
//                <display:table name="${LogVH.detalheLog()}" decorator="br.com.delphos.web.logs.LogDecorator" requestURI="">
//                <display:column property="dataOperacao" titleKey="label.dataOperacao" style="text-align:center; width:10%;" />
//                <display:column property="usuario" titleKey="label.usuario" style="text-align:left; width:30%;" />
//                <display:column property="tipoOperacao" titleKey="label.tipo.operacao" style="text-align:left; width:15%; word-break: break-word;" />
//                <display:column property="tipoObjetoOperacao" titleKey="label.tipo.objeto.operacao" style="text-align:left; width:15%; word-break: break-word;" />
//                <display:column property="identificadorObjetoOperacao" titleKey="label.identificadorObjetoOperacao" style="text-align:left; width:15%; word-break: break-word;" />
//            </display:table>
//            <display:table name="${LogVH.detalheLog()}" decorator="br.com.delphos.web.logs.LogDecorator" requestURI="">
//            	<display:column property="complementoOperacao" titleKey="label.complemento.operacao" style="text-align:left; width:15%; word-break: break-word;" />
//            </display:table>

    			
    			if (logOperacaoUsuario != null) {
    				
    				client = new SCAClient();
    				String usuario = client.obterNomeUsuario(logOperacaoUsuario.getUsuario().toString());
    				LogViewHelper logvh = new LogViewHelper();
    				String identificadorObjetoOperacao = "";
    				
    				ListaValor listaValorTipoOperacao = listaValorDLO.obterPorCodigo("TPOP");
//    				ListaValor listaValorTipoOperacao = listaValorDLO.obterPorCodigo(TipoListaValor.Operacao.getValor());
    				
    				List<ItemLista> listItemLista = listaValorTipoOperacao.getItensLista();
    				
					retorno += "<tr class='odd' style='background-color: rgb(255, 255, 255);'>";	            		
					retorno += "<td style='text-align:center; width:10%;'>"+Data.formatar(logOperacaoUsuario.getData(), Data.MASCARA_COM_HORA)+"</td>";
					retorno += "<td style='text-align:left; width:30%;'>"+usuario+"</td>";
					retorno += "<td style='text-align:left; width:15%; word-break: break-word;'>"+buscarPorValor(listItemLista, logOperacaoUsuario.getOperacao())+"</td>";
					retorno += "<td style='text-align:left; width:15%; word-break: break-word;'>"+logOperacaoUsuario.getTabela()+"</td>";
					
			    	if (logOperacaoUsuario.getIdObjeto() != null && !Validador.vazio(logOperacaoUsuario.getIdObjeto().toString())) {
			        	
			    		//identificadorObjetoOperacao = logvh.recuperaDescricaoIdentificadorObjetoOperacao(logOperacaoUsuario.getTabela(), logOperacaoUsuario.getIdObjeto().toString());
			    		identificadorObjetoOperacao = logOperacaoUsuario.getDescricaoObjeto();
			    		
			    	} else {
			    		
			    		identificadorObjetoOperacao = "";
			    		
			    	}
			    	retorno += "<td style='text-align:left; width:15%; word-break: break-word;'>"+identificadorObjetoOperacao+"</td>";
					
					retorno += "</tr>";
					
					retorno += "<tr style='background: rgb(249, 249, 249) none repeat scroll 0% 0%;'>";
					retorno += "<th class='ui-widget-header ui-corner-all' colspan='5'>"+Mensagens.get("label.complemento.operacao")+"</th>";
					retorno += "</tr>";
					
					retorno += "<tr class='odd' style='background-color: rgb(255, 255, 255);'>";
					retorno += "<td style='text-align:left; width:15%; word-break: break-word;' colspan='5'>"+logOperacaoUsuario.getComplemento()+"</td>";
					retorno += "</tr>";
    			}
    			
    			out.println(retorno);
    			out.close();
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}    	
    }
    
    public void retornaValoresEntidade(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	
    	String nomeEntidade = request.getParameter("objeto");
    	String retorno = "";
    	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
    	
		LogViewHelper lvh = new LogViewHelper();
		retorno = lvh.preparaOptionsEntidade(nomeEntidade);
    	
		out.println(retorno);
		out.close();
    	
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