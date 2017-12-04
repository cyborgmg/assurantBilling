package br.com.delphos.billing.enumeracoes;

public enum StatusProcConciliacaoEvento {

	ATIVO("ATI"),
	ANULADO("ANU"),
	REJEITADO("REJ");
	
	private final String valor;

	private StatusProcConciliacaoEvento(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static StatusProcConciliacaoEvento buscarPorValor(String valor) {
		for (StatusProcConciliacaoEvento elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
}