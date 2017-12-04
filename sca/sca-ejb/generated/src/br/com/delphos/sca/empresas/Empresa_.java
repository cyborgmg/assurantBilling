package br.com.delphos.sca.empresas;

import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.usuarios.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.135-0200")
@StaticMetamodel(Empresa.class)
public class Empresa_ {
	public static volatile SingularAttribute<Empresa, Long> id;
	public static volatile SingularAttribute<Empresa, String> codigo;
	public static volatile SingularAttribute<Empresa, String> descricao;
	public static volatile ListAttribute<Empresa, Sistema> sistemas;
	public static volatile ListAttribute<Empresa, Usuario> usuarios;
}
