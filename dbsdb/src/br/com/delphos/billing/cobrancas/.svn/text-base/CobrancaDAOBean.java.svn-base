package br.com.delphos.billing.cobrancas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.conciliacoes.ConciliacaoInterface;
import br.com.delphos.billing.conciliacoes.EventoConciliacao;
import br.com.delphos.billing.conciliacoes.EventoConciliacao_;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento_;
import br.com.delphos.billing.conciliacoes.ProcessamentoConciliacao;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.StatusProcConciliacaoEvento;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;	
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDAO;
import br.com.delphos.billing.parametros.Parametro;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.Venda_;


/**
 * Session Bean implementation class CobrancaDAOBean
 */
@Stateless
public class CobrancaDAOBean extends AbstractDAO<Cobranca> implements CobrancaDAO {
	private static final long serialVersionUID = 1L;
	public static Logger LOGGER = LoggerFactory.getLogger(CobrancaDAOBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	@EJB
	private ItemListaDAO itemListaDao;
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	private static final Comparator<Tentativa> MAIOR_NUM_TENTATIVA = new Comparator<Tentativa>() { 
		public int compare(Tentativa tentativa1, Tentativa tentativa2) { return tentativa2.getNumeroTentativa() - tentativa1.getNumeroTentativa(); } }; 
	
	
	public CobrancaDAOBean() {
		super(Cobranca.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Cobranca obterPorIdentidade(Cobranca entidade) {
		String jpql = "select o from Cobranca o"
				+ " where o.venda.id = :venda"
				+ " and o.numeroParcela = :numeroParcela";
		TypedQuery<Cobranca> query = getEntityManager().createQuery(jpql, Cobranca.class);
		query.setParameter(Cobranca_.venda.getName(), entidade.getVenda().getId());
		query.setParameter(Cobranca_.numeroParcela.getName(), entidade.getNumeroParcela());
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public List<Cobranca> listarPrimeirasCobrancasPendentes(Parametro parametro) {
		
		List<Cobranca>  cobrancas		  = null;
		List<Cobranca>  cobrancasPendentes= new ArrayList<Cobranca>();
		List<Tentativa> tentativas        = null;
		
		String jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c where c.statusCobranca = :statusCobranca and c.numeroParcela = 1";
		
		TypedQuery<Cobranca> query =entityManager.createQuery(jpql, Cobranca.class);
		
							 query.setParameter("statusCobranca", StatusCobranca.Pendente.getValor());
		
				cobrancas =  query.getResultList();
		
				cobrancas.size();
		
				for (Cobranca cobranca : cobrancas) {
				
					cobranca.getTentativas().size();
					
					tentativas= cobranca.getTentativas();
				
					Collections.sort(tentativas, MAIOR_NUM_TENTATIVA); 
					
					Tentativa tentativa =tentativas.get(0);
					
					long difMilli =( new Date().getTime() - tentativa.getDataTentativa().getTime());  
					int timeInSeconds = (int)(difMilli / 1000);  
				
					if(timeInSeconds>parametro.getIntervaloPendencia())
						cobrancasPendentes.add(cobranca);
				}
		 
		return cobrancasPendentes;
	}

	public List<Cobranca> listarCobrancasSinglePremiumPendentes(Parametro parametro) {
		
		// Valores
		int intervaloPendencia = parametro.getIntervaloPendencia().intValue();
		int intervaloEstornoSolicitado = parametro.getIntervaloEstornoSolicitado().intValue();
		List<Cobranca> retorno = new ArrayList<Cobranca>();

		// Parâmetros
		List<String> listaStatusPesquisa = new ArrayList<String>();
		listaStatusPesquisa.add(StatusCobranca.Pendente.getValor());
		listaStatusPesquisa.add(StatusCobranca.EstornoSolicitado.getValor());
		List<String> listaTipoCobranca = new ArrayList<String>();
		listaTipoCobranca.add(TipoCobranca.SinglePremiumVista.getValor());
		listaTipoCobranca.add(TipoCobranca.SinglePremiumVistaOuParcelado.getValor());
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Cobranca> cq = cb.createQuery(Cobranca.class);
		Root<Cobranca> root = cq.from(Cobranca.class);
		
		cq.select(root)
			.where(
				cb.and(
					root.get(Cobranca_.statusCobranca).in(listaStatusPesquisa),
					root.get(Cobranca_.venda).get(Venda_.tipoCobranca).in(listaTipoCobranca)
				)
			)
		;
		
		// Efetivação da query
		TypedQuery<Cobranca> query = entityManager.createQuery(cq);
		List<Cobranca> cobrancas = query.getResultList();
		Calendar calDataHoje = DateUtils.getCalendarDataHoraAtual();
		Calendar calDataAlteracao = DateUtils.getCalendarDataHoraAtual();
		Calendar calProximaData = DateUtils.getCalendarDataHoraAtual();
		
		for (Cobranca cobranca : cobrancas) {
			Tentativa tentativa = obterUltimaTentativa(cobranca); //cobranca.getTentativas().get(cobranca.getTentativas().size() - 1);
			
			switch (cobranca.getStatusCobranca()) {
			case Pendente:
				calProximaData.setTime(tentativa.getDataTentativa());
				calProximaData.add(Calendar.SECOND, intervaloPendencia); // e.g. se dataTentativa = 16:00 e intervalo = 5 min, dataComparacao = 16:05, ou seja, deve ser no mínimo 16:05 para cancelar
				
				if (calDataHoje.compareTo(calProximaData) > 0) { // e.g. dataComparacao < dataAtual 
					retorno.add(cobranca);
				}
				
				break;
			case EstornoSolicitado:
				if (cobranca.getDataUltimaAlteracao() == null) {
					logger.warn("Cobrança \\{{}\\} será ignorada em listarCobrancasSinglePremiumPendentes"
							+ " por não possuir data da última alteração mesmo tendo status EstornoSolicitado.", cobranca);
					
				} else {
					calDataAlteracao.setTime(cobranca.getDataUltimaAlteracao());
					calProximaData.setTime(cobranca.getDataUltimaAlteracao());
					calProximaData.set(Calendar.HOUR_OF_DAY, 18);
					calProximaData.set(Calendar.MINUTE, 0);
					calProximaData.set(Calendar.SECOND, 0);
					calProximaData.set(Calendar.MILLISECOND, 0);
					
					if (calDataAlteracao.compareTo(calProximaData) <= 0) {
						calDataAlteracao.add(Calendar.DAY_OF_YEAR, intervaloEstornoSolicitado);
						
					} else {
						calDataAlteracao.add(Calendar.DAY_OF_YEAR, intervaloEstornoSolicitado + 1);
					}
					
					if (calDataHoje.compareTo(calDataAlteracao) >= 0) {
						retorno.add(cobranca);
					}
				}
				
				break;
			default:
				break;
			}
		}
		
		return retorno;
	}
	
	@Override
	public boolean isIdRequisicaoExistente(String id) {
		boolean retorno = false;
		String jpql = "select case when count(o) > 0 then true else false end"
				+ " from " + JpaUtils.getNomeEntidade(Cobranca.class) + " o"
				+ " where o.idRequisicaoProvedor = :id";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("id", id);
		retorno = (Boolean) JpaUtils.getSingleResult(query);
		return retorno;
	}
	
	public List<Cobranca> listarCobrancasPorVenda(Venda venda) {
		List<Cobranca>  cobrancas		  = null;
		
		String jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c where c.venda = :venda"
				+ " order by c.numeroParcela desc ";
		
		TypedQuery<Cobranca> query =entityManager.createQuery(jpql, Cobranca.class);
		
							 query.setParameter("venda", venda);
		
				cobrancas =  query.getResultList();	
		return cobrancas;
	}
	
	public List<Cobranca> listarCobrancasNaoConciliadas(Cobranca cobranca,double diasAtraso) {
		TypedQuery<Cobranca> query =  null;
		String 				  jpql =  null;
		
		boolean isCodigoEmpresa =  (cobranca.getVenda().getEmpresa()!=null && cobranca.getVenda().getEmpresa().getId()!=null);
		boolean isCodigoProduto =  (cobranca.getVenda().getProduto()!=null && cobranca.getVenda().getProduto().getId()!=null);
		boolean isCodigoSistema =  (cobranca.getVenda().getSistema()!=null && cobranca.getVenda().getSistema().getId()!=null);
		
		
						jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c ";
				
						jpql+= " where ( to_date(SYSDATE,'DD/MM/YYYY') - to_date(c.dataCobranca, 'DD/MM/YYYY') ) <=:diasAtraso   ";
		 		
						jpql+= " and   c.statusCobranca     = ('"+StatusCobranca.Capturada.getValor()+"') ";

						jpql+= " and   c.codigoAutorizacao IS NOT NULL ";
						
						
					if(isCodigoEmpresa)
		 				jpql+=" and c.venda.empresa.id =:pCodigoEmpresa ";
					if(isCodigoProduto)
		 				jpql+=" and c.venda.produto.id =:pCodigoProduto ";
		 			if(isCodigoSistema)
		 				jpql+=" and c.venda.sistema.id =:pCodigoSistema ";
		 			
		 			
		 			jpql+=" order by  c.dataCobranca asc ";
		 			
		 			query = getEntityManager().createQuery(jpql, Cobranca.class);
		 			
		 			
		 			query.setParameter("diasAtraso", diasAtraso);
		 			
		 			if(isCodigoEmpresa)
		 				query.setParameter("pCodigoEmpresa",cobranca.getVenda().getEmpresa().getId());
		 			
		 			if(isCodigoProduto)
		 				query.setParameter("pCodigoProduto",cobranca.getVenda().getProduto().getId());
		 			
		 			if(isCodigoSistema)
		 				query.setParameter("pCodigoSistema",cobranca.getVenda().getSistema().getId());
		 			
		return query.getResultList();
	}
	
	@Override
	public List<Tentativa> listarTentativas(Cobranca cobranca) {
		Cobranca cobrancaPersistida = obter(cobranca.getId());
		return cobrancaPersistida.getTentativas();
	}

	@Override
	public Tentativa obterUltimaTentativa(Cobranca cobranca, TipoTentativa... tiposTentativa) {

		String jpql = 
				  "select t "
				+ "  from " + JpaUtils.getNomeEntidade(Tentativa.class) + " t"
				+ " where t.cobranca.id = :idCobranca";
		
		if (tiposTentativa != null && tiposTentativa.length > 0) {
			jpql += " and t.tipoTentativa in :tiposTentativa";
		}
		
		jpql += " order by t.numeroTentativa desc";
		
		TypedQuery<Tentativa> query = entityManager.createQuery(jpql, Tentativa.class);
		query.setParameter("idCobranca", cobranca.getId());
		query.setMaxResults(1);
		
		if (tiposTentativa != null && tiposTentativa.length > 0) {
			List<String> tipos = new ArrayList<String>();
			for (TipoTentativa tipo : tiposTentativa) {
				tipos.add(tipo.getValor());
			}
			query.setParameter("tiposTentativa", tipos);
		}
		
		List<Tentativa> tentativas = query.getResultList();
		return tentativas.isEmpty() ? null : tentativas.get(0);
		
	}

	@Override
	public List<Cobranca> listarCobrancasConciliadasInterface(
			Cobranca cobranca, Date dataInicial, Date dataFinal,
			StatusConciliacaoInterface situacaoConciliacao) {
		
		 TypedQuery<Cobranca> query =  null;
		
		boolean isCodigoEmpresa =  	(cobranca.getVenda().getEmpresa()!=null && cobranca.getVenda().getEmpresa().getId()!=null);
		boolean isCodigoProduto =  	(cobranca.getVenda().getProduto()!=null && cobranca.getVenda().getProduto().getId()!=null);
		boolean isCodigoSistema =  	(cobranca.getVenda().getSistema()!=null && cobranca.getVenda().getSistema().getId()!=null);
	
		boolean isStatus        =  	(situacaoConciliacao!=null);
		
		boolean isPeriodo 		=	(dataInicial!=null&&dataFinal!=null);
		
		//String jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c , "+ JpaUtils.getNomeEntidade(ConciliacaoInterface.class) + " ci where c.id=ci.idecobr  and c.codigoAutorizacao IS NOT NULL ";
		String jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c , "+ JpaUtils.getNomeEntidade(ConciliacaoInterface.class) + " ci where c.id=ci.idecobr ";
		if(isStatus){
					
					if(StatusConciliacaoInterface.ATIVA==situacaoConciliacao){
							jpql+=( " and ci.statusProcessamento  = '" +(StatusConciliacaoInterface.ATIVA.getValor()) )+"'";
					}else if(StatusConciliacaoInterface.ANULADA==situacaoConciliacao){
							jpql+=( " and ci.statusProcessamento  = '" +(StatusConciliacaoInterface.ANULADA.getValor())  )+"'";
					}
		}else{
	    			jpql+= " and ci.statusProcessamento in('"+StatusConciliacaoInterface.ATIVA.getValor()+"','"+StatusConciliacaoInterface.ANULADA.getValor()+"')";
	    }
		
        if(isPeriodo)
				jpql+=( " and   ci.dataPagamento  between :pDataInicial and :pDataFinal ");
		 		
					if(isCodigoEmpresa)
		 				jpql+=" and c.venda.empresa.id =:pCodigoEmpresa ";
					if(isCodigoProduto)
		 				jpql+=" and c.venda.produto.id =:pCodigoProduto ";
		 			if(isCodigoSistema)
		 				jpql+=" and c.venda.sistema.id =:pCodigoSistema ";
		 			
		 			jpql+=" order by  c.dataCobranca asc ";
		 			
		 			query = getEntityManager().createQuery(jpql, Cobranca.class);
		 			
		 			if(isPeriodo){
		 				query.setParameter("pDataInicial", dataInicial);
			 			query.setParameter("pDataFinal"  , dataFinal  );	
		 			}
		 			
		 			if(isCodigoEmpresa)
		 				query.setParameter("pCodigoEmpresa", cobranca.getVenda().getEmpresa().getId());
		 			
		 			if(isCodigoProduto)
		 				query.setParameter("pCodigoProduto",cobranca.getVenda().getProduto().getId());
		 			
		 			if(isCodigoSistema)
		 				query.setParameter("pCodigoSistema", cobranca.getVenda().getSistema().getId());
		 			
		return query.getResultList();
	}

	@Override
	public List<Cobranca> listarCobrancasConciliadasInterface(
			Cobranca cobranca, Date dataInicial, Date dataFinal,
			ItemLista itemLista) {
		List<ItemLista> itemListaVendasConcretizadas=null;	

		 TypedQuery<Cobranca> query =  null;
			
			boolean isCodigoEmpresa =  	(cobranca.getVenda().getEmpresa()!=null && cobranca.getVenda().getEmpresa().getId()!=null);
			boolean isCodigoProduto =  	(cobranca.getVenda().getProduto()!=null && cobranca.getVenda().getProduto().getId()!=null);
			boolean isCodigoSistema =  	(cobranca.getVenda().getSistema()!=null && cobranca.getVenda().getSistema().getId()!=null);
		
			boolean isStatus        =  	(itemLista!=null);
			
			boolean isPeriodo 		=	(dataInicial!=null&&dataFinal!=null);
			
		
			String jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c , "+ JpaUtils.getNomeEntidade(ConciliacaoInterface.class) + " ci where c.id=ci.idecobr ";
				
			if(isStatus)
				jpql+= " and ci.statusProcessamento='"+itemLista.getCodigo()+"'";
					else{
						  List<ItemLista> itemListaTodos = itemListaDao.listarItemListaPorListaValor(TipoListaValor.SituacaoConciliacao.getValor());
						  
						    if (itemListaTodos != null && itemListaTodos.size() > 0) {
						    	itemListaVendasConcretizadas =new ArrayList<ItemLista>();
					           	for (ItemLista  il : itemListaTodos)
					           		if(il.getCodigo().equals(StatusConciliacaoInterface.ATIVA.getValor())
					           				||il.getCodigo().equals(StatusConciliacaoInterface.ANULADA.getValor()))
					           			itemListaVendasConcretizadas.add(il);
					           }
						
						String in ="";    
						
						 for(int i = 0;i<itemListaVendasConcretizadas.size();i++)
							 if(i!=itemListaVendasConcretizadas.size()-1)
								 in+="'"+itemListaVendasConcretizadas.get(i).getCodigo()+"',";
							 else
								 in+="'"+itemListaVendasConcretizadas.get(i).getCodigo()+"'";

						 jpql+= " and ci.statusProcessamento in("+in+")";
					}
			
			if(isPeriodo)
					jpql+=( " and   ci.dataPagamento  between :pDataInicial and :pDataFinal ");
			 		
						if(isCodigoEmpresa)
			 				jpql+=" and c.venda.empresa.id =:pCodigoEmpresa ";
						if(isCodigoProduto)
			 				jpql+=" and c.venda.produto.id =:pCodigoProduto ";
			 			if(isCodigoSistema)
			 				jpql+=" and c.venda.sistema.id =:pCodigoSistema ";
			 			
			 			jpql+=" order by  c.dataCobranca asc ";
			 			
			 			query = getEntityManager().createQuery(jpql, Cobranca.class);
			 			
			 			if(isPeriodo){
			 				query.setParameter("pDataInicial", dataInicial);
				 			query.setParameter("pDataFinal"  , dataFinal  );	
			 			}
			 			
			 			if(isCodigoEmpresa)
			 				query.setParameter("pCodigoEmpresa", cobranca.getVenda().getEmpresa().getId());
			 			
			 			if(isCodigoProduto)
			 				query.setParameter("pCodigoProduto",cobranca.getVenda().getProduto().getId());
			 			
			 			if(isCodigoSistema)
			 				query.setParameter("pCodigoSistema", cobranca.getVenda().getSistema().getId());
			 			
			return query.getResultList();
	}

	@Override
	public List<Cobranca> listarCobrancasConciliacao(Cobranca cobranca,
			Date dataInicial, Date dataFinal, ItemLista itemLista)
			{
		
		List<ItemLista> itemListaCobrancasConciliacao=null;	


		 TypedQuery<Cobranca> query =  null;
			
			boolean isCodigoEmpresa =  	(cobranca.getVenda().getEmpresa()!=null && cobranca.getVenda().getEmpresa().getId()!=null);
			boolean isCodigoProduto =  	(cobranca.getVenda().getProduto()!=null && cobranca.getVenda().getProduto().getId()!=null);
			boolean isCodigoSistema =  	(cobranca.getVenda().getSistema()!=null && cobranca.getVenda().getSistema().getId()!=null);
		
			boolean isStatus        =  	(itemLista!=null);
			
			boolean isPeriodo 		=	(dataInicial!=null&&dataFinal!=null);
			
		
			String jpql = "select c from " + JpaUtils.getNomeEntidade(Cobranca.class) + " c , "+ 
											 JpaUtils.getNomeEntidade(ProcessamentoConciliacao.class) + " p, " + 
					                         JpaUtils.getNomeEntidade(ProcConciliacaoEvento.class) + " pce "
					                         + "where c.id=p.orderIdSale and p.id = pce.processamentoConciliacao ";
				
			if(isStatus){
				if(itemLista.getCodigo().equals(StatusProcConciliacaoEvento.ATIVO.getValor())){
					jpql+= " and pce.statusProcessamento='"+StatusProcConciliacaoEvento.ATIVO.getValor()+"'";
				
				}else if(itemLista.getCodigo().equals(StatusProcConciliacaoEvento.ANULADO.getValor())){
					jpql+= " and pce.statusProcessamento='"+StatusProcConciliacaoEvento.ANULADO.getValor()+"'";
			    }else if(itemLista.getCodigo().equals(StatusProcConciliacaoEvento.REJEITADO.getValor())){
			    	jpql+= " and pce.statusProcessamento='"+StatusProcConciliacaoEvento.REJEITADO.getValor()+"'";
			    }
			}else{
						  List<ItemLista> itemListaTodos = itemListaDao.listarItemListaPorListaValor(TipoListaValor.EventoConciliacao.getValor());
						  
						    if (itemListaTodos != null && itemListaTodos.size() > 0) {
						    	itemListaCobrancasConciliacao =new ArrayList<ItemLista>();
					           	for (ItemLista  il : itemListaTodos)
					           		if(il.getCodigo().equals(StatusProcConciliacaoEvento.ATIVO.getValor())
					           		   || il.getCodigo().equals(StatusProcConciliacaoEvento.ANULADO.getValor())
					           		   || il.getCodigo().equals(StatusProcConciliacaoEvento.REJEITADO.getValor()))
					           			itemListaCobrancasConciliacao.add(il);
					           }
						
						String in ="";    
						
						 for(int i = 0;i<itemListaCobrancasConciliacao.size();i++)
							 if(i!=itemListaCobrancasConciliacao.size()){
								 if(itemListaCobrancasConciliacao.get(i).getCodigo().equals(StatusProcConciliacaoEvento.ATIVO.getValor())){
									 in+="'"+StatusProcConciliacaoEvento.ATIVO.getValor()+"',";
								 }else if(itemListaCobrancasConciliacao.get(i).getCodigo().equals(StatusProcConciliacaoEvento.ANULADO.getValor())){
									 in+="'"+StatusProcConciliacaoEvento.ANULADO.getValor()+"',";		
								 }else if(itemListaCobrancasConciliacao.get(i).getCodigo().equals(StatusProcConciliacaoEvento.REJEITADO.getValor())){
									 in+="'"+StatusProcConciliacaoEvento.REJEITADO.getValor()+"'";		
								}
							 }
					

						 jpql+= " and pce.statusProcessamento in("+in+")";
					}
			
			if(isPeriodo)
					jpql+=( " and  pce.eventDate  between :pDataInicial and :pDataFinal ");
			 	
						if(isCodigoEmpresa)
			 				jpql+=" and c.venda.empresa.id =:pCodigoEmpresa ";
						if(isCodigoProduto)
			 				jpql+=" and c.venda.produto.id =:pCodigoProduto ";
			 			if(isCodigoSistema)
			 				jpql+=" and c.venda.sistema.id =:pCodigoSistema ";
			 			
			 			jpql+=" order by  c.dataCobranca asc ";
			 			
			 			query = getEntityManager().createQuery(jpql, Cobranca.class);
			 			
			 			if(isPeriodo){
			 				query.setParameter("pDataInicial", dataInicial);
				 			query.setParameter("pDataFinal"  , dataFinal  );	
			 			}
			 			
			 			if(isCodigoEmpresa)
			 				query.setParameter("pCodigoEmpresa", cobranca.getVenda().getEmpresa().getId());
			 			
			 			if(isCodigoProduto)
			 				query.setParameter("pCodigoProduto",cobranca.getVenda().getProduto().getId());
			 			
			 			if(isCodigoSistema)
			 				query.setParameter("pCodigoSistema", cobranca.getVenda().getSistema().getId());

			return query.getResultList();
		
		
				
	}

	@Override
	public boolean isMovimentoConciliado(Cobranca cobranca, Integer numeroParcelaLoja) {
		String jpql = ""
				+ " select case when count(o) > 0 then true else false end"
				+ " from " + JpaUtils.getNomeEntidade(EventoConciliacao.class) + " o"
				+ " where o.cobranca.id = :id"
				+ " and ("
				+ "   ("      // para cancelamento e aceleração
				+ "     o.codigoEvento in :codigoEvento"
				+ "     and o.numeroParcelaLoja = :numeroParcelaLoja"
				+ "   ) or " // para estorno
				+ "     o.codigoEvento in :codigoEventoEstorno"
				+ "   "
				+ " )";
		
		List<String> codigosEvento = new ArrayList<String>();
		List<String> codigosEventoEstorno = new ArrayList<String>();
		codigosEvento.add(TipoEvento.CobrancaPaga.getValor());
		codigosEvento.add(TipoEvento.AceleracaoConciliada.getValor());
		
		codigosEventoEstorno.add(TipoEvento.EstornoConciliado.getValor());
		
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(Cobranca_.id.getName(), cobranca.getId());
		query.setParameter(EventoConciliacao_.codigoEvento.getName(), codigosEvento);
		query.setParameter(EventoConciliacao_.numeroParcelaLoja.getName(), numeroParcelaLoja);
		query.setParameter("codigoEventoEstorno", codigosEventoEstorno);
		
		boolean retorno = JpaUtils.getSingleResult(query, Boolean.class);
		return retorno;
	}

	@Override
	public Integer obterNumeroUltimaParcelaConciliada(Cobranca cobranca) {
		String jpql = ""
				+ " select max(ec.numeroParcelaLoja)"
				+ " from " + JpaUtils.getNomeEntidade(EventoConciliacao.class) + " ec"
				+ " where ec.cobranca.id = :cobranca"
				+ " and ec.codigoEvento in :codigoEvento";
		
		List<String> codigosEvento = new ArrayList<String>();
		codigosEvento.add(TipoEvento.CobrancaPaga.getValor());
		codigosEvento.add(TipoEvento.EstornoConciliado.getValor());
		codigosEvento.add(TipoEvento.AceleracaoConciliada.getValor());
		codigosEvento.add(TipoEvento.CapturaConciliada.getValor());
		
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(EventoConciliacao_.cobranca.getName(), cobranca.getId());
		query.setParameter(EventoConciliacao_.codigoEvento.getName(), codigosEvento);
		return JpaUtils.getSingleResult(query, Integer.class);
	}

	@Override
	public ProcConciliacaoEvento obterEventoProcessamento(Cobranca cobranca, Integer numeroParcela) {
		String jpql = ""
				+ " select pe "
				+ " from " + JpaUtils.getNomeEntidade(ProcConciliacaoEvento.class) + " pe"
				+ " where pe.processamentoConciliacao.orderId = :processamentoConciliacao"
				+ " and pe.transactionInstallment = :transactionInstallment"
				+ " order by pe.dataProcessamento desc";

		TypedQuery<ProcConciliacaoEvento> query = getEntityManager().createQuery(jpql, ProcConciliacaoEvento.class);
		query.setParameter(ProcConciliacaoEvento_.processamentoConciliacao.getName(), cobranca.getId());
		query.setParameter(ProcConciliacaoEvento_.transactionInstallment.getName(), numeroParcela);
		query.setMaxResults(1);
		return JpaUtils.getSingleResult(query);
	}
}
