package br.com.delphos.billing.tentativas;

import br.com.delphos.billing.cobrancas.Cobranca;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.750-0200")
@StaticMetamodel(Tentativa.class)
public class Tentativa_ {
	public static volatile SingularAttribute<Tentativa, Long> id;
	public static volatile SingularAttribute<Tentativa, String> codigoRetornoAdquirente;
	public static volatile SingularAttribute<Tentativa, String> codigoRetornoProvedor;
	public static volatile SingularAttribute<Tentativa, String> codigoTransacaoAdquirente;
	public static volatile SingularAttribute<Tentativa, Date> dataTentativa;
	public static volatile SingularAttribute<Tentativa, String> mensagemRetornoAdquirente;
	public static volatile SingularAttribute<Tentativa, String> mensagemRetornoProvedor;
	public static volatile SingularAttribute<Tentativa, Integer> numeroTentativa;
	public static volatile SingularAttribute<Tentativa, String> tipoTentativa;
	public static volatile ListAttribute<Tentativa, Evento> eventos;
	public static volatile SingularAttribute<Tentativa, Cobranca> cobranca;
}
