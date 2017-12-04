package br.com.delphos.billing.util;

import br.com.delphos.billing.excecoes.DLOException;

public interface RegraNegocio {

	boolean isValida() throws DLOException;
	String getMensagemRetorno();
	void run() throws DLOException;
}
