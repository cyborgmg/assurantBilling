package br.com.delphos.billing.enumeracoes;

/**
 * Representa o estado de uma autorização junto à adquirente.
 * 
 * <p>Os métodos de consulta de estado de transação retornarão um objeto 
 * {@link StatusAutorizacao} para indicar o estado  da transação junto à
 * adquirente.
 * </p>
 * 
 * <p>Para a Braspag, esta enumeração mapeia conceitualmente os valores usados
 * pelo serviço de consulta (tabela 14.1 do manual 
 * <em>Pagador Transaction - Consulta</em>, versão 1.3).
 * </p> 
 * 
 * @author flavio.costa
 */
public enum StatusAutorizacao {

	/**
	 * Estado da transação desconhecido ou erro interno ao pesquisar.
	 */
	Desconhecido("NUL"),
	
	/**
	 * Transação autorizada, mas não capturada.
	 */
	Autorizado("AUT"),
	
	/**
	 * Transação não autorizada.
	 */
	NaoAutorizado("NAU"),
	
	/**
	 * Transação autorizada e capturada. Considera-se que o débito já tenha
	 * sido efetivado.
	 */
	Capturado("CAP"),
	
	/**
	 * Transação estornada.
	 */
	Estornado("EST"),
	
	/**
	 * Solicitação de estorno não aceita ou estorno não realizado.
	 */
	EstornoNegado("ENE"),
	
	/**
	 * Transação cancelada (sem necessidade de estorno, e.g. cancelamento feito
	 * até às 23:59 do mesmo dia da autorização/captura).
	 */
	Cancelado("CAN"),
	
	/**
	 * Aguardando resposta da adquirente em relação à transação (e.g. 
	 * resposta à solicitação de estorno aceita, mas não definda).
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
