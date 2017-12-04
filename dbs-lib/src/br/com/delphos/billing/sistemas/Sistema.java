package br.com.delphos.billing.sistemas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.delphos.billing.vendas.Venda;


/**
 * The persistent class for the SISTEMA database table.
 * 
 */
@Entity
@NamedQuery(name="Sistema.findAll", query="SELECT s FROM Sistema s")
@SequenceGenerator(sequenceName = "SEQ_ID_SISTEMA", name = "SEQ_ID_SISTEMA", allocationSize = 1)
public class Sistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_SISTEMA")
	@Column(name="ID_SISTEMA")
	private Long id;

	private String codigo;

	@Column(name="SIGLA_SISTEMA_ENVIO")
	private String codigoSistemaEnvio;

	@Column(name = "DESCRICAO_SISTEMA")
	private String descricao;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="sistema")
	private List<Venda> vendas;

	public Sistema() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoSistemaEnvio() {
		return this.codigoSistemaEnvio;
	}

	public void setCodigoSistemaEnvio(String codigoSistemaEnvio) {
		this.codigoSistemaEnvio = codigoSistemaEnvio;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setSistema(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setSistema(null);

		return venda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime
				* result
				+ ((codigoSistemaEnvio == null) ? 0 : codigoSistemaEnvio
						.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((vendas == null) ? 0 : vendas.hashCode());
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
		Sistema other = (Sistema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoSistemaEnvio == null) {
			if (other.codigoSistemaEnvio != null)
				return false;
		} else if (!codigoSistemaEnvio.equals(other.codigoSistemaEnvio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (vendas == null) {
			if (other.vendas != null)
				return false;
		} else if (!vendas.equals(other.vendas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sistema [id=" + id + ", codigo=" + codigo
				+ ", codigoSistemaEnvio=" + codigoSistemaEnvio + ", descricao="
				+ descricao +"]";
	}

}