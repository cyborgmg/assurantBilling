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
 * Session Bean implementation class ItemListaDAOBean
 */
@Stateless
public class ItemListaDAOBean extends AbstractDAO<ItemLista> implements ItemListaDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ItemListaDAOBean() {
		super(ItemLista.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ItemLista obterPorIdentidade(ItemLista entidade) {
		String jpql = "select o from ItemLista o"
				+ " where o.codigo = :codigo"
				+ " and o.listaValor.id = :listaValor";
		TypedQuery<ItemLista> query = getEntityManager().createQuery(jpql, ItemLista.class);
		query.setParameter(ItemLista_.codigo.getName(), entidade.getCodigo());
		query.setParameter(ItemLista_.listaValor.getName(), entidade.getListaValor().getId());
		return JpaUtils.getSingleResult(query);
	}

	@Override
	public ItemLista obterPorCodigo(ItemLista itemLista) {
		
		String jpql = "select i from " + JpaUtils.getNomeEntidade(ItemLista.class) + " i where i.codigo = :codigo";
		
		TypedQuery<ItemLista> query = getEntityManager().createQuery(jpql, ItemLista.class);
		
		query.setParameter("codigo", itemLista.getCodigo());
		
		return JpaUtils.getSingleResult(query);
		
	}
	
	@Override
	public ItemLista obterPorCodigo(String codItemLista, String codListaValor) {
		
		String jpql = "select i from " + JpaUtils.getNomeEntidade(ItemLista.class) + " i where i.codigo = :codigoItem"
				+ " and i.listaValor.codigo = :codListaValor";
		
		TypedQuery<ItemLista> query = getEntityManager().createQuery(jpql, ItemLista.class);
		
		query.setParameter("codigoItem", codItemLista);
		query.setParameter("codListaValor", codListaValor);
		
		return JpaUtils.getSingleResult(query);
		
	}
	
	public List<ItemLista> listarItemListaPorCriterio (String codigoItemLista, String descricaoItemLista, String idListaValor, String status) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ItemLista.class) + " o " 
		+ " inner join fetch o.listaValor lv " + 
				" where ";
				if (!Validador.vazio(codigoItemLista)) {
					jpql += "upper(o.codigo) like :codigoItemLista ";
				}
				if (!Validador.vazio(descricaoItemLista)) {
					if (!Validador.vazio(codigoItemLista)) {
						jpql += " and ";
					}
					jpql += "upper(o.descricao) like :descricaoItemLista";
				}
				if ((!Validador.vazio(codigoItemLista) || !Validador.vazio(descricaoItemLista))
						&& !Validador.vazio(idListaValor)) {
					jpql += " and ";
				}
				if (!Validador.vazio(idListaValor)) {
					jpql += "lv.id = :idListaValor";
				}
				jpql += " order by o.descricao";
				
		TypedQuery<ItemLista> query = getEntityManager().createQuery(jpql, ItemLista.class);
		if (!Validador.vazio(codigoItemLista))
			query.setParameter("codigoItemLista", codigoItemLista.toUpperCase() +"%");
		if (!Validador.vazio(descricaoItemLista))
			query.setParameter("descricaoItemLista", descricaoItemLista.toUpperCase()+"%");
		if (!Validador.vazio(idListaValor))
			query.setParameter("idListaValor", Long.valueOf(idListaValor));
		return query.getResultList();
	}

	@Override
	public List<ItemLista> listarItemListaPorListaValor(String codListaValor) {
		String jpql = "select i from " + JpaUtils.getNomeEntidade(ItemLista.class) + " i where i.listaValor.codigo = :codListaValor";
		
		TypedQuery<ItemLista> query = getEntityManager().createQuery(jpql, ItemLista.class);
		
		query.setParameter("codListaValor", codListaValor);
		
		return query.getResultList();
	}
			
			
			
}