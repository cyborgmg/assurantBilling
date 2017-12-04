package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.util.Validador;

public class ReiniciarSenhaActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String usuario;
	private String email;
	private String chaveTitulo;
	
	private String token;
	private String origem;
    private String senhaNova;
    private String senhaConfirmacao;

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        boolean campoVazio = false;
        this.senhaNova = this.senhaNova.trim();
        this.senhaConfirmacao = this.senhaConfirmacao.trim();
        
        // - Senha Nova --------------------------------------------------------------------------------  
        if (Validador.vazio(this.senhaNova)) {
            campoVazio = true;
            errors.add("senhaNova", new ActionMessage("erro.senha.nova.nao.informada", "Senha Nova"));
            errors.add("senha", new ActionMessage(""));
            errors.add("senhaConfirmacao", new ActionMessage(""));
        } else if (this.senhaNova.trim().length() < 6) {
            errors.add("senhaNova", new ActionMessage("erro.tamanho.senhaNova.menor6", "Senha Nova"));
            errors.add("senha", new ActionMessage(""));
            errors.add("senhaConfirmacao", new ActionMessage(""));
        }

        // - Senha Confirmação -------------------------------------------------------------------------
        if (Validador.vazio(this.senhaConfirmacao)) {
            campoVazio = true;
            errors.add("senhaConfirmacao", new ActionMessage("erro.senha.repetida.nao.informada", "Confirmar Senha Nova"));
            errors.add("senha", new ActionMessage(""));
            errors.add("senhaNova", new ActionMessage(""));
        } else if (!this.senhaConfirmacao.trim().equals(this.senhaNova)) {
            errors.add("senhaConfirmacao", new ActionMessage("erro.senhas.diferentes", "Confirmar Senha Nova"));
            errors.add("senha", new ActionMessage(""));
            errors.add("senhaNova", new ActionMessage(""));
        }
        
        if (campoVazio) {
            errors.add("dialogoErro", new ActionMessage("erro.campoVermelho"));
        }
        
        return errors;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChaveTitulo() {
		return chaveTitulo;
	}

	public void setChaveTitulo(String titulo) {
		this.chaveTitulo = titulo;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String operacao) {
		this.origem = operacao;
	}
    
}
