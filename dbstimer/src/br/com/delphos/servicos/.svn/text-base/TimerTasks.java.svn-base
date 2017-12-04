package br.com.delphos.servicos;

import javax.ejb.Local;

import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.logs.LogProcessoBatch;

@Local
public interface TimerTasks {

	LogProcessoBatch iniciarLogProcessoBatch(IdentificadorProcesso identificadorProcesso);
	void finalizarLogProcessoBatch(LogProcessoBatch log, StatusLogProcesso status);
}
