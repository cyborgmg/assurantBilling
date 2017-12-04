package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface BandeiraDAO extends DAOEntidade<Bandeira> {

	/**
	 * Busca por uma bandeira que possua o código de bandeira passado.
	 * 
	 * @param codigo O código de bandeira a ser buscado.
	 * @return A bandeira com o código passado, ou {@code null} caso
	 * não exista uma bandeira com este código.
	 */
	Bandeira obterPorCodigo(String codigo);
	
	public List<Bandeira> listarPorCriterio(String codigo, String nome);
	
}
