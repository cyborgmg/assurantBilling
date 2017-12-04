package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class SenhaIncorretaException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException() {
		super();
	}

	public SenhaIncorretaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public SenhaIncorretaException(String m, Throwable t) {
		super(m, t);
	}

	public SenhaIncorretaException(String m) {
		super(m);
	}

	public SenhaIncorretaException(Throwable t) {
		super(t);
	}

}
