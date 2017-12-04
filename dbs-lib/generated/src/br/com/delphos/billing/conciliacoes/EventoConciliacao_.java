package br.com.delphos.billing.conciliacoes;

import br.com.delphos.billing.cobrancas.Cobranca;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.720-0200")
@StaticMetamodel(EventoConciliacao.class)
public class EventoConciliacao_ {
	public static volatile SingularAttribute<EventoConciliacao, Long> id;
	public static volatile SingularAttribute<EventoConciliacao, String> codigoEvento;
	public static volatile SingularAttribute<EventoConciliacao, String> complemento;
	public static volatile SingularAttribute<EventoConciliacao, Date> dataEvento;
	public static volatile SingularAttribute<EventoConciliacao, Cobranca> cobranca;
	public static volatile SingularAttribute<EventoConciliacao, Integer> numeroParcelaLoja;
}
