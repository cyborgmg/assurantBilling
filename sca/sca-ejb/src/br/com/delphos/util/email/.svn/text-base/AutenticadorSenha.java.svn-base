package br.com.delphos.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticadorSenha extends Authenticator {
    
    private String usuario;
    private String senha;

    public AutenticadorSenha(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usuario, senha);
    }
}
