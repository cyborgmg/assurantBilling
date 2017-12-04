package br.com.delphos.billing.enumeracoes;

/**
 * Enumeração de todos os possíveis tipos de cobrança.
 * 
 * @author flavio.costa
 */
public enum TipoCobranca {

	SinglePremiumVista("001", TipoPagamento.Vista, false),
	SinglePremiumVistaOuParcelado("002", TipoPagamento.Parcelado, true),
	SinglePremiumParcelamentoCartao("003", TipoPagamento.Parcelado, false),
	SinglePremiumRecorrencia("004", TipoPagamento.Parcelado, true),
	Mensal("005", TipoPagamento.VigenciaAberta, true);
	
	private final String valor;
	private final TipoPagamento tipoPagamento;
	private final boolean recorrencia;

	private TipoCobranca(String valor, TipoPagamento tipoPagamento, boolean recorrencia) {
		this.valor = valor;
		this.tipoPagamento = tipoPagamento;
		this.recorrencia = recorrencia;
	}

	public String getValor() {
		return valor;
	}
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	/**
	 * Indica se o tipo de cobrança é recorrente.
	 * 
	 * <p>Cobranças recorrentes são aquelas que acontecem em tempos regulares.
	 * TODO: Melhorar a descrição para recorrência.
	 * </p>
	 * 
	 * @return
	 */
	public boolean isRecorrencia() {
		return recorrencia;
	}
	
	/**
	 * Indica se o tipo de cobrança permite pagamento à vista.
	 * @return
	 */
	public boolean isPagamentoVista() {
		return this.tipoPagamento == TipoPagamento.Vista || this == SinglePremiumVistaOuParcelado;
	}
	
	/**
	 * Indica se o tipo da cobrança permite parcelamento.
	 * 
	 * <p>Este é apenas um indicador. Um determinado tipo de cobrança pode ser
	 * naturalmente parcelado, mas ainda assim ser considerado como pagamento à
	 * vista caso haja apenas uma parcela e, em alguns casos, caso a captura
	 * desta parcela única seja automática.
	 * </p>
	 * 
	 * <p>O inverso disto é quando o pagamento é à vista ou periódico (vigência
	 * em aberto). Para determinar exatamente se algum dos casos complementares
	 * se aplica à este tipo de cobrança, deve-se chamar seus métodos
	 * correspondentes ({@link #isPagamentoVista()} e
	 * {@link #isVigenciaAberta()}. 
	 * </p>
	 * 
	 * @return
	 */
	public boolean isPagamentoParcelado() {
		return tipoPagamento == TipoPagamento.Parcelado;
	}

	/**
	 * Indica um tipo de cobrança que gera uma única cobrança por venda, independente
	 * da opção de parcelamento.
	 * 
	 * @return
	 */
	public boolean isAutorizacaoUnica() {
		return this == TipoCobranca.SinglePremiumParcelamentoCartao;
	}
	
	/**
	 * Indica se o tipo da cobrança é relativa à um prêmio único.
	 * 
	 * <p>Pagamentos na modalidade prêmio único dizem respeito ao pagamento de
 	 * toda a vigência do seguro, seja de uma única vez ou de forma parcelada.
 	 * </p>
 	 * 
 	 * <p>O inverso disto é quando o fim de vigência de um seguro está em aberto;
 	 * nestes casos, {@link isPremioUnico()} retornará {@code false} para
 	 * indicar que o pagamento de um seguro é feito ao decorrer do seu uso, 
 	 * até que o cliente cancele o seguro.
 	 * </p>
 	 * 
	 * @return
	 */
	public boolean isPremioUnico() {
		return this.tipoPagamento != TipoPagamento.VigenciaAberta;
	}
	
	/**
	 * Indica se o tipo de cobrança permite pagamentos periódicos em aberto.
	 * @see #isPremioUnico()
	 * @return
	 */
	public boolean isVigenciaAberta() {
		return this.tipoPagamento == TipoPagamento.VigenciaAberta;
	}
	
	public static TipoCobranca buscarPorValor(String valor) {
		for (TipoCobranca elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
}
