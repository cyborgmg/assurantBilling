package br.com.delphos.billing.enumeracoes;

public enum TipoTentativa {

	Cobranca("COB"),
	Cancelamento("CAN"),
	Estorno("EST");
	
	private final String valor;

	private TipoTentativa(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoTentativa buscarPorValor(String valor) {
		for (TipoTentativa elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
