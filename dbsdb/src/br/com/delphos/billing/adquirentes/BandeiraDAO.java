package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface BandeiraDAO extends DAOEntidade<Bandeira> {

	/**
	 * Busca por uma bandeira que possua o c�digo de bandeira passado.
	 * 
	 * @param codigo O c�digo de bandeira a ser buscado.
	 * @return A bandeira com o c�digo passado, ou {@code null} caso
	 * n�o exista uma bandeira com este c�digo.
	 */
	Bandeira obterPorCodigo(String codigo);
	
	public List<Bandeira> listarPorCriterio(String codigo, String nome);
	
}
