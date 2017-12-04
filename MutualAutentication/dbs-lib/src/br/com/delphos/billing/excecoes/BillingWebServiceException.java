package br.com.delphos.billing.excecoes;

import javax.xml.ws.WebFault;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;

@WebFault(name="falhaServico", targetNamespace="http://delphos.com.br/billing")
public class BillingWebServiceException extends BillingException {
	private static final long serialVersionUID = 1L;

	public BillingWebServiceException() {
		super();
	}

	public BillingWebServiceException(CodigoMensagem codigoRetorno,
			Object... args) {
		super(codigoRetorno, args);
	}

	public BillingWebServiceException(String m, Throwable t) {
		super(m, t);
	}

	public BillingWebServiceException(String m) {
		super(m);
	}

	public BillingWebServiceException(Throwable t) {
		super(t);	
	}

}
