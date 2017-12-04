package br.com.delphos.billing.provedores;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ProvedorDAO extends DAOEntidade<Provedor> {
	
	/**
	 * Busca por um provedor que possua o c�digo passado.
	 * 
	 * @param codigo O c�digo do provedor a ser buscado.
	 * @return O provedor com o c�digo passado, ou {@code null} 
	 * caso n�o exista um provedor com este c�digo.
	 */
	Provedor obterPorCodigo(String codigo);
	
	public List<Provedor> listarPorCriterio(String codigo, String descricao);
	
}
