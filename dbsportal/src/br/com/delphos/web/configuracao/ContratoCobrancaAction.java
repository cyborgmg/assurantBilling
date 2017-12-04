package br.com.delphos.web.configuracao;

import java.text.ParseException;
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
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class ContratoCobrancaAction extends StrutsAction {

	private SCAClient client = null;
	ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator
			.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
	EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
	MeioPagamentoDLO meioPagamentoDLO = (MeioPagamentoDLO) ServiceLocator
			.lookup("java:/global/dbsdb/MeioPagamentoDLOBean");
	ProvedorDLO provedorDLO = (ProvedorDLO) ServiceLocator.lookup("java:/global/dbsdb/ProvedorDLOBean");

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;
		boolean sucesso = true;

		String operacao = request.getParameter("operacao");

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");

		if (Boolean.valueOf(vaf.getNovaBusca())) {

			session.setAttribute("codigoEmpresaContratoCobranca", vaf.getCodigoEmpresaContratoCobranca());
			session.setAttribute("codigoProvedorContratoCobranca", vaf.getCodigoProvedorContratoCobranca());
			session.setAttribute("descricaoContratoCobranca", vaf.getDescricaoContratoCobranca());

		}

		if ("2".equalsIgnoreCase(operacao)) {

			ContratoCobranca contratoCobranca = new ContratoCobranca();
			ContratoCobranca contratoCobrancaConsultaPrevia = null;

			if (!vaf.getIdContratoCobranca().trim().isEmpty()) {
				contratoCobrancaConsultaPrevia = contratoCobrancaDLO.obter(Long.valueOf(vaf.getIdContratoCobranca()));

			}

			if (contratoCobrancaConsultaPrevia == null) {
				// Inserção
				// Se o contrato estiver dentro do período de outro contrato (em termos mais
				// matemáticos, se houver uma interseção entre os dois períodos de tempo),
				// esta condição será identificada pelos procedimentos de persistência
				// (mais precisamente antes de incluir, no método obterPorIdentidade ou existePorIdentidade).
				contratoCobranca.setEmpresa(empresaDLO.obterPorCodigo(vaf.getCodigoEmpresaContratoCobranca()));
				contratoCobranca.setMeioPagamento(
						meioPagamentoDLO.obterPorCodigo(vaf.getCodigoMeioPagamentoContratoCobranca()));
				contratoCobranca.setProvedor(provedorDLO.obterPorCodigo(vaf.getCodigoProvedorContratoCobranca()));
				contratoCobranca.setDescricaoContrato(vaf.getDescricaoContratoCobranca().toUpperCase());
				contratoCobranca
						.setCodigoEmpresaNoProvedor(vaf.getCodEmpresaProvMeioPagamentoContratoCobranca().toUpperCase());
				contratoCobranca.setPrazoPagamento(Integer.valueOf(vaf.getPrazoPagamentoDiasContratoCobranca()));
				contratoCobranca.setTipoTransacaoProvedor(vaf.getTipoTransacaoProvedorContratoCobranca());
				contratoCobranca.setDataInicioVigencia(
						DateUtils.removerHoras(Validador.obterDataPadrao(vaf.getInicioVigenciaContratoCobranca())));
				contratoCobranca.setDataFimVigencia(
						DateUtils.removerHoras(Validador.obterDataPadrao(vaf.getFimVigenciaContratoCobranca())));

				List<Produto> produtosAssociados = new ArrayList<Produto>();
				if (vaf.getProdutosAssociados().isEmpty()) {
					contratoCobranca.setProdutos((new ArrayList<Produto>()));
				} else {
					String[] produtosAssociadosFromForm = vaf.getProdutosAssociados().split(",");
					for (int i = 0; i < produtosAssociadosFromForm.length; i++) {
						String string = produtosAssociadosFromForm[i];
						produtosAssociados.add(produtoDLO.obter(Long.valueOf(string)));
					}
					contratoCobranca.setProdutos(produtosAssociados);
				}
				
				List<Produto> produtosSemAssociacao = produtoDLO.listarProdutosSemAssociacao();
				produtosSemAssociacao.removeAll(produtosAssociados);
				
				session.setAttribute("produtosNaoAssociados", produtosSemAssociacao);
				session.setAttribute("produtosAssociados", produtosAssociados);
				
				contratoCobranca.setId((Long) contratoCobrancaDLO.manter(contratoCobranca)); 
				vaf.setIdContratoCobranca(contratoCobranca.getId().toString());

				logOperacaoUsuario.setIdObjeto(String.valueOf(vaf.getIdContratoCobranca()));

				vaf.setIdContratoCobranca(vaf.getIdContratoCobranca());

				logOperacaoUsuario.setIdObjeto(String.valueOf(contratoCobranca.getId()));
		     	logOperacaoUsuario.setDescricaoObjeto(contratoCobranca.getDescricaoContrato());		     	 
		     	logOperacaoUsuario.setUsuario(usuarioId);
		     	logOperacaoUsuario.setDescricaoUsuario(uid);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						ContratoCobranca.class, null, contratoCobranca);

				ActionMessage ok = new ActionMessage("msg.contratoCobranca.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setNovaBusca(null);

//				retorno = mapping.findForward("editarContratoCobranca");
//
//				vaf.setOpcao("editar"); // TODO MANDAR PARA PESQUISA
				
				retorno = mapping.findForward("listarContratoCobranca");
				
			} else if (!existeContratoNoPeriodo(vaf)) {
				
				if (contratoCobrancaDLO.contarRelacionamentos(contratoCobrancaConsultaPrevia, ContratoCobranca_.vendas) > 0) {
					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.alteracao.contratoCobranca");
					setMensagemAlerta(erro);
					vaf.setNovaBusca("true"); 
				} else { 
					
					ContratoCobranca contratoCobrancaNovo = (ContratoCobranca) BeanUtils.cloneBean(contratoCobrancaConsultaPrevia);

					contratoCobrancaNovo
							.setEmpresa(empresaDLO.obterPorCodigo(vaf.getCodigoEmpresaContratoCobranca()));
					contratoCobrancaNovo.setMeioPagamento(
							meioPagamentoDLO.obterPorCodigo(vaf.getCodigoMeioPagamentoContratoCobranca()));
					contratoCobrancaNovo
							.setProvedor(provedorDLO.obterPorCodigo(vaf.getCodigoProvedorContratoCobranca()));
					contratoCobrancaNovo.setDescricaoContrato(vaf.getDescricaoContratoCobranca().toUpperCase());
					contratoCobrancaNovo
							.setCodigoEmpresaNoProvedor(vaf.getCodEmpresaProvMeioPagamentoContratoCobranca().toUpperCase());
					contratoCobrancaNovo
							.setPrazoPagamento(Integer.valueOf(vaf.getPrazoPagamentoDiasContratoCobranca()));
					contratoCobrancaNovo.setTipoTransacaoProvedor(vaf.getTipoTransacaoProvedorContratoCobranca());
					contratoCobrancaNovo.setDataInicioVigencia(
							DateUtils.removerHoras(Validador.obterDataPadrao(vaf.getInicioVigenciaContratoCobranca())));
					contratoCobrancaNovo.setDataFimVigencia(
							DateUtils.removerHoras(Validador.obterDataPadrao(vaf.getFimVigenciaContratoCobranca())));
	
					List<Produto> produtosAssociados = new ArrayList<Produto>();
					if (vaf.getProdutosAssociados().isEmpty()) {
						contratoCobrancaNovo.setProdutos((new ArrayList<Produto>()));
					} else {
						String[] produtosAssociadosFromForm = vaf.getProdutosAssociados().split(",");
						for (int i = 0; i < produtosAssociadosFromForm.length; i++) {
							String string = produtosAssociadosFromForm[i];
							produtosAssociados.add(produtoDLO.obter(Long.valueOf(string)));
						}
						contratoCobrancaNovo.setProdutos(produtosAssociados);
					}
	
					contratoCobrancaNovo.setId((Long) contratoCobrancaDLO.manter(contratoCobrancaNovo));
	
					contratoCobrancaNovo = contratoCobrancaDLO.obter(contratoCobrancaConsultaPrevia.getId());
	
					logOperacaoUsuario.setIdObjeto(String.valueOf(contratoCobrancaConsultaPrevia.getId()));
			     	logOperacaoUsuario.setDescricaoObjeto(contratoCobrancaConsultaPrevia.getDescricaoContrato());		     	 
			     	logOperacaoUsuario.setUsuario(usuarioId);
			     	logOperacaoUsuario.setDescricaoUsuario(uid);
	
					vaf.setIdContratoCobranca(contratoCobrancaNovo.getId().toString());
					
					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							ContratoCobranca.class, contratoCobrancaConsultaPrevia, contratoCobrancaNovo);
	
					List<Produto> listaProdutosAssociados = contratoCobrancaConsultaPrevia.getProdutos();
					session.setAttribute("produtosAssociados", listaProdutosAssociados);
					List<Produto> listaProdutosNaoAssociados = produtoDLO
							.obterProdutosNaoRelacionadosAoContrato(contratoCobrancaConsultaPrevia.getId().toString());
					session.setAttribute("produtosNaoAssociados", listaProdutosNaoAssociados);
	
					ActionMessage ok = new ActionMessage("msg.contratoCobranca.alterado.sucesso");
					setMensagemSucesso(ok);
					
					// TODO MANDAR PARA PESQUISA
	//				retorno = mapping.findForward("editarContratoCobranca");
	//
	//				vaf.setOpcao("editar"); 			
					
					retorno = mapping.findForward("listarContratoCobranca");
				
				}
				
			} else {
				
				sucesso = false;
				ActionMessage mensagemAlerta = new ActionMessage("erro.periodo.cobranca.contratoCobranca");
				setMensagemAlerta(mensagemAlerta);
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {
			retorno = mapping.findForward("listarContratoCobranca");
			ContratoCobranca contratoCobrancaConsultaPrevia = contratoCobrancaDLO.obter(Long.valueOf(vaf.getIdContratoCobranca()));
			
			if (contratoCobrancaDLO.isExcluivel(contratoCobrancaConsultaPrevia)) {
				contratoCobrancaDLO.excluir(contratoCobrancaConsultaPrevia);

				logOperacaoUsuario.setIdObjeto(String.valueOf(contratoCobrancaConsultaPrevia.getId()));
		     	logOperacaoUsuario.setDescricaoObjeto(contratoCobrancaConsultaPrevia.getDescricaoContrato());		     	 
		     	logOperacaoUsuario.setUsuario(usuarioId);
		     	logOperacaoUsuario.setDescricaoUsuario(uid);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						ContratoCobranca.class, contratoCobrancaConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.contratoCobranca.excluido.sucesso");
				setMensagemSucesso(ok);

			} else if (contratoCobrancaDLO.contarRelacionamentos(contratoCobrancaConsultaPrevia, ContratoCobranca_.vendas) > 0) {
				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.exclusao.contratoCobranca");
				setMensagemAlerta(erro);
				vaf.setNovaBusca("true");
				
			} else if (contratoCobrancaDLO.contarRelacionamentos(contratoCobrancaConsultaPrevia, ContratoCobranca_.produtos) > 0) {
				ActionMessage erro = new ActionMessage("erro.produto.cadastrado.contratoCobranca");
				setMensagemAlerta(erro);
				vaf.setNovaBusca("true");
				
			} else if (contratoCobrancaDLO.contarRelacionamentos(contratoCobrancaConsultaPrevia, ContratoCobranca_.cobrancas) > 0) {
				ActionMessage erro = new ActionMessage("erro.cobranca.cadastrada.contratoCobranca");
				setMensagemAlerta(erro);
				vaf.setNovaBusca("true");
				
			} else {
				throw new DLOException(CodigoMensagem.Falha); 
			}
		}

//		if (Validador.vazio(uaf.getEmpresa())) {
//			uaf.setCodigoEmpresaContratoCobranca("");
//		}
//
//		if (Validador.vazio(vaf.getProvedorContratoCobranca())) {
//			vaf.setCodigoProvedorContratoCobranca("");
//		}
		
		if (sucesso && vaf.getNovaBusca() != null) {
	        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
	        	session.setAttribute("pesquisarSessaoForm", vaf);
	        } else {
	        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
	        	if (caf != null) {
	        		BeanUtils.copyProperties(vaf, caf);
	        	}
	        }
		}

		return retorno;
	}

	public boolean existeContratoNoPeriodo(ConfiguracaoActionForm vaf)
			throws br.com.delphos.billing.excecoes.DLOException, ParseException {

		ContratoCobranca contratoCobranca = new ContratoCobranca();
		
		if (vaf.getIdContratoCobranca() != null && !vaf.getIdContratoCobranca().isEmpty()) {
			contratoCobranca = contratoCobrancaDLO.obter(Long.parseLong(vaf.getIdContratoCobranca()));
		}

		contratoCobranca.setEmpresa(
				empresaDLO.obterPorCodigo(vaf.getCodigoEmpresaContratoCobranca()));
		contratoCobranca.setMeioPagamento(
				meioPagamentoDLO.obterPorCodigo(vaf.getCodigoMeioPagamentoContratoCobranca()));
		contratoCobranca.setProvedor(
				provedorDLO.obterPorCodigo(vaf.getCodigoProvedorContratoCobranca()));
		contratoCobranca.setDataInicioVigencia(
				DateUtils.removerHoras(Validador.obterDataPadrao(vaf.getInicioVigenciaContratoCobranca())));
		contratoCobranca.setDataFimVigencia(
				DateUtils.removerHoras(Validador.obterDataPadrao(vaf.getFimVigenciaContratoCobranca())));

		boolean existeContrato = contratoCobrancaDLO.existeContratoNoPeriodo(contratoCobranca);
		return existeContrato;
	}

}