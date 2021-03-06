
package br.com.delphos.billing.services;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.17
 * 2015-11-13T08:50:59.723-02:00
 * Generated source version: 2.7.17
 */

@WebFault(name = "BillingException", targetNamespace = "http://servicos.billing.delphos.com.br/")
public class BillingException_Exception extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private br.com.delphos.billing.services.BillingException billingException;

    public BillingException_Exception() {
        super();
    }
    
    public BillingException_Exception(String message) {
        super(message);
    }
    
    public BillingException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public BillingException_Exception(String message, br.com.delphos.billing.services.BillingException billingException) {
        super(message);
        this.billingException = billingException;
    }

    public BillingException_Exception(String message, br.com.delphos.billing.services.BillingException billingException, Throwable cause) {
        super(message, cause);
        this.billingException = billingException;
    }

    public br.com.delphos.billing.services.BillingException getFaultInfo() {
        return this.billingException;
    }
}
