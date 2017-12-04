package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.tentativas.Tentativa;

@Local
public interface ProcConciliacaoEventoDAO extends DAOEntidade<ProcConciliacaoEvento>{
	public ProcConciliacaoEvento obterPorProcessamentoConciliacao(ProcessamentoConciliacao pc);
	
	public List<ProcConciliacaoEvento> listarConciliacaoPorCobranca(Cobranca cobranca);
}
