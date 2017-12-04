package br.com.delphos.sca.gruposInterfaces;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.util.JPAUtil;
import br.com.delphos.util.Validador;
@Stateless
public class InterfaceDAOBean extends AbstractDAOBean<Interface, Long> implements InterfaceDAO {

	public InterfaceDAOBean() {
		super(Interface.class, Long.class);
	}

	@Override
	public Interface obterInterfacePorDescricao(String descricao) {
		Interface retorno = null;
		try {
			String query = " select i from Interface i where upper(i.descricao) like :descricao";
			retorno = (Interface) getEntityManager().createQuery(query).setParameter("descricao", descricao.toUpperCase()).getSingleResult();
		}catch (Exception e) {
		}
		return retorno;
	}

	@Override
	public void excluirPorIdentidade(Interface entidade) {
		String jpql = "delete from Interface o where o.descricao = :descricao";
		TypedQuery<Interface> query = getEntityManager().createQuery(jpql, Interface.class);
		query.setParameter(Interface_.descricao.getName(), entidade.getDescricao());
		query.executeUpdate();
	}

	@Override
	public Interface obterPorIdentidade(Interface entidade) {
		String jpql = "select o from Interface o where o.descricao = :descricao";
		TypedQuery<Interface> query = getEntityManager().createQuery(jpql, Interface.class);
		query.setParameter(Interface_.descricao.getName(), entidade.getDescricao());
		return JPAUtil.getSingleResult(query);
	}

	@Override
	public boolean isAutorizado(Long idUsuario, String uri) throws DelphosException {
		String jpql = "select case when (count(u) > 0) then true else false end"
				+ " from Interface i, GrupoInterface gi, GrupoUsuario gu, Usuario u"
				+ " where u member of gu.usuarios"
				+ " and gi member of gu.gruposInterface"
				+ " and i member of gi.interfaces"
				+ " and upper(i.descricao) = upper(:uri)"
				+ " and u.id = :idUsuario";
		Query query = getEntityManager().createQuery(jpql);
		query.setParameter("uri", uri);
		query.setParameter("idUsuario", idUsuario);
		return JPAUtil.getSingleResult(query, Boolean.class);
	}
	
	public List<Interface> listarInterfacePorDescricao(String descricaoInterface) {
		String jpql = "select i from Interface i where upper(i.descricao) like :descricaoInterface ";
		TypedQuery<Interface> query = getEntityManager().createQuery(jpql, Interface.class);
		query.setParameter("descricaoInterface", descricaoInterface.toUpperCase() +"%");
		return query.getResultList();
	}

	public List<Interface> listarInterfacePorCriterio(String descricaoInterface, String descricaoGrupoInterface) {
		Interface i=null;
		
		String jpql = "select i from Interface i " ;
				if (!Validador.vazio(descricaoGrupoInterface)) {
					jpql +=  "inner join fetch i.gruposInterface g ";
				}
				
				jpql += "where " ;
			
				if (!Validador.vazio(descricaoInterface)) {
					jpql += "upper(i.descricao) like :descricaoInterface ";
				}
				if (!Validador.vazio(descricaoInterface) && !Validador.vazio(descricaoGrupoInterface)) {
					jpql += " and ";
				}
				if (!Validador.vazio(descricaoGrupoInterface)) {
					jpql += "upper(g.descricao) like :descricaoGrupoInterface";
				}
				jpql += " order by i.descricao";
		TypedQuery<Interface> query = getEntityManager().createQuery(jpql, Interface.class);
		
		if (!Validador.vazio(descricaoInterface))
			query.setParameter("descricaoInterface",  descricaoInterface.toUpperCase() +"%");
		if (!Validador.vazio(descricaoGrupoInterface)){
			query.setParameter("descricaoGrupoInterface", descricaoGrupoInterface.toUpperCase()+"%");
		}
			
		return query.getResultList();
	}
		
}
