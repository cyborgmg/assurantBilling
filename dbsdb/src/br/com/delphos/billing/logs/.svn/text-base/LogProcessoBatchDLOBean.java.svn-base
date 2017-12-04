package br.com.delphos.billing.logs;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class LogProcessoBatchDLOBean
 */
@Stateless
public class LogProcessoBatchDLOBean extends AbstractDLO<LogProcessoBatch>
		implements LogProcessoBatchDLO {

	@EJB
	private LogProcessoBatchDAO dao;

	@Override
	protected LogProcessoBatchDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public Long gravarLogProcessoBatch(StatusLogProcesso status, IdentificadorProcesso processo,
			Date dataInicio, Date dataFim, String complemento) {
		if (status != null && processo != null && dataInicio != null) {
			return  dao.gravarLogProcessoBatch(status, processo, dataInicio,
					dataFim, complemento);
		} else {
			return null;
		}
		
	}

	@Override
	public boolean isEditavel(LogProcessoBatch entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}
}
