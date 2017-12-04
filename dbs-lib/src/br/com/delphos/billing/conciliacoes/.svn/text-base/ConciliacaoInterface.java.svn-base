package br.com.delphos.billing.conciliacoes;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.enumeracoes.TipoConciliacaoInterface;

import java.math.BigDecimal;
import java.util.Date;
/**
 * The persistent class for the CONCILIACAO_INTERFACE database table.
 * 
 */
@Entity
@Table(name="CONCILIACAO_INTERFACE")
@SequenceGenerator(sequenceName = "SEQ_ID_CONCILIACAO_INTERFACE", name = "SEQ_ID_CONCILIACAO_INTERFACE", allocationSize = 1)
@NamedQuery(name="ConciliacaoInterface.findAll", query="SELECT c FROM ConciliacaoInterface c")
public class ConciliacaoInterface implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_CONCILIACAO_INTERFACE")
	@Column(name="ID_CONCILIACAO_INTERFACE")
	private Long id;

	@Column(name="AGENCIA")
	private String agencia;

	@Column(name="BANCO")
	private String banco;

	@Column(name="CODIGO_AFILIACAO")
	private String codigoAfiliacao;

	@Column(name="CODIGO_CLIENTE")
	private String codigoCliente;

	@Column(name="CODIGO_EMPRESA")
	private String codigoEmpresa;

	@Column(name="CODIGO_PRODUTO")
	private String codigoProduto;

	@Column(name="CODIGO_SISTEMA")
	private String codigoSistema;

	@Column(name="CONTA")
	private String conta;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_AUTORIZACAO")
	private Date dataAutorizacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_BANCO")
	private Date dataBanco;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_GERACAO")
	private Date dataGeracao;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_CONTABIL")
	private Date dataContabil;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PAGAMENTO")
	private Date dataPagamento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PROCESSAMENTO")
	private Date dataProcessamento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_VENCIMENTO")
	private Date dataVencimento;
	
	@Column(name="IDECOBR")
	private BigDecimal idecobr;

	@Column(name="IDECOMPBANCO")
	private BigDecimal ideCompBanco;

	@Column(name="IDEFACT")
	private BigDecimal ideFact;

	@Column(name="MENSAGEM_PROCESSAMENTO")
	private String mensagemProcessamento;

	@Column(name="NUMERO_PARCELA")
	private Integer numeroParcela;

	@Column(name="NUMOBLIG")
	private BigDecimal numOblig;

	@Column(name="STATUS_PROCESSAMENTO")
	private String statusProcessamento = StatusConciliacaoInterface.VALIDA.getValor();

	@Column(name="TIPO_REGISTRO")
	private String tipoRegistro;

	@Column(name="VALOR_COBRANCA")
	private BigDecimal valorCobranca;

	@Column(name="VALOR_TARIFA")
	private BigDecimal valorTarifa;
	
	@Column(name="NOME_BANDEIRA")
	private String nomeBandeira;
	
	@Column(name="NSU")
	private String nsu;
	
	public ConciliacaoInterface() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAgencia() {
		return this.agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCodigoAfiliacao() {
		return this.codigoAfiliacao;
	}

	public void setCodigoAfiliacao(String codigoAfiliacao) {
		this.codigoAfiliacao = codigoAfiliacao;
	}

	public String getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoEmpresa() {
		return this.codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getCodigoProduto() {
		return this.codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getCodigoSistema() {
		return this.codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public String getConta() {
		return this.conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Date getDataAutorizacao() {
		return this.dataAutorizacao;
	}

	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public Date getDataBanco() {
		return this.dataBanco;
	}

	public void setDataBanco(Date dataBanco) {
		this.dataBanco = dataBanco;
	}

	public Date getDataGeracao() {
		return this.dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public Date getDataPagamento() {
		return this.dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return this.dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public BigDecimal getIdecobr() {
		return this.idecobr;
	}

	public void setIdecobr(BigDecimal idecobr) {
		this.idecobr = idecobr;
	}

	public BigDecimal getIdeCompBanco() {
		return this.ideCompBanco;
	}

	public void setIdeCompBanco(BigDecimal idecompbanco) {
		this.ideCompBanco = idecompbanco;
	}

	public BigDecimal getIdeFact() {
		return this.ideFact;
	}

	public void setIdeFact(BigDecimal idefact) {
		this.ideFact = idefact;
	}

	public String getMensagemProcessamento() {
		return this.mensagemProcessamento;
	}

	public void setMensagemProcessamento(String mensagemProcessamento) {
		this.mensagemProcessamento = mensagemProcessamento;
	}

	public Integer getNumeroParcela() {
		return this.numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getNumOblig() {
		return this.numOblig;
	}

	public void setNumOblig(BigDecimal numoblig) {
		this.numOblig = numoblig;
	}

	public TipoConciliacaoInterface getTipo() {
		return TipoConciliacaoInterface.buscarPorValor(this.tipoRegistro);
	}
	
	public void setTipo(TipoConciliacaoInterface tipo) {
		this.tipoRegistro = tipo.getValor();
	}
	
	public BigDecimal getValorCobranca() {
		return this.valorCobranca;
	}

	public void setValorCobranca(BigDecimal valorCobranca) {
		this.valorCobranca = valorCobranca;
	}

	public BigDecimal getValorTarifa() {
		return this.valorTarifa;
	}

	public void setValorTarifa(BigDecimal valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

	public Date getDataContabil() {
		return dataContabil;
	}

	public void setDataContabil(Date dataContabil) {
		this.dataContabil = dataContabil;
	}

	public StatusConciliacaoInterface getStatus() {
		return StatusConciliacaoInterface.buscarPorValor(statusProcessamento);
	}

	public void setStatus(StatusConciliacaoInterface statusProcessamento) {
		this.statusProcessamento = statusProcessamento.getValor();
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ConciliacaoInterface other = (ConciliacaoInterface) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConciliacaoInterface [id=" + id + "]";
	}

}