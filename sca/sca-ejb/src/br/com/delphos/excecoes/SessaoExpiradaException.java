package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class SessaoExpiradaException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	public SessaoExpiradaException() {
		super();
	}

	public SessaoExpiradaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public SessaoExpiradaException(String m, Throwable t) {
		super(m, t);
	}

	public SessaoExpiradaException(String m) {
		super(m);
	}

	public SessaoExpiradaException(Throwable t) {
		super(t);
	}

}
