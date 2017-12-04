package br.com.delphos.billing.provedores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class ProvedorDAOBean
 */
@Stateless
public class ProvedorDAOBean extends AbstractDAO<Provedor> implements ProvedorDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ProvedorDAOBean() {
		super(Provedor.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Provedor obterPorIdentidade(Provedor entidade) {
		String jpql = "select o from Provedor o"
				+ " where o.codigoProvedor = :codigoProvedor";
		TypedQuery<Provedor> query = getEntityManager().createQuery(jpql, Provedor.class);
		query.setParameter(Provedor_.codigoProvedor.getName(), entidade.getCodigoProvedor());
		return JpaUtils.getSingleResult(query);
	}
	
	@Override
	public Provedor obterPorCodigo(String codigo) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Provedor.class) + " o where o.codigoProvedor = :codigo";
		TypedQuery<Provedor> query = getEntityManager().createQuery(jpql, Provedor.class);
		query.setParameter("codigo", codigo);
		return JpaUtils.getSingleResult(query);
	}
	
	public List<Provedor> listarPorCriterio(String codigo, String descricao) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Provedor.class) + " o"
				+ " where ";
				if (!Validador.vazio(codigo)) {
					jpql += "upper(o.codigoProvedor) like :codigo ";
				}
				if (!Validador.vazio(descricao)) {
					if (!Validador.vazio(codigo)) {
						jpql += " and ";
					}
					jpql += " upper(o.descricaoProvedor) like :descricao ";
				}
				jpql += " order by o.descricaoProvedor";
				
				TypedQuery<Provedor> query = getEntityManager().createQuery(jpql, Provedor.class);
				if (!Validador.vazio(codigo)) {
					query.setParameter("codigo", codigo.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricao)) {
					query.setParameter("descricao", descricao.toUpperCase() + "%");
				}
		return query.getResultList();		
	}
     
}
