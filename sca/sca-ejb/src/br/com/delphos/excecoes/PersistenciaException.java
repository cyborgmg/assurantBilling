package br.com.delphos.excecoes;

import br.com.delphos.sca.constantes.CodigoMensagem;

public class PersistenciaException extends DelphosRuntimeException {
	private static final long serialVersionUID = 1L;

	public PersistenciaException() {
		super();
	}

	public PersistenciaException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public PersistenciaException(String m, Throwable t) {
		super(m, t);
	}

	public PersistenciaException(String m) {
		super(m);
	}

	public PersistenciaException(Throwable t) {
		super(t);
	}

}
