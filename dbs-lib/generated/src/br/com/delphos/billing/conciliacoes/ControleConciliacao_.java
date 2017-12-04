package br.com.delphos.billing.conciliacoes;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.720-0200")
@StaticMetamodel(ControleConciliacao.class)
public class ControleConciliacao_ {
	public static volatile SingularAttribute<ControleConciliacao, Long> id;
	public static volatile SingularAttribute<ControleConciliacao, String> adquirente;
	public static volatile SingularAttribute<ControleConciliacao, byte[]> arquivo;
	public static volatile SingularAttribute<ControleConciliacao, String> arquivoVazio;
	public static volatile SingularAttribute<ControleConciliacao, Date> dataImportacaoArquivo;
	public static volatile SingularAttribute<ControleConciliacao, Date> dataMovimento;
	public static volatile SingularAttribute<ControleConciliacao, Date> dataProcessamentoArquivo;
	public static volatile SingularAttribute<ControleConciliacao, String> mensagemValidacao;
	public static volatile SingularAttribute<ControleConciliacao, Long> numeroReprocessamento;
	public static volatile SingularAttribute<ControleConciliacao, String> status;
	public static volatile SingularAttribute<ControleConciliacao, BigDecimal> valorArquivo;
	public static volatile SingularAttribute<ControleConciliacao, BigDecimal> valorDeposito;
}
