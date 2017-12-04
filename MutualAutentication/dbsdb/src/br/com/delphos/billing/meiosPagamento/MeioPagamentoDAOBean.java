package br.com.delphos.billing.meiosPagamento;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class MeioPagamentoDAOBean
 */
@Stateless
public class MeioPagamentoDAOBean extends AbstractDAO<MeioPagamento> implements MeioPagamentoDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public MeioPagamentoDAOBean() {
		super(MeioPagamento.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public MeioPagamento obterPorIdentidade(MeioPagamento entidade) {
		String jpql = "select o from MeioPagamento o"
				+ " where o.codigo = :codigo";
		TypedQuery<MeioPagamento> query = getEntityManager().createQuery(jpql, MeioPagamento.class);
		query.setParameter(MeioPagamento_.codigo.getName(), entidade.getCodigo());
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public MeioPagamento obterPorCodigo(String codigo) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(MeioPagamento.class) + " o where o.codigo = :codigo";
		TypedQuery<MeioPagamento> query = getEntityManager().createQuery(jpql, MeioPagamento.class);
		query.setParameter("codigo", codigo);
		return JpaUtils.getSingleResult(query);
	}
	
	public List<MeioPagamento> listarPorCriterio(String codigo, String descricao) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(MeioPagamento.class) + " o"
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
				
				TypedQuery<MeioPagamento> query = getEntityManager().createQuery(jpql, MeioPagamento.class);
				if (!Validador.vazio(codigo)) {
					query.setParameter("codigo", codigo.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricao)) {
					query.setParameter("descricao", descricao.toUpperCase() + "%");
				}
		return query.getResultList();		
	}
	
}
