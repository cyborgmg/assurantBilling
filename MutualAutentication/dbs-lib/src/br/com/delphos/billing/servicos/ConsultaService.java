package br.com.delphos.billing.servicos;

import javax.ejb.Remote;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.servicos.retornos.RetornoConsultarStatusCobranca;
import br.com.delphos.billing.servicos.retornos.RetornoObterCodigoTransacaoProvedor;

@Remote
public interface ConsultaService {
	
	RetornoConsultarStatusCobranca consultarStatusCobranca(
			Cobranca cobranca)
			throws BillingWebServiceException;

	RetornoObterCodigoTransacaoProvedor obterCodigoTransacaoProvedor(
			Cobranca cobranca)
			throws BillingWebServiceException;
}
