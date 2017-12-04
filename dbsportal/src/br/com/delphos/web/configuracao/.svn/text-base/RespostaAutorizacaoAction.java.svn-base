package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacao;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacaoDLO;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class RespostaAutorizacaoAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		String operacao = request.getParameter("operacao");

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();

		RespostaAutorizacaoDLO respostaAutorizacaoDLO = (RespostaAutorizacaoDLO) ServiceLocator
				.lookup("java:/global/dbsdb/RespostaAutorizacaoDLOBean");
		ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");

		retorno = mapping.findForward("editarRespostaAutorizacao");

		RespostaAutorizacao respostaAutorizacaoConsultaPrevia = null;
		RespostaAutorizacao respostaAutorizacao = new RespostaAutorizacao();

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		if ("2".equalsIgnoreCase(operacao)) {
			if (!vaf.getIdRespostaAutorizacao().trim().isEmpty()) {
				respostaAutorizacaoConsultaPrevia = respostaAutorizacaoDLO
						.obter(Long.valueOf(vaf.getIdRespostaAutorizacao()));
			}

			if (respostaAutorizacaoConsultaPrevia == null) {
				respostaAutorizacao.setCodigoResposta((vaf.getCodigoRespostaAutorizacao()));
				respostaAutorizacao.setDescricaoRespostaProvedor(vaf.getDescricaoRespostaAutorizacaoProvedor());
				respostaAutorizacao.setDescricaoRespostaConsulta(vaf.getDescricaoRespostaAutorizacaoConsulta());
				if (!Validador.vazio(vaf.getQuantidadeRetentativas())) {
					int quantidadeRetentativas = Integer.parseInt(vaf.getQuantidadeRetentativas());
					respostaAutorizacao.setQuantidadeRetentativa(quantidadeRetentativas);
					if (quantidadeRetentativas > 0) {
						respostaAutorizacao.setTipoIntervalo(vaf.getTipoIntervaloRetentativas());
						respostaAutorizacao.setValorIntervalo(Integer.valueOf(vaf.getIntervaloEntreRetentativas()));
					} else {
						respostaAutorizacao.setTipoIntervalo(null);
						respostaAutorizacao.setValorIntervalo(null);
					}
				}
				respostaAutorizacao
						.setContratoCobranca(contratoCobrancaDLO.obter(Long.valueOf(vaf.getIdContratoCobranca())));

				RespostaAutorizacao respostaAutorizacaoNovo = respostaAutorizacaoDLO
						.obter((Long) respostaAutorizacaoDLO.manter(respostaAutorizacao));

				vaf.setIdRespostaAutorizacao(respostaAutorizacaoNovo.getId().toString());

		      	   logOperacaoUsuario.setIdObjeto(String.valueOf(respostaAutorizacaoNovo.getId()));
		      	   logOperacaoUsuario.setDescricaoObjeto(respostaAutorizacaoNovo.getCodigoResposta());
		      	  
		      	   logOperacaoUsuario.setUsuario(usuarioId);
		      	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						RespostaAutorizacao.class, null, respostaAutorizacaoNovo);

				ActionMessage ok = new ActionMessage("msg.respostaAutorizacao.incluido.sucesso");
				setMensagemSucesso(ok);
				
				retorno = mapping.findForward("editarRespostaAutorizacao");
				
				vaf.setIdContratoCobranca(String.valueOf(respostaAutorizacaoNovo.getContratoCobranca().getId()));
				vaf.setOpcao("respostaAutorizacao");
				limparCamposCadastro(vaf);

			} else {
				ContratoCobranca cc = contratoCobrancaDLO.completar(respostaAutorizacaoConsultaPrevia.getContratoCobranca(), ContratoCobranca_.vendas.getName());
				boolean temVenda = cc.getVendas().size() > 0;

				if (!temVenda) { // alteração (* desde que não haja venda
									// cadastrada)

					RespostaAutorizacao respostaAutorizacaoNovo = new RespostaAutorizacao();
					respostaAutorizacaoNovo.setId(respostaAutorizacaoConsultaPrevia.getId());
					respostaAutorizacaoNovo.setCodigoResposta((vaf.getCodigoRespostaAutorizacao()));
					respostaAutorizacaoNovo.setDescricaoRespostaProvedor(vaf.getDescricaoRespostaAutorizacaoProvedor());
					respostaAutorizacaoNovo.setDescricaoRespostaConsulta(vaf.getDescricaoRespostaAutorizacaoConsulta());
					if (!Validador.vazio(vaf.getQuantidadeRetentativas())) {
						int quantidadeRetentativas = Integer.parseInt(vaf.getQuantidadeRetentativas());
						respostaAutorizacaoNovo.setQuantidadeRetentativa(quantidadeRetentativas);
						if (quantidadeRetentativas > 0) {
							respostaAutorizacaoNovo.setTipoIntervalo(vaf.getTipoIntervaloRetentativas());
							respostaAutorizacaoNovo.setValorIntervalo(Integer.valueOf(vaf.getIntervaloEntreRetentativas()));
						}
					}
					respostaAutorizacaoNovo
							.setContratoCobranca(respostaAutorizacaoConsultaPrevia.getContratoCobranca());

					respostaAutorizacaoNovo = respostaAutorizacaoDLO
							.obter((Long) (respostaAutorizacaoDLO.manter(respostaAutorizacaoNovo)));

			      	   logOperacaoUsuario.setIdObjeto(String.valueOf(respostaAutorizacaoNovo.getId()));
			      	   logOperacaoUsuario.setDescricaoObjeto(respostaAutorizacaoNovo.getCodigoResposta());
			      	  
			      	   logOperacaoUsuario.setUsuario(usuarioId);
			      	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							RespostaAutorizacao.class, respostaAutorizacaoConsultaPrevia, respostaAutorizacaoNovo);

					vaf.setDescricaoContratoCobranca(
							respostaAutorizacaoNovo.getContratoCobranca().getDescricaoContrato());
					vaf.setIdContratoCobranca(respostaAutorizacaoNovo.getContratoCobranca().getId().toString());

					ActionMessage ok = new ActionMessage("msg.respostaAutorizacao.alterado.sucesso");
					setMensagemSucesso(ok);
					limparCamposCadastro(vaf);

				} else {
	  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.respostaAutorizacao");
	  				setMensagemAlerta(erro);
				}
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {
			retorno = mapping.findForward("editarRespostaAutorizacao");

			if (!vaf.getIdRespostaAutorizacao().trim().isEmpty()) {
				respostaAutorizacaoConsultaPrevia = respostaAutorizacaoDLO
						.obter(Long.valueOf(vaf.getIdRespostaAutorizacao()));
			}
			ContratoCobranca cc = contratoCobrancaDLO.completar(respostaAutorizacaoConsultaPrevia.getContratoCobranca(), ContratoCobranca_.vendas.getName());
			boolean temVenda = cc.getVendas().size() > 0;

			if (!temVenda) { // alteração (* desde que não haja venda
								// cadastrada)

				respostaAutorizacaoDLO.excluir(respostaAutorizacaoConsultaPrevia);

		      	   logOperacaoUsuario.setIdObjeto(String.valueOf(respostaAutorizacaoConsultaPrevia.getId()));
		      	   logOperacaoUsuario.setDescricaoObjeto(respostaAutorizacaoConsultaPrevia.getCodigoResposta());
		      	  
		      	   logOperacaoUsuario.setUsuario(usuarioId);
		      	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						RespostaAutorizacao.class, respostaAutorizacaoConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.respostaAutorizacao.excluida.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setOpcao("respostaAutorizacao");
				limparCamposCadastro(vaf);

			} else {
  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.respostaAutorizacao");
  				setMensagemAlerta(erro);
			}

		}

		return retorno;
	}
	
	
	private void limparCamposCadastro(ConfiguracaoActionForm form) {
		
		form.setCodigoRespostaAutorizacao("");
		form.setDescricaoRespostaAutorizacaoProvedor("");
		form.setDescricaoRespostaAutorizacaoConsulta("");
		form.setQuantidadeRetentativas("");
		form.setTipoIntervaloRetentativas("");
		form.setIntervaloEntreRetentativas("");
	}
}