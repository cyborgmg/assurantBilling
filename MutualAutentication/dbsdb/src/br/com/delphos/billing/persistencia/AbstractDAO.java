package br.com.delphos.billing.persistencia;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.delphos.billing.empresas.Empresa_;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.JpaUtils;

public abstract class AbstractDAO<T> implements DAOEntidade<T> {
	
	private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public Object incluir(T entity) throws AbstractMethodError {

        Object retorno = null;
        getEntityManager().persist(entity);
        getEntityManager().flush();
        try {

            Class<?>[] classes = new Class<?>[0];
            Method metodo = entityClass.getMethod("getId", classes);
            if (metodo != null) {

                Object[] objetos = new Object[0];
                retorno = (Object) metodo.invoke(entity, objetos);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return retorno;
    }

    public void alterar(T entity) {
        getEntityManager().merge(entity);
    }

    public void excluir(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

	public void excluirPorId(Object id) {
		T entidade = getEntityManager().find(entityClass, id);
		if (entidade != null) {
			getEntityManager().remove(entidade);
		}
	}
	
    public T obter(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public T obterEntidadeAtualizada(T entidade) {
    	T retorno = entidade;
    	if (!getEntityManager().contains(retorno)) {
    		retorno = getEntityManager().find(entityClass, EntidadeUtils.getId(entidade));
    	}
    	return retorno;
    }
    
    public List<T> listar() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @SuppressWarnings("unchecked")
	public List<T> listarPorFaixa(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return (List<T>) q.getResultList();
    }

    @Override
    public long contar() {
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).longValue();
    }

	@Override
	public long contarReferencias(T entidade, String atributo) {
		String jpql = "select size(o." + atributo + ")"
				+ " from " + JpaUtils.getNomeEntidade(entityClass) + " o"
				+ " where o.id = :id";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(Empresa_.id.getName(), EntidadeUtils.getId(entidade));
		long retorno = JpaUtils.getSingleResult(query, Integer.class);
		return retorno;
	}
    
    @SuppressWarnings({ "rawtypes" })
	@Override
    public T completar(T entidadeOriginal, String... nomesPropriedade) {
		boolean semErros = true;
		T entidade = obterEntidadeAtualizada(entidadeOriginal);
		
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
					System.out.println(PropertyUtils.class);
					if (PropertyUtils.isReadable(entidade, nomePropriedade)
							&& PropertyUtils.isWriteable(entidadeOriginal, nomePropriedade)) {
						Object objeto = PropertyUtils.getProperty(entidade, nomePropriedade);
						
						if (Collection.class.isAssignableFrom(objeto.getClass())) {
							Collection colecao = (Collection) objeto;
							
							if (colecao != null) {
								colecao.size(); // lazy fetch de coleções 
							}
							
							// Será que o procedimento abaixo é necessário, por
							// questões de proxies (e.g. Hibernate e EclipseLink
							// têm abordagens diferentes quanto ao que deve ser
							// envolvido em um proxy)?
//							Collection colecaoOriginal = (Collection) PropertyUtils.getProperty(entidadeOriginal, nomePropriedade);
//							if (colecaoOriginal == null) {
//								PropertyUtils.setProperty(entidadeOriginal, nomePropriedade, objeto);
//								
//							} else {
//								colecaoOriginal.clear();
//								colecaoOriginal.addAll(colecao);
//							}
//							
//						} else {
//							PropertyUtils.setProperty(entidadeOriginal, nomePropriedade, objeto);
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
