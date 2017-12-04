package br.com.delphos.billing.enumeracoes;

/**
 * Representa o estado de uma autoriza��o junto � adquirente.
 * 
 * <p>Os m�todos de consulta de estado de transa��o retornar�o um objeto 
 * {@link StatusAutorizacao} para indicar o estado  da transa��o junto �
 * adquirente.
 * </p>
 * 
 * <p>Para a Braspag, esta enumera��o mapeia conceitualmente os valores usados
 * pelo servi�o de consulta (tabela 14.1 do manual 
 * <em>Pagador Transaction - Consulta</em>, vers�o 1.3).
 * </p> 
 * 
 * @author flavio.costa
 */
public enum StatusAutorizacao {

	/**
	 * Estado da transa��o desconhecido ou erro interno ao pesquisar.
	 */
	Desconhecido("NUL"),
	
	/**
	 * Transa��o autorizada, mas n�o capturada.
	 */
	Autorizado("AUT"),
	
	/**
	 * Transa��o n�o autorizada.
	 */
	NaoAutorizado("NAU"),
	
	/**
	 * Transa��o autorizada e capturada. Considera-se que o d�bito j� tenha
	 * sido efetivado.
	 */
	Capturado("CAP"),
	
	/**
	 * Transa��o estornada.
	 */
	Estornado("EST"),
	
	/**
	 * Solicita��o de estorno n�o aceita ou estorno n�o realizado.
	 */
	EstornoNegado("ENE"),
	
	/**
	 * Transa��o cancelada (sem necessidade de estorno, e.g. cancelamento feito
	 * at� �s 23:59 do mesmo dia da autoriza��o/captura).
	 */
	Cancelado("CAN"),
	
	/**
	 * Aguardando resposta da adquirente em rela��o � transa��o (e.g. 
	 * resposta � solicita��o de estorno aceita, mas n�o definda).
	 */
	AguardandoResposta("ESO");
	
	private final String valor;
	
	private StatusAutorizacao(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static StatusAutorizacao buscarPorValor(String valor) {
		for (StatusAutorizacao elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
