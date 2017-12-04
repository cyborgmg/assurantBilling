package br.com.delphos.web.configuracao;

 
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import br.com.delphos.sca.gruposInterfaces.Interface;
import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class ConfiguracaoDecorator extends DelphosDecorator {
	
	public ConfiguracaoDecorator() {
        this.setFiltro(false);
    }
    
    public String getDescricaoEmpresa() {
    	Empresa empresa = (Empresa) this.getCurrentRowObject();
    	return empresa.getDescricao();
    }
    
    public String getCodigoEmpresa() {
    	Empresa empresa = (Empresa) this.getCurrentRowObject();
    	return empresa.getCodigo();
    }   
    
    public String getSiglaSistema() {
    	Sistema sistema = (Sistema) this.getCurrentRowObject();
    	return sistema.getSigla();
    }  
    
    public String getCodigoEmpresaSistema() {
    	Sistema sistema = (Sistema) this.getCurrentRowObject();
    	return sistema.getEmpresa().getCodigo();
    }  
    
    public String getDescricaoSistema() {
    	Sistema sistema = (Sistema) this.getCurrentRowObject();
    	return sistema.getDescricao();
    }
    
    public String getCodigoUsuario() {
    	Usuario usuario = (Usuario) this.getCurrentRowObject();
    	return usuario.getCodigo();
    }
    
    public String getDescricaoUsuario() {
    	Usuario usuario = (Usuario) this.getCurrentRowObject();
    	return usuario.getDescricao();
    }
    public String getEmail() {
    	Usuario usuario = (Usuario) this.getCurrentRowObject();
    	return usuario.getEmail();
    }
    
    public String getDescricaoInterface() {
    	Interface interfaci = (Interface) this.getCurrentRowObject();
    	return interfaci.getDescricao();
    }    
    
    public String getDescricaoGrupoUsuario() {
    	GrupoUsuario grupoUsuario = (GrupoUsuario) this.getCurrentRowObject();
    	return grupoUsuario.getDescricao();
    }    
    
    public String getTipoGrupo() {
    	GrupoUsuario grupoUsuario = (GrupoUsuario) this.getCurrentRowObject();
    	return grupoUsuario.getTipoGrupo();
    }   

    public String getDescricaoGrupoInterface() {
    	GrupoInterface grupoInterface = (GrupoInterface) this.getCurrentRowObject();
    	return grupoInterface.getDescricao();
    }
    
    public String getGrupoInterfaceDescricao(){
    	Interface interfaci = (Interface) this.getCurrentRowObject();
    	return interfaci.getGruposInterface().get(0).getDescricao();
    }
    
    public String getAcaoSistema() {
    	Sistema sistema = (Sistema) this.getCurrentRowObject();
    	String html = "";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+sistema.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
    	html += "</div";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+sistema.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
    	html += "</div";
    	return html;
    	
    }
    
    public String getAcaoEmpresa() {
    	Empresa empresa = (Empresa) this.getCurrentRowObject();
    	String html = "";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+empresa.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
    	html += "</div";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+empresa.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
    	html += "</div";
    	return html;
    	
    }

    public String getAcaoUsuario() {
    	Usuario usuario = (Usuario) this.getCurrentRowObject();
    	String html = "";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+usuario.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
    	html += "</div";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+usuario.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
    	html += "</div";
    	return html;
    }
    
    public String getAcaoInterface() {
    	Interface interfaci = (Interface) this.getCurrentRowObject();
    	String html = "";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+interfaci.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
    	html += "</div";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+interfaci.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
    	html += "</div";
    	return html;
    }
    
    public String getAcaoGrupoUsuario() {
    	GrupoUsuario grupoUsuario = (GrupoUsuario) this.getCurrentRowObject();
    	String html = "";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+grupoUsuario.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
    	html += "</div";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+grupoUsuario.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
    	html += "</div";
    	return html;
    }
    
    public String getAcaoGrupoInterface() {
    	GrupoInterface grupoInterface = (GrupoInterface) this.getCurrentRowObject();
    	String html = "";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+grupoInterface.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
    	html += "</div";
    	html += "<div class='iconeDisplaytag' style='margin-right: 0px;'>";
    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+grupoInterface.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
    	html += "</div";
    	return html;
    }
}