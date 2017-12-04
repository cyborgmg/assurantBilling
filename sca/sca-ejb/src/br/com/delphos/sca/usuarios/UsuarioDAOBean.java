package br.com.delphos.sca.usuarios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.util.JPAUtil;
import br.com.delphos.util.Validador;

@Stateless
public class UsuarioDAOBean extends AbstractDAOBean<Usuario, Long>implements UsuarioDAO {

	public UsuarioDAOBean() {
		super(Usuario.class, Long.class);
	}

	@Override
	public List<Usuario> listarUsuariosAtivos() {
		String jpql = "select u from Usuario u "
				+ " where u.dataExpiracao is null"
				+ " order by u.descricao";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		List<Usuario> retorno = query.getResultList();
		return retorno;
	}

	@Override
	public List<Usuario> listarPorParteDescricao(String criterio) {
		criterio = criterio.toUpperCase().replaceAll("%", "\\%").replaceAll("_", "\\_");
		String jpql = " select u from Usuario u "
				+ " where upper(u.descricao) like :criterio "
				+ " order by u.descricao";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter("criterio", criterio + "%");
		List<Usuario> retorno = query.getResultList();
		return retorno;
	}

	@Override
	public void excluirPorIdentidade(Usuario entidade) {
		String jpql = "delete from Usuario o where upper(o.sigla) = :sigla";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter(Usuario_.codigo.getName(), entidade.getCodigo().toUpperCase());
		query.executeUpdate();
	}

	@Override
	public Usuario obterPorIdentidade(Usuario entidade) {
		String jpql = "select o from Usuario o where upper(o.codigo) = :codigo";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter(Usuario_.codigo.getName(), entidade.getCodigo().toUpperCase());
		return JPAUtil.getSingleResult(query);
	}
	
	@Override
	public Usuario obterPorToken(String token) {
		String jpql = "select o from Usuario o where o.token = :token";
		TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
		query.setParameter("token", token);
		return JPAUtil.getSingleResult(query);
	}
	
	public List<Usuario> listarPorCriterio (String codigoUsuario, String descricaoUsuario, String descricaoEmpresa) {
		String jpql = "select o from Usuario o" ;
				if (!Validador.vazio(descricaoEmpresa)) {
					jpql += " inner join fetch o.empresas e ";
				}
				jpql += " where ";
				if (!Validador.vazio(codigoUsuario)) {
					jpql += "upper(o.codigo) like :codigo ";
				}
				if (!Validador.vazio(descricaoUsuario)) {
					if (!Validador.vazio(codigoUsuario)) {
						jpql += " and ";
					}
					jpql += " upper(o.descricao) like :descricao ";
				}
				if (!Validador.vazio(descricaoEmpresa)) {
					if (!Validador.vazio(codigoUsuario) || !Validador.vazio(descricaoUsuario)) {
						jpql += " and ";
					}
					jpql += " upper(e.descricao) like :empresa ";
				}
				TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
				if (!Validador.vazio(codigoUsuario)) {
					
					query.setParameter("codigo", codigoUsuario.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricaoUsuario)) {
					query.setParameter("descricao", descricaoUsuario.toUpperCase() + "%");
				}
				if (!Validador.vazio(descricaoEmpresa)) {
					query.setParameter("empresa", descricaoEmpresa + "%");
				}

		return query.getResultList();	
	}
}
