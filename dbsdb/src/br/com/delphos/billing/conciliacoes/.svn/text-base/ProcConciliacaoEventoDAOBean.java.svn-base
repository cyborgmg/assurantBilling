package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.util.JpaUtils;

@Stateless
public class ProcConciliacaoEventoDAOBean extends AbstractDAO<ProcConciliacaoEvento> implements ProcConciliacaoEventoDAO {

	public ProcConciliacaoEventoDAOBean() {
		super(ProcConciliacaoEvento.class);
	}

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ProcConciliacaoEvento obterPorIdentidade(
			ProcConciliacaoEvento entidade) {
		return null;
	}

	@Override
	public ProcConciliacaoEvento obterPorProcessamentoConciliacao(ProcessamentoConciliacao pc) {
		
		ProcConciliacaoEvento procConciliacaoEvento = null;
		TypedQuery<ProcConciliacaoEvento> query =  null;
		
				String jpql = "select pce from " + JpaUtils.getNomeEntidade(ProcConciliacaoEvento.class) + " pce where pce.processamentoConciliacao=:id";
				
				query =		getEntityManager().createQuery(jpql, ProcConciliacaoEvento.class);
				query.setParameter("id", pc);
				
				List<ProcConciliacaoEvento> list	=(List<ProcConciliacaoEvento>) query.getResultList();
				if(!list.isEmpty()){
					return list.get(0);
				}else
					return null;
	
	}

	public List<ProcConciliacaoEvento> listarConciliacaoPorCobranca(Cobranca cobranca) {
		
		List<ProcConciliacaoEvento> conciliacao	= null;
		String jpql = "select p from " + JpaUtils.getNomeEntidade(ProcConciliacaoEvento.class) + " p "
				+ " join p.processamentoConciliacao pc "
				+ " where pc.orderIdSale = :cobranca"
				+ " order by p.dataProcessamento desc";
		
		TypedQuery<ProcConciliacaoEvento> query =entityManager.createQuery(jpql, ProcConciliacaoEvento.class);
		
		query.setParameter("cobranca", String.valueOf(cobranca.getId()));
		conciliacao =  query.getResultList();	
		return conciliacao;
		
	}
}
