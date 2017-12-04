package br.com.delphos.billing.util;

import java.util.Arrays;

import org.slf4j.Logger;

public class Slf4jWrapper implements LoggerWrapper {

	private Class<?> classeObjeto;
	private Object objeto;
	private Logger logger;
	private String nomeMetodo;
	private boolean ignorandoCheckpoints;
	private String cabecalho;
	private Object[] argumentosCabecalho;
	
	public Slf4jWrapper() {
		
	}
	
	public Slf4jWrapper(Slf4jWrapper wrapper) {
		this.classeObjeto = wrapper.classeObjeto;
		this.objeto = wrapper.objeto;
		this.logger = wrapper.logger;
		this.nomeMetodo = wrapper.nomeMetodo;
		this.ignorandoCheckpoints = wrapper.ignorandoCheckpoints;
	}
	
	public Slf4jWrapper(Logger logger, Class<?> classeObjeto, boolean ignorandoCheckpoints) {
		this.logger = logger;
		this.classeObjeto = objeto.getClass();
		this.ignorandoCheckpoints = ignorandoCheckpoints;
	}
	
	public Slf4jWrapper(
			Logger logger, 
			Object objeto,
			boolean ignorandoCheckpoints) {
		this.logger = logger;
		this.objeto = objeto;
		this.classeObjeto = objeto.getClass();
		this.ignorandoCheckpoints = ignorandoCheckpoints;
	}
	
	public Slf4jWrapper(
			Logger logger, 
			Object objeto,
			boolean ignorandoCheckpoints, 
			String cabecalho, 
			Object... argumentosCabecalho) {
		this.logger = logger;
		this.objeto = objeto;
		this.classeObjeto = objeto.getClass();
		this.ignorandoCheckpoints = ignorandoCheckpoints;
		this.cabecalho = cabecalho;
		this.argumentosCabecalho = argumentosCabecalho;
	}

	public Slf4jWrapper(
			Logger logger, 
			Object objeto, 
			String nomeMetodo, 
			boolean ignorandoCheckpoints) {
		this.logger = logger;
		this.objeto = objeto;
		this.classeObjeto = objeto.getClass();
		this.nomeMetodo = nomeMetodo;
		this.ignorandoCheckpoints = ignorandoCheckpoints;
	} 
	
	@Override
	public LoggerWrapper entrando(String nomeMetodo, Object... args) {
		Slf4jWrapper retorno = new Slf4jWrapper(this);
		if (nomeMetodo == null) {
			nomeMetodo = new Exception().getStackTrace()[0].getMethodName();
		}
		retorno.nomeMetodo = nomeMetodo;
		if (this.logger.isTraceEnabled() && !ignorandoCheckpoints) {
			StringBuffer mensagem = new StringBuffer(">>> ENTRANDO >>> {}.{}");
			Object[] pargs = new Object[]{classeObjeto.getName(), nomeMetodo};
			if (args.length > 0) {
				mensagem.append(" - (");
				String sep = "";
				for (int i = 0; i < args.length; ++i) {
					mensagem.append(sep).append("{}");
					sep = ", ";
				}
				mensagem.append(")");
				pargs = new Object[2 + args.length];
				pargs[0] = classeObjeto.getName();
				pargs[1] = nomeMetodo;
				for (int i = 0; i < args.length; ++i) {
					pargs[2 + i] = args[i];
				}
			}
			trace(mensagem.toString(), pargs);
		}
		return retorno;
	}
	
	@Override
	public void saindo() {
		if (!ignorandoCheckpoints) {
			trace("<<< SAINDO <<< {}.{}", classeObjeto.getName(), nomeMetodo);
		}
	}

	@Override
	public <T> T saindo(T objetoRetorno) {
		if (!ignorandoCheckpoints) {
			saindo();
		} else {
			saindo(objetoRetorno, true);
		}
		return objetoRetorno;
	}

	@Override
	public <T> T saindo(T objetoRetorno, boolean imprimindoObjeto) {
		if (!ignorandoCheckpoints) {
			if (imprimindoObjeto) {
				trace("<<< SAINDO <<< {}.{} - {}", classeObjeto.getName(), nomeMetodo, objetoRetorno);
			} else {
				saindo();
			}
		}
		return objetoRetorno;
	}
	
	@Override
	public <T extends Throwable> T lancando(T ex, String mensagem, Object... args) {
		return lancando("LANCANDO", false, ex, mensagem, args);
	}
	
	@Override
	public <T extends Throwable> T relancando(T ex, String mensagem, Object... args) {
		return lancando("RELANCANDO", false, ex, mensagem, args);
	}

	@Override
	public <T extends Throwable> T encapsulando(T ex, String mensagem, Object... args) {
		return lancando("ENCAPSULANDO", true, ex, mensagem, args);
	}
	
	private <T extends Throwable> T lancando(String prefixo, boolean imprimindoCausa, T ex, String mensagem, Object... args) {
		if (this.logger.isTraceEnabled() && !ignorandoCheckpoints) {
			int tamanhoArray = 5;
			int idx = 0;
			StringBuffer msg = new StringBuffer("!!! ");
			msg.append(prefixo);
			msg.append(" !!! Em {}.{}, exceção: \"{}: {}\"");
			if (imprimindoCausa) {
				tamanhoArray += 2;
				msg.append(", causa: \"{}: {}\"");
			}
			msg.append(", objeto: \\{{}\\}");
			if (mensagem != null) {
				tamanhoArray += args.length;
				msg.append("mensagem: ").append(mensagem);
			} else {
				msg.append(".");
			}
			Object[] pargs = new Object[tamanhoArray];
			pargs[idx++] = classeObjeto.getName();
			pargs[idx++] = nomeMetodo;
			pargs[idx++] = ex.getClass().getName();
			pargs[idx++] = ex.getLocalizedMessage();
			if (imprimindoCausa) {
				pargs[idx++] = ex.getCause().getClass().getName();
				pargs[idx++] = ex.getCause().getLocalizedMessage();
			}
			pargs[idx++] = objeto;
			if (args.length > 0) {
				for (int i = 0; i < args.length; ++i) {
					pargs[idx++] = args[i];
				}
			}
			trace(msg.toString(), pargs);
		}
		return ex;
	}
	
	@Override
	public void trace(String mensagem, Object... args) {
		this.logger.trace(prepararMensagem(mensagem), prepararArgumentos(args));
	}
	
	@Override
	public void debug(String mensagem, Object... args) {
		this.logger.debug(prepararMensagem(mensagem), prepararArgumentos(args));
	}
	
	@Override
	public void info(String mensagem, Object... args) {
		this.logger.info(prepararMensagem(mensagem), prepararArgumentos(args));
	}
	
	@Override
	public void warn(String mensagem, Object... args) {
		this.logger.warn(prepararMensagem(mensagem), prepararArgumentos(args));
	}
	
	@Override
	public void error(String mensagem, Object... args) {
		this.logger.error(prepararMensagem(mensagem), prepararArgumentos(args));
	}
	
	private String prepararMensagem(String mensagem) {
		if (cabecalho != null) {
			return String.format("%s%s", cabecalho, mensagem);
		}
		return mensagem;
	}
	
	private Object[] prepararArgumentos(Object... argumentos) {
		if (cabecalho != null && argumentosCabecalho != null && argumentosCabecalho.length > 0) {
			Object[] argumentosFinal = Arrays.copyOf(argumentos, argumentos.length + argumentosCabecalho.length);
			for (int i = 0; i < argumentosCabecalho.length; ++i) {
				argumentosFinal[argumentos.length + i] = argumentosCabecalho[i];
			}
			return argumentosFinal;
		}
		return argumentos;
	}
}
