package br.com.delphos.billing.sistemas;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.produtos.Produto;

@Local
public interface SistemaDAO extends DAOEntidade<Sistema> {

	/**
	 * Busca por um sistema que possua o código de sistema passado e que
	 * pertença ao produto {@param produto}.
	 * 
	 * @param produto O produto a ser usado na pesquisa.
	 * @param codigoSistema O código do sistema a ser buscado.
	 * @return O sistema com o código passado, ou {@code null} 
	 * caso não exista um sistema com este código.
	 */
	Sistema obterPorProdutoCodigo(Produto produto, String codigoSistema);
	
	/**
	 * Busca por todos os sistemas que tenham o código de sistema passado.
	 * 
	 * @param codigo O código de sistema a ser pesquisado.
	 * @return Uma lista contento todos os sistemas com o código passado,
	 * ou {@code null} em caso de erro.
	 */
	List<Sistema> listarPorCodigo(String codigo);
	
	Sistema obterPorCodigo(String codigo);
	
	public List<Sistema> listarPorCriterio(String codigo, String descricao);
}
