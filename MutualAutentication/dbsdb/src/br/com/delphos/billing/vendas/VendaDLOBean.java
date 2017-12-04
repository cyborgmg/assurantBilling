package br.com.delphos.billing.vendas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.Validador;
/**
 * Session Bean implementation class VendaDLOBean
 */
@Stateless
public class VendaDLOBean extends AbstractDLO<Venda> implements VendaDLO {
	
	@EJB
	private VendaDAO dao;
	
	@EJB private ProdutoDLO produtoDLO;
	@EJB private EmpresaDLO empresaDLO;

	@Override
	protected VendaDAO getDAOEntidade() {
		return dao;
	}

	/**
	 * @see VendaDAOBean#obterPorIdentidade(Venda)
	 */
	@Override
	protected boolean isEntidadePersistidaPorIdentidade(Venda entidade) {
		return false;
	}
	
	@Override
	protected Venda obterEntidadePorIdentidade(Venda entidade) {
		return entidade;
	}
	
	@Override
	public boolean isCodigoValido(String codigo){
		
//		if (codigo == null || Validador.vazio(codigo)) {
//			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.codigoVenda")));
//		}
		
		return codigo != null && codigo.length() >= 1 && codigo.length() <= 36;
	}

	@Override
	public Venda obterVendaAtivaPorProdutoEmpresaCodigo(Produto produto, Empresa empresa, String codigoVendaOrigem){
		
//		if (codigoVendaOrigem == null || Validador.vazio(codigoVendaOrigem)) {
//			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.codigoVendaOrigem")));
//		}
//		if (empresa == null) {
//			throw new DLOException(Mensagens.get("parametro.nao.informado", Mensagens.get("parametro.validacaoDLO.empresa")));
//		}
//		if (produto == null) {
//			throw new DLOException(Mensagens.get("parametro.nao.informado", Mensagens.get("parametro.validacaoDLO.produto")));
//		}
		
		Venda retorno = null;
		produto = produtoDLO.obterEntidadeAtualizada(produto);
		empresa = empresaDLO.obterEntidadeAtualizada(empresa);
		
		if (isCodigoValido(codigoVendaOrigem) && produto != null && empresa != null) {
			retorno = dao.obterVendaAtivaPorProdutoEmpresaCodigo(produto, empresa, codigoVendaOrigem);
		} 
//		else {
//			throw new DLOException(Mensagens.get("parametro.invalido", Mensagens.get("parametro.validacaoDLO.codigoVendaOrigem")));			
//		}
		return retorno;
	}
	
	public List<Venda> listarVendasPorCriterio(String idEmpresa, String idProduto, String idSistema, String cpf, String certificado){
		List <Venda> listaRetorno = new ArrayList<Venda>();
		
		boolean isCPF 			=  (cpf!=null && !cpf.isEmpty());
		boolean isCertificado 	=  (certificado!=null && !certificado.isEmpty());
		
//        if (!isCPF && !isCertificado) {
//	        throw new DLOException("erro.cpf.certificado.vazio");
//        }
        
		listaRetorno = dao.listarVendasPorCriterio(idEmpresa, idProduto, idSistema, cpf, certificado);
		return listaRetorno;		
	}
	
	public Venda obterVendaPorId(String idVenda){
		
//		if (idVenda == null || Validador.vazio(idVenda)) {
//			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.idVenda")));
//		}
		
		return dao.obterVendaPorId(idVenda);
	}
	
	public long contarVendasPorEmpresa(String idEmpresa) {
		return dao.contarVendasPorEmpresa(idEmpresa);
	}
	
	@Override
	public List<Venda> listarVendasConcretizadas(Venda venda, Date dataInicial,
			Date dataFinal,StatusCobranca status){
		return dao.listarVendasConcretizadas(venda, dataInicial, dataFinal, status);
	}

	@Override
	public Cobranca obterUltimaCobranca(Venda venda, StatusCobranca... estados){
		venda = completar(venda, Venda_.cobrancas);
		
//		if (venda == null) {
//			throw new ValidacaoDLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.venda")));
//		}
//		
//		if (venda.getCobrancas().isEmpty()) {
//			throw new ValidacaoDLOException(Mensagens.get("parametro.validacaoDLO.naoHaCobrancas"));
//		}
		
		Cobranca retorno = null;
		
		if (estados != null && estados.length > 0) {
			
			for (int i = venda.getCobrancas().size() - 1; retorno == null && i >= 0 ; --i) {
				Cobranca cobranca = venda.getCobrancas().get(i);
				
				for (StatusCobranca estado : estados) {
					
					if (cobranca.getStatusCobranca() == estado) {
						retorno = cobranca;
						break;
					}
				}
			}
			
		} else {
			retorno = venda.getCobrancas().get(venda.getCobrancas().size() - 1);
		}
		
		return retorno;
	}
		
	@Override
	public List<Venda> listarVendasEstornadas(Venda venda, Date dataInicial,
			Date dataFinal, StatusCobranca status){
		return dao.listarVendasEstornadas(venda, dataInicial, dataFinal, status);
	}

	@Override
	public List<Venda> listarVendasConcretizadas(Venda venda, Date dataInicial,
			Date dataFinal, ItemLista itemLista){
		return dao.listarVendasConcretizadas(venda, dataInicial, dataFinal, itemLista);
	}

	@Override
	public List<Venda> listarVendasEstornadas(Venda venda, Date dataInicial,
			Date dataFinal, ItemLista itemLista){
		return dao.listarVendasEstornadas(venda, dataInicial, dataFinal, itemLista);
	}

	@Override
	public boolean isEditavel(Venda entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
}
