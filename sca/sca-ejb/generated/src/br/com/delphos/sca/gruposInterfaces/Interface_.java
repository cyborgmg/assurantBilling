package br.com.delphos.sca.gruposInterfaces;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.135-0200")
@StaticMetamodel(Interface.class)
public class Interface_ {
	public static volatile SingularAttribute<Interface, Long> id;
	public static volatile SingularAttribute<Interface, String> descricao;
	public static volatile ListAttribute<Interface, GrupoInterface> gruposInterface;
}
