package br.com.delphos.util.configuracoes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfiguracaoSimples implements Configuracao {

	public static class Chave {
		
		private final String nomePropriedade;
		private final List<Object> argumentosPropriedade;
		private final List<Object> argumentos;
		
		public Chave() {
			this.nomePropriedade = null;
			this.argumentosPropriedade = null;
			this.argumentos = null;
		}
		
		public Chave(Propriedade propriedade) {
			this.nomePropriedade = propriedade.getNomePropriedade();
			this.argumentosPropriedade = propriedade.getArgumentos();
			this.argumentos = null;
		}
		
		public Chave(Propriedade propriedade, Object... argumentos) {
			this.nomePropriedade = propriedade.getNomePropriedade();
			this.argumentosPropriedade = propriedade.getArgumentos();
			this.argumentos = Arrays.asList(argumentos);
		}
		
		public Chave(Propriedade propriedade, List<Object> argumentos) {
			this.nomePropriedade = propriedade.getNomePropriedade();
			this.argumentosPropriedade = propriedade.getArgumentos();
			this.argumentos = argumentos;
		}
		
		public String getNomePropriedade() {
			return nomePropriedade;
		}
		
		public List<Object> getArgumentosPropriedade() {
			return argumentosPropriedade;
		}
		
		public List<Object> getArgumentos() {
			return argumentos;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nomePropriedade == null) ? 0 : nomePropriedade.hashCode());
			result = prime * result + ((argumentosPropriedade == null || argumentosPropriedade.size() == 0) ? 0 : hash(argumentosPropriedade.toArray()));
			result = prime * result + ((argumentos == null || argumentos.size() == 0) ? 0 : hash(argumentos.toArray()));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Chave other = (Chave) obj;
			if (nomePropriedade == null) {
				if (other.nomePropriedade != null)
					return false;
			} else if (!nomePropriedade.equals(other.nomePropriedade))
				return false;
			if (argumentosPropriedade == null) {
				if (other.argumentosPropriedade != null)
					return false;
			} else if (other.argumentosPropriedade == null) {
				return false;
			} else if (!listEquals(argumentosPropriedade, other.argumentosPropriedade))
				return false;
			if (argumentos == null) {
				if (other.argumentos != null)
					return false;
			} else if (other.argumentos == null) {
				return false;
			} else if (!listEquals(argumentos, other.argumentos))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "Chave [nomePropriedade=" + nomePropriedade + ", argumentosPropriedade=" + argumentosPropriedade
					+ ", argumentos=" + argumentos + "]";
		}

		public static int hash(Object... args) {
			final int prime = 31;
			int result = 1;
			for (Object obj : args) {
				if (obj != null && obj instanceof Propriedade) {
					obj = new Chave((Propriedade) obj);
				}
				result = prime * result + ((obj == null) ? 0 : obj.hashCode());
			}
			return result;
		}
		
		public static boolean listEquals(List<Object> a, List<Object> b) {
			if (a.size() != b.size()) {
				return false;
			}
			boolean resultado = true;
			for (int i = 0; i < a.size() && resultado; ++i) {
				Object oa = a.get(i);
				Object ob = b.get(i);
				if (oa != null && oa instanceof Propriedade) {
					oa = new Chave((Propriedade) oa);
				}
				if (ob != null && ob instanceof Propriedade) {
					ob = new Chave((Propriedade) ob);
				}
				if (oa != null) {
					resultado = oa.equals(ob);
				} else if (ob != null) {
					resultado = false;
				}
				if (resultado) {
					
				}
			}
			return resultado;
		}
	}
	
	public static void main(String[] args) {
		Chave prop1 = new Chave(new PropriedadeSimples("a"));
		Chave prop2 = new Chave(new PropriedadeSimples("a"));
		Chave prop3 = new Chave(new PropriedadeSimples("a"), Arrays.asList(new Object[]{1, 2, "a"}));
		Chave prop4 = new Chave(new PropriedadeSimples("b"), Arrays.asList(new Object[]{1, 2, "a"}));
		Chave prop5 = new Chave(new PropriedadeSimples("b"), Arrays.asList(new Object[]{1, 2, "a"}));
		Chave prop6 = new Chave(new PropriedadeSimples("b"), Arrays.asList(new Object[]{1, 2, "a", prop4}));
		Chave prop7 = new Chave(new PropriedadeSimples("b"), Arrays.asList(new Object[]{1, 2, "a", prop5}));
		
		assert prop1.hashCode() == prop2.hashCode() : "prop1 e prop2: hashCodes devem ser iguais";
		assert prop1.equals(prop2) : "prop1 e prop2: equals deve ser true";
		
		assert prop2.hashCode() != prop3.hashCode() : "prop2 e prop3: hashCodes devem ser diferentes";
		assert !prop2.equals(prop3) : "prop2 e prop3: equals deve ser false";
		
		assert prop3.hashCode() != prop4.hashCode() : "prop3 e prop4: hashCodes devem ser diferentes";
		assert !prop3.equals(prop4) : "prop3 e prop4: equals deve ser false";

		assert prop4.hashCode() == prop5.hashCode() : "prop4 e prop5: hashCodes devem ser iguais";
		assert prop4.equals(prop5) : "prop4 e prop5: equals deve ser true";

		assert prop5.hashCode() != prop6.hashCode() : "prop5 e prop6: hashCodes devem ser diferentes";
		assert !prop5.equals(prop6) : "prop5 e prop6: equals deve ser false";

		assert prop6.hashCode() == prop7.hashCode() : "prop6 e prop7: hashCodes devem ser iguais";
		assert prop6.equals(prop7) : "prop6 e prop7: equals deve ser true";
		
		System.out.println("Finished.");
	}
	
	public static class Valor {
		
		private final boolean valorStringCalculado;
		private final boolean valorObjetoCalculado;
		private final String valorString;
		private final Class<?> classeObjeto;
		private final Class<?> classeElemento;
		private final Object valorObjeto;
		
		public Valor() {
			this.valorStringCalculado = false;
			this.valorObjetoCalculado = false;
			this.valorString = null;
			this.valorObjeto = null;
			this.classeObjeto = null;
			this.classeElemento = null;
		}

		public Valor(String valorString) {
			this.valorStringCalculado = true;
			this.valorObjetoCalculado = false;
			this.valorString = valorString;
			this.valorObjeto = null;
			this.classeObjeto = null;
			this.classeElemento = null;
		}

		public Valor(String valorString, Object objeto, Class<?> classeObjeto, Class<?> classeElemento) {
			this.valorStringCalculado = true;
			this.valorObjetoCalculado = true;
			this.valorString = valorString;
			this.valorObjeto = objeto;
			this.classeObjeto = classeObjeto;
			this.classeElemento = classeElemento;
		}
		
		public boolean isValorStringCalculado() {
			return valorStringCalculado;
		}
		
		public boolean isValorObjetoCalculado() {
			return valorObjetoCalculado;
		}
		
		public String getValorString() {
			return valorString;
		}
		
		public Object getValorObjeto() {
			return valorObjeto;
		}

		public Class<?> getClasseObjeto() {
			return classeObjeto;
		}

		public Class<?> getClasseElemento() {
			return classeElemento;
		}

		@Override
		public String toString() {
			return "Valor [valorStringCalculado=" + valorStringCalculado + ", valorObjetoCalculado="
					+ valorObjetoCalculado + ", valorString=" + valorString + ", valorObjeto=" + valorObjeto + "]";
		}
		
	}
	
	private boolean usandoCache = true;
	private Resolvedor resolvedor = null;
	private Map<Chave, Valor> cache = new HashMap<Chave, Valor>();

	public ConfiguracaoSimples() {
	}
	
	public ConfiguracaoSimples(Resolvedor resolvedor) {
		this.resolvedor = resolvedor;
	}
	
	public ConfiguracaoSimples(boolean usandoCache, Resolvedor resolvedor) {
		this.usandoCache = usandoCache;
		this.resolvedor = resolvedor;
	}

	@Override
	public void limparCache() {
		this.cache.clear();
	}

	@Override
	public String getString(Propriedade propriedade, Object... args) {
		if (propriedade == null || propriedade.getNomePropriedade() == null) {
			throw new IllegalArgumentException();
		}
		
		boolean emCache = false;
		Chave chave = new Chave(propriedade, args);
		Valor valor = null;
		
		if (usandoCache) {
			valor = cache.get(chave);
			emCache = valor != null;
		}
		
		if (valor == null) {

			for (int i = 0; i < args.length; ++i) {
				Object arg = args[i];
				
				if (arg instanceof Propriedade) {
					Propriedade prop = (Propriedade) arg;
					Object[] argsProp = prop.getArgumentos() != null ? prop.getArgumentos().toArray() : new Object[0];
					args[i] = getString(prop, argsProp);
				}
			}
			
			valor = new Valor(_getString(chave, args));
		}
		
		if (usandoCache && !emCache) {
			cache.put(chave, valor);
		}
		
		return valor.getValorString();
	}

	@Override
	public String getStringOuPadrao(Propriedade propriedade, String padrao, Object... args) {
		String retorno;
		try {
			retorno = getString(propriedade, args);
		} catch (Exception ex) {
			retorno = padrao;
		}
		return retorno;
	}

	@Override
	public Object getObjeto(Propriedade propriedade, Object... args) {
		return _getObject(propriedade, null, null, null, null, args);
	}

	@Override
	public Object getObjetoOuPadrao(Propriedade propriedade, Object padrao, Object... args) {
		Object retorno;
		try {
			retorno = getString(propriedade, args);
		} catch (Exception ex) {
			retorno = padrao;
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjeto(Propriedade propriedade, Class<T> classeRetorno, Object... args) {
		return (T) _getObject(propriedade, null, null, classeRetorno, null, args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObjetoOuPadrao(Propriedade propriedade, Class<T> classeRetorno, T padrao, Object... args) {
		T retorno;
		if (classeRetorno == null) {
			classeRetorno = (Class<T>) padrao.getClass();
		}
		try {
			retorno = getObjeto(propriedade, classeRetorno, args);
		} catch (Exception ex) {
			retorno = padrao;
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getLista(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		List<T> lista = new ArrayList<T>();
		return (List<T>) _getObject(propriedade, (Class<? extends Collection<?>>) List.class, lista, lista.getClass(), classeElemento, args);
	}

	@Override
	public <T> List<T> getListaOuVazio(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		return getListaOuPadrao(propriedade, classeElemento, new ArrayList<T>(), args);
	}

	@Override
	public <T> List<T> getListaOuPadrao(Propriedade propriedade, Class<T> classeElemento, List<T> padrao,
			Object... args) {
		List<T> retorno;
		try {
			retorno = getLista(propriedade, classeElemento, args);
		} catch (Exception ex) {
			retorno = padrao;
		}
		return retorno;
	}

	private String _getString(Chave chave, Object... args) {
		String valorString = resolvedor.getString(chave.getNomePropriedade());
		
		if (valorString != null && args != null && args.length > 0) {
			valorString = MessageFormat.format(valorString, args);
		}
		
		return valorString;
	}
	
	private Object _getObject(Propriedade propriedade, Class<? extends Collection<?>> classeColecao, Collection<?> colecao, Class<?> classeRetorno, Class<?> classeElemento, Object... args) {
		if (propriedade == null || propriedade.getNomePropriedade() == null) {
			throw new IllegalArgumentException();
		}
		
		boolean obtendoObjetoPorTipo = classeRetorno != null;
		boolean obtendoColecao = obtendoObjetoPorTipo 
				&& classeColecao != null
				&& classeElemento != null
				&& classeColecao.isAssignableFrom(classeRetorno);
		
		boolean valorStringCalculado = false;
		boolean emCache = false;
		Chave chave = new Chave(propriedade, args);
		Valor valor = null;
		String valorString = null;
		Class<?> classeObjeto = obtendoColecao ? classeColecao : classeRetorno;
		
		if (obtendoColecao && colecao == null) {
			try {
				colecao = (Collection<?>) classeRetorno.newInstance();
				
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		
		if (usandoCache) {
			valor = cache.get(chave);
			
			if (valor != null) {
				valorStringCalculado = valor.isValorStringCalculado();
				valorString = valorStringCalculado ? valor.getValorString() : null;
			}
			
			emCache = valor != null
					&& valor.isValorObjetoCalculado();
			
			if (obtendoColecao) {
				emCache = emCache
						&& valor.getClasseObjeto() != null
						&& valor.getClasseElemento() != null
						&& classeColecao.isAssignableFrom(valor.getClasseObjeto())
						&& classeElemento.isAssignableFrom(valor.getClasseElemento());
				
			} else if (obtendoObjetoPorTipo) {
				emCache = emCache
						&& valor.getClasseObjeto() != null
						&& classeRetorno.isAssignableFrom(valor.getClasseObjeto());
			}
			
			if (!emCache) {
				valor = null;
			}
		}
		
		if (valor == null) {
			if (!valorStringCalculado) {
				valorString = getString(propriedade, args);
			}
			
			Conversor conversor = propriedade.getConversor();
			Object valorObjeto = conversor.converter(valorString);
			
			if (valorObjeto != null) {
				if (obtendoColecao && !classeColecao.isAssignableFrom(valorObjeto.getClass())) {
					throw new ClassCastException();
					
				} else if (obtendoObjetoPorTipo && !classeRetorno.isAssignableFrom(valorObjeto.getClass())) {
					throw new ClassCastException();
				}
			}
			
			valor = new Valor(valorString, valorObjeto, classeObjeto, classeElemento);
		}

		if (usandoCache && !emCache) {
			cache.put(chave, valor);
		}
		
		return valor.getValorObjeto();
	}
}
