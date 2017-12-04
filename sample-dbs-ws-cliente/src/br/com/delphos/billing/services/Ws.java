package br.com.delphos.billing.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.17
 * 2015-11-12T09:24:02.259-02:00
 * Generated source version: 2.7.17
 * 
 */
@WebService(targetNamespace = "http://servicos.billing.delphos.com.br/", name = "Ws")
@XmlSeeAlso({ObjectFactory.class})
public interface Ws {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "iniciarVendaCartaoCredito", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.IniciarVendaCartaoCredito")
    @WebMethod(action = "urn:IniciarVendaCartaoCredito")
    @ResponseWrapper(localName = "iniciarVendaCartaoCreditoResponse", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.IniciarVendaCartaoCreditoResponse")
    public br.com.delphos.billing.services.RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.String arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.String arg8,
        @WebParam(name = "arg9", targetNamespace = "")
        java.lang.String arg9,
        @WebParam(name = "arg10", targetNamespace = "")
        java.lang.String arg10,
        @WebParam(name = "arg11", targetNamespace = "")
        java.lang.String arg11,
        @WebParam(name = "arg12", targetNamespace = "")
        java.lang.String arg12,
        @WebParam(name = "arg13", targetNamespace = "")
        java.lang.String arg13,
        @WebParam(name = "arg14", targetNamespace = "")
        java.lang.String arg14,
        @WebParam(name = "arg15", targetNamespace = "")
        java.lang.String arg15,
        @WebParam(name = "arg16", targetNamespace = "")
        java.lang.String arg16,
        @WebParam(name = "arg17", targetNamespace = "")
        java.lang.String arg17,
        @WebParam(name = "arg18", targetNamespace = "")
        java.lang.String arg18,
        @WebParam(name = "arg19", targetNamespace = "")
        java.lang.String arg19,
        @WebParam(name = "arg20", targetNamespace = "")
        java.lang.String arg20,
        @WebParam(name = "arg21", targetNamespace = "")
        java.lang.String arg21,
        @WebParam(name = "arg22", targetNamespace = "")
        java.lang.String arg22,
        @WebParam(name = "arg23", targetNamespace = "")
        java.lang.String arg23
    ) throws BillingException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "cancelarVenda", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.CancelarVenda")
    @WebMethod(action = "urn:CancelarVenda")
    @ResponseWrapper(localName = "cancelarVendaResponse", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.CancelarVendaResponse")
    public br.com.delphos.billing.services.RetornoCancelarVenda cancelarVenda(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        java.lang.String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        java.lang.String arg7,
        @WebParam(name = "arg8", targetNamespace = "")
        java.lang.String arg8
    ) throws BillingException_Exception;
}