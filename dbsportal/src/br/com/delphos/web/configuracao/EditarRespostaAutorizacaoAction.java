package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacao;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacaoDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarRespostaAutorizacaoAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("editarRespostaAutorizacao");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
String descUser = (String) session.getAttribute("corporativo_usuario");

		RespostaAutorizacaoDLO dloRespostaAutorizacao = (RespostaAutorizacaoDLO) ServiceLocator
				.lookup("java:/global/dbsdb/RespostaAutorizacaoDLOBean");
		ContratoCobrancaDLO dloContratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
		RespostaAutorizacao respostaAutorizacao = new RespostaAutorizacao();
		// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				respostaAutorizacao = dloRespostaAutorizacao.obter(Long.parseLong(request.getParameter("idPesquisa")));
				// respostaAutorizacao = cvh.listarRespostaAutorizacao().get(0);

				uaf.setCodigoEmpresaRespostaAutorizacao(
						respostaAutorizacao.getContratoCobranca().getEmpresa().getCodigo());
				uaf.setCodigoMeioPagamentoRespostaAutorizacao(
						respostaAutorizacao.getContratoCobranca().getMeioPagamento().getCodigo());
				uaf.setCodigoProvedorRespostaAutorizacao(
						respostaAutorizacao.getContratoCobranca().getProvedor().getCodigoProvedor());
				uaf.setCodigoContratoRespostaAutorizacao(
						respostaAutorizacao.getContratoCobranca().getDescricaoContrato()); // TODO
																							// MUDAR
																							// PARA
																							// ID
																							// NA
																							// DOCUMENTACAO
				uaf.setCodigoRespostaAutorizacao(respostaAutorizacao.getCodigoResposta());
				uaf.setDescricaoRespostaAutorizacaoProvedor(respostaAutorizacao.getDescricaoRespostaProvedor());
				uaf.setDescricaoRespostaAutorizacaoConsulta(respostaAutorizacao.getDescricaoRespostaConsulta());
				uaf.setQuantidadeRetentativas(String.valueOf(respostaAutorizacao.getQuantidadeRetentativa()));
				uaf.setTipoIntervaloRetentativas(respostaAutorizacao.getTipoIntervalo());
				uaf.setIntervaloEntreRetentativas(String.valueOf(respostaAutorizacao.getValorIntervalo()));
				uaf.setDescricaoContratoCobranca(respostaAutorizacao.getContratoCobranca().getDescricaoContrato());
				uaf.setIdContratoCobranca(respostaAutorizacao.getContratoCobranca().getId().toString());

				uaf.setIdRespostaAutorizacao(respostaAutorizacao.getId().toString());
			}
		}

		return retorno;
	}

}