package br.com.delphos.billing.teste;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

import br.com.delphos.billing.client.ServicoServer;
import br.com.delphos.billing.security.ClientPasswordCallback;


public class TesteService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();        

        // Use the URL defined in the soap address portion of the WSDL
        factory.setAddress("http://localhost:8080/dbs-war/services/servicoserver"); 
       
        // Utilize the class which was auto-generated by Apache CXF wsdl2java
        factory.setServiceClass(ServicoServer.class);        

        Object client = factory.create();
        /********************************SSL********************************************************/
        
        
        // Adding Logging Interceptors
        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
        loggingOutInterceptor.setPrettyLogging(true);
        ClientProxy.getClient(client).getOutInterceptors().add(loggingOutInterceptor);
        
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        ClientProxy.getClient(client).getInInterceptors().add(loggingInInterceptor);

        // Set up WS-Security Encryption
        // Reference: https://ws.apache.org/wss4j/using.html
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(WSHandlerConstants.USER, "testkey");
        props.put(WSHandlerConstants.ACTION, WSHandlerConstants.ENCRYPT);
        props.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
        props.put(WSHandlerConstants.ENC_PROP_FILE, "clientKeystore.properties");
        props.put(WSHandlerConstants.ENCRYPTION_PARTS, "{Content}{http://schemas.xmlsoap.org/soap/envelope/}Body");
        props.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallback.class.getName());
        
        WSS4JOutInterceptor wss4jOut = new WSS4JOutInterceptor(props);

        ClientProxy.getClient(client).getOutInterceptors().add(wss4jOut);
        
        
        /********************************SSL********************************************************/
        try {        
    
            // Call the Web Service to perform an operation
            String response = ((ServicoServer)client).sayHello("");
            		//factorial(4);    
        
            System.out.println(response);    
        
          } catch (SecurityException e) {

            e.printStackTrace();

          } catch (IllegalArgumentException e) {

            e.printStackTrace();

          } 
		

	}

}
