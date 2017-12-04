package br.com.delphos.web.vendas;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Texto;

public class CobrancaDecorator extends br.com.delphos.web.widgets.decoradores.DelphosDecorator {
	
	private ItemListaDLO itemListaDLO= (ItemListaDLO)  ServiceLocator.lookup("java:global/dbsdb/ItemListaDLOBean");
	
	public CobrancaDecorator() {
        this.setFiltro(false);
    }
	
    public String getIdCobranca() {
    	Cobranca cobranca = (br.com.delphos.billing.cobrancas.Cobranca) this.getCurrentRowObject();
        return String.valueOf(cobranca.getId());
    }
    
    public String getNumeroParcela() {
    	Cobranca cobranca = (br.com.delphos.billing.cobrancas.Cobranca) this.getCurrentRowObject();
    	  String parcelaZeroEsquerda =String.format("%02d", cobranca.getNumeroParcela());
    	return parcelaZeroEsquerda;
    	
    }
    
    public String getDataCobranca() {
    	Cobranca cobranca = (br.com.delphos.billing.cobrancas.Cobranca) this.getCurrentRowObject();
        return Data.formatar(cobranca.getDataCobranca(), Data.MASCARA_SEM_HORA);
    }   
    
    public String getValor() {
    	Cobranca cobranca = (br.com.delphos.billing.cobrancas.Cobranca) this.getCurrentRowObject();
        return Texto.doubleToMoeda(cobranca.getValorCobranca().doubleValue(), "###,###,###,##0.00");
    } 
    
    public String getStatus() {
    	Cobranca cobranca = (br.com.delphos.billing.cobrancas.Cobranca) this.getCurrentRowObject();
    	
    	ItemLista itemLista=itemListaDLO.obterPorCodigo(cobranca.getStatusCobranca().getValor(), TipoListaValor.Status.getValor());
	
    	return itemLista.getDescricao().toUpperCase();//pegar da base de dados 
    
    } 

}
