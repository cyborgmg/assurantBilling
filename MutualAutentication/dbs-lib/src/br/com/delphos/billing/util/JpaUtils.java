package br.com.delphos.billing.util;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaUtils {

	public static Object getSingleResult(Query query) {
		List<?> resultados = query.getResultList();
		if (resultados != null && resultados.size() == 1) {
			return resultados.get(0);
		}
		return null;
	}

	public static <T> T getSingleResult(Query query, Class<T> classeRetorno) {
		@SuppressWarnings("unchecked")
		List<T> resultados = (List<T>) query.getResultList();
		if (resultados != null && resultados.size() == 1) {
			return resultados.get(0);
		}
		return null;
	}
	
	public static <T> T getSingleResult(TypedQuery<T> query) {
		List<T> resultados = query.getResultList();
		if (resultados != null && resultados.size() == 1) {
			return resultados.get(0);
		}
		return null;
	}
	
	public static <T> String getNomeEntidade(Class<T> classeEntidade) {
	
		String retorno = classeEntidade.getSimpleName();
		
		if (classeEntidade.isAnnotationPresent(Entity.class)) {
			
			String nomeEntidade = classeEntidade.getAnnotation(Entity.class).name();
			if (nomeEntidade != null && !nomeEntidade.isEmpty()) {
				retorno = classeEntidade.getAnnotation(Entity.class).name();
			}
		}
		return retorno;
	}
}
