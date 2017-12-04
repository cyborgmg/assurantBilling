package br.com.delphos.billing.enumeracoes;

public enum SimNao {
	
	Sim("S", "SIM"),
	Nao("N", "NAO");
	
	private final String valor;
	private final String descricao;
	
	private SimNao(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static SimNao buscarPorValor(String valor) {
		for (SimNao elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}

}
