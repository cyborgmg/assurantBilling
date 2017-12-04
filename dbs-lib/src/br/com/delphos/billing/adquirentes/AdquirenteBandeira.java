package br.com.delphos.billing.adquirentes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;


/**
 * The persistent class for the ADQUIRENTE_BANDEIRA database table.
 * 
 */
@Entity
@Table(name="ADQUIRENTE_BANDEIRA")
@NamedQuery(name="AdquirenteBandeira.findAll", query="SELECT a FROM AdquirenteBandeira a")
public class AdquirenteBandeira implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AdquirenteBandeiraPK id;

	@Column(name="ADQUIRENTE_PADRAO")
	private String adquirentePadrao;

	@Column(name="CODIGO_METODO_PAGAMENTO")
	private Long codigoMetodoPagamento;
	
	@MapsId("idContratoCobranca")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CONTRATO_COBRANCA")
	private ContratoCobranca contratoCobranca;

//	@Transient
    @MapsId("idAdquirente")
	@JoinColumn(name = "ID_ADQUIRENTE")
    @ManyToOne(fetch = FetchType.EAGER)
	private Adquirente adquirente;

    @MapsId("idBandeira")
	@JoinColumn(name = "ID_BANDEIRA")
    @ManyToOne(fetch = FetchType.EAGER)
	private Bandeira bandeira;

	public AdquirenteBandeira() {
	}

	public AdquirenteBandeiraPK getId() {
		return this.id;
	}

	public void setId(AdquirenteBandeiraPK id) {
		this.id = id;
	}
	
	public boolean isAdquirentePadrao() {
		return this.adquirentePadrao != null && this.adquirentePadrao.equals("S") ? true : false;
	}
	
	public void setAdquirentePadrao(boolean adquirentePadrao) {
		this.adquirentePadrao = adquirentePadrao ? "S" : null;
	}

	public Long getCodigoMetodoPagamento() {
		return codigoMetodoPagamento;
	}

	public void setCodigoMetodoPagamento(Long codigoMetodoPagamento) {
		this.codigoMetodoPagamento = codigoMetodoPagamento;
	}

	public Adquirente getAdquirente() {
		return this.adquirente;
	}

	public void setAdquirente(Adquirente adquirente) {
//		if (this.id == null) {
//			this.id = new AdquirenteBandeiraPK();
//		}
//		this.id.setIdAdquirente(adquirente.getId());
		this.adquirente = adquirente;
	}

	public Bandeira getBandeira() {
		return this.bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
//		if (this.id == null) {
//			this.id = new AdquirenteBandeiraPK();
//		}
//		this.id.setIdBandeira(bandeira.getId());
		this.bandeira = bandeira;
	}

	public ContratoCobranca getContratoCobranca() {
		return this.contratoCobranca;
	}

	public void setContratoCobranca(ContratoCobranca contratoCobranca) {
		this.contratoCobranca = contratoCobranca;
	}

	public String getAdquirentePadrao() {
		return adquirentePadrao;
	}

	public void setAdquirentePadrao(String adquirentePadrao) {
		this.adquirentePadrao = adquirentePadrao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adquirente == null) ? 0 : adquirente.hashCode());
		result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
		result = prime * result + ((contratoCobranca == null) ? 0 : contratoCobranca.hashCode());
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
		AdquirenteBandeira other = (AdquirenteBandeira) obj;
		if (adquirente == null) {
			if (other.adquirente != null)
				return false;
		} else if (!adquirente.equals(other.adquirente))
			return false;
		if (bandeira == null) {
			if (other.bandeira != null)
				return false;
		} else if (!bandeira.equals(other.bandeira))
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
		return "AdquirenteBandeira [id=" + id + ", contratoCobranca=" + contratoCobranca + ", adquirente=" + adquirente
				+ ", bandeira=" + bandeira + "]";
	}


}