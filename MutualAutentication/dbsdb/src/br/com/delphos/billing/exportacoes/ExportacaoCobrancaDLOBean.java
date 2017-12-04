package br.com.delphos.billing.exportacoes;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class ExportacaoCobrancaDLOBean
 */
@Stateless
public class ExportacaoCobrancaDLOBean extends AbstractDLO<ExportacaoCobranca> implements ExportacaoCobrancaDLO {
	
	@EJB
	private ExportacaoCobrancaDAO dao;

	@Override
	protected ExportacaoCobrancaDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isEditavel(ExportacaoCobranca entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}

}
