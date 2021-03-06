package br.com.delphos.sca.wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.11.redhat-3
 * 2015-08-11T17:04:21.945-03:00
 * Generated source version: 2.7.11.redhat-3
 * 
 */
@WebServiceClient(name = "ControleAcessoServiceBeanService", 
                  wsdlLocation = "classpath:br/com/delphos/sca/wsclient/sca.wsdl",
                  targetNamespace = "http://delphos.com.br/sca") 
public class ControleAcessoServiceClient extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://delphos.com.br/sca", "ControleAcessoServiceBeanService");
    public final static QName ControleAcessoPort = new QName("http://delphos.com.br/sca", "ControleAcessoPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/sca-ejb/ControleAcesso?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ControleAcessoServiceClient.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/sca-ejb/ControleAcesso?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ControleAcessoServiceClient(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ControleAcessoServiceClient(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ControleAcessoServiceClient() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     *
     * @return
     *     returns ControleAcesso
     */
    @WebEndpoint(name = "ControleAcessoPort")
    public ControleAcessoService getControleAcessoPort() {
        return super.getPort(ControleAcessoPort, ControleAcessoService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ControleAcesso
     */
    @WebEndpoint(name = "ControleAcessoPort")
    public ControleAcessoService getControleAcessoPort(WebServiceFeature... features) {
        return super.getPort(ControleAcessoPort, ControleAcessoService.class, features);
    }

}
