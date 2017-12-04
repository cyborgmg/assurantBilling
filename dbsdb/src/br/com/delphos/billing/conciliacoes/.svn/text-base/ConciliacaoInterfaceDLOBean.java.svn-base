package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.persistencia.DAOEntidade;
@Stateless
public class ConciliacaoInterfaceDLOBean extends AbstractDLO<ConciliacaoInterface> implements ConciliacaoInterfaceDLO {

	@EJB
	private ConciliacaoInterfaceDAO dao;

	
	@Override
	protected DAOEntidade<ConciliacaoInterface> getDAOEntidade() {
		return dao;
	}

	@Override
	public ConciliacaoInterface obterPorCobranca(Cobranca c) {
		return dao.obterPorCobranca(c);
	}

	@Override
	public boolean isEditavel(ConciliacaoInterface entidade)
			throws DLOException {
		return false;
	}
	
	public List<ConciliacaoInterface> listarPorCriterio(String codigo, String periodoDe, String periodoAte) {
		return dao.listarPorCriterio(codigo, periodoDe, periodoAte);
	}

}
