package br.com.delphos.util.configuracoes;

public interface Resolvedor {

	void limparCache();
	String getString(String nomePropriedade);
}
