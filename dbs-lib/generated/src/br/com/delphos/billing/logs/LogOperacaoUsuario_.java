package br.com.delphos.billing.logs;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.730-0200")
@StaticMetamodel(LogOperacaoUsuario.class)
public class LogOperacaoUsuario_ {
	public static volatile SingularAttribute<LogOperacaoUsuario, Long> id;
	public static volatile SingularAttribute<LogOperacaoUsuario, String> complemento;
	public static volatile SingularAttribute<LogOperacaoUsuario, Date> data;
	public static volatile SingularAttribute<LogOperacaoUsuario, String> operacao;
	public static volatile SingularAttribute<LogOperacaoUsuario, String> tabela;
	public static volatile SingularAttribute<LogOperacaoUsuario, Long> usuario;
	public static volatile SingularAttribute<LogOperacaoUsuario, String> descricaoUsuario;
	public static volatile SingularAttribute<LogOperacaoUsuario, String> idObjeto;
	public static volatile SingularAttribute<LogOperacaoUsuario, String> descricaoObjeto;
}
