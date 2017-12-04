package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ListaValorDLO extends DLOEntidade<ListaValor> {
	
	/**
	 * Valida um código de lista de valores e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * uma lista de valores com o código passado. Para isto, utilize
	 * os métodos de busca por código.
	 * 
	 * @param codigo O código de lista de valores a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 * @throws DLOException 
	 */
	boolean isCodigoValido(String codigo) throws DLOException;
	
	/**
	 * Busca por uma lista de valores que possua o código passado.
	 * 
	 * @param codigo O código de lista de valores a ser buscado.
	 * @return A lista de valores com o código passado, ou {@code null}
	 * caso o código seja inválido ou caso não exista uma lista de valores
	 * com este código.
	 * @throws DLOException 
	 */
	ListaValor obterPorCodigo(String codigo) throws DLOException;
	
	
	public void alterarListaValor(ListaValor listaValor);

	public void excluirListaValor(ListaValor listaValor);

	public void incluirListaValor(ListaValor listaValor);

	public List<ListaValor> listarPorCriterio(String codigo, String descricao, String status, String tipoUso);
	
}
