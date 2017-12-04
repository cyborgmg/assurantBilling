package br.com.delphos.billing.enumeracoes;


public enum StatusCobranca {

	Pendente("PEN", false),
	NaoAutorizada("NAU", true),
	Cancelada("CAN", true),
	Capturada("CAP", false),
	ParcialmenteCobrada("PCO", false),
	Estornada("EST",false),
	CobradaConciliada("COB", true),
	EstornoSolicitado("ESO", false),
	EstornoConciliado("ECO", true),
	EstornoNegado("ENE", true),
	;
	
	
	private final String valor;
	private final boolean terminal;

	private StatusCobranca(String valor, boolean terminal) {
		this.valor = valor;
		this.terminal = terminal;
	}

	public String getValor() {
		return valor;
	}
	
	public boolean isTerminal() {
		return terminal;
	}
	
	public static StatusCobranca buscarPorValor(String valor) {
		for (StatusCobranca elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
}
