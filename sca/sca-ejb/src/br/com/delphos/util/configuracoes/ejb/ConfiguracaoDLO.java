package br.com.delphos.util.configuracoes.ejb;

import javax.ejb.Local;

import br.com.delphos.util.configuracoes.Configuracao;

@Local
public interface ConfiguracaoDLO extends Configuracao {

	public Configuracao padrao();
}
