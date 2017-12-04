package br.com.delphos.billing.sistemas;

import br.com.delphos.billing.vendas.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.740-0200")
@StaticMetamodel(Sistema.class)
public class Sistema_ {
	public static volatile SingularAttribute<Sistema, Long> id;
	public static volatile SingularAttribute<Sistema, String> codigo;
	public static volatile SingularAttribute<Sistema, String> codigoSistemaEnvio;
	public static volatile SingularAttribute<Sistema, String> descricao;
	public static volatile ListAttribute<Sistema, Venda> vendas;
}
