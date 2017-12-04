package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.EntidadeExistenteDLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.produtos.Produto_;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class ProdutoAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		// ActionForward retorno = mapping.findForward("preparaConsultaLogs");
		ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();

		ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
		EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		VendaDLO dloVenda = (VendaDLO) ServiceLocator.lookup("java:/global/dbsdb/VendaDLOBean");
		ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		Produto produtoConsultaPrevia = null;

		if ("2".equalsIgnoreCase(operacao)) {


			Produto produto = new Produto();

			if (!vaf.getIdProduto().trim().isEmpty()) {
				produtoConsultaPrevia = produtoDLO.obter(Long.valueOf(vaf.getIdProduto()));
			}

			if (produtoConsultaPrevia == null) {
				produto.setCodigo(vaf.getCodigoProduto().toUpperCase());
				produto.setEmpresa(empresaDLO.obterPorCodigo(vaf.getCodigoEmpresa().toUpperCase()));
				produto.setDescricao(vaf.getDescricaoProduto().toUpperCase());
				produto.setNumeroMaximoParcelas(Integer.valueOf(vaf.getNumeroMaximoParcelasProduto()));
				produto.setTipoCobranca(TipoCobranca.buscarPorValor(vaf.getTipoCobrancaProduto()));

				List<ContratoCobranca> contratosAssociados = new ArrayList<ContratoCobranca>();
				if (vaf.getContratosAssociados().isEmpty()) {
					produto.setContratosCobranca(new ArrayList<ContratoCobranca>());
				} else {
					String[] contratosAssociadosFromForm = vaf.getContratosAssociados().split(",");
					for (int i = 0; i < contratosAssociadosFromForm.length; i++) {
						String string = contratosAssociadosFromForm[i];
						contratosAssociados.add(contratoCobrancaDLO.obter(Long.valueOf(string)));
					}
					produto.setContratosCobranca(contratosAssociados);
				}
				
				try {
					produto = produtoDLO.obter(produtoDLO.manter(produto));
	
             	   logOperacaoUsuario.setIdObjeto(String.valueOf(produto.getId()));
             	   logOperacaoUsuario.setDescricaoObjeto(produto.getDescricao());
             	  
             	   logOperacaoUsuario.setUsuario(usuarioId);
             	   logOperacaoUsuario.setDescricaoUsuario(descUser);
	
					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
							Produto.class, null, produto);
	
					ActionMessage ok = new ActionMessage("msg.produto.incluido.sucesso");
					setMensagemSucesso(ok);
	
					request.setAttribute("operacao", "inclusaoSucesso");
					
					retorno = mapping.findForward("listarProduto");

				} catch (EntidadeExistenteDLOException e) {
					ActionMessage erro = new ActionMessage("erro.entidade.existente");
					setMensagemAlerta(erro);
				}
				
			} else {

				Produto produtoNovo = new Produto();
				produtoNovo.setId(Long.valueOf(vaf.getIdProduto()));
				produtoNovo.setCodigo(vaf.getCodigoProduto().toUpperCase());
				produtoNovo.setEmpresa(empresaDLO.obterPorCodigo(vaf.getCodigoEmpresa()));
				produtoNovo.setDescricao(vaf.getDescricaoProduto().toUpperCase());
				produtoNovo.setNumeroMaximoParcelas(Integer.valueOf(vaf.getNumeroMaximoParcelasProduto()));
				produtoNovo.setTipoCobranca(TipoCobranca.buscarPorValor(vaf.getTipoCobrancaProduto()));

				List<ContratoCobranca> contratosAssociados = new ArrayList<ContratoCobranca>();
				if (vaf.getContratosAssociados().isEmpty()) {
					produtoNovo.setContratosCobranca(new ArrayList<ContratoCobranca>());
				} else {
					String[] contratosAssociadosFromForm = vaf.getContratosAssociados().split(",");
					for (int i = 0; i < contratosAssociadosFromForm.length; i++) {
						String string = contratosAssociadosFromForm[i];
						contratosAssociados.add(contratoCobrancaDLO.obter(Long.valueOf(string)));
					}
					produtoNovo.setContratosCobranca(contratosAssociados);
				}

//				Empresa empresaProdutoNovo = empresaDLO.completar(produtoNovo.getEmpresa(), Empresa_.vendas);
//				Long contagemVendasParaEmpresa = Long.valueOf(empresaProdutoNovo.getVendas().size());
				produtoNovo = produtoDLO.completar(produtoNovo, Produto_.vendas);
				Long contagemVendasParaEmpresa = Long.valueOf(produtoNovo.getVendas().size());

				if (contagemVendasParaEmpresa == 0) { // alteração (* desde que
														// não haja venda
														// cadastrada)

					produtoDLO.manter(produtoNovo);

					produtoNovo = produtoDLO.obter(Long.valueOf(produtoNovo.getId()));

	             	   logOperacaoUsuario.setIdObjeto(String.valueOf(produtoNovo.getId()));
	             	   logOperacaoUsuario.setDescricaoObjeto(produtoNovo.getDescricao());
	             	  
	             	   logOperacaoUsuario.setUsuario(usuarioId);
	             	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							Produto.class, produtoConsultaPrevia, produtoNovo);

					ActionMessage ok = new ActionMessage("msg.produto.alterado.sucesso");
					setMensagemSucesso(ok);
					
		        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
		        	if (caf != null) {
		        		BeanUtils.copyProperties(vaf, caf);
		        	}
					
					retorno = mapping.findForward("listarProduto");

				} else {
					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.produto");
					setMensagemAlerta(erro);
				}

			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarProduto");

			produtoConsultaPrevia = produtoDLO.obter(Long.valueOf(vaf.getIdProduto()));

			produtoConsultaPrevia = produtoDLO.completar(produtoConsultaPrevia, Produto_.vendas);
			Long contagemVendasParaProduto = Long.valueOf(produtoConsultaPrevia.getVendas().size());
			
			produtoConsultaPrevia = produtoDLO.completar(produtoConsultaPrevia, Produto_.contratosCobranca);
			Long contagemContratoCobrancaParaProduto = Long.valueOf(produtoConsultaPrevia.getContratosCobranca().size());

			vaf.setListaAtiva("true");
			
			if (contagemVendasParaProduto == 0 && contagemContratoCobrancaParaProduto == 0) { // exclusão (* desde que não haja venda cadastrada)

				produtoDLO.excluir(produtoConsultaPrevia);

          	   logOperacaoUsuario.setIdObjeto(String.valueOf(produtoConsultaPrevia.getId()));
          	   logOperacaoUsuario.setDescricaoObjeto(produtoConsultaPrevia.getDescricao());
          	  
          	   logOperacaoUsuario.setUsuario(usuarioId);
          	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						Produto.class, produtoConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.produto.excluido.sucesso");
				setMensagemSucesso(ok);

			} else {
				
				if(contagemVendasParaProduto > 0){
					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.produto");
					setMensagemAlerta(erro);
					
				} else if(contagemContratoCobrancaParaProduto > 0){
					ActionMessage erro = new ActionMessage("erro.contrato.cadastrado.produto");
					setMensagemAlerta(erro);
					
				}
			}

		}

		return retorno;
	}

}