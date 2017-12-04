package br.com.delphos.sca.gruposInterfaces;

import br.com.delphos.sca.usuarios.GrupoUsuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.135-0200")
@StaticMetamodel(GrupoInterface.class)
public class GrupoInterface_ {
	public static volatile SingularAttribute<GrupoInterface, Long> id;
	public static volatile SingularAttribute<GrupoInterface, String> descricao;
	public static volatile ListAttribute<GrupoInterface, GrupoUsuario> gruposUsuario;
	public static volatile ListAttribute<GrupoInterface, Interface> interfaces;
}
