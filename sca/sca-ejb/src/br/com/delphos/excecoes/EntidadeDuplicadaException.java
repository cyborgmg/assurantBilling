package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class EntidadeDuplicadaException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	public EntidadeDuplicadaException() {
		super();
	}

	public EntidadeDuplicadaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public EntidadeDuplicadaException(String m, Throwable t) {
		super(m, t);
	}

	public EntidadeDuplicadaException(String m) {
		super(m);
	}

	public EntidadeDuplicadaException(Throwable t) {
		super(t);
	}

}
