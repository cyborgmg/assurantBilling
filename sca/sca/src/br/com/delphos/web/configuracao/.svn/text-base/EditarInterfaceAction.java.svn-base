package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import br.com.delphos.sca.gruposInterfaces.GrupoInterfaceDLO;
import br.com.delphos.sca.gruposInterfaces.Interface;
import br.com.delphos.sca.gruposInterfaces.InterfaceDLO;
import br.com.delphos.sca.gruposInterfaces.Interface_;
import br.com.delphos.sca.servicos.ControleAcesso;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarInterfaceAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
        ActionForward retorno = mapping.findForward("editarInterface");
		ConfiguracaoActionForm caf = (ConfiguracaoActionForm) form;
        
		String operacao = request.getParameter("operacao");
		
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		
		//request.setAttribute("escondeComboGrupos", null);
		request.removeAttribute("escondeComboGrupos");
		if(operacao!=null&&operacao.equals("exibirDetalhe"))
			request.setAttribute("escondeComboGrupos", "escondeComboGrupos");
		
		InterfaceDLO dloInterface = (InterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/InterfaceDLOBean");
		GrupoInterfaceDLO dloGrupoInterface = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
		
		Interface interfac = new Interface();
	    
		ControleAcesso client = (ControleAcesso) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/ControleAcessoBean");
		
	       if (caf != null) {
	    	   
	            if ((request.getParameter("idPesquisa") != null && !(request.getParameter("idPesquisa").isEmpty()))
	            		&& (request.getParameter("idPesquisa").length() > 0)) {
	            	

	            	interfac = dloInterface.obterPorId(Long.parseLong(request.getParameter("idPesquisa")));
	            	
	            	caf.setIdInterface(interfac.getId().toString());
	            	caf.setDescricaoInterface(interfac.getDescricao());
	            	
    	            List<GrupoInterface> listaGrupoInterfaceNaoAssociado = dloGrupoInterface.obterGrupoInterfaceSemAssociacaoComInterface(interfac.getId());
	            	interfac = dloInterface.completar(interfac, Interface_.gruposInterface.getName());
    	            listaGrupoInterfaceNaoAssociado.removeAll(interfac.getGruposInterface());
	            	
	            	session.setAttribute("gruposInterfaceNaoAssociados", listaGrupoInterfaceNaoAssociado);
	            	session.setAttribute("gruposInterfaceAssociados", interfac.getGruposInterface());
	            	
	            } else {
	        			
        			String idInterface  			= caf.getIdInterface();
        			String descricaoInterface   	= caf.getDescricaoInterface();
        			
        			Long usuarioId = client.obterPorNomeLogin(uid).getId(); 
        			
        			session.setAttribute("idInterface", idInterface);
        			session.setAttribute("descricaoInterface", descricaoInterface);
        			
        			session.setAttribute("gruposInterfaceNaoAssociados", dloGrupoInterface.listar());
	            	session.removeAttribute("gruposInterfaceAssociados");
        			
        			
                    Interface interfaceConsultaPrevia = null;
        			
        	       if ("2".equalsIgnoreCase(operacao)) { // Inclusão / alteração
        	    	   
        	    	   retorno = mapping.findForward("listarInterface");
        	    	   
                       if (!caf.getIdInterface().trim().isEmpty()) {
                    	   interfaceConsultaPrevia = dloInterface.obterPorId(Long.valueOf(caf.getIdInterface()));
                       }

                       if (interfaceConsultaPrevia == null) { // Inclusão
                    	   	
                    	   		boolean isIncluidoAssociado =false;
                    	   
//	                    	   	   GrupoInterface grupoInterface =null;
//	                    	
//	                    	   	   grupoInterface=  dloGrupoInterface.obterPorId(new Long(caf.getGrupoInterface()));
                    	   	   
                    	   	   interfac.setDescricao(caf.getDescricaoInterface().toUpperCase());
                    	     
/*	                    	   	    
		                    	   List<Interface> listaInterface = dloInterface.listarInterfacePorDescricao(caf.getDescricaoInterface().toUpperCase());
		                    	   
		                    	   if (!listaInterface.isEmpty()){
		                    		
		                    		   for (Interface interface1 : listaInterface) {
		                    		   
		                    			   if(interface1.getDescricao().equals(i.getDescricao().toUpperCase())){
		                    				   interface1=   dloInterface.completar(interface1, "gruposInterface");
		                    				   if(!dloGrupoInterface.isAssociado(grupoInterface, interface1)){
		                    					   
		                    					 //  if(dloInterface.obter(i)==null);
		                    					   //		i = dloInterface.obterPorId((dloInterface.incluir(i)));//não usar o manter() , pois o manter permite que se inclua 1 só vez , neste caso aqui NxN
		          	                    	   	 
		                    					   
		                    					   dloGrupoInterface.associar(grupoInterface,i);
		          	                    	   	
		                    					   isIncluidoAssociado = dloGrupoInterface.isAssociado(grupoInterface,i);
		          	                    	   	   break;
		                    				   }	 
		                    				   else{
		                    					   		System.out.println("!@#$%_nao incluido e nem associado---> "+i.getDescricao()+" - "+grupoInterface.getDescricao());
		                    				   		}
		                    		       }
		                    		   }
								   }
		                    		else{
		                    			//não usar o manter() , pois o manter permite que se inclua 1 só vez , neste caso aqui NxN
//		                    			   i = dloInterface.obterPorId((dloInterface.incluir(i)));
		                    			   i = dloInterface.obterPorId((dloInterface.manter(i))); // TODO VER DEPOIS
		                        		  dloGrupoInterface.associar(grupoInterface,i);
		                        		  isIncluidoAssociado = dloGrupoInterface.isAssociado(grupoInterface,i);
		                    		}
	                    	   	                        	  
			                        	  caf.setIdInterface(String.valueOf(i.getId()));
			                        	 
			                        	  //possível solução pois o campo perde o valor
			                        	  //caf.setGrupoInterface(grupoInterface.getId().toString());
			                        	  
			                        	  if(isIncluidoAssociado){
			                        		   ActionMessage ok = new ActionMessage("msg.interface.incluido.sucesso");
				                               messages.add("dialogoSucesso", ok);
				                               this.addMessages(request, messages);
				                               request.setAttribute("operacao", "inclusaoSucesso");  
			                        	  }else{
			                        			ActionMessage erro = new ActionMessage("erro.associacao.interface.grupo.interface.existente");
			            	        			messages.add("dialogoAlerta", erro);
			            	        			this.addErrors(request, messages);		
			                        	  }*/
                    	   	   
                        	   List<GrupoInterface> gruposInterfaceAssociados = new ArrayList<GrupoInterface>();
                        	   if (caf.getGruposInterfaceAssociados().isEmpty()) {
                        		   interfac.setGruposInterface((new ArrayList<GrupoInterface>()));
                        	   } else {
                        		   String[] gruposInterfaceAssociadosFromForm = caf.getGruposInterfaceAssociados().split(",");
                        		   for (int i = 0; i < gruposInterfaceAssociadosFromForm.length; i++) {
                        			   String string = gruposInterfaceAssociadosFromForm[i];
                        			   gruposInterfaceAssociados.add(dloGrupoInterface.obterPorId(Long.valueOf(string)));
                        		   }
                        		   interfac.setGruposInterface(gruposInterfaceAssociados);
                        	   }
                               
                        	   Interface interfaceNovo = dloInterface.obterPorId(dloInterface.manter(interfac));
                        	   
                        	   dloInterface.reassociarGrupoInterface(interfaceNovo, gruposInterfaceAssociados);
                        	   
                			   interfac = dloInterface.obterPorId((dloInterface.manter(interfac))); // TODO VER DEPOIS
                			   
                			   caf.setDescricaoInterface(interfac.getDescricao());
                			   caf.setNovaBusca("true");
                        	   
                    		   ActionMessage ok = new ActionMessage("msg.interface.incluido.sucesso");
                               messages.add("dialogoSucesso", ok);
                               this.addMessages(request, messages);
                               request.setAttribute("operacao", "inclusaoSucesso"); 
                        	   
                     	   
                       } else {
                    	   
                    	   Interface interfaceNova =new Interface();
                    	   interfaceNova.setId(interfaceConsultaPrevia.getId());
                    	   interfaceNova.setDescricao(caf.getDescricaoInterface().toUpperCase());
                    	   
                    	   List<GrupoInterface> gruposInterfaceAssociados = new ArrayList<GrupoInterface>();
                    	   if (caf.getGruposInterfaceAssociados().isEmpty()) {
                    		   interfaceNova.setGruposInterface((new ArrayList<GrupoInterface>()));
                    	   } else {
                    		   String[] gruposInterfaceAssociadosFromForm = caf.getGruposInterfaceAssociados().split(",");
                    		   for (int i = 0; i < gruposInterfaceAssociadosFromForm.length; i++) {
                    			   String string = gruposInterfaceAssociadosFromForm[i];
                    			   gruposInterfaceAssociados.add(dloGrupoInterface.obterPorId(Long.valueOf(string)));
                    		   }
                    		   interfaceNova.setGruposInterface(gruposInterfaceAssociados);
                    	   }
                    	   
                    	   dloInterface.manter(interfaceNova);
                    	   
                    	   caf.setNovaBusca("true");
                    	   
                    	   ActionMessage ok = new ActionMessage("msg.interface.alterada.sucesso");
                           messages.add("dialogoSucesso", ok);
                           this.addMessages(request, messages);
                    	   
                       }
        	    	   
        	       }
        	       
        	       if ("3".equalsIgnoreCase(operacao)) { // Exclusão
        	    	   
        	    	   retorno = mapping.findForward("listarInterface");
        	    	   
        	    	   interfaceConsultaPrevia = dloInterface.obterPorId(Long.valueOf(caf.getIdInterface()));
        	    	   interfaceConsultaPrevia= dloInterface.completar(interfaceConsultaPrevia, "gruposInterface");
        	    	   
        	    	   for (GrupoInterface grupoInterface :  interfaceConsultaPrevia.getGruposInterface()) 
        	    		   dloGrupoInterface.desassociar(grupoInterface, interfaceConsultaPrevia);   
        	    	   
        	    	   dloInterface.excluir(interfaceConsultaPrevia);
            	    	   
                    	   ActionMessage ok = new ActionMessage("msg.interface.excluido.sucesso");
                           messages.add("dialogoSucesso", ok);
                           this.addMessages(request, messages);
        	    	   
        	       }
	
	            }
	        }

		return retorno;
	}
	
}