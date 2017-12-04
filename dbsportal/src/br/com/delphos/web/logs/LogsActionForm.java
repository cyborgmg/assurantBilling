package br.com.delphos.web.logs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.enumeracoes.Entidade;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;

public class LogsActionForm extends ActionForm {
	
	//Flag para aferir se é uma nova busca
	private String novaBusca;

	private String periodoDe;
	private String periodoAte;
	private String usuario;
	private String tipoOperacao;
	private String tipoObjetoOperacao;
	private String identificadorObjetoOperacao;
	private String complementoOperacao;
	
	ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getTipoObjetoOperacao() {
		return tipoObjetoOperacao;
	}

	public void setTipoObjetoOperacao(String tipoObjetoOperacao) {
		this.tipoObjetoOperacao = tipoObjetoOperacao;
	}

	public String getIdentificadorObjetoOperacao() {
		return identificadorObjetoOperacao;
	}

	public void setIdentificadorObjetoOperacao(String identificadorObjetoOperacao) {
		this.identificadorObjetoOperacao = identificadorObjetoOperacao;
	}

	public String getComplementoOperacao() {
		return complementoOperacao;
	}

	public void setComplementoOperacao(String complementoOperacao) {
		this.complementoOperacao = complementoOperacao;
	}

	public String getNovaBusca() {
		return novaBusca;
	}

	public void setNovaBusca(String novaBusca) {
		this.novaBusca = novaBusca;
	}
	
	@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        
        ActionErrors errors = new ActionErrors();
        boolean campoVazio = false;
        
        if (Boolean.valueOf(getNovaBusca()).booleanValue()) {
//        	if (Validador.vazio(this.tipoOperacao)) {
//        		campoVazio = true;
//        		errors.add("tipoOperacao", new ActionMessage("", "tipoOperacao"));
//        		errors.add("", new ActionMessage("erro.tipoOperacao.log.vazio", ""));
//        	}   
//        	if (Validador.vazio(this.tipoObjetoOperacao)) {
//        		campoVazio = true;
//        		errors.add("tipoObjetoOperacao", new ActionMessage("", "tipoObjetoOperacao"));
//        		errors.add("", new ActionMessage("erro.tipoObjetoOperacao.log.vazio", ""));
//        	}     
//        	if (Validador.vazio(this.identificadorObjetoOperacao)) {
//        		campoVazio = true;
//        		errors.add("identificadorObjetoOperacao", new ActionMessage("", "identificadorObjetoOperacao"));
//        		errors.add("", new ActionMessage("erro.identificadorObjetoOperacao.log.vazio", ""));
//        	} 
        	if (!Validador.vazio(this.usuario) && this.usuario.length() < 3) {
        		campoVazio = true;
        		errors.add("usuario", new ActionMessage("", "usuario"));
        		errors.add("", new ActionMessage("erro.usuario.log.menor.tres.caracteres", ""));
        	} 
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
        

        if (campoVazio) {
            errors.add("", new ActionMessage("erro.campoVermelho", ""));
        }
        
        if (errors.size() > 0) {
        	
        }
        
        ListaValor listaValorTipoOperacao;
		try {
//			listaValorTipoOperacao = listaValorDLO.obterPorCodigo("TPOP");
			listaValorTipoOperacao = listaValorDLO.obterPorCodigo(TipoListaValor.Operacao.getValor());
			
	        request.setAttribute("listaTipoOperacao", listaValorTipoOperacao.getItensLista());
		} catch (DLOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		request.setAttribute("listaObjeto", Entidade.values());

        return errors;
    }
}