package br.com.delphos.billing.enumeracoes;

public enum TipoConciliacaoInterface {

	Cobranca("COB"),
	CobrancaComEstorno("CBE"),
	CobrancaConciliada("CBD"),
	Estorno("EST"),
	EstornoConciliado("ESD"),
	Chargeback("CHB"),
	Outros("OUT"),
	CobrancaNaoIdentificada("NID"),
	RejeitadoPeloDbs("REJ");

	private final String valor;
	
	private TipoConciliacaoInterface(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoConciliacaoInterface buscarPorValor(String valor) {
		for (TipoConciliacaoInterface elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
