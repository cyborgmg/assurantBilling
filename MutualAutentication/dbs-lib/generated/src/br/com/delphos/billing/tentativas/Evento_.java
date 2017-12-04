package br.com.delphos.billing.tentativas;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.740-0200")
@StaticMetamodel(Evento.class)
public class Evento_ {
	public static volatile SingularAttribute<Evento, Long> id;
	public static volatile SingularAttribute<Evento, String> codigoEvento;
	public static volatile SingularAttribute<Evento, String> complemento;
	public static volatile SingularAttribute<Evento, Date> dataEvento;
	public static volatile SingularAttribute<Evento, Tentativa> tentativa;
}
