	package br.com.delphos.web.conciliacao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ControleConciliacaoDLO;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.util.CurrencyUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class ConciliacaoAction extends StrutsAction {

	private SCAClient client = null;
	
	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.getInputForward();
		ConciliacaoActionForm vaf = (ConciliacaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
		ControleConciliacaoDLO controleConciliacaoDLO = (ControleConciliacaoDLO) ServiceLocator.lookup("java:/global/dbsdb/ControleConciliacaoDLOBean");
		
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
		if (vaf.getOpcao() == null) {
			vaf.setOpcao("listarConciliacao");
			retorno = mapping.findForward("listarConciliacao");
		}
		
		if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarConciliacao") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}
		
        if (!Validador.vazio(vaf.getNovaBusca())) {
        	session.setAttribute("pesquisarSessaoForm", vaf);
        } else {
        	ConciliacaoActionForm caf = (ConciliacaoActionForm) session.getAttribute("pesquisarSessaoForm");
        	if (caf != null) {
        		BeanUtils.copyProperties(vaf, caf);
        	}
        }

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarConciliacao");

			ControleConciliacao controleConciliacao = new ControleConciliacao();
			ControleConciliacao controleConciliacaoConsultaPrevia = null;
			
			if (!vaf.getIdControleConciliacao().trim().isEmpty()) {
				controleConciliacaoConsultaPrevia = controleConciliacaoDLO.obter(Long.valueOf(vaf.getIdControleConciliacao()));
			}

			if (controleConciliacaoConsultaPrevia == null) {
				
//				RN 3:	O usuário não poderá incluir um processamento de um adquirente com uma data já cadastrada (está no ActionForm)
//				RN 4:	O usuário poderá incluir o valor zerado para os dias em que não houver movimento bancário. (aceitar valores zerados)

				ControleConciliacao conciliacaoNovo = new ControleConciliacao();
				conciliacaoNovo.setAdquirente(vaf.getNomeAdquirente().toUpperCase());
				conciliacaoNovo.setDataMovimento(Validador.obterDataPadrao(vaf.getDataMovimentoArquivo()));
				conciliacaoNovo.setValorDeposito(CurrencyUtils.fromString(vaf.getValorDeposito()));	
				conciliacaoNovo.setStatus(StatusControleConciliacao.Pendente);

				conciliacaoNovo = controleConciliacaoDLO.obter((Long) controleConciliacaoDLO.manter(conciliacaoNovo));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(conciliacaoNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(conciliacaoNovo.getId().toString()); // TODO VER DESCRIÇÃO PARA LOG
     	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						ControleConciliacao.class, null, conciliacaoNovo);    

				ActionMessage ok = new ActionMessage("msg.inclusao.deposito.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setListaAtiva("true");
				vaf.setNovaBusca(null);
				vaf.setPeriodoDe(vaf.getDataMovimentoArquivo());
				vaf.setPeriodoAte(vaf.getDataMovimentoArquivo());

			} else {

/*				List<ContratoCobranca> listaContratos = contratoCobrancaDLO
						.listarPorAdquirente(conciliacaoInterfaceConsultaPrevia);
				listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
						ContratoCobranca_.vendas);
				boolean temVenda = verificaVendaPorAdquirente(listaContratos);*/

//				if (!temVenda) { // alteração (* desde que não haja venda
//									// cadastrada)

					ControleConciliacao conciliacaoNovo = controleConciliacaoDLO.obter(controleConciliacaoConsultaPrevia.getId());
					conciliacaoNovo.setId(controleConciliacaoConsultaPrevia.getId());
					conciliacaoNovo.setAdquirente(vaf.getNomeAdquirente().toUpperCase());
					conciliacaoNovo.setDataMovimento(Validador.obterDataPadrao(vaf.getDataMovimentoArquivo()));
					conciliacaoNovo.setValorDeposito(CurrencyUtils.fromString(vaf.getValorDeposito()));	
					
					// depois de validado o arquivo (validarValorArquivoConciliacao):
					Adquirente adquirenteParaBusca = new Adquirente();
					adquirenteParaBusca.setNome(vaf.getNomeAdquirente());
					Adquirente adquirente = adquirenteDLO.obterPorIdentidade(adquirenteParaBusca);
					
					//TODO: ANTIGA VALIDACAO DO VALOR DA CONCILIACAO
//					if (controleConciliacaoConsultaPrevia.getDataImportacaoArquivo() != null) {
//						if (!controleConciliacaoDLO.validarValorArquivoConciliacao(adquirente, controleConciliacaoConsultaPrevia.getDataMovimento(), 
//								conciliacaoNovo)) {
//							vaf.setStatusDeposito(conciliacaoNovo.getStatus().getValor());
//							throw new DLOException("erro.valorDeposito.difere.valorArquivo");
//						}
//					}

					controleConciliacaoDLO.validarValorConciliacaoTela(conciliacaoNovo);

					// TODO RN 2:	A data de processamento só poderá ser alterada no caso de o arquivo estiver pendente e não ter sido importado.
					// TODO RN 4:	Ao informar ou alterar o valor do depósito de um arquivo já importado, 
					// esse valor deverá ser comparado com o valor total do arquivo. Se verificar divergência no valor o aviso deverá ser 
					// imediato e o arquivo não poderá ser processado. 
					
				   conciliacaoNovo = controleConciliacaoDLO.obter(controleConciliacaoDLO.manter(conciliacaoNovo));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(conciliacaoNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(conciliacaoNovo.getId().toString()); // TODO VER DESCRIÇÃO PARA LOG
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuario.setUsuario(usuarioId);
					
					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							ControleConciliacao.class, controleConciliacaoConsultaPrevia, conciliacaoNovo);

					ActionMessage ok = new ActionMessage("msg.alteracao.deposito.sucesso");
					setMensagemSucesso(ok);
					
					vaf.setPeriodoDe(vaf.getDataMovimentoArquivo());
					vaf.setPeriodoAte(vaf.getDataMovimentoArquivo());

//				} else {
//					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.adquirente");
//					setMensagemAlerta(erro);
//				}
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarConciliacao");

			ControleConciliacao conciliacaoInterfaceConsultaPrevia = controleConciliacaoDLO.obter(Long.valueOf(vaf.getIdControleConciliacao()));
//			List<ContratoCobranca> listaContratos = contratoCobrancaDLO.listarPorAdquirente(adquirenteConsultaPrevia);
//			listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
//					ContratoCobranca_.vendas);
//			boolean temVenda = verificaVendaPorAdquirente(listaContratos);

//			if (!temVenda) { // exclusão (* desde que não haja venda cadastrada)

			controleConciliacaoDLO.excluir(conciliacaoInterfaceConsultaPrevia);

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(conciliacaoInterfaceConsultaPrevia.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(conciliacaoInterfaceConsultaPrevia.getId().toString()); // TODO VER DESCRIÇÃO PARA LOG
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						ControleConciliacao.class, conciliacaoInterfaceConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.deposito.excluido.sucesso");
				setMensagemSucesso(ok);

//			} else {
//				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.adquirente");
//				setMensagemAlerta(erro);
//			}
		}
		
		if ("4".equalsIgnoreCase(operacao)) { // solicitação de reprocessamento
			
			retorno = mapping.findForward("listarConciliacao");

			ControleConciliacao controleConciliacaoConsultaPrevia = null;
			
			if (!vaf.getIdControleConciliacao().trim().isEmpty()) {
				controleConciliacaoConsultaPrevia = controleConciliacaoDLO.obter(Long.valueOf(vaf.getIdControleConciliacao()));
			}

			if (controleConciliacaoConsultaPrevia != null) {
				
				controleConciliacaoDLO.marcarParaReprocessamento(controleConciliacaoConsultaPrevia);
				
				ActionMessage ok = new ActionMessage("msg.execucao.reprocessamento.sucesso");
				setMensagemSucesso(ok);
				
			}
			
		}
		
		return retorno;
	}


}