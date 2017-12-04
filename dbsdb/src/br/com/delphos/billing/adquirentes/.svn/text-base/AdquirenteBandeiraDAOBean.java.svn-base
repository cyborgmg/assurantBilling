package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDAO;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

@Stateless
public class AdquirenteBandeiraDAOBean extends AbstractDAO<AdquirenteBandeira> implements AdquirenteBandeiraDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	@EJB
	private AdquirenteDAO adquirenteDAO;
	
	@EJB
	private BandeiraDAO bandeiraDAO;
	
	@EJB
	private ContratoCobrancaDAO contratoCobrancaDAO;
	
	public AdquirenteBandeiraDAOBean() {
		super(AdquirenteBandeira.class);
	}

	@Override
	public AdquirenteBandeira obterPorIdentidade(AdquirenteBandeira entidade) {
		String jpql = "select o from AdquirenteBandeira o"
				+ " where o.adquirente.id = :adquirente"
				+ " and o.bandeira.id = :bandeira"
				+ " and o.contratoCobranca.id = :contratoCobranca";
		TypedQuery<AdquirenteBandeira> query = getEntityManager().createQuery(jpql, AdquirenteBandeira.class);
		query.setParameter(AdquirenteBandeira_.adquirente.getName(), entidade.getAdquirente().getId());
		query.setParameter(AdquirenteBandeira_.bandeira.getName(), entidade.getBandeira().getId());
		query.setParameter(AdquirenteBandeira_.contratoCobranca.getName(), entidade.getContratoCobranca().getId());
		return JpaUtils.getSingleResult(query);
	}
	
	@Override
	public Object incluir(AdquirenteBandeira adquirenteBandeira) {
		adquirenteBandeira.setAdquirente(adquirenteDAO.obter(adquirenteBandeira.getAdquirente().getId()));
		adquirenteBandeira.setBandeira(bandeiraDAO.obter(adquirenteBandeira.getBandeira().getId()));
		adquirenteBandeira.setContratoCobranca(contratoCobrancaDAO.obter(adquirenteBandeira.getContratoCobranca().getId()));
		return super.incluir(adquirenteBandeira);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<AdquirenteBandeira> listarPorContrato(ContratoCobranca contratoCobranca) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(AdquirenteBandeira.class) + " o"
				+ " inner join fetch o.contratoCobranca cc "
				+ " where ";
		jpql += "cc.id = :contratoCobranca ";
		TypedQuery<AdquirenteBandeira> query = getEntityManager().createQuery(jpql, AdquirenteBandeira.class);
		query.setParameter("contratoCobranca",  contratoCobranca.getId());
		return query.getResultList();		
	}
	
	public List<AdquirenteBandeira> listarPorCriterio(String idAdquirente, String idBandeira, String idContratoCobranca, 
			String adquirentePadrao, String codigoMetodoPagamento) {
		
		String jpql = "select o from " + JpaUtils.getNomeEntidade(AdquirenteBandeira.class) + " o"
				+ " where ";
				if (!Validador.vazio(idAdquirente)) {
					jpql += "o.adquirente.id = :adquirente ";
				}
				if (!Validador.vazio(idBandeira)) {
					if (!Validador.vazio(idAdquirente)) {
						jpql += " and ";
					}
					jpql += " o.bandeira.id = :bandeira ";
				}
				if (!Validador.vazio(idContratoCobranca)) {
					if (!Validador.vazio(idAdquirente) || !Validador.vazio(idBandeira)) {
						jpql += " and ";
					}
					jpql += " o.contratoCobranca.id = :contratoCobranca ";
				}
				if (!Validador.vazio(adquirentePadrao)) {
					if (!Validador.vazio(idAdquirente) || !Validador.vazio(idBandeira)
							|| !Validador.vazio(idContratoCobranca)) {
						jpql += " and ";
					}
					jpql += " upper(o.adquirentePadrao) = :adquirentePadrao ";
				}
				if (!Validador.vazio(codigoMetodoPagamento)) {
					if (!Validador.vazio(idAdquirente) || !Validador.vazio(idBandeira)
							|| !Validador.vazio(idContratoCobranca) || !Validador.vazio(adquirentePadrao)) {
						jpql += " and ";
					}
					jpql += " o.codigoMetodoPagamento = :codigoMetodoPagamento ";
				}
				
				TypedQuery<AdquirenteBandeira> query = getEntityManager().createQuery(jpql, AdquirenteBandeira.class);
				if (!Validador.vazio(idAdquirente)) {
					query.setParameter("adquirente", Long.valueOf(idAdquirente));
				}
				if (!Validador.vazio(idBandeira)) {
					query.setParameter("bandeira", Long.valueOf(idBandeira));
				}
				if (!Validador.vazio(idContratoCobranca)) {
					query.setParameter("contratoCobranca", Long.valueOf(idContratoCobranca));
				}
				if (!Validador.vazio(adquirentePadrao)) {
					query.setParameter("adquirentePadrao", adquirentePadrao.toUpperCase());
				}
				if (!Validador.vazio(codigoMetodoPagamento)) {
					query.setParameter("codigoMetodoPagamento", Long.valueOf(codigoMetodoPagamento));
				}
		return query.getResultList();		
	}

}
