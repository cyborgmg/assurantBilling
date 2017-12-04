package br.com.delphos.util.configuracoes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ConfiguracoesUtils {

	@SuppressWarnings("unchecked")
	public static <C extends Collection<?>, R extends C> Class<R> getImplementacaoColecaoPadrao(Class<C> classeColecao) {
		Class<R> retorno = (Class<R>) classeColecao;
		
		if (List.class.isAssignableFrom(classeColecao)) {
			retorno = (Class<R>) ArrayList.class;
			
		} else if (SortedSet.class.isAssignableFrom(classeColecao)) {
			retorno = (Class<R>) TreeSet.class;
			
		} else if (Set.class.isAssignableFrom(classeColecao)) {
			retorno = (Class<R>) LinkedHashSet.class;
		}
		
		return retorno;
	}
	
	public static boolean hasMetodo(Class<?> classe, String nomeMetodo) {
		boolean retorno = false;
		for (Method metodo : classe.getMethods()) {
			if (metodo.getName().equals(nomeMetodo)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}
	
	public static boolean hasMetodoDeclarado(Class<?> classe, String nomeMetodo) {
		boolean retorno = false;
		for (Method metodo : classe.getDeclaredMethods()) {
			if (metodo.getName().equals(nomeMetodo)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}
}
