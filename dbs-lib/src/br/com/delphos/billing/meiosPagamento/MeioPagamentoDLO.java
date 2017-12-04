package br.com.delphos.billing.meiosPagamento;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface MeioPagamentoDLO extends DLOEntidade<MeioPagamento> {

	/**
	 * Valida um código de meio de pagamento e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * um meio de pagamento com o código passado. Para isto, utilize
	 * os métodos de busca por código.
	 * 
	 * @param codigo O código do meio de pagamento a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 */
	boolean isCodigoValido(String codigo);
	
	/**
	 * Busca por um meio de pagamento que possua o código passado.
	 * 
	 * @param codigo O código do meio de pagamento a ser buscado.
	 * @return O meio de pagamento com o código passado, ou {@code null} caso
	 * o código seja inválido ou caso não exista um meio de pagamento com este
	 * código.
	 */
	MeioPagamento obterPorCodigo(String codigo);
	
	public List<MeioPagamento> listarPorCriterio(String codigo, String descricao);
}
