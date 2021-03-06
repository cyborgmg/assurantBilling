package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarBandeiraAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaġes gerais
		ActionForward retorno = mapping.findForward("editarBandeira");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		BandeiraDLO dloBandeira = (BandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/BandeiraDLOBean");
		Bandeira bandeira = new Bandeira();

		AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");

		List<Adquirente> listaAdquirentesNaoAssociados = adquirenteDLO.listar();
		List<Adquirente> listaAdquirentesAssociados = new ArrayList<Adquirente>();

		session.setAttribute("adquirentesNaoAssociados", listaAdquirentesNaoAssociados);
		session.setAttribute("adquirentesAssociados", listaAdquirentesAssociados);

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				bandeira = dloBandeira.obter(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setCodigoBandeira(bandeira.getCodigoBandeira());
				uaf.setNomeBandeira(bandeira.getNomeBandeira());
				uaf.setIdBandeira(bandeira.getId().toString());
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}