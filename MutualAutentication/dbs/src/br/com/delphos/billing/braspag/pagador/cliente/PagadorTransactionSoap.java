
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PagadorTransactionSoap", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PagadorTransactionSoap {


    /**
     * Authorize an order using the new Pagador plataform.
     * 
     * @param request
     * @return
     *     returns br.com.delphos.assurant.billing.ws.pagador.cliente.AuthorizeTransactionResponse2
     */
    @WebMethod(operationName = "AuthorizeTransaction", action = "https://www.pagador.com.br/webservice/pagador/AuthorizeTransaction")
    @WebResult(name = "AuthorizeTransactionResult", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
    @RequestWrapper(localName = "AuthorizeTransaction", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.AuthorizeTransaction")
    @ResponseWrapper(localName = "AuthorizeTransactionResponse", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.AuthorizeTransactionResponse")
    public AuthorizeTransactionResponse2 authorizeTransaction(
        @WebParam(name = "request", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
        AuthorizeTransactionRequest request);

    /**
     * Capture an order using the new Pagador plataform.
     * 
     * @param request
     * @return
     *     returns br.com.delphos.assurant.billing.ws.pagador.cliente.CaptureCreditCardTransactionResponse2
     */
    @WebMethod(operationName = "CaptureCreditCardTransaction", action = "https://www.pagador.com.br/webservice/pagador/CaptureCreditCardTransaction")
    @WebResult(name = "CaptureCreditCardTransactionResult", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
    @RequestWrapper(localName = "CaptureCreditCardTransaction", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.CaptureCreditCardTransaction")
    @ResponseWrapper(localName = "CaptureCreditCardTransactionResponse", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.CaptureCreditCardTransactionResponse")
    public CaptureCreditCardTransactionResponse2 captureCreditCardTransaction(
        @WebParam(name = "request", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
        CaptureCreditCardTransactionRequest request);

    /**
     * Void an order using the new Pagador plataform.
     * 
     * @param request
     * @return
     *     returns br.com.delphos.assurant.billing.ws.pagador.cliente.VoidCreditCardTransactionResponse2
     */
    @WebMethod(operationName = "VoidCreditCardTransaction", action = "https://www.pagador.com.br/webservice/pagador/VoidCreditCardTransaction")
    @WebResult(name = "VoidCreditCardTransactionResult", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
    @RequestWrapper(localName = "VoidCreditCardTransaction", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.VoidCreditCardTransaction")
    @ResponseWrapper(localName = "VoidCreditCardTransactionResponse", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.VoidCreditCardTransactionResponse")
    public VoidCreditCardTransactionResponse2 voidCreditCardTransaction(
        @WebParam(name = "request", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
        VoidCreditCardTransactionRequest request);

    /**
     * Refund an order using the new Pagador plataform.
     * 
     * @param request
     * @return
     *     returns br.com.delphos.assurant.billing.ws.pagador.cliente.RefundCreditCardTransactionResponse2
     */
    @WebMethod(operationName = "RefundCreditCardTransaction", action = "https://www.pagador.com.br/webservice/pagador/RefundCreditCardTransaction")
    @WebResult(name = "RefundCreditCardTransactionResult", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
    @RequestWrapper(localName = "RefundCreditCardTransaction", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.RefundCreditCardTransaction")
    @ResponseWrapper(localName = "RefundCreditCardTransactionResponse", targetNamespace = "https://www.pagador.com.br/webservice/pagador", className = "br.com.delphos.assurant.billing.ws.pagador.cliente.RefundCreditCardTransactionResponse")
    public RefundCreditCardTransactionResponse2 refundCreditCardTransaction(
        @WebParam(name = "request", targetNamespace = "https://www.pagador.com.br/webservice/pagador")
        RefundCreditCardTransactionRequest request);

}
