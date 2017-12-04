package br.com.delphos.billing.contratosCobranca;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.730-0200")
@StaticMetamodel(RespostaAutorizacao.class)
public class RespostaAutorizacao_ {
	public static volatile SingularAttribute<RespostaAutorizacao, Long> id;
	public static volatile SingularAttribute<RespostaAutorizacao, String> codigoResposta;
	public static volatile SingularAttribute<RespostaAutorizacao, String> descricaoRespostaConsulta;
	public static volatile SingularAttribute<RespostaAutorizacao, String> descricaoRespostaProvedor;
	public static volatile SingularAttribute<RespostaAutorizacao, Integer> quantidadeRetentativa;
	public static volatile SingularAttribute<RespostaAutorizacao, String> tipoIntervalo;
	public static volatile SingularAttribute<RespostaAutorizacao, Integer> valorIntervalo;
	public static volatile SingularAttribute<RespostaAutorizacao, ContratoCobranca> contratoCobranca;
}
