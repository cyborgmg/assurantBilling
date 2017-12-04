package br.com.delphos.billing.tentativas;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.enumeracoes.TipoEvento;

import java.util.Date;


/**
 * The persistent class for the EVENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Evento.findAll", query="SELECT e FROM Evento e")
@SequenceGenerator(sequenceName = "SEQ_ID_EVENTO", name = "SEQ_ID_EVENTO", allocationSize = 1)
public class Evento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_EVENTO")
	@Column(name="ID_EVENTO")
	private Long id;

	@Column(name="CODIGO_EVENTO")
	private String codigoEvento;

	private String complemento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EVENTO")
	private Date dataEvento;

	//bi-directional many-to-one association to Tentativa
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_TENTATIVA")
	private Tentativa tentativa;

	public Evento() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEvento getTipoEvento() {
		return TipoEvento.buscarPorValor(this.codigoEvento);
	}
	
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.codigoEvento = tipoEvento.getValor();
	}
	
	// TODO Verificar a possibilidade de excluir este método e usar o (get|set)TipoEvento em seu lugar
	public String getCodigoEvento() {
		return this.codigoEvento;
	}
	
	// TODO Verificar a possibilidade de excluir este método e usar o (get|set)TipoEvento em seu lugar
	public void setCodigoEvento(String codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getComplemento() {
		return this.complemento != null ? this.complemento : "";
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataEvento() {
		return this.dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Tentativa getTentativa() {
		return this.tentativa;
	}

	public void setTentativa(Tentativa tentativa) {
		this.tentativa = tentativa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoEvento == null) ? 0 : codigoEvento.hashCode());
		result = prime * result
				+ ((dataEvento == null) ? 0 : dataEvento.hashCode());
		result = prime * result
				+ ((tentativa == null) ? 0 : tentativa.hashCode());
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
		Evento other = (Evento) obj;
		if (codigoEvento == null) {
			if (other.codigoEvento != null)
				return false;
		} else if (!codigoEvento.equals(other.codigoEvento))
			return false;
		if (dataEvento == null) {
			if (other.dataEvento != null)
				return false;
		} else if (!dataEvento.equals(other.dataEvento))
			return false;
		if (tentativa == null) {
			if (other.tentativa != null)
				return false;
		} else if (!tentativa.equals(other.tentativa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", codigoEvento=" + codigoEvento
				+ ", dataEvento=" + dataEvento + ", tentativa=" + tentativa
				+ "]";
	}
	
}