package br.com.delphos.billing.util;

import org.slf4j.Logger;

public class LoggerWrappers {

	public static LoggerWrapper getWrapper(Logger logger, Class<?> classeObjeto) {
		return new Slf4jWrapper(logger, classeObjeto, false);
	}

	public static LoggerWrapper getWrapper(Logger logger, Object objeto) {
		return new Slf4jWrapper(logger, objeto, false);
	}

	public static LoggerWrapper getWrapper(Logger logger, Object objeto, String cabecalho, Object... args) {
		return new Slf4jWrapper(logger, objeto, false, cabecalho, args);
	}
}
