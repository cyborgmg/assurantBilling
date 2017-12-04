package br.com.delphos.excecoes;


import javax.ejb.ApplicationException;
import javax.xml.bind.annotation.XmlTransient;

import br.com.delphos.sca.constantes.CodigoMensagem;
import br.com.delphos.util.Mensagens;

@ApplicationException(rollback = true, inherited = true)
public class DelphosException extends Exception {
	private static final long serialVersionUID = 1L;

	private final CodigoMensagem codigoMensagem;
	private final Object[] argumentosMensagem;
	
	public DelphosException() {
		super();
		this.codigoMensagem = null;
		this.argumentosMensagem = null;
	}

	public DelphosException(String m) {
		super(m);
		this.codigoMensagem = null;
		this.argumentosMensagem = null;
	}
	
	public DelphosException(Throwable t) {
		super(t);
		this.codigoMensagem = null;
		this.argumentosMensagem = null;
	}
	
	public DelphosException(String m, Throwable t) {
		super(m, t);
		this.codigoMensagem = null;
		this.argumentosMensagem = null;
	}
	
	public DelphosException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno.getNomePropriedadeErro());
		this.codigoMensagem = codigoRetorno;
		this.argumentosMensagem = args;
	}

	@XmlTransient
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
	@XmlTransient
	public CodigoMensagem getCodigoMensagem() {
		return codigoMensagem;
	}
	
	@XmlTransient
	@Override
	public String getLocalizedMessage() {
		String mensagem = this.codigoMensagem != null ? this.codigoMensagem.getNomePropriedadeErro() : super.getLocalizedMessage();
		if (mensagem != null) {
			if (argumentosMensagem != null && argumentosMensagem.length > 0) {
				mensagem = Mensagens.mensagemOuCodigo(mensagem, argumentosMensagem);
			} else {
				mensagem = Mensagens.mensagemOuCodigo(mensagem);
			}
		}
		return mensagem;
	}
	
	public String getCodigoErro() {
		return codigoMensagem != null ? codigoMensagem.getCodigo() : null;
	}
	
	public String getMensagemErro() {
		return getLocalizedMessage();
	}
}
