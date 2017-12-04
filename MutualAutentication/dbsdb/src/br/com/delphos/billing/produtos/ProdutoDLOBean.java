package br.com.delphos.billing.produtos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class ProdutoDLOBean
 */
@Stateless
public class ProdutoDLOBean extends AbstractDLO<Produto> implements ProdutoDLO {
	
	@EJB
	private ProdutoDAO dao;
	
	@EJB EmpresaDLO empresaDLO; 

	@Override
	protected ProdutoDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isCodigoValido(String codigoProduto) {
		return codigoProduto != null && codigoProduto.length() >= 1 && codigoProduto.length() <= 10;
	}

	@Override
	public Produto obterPorEmpresaCodigo(Empresa empresa, String codigoProduto) {
		Produto retorno = null;
		empresa = empresaDLO.obterEntidadeAtualizada(empresa);
		if (isCodigoValido(codigoProduto) && empresa != null) {
			retorno = dao.obterPorEmpresaCodigo(empresa, codigoProduto);
		}
		return retorno;
	}
	
	@Override
	public List<Produto> listarPorCodigo(String codigo) {
		List<Produto> retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.listarPorCodigo(codigo);
		}
		return retorno;
	}
	
	public List<Produto> listarProdutosPorCriterio(String codigoProduto, String descricaoProduto, String codigoEmpresa) {
		List<Produto> retorno = null;
		retorno = dao.listarProdutosPorCriterio(codigoProduto, descricaoProduto, codigoEmpresa);
		return retorno;		
	}

	@Override
	public Produto obterPorCodigo(String codigo) {
		Produto retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.obterPorCodigo(codigo);
		}
		return retorno;
	}
	
	public void alterarProduto(Produto produto) {
		dao.alterar(produto);
	}

	public void excluirProduto(Produto produto) {
		dao.excluir(produto);
	}

	public void incluirProduto(Produto produto) {
		dao.incluir(produto);
	}
	
	public List<Produto> listarPorEmpresa(String codigoEmpresa) {
		List<Produto> retorno = null;
		retorno = dao.obterProdutosPorEmpresa(codigoEmpresa);
		return retorno;
	}
	
	public List<Produto> listarProdutosSemAssociacao() {
		List<Produto> retorno = null;
		retorno = dao.obterProdutosSemAssociacao();
		return retorno;
	}
	
	public void associarProdutosEmpresa(String csvProdutos, Empresa empresa) {
		empresa = empresaDLO.obterEntidadeAtualizada(empresa);
		
		dao.associarProdutosEmpresa(csvProdutos, empresa);
		
	}
	
	public List<Produto> obterProdutosNaoRelacionadosAoContrato(String idContratoCobranca) {
		return dao.obterProdutosNaoRelacionadosAoContrato(idContratoCobranca);
	}

	@Override
	public boolean isEditavel(Produto produto) throws DLOException {
		
		return contarReferencias(produto, Produto_.vendas) == 0;
	}
	
}
