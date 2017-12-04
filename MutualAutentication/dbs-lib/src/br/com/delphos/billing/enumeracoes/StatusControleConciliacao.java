package br.com.delphos.billing.enumeracoes;

public enum StatusControleConciliacao {
	
	Pendente("PEN", true),
	Liberado("LIB", true),
	Processado("PRC", true),
	Rejeitado("REJ", false),
	ErroValor("RVL", false),
	ReprocessamentoSolicitado("REP", true);
	
	private final String valor;
	private final boolean validoParaProcesso;

	private StatusControleConciliacao(String valor, boolean valido) {
		this.valor = valor;
		this.validoParaProcesso = valido;
	}

	public String getValor() {
		return valor;
	}
	
	public boolean isValidoParaProcesso() {
		return validoParaProcesso;
	}

	public static StatusControleConciliacao buscarPorValor(String valor) {
		for (StatusControleConciliacao elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
