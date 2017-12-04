package br.com.delphos.billing.sistemas;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;
import br.com.delphos.billing.produtos.Produto;

@Remote
public interface SistemaDLO extends DLOEntidade<Sistema> {

	/**
	 * Valida um código de sistema e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * um sistema com o código passado. Para isto, utilize os métodos
	 * de busca por código.
	 * 
	 * @param codigoSistema O código do sistema a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 * @throws DLOException 
	 */
	boolean isCodigoValido(String codigoSistema) throws DLOException;

	/**
	 * Busca por um sistema que possua o código de sistema passado e que
	 * pertença ao produto {@param produto}.
	 * 
	 * @param produto O produto a ser usado na pesquisa.
	 * @param codigoSistema O código do sistema a ser buscado.
	 * @return O sistema com o código passado, ou {@code null} caso
	 * o código seja inválido ou caso não exista um sistema com este
	 * código.
	 * @throws DLOException 
	 */
	Sistema obterPorProdutoCodigo(Produto produto, String codigoSistema) throws DLOException;

	/**
	 * Busca por todos os produtos que tenham o código de produto passado.
	 * 
	 * @param codigo O código de produto a ser pesquisado.
	 * @return Uma lista contento todos os produtos com o código passado,
	 * ou {@code null} caso o código seja inválido ou em caso de erro.
	 * @throws DLOException 
	 */
	List<Sistema> listarPorCodigo(String codigo) throws DLOException;
	
	
	Sistema obterPorCodigo(String codigo) throws DLOException;
	
	Object incluir(Sistema sistema)throws DLOException;
	
	void alterar(Sistema sistema)throws DLOException;
	
	public void alterarSistema(Sistema sistema);

	public void excluirSistema(Sistema sistema);

	public void incluirSistema(Sistema sistema);
	
	public List<Sistema> listarPorCriterio(String codigo, String descricao);
}
