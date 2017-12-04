package br.com.delphos.web.configuracao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class EditarContratoCobrancaAction extends StrutsAction {

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("editarContratoCobranca");
		// ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm uaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");

		ContratoCobrancaDLO dloContratoCobranca = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
		ContratoCobranca contratoCobranca = new ContratoCobranca();

		ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");

		// ConfiguracaoViewHelper cvh = new ConfiguracaoViewHelper();
		// List<Produto> listaProdutosNaoAssociados = cvh.listarProduto();

		if (uaf != null) {

			if (request.getParameter("idPesquisa") != null && !request.getParameter("idPesquisa").isEmpty()) {

				contratoCobranca = dloContratoCobranca.obter(Long.parseLong(request.getParameter("idPesquisa")));
				// contratoCobranca = cvh.listarContratoCobranca().get(0);

				contratoCobranca = dloContratoCobranca.completar(contratoCobranca, ContratoCobranca_.produtos);
				List<Produto> listaProdutosAssociados = contratoCobranca.getProdutos();
				session.setAttribute("produtosAssociados", listaProdutosAssociados);
				List<Produto> listaProdutosNaoAssociados = produtoDLO
						.obterProdutosNaoRelacionadosAoContrato(contratoCobranca.getId().toString());

//				session.setAttribute("produtosNaoAssociados", listaProdutosNaoAssociados); 
				// AGORA EH FEITA UMA CHAMADA AJAX PRA CARREGAR A COMBO POR CONTA DA QUESTAO QUE ENVOLVE A VALIDACAO DOS CAMPOS
				// QUANDO UM DOS MESMOS REQUER PREENCHIMENTO

				uaf.setCodigoEmpresaContratoCobranca(contratoCobranca.getEmpresa().getCodigo());
				uaf.setCodigoMeioPagamentoContratoCobranca(contratoCobranca.getMeioPagamento().getCodigo());
				uaf.setCodigoProvedorContratoCobranca(contratoCobranca.getProvedor().getCodigoProvedor());
				uaf.setDescricaoContratoCobranca(contratoCobranca.getDescricaoContrato());
				uaf.setCodEmpresaProvMeioPagamentoContratoCobranca(contratoCobranca.getCodigoEmpresaNoProvedor());
				uaf.setPrazoPagamentoDiasContratoCobranca(String.valueOf(contratoCobranca.getPrazoPagamento()));
				uaf.setTipoTransacaoProvedorContratoCobranca(contratoCobranca.getTipoTransacaoProvedor());
				uaf.setInicioVigenciaContratoCobranca(
						Data.formatar(contratoCobranca.getDataInicioVigencia(), Data.MASCARA_SEM_HORA));
				uaf.setFimVigenciaContratoCobranca(
						Data.formatar(contratoCobranca.getDataFimVigencia(), Data.MASCARA_SEM_HORA));

				uaf.setIdContratoCobranca(contratoCobranca.getId().toString());
				
				uaf.setListaAtiva("true");
			}
		}

		return retorno;
	}

}