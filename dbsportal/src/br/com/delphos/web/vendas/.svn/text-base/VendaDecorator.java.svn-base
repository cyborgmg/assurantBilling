package br.com.delphos.web.vendas;

import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.conciliacoes.ProcessamentoConciliacao;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Texto;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class VendaDecorator extends DelphosDecorator {
    
	private ItemListaDLO itemListaDLO= (ItemListaDLO)  ServiceLocator.lookup("java:global/dbsdb/ItemListaDLOBean");
	
	
    public VendaDecorator() {
        this.setFiltro(false);
    }
    
    public String getCelular() {
    	br.com.delphos.billing.vendas.Venda venda = (br.com.delphos.billing.vendas.Venda) this.getCurrentRowObject();
        return venda.getCelular();
    }
    
    public String getCodigoBandeiraCartao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getCodigoBandeiraCartao();
    }
    
    public String getCodigoMotivoCancelamento() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getCodigoMotivoCancelamento();
    }
    
    public String getCodigoVendaOrigem() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getCodigoVendaOrigem();
    }
    
    public String getCpf() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return Texto.formataCpf(venda.getCpf());
    }
    
    public String getCpfPortadorCartao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return Texto.formataCpf(venda.getCpfPortadorCartao());
    }
    
    public String getDataCancelamento() {
        Venda venda = (Venda) this.getCurrentRowObject();
        
        String dataCancelamento = Data.formatar(venda.getDataCancelamento(), Data.MASCARA_SEM_HORA);
        
        return dataCancelamento;
    }
    
    public String getDataFimVigencia() {
        Venda venda = (Venda) this.getCurrentRowObject();
        
        String dataFimVigencia = Data.formatar(venda.getDataFimVigencia(), Data.MASCARA_SEM_HORA);
        
        return dataFimVigencia;
    }
    
    public String getDataPrimeiraCobranca() {
        Venda venda = (Venda) this.getCurrentRowObject();
        
        String dataPrimeiraCobranca = Data.formatar(venda.getDataPrimeiraCobranca(), Data.MASCARA_SEM_HORA);
        
        return dataPrimeiraCobranca;
    }
    
    public String getDataVendaOrigem() {
        Venda venda = (Venda) this.getCurrentRowObject();
        
        String dataVendaOrigem = Data.formatar(venda.getDataVendaOrigem(), Data.MASCARA_SEM_HORA);       
        
        return dataVendaOrigem;
    }
    
    public String getDddCelular() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getDddCelular();
    }
    
    public String getDddTelefone() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getDddTelefone();
    }
    
    public String getEmail() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getEmail();
    }
    
    public String getNome() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getNome();
    }
    
    public String getNomeImpressoCartao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getNomeImpressoCartao();
    }
    
    public String getQuantidadeParcelas() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return String.valueOf(venda.getQuantidadeParcelas());
    }
    
    public String getStatus() {
        Venda venda = (Venda) this.getCurrentRowObject();
        
    	ItemLista itemLista=itemListaDLO.obterPorCodigo(venda.getStatus().getValor(), TipoListaValor.Status.getValor());
    	
    	return 			itemLista.getDescricao();
    }
    
    public String getTelefone() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getTelefone();
    }
    
    public String getTipoCobranca() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getTipoCobranca().getValor();
    }
    
    public String getTokenCartao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getTokenCartao();
    }
    
    public String getUltimosDigitosCartao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getUltimosDigitosCartao();
    }
    
    public String getValorCobranca() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return Texto.doubleToMoeda(venda.getValorCobranca().doubleValue(), "###,###,###,##0.00");
    }
    
    public String getVencimentoCartao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getVencimentoCartao();
    }
    
    public String getEmpresa() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getEmpresa().getCodigo();
    }
    
    public String getProduto() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getProduto().getCodigo();
    }
    
    public String getSistema() {
        Venda venda = (Venda) this.getCurrentRowObject();
        return venda.getSistema().getCodigo();
    }
    
    public String getDataConciliacao() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
        
        String dataConciliacao = Data.formatar(procConciliacaoEvento.getDataProcessamento(), Data.MASCARA_SEM_HORA);    
        
        return dataConciliacao;
    }
    
    public String getStatusConciliacao() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
        
        ItemLista itemLista=itemListaDLO.obterPorCodigo(procConciliacaoEvento.getStatus().getValor(), TipoListaValor.EventoConciliacao.getValor());
    	
    	return itemLista.getDescricao();
    }
    
    public String getEvento() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
    	
    	ItemLista itemLista=itemListaDLO.obterPorCodigo(Texto.preencheString(String.valueOf(procConciliacaoEvento.getCategoryId()), 3, "0", "I"), TipoListaValor.EventoConciliacao.getValor());
    	
    	String evento = procConciliacaoEvento.getCategoryId() + " - " + itemLista.getDescricao();
    	
    	return evento;
    }
    
    public String getParcela() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
        return String.valueOf(procConciliacaoEvento.getTransactionInstallment());
    }
    
    public String getTotalParcela() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
//    	ProcessamentoConciliacao processamentoConciliacao = (ProcessamentoConciliacao) this.getCurrentRowObject();
//      return Texto.doubleToMoeda(processamentoConciliacao.getInstallmentCountSale(), "###,###,###,##0.00");
        return String.valueOf(procConciliacaoEvento.getProcessamentoConciliacao().getInstallmentCountSale());
    }
    
    public String getValorTarifa() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
        return Texto.doubleToMoeda(procConciliacaoEvento.getTaxAmount().doubleValue(), "###,###,###,##0.00");
    }
    
    public String getNsu() {
    	ProcConciliacaoEvento procConciliacaoEvento = (ProcConciliacaoEvento) this.getCurrentRowObject();
//    	ProcessamentoConciliacao processamentoConciliacao = (ProcessamentoConciliacao) this.getCurrentRowObject();
//        return String.valueOf(processamentoConciliacao.getNsuSale());
        return Texto.doubleToMoeda(procConciliacaoEvento.getProcessamentoConciliacao().getNsuSale(), "###,###,###,##0.00");
    }
    
    public String getAcao() {
        Venda venda = (Venda) this.getCurrentRowObject();
        String html = "";
        html += "<div class='iconeDisplaytag' >";
        html += "<input type='image' title='"+Mensagens.get("msg.botao.exibir.detalhes")+"' style='margin-bottom: 3px;' class='tooltipBottom' onClick='javascript: detalharVenda(" + venda.getId() + ");' src=\"../img/icones/pesquisar_16x16.png\"/>";
        html += "</div>";

        return html;

    }
    
    public String getAcaoTentativa() {
        Venda venda = (Venda) this.getCurrentRowObject();
        String html = "";
        html += "<div class='iconeDisplaytag' >";
        html += "<input type='image' title='"+Mensagens.get("msg.botao.exibir.eventos")+"' style='margin-bottom: 3px;' class='tooltipBottom' onClick='javascript: exibirEventos(); return false;' src=\"../img/icones/pesquisar_16x16.png\"/>";
        html += "</div>";

        return html;

    }
}