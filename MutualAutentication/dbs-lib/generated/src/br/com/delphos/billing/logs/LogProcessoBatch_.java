package br.com.delphos.billing.logs;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.730-0200")
@StaticMetamodel(LogProcessoBatch.class)
public class LogProcessoBatch_ {
	public static volatile SingularAttribute<LogProcessoBatch, Long> id;
	public static volatile SingularAttribute<LogProcessoBatch, String> complemento;
	public static volatile SingularAttribute<LogProcessoBatch, Date> dataFim;
	public static volatile SingularAttribute<LogProcessoBatch, Date> dataInicio;
	public static volatile SingularAttribute<LogProcessoBatch, String> processo;
	public static volatile SingularAttribute<LogProcessoBatch, String> status;
}
