package br.com.delphos.sca.usuarios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.util.JPAUtil;
import br.com.delphos.util.Validador;

@Stateless
public class GrupoUsuarioDAOBean extends AbstractDAOBean<GrupoUsuario, Long> implements
		GrupoUsuarioDAO {

	public GrupoUsuarioDAOBean() {
		super(GrupoUsuario.class, Long.class);
	}

	@Override
	public void excluirPorIdentidade(GrupoUsuario entidade) {
		String jpql = "delete from GrupoUsuario o where o.descricao = :descricao";
		TypedQuery<GrupoUsuario> query = getEntityManager().createQuery(jpql, GrupoUsuario.class);
		query.setParameter(GrupoUsuario_.descricao.getName(), entidade.getDescricao());
		query.executeUpdate();
	}

	@Override
	public GrupoUsuario obterPorIdentidade(GrupoUsuario entidade) {
		String jpql = "select o from GrupoUsuario o where o.descricao = :descricao";
		TypedQuery<GrupoUsuario> query = getEntityManager().createQuery(jpql, GrupoUsuario.class);
		query.setParameter(GrupoUsuario_.descricao.getName(), entidade.getDescricao());
		return JPAUtil.getSingleResult(query);
	}
	
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idUsuario) {
		
		String jpql = "select gu from GrupoUsuario gu, "
				+ "Usuario u "
				+ "where u not member of gu.usuarios "
				+ "and u.id = :idUsuario";
		TypedQuery<GrupoUsuario> query = getEntityManager().createQuery(jpql, GrupoUsuario.class);
		if (!Validador.vazio(idUsuario))
			query.setParameter("idUsuario", Long.valueOf(idUsuario));
		return query.getResultList();
	}
	
	public List<Usuario> obterUsuariosSemAssociacao(String idGrupoUsuario) {
		
		String jpql = "select u from Usuario u, "
				+ "GrupoUsuario gu "
				+ "where u not member of gu.usuarios "
				+ "and gu.id = :idGrupoUsuario";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		if (!Validador.vazio(idGrupoUsuario))
			query.setParameter("idGrupoUsuario", Long.valueOf(idGrupoUsuario));
		return query.getResultList();
	}
	
	public List<GrupoUsuario> listarPorCriterio (String tipoGrupo, String descricaoGrupoUsuario) {
		String jpql = "select gu from GrupoUsuario gu " ;
				jpql += " where ";
				if (!Validador.vazio(tipoGrupo)) {
					jpql += "upper(gu.tipoGrupo) like :tipoGrupo ";
				}
				if (!Validador.vazio(descricaoGrupoUsuario)) {
					if (!Validador.vazio(tipoGrupo)) {
						jpql += " and ";
					}
					jpql += " upper(gu.descricao) like :descricaoGrupoUsuario ";
				}
				TypedQuery<GrupoUsuario> query = getEntityManager().createQuery(jpql, GrupoUsuario.class);
				if (!Validador.vazio(tipoGrupo)) {
					query.setParameter("tipoGrupo", tipoGrupo.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricaoGrupoUsuario)) {
					query.setParameter("descricaoGrupoUsuario", descricaoGrupoUsuario.toUpperCase() + "%");
				}
		return query.getResultList();	
	}
	
}
