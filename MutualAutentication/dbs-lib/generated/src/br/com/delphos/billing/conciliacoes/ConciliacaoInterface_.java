package br.com.delphos.billing.conciliacoes;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.720-0200")
@StaticMetamodel(ConciliacaoInterface.class)
public class ConciliacaoInterface_ {
	public static volatile SingularAttribute<ConciliacaoInterface, Long> id;
	public static volatile SingularAttribute<ConciliacaoInterface, String> agencia;
	public static volatile SingularAttribute<ConciliacaoInterface, String> banco;
	public static volatile SingularAttribute<ConciliacaoInterface, String> codigoAfiliacao;
	public static volatile SingularAttribute<ConciliacaoInterface, String> codigoCliente;
	public static volatile SingularAttribute<ConciliacaoInterface, String> codigoEmpresa;
	public static volatile SingularAttribute<ConciliacaoInterface, String> codigoProduto;
	public static volatile SingularAttribute<ConciliacaoInterface, String> codigoSistema;
	public static volatile SingularAttribute<ConciliacaoInterface, String> conta;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataAutorizacao;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataBanco;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataGeracao;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataContabil;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataPagamento;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataProcessamento;
	public static volatile SingularAttribute<ConciliacaoInterface, Date> dataVencimento;
	public static volatile SingularAttribute<ConciliacaoInterface, BigDecimal> idecobr;
	public static volatile SingularAttribute<ConciliacaoInterface, BigDecimal> ideCompBanco;
	public static volatile SingularAttribute<ConciliacaoInterface, BigDecimal> ideFact;
	public static volatile SingularAttribute<ConciliacaoInterface, String> mensagemProcessamento;
	public static volatile SingularAttribute<ConciliacaoInterface, Integer> numeroParcela;
	public static volatile SingularAttribute<ConciliacaoInterface, BigDecimal> numOblig;
	public static volatile SingularAttribute<ConciliacaoInterface, String> statusProcessamento;
	public static volatile SingularAttribute<ConciliacaoInterface, String> tipoRegistro;
	public static volatile SingularAttribute<ConciliacaoInterface, BigDecimal> valorCobranca;
	public static volatile SingularAttribute<ConciliacaoInterface, BigDecimal> valorTarifa;
	public static volatile SingularAttribute<ConciliacaoInterface, String> nomeBandeira;
	public static volatile SingularAttribute<ConciliacaoInterface, String> nsu;
}
