package br.com.delphos.billing.adquirentes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the BANDEIRA database table.
 * 
 */
@Entity
@NamedQuery(name="Bandeira.findAll", query="SELECT b FROM Bandeira b")
@SequenceGenerator(sequenceName = "SEQ_ID_BANDEIRA", name = "SEQ_ID_BANDEIRA", allocationSize = 1)
public class Bandeira implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_BANDEIRA")
	@Column(name="ID_BANDEIRA")
	private Long id;

	@Column(name="CODIGO_BANDEIRA")
	private String codigoBandeira;

	@Column(name="NOME_BANDEIRA")
	private String nomeBandeira;
	
	@JoinColumn(name = "ID_BANDEIRA")
    @OneToMany(fetch = FetchType.EAGER)
	private List<AdquirenteBandeira> adquirentesBandeira;

	public Bandeira() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBandeira() {
		return this.codigoBandeira;
	}

	public void setCodigoBandeira(String codigoBandeira) {
		this.codigoBandeira = codigoBandeira;
	}

	public String getNomeBandeira() {
		return this.nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public List<AdquirenteBandeira> getAdquirentesBandeira() {
		return adquirentesBandeira;
	}

	public void setAdquirentesBandeira(List<AdquirenteBandeira> adquirentesBandeira) {
		this.adquirentesBandeira = adquirentesBandeira;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoBandeira == null) ? 0 : codigoBandeira.hashCode());
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
		Bandeira other = (Bandeira) obj;
		if (codigoBandeira == null) {
			if (other.codigoBandeira != null)
				return false;
		} else if (!codigoBandeira.equals(other.codigoBandeira))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bandeira [id=" + id + ", codigoBandeira=" + codigoBandeira
				+ "]";
	}

}