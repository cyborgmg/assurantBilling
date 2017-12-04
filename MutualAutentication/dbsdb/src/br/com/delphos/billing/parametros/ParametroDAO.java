package br.com.delphos.billing.parametros;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ParametroDAO extends DAOEntidade<Parametro>{

	Parametro obterUltimosParametros();

}
