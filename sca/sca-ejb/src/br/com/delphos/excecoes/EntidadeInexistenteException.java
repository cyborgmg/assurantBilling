package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class EntidadeInexistenteException extends DelphosException {
	private static final long serialVersionUID = 1L;

	public EntidadeInexistenteException() {
		super();
	}

	public EntidadeInexistenteException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public EntidadeInexistenteException(String m, Throwable t) {
		super(m, t);
	}

	public EntidadeInexistenteException(String m) {
		super(m);
	}

	public EntidadeInexistenteException(Throwable t) {
		super(t);
	}

}
