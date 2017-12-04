package br.com.delphos.sca.servicos;

import br.com.delphos.excecoes.DelphosException;

public interface ServiceFactory<ST, PT> {

	ST verificarServico(boolean forcandoReinicio) throws DelphosException;
	
	PT verificarPorta(boolean forcandoReinicio) throws DelphosException;
	
	<R> R novaRequisicao(Class<R> classeRequisicao) throws DelphosException;
	
	<R, T> T executar(R requisicao, Class<T> classeResposta) throws DelphosException;
}