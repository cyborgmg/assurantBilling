package br.com.delphos.billing.listasValores;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.730-0200")
@StaticMetamodel(ListaValor.class)
public class ListaValor_ {
	public static volatile SingularAttribute<ListaValor, Long> id;
	public static volatile SingularAttribute<ListaValor, String> codigo;
	public static volatile SingularAttribute<ListaValor, String> descricao;
	public static volatile SingularAttribute<ListaValor, String> status;
	public static volatile SingularAttribute<ListaValor, String> tipoUso;
	public static volatile ListAttribute<ListaValor, ItemLista> itensLista;
}
