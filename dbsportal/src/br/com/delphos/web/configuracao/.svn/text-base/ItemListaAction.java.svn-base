package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class ItemListaAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		ItemListaDLO itemListaDLO = (ItemListaDLO) ServiceLocator.lookup("java:/global/dbsdb/ItemListaDLOBean");
		ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");

		if (vaf.getIdListaValor() != null && !vaf.getIdListaValor().trim().isEmpty()) {
			ListaValor lv = listaValorDLO.obter(Long.valueOf(vaf.getIdListaValor()));
			vaf.setDescricaoListaValorItemLista(lv.getDescricao());
		}
		
		if (vaf.getOpcao().equals("associarItemLista") || vaf.getOpcao().equals("associarItemListaPrimeiroAcesso")) {
			
			if (vaf.getOpcao().equals("associarItemListaPrimeiroAcesso")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("associarItemLista");

			ItemLista itemLista = new ItemLista();
			ItemLista itemListaConsultaPrevia = null;
			if (!vaf.getIdItemLista().trim().isEmpty()) {
				itemListaConsultaPrevia = itemListaDLO.obter(Long.valueOf(vaf.getIdItemLista()));
			}

			if (itemListaConsultaPrevia == null) {
				itemLista.setCodigo(vaf.getCodigoItemLista().toUpperCase());
				itemLista.setListaValor(listaValorDLO.obter(Long.valueOf(vaf.getIdListaValorItemLista())));
				itemLista.setDescricao(vaf.getDescricaoItemLista().toUpperCase());
				itemLista.setStatus(vaf.getStatusItemLista());

				ItemLista itemListaNovo = itemListaDLO.obter((Long) itemListaDLO.manter(itemLista));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(itemListaNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(itemListaNovo.getDescricao());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						ItemLista.class, null, itemListaNovo);

				ActionMessage ok = new ActionMessage("msg.itemLista.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setNovaBusca(null);

			} else {
				ItemLista itemListaNovo = new ItemLista();
				itemListaNovo.setId(itemListaConsultaPrevia.getId());
				itemListaNovo.setCodigo(vaf.getCodigoItemLista().toUpperCase());
				//itemListaNovo.setListaValor(listaValorDLO.obter(Long.valueOf(vaf.getIdListaValorItemLista()))); 
				itemListaNovo.setListaValor(listaValorDLO.obter(Long.valueOf(itemListaConsultaPrevia.getListaValor().getId())));
																												// NÃO
																												// TERÁ
																												// ALTERAÇÃO
				itemListaNovo.setDescricao(vaf.getDescricaoItemLista().toUpperCase());
				itemListaNovo.setStatus(vaf.getStatusItemLista());

				itemListaNovo = itemListaDLO.obter(itemListaDLO.manter(itemListaNovo));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(itemListaNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(itemListaNovo.getDescricao());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
						ItemLista.class, itemListaConsultaPrevia, itemListaNovo);

				// REMOVENDO OUTROS FILTROS DO FORM BEAN PARA VOLTAR
				// CORRETAMENTE AO JSP DE ASSOCIAR ITEM LISTA
				vaf = new ConfiguracaoActionForm();
				vaf.setIdListaValorItemLista(String.valueOf(itemListaConsultaPrevia.getListaValor().getId()));

				ActionMessage ok = new ActionMessage("msg.itemLista.alterado.sucesso");
				setMensagemSucesso(ok);

			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("associarItemLista");

			ItemLista itemListaConsultaPrevia = itemListaDLO.obter(Long.valueOf(vaf.getIdItemLista()));

			itemListaDLO.excluir(itemListaConsultaPrevia);

      	   logOperacaoUsuario.setIdObjeto(String.valueOf(itemListaConsultaPrevia.getId()));
      	   logOperacaoUsuario.setDescricaoObjeto(itemListaConsultaPrevia.getDescricao());
      	  
      	   logOperacaoUsuario.setUsuario(usuarioId);
      	   logOperacaoUsuario.setDescricaoUsuario(descUser);

			logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
					ItemLista.class, itemListaConsultaPrevia, null);

			vaf.setDescricaoListaValorItemLista(
					listaValorDLO.obter(Long.valueOf(vaf.getIdListaValor())).getDescricao());

			ActionMessage ok = new ActionMessage("msg.itemLista.excluida.sucesso");
			setMensagemSucesso(ok);

		}
		
		if (vaf.getOpcao() != null && !vaf.getOpcao().equals("desativarItemLista") && !vaf.getOpcao().equals("ativarItemLista")
				&& !vaf.getOpcao().equals("incluirItemLista") && !vaf.getOpcao().equals("limparItemLista")) {
			if (vaf.getNovaBusca() != null && vaf.getOpcao() != null ) {
		        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
		        	session.setAttribute("pesquisarSessaoForm", vaf);
		        } else {
		        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
		        	if (caf != null) {
		        		BeanUtils.copyProperties(vaf, caf);
		        	}
		        }
		}
		}

		if ("4".equalsIgnoreCase(operacao)) { // inclusão de item lista com
												// lista valor associado
			retorno = mapping.findForward("editarItemLista");

			vaf.setIdItemLista("");
			vaf.setCodigoItemLista("");
			vaf.setDescricaoItemLista("");
			vaf.setStatusItemLista("");
			vaf.setDescricaoListaValorItemLista(
					listaValorDLO.obter(Long.valueOf(vaf.getIdListaValor())).getDescricao());

		}

		if ("5".equalsIgnoreCase(operacao) || "6".equalsIgnoreCase(operacao)) { // ativação/desativação
																				// de
																				// item
																				// lista
																				// com
																				// lista
																				// valor
																				// associado

			ItemLista itemListaConsultaPrevia = itemListaDLO.obter(Long.valueOf(vaf.getIdItemLista()));

			ActionMessage ok = null;

			ItemLista itemListaNovo = new ItemLista();
			itemListaNovo.setId(itemListaConsultaPrevia.getId());
			itemListaNovo.setCodigo(itemListaConsultaPrevia.getCodigo());
			itemListaNovo.setListaValor(itemListaConsultaPrevia.getListaValor());
			itemListaNovo.setDescricao(itemListaConsultaPrevia.getDescricao());
			if ("5".equalsIgnoreCase(operacao)) {
				itemListaNovo.setStatus("A");
				ok = new ActionMessage("msg.itemLista.ativado.sucesso");
			} else {
				itemListaNovo.setStatus("I");
				ok = new ActionMessage("msg.itemLista.desativado.sucesso");
			}

			itemListaDLO.manter(itemListaNovo);

			itemListaNovo = itemListaDLO.obter(itemListaConsultaPrevia.getId());

	      	   logOperacaoUsuario.setIdObjeto(String.valueOf(itemListaNovo.getId()));
	      	   logOperacaoUsuario.setDescricaoObjeto(itemListaNovo.getDescricao());
	      	  
	      	   logOperacaoUsuario.setUsuario(usuarioId);
	      	   logOperacaoUsuario.setDescricaoUsuario(descUser);

//			logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
//					ItemLista.class, itemListaConsultaPrevia, itemListaNovo);
			
	      	 if (itemListaNovo.getStatus().equals("A")) {
	 			logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ATIVACAO,
					ItemLista.class, itemListaConsultaPrevia, itemListaNovo);
	       	 } else {
	 			logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.DESATIVACAO,
						ItemLista.class, itemListaConsultaPrevia, itemListaNovo);
	       	 }

			// REMOVENDO OUTROS FILTROS DO FORM BEAN PARA VOLTAR CORRETAMENTE AO
			// JSP DE ASSOCIAR ITEM LISTA
			vaf = new ConfiguracaoActionForm();
			vaf.setIdListaValorItemLista(String.valueOf(itemListaNovo.getListaValor().getId()));

			setMensagemSucesso(ok);

			retorno = mapping.findForward("associarItemLista");

			vaf.setIdItemLista("");
			vaf.setCodigoItemLista("");
			vaf.setDescricaoItemLista("");
			vaf.setStatusItemLista("");
			vaf.setDescricaoListaValorItemLista(itemListaConsultaPrevia.getListaValor().getDescricao().toUpperCase());
		}

		return retorno;
	}
}