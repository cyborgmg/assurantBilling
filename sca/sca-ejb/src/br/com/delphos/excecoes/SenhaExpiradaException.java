package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class SenhaExpiradaException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	public SenhaExpiradaException() {
		super();
	}

	public SenhaExpiradaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public SenhaExpiradaException(String m, Throwable t) {
		super(m, t);
	}

	public SenhaExpiradaException(String m) {
		super(m);
	}

	public SenhaExpiradaException(Throwable t) {
		super(t);
	}

}
