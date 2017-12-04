package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.Cobranca_;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class BandeiraDAOBean
 */
@Stateless
public class BandeiraDAOBean extends AbstractDAO<Bandeira> implements BandeiraDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public BandeiraDAOBean() {
		super(Bandeira.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Bandeira obterPorIdentidade(Bandeira entidade) {
		String jpql = "select o from Bandeira o"
				+ " where o.codigoBandeira = :codigoBandeira";
		TypedQuery<Bandeira> query = getEntityManager().createQuery(jpql, Bandeira.class);
		query.setParameter(Bandeira_.codigoBandeira.getName(), entidade.getCodigoBandeira());
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public Bandeira obterPorCodigo(String codigoBandeira) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Bandeira.class) + " o where o.codigoBandeira = :codigoBandeira";
		TypedQuery<Bandeira> query = getEntityManager().createQuery(jpql, Bandeira.class);
		query.setParameter("codigoBandeira", codigoBandeira);
		return JpaUtils.getSingleResult(query);
	}
	
	public List<Bandeira> listarPorCriterio(String codigo, String nome) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Bandeira.class) + " o"
				+ " where ";
				if (!Validador.vazio(codigo)) {
					jpql += "upper(o.codigoBandeira) like :codigo ";
				}
				if (!Validador.vazio(nome)) {
					if (!Validador.vazio(codigo)) {
						jpql += " and ";
					}
					jpql += " upper(o.nomeBandeira) like :nome ";
				}
				jpql += " order by o.nomeBandeira";
				
				TypedQuery<Bandeira> query = getEntityManager().createQuery(jpql, Bandeira.class);
				if (!Validador.vazio(codigo)) {
					query.setParameter("codigo", codigo.toUpperCase() + "%");
				}
				if (!Validador.vazio(nome)) {
					query.setParameter("nome", nome.toUpperCase() + "%");
				}
		return query.getResultList();		
	}
	
}
