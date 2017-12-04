package br.com.delphos.billing.tentativas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.vendas.Venda;

/**
 * Session Bean implementation class TentativaDAOBean
 */
@Stateless
public class TentativaDAOBean extends AbstractDAO<Tentativa> implements TentativaDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public TentativaDAOBean() {
		super(Tentativa.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Tentativa obterPorIdentidade(Tentativa entidade) {
		String jpql = "select o from Tentativa o"
				+ " where o.cobranca.id = :cobranca"
				+ " and o.numeroTentativa = :numeroTentativa";
		TypedQuery<Tentativa> query = getEntityManager().createQuery(jpql, Tentativa.class);
		query.setParameter(Tentativa_.cobranca.getName(), entidade.getCobranca().getId());
		query.setParameter(Tentativa_.numeroTentativa.getName(), entidade.getNumeroTentativa());
		return JpaUtils.getSingleResult(query);
	}
	
	public List<Tentativa> listarTentativasCobrancaPorVenda(Venda venda) {
		
		List<Tentativa>  tentativas		  = null;
		
		String jpql = "select t from " + JpaUtils.getNomeEntidade(Tentativa.class) + " t "
				+ " inner join fetch t.cobranca c "
				+ " inner join fetch c.venda v "
				+ " where t.cobranca = c "
				+ " and v = :venda "
				+ " order by t.numeroTentativa ";
		
		TypedQuery<Tentativa> query =entityManager.createQuery(jpql, Tentativa.class);
		
							 query.setParameter("venda", venda);
		
				tentativas =  query.getResultList();	
		return tentativas;
		
	}
	
	public List<Tentativa> listarTentativasPorCobranca(Cobranca cobranca) {
		
		List<Tentativa> tentativas	= null;
		String jpql = "select t from " + JpaUtils.getNomeEntidade(Tentativa.class) + " t "
				+ " inner join fetch t.cobranca c "
				+ " where t.cobranca = c and c = :cobranca"
				+ " order by t.numeroTentativa";
		TypedQuery<Tentativa> query =entityManager.createQuery(jpql, Tentativa.class);
		
		query.setParameter("cobranca", cobranca);
		tentativas =  query.getResultList();	
		return tentativas;
		
	}

}
