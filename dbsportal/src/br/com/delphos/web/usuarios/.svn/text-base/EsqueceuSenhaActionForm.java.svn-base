package br.com.delphos.web.usuarios;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.util.Validador;

public class EsqueceuSenhaActionForm extends ActionForm {

    private String usuario;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        
        // - Login ---------------------------------------------------------
        if (Validador.vazio(getUsuario())) {
            campoVazio = true;
            errors.add("usuario", new ActionMessage(""));
        }

        // - Endereço de e-mail -------------------------------------------- 
        if (Validador.vazio(getEmail())) {
            campoVazio = true;
            errors.add("email", new ActionMessage(""));
        } else if (!Validador.email(getEmail())) {
            errors.add("email", new ActionMessage("erro.email.invalido"));
            errors.add("dialogoErro", new ActionMessage("error.espaco"));
        }

        if (campoVazio) {
            errors.add("dialogoErro", new ActionMessage("erro.campoVermelho"));
        }
        
        return errors;
    }
}