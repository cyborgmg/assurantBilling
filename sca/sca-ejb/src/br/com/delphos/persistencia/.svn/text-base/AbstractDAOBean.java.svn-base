package br.com.delphos.persistencia;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import br.com.delphos.excecoes.DelphosRuntimeException;
import br.com.delphos.sca.constantes.ConstantesSca;

public abstract class AbstractDAOBean<T extends Entidade<Id>, Id extends Serializable> implements AbstractDAO<T, Id> {

	@PersistenceContext(unitName = ConstantesSca.UNIT_NAME)
	protected EntityManager entityManager;

	private Class<T> classeEntidade;
	private Class<Id> classeId;

	public AbstractDAOBean(Class<T> entityClass, Class<Id> classeId) {
		this.classeEntidade = entityClass;
		this.classeId = classeId;
	}

	public AbstractDAOBean(Class<T> entityClass, Class<Id> classeId, EntityManager entityManager) {
		this.classeEntidade = entityClass;
		this.classeId = classeId;
		this.entityManager = entityManager;
	}

	@Override
	public Class<T> getClasseEntidade() {
		return classeEntidade;
	}
	
	@Override
	public Class<Id> getClasseId() {
		return classeId;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void alterar(T entity) {
		getEntityManager().merge(entity);
	}

	@Override
	public void excluirPorId(Id id) {
		T entidade = getEntityManager().find(classeEntidade, id);
		if (entidade != null) {
			getEntityManager().remove(entidade);
		}
	}

	@Override
	public T obterPorId(Id id) {
		return getEntityManager().find(classeEntidade, id);
	}
	
	@Override
	public boolean isGerenciado(T entidade) {
		return getEntityManager().contains(entidade);
	}
	
	@Override
	public void atualizar(T entidade) {
		getEntityManager().refresh(entidade);
	}

	@Override
	public T destacar(T entidade) {
		if (isGerenciado(entidade)) {
			getEntityManager().flush();
			getEntityManager().detach(entidade);
		}
		return entidade;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T clonar(T entidade) {		
		try {
			return (T) BeanUtils.cloneBean(entidade);
			
		} catch (IllegalAccessException e) {
			throw new DelphosRuntimeException(e);
		} catch (InstantiationException e) {
			throw new DelphosRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new DelphosRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new DelphosRuntimeException(e);
		}
	}
	
	public abstract void excluirPorIdentidade(T entidade);
	public abstract T obterPorIdentidade(T entidade);
	
	@Override
	public List<T> listar() {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(classeEntidade);
		cq.select(cq.from(classeEntidade));
		return getEntityManager().createQuery(cq).getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> listarPorFaixa(long... range) {
		CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(classeEntidade);
		cq.select(cq.from(classeEntidade));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(new Long(range[1] - range[0]).intValue());
		q.setFirstResult(new Long(range[0]).intValue());
		return (List<T>) q.getResultList();
	}

	@Override
	public long contar() {
		CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
		javax.persistence.criteria.Root<T> rt = cq.from(classeEntidade);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).longValue();
	}

	@Override
	public Id incluir(T entity) {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		Id retorno = entity.getId();
		return retorno;
	}

    @SuppressWarnings({ "rawtypes" })
	@Override
    public T completar(T entidadeOriginal, String... nomesPropriedade) {
		boolean semErros = true;
		T entidade = entidadeOriginal;
		
		if (!getEntityManager().contains(entidadeOriginal)) {
			entidade = getEntityManager().find(getClasseEntidade(), entidadeOriginal.getId());
		}
		
		if (semErros) {
			
			// Se não foram fornecidas propriedades para carregar, carregamos
			// todas as propriedades.
			if (nomesPropriedade.length == 0) {
				PropertyDescriptor[] descritores = PropertyUtils.getPropertyDescriptors(entidade);
				nomesPropriedade = new String[descritores.length];
				
				for (int i = 0; i < descritores.length; ++i) {
					nomesPropriedade[i] = descritores[i].getName();
				}
			}

			try {
				for (String nomePropriedade : nomesPropriedade) {
					if (PropertyUtils.isReadable(entidade, nomePropriedade)
							&& PropertyUtils.isWriteable(entidadeOriginal, nomePropriedade)) {
						Object objeto = PropertyUtils.getProperty(entidade, nomePropriedade);
						
						if (Collection.class.isAssignableFrom(objeto.getClass())) {
							Collection colecao = (Collection) objeto;
							
							if (colecao != null) {
								colecao.size(); // lazy fetch de coleções 
							}
						}
						
						PropertyUtils.setProperty(entidadeOriginal, nomePropriedade, objeto);
					}
				}
				
			} catch (IllegalAccessException e) {
				semErros = false;
			} catch (InvocationTargetException e) {
				semErros = false;
			} catch (NoSuchMethodException e) {
				semErros = false;
			}
		}
		
		if (!semErros) {
			entidadeOriginal = null;
		}
		
		return entidadeOriginal;
    }
}
