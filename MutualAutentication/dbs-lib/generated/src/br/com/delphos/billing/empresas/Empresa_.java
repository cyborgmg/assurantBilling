package br.com.delphos.billing.empresas;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.vendas.Venda;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.730-0200")
@StaticMetamodel(Empresa.class)
public class Empresa_ {
	public static volatile SingularAttribute<Empresa, Long> id;
	public static volatile SingularAttribute<Empresa, String> codigo;
	public static volatile SingularAttribute<Empresa, String> descricao;
	public static volatile SingularAttribute<Empresa, String> usuarioConciliador;
	public static volatile SingularAttribute<Empresa, String> senhaConciliador;
	public static volatile ListAttribute<Empresa, ContratoCobranca> contratosCobranca;
	public static volatile ListAttribute<Empresa, Produto> produtos;
	public static volatile ListAttribute<Empresa, Venda> vendas;
}
