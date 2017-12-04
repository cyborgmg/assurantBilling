package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.sistemas.SistemaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class SistemaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:global/sca-ear/sca-ejb/SistemaDLOBean");
		EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:global/sca-ear/sca-ejb/EmpresaDLOBean");

		Sistema sistema = new Sistema();
		Sistema sistemaConsultaPrevia = null;
		
		HttpSession session = request.getSession();
		
		if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarSistema") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		}
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }

		if ("2".equalsIgnoreCase(operacao)) {

			if (!vaf.getIdSistema().trim().isEmpty()) {
				sistemaConsultaPrevia = sistemaDLO.obterPorId(Long.valueOf(vaf.getIdSistema()));
			}

			if (sistemaConsultaPrevia == null) {
				sistema.setSigla(vaf.getSiglaSistema());
				sistema.setDescricao(vaf.getDescricaoSistema().toUpperCase());
				sistema.setEmpresa(empresaDLO.obterPorId(Long.valueOf(vaf.getIdEmpresa())));

				vaf.setIdSistema(((Long) sistemaDLO.manter(sistema)).toString());
				
				vaf.setIdEmpresa(sistema.getEmpresa().getId().toString());
				vaf.setEmpresa(sistema.getEmpresa().getDescricao());
				vaf.setListaAtiva("true");

				setMensagemSucesso(new ActionMessage("msg.sistema.incluido.sucesso"));
				retorno = mapping.findForward("listarSistema");
				
			} else {

				Sistema sistemaNovo = new Sistema();
				sistemaNovo.setId(Long.valueOf(vaf.getIdSistema()));
				sistemaNovo.setSigla(vaf.getSiglaSistema());
				sistemaNovo.setDescricao(vaf.getDescricaoSistema().toUpperCase());
				sistemaNovo.setEmpresa(empresaDLO.obterPorId(Long.valueOf(vaf.getIdEmpresa())));
				sistemaNovo = sistemaDLO.obterPorId((Long) sistemaDLO.manter(sistemaNovo));

				setMensagemSucesso(new ActionMessage("msg.sistema.alterado.sucesso"));
				retorno = mapping.findForward("listarSistema");
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			sistemaConsultaPrevia = sistemaDLO.obterPorId(Long.valueOf(vaf.getIdSistema()));
			sistemaDLO.excluir(sistemaConsultaPrevia);

			ActionMessage ok = new ActionMessage("msg.sistema.excluido.sucesso");
			setMensagemSucesso(ok);
			retorno = mapping.findForward("listarSistema");
		}

		return retorno;
	}
	
	private void limparTelaPesquisa(ConfiguracaoActionForm vaf) {
		
		vaf.setSiglaSistema("");
		vaf.setCodigoEmpresa("");
		vaf.setDescricaoSistema("");
	}
}