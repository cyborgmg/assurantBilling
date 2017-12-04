package br.com.delphos.billing.enumeracoes;

public enum TipoBandeira {

	Visa("01"),
	Mastercard("02"),
	Hipercard("06"),
	Amex("07");
	
	private final String valor;

	private TipoBandeira(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoBandeira buscarPorValor(String valor) {
		for (TipoBandeira elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
