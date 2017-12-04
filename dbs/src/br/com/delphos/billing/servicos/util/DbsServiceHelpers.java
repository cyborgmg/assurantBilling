package br.com.delphos.billing.servicos.util;

import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.util.LoggerWrapper;

public class DbsServiceHelpers {

	public static <T extends RetornoBillingService> BillingServiceHelper<T> entrando(
			LoggerWrapper logger,	
			T retorno,
			Object... args) {
		return new BillingServiceHelper<T>(
				logger.entrando(retorno.getNomeMetodoWebService(), args),
				retorno
		);
	}
}
