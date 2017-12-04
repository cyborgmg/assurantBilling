package br.com.delphos.billing.servicos;

import javax.ejb.Remote;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.servicos.retornos.RetornoAutorizarVendaCartaoCredito;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarCobrancaPendente;
import br.com.delphos.billing.tentativas.Tentativa;

@Remote
public interface PagamentoService {

	RetornoAutorizarVendaCartaoCredito autorizarVendaCartaoCredito(
			Cobranca cobranca, AdquirenteBandeira adquirenteBandeira,
			String cardNumber, String cardSecurityCode);

	RetornoCancelarCobrancaPendente cancelarCobrancaPendente(Cobranca cobranca);
	
	RetornoCancelarCobrancaPendente cancelarCobrancaPendente(Cobranca cobranca, Tentativa tentativa);
}