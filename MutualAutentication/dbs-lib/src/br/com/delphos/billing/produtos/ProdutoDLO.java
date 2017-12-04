package br.com.delphos.billing.produtos;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ProdutoDLO extends DLOEntidade<Produto> {

	/**
	 * Valida um código de produto e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * um produto com o código passado. Para isto, utilize os métodos
	 * de busca por código.
	 * 
	 * @param codigoEmpresa O código do produto a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 */
	boolean isCodigoValido(String codigoProduto);
	
	/**
	 * Busca por um produto da empresa {@code empresa} que possua o
	 * código de produto passado.
	 * 
	 * @param codigoProduto O código do produto a ser buscado.
	 * @param empresa A empresa a ser usada na pesquisa.
	 * @return O produto com o código passado, ou {@code null} caso
	 * o código seja inválido ou caso não exista um produto com este
	 * código.
	 */
	Produto obterPorEmpresaCodigo(Empresa empresa, String codigoProduto);
	
	/**
	 * Busca por todos os produtos que tenham o código de produto passado.
	 * 
	 * @param codigo O código de produto a ser pesquisado.
	 * @return Uma lista contento todos os produtos com o código passado,
	 * ou {@code null} caso o código seja inválido ou em caso de erro.
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
