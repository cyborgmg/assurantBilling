package br.com.delphos.billing.logs;

import java.util.Date;

import javax.ejb.Remote;

import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface LogProcessoBatchDLO extends DLOEntidade<LogProcessoBatch> {
	public Long gravarLogProcessoBatch(
			StatusLogProcesso status, 
			IdentificadorProcesso processo,
			Date dataInicio,
			Date dataFim,
			String complemento);
}
