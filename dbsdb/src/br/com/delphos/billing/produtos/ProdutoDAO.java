package br.com.delphos.billing.produtos;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ProdutoDAO extends DAOEntidade<Produto> {

	/**
	 * Busca por um produto quepertença à empresa {@code empresa} e que
	 * possua o código de produto passado.
	 * 
	 * @param empresa A empresa a ser usada na pesquisa.
	 * @param codigoProduto O código do produto a ser buscado.
	 * @return O produto com o código passado, ou {@code null} caso
	 * não exista um produto com este código.
	 */
	Produto obterPorEmpresaCodigo(Empresa empresa, String codigoProduto);

	/**
	 * Busca por todos os produtos que tenham o código de produto passado.
	 * 
	 * @param codigo O código de produto a ser pesquisado.
	 * @return Uma lista contento todos os produtos com o código passado,
	 * ou {@code null} em caso de erro.
	 */
	List<Produto> listarPorCodigo(String codigo);
	
	
	Produto obterPorCodigo(String codigo);
	
	public List<Produto> obterProdutosSemAssociacao();
	
	public List<Produto> obterProdutosPorEmpresa(String codigoEmpresa);
	
	public void associarProdutosEmpresa(String csvProdutos, Empresa empresa);
	
	public List<Produto> listarProdutosPorCriterio(String codigoProduto, String descricaoProduto, String codigoEmpresa);
	
	public List<Produto> obterProdutosNaoRelacionadosAoContrato(String idContratoCobranca);
	
}
