package br.com.delphos.billing.produtos;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.vendas.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.740-0200")
@StaticMetamodel(Produto.class)
public class Produto_ {
	public static volatile SingularAttribute<Produto, Long> id;
	public static volatile SingularAttribute<Produto, String> codigo;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, Integer> numeroMaximoParcelas;
	public static volatile SingularAttribute<Produto, String> tipoCobranca;
	public static volatile SingularAttribute<Produto, Empresa> empresa;
	public static volatile ListAttribute<Produto, ContratoCobranca> contratosCobranca;
	public static volatile ListAttribute<Produto, Venda> vendas;
}
