package br.com.delphos.billing.meiosPagamento;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.740-0200")
@StaticMetamodel(MeioPagamento.class)
public class MeioPagamento_ {
	public static volatile SingularAttribute<MeioPagamento, Long> id;
	public static volatile SingularAttribute<MeioPagamento, String> codigo;
	public static volatile SingularAttribute<MeioPagamento, String> descricao;
	public static volatile SingularAttribute<MeioPagamento, Integer> limiteDiasGerarRetentativa;
	public static volatile ListAttribute<MeioPagamento, ContratoCobranca> contratosCobranca;
}
