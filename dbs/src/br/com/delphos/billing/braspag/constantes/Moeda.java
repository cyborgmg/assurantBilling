package br.com.delphos.billing.braspag.constantes;

public enum Moeda {
	
	RealBrasileiro("BRL"),
	DolarAmericano("USD");
	
	private final String valor;

	private Moeda(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
}
