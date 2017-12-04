package br.com.delphos.billing.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.17
 * 2015-11-13T08:50:59.881-02:00
 * Generated source version: 2.7.17
 * 
 */
@WebServiceClient(name = "ServicoServerImplService", 
                  wsdlLocation = "http://localhost:8080/dbs-ws/services/servicoserver?wsdl",
                  targetNamespace = "http://servicos.billing.delphos.com.br/") 
public class ServicoServerImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://servicos.billing.delphos.com.br/", "ServicoServerImplService");
    public final static QName ServicoServerImplPort = new QName("http://servicos.billing.delphos.com.br/", "ServicoServerImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/dbs-ws/services/servicoserver?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ServicoServerImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/dbs-ws/services/servicoserver?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ServicoServerImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ServicoServerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServicoServerImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ServicoServer
     */
    @WebEndpoint(name = "ServicoServerImplPort")
    public ServicoServer getServicoServerImplPort() {
        return super.getPort(ServicoServerImplPort, ServicoServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServicoServer
     */
    @WebEndpoint(name = "ServicoServerImplPort")
    public ServicoServer getServicoServerImplPort(WebServiceFeature... features) {
        return super.getPort(ServicoServerImplPort, ServicoServer.class, features);
    }

}
