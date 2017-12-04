package br.com.delphos.util.email;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

public class ConstantesEmail {
    
    public static final String CodificacaoEnvio = null;
    public static final String CodificacaoRfc822Padrao = null;
            
    public static String getCodificacaoJvmPadrao() {
    	OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
    	String enc = writer.getEncoding();
    	return enc;
    }
    
    public static String getCodificacaoRfc822Padrao() {
        return CodificacaoRfc822Padrao;
    }
}
