package br.com.delphos.billing.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;

public class Mensagens {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("mensagens");
	
	public static String get(String nomePropriedade, Object... args) {
		String mensagem = bundle.getString(nomePropriedade);
		if (args != null && args.length > 0){
			mensagem = MessageFormat.format(mensagem, args);
		}
		return mensagem;
	}
	
	public static String get(CodigoMensagem codigoErro, Object... args) {
		return get(codigoErro.getNomePropriedadeErro(), args);
	}

	public static String mensagemOuCodigo(String nomePropriedade, Object... args) {
		String mensagem = nomePropriedade;
		
		try {
			mensagem = get(nomePropriedade, args);	
		} catch (MissingResourceException ex) {
		}
		
		return mensagem;
	}
	
	public static String mensagemOuCodigo(CodigoMensagem codigoErro, Object... args) {
		return mensagemOuCodigo(codigoErro.getNomePropriedadeErro(), args);
	}
}
