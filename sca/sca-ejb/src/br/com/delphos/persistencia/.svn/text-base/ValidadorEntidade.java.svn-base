package br.com.delphos.persistencia;

import java.io.Serializable;

import javax.persistence.metamodel.Attribute;

import br.com.delphos.excecoes.ValidacaoException;

public interface ValidadorEntidade<T extends Entidade<Id>, Id extends Serializable> {

	void validar(Id id) throws ValidacaoException;
	void validar(T entidade, Class<?>... validacoes) throws ValidacaoException;
	void validar(T entidade, String atributo, Class<?>... validacoes) throws ValidacaoException;
	void validar(T entidade, Attribute<T, ?> atributo, Class<?>... validacoes) throws ValidacaoException;
}
