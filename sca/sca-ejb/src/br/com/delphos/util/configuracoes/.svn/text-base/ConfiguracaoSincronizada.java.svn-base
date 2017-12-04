package br.com.delphos.util.configuracoes;

import java.util.List;

public class ConfiguracaoSincronizada implements Configuracao {

	private Configuracao configuracao;
	private final Object lock = new Object();

	public ConfiguracaoSincronizada(Configuracao configuracao) {
		this.configuracao = configuracao;
	}

	@Override
	public void limparCache() {
		synchronized(lock) {
			this.configuracao.limparCache();
		}
	}

	@Override
	public String getString(Propriedade propriedade, Object... args) {
		synchronized(lock) {
			return configuracao.getString(propriedade, args);
		}
	}

	@Override
	public String getStringOuPadrao(Propriedade propriedade, String padrao, Object... args) {
		synchronized(lock) {
			return configuracao.getStringOuPadrao(propriedade, padrao, args);
		}
	}

	@Override
	public Object getObjeto(Propriedade propriedade, Object... args) {
		synchronized(lock) {
			return configuracao.getObjeto(propriedade, args);
		}
	}

	@Override
	public Object getObjetoOuPadrao(Propriedade propriedade, Object padrao, Object... args) {
		synchronized(lock) {
			return configuracao.getObjetoOuPadrao(propriedade, padrao, args);
		}
	}

	@Override
	public <T> T getObjeto(Propriedade propriedade, Class<T> classeRetorno, Object... args) {
		synchronized(lock) {
			return configuracao.getObjeto(propriedade, classeRetorno, args);
		}
	}

	@Override
	public <T> T getObjetoOuPadrao(Propriedade propriedade, Class<T> classeRetorno, T padrao, Object... args) {
		synchronized(lock) {
			return configuracao.getObjetoOuPadrao(propriedade, classeRetorno, padrao, args);
		}
	}

	@Override
	public <T> List<T> getLista(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		synchronized(lock) {
			return configuracao.getLista(propriedade, classeElemento, args);
		}
	}

	@Override
	public <T> List<T> getListaOuVazio(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		synchronized(lock) {
			return configuracao.getListaOuVazio(propriedade, classeElemento, args);
		}
	}

	@Override
	public <T> List<T> getListaOuPadrao(Propriedade propriedade, Class<T> classeElemento, List<T> padrao,
			Object... args) {
		synchronized(lock) {
			return configuracao.getListaOuPadrao(propriedade, classeElemento, padrao, args);
		}
	}
}
