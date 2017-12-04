package br.com.delphos.billing.conciliacoes;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class ConciliacaoRecepcaoDLOBean
 */
@Stateless
public class ProcessamentoConciliacaoDLOBean extends AbstractDLO<ProcessamentoConciliacao> implements ProcessamentoConciliacaoDLO {
	
	@EJB
	private ProcessamentoConciliacaoDAO dao;

	@Override
	protected ProcessamentoConciliacaoDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public ProcessamentoConciliacao obterPorCobranca(Cobranca c) {
		return dao.obterPorCobranca(c);
	}

	@Override
	public boolean isEditavel(ProcessamentoConciliacao entidade)
			throws DLOException {
		return false;
	}

}
