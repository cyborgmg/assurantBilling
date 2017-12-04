package br.com.delphos.web.configuracao;

import java.util.Iterator;
import java.util.List;

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
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class AdquirenteAction extends StrutsAction {

	private SCAClient client = null;
	
	ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator
			.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");

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
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
		if (vaf.getOpcao().equals("listarAdquirente") || vaf.getOpcao().equals("1")) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarAdquirente");

			Adquirente adquirente = new Adquirente();
			Adquirente adquirenteConsultaPrevia = null;
			if (!vaf.getIdAdquirente().trim().isEmpty()) {
				adquirenteConsultaPrevia = adquirenteDLO.obter(Long.valueOf(vaf.getIdAdquirente()));
			}

			if (adquirenteConsultaPrevia == null) {
				adquirente.setNome(vaf.getNomeAdquirente().toUpperCase());
				
				if(vaf.getCodigoAfiliacao() != null && vaf.getCodigoAfiliacao() != ""){
				adquirente.setCodigoAfiliacaoConciliador(Integer.parseInt(vaf.getCodigoAfiliacao()));
				}
				
				adquirente.setCodigoConvenio(vaf.getCodigoConvenioAdquirente().toUpperCase());

				Adquirente adquirenteNovo = adquirenteDLO.obter((Long) adquirenteDLO.manter(adquirente));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(adquirenteNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(adquirenteNovo.getNome());
	         	   
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						Adquirente.class, null, adquirenteNovo);

				ActionMessage ok = new ActionMessage("msg.adquirente.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setListaAtiva("true");
				vaf.setNovaBusca(null);

			} else {

				List<ContratoCobranca> listaContratos = contratoCobrancaDLO
						.listarPorAdquirente(adquirenteConsultaPrevia);
				listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
						ContratoCobranca_.vendas);
				boolean temVenda = verificaVendaPorAdquirente(listaContratos);

				if (!temVenda) { // alteração (* desde que não haja venda
									// cadastrada)

					Adquirente adquirenteNovo = new Adquirente();
					adquirenteNovo.setId(adquirenteConsultaPrevia.getId());
					adquirenteNovo.setNome(vaf.getNomeAdquirente().toUpperCase());
					if(vaf.getCodigoAfiliacao() != "" && vaf.getCodigoAfiliacao() != null){
						adquirenteNovo.setCodigoAfiliacaoConciliador(Integer.parseInt(vaf.getCodigoAfiliacao()));
					}
					adquirenteNovo.setCodigoConvenio(vaf.getCodigoConvenioAdquirente().toUpperCase());

					adquirenteDLO.manter(adquirenteNovo);

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(adquirenteNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(adquirenteNovo.getNome());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuario.setUsuario(usuarioId);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							Adquirente.class, adquirenteConsultaPrevia, adquirenteNovo);

					ActionMessage ok = new ActionMessage("msg.adquirente.alterado.sucesso");
					setMensagemSucesso(ok);

				} else {
					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.adquirente");
					setMensagemAlerta(erro);
				}
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarAdquirente");

			Adquirente adquirenteConsultaPrevia = adquirenteDLO.obter(Long.valueOf(vaf.getIdAdquirente()));
			List<ContratoCobranca> listaContratos = contratoCobrancaDLO.listarPorAdquirente(adquirenteConsultaPrevia);
			listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
					ContratoCobranca_.vendas);
			boolean temVenda = verificaVendaPorAdquirente(listaContratos);

			if (!temVenda) { // exclusão (* desde que não haja venda cadastrada)

				adquirenteDLO.excluir(adquirenteConsultaPrevia);

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(adquirenteConsultaPrevia.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(adquirenteConsultaPrevia.getNome());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						Adquirente.class, adquirenteConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.adquirente.excluido.sucesso");
				setMensagemSucesso(ok);

			} else {
				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.adquirente");
				setMensagemAlerta(erro);
			}
		}
		
		if (vaf.getNovaBusca() != null) {
	        if (!Validador.vazio(vaf.getNovaBusca()) && vaf.getNovaBusca().equals("true")) {
	        	session.setAttribute("pesquisarSessaoForm", vaf);
	        } else {
	        	if (!vaf.getNovaBusca().equals("true")) {
		        	ConfiguracaoActionForm caf = (ConfiguracaoActionForm) session.getAttribute("pesquisarSessaoForm");
		        	if (caf != null) {
		        		BeanUtils.copyProperties(vaf, caf);
		        	}
	        	}
	        }
		}

		return retorno;
	}

	private boolean verificaVendaPorAdquirente(List<ContratoCobranca> listaContratos) {
		boolean temVenda = false;

		for (Iterator<ContratoCobranca> iterator = listaContratos.iterator(); iterator.hasNext();) {
			ContratoCobranca contratoCobranca = (ContratoCobranca) iterator.next();
			contratoCobranca = contratoCobrancaDLO.completar(contratoCobranca, ContratoCobranca_.vendas.getName());
			if (contratoCobranca.getVendas().size() > 0) {
				temVenda = true;
				break;
			}
		}
		return temVenda;
	}

}