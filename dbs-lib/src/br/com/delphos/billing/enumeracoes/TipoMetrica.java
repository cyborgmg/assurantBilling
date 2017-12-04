package br.com.delphos.billing.enumeracoes;

public enum TipoMetrica {

	TempoChamadaMetodo("TCM"),
	TempoChamadaMetodoEjbLocal("TCML"),
	TempoChamadaMetodoEjbRemoto("TCMR"),
	TempoChamadaMetodoEjbExterno("TCME"),
	TempoChamadaWebService("TCW"),
	TempoExecucaoProcesso("TEP"),
	TempoSolicitacaoWebService("TSW"), // Uma resposta à uma solicitação via JAX-WS
	;
	
	private final String valor;

	private TipoMetrica(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoMetrica buscarPorValor(String valor) {
		for (TipoMetrica elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
