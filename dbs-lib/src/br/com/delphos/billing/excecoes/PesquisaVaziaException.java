package br.com.delphos.billing.excecoes;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;

public class PesquisaVaziaException extends DLOException {
	private static final long serialVersionUID = 1L;

	public PesquisaVaziaException() {
		super();
	}

	public PesquisaVaziaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public PesquisaVaziaException(String m, Throwable t) {
		super(m, t);
	}

	public PesquisaVaziaException(String m) {
		super(m);
	}

	public PesquisaVaziaException(Throwable t) {
		super(t);
	}

}
