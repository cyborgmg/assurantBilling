package br.com.delphos.billing.provedores;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ProvedorDAO extends DAOEntidade<Provedor> {
	
	/**
	 * Busca por um provedor que possua o código passado.
	 * 
	 * @param codigo O código do provedor a ser buscado.
	 * @return O provedor com o código passado, ou {@code null} 
	 * caso não exista um provedor com este código.
	 */
	Provedor obterPorCodigo(String codigo);
	
	public List<Provedor> listarPorCriterio(String codigo, String descricao);
	
}
