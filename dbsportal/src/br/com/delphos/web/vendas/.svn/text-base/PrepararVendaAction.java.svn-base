package br.com.delphos.web.vendas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.util.StrutsAction;

public class PrepararVendaAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// --- inicializaões gerais
		ActionForward retorno = mapping.findForward("prepararVenda");
//		VendasActionForm vaf = (VendasActionForm) form;
//		vaf = new VendasActionForm();

		HttpSession session = request.getSession();
		
		session.removeAttribute("produtos_filtro_produto");
		session.removeAttribute("produtos_filtro_sistema");
        session.removeAttribute("codEmpresa");
        session.removeAttribute("idProduto");
        session.removeAttribute("idSistema");
        session.removeAttribute("cpf");  
        session.removeAttribute("certificado");
        
		return retorno;
	}

}
