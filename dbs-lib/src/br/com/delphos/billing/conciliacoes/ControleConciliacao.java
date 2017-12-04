package br.com.delphos.billing.conciliacoes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.enumeracoes.TipoMensagemConciliacao;


/**
 * The persistent class for the CONTROLE_CONCILIACAO database table.
 * 
 */
@Entity
@Table(name="CONTROLE_CONCILIACAO")
@SequenceGenerator(sequenceName = "SEQ_ID_CONTROLE_CONCILIACAO", name = "SEQ_ID_CONTROLE_CONCILIACAO", allocationSize = 1)
@NamedQuery(name="ControleConciliacao.findAll", query="SELECT c FROM ControleConciliacao c")
public class ControleConciliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_CONTROLE_CONCILIACAO")
	@Column(name="ID_CONTROLE_CONCILIACAO")
	private Long id;
	
	@Column(name = "adquirente")
	private String adquirente;

	@Lob
	@Column(name = "arquivo")
	private byte[] arquivo;

	@Column(name="ARQUIVO_VAZIO")
	private String arquivoVazio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_IMPORTACAO_ARQUIVO")
	private Date dataImportacaoArquivo;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_MOVIMENTO")
	private Date dataMovimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_PROCESSAMENTO_ARQUIVO")
	private Date dataProcessamentoArquivo;

	@Column(name="MENSAGEM_VALIDACAO")
	private String mensagemValidacao;

	@Column(name="NUMERO_REPROCESSAMENTO")
	private Long numeroReprocessamento;

	@Column(name = "status")
	private String status;

	@Column(name="VALOR_ARQUIVO")
	private BigDecimal valorArquivo;

	@Column(name="VALOR_DEPOSITO")
	private BigDecimal valorDeposito;

//	@JoinColumn(name = "adquirente", referencedColumnName = "nome", insertable = false, updatable = false)
//	private Adquirente adquirenteCadastrada;
	
	public ControleConciliacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idControleConciliacao) {
		this.id = idControleConciliacao;
	}

	public String getAdquirente() {
		return this.adquirente;
	}

	public void setAdquirente(String adquirente) {
		this.adquirente = adquirente;
	}

	public byte[] getArquivo() {
		return this.arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public boolean isArquivoVazio() {
		return this.arquivoVazio != null && this.arquivoVazio.equals("S");
	}

	public void setArquivoVazio(boolean arquivoVazio) {
		this.arquivoVazio = arquivoVazio ? "S" : "N";
	}

	public Date getDataImportacaoArquivo() {
		return this.dataImportacaoArquivo;
	}

	public void setDataImportacaoArquivo(Date dataImportacaoArquivo) {
		this.dataImportacaoArquivo = dataImportacaoArquivo;
	}

	public Date getDataMovimento() {
		return this.dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Date getDataProcessamentoArquivo() {
		return this.dataProcessamentoArquivo;
	}

	public void setDataProcessamentoArquivo(Date dataProcessamentoArquivo) {
		this.dataProcessamentoArquivo = dataProcessamentoArquivo;
	}

	public TipoMensagemConciliacao getTipoMensagemConciliacao() {
		return TipoMensagemConciliacao.buscarPorValor(this.mensagemValidacao);
	}

	public void setTipoMensagemConciliacao(TipoMensagemConciliacao mensagemValidacao) {
		this.mensagemValidacao = mensagemValidacao.getValor();
	}

	public Long getNumeroReprocessamento() {
		return this.numeroReprocessamento;
	}

	public void setNumeroReprocessamento(Long numeroReprocessamento) {
		this.numeroReprocessamento = numeroReprocessamento;
	}

	public StatusControleConciliacao getStatus() {
		return StatusControleConciliacao.buscarPorValor(this.status);
	}

	public void setStatus(StatusControleConciliacao status) {
		this.status = status.getValor();
	}

	public BigDecimal getValorArquivo() {
		return this.valorArquivo;
	}

	public void setValorArquivo(BigDecimal valorArquivo) {
		this.valorArquivo = valorArquivo;
	}

	public BigDecimal getValorDeposito() {
		return this.valorDeposito;
	}

	public void setValorDeposito(BigDecimal valorDeposito) {
		this.valorDeposito = valorDeposito;
	}

//	public Adquirente getAdquirenteCadastrada() {
//		return adquirenteCadastrada;
//	}
//
//	public void setAdquirenteCadastrada(Adquirente adquirenteCadastrada) {
//		this.adquirenteCadastrada = adquirenteCadastrada;
//	}

	@Override
	public String toString() {
		return "ControleConciliacao [id=" + id + ", adquirente=" + adquirente + ", dataMovimento=" + dataMovimento
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adquirente == null) ? 0 : adquirente.hashCode());
		result = prime * result + ((dataMovimento == null) ? 0 : dataMovimento.hashCode());
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
		ControleConciliacao other = (ControleConciliacao) obj;
		if (adquirente == null) {
			if (other.adquirente != null)
				return false;
		} else if (!adquirente.equals(other.adquirente))
			return false;
		if (dataMovimento == null) {
			if (other.dataMovimento != null)
				return false;
		} else if (!dataMovimento.equals(other.dataMovimento))
			return false;
		return true;
	}

	public boolean isArquivoImportado() {
		return dataImportacaoArquivo != null 
				&& getStatus() != StatusControleConciliacao.ReprocessamentoSolicitado;
	}

}