package br.com.delphos.billing.logs;

import java.util.Date;

import javax.ejb.Local;

import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface LogProcessoBatchDAO extends DAOEntidade<LogProcessoBatch> {
	public Long gravarLogProcessoBatch(StatusLogProcesso status, IdentificadorProcesso processo,
			Date dataInicio, Date dataFim, String complemento);
}
