package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ConciliacaoInterfaceDLO extends DLOEntidade<ConciliacaoInterface> {
	public ConciliacaoInterface obterPorCobranca(Cobranca c);

	public List<ConciliacaoInterface> listarPorCriterio(String codigo,
			String periodoDe, String periodoAte);
}
