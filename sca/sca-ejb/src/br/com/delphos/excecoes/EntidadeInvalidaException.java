package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class EntidadeInvalidaException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	public EntidadeInvalidaException() {
		super();
	}

	public EntidadeInvalidaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public EntidadeInvalidaException(String m, Throwable t) {
		super(m, t);
	}

	public EntidadeInvalidaException(String m) {
		super(m);
	}

	public EntidadeInvalidaException(Throwable t) {
		super(t);
	}

}
