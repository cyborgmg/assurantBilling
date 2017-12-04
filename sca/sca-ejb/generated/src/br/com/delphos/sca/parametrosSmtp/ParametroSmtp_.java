package br.com.delphos.sca.parametrosSmtp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:45:23.135-0200")
@StaticMetamodel(ParametroSmtp.class)
public class ParametroSmtp_ {
	public static volatile SingularAttribute<ParametroSmtp, Long> id;
	public static volatile SingularAttribute<ParametroSmtp, String> autenticado;
	public static volatile SingularAttribute<ParametroSmtp, String> host;
	public static volatile SingularAttribute<ParametroSmtp, Integer> porta;
	public static volatile SingularAttribute<ParametroSmtp, String> protocolo;
	public static volatile SingularAttribute<ParametroSmtp, String> senha;
	public static volatile SingularAttribute<ParametroSmtp, String> usuario;
}
