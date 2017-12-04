package br.com.delphos.billing.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import br.com.delphos.billing.persistencia.DLOEntidade;

public class EntidadeUtils {

	/**
	 * Obtém o ID de uma entidade de negócio.
	 * 
	 * A classe da entidade deve possuir um método com assinatura
	 * {@code <T extends Object> T getId()}.
	 * 
	 * @param entidade A entidade a ser verificada.
	 * @return O ID da entidade, ou nulo caso a entidade não possua
	 * um ID ou se algum erro ocorrer.
	 */
	public static Object getId(Object entidade) {
		Object id = null;
		if (entidade != null) {
            Method metodo;
			try {
				metodo = entidade.getClass().getMethod("getId");
            	id = (Object) metodo.invoke(entidade);
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		return id;
	}
	
	/**
	 * Verifica se uma classe de validação está contemplada em uma lista de classes de validação.
	 * 
	 * A verificação se baseia nos conceitos da especificação JSR-303. 
	 * 
	 * @param validacao Uma interface representando a validação a ser verificada.
	 * @param validacoesAplicadas As interfaces representando as validações a serem usadas na
	 * comparação. 
	 * @return {@code true} se a validação está contemplada, e {@code false} caso não esteja ou
	 * se a verificação não pôde ser realizada.
	 */
	public static boolean isValidacaoInclusa(Class<?> validacao, Class<?>... validacoesAplicadas) {
		boolean retorno = false;
		if (validacao != null && validacoesAplicadas.length > 0) {
			Set<Class<?>> listaValidacoes = new HashSet<Class<?>>();
			for (int idx = 0; idx < validacoesAplicadas.length; ++idx) {
				Class<?> validacaoAplicada = validacoesAplicadas[0];
				listaValidacoes.add(validacaoAplicada);
				for (Class<?> validacaoAnexada : validacaoAplicada.getInterfaces()) {
					listaValidacoes.add(validacaoAnexada);
				}
			}
			for (Class<?> validacaoAplicada : listaValidacoes) {
				if (validacao.isAssignableFrom(validacaoAplicada)) {
					retorno = true;
					break;
				}
			}
		}
		return retorno;
	}
	
	public static boolean forcarBuscaLentaJpa(Object entidade) {
		boolean semErros = true;
		Set<Object> objetosVerificados = new HashSet<Object>();
		List<Object> objetosParaVerificacao = new LinkedList<Object>();
		objetosParaVerificacao.add(entidade);
		do {
			Object objeto = objetosParaVerificacao.remove(0);
			objetosVerificados.add(objeto);
			Class<?> classeObjeto = objeto.getClass();
			for (Field campo : classeObjeto.getFields()) {
				Class<?> tipoCampo = campo.getDeclaringClass();
				try {
					if (Collection.class.isAssignableFrom(tipoCampo)) {
						// Campo representa uma lista, devemos verificar se o tipo
						// da lista é de entidades.
						Collection<?> objetosCampo = (Collection<?>) campo.get(objeto);
						Type tipoGenericoCampo = campo.getGenericType();
						forcarBuscaLentaJpa_campo(objetosCampo, tipoGenericoCampo, objetosParaVerificacao);
					} else if (tipoCampo.isAnnotationPresent(Entity.class)) {
						Object objetoFilho = campo.get(entidade);
						objetosParaVerificacao.add(objetoFilho);
					}
				} catch (IllegalAccessException ex) {
					semErros = false;
				}
			}
			for (Method metodo : classeObjeto.getMethods()) {
				if (metodo.getName().startsWith("get") && metodo.getParameterTypes().length == 0) {
					Class<?> tipoRetorno = metodo.getReturnType();
					try {
						if (Collection.class.isAssignableFrom(tipoRetorno)) {
							// Primeiro, o "fetch".
							Collection<?> objetosCampo = (Collection<?>) metodo.invoke(objeto);
							Type tipoGenericoCampo = metodo.getGenericReturnType();
							forcarBuscaLentaJpa_campo(objetosCampo, tipoGenericoCampo, objetosParaVerificacao);
						} else if (tipoRetorno.isAnnotationPresent(Entity.class)) {
							Object objetoFilho = metodo.invoke(entidade);
							objetosParaVerificacao.add(objetoFilho);
						}
					} catch (IllegalAccessException ex) {
						semErros = false;
					} catch (IllegalArgumentException ex) {
						semErros = false;
					} catch (InvocationTargetException e) {
						semErros = false;
					}
				}
			}
		} while (!objetosParaVerificacao.isEmpty());
		return semErros;
	}
	
	private static void forcarBuscaLentaJpa_campo(Collection<?> objetosCampo, Type tipoCampo, List<Object> objetosParaVerificacao) throws IllegalArgumentException, IllegalAccessException {
		// Precisamos saber qual o tipo do campo. Se pudermos deduzir o tipo genérico
		// usado na coleção (e.g. List<Usuario>), poderemos checar se o tipo
		// parametrizado contém uma anotação Entity.
		if (tipoCampo instanceof ParameterizedType) {
			ParameterizedType tipoParametrizado = (ParameterizedType) tipoCampo;
			Type tipoRealizado = tipoParametrizado.getActualTypeArguments()[0];
			if (tipoRealizado instanceof Class) {
				Class<?> classeRealizada = (Class<?>) tipoRealizado;
				if (classeRealizada.isAnnotationPresent(Entity.class)) {
					// O tipo representa uma entidade. Iremos adicionar todos os elementos
					// para verificação posterior.
					for (Object entidadeFilha : objetosCampo) {
						objetosParaVerificacao.add(entidadeFilha);
					}
				}
			}
		}
	}
	
	public static String getNomePropriedade(String nomeMetodoOuCampo) {
		String retorno = null;
		if (nomeMetodoOuCampo != null && (nomeMetodoOuCampo.startsWith("get") || nomeMetodoOuCampo.startsWith("set"))) {
			retorno = nomeMetodoOuCampo.substring(3);
			retorno = String.format("%s%s", retorno.substring(0, 1).toLowerCase(), retorno.substring(1));
		} else {
			retorno = nomeMetodoOuCampo;
		}
		return retorno;
	}
	
	public static <T> String getNomeTabela(EntityManager em, Class<T> entityClass) {
	    Metamodel meta = em.getMetamodel();
	    
	    EntityType<T> entityType = meta.entity(entityClass);

	    Table t = entityClass.getAnnotation(Table.class);

	    String tableName = ( (t == null) ? entityType.getName().toUpperCase(): t.name() );

	    return tableName;
	}

	
	public static <T> List<T> prepararPropriedades(DLOEntidade<T> dlo, List<T> lista, Attribute<T, ?> propriedade) {
		return prepararPropriedades(dlo, lista, propriedade.getName());
	}

	public static <T> List<T> prepararPropriedades(DLOEntidade<T> dlo, List<T> lista, String... propriedades) {
		if (lista != null) {
			for (int i = 0; i < lista.size(); ++i) {
				lista.set(i, dlo.completar(lista.get(i), propriedades));
			}
		}
		return lista;
	}
}
