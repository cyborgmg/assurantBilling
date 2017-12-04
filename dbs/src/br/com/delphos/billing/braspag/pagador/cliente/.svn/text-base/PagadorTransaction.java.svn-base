
package br.com.delphos.billing.braspag.pagador.cliente;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.util.Configuracoes;


/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Favor não alterar as URLs neste arquivo. A alteração da localização do WSDL dos serviços Braspag
 * deve ser feita no configuracoes.properties.
 * 
 * by Cristiano
 * 
 * 
 * 
 * 
 * 
 * 
 */
@WebServiceClient(
	name = "PagadorTransaction",
	targetNamespace = "https://www.pagador.com.br/webservice/pagador",
	wsdlLocation = "classpath:META-INF/wsdl/braspag/PagadorTransaction.wsdl"
)
public class PagadorTransaction
    extends Service
{

    private final static Logger logger = LoggerFactory.getLogger(PagadorTransaction.class);
    private final static URL PAGADORTRANSACTION_WSDL_LOCATION;
    private final static WebServiceException PAGADORTRANSACTION_EXCEPTION;
    private final static QName PAGADORTRANSACTION_QNAME = new QName("https://www.pagador.com.br/webservice/pagador", "PagadorTransaction");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = Configuracoes.getObjeto(PropriedadeConfiguracao.UrlWsdlPagadorTransaction, URL.class);
        } catch (Exception ex) {
            logger.error("Erro ao carregar a URL de execução do serviço PagadorTransaction, favor rever as configurações em 'configuracoes.properties'.", e);
        }
        PAGADORTRANSACTION_WSDL_LOCATION = url;
        PAGADORTRANSACTION_EXCEPTION = e;
    }

    public PagadorTransaction() {
        super(__getWsdlLocation(), PAGADORTRANSACTION_QNAME);
    }


    public PagadorTransaction(URL wsdlLocation) {
        super(wsdlLocation, PAGADORTRANSACTION_QNAME);
    }

    public PagadorTransaction(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }


    /**
     * 
     * @return
     *     returns PagadorTransactionSoap
     */
    @WebEndpoint(name = "PagadorTransactionSoap")
    public PagadorTransactionSoap getPagadorTransactionSoap() {
        return super.getPort(new QName("https://www.pagador.com.br/webservice/pagador", "PagadorTransactionSoap"), PagadorTransactionSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PagadorTransactionSoap
     */
    @WebEndpoint(name = "PagadorTransactionSoap")
    public PagadorTransactionSoap getPagadorTransactionSoap(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.pagador.com.br/webservice/pagador", "PagadorTransactionSoap"), PagadorTransactionSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PAGADORTRANSACTION_EXCEPTION!= null) {
            throw PAGADORTRANSACTION_EXCEPTION;
        }
        return PAGADORTRANSACTION_WSDL_LOCATION;
    }

}
