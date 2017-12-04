package br.com.delphos.billing.conciliacoes;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.720-0200")
@StaticMetamodel(ProcConciliacaoEvento.class)
public class ProcConciliacaoEvento_ {
	public static volatile SingularAttribute<ProcConciliacaoEvento, Long> id;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> account;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> acquirerAdjustCode;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> acquirerAdjustDescription;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> affiliationCode;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> agency;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> anticipationOperationNumber;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> bank;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> category;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> categoryId;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Date> dataProcessamento;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Date> eventDate;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> eventId;
	public static volatile SingularAttribute<ProcConciliacaoEvento, BigDecimal> grossAmount;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> mensagemProcessamento;
	public static volatile SingularAttribute<ProcConciliacaoEvento, BigDecimal> netAmount;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Date> originalPaymentDate;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> statusProcessamento;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> tagRegistro;
	public static volatile SingularAttribute<ProcConciliacaoEvento, BigDecimal> taxAmount;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> transactionInstallment;
	public static volatile SingularAttribute<ProcConciliacaoEvento, String> type;
	public static volatile SingularAttribute<ProcConciliacaoEvento, Integer> typeId;
	public static volatile SingularAttribute<ProcConciliacaoEvento, ProcessamentoConciliacao> processamentoConciliacao;
}
