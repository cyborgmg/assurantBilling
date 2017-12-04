package br.com.delphos.web.logs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.enumeracoes.Entidade;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class LogsAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("prepararConsultaLogs");
		LogsActionForm vaf = (LogsActionForm) form;

		ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		if (Boolean.valueOf(vaf.getNovaBusca())) {
			// request.setAttribute("listaTipoOperacao",
			// TipoOperacaoUsuario.values());
			// ListaValor listaValorTipoOperacao =
			// listaValorDLO.obterPorCodigo("TPOP");
			ListaValor listaValorTipoOperacao = listaValorDLO.obterPorCodigo(TipoListaValor.Operacao.getValor());

			// request.setAttribute("listaTipoOperacao",
			// TipoOperacaoUsuario.values());
			request.setAttribute("listaTipoOperacao", listaValorTipoOperacao.getItensLista());
			request.setAttribute("listaObjeto", Entidade.values());
		}

		return retorno;
	}

}