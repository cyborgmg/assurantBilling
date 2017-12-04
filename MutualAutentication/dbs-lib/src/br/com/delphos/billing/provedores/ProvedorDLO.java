package br.com.delphos.billing.provedores;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ProvedorDLO extends DLOEntidade<Provedor> {
	
	/**
	 * Busca por um provedor que possua o código passado.
	 * 
	 * @param codigo O código do provedor a ser buscado.
	 * @return O provedor com o código passado, ou {@code null} caso
	 * o código seja inválido ou caso não exista um provedor com este
	 * código.
	 */
	Provedor obterPorCodigo(String codigo);
	
	public boolean isCodigoValido(String codigo);
	
	public List<Provedor> listarPorCriterio(String codigo, String descricao);

}
