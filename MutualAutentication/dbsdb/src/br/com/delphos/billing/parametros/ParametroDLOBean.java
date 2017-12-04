package br.com.delphos.billing.parametros;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Stateless
public class ParametroDLOBean extends AbstractDLO<Parametro> implements ParametroDLO {

	@EJB
	private ParametroDAO dao;
	
	@Override
	protected DAOEntidade<Parametro> getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isEditavel(Parametro entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Parametro obterUltimosParametros() throws DLOException {
		Parametro parametro = dao.obterUltimosParametros();
		if (parametro == null) {
			throw new DLOException();
		}
		return parametro;
	}

}
