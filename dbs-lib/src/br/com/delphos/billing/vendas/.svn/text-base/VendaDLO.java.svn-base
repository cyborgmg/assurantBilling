package br.com.delphos.billing.vendas;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.persistencia.DLOEntidade;
import br.com.delphos.billing.produtos.Produto;

@Remote
public interface VendaDLO extends DLOEntidade<Venda> {
	
	/**
	 * Valida um código de venda e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * uma venda com o código passado. Para isto, utilize os métodos
	 * de busca por código.
	 * 
	 * @param codigo O código da venda a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 * @ 
	 */
	boolean isCodigoValido(String codigo) ;
	
	/**
	 * Busca por uma venda que seja do produto e empresa passados, e que tenha
	 * o código de venda informado.
	 * 
	 * @param codigoVendaOrigem O código da venda no sistema de origem.
	 * @param produto O produto a ser usado na pesquisa. 
	 * @param empresa A empresa a ser usado na pesquisa.
	 * @return A venda referente à esta combinação, ou {@code null} caso não
	 * exista ou caso um erro ocorra durante o processo de busca. 
	 * @ 
	 */
	Venda obterVendaAtivaPorProdutoEmpresaCodigo(Produto produto, Empresa empresa, String codigoVendaOrigem) ;

	List<Venda> listarVendasConcretizadas(Venda venda,Date dataInicial,Date dataFinal,StatusCobranca status) ;
	
	List<Venda> listarVendasConcretizadas(Venda venda,Date dataInicial,Date dataFinal,ItemLista itemLista) ;
	
	List<Venda> listarVendasEstornadas(Venda venda,Date dataInicial,Date dataFinal,ItemLista itemLista) ;
	
	List<Venda> listarVendasEstornadas(Venda venda,Date dataInicial,Date dataFinal,StatusCobranca status) ;
	
	List<Venda> listarVendasPorCriterio(String idEmpresa, String idProduto, String idSistema, String cpf, String certificado) ;
	
	Venda obterVendaPorId(String idVenda) ;

	/**
	 * Obtém a última cobrança que esteja com algum dos estados passados.
	 * 
	 * @param venda A venda a ser usada.
	 * @param estado A lista de estados a serem considerados.
	 * @return Uma cobrança com um dos estados na lista, ou a última cobrança
	 * caso não tenha sido passado nenhum estado.
	 * @ Caso a venda seja nula, ou caso a venda não tenha
	 * cobranças.
	 */
	Cobranca obterUltimaCobranca(Venda venda, StatusCobranca... estados) ;
	
	public long contarVendasPorEmpresa(String idEmpresa);

}
