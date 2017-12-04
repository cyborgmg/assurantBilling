package br.com.delphos.billing.exportacoes;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class ExportacaoConciliacaoDLOBean
 */
@Stateless
public class ExportacaoConciliacaoDLOBean extends AbstractDLO<ExportacaoConciliacao> implements ExportacaoConciliacaoDLO {
	
	@EJB
	private ExportacaoConciliacaoDAO dao;

	@Override
	protected ExportacaoConciliacaoDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isEditavel(ExportacaoConciliacao entidade)
			throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}

}
