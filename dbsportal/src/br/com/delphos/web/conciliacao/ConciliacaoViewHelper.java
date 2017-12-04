package br.com.delphos.web.conciliacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ControleConciliacaoDLO;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;

public class ConciliacaoViewHelper {
	
	private MeioPagamentoDLO meioPagamentoDLO = (MeioPagamentoDLO) ServiceLocator.lookup("java:/global/dbsdb/MeioPagamentoDLOBean");
	private ControleConciliacaoDLO controleConciliacaoDLO = (ControleConciliacaoDLO) ServiceLocator.lookup("java:/global/dbsdb/ControleConciliacaoDLOBean");
	
	private String nomeAdquirente;
	private String periodoDe;
	private String periodoAte;
	
    public List<ControleConciliacao> listarConciliacao(){

    	List<ControleConciliacao> retorno = controleConciliacaoDLO.listar();
//    	List<ConciliacaoActionForm> retorno = new ArrayList<ConciliacaoActionForm>();
//    	
//    	ConciliacaoActionForm conciliacao = new ConciliacaoActionForm();
//    	conciliacao.setIdAdquirente(1L);
//    	conciliacao.setDescricaoAdquirente("CIELO");
//    	conciliacao.setStatusDeposito("PROCESSADO");
//    	conciliacao.setDataMovimentoArquivo("15/09/2015");
//    	conciliacao.setValorArquivo("50,00");
//    	conciliacao.setDataImportacaoArquivo("16/09/2015");
//    	conciliacao.setNumeroReprocessamento("0");
//    	conciliacao.setDataDepositoArquivo("16/09/2015");
//    	conciliacao.setMensagemDeposito("");
//    	retorno.add(conciliacao);
    	
        return retorno;
    }
    
    public List<ControleConciliacao> listarConciliacaoInterfacePorCriterio(){
    	
    	List<ControleConciliacao> retorno = new ArrayList<ControleConciliacao>();
    	
    	if (	!Validador.vazioComTrim(periodoDe) &&
    			!Validador.vazioComTrim(periodoAte)) {
    	
    		retorno = controleConciliacaoDLO.listarPorCriterio(nomeAdquirente, periodoDe, periodoAte);
    	
    	}
    	
    	return retorno;
    }
    
    public List<Adquirente> listarAdquirentesAtivosConciliacao() throws DLOException {
    	return controleConciliacaoDLO.listarAdquirentesAtivosConciliacao(new Date());
    }
    
	public MeioPagamentoDLO getMeioPagamentoDLO() {
		return meioPagamentoDLO;
	}

	public void setMeioPagamentoDLO(MeioPagamentoDLO meioPagamentoDLO) {
		this.meioPagamentoDLO = meioPagamentoDLO;
	}

	public String getCodigoAfiliacaoConciliador() {
		return nomeAdquirente;
	}

	public void setCodigoAfiliacaoConciliador(String codigoAfiliacaoConciliador) {
		this.nomeAdquirente = codigoAfiliacaoConciliador;
	}

	public String getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(String periodoDe) {
		this.periodoDe = periodoDe;
	}

	public String getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(String periodoAte) {
		this.periodoAte = periodoAte;
	}

	public String getNomeAdquirente() {
		return nomeAdquirente;
	}

	public void setNomeAdquirente(String nomeAdquirente) {
		this.nomeAdquirente = nomeAdquirente;
	}
	
}