package br.com.delphos.billing.listasValores;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.730-0200")
@StaticMetamodel(ItemLista.class)
public class ItemLista_ {
	public static volatile SingularAttribute<ItemLista, Long> id;
	public static volatile SingularAttribute<ItemLista, String> codigo;
	public static volatile SingularAttribute<ItemLista, String> descricao;
	public static volatile SingularAttribute<ItemLista, String> status;
	public static volatile SingularAttribute<ItemLista, ListaValor> listaValor;
}
