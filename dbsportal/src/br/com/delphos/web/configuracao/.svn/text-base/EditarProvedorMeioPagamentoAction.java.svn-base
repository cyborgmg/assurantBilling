package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarProvedorMeioPagamentoAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarProvedorMeioPagamento");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ProvedorDLO dloProvedor = (ProvedorDLO) ServiceLocator.lookup("java:/global/dbsdb/ProvedorDLOBean");
		Provedor provedor = new Provedor();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				provedor = dloProvedor.obter(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setIdProvedorMeioPagamento(provedor.getId().toString());
				uaf.setCodigoProvedor(provedor.getCodigoProvedor());
				uaf.setDescricaoProvedor(provedor.getDescricaoProvedor());
				
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}