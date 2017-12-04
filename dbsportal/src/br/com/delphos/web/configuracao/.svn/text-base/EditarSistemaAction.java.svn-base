package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarSistemaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarSistema");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		SistemaDLO dloSistema = (SistemaDLO) ServiceLocator.lookup("java:global/dbsdb/SistemaDLOBean");

		Sistema sistema = new Sistema();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				sistema = dloSistema.obter(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setCodigoSistema(sistema.getCodigo());
				uaf.setDescricaoSistema(sistema.getDescricao());
				uaf.setSistemaEnvioInformacoesSistema(sistema.getCodigoSistemaEnvio());

				uaf.setIdSistema(sistema.getId().toString());
				
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}