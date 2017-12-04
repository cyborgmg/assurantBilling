package br.com.delphos.billing.adquirentes;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.710-0200")
@StaticMetamodel(AdquirenteBandeira.class)
public class AdquirenteBandeira_ {
	public static volatile SingularAttribute<AdquirenteBandeira, AdquirenteBandeiraPK> id;
	public static volatile SingularAttribute<AdquirenteBandeira, String> adquirentePadrao;
	public static volatile SingularAttribute<AdquirenteBandeira, Long> codigoMetodoPagamento;
	public static volatile SingularAttribute<AdquirenteBandeira, ContratoCobranca> contratoCobranca;
	public static volatile SingularAttribute<AdquirenteBandeira, Adquirente> adquirente;
	public static volatile SingularAttribute<AdquirenteBandeira, Bandeira> bandeira;
}
