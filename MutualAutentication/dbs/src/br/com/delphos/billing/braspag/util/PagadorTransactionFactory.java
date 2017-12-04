package br.com.delphos.billing.braspag.util;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.braspag.pagador.cliente.AuthorizeTransactionRequest;
import br.com.delphos.billing.braspag.pagador.cliente.AuthorizeTransactionResponse2;
import br.com.delphos.billing.braspag.pagador.cliente.PagadorTransaction;
import br.com.delphos.billing.braspag.pagador.cliente.PagadorTransactionSoap;
import br.com.delphos.billing.braspag.pagador.cliente.RefundCreditCardTransactionRequest;
import br.com.delphos.billing.braspag.pagador.cliente.RefundCreditCardTransactionResponse2;
import br.com.delphos.billing.braspag.pagador.cliente.VoidCreditCardTransactionRequest;
import br.com.delphos.billing.braspag.pagador.cliente.VoidCreditCardTransactionResponse2;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

public class PagadorTransactionFactory extends BraspagServiceFactory<PagadorTransaction, PagadorTransactionSoap> {
	public static Logger LOGGER = LoggerFactory.getLogger(PagadorTransactionFactory.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);


	public PagadorTransaction newServico() {
		URL urlWsdl = Configuracoes.getObjetoOuNulo(PropriedadeConfiguracao.UrlWsdlPagadorTransaction, URL.class);
		if (urlWsdl == null) {
			return new PagadorTransaction();
		}
		return new PagadorTransaction(urlWsdl);
	}
	
	public PagadorTransactionSoap newPortaServico() {
		iniciarServico();
		return servico.getPagadorTransactionSoap();
	}

	public AuthorizeTransactionRequest newAuthorizeTransactionRequest() {
		return new AuthorizeTransactionRequest();
	}
	
	public AuthorizeTransactionResponse2 authorizeTransaction(final AuthorizeTransactionRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<AuthorizeTransactionResponse2>() {
				public AuthorizeTransactionResponse2 run() {
					return porta.authorizeTransaction(req);
				}
			}
		);
	}

	public RefundCreditCardTransactionRequest newRefundCreditCardTransactionRequest() {
		return new RefundCreditCardTransactionRequest();
	}
	
	public RefundCreditCardTransactionResponse2 refundCreditCardTransaction(final RefundCreditCardTransactionRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<RefundCreditCardTransactionResponse2>() {
				public RefundCreditCardTransactionResponse2 run() {
					return porta.refundCreditCardTransaction(req);
				}
			}
		);
	}

	public VoidCreditCardTransactionRequest newVoidCreditCardTransactionRequest() {
		return new VoidCreditCardTransactionRequest();
	}
	
	public VoidCreditCardTransactionResponse2 voidCreditCardTransaction(final VoidCreditCardTransactionRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<VoidCreditCardTransactionResponse2>() {
				public VoidCreditCardTransactionResponse2 run() {
					return porta.voidCreditCardTransaction(req);
				}
			}
		);
	}
}
