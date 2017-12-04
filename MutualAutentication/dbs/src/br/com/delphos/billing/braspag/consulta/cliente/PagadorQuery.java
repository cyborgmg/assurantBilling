
package br.com.delphos.billing.braspag.consulta.cliente;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
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
	name = "PagadorQuery",
	targetNamespace = "https://www.pagador.com.br/query/pagadorquery",
	wsdlLocation = "classpath:META-INF/wsdl/braspag/PagadorQuery.wsdl"
)
public class PagadorQuery
    extends Service
{

    private final static URL PAGADORQUERY_WSDL_LOCATION;
    private final static Logger logger = LoggerFactory.getLogger(PagadorQuery.class);

    static {
        URL url = null;
        try {
            url = Configuracoes.getObjeto(PropriedadeConfiguracao.UrlWsdlPagadorQuery, URL.class);
        } catch (Exception e) {
            logger.error("Erro ao carregar a URL de execução do serviço PagadorQuery, favor rever as configurações em 'configuracoes.properties'.", e);
        }
        PAGADORQUERY_WSDL_LOCATION = url;
    }

    public PagadorQuery(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PagadorQuery(URL wsdlLocation) {
        super(wsdlLocation, new QName("https://www.pagador.com.br/query/pagadorquery", "PagadorQuery"));
    }
    
    public PagadorQuery() {
        super(PAGADORQUERY_WSDL_LOCATION, new QName("https://www.pagador.com.br/query/pagadorquery", "PagadorQuery"));
    }

    /**
     * 
     * @return
     *     returns PagadorQuerySoap
     */
    @WebEndpoint(name = "PagadorQuerySoap")
    public PagadorQuerySoap getPagadorQuerySoap() {
        return super.getPort(new QName("https://www.pagador.com.br/query/pagadorquery", "PagadorQuerySoap"), PagadorQuerySoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PagadorQuerySoap
     */
    @WebEndpoint(name = "PagadorQuerySoap")
    public PagadorQuerySoap getPagadorQuerySoap(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.pagador.com.br/query/pagadorquery", "PagadorQuerySoap"), PagadorQuerySoap.class, features);
    }

}
