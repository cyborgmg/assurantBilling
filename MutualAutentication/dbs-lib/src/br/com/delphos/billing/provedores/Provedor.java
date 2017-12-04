package br.com.delphos.billing.provedores;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROVEDOR database table.
 * 
 */
@Entity
@NamedQuery(name="Provedor.findAll", query="SELECT p FROM Provedor p")
@SequenceGenerator(sequenceName = "SEQ_ID_PROVEDOR", name = "SEQ_ID_PROVEDOR", allocationSize = 1)
public class Provedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PROVEDOR")
	@Column(name="ID_PROVEDOR")
	private Long id;

	@Column(name="CODIGO_PROVEDOR")
	private String codigoProvedor;

	@Column(name="DESCRICAO_PROVEDOR")
	private String descricaoProvedor;

	//bi-directional many-to-one association to ContratoCobranca
	@OneToMany(mappedBy="provedor")
	private List<ContratoCobranca> contratosCobranca;

	public Provedor() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoProvedor() {
		return this.codigoProvedor;
	}

	public void setCodigoProvedor(String codigoProvedor) {
		this.codigoProvedor = codigoProvedor;
	}

	public String getDescricaoProvedor() {
		return this.descricaoProvedor;
	}

	public void setDescricaoProvedor(String descricaoProvedor) {
		this.descricaoProvedor = descricaoProvedor;
	}

	public List<ContratoCobranca> getContratosCobranca() {
		return this.contratosCobranca;
	}

	public void setContratosCobranca(List<ContratoCobranca> contratosCobranca) {
		this.contratosCobranca = contratosCobranca;
	}

	public ContratoCobranca addContratosCobranca(ContratoCobranca contratosCobranca) {
		getContratosCobranca().add(contratosCobranca);
		contratosCobranca.setProvedor(this);

		return contratosCobranca;
	}

	public ContratoCobranca removeContratosCobranca(ContratoCobranca contratosCobranca) {
		getContratosCobranca().remove(contratosCobranca);
		contratosCobranca.setProvedor(null);

		return contratosCobranca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoProvedor == null) ? 0 : codigoProvedor.hashCode());
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
		Provedor other = (Provedor) obj;
		if (codigoProvedor == null) {
			if (other.codigoProvedor != null)
				return false;
		} else if (!codigoProvedor.equals(other.codigoProvedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Provedor [id=" + id + ", codigoProvedor=" + codigoProvedor
				+ "]";
	}

}