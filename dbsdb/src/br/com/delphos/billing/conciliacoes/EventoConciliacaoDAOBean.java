package br.com.delphos.billing.conciliacoes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;

@Stateless
public class EventoConciliacaoDAOBean extends AbstractDAO<EventoConciliacao> implements EventoConciliacaoDAO {

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public EventoConciliacaoDAOBean() {
		super(EventoConciliacao.class);
	}

	@Override
	public EventoConciliacao obterPorIdentidade(EventoConciliacao entidade) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(EventoConciliacao.class) + " o"
				+ " where o.cobranca.id = :cobranca"
				+ " and o.dataEvento = :dataEvento"
				+ " and o.codigoEvento = :codigoEvento";
		TypedQuery<EventoConciliacao> query = getEntityManager().createQuery(jpql, EventoConciliacao.class);
		query.setParameter(EventoConciliacao_.cobranca.getName(), entidade.getCobranca().getId());
		query.setParameter(EventoConciliacao_.dataEvento.getName(), entidade.getDataEvento());
		query.setParameter(EventoConciliacao_.codigoEvento.getName(), entidade.getTipo().getValor());
		EventoConciliacao retorno = JpaUtils.getSingleResult(query);
		return retorno;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
}
