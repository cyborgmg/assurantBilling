package br.com.delphos.billing.exportacoes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;

/**
 * Session Bean implementation class ExportacaoCobrancaDAOBean
 */
@Stateless
public class ExportacaoCobrancaDAOBean extends AbstractDAO<ExportacaoCobranca> implements ExportacaoCobrancaDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ExportacaoCobrancaDAOBean() {
		super(ExportacaoCobranca.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ExportacaoCobranca obterPorIdentidade(ExportacaoCobranca entidade) {
		ExportacaoCobranca retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from ExportacaoCobranca o"
					+ " where o.id = :id";
			TypedQuery<ExportacaoCobranca> query = getEntityManager().createQuery(jpql, ExportacaoCobranca.class);
			query.setParameter("id", entidade.getId());
			return JpaUtils.getSingleResult(query);	
		}
		return retorno;
	}
	
}
