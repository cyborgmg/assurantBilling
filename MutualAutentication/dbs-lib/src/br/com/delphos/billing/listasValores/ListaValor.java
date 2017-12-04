package br.com.delphos.billing.listasValores;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.enumeracoes.TipoListaValor;

import java.util.List;


/**
 * The persistent class for the LISTA_VALOR database table.
 * 
 */
@Entity
@Table(name="LISTA_VALOR")
@NamedQuery(name="ListaValor.findAll", query="SELECT l FROM ListaValor l")
@SequenceGenerator(sequenceName = "SEQ_ID_LISTA_VALOR", name = "SEQ_ID_LISTA_VALOR", allocationSize = 1)
public class ListaValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_LISTA_VALOR")
	@Column(name="ID_LISTA_VALOR")
	private Long id;

	private String codigo;

	private String descricao;

	private String status;

	@Column(name="TPO_USO")
	private String tipoUso;

	//bi-directional many-to-one association to ItemLista
	@OneToMany(mappedBy="listaValor")
	private List<ItemLista> itensLista;

	public ListaValor() {
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TipoListaValor getTipoListaValor() {
		return TipoListaValor.buscarPorValor(this.tipoUso);
	}
	
	public void setTipoListaValor(TipoListaValor tipoListaValor) {
		this.tipoUso = tipoListaValor.getValor();
	}
	
	// TODO Verificar a possibilidade de excluir este método e usar o (get|set)TipoListaValor em seu lugar
	public String getTipoUso() {
		return this.tipoUso;
	}
	
	// TODO Verificar a possibilidade de excluir este método e usar o (get|set)TipoListaValor em seu lugar
	public void setTipoUso(String tipoUso) {
		this.tipoUso = tipoUso;
	}

	public List<ItemLista> getItensLista() {
		return this.itensLista;
	}

	public void setItensLista(List<ItemLista> itensLista) {
		this.itensLista = itensLista;
	}

	public ItemLista addItensLista(ItemLista itensLista) {
		getItensLista().add(itensLista);
		itensLista.setListaValor(this);

		return itensLista;
	}

	public ItemLista removeItensLista(ItemLista itensLista) {
		getItensLista().remove(itensLista);
		itensLista.setListaValor(null);

		return itensLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		ListaValor other = (ListaValor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListaValor [id=" + id + ", codigo=" + codigo + "]";
	}

}