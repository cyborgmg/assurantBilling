package br.com.delphos.sca.usuarios;

import br.com.delphos.sca.empresas.Empresa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.145-0200")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> codigo;
	public static volatile SingularAttribute<Usuario, Date> dataExclusao;
	public static volatile SingularAttribute<Usuario, Date> dataExpiracao;
	public static volatile SingularAttribute<Usuario, Date> dataUltimaAlteracao;
	public static volatile SingularAttribute<Usuario, String> descricao;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, String> token;
	public static volatile SingularAttribute<Usuario, String> ultimaSenha;
	public static volatile ListAttribute<Usuario, Empresa> empresas;
	public static volatile ListAttribute<Usuario, GrupoUsuario> gruposUsuario;
}
