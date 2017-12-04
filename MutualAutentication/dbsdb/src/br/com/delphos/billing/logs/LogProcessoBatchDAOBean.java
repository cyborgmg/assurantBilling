package br.com.delphos.billing.logs;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;

/**
 * Session Bean implementation class LogProcessoBatchDAOBean
 */
@Stateless
public class LogProcessoBatchDAOBean extends AbstractDAO<LogProcessoBatch>
		implements LogProcessoBatchDAO {

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;

	public LogProcessoBatchDAOBean() {
		super(LogProcessoBatch.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public LogProcessoBatch obterPorIdentidade(LogProcessoBatch entidade) {
		LogProcessoBatch retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from LogProcessoBatch o"
					+ " where o.id = :id";
			TypedQuery<LogProcessoBatch> query = getEntityManager().createQuery(jpql, LogProcessoBatch.class);
			query.setParameter(LogProcessoBatch_.id.getName(), entidade.getId());
			return JpaUtils.getSingleResult(query);	
		}
		return retorno;
	}

	@Override
	public Long gravarLogProcessoBatch(StatusLogProcesso status, IdentificadorProcesso processo,
			Date dataInicio, Date dataFim, String complemento) {

		LogProcessoBatch logProcesso = new LogProcessoBatch();
		logProcesso.setStatus(status);
		logProcesso.setProcesso(processo.getValor());

		if (dataInicio != null) {
			logProcesso.setDataInicio(dataInicio);
		}

		if (dataFim != null) {
			logProcesso.setDataFim(dataFim);
		}

		if (complemento != null && !complemento.isEmpty()) {
			logProcesso.setComplemento(complemento);
		}

		return (Long) incluir(logProcesso);
	}

}
