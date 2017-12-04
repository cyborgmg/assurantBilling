package br.com.delphos.billing.enumeracoes;

public enum PropriedadeConfiguracao {
	
	// Configurações de teste
	
	Debug("billing.debug", 1, ","),

	PrefixoVendaNaoConfirmada("billing.dbs.prefixoVendaNaoConfirmada", 1, ","),
	PrefixoVendaNaoChegouNaAdquirente("billing.dbs.prefixoVendaNaoChegouNaAdquirente", 1, ","),
	PrefixoVendaRespostaNula("billing.dbs.prefixoVendaRespostaNula", 1, ","),
	PrefixoVendaErroRotina("billing.dbs.prefixoVendaErroRotina", 1, ","),
	
	// Configurações Serviços Braspag
	
	UrlWsdlPagadorTransaction("billing.braspag.urlWsdlPagadorTransaction", 1, ","),
	UrlWsdlPagadorQuery("billing.braspag.urlWsdlPagadorQuery", 1, ","),
	UrlWsdlPagadorReconciliation("billing.braspag.urlWsdlPagadorReconciliation", 1, ","),
	
	// Propriedades das rotinas agendadas

	ImportandoUmAdquirenteConciliacaoPorVez("billing.dbstimer.importandoUmAdquirenteConciliacaoPorVez", 1, ","),
	ImportandoUmArquivoConciliacaoPorVez("billing.dbstimer.importandoUmArquivoConciliacaoPorVez", 1, ","),
	ProcessandoUmAdquirenteConciliacaoPorVez("billing.dbstimer.processandoUmAdquirenteConciliacaoPorVez", 1, ","),
	
	// Configurações para recuperação de falha
	
	AcaoRecuperacaoFalhaJaxWs("billing.jaxws.acaoRecuperacaoFalhaJaxWs", -1, ","),
	TempoEsperaQuandoFalhaJaxWs("billing.jaxws.tempoEsperaQuandoFalhaJaxWs", 1, ","),
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
