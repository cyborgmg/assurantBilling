package br.com.delphos.billing.parametros;

import javax.ejb.Remote;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ParametroDLO extends DLOEntidade<Parametro> {

	Parametro obterUltimosParametros() throws DLOException;
}
