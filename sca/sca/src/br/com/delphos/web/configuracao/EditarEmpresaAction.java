package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarEmpresaAction extends StrutsAction {
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward retorno = mapping.findForward("editarEmpresa");
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;
		Empresa empresa = new Empresa();
		
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:global/sca-ear/sca-ejb/EmpresaDLOBean");

		if (uaf != null) {

			if ((request.getParameter("idPesquisa") != null && !(request.getParameter("idPesquisa").isEmpty()))
					&& (request.getParameter("idPesquisa").length() > 0)) {

				empresa = dloEmpresa.obterPorId(Long.parseLong(request.getParameter("idPesquisa")));

				uaf.setCodigoEmpresa(empresa.getCodigo());
				uaf.setDescricaoEmpresa(empresa.getDescricao());
				uaf.setIdEmpresa(empresa.getId().toString());

				uaf.setListaAtiva("true");
			}
		}
		
		return retorno;
	}
	
}