package br.com.delphos.util.configuracoes;

import java.util.List;

public interface Configuracao {

	void limparCache();
	// RAW
	String getString(Propriedade propriedade, Object... args);
	String getStringOuPadrao(Propriedade propriedade, String padrao, Object... args);
	// UNSAFE OBJ
	Object getObjeto(Propriedade propriedade, Object... args);
	Object getObjetoOuPadrao(Propriedade propriedade, Object padrao, Object... args);
	// "TYPESAFE" OBJ
	<T> T getObjeto(Propriedade propriedade, Class<T> classeRetorno, Object... args);
	<T> T getObjetoOuPadrao(Propriedade propriedade, Class<T> classeRetorno, T padrao, Object... args);
	// "TYPESAFE" LIST
	<T> List<T> getLista(Propriedade propriedade, Class<T> classeElemento, Object... args);
	<T> List<T> getListaOuVazio(Propriedade propriedade, Class<T> classeElemento, Object... args);
	<T> List<T> getListaOuPadrao(Propriedade propriedade, Class<T> classeElemento, List<T> padrao, Object... args);
}
