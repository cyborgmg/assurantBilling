package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class SenhaInvalidaException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
		super();
	}

	public SenhaInvalidaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public SenhaInvalidaException(String m, Throwable t) {
		super(m, t);
	}

	public SenhaInvalidaException(String m) {
		super(m);
	}

	public SenhaInvalidaException(Throwable t) {
		super(t);
	}

}
