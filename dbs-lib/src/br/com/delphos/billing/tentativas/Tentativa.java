package br.com.delphos.billing.tentativas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.enumeracoes.TipoTentativa;


/**
 * The persistent class for the TENTATIVA database table.
 * 
 */
@Entity
@NamedQuery(name="Tentativa.findAll", query="SELECT t FROM Tentativa t")
@SequenceGenerator(sequenceName = "SEQ_ID_TENTATIVA", name = "SEQ_ID_TENTATIVA", allocationSize = 1)
public class Tentativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_TENTATIVA")
	@Column(name="ID_TENTATIVA")
	private Long id;

	@Column(name="CODIGO_RETORNO_ADQUIRENTE")
	private String codigoRetornoAdquirente;

	@Column(name="CODIGO_RETORNO_PROVEDOR")
	private String codigoRetornoProvedor;

	@Column(name="CODIGO_TRANSACAO_ADQUIRENTE")
	private String codigoTransacaoAdquirente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_TENTATIVA")
	private Date dataTentativa;

	@Column(name="MENSAGEM_RETORNO_ADQUIRENTE")
	private String mensagemRetornoAdquirente;

	@Column(name="MENSAGEM_RETORNO_PROVEDOR")
	private String mensagemRetornoProvedor;

	@Column(name="NUMERO_TENTATIVA")
	private int numeroTentativa;

	@Column(name="TIPO_TENTATIVA")
	private String tipoTentativa;
	
	//bi-directional many-to-one association to Evento
	@OneToMany(mappedBy="tentativa")
	@OrderBy("dataEvento, id")
	private List<Evento> eventos;

	//bi-directional many-to-one association to Cobranca
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_COBRANCA")
	private Cobranca cobranca;

	public Tentativa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoRetornoAdquirente() {
		return this.codigoRetornoAdquirente;
	}

	public void setCodigoRetornoAdquirente(String codigoRetornoAdquirente) {
		this.codigoRetornoAdquirente = codigoRetornoAdquirente;
	}

	public String getCodigoRetornoProvedor() {
		return this.codigoRetornoProvedor;
	}

	public void setCodigoRetornoProvedor(String codigoRetornoProvedor) {
		this.codigoRetornoProvedor = codigoRetornoProvedor;
	}

	public String getCodigoTransacaoAdquirente() {
		return this.codigoTransacaoAdquirente;
	}

	public void setCodigoTransacaoAdquirente(String codigoTransacaoAdquirente) {
		this.codigoTransacaoAdquirente = codigoTransacaoAdquirente;
	}

	public Date getDataTentativa() {
		return this.dataTentativa;
	}

	public void setDataTentativa(Date dataTentativa) {
		this.dataTentativa = dataTentativa;
	}

	public String getMensagemRetornoAdquirente() {
		return this.mensagemRetornoAdquirente;
	}

	public void setMensagemRetornoAdquirente(String mensagemRetornoAdquirente) {
		this.mensagemRetornoAdquirente = mensagemRetornoAdquirente;
	}

	public String getMensagemRetornoProvedor() {
		return this.mensagemRetornoProvedor;
	}

	public void setMensagemRetornoProvedor(String mensagemRetornoProvedor) {
		this.mensagemRetornoProvedor = mensagemRetornoProvedor;
	}

	public int getNumeroTentativa() {
		return this.numeroTentativa;
	}

	public void setNumeroTentativa(int numeroTentativa) {
		this.numeroTentativa = numeroTentativa;
	}

	public TipoTentativa getTipoTentativa() {
		return TipoTentativa.buscarPorValor(tipoTentativa);
	}

	public void setTipoTentativa(TipoTentativa tipoTentativa) {
		this.tipoTentativa = tipoTentativa.getValor();
	}

	public List<Evento> getEventos() {
		return this.eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Evento addEvento(Evento evento) {
		getEventos().add(evento);
		evento.setTentativa(this);

		return evento;
	}

	public Evento removeEvento(Evento evento) {
		getEventos().remove(evento);
		evento.setTentativa(null);

		return evento;
	}

	public Cobranca getCobranca() {
		return this.cobranca;
	}

	public void setCobranca(Cobranca cobranca) {
		this.cobranca = cobranca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cobranca == null) ? 0 : cobranca.hashCode());
		result = prime * result + numeroTentativa;
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
		Tentativa other = (Tentativa) obj;
		if (cobranca == null) {
			if (other.cobranca != null)
				return false;
		} else if (!cobranca.equals(other.cobranca))
			return false;
		if (numeroTentativa != other.numeroTentativa)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tentativa [id=" + id + ", numeroTentativa=" + numeroTentativa
				+ ", cobranca=" + cobranca + "]";
	}

}