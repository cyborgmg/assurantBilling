package br.com.delphos.billing.conciliacoes;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PROCESSAMENTO_CONCILIACAO database table.
 * 
 */
@Entity
@Table(name = "PROCESSAMENTO_CONCILIACAO")
@NamedQuery(name = "ProcessamentoConciliacao.findAll", query = "SELECT p FROM ProcessamentoConciliacao p")
public class ProcessamentoConciliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PROCESSAMENTO_CONCILIACAO_IDPROCESSAMENTOCONCILIACAO_GENERATOR", sequenceName = "SEQ_ID_PROC_CONCILIACAO_EVENTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCESSAMENTO_CONCILIACAO_IDPROCESSAMENTOCONCILIACAO_GENERATOR")
	@Column(name = "ID_PROCESSAMENTO_CONCILIACAO")
	private Long id;

	@Column(name = "acquirerid")
	private Integer acquirerId;

	@Column(name = "affiliationcodeacquirer")
	private String affiliationCodeAcquirer;

	@Column(name = "affiliationcodesale")
	private String affiliationCodeSale;

	@Column(name = "authorizationcodeacquirer")
	private String authorizationCodeAcquirer;

	@Column(name = "authorizationCodeSale")
	private String authorizationCodeSale;

	@Column(name = "branchid")
	private String branchId;

	@Temporal(TemporalType.DATE)
	@Column(name = "capturedateacquirer")
	private Date captureDateAcquirer;

	@Temporal(TemporalType.DATE)
	@Column(name = "capturedatesale")
	private Date captureDateSale;

	@Column(name = "capturemethoddescription")
	private String captureMethodDescription;

	@Column(name="capturemethodid")
	private Integer captureMethodId;

	@Column(name="cardnumberacquirer")
	private String cardNumberAcquirer;

	@Column(name="cardnumbersale")
	private String cardNumberSale;

	@Column(name="cardtype")
	private String cardType;

	@Column(name="cardtypeid")
	private Integer cardTypeId;

	@Column(name="conciliationdatetime")
	@Temporal(TemporalType.DATE)
	private Date conciliationDateTime;

	@Column(name="conciliationtype")
	private String conciliationType;

	@Column(name="conciliationtypeid")
	private Integer conciliationTypeId;

	@Column(name="conciliationusername")
	private String conciliationUserName;

	@Column(name="customername")
	private String customerName;

	@Column(name="endperiod")
	@Temporal(TemporalType.DATE)
	private Date endPeriod;

	@Column(name="externalid")
	private String externalId;

	@Column(name="generationdatetime")
	@Temporal(TemporalType.DATE)
	private Date generationDateTime;

	@Column(name="installmentcount")
	private Integer installmentCount;

	@Column(name="installmentcountacquirer")
	private Integer installmentCountAcquirer;

	@Column(name="installmentcountsale")
	private Integer installmentCountSale;

	@Column(name="installmentsgrossamount")
	private BigDecimal installmentsGrossAmount;

	@Column(name="installmentsnetamount")
	private BigDecimal installmentsNetAmount;

	@Column(name="installmentstaxamount")
	private BigDecimal installmentsTaxAmount;

	@Column(name = "merchantid")
	private Integer merchantId;

	@Column(name = "nsuacquirer")
	private Integer nsuAcquirer;

	@Column(name = "nsusale")
	private Integer nsuSale;

	@Column(name = "orderidacquiirer")
	private String orderIdAcquiirer;

	@Column(name = "orderidsale")
	private String orderIdSale;

	@Column(name = "paymentmethodname")
	private String paymentMethodName;

	@Column(name = "processingtype")
	private String processingType;

	@Column(name = "processingtypeid")
	private Integer processingTypeId;

	@Column(name = "productidentifiercode")
	private Integer productIdentifierCode;

	@Column(name = "productidentifierdescription")
	private String productIdentifierDescription;

	@Column(name = "roundinginstallmentgrossamount")
	private BigDecimal roundingInstallmentGrossAmount;

	@Column(name = "roundinginstallmentnetamount")
	private BigDecimal roundingInstallmentNetAmount;

	@Column(name = "roundinginstallmenttaxamount")
	private BigDecimal roundingInstallmentTaxAmount;

	@Temporal(TemporalType.DATE)
	@Column(name = "saledateacquirer")
	private Date saleDateAcquirer;

	@Temporal(TemporalType.DATE)
	@Column(name = "saledatesale")
	private Date saleDateSale;

	@Column(name = "sequentialnumber")
	private Integer sequentialNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "startperiod")
	private Date startPeriod;

	@Column(name = "summaryidentifiernumber")
	private Integer summaryIdentifierNumber;

	@Column(name = "summarynumber")
	private Integer summaryNumber;

	@Column(name = "tax")
	private BigDecimal tax;

	@Column(name = "tidacquirer")
	private String tidAcquirer;

	@Column(name = "tidsale")
	private String tidSale;

	@Column(name = "transactionamount")
	private BigDecimal transactionAmount;

	@Column(name = "transactiongrossamount")
	private BigDecimal transactionGrossAmount;

	@Column(name = "transactionidacquirer")
	private String transactionIdAcquirer;

	@Column(name = "transactionidsale")
	private String transactionIdSale;

	@Column(name = "transactionnetamount")
	private BigDecimal transactionNetAmount;

	@Column(name = "transactiontaxamount")
	private BigDecimal transactionTaxAmount;

	@Column(name = "\"VERSION\"")
	private String version;

	// bi-directional many-to-one association to ProcConciliacaoEvento
	@OneToMany(mappedBy = "processamentoConciliacao", fetch = FetchType.EAGER)
	private List<ProcConciliacaoEvento> procConciliacaoEventos;

	public ProcessamentoConciliacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long idProcConc) {
		this.id = idProcConc;
	}

	public Integer getAcquirerId() {
		return this.acquirerId;
	}

	public void setAcquirerId(Integer acquirerid) {
		this.acquirerId = acquirerid;
	}

	public String getAffiliationCodeAcquirer() {
		return this.affiliationCodeAcquirer;
	}

	public void setAffiliationCodeAcquirer(String affiliationcodeacquirer) {
		this.affiliationCodeAcquirer = affiliationcodeacquirer;
	}

	public String getAffiliationCodeSale() {
		return this.affiliationCodeSale;
	}

	public void setAffiliationCodeSale(String affiliationcodesale) {
		this.affiliationCodeSale = affiliationcodesale;
	}

	public String getAuthorizationCodeAcquirer() {
		return this.authorizationCodeAcquirer;
	}

	public void setAuthorizationCodeAcquirer(String authorizationcodeacquirer) {
		this.authorizationCodeAcquirer = authorizationcodeacquirer;
	}

	public String getAuthorizationCodeSale() {
		return this.authorizationCodeSale;
	}

	public void setAuthorizationCodeSale(String authorizationcodesale) {
		this.authorizationCodeSale = authorizationcodesale;
	}

	public String getBranchId() {
		return this.branchId;
	}

	public void setBranchId(String branchid) {
		this.branchId = branchid;
	}

	public Date getCaptureDateAcquirer() {
		return this.captureDateAcquirer;
	}

	public void setCaptureDateAcquirer(Date capturedateacquirer) {
		this.captureDateAcquirer = capturedateacquirer;
	}

	public Date getCaptureDateSale() {
		return this.captureDateSale;
	}

	public void setCaptureDateSale(Date capturedatesale) {
		this.captureDateSale = capturedatesale;
	}

	public String getCaptureMethodDescription() {
		return this.captureMethodDescription;
	}

	public void setCaptureMethodDescription(String capturemethoddescription) {
		this.captureMethodDescription = capturemethoddescription;
	}

	public Integer getCaptureMethodId() {
		return captureMethodId;
	}

	public void setCaptureMethodId(Integer captureMethodId) {
		this.captureMethodId = captureMethodId;
	}

	public String getCardNumberAcquirer() {
		return cardNumberAcquirer;
	}

	public void setCardNumberAcquirer(String cardNumberAcquirer) {
		this.cardNumberAcquirer = cardNumberAcquirer;
	}

	public String getCardNumberSale() {
		return cardNumberSale;
	}

	public void setCardNumberSale(String cardNumberSale) {
		this.cardNumberSale = cardNumberSale;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public BigDecimal getInstallmentsNetAmount() {
		return installmentsNetAmount;
	}

	public void setInstallmentsNetAmount(BigDecimal installmentsNetAmount) {
		this.installmentsNetAmount = installmentsNetAmount;
	}

	public BigDecimal getInstallmentsTaxAmount() {
		return installmentsTaxAmount;
	}

	public void setInstallmentsTaxAmount(BigDecimal installmentsTaxAmount) {
		this.installmentsTaxAmount = installmentsTaxAmount;
	}

	public Integer getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Date getConciliationDateTime() {
		return conciliationDateTime;
	}

	public void setConciliationDateTime(Date conciliationDateTime) {
		this.conciliationDateTime = conciliationDateTime;
	}

	public String getConciliationType() {
		return conciliationType;
	}

	public void setConciliationType(String conciliationType) {
		this.conciliationType = conciliationType;
	}

	public Integer getConciliationTypeId() {
		return conciliationTypeId;
	}

	public void setConciliationTypeId(Integer conciliationTypeId) {
		this.conciliationTypeId = conciliationTypeId;
	}

	public String getOrderIdSale() {
		return orderIdSale;
	}

	public void setOrderIdSale(String orderIdSale) {
		this.orderIdSale = orderIdSale;
	}



	public String getConciliationUserName() {
		return conciliationUserName;
	}

	public void setConciliationUserName(String conciliationUserName) {
		this.conciliationUserName = conciliationUserName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Integer getInstallmentCount() {
		return installmentCount;
	}

	public void setInstallmentCount(Integer installmentCount) {
		this.installmentCount = installmentCount;
	}

	public Integer getInstallmentCountAcquirer() {
		return installmentCountAcquirer;
	}

	public void setInstallmentCountAcquirer(Integer installmentCountAcquirer) {
		this.installmentCountAcquirer = installmentCountAcquirer;
	}

	public Integer getInstallmentCountSale() {
		return installmentCountSale;
	}

	public void setInstallmentCountSale(Integer installmentCountSale) {
		this.installmentCountSale = installmentCountSale;
	}

	public BigDecimal getInstallmentsGrossAmount() {
		return installmentsGrossAmount;
	}

	public void setInstallmentsGrossAmount(BigDecimal installmentsGrossAmount) {
		this.installmentsGrossAmount = installmentsGrossAmount;
	}

	public Date getGenerationDateTime() {
		return this.generationDateTime;
	}

	public void setGenerationDateTime(Date generationdatetime) {
		this.generationDateTime = generationdatetime;
	}


	public Integer getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(Integer merchantid) {
		this.merchantId = merchantid;
	}

	public Integer getNsuAcquirer() {
		return this.nsuAcquirer;
	}

	public void setNsuAcquirer(Integer nsuacquirer) {
		this.nsuAcquirer = nsuacquirer;
	}

	public Integer getNsuSale() {
		return this.nsuSale;
	}

	public void setNsuSale(Integer nsusale) {
		this.nsuSale = nsusale;
	}

	public String getOrderIdAcquiirer() {
		return this.orderIdAcquiirer;
	}

	public void setOrderIdAcquiirer(String orderidacquiirer) {
		this.orderIdAcquiirer = orderidacquiirer;
	}

	public String getPaymentMethodName() {
		return this.paymentMethodName;
	}

	public void setPaymentMethodName(String paymentmethodname) {
		this.paymentMethodName = paymentmethodname;
	}

	public String getProcessingType() {
		return this.processingType;
	}

	public void setProcessingType(String processingtype) {
		this.processingType = processingtype;
	}

	public Integer getProcessingTypeId() {
		return this.processingTypeId;
	}

	public void setProcessingTypeId(Integer processingtypeid) {
		this.processingTypeId = processingtypeid;
	}

	public Integer getProductIdentifierCode() {
		return this.productIdentifierCode;
	}

	public void setProductIdentifierCode(Integer productidentifiercode) {
		this.productIdentifierCode = productidentifiercode;
	}

	public String getProductIdentifierDescription() {
		return this.productIdentifierDescription;
	}

	public void setProductIdentifierDescription(
			String productidentifierdescription) {
		this.productIdentifierDescription = productidentifierdescription;
	}

	public BigDecimal getRoundingInstallmentGrossAmount() {
		return this.roundingInstallmentGrossAmount;
	}

	public void setRoundingInstallmentGrossAmount(
			BigDecimal roundinginstallmentgrossamount) {
		this.roundingInstallmentGrossAmount = roundinginstallmentgrossamount;
	}

	public BigDecimal getRoundingInstallmentNetAmount() {
		return this.roundingInstallmentNetAmount;
	}

	public void setRoundingInstallmentNetAmount(
			BigDecimal roundinginstallmentnetamount) {
		this.roundingInstallmentNetAmount = roundinginstallmentnetamount;
	}

	public BigDecimal getRoundingInstallmentTaxAmount() {
		return this.roundingInstallmentTaxAmount;
	}

	public void setRoundingInstallmentTaxAmount(
			BigDecimal roundinginstallmenttaxamount) {
		this.roundingInstallmentTaxAmount = roundinginstallmenttaxamount;
	}

	public Date getSaleDateAcquirer() {
		return this.saleDateAcquirer;
	}

	public void setSaleDateAcquirer(Date saledateacquirer) {
		this.saleDateAcquirer = saledateacquirer;
	}

	public Date getSaleDateSale() {
		return this.saleDateSale;
	}

	public void setSaleDateSale(Date saledatesale) {
		this.saleDateSale = saledatesale;
	}

	public Integer getSequentialNumber() {
		return this.sequentialNumber;
	}

	public void setSequentialNumber(Integer sequentialnumber) {
		this.sequentialNumber = sequentialnumber;
	}

	public Date getStartPeriod() {
		return this.startPeriod;
	}

	public void setStartPeriod(Date startperiod) {
		this.startPeriod = startperiod;
	}

	public Integer getSummaryIdentifierNumber() {
		return this.summaryIdentifierNumber;
	}

	public void setSummaryIdentifierNumber(Integer summaryidentifiernumber) {
		this.summaryIdentifierNumber = summaryidentifiernumber;
	}

	public Integer getSummaryNumber() {
		return this.summaryNumber;
	}

	public void setSummaryNumber(Integer summarynumber) {
		this.summaryNumber = summarynumber;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public String getTidAcquirer() {
		return this.tidAcquirer;
	}

	public void setTidAcquirer(String tidacquirer) {
		this.tidAcquirer = tidacquirer;
	}

	public String getTidSale() {
		return this.tidSale;
	}

	public void setTidSale(String tidsale) {
		this.tidSale = tidsale;
	}

	public BigDecimal getTransactionAmount() {
		return this.transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionamount) {
		this.transactionAmount = transactionamount;
	}

	public BigDecimal getTransactionGrossAmount() {
		return this.transactionGrossAmount;
	}

	public void setTransactionGrossAmount(BigDecimal transactiongrossamount) {
		this.transactionGrossAmount = transactiongrossamount;
	}

	public String getTransactionIdAcquirer() {
		return this.transactionIdAcquirer;
	}

	public void setTransactionIdAcquirer(String transactionidacquirer) {
		this.transactionIdAcquirer = transactionidacquirer;
	}

	public String getTransactionIdSale() {
		return this.transactionIdSale;
	}

	public void setTransactionIdSale(String transactionidsale) {
		this.transactionIdSale = transactionidsale;
	}

	public BigDecimal getTransactionNetAmount() {
		return this.transactionNetAmount;
	}

	public void setTransactionNetAmount(BigDecimal transactionnetamount) {
		this.transactionNetAmount = transactionnetamount;
	}

	public BigDecimal getTransactionTaxAmount() {
		return this.transactionTaxAmount;
	}

	public void setTransactionTaxAmount(BigDecimal transactiontaxamount) {
		this.transactionTaxAmount = transactiontaxamount;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<ProcConciliacaoEvento> getProcConciliacaoEventos() {
		return this.procConciliacaoEventos;
	}

	public void setProcConciliacaoEventos(
			List<ProcConciliacaoEvento> procConciliacaoEventos) {
		this.procConciliacaoEventos = procConciliacaoEventos;
	}

	public ProcConciliacaoEvento addProcConciliacaoEvento(
			ProcConciliacaoEvento procConciliacaoEvento) {
		getProcConciliacaoEventos().add(procConciliacaoEvento);
		procConciliacaoEvento.setProcessamentoConciliacao(this);

		return procConciliacaoEvento;
	}

	public ProcConciliacaoEvento removeProcConciliacaoEvento(
			ProcConciliacaoEvento procConciliacaoEvento) {
		getProcConciliacaoEventos().remove(procConciliacaoEvento);
		procConciliacaoEvento.setProcessamentoConciliacao(null);

		return procConciliacaoEvento;
	}

}