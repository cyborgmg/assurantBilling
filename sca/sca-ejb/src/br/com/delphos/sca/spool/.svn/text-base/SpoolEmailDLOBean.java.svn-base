package br.com.delphos.sca.spool;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.sca.parametrosSmtp.ParametroSmtp;
import br.com.delphos.sca.parametrosSmtp.ParametroSmtpDLO;
import br.com.delphos.util.email.Email;
import br.com.delphos.util.email.EmailException;
import br.com.delphos.util.email.EnvioEmail;

@Stateless
public class SpoolEmailDLOBean implements SpoolEmailDLO {
	
	@EJB
	private ParametroSmtpDLO parametroSmtpDLO;
	
	private EnvioEmail dadosEnvio;
	
	@Override
	public void enviar(Email email) throws EmailException, DelphosException {
		if (email == null) {
			throw new EmailException();
		}
		
		ParametroSmtp parametro = parametroSmtpDLO.obterUltimosParametros();
		
		dadosEnvio = EnvioEmail
					.novoEnvio()
					.servidor(parametro.getHost(), parametro.getPorta(), parametro.getProtocolo());

		EnvioEmail.para(email, dadosEnvio).enviar();
	}

}
