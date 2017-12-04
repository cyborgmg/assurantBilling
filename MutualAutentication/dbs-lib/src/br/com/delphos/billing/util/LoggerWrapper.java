package br.com.delphos.billing.util;

public interface LoggerWrapper {

	LoggerWrapper entrando(String nomeMetodo, Object... args);

	void saindo();
	
	<T> T saindo(T objetoRetorno);
	
	<T> T saindo(T objetoRetorno, boolean imprimindoObjeto);

	<T extends Throwable> T lancando(T ex, String mensagem, Object... args);
	
	<T extends Throwable> T relancando(T ex, String mensagem, Object... args);

	<T extends Throwable> T encapsulando(T ex, String mensagem, Object... args);
	
	void trace(String mensagem, Object... args);
	
	void debug(String mensagem, Object... args);

	void info(String mensagem, Object... args);

	void warn(String mensagem, Object... args);

	void error(String mensagem, Object... args);

}