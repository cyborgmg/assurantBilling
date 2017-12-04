package br.com.delphos.billing.produtos;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ProdutoDLO extends DLOEntidade<Produto> {

	/**
	 * Valida um c�digo de produto e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este m�todo realiza apenas valida��es b�sicas e de natureza
	 * sint�tica. N�o est� contemplada a verifica��o de exist�ncia de
	 * um produto com o c�digo passado. Para isto, utilize os m�todos
	 * de busca por c�digo.
	 * 
	 * @param codigoEmpresa O c�digo do produto a ser validado.
	 * @return {@code true} se for v�lido, e {@code false} caso contr�rio.
	 */
	boolean isCodigoValido(String codigoProduto);
	
	/**
	 * Busca por um produto da empresa {@code empresa} que possua o
	 * c�digo de produto passado.
	 * 
	 * @param codigoProduto O c�digo do produto a ser buscado.
	 * @param empresa A empresa a ser usada na pesquisa.
	 * @return O produto com o c�digo passado, ou {@code null} caso
	 * o c�digo seja inv�lido ou caso n�o exista um produto com este
	 * c�digo.
	 */
	Produto obterPorEmpresaCodigo(Empresa empresa, String codigoProduto);
	
	/**
	 * Busca por todos os produtos que tenham o c�digo de produto passado.
	 * 
	 * @param codigo O c�digo de produto a ser pesquisado.
	 * @return Uma lista contento todos os produtos com o c�digo passado,
	 * ou {@code null} caso o c�digo seja inv�lido ou em caso de erro.
	 */
	List<Produto> listarPorCodigo(String codigo);
	
	
	Produto obterPorCodigo(String codigo);
	
	
	public void alterarProduto(Produto produto);

	public void excluirProduto(Produto produto);

	public void incluirProduto(Produto produto);
	
	public List<Produto> listarPorEmpresa(String codigoEmpresa);
	
	public List<Produto> listarProdutosSemAssociacao();
	
	public void associarProdutosEmpresa(String csvProdutos, Empresa empresa);
	
	public List<Produto> listarProdutosPorCriterio(String codigoProduto, String descricaoProduto, String codigoEmpresa);

	public List<Produto> obterProdutosNaoRelacionadosAoContrato(String idContratoCobranca);
	
}
