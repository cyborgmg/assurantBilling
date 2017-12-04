package br.com.delphos.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.EntidadeExistenteDLOException;
import br.com.delphos.billing.util.Mensagens;

public abstract class StrutsAction extends Action {
	public static Logger LOGGER = LoggerFactory.getLogger(StrutsAction.class);

	private ActionMessages mensagensSucesso = new ActionMessages();
	private ActionMessages mensagensAviso = new ActionMessages();
	private ActionMessages mensagensErro = new ActionMessages();
	
	public abstract ActionForward executarAcao(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward retorno = mapping.getInputForward();
		
		try {
			retorno = executarAcao(mapping, form, request, response);
			
		} catch (EntidadeExistenteDLOException e) {
			ActionMessage erro = new ActionMessage("erro.entidade.existente");
			setMensagemAlerta(erro);
			
		} catch (BillingException e) {
			ActionMessage erro = new ActionMessage(e.getLocalizedMessage(), false);
			setMensagemAlerta(erro);
			
		} catch (Exception e) {
			LOGGER.error(null, e);
			ActionMessage erro = new ActionMessage(Mensagens.get(CodigoMensagem.Falha), false);
			setMensagemAlerta(erro);
		}

		this.addMessages(request, mensagensSucesso);
		this.addMessages(request, mensagensAviso);
		this.addErrors(request, mensagensErro);
		this.mensagensSucesso = new ActionMessages();
		this.mensagensAviso = new ActionMessages();
		this.mensagensErro = new ActionMessages();
		
		return retorno;
	}
	
	public void addMensagemSucesso(String propriedade, ActionMessage mensagem) {
		this.mensagensSucesso.add(propriedade, mensagem);
	}
	
	public void addMensagemAviso(String propriedade, ActionMessage mensagem) {
		this.mensagensAviso.add(propriedade, mensagem);
	}
	
	public void addMensagemErro(String propriedade, ActionMessage mensagem) {
		this.mensagensErro.add(propriedade, mensagem);
	}
	
	public void setMensagemAlerta(ActionMessage mensagem) {
		this.addMensagemErro("dialogoAlerta", mensagem);
	}
	
	public void setMensagemSucesso(ActionMessage mensagem) {
		this.addMensagemSucesso("dialogoSucesso", mensagem);
	}
}
