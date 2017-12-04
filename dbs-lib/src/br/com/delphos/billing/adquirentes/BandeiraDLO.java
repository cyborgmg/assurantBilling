package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface BandeiraDLO extends DLOEntidade<Bandeira> {

	/**
	 * Valida um c�digo de bandeira e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este m�todo realiza apenas valida��es b�sicas e de natureza
	 * sint�tica. N�o est� contemplada a verifica��o de exist�ncia de
	 * uma bandeira com o c�digo passado. Para isto, utilize os m�todos
	 * de busca por c�digo.
	 * 
	 * @param codigo O c�digo de bandeira a ser validado.
	 * @return {@code true} se for v�lido, e {@code false} caso contr�rio.
	 */
	boolean isCodigoValido(String codigo);
	
	/**
	 * Busca por uma bandeira de cart�o que possua o c�digo de bandeira
	 * passado.
	 * 
	 * @param codigo O c�digo de bandeira a ser buscado.
	 * @return A bandeira com o c�digo passado, ou {@code null} caso
	 * o c�digo seja inv�lido ou caso n�o exista uma bandeira com este
	 * c�digo.
	 */
	Bandeira obterPorCodigo(String codigo);
	
	public void alterarBandeira(Bandeira bandeira);

	public void excluirBandeira(Bandeira bandeira);

	public void incluirBandeira(Bandeira bandeira);
	
	public List<Bandeira> listarPorCriterio(String codigo, String nome);
}
