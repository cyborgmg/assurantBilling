package br.com.delphos.sca.sistemas;

import br.com.delphos.sca.empresas.Empresa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.135-0200")
@StaticMetamodel(Sistema.class)
public class Sistema_ {
	public static volatile SingularAttribute<Sistema, Long> id;
	public static volatile SingularAttribute<Sistema, String> descricao;
	public static volatile SingularAttribute<Sistema, String> sigla;
	public static volatile SingularAttribute<Sistema, Empresa> empresa;
}
