package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarItemListaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarItemLista");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ItemListaDLO dloItemLista = (ItemListaDLO) ServiceLocator.lookup("java:/global/dbsdb/ItemListaDLOBean");
		ItemLista itemLista = new ItemLista();

		if (uaf != null && request.getParameter("idPesquisa") != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)) {

				itemLista = dloItemLista.obter(Long.parseLong(request.getParameter("idPesquisa")));
				// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
				// itemLista = cvh.listarItemLista().get(0);

				uaf.setCodigoItemLista(itemLista.getCodigo());
				uaf.setDescricaoListaValorItemLista(itemLista.getListaValor().getDescricao());
				uaf.setIdListaValorItemLista(itemLista.getListaValor().getId().toString());
				uaf.setDescricaoItemLista(itemLista.getDescricao());
				uaf.setStatusItemLista(itemLista.getStatus());

				uaf.setIdItemLista(itemLista.getId().toString());
			}
		}
		
		return retorno;
	}

}