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
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.sistemas.Sistema_;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class SistemaAction extends StrutsAction {

	private SCAClient client = null;

	@Override
	public ActionForward executarAcao(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// --- inicializações gerais
		ActionForward retorno = mapping.findForward("listarSistema");
		ConfiguracaoActionForm vaf = (ConfiguracaoActionForm) form;

		if (Validador.vazio(vaf.getProduto())) {
			vaf.setCodigoProduto("");
		}

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();
		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		String operacao = request.getParameter("operacao");

		SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:global/dbsdb/SistemaDLOBean");
		ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:global/dbsdb/ProdutoDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:global/dbsdb/LogOperacaoUsuarioDLOBean");

		Sistema sistema = new Sistema();
		Sistema sistemaConsultaPrevia = null;
		
		if (vaf.getOpcao() != null && (vaf.getOpcao().equals("listarSistema") || vaf.getOpcao().equals("1"))) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		}
		
		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarSistema");

			if (!vaf.getIdSistema().trim().isEmpty()) {
				sistemaConsultaPrevia = sistemaDLO.obter(Long.valueOf(vaf.getIdSistema()));
				sistemaConsultaPrevia = sistemaDLO.completar(sistemaConsultaPrevia, Sistema_.vendas);
			}

			if (sistemaConsultaPrevia == null) {
				sistema.setCodigo(vaf.getCodigoSistema().toUpperCase());
				sistema.setCodigoSistemaEnvio(vaf.getSistemaEnvioInformacoesSistema().toUpperCase()); // TODO
																										// VER
																										// INSERÇÃO
																										// PARA
																										// Domínio:
																										// Acsel/X
																										// ou
																										// Elita
				sistema.setDescricao(vaf.getDescricaoSistema().toUpperCase());

				Sistema sistemaNovo = sistemaDLO.obter(sistemaDLO.manter(sistema));

         	   logOperacaoUsuario.setIdObjeto(String.valueOf(sistemaNovo.getId()));
         	   logOperacaoUsuario.setDescricaoObjeto(sistemaNovo.getDescricao());
         	  
         	   logOperacaoUsuario.setUsuario(usuarioId);
         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						Sistema.class, null, sistemaNovo);

				ActionMessage ok = new ActionMessage("msg.sistema.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setListaAtiva("true");
				vaf.setNovaBusca(null);

			} else {
				
				sistemaConsultaPrevia = sistemaDLO.completar(sistemaConsultaPrevia, Sistema_.vendas.getName());
				int contagemVendasParaSistema = sistemaConsultaPrevia.getVendas().size();

				if (contagemVendasParaSistema == 0) { // alteração (* desde que
														// não haja venda
														// cadastrada)

					Sistema sistemaNovo = new Sistema();
					sistemaNovo.setId(Long.valueOf(vaf.getIdSistema()));
					sistemaNovo.setCodigo(vaf.getCodigoSistema().toUpperCase());
					sistemaNovo.setCodigoSistemaEnvio(vaf.getSistemaEnvioInformacoesSistema().toUpperCase()); // TODO
																												// VER
																												// INSERÇÃO
																												// PARA
																												// Domínio:
																												// Acsel/X
																												// ou
																												// Elita
					sistemaNovo.setDescricao(vaf.getDescricaoSistema().toUpperCase());

					sistemaNovo = sistemaDLO.obter(sistemaDLO.manter(sistemaNovo));

		         	   logOperacaoUsuario.setIdObjeto(String.valueOf(sistemaNovo.getId()));
		         	   logOperacaoUsuario.setDescricaoObjeto(sistemaNovo.getDescricao());
		         	  
		         	   logOperacaoUsuario.setUsuario(usuarioId);
		         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							Sistema.class, sistemaConsultaPrevia, sistemaNovo);

					ActionMessage ok = new ActionMessage("msg.sistema.alterado.sucesso");
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
	  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.sistema");
	  				setMensagemAlerta(erro);
				}

			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarSistema");

			sistemaConsultaPrevia = sistemaDLO.obter(Long.valueOf(vaf.getIdSistema()));
			sistemaConsultaPrevia = sistemaDLO.completar(sistemaConsultaPrevia, Sistema_.vendas);

			Long contagemVendasParaSistema = Long.valueOf(sistemaConsultaPrevia.getVendas().size());

			if (contagemVendasParaSistema == 0) { // exclusão (* desde que não
													// haja venda cadastrada)

				sistemaDLO.excluir(sistemaConsultaPrevia);

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(sistemaConsultaPrevia.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(sistemaConsultaPrevia.getDescricao());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						Sistema.class, sistemaConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.sistema.excluido.sucesso");
				setMensagemSucesso(ok);


			} else {
  				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.sistema");
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