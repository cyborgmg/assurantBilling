
package br.com.delphos.billing.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.delphos.billing.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BillingException_QNAME = new QName("http://servicos.billing.delphos.com.br/", "BillingException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.delphos.billing.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BillingException }
     * 
     */
    public BillingException createBillingException() {
        return new BillingException();
    }

    /**
     * Create an instance of {@link RetornoCancelarVenda }
     * 
     */
    public RetornoCancelarVenda createRetornoCancelarVenda() {
        return new RetornoCancelarVenda();
    }

    /**
     * Create an instance of {@link RetornoIniciarVendaCartaoCredito }
     * 
     */
    public RetornoIniciarVendaCartaoCredito createRetornoIniciarVendaCartaoCredito() {
        return new RetornoIniciarVendaCartaoCredito();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.billing.delphos.com.br/", name = "BillingException")
    public JAXBElement<BillingException> createBillingException(BillingException value) {
        return new JAXBElement<BillingException>(_BillingException_QNAME, BillingException.class, null, value);
    }

}
