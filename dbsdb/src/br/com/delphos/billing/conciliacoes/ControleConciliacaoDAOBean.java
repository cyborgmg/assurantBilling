package br.com.delphos.billing.conciliacoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.adquirentes.Adquirente_;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.persistencia.AbstractDAO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.JpaUtils;
import br.com.delphos.billing.util.Validador;

@Stateless
public class ControleConciliacaoDAOBean extends AbstractDAO<ControleConciliacao> implements ControleConciliacaoDAO{

	@PersistenceContext(unitName = "dbs-PU")
	private EntityManager entityManager;
	
	public ControleConciliacaoDAOBean() {
		super(ControleConciliacao.class);
	}
	
	@Override
	public ControleConciliacao obterPorIdentidade(ControleConciliacao entidade) {
		ControleConciliacao retorno = null;
		if (entidade.getId() != null) {
			String jpql = "select o from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " o"
					+ " where o.adquirente = :adquirente"
					+ " and o.dataMovimento = :dataMovimento";
			TypedQuery<ControleConciliacao> query = getEntityManager().createQuery(jpql, ControleConciliacao.class);
			query.setParameter(ControleConciliacao_.adquirente.getName(), entidade.getAdquirente());
			query.setParameter(ControleConciliacao_.dataMovimento.getName(), entidade.getDataMovimento());
			retorno = JpaUtils.getSingleResult(query);	
		}
		return retorno;
	}
	
	public List<ControleConciliacao> listarPorCriterio(String nomeAdquirente, String periodoDe, String periodoAte) {
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " o"
				+ " where ";
				if (!Validador.vazio(nomeAdquirente)) {
					jpql += "upper(o.adquirente) = :adquirente ";
				}
//				if (!Validador.vazio(descricao)) {
//					if (!Validador.vazio(codigo)) {
//						jpql += " and ";
//					}
//					jpql += " upper(o.descricao) like :descricao ";
//				}
				if (!Validador.vazio(periodoDe) && !Validador.vazio(periodoAte)) {
					if (!Validador.vazio(nomeAdquirente)) {
						jpql += " and ";
					}
					jpql += " o.dataMovimento >= :periodoDe ";
					if (!Validador.vazio(nomeAdquirente)
							|| !Validador.vazio(periodoDe)) {
						jpql += " and ";
					}
					jpql += " o.dataMovimento <= :periodoAte ";
				}
				jpql += " order by o.dataMovimento desc";
				
				TypedQuery<ControleConciliacao> query = getEntityManager().createQuery(jpql, ControleConciliacao.class);
				if (!Validador.vazio(nomeAdquirente)) {
					query.setParameter("adquirente", nomeAdquirente.toUpperCase());
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

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public ControleConciliacao buscarPorAdquirenteDataMovimento(Adquirente adquirente, Date dataMovimento) {
//		String jpql = "select first(o) from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " o"
		String jpql = "select o from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " o"
				+ " where o.dataMovimento = :dataMovimento";
		jpql += " and o.adquirente = :adquirente";
		
		TypedQuery<ControleConciliacao> query = getEntityManager().createQuery(jpql, ControleConciliacao.class);
		query.setParameter(ControleConciliacao_.adquirente.getName(), adquirente.getNome());
		query.setParameter(ControleConciliacao_.dataMovimento.getName(), dataMovimento);
		query.setMaxResults(1);
		ControleConciliacao controle = JpaUtils.getSingleResult(query);		
		return controle;
	}

	@Override
	public List<Adquirente> listarAdquirentesAtivosConciliacao(Date dataHoje, int intervaloVigenciaEmMeses) {
		String jpql = ""
				+ " select distinct a"
				+ " from " + JpaUtils.getNomeEntidade(AdquirenteBandeira.class) + " ab"
				+ " join ab.adquirente a"
				+ " join ab.contratoCobranca cc"
				+ " where "
				+ " a.codigoAfiliacaoConciliador is not null"
				+ " and "
				+ " ((cc.dataInicioVigencia <= :dataHoje) and (cc.dataFimVigencia >= :dataLimite))"
				+ " order by a.nome";
		Calendar cal = DateUtils.getCalendarDataHoraAtual();
		cal.setTime(dataHoje);
		cal.add(Calendar.MONTH, -intervaloVigenciaEmMeses);
		TypedQuery<Adquirente> query = getEntityManager().createQuery(jpql, Adquirente.class);
		query.setParameter("dataHoje", dataHoje);
		query.setParameter("dataLimite", cal.getTime());
		return query.getResultList();
	}

	@Override
	public List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente) {
		String jpql = ""
				+ " select c"
				+ " from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " c"
				+ " where c.status in :status"
				+ " and c.dataImportacaoArquivo is null";

		jpql += " and c.adquirente = :adquirenteCadastrada";
		jpql += " order by c.dataImportacaoArquivo asc";
		
		List<String> statusesParaProcessamento = new ArrayList<String>();
		statusesParaProcessamento.add(StatusControleConciliacao.ReprocessamentoSolicitado.getValor());
		statusesParaProcessamento.add(StatusControleConciliacao.Pendente.getValor());
		
		TypedQuery<ControleConciliacao> query = getEntityManager().createQuery(jpql, ControleConciliacao.class);
		query.setParameter(ControleConciliacao_.status.getName(), statusesParaProcessamento);
		query.setParameter("adquirenteCadastrada", adquirente.getNome());
				
		return query.getResultList();
	}
	
	public void marcarParaReprocessamento(ControleConciliacao cc) {
//		cc = obterPorIdentidade(cc);
		if (cc.getId() != null) {
				
			cc.setStatus(StatusControleConciliacao.ReprocessamentoSolicitado);
			if (cc.getNumeroReprocessamento() != null) {
				cc.setNumeroReprocessamento(cc.getNumeroReprocessamento()+1L);
			} else {
				cc.setNumeroReprocessamento(1L);
			}
			cc.setDataImportacaoArquivo(null);
			alterar(cc);
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContratoCobranca obterContratoCobrancaAssociado(ControleConciliacao controle) {
		// Veja a documentação do método no DLO antes de fazer qualquer coisa!
		String jpql = ""
				+ " select cc"
				+ " from " + JpaUtils.getNomeEntidade(AdquirenteBandeira.class) + " ab"
				+ " join ab.adquirente a"
				+ " join ab.contratoCobranca cc"
				+ " where a.nome = :adquirente"
				+ " order by cc.dataInicioVigencia desc";
		TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
		query.setParameter(ControleConciliacao_.adquirente.getName(), controle.getAdquirente());
		query.setMaxResults(1);
		ContratoCobranca contrato = JpaUtils.getSingleResult(query);
		return contrato;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente) {
		// Veja a documentação do método no DLO antes de fazer qualquer coisa!
		String jpql = ""
				+ " select ab.contratoCobranca"
				+ " from " + JpaUtils.getNomeEntidade(AdquirenteBandeira.class) + " ab"
				+ " join ab.adquirente a"
				+ " where a.id = :id"
				+ " order by ab.contratoCobranca.dataInicioVigencia desc";
		TypedQuery<ContratoCobranca> query = getEntityManager().createQuery(jpql, ContratoCobranca.class);
		query.setMaxResults(1);
		query.setParameter(Adquirente_.id.getName(), adquirente.getId());
		return JpaUtils.getSingleResult(query);
	}
	
	@Override
	public Date obterUltimaDataMovimentoImportada(Adquirente adquirente) {
		String jpql = ""
				+ " select coc.dataMovimento"
				+ " from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " coc"
				+ " where coc.adquirente = :adquirenteCadastrada"
				+ " and coc.dataImportacaoArquivo is not null"
				+ " and coc.status is not null"
				+ " order by coc.dataMovimento desc";

		Query query = getEntityManager().createQuery(jpql);
		query.setMaxResults(1);
		query.setParameter("adquirenteCadastrada", adquirente.getNome());
		return JpaUtils.getSingleResult(query, Date.class);
	}

	@Override
	public ControleConciliacao buscarProximoArquivoParaProcessamento(Adquirente adquirente) {
		String jpql = ""
				+ " select coc"
				+ " from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " coc"
				+ " where coc.adquirente = :adquirente"
				+ " and coc.dataMovimento > ("
				+ "   select max(coc.dataMovimento)"
				+ "   from " + JpaUtils.getNomeEntidade(ControleConciliacao.class) + " coc"
				+ "   where coc.adquirente = :adquirente"
				+ "   and coc.status = :status"
				+ " )"
				+ " order by coc.dataMovimento asc";

		TypedQuery<ControleConciliacao> query = getEntityManager().createQuery(jpql, ControleConciliacao.class);
		query.setParameter(ControleConciliacao_.adquirente.getName(), adquirente.getNome());
		query.setParameter(ControleConciliacao_.status.getName(), StatusControleConciliacao.Processado.getValor());
		query.setMaxResults(1);
		ControleConciliacao retorno = JpaUtils.getSingleResult(query);
		return retorno;
	}
}
