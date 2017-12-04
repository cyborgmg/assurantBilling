package br.com.delphos.sca.gruposInterfaces;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.util.JPAUtil;
import br.com.delphos.util.Validador;
@Stateless
public class GrupoInterfaceDAOBean extends AbstractDAOBean<GrupoInterface, Long> implements
		GrupoInterfaceDAO {

	public GrupoInterfaceDAOBean() {
		super(GrupoInterface.class, Long.class);
	}

	@Override
	public void excluirPorIdentidade(GrupoInterface entidade) {
		String jpql = "delete from GrupoInterface o where o.descricao = :descricao";
		TypedQuery<GrupoInterface> query = getEntityManager().createQuery(jpql, GrupoInterface.class);
		query.setParameter(GrupoInterface_.descricao.getName(), entidade.getDescricao());
		query.executeUpdate();
	}

	@Override
	public GrupoInterface obterPorIdentidade(GrupoInterface entidade) {
		String jpql = "select o from GrupoInterface o where o.descricao = :descricao";
		TypedQuery<GrupoInterface> query = getEntityManager().createQuery(jpql, GrupoInterface.class);
		query.setParameter(GrupoInterface_.descricao.getName(), entidade.getDescricao());
		return JPAUtil.getSingleResult(query);
	}
	
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacao(Long idGrupoUsuario) {
		
		String jpql = "select gi from GrupoInterface gi, "
				+ "GrupoUsuario gu "
				+ "where gi not member of gu.gruposInterface "
				+ "and gu.id = :idGrupoUsuario";
		TypedQuery<GrupoInterface> query = getEntityManager().createQuery(jpql, GrupoInterface.class);
		query.setParameter("idGrupoUsuario", idGrupoUsuario);
		return query.getResultList();
	}
	
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacaoComInterface(Long interfac) {
		
		String jpql = "select gi from GrupoInterface gi, "
				+ "Interface i "
				+ "where gi not member of i.gruposInterface "
				+ "and i.id = :idInterface";
		TypedQuery<GrupoInterface> query = getEntityManager().createQuery(jpql, GrupoInterface.class);
		query.setParameter("idInterface", interfac);
		return query.getResultList();
	}
	
	public List<GrupoInterface> listarPorCriterio (String descricaoGrupoUsuario) {
		String jpql = "select gi from GrupoInterface gi " ;
				jpql += " where ";
				if (!Validador.vazio(descricaoGrupoUsuario)) {
					jpql += "upper(gi.descricao) like :descricao ";
				}
				TypedQuery<GrupoInterface> query = getEntityManager().createQuery(jpql, GrupoInterface.class);
				if (!Validador.vazio(descricaoGrupoUsuario)) {
					query.setParameter("descricao", descricaoGrupoUsuario.toUpperCase() + "%");
				}
		return query.getResultList();	
	}
	
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idGrupoInterface) {
		
		String jpql = "select gu from GrupoUsuario gu, "
				+ "GrupoInterface gi "
				+ "where gi not member of gu.gruposInterface "
				+ "and gi.id = :idGrupoInterface";
		TypedQuery<GrupoUsuario> query = getEntityManager().createQuery(jpql, GrupoUsuario.class);
		if (!Validador.vazio(idGrupoInterface))
			query.setParameter("idGrupoInterface", Long.valueOf(idGrupoInterface));
		return query.getResultList();
	}
}
