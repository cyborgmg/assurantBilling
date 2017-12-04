package br.com.delphos.util.email;

import java.util.Date;

import br.com.delphos.excecoes.DelphosException;

public class TesteEnvioEmail {
	
	public static void main(String[] args) throws EmailException, DelphosException {

		Email email = Email.novoEmail("ISO-8859-1")
				.addRemetente("gcd@delphos.com.br")
				.addDestinatario("flisboa.costa@gmail.com")
				.addAssunto("[DELPHOS] Teste Envio e-mail")
				.addMensagemHtml("<a href=\"http://google.com\">Testando envio de e-mail, às " + new Date().toString() + ".</a>");
		EnvioEmail
			.para(email)
			.servidor("correio.delphos.com.br", 25, "SMTP")
			.enviar();
	}
}
