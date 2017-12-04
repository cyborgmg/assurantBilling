package br.com.delphos.billing.logs;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the LOG_OPERACAO_USUARIO database table.
 * 
 */
@Entity
@Table(name="LOG_OPERACAO_USUARIO")
@NamedQuery(name="LogOperacaoUsuario.findAll", query="SELECT l FROM LogOperacaoUsuario l")
@SequenceGenerator(sequenceName = "SEQ_ID_LOG_OPERACAO_USUARIO", name = "SEQ_ID_LOG_OPERACAO_USUARIO", allocationSize = 1)
public class LogOperacaoUsuario implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_LOG_OPERACAO_USUARIO")
	@Column(name="ID_LOG_OPERACAO_USUARIO")
	private Long id;

	private String complemento;

	//@Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="\"DATA\"")
	private Date data;

	private String operacao;

	private String tabela;

	@Column(name="ID_USUARIO")
	private Long usuario;
	
	@Column(name="DESCRICAO_USUARIO")
	private String descricaoUsuario;	
	
	@Column(name="ID_OBJETO")
	private String idObjeto;

	@Column(name="DESCRICAO_OBJETO")
	private String descricaoObjeto;
	
	public LogOperacaoUsuario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getTabela() {
		return this.tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	public Long getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	
	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public String getDescricaoObjeto() {
		return descricaoObjeto;
	}

	public void setDescricaoObjeto(String descricaoObjeto) {
		this.descricaoObjeto = descricaoObjeto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogOperacaoUsuario other = (LogOperacaoUsuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogOperacaoUsuario [id=" + id + "]";
	}

}