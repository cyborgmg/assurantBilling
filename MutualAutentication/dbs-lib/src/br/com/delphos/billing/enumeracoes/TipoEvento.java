package br.com.delphos.billing.enumeracoes;


public enum TipoEvento {

	GeradaCobrancaPorVenda("001"),
	ChamadaMetodoAutorizacaoGateway("002"),
	TransacaoBemSucedida("003"),
	RetornoMetodoConsultaTransacao("004"),
	ErroProcessamentoSolicitacao("005"),
	
	GeradaCobrancaPorRecorrencia("030"),
	GeradaRetentativaCobranca("031"),
	CobrancaCanceladaPorNaoAutorizacao("032"),
	CobrancaCanceladaComEstornoZero("050"),
	
	ChamadaMetodoGateway("051"),
	CobrancaCanceladaComSucesso("052"),
	CobrancaNaoCancelada("053"),
	CobrancaNaoLocalizadaParaCancelamento("054"),
	SolicitacaoEstornoRecebida("055"),
	CobrancaCanceladaPorCancelamentoVenda("056"),
	
	CobrancaPaga("057"),
	CobrancaEstornadaPorChargeback("058"),
	EstornoConciliado("059"),
	AceleracaoConciliada("060"),
	ReagendamentoConciliado("061"),
	CapturaConciliada("062"),
	
	TransacaoNaoEfetuada("100"),
	TransacaoNaoConfirmada("101"),
	TransacaoNaoChegouAoAdquirente("102"),
	ErroRotinaProvedor("103"),
	ErroRotinaPagamentoOuCaptura("104");
	
	private final String valor;
	
	private TipoEvento(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static TipoEvento buscarPorValor(String valor) {
		for (TipoEvento elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
}
