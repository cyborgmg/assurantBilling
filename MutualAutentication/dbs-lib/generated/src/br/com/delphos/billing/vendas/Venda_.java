package br.com.delphos.billing.vendas;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.sistemas.Sistema;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.750-0200")
@StaticMetamodel(Venda.class)
public class Venda_ {
	public static volatile SingularAttribute<Venda, Long> id;
	public static volatile SingularAttribute<Venda, String> celular;
	public static volatile SingularAttribute<Venda, String> codigoBandeiraCartao;
	public static volatile SingularAttribute<Venda, String> codigoMotivoCancelamento;
	public static volatile SingularAttribute<Venda, String> codigoVendaOrigem;
	public static volatile SingularAttribute<Venda, String> cpf;
	public static volatile SingularAttribute<Venda, String> cpfPortadorCartao;
	public static volatile SingularAttribute<Venda, Date> dataCancelamento;
	public static volatile SingularAttribute<Venda, Date> dataFimVigencia;
	public static volatile SingularAttribute<Venda, Date> dataPrimeiraCobranca;
	public static volatile SingularAttribute<Venda, Date> dataVendaOrigem;
	public static volatile SingularAttribute<Venda, String> dddCelular;
	public static volatile SingularAttribute<Venda, String> dddTelefone;
	public static volatile SingularAttribute<Venda, String> email;
	public static volatile SingularAttribute<Venda, String> nome;
	public static volatile SingularAttribute<Venda, String> nomeImpressoCartao;
	public static volatile SingularAttribute<Venda, Integer> quantidadeParcelas;
	public static volatile SingularAttribute<Venda, String> status;
	public static volatile SingularAttribute<Venda, String> telefone;
	public static volatile SingularAttribute<Venda, String> tipoCobranca;
	public static volatile SingularAttribute<Venda, String> tokenCartao;
	public static volatile SingularAttribute<Venda, String> ultimosDigitosCartao;
	public static volatile SingularAttribute<Venda, BigDecimal> valorCobranca;
	public static volatile SingularAttribute<Venda, String> vencimentoCartao;
	public static volatile ListAttribute<Venda, Cobranca> cobrancas;
	public static volatile SingularAttribute<Venda, ContratoCobranca> contratoCobranca;
	public static volatile SingularAttribute<Venda, Empresa> empresa;
	public static volatile SingularAttribute<Venda, Produto> produto;
	public static volatile SingularAttribute<Venda, Sistema> sistema;
}
