package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarMeioPagamentoAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("editarMeioPagamento");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		MeioPagamentoDLO dloMeioPagamento = (MeioPagamentoDLO) ServiceLocator
				.lookup("java:/global/dbsdb/MeioPagamentoDLOBean");
		MeioPagamento meioPagamento = new MeioPagamento();

		if (uaf != null) {

			if (!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0)
					|| (request.getParameter("idPesquisa") != null)) {

				meioPagamento = dloMeioPagamento.obter(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setIdMeioPagamento(meioPagamento.getId().toString());
				uaf.setCodigoMeioPagamento(meioPagamento.getCodigo());
				uaf.setDescricaoMeioPagamento(meioPagamento.getDescricao());
				uaf.setLimiteDiasRetentativa(Integer.valueOf(meioPagamento.getLimiteDiasGerarRetentativa()).toString());
				
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}