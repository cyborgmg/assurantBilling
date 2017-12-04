package br.com.delphos.util.configuracoes;

import java.util.Collection;
import java.util.List;

public class Conversores {
	
	public static Conversor paraString() {
		return new ConversorSimples(String.class);
	}
	
	public static Conversor paraObjeto(Class<?> classeRetorno) {
		return new ConversorSimples(classeRetorno);
	}
	
	public static Conversor paraLista(Class<?> classeElemento, String separador, Integer... limitesArgumentos) {
		@SuppressWarnings("unchecked")
		ConversorSimples conversor = new ConversorSimples((Class<? extends Collection<?>>) List.class, null, classeElemento);
		conversor.setSeparador(separador);
		conversor.setLimitesArgumentos(limitesArgumentos);
		return conversor;
	}

	public static Conversor paraLista(Class<?> classeElemento, String separador) {
		@SuppressWarnings("unchecked")
		ConversorSimples conversor = new ConversorSimples((Class<? extends Collection<?>>) List.class, null, classeElemento);
		conversor.setSeparador(separador);
		return conversor;
	}

	public static Conversor paraLista(Class<?> classeElemento, Integer... limitesArgumentos) {
		@SuppressWarnings("unchecked")
		ConversorSimples conversor = new ConversorSimples((Class<? extends Collection<?>>) List.class, null, classeElemento);
		conversor.setLimitesArgumentos(limitesArgumentos);
		return conversor;
	}
}
