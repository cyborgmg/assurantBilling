package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;

/**
 * Session Bean implementation class ConciliacaoRecepcaoDAOBean
 */
@Stateless
public class ProcessamentoConciliacaoDAOBean extends AbstractDAO<ProcessamentoConciliacao> implements ProcessamentoConciliacaoDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ProcessamentoConciliacaoDAOBean() {
		super(ProcessamentoConciliacao.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ProcessamentoConciliacao obterPorIdentidade(ProcessamentoConciliacao entidade) {
		if (entidade.getId() == null) {
			return null;
		}
		String jpql = "select o from ProcessamentoConciliacao o"
				+ " where o.id = :id";
		TypedQuery<ProcessamentoConciliacao> query = getEntityManager().createQuery(jpql, ProcessamentoConciliacao.class);
		query.setParameter(ProcessamentoConciliacao_.id.getName(), entidade.getId());
		ProcessamentoConciliacao retorno = JpaUtils.getSingleResult(query);
		return retorno;
	}
	
	@Override
	public ProcessamentoConciliacao obterPorCobranca(Cobranca c) {
		
		TypedQuery<ProcessamentoConciliacao> query =  null;
		
				String jpql = "select pc from " + JpaUtils.getNomeEntidade(ProcessamentoConciliacao.class) + " pc where pc.orderIdSale=:orderId";
				
				query =		getEntityManager().createQuery(jpql, ProcessamentoConciliacao.class);
				query.setParameter("orderId",(c.getId().toString()));
				
				List<ProcessamentoConciliacao> list	=(List<ProcessamentoConciliacao>) query.getResultList();
				if(!list.isEmpty()){
					return list.get(0);
				}else{
					return null;
				}	
	}
	
}
