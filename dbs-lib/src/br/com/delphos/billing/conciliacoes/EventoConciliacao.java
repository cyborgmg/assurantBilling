package br.com.delphos.billing.conciliacoes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoEvento;


/**
 * The persistent class for the EVENTO_CONCILIACAO database table.
 * 
 */
@Entity
@Table(name="EVENTO_CONCILIACAO")
@NamedQuery(name="EventoConciliacao.findAll", query="SELECT e FROM EventoConciliacao e")
@SequenceGenerator(sequenceName = "SEQ_ID_EVENTO_CONCILIACAO", name = "SEQ_ID_EVENTO_CONCILIACAO", allocationSize = 1)
public class EventoConciliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_EVENTO_CONCILIACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_EVENTO_CONCILIACAO")
	private Long id;

	@Column(name="CODIGO_EVENTO")
	private String codigoEvento;

	@Column(name = "complemento")
	private String complemento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EVENTO")
	private Date dataEvento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_COBRANCA")
	private Cobranca cobranca;

	@Column(name="NUMERO_PARCELA_LOJA")
	private Integer numeroParcelaLoja;

	public EventoConciliacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idEventoConciliacao) {
		this.id = idEventoConciliacao;
	}

	public TipoEvento getTipo() {
		return TipoEvento.buscarPorValor(this.codigoEvento);
	}
	
	public void setTipo(TipoEvento tipoEvento) {
		this.codigoEvento = tipoEvento.getValor();
	}
	
	public String getComplemento() {
		return this.complemento;
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

	public Cobranca getCobranca() {
		return cobranca;
	}

	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

	public Integer getNumeroParcelaLoja() {
		return this.numeroParcelaLoja;
	}

	public void setNumeroParcelaLoja(Integer numeroParcelaLoja) {
		this.numeroParcelaLoja = numeroParcelaLoja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cobranca == null) ? 0 : cobranca.hashCode());
		result = prime * result + ((codigoEvento == null) ? 0 : codigoEvento.hashCode());
		result = prime * result + ((dataEvento == null) ? 0 : dataEvento.hashCode());
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
		EventoConciliacao other = (EventoConciliacao) obj;
		if (cobranca == null) {
			if (other.cobranca != null)
				return false;
		} else if (!cobranca.equals(other.cobranca))
			return false;
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
		return true;
	}

	@Override
	public String toString() {
		return "EventoConciliacao [id=" + id + ", codigoEvento=" + codigoEvento + ", dataEvento=" + dataEvento
				+ ", cobranca=" + cobranca + "]";
	}

}