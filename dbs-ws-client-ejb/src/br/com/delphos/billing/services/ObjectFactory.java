
package br.com.delphos.billing.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.delphos.billing.services package. 
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

    private final static QName _IniciarVendaCartaoCredito_QNAME = new QName("http://servicos.billing.delphos.com.br/", "iniciarVendaCartaoCredito");
    private final static QName _CancelarVenda_QNAME = new QName("http://servicos.billing.delphos.com.br/", "cancelarVenda");
    private final static QName _BillingException_QNAME = new QName("http://servicos.billing.delphos.com.br/", "BillingException");
    private final static QName _CancelarVendaResponse_QNAME = new QName("http://servicos.billing.delphos.com.br/", "cancelarVendaResponse");
    private final static QName _IniciarVendaCartaoCreditoResponse_QNAME = new QName("http://servicos.billing.delphos.com.br/", "iniciarVendaCartaoCreditoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.delphos.billing.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelarVendaResponse }
     * 
     */
    public CancelarVendaResponse createCancelarVendaResponse() {
        return new CancelarVendaResponse();
    }

    /**
     * Create an instance of {@link IniciarVendaCartaoCreditoResponse }
     * 
     */
    public IniciarVendaCartaoCreditoResponse createIniciarVendaCartaoCreditoResponse() {
        return new IniciarVendaCartaoCreditoResponse();
    }

    /**
     * Create an instance of {@link BillingException }
     * 
     */
    public BillingException createBillingException() {
        return new BillingException();
    }

    /**
     * Create an instance of {@link CancelarVenda }
     * 
     */
    public CancelarVenda createCancelarVenda() {
        return new CancelarVenda();
    }

    /**
     * Create an instance of {@link IniciarVendaCartaoCredito }
     * 
     */
    public IniciarVendaCartaoCredito createIniciarVendaCartaoCredito() {
        return new IniciarVendaCartaoCredito();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarVendaCartaoCredito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.billing.delphos.com.br/", name = "iniciarVendaCartaoCredito")
    public JAXBElement<IniciarVendaCartaoCredito> createIniciarVendaCartaoCredito(IniciarVendaCartaoCredito value) {
        return new JAXBElement<IniciarVendaCartaoCredito>(_IniciarVendaCartaoCredito_QNAME, IniciarVendaCartaoCredito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarVenda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.billing.delphos.com.br/", name = "cancelarVenda")
    public JAXBElement<CancelarVenda> createCancelarVenda(CancelarVenda value) {
        return new JAXBElement<CancelarVenda>(_CancelarVenda_QNAME, CancelarVenda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillingException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.billing.delphos.com.br/", name = "BillingException")
    public JAXBElement<BillingException> createBillingException(BillingException value) {
        return new JAXBElement<BillingException>(_BillingException_QNAME, BillingException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelarVendaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.billing.delphos.com.br/", name = "cancelarVendaResponse")
    public JAXBElement<CancelarVendaResponse> createCancelarVendaResponse(CancelarVendaResponse value) {
        return new JAXBElement<CancelarVendaResponse>(_CancelarVendaResponse_QNAME, CancelarVendaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarVendaCartaoCreditoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicos.billing.delphos.com.br/", name = "iniciarVendaCartaoCreditoResponse")
    public JAXBElement<IniciarVendaCartaoCreditoResponse> createIniciarVendaCartaoCreditoResponse(IniciarVendaCartaoCreditoResponse value) {
        return new JAXBElement<IniciarVendaCartaoCreditoResponse>(_IniciarVendaCartaoCreditoResponse_QNAME, IniciarVendaCartaoCreditoResponse.class, null, value);
    }

}
