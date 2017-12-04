package br.com.delphos.billing.enumeracoes;

public enum StatusLogProcesso {
	
	Iniciado("INI"),
	Processado("PRO"),
	Erro("ERR");
	
	private final String valor;

	private StatusLogProcesso(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static StatusLogProcesso buscarPorValor(String valor) {
		for (StatusLogProcesso elem : values()) {
			if (elem == null) {
				if (valor == null) {
					return elem;
				}
			} else if (elem.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
