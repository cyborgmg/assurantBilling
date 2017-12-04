package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class PrepararBandeiraAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("listarBandeira");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");

		ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
		List<Adquirente> listaAdquirentesNaoAssociados = cvh.listarAdquirente();
		List<Adquirente> listaAdquirentesAssociados = new ArrayList<Adquirente>();

		session.setAttribute("adquirentesNaoAssociados", listaAdquirentesNaoAssociados);
		session.setAttribute("adquirentesAssociados", listaAdquirentesAssociados);

		if (!"true".equals(vaf.getListaAtiva()) || request.getParameter("opcao") == null
				|| (request.getParameter("opcao") != null && !request.getParameter("opcao").equals("prepararVoltar"))) {
			
			vaf.setCodigoBandeira("");
			vaf.setNomeBandeira("");
		}

		if (request.getParameter("opcao") != null && request.getParameter("opcao").equals("incluir")) {

			retorno = mapping.findForward("editarBandeira");
		}
		
		if (vaf.getNovaBusca() != null) {
	        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
	        	session.setAttribute("pesquisarSessaoForm", vaf);
	        } else {
	        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
	        	if (caf != null) {
	        		BeanUtils.copyProperties(vaf, caf);
	        	}
	        }
		}
		
		return retorno;
	}

}