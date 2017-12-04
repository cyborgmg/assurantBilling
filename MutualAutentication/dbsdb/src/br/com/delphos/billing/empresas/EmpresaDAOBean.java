package br.com.delphos.billing.empresas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;


/**
 * Session Bean implementation class EmpresaDAOBean
 */
@Stateless
public class EmpresaDAOBean extends AbstractDAO<Empresa> implements EmpresaDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public EmpresaDAOBean() {
		super(Empresa.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Empresa obterPorIdentidade(Empresa entidade) {
		String jpql = "select o from Empresa o"
				+ " where o.codigo = :codigo";
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		query.setParameter(Empresa_.codigo.getName(), entidade.getCodigo());
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public Empresa obterPorCodigo(String codigoEmpresa) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Empresa.class) + " o where o.codigo = :codigoEmpresa";
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		query.setParameter("codigoEmpresa", codigoEmpresa);
		return JpaUtils.getSingleResult(query);
	}
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Empresa.class) + " o where " ;
				if (!Validador.vazio(codigoEmpresa)) {
					jpql += "upper(o.codigo) like :codigoEmpresa ";
				}
				if (!Validador.vazio(codigoEmpresa) && !Validador.vazio(descricaoEmpresa)) {
					jpql += " and ";
				}
				if (!Validador.vazio(descricaoEmpresa)) {
					jpql += "upper(o.descricao) like :descricaoEmpresa";
				}
				jpql += " order by o.descricao";
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		if (!Validador.vazio(codigoEmpresa))
			query.setParameter("codigoEmpresa", codigoEmpresa.toUpperCase() +"%");
		if (!Validador.vazio(descricaoEmpresa))
			query.setParameter("descricaoEmpresa", descricaoEmpresa.toUpperCase()+"%");
		return query.getResultList();
	}

}
