package br.com.delphos.billing.parametros;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.740-0200")
@StaticMetamodel(Parametro.class)
public class Parametro_ {
	public static volatile SingularAttribute<Parametro, Long> id;
	public static volatile SingularAttribute<Parametro, Long> intervaloPendencia;
	public static volatile SingularAttribute<Parametro, Long> intervaloEstornoSolicitado;
	public static volatile SingularAttribute<Parametro, Integer> limiteAdquirenteAtivo;
	public static volatile SingularAttribute<Parametro, BigDecimal> margemAceitacaoConciliacao;
}
