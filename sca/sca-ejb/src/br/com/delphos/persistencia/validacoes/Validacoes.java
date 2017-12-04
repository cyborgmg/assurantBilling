package br.com.delphos.persistencia.validacoes;

public class Validacoes {

	public static boolean contem(Class<?> validacao, Class<?>... gruposValidacao) {
		boolean retorno = false;
		
		if (gruposValidacao == null || gruposValidacao.length == 0) {
			retorno = true;
			
		} else {
			for (Class<?> grupoValidacao : gruposValidacao) {
				if (validacao.isAssignableFrom(grupoValidacao)) {
					retorno = true;
					break;
				}
			}
		}
		
		return retorno;
	}
}
