package br.com.delphos.billing.tentativas;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.vendas.Venda;

/**
 * Session Bean implementation class EventoDAOBean
 */
@Stateless
public class EventoDAOBean extends AbstractDAO<Evento> implements EventoDAO {

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;

	public EventoDAOBean() {
		super(Evento.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Método não implementado.
	 * 
	 * Dado o fato de que os eventos são lançados exclusivamente pelos serviços
	 * (DBS e DBSTIMER), no momento, não haverá verificação de duplicidade antes
	 * da sua persistência.
	 */
	@Override
	public Evento obterPorIdentidade(Evento entidade) {
//		String jpql = "select o from Evento o"
//				+ " where o.codigoEvento = :codigoEvento"
//				+ " and o.tentativa.id = :tentativa"
//				+ " and o.dataEvento = :dataEvento";
//		TypedQuery<Evento> query = getEntityManager().createQuery(jpql, Evento.class);
//		query.setParameter(Evento_.codigoEvento.getName(), entidade.getCodigoEvento());
//		query.setParameter(Evento_.dataEvento.getName(), entidade.getDataEvento());
//		query.setParameter(Evento_.tentativa.getName(), entidade.getTentativa().getId());
//		return JpaUtils.getSingleResult(query);
		throw new UnsupportedOperationException();
	}

	@Override
	public void gerarHistoricoEvento(Tentativa tentativa, TipoEvento tipoEvento,
			String complemento) {

		Evento evento = new Evento();
		evento.setDataEvento(new Date());
		evento.setTentativa(tentativa);

		if (tipoEvento != null) {
			evento.setCodigoEvento(tipoEvento.getValor()); // Opcional
		}

		if (complemento != null && !complemento.isEmpty()) { // Opcional
			evento.setComplemento(complemento);
		}

		incluir(evento);
	}
	
	public List<Evento> listarEventosPorTentativa(Tentativa tentativa) {
		List<Evento>  eventos		  = null;
		
		String jpql = "select e from " + JpaUtils.getNomeEntidade(Evento.class) + " e where e.tentativa = :tentativa order by e.id desc";
		
		TypedQuery<Evento> query =entityManager.createQuery(jpql, Evento.class);
		
							 query.setParameter("tentativa", tentativa);
		
				eventos =  query.getResultList();	
		return eventos;
	}
	
	public List<Evento> listarEventosUltimaCobrancaPorVenda(Venda venda) {
		List<Evento>  eventos		  = null;
		
		String jpql = "select e from " + JpaUtils.getNomeEntidade(Evento.class) + " e "
				+ " inner join fetch e.tentativa t "
				+ " inner join fetch t.cobranca c "
				+ " inner join fetch c.venda v "
				+ " where "
				+ " c.id = (select max(cob.id) from " + JpaUtils.getNomeEntidade(Cobranca.class) + " cob where cob.venda = :venda) "
				+ " and v = :venda "
				+ " order by e.id desc ";
		
		TypedQuery<Evento> query =entityManager.createQuery(jpql, Evento.class);
		
							 query.setParameter("venda", venda);
		
				eventos =  query.getResultList();	
		return eventos;		
	}
}
