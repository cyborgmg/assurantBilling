package br.com.delphos.billing.cobrancas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

import br.com.delphos.billing.conciliacoes.EventoConciliacao;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.StatusCobranca;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.vendas.Venda;


/**
 * The persistent class for the COBRANCA database table.
 * 
 */
@Entity
@NamedQuery(name="Cobranca.findAll", query="SELECT c FROM Cobranca c")
@SequenceGenerator(sequenceName = "SEQ_ID_COBRANCA", name = "SEQ_ID_COBRANCA", allocationSize = 1)
public class Cobranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_COBRANCA")
	@Column(name="ID_COBRANCA")
	private Long id;

	@Column(name="CODIGO_AUTORIZACAO")
	private String codigoAutorizacao;

	@Column(name="CODIGO_BANDEIRA_CARTAO")
	private String codigoBandeiraCartao;

	@Column(name="CODIGO_PEDIDO_PROVEDOR")
	private String codigoPedidoProvedor;

	@Column(name="CODIGO_TRANSACAO_ADQUIRENTE")
	private String codigoTransacaoAdquirente;

	@Column(name="CODIGO_TRANSACAO_PROVEDOR")
	private String codigoTransacaoProvedor;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_COBRANCA")
	private Date dataCobranca;

	@Column(name="ID_REQUISICAO_PROVEDOR")
	private String idRequisicaoProvedor;

	@Column(name="NUMERO_COMPROVANTE_VENDA")
	private String numeroComprovanteVenda;

	@Column(name="NUMERO_PARCELA")
	private int numeroParcela;

	@Column(name="NUMERO_ULTIMA_TENTATIVA")
	private int numeroUltimaTentativa;

	@Column(name="STATUS_COBRANCA")
	private String statusCobranca;

	@Column(name="ULTIMOS_DIGITOS_CARTAO")
	private String ultimosDigitosCartao;

	@Column(name="VALOR_COBRANCA")
	private BigDecimal valorCobranca;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ULTIMA_ALTERACAO")
	private Date dataUltimaAlteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ESTORNO")
	private Date dataEstorno;
	
	@Column(name = "VALOR_ESTORNO")
	private BigDecimal valorEstorno;
	
	//bi-directional many-to-one association to ContratoCobranca
	@ManyToOne
	@JoinColumn(name="ID_CONTRATO_COBRANCA")
	private ContratoCobranca contratoCobranca;

	//bi-directional many-to-one association to ExportacaoCobranca
	//@ManyToOne
	//@JoinColumn(name="ID_EXPORTACAO_COBRANCA")
	//private ExportacaoCobranca exportacaoCobranca;

	//bi-directional many-to-one association to ExportacaoConciliacao
	//@ManyToOne
	//@JoinColumn(name="ID_EXPORTACAO_CONCILIACAO")
	//private ExportacaoConciliacao exportacaoConciliacao;

	//bi-directional many-to-one association to Venda
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_VENDA")
	private Venda venda;

	//bi-directional many-to-one association to Tentativa
	@OneToMany(mappedBy="cobranca", fetch = FetchType.LAZY)
	@OrderBy("numeroTentativa asc")
	private List<Tentativa> tentativas;

	@OneToMany(mappedBy = "cobranca")
	private List<EventoConciliacao> eventosConciliacao;
	
	public Cobranca() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoAutorizacao() {
		return this.codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public String getCodigoBandeiraCartao() {
		return this.codigoBandeiraCartao;
	}

	public void setCodigoBandeiraCartao(String codigoBandeiraCartao) {
		this.codigoBandeiraCartao = codigoBandeiraCartao;
	}

	public String getCodigoPedidoProvedor() {
		return this.codigoPedidoProvedor;
	}

	public void setCodigoPedidoProvedor(String codigoPedidoProvedor) {
		this.codigoPedidoProvedor = codigoPedidoProvedor;
	}

	public String getCodigoTransacaoAdquirente() {
		return this.codigoTransacaoAdquirente;
	}

	public void setCodigoTransacaoAdquirente(String codigoTransacaoAdquirente) {
		this.codigoTransacaoAdquirente = codigoTransacaoAdquirente;
	}

	public String getCodigoTransacaoProvedor() {
		return this.codigoTransacaoProvedor;
	}

	public void setCodigoTransacaoProvedor(String codigoTransacaoProvedor) {
		this.codigoTransacaoProvedor = codigoTransacaoProvedor;
	}

	public Date getDataCobranca() {
		return this.dataCobranca;
	}

	public void setDataCobranca(Date dataCobranca) {
		this.dataCobranca = dataCobranca;
	}

	public String getIdRequisicaoProvedor() {
		return this.idRequisicaoProvedor;
	}

	public void setIdRequisicaoProvedor(String idRequisicaoProvedor) {
		this.idRequisicaoProvedor = idRequisicaoProvedor;
	}

	public String getNumeroComprovanteVenda() {
		return this.numeroComprovanteVenda;
	}

	public void setNumeroComprovanteVenda(String numeroComprovanteVenda) {
		this.numeroComprovanteVenda = numeroComprovanteVenda;
	}

	public int getNumeroParcela() {
		return this.numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public int getNumeroUltimaTentativa() {
		return this.numeroUltimaTentativa;
	}

	public void setNumeroUltimaTentativa(int numeroUltimaTentativa) {
		this.numeroUltimaTentativa = numeroUltimaTentativa;
	}

	public StatusCobranca getStatusCobranca() {
		return StatusCobranca.buscarPorValor(this.statusCobranca);
	}

	public void setStatusCobranca(StatusCobranca statusCobranca) {
		this.statusCobranca = statusCobranca.getValor();
	}

	public String getUltimosDigitosCartao() {
		return this.ultimosDigitosCartao;
	}

	public void setUltimosDigitosCartao(String ultimosDigitosCartao) {
		this.ultimosDigitosCartao = ultimosDigitosCartao;
	}

	public BigDecimal getValorCobranca() {
		return this.valorCobranca;
	}

	public void setValorCobranca(BigDecimal valorCobranca) {
		this.valorCobranca = valorCobranca;
	}

	public ContratoCobranca getContratoCobranca() {
		return this.contratoCobranca;
	}

	public void setContratoCobranca(ContratoCobranca contratoCobranca) {
		this.contratoCobranca = contratoCobranca;
	}

//	public ExportacaoCobranca getExportacaoCobranca() {
//		return this.exportacaoCobranca;
//	}
//
//	public void setExportacaoCobranca(ExportacaoCobranca exportacaoCobranca) {
//		this.exportacaoCobranca = exportacaoCobranca;
//	}
//
//	public ExportacaoConciliacao getExportacaoConciliacao() {
//		return this.exportacaoConciliacao;
//	}
//
//	public void setExportacaoConciliacao(ExportacaoConciliacao exportacaoConciliacao) {
//		this.exportacaoConciliacao = exportacaoConciliacao;
//	}

	public Venda getVenda() {
		return this.venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public List<Tentativa> getTentativas() {
		return this.tentativas;
	}

	public Date getDataEstorno() {
		return dataEstorno;
	}

	public void setDataEstorno(Date dataEstorno) {
		this.dataEstorno = dataEstorno;
	}

	public BigDecimal getValorEstorno() {
		return valorEstorno;
	}

	public void setValorEstorno(BigDecimal valorEstornado) {
		this.valorEstorno = valorEstornado;
	}

	public void setTentativas(List<Tentativa> tentativas) {
		this.tentativas = tentativas;
	}

	public Tentativa addTentativa(Tentativa tentativa) {
		getTentativas().add(tentativa);
		tentativa.setCobranca(this);

		return tentativa;
	}

	public Tentativa removeTentativa(Tentativa tentativa) {
		getTentativas().remove(tentativa);
		tentativa.setCobranca(null);

		return tentativa;
	}

	public List<EventoConciliacao> getEventosConciliacao() {
		return eventosConciliacao;
	}

	public void setEventosConciliacao(List<EventoConciliacao> eventosConciliacao) {
		this.eventosConciliacao = eventosConciliacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numeroParcela;
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		Cobranca other = (Cobranca) obj;
		if (numeroParcela != other.numeroParcela)
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cobranca [id=" + id + ", numeroParcela=" + numeroParcela
				+ ", venda=" + venda + ", dataUltimaAlteracao=" + dataUltimaAlteracao + "]";
	}

}