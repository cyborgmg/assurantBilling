package br.com.delphos.util.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class EnvioEmail {

	private TipoProtocolo tipoProtocolo = TipoProtocolo.SMTP;
	private String codificacaoPadrao = ConstantesEmail.CodificacaoEnvio;
	private String host = "localhost";
	private int porta = tipoProtocolo.getPortaPadrao();
	private boolean debug = false;
	private boolean necessitaAutorizacao = false;
	private String login;
	private String senha;
	private Email email;
	
	public EnvioEmail() {
	}
	
	protected EnvioEmail(Email email) {
		this(email, null);
	}
	
	protected EnvioEmail(EnvioEmail envio) {
		this(null, envio);
	}

	protected EnvioEmail(Email email, EnvioEmail envio) {
		if (envio != null) {
			this.tipoProtocolo = envio.tipoProtocolo;
			this.codificacaoPadrao = envio.codificacaoPadrao;
			this.host = envio.host;
			this.porta = envio.porta;
			this.debug = envio.debug;
			this.necessitaAutorizacao = envio.necessitaAutorizacao;
			this.login = envio.login;
			this.senha = envio.senha;
			this.email = envio.email;
		}
		if (email != null) {
			this.email = email;
	    	this.codificacaoPadrao = email.getCodificacaoPadrao() != null ? email.getCodificacaoPadrao() : this.codificacaoPadrao;
		}
	}
	
    public Properties gerarProperties() {
    	Properties props = new Properties();
        props.put("mail.transport.protocol", tipoProtocolo.getTipo());
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.port", porta);
    	
    	switch (tipoProtocolo) {
    	case SMTPS:
    		props.put("mail.smtp.ssl.enable", true);
    		break;
    	case TLS:
    		props.put("mail.smtp.starttls.enable", true);
    		break;
		default:
			break;
    	}
    	
    	if (necessitaAutorizacao) {
    		props.put("mail.smtp.auth", true);
    	}
    	
    	if (codificacaoPadrao != null) {
    		props.put("mail.mime.charset", codificacaoPadrao);
    	}
    	
    	return props;
    }
    
    public Authenticator gerarAutenticador() {
    	Authenticator retorno = null;
    	
    	if (necessitaAutorizacao) {
    		retorno = new AutenticadorSenha(login, senha);
    	}
    	
    	return retorno;
    }

    public Session gerarSessao() {
    	Properties props = gerarProperties();
    	Authenticator autenticador = gerarAutenticador();
    	Session session = null;
    	
    	if (autenticador != null) {
        	session = Session.getInstance(props, autenticador);
        	
    	} else {
    		session = Session.getInstance(props);
    	}
    	
    	session.setDebug(debug);
    	return session;
    }
    
    public static EnvioEmail novoEnvio() {
    	return new EnvioEmail();
    }
    
    public static EnvioEmail para(Email email) {
    	return new EnvioEmail(email);
    }

    public static EnvioEmail para(Email email, EnvioEmail envio) {
    	return new EnvioEmail(email, envio);
    }

    public static EnvioEmail copiar(EnvioEmail envio) {
    	return new EnvioEmail(envio);
    }
    
    public EnvioEmail servidor(String host) {
    	this.host = host;
    	return this;
    }
        
    public EnvioEmail servidor(String host, int porta, String protocolo) {
    	this.host = host;
    	this.porta = porta;
    	this.tipoProtocolo = TipoProtocolo.buscarPorValor(protocolo);
    	return this;
    }
    
    public EnvioEmail protocolo(TipoProtocolo tipoProtocolo) {
    	this.tipoProtocolo = tipoProtocolo;
//    	this.porta = tipoProtocolo.getPortaPadrao();
    	return this;
    }
    
    public EnvioEmail autenticado(String login, String senha) {
    	this.login = login;
    	this.senha = senha;
    	this.necessitaAutorizacao = true;
    	return this;
    }
    
    public EnvioEmail codificacao(String codificacao) {
    	this.codificacaoPadrao = codificacao;
    	return this;
    }
    
    public void enviar() throws EmailException {
    	enviar(email);
    }
    
    public void enviar(Email email) throws EmailException {
    	Session sessao = gerarSessao();
    	MimeMessage msg = new MimeMessage(sessao);
    	
    	try {
    	    email.preparar(msg);
    	    Transport.send(msg);
    	    
    	} catch (MessagingException ex) {
    	    throw new EmailException(ex);
    	}
    }
    
	public TipoProtocolo getTipoProtocolo() {
		return tipoProtocolo;
	}
	
	public void setProtocolo(TipoProtocolo protocolo) {
		this.tipoProtocolo = protocolo;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPorta() {
		return porta;
	}
	
	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	public boolean isNecessitaAutorizacao() {
		return necessitaAutorizacao;
	}
	
	public void setNecessitaAutorizacao(boolean necessitaAutorizacao) {
		this.necessitaAutorizacao = necessitaAutorizacao;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCodificacaoPadrao() {
		return codificacaoPadrao;
	}

	public void setCodificacaoPadrao(String codificacaoPadrao) {
		this.codificacaoPadrao = codificacaoPadrao;
	}
	
	
}
