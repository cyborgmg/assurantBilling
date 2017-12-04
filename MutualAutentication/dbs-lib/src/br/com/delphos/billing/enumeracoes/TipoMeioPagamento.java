package br.com.delphos.billing.enumeracoes;


public enum TipoMeioPagamento {

	CartaoCredito("CC");
	
	private String valor;

	private TipoMeioPagamento(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static TipoMeioPagamento buscarPorValor(String valor) {
		for (TipoMeioPagamento tipo : values()) {
			if (tipo.valor.equals(valor)) {
				return tipo;
			}
		}
		return null;
	}
}
