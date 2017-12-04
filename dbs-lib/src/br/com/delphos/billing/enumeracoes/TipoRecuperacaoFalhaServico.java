package br.com.delphos.billing.enumeracoes;

public enum TipoRecuperacaoFalhaServico {

	Nenhum("nenhuma"),
	ReiniciarServico("servico"),
	ReiniciarPorta("porta"),
	Esperar("esperar");
	
	private final String valor;
	
	private TipoRecuperacaoFalhaServico(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoRecuperacaoFalhaServico buscarPorValor(String valor) {
		for (TipoRecuperacaoFalhaServico elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
