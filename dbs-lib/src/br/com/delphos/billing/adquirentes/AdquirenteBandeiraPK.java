package br.com.delphos.billing.adquirentes;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the ADQUIRENTE_BANDEIRA database table.
 * 
 */
@Embeddable
public class AdquirenteBandeiraPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;


//	@Column(name = "ID_ADQUIRENTE", insertable = false, updatable = false)
	private Long idAdquirente;

//	@Column(name = "ID_BANDEIRA", insertable = false, updatable = false)
	private Long idBandeira;
	
//	@Column(name = "ID_CONTRATO_COBRANCA", insertable = false, updatable = false)
	private Long idContratoCobranca;

	public AdquirenteBandeiraPK() {
	}

	public Long getIdAdquirente() {
		return idAdquirente;
	}

	public void setIdAdquirente(Long adquirente) {
		this.idAdquirente = adquirente;
	}

	public Long getIdBandeira() {
		return idBandeira;
	}

	public void setIdBandeira(Long bandeira) {
		this.idBandeira = bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.idBandeira = bandeira.getId();
	}
	
	public void setAdquirente(Adquirente adquirente) {
		this.idAdquirente = adquirente.getId();
	}

	public Long getIdContratoCobranca() {
		return idContratoCobranca;
	}

	public void setIdContratoCobranca(Long idContratoCobranca) {
		this.idContratoCobranca = idContratoCobranca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAdquirente == null) ? 0 : idAdquirente.hashCode());
		result = prime * result
				+ ((idBandeira == null) ? 0 : idBandeira.hashCode());
		result = prime
				* result
				+ ((idContratoCobranca == null) ? 0 : idContratoCobranca
						.hashCode());
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
		AdquirenteBandeiraPK other = (AdquirenteBandeiraPK) obj;
		if (idAdquirente == null) {
			if (other.idAdquirente != null)
				return false;
		} else if (!idAdquirente.equals(other.idAdquirente))
			return false;
		if (idBandeira == null) {
			if (other.idBandeira != null)
				return false;
		} else if (!idBandeira.equals(other.idBandeira))
			return false;
		if (idContratoCobranca == null) {
			if (other.idContratoCobranca != null)
				return false;
		} else if (!idContratoCobranca.equals(other.idContratoCobranca))
			return false;
		return true;
	}

	@Override
	public String toString() {
//		return "AdquirenteBandeiraPK [idAdquirente=" + idAdquirente
//				+ ", idBandeira=" + idBandeira + ", idContratoCobranca="
//				+ idContratoCobranca + "]";
		return idAdquirente + "-" + idBandeira + "-"+ idContratoCobranca;
	}
	
	
	
}