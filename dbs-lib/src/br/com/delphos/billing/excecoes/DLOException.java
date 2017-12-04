package br.com.delphos.billing.excecoes;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.util.Mensagens;

public class DLOException extends BillingException {
	private static final long serialVersionUID = 1L;

	public DLOException() {
		super();
	}

	public DLOException(String m) {
		super(m);
	}

	public DLOException(Throwable t) {
		super(t);
	}

	public DLOException(String m, Throwable t) {
		super(m, t);
	}

	public DLOException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
	}

	public static String obterMensagemDLO(final Exception e) {
		String mensagem;
		if (e instanceof BillingException) {
			mensagem = e.getLocalizedMessage();
		} else {
			mensagem = Mensagens.mensagemOuCodigo(e.getLocalizedMessage());
		}
		return mensagem;
	}
}
