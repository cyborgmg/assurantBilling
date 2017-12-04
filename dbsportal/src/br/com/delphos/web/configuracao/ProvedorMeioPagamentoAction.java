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
import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.billing.provedores.Provedor_;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class ProvedorMeioPagamentoAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.getInputForward();
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		String operacao = request.getParameter("operacao");

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		ProvedorDLO provedorDLO = (ProvedorDLO) ServiceLocator.lookup("java:/global/dbsdb/ProvedorDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
		if (vaf.getOpcao().equals("listarProvedor") || vaf.getOpcao().equals("1")) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarProvedor");

			Provedor provedor = new Provedor();

			Provedor provedorConsultaPrevia = null;

			if (!vaf.getIdProvedorMeioPagamento().trim().isEmpty()) {
				provedorConsultaPrevia = provedorDLO.obter(Long.valueOf(vaf.getIdProvedorMeioPagamento()));
			}

			if (provedorConsultaPrevia == null) {
				provedor.setCodigoProvedor(vaf.getCodigoProvedor().toUpperCase());
				provedor.setDescricaoProvedor(vaf.getDescricaoProvedor().toUpperCase());
				provedorDLO.manter(provedor);

				Provedor provedorNovo = provedorDLO.obterPorCodigo(provedor.getCodigoProvedor());

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(provedorNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(provedorNovo.getDescricaoProvedor());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						Provedor.class, null, provedorNovo);

				ActionMessage ok = new ActionMessage("msg.provedorMeioPagamento.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setListaAtiva("true");
				vaf.setNovaBusca(null);

			} else {
				provedorConsultaPrevia = provedorDLO.completar(provedorConsultaPrevia, Provedor_.contratosCobranca);

				if (provedorConsultaPrevia.getContratosCobranca().size() == 0) {
					Provedor provedorNovo = new Provedor();
					provedorNovo.setId(provedorConsultaPrevia.getId());
					provedorNovo.setCodigoProvedor(vaf.getCodigoProvedor().toUpperCase());
					provedorNovo.setDescricaoProvedor(vaf.getDescricaoProvedor().toUpperCase());

					provedorDLO.manter(provedorNovo);

					provedorNovo = provedorDLO.obter(provedorNovo.getId());

		         	   logOperacaoUsuario.setIdObjeto(String.valueOf(provedorNovo.getId()));
		         	   logOperacaoUsuario.setDescricaoObjeto(provedorNovo.getDescricaoProvedor());
		         	  
		         	   logOperacaoUsuario.setUsuario(usuarioId);
		         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							Provedor.class, provedorConsultaPrevia, provedorNovo);

					ActionMessage ok = new ActionMessage("msg.provedorMeioPagamento.alterado.sucesso");
					setMensagemSucesso(ok);

				} else {
	  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.provedorMeioPagamento");
	  				setMensagemAlerta(erro);
				}
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {
			retorno = mapping.findForward("listarProvedor");

			Provedor provedorConsultaPrevia = provedorDLO.obter(Long.valueOf(vaf.getIdProvedorMeioPagamento()));
			provedorConsultaPrevia = provedorDLO.completar(provedorConsultaPrevia, Provedor_.contratosCobranca);

			if (provedorConsultaPrevia.getContratosCobranca().size() == 0) { // exclusão
																				// (*
																				// desde
																				// que
																				// não
																				// haja
																				// venda
																				// cadastrada)

				provedorDLO.excluir(provedorConsultaPrevia);

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(provedorConsultaPrevia.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(provedorConsultaPrevia.getDescricaoProvedor());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						Provedor.class, provedorConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.provedorMeioPagamento.excluido.sucesso");
				setMensagemSucesso(ok);


			} else {
  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.provedorMeioPagamento");
  				setMensagemAlerta(erro);
			}
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