package br.com.delphos.util.configuracoes;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class ConversorSimples implements Conversor {

	// Dados do conversor
	private TipoValor tipoValor;
	private Class<?> classeRetorno;
	private Class<? extends Collection<?>> classeColecao;
	private Class<?> classeElemento;
	
	// Parâmetros para conversão
	private Integer[] limitesArgumentos = new Integer[]{0, 0};
	private String separador = ",";

	public ConversorSimples() {
	}

	public ConversorSimples(Class<?> classeRetorno) {
		this.tipoValor = TipoValor.OBJETO;
		this.classeRetorno = classeRetorno;
		
		if (this.classeRetorno == null) {
			throw new IllegalArgumentException();
		}
	}

	public ConversorSimples(Class<? extends Collection<?>> classeColecao, Class<?> classeRetorno, Class<?> classeElemento) {
		this.tipoValor = TipoValor.COLECAO;
		this.classeRetorno = classeRetorno;
		this.classeElemento = classeElemento;
		this.classeColecao = classeColecao;
		
		if (this.classeRetorno == null) {
			this.classeRetorno = ConfiguracoesUtils.getImplementacaoColecaoPadrao(this.classeColecao);
		}
		
		if (this.classeColecao == null || this.classeRetorno == null || this.classeElemento == null) {
			throw new IllegalArgumentException();
		}
	}
	
	protected ConversorSimples(ConversorSimples conversor) {
		this.tipoValor = conversor.tipoValor;
		this.classeRetorno = conversor.classeRetorno;
		this.classeColecao = conversor.classeColecao;
		this.classeElemento = conversor.classeElemento;
		
		this.limitesArgumentos = conversor.limitesArgumentos;
		this.separador = conversor.separador;
	}	

	public TipoValor getTipoValor() {
		return tipoValor;
	}

	public Class<?> getClasseRetorno() {
		return classeRetorno;
	}

	public Class<? extends Collection<?>> getClasseColecao() {
		return classeColecao;
	}

	public Class<?> getClasseElemento() {
		return classeElemento;
	}

	public Integer[] getLimitesArgumentos() {
		return limitesArgumentos;
	}

	public void setLimitesArgumentos(Integer[] limitesArgumentos) {
		this.limitesArgumentos[0] = limitesArgumentos.length > 0 ? limitesArgumentos[0] : this.limitesArgumentos[0];
		this.limitesArgumentos[1] = limitesArgumentos.length > 1 ? limitesArgumentos[1] : this.limitesArgumentos[1];
	}

	public String getSeparador() {
		return separador;
	}

	public void setSeparador(String separador) {
		this.separador = separador;
	}

	@Override
	public Object converter(String valor) {
		switch (tipoValor) {
		case OBJETO:
			return doConverter(valor, classeRetorno, null, null, null);
		case COLECAO:
			return doConverter(valor, classeRetorno, null, classeColecao, classeElemento);
		default:
			throw new RuntimeException();
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T converterParaObjeto(String dados, Class<T> classeRetorno) {
		verificarCompatibilidadeTipo(classeRetorno, null);
		return (T) doConverter(dados, classeRetorno, null, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C extends Collection<T>, T> C converterParaColecao(String dados, Class<T> classeElemento) {
		verificarCompatibilidadeTipo(this.classeColecao, classeElemento);
		return (C) doConverter(dados, classeRetorno, null, this.classeColecao, classeElemento);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <C extends Collection<T>, T> C converterParaColecao(String dados, Class<C> classeRetorno, C colecao, Class<T> classeElemento) {
		verificarCompatibilidadeTipo(classeRetorno, classeElemento);
		return (C) doConverter(dados, classeRetorno, colecao, this.classeColecao, classeElemento);
	}
	
	@SuppressWarnings("unchecked")
	protected Object doConverter(
			String valor,
			Class<?> classeRetorno,
			Collection<?> colecao,
			Class<? extends Collection<?>> classeColecao,
			Class<?> classeElemento) {
		
		Object retorno = null;
		valor = valor.trim();
		
		if (classeRetorno == null) {
			throw new IllegalArgumentException();
		}
		
		if (String.class.isAssignableFrom(classeRetorno)) {
			retorno = valor;
			
		} else if (Character.class.isAssignableFrom(classeRetorno)) {
			retorno = new Character(valor.charAt(0));
			
		} else if (Short.class.isAssignableFrom(classeRetorno)) {
			retorno = new Short(Short.parseShort(valor));
			
		} else if (Integer.class.isAssignableFrom(classeRetorno)) {
			retorno = new Integer(Integer.parseInt(valor));
			
		} else if (Long.class.isAssignableFrom(classeRetorno)) {
			retorno = new Long(Long.parseLong(valor));
			
		} else if (Float.class.isAssignableFrom(classeRetorno)) {
			retorno = new Float(Float.parseFloat(valor));
			
		} else if (Double.class.isAssignableFrom(classeRetorno)) {
			retorno = new Double(Double.parseDouble(valor));
			
		} else if (BigInteger.class.isAssignableFrom(classeRetorno)) {
			retorno = new BigInteger(valor);
			
		} else if (BigDecimal.class.isAssignableFrom(classeRetorno)) {
			retorno = new BigDecimal(valor);
	
		} else if (Boolean.class.isAssignableFrom(classeRetorno)) {
			Pattern padraoTrue = Pattern.compile("(sim|yes|true|verdadeiro|on|ligado)", Pattern.CASE_INSENSITIVE);
			Pattern padraoFalse = Pattern.compile("(nao|no|false|falso|off|desligado)", Pattern.CASE_INSENSITIVE);
			
			if (padraoTrue.matcher(valor).matches()) {
				retorno = true;
				
			} else if (padraoFalse.matcher(valor).matches()) {
				retorno = false;
				
			} else if (valor.matches("[0-9]+")) {
				int valorInt = Integer.parseInt(valor);
				retorno = valorInt != 0;
				
			} else {
				throw new IllegalArgumentException();
			}
			
		} else if (Enum.class.isAssignableFrom(classeRetorno)) {
			boolean encontrado = false;
			
			if (ConfiguracoesUtils.hasMetodo(classeRetorno, "buscarPorValor")) {
				try {
					Method buscarPorValor = classeRetorno.getMethod("buscarPorValor", String.class);
					retorno = buscarPorValor.invoke(null, valor);
					encontrado = true;
					
				} catch (IllegalAccessException ex) {
				} catch (InvocationTargetException ex) {
				} catch (IllegalArgumentException ex) {
				} catch (SecurityException e) {
				} catch (NoSuchMethodException e) {
				}
			}
			
			if (!encontrado) {
				try {
					Method values = classeRetorno.getMethod("values");
					Method name = classeRetorno.getMethod("name");
					Object[] elems = (Object[]) values.invoke(null);
					
					for (Object elem : elems) {
						String elemName = (String) name.invoke(elem);
						
						if (elemName.equals(valor)) {
							retorno = elem;
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
				retorno = new URL(valor);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
			
		} else if (File.class.isAssignableFrom(classeRetorno)) {
			retorno = new File(valor);
			
		} else if (Collection.class.isAssignableFrom(classeRetorno)) {
			if (colecao == null) {
				try {
					colecao = (Collection<?>) classeRetorno.newInstance();
					
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			
			retorno = colecao;
			
			if (classeElemento == null) {
				throw new IllegalArgumentException();
			}
			
			List<String> elementos = repartirString(valor);
			for (String elemento : elementos) {
				((Collection<Object>) retorno).add((Object) doConverter(elemento, classeElemento, null, null, null));
			}
			
			if ((this.limitesArgumentos[0] >= 0 && this.limitesArgumentos[0] >= colecao.size())
					|| (this.limitesArgumentos[1] > 0 && this.limitesArgumentos[1] <= colecao.size())) {
				throw new IllegalArgumentException();
			}
			
		} else {
			throw new UnsupportedOperationException();
		}
		
		return retorno;
	}
	
	protected void verificarCompatibilidadeTipo(Class<?> classeRetorno, Class<?> classeElemento) {
		boolean compativel = false;
		
		switch (this.tipoValor) {
		case OBJETO:
			compativel = this.classeRetorno.isAssignableFrom(classeRetorno);
			break;
		case COLECAO:
			compativel = this.classeColecao.isAssignableFrom(classeRetorno);
			
			if (compativel && classeElemento != null) {
				compativel = this.classeElemento.isAssignableFrom(classeElemento);
			}
			break;
		}
		
		if (!compativel) {
			throw new ClassCastException();
		}
	}
	
	protected List<String> repartirString(String valor) {
		return Arrays.asList(valor.split(separador));
	}
}
