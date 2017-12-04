package br.com.delphos.billing.adquirentes;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.710-0200")
@StaticMetamodel(Bandeira.class)
public class Bandeira_ {
	public static volatile SingularAttribute<Bandeira, Long> id;
	public static volatile SingularAttribute<Bandeira, String> codigoBandeira;
	public static volatile SingularAttribute<Bandeira, String> nomeBandeira;
	public static volatile ListAttribute<Bandeira, AdquirenteBandeira> adquirentesBandeira;
}
