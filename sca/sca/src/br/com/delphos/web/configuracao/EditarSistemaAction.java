package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.sistemas.SistemaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarSistemaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarSistema");
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		SistemaDLO dloSistema = (SistemaDLO) ServiceLocator.lookup("java:global/sca-ear/sca-ejb/SistemaDLOBean");

		Sistema sistema = new Sistema();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				sistema = dloSistema.obterPorId(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setSiglaSistema(sistema.getSigla());
				uaf.setIdEmpresa(sistema.getEmpresa().getId().toString());
				uaf.setDescricaoSistema(sistema.getDescricao());
				uaf.setIdSistema(sistema.getId().toString());

				uaf.setListaAtiva("true");
			}
		}
		
		return retorno;
	}

}