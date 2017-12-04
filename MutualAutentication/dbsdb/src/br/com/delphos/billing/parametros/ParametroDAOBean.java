package br.com.delphos.billing.parametros;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
@Stateless
public class ParametroDAOBean extends AbstractDAO<Parametro> implements ParametroDAO{

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ParametroDAOBean() {
		super(Parametro.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public Parametro obterPorIdentidade(Parametro entidade) {
		Parametro retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from Parametro o"
					+ " where o.id = :id";
			TypedQuery<Parametro> query = getEntityManager().createQuery(jpql, Parametro.class);
			query.setParameter(Parametro_.id.getName(), entidade.getId());
			retorno = JpaUtils.getSingleResult(query);
		}
		return retorno;
	}

	@Override
	public Parametro obterUltimosParametros() {
//		String jpql = "select first(o) from Parametro o"
		String jpql = "select o from Parametro o"
				+ " order by o.id desc";
		TypedQuery<Parametro> query = getEntityManager().createQuery(jpql, Parametro.class);
		query.setMaxResults(1);
		return JpaUtils.getSingleResult(query);
	}
	
}
