package br.com.delphos.web.vendas;

import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class EventoDecorator extends DelphosDecorator {
	
	ItemListaDLO dloItemLista = (ItemListaDLO) ServiceLocator
			.lookup("java:global/dbsdb/ItemListaDLOBean");
	
	public EventoDecorator() {
        this.setFiltro(false);
    }
	
    public String getDataEvento() {
    	br.com.delphos.billing.tentativas.Evento evento = (br.com.delphos.billing.tentativas.Evento) this.getCurrentRowObject();
        return Data.formatar(evento.getDataEvento(), Data.MASCARA_COM_HORA);
    }   
    
    public String getTipo() {
    	br.com.delphos.billing.tentativas.Evento evento = (br.com.delphos.billing.tentativas.Evento) this.getCurrentRowObject();
    	ItemLista itemLista = dloItemLista.obterPorCodigo(evento.getTipoEvento().getValor(), "TPEVT");
    	return itemLista.getDescricao();
    } 
    
    public String getComplemento() {
    	br.com.delphos.billing.tentativas.Evento evento = (br.com.delphos.billing.tentativas.Evento) this.getCurrentRowObject();
        return evento.getComplemento();
    }

}
