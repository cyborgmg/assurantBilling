package br.com.delphos.billing.cobrancas;

import br.com.delphos.billing.conciliacoes.EventoConciliacao;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.vendas.Venda;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.710-0200")
@StaticMetamodel(Cobranca.class)
public class Cobranca_ {
	public static volatile SingularAttribute<Cobranca, Long> id;
	public static volatile SingularAttribute<Cobranca, String> codigoAutorizacao;
	public static volatile SingularAttribute<Cobranca, String> codigoBandeiraCartao;
	public static volatile SingularAttribute<Cobranca, String> codigoPedidoProvedor;
	public static volatile SingularAttribute<Cobranca, String> codigoTransacaoAdquirente;
	public static volatile SingularAttribute<Cobranca, String> codigoTransacaoProvedor;
	public static volatile SingularAttribute<Cobranca, Date> dataCobranca;
	public static volatile SingularAttribute<Cobranca, String> idRequisicaoProvedor;
	public static volatile SingularAttribute<Cobranca, String> numeroComprovanteVenda;
	public static volatile SingularAttribute<Cobranca, Integer> numeroParcela;
	public static volatile SingularAttribute<Cobranca, Integer> numeroUltimaTentativa;
	public static volatile SingularAttribute<Cobranca, String> statusCobranca;
	public static volatile SingularAttribute<Cobranca, String> ultimosDigitosCartao;
	public static volatile SingularAttribute<Cobranca, BigDecimal> valorCobranca;
	public static volatile SingularAttribute<Cobranca, Date> dataUltimaAlteracao;
	public static volatile SingularAttribute<Cobranca, Date> dataEstorno;
	public static volatile SingularAttribute<Cobranca, BigDecimal> valorEstorno;
	public static volatile SingularAttribute<Cobranca, ContratoCobranca> contratoCobranca;
	public static volatile SingularAttribute<Cobranca, Venda> venda;
	public static volatile ListAttribute<Cobranca, Tentativa> tentativas;
	public static volatile ListAttribute<Cobranca, EventoConciliacao> eventosConciliacao;
}
