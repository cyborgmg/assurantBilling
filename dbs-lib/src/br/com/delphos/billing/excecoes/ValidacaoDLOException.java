package br.com.delphos.billing.excecoes;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;

public class ValidacaoDLOException extends DLOException {
	private static final long serialVersionUID = 1L;

	public ValidacaoDLOException() {
		super();
	}

	public ValidacaoDLOException(String m, Throwable t) {
		super(m, t);
	}

	public ValidacaoDLOException(String m) {
		super(m);
	}

	public ValidacaoDLOException(Throwable t) {
		super(t);
	}

	public ValidacaoDLOException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

}
