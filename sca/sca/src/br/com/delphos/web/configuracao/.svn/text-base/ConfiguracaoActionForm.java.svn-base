package br.com.delphos.web.configuracao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.util.Validador;

public class ConfiguracaoActionForm extends ActionForm {
	
	//Flag para aferir se é uma nova busca
	private String novaBusca;
	
	// Flag pra aferir de qual CRUD vai validar
	private String opcao;
	
	//Flag para mostrar listas pesquisadas
	private String listaAtiva;
	
	// Empresa
	private String idEmpresa;
	private String codigoEmpresa;
	private String descricaoEmpresa;
	private String empresa;
	private String empresasAssociadas;
	
	// Sistema
	private String idSistema;
	private String codigoSistema;
	private String descricaoSistema;
	private String siglaSistema;
	
	//Usuário
	private String idUsuario;
	private String codigoUsuario;
	private String descricaoUsuario;
	private String email;
	private String usuario;
	
	// Interface
	private String idInterface;
	private String descricaoInterface;
	private String grupoInterface;
	
	// Grupo Usuário
	private String idGrupoUsuario;
	private String descricaoGrupoUsuario;
	private String tipoGrupo;
	private String gruposUsuariosAssociados;
	private String usuariosAssociados;
	private String gruposInterfaceAssociados;
	
	// Grupo Interface
	private String idGrupoInterface;
	private String descricaoGrupoInterface;
	
	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public String getDescricaoSistema() {
		return descricaoSistema;
	}

	public void setDescricaoSistema(String descricaoSistema) {
		this.descricaoSistema = descricaoSistema;
	}

	public String getNovaBusca() {
		return novaBusca;
	}

	public void setNovaBusca(String novaBusca) {
		this.novaBusca = novaBusca;
	}
	
	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdInterface() {
		return idInterface;
	}

	public void setIdInterface(String idInterface) {
		this.idInterface = idInterface;
	}

	public String getDescricaoInterface() {
		return descricaoInterface;
	}

	public void setDescricaoInterface(String descricaoInterface) {
		this.descricaoInterface = descricaoInterface;
	}

	public String getGrupoInterface() {
		return grupoInterface;
	}

	public void setGrupoInterface(String grupoInterface) {
		this.grupoInterface = grupoInterface;
	}

	public String getIdGrupoUsuario() {
		return idGrupoUsuario;
	}

	public void setIdGrupoUsuario(String idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}

	public String getDescricaoGrupoUsuario() {
		return descricaoGrupoUsuario;
	}

	public void setDescricaoGrupoUsuario(String descricaoGrupoUsuario) {
		this.descricaoGrupoUsuario = descricaoGrupoUsuario;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public String getIdGrupoInterface() {
		return idGrupoInterface;
	}

	public void setIdGrupoInterface(String idGrupoInterface) {
		this.idGrupoInterface = idGrupoInterface;
	}

	public String getDescricaoGrupoInterface() {
		return descricaoGrupoInterface;
	}

	public void setDescricaoGrupoInterface(String descricaoGrupoInterface) {
		this.descricaoGrupoInterface = descricaoGrupoInterface;
	}
	
	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public String getSiglaSistema() {
		return siglaSistema;
	}

	public void setSiglaSistema(String siglaSistema) {
		this.siglaSistema = siglaSistema;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresasAssociadas() {
		return empresasAssociadas;
	}

	public void setEmpresasAssociadas(String empresasAssociadas) {
		this.empresasAssociadas = empresasAssociadas;
	}

	public String getGruposUsuariosAssociados() {
		return gruposUsuariosAssociados;
	}

	public void setGruposUsuariosAssociados(String gruposUsuariosAssociados) {
		this.gruposUsuariosAssociados = gruposUsuariosAssociados;
	}

	public String getUsuariosAssociados() {
		return usuariosAssociados;
	}

	public void setUsuariosAssociados(String usuariosAssociados) {
		this.usuariosAssociados = usuariosAssociados;
	}

	public String getGruposInterfaceAssociados() {
		return gruposInterfaceAssociados;
	}

	public void setGruposInterfaceAssociados(String gruposInterfaceAssociados) {
		this.gruposInterfaceAssociados = gruposInterfaceAssociados;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getListaAtiva() {
		return listaAtiva;
	}

	public void setListaAtiva(String listaAtiva) {
		this.listaAtiva = listaAtiva;
	}

	@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        
        ActionErrors errors = new ActionErrors();
        boolean campoVazio = false;
        boolean validouAlgumCampo = false;
        boolean semMensagemCamposEmVermelho = false;
        
      /*  if (getOpcao() != null && (getOpcao().equals("interface") || getOpcao().equals("pesquisaInterface"))) {
        	if(Validador.vazioComTrim(this.descricaoInterface)){
        		  campoVazio = true;
	        	  errors.add("descricaoInterface", new ActionMessage("", "descricaoInterface"));
        	}
        	else{
        		  validouAlgumCampo = true;
        	}
        	//
        	if(Validador.vazioComTrim(this.grupoInterface)){
      		  campoVazio = true;
	        	  errors.add("grupoInterface", new ActionMessage("", "grupoInterface"));
        	}
        	else{
        			validouAlgumCampo = true;
        	}
        }*/

        
        
        
        if (getOpcao() != null && (getOpcao().equals("empresa") || getOpcao().equals("pesquisaEmpresa"))) {
            // EMPRESA
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
	          if (Validador.vazioComTrim(this.codigoEmpresa)) {
	        	  campoVazio = true;
	        	  errors.add("codigoEmpresa", new ActionMessage("", "codigoEmpresa"));
//	        	  errors.add("", new ActionMessage("erro.codigo.empresa.vazio", ""));
	          } else {
	        	  validouAlgumCampo = true;
	        	  this.codigoEmpresa = this.codigoEmpresa.trim();
	          }
	          if (Validador.vazioComTrim(this.descricaoEmpresa)) {
	        	  campoVazio = true;
	        	  errors.add("descricaoEmpresa", new ActionMessage("", "descricaoEmpresa"));
//	        	  errors.add("", new ActionMessage("erro.descricao.empresa.vazio", ""));
	          } else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoEmpresa = this.descricaoEmpresa.trim();
	          }     
        }
        
        if (getOpcao() != null && (getOpcao().equals("sistema") || getOpcao().equals("pesquisaSistema"))) {
        	// SISTEMA
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.siglaSistema)) {
        		campoVazio = true;
        		errors.add("siglaSistema", new ActionMessage("", "siglaSistema"));
//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.siglaSistema = this.siglaSistema.trim();
	        }    	
        	if (Validador.vazioComTrim(this.idEmpresa) || Validador.vazioComTrim(this.empresa)) {
        		if (!getOpcao().equals("sistema")) {
	        		campoVazio = true;
	        		errors.add("idEmpresa", new ActionMessage("", "idEmpresa"));
	//        		errors.add("", new ActionMessage("erro.codigo.produto.vazio", ""));
	        		this.idEmpresa = "";
	        		this.empresa = "";
        		}
        	} else {
        		if (!Validador.vazioComTrim(this.siglaSistema) || !Validador.vazioComTrim(this.descricaoSistema)) {
	        	  validouAlgumCampo = true;
	        	  this.siglaSistema = this.siglaSistema.trim();
        		}
	        }    	
        	if (Validador.vazioComTrim(this.descricaoSistema)) {
        		campoVazio = true;
        		errors.add("descricaoSistema", new ActionMessage("", "descricaoSistema"));
//        		errors.add("", new ActionMessage("erro.descricao.sistema.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoSistema = this.descricaoSistema.trim();
	        }
        }
        
        if (getOpcao() != null && (getOpcao().equals("manterUsuario") || getOpcao().equals("pesquisaUsuario"))) {
        	// USUARIO
        	
        	if ((getOpcao().equals("manterUsuario") || getOpcao().equals("pesquisaUsuario"))) {
        	
        		// - CODIGO -------------------------------------------------------------------------------------  
        	
	        	if (Validador.vazioComTrim(this.codigoUsuario)) {
	        		campoVazio = true;
	        		errors.add("codigoUsuario", new ActionMessage("", "codigoUsuario"));
	//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.codigoUsuario = this.codigoUsuario.trim();
	        	}    	
	        	if (Validador.vazioComTrim(this.descricaoUsuario) || Validador.vazioComTrim(this.usuario)) {
	        		if (!getOpcao().equals("manterUsuario")) {
		        		campoVazio = true;
		        		this.usuario = "";
		        		this.descricaoUsuario = "";
		        		errors.add("descricaoUsuario", new ActionMessage("", "descricaoUsuario"));
		//        		errors.add("", new ActionMessage("erro.codigo.produto.vazio", ""));
	        		}
	        	} else {
	        		validouAlgumCampo = true;
	        		this.usuario = this.usuario.trim();
	        		this.descricaoUsuario = this.descricaoUsuario.trim();
	        	}   
	        	
        	} 
        	
        	if (getOpcao().equals("pesquisaUsuario")) {
	        	if (Validador.vazioComTrim(this.descricaoEmpresa) || Validador.vazioComTrim(this.empresa)) {
	        		campoVazio = true;
	        		this.empresa = "";
	        		this.descricaoEmpresa = "";
	        		errors.add("descricaoEmpresa", new ActionMessage("", "descricaoEmpresa"));
	//        		errors.add("", new ActionMessage("erro.codigo.produto.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.empresa = this.empresa.trim();
	        		this.descricaoEmpresa = this.descricaoEmpresa.trim();
	        	} 
        	}
        	
        	if (getOpcao().equals("manterUsuario")) {
        		
	        	if (Validador.vazioComTrim(this.codigoUsuario)) {
	        		campoVazio = true;
	        		errors.add("codigoUsuario", new ActionMessage("", "codigoUsuario"));
	//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.codigoUsuario = this.codigoUsuario.trim();
	        	} 
	        	
	        	if (Validador.vazioComTrim(this.descricaoUsuario)) {
	        		campoVazio = true;
	        		errors.add("descricaoUsuario", new ActionMessage("", "descricaoUsuario"));
	        		//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.descricaoUsuario = this.descricaoUsuario.trim();
	        	}  
        	
	        	if (Validador.vazioComTrim(this.email)) {
	        		campoVazio = true;
	        		errors.add("email", new ActionMessage("", "email"));
	//        		errors.add("", new ActionMessage("erro.descricao.sistema.vazio", ""));
	        	} else {
	        		if (!Validador.email(this.email.toLowerCase())) {
	        			errors.add("email", new ActionMessage("", "email"));
	        		} else {
	        			validouAlgumCampo = true;
	        			this.email = this.email.trim();
	        		}
	        	}
        	
        	}
        }
        
        if (getOpcao() != null && (getOpcao().equals("grupoUsuario") || getOpcao().equals("pesquisaGrupoUsuario")
        		|| getOpcao().equals("manterGrupoUsuario"))) {
        	// USUARIO
        	// - TIPO GRUPO -------------------------------------------------------------------------------------  
//        	if (Validador.vazioComTrim(this.tipoGrupo)) {
//        		campoVazio = true;
//        		errors.add("tipoGrupo", new ActionMessage("", "tipoGrupo"));
////        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
//        	} else {
//        		validouAlgumCampo = true;
//        	}    	
        	if (Validador.vazioComTrim(this.descricaoGrupoUsuario)) {
        		campoVazio = true;
        		errors.add("descricaoGrupoUsuario", new ActionMessage("", "descricaoGrupoUsuario"));
//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.descricaoGrupoUsuario = this.descricaoGrupoUsuario.trim();
        	}    	
        }
        
        if (getOpcao() != null && (getOpcao().equals("grupoInterface") || getOpcao().equals("pesquisaGrupoInterface"))) {
        	// USUARIO
        	// - CODIGO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.descricaoGrupoInterface)) {
        		campoVazio = true;
        		errors.add("descricaoGrupoInterface", new ActionMessage("", "descricaoGrupoInterface"));
//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.descricaoGrupoInterface = this.descricaoGrupoInterface.trim();
        	}    	
        }
        
        if (getOpcao() != null && (getOpcao().equals("interface") || getOpcao().equals("pesquisaInterface"))
        		&& !getOpcao().equals("listarInterface")) {
        	if (Validador.vazioComTrim(this.descricaoInterface)) {
        		campoVazio = true;
        		errors.add("descricaoInterface", new ActionMessage("", "descricaoInterface"));
//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.descricaoInterface = this.descricaoInterface.trim();
        	}    	
        }
        
        if (!semMensagemCamposEmVermelho) {
	        if ((validouAlgumCampo && getOpcao().contains("pesquisa")) || errors.size() == 0) {
	        	errors = new ActionErrors();
	        } else {
	        	if (!getOpcao().contains("pesquisa")) {
	        		errors.add("", new ActionMessage("erro.campoVermelho", ""));
	        	} else {
	        		errors.add("", new ActionMessage("erro.campoVermelho.configuracao", ""));
	        	}
	        }
        }
        return errors;
	}
}