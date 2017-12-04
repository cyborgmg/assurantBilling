package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;


/**
 * Session Bean implementation class ListaValorDAOBean
 */
@Stateless
public class ListaValorDAOBean extends AbstractDAO<ListaValor> implements ListaValorDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ListaValorDAOBean() {
		super(ListaValor.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ListaValor obterPorIdentidade(ListaValor entidade) {
		String jpql = "select o from ListaValor o"
				+ " where o.codigo = :codigo";
		TypedQuery<ListaValor> query = getEntityManager().createQuery(jpql, ListaValor.class);
		query.setParameter(ListaValor_.codigo.getName(), entidade.getCodigo());
		return JpaUtils.getSingleResult(query);
	}
	
	@Override
	public ListaValor obterPorCodigo(String codigo) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ListaValor.class) + " o where o.codigo = :codigo";
		jpql += " order by o.descricao";
		TypedQuery<ListaValor> query = getEntityManager().createQuery(jpql, ListaValor.class);
		query.setParameter("codigo", codigo);
		ListaValor lst = JpaUtils.getSingleResult(query);
//		lst.getItensLista().size();
		completar(lst, ListaValor_.itensLista.getName());
		return lst;
	}
	
	public List<ListaValor> listarPorCriterio(String codigo, String descricao, String status, String tipoUso) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ListaValor.class) + " o"
				+ " where ";
				if (!Validador.vazio(codigo)) {
					jpql += "upper(o.codigo) like :codigo ";
				}
				if (!Validador.vazio(descricao)) {
					if (!Validador.vazio(codigo)) {
						jpql += " and ";
					}
					jpql += " upper(o.descricao) like :descricao ";
				}
				if (!Validador.vazio(status)) {
					if (!Validador.vazio(codigo) || !Validador.vazio(descricao)) {
						jpql += " and ";
					}
					jpql += " upper(o.status) = :status ";
				}
				if (!Validador.vazio(tipoUso)) {
					if (!Validador.vazio(codigo) || !Validador.vazio(descricao)
							|| !Validador.vazio(status)) {
						jpql += " and ";
					}
					jpql += " upper(o.tipoUso) = :tipoUso ";
				}
				jpql += " order by o.descricao";				
				
				TypedQuery<ListaValor> query = getEntityManager().createQuery(jpql, ListaValor.class);
				if (!Validador.vazio(codigo)) {
					query.setParameter("codigo", codigo.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricao)) {
					query.setParameter("descricao", descricao.toUpperCase() + "%");
				}
				if (!Validador.vazio(status)) {
					query.setParameter("status",  status.toUpperCase() );
				}
				if (!Validador.vazio(tipoUso)) {
					query.setParameter("tipoUso",  tipoUso.toUpperCase() );
				}
		return query.getResultList();		
	}
	
}
