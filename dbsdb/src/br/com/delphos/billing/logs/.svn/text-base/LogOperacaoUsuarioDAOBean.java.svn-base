package br.com.delphos.billing.logs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.StringUtils;
import br.com.delphos.billing.util.Validador;


/**
 * Session Bean implementation class LogOperacaoUsuarioDAOBean
 * @param <T>
 */
@Stateless
public class LogOperacaoUsuarioDAOBean<T> extends AbstractDAO<LogOperacaoUsuario> implements LogOperacaoUsuarioDAO {

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;

	public LogOperacaoUsuarioDAOBean() {
		super(LogOperacaoUsuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public LogOperacaoUsuario obterPorIdentidade(LogOperacaoUsuario entidade) {
		LogOperacaoUsuario retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from LogOperacaoUsuario o"
					+ " where o.id = :id";
			TypedQuery<LogOperacaoUsuario> query = getEntityManager().createQuery(jpql, LogOperacaoUsuario.class);
			query.setParameter(LogOperacaoUsuario_.id.getName(), entidade.getId());
			return JpaUtils.getSingleResult(query);	
		}
		return retorno;
	}

	@Override
	public void registrarLogOperacoes(LogOperacaoUsuario logOperacaoUsuario,TipoOperacaoUsuario tipoOperacaoUsuario,Class classeEntidadeOperacaoUsuario) throws ParseException {
		
		logOperacaoUsuario.setTabela(EntidadeUtils.getNomeTabela(entityManager, classeEntidadeOperacaoUsuario));
		
		Calendar calendarioCorrente = DateUtils.getCalendarDataHoraAtual();;
		Date dataHoraCorrente = calendarioCorrente.getTime();

		logOperacaoUsuario.setData(dataHoraCorrente);
		//logOperacaoUsuario.setStatus(statusLogOperacaoUsuario.getValor());
		logOperacaoUsuario.setOperacao(tipoOperacaoUsuario.getValor());

		incluir(logOperacaoUsuario);
		
//		alterar(classeEntidadeOperacaoUsuario,logOperacaoUsuario);

	}

	private int alterar(Class clazz,LogOperacaoUsuario logOperacaoUsuario){
		String jpql = "update " + JpaUtils.getNomeEntidade(clazz) + " obj  set  obj.dataUltimaAlteracao =:dataUltimaAlteracao , obj.usuario=:usuario  where obj.id = :idEntidadeOperacao";
		Query query =		getEntityManager().createQuery(jpql);
		query.setParameter("dataUltimaAlteracao",logOperacaoUsuario.getData());
		query.setParameter("usuario"            ,logOperacaoUsuario.getUsuario());
		query.setParameter("idEntidadeOperacao" ,logOperacaoUsuario.getIdObjeto());
		return  query.executeUpdate();

	}
	
	public List<LogOperacaoUsuario> listarPorCriterio(String tipoOperacao, String tipoObjetoOperacao, String identificadorObjetoOperacao,
			String usuario, String periodoDe, String periodoAte) {
		
		String jpql = "select o from " + JpaUtils.getNomeEntidade(LogOperacaoUsuario.class) + " o"
				+ " where ";
				if (!Validador.vazio(tipoOperacao)) {
					jpql += "upper(o.operacao) like :operacao ";
				}
				if (!Validador.vazio(tipoObjetoOperacao)) {
					if (!Validador.vazio(tipoOperacao)) {
						jpql += " and ";
					}
					jpql += " upper(o.tabela) = :tabela ";
				}
				if (!Validador.vazio(identificadorObjetoOperacao)) {
					if (!Validador.vazio(tipoOperacao) || !Validador.vazio(tipoObjetoOperacao)) {
						jpql += " and ";
					}
					jpql += " o.descricaoObjeto like :idObjeto ";
				}
				if (!Validador.vazio(usuario)) {
					if (!Validador.vazio(tipoOperacao) || !Validador.vazio(tipoObjetoOperacao) ||
							!Validador.vazio(identificadorObjetoOperacao)) {
						jpql += " and ";
					}
					jpql += " o.descricaoUsuario like :usuario ";
				}
				if (!Validador.vazio(periodoDe) && !Validador.vazio(periodoAte)) {
					if (!Validador.vazio(tipoOperacao) || !Validador.vazio(tipoObjetoOperacao) ||
							!Validador.vazio(identificadorObjetoOperacao) || !Validador.vazio(usuario)) {
						jpql += " and ";
					}
					jpql += " o.data > :periodoDe ";
					if (!Validador.vazio(tipoOperacao) || !Validador.vazio(tipoObjetoOperacao) ||
							!Validador.vazio(identificadorObjetoOperacao) || !Validador.vazio(usuario)
							|| !Validador.vazio(periodoDe)) {
						jpql += " and ";
					}
					jpql += " o.data < :periodoAte ";
				}
				
				jpql += " order by tabela, o.data ";
				
				TypedQuery<LogOperacaoUsuario> query = getEntityManager().createQuery(jpql, LogOperacaoUsuario.class);
				if (!Validador.vazio(tipoOperacao)) {
					query.setParameter("operacao", tipoOperacao.toUpperCase() + "%");
				}
				if (!Validador.vazio(tipoObjetoOperacao)) {
					tipoObjetoOperacao = StringUtils.capitalsToUnderscore(tipoObjetoOperacao).substring(1);
					query.setParameter("tabela", tipoObjetoOperacao.toUpperCase());
				}
				if (!Validador.vazio(identificadorObjetoOperacao)) {
					query.setParameter("idObjeto", identificadorObjetoOperacao.toUpperCase() + "%");
				}
				if (!Validador.vazio(usuario)) {
					query.setParameter("usuario", usuario.toUpperCase() + "%" );
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
