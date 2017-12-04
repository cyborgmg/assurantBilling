package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ConciliacaoInterfaceDAO extends DAOEntidade<ConciliacaoInterface> {
	public ConciliacaoInterface obterPorCobranca(Cobranca c);

	public List<ConciliacaoInterface> listarPorCriterio(String codigo,
			String periodoDe, String periodoAte);
}
