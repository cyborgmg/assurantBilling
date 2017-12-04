package br.com.delphos.web.configuracao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.adquirentes.AdquirenteBandeiraDLO;
import br.com.delphos.billing.adquirentes.AdquirenteBandeiraPK;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacaoDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.empresas.Empresa_;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.billing.vendas.VendaDLO;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.StrutsAction;
import br.com.delphos.util.ServiceLocator;

public class AssociarAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.getInputForward();
		HttpSession sessao = request.getSession();
		ContratoCobrancaDLO dloContratoCobranca = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");

		// --- inicializações gerais
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");
		String opcao = request.getParameter("opcao") != null ? request.getParameter("opcao") : vaf.getOpcao();

		sessao.removeAttribute("produtosAssociados");
		sessao.removeAttribute("codigoEmpresa");
		sessao.removeAttribute("descricaoEmpresa");

		if (request.getParameter("idContrato") != null
				&& !Validador.vazio((String) request.getParameter("idContrato"))) {
			retorno = mapping.findForward("associarAdquirente");
			ContratoCobranca contratoCobranca = new ContratoCobranca();

			contratoCobranca = dloContratoCobranca.obter(Long.parseLong(request.getParameter("idContrato")));

			vaf.setIdContratoCobranca(contratoCobranca.getId().toString());
			vaf.setDescricaoContratoCobranca(contratoCobranca.getDescricaoContrato());

		}

		sessao.removeAttribute("produtosAssociados");

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
		AdquirenteBandeiraDLO adquirenteBandeiraDLO = (AdquirenteBandeiraDLO) ServiceLocator
				.lookup("java:/global/dbsdb/AdquirenteBandeiraDLOBean");
		AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
		BandeiraDLO bandeiraDLO = (BandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/BandeiraDLOBean");
		RespostaAutorizacaoDLO respostaAutorizacaoDLO = (RespostaAutorizacaoDLO) ServiceLocator
				.lookup("java:/global/dbsdb/RespostaAutorizacaoDLOBean");

		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		if (opcao != null && (opcao.equals("listarEmpresa"))) {
			retorno = mapping.findForward("listarEmpresa");
		}
		if (opcao != null && (opcao.equals("1"))) {
			
			session.removeAttribute("pesquisarSessaoForm");
			
			retorno = mapping.findForward("listarEmpresa");
		}
		
		if (opcao != null && (opcao.equals("incluirEmpresa"))) {
				
				vaf.setIdEmpresa("");
				vaf.setCodigoEmpresa("");
				vaf.setDescricaoEmpresa("");
				
		}

		if (opcao != null && (opcao.equals("adquirenteBandeira") || opcao.equals("editarAssociacaoAdquirente")
				|| opcao.equals("excluirAssociacaoAdquirente") || opcao.equals("prosseguirAdquirenteBandeira"))) {

			AdquirenteBandeira adquirenteBandeiraConsultaPrevia = null;
			AdquirenteBandeira adquirenteBandeiraConsultaPreviaOld = null;
			List<AdquirenteBandeira> listAdquirenteBandeiraOld = null;
			List<AdquirenteBandeira> listAdquirenteBandeira = null;

			if ("2".equalsIgnoreCase(operacao)) {

				retorno = mapping.findForward("associarAdquirente");

				AdquirenteBandeira adquirenteBandeira = new AdquirenteBandeira();

				if (!vaf.getIdAdquirenteOld().trim().isEmpty() && !vaf.getIdBandeiraOld().trim().isEmpty()
						&& !vaf.getIdContratoCobranca().trim().isEmpty()
						&& !vaf.getPadraoAssociarContratoOld().trim().isEmpty()
						&& !vaf.getCodigoMetodoPagamentoOld().trim().isEmpty()) { // edição
					listAdquirenteBandeiraOld = adquirenteBandeiraDLO.listarPorCriterio(vaf.getIdAdquirenteOld(),
							vaf.getIdBandeiraOld(), vaf.getIdContratoCobranca(), "", "");
					listAdquirenteBandeira = adquirenteBandeiraDLO.listarPorCriterio(vaf.getIdAdquirente(),
							vaf.getIdBandeira(), vaf.getIdContratoCobranca(), "", "");
					if (listAdquirenteBandeiraOld.size() == 1 || listAdquirenteBandeira.size() == 1) { // se
																										// for
																										// edição
																										// vindo
																										// do
																										// detalhar
						if (listAdquirenteBandeiraOld.size() == 1) {
							adquirenteBandeiraConsultaPrevia = listAdquirenteBandeiraOld.get(0);
						} else if (listAdquirenteBandeira.size() == 1) {
							adquirenteBandeiraConsultaPrevia = listAdquirenteBandeira.get(0);
						}
						adquirenteBandeiraConsultaPreviaOld = (AdquirenteBandeira) BeanUtils
								.cloneBean(adquirenteBandeiraConsultaPrevia);
					} else { // se for edição não alterando nenhum dado e
								// clicando em gravar

						adquirenteBandeiraConsultaPrevia = null;
					}
				} else {
					listAdquirenteBandeira = adquirenteBandeiraDLO.listarPorCriterio(vaf.getIdAdquirente(),
							vaf.getIdBandeira(), vaf.getIdContratoCobranca(), "", ""); // criação
					if (listAdquirenteBandeira.size() == 1) {

						vaf.setIdAdquirenteOld("");
						vaf.setIdBandeiraOld("");
						vaf.setPadraoAssociarContratoOld("");
						vaf.setCodigoMetodoPagamentoOld("");

						throw new DLOException("erro.adquirente.bandeira.existe.associacaoAdquirente");

					} else {
						adquirenteBandeiraConsultaPrevia = null;
					}
				}

				if (adquirenteBandeiraConsultaPrevia == null) {
					if (vaf.getPadraoAssociarContrato() != null) {
						adquirenteBandeira.setAdquirentePadrao(vaf.getPadraoAssociarContrato());
					} else {
						adquirenteBandeira.setAdquirentePadrao("N");
					}
//					adquirenteBandeira.setId(null);
					adquirenteBandeira.setBandeira(bandeiraDLO.obter(Long.valueOf(vaf.getIdBandeira())));
					adquirenteBandeira.setAdquirente(adquirenteDLO.obter(Long.valueOf(vaf.getIdAdquirente())));
					adquirenteBandeira.setCodigoMetodoPagamento(Long.valueOf(vaf.getCodigoMetodoPagamento()));
					adquirenteBandeira
							.setContratoCobranca(contratoCobrancaDLO.obter(Long.valueOf(vaf.getIdContratoCobranca())));
					adquirenteBandeira.setId(new AdquirenteBandeiraPK());

					adquirenteBandeiraDLO.manter(adquirenteBandeira);

					// logOperacaoUsuario.setIdObjeto(vaf.getIdAdquirente() +
					// "-" + vaf.getIdBandeira() + "-"+
					// vaf.getIdContratoCobranca());
//					logOperacaoUsuario.setIdObjeto(adquirenteBandeira.toString());

					ActionMessage ok = new ActionMessage("msg.associacaoAdquirente.incluido.sucesso");
					setMensagemSucesso(ok);

					vaf.setIdAdquirente("");
					vaf.setIdAdquirenteOld("");
					vaf.setIdBandeira("");
					vaf.setIdBandeiraOld("");
					vaf.setPadraoAssociarContrato("");
					vaf.setPadraoAssociarContratoOld("");
					vaf.setCodigoMetodoPagamento("");
					vaf.setCodigoMetodoPagamentoOld("");
					vaf.setIdAdquirenteSelecionado("");
					vaf.setIdBandeiraSelecionado("");

					request.setAttribute("operacao", "inclusaoSucesso");

				} else { // alteração
					if (listAdquirenteBandeiraOld.size() == 1 || listAdquirenteBandeira.size() == 1) { // se
																										// for
																										// edição
																										// vindo
																										// do
																										// detalhar
						if (listAdquirenteBandeiraOld.size() == 1) {
							adquirenteBandeiraConsultaPrevia = listAdquirenteBandeiraOld.get(0);
							adquirenteBandeiraConsultaPrevia = adquirenteBandeiraDLO
									.obterEntidadeAtualizada(adquirenteBandeiraConsultaPrevia);
						} else if (listAdquirenteBandeira.size() == 1) {
							adquirenteBandeiraConsultaPrevia = listAdquirenteBandeira.get(0);
							adquirenteBandeiraConsultaPrevia = adquirenteBandeiraDLO
									.obterEntidadeAtualizada(adquirenteBandeiraConsultaPrevia);
						}
					}

					adquirenteBandeiraConsultaPrevia
							.setAdquirente(adquirenteDLO.obter(Long.valueOf(vaf.getIdAdquirente())));
					if (vaf.getPadraoAssociarContrato() != null) {
						adquirenteBandeiraConsultaPrevia.setAdquirentePadrao(vaf.getPadraoAssociarContrato());
					}
					adquirenteBandeiraConsultaPrevia.setBandeira(bandeiraDLO.obter(Long.valueOf(vaf.getIdBandeira())));
					adquirenteBandeiraConsultaPrevia
							.setCodigoMetodoPagamento(Long.valueOf(vaf.getCodigoMetodoPagamento()));
					adquirenteBandeiraConsultaPrevia
							.setContratoCobranca(contratoCobrancaDLO.obter(Long.valueOf(vaf.getIdContratoCobranca())));

					if (!(adquirenteBandeiraConsultaPreviaOld.getAdquirente().getId().toString()
							.equals(vaf.getIdAdquirente())
							&& adquirenteBandeiraConsultaPreviaOld.getBandeira().getId().toString()
									.equals(vaf.getIdBandeira()))) {

						// se o adquirenteBandeira é alterado para outro
						// adquirente x bandeira existente.... tirando o q já
						// existe
						listAdquirenteBandeira = adquirenteBandeiraDLO.listarPorCriterio(vaf.getIdAdquirente(),
								vaf.getIdBandeira(), vaf.getIdContratoCobranca(), "", "");

						if (listAdquirenteBandeira.size() == 1) {

							vaf.setIdAdquirenteOld("");
							vaf.setIdBandeiraOld("");
							vaf.setPadraoAssociarContratoOld("");
							vaf.setCodigoMetodoPagamentoOld("");

							throw new DLOException("erro.adquirente.bandeira.existe.associacaoAdquirente");
						}

					}

					Empresa empresaConsultaPrevia = adquirenteBandeiraConsultaPrevia.getContratoCobranca().getEmpresa();
					empresaConsultaPrevia = empresaDLO.completar(empresaConsultaPrevia, Empresa_.vendas);
					Long contagemVendasParaEmpresa = Long.valueOf(empresaConsultaPrevia.getVendas().size());

					//if (contagemVendasParaEmpresa == 0) { // alteração (* desde
															// que não haja
															// venda cadastrada)

						adquirenteBandeiraDLO.manter(adquirenteBandeiraConsultaPrevia);

						// logOperacaoUsuario.setComplemento("Id Bandeira: " +
						// vaf.getIdBandeira() + ", Id Adquirente: " +
						// vaf.getIdAdquirente());
//						logOperacaoUsuario.setIdObjeto(adquirenteBandeira.toString());

						ActionMessage ok = new ActionMessage("msg.associacaoAdquirente.alterado.sucesso");
						setMensagemSucesso(ok);

						vaf.setIdAdquirenteOld("");
						vaf.setIdBandeiraOld("");
						vaf.setIdAdquirente("");
						vaf.setIdBandeira("");
						vaf.setIdContratoCobranca(
								adquirenteBandeiraConsultaPrevia.getContratoCobranca().getId().toString());
						vaf.setPadraoAssociarContratoOld("");
						vaf.setCodigoMetodoPagamentoOld("");
						vaf.setPadraoAssociarContrato("");
						vaf.setCodigoMetodoPagamento("");
						vaf.setIdAdquirenteSelecionado("");
						vaf.setIdBandeiraSelecionado("");

//					} else {
//						ActionMessage erro = new ActionMessage("erro.venda.cadastrada.associacaoAdquirente");
//						setMensagemAlerta(erro);
//					}

				}

			}

			if ("3".equalsIgnoreCase(operacao)) {
				adquirenteBandeiraConsultaPrevia = adquirenteBandeiraDLO
						.listarPorCriterio(vaf.getIdAdquirente(), vaf.getIdBandeira(), vaf.getIdContratoCobranca(),
								vaf.getPadraoAssociarContrato(), vaf.getCodigoMetodoPagamento())
						.get(0);

				Empresa empresaConsultaPrevia = adquirenteBandeiraConsultaPrevia.getContratoCobranca().getEmpresa();
				empresaConsultaPrevia = empresaDLO.completar(empresaConsultaPrevia, Empresa_.vendas);
				Long contagemVendasParaEmpresa = Long.valueOf(empresaConsultaPrevia.getVendas().size());

				//if (contagemVendasParaEmpresa == 0) { // exclusão (* desde que
														// não haja venda
														// cadastrada)
					
					// logOperacaoUsuario.setComplemento("Id Bandeira: " +
					// vaf.getIdBandeira() + ", Id Adquirente: " +
					// vaf.getIdAdquirente());
//					logOperacaoUsuario.setIdObjeto(adquirenteBandeiraConsultaPrevia.toString());

					adquirenteBandeiraDLO.excluir(adquirenteBandeiraConsultaPrevia);

					ActionMessage ok = new ActionMessage("msg.associacaoAdquirente.excluido.sucesso");
					setMensagemSucesso(ok);

//				} else {
//					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.associacaoAdquirente");
//					setMensagemAlerta(erro);
//				}

				vaf.setIdAdquirente("");
				vaf.setIdAdquirenteOld("");
				vaf.setIdBandeira("");
				vaf.setIdBandeiraOld("");
				vaf.setPadraoAssociarContrato("");
				vaf.setPadraoAssociarContratoOld("");
				vaf.setCodigoMetodoPagamento("");
				vaf.setCodigoMetodoPagamentoOld("");
				vaf.setIdAdquirenteSelecionado("");
				vaf.setIdBandeiraSelecionado("");
			}

		}

		if (opcao != null && opcao.equals("respostaAutorizacao")) {
			retorno = mapping.findForward("editarRespostaAutorizacao");

			vaf.setIdContratoCobranca(request.getParameter("idContrato"));

			ContratoCobranca contrato = dloContratoCobranca.obter(Long.valueOf(vaf.getIdContratoCobranca()));

			vaf.setCodigoEmpresaRespostaAutorizacao(contrato.getEmpresa().getCodigo());
			vaf.setCodigoMeioPagamentoRespostaAutorizacao(contrato.getMeioPagamento().getCodigo());
			vaf.setCodigoProvedorRespostaAutorizacao(contrato.getProvedor().getCodigoProvedor());

			vaf.setDescricaoContratoCobranca(contrato.getDescricaoContrato());
			vaf.setCodigoContratoRespostaAutorizacao(contrato.getDescricaoContrato());

		}

		return retorno;
	}
}