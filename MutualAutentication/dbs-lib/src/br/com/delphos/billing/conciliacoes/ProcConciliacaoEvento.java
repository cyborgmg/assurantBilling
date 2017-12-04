package br.com.delphos.billing.conciliacoes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.StatusProcConciliacaoEvento;
import br.com.delphos.billing.util.Mensagens;


/**
 * The persistent class for the PROC_CONCILIACAO_EVENTO database table.
 * 
 */
@Entity
@Table(name="PROC_CONCILIACAO_EVENTO")
public class ProcConciliacaoEvento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROC_CONCILIACAO_EVENTO_IDPROCCONCILIACAOEVENTO_GENERATOR", sequenceName="SEQ_ID_PROC_CONCILIACAO_EVENTO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROC_CONCILIACAO_EVENTO_IDPROCCONCILIACAOEVENTO_GENERATOR")
	@Column(name="ID_PROC_CONCILIACAO_EVENTO")
	private Long id;

	@Column(name = "account")
	private String account;

	@Column(name = "acquireradjustcode")
	private Integer acquirerAdjustCode;

	@Column(name = "acquireradjustdescription")
	private String acquirerAdjustDescription;

	@Column(name = "affiliationcode")
	private String affiliationCode;

	@Column(name = "agency")
	private Integer agency;

	@Column(name = "anticipationoperationnumber")
	private Integer anticipationOperationNumber;

	@Column(name = "bank")
	private Integer bank;

	@Column(name = "category")
	private String category;

	@Column(name = "categoryid")
	private Integer categoryId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_PROCESSAMENTO")
	private Date dataProcessamento;

	@Temporal(TemporalType.DATE)
	@Column(name="eventdate")
	private Date eventDate;
	
	@Column(name="eventid")
	private String eventId;

	@Column(name = "grossamount")
	private BigDecimal grossAmount;

	@Column(name="MENSAGEM_PROCESSAMENTO")
	private String mensagemProcessamento;

	@Column(name = "netamount")
	private BigDecimal netAmount;

	@Temporal(TemporalType.DATE)
	@Column(name = "originalpaymentdate")
	private Date originalPaymentDate;

	@Column(name="STATUS_PROCESSAMENTO")
	private String statusProcessamento;

	@Column(name="TAG_REGISTRO")
	private String tagRegistro;

	@Column(name = "taxamount")
	private BigDecimal taxAmount;

	@Column(name = "transactioninstallment")
	private Integer transactionInstallment;

	@Column(name="\"TYPE\"")
	private String type;

	@Column(name = "typeid")
	private Integer typeId;

	//bi-directional many-to-one association to ProcessamentoConciliacao
	@ManyToOne
	@JoinColumn(name="ID_PROCESSAMENTO_CONCILIACAO")
	private ProcessamentoConciliacao processamentoConciliacao;

	public ProcConciliacaoEvento() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idProcConciliacaoEvento) {
		this.id = idProcConciliacaoEvento;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getAcquirerAdjustCode() {
		return this.acquirerAdjustCode;
	}

	public void setAcquirerAdjustCode(Integer acquireradjustcode) {
		this.acquirerAdjustCode = acquireradjustcode;
	}

	public String getAcquirerAdjustDescription() {
		return this.acquirerAdjustDescription;
	}

	public void setAcquirerAdjustDescription(String acquireradjustdescription) {
		this.acquirerAdjustDescription = acquireradjustdescription;
	}

	public String getAffiliationCode() {
		return this.affiliationCode;
	}

	public void setAffiliationCode(String affiliationcode) {
		this.affiliationCode = affiliationcode;
	}

	public Integer getAgency() {
		return this.agency;
	}

	public void setAgency(Integer agency) {
		this.agency = agency;
	}

	public Integer getAnticipationOperationNumber() {
		return this.anticipationOperationNumber;
	}

	public void setAnticipationOperationNumber(Integer anticipationoperationnumber) {
		this.anticipationOperationNumber = anticipationoperationnumber;
	}

	public Integer getBank() {
		return this.bank;
	}

	public void setBank(Integer bank) {
		this.bank = bank;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryid) {
		this.categoryId = categoryid;
	}

	public Date getDataProcessamento() {
		return this.dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Date getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventId() {
		return this.eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public BigDecimal getGrossAmount() {
		return this.grossAmount;
	}

	public void setGrossAmount(BigDecimal grossamount) {
		this.grossAmount = grossamount;
	}

	public String getMensagemProcessamento() {
		return this.mensagemProcessamento;
	}

	public void setMensagemProcessamento(String mensagemProcessamento) {
		this.mensagemProcessamento = mensagemProcessamento;
	}

	public void setCodigoMensagemProcessamento(CodigoMensagem mensagemProcessamento) {
		this.mensagemProcessamento = Mensagens.get(mensagemProcessamento);
	}

	public BigDecimal getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(BigDecimal netamount) {
		this.netAmount = netamount;
	}

	public Date getOriginalPaymentDate() {
		return this.originalPaymentDate;
	}

	public void setOriginalPaymentDate(Date originalpaymentdate) {
		this.originalPaymentDate = originalpaymentdate;
	}

	public StatusProcConciliacaoEvento getStatus() {
		return StatusProcConciliacaoEvento.buscarPorValor(this.statusProcessamento);
	}
	
	public void setStatus(StatusProcConciliacaoEvento status) {
		this.statusProcessamento = status.getValor();
	}
	
	public String getTagRegistro() {
		return this.tagRegistro;
	}

	public void setTagRegistro(String tagRegistro) {
		this.tagRegistro = tagRegistro;
	}

	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	public void setTaxAmount(BigDecimal taxamount) {
		this.taxAmount = taxamount;
	}

	public Integer getTransactionInstallment() {
		return this.transactionInstallment;
	}

	public void setTransactionInstallment(Integer transactioninstallment) {
		this.transactionInstallment = transactioninstallment;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeid) {
		this.typeId = typeid;
	}

	public ProcessamentoConciliacao getProcessamentoConciliacao() {
		return this.processamentoConciliacao;
	}

	public void setProcessamentoConciliacao(ProcessamentoConciliacao processamentoConciliacao) {
		this.processamentoConciliacao = processamentoConciliacao;
	}

}