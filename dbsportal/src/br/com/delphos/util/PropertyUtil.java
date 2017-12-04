package br.com.delphos.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 
public class PropertyUtil {
 
    private static Properties prop = null;
    private static InputStream  is = null;
    
    public static String getPropertyValue(String propertiesFile ,String key){
    	String propertyValue =null;
    	
    	is= initialize(propertiesFile);
    	
    	if( is!=null )
			try {
				prop.load(is);
				propertyValue= prop.getProperty(key);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	
		return propertyValue;
    }
    
    private static InputStream initialize(String propertiesFile){
    	InputStream inputStream=null;
    	
    	inputStream= PropertyUtil.class.getResourceAsStream(propertiesFile) ;
    	
    	if(inputStream!=null)
    		prop=new Properties();
    	    
    	return inputStream;    
    		
    }
   
}
