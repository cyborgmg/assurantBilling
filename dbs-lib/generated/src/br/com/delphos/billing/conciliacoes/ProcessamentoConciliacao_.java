package br.com.delphos.billing.conciliacoes;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.720-0200")
@StaticMetamodel(ProcessamentoConciliacao.class)
public class ProcessamentoConciliacao_ {
	public static volatile SingularAttribute<ProcessamentoConciliacao, Long> id;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> acquirerId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> affiliationCodeAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> affiliationCodeSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> authorizationCodeAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> authorizationCodeSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> branchId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> captureDateAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> captureDateSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> captureMethodDescription;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> captureMethodId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> cardNumberAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> cardNumberSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> cardType;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> cardTypeId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> conciliationDateTime;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> conciliationType;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> conciliationTypeId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> conciliationUserName;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> customerName;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> endPeriod;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> externalId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> generationDateTime;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> installmentCount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> installmentCountAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> installmentCountSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> installmentsGrossAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> installmentsNetAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> installmentsTaxAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> merchantId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> nsuAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> nsuSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> orderIdAcquiirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> orderIdSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> paymentMethodName;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> processingType;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> processingTypeId;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> productIdentifierCode;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> productIdentifierDescription;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> roundingInstallmentGrossAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> roundingInstallmentNetAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> roundingInstallmentTaxAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> saleDateAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> saleDateSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> sequentialNumber;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Date> startPeriod;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> summaryIdentifierNumber;
	public static volatile SingularAttribute<ProcessamentoConciliacao, Integer> summaryNumber;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> tax;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> tidAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> tidSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> transactionAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> transactionGrossAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> transactionIdAcquirer;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> transactionIdSale;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> transactionNetAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, BigDecimal> transactionTaxAmount;
	public static volatile SingularAttribute<ProcessamentoConciliacao, String> version;
	public static volatile ListAttribute<ProcessamentoConciliacao, ProcConciliacaoEvento> procConciliacaoEventos;
}