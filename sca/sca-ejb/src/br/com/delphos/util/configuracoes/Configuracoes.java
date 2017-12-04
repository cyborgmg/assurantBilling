package br.com.delphos.util.configuracoes;

import java.util.List;

public class Configuracoes {

	private static final Configuracao configuracaoGlobal = sincronizado(padrao());
	
	public static Configuracao global() {
		return configuracaoGlobal;
	}
	
	public static Configuracao padrao() {
		ConfiguracaoSimples configuracao = new ConfiguracaoSimples(true, Resolvedores.padrao());
		return configuracao;
	}
	
	public static Configuracao criar(boolean usandoCache, Resolvedor resolvedor) {
		ConfiguracaoSimples configuracao = new ConfiguracaoSimples(usandoCache, resolvedor);
		return configuracao;
	}
	
	public static Configuracao sincronizado(Configuracao configuracao) {
		configuracao = new ConfiguracaoSincronizada(configuracao);
		return configuracao;
	}

	public static String getString(Propriedade propriedade, Object... args) {
		return global().getString(propriedade, args);
	}
	
	public static String getStringOuPadrao(Propriedade propriedade, String padrao, Object... args) {
		return global().getStringOuPadrao(propriedade, padrao, args);
	}

	public static String getString(String propriedade, Object... args) {
		return global().getString(Propriedades.porNome(propriedade), args);
	}
	
	public static String getStringOuPadrao(String propriedade, String padrao, Object... args) {
		return global().getStringOuPadrao(Propriedades.porNome(propriedade), padrao, args);
	}
	
	public static Object getObjeto(Propriedade propriedade, Object... args) {
		return global().getObjeto(propriedade, args);
	}
	
	public static Object getObjetoOuPadrao(Propriedade propriedade, Object padrao, Object... args) {
		return global().getObjetoOuPadrao(propriedade, padrao, args);
	}
	
	public static <T> T getObjeto(Propriedade propriedade, Class<T> classeRetorno, Object... args) {
		return global().getObjeto(propriedade, classeRetorno, args);
	}
	
	public static <T> T getObjetoOuPadrao(Propriedade propriedade, Class<T> classeRetorno, T padrao, Object... args) {
		return global().getObjetoOuPadrao(propriedade, classeRetorno, padrao, args);
	}
	
	public static <T> List<T> getLista(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		return global().getLista(propriedade, classeElemento, args);
	}
	
	public static <T> List<T> getListaOuVazio(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		return global().getListaOuVazio(propriedade, classeElemento, args);
	}
	
	public static <T> List<T> getListaOuPadrao(Propriedade propriedade, Class<T> classeElemento, List<T> padrao, Object... args) {
		return global().getListaOuPadrao(propriedade, classeElemento, padrao, args);
	}
	
}
