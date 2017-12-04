package br.com.delphos.billing.exportacoes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;

/**
 * Session Bean implementation class ExportacaoConciliacaoDAOBean
 */
@Stateless
public class ExportacaoConciliacaoDAOBean extends AbstractDAO<ExportacaoConciliacao> implements ExportacaoConciliacaoDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ExportacaoConciliacaoDAOBean() {
		super(ExportacaoConciliacao.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ExportacaoConciliacao obterPorIdentidade(ExportacaoConciliacao entidade) {
		ExportacaoConciliacao retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from ExportacaoConciliacao o"
					+ " where o.id = :id";
			TypedQuery<ExportacaoConciliacao> query = getEntityManager().createQuery(jpql, ExportacaoConciliacao.class);
			query.setParameter("id", entidade.getId());
			return JpaUtils.getSingleResult(query);	
		}
		return retorno;
	}

}
