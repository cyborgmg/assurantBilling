package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class AdquirenteDAOBean
 */
@Stateless
public class AdquirenteDAOBean extends AbstractDAO<Adquirente> implements AdquirenteDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public AdquirenteDAOBean() {
		super(Adquirente.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Adquirente obterPorIdentidade(Adquirente entidade) {
		String jpql = "select o from Adquirente o"
				+ " where o.nome = :nome";
		TypedQuery<Adquirente> query = getEntityManager().createQuery(jpql, Adquirente.class);
		query.setParameter(Adquirente_.nome.getName(), entidade.getNome());
		return JpaUtils.getSingleResult(query);
	}
	
	public List<Adquirente> listarPorCriterio(String codigoConvenio, String nome, String codigoAfiliacao) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Adquirente.class) + " o"
				+ " where ";
				if (!Validador.vazio(codigoConvenio)) {
					jpql += "upper(o.codigoConvenio) like :codigo ";
				}
				if (!Validador.vazio(nome)) {
					if (!Validador.vazio(codigoConvenio)) {
						jpql += " and ";
					}
					jpql += " upper(o.nome) like :nome ";
				}
				if (!Validador.vazio(codigoAfiliacao)) {
					if (!Validador.vazio(codigoConvenio) || !Validador.vazio(nome)) {
						jpql += " and ";
					}
					jpql += " upper(o.codigoAfiliacaoConciliador) like :codigoAfiliacao ";
				}
				
				jpql += " order by o.nome";
				
				TypedQuery<Adquirente> query = getEntityManager().createQuery(jpql, Adquirente.class);
				if (!Validador.vazio(codigoConvenio)) {
					query.setParameter("codigo", codigoConvenio.toUpperCase() + "%");
				}
				if (!Validador.vazio(nome)) {
					query.setParameter("nome", nome.toUpperCase() + "%");
				}
				if (!Validador.vazio(codigoAfiliacao)) {
					query.setParameter("codigoAfiliacao", Integer.parseInt(codigoAfiliacao));
				}
		return query.getResultList();		
	}
	
	public List<Adquirente> listarAdquirenteOrdenado() {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Adquirente.class) + " o ";
		//+ " where 1=1";
		jpql += " order by o.nome";
		TypedQuery<Adquirente> query = getEntityManager().createQuery(jpql, Adquirente.class);
		return query.getResultList();	
	}
}
