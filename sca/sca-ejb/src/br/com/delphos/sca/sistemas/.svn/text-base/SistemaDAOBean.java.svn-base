package br.com.delphos.sca.sistemas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.util.JPAUtil;
import br.com.delphos.util.Validador;
@Stateless
public class SistemaDAOBean extends AbstractDAOBean<Sistema, Long> implements SistemaDAO {

	public SistemaDAOBean() {
		super(Sistema.class, Long.class);
	}

	@Override
	public void excluirPorIdentidade(Sistema entidade) {
		String jpql = "delete from Sistema o where o.sigla = :sigla";
		TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
		query.setParameter(Sistema_.sigla.getName(), entidade.getSigla());
		query.executeUpdate();
	}

	@Override
	public Sistema obterPorIdentidade(Sistema entidade) {
		String jpql = "select o from Sistema o where o.sigla = :sigla";
		TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
		query.setParameter(Sistema_.sigla.getName(), entidade.getSigla());
		return JPAUtil.getSingleResult(query);
	}
	
	public List<Sistema> listarPorCriterio(String siglaSistema, String descricaoSistema, String idEmpresa) {
		String jpql = "select o from Sistema o"
				+ " inner join fetch o.empresa e "
				+ " where ";
				if (!Validador.vazio(siglaSistema)) {
					jpql += "upper(o.sigla) like :sigla ";
				}
				if (!Validador.vazio(descricaoSistema)) {
					if (!Validador.vazio(siglaSistema)) {
						jpql += " and ";
					}
					jpql += " upper(o.descricao) like :descricao ";
				}
				if (!Validador.vazio(idEmpresa)) {
					if (!Validador.vazio(siglaSistema) || !Validador.vazio(descricaoSistema)) {
						jpql += " and ";
					}
					jpql += " upper(e.id) = :empresa ";
				}
				TypedQuery<Sistema> query = getEntityManager().createQuery(jpql, Sistema.class);
				if (!Validador.vazio(siglaSistema)) {
					query.setParameter("sigla",  siglaSistema.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricaoSistema)) {
					query.setParameter("descricao",  descricaoSistema.toUpperCase() + "%");
				}
				if (!Validador.vazio(idEmpresa)) {
					query.setParameter("empresa", Long.valueOf(idEmpresa));
				}
		return query.getResultList();		
	}
}
