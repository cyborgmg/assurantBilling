package br.com.delphos.billing.conciliacoes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;
@Stateless
public class ConciliacaoInterfaceDAOBean extends AbstractDAO<ConciliacaoInterface> implements ConciliacaoInterfaceDAO {


	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ConciliacaoInterfaceDAOBean() {
		super(ConciliacaoInterface.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public ConciliacaoInterface obterPorIdentidade(ConciliacaoInterface entidade) {
		ConciliacaoInterface retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from ConciliacaoInterface o"
					+ " where o.id = :id";
			TypedQuery<ConciliacaoInterface> query = getEntityManager().createQuery(jpql, ConciliacaoInterface.class);
			query.setParameter(ConciliacaoInterface_.id.getName(), entidade.getId());
			return JpaUtils.getSingleResult(query);	
		}
		return retorno;
	}
	
	
	public ConciliacaoInterface obterPorCobranca(Cobranca c) {
		ConciliacaoInterface conciliacaoInterface = null;
		TypedQuery<ConciliacaoInterface> query =  null;
		
				String jpql = "select ci from " + JpaUtils.getNomeEntidade(ConciliacaoInterface.class) + " ci where ci.idecobr=:idcob";
				
				query =		getEntityManager().createQuery(jpql, ConciliacaoInterface.class);
				query.setParameter("idcob",new BigDecimal(c.getId()));
				
				List<ConciliacaoInterface> list	=(List<ConciliacaoInterface>) query.getResultList();
				if(!list.isEmpty())
					return list.get(0);
				else
					return null;
	}

	public List<ConciliacaoInterface> listarPorCriterio(String codigo, String periodoDe, String periodoAte) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ConciliacaoInterface.class) + " o"
				+ " where ";
				if (!Validador.vazio(codigo)) {
					jpql += "upper(o.codigoAfiliacao) like :codigo ";
				}
//				if (!Validador.vazio(descricao)) {
//					if (!Validador.vazio(codigo)) {
//						jpql += " and ";
//					}
//					jpql += " upper(o.descricao) like :descricao ";
//				}
				if (!Validador.vazio(periodoDe) && !Validador.vazio(periodoAte)) {
					if (!Validador.vazio(codigo)) {
						jpql += " and ";
					}
					jpql += " o.dataProcessamento > :periodoDe ";
					if (!Validador.vazio(codigo)
							|| !Validador.vazio(periodoDe)) {
						jpql += " and ";
					}
					jpql += " o.dataProcessamento < :periodoAte ";
				}
				jpql += " order by o.dataAutorizacao";
				
				TypedQuery<ConciliacaoInterface> query = getEntityManager().createQuery(jpql, ConciliacaoInterface.class);
				if (!Validador.vazio(codigo)) {
					query.setParameter("codigo", codigo.toUpperCase() + "%");
				}
				
				SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
				sdf.setLenient(false);

				Date dataInicial=null;
				Date dataFinal=null;

				if(!"".equals(periodoDe)&&!"".equals(periodoAte)){
					try {
						dataInicial= sdf.parse(periodoDe); 
						dataFinal  = sdf.parse(periodoAte);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					query.setParameter("periodoDe", dataInicial);
					query.setParameter("periodoAte", DateUtils.horaParaMeiaNoite(dataFinal));
				}
		return query.getResultList();		
	}
	
}
