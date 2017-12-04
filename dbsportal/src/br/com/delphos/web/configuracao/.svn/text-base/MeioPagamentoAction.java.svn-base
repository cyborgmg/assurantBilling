package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.billing.meiosPagamento.MeioPagamento_;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class MeioPagamentoAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("listarMeioPagamento");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();
		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		String operacao = request.getParameter("operacao");

		MeioPagamentoDLO meioPagamentoDLO = (MeioPagamentoDLO) ServiceLocator
				.lookup("java:/global/dbsdb/MeioPagamentoDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
		if (vaf.getOpcao().equals("listarMeioPagamento") || vaf.getOpcao().equals("1")) {
		
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarMeioPagamento");

			MeioPagamento meioPagamento = new MeioPagamento();
			MeioPagamento meioPagamentoConsultaPrevia = null;

			if (!vaf.getIdMeioPagamento().trim().isEmpty()) {
				meioPagamentoConsultaPrevia = meioPagamentoDLO.obter(Long.valueOf(vaf.getIdMeioPagamento()));
			}

			if (meioPagamentoConsultaPrevia == null) {
				meioPagamento.setCodigo(vaf.getCodigoMeioPagamento().toUpperCase());
				meioPagamento.setDescricao(vaf.getDescricaoMeioPagamento().toUpperCase());
				meioPagamento.setLimiteDiasGerarRetentativa(Integer.valueOf(vaf.getLimiteDiasRetentativa()));
				meioPagamentoDLO.manter(meioPagamento);

				MeioPagamento meioPagamentoNovo = meioPagamentoDLO.obterPorCodigo((meioPagamento.getCodigo()));

         	   logOperacaoUsuario.setIdObjeto(String.valueOf(meioPagamentoNovo.getId()));
         	   logOperacaoUsuario.setDescricaoObjeto(meioPagamentoNovo.getDescricao());
         	  
         	   logOperacaoUsuario.setUsuario(usuarioId);
         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						MeioPagamento.class, null, meioPagamentoNovo);

				ActionMessage ok = new ActionMessage("msg.meioPagamento.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setListaAtiva("true");
				vaf.setNovaBusca(null);

			} else {
				meioPagamentoConsultaPrevia = meioPagamentoDLO.completar(meioPagamentoConsultaPrevia,
						MeioPagamento_.contratosCobranca);

				if (meioPagamentoConsultaPrevia.getContratosCobranca().size() == 0) {
					MeioPagamento meioPagamentoNovo = new MeioPagamento();
					meioPagamentoNovo.setId(meioPagamentoConsultaPrevia.getId());
					meioPagamentoNovo.setCodigo(vaf.getCodigoMeioPagamento().toUpperCase());
					meioPagamentoNovo.setDescricao(vaf.getDescricaoMeioPagamento().toUpperCase());
					meioPagamentoNovo.setLimiteDiasGerarRetentativa(Integer.valueOf(vaf.getLimiteDiasRetentativa()));

					meioPagamentoDLO.salvar(meioPagamentoNovo);

					meioPagamentoNovo = meioPagamentoDLO.obter(meioPagamentoNovo.getId());

		         	   logOperacaoUsuario.setIdObjeto(String.valueOf(meioPagamentoNovo.getId()));
		         	   logOperacaoUsuario.setDescricaoObjeto(meioPagamentoNovo.getDescricao());
		         	  
		         	   logOperacaoUsuario.setUsuario(usuarioId);
		         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							MeioPagamento.class, meioPagamentoConsultaPrevia, meioPagamentoNovo);

					ActionMessage ok = new ActionMessage("msg.meioPagamento.alterado.sucesso");
					setMensagemSucesso(ok);
					
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

				} else {
      				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.meioPagamento");
      				setMensagemAlerta(erro);
				}
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarMeioPagamento");

			MeioPagamento meioPagamentoConsultaPrevia = meioPagamentoDLO.obter(Long.valueOf(vaf.getIdMeioPagamento()));
			meioPagamentoConsultaPrevia = meioPagamentoDLO.completar(meioPagamentoConsultaPrevia,
					MeioPagamento_.contratosCobranca);

			if (meioPagamentoConsultaPrevia.getContratosCobranca().size() == 0) { // exclusão
																					// (*
																					// desde
																					// que
																					// não
																					// haja
																					// venda
																					// cadastrada)

				meioPagamentoDLO.excluir(meioPagamentoConsultaPrevia);

				// MeioPagamento meioPagamentoNovo =
				// meioPagamentoDLO.obterPorCodigo((meioPagamento.getCodigo()));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(meioPagamentoConsultaPrevia.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(meioPagamentoConsultaPrevia.getDescricao());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						MeioPagamento.class, meioPagamentoConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.meioPagamento.excluido.sucesso");
				setMensagemSucesso(ok);


			} else {
  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.meioPagamento");
  				setMensagemAlerta(erro);
			}

		}
		
		if (vaf.getListaAtiva() != null  && (!"true".equals(vaf.getListaAtiva()) || vaf.getListaAtiva().trim().length() == 0) && (vaf.getNovaBusca() != null && vaf.getNovaBusca().equals("true"))) {
			vaf.setCodigoMeioPagamento("");
			vaf.setDescricaoMeioPagamento("");
			vaf.setLimiteDiasRetentativa("");		
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

		return retorno;
	}

}