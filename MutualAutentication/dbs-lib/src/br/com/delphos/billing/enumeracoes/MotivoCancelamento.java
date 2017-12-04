package br.com.delphos.billing.enumeracoes;

public enum MotivoCancelamento {

	CancelamentoPorSolicitacao("001"),
	CancelamentoPorInadimplencia("002"),
	CancelamentoPorChargeBack("003"),
	CancelamentoAutomatico("004");
	
	private final String valor;
	
	private MotivoCancelamento(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public static MotivoCancelamento buscarPorValor(String valor) {
		for (MotivoCancelamento elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
