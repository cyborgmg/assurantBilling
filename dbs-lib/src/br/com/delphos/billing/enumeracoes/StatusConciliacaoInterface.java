package br.com.delphos.billing.enumeracoes;

public enum StatusConciliacaoInterface {
	
	ATIVA("ATI"),
	ANULADA("ANU"),
	VALIDA("VAL"),
	INCLUIDA("INC");
	
	private final String valor;

	private StatusConciliacaoInterface(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static StatusConciliacaoInterface buscarPorValor(String valor) {
		for (StatusConciliacaoInterface elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
