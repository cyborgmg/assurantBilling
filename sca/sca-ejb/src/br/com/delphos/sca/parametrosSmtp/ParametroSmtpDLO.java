package br.com.delphos.sca.parametrosSmtp;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDLO;

@Local
public interface ParametroSmtpDLO extends AbstractDLO<ParametroSmtp, Long> {

	ParametroSmtp obterUltimosParametros() throws DelphosException;
}
