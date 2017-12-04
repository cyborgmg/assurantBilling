package br.com.delphos.billing.braspag.constantes;

public enum CodigoAdquirente {

	OperacaoBemSucedida("4"),
	OperacaoNaoAutorizada("6"),
	Timeout("99"),
	CartaoCreditoCancelado("77"),
	ProblemasComCartaoCredito("78"),
	CartaoCreditoExpirado("57");
	
	private final String valor;

	private CodigoAdquirente(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static CodigoAdquirente buscarPorValor(String valor) {
		for (CodigoAdquirente elem: values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
