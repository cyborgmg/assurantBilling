package br.com.delphos.billing.vendas;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.produtos.Produto;

@Local
public interface VendaDAO extends DAOEntidade<Venda> {

	/**
	 * Busca por uma venda que seja do produto e empresa passados, e que tenha
	 * o código de venda informado.
	 * 
	 * @param codigoVendaOrigem O código da venda no sistema de origem.
	 * @param produto O produto a ser usado na pesquisa. 
	 * @param empresa A empresa a ser usado na pesquisa.
	 * @return A venda referente à esta combinação, ou {@code null} caso não
	 * exista ou caso um erro ocorra durante o processo de busca. 
	 */
	Venda obterVendaAtivaPorProdutoEmpresaCodigo(Produto produto, Empresa empresa, String codigoVendaOrigem);

	List<Venda> listarVendasConcretizadas(Venda venda,Date dataInicial,Date dataFinal,StatusCobranca status);
	
	List<Venda> listarVendasConcretizadas(Venda venda,Date dataInicial,Date dataFinal,ItemLista itemLista);
	
	List<Venda> listarVendasEstornadas(Venda venda,Date dataInicial,Date dataFinal,StatusCobranca status);
	
	List<Venda> listarVendasEstornadas(Venda venda,Date dataInicial,Date dataFinal,ItemLista itemLista);
	
	List<Venda> listarVendasPorCriterio(String idEmpresa, String idProduto, String idSistema, String cpf, String certificado);
	
    public Venda obterVendaPorId(String idVenda);
	
	public List<Venda> listarVendasPorEmpresa(String idEmpresa);
	
	public long contarVendasPorEmpresa(String idEmpresa);

}
