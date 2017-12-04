package br.com.delphos.billing.excecoes;

import java.text.MessageFormat;

import javax.xml.bind.annotation.XmlTransient;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.util.Mensagens;

public class BillingException extends Exception {
	private static final long serialVersionUID = 1L;

	private final CodigoMensagem codigoRetorno;
	private final Object[] args;
	
	public BillingException() {
		super();
		this.codigoRetorno = null;
		this.args = null;
	}

	public BillingException(String m) {
		super(m);
		this.codigoRetorno = null;
		this.args = null;
	}
	
	public BillingException(Throwable t) {
		super(t);
		this.codigoRetorno = null;
		this.args = null;
	}
	
	public BillingException(String m, Throwable t) {
		super(m, t);
		this.codigoRetorno = null;
		this.args = null;
	}
	
	public BillingException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno.getNomePropriedadeErro());
		this.codigoRetorno = codigoRetorno;
		this.args = args;
	}

	@XmlTransient
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	@XmlTransient
	public CodigoMensagem getCodigoRetorno() {
		return codigoRetorno;
	}
	
	@XmlTransient
	@Override
	public String getLocalizedMessage() {
		String mensagem = super.getLocalizedMessage();
		if (mensagem != null) {
			mensagem = Mensagens.mensagemOuCodigo(mensagem, args);
		}
		return mensagem;
	}
	
	public String getCodigoErro() {
		return codigoRetorno != null ? codigoRetorno.getCodigo() : null;
	}
	
	public String getMensagemErro() {
		return getLocalizedMessage();
	}
}
