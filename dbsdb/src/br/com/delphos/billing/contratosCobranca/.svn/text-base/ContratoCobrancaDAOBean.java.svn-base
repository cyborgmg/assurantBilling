package br.com.delphos.billing.contratosCobranca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class ContratoCobrancaDAOBean
 */
@Stateless
public class ContratoCobrancaDAOBean extends AbstractDAO<ContratoCobranca> implements ContratoCobrancaDAO {
	
	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ContratoCobrancaDAOBean() {
		super(ContratoCobranca.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
    public ContratoCobranca obterPorIdentidade(ContratoCobranca entidade) {
        String jpql = "select o from ContratoCobranca o"
                + " where o.empresa.id = :empresa"
                + " and o.meioPagamento.id = :meioPagamento"
                + " and o.provedor.id = :provedor"
                + " and ("
                + "       (o.dataInicioVigencia = :dataInicioVigencia and o.dataFimVigencia = :dataFimVigencia)"
                + "       or ((o.dataInicioVigencia <= :dataFimVigencia) and (o.dataFimVigencia >= :dataInicioVigencia))"
                + "     )";
        TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
        query.setParameter(ContratoCobranca_.empresa.getName(), entidade.getEmpresa().getId());
        query.setParameter(ContratoCobranca_.meioPagamento.getName(), entidade.getMeioPagamento().getId());
        query.setParameter(ContratoCobranca_.provedor.getName(), entidade.getProvedor().getId());
        query.setParameter(ContratoCobranca_.dataInicioVigencia.getName(), entidade.getDataInicioVigencia());
        query.setParameter(ContratoCobranca_.dataFimVigencia.getName(), entidade.getDataFimVigencia());
        ContratoCobranca retorno = JpaUtils.getSingleResult(query);
        return retorno;
    }
	
	public List<ContratoCobranca> listarPorAdquirente(Adquirente adquirente) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " inner join fetch o.adquirentesBandeiras ab "
				+ " where ";
					jpql += "ab.adquirente.id = :adquirente ";
				TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
					query.setParameter("adquirente",  adquirente.getId());
		return query.getResultList();		
	}
	
	public List<ContratoCobranca> listarPorBandeira(Bandeira bandeira) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " inner join fetch o.adquirentesBandeiras ab "
				+ " where ";
		jpql += "ab.bandeira.id = :bandeira ";
		TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
		query.setParameter("bandeira",  bandeira.getId());
		return query.getResultList();		
	}
	
	public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " inner join fetch o.empresa e "
				+ " inner join fetch o.provedor p "
				+ " inner join fetch o.meioPagamento m "
				+ " where ";
				if (!Validador.vazio(codigoEmpresa)) {
					jpql += "upper(e.codigo) like :codigoEmpresa ";
				}
				if (!Validador.vazio(codigoProvedor)) {
					if (!Validador.vazio(codigoEmpresa)) {
						jpql += " and ";
					}
					jpql += "upper(p.codigoProvedor) like :codigoProvedor ";
				}
				if (!Validador.vazio(descricao)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor)) {
						jpql += " and ";
					}
					jpql += "upper(o.descricaoContrato) like :descricao ";
				}
				if (!Validador.vazio(codigoMeioPagamento)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor) 
							|| !Validador.vazio(descricao)) {
						jpql += " and ";
					}
					jpql += "upper(m.codigo) like :codigoMeioPagamento ";
				}
				if (!Validador.vazio(periodoDe)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor) 
							|| !Validador.vazio(descricao) || !Validador.vazio(codigoMeioPagamento)) {
						jpql += " and ";
					}
					jpql += " o.dataInicioVigencia > :periodoDe ";
				}
				if (!Validador.vazio(periodoAte)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor) 
							|| !Validador.vazio(descricao) || !Validador.vazio(codigoMeioPagamento)) {
						jpql += " and ";
					}
					jpql += " o.dataFimVigencia < :periodoAte ";
				}
				jpql += " order by o.descricaoContrato";
				
				TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
				if (!Validador.vazio(codigoEmpresa)) {
					query.setParameter("codigoEmpresa", codigoEmpresa.toUpperCase() + "%");
				}
				if (!Validador.vazio(codigoProvedor)) {
					query.setParameter("codigoProvedor", codigoProvedor.toUpperCase() + "%");
				}
				if (!Validador.vazio(codigoMeioPagamento)) {
					query.setParameter("codigoMeioPagamento", codigoMeioPagamento.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricao)) {
					query.setParameter("descricao", descricao.toUpperCase() + "%");
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
				}
				
				if (!Validador.vazio(periodoDe)) {
					query.setParameter("periodoDe", dataInicial);
				}
				if (!Validador.vazio(periodoAte)) {
					query.setParameter("periodoAte", dataFinal);
				}
		return query.getResultList();		
	}
	
	public List<ContratoCobranca> listarPorCriterioDescricao(String descricaoEmpresa, String descricaoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " inner join fetch o.empresa e "
				+ " inner join fetch o.provedor p "
				+ " inner join fetch o.meioPagamento m "
				+ " where ";
		if (!Validador.vazio(descricaoEmpresa)) {
			jpql += "upper(e.descricao) like :codigoEmpresa ";
		}
		if (!Validador.vazio(descricaoProvedor)) {
			if (!Validador.vazio(descricaoEmpresa)) {
				jpql += " and ";
			}
			jpql += "upper(p.descricaoProvedor) like :codigoProvedor ";
		}
		if (!Validador.vazio(descricao)) {
			if (!Validador.vazio(descricaoEmpresa) || !Validador.vazio(descricaoProvedor)) {
				jpql += " and ";
			}
			jpql += "upper(o.descricaoContrato) like :descricao ";
		}
		if (!Validador.vazio(codigoMeioPagamento)) {
			if (!Validador.vazio(descricaoEmpresa) || !Validador.vazio(descricaoProvedor) 
					|| !Validador.vazio(descricao)) {
				jpql += " and ";
			}
			jpql += "upper(m.codigo) like :codigoMeioPagamento ";
		}
		if (!Validador.vazio(periodoDe)) {
			if (!Validador.vazio(descricaoEmpresa) || !Validador.vazio(descricaoProvedor) 
					|| !Validador.vazio(descricao) || !Validador.vazio(codigoMeioPagamento)) {
				jpql += " and ";
			}
			jpql += " o.dataInicioVigencia > :periodoDe ";
		}
		if (!Validador.vazio(periodoAte)) {
			if (!Validador.vazio(descricaoEmpresa) || !Validador.vazio(descricaoProvedor) 
					|| !Validador.vazio(descricao) || !Validador.vazio(codigoMeioPagamento)) {
				jpql += " and ";
			}
			jpql += " o.dataFimVigencia < :periodoAte ";
		}
		TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
		if (!Validador.vazio(descricaoEmpresa)) {
			query.setParameter("codigoEmpresa", "%" + descricaoEmpresa.toUpperCase() + "%");
		}
		if (!Validador.vazio(descricaoProvedor)) {
			query.setParameter("codigoProvedor", "%" + descricaoProvedor.toUpperCase() + "%");
		}
		if (!Validador.vazio(codigoMeioPagamento)) {
			query.setParameter("codigoMeioPagamento", "%" + codigoMeioPagamento.toUpperCase() + "%");
		}
		if (!Validador.vazio(descricao)) {
			query.setParameter("descricao", "%" + descricao.toUpperCase() + "%");
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
		}
		
		if (!Validador.vazio(periodoDe)) {
			query.setParameter("periodoDe", dataInicial);
		}
		if (!Validador.vazio(periodoAte)) {
			query.setParameter("periodoAte", dataFinal);
		}
		return query.getResultList();		
	}
	
	public boolean existeContratoNoPeriodo(String codigoEmpresa, String codigoProvedor, String codigoMeioPagamento, String periodoDe, String periodoAte) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " inner join fetch o.empresa e "
				+ " inner join fetch o.provedor p "
				+ " inner join fetch o.meioPagamento m "
				+ " where ";
				if (!Validador.vazio(codigoEmpresa)) {
					jpql += "upper(e.codigo) like :codigoEmpresa ";
				}
				if (!Validador.vazio(codigoProvedor)) {
					if (!Validador.vazio(codigoEmpresa)) {
						jpql += " and ";
					}
					jpql += "upper(p.codigoProvedor) like :codigoProvedor ";
				}
				if (!Validador.vazio(codigoMeioPagamento)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor) 
							) {
						jpql += " and ";
					}
					jpql += "upper(m.codigo) like :codigoMeioPagamento ";
				}
				if (!Validador.vazio(periodoDe)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor) 
							|| !Validador.vazio(codigoMeioPagamento)) {
						jpql += " and ";
					}
					jpql += " (o.dataInicioVigencia > :periodoDe ";
				}
				if (!Validador.vazio(periodoAte)) {
					if (!Validador.vazio(codigoEmpresa) || !Validador.vazio(codigoProvedor) 
							|| !Validador.vazio(codigoMeioPagamento)) {
						jpql += " or ";
					}
					jpql += " o.dataFimVigencia < :periodoAte) ";
				}
				TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
				if (!Validador.vazio(codigoEmpresa)) {
					query.setParameter("codigoEmpresa", "%" + codigoEmpresa.toUpperCase() + "%");
				}
				if (!Validador.vazio(codigoProvedor)) {
					query.setParameter("codigoProvedor", "%" + codigoProvedor.toUpperCase() + "%");
				}
				if (!Validador.vazio(codigoMeioPagamento)) {
					query.setParameter("codigoMeioPagamento", "%" + codigoMeioPagamento.toUpperCase() + "%");
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
				}
				
				if (!Validador.vazio(periodoDe)) {
					query.setParameter("periodoDe", dataInicial);
				}
				if (!Validador.vazio(periodoAte)) {
					query.setParameter("periodoAte", dataFinal);
				}
		return query.getResultList().size() > 0;		
	}
	
	@Override
	public boolean isExcluivelPorId(Long idContratoCobranca) throws DLOException {
		String jpql = "select case when count(o) > 0 then true else false end "
				+ " from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " where o.id = :id"
				+ " and size(o.vendas) = 0"
				+ " and size(o.cobrancas) = 0"
				+ " and size(o.produtos) = 0";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(ContratoCobranca_.id.getName(), idContratoCobranca);
		return JpaUtils.getSingleResult(query, Boolean.class);
	}

	@Override
	public boolean existeContratoNoPeriodo(ContratoCobranca contratoCobranca) {
		Long id = contratoCobranca.getId();
		String jpql = "select case when count(o) > 0 then true else false end from ContratoCobranca o"
                + " where o.empresa.id = :empresa"
                + " and o.meioPagamento.id = :meioPagamento"
                + " and o.provedor.id = :provedor"
                + " and ("
                + "       (o.dataInicioVigencia <= :dataFimVigencia) and (o.dataFimVigencia >= :dataInicioVigencia)"
                + "     )";
        if (id != null) {
        	jpql += " and o.id != :id";
        }
		Query query = getEntityManager().createQuery(jpql);
		if (id != null) {
			query.setParameter("id", id);
		}
        query.setParameter(ContratoCobranca_.empresa.getName(), contratoCobranca.getEmpresa().getId());
        query.setParameter(ContratoCobranca_.meioPagamento.getName(), contratoCobranca.getMeioPagamento().getId());
        query.setParameter(ContratoCobranca_.provedor.getName(), contratoCobranca.getProvedor().getId());
        query.setParameter(ContratoCobranca_.dataInicioVigencia.getName(), contratoCobranca.getDataInicioVigencia());
        query.setParameter(ContratoCobranca_.dataFimVigencia.getName(), contratoCobranca.getDataFimVigencia());
        boolean retorno = JpaUtils.getSingleResult(query, Boolean.class);
		return retorno;
	}

	public long contarRelacionamentos(ContratoCobranca contratoCobranca, String nomePropriedade) {
		String jpql = "select size(o." + nomePropriedade + ") from " + JpaUtils.getNomeEntidade(ContratoCobranca.class) + " o"
				+ " where o.id = :id";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter(ContratoCobranca_.id.getName(), contratoCobranca.getId());
        long retorno = JpaUtils.getSingleResult(query, Integer.class);
		return retorno;
	}
}
