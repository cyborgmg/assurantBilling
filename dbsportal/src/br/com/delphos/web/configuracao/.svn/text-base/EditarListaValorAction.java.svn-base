package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarListaValorAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarListaValor");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ListaValorDLO dloListaValor = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
		ListaValor listaValor = new ListaValor();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				listaValor = dloListaValor.obter(Long.parseLong(request.getParameter("idPesquisa")));
				// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
				// listaValor = cvh.listarListaValor().get(0);

				uaf.setCodigoListaValor(listaValor.getCodigo());
				uaf.setDescricaoListaValor(listaValor.getDescricao().toUpperCase());
				uaf.setTipoDeUsoListaValor(listaValor.getTipoUso());
				uaf.setStatusListaValor(listaValor.getStatus());
				uaf.setIdListaValor(listaValor.getId().toString());

				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}