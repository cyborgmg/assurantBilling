package br.com.delphos.billing.listasValores;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ITEM_LISTA database table.
 * 
 */
@Entity
@Table(name="ITEM_LISTA")
@NamedQuery(name="ItemLista.findAll", query="SELECT i FROM ItemLista i")
@SequenceGenerator(sequenceName = "SEQ_ID_ITEM_LISTA", name = "SEQ_ID_ITEM_LISTA", allocationSize = 1)
public class ItemLista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ITEM_LISTA")
	@Column(name="ID_ITEM_LISTA")
	private Long id;

	private String codigo;

	private String descricao;
	
	private String status;

	//bi-directional many-to-one association to ListaValor
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_LISTA_VALOR")
	private ListaValor listaValor;

	public ItemLista() {
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ListaValor getListaValor() {
		return this.listaValor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setListaValor(ListaValor listaValor) {
		this.listaValor = listaValor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((listaValor == null) ? 0 : listaValor.hashCode());
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
		ItemLista other = (ItemLista) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (listaValor == null) {
			if (other.listaValor != null)
				return false;
		} else if (!listaValor.equals(other.listaValor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemLista [id=" + id + ", codigo=" + codigo + ", listaValor="
				+ listaValor + "]";
	}

}