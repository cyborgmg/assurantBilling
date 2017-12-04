package br.com.delphos.util;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class ServiceLocator {

   private static HashMap<String, Object> interfaces = new HashMap<String, Object>();
   private static InitialContext ctx;

   private ServiceLocator() {
   }

   static {

      interfaces = new HashMap<String, Object>();

      Properties props = new Properties();
      props.setProperty("java.naming.factory.initial", 
              "com.sun.enterprise.naming.SerialInitContextFactory");
      props.setProperty("java.naming.factory.url.pkgs", 
              "com.sun.enterprise.naming");
      props.setProperty("java.naming.factory.state", 
              "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
      props.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
      props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

      try {
//         ctx = new InitialContext(props);
         ctx = new InitialContext();
      } catch (NamingException ex) {

         Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public static Object lookup(String remote) {

      Object retorno = interfaces.get(remote);
      if (retorno == null) {

         try {

            retorno = ctx.lookup(remote);
            if (retorno != null) {

               interfaces.put(remote, retorno);
            }
         } catch (NamingException ex) {

            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      return retorno;
   }
}
