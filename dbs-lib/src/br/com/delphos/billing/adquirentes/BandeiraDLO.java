package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface BandeiraDLO extends DLOEntidade<Bandeira> {

	/**
	 * Valida um código de bandeira e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * uma bandeira com o código passado. Para isto, utilize os métodos
	 * de busca por código.
	 * 
	 * @param codigo O código de bandeira a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 */
	boolean isCodigoValido(String codigo);
	
	/**
	 * Busca por uma bandeira de cartão que possua o código de bandeira
	 * passado.
	 * 
	 * @param codigo O código de bandeira a ser buscado.
	 * @return A bandeira com o código passado, ou {@code null} caso
	 * o código seja inválido ou caso não exista uma bandeira com este
	 * código.
	 */
	Bandeira obterPorCodigo(String codigo);
	
	public void alterarBandeira(Bandeira bandeira);

	public void excluirBandeira(Bandeira bandeira);

	public void incluirBandeira(Bandeira bandeira);
	
	public List<Bandeira> listarPorCriterio(String codigo, String nome);
}
