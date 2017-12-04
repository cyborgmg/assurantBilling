package br.com.delphos.sca.parametrosSmtp;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.persistencia.Entidade;

import java.math.BigDecimal;


/**
 * The persistent class for the PARAMETRO_SMTP database table.
 * 
 */
@Entity
@Table(name="PARAMETRO_SMTP")
@NamedQuery(name="ParametroSmtp.findAll", query="SELECT p FROM ParametroSmtp p")
public class ParametroSmtp implements Serializable, Entidade<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="SEQ_ID_PARAMETRO_SMTP",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ_ID_PARAMETRO_SMTP",name="SEQ_ID_PARAMETRO_SMTP",allocationSize=1)
	@Column(name="ID_PARAMETRO_SMTP")
	private Long id;

	private String autenticado;

	@Column(name="\"HOST\"")
	private String host;

	private Integer porta;

	private String protocolo;

	private String senha;

	private String usuario;

	public ParametroSmtp() {
	}

	public long getIdParametroSmtp() {
		return this.id;
	}

	public void setIdParametroSmtp(long idParametroSmtp) {
		this.id = idParametroSmtp;
	}

	public String getAutenticado() {
		return this.autenticado;
	}

	public void setAutenticado(String autenticado) {
		this.autenticado = autenticado;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}


	public String getProtocolo() {
		return this.protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return this.usuario;
	}
	
	public Integer getPorta() {
		return porta;
	}

	public void setPorta(Integer porta) {
		this.porta = porta;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean hasId() {
		return this.id != null;
	}

	public Long getId() {
		return this.id;
	}

}