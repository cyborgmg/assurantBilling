package br.com.delphos.billing.braspag.constantes;

public enum PlanoPagamento {

	Vista(0),
	ParceladoEstabelecimento(1),
	ParceladoCartao(2),
	ParceladoIataEstabelecimento(3),
	ParceladoIataCartao(4),
	VistaIata(5);
	
	private final short valor;

	private PlanoPagamento(int valor) {
		this.valor = (short) valor;
	}

	public short getValor() {
		return valor;
	}
	
}
