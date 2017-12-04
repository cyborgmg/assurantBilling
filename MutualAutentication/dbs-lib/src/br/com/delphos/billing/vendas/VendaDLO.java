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
	 * Valida um c�digo de venda e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este m�todo realiza apenas valida��es b�sicas e de natureza
	 * sint�tica. N�o est� contemplada a verifica��o de exist�ncia de
	 * uma venda com o c�digo passado. Para isto, utilize os m�todos
	 * de busca por c�digo.
	 * 
	 * @param codigo O c�digo da venda a ser validado.
	 * @return {@code true} se for v�lido, e {@code false} caso contr�rio.
	 * @ 
	 */
	boolean isCodigoValido(String codigo) ;
	
	/**
	 * Busca por uma venda que seja do produto e empresa passados, e que tenha
	 * o c�digo de venda informado.
	 * 
	 * @param codigoVendaOrigem O c�digo da venda no sistema de origem.
	 * @param produto O produto a ser usado na pesquisa. 
	 * @param empresa A empresa a ser usado na pesquisa.
	 * @return A venda referente � esta combina��o, ou {@code null} caso n�o
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
	 * Obt�m a �ltima cobran�a que esteja com algum dos estados passados.
	 * 
	 * @param venda A venda a ser usada.
	 * @param estado A lista de estados a serem considerados.
	 * @return Uma cobran�a com um dos estados na lista, ou a �ltima cobran�a
	 * caso n�o tenha sido passado nenhum estado.
	 * @ Caso a venda seja nula, ou caso a venda n�o tenha
	 * cobran�as.
	 */
	Cobranca obterUltimaCobranca(Venda venda, StatusCobranca... estados) ;
	
	public long contarVendasPorEmpresa(String idEmpresa);

}
