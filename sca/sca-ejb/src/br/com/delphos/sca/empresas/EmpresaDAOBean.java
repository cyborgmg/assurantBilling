package br.com.delphos.sca.empresas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.delphos.persistencia.AbstractDAOBean;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.util.JPAUtil;
import br.com.delphos.util.Validador;

@Stateless
public class EmpresaDAOBean extends AbstractDAOBean<Empresa, Long> implements EmpresaDAO {

	public EmpresaDAOBean() {
		super(Empresa.class, Long.class);
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> obterEmpresasUsuario(Usuario usuario) {

		List<Empresa> empresas = null;

		try {
			String jpql = "SELECT e FROM Usuario u, IN ( u.empresas ) e where u.codigo =:codigo and u.token =:token";
			Query query = getEntityManager().createQuery(jpql);
			query.setParameter("codigo", usuario.getCodigo());
			query.setParameter("token", usuario.getToken());

			empresas = query.getResultList();

			for (Empresa empresa : empresas) {
				empresa.getSistemas().size();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return empresas;
	}

	@Override
	public void excluirPorIdentidade(Empresa entidade) {
		String jpql = "delete from Empresa o where o.codigo = :codigo";
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		query.setParameter(Empresa_.codigo.getName(), entidade.getCodigo());
		query.executeUpdate();
	}

	@Override
	public Empresa obterPorIdentidade(Empresa entidade) {
		String jpql = "select o from Empresa o where o.codigo = :codigo";
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		query.setParameter(Empresa_.codigo.getName(), entidade.getCodigo());
		return JPAUtil.getSingleResult(query);
	}
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa) {
		String jpql = "select o from Empresa o where " ;
				if (!Validador.vazio(codigoEmpresa)) {
					jpql += "upper(o.codigo) like :codigoEmpresa ";
				}
				if (!Validador.vazio(codigoEmpresa) && !Validador.vazio(descricaoEmpresa)) {
					jpql += " and ";
				}
				if (!Validador.vazio(descricaoEmpresa)) {
					jpql += "upper(o.descricao) like :descricaoEmpresa";
				}
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		if (!Validador.vazio(codigoEmpresa))
			query.setParameter("codigoEmpresa", codigoEmpresa.toUpperCase() +"%");
		if (!Validador.vazio(descricaoEmpresa))
			query.setParameter("descricaoEmpresa", descricaoEmpresa.toUpperCase()+"%");
		return query.getResultList();
	}
	
	public List<Empresa> obterEmpresasSemAssociacao(String idUsuario) {
		
/*		String jpql = "select e from Empresa e "
				+ " inner join fetch e.usuarios u "
				+ "where u.id NOT IN ("
				
				+ "select u.id from Usuario u "
				+ "where u.id = :idUsuario"
				
				+ ") ";*/
		String jpql = "select e from Empresa e, "
				+ "Usuario u "
				+ "where u not member of e.usuarios "
				+ "and u.id = :idUsuario";
		TypedQuery<Empresa> query = getEntityManager().createQuery(jpql, Empresa.class);
		if (!Validador.vazio(idUsuario))
			query.setParameter("idUsuario", Long.valueOf(idUsuario));
		return query.getResultList();
	}

}
