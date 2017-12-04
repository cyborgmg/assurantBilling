package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.util.Validador;

public class SenhaUsuarioActionForm extends ActionForm {

    private String usuario;
    private String senha;
    private String senhaNova;
    private String senhaConfirmacao;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    public void setSenhaConfirmacao(String senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();

        boolean campoVazio = false;
      
        // - Senha -------------------------------------------------------------------------------------  
        if (Validador.vazio(this.senha)) {
            campoVazio = true;
            errors.add("senha", new ActionMessage("erro.senha.nao.informada", "Senha Atual"));
            errors.add("senhaNova", new ActionMessage(""));
            errors.add("senhaConfirmacao", new ActionMessage(""));
        } else if (this.senha.trim().length() < 6) {
            errors.add("senha", new ActionMessage("erro.tamanho.senha.menor6", "Senha"));
            errors.add("senhaNova", new ActionMessage(""));
            errors.add("senhaConfirmacao", new ActionMessage(""));
        }

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
            errors.add("", new ActionMessage("erro.campo.vazio", ""));
        }

        return errors;
    }
}