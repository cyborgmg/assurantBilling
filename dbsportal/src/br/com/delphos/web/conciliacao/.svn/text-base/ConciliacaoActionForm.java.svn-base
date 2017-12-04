package br.com.delphos.web.conciliacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ControleConciliacaoDLO;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;

public class ConciliacaoActionForm extends ActionForm {
	
	//Flag para aferir se é uma nova busca
	private String novaBusca;
	
	private String idAdquirente;
	private String nomeAdquirente;
	private String idControleConciliacao;
	private String descricaoAdquirente;
	private String statusDeposito;
	private String dataMovimentoArquivo;
	private String valorArquivo;
	private String valorDeposito;
	private String dataImportacaoArquivo;
	private String numeroReprocessamento;
	private String dataProcessamentoArquivo;
	private String mensagemDeposito;
	private String periodoDe;
	private String periodoAte;
	
	private String opcao;
	private String listaAtiva;
	
	ControleConciliacaoDLO controleConciliacaoDLO = (ControleConciliacaoDLO) ServiceLocator.lookup("java:/global/dbsdb/ControleConciliacaoDLOBean");
	AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
	
	public String getNovaBusca() {
		return novaBusca;
	}

	public void setNovaBusca(String novaBusca) {
		this.novaBusca = novaBusca;
	}

	public String getIdAdquirente() {
		return idAdquirente;
	}

	public void setIdAdquirente(String idAdquirente) {
		this.idAdquirente = idAdquirente;
	}

	public String getDescricaoAdquirente() {
		return descricaoAdquirente;
	}

	public void setDescricaoAdquirente(String descricaoAdquirente) {
		this.descricaoAdquirente = descricaoAdquirente;
	}

	public String getDataMovimentoArquivo() {
		return dataMovimentoArquivo;
	}

	public void setDataMovimentoArquivo(String dataMovimentoArquivo) {
		this.dataMovimentoArquivo = dataMovimentoArquivo;
	}

	public String getValorArquivo() {
		return valorArquivo;
	}

	public void setValorArquivo(String valorArquivo) {
		this.valorArquivo = valorArquivo;
	}

	public String getDataImportacaoArquivo() {
		return dataImportacaoArquivo;
	}

	public void setDataImportacaoArquivo(String dataImportacaoArquivo) {
		this.dataImportacaoArquivo = dataImportacaoArquivo;
	}

	public String getNumeroReprocessamento() {
		return numeroReprocessamento;
	}

	public void setNumeroReprocessamento(String numeroReprocessamento) {
		this.numeroReprocessamento = numeroReprocessamento;
	}

	public String getStatusDeposito() {
		return statusDeposito;
	}

	public void setStatusDeposito(String statusDeposito) {
		this.statusDeposito = statusDeposito;
	}

	public String getDataProcessamentoArquivo() {
		return dataProcessamentoArquivo;
	}

	public void setDataProcessamentoArquivo(String dataProcessamentoArquivo) {
		this.dataProcessamentoArquivo = dataProcessamentoArquivo;
	}

	public String getMensagemDeposito() {
		return mensagemDeposito;
	}

	public void setMensagemDeposito(String mensagemDeposito) {
		this.mensagemDeposito = mensagemDeposito;
	}
	
	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public String getListaAtiva() {
		return listaAtiva;
	}

	public void setListaAtiva(String listaAtiva) {
		this.listaAtiva = listaAtiva;
	}

	public String getIdConciliacaoInterface() {
		return idControleConciliacao;
	}

	public void setIdConciliacaoInterface(String idConciliacaoInterface) {
		this.idControleConciliacao = idConciliacaoInterface;
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

	public String getIdControleConciliacao() {
		return idControleConciliacao;
	}

	public void setIdControleConciliacao(String idControleConciliacao) {
		this.idControleConciliacao = idControleConciliacao;
	}

	public String getValorDeposito() {
		return valorDeposito;
	}

	public void setValorDeposito(String valorDeposito) {
		this.valorDeposito = valorDeposito;
	}

	@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        
        ActionErrors errors = new ActionErrors();
        boolean campoVazio = false;
        boolean validouAlgumCampo = false;
        boolean semMensagemCamposEmVermelho = false;
        
        if (getOpcao() != null && (getOpcao().equals("conciliacao") || getOpcao().equals("pesquisarConciliacao"))) {
        	if (Validador.vazio(this.periodoDe) || Validador.vazio(this.periodoAte)) {
	        	if (Validador.vazio(this.periodoDe)) {
	        		campoVazio = true;
	        		errors.add("periodoDeLogs", new ActionMessage("", "periodoDeLogs"));
//	        		errors.add("", new ActionMessage("erro.periodoDe.log.vazio", ""));
	        	}
	        	if (Validador.vazio(this.periodoAte)) {
	        		campoVazio = true;
	        		errors.add("periodoAteLogs", new ActionMessage("", "periodoAteLogs"));
//	        		errors.add("", new ActionMessage("erro.periodoAte.log.vazio", ""));
	        	}
        	} else {
	        	if (!Validador.vazio(this.periodoDe) || !Validador.vazio(this.periodoAte)) {
		        	if (Validador.vazio(this.periodoDe)) {
		        		campoVazio = true;
		        		errors.add("periodoDeLogs", new ActionMessage("", "periodoDeLogs"));
//		        		errors.add("", new ActionMessage("erro.periodoDe.log.vazio", ""));
		        	}     
		        	if (Validador.vazio(this.periodoAte)) {
		        		campoVazio = true;
		        		errors.add("periodoAteLogs", new ActionMessage("", "periodoAteLogs"));
//		        		errors.add("", new ActionMessage("erro.periodoAte.log.vazio", ""));
		        	}     
	        	}
	        	if (!Validador.vazio(this.periodoDe) && !Validador.vazio(this.periodoAte)) {
					SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
					sdf.setLenient(false);
		
					Date dataInicial=null;
					Date dataFinal=null;
		
					if(!"".equals(periodoDe)&&!"".equals(periodoAte)){
						try {
							dataInicial= sdf.parse(periodoDe); 
							dataFinal  = sdf.parse(periodoAte);
							
							if (dataInicial.after(dataFinal)) {
								errors.add("periodoDeLogs", new ActionMessage("", "periodoDeLogs"));
				        		errors.add("periodoAteLogs", new ActionMessage("", "periodoAteLogs"));
				        		errors.add("", new ActionMessage("erro.periodoDe.log.depois.periodoAte", ""));
							} else if (dataFinal.before(dataInicial)) {
								errors.add("periodoDeLogs", new ActionMessage("", "periodoDeLogs"));
				        		errors.add("periodoAteLogs", new ActionMessage("", "periodoAteLogs"));
				        		errors.add("", new ActionMessage("erro.periodoAte.log.depois.periodoDe", ""));							
							}
							
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
	        	}
        	}
        }
        
        //TODO VERIFICAR O MOTIVO DO DLO ESTAR SENDO USADO NO ACTIONFORM...
        if (getOpcao() != null && getOpcao().equals("manterConciliacao")) {
        	
	          if (Validador.vazioComTrim(this.nomeAdquirente)) {
	        	  campoVazio = true;
	        	  errors.add("nomeAdquirente", new ActionMessage("", "nomeAdquirente"));
	//    	        	  errors.add("", new ActionMessage("erro.codigo.empresa.vazio", ""));
	          } else {
	        	  validouAlgumCampo = true;
	        	  this.nomeAdquirente = this.nomeAdquirente.trim();
	        	  
	          	List<Adquirente> listaAdquirente = adquirenteDLO.listarPorCriterio(null, this.getNomeAdquirente(), null);
	          	ControleConciliacao controleConciliacaoConsultaPrevia = null;
	          	
	          	if (listaAdquirente.size() > 0) {
	        	
	          		try {
	          			if (this.idControleConciliacao.trim().length() > 0) {
	          				controleConciliacaoConsultaPrevia = controleConciliacaoDLO.obter(Long.valueOf(idControleConciliacao));
	          			}
	          			
						ControleConciliacao cc = controleConciliacaoDLO.buscarPorAdquirenteDataMovimento(listaAdquirente.get(0), 
								Validador.obterDataPadrao(getDataMovimentoArquivo()));
						
						// se existe controle e está incluindo
						if (cc != null && this.idControleConciliacao.trim().length() == 0) {
							errors.add("", new ActionMessage("erro.dataDeposito.jaExiste", ""));
						}
						
						// se está editando porém a data é alterada ou um adquirente é alterado 
						// e já existe um controle na data alterada...
						if (cc != null && this.idControleConciliacao.trim().length() > 0) {
							if (!DateUtils.removerHoras(controleConciliacaoConsultaPrevia.getDataMovimento()).equals(
											DateUtils.removerHoras(Validador.obterDataPadrao(getDataMovimentoArquivo())))
								||
								!controleConciliacaoConsultaPrevia.getAdquirente().equals(getNomeAdquirente())) {
								errors.add("", new ActionMessage("erro.dataDeposito.jaExiste", ""));
							}
						}
						
					} catch (BillingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	          			
	          	}
	        	  
	          }
	          
		     if (Validador.vazio(this.dataMovimentoArquivo)) {
		    		campoVazio = true;
		    		errors.add("dataMovimentoArquivo", new ActionMessage("", "dataMovimentoArquivo"));
		    		//    		errors.add("", new ActionMessage("erro.periodoDe.log.vazio", ""));
		     }
		     if (Validador.vazio(this.valorDeposito)) {
		    	 campoVazio = true;
		    	 errors.add("valorDeposito", new ActionMessage("", "valorDeposito"));
		    	 //    		errors.add("", new ActionMessage("erro.periodoDe.log.vazio", ""));
		     }
        	
        }
        
        if (campoVazio) {
            errors.add("", new ActionMessage("erro.campoVermelho", ""));
        }

        return errors;
    }
}