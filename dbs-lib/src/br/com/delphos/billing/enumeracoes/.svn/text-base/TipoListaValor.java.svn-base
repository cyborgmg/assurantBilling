package br.com.delphos.billing.enumeracoes;

/**
 * Enumeração contento todos os códigos de listas de valores previstos
 * na documentação funcional, Anexo IV.
 * @author flavio.costa
 *
 */
public enum TipoListaValor {
	
	Cobranca("TPCOB"),
	Evento("TPEVT"),
	MotivoCancelamento("MOTCAN"),
	Status("TPSTA"),
	EventoConciliacao("EVECONC"),
	SituacaoConciliacao("SITCON"),
	Operacao("TPOP"),
	SimNao("SIMNAO"),
	MensagemConciliacao("MSGVAL"),
	StatusControleConciliacao("SCC");
	
	private String valor;

	private TipoListaValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

	public static TipoListaValor buscarPorValor(String valor) {
		for (TipoListaValor tipo : values()) {
			if (tipo.valor.equals(valor)) {
				return tipo;
			}
		}
		return null;
	}
}
