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

public class EditarAdquirenteAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarAdquirente");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		AdquirenteDLO dloAdquirente = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
		Adquirente adquirente = new Adquirente();

		BandeiraDLO bandeiraDLO = (BandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/BandeiraDLOBean");

		ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
		List<Bandeira> listaBandeirasNaoAssociadas = cvh.listarBandeira();
		List<Bandeira> listaBandeirasAssociadas = new ArrayList<Bandeira>();

		session.setAttribute("bandeirasNaoAssociadas", listaBandeirasNaoAssociadas);
		session.setAttribute("bandeirasAssociadas", listaBandeirasAssociadas);

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				adquirente = dloAdquirente.obter(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setCodigoConvenioAdquirente(adquirente.getCodigoConvenio());
				if(adquirente.getCodigoAfiliacaoConciliador() != null){
				uaf.setCodigoAfiliacao(Integer.toString(adquirente.getCodigoAfiliacaoConciliador()));
				}
				uaf.setNomeAdquirente(adquirente.getNome());
				uaf.setIdAdquirente(adquirente.getId().toString());
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}