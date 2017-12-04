package br.com.delphos.billing.conciliacoes;

import javax.ejb.Remote;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ProcessamentoConciliacaoDLO extends DLOEntidade<ProcessamentoConciliacao> {
	public ProcessamentoConciliacao obterPorCobranca(Cobranca c);

}
