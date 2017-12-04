package br.com.delphos.web.logs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.enumeracoes.Entidade;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.util.ServiceLocator;

public class PrepararConsultaLogAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializações gerais
		ActionMessages messages = new ActionMessages();
		ActionForward retorno = mapping.findForward("prepararConsultaLogs");
//		VendasActionForm vaf = (VendasActionForm) form;
//		vaf = new VendasActionForm();
		
		ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");

		try {
			HttpSession session = request.getSession();
			
//			ListaValor listaValorTipoOperacao = listaValorDLO.obterPorCodigo("TPOP");
			ListaValor listaValorTipoOperacao = listaValorDLO.obterPorCodigo(TipoListaValor.Operacao.getValor());
			
			//request.setAttribute("listaTipoOperacao", TipoOperacaoUsuario.values());
			request.setAttribute("listaTipoOperacao", listaValorTipoOperacao.getItensLista());
			
			
			request.setAttribute("listaObjeto", Entidade.values());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String msg = DLOException.obterMensagemDLO(e);
			ActionMessage erro = new ActionMessage(msg, false);
			
			messages.add("dialogoAlerta", erro);
			this.addErrors(request, messages);

		}
		return retorno;
	}

}
