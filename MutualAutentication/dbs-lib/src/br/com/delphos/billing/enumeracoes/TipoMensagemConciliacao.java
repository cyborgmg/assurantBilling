package br.com.delphos.billing.enumeracoes;

public enum TipoMensagemConciliacao {
	ValorDefault(""),
	CodigoAfiliacaoInvalido("001"),
	ArquivoMaisDeUmDiaMovimento("002"),
	ArquivoComMaisDeUmDiaMovimento("003"),
	ArquivoPossuiMovimentoComErro("004"),
	ArquivoPossuiEventoAfiliacaoInformativo("005"),
	ArquivoComMovimentoFinanceiroNoFimDeSemana("006"),
	ValorArquivoDiferenteDoValorDeposito("007")
	;
	
	private String valor;

	private TipoMensagemConciliacao(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

	public static TipoMensagemConciliacao buscarPorValor(String valor) {
		for (TipoMensagemConciliacao tipo : values()) {
			if (tipo.valor.equals(valor)) {
				return tipo;
			}
		}
		return null;
	}
}
