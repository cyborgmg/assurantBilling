package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class EmpresaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		 ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		String operacao = request.getParameter("operacao");

		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:global/sca-ear/sca-ejb/EmpresaDLOBean");

		Empresa empresa = new Empresa();
		Empresa empresaConsultaPrevia = null;
		
		HttpSession session = request.getSession();
		
		if (request.getParameter("opcao") != null && (request.getParameter("opcao").equals("1"))) {
			
			session.removeAttribute("pesquisarSessaoForm");
			
			retorno = mapping.findForward("listarEmpresa");
		}
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }

		if ("2".equalsIgnoreCase(operacao)) { // Inclusão / alteração

			retorno = mapping.findForward("listarEmpresa");

			if (!vaf.getIdEmpresa().trim().isEmpty()) {
				empresaConsultaPrevia = dloEmpresa.obterPorId(Long.valueOf(vaf.getIdEmpresa()));
			}

			if (empresaConsultaPrevia == null) { // Inclusão
				empresa.setCodigo(vaf.getCodigoEmpresa().toUpperCase());
				empresa.setDescricao(vaf.getDescricaoEmpresa().toUpperCase());

				empresa = dloEmpresa.obterPorId((dloEmpresa.manter(empresa)));
				vaf.setIdEmpresa(String.valueOf(empresa.getId()));
				vaf.setListaAtiva("true");

				setMensagemSucesso(new ActionMessage("msg.empresa.incluido.sucesso"));
				retorno = mapping.findForward("listarEmpresa");
//					request.setAttribute("operacao", "inclusaoSucesso");

			} else {

				empresaConsultaPrevia.setCodigo(vaf.getCodigoEmpresa());
				empresaConsultaPrevia.setDescricao(vaf.getDescricaoEmpresa());
				dloEmpresa.manter(empresaConsultaPrevia);

				setMensagemSucesso(new ActionMessage("msg.empresa.alterada.sucesso"));
				retorno = mapping.findForward("listarEmpresa");
			}

		}

 		if ("3".equalsIgnoreCase(operacao)) { // Exclusão

			empresaConsultaPrevia = dloEmpresa.obterPorId(Long.valueOf(vaf.getIdEmpresa()));
			dloEmpresa.excluir(empresaConsultaPrevia);
			setMensagemSucesso(new ActionMessage("msg.empresa.excluido.sucesso"));
			retorno = mapping.findForward("listarEmpresa");
		}

		return retorno;
	}

}
