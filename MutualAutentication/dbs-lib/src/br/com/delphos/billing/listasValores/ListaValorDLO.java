package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ListaValorDLO extends DLOEntidade<ListaValor> {
	
	/**
	 * Valida um c�digo de lista de valores e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este m�todo realiza apenas valida��es b�sicas e de natureza
	 * sint�tica. N�o est� contemplada a verifica��o de exist�ncia de
	 * uma lista de valores com o c�digo passado. Para isto, utilize
	 * os m�todos de busca por c�digo.
	 * 
	 * @param codigo O c�digo de lista de valores a ser validado.
	 * @return {@code true} se for v�lido, e {@code false} caso contr�rio.
	 * @throws DLOException 
	 */
	boolean isCodigoValido(String codigo) throws DLOException;
	
	/**
	 * Busca por uma lista de valores que possua o c�digo passado.
	 * 
	 * @param codigo O c�digo de lista de valores a ser buscado.
	 * @return A lista de valores com o c�digo passado, ou {@code null}
	 * caso o c�digo seja inv�lido ou caso n�o exista uma lista de valores
	 * com este c�digo.
	 * @throws DLOException 
	 */
	ListaValor obterPorCodigo(String codigo) throws DLOException;
	
	
	public void alterarListaValor(ListaValor listaValor);

	public void excluirListaValor(ListaValor listaValor);

	public void incluirListaValor(ListaValor listaValor);

	public List<ListaValor> listarPorCriterio(String codigo, String descricao, String status, String tipoUso);
	
}
