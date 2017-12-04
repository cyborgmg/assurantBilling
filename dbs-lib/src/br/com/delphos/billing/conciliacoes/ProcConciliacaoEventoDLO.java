package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;
import br.com.delphos.billing.tentativas.Tentativa;

@Remote
public interface ProcConciliacaoEventoDLO extends DLOEntidade<ProcConciliacaoEvento> {
	public ProcConciliacaoEvento obterPorProcessamentoConciliacao(ProcessamentoConciliacao pc);
	
	public List<ProcConciliacaoEvento> listarConciliacaoPorCobranca(Long idcobranca) throws DLOException;
}
