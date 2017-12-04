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
 * 2015-11-11T08:55:08.250-02:00
 * Generated source version: 2.7.17
 * 
 */
@WebService(targetNamespace = "http://servicos.billing.delphos.com.br/", name = "ServicoServer")
@XmlSeeAlso({ObjectFactory.class})
public interface ServicoServer {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "iniciarVendaCartaoCredito", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.IniciarVendaCartaoCredito")
    @WebMethod(action = "urn:IniciarVendaCartaoCredito")
    @ResponseWrapper(localName = "iniciarVendaCartaoCreditoResponse", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.IniciarVendaCartaoCreditoResponse")
    public br.com.delphos.billing.services.RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token,
        @WebParam(name = "codigoEmpresa", targetNamespace = "")
        java.lang.String codigoEmpresa,
        @WebParam(name = "codigoProduto", targetNamespace = "")
        java.lang.String codigoProduto,
        @WebParam(name = "codigoSistema", targetNamespace = "")
        java.lang.String codigoSistema,
        @WebParam(name = "cpf", targetNamespace = "")
        java.lang.String cpf,
        @WebParam(name = "nome", targetNamespace = "")
        java.lang.String nome,
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email,
        @WebParam(name = "dddCelular", targetNamespace = "")
        java.lang.String dddCelular,
        @WebParam(name = "telefoneCelular", targetNamespace = "")
        java.lang.String telefoneCelular,
        @WebParam(name = "dddContato", targetNamespace = "")
        java.lang.String dddContato,
        @WebParam(name = "telefoneContato", targetNamespace = "")
        java.lang.String telefoneContato,
        @WebParam(name = "codigoVenda", targetNamespace = "")
        java.lang.String codigoVenda,
        @WebParam(name = "dataVenda", targetNamespace = "")
        java.lang.String dataVenda,
        @WebParam(name = "bandeiraCartao", targetNamespace = "")
        java.lang.String bandeiraCartao,
        @WebParam(name = "cpfPortadorCartao", targetNamespace = "")
        java.lang.String cpfPortadorCartao,
        @WebParam(name = "nomePortadorCartao", targetNamespace = "")
        java.lang.String nomePortadorCartao,
        @WebParam(name = "numeroCartao", targetNamespace = "")
        java.lang.String numeroCartao,
        @WebParam(name = "vencimentoCartao", targetNamespace = "")
        java.lang.String vencimentoCartao,
        @WebParam(name = "cvvCartao", targetNamespace = "")
        java.lang.String cvvCartao,
        @WebParam(name = "tipoCobranca", targetNamespace = "")
        java.lang.String tipoCobranca,
        @WebParam(name = "quantidadeParcelas", targetNamespace = "")
        java.lang.String quantidadeParcelas,
        @WebParam(name = "valorCobranca", targetNamespace = "")
        java.lang.String valorCobranca,
        @WebParam(name = "dataPrimeiraCobranca", targetNamespace = "")
        java.lang.String dataPrimeiraCobranca,
        @WebParam(name = "dataFimVigencia", targetNamespace = "")
        java.lang.String dataFimVigencia
    ) throws BillingException_Exception;

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "cancelarVenda", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.CancelarVenda")
    @WebMethod(action = "urn:CancelarVenda")
    @ResponseWrapper(localName = "cancelarVendaResponse", targetNamespace = "http://servicos.billing.delphos.com.br/", className = "br.com.delphos.billing.services.CancelarVendaResponse")
    public br.com.delphos.billing.services.RetornoCancelarVenda cancelarVenda(
        @WebParam(name = "codigoEmpresa", targetNamespace = "")
        java.lang.String codigoEmpresa,
        @WebParam(name = "codigoProduto", targetNamespace = "")
        java.lang.String codigoProduto,
        @WebParam(name = "codigoSistema", targetNamespace = "")
        java.lang.String codigoSistema,
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token,
        @WebParam(name = "cpf", targetNamespace = "")
        java.lang.String cpf,
        @WebParam(name = "codigoVenda", targetNamespace = "")
        java.lang.String codigoVenda,
        @WebParam(name = "codigoMotivoCancelamento", targetNamespace = "")
        java.lang.String codigoMotivoCancelamento,
        @WebParam(name = "valorEstorno", targetNamespace = "")
        java.lang.String valorEstorno,
        @WebParam(name = "flagCancelamentoGateway", targetNamespace = "")
        java.lang.String flagCancelamentoGateway
    ) throws BillingException_Exception;
}
