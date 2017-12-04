package br.com.delphos.billing.conciliacoes;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.cobrancas.CobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.util.Mensagens;

@Stateless
public class ProcConciliacaoEventoDLOBean  extends AbstractDLO<ProcConciliacaoEvento> implements ProcConciliacaoEventoDLO{

	@EJB
	private ProcConciliacaoEventoDAO dao;
	
	@EJB(mappedName = "java:global/dbsdb/CobrancaDLOBean")
	private CobrancaDLO cobrancaDLO;
	
	@EJB(mappedName = "java:global/dbsdb/ProcessamentoConciliacaoDLOBean")
	private ProcessamentoConciliacaoDLO processamentoConciliacaoDLO;
	
	@Override
	public ProcConciliacaoEvento obterPorProcessamentoConciliacao(
			ProcessamentoConciliacao pc) {
		
		return dao.obterPorProcessamentoConciliacao(pc);
	}
	
	public List<ProcConciliacaoEvento> listarConciliacaoPorCobranca(Long idcobranca) throws DLOException {
		
		if (idcobranca == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.idCobranca")));
		}
		
		Cobranca cobranca = cobrancaDLO.obter(idcobranca);
		
		if (cobranca == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.cobranca")));
		}
		
//		ProcessamentoConciliacao pc = processamentoConciliacaoDLO.obterPorCobranca(cobranca);
		
		List<ProcConciliacaoEvento> conciliacao = dao.listarConciliacaoPorCobranca(cobranca);
		
		return conciliacao;
	}

	@Override
	protected DAOEntidade<ProcConciliacaoEvento> getDAOEntidade() {
		
		return dao;
	}
	
    @Override
	public boolean isEditavel(ProcConciliacaoEvento entidade)
			throws DLOException {
		return false;
	}


}
