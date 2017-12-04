package br.com.delphos.billing.braspag.constantes;

public enum Pais {
	
	Brasil("BRA"),
	Eua("EUA"),
	Mexico("MEX"),
	Colombia("COL"),
	Chile("CHL"),
	Argentina("ARG"),
	Peru("PER"),
	Venezuela("VEN"),
	Equador("ECU");
	
	private final String valor;

	private Pais(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
}
