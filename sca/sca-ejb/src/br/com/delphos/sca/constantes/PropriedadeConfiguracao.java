package br.com.delphos.sca.constantes;

public enum PropriedadeConfiguracao {
	
	// Configurações de teste
	
	Debug("sca.debug", 1, ","),
	AcaoRecuperacaoFalhaJaxWs("sca.jaxws.acaoRecuperacaoFalhaJaxWs", -1, ","),
	TempoEsperaQuandoFalhaJaxWs("sca.jaxws.tempoEsperaQuandoFalhaJaxWs", 1, ","),
	;
	
	private final String nomePropriedade;
	private final int numeroElementos;
	private final String separador;
	
	private PropriedadeConfiguracao(
			String nomePropriedade,
			int numeroElementos,
			String separador) {
		this.nomePropriedade = nomePropriedade;
		this.numeroElementos = numeroElementos;
		this.separador = separador;
	}

	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public int getNumeroElementos() {
		return numeroElementos;
	}

	public String getSeparador() {
		return separador;
	}
}
