package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.StrutsAction;

public class ListaValorAction extends StrutsAction {

	private SCAClient client = null;

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

		ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
		ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator
				.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator
				.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
		if (vaf.getOpcao().equals("listarListaValor") || vaf.getOpcao().equals("ignorarValidacao")) {
			
			if (vaf.getOpcao().equals("ignorarValidacao")) {
				session.removeAttribute("pesquisarSessaoForm");
			}
		
		}

		if ("2".equalsIgnoreCase(operacao)) {

			retorno = mapping.findForward("listarListaValor");

			ListaValor listaValor = new ListaValor();
			ListaValor listaValorConsultaPrevia = null;
			if (!vaf.getIdListaValor().trim().isEmpty()) {
				listaValorConsultaPrevia = listaValorDLO.obter(Long.valueOf(vaf.getIdListaValor()));
			}

			if (listaValorConsultaPrevia == null) {
				listaValor.setCodigo(vaf.getCodigoListaValor().toUpperCase());
				listaValor.setDescricao(vaf.getDescricaoListaValor().toUpperCase());
				listaValor.setTipoUso(vaf.getTipoDeUsoListaValor());
				listaValor.setStatus(vaf.getStatusListaValor());

				ListaValor listaValorNovo = listaValorDLO.obter((Long) listaValorDLO.manter(listaValor));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(listaValorNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(listaValorNovo.getDescricao());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.INCLUSAO,
						ListaValor.class, null, listaValorNovo);

				ActionMessage ok = new ActionMessage("msg.listaValor.incluido.sucesso");
				setMensagemSucesso(ok);
				
				vaf.setNovaBusca(null);

			} else {
				ListaValor listaValorNovo = new ListaValor();

				listaValorNovo.setId(Long.valueOf(vaf.getIdListaValor()));
				listaValorNovo.setCodigo(vaf.getCodigoListaValor().toUpperCase());
				listaValorNovo.setDescricao(vaf.getDescricaoListaValor().toUpperCase());
				listaValorNovo.setTipoUso(vaf.getTipoDeUsoListaValor());
				listaValorNovo.setStatus(vaf.getStatusListaValor());

				//listaValorDLO.alterarListaValor(listaValorNovo);

//				listaValorNovo = listaValorDLO.obter(listaValorConsultaPrevia.getId());
				listaValorNovo = listaValorDLO.obter(listaValorDLO.manter(listaValorNovo));

	         	   logOperacaoUsuario.setIdObjeto(String.valueOf(listaValorNovo.getId()));
	         	   logOperacaoUsuario.setDescricaoObjeto(listaValorNovo.getDescricao());
	         	  
	         	   logOperacaoUsuario.setUsuario(usuarioId);
	         	   logOperacaoUsuario.setDescricaoUsuario(descUser);

				logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ALTERACAO,
						ListaValor.class, listaValorConsultaPrevia, listaValorNovo);

				ActionMessage ok = new ActionMessage("msg.listaValor.alterado.sucesso");
				setMensagemSucesso(ok);


			}

		}

		if ("3".equalsIgnoreCase(operacao)) {
			retorno = mapping.findForward("listarListaValor");
			
			ActionMessage ok = null;

			ListaValor listaValorConsultaPrevia = listaValorDLO.obter(Long.valueOf(vaf.getIdListaValor()));
			ListaValor listaValorAntigo = listaValorDLO.obter(listaValorConsultaPrevia.getId());
			
//			listaValorDLO.excluir(listaValorConsultaPrevia);
			
			if (listaValorConsultaPrevia.getStatus().equals("A")) {
				listaValorConsultaPrevia.setStatus("I");
				ok = new ActionMessage("msg.listaValor.inativada.sucesso");
			} else {
				listaValorConsultaPrevia.setStatus("A");
				ok = new ActionMessage("msg.listaValor.ativada.sucesso");
			}
			
			listaValorConsultaPrevia = listaValorDLO.obter(listaValorDLO.manter(listaValorConsultaPrevia));

      	   logOperacaoUsuario.setIdObjeto(String.valueOf(listaValorConsultaPrevia.getId()));
      	   logOperacaoUsuario.setDescricaoObjeto(listaValorConsultaPrevia.getDescricao());
      	  
      	   logOperacaoUsuario.setUsuario(usuarioId);
      	   logOperacaoUsuario.setDescricaoUsuario(descUser);

      	 if (listaValorConsultaPrevia.getStatus().equals("A")) {
			logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.ATIVACAO,
					ListaValor.class, listaValorAntigo, listaValorConsultaPrevia);
      	 } else {
      		logOperacaoUsuarioDLO.registrarLogOperacoes(logOperacaoUsuario, TipoOperacaoUsuario.DESATIVACAO,
					ListaValor.class, listaValorAntigo, listaValorConsultaPrevia);
      	 }

			setMensagemSucesso(ok);

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