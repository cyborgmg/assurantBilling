package br.com.delphos.billing.util;

import br.com.delphos.billing.excecoes.DLOException;


public abstract class AbstractRegraNegocio implements RegraNegocio {

	private Boolean valido;
	private String mensagem;
	
	@Override
	public boolean isValida() throws DLOException {
		if (valido == null) {
			run();
			if (valido == null) {
				throw new RuntimeException("Regra " + this.getClass().getName() + " não pôde determinar validade.");
			}
		}
		return valido;
	}

	protected void setValida(boolean valido, String mensagem) {
		this.valido = valido;
		this.mensagem = mensagem;
	}
	
	protected void setValida(boolean valido, String nomePropriedade, Object... args) {
		this.valido = valido;
		this.mensagem = Mensagens.get(nomePropriedade, args);
	}
	
	@Override
	public String getMensagemRetorno() {
		return mensagem;
	}

	@Override
	public abstract void run() throws DLOException;
}
