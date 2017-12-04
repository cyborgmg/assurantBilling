package br.com.delphos.util.configuracoes.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import br.com.delphos.util.configuracoes.Configuracao;
import br.com.delphos.util.configuracoes.Configuracoes;
import br.com.delphos.util.configuracoes.Propriedade;

@Stateless
public class ConfiguracaoDLOBean implements ConfiguracaoDLO {

	private Configuracao configuracao;	
	
	@PostConstruct
	private void init() {
		this.configuracao = padrao();
	}
	
	@Override
	public void limparCache() {
		this.configuracao.limparCache();
	}

	@Override
	public String getString(Propriedade propriedade, Object... args) {
		return this.configuracao.getString(propriedade, args);
	}

	@Override
	public String getStringOuPadrao(Propriedade propriedade, String padrao, Object... args) {
		return this.configuracao.getStringOuPadrao(propriedade, padrao, args);
	}

	@Override
	public Object getObjeto(Propriedade propriedade, Object... args) {
		return this.configuracao.getObjeto(propriedade, args);
	}

	@Override
	public Object getObjetoOuPadrao(Propriedade propriedade, Object padrao, Object... args) {
		return this.configuracao.getObjetoOuPadrao(propriedade, padrao, args);
	}

	@Override
	public <T> T getObjeto(Propriedade propriedade, Class<T> classeRetorno, Object... args) {
		return this.configuracao.getObjeto(propriedade, classeRetorno, args);
	}

	@Override
	public <T> T getObjetoOuPadrao(Propriedade propriedade, Class<T> classeRetorno, T padrao, Object... args) {
		return this.configuracao.getObjetoOuPadrao(propriedade, classeRetorno, padrao, args);
	}

	@Override
	public <T> List<T> getLista(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		return this.configuracao.getLista(propriedade, classeElemento, args);
	}

	@Override
	public <T> List<T> getListaOuVazio(Propriedade propriedade, Class<T> classeElemento, Object... args) {
		return this.configuracao.getListaOuVazio(propriedade, classeElemento, args);
	}

	@Override
	public <T> List<T> getListaOuPadrao(Propriedade propriedade, Class<T> classeElemento, List<T> padrao,
			Object... args) {
		return this.configuracao.getListaOuPadrao(propriedade, classeElemento, padrao, args);
	}

	@Override
	public Configuracao padrao() {
		// Altere aqui a forma como as configurações são geradas (e.g. adicionar mais bundles, etc).
		return Configuracoes.padrao();
	}

}
