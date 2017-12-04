package br.com.delphos.web.logs;

import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.sca.wsclient.SCAClient;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class LogDecorator extends DelphosDecorator {
	
	private SCAClient client = null;
	ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
	private ItemListaDLO itemListaDLO = (ItemListaDLO) ServiceLocator.lookup("java:/global/dbsdb/ItemListaDLOBean");
	
	public LogDecorator() {
        this.setFiltro(false);
    }
	
    public String getDataOperacao() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
        return Data.formatar(logOperacaoUsuario.getData(), Data.MASCARA_COM_HORA);
    }   
    
    public String getUsuario() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
		client = new SCAClient();
		return client.obterNomeUsuario((String.valueOf(logOperacaoUsuario.getUsuario())));
		
    }
    
    public String getTipoOperacao() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
        if (logOperacaoUsuario.getOperacao() != null && !Validador.vazio(logOperacaoUsuario.getOperacao())) {
//        	if (logOperacaoUsuario.getOperacao().equals("INC")) {
//        		return "INCLUSÃO";
//        	} else if (logOperacaoUsuario.getOperacao().equals("EXC")) {
//        		return "EXCLUSÃO";
//	        } else if (logOperacaoUsuario.getOperacao().equals("ALT")) {
//	        	return "ALTERAÇÃO";
//	        }
        	return itemListaDLO.listarItemListaPorCriterio(logOperacaoUsuario.getOperacao(), "", "", "").get(0).getDescricao();
        } 
    	return "";
    }
    
    public String getTipoObjetoOperacao() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
    	return logOperacaoUsuario.getTabela();
    }
    
    public String getIdentificadorObjetoOperacao() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
    	
    	LogViewHelper logvh = new LogViewHelper();
    	
    	if (logOperacaoUsuario.getIdObjeto() != null && !Validador.vazio(logOperacaoUsuario.getIdObjeto().toString())) {
    	
    		//return logvh.recuperaDescricaoIdentificadorObjetoOperacao(getTipoObjetoOperacao(), logOperacaoUsuario.getIdObjeto().toString());
    		return logOperacaoUsuario.getDescricaoObjeto();
    	
    	} else {
    		return "";
    	}
    }
    
    public String getComplementoOperacao() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
        if (logOperacaoUsuario.getComplemento() != null && !Validador.vazio(logOperacaoUsuario.getComplemento())) {
        	return logOperacaoUsuario.getComplemento();
        } 
    	return "";
    }
    
    public String getAcaoLog() {
    	br.com.delphos.billing.logs.LogOperacaoUsuario logOperacaoUsuario = (br.com.delphos.billing.logs.LogOperacaoUsuario) this.getCurrentRowObject();
        String html = "";
        html += "<div class='iconeDisplaytag' >";
        html += "<img title='Exibir Eventos' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: exibirDetalheLog("+logOperacaoUsuario.getId()+");' src=\"../img/icones/pesquisar_16x16.png\"/>";
        html += "</div>";

        return html;

    }

}
