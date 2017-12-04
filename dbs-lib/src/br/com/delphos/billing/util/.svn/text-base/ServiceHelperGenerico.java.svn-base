package br.com.delphos.billing.util;

import javax.ejb.EJBContext;
import javax.ejb.EJBException;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;

public class ServiceHelperGenerico<T extends RetornoServico>
		implements ServiceHelper<T> {

	private LoggerWrapper logger;
	private T retorno;
	private String mensagemRetornoPadrao = Mensagens.get(CodigoMensagem.DPH0011);
	private EJBContext context;

	public ServiceHelperGenerico() {
		
	}
	
	public ServiceHelperGenerico(LoggerWrapper logger, T retorno) {
		super();
		this.logger = logger;
		this.retorno = retorno;
	}

	@Override
	public LoggerWrapper entrando(String nomeMetodo, Object... args) {
		return this.logger.entrando(nomeMetodo, args);
	}

	@Override
	public void saindo() {
		this.logger.saindo();
	}

	@Override
	public T retorno() {
		return this.logger.saindo(this.retorno);
	}
	
	@Override
	public T retorno(boolean imprimindoRetorno) {
		return this.logger.saindo(this.retorno, imprimindoRetorno);
	}
	
	@Override
	public <RT> RT saindo(RT objetoRetorno) {
		return this.logger.saindo(objetoRetorno);
	}

	@Override
	public <RT> RT saindo(RT objetoRetorno, boolean imprimindoObjeto) {
		return this.logger.saindo(objetoRetorno, imprimindoObjeto);
	}

	@Override
	public <TT extends Throwable> TT lancando(TT ex, String mensagem,
			Object... args) {
		return this.logger.lancando(ex, mensagem, args);
	}

	@Override
	public <TT extends Throwable> TT relancando(TT ex, String mensagem,
			Object... args) {
		return this.logger.relancando(ex, mensagem, args);
	}

	@Override
	public <TT extends Throwable> TT encapsulando(TT ex, String mensagem,
			Object... args) {
		return this.logger.encapsulando(ex, mensagem, args);
	}

	@Override
	public void trace(String mensagem, Object... args) {
		this.logger.trace(mensagem, args);
	}

	@Override
	public void debug(String mensagem, Object... args) {
		this.logger.debug(mensagem, args);
	}

	@Override
	public void info(String mensagem, Object... args) {
		this.logger.info(mensagem, args);
	}

	@Override
	public void warn(String mensagem, Object... args) {
		this.logger.warn(mensagem, args);
	}

	@Override
	public void error(String mensagem, Object... args) {
		this.logger.error(mensagem, args);
	}

	@Override
	public T getRetorno() {
		return this.retorno;
	}

	@Override
	public void setValorRetorno(CodigoMensagem codigoRetorno, Object... args) {
		this.retorno.setValorRetorno(codigoRetorno);
	}

	@Override
	public void setCodigoRetornoParaParametro(
			CodigoMensagem codigoRetorno,
			String nomeParametro) {
		String nomePropriedade = String.format("parametro.%s", nomeParametro);
		String valor = Mensagens.get(nomePropriedade);
		if (valor == null) {
			valor = nomeParametro;
		}
		setValorRetorno(codigoRetorno, valor);
	}

	@Override
	public void sucesso() {
		this.retorno.setValorRetorno(CodigoMensagem.Sucesso);
	}

	@Override
	public void falha() {
		this.retorno.setValorRetorno(CodigoMensagem.Falha);
	}

	@Override
	public boolean isSucesso() {
		return retorno.isSucesso();
	}

	@Override
	public void setMensagemRetornoPadrao(String mensagemRetorno) {
		this.mensagemRetornoPadrao = mensagemRetorno;
	}

	@Override
	public String getMensagemRetornoPadrao() {
		return this.mensagemRetornoPadrao;
	}

	@Override
	public void begin(EJBContext ejbContext, boolean lancandoExcecao) throws EJBException {
		this.context = ejbContext;
		try {
			this.context.getUserTransaction().begin();
		} catch (Exception e) {
			this.falha();
			this.error(null,  e);
			if (lancandoExcecao) {
				throw new EJBException(e); 
			}
		}
	}
	
	@Override
	public void commit() throws EJBException {
		try {
			this.context.getUserTransaction().commit();
		} catch (Exception e) {
			this.falha();
			this.error(null,  e);
			throw new EJBException(e);
		}
	}
	
	@Override
	public void rollback() throws EJBException {
		try {
			this.context.getUserTransaction().rollback();
		} catch (Exception e) {
			this.falha();
			this.error(null,  e);
			throw new EJBException(e);
		}
	}
	
	@Override
	public void setRollbackOnly() throws EJBException {
		this.context.setRollbackOnly();
	}
	
}
