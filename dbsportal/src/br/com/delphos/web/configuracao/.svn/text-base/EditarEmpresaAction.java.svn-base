package br.com.delphos.web.configuracao;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.EntidadeExistenteDLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarEmpresaAction extends StrutsAction {
	
	private SCAClient client = null;
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("editarEmpresa");
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;
		
        String operacao = request.getParameter("operacao");
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
	    ProdutoDLO dloProduto = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		VendaDLO dloVenda = (VendaDLO) ServiceLocator.lookup("java:/global/dbsdb/VendaDLOBean");
		
	    Empresa empresa = new Empresa();
	    
	    client = new SCAClient();
		
	       if (uaf != null) {
	    	   
	            if ((request.getParameter("idPesquisa") != null && !(request.getParameter("idPesquisa").isEmpty()))
	            		&& (request.getParameter("idPesquisa").length() > 0)) {
	            	
	            	empresa = dloEmpresa.obter(Long.parseLong(request.getParameter("idPesquisa")));
	            	
	                List<Produto> listaProdutosAssociados = dloProduto.listarPorEmpresa(empresa.getCodigo());
	            	
	                StringBuffer sb = new StringBuffer();
	                for (Iterator iterator = listaProdutosAssociados.iterator(); iterator
	    					.hasNext();) {
	    				Produto produto = (Produto) iterator.next();
	    				sb.append("-").append(produto.getDescricao()).append("&#13;&#10;");
	    			}
	                session.setAttribute("produtosAssociados", sb.toString());
	            	
	            	uaf.setCodigoEmpresa(empresa.getCodigo());
	            	uaf.setDescricaoEmpresa(empresa.getDescricao());
	            	uaf.setIdEmpresa(empresa.getId().toString());
	            	
	            	uaf.setListaAtiva("true");
	            	
	            } else {
	        			
        			String codEmpresa  = uaf.getCodigoEmpresa();
        			String descricaoEmpresa   = uaf.getDescricaoEmpresa();
        			
        			Long usuarioId = client.obterPorNomeLogin(uid).getId(); 
        			
        			session.setAttribute("codigoEmpresa", codEmpresa);
        			session.setAttribute("descricaoEmpresa", descricaoEmpresa);
        			
        			LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();
        			
                    Empresa empresaConsultaPrevia = null;
        			
        	       if ("2".equalsIgnoreCase(operacao)) { // Inclusão / alteração
        	    	   
        	    	   retorno = mapping.findForward("listarEmpresa");
        	    	   
                       if (!uaf.getIdEmpresa().trim().isEmpty()) {
                    	   empresaConsultaPrevia = dloEmpresa.obter(Long.valueOf(uaf.getIdEmpresa()));
                       }

                       if (empresaConsultaPrevia == null) { // Inclusão
                    	   empresa.setCodigo(uaf.getCodigoEmpresa().toUpperCase());
                    	   empresa.setDescricao(uaf.getDescricaoEmpresa().toUpperCase());
                    	   
                    	   List<Empresa> listaEmpresa = dloEmpresa.listarEmpresasPorCriterio(uaf.getCodigoEmpresa().toUpperCase(), uaf.getDescricaoEmpresa().toUpperCase());
                    	   
                    	   if (listaEmpresa.isEmpty()) { 
                           
//	                        	   dloEmpresa.incluirEmpresa(empresa);
                    		   dloEmpresa.manter(empresa);
                        	   
                        	   empresa = dloEmpresa.obterPorCodigo(empresa.getCodigo());
                        	   
                        	   logOperacaoUsuario.setIdObjeto(String.valueOf(empresa.getId()));
                        	   logOperacaoUsuario.setDescricaoObjeto(empresa.getDescricao());
                        	  
                        	   logOperacaoUsuario.setUsuario(usuarioId);
                        	   logOperacaoUsuario.setDescricaoUsuario(descUser);
                        	   
                        	   logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO, Empresa.class,null,empresa);
                        	   
                        	   uaf.setIdEmpresa(String.valueOf(empresa.getId()));
                        	   
                        	   ActionMessage ok = new ActionMessage("msg.empresa.incluido.sucesso");
                               messages.add("dialogoSucesso", ok);
                               this.addMessages(request, messages);
                               
                               request.setAttribute("operacao", "inclusaoSucesso");
                               
                               uaf.setListaAtiva("true");
                           
                    	   } else {
                    		   
                    		   retorno = mapping.findForward("editarEmpresa");
                    		   
                    		   throw new EntidadeExistenteDLOException("erro.empresa.existente");
                    		   
                    	   }
                    	   
                       } else {
                    	   
                    	   Empresa empresaNova =new Empresa();
                    	   empresaNova.setId(empresaConsultaPrevia.getId());
                    	   empresaNova.setCodigo(uaf.getCodigoEmpresa().toUpperCase());
                    	   empresaNova.setDescricao(uaf.getDescricaoEmpresa().toUpperCase());
                    	   
                    	   Long contagemVendasParaEmpresa = dloVenda.contarVendasPorEmpresa(String.valueOf(empresaNova.getId()));
                    	   
                    	   if (contagemVendasParaEmpresa == 0) { // alteração (* desde que não haja venda cadastrada)
                    	      
//                        	   dloEmpresa.alterarEmpresa(empresaNova);
                        	   
//                        	   empresaNova =dloEmpresa.obterPorCodigo(empresaNova.getCodigo());
                        	   empresaNova =dloEmpresa.obter(dloEmpresa.manter(empresaNova));
                        	   
                        	   logOperacaoUsuario.setIdObjeto(String.valueOf(empresaNova.getId()));
                        	   logOperacaoUsuario.setDescricaoObjeto(empresaNova.getDescricao());
                        	  
                        	   logOperacaoUsuario.setUsuario(usuarioId);
                        	   logOperacaoUsuario.setDescricaoUsuario(descUser);
                        	   
                        	   logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO, Empresa.class,empresaConsultaPrevia,empresaNova);
                        	   
                        	   ActionMessage ok = new ActionMessage("msg.empresa.alterada.sucesso");
                               messages.add("dialogoSucesso", ok);
                               this.addMessages(request, messages);
                               
	                           	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
	                        	if (caf != null) {
	                        		BeanUtils.copyProperties(uaf, caf);
	                        	}
                               
                    	   } else {
	               				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.empresa");
	            				setMensagemAlerta(erro);
                    	   }
                    	   
                       }
        	    	   
        	       }
        	       
        	       if ("3".equalsIgnoreCase(operacao)) { // Exclusão
        	    	   
        	    	   retorno = mapping.findForward("listarEmpresa");
        	    	   
        	    	   empresaConsultaPrevia = dloEmpresa.obter(Long.valueOf(uaf.getIdEmpresa()));
        	    	   
        	    	   Long contagemVendasParaEmpresa = dloVenda.contarVendasPorEmpresa(String.valueOf(empresaConsultaPrevia.getId()));
                	   
                	   if (contagemVendasParaEmpresa == 0) {
                		   
            	    	   dloEmpresa.excluir(empresaConsultaPrevia);
            	    	   
                    	   logOperacaoUsuario.setIdObjeto(String.valueOf(empresaConsultaPrevia.getId()));
                    	   logOperacaoUsuario.setDescricaoObjeto(empresaConsultaPrevia.getDescricao());
                    	  
                    	   logOperacaoUsuario.setUsuario(usuarioId);
                    	   logOperacaoUsuario.setDescricaoUsuario(descUser);
                    	   
                    	   logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO, Empresa.class,empresaConsultaPrevia,null);
            	    	   
                    	   ActionMessage ok = new ActionMessage("msg.empresa.excluido.sucesso");
                           messages.add("dialogoSucesso", ok);
                           this.addMessages(request, messages);
                	   
                	   } else {
              				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.empresa");
              				setMensagemAlerta(erro);
                	   }
        	    	   
        	       }
	
	            }
	        }

		return retorno;
	}
	
}