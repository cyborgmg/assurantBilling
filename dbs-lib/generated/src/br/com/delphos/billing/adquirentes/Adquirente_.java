package br.com.delphos.billing.adquirentes;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.620-0200")
@StaticMetamodel(Adquirente.class)
public class Adquirente_ {
	public static volatile SingularAttribute<Adquirente, Long> id;
	public static volatile SingularAttribute<Adquirente, String> codigoConvenio;
	public static volatile SingularAttribute<Adquirente, String> nome;
	public static volatile SingularAttribute<Adquirente, Integer> codigoAfiliacaoConciliador;
	public static volatile ListAttribute<Adquirente, AdquirenteBandeira> adquirentesBandeira;
}
