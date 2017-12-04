package br.com.delphos.billing.contratosCobranca;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the RESPOSTA_AUTORIZACAO database table.
 * 
 */
@Entity
@Table(name="RESPOSTA_AUTORIZACAO")
@NamedQuery(name="RespostaAutorizacao.findAll", query="SELECT r FROM RespostaAutorizacao r")
@SequenceGenerator(sequenceName = "SEQ_ID_RESPOSTA_AUTORIZACAO", name = "SEQ_ID_RESPOSTA_AUTORIZACAO", allocationSize = 1)
public class RespostaAutorizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_RESPOSTA_AUTORIZACAO")
	@Column(name="ID_RESPOSTA_AUTORIZACAO")
	private Long id;

	@Column(name="CODIGO_RESPOSTA")
	private String codigoResposta;

	@Column(name="DESCRICAO_RESPOSTA_CONSULTA")
	private String descricaoRespostaConsulta;

	@Column(name="DESCRICAO_RESPOSTA_PROVEDOR")
	private String descricaoRespostaProvedor;

	@Column(name="QUANTIDADE_RETENTATIVA")
	private int quantidadeRetentativa;

	@Column(name="TIPO_INTERVALO")
	private String tipoIntervalo;

	@Column(name="VALOR_INTERVALO")
	private Integer valorIntervalo;

	//bi-directional many-to-one association to ContratoCobranca
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CONTRATO_COBRANCA")
	private ContratoCobranca contratoCobranca;

	public RespostaAutorizacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoResposta() {
		return this.codigoResposta;
	}

	public void setCodigoResposta(String codigoResposta) {
		this.codigoResposta = codigoResposta;
	}

	public String getDescricaoRespostaConsulta() {
		return this.descricaoRespostaConsulta;
	}

	public void setDescricaoRespostaConsulta(String descricaoRespostaConsulta) {
		this.descricaoRespostaConsulta = descricaoRespostaConsulta;
	}

	public String getDescricaoRespostaProvedor() {
		return this.descricaoRespostaProvedor;
	}

	public void setDescricaoRespostaProvedor(String descricaoRespostaProvedor) {
		this.descricaoRespostaProvedor = descricaoRespostaProvedor;
	}

	public int getQuantidadeRetentativa() {
		return this.quantidadeRetentativa;
	}

	public void setQuantidadeRetentativa(int quantidadeRetentativa) {
		this.quantidadeRetentativa = quantidadeRetentativa;
	}

	public String getTipoIntervalo() {
		return this.tipoIntervalo;
	}

	public void setTipoIntervalo(String tipoIntervalo) {
		this.tipoIntervalo = tipoIntervalo;
	}

	public Integer getValorIntervalo() {
		return this.valorIntervalo;
	}

	public void setValorIntervalo(Integer valorIntervalo) {
		this.valorIntervalo = valorIntervalo;
	}

	public ContratoCobranca getContratoCobranca() {
		return this.contratoCobranca;
	}

	public void setContratoCobranca(ContratoCobranca contratoCobranca) {
		this.contratoCobranca = contratoCobranca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoResposta == null) ? 0 : codigoResposta.hashCode());
		result = prime
				* result
				+ ((contratoCobranca == null) ? 0 : contratoCobranca.hashCode());
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
		RespostaAutorizacao other = (RespostaAutorizacao) obj;
		if (codigoResposta == null) {
			if (other.codigoResposta != null)
				return false;
		} else if (!codigoResposta.equals(other.codigoResposta))
			return false;
		if (contratoCobranca == null) {
			if (other.contratoCobranca != null)
				return false;
		} else if (!contratoCobranca.equals(other.contratoCobranca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RespostaAutorizacao [id=" + id + ", codigoResposta="
				+ codigoResposta + ", contratoCobranca=" + contratoCobranca
				+ "]";
	}

}