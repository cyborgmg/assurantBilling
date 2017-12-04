package br.com.delphos.billing.sistemas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class SistemaDAOBean
 */
@Stateless
public class SistemaDAOBean extends AbstractDAO<Sistema> implements SistemaDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public SistemaDAOBean() {
		super(Sistema.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Sistema obterPorIdentidade(Sistema entidade) {
		String jpql = "select o from Sistema o"
				+ " where o.codigo = :codigo";
		TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
		query.setParameter(Sistema_.codigo.getName(), entidade.getCodigo());
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public Sistema obterPorProdutoCodigo(Produto produto, String codigoSistema) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Sistema.class) + " o"
				+ " where o.codigoSistema = :codigoSistema"
				+ " and o.produto = :produto";
		TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
		query.setParameter("codigoSistema", codigoSistema);
		query.setParameter("produto", produto);
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public List<Sistema> listarPorCodigo(String codigo) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Sistema.class) + " o"
				+ " where o.codigo = :codigo";
		TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}
	
	public List<Sistema> listarPorCriterio(String codigo, String descricao) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Sistema.class) + " o"
				+ " where ";
				if (!Validador.vazio(codigo)) {
					jpql += "upper(o.codigo) like :codigo ";
				}
				if (!Validador.vazio(descricao)) {
					if (!Validador.vazio(codigo)) {
						jpql += " and ";
					}
					jpql += " upper(o.descricao) like :descricao ";
				}
				jpql += " order by o.descricao";
				
				TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
				if (!Validador.vazio(codigo)) {
					query.setParameter("codigo", codigo.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricao)) {
					query.setParameter("descricao", descricao.toUpperCase() + "%");
				}
		return query.getResultList();		
	}

	@Override
	public Sistema obterPorCodigo(String codigo) {
		String jpql = "select s from " + JpaUtils.getNomeEntidade(Sistema.class) + " s where s.codigo = :codigo";
		TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
		query.setParameter("codigo", codigo);
		return JpaUtils.getSingleResult(query);
	}
}
