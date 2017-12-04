package br.com.delphos.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.constantes.PropriedadeConfiguracao;

public class Configuracoes {
	
	private static final ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle(ConstantesSca.NOME_ARQUIVO_CONFIGURACOES);	
	}
	
	public static boolean isDebug() {
		return _getBooleanOuFalse(get(PropriedadeConfiguracao.Debug.getNomePropriedade()));
	}
	
	public static String get(String nomePropriedade, Object... args) {
		String mensagem = System.getProperty(nomePropriedade);
		if (mensagem == null) {
			mensagem = bundle.getString(nomePropriedade);
		}
		if (args != null && args.length > 0) {
			mensagem = MessageFormat.format(mensagem, args);
		}
		return mensagem;
	}

	public static boolean getBooleanOuFalse(String nomePropriedade) {
		Boolean retorno = null;
		try {
			retorno = getBoolean(nomePropriedade);
		} catch (MissingResourceException ex) {
		}
		return retorno != null ? retorno : false;
	}

	public static Boolean getBoolean(String nomePropriedade) {
		return _getBoolean(get(nomePropriedade));
	}

	public static <T> List<T> getLista(PropriedadeConfiguracao config, Class<T> classeRetorno, Object... args) {
		String mensagem = get(config.getNomePropriedade(), args);
		List<T> retorno = new ArrayList<T>();
		String[] valores = mensagem.split(config.getSeparador());
		for (String valor : valores) {
			retorno.add(_getObjeto(valor, classeRetorno));
		}
		return retorno;
	}

	public static <T> List<T> getListaOuVazio(PropriedadeConfiguracao config, Class<T> classeRetorno, Object... args) {
		try {
			return getLista(config, classeRetorno, args);
		} catch (Exception ex) {
			return new ArrayList<T>();
		}
	}
	
	public static <T> T getObjeto(PropriedadeConfiguracao config, Class<T> classeRetorno, Object... args) {
		String mensagem = get(config.getNomePropriedade(), args);
		return _getObjeto(mensagem, classeRetorno);
	}

	public static <T> T getObjetoOuNulo(PropriedadeConfiguracao config, Class<T> classeRetorno, Object... args) {
		try {
			String mensagem = get(config.getNomePropriedade(), args);
			return _getObjeto(mensagem, classeRetorno);
		} catch(Exception e) {
			return null;
		}
	}

	private static boolean _getBooleanOuFalse(String valor) {
		Boolean retorno = _getBoolean(valor);
		return retorno != null ? retorno : false;
	}

	private static Boolean _getBoolean(String valor) {
		Boolean retorno = null;
		valor = valor.trim();
		if (Validador.trueString(valor)) {
			retorno = true;
		} else if (Validador.falseString(valor)) {
			retorno = false;
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	private static <T> T _getObjeto(String valor, Class<T> classeRetorno) {
		T retorno = null;
		valor = valor.trim();
		
		if (String.class.isAssignableFrom(classeRetorno)) {
			retorno = (T) valor;
			
		} else if (Long.class.isAssignableFrom(classeRetorno)) {
			retorno = (T) new Long(Long.parseLong(valor));
			
		} else if (Double.class.isAssignableFrom(classeRetorno)) {
			retorno = (T) new Double(Double.parseDouble(valor));
			
		} else if (Enum.class.isAssignableFrom(classeRetorno)) {
			boolean encontrado = false;
			try {
				Method buscarPorValor = classeRetorno.getMethod("buscarPorValor", String.class);
				retorno = (T) buscarPorValor.invoke(null, valor);
				encontrado = true;
				
			} catch (IllegalAccessException ex) {
			} catch (InvocationTargetException ex) {
			} catch (IllegalArgumentException ex) {
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			}
			
			if (!encontrado) {
				try {
					Method values = classeRetorno.getMethod("values");
					Method name = classeRetorno.getMethod("name");
					Object[] elems = (Object[]) values.invoke(null);
					
					for (Object elem : elems) {
						String elemName = (String) name.invoke(elem);
						
						if (elemName.equals(valor)) {
							retorno = (T) elem;
							encontrado = true;
							break;
						}
					}

				} catch (SecurityException e) {
				} catch (NoSuchMethodException e) {
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (InvocationTargetException e) {
				}
			}
			
			if (!encontrado) {
				throw new NoSuchElementException();
			}
			
		} else if (URL.class.isAssignableFrom(classeRetorno)) {
			try {
				retorno = (T) new URL(valor);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		
		return retorno;
	}
}
