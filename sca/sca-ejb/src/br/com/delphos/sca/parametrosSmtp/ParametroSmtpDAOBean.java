package br.com.delphos.sca.parametrosSmtp;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.util.JPAUtil;
@Stateless
public class ParametroSmtpDAOBean extends AbstractDAOBean<ParametroSmtp, Long> implements ParametroSmtpDAO {

	public ParametroSmtpDAOBean() {
		super(ParametroSmtp.class, Long.class);
	}

	@Override
	public ParametroSmtp obterPorIdentidade(ParametroSmtp entidade) {
		ParametroSmtp retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from ParametroSmtp o"
					+ " where o.id = :id";
			TypedQuery<ParametroSmtp> query = getEntityManager().createQuery(jpql, ParametroSmtp.class);
			query.setParameter(ParametroSmtp_.id.getName(), entidade.getId());
			retorno = JPAUtil.getSingleResult(query);
		}
		return retorno;
	}

	@Override
	public ParametroSmtp obterUltimosParametros() {
//		String jpql = "select first(o) from ParametroSmtp o"
		String jpql = "select o from ParametroSmtp o"
				+ " order by o.id desc";
		TypedQuery<ParametroSmtp> query = getEntityManager().createQuery(jpql, ParametroSmtp.class);
		query.setMaxResults(1);
		return JPAUtil.getSingleResult(query);
	}

	@Override
	public void excluirPorIdentidade(ParametroSmtp entidade) {
		// TODO Auto-generated method stub
		
	}
	
}
