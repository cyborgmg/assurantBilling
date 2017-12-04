package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.adquirentes.AdquirenteBandeiraDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarAssociacaoAdquirenteAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("associarAdquirente");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		AdquirenteBandeiraDLO adquirenteBandeiraDLO = (AdquirenteBandeiraDLO) ServiceLocator
				.lookup("java:/global/dbsdb/AdquirenteBandeiraDLOBean");

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
String descUser = (String) session.getAttribute("corporativo_usuario");

		if (vaf.getIdContratoCobranca() != null && !Validador.vazio((String) vaf.getIdContratoCobranca())) {
			retorno = mapping.findForward("associarAdquirente");

			AdquirenteBandeira associacaoAdquirente = adquirenteBandeiraDLO
					.listarPorCriterio(vaf.getIdAdquirente(), vaf.getIdBandeira(), vaf.getIdContratoCobranca(),
							null, null)
					.get(0);

			vaf.setIdContratoCobranca(associacaoAdquirente.getContratoCobranca().getId().toString());
			vaf.setIdAdquirente(associacaoAdquirente.getAdquirente().getId().toString());
			vaf.setIdAdquirenteOld(associacaoAdquirente.getAdquirente().getId().toString());
			vaf.setNomeAdquirente(associacaoAdquirente.getAdquirente().getNome());
			vaf.setIdBandeira(associacaoAdquirente.getBandeira().getId().toString());
			vaf.setIdBandeiraOld(associacaoAdquirente.getBandeira().getId().toString());
			vaf.setNomeBandeira(associacaoAdquirente.getBandeira().getNomeBandeira());
			vaf.setPadraoAssociarContrato(associacaoAdquirente.getAdquirentePadrao());
			vaf.setPadraoAssociarContratoOld(associacaoAdquirente.getAdquirentePadrao());
			vaf.setCodigoMetodoPagamento(Long.valueOf(associacaoAdquirente.getCodigoMetodoPagamento()).toString());
			vaf.setCodigoMetodoPagamentoOld(Long.valueOf(associacaoAdquirente.getCodigoMetodoPagamento()).toString());

			vaf.setDescricaoContratoCobranca(associacaoAdquirente.getContratoCobranca().getDescricaoContrato());

		}

		return retorno;
	}

}