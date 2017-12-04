package br.com.delphos.billing.produtos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class ProdutoDAOBean
 */
@Stateless
public class ProdutoDAOBean extends AbstractDAO<Produto> implements ProdutoDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ProdutoDAOBean() {
		super(Produto.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Produto obterPorIdentidade(Produto entidade) {
		String jpql = "select o from Produto o"
				+ " where o.codigo = :codigo"
				+ " and o.empresa.id = :empresa";
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		query.setParameter(Produto_.codigo.getName(), entidade.getCodigo());
		query.setParameter(Produto_.empresa.getName(), entidade.getEmpresa().getId());
		Produto retorno = JpaUtils.getSingleResult(query);
		return retorno;
	}

	@Override
	public Produto obterPorEmpresaCodigo(Empresa empresa, String codigoProduto) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Produto.class) + " o"
				+ " where o.empresa = :empresa"
				+ " and o.codigoProduto = :codigoProduto";
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		query.setParameter("empresa", empresa);
		query.setParameter("codigoProduto", codigoProduto);
		return JpaUtils.getSingleResult(query);
	}
	
	@Override
	public List<Produto> listarPorCodigo(String codigo) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Produto.class) + " o"
				+ " where o.codigo = :codigo";
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		query.setParameter("codigo", codigo);
		return query.getResultList();
	}
	
	public List<Produto> listarProdutosPorCriterio(String codigoProduto, String descricaoProduto, String codigoEmpresa) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(Produto.class) + " o "
				+ " inner join fetch o.empresa e "
		+ " where ";
		if (!Validador.vazio(codigoProduto)) {
			jpql += "upper(o.codigo) like :codigo ";
		}
		if (!Validador.vazio(descricaoProduto)) {
			if (!Validador.vazio(codigoProduto)) {
				jpql += " and ";
			}
			jpql += " upper(o.descricao) like :descricao ";
		}
		if (!Validador.vazio(codigoEmpresa)) {
			if (!Validador.vazio(codigoProduto) || !Validador.vazio(descricaoProduto)) {
				jpql += " and ";
			}
			jpql += " upper(e.codigo) like :empresa ";
		}
		jpql += " order by o.descricao";
		
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		if (!Validador.vazio(codigoProduto)) {
			query.setParameter("codigo", codigoProduto.toUpperCase() + "%");
		}
		if (!Validador.vazio(descricaoProduto)) {
			query.setParameter("descricao", descricaoProduto.toUpperCase() + "%");
		}
		if (!Validador.vazio(codigoEmpresa)) {
			query.setParameter("empresa", codigoEmpresa.toUpperCase() + "%");
		}
		return query.getResultList();
	}
	
	@Override
	public Produto obterPorCodigo(String codigo) {
		String jpql = "select p from " + JpaUtils.getNomeEntidade(Produto.class) + " p where p.codigo = :codigo";
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		query.setParameter("codigo", codigo);
		return JpaUtils.getSingleResult(query);
	}
	
	public List<Produto> obterProdutosSemAssociacao() {
		String jpql = "select p from " + JpaUtils.getNomeEntidade(Produto.class) + " p "
				+ "where p.empresa = null";
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		return query.getResultList();
	}
	
	public List<Produto> obterProdutosPorEmpresa(String codigoEmpresa) {
		String jpql = "select p from " + JpaUtils.getNomeEntidade(Produto.class) + " p "
				+ "where p.empresa.codigo = :codigoEmpresa";
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		query.setParameter("codigoEmpresa", codigoEmpresa);
		return query.getResultList();		
	}
	
	public List<Produto> obterProdutosNaoRelacionadosAoContrato(String idContratoCobranca) {
//		String jpql = "select p from " + JpaUtils.getNomeEntidade(Produto.class) + " p, "
//				+ JpaUtils.getNomeEntidade(ContratoCobranca.class) + " cc "
//				+ " left join cc.produtos produtosContrato "
//				//+ "where p.id <> produtosContrato.id "
//				+ " where produtosContrato.id not in (select p.id from "+JpaUtils.getNomeEntidade(Produto.class)+" p) "
//				+ "and cc.id = :idContratoCobranca";
		
		//JpaUtils.getNomeEntidade(Produto.class) + " p, "
		
//		String jpql = "select cc.produtos from "  
//		+ JpaUtils.getNomeEntidade(ContratoCobranca.class) + " cc "
//		+ " inner join cc.produtos produtosContrato "
//		+ " where produtosContrato.id not in (select p.id from "+JpaUtils.getNomeEntidade(Produto.class)+" p) "
//		+ " and cc.id = :idContratoCobranca";
		
		String jpql2 = "select prod from " + JpaUtils.getNomeEntidade(Produto.class) + " prod "
				+ "where prod.id not in ("
				+ "select p.id from " + JpaUtils.getNomeEntidade(Produto.class) + " p, "
				+ JpaUtils.getNomeEntidade(ContratoCobranca.class) + " cc "
				+ " left join cc.produtos produtosContrato "
				+ "where p.id = produtosContrato.id "
				//+ " where produtosContrato.id not in (select p.id from "+JpaUtils.getNomeEntidade(Produto.class)+" p) "
				+ "and cc.id = :idContratoCobranca"
				+ ") ";
		
		// (select prod.id from "+JpaUtils.getNomeEntidade(Produto.class) + " prod )
				
//		TypedQuery<Produto> query = getEntityManager().createQuery(jpql, Produto.class);
		TypedQuery<Produto> query = getEntityManager().createQuery(jpql2, Produto.class);
		query.setParameter("idContratoCobranca", Long.valueOf(idContratoCobranca));
		return query.getResultList();		
	}
	
	public void associarProdutosEmpresa(String csvProdutos, Empresa empresa) {
		String jpql = "update " + JpaUtils.getNomeEntidade(Produto.class) + " obj  "
				+ "set  obj.empresa =:empresa "
				+ "where obj.id = :idEntidadeOperacao";
		Query query =		getEntityManager().createQuery(jpql);

		String[] arrProdutos = csvProdutos.split(",");
		
		for (int i = 0; i < arrProdutos.length; i++) {
			if (arrProdutos[i].trim().length() > 0) {
				
				query.setParameter("empresa",empresa);
				query.setParameter("idEntidadeOperacao",Long.valueOf(arrProdutos[i]));
				query.executeUpdate();				
			}
		}
		
	}
	
}
