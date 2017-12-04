package br.com.delphos.web.consultas;

import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class FilesDecorator extends DelphosDecorator {
    
    public FilesDecorator() {
        this.setFiltro(false);
    }
    
    public String getEmpresa() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getEmpresa();
    }
    
    public String getProduto() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getProduto();
    }
    
    public String getSistema() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getSistema();
    }
    
    public String getCpf() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getCpf();
    }
    
    public String getNome() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getNome();
    }
    
    public String getDataVenda() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getDataVenda();
    }
    
    public String getNumeroParcela() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getNumeroParcela();
    }
    
    public String getDataCobranca() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getDataCobranca();
    }
    
    public String getValor() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getValor();
    }
    
    public String getStatus() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getStatus();
    }
    
    public String getDataEvento() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getDataEvento();
    }
    
    public String getTipo() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getTipo();
    }
    
    public String getComplemento() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getComplemento();
    }
    
    public String getCertificado() {
        Files files = (Files) this.getCurrentRowObject();
        return files.getCertificado();
    }
    
    public String getAcao() {
        Files files = (Files) this.getCurrentRowObject();
        String html = "";
        html += "<div class='iconeDisplaytag' >";
        html += "<input type='image' title='Exibir Detalhes' style='margin-bottom: 3px;' class='tooltipBottom' onClick='javascript: pesquisaSolicitacaoForm.submit();' src=\"../img/icones/pesquisar_16x16.png\"/>";
        html += "</div>";

        return html;

    }
    
    public String getAcaoTentativa() {
        Files files = (Files) this.getCurrentRowObject();
        String html = "";
        html += "<div class='iconeDisplaytag' >";
        html += "<input type='image' title='Exibir Eventos' style='margin-bottom: 3px;' class='tooltipBottom' onClick='javascript: exibirEventos(); return false;' src=\"../img/icones/pesquisar_16x16.png\"/>";
        html += "</div>";

        return html;

    }
}