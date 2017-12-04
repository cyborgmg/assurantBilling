package br.com.delphos.web.conciliacao;

import java.util.Iterator;
import java.util.List;

import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.conciliacoes.ConciliacaoInterface;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Texto;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class ConciliacaoDecorator extends DelphosDecorator {
	
	AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
	private ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
	
	public ConciliacaoDecorator() {
        this.setFiltro(false);
    }
	    
/*    public String getIdAdquirente() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return String.valueOf(conciliacao.getIdAdquirente());
    }
    
    public String getDescricaoAdquirente() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getDescricaoAdquirente();
    }
    
    public String getDataMovimentoArquivo() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getDataMovimentoArquivo();
    }
    
    public String getValorArquivo() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getValorArquivo();
    }
    
    public String getDataImportacaoArquivo() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getDataImportacaoArquivo();
    }
    
    public String getNumeroReprocessamento() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getNumeroReprocessamento();
    }
    
    public String getDataDepositoArquivo() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getDataDepositoArquivo();
    }
    
    public String getMensagemDeposito() {
    	ConciliacaoActionForm conciliacao = (ConciliacaoActionForm) this.getCurrentRowObject();
    	return conciliacao.getMensagemDeposito();
    }*/
	
    public String getDescricaoAdquirente() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	return conciliacao.getAdquirente();
    }
    
    public String getDataMovimentoArquivo() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	return Data.formatar(conciliacao.getDataMovimento(), Data.MASCARA_SEM_HORA);
    }
    
    public String getDataProcessamentoArquivo() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	return Data.formatar(conciliacao.getDataProcessamentoArquivo(), Data.MASCARA_SEM_HORA);
    }
    
    public String getValorDeposito() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	if (conciliacao.getValorDeposito() != null) {
    		return Texto.doubleToMoeda(conciliacao.getValorDeposito().doubleValue(), "###,###,###,##0.00");
    	} else {
    		return "";
    	}
    }
    
    public String getDataImportacaoArquivo() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	if (conciliacao.getDataImportacaoArquivo() != null) {
    		return Data.formatar(conciliacao.getDataImportacaoArquivo(), Data.MASCARA_SEM_HORA);
    	} else {
    		return "";
    	}
    }
    
    public String getNumeroReprocessamento() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	if (conciliacao.getNumeroReprocessamento() != null)
    		return conciliacao.getNumeroReprocessamento().toString();
    	else
    		return "";
    }
    
    public String getMensagemDeposito() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
    	if (conciliacao.getTipoMensagemConciliacao() != null)
    		return conciliacao.getTipoMensagemConciliacao().getValor();
    	else
    		return "";
    }
    
    public String getStatusDeposito() {
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
//    	return StatusControleConciliacao.buscarPorValor(conciliacao.getStatus().getValor()).getValor();
    	
		ListaValor listaStatusControleConciliacao;
		try {
			listaStatusControleConciliacao = listaValorDLO.obterPorCodigo(TipoListaValor.StatusControleConciliacao.getValor());
			
			List<ItemLista> listItemLista = listaStatusControleConciliacao.getItensLista();
			
			for (Iterator iterator = listItemLista.iterator(); iterator
					.hasNext();) {
				ItemLista itemLista = (ItemLista) iterator.next();
				
				if (itemLista.getCodigo().equalsIgnoreCase(conciliacao.getStatus().getValor())) {
					return itemLista.getDescricao();
				}
				
			}
			
		} catch (DLOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
    	
    }
    
    public String getAcaoDeposito() {
    	
    	// TODO VER 5.3.2
    	//    	Alterar Depósito: Caso o movimento encontre-se com os seguintes status (CONTROLE_CONCILIACAO.status) 
    	// a ação não deve ser exibida: “LIB”, “PRC”, “REJ” e “REP”.
    	//    	Solicitar Reprocessamento: Caso o movimento encontre-se com os seguintes status (CONTROLE_CONCILIACAO.status) 
    	// a ação não deve ser exibida: “LIB” e “PEN”.
    	//    	Excluir Movimento: Apenas estará disponível se o status do movimento (CONTROLE_CONCILIACAO.status)   
    	// for “PEN” e o arquivo não houver sido importado (CONTROLE_CONCILIACAO.dataImportacaoArquivo = null).

    	
    	
    	ControleConciliacao conciliacao = (ControleConciliacao) this.getCurrentRowObject();
        String html = "";
//        html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
//        html += "<img title='Processo Autorizado' style='margin-bottom: 3px; margin-right: 5%;' class='tooltipBottom' src=\"../img/icones/ativar_16x16.png\"/>";
//        html += "</div";
        if (!conciliacao.getStatus().equals(StatusControleConciliacao.Liberado) &&
        		!conciliacao.getStatus().equals(StatusControleConciliacao.Processado) &&
        		!conciliacao.getStatus().equals(StatusControleConciliacao.Rejeitado) &&
        		!conciliacao.getStatus().equals(StatusControleConciliacao.ReprocessamentoSolicitado)) { 
	        html += "<div style='margin-right: 0px;'>";
	        html += "<img title='Alterar Dep\u00f3sito' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: editarConciliacao("+conciliacao.getId().toString()+");' src=\"../img/icones/editar_16x16.png\"/>";
	        html += "</div";
        }
//        html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
//        html += "<img title='Incluir Processamento' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: incluirValor();' src=\"../img/icones/adicionar_16x16.png\"/>";
//        html += "</div";
	        
	    // if (conciliacao.getStatus().equals(StatusControleConciliacao.Rejeitado) && conciliacao.isArquivoVazio() == true)
        if (!conciliacao.getStatus().equals(StatusControleConciliacao.Liberado) &&
        		!conciliacao.getStatus().equals(StatusControleConciliacao.Pendente)) {
	        html += "<div style='margin-right: 0px;'>";
	        html += "<img title='Solicitar Reprocessamento' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: solicitarReprocessamento("+conciliacao.getId().toString()+");' src=\"../img/icones/engrenagem_16x16.png\"/>";
	        html += "</div";
    	}
        if (conciliacao.getStatus().equals(StatusControleConciliacao.Pendente) &&
        		conciliacao.getDataImportacaoArquivo() == null) {
	        html += "<div style='margin-right: 0px;'>";
	        html += "<img title='Excluir Dep\u00f3sito' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: excluirValor("+conciliacao.getId().toString()+");' src=\"../img/icones/desativar_16x16.png\"/>";
	        html += "</div";
        }
        return html;

    }   
}