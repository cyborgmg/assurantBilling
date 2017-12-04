package br.com.delphos.billing.contratosCobranca;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.vendas.Venda;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-30T10:39:44.720-0200")
@StaticMetamodel(ContratoCobranca.class)
public class ContratoCobranca_ {
	public static volatile SingularAttribute<ContratoCobranca, Long> id;
	public static volatile SingularAttribute<ContratoCobranca, String> codigoEmpresaNoProvedor;
	public static volatile SingularAttribute<ContratoCobranca, Integer> codigoEmpresaConciliador;
	public static volatile SingularAttribute<ContratoCobranca, Date> dataFimVigencia;
	public static volatile SingularAttribute<ContratoCobranca, Date> dataInicioVigencia;
	public static volatile SingularAttribute<ContratoCobranca, String> descricaoContrato;
	public static volatile SingularAttribute<ContratoCobranca, Integer> prazoPagamento;
	public static volatile SingularAttribute<ContratoCobranca, String> tipoTransacaoProvedor;
	public static volatile SingularAttribute<ContratoCobranca, String> versaoContratoWs;
	public static volatile ListAttribute<ContratoCobranca, AdquirenteBandeira> adquirentesBandeiras;
	public static volatile ListAttribute<ContratoCobranca, Cobranca> cobrancas;
	public static volatile SingularAttribute<ContratoCobranca, Empresa> empresa;
	public static volatile SingularAttribute<ContratoCobranca, MeioPagamento> meioPagamento;
	public static volatile SingularAttribute<ContratoCobranca, Provedor> provedor;
	public static volatile ListAttribute<ContratoCobranca, Produto> produtos;
	public static volatile ListAttribute<ContratoCobranca, RespostaAutorizacao> respostasAutorizacao;
	public static volatile ListAttribute<ContratoCobranca, Venda> vendas;
}
