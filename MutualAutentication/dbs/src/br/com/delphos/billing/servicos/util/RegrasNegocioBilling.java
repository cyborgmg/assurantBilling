package br.com.delphos.billing.servicos.util;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.regras.RegraVendaCodigoEmpresa;
import br.com.delphos.billing.regras.RegraVendaCodigoProduto;
import br.com.delphos.billing.regras.RegraVendaCodigoSistema;
import br.com.delphos.billing.regras.RegraVendaCodigoVenda;
import br.com.delphos.billing.regras.RegraVendaCpf;
import br.com.delphos.billing.regras.RegraVendaTokenWebService;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.util.ServiceHelper;
import br.com.delphos.billing.vendas.VendaDLO;

public class RegrasNegocioBilling {
	
	public static <T extends RetornoBillingService> RegraVendaTokenWebService
	tokenValidacao(
			ServiceHelper<T> helper, 
			String token) {
		return new RegraVendaTokenWebService(helper, token);
	}
	
	public static <T extends RetornoBillingService> RegraVendaCodigoEmpresa
	codigoEmpresa(
			ServiceHelper<T> helper, 
			String codigoEmpresa, 
			EmpresaDLO empresaDLO) {
		return new RegraVendaCodigoEmpresa(helper, codigoEmpresa, empresaDLO);
	}
	
	public static <T extends RetornoBillingService> RegraVendaCodigoProduto
	codigoProduto(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoProduto, 
			Empresa empresa,
			ProdutoDLO produtoDLO) {
		return new RegraVendaCodigoProduto(helper, codigoProduto, empresa, produtoDLO);
	}
	
	public static <T extends RetornoBillingService> RegraVendaCodigoSistema
	codigoSistema(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoSistema, 
			Produto produto,
			SistemaDLO sistemaDLO) {
		return new RegraVendaCodigoSistema(helper, codigoSistema, produto, sistemaDLO);
	}

	public static <T extends RetornoBillingService> RegraVendaCpf
	cpf(
			ServiceHelper<? extends RetornoBillingService> helper,
			String cpf, String nomeParametro) {
		return new RegraVendaCpf(helper, cpf, nomeParametro);
	}
	
	public static <T extends RetornoBillingService> RegraVendaCodigoVenda
	codigoVendaNaoExiste(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoVenda,
			Produto produto,
			Empresa empresa,
			VendaDLO vendaDLO) {
		return new RegraVendaCodigoVenda(helper, codigoVenda, produto, empresa, vendaDLO, false);
	}

	public static <T extends RetornoBillingService> RegraVendaCodigoVenda
	codigoVendaExiste(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoVenda,
			Produto produto,
			Empresa empresa,
			VendaDLO vendaDLO) {
		return new RegraVendaCodigoVenda(helper, codigoVenda, produto, empresa, vendaDLO, true);
	}
}
