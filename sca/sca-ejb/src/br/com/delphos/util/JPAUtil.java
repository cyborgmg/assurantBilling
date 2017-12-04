package br.com.delphos.util;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JPAUtil{

	@SuppressWarnings("unchecked")
	public static <T> T getSingleResult(Query query, Class<T> classeRetorno) {
		List<?> resultados = query.getResultList();
		if (resultados != null && resultados.size() == 1) {
			return (T) resultados.get(0);
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
	
}