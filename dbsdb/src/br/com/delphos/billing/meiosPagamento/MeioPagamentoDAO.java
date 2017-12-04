package br.com.delphos.billing.meiosPagamento;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface MeioPagamentoDAO extends DAOEntidade<MeioPagamento> {
	
	/**
	 * Busca por um meio de pagamento que possua o código passado.
	 * 
	 * @param codigo O código do meio de pagamento a ser buscado.
	 * @return O meio de pagamento com o código passado, ou {@code null} 
	 * caso não exista um meio de pagamento com este código.
	 */
	MeioPagamento obterPorCodigo(String codigo);
	
	public List<MeioPagamento> listarPorCriterio(String codigo, String descricao);
}
