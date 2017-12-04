package br.com.delphos.sca.usuarios;

import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.145-0200")
@StaticMetamodel(GrupoUsuario.class)
public class GrupoUsuario_ {
	public static volatile SingularAttribute<GrupoUsuario, Long> id;
	public static volatile SingularAttribute<GrupoUsuario, String> descricao;
	public static volatile SingularAttribute<GrupoUsuario, String> tipoGrupo;
	public static volatile ListAttribute<GrupoUsuario, Usuario> usuarios;
	public static volatile ListAttribute<GrupoUsuario, GrupoInterface> gruposInterface;
}
