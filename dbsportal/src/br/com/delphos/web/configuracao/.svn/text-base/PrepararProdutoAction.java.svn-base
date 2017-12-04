package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.Validador;

public class PrepararProdutoAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("editarProduto");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
		EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
		SistemaDLO dloSistema = (SistemaDLO) ServiceLocator.lookup("java:/global/dbsdb/SistemaDLOBean");
		ContratoCobrancaDLO dloContratoCobranca = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
		
		if (!"true".equals(vaf.getListaAtiva())) {
			session.removeAttribute("contratosCobrancaAssociados");
			session.removeAttribute("empresasAssociadas");
			session.removeAttribute("sistemasAssociados");
			session.removeAttribute("produtosAssociados");
			vaf.setCodigoProduto("");
			vaf.setDescricaoProduto("");
			vaf.setEmpresa("");
			vaf.setCodigoEmpresa("");
			vaf.setIdProduto("");
		}

		if (vaf.getOpcao() != null) {
			if (!vaf.getOpcao().equals("listarProduto")) {
				
				// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();

				List<Empresa> listaEmpresasNaoAssociadas = dloEmpresa.listar();
				// List<Empresa> listaEmpresasNaoAssociadas =
				// cvh.listarEmpresa();
				List<Empresa> listaEmpresasAssociadas = new ArrayList<Empresa>();

				session.setAttribute("empresasNaoAssociadas", listaEmpresasNaoAssociadas);
				session.setAttribute("empresasAssociadas", listaEmpresasAssociadas);

				// List<Sistema> listaSistemasNaoAssociados =
				// dloSistema.listar();
				// List<Sistema> listaSistemasNaoAssociados =
				// cvh.listarSistema();
				List<Sistema> listaSistemasAssociados = new ArrayList<Sistema>();

				// session.setAttribute("sistemasNaoAssociados",
				// listaSistemasNaoAssociados);

				StringBuffer sb = new StringBuffer();
				for (Iterator<Sistema> iterator = listaSistemasAssociados.iterator(); iterator.hasNext();) {
					Sistema sistema = (Sistema) iterator.next();
					sb.append("-").append(sistema.getDescricao()).append("&#13;&#10;");
				}
				session.setAttribute("sistemasAssociados", sb.toString());

				session.setAttribute("produtosAssociados", sb.toString());

				List<ContratoCobranca> listaContratosCobrancaNaoAssociados = dloContratoCobranca.listar();
				// List<ContratoCobranca> listaContratosCobrancaNaoAssociados =
				// cvh.listarContratoCobranca();
				List<ContratoCobranca> listaContratosCobrancaAssociados = new ArrayList<ContratoCobranca>();

				session.setAttribute("contratosCobrancaNaoAssociados", listaContratosCobrancaNaoAssociados);
				session.setAttribute("contratosCobrancaAssociados", listaContratosCobrancaAssociados);

			} 
			
			if (vaf.getOpcao() != null && (vaf.getOpcao().equals("1") || vaf.getOpcao().equals("listarProduto"))) {
				
				if (vaf.getOpcao().equals("1")) {
					session.removeAttribute("pesquisarSessaoForm");
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
				
				retorno = mapping.findForward("listarProduto");
			}
		}

		return retorno;
	}

}