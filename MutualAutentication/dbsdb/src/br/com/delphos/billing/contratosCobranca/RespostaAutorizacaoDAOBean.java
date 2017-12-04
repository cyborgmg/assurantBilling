package br.com.delphos.billing.contratosCobranca;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class RespostaAutorizacaoDAOBean
 */
@Stateless
public class RespostaAutorizacaoDAOBean extends AbstractDAO<RespostaAutorizacao> implements RespostaAutorizacaoDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public RespostaAutorizacaoDAOBean() {
		super(RespostaAutorizacao.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public RespostaAutorizacao obterPorIdentidade(RespostaAutorizacao entidade) {
		String jpql = "select o from RespostaAutorizacao o"
				+ " where o.codigoResposta = :codigoResposta"
				+ " and o.contratoCobranca.id = :contratoCobranca";
		TypedQuery<RespostaAutorizacao> query = getEntityManager().createQuery(jpql, RespostaAutorizacao.class);
		query.setParameter(RespostaAutorizacao_.codigoResposta.getName(), entidade.getCodigoResposta());
		query.setParameter(RespostaAutorizacao_.contratoCobranca.getName(), entidade.getContratoCobranca().getId());
		return JpaUtils.getSingleResult(query);
	}
	
	public List<RespostaAutorizacao> listarPorCriterio(String idContratoCobranca, String codigoRespostaAutorizacao, String descricaoRespostaAutorizacaoProvedor) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(RespostaAutorizacao.class) + " o"
				+ " where ";
				if (!Validador.vazio(idContratoCobranca)) {
					jpql += "contratoCobranca.id = :contratoCobranca ";
				}
				if (!Validador.vazio(codigoRespostaAutorizacao)) {
					if (!Validador.vazio(idContratoCobranca)) {
						jpql += " and ";
					}
					jpql += "upper(o.codigoResposta) like :codigoRespostaAutorizacao ";
				}
				if (!Validador.vazio(descricaoRespostaAutorizacaoProvedor)) {
					if (!Validador.vazio(idContratoCobranca) || !Validador.vazio(codigoRespostaAutorizacao)) {
						jpql += " and ";
					}
					jpql += "upper(o.descricaoRespostaProvedor) like :descricaoRespostaProvedor ";
				}
				jpql += " order by o.descricaoRespostaProvedor";
				
				TypedQuery<RespostaAutorizacao> query = getEntityManager().createQuery(jpql, RespostaAutorizacao.class);
				if (!Validador.vazio(idContratoCobranca)) {
					query.setParameter("contratoCobranca", Long.valueOf(idContratoCobranca));
				}
				if (!Validador.vazio(codigoRespostaAutorizacao)) {
					query.setParameter("codigoRespostaAutorizacao", codigoRespostaAutorizacao.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricaoRespostaAutorizacaoProvedor)) {
					query.setParameter("descricaoRespostaProvedor", descricaoRespostaAutorizacaoProvedor.toUpperCase() + "%");
				}
		return query.getResultList();		
	}

}
