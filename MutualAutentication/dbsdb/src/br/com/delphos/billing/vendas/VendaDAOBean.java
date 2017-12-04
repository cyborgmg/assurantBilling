package br.com.delphos.billing.vendas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusVenda;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDAO;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

/**
 * Session Bean implementation class VendaDAOBean
 */
@Stateless
public class VendaDAOBean extends AbstractDAO<Venda> implements VendaDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(VendaDAOBean.class);
	
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	@EJB
	private ItemListaDAO itemListaDao;
	
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;

	public VendaDAOBean() {
		super(Venda.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Método não implementado.
	 * 
	 * Dada a complexidade em definir a duplicidade das vendas e pelo fato de que 
	 * as vendas são persistidas exclusivamente pelos serviços (DBS e DBSTIMER),
	 * no momento, não haverá verificação de duplicidade antes da sua persistência.
	 */
	@Override
	public Venda obterPorIdentidade(Venda venda) {  
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Venda obterVendaAtivaPorProdutoEmpresaCodigo(Produto produto, Empresa empresa, String codigoVendaOrigem) {
		LoggerWrapper log = logger.entrando(null, produto, empresa, codigoVendaOrigem);
		// Bom candidato para um NamedQuery
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Venda.class) + " o"
				// Para produto + empresa + código
				+ " where o.codigoVendaOrigem = :codigoVendaOrigem"
				+ " and o.produto = :produto"
				+ " and o.empresa = :empresa"
				// Dentro do período de vigência
				+ " and o.dataFimVigencia >= :dataCorrente"
				//+ " and :dataCorrente < o.dataFimVigencia"
				//+ " and year(o.dataFimVigencia) <= year(:dataFim)"
				//+ " and month(o.dataFimVigencia) <= month(:dataFim)"
				//+ " and day(o.dataFimVigencia) <= day(:dataFim)"
				+ " and o.dataCancelamento is null"
				// Apenas Pendente e Efetivada
				+ " and o.status in :statuses";
		TypedQuery<Venda> query = getEntityManager().createQuery(jpql, Venda.class);
		Date dataCorrente = DateUtils.getDataHoraAtual();
		Set<String> statuses = new HashSet<String>();
		statuses.add(StatusVenda.Pendente.getValor());
		statuses.add(StatusVenda.Efetivada.getValor());
		query.setParameter("codigoVendaOrigem", codigoVendaOrigem);
		query.setParameter("produto", produto);
		query.setParameter("empresa", empresa);
		query.setParameter("dataCorrente", dataCorrente, TemporalType.DATE);
		query.setParameter("statuses", statuses);
		List<Venda> lista = query.getResultList();
		if (lista.size() > 1) {
			log.warn("Existe mais de uma venda ativa para o período!"
					+ " Data: {}"
					+ " Produto: \\{{}\\},"
					+ " Empresa: \\{{}\\},"
					+ " Código de venda: \"{}\","
					+ " Vendas retornadas: {}", 
					dataCorrente, produto, empresa, codigoVendaOrigem, lista);
		}
		Venda retorno = lista.isEmpty() ? null : lista.get(0);
		return log.saindo(retorno);
	}
	
	public List<Venda> listarVendasPorCriterio(String idEmpresa, String idProduto, String idSistema, String cpf, String certificado) {
		
		boolean isCodigoEmpresa =  (idEmpresa!=null && !idEmpresa.isEmpty());	
		boolean isCPF 			=  (cpf!=null && !cpf.isEmpty());
		boolean isCertificado 	=  (certificado!=null && !certificado.isEmpty());
		boolean isIdProduto =  (idProduto!=null && (!idProduto.isEmpty() && !idProduto.equals("0")));
		boolean isIdSistema =  (idSistema!=null && (!idSistema.isEmpty() && !idSistema.equals("0")));
		
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Venda.class) + " o"
				// Para produto + empresa + código
				+ " where " ;
		
        if (isCPF && isCertificado) {
        	jpql += " o.cpf = :cpf"
    				+ " and o.codigoVendaOrigem = :certificado";
        } else {
        	if(isCPF){
        		jpql += " o.cpf = :cpf";
        	}
        	if(isCertificado){
        		jpql += " o.codigoVendaOrigem = :certificado";
        	}
        }
        
        String arrEmpresas[] = null;
    	Set<String> setEmpresas = new HashSet<String>();
        
        if (isCodigoEmpresa) {
        	if (!idEmpresa.contains(",")) {
        		jpql += " and o.empresa.codigo = :empresa";
        	} else {
        		jpql += " and o.empresa.codigo in :empresa";
            	arrEmpresas = idEmpresa.split(",");
        	}
        	if (isIdProduto) {
        		jpql += " and o.produto.id = :produto";
        	}
        	if (isIdSistema) {
        		jpql += " and o.sistema.id = :sistema ";
        	}
        } 
        
        jpql += " order by o.dataVendaOrigem desc ";
       
		TypedQuery<Venda> query = getEntityManager().createQuery(jpql, Venda.class);
		
		if(isCPF){
			query.setParameter("cpf", cpf);
		}
		
		if(isCertificado){
			query.setParameter("certificado", certificado);
		}

		if (isCodigoEmpresa) {
			if (arrEmpresas != null) {
				for (int i = 0; i < arrEmpresas.length; i++) {
					setEmpresas.add(arrEmpresas[i]);
				}
				query.setParameter("empresa", setEmpresas);
			} else {
				query.setParameter("empresa", idEmpresa);
			}
			if (isIdProduto) {
				query.setParameter("produto", Long.valueOf(idProduto));
			}
			if (isIdSistema) {
				query.setParameter("sistema", Long.valueOf(idSistema));
			}
		}
		
		List<Venda> lista = query.getResultList();		
		return lista;
	}
	
	public List<Venda> listarVendasPorEmpresa(String idEmpresa) {
		
		boolean isCodigoEmpresa =  (idEmpresa!=null && !idEmpresa.isEmpty());	
		
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Venda.class) + " o"
				// Para produto + empresa + código
				+ " where " ;		
		jpql += " o.empresa.codigo = :empresa";
        
		TypedQuery<Venda> query = getEntityManager().createQuery(jpql, Venda.class);
		query.setParameter("empresa", idEmpresa);
		List<Venda> lista = query.getResultList();		
		return lista;		
	}
	
	public long contarVendasPorEmpresa(String idEmpresa) {
		
		String jpql = "select count(o) from " + JpaUtils.getNomeEntidade(Venda.class) + " o"
				+ " where " ;		
		jpql += " o.empresa.codigo = :empresa";
		
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("empresa", idEmpresa);
		return (Long) JpaUtils.getSingleResult(query);
	}
	
	public Venda obterVendaPorId(String idVenda) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Venda.class) + " o"
				+ " where o.id = :id" ;		
		TypedQuery<Venda> query = getEntityManager().createQuery(jpql, Venda.class);
		query.setParameter("id", Long.valueOf(idVenda));
		return query.getSingleResult();
		
	}
	
	@Override
	public List<Venda> listarVendasEstornadas(Venda venda, Date dataInicial,Date dataFinal, ItemLista itemLista) {
		TypedQuery<Venda> query =  null;
		List<ItemLista> itemListaVendasConcretizadas=null;	

		boolean isCodigoEmpresa =  (venda.getEmpresa()!=null && venda.getEmpresa().getId()!=null);
		boolean isCodigoProduto =  (venda.getProduto()!=null && venda.getProduto().getId()!=null);
		boolean isCodigoSistema =  (venda.getSistema()!=null && venda.getSistema().getId()!=null);
		
		boolean isStatus        =  (itemLista!=null);
		
		boolean isPeriodo =(dataInicial!=null&&dataFinal!=null);
		
		String jpql = "select v from " + JpaUtils.getNomeEntidade(Venda.class) + " v "
				+ " inner join fetch v.cobrancas c ";
		
		if(isStatus)
			jpql+= " where c.statusCobranca='"+itemLista.getCodigo()+"'";
				else{
					  List<ItemLista> itemListaTodos = itemListaDao.listarItemListaPorListaValor(TipoListaValor.Status.getValor());
					  
					    if (itemListaTodos != null && itemListaTodos.size() > 0) {
					    	itemListaVendasConcretizadas =new ArrayList<ItemLista>();
				           	for (ItemLista  il : itemListaTodos)
				           		if(il.getCodigo().equals(StatusCobranca.Estornada.getValor())||il.getCodigo().equals(StatusCobranca.EstornoSolicitado.getValor())||
				           			il.getCodigo().equals(StatusCobranca.EstornoConciliado.getValor())||il.getCodigo().equals(StatusCobranca.EstornoNegado.getValor()))
				           			itemListaVendasConcretizadas.add(il);
				           }
					
					String in ="";    
					
					 for(int i = 0;i<itemListaVendasConcretizadas.size();i++)
						 if(i!=itemListaVendasConcretizadas.size()-1)
							 in+="'"+itemListaVendasConcretizadas.get(i).getCodigo()+"',";
						 else
							 in+="'"+itemListaVendasConcretizadas.get(i).getCodigo()+"'";

					 jpql+= " where c.statusCobranca in("+in+")";
				}
		
		if(isPeriodo)
				jpql+=( " and   v.dataVendaOrigem   between :pDataInicial and :pDataFinal ");
		
		 		
				if(isCodigoEmpresa)
		 				jpql+=" and v.empresa.id =:pCodigoEmpresa ";
					if(isCodigoProduto)
		 				jpql+=" and v.produto.id =:pCodigoProduto ";
		 			if(isCodigoSistema)
		 				jpql+=" and v.sistema.id =:pCodigoSistema ";
		 					
		 			
		 			jpql+=" order by  v.dataVendaOrigem asc ";
		 			
		 			query = getEntityManager().createQuery(jpql, Venda.class);
		 			
		 			if(isPeriodo){
		 				query.setParameter("pDataInicial", dataInicial);
			 			query.setParameter("pDataFinal"  , dataFinal  );	
		 			}
		 			
		 			if(isCodigoEmpresa)
		 				query.setParameter("pCodigoEmpresa", venda.getEmpresa().getId());
		 			
		 			if(isCodigoProduto)
		 				query.setParameter("pCodigoProduto",venda.getProduto().getId());
		 			
		 			if(isCodigoSistema)
		 				query.setParameter("pCodigoSistema", venda.getSistema().getId());
		 			
		return query.getResultList();
	}
		
	public List<Venda> listarVendasEstornadas(Venda venda,Date dataInicial,Date dataFinal,StatusCobranca status) {
		TypedQuery<Venda> query =  null;
		
		boolean isCodigoEmpresa =  (venda.getEmpresa()!=null && venda.getEmpresa().getId()!=null);
		boolean isCodigoProduto =  (venda.getProduto()!=null && venda.getProduto().getId()!=null);
		boolean isCodigoSistema =  (venda.getSistema()!=null && venda.getSistema().getId()!=null);
		
		boolean isStatus        =  (status!=null);
		
		boolean isPeriodo =(dataInicial!=null&&dataFinal!=null);
		
		String jpql = "select v from " + JpaUtils.getNomeEntidade(Venda.class) + " v "
				+ " inner join fetch v.cobrancas c ";
		
		if(isStatus){
					if(StatusCobranca.Estornada==status)
						jpql+=( " where c.statusCobranca  = '" +(StatusCobranca.Estornada.getValor()) )+"'";
					if(StatusCobranca.EstornoSolicitado==status)
						jpql+=( " where c.statusCobranca  = '" +(StatusCobranca.EstornoSolicitado.getValor()))+"'";
					if(StatusCobranca.EstornoConciliado==status)
						jpql+=( " where c.statusCobranca  = '" +(StatusCobranca.EstornoConciliado.getValor()))+"'";
					if(StatusCobranca.EstornoNegado==status)
						jpql+=( " where c.statusCobranca  = '" +(StatusCobranca.EstornoNegado.getValor()))+"'";
				}else{
						jpql+= " where c.statusCobranca in('"+StatusCobranca.Estornada.getValor()+"','"+StatusCobranca.EstornoSolicitado.getValor()+"','"+StatusCobranca.EstornoConciliado.getValor()+"','"+StatusCobranca.EstornoNegado.getValor()+"')";
					}			
		
		if(isPeriodo)
				jpql+=( " and   v.dataVendaOrigem   between :pDataInicial and :pDataFinal ");
		
		 		
				if(isCodigoEmpresa)
		 				jpql+=" and v.empresa.id =:pCodigoEmpresa ";
					if(isCodigoProduto)
		 				jpql+=" and v.produto.id =:pCodigoProduto ";
		 			if(isCodigoSistema)
		 				jpql+=" and v.sistema.id =:pCodigoSistema ";
		 					
		 			
		 			jpql+=" order by  v.dataVendaOrigem asc ";
		 			
		 			query = getEntityManager().createQuery(jpql, Venda.class);
		 			
		 			if(isPeriodo){
		 				query.setParameter("pDataInicial", dataInicial);
			 			query.setParameter("pDataFinal"  , dataFinal  );	
		 			}
		 			
		 			if(isCodigoEmpresa)
		 				query.setParameter("pCodigoEmpresa", venda.getEmpresa().getId());
		 			
		 			if(isCodigoProduto)
		 				query.setParameter("pCodigoProduto",venda.getProduto().getId());
		 			
		 			if(isCodigoSistema)
		 				query.setParameter("pCodigoSistema", venda.getSistema().getId());
		 			
		 					 			
		 			
		return query.getResultList();
	}
	
	public List<Venda> listarVendasConcretizadas(Venda venda,Date dataInicial,Date dataFinal,StatusCobranca status) {
		TypedQuery<Venda> query =  null;
		
		boolean isCodigoEmpresa =  (venda.getEmpresa()!=null && venda.getEmpresa().getId()!=null);
		boolean isCodigoProduto =  (venda.getProduto()!=null && venda.getProduto().getId()!=null);
		boolean isCodigoSistema =  (venda.getSistema()!=null && venda.getSistema().getId()!=null);
		
		boolean isStatus        =  (status!=null);
		
		boolean isPeriodo =(dataInicial!=null&&dataFinal!=null);
		
		String jpql = "select v from " + JpaUtils.getNomeEntidade(Venda.class) + " v "
				+ " inner join fetch v.cobrancas c "
				+ " where c.numeroParcela   = 1 ";
		
		if(isStatus){
					if(StatusCobranca.Capturada==status)
						jpql+= " and   c.statusCobranca  in('"+StatusCobranca.Capturada.getValor()+"')";
					if(StatusCobranca.NaoAutorizada==status)
						jpql+=( " and   c.statusCobranca  = '" +(StatusCobranca.NaoAutorizada.getValor()))+"'";
				}else{
						jpql+= " and c.statusCobranca in('"+StatusCobranca.Capturada.getValor()+"','"+StatusCobranca.NaoAutorizada.getValor()+"')";
					}			
		
		if(isPeriodo)
				jpql+=( " and   v.dataVendaOrigem   between :pDataInicial and :pDataFinal ");
		
		 		
				if(isCodigoEmpresa)
		 				jpql+=" and v.empresa.id =:pCodigoEmpresa ";
					if(isCodigoProduto)
		 				jpql+=" and v.produto.id =:pCodigoProduto ";
		 			if(isCodigoSistema)
		 				jpql+=" and v.sistema.id =:pCodigoSistema ";
		 			
			
		 			/*
		 			 * validar caso não entrem no relatório as canceladas e outras
		 			 * jpql+=" and v.status =:pStatus " ;*/
		 			
		 			jpql+=" order by  v.dataVendaOrigem asc ";
		 			
		 			query = getEntityManager().createQuery(jpql, Venda.class);
		 			
		 			if(isPeriodo){
		 				query.setParameter("pDataInicial", dataInicial);
			 			query.setParameter("pDataFinal"  , dataFinal  );	
		 			}
		 			
		 			if(isCodigoEmpresa)
		 				query.setParameter("pCodigoEmpresa", venda.getEmpresa().getId());
		 			
		 			if(isCodigoProduto)
		 				query.setParameter("pCodigoProduto",venda.getProduto().getId());
		 			
		 			if(isCodigoSistema)
		 				query.setParameter("pCodigoSistema", venda.getSistema().getId());
		 			
		 			/*
		 			 * validar caso não entrem no relatório as canceladas e outras
		 			 * query.setParameter("pStatus", StatusVenda.Efetivada.getValor());//status EFE 
		 			 */		 			
		 			
		return query.getResultList();
	}

	@Override
	public List<Venda> listarVendasConcretizadas(Venda venda, Date dataInicial,
			Date dataFinal, ItemLista itemLista) {
		
		TypedQuery<Venda> query =  null;
		
		List<ItemLista> itemListaVendasConcretizadas=null;	
		
		boolean isCodigoEmpresa =  (venda.getEmpresa()!=null && venda.getEmpresa().getId()!=null);
		
		boolean isCodigoProduto =  (venda.getProduto()!=null && venda.getProduto().getId()!=null);
		
		boolean isCodigoSistema =  (venda.getSistema()!=null && venda.getSistema().getId()!=null);
		
		boolean isStatus        =  (itemLista!=null);
		
		boolean isPeriodo =(dataInicial!=null&&dataFinal!=null);
		
		String jpql = "select v from " + JpaUtils.getNomeEntidade(Venda.class) + " v "
				+ " inner join fetch v.cobrancas c "
				+ " where c.numeroParcela   = 1 ";
		
		if(isStatus)
			jpql+= " and c.statusCobranca='"+itemLista.getCodigo()+"'";
				else{
					  List<ItemLista> itemListaTodos = itemListaDao.listarItemListaPorListaValor(TipoListaValor.Status.getValor());
					  
					    if (itemListaTodos != null && itemListaTodos.size() > 0) {
					    	itemListaVendasConcretizadas =new ArrayList<ItemLista>();
				           	for (ItemLista  il : itemListaTodos)
				           		if(il.getCodigo().equals(StatusCobranca.Capturada.getValor())||il.getCodigo().equals(StatusCobranca.NaoAutorizada.getValor()))
				           			itemListaVendasConcretizadas.add(il);
				           }
					
					String in ="";    
					
					 for(int i = 0;i<itemListaVendasConcretizadas.size();i++)
						 if(i!=itemListaVendasConcretizadas.size()-1)
							 in+="'"+itemListaVendasConcretizadas.get(i).getCodigo()+"',";
						 else
							 in+="'"+itemListaVendasConcretizadas.get(i).getCodigo()+"'";

					 jpql+= " and c.statusCobranca in("+in+")";
				}
		
		if(isPeriodo)
				jpql+=( " and   v.dataVendaOrigem   between :pDataInicial and :pDataFinal ");
		
		 		
				if(isCodigoEmpresa)
		 				jpql+=" and v.empresa.id =:pCodigoEmpresa ";
					if(isCodigoProduto)
		 				jpql+=" and v.produto.id =:pCodigoProduto ";
		 			if(isCodigoSistema)
		 				jpql+=" and v.sistema.id =:pCodigoSistema ";
		 				
		 			jpql+=" order by  v.dataVendaOrigem asc ";
		 			
		 			query = getEntityManager().createQuery(jpql, Venda.class);
		 			
		 			if(isPeriodo){
		 				query.setParameter("pDataInicial", dataInicial);
			 			query.setParameter("pDataFinal"  , dataFinal  );	
		 			}
		 			
		 			if(isCodigoEmpresa)
		 				query.setParameter("pCodigoEmpresa", venda.getEmpresa().getId());
		 			
		 			if(isCodigoProduto)
		 				query.setParameter("pCodigoProduto",venda.getProduto().getId());
		 			
		 			if(isCodigoSistema)
		 				query.setParameter("pCodigoSistema", venda.getSistema().getId());
		 			
		return query.getResultList();
	}
	
}
