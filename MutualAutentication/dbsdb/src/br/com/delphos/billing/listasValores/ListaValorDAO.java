package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ListaValorDAO extends DAOEntidade<ListaValor> {

	/**
	 * Busca por uma lista de valores que possua o c�digo passado.
	 * 
	 * @param codigo O c�digo de lista de valores a ser buscado.
	 * @return A lista de valores com o c�digo passado, ou {@code null}
	 * caso n�o exista uma lista de valores com este c�digo.
	 */
	ListaValor obterPorCodigo(String codigo);
	
	public List<ListaValor> listarPorCriterio(String codigo, String descricao, String status, String tipoUso);
}
