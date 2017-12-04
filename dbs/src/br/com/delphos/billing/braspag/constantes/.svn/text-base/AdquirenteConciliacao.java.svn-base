package br.com.delphos.billing.braspag.constantes;

public enum AdquirenteConciliacao {

	Cielo(1),
	Redecard(2),
	Amex(3),
	Losango(4);
	
	private final Integer valor;

	private AdquirenteConciliacao(int valor) {
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}
	
	public static AdquirenteConciliacao buscarPorValor(Integer valor) {
		for (AdquirenteConciliacao elem: values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
