package br.com.delphos.billing.provedores;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.740-0200")
@StaticMetamodel(Provedor.class)
public class Provedor_ {
	public static volatile SingularAttribute<Provedor, Long> id;
	public static volatile SingularAttribute<Provedor, String> codigoProvedor;
	public static volatile SingularAttribute<Provedor, String> descricaoProvedor;
	public static volatile ListAttribute<Provedor, ContratoCobranca> contratosCobranca;
}
