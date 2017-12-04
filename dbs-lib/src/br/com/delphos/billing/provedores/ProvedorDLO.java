package br.com.delphos.billing.provedores;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ProvedorDLO extends DLOEntidade<Provedor> {
	
	/**
	 * Busca por um provedor que possua o c�digo passado.
	 * 
	 * @param codigo O c�digo do provedor a ser buscado.
	 * @return O provedor com o c�digo passado, ou {@code null} caso
	 * o c�digo seja inv�lido ou caso n�o exista um provedor com este
	 * c�digo.
	 */
	Provedor obterPorCodigo(String codigo);
	
	public boolean isCodigoValido(String codigo);
	
	public List<Provedor> listarPorCriterio(String codigo, String descricao);

}
