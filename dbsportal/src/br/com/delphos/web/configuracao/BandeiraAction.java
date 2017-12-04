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

import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class BandeiraAction extends StrutsAction {

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

		LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();

		HttpSession session = request.getSession();
		String uid = (String) session.getAttribute("corporativo_uid");
		String descUser = (String) session.getAttribute("corporativo_usuario");
		client = new SCAClient();
		Long usuarioId = client.obterPorNomeLogin(uid).getId();

		BandeiraDLO bandeiraDLO = (BandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/BandeiraDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
		if (vaf.getOpcao().equals("listarBandeira") || vaf.getOpcao().equals("1")) {
			
			if (vaf.getOpcao().equals("1")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarBandeira");

			Bandeira bandeira = new Bandeira();
			Bandeira bandeiraConsultaPrevia = null;
			if (!vaf.getIdBandeira().trim().isEmpty()) {
				bandeiraConsultaPrevia = bandeiraDLO.obter(Long.valueOf(vaf.getIdBandeira()));
			}

			if (bandeiraConsultaPrevia == null) {
				bandeira.setNomeBandeira(vaf.getNomeBandeira().toUpperCase());
				bandeira.setCodigoBandeira(vaf.getCodigoBandeira().toUpperCase());

				Bandeira bandeiraNovo = bandeiraDLO.obter(bandeiraDLO.manter(bandeira));

         	   logOperacaoUsuario.setIdObjeto(String.valueOf(bandeiraNovo.getId()));
         	   logOperacaoUsuario.setDescricaoObjeto(bandeiraNovo.getNomeBandeira());
         	  
         	   logOperacaoUsuario.setUsuario(usuarioId);
         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						Bandeira.class, null, bandeiraNovo);

				ActionMessage ok = new ActionMessage("msg.bandeira.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setNovaBusca(null);

			} else {
				List<ContratoCobranca> listaContratos = contratoCobrancaDLO.listarPorBandeira(bandeiraConsultaPrevia);
				listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
						ContratoCobranca_.vendas);
				boolean temVenda = verificaVendaPorBandeira(listaContratos);

				if (!temVenda) { // alteração (* desde que não haja venda
									// cadastrada)

					Bandeira bandeiraNovo = new Bandeira();
					bandeiraNovo.setId(bandeiraConsultaPrevia.getId());
					bandeiraNovo.setNomeBandeira(vaf.getNomeBandeira().toUpperCase());
					bandeiraNovo.setCodigoBandeira(vaf.getCodigoBandeira().toUpperCase());

					bandeiraNovo = bandeiraDLO.obter((Long) bandeiraDLO.manter(bandeiraNovo));

		         	   logOperacaoUsuario.setIdObjeto(String.valueOf(bandeiraNovo.getId()));
		         	   logOperacaoUsuario.setDescricaoObjeto(bandeiraNovo.getNomeBandeira());
		         	  
		         	   logOperacaoUsuario.setUsuario(usuarioId);
		         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

					logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
							Bandeira.class, bandeiraConsultaPrevia, bandeiraNovo);

					ActionMessage ok = new ActionMessage("msg.bandeira.alterado.sucesso");
					setMensagemSucesso(ok);

				} else {
					ActionMessage erro = new ActionMessage("erro.venda.cadastrada.bandeira");
					setMensagemAlerta(erro);
				}
			}

		}

		if ("3".equalsIgnoreCase(operacao)) {
			retorno = mapping.findForward("listarBandeira");

			Bandeira bandeiraConsultaPrevia = bandeiraDLO.obter(Long.valueOf(vaf.getIdBandeira()));
			List<ContratoCobranca> listaContratos = contratoCobrancaDLO.listarPorBandeira(bandeiraConsultaPrevia);
			listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
					ContratoCobranca_.vendas);
			boolean temVenda = verificaVendaPorBandeira(listaContratos);

			if (!temVenda) { // alteração (* desde que não haja venda
								// cadastrada)

				bandeiraDLO.excluir(bandeiraConsultaPrevia);

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(bandeiraConsultaPrevia.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(bandeiraConsultaPrevia.getNomeBandeira());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.EXCLUSAO,
						Bandeira.class, bandeiraConsultaPrevia, null);

				ActionMessage ok = new ActionMessage("msg.bandeira.excluida.sucesso");
				setMensagemSucesso(ok);

			} else {
				ActionMessage erro = new ActionMessage("erro.venda.cadastrada.bandeira");
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

	private boolean verificaVendaPorBandeira(List<ContratoCobranca> listaContratos) {
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