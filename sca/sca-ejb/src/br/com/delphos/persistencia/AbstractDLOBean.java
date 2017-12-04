package br.com.delphos.persistencia;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Transient;
import javax.persistence.metamodel.Attribute;
import javax.validation.Validator;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.DelphosRuntimeException;
import br.com.delphos.excecoes.EntidadeDuplicadaException;
import br.com.delphos.excecoes.EntidadeInexistenteException;
import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.PersistenciaException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.validacoes.Alteracao;
import br.com.delphos.persistencia.validacoes.ConsultaPorId;
import br.com.delphos.persistencia.validacoes.ConsultaPorIdentidade;
import br.com.delphos.persistencia.validacoes.Criacao;
import br.com.delphos.persistencia.validacoes.ExclusaoPorId;
import br.com.delphos.persistencia.validacoes.ExclusaoPorIdentidade;
import br.com.delphos.sca.constantes.CodigoParametro;

public abstract class AbstractDLOBean<T extends Entidade<Id>, Id extends Serializable> implements AbstractDLO<T, Id> {

	@Inject
	protected Validator validator;
	
	protected abstract AbstractDAO<T, Id> getDAOEntidade();

	@Override
	public boolean isPersistidoPorId(Id id) throws DelphosException {
		return isPersistido(instanciar(id));
	}
	
	@Override
	public boolean isPersistido(T entidade) throws DelphosException {
		validarEntidadeNaoNula(entidade);
		boolean retorno = false;		
		entidade = prepararEntidadeParaIsPersistido(entidade);
		
		if (!entidade.hasId()) {
			validar(entidade, ConsultaPorId.class);
			retorno = isEntidadePersistidaPorId(entidade);

		} else {
			validar(entidade, ConsultaPorIdentidade.class);
			retorno = isEntidadePersistidaPorIdentidade(entidade);
		}
		
		return retorno;
	}
	
	
	@Override
	public Id manter(T entidade) throws DelphosException {
		validarEntidadeNaoNula(entidade);
		Id id = null;
		entidade = prepararEntidadeParaManter(entidade);

		if (!entidade.hasId()) {
			validar(entidade, Criacao.class);
			if (isEntidadePersistidaPorIdentidade(entidade)) {
				throw new EntidadeDuplicadaException();
			}
			id = incluirEntidade(entidade);

		} else {
			validar(entidade, Alteracao.class);
			T entidadePersistida = obterEntidadePorIdentidade(entidade);
			if (entidadePersistida != null) {
				Id idPersistido = entidadePersistida.getId();
				if (!idPersistido.equals(entidade.getId())) {
					throw new EntidadeDuplicadaException();
				}
			}
			id = alterarEntidade(entidade);
		}

		return id;
	}

	@Override
	public void salvar(T entidade) throws DelphosException {
		Id id = manter(entidade);
		
		if (id == null) {
			throw new PersistenciaException();
		}
	}

	@Override
	public void excluir(T entidade) throws DelphosException {
		validarEntidadeNaoNula(entidade);
		entidade = prepararParaExclusao(entidade);
		
		if (entidade.hasId()) {
			validar(entidade, ExclusaoPorId.class);
			excluirEntidadePorId(entidade);

		} else {
			validar(entidade, ExclusaoPorIdentidade.class);
			excluirEntidadePorIdentidade(entidade);
		}
	}

	@Override
	public void excluirPorId(Id id) throws DelphosException {
		excluir(instanciar(id));
	}

	@Override
	public T obterPorId(Id id) throws DelphosException {
		return obter(instanciar(id));
	}

	@Override
	public T atualizar(T entidade) throws DelphosException {
		validarEntidadeNaoNula(entidade);
		T retorno = null;
		entidade = prepararEntidadeParaAtualizar(entidade);
		
		if (getDAOEntidade().isGerenciado(entidade)) {
			retorno = atualizarEntidade(entidade);
			
		} else {
			if (entidade.hasId()) {
				validar(entidade, ConsultaPorId.class);
				retorno = obterEntidadePorId(entidade);
	
			} else {
				validar(entidade, ConsultaPorIdentidade.class);
				retorno = obterEntidadePorIdentidade(entidade);
			}
		}
		
		return retorno;
	}

	@Override
	public T atualizarOuFalhar(T entidade) throws DelphosException {
		T retorno = atualizar(entidade);
		
		if (retorno == null) {
			throw new EntidadeInexistenteException();
		}
		
		return retorno;
	}

	@Override
	public T obter(T entidade) throws DelphosException {
		validarEntidadeNaoNula(entidade);
		T retorno = null;
		entidade = prepararEntidadeParaObter(entidade);
		
		if (getDAOEntidade().isGerenciado(entidade)) {
			retorno = entidade;
			
		} else {
			if (entidade.hasId()) {
				validar(entidade.getId());
				retorno = obterEntidadePorId(entidade);
	
			} else {
				validar(entidade, ConsultaPorIdentidade.class);
				retorno = obterEntidadePorIdentidade(entidade);
			}
		}
		
		return retorno;
	}

	@Override
	public T obterOuFalhar(T entidade) throws DelphosException {
		T retorno = obter(entidade);
		
		if (retorno == null) {
			throw new EntidadeInexistenteException();
		}
		
		return retorno;
	}
	
	@Override
	public List<T> listar() {
		return getDAOEntidade().listar();
	}

	@Override
	public List<T> listarPorFaixa(long... range) throws DelphosException {
		List<T> retorno = null;

		if (range != null && range.length >= 2 && range[0] < range[1]) {
			retorno = getDAOEntidade().listarPorFaixa(range);

		} else {
			throw new ParametroInvalidoException(CodigoParametro.FaixaIndices);
		}

		return retorno;
	}

	@Override
	public long contar() {
		return getDAOEntidade().contar();
	}

	@Override
	public T completar(T entidade, String... nomesPropriedade) throws DelphosException {
		T retorno = null;
		validarEntidadeNaoNula(entidade);

		if (entidade.hasId() && nomesPropriedade != null) {
			//validar(entidade.getId());
			retorno = getDAOEntidade().completar(obterOuFalhar(entidade), nomesPropriedade);

		} else {
			throw new ParametroInvalidoException();
		}

		return retorno;
	}

	@Override
	public T completar(T entidade, Attribute<T, ?> propriedade) throws DelphosException {
		return completar(entidade, propriedade.getName());
	}

	@Override
	public void validar(Id id) throws ValidacaoException {
		if (id == null) {
			throw new ParametroInvalidoException(CodigoParametro.Id);
		}
	}

	@Override
	public void validar(T entidade, Class<?>... validacoes) throws ValidacaoException {
		validarEntidadeNaoNula(entidade);
		
		for (Field campo : getDAOEntidade().getClasseEntidade().getDeclaredFields()) {
			int mods = campo.getModifiers();
			
			if (!Modifier.isStatic(mods)
					&& !Modifier.isTransient(mods)
					&& !campo.isAnnotationPresent(Transient.class)) {
				validarCampoEntidade(entidade, campo.getName(), validacoes);
			}
		}
	}

	@Override
	public void validar(T entidade, String atributo, Class<?>... validacoes) throws ValidacaoException {
		validarEntidadeNaoNula(entidade);
		
		try {
			entidade.getClass().getDeclaredField(atributo);
			
		} catch (NoSuchFieldException ex) {
			throw new ParametroInvalidoException(CodigoParametro.Propriedade, atributo);
		}
		
		validarCampoEntidade(entidade, atributo, validacoes);
	}

	@Override
	public void validar(T entidade, Attribute<T, ?> atributo, Class<?>... validacoes) throws ValidacaoException {
		validar(entidade, atributo.getName(), validacoes);
	}
	
	@Override
	public T instanciar() {
		try {
			return getDAOEntidade().getClasseEntidade().newInstance();
		} catch (InstantiationException e) {
			throw new DelphosRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new DelphosRuntimeException(e);
		}
	}
	
	@Override
	public T instanciar(Id id) {
		T retorno = instanciar();
		boolean idAtribuido = false;
		
		try {
			Method mSetId = retorno.getClass().getDeclaredMethod("setId", getDAOEntidade().getClasseId());
			mSetId.setAccessible(true);
			mSetId.invoke(retorno, id);
			idAtribuido = true;
			
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		if (!idAtribuido) {
			try {
				Field fSetId = retorno.getClass().getDeclaredField("id");
				fSetId.setAccessible(true);
				fSetId.set(retorno, id);
				idAtribuido = true;
				
			} catch (SecurityException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (NoSuchFieldException e) {
			}
		}
		
		if (!idAtribuido) {
			throw new DelphosRuntimeException();
		}
		
		return retorno;
	}
	
	@Override
	public T copiar(T entidade) throws DelphosException {
		validarEntidadeNaoNula(entidade);
		return getDAOEntidade().clonar(entidade);
	}
	
	// EVENTOS DO DLO
	
	protected abstract void validarCampoEntidade(T entidade, String nomeAtributo, Class<?>... validacoes) throws ValidacaoException;
	
	protected void validarEntidadeNaoNula(T entidade) throws ValidacaoException {
		if (entidade == null) {
			throw new ParametroInvalidoException(CodigoParametro.Entidade);
		}
	}
	
	protected T prepararEntidadeParaIsPersistido(T entidade) throws DelphosException {
		return entidade;
	}
	
	protected boolean isEntidadePersistidaPorId(T entidade) throws DelphosException {
		return obterEntidadePorId(entidade) != null;
	}
	
	protected boolean isEntidadePersistidaPorIdentidade(T entidade) throws DelphosException {
		return obterEntidadePorIdentidade(entidade) != null;
	}
	
	protected T prepararEntidadeParaManter(T entidade) throws DelphosException {
		return entidade;
	}

	protected Id incluirEntidade(T entidade) throws DelphosException {
		return getDAOEntidade().incluir(entidade);
	}

	protected Id alterarEntidade(T entidade) throws DelphosException {
		getDAOEntidade().alterar(entidade);
		return entidade.getId();
	}
	
	protected T prepararParaExclusao(T entidade) throws DelphosException {
		return entidade;
	}
	
	protected void excluirEntidadePorIdentidade(T entidade) throws DelphosException {
		getDAOEntidade().excluirPorIdentidade(entidade);
	}
	
	protected void excluirEntidadePorId(T entidade) throws DelphosException {
		getDAOEntidade().excluirPorId(entidade.getId());
	}
	
	protected T prepararEntidadeParaAtualizar(T entidade) throws DelphosException {
		return entidade;
	}
	
	protected T prepararEntidadeParaObter(T entidade) throws DelphosException {
		return entidade;
	}
	
	protected T atualizarEntidade(T entidade) throws DelphosException {
		getDAOEntidade().atualizar(entidade);
		return entidade;
	}
	
	protected T obterEntidadePorId(T entidade) throws DelphosException {
		return getDAOEntidade().obterPorId(entidade.getId());
	}
	
	protected T obterEntidadePorIdentidade(T entidade) throws DelphosException {
		return getDAOEntidade().obterPorIdentidade(entidade);
	}
}
