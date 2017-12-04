package br.com.delphos.billing.enumeracoes;

public enum StatusCancelamento {

	NaoEncontrado("2"),
	Cancelado("0"),
	Negado("1"),
	Aceito("3"),
	Erro(null);
	
	private final String valor;
	
	private StatusCancelamento(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
	
	public static StatusCancelamento buscarPorValor(String valor) {
		if (valor == null) {
			return Erro;
		}
		for (StatusCancelamento elem : values()) {
			if (elem.valor != null && elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
}
