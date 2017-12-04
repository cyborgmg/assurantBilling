package br.com.delphos.web.conciliacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ControleConciliacaoDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Texto;

public class EditarConciliacaoAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("editarConciliacao");
		// ActionForward retorno = mapping.getInputForward();
		ConciliacaoActionForm uaf = (ConciliacaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
		ControleConciliacaoDLO conciliacaoInterfaceDLO = (ControleConciliacaoDLO) ServiceLocator.lookup("java:/global/dbsdb/ControleConciliacaoDLOBean");

		ControleConciliacao conciliacao = new ControleConciliacao();
		
		if (uaf != null) {

			if ((request.getParameter("idPesquisa") != null) &&
					(!(request.getParameter("idPesquisa").isEmpty()) || (request.getParameter("idPesquisa").length() > 0))
					) {

				conciliacao = conciliacaoInterfaceDLO.obter(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setIdControleConciliacao(conciliacao.getId().toString());
				uaf.setNomeAdquirente(conciliacao.getAdquirente());
				uaf.setDataMovimentoArquivo(Data.formatar(conciliacao.getDataMovimento(), Data.MASCARA_SEM_HORA));
				if (conciliacao.getValorDeposito() != null) {
					uaf.setValorDeposito(Texto.doubleToMoeda(conciliacao.getValorDeposito().doubleValue(), "###,###,###,##0.00"));
				}
				uaf.setListaAtiva("true");
				
				uaf.setStatusDeposito(conciliacao.getStatus().getValor());
				uaf.setDataProcessamentoArquivo(Data.formatar(conciliacao.getDataProcessamentoArquivo(), Data.MASCARA_SEM_HORA));
			} else {
				uaf.setStatusDeposito("PEN");
			}
		}

		return retorno;
	}

}