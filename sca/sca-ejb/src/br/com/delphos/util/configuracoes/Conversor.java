package br.com.delphos.util.configuracoes;

import java.util.Collection;

public interface Conversor {

	TipoValor getTipoValor();
	
	/**
	 * Retorna a classe do objeto de retorno.
	 * 
	 * <p>Caso o tipo de valor retornado seja {@link TipoValor#OBJETO},
	 * a classe de retorno será a classe do objeto retornado. Se o tipo
	 * de valor for {@link TipoValor#COLECAO}, a classe de retorno será
	 * a classe que implementa a {@link #getClasseColecao() interface da
	 * coleção}.
	 * </p>
	 * 
	 * @return
	 */
	Class<?> getClasseRetorno();
	Class<? extends Collection<?>> getClasseColecao();
	Class<?> getClasseElemento();

	Object converter(String dados);
	<T> T converterParaObjeto(String dados, Class<T> classeRetorno);
	<C extends Collection<T>, T> C converterParaColecao(String dados, Class<T> classeElemento);
	<C extends Collection<T>, T> C converterParaColecao(String dados, Class<C> classeColecao, C colecao, Class<T> classeElemento);
}
