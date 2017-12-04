package br.com.delphos.web.vendas;

import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class TentativaDecorator extends DelphosDecorator {
	
	public TentativaDecorator() {
        this.setFiltro(false);
    }
	
    public String getNumeroTentativa() {
    	br.com.delphos.billing.tentativas.Tentativa tentativa = (br.com.delphos.billing.tentativas.Tentativa) this.getCurrentRowObject();
        return String.valueOf(tentativa.getNumeroTentativa());
    }
    
    public String getDataTentativa() {
    	br.com.delphos.billing.tentativas.Tentativa tentativa = (br.com.delphos.billing.tentativas.Tentativa) this.getCurrentRowObject();
        return Data.formatar(tentativa.getDataTentativa(), Data.MASCARA_COM_HORA);
    }   
    
    public String getCodRetorno() {
    	br.com.delphos.billing.tentativas.Tentativa tentativa = (br.com.delphos.billing.tentativas.Tentativa) this.getCurrentRowObject();
        if ((tentativa.getCodigoRetornoAdquirente() != null && !Validador.vazio(tentativa.getCodigoRetornoAdquirente()))
         		&& (tentativa.getCodigoRetornoProvedor() == null)) {
        	return tentativa.getCodigoRetornoAdquirente();
        } else {
        	return tentativa.getCodigoRetornoProvedor();
        }
    } 
    
    public String getMensagem() {
    	br.com.delphos.billing.tentativas.Tentativa tentativa = (br.com.delphos.billing.tentativas.Tentativa) this.getCurrentRowObject();
        if ((tentativa.getMensagemRetornoAdquirente() != null && !Validador.vazio(tentativa.getMensagemRetornoAdquirente()))
        		&& (tentativa.getMensagemRetornoProvedor() == null)) {
        	return tentativa.getMensagemRetornoAdquirente();
        } else {
        	return tentativa.getMensagemRetornoProvedor();
        }
    }
    
    public String getAcaoTentativa() {
    	br.com.delphos.billing.tentativas.Tentativa tentativa = (br.com.delphos.billing.tentativas.Tentativa) this.getCurrentRowObject();
        String html = "";
        html += "<div class='iconeDisplaytag' >";
        html += "<img title='Exibir Eventos' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: exibirEventos("+tentativa.getId()+");' src=\"../img/icones/pesquisar_16x16.png\"/>";
        html += "</div>";

        return html;

    }
}