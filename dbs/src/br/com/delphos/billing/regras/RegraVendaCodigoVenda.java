package br.com.delphos.billing.regras;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.util.AbstractRegraNegocio;
import br.com.delphos.billing.util.ServiceHelper;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDLO;

public class RegraVendaCodigoVenda extends AbstractRegraNegocio {

	private final ServiceHelper<? extends RetornoBillingService> helper;
	private final String codigoVenda;
	private final Produto produto;
	private final Empresa empresa;
	private final VendaDLO vendaDLO;
	private final boolean deveExistir;
	private Venda venda;
	
	public RegraVendaCodigoVenda(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoVenda,
			Produto produto,
			Empresa empresa,
			VendaDLO vendaDLO,
			boolean deveExistir) {
		super();
		this.helper = helper;
		this.codigoVenda = codigoVenda;
		this.produto = produto;
		this.empresa = empresa;
		this.vendaDLO = vendaDLO;
		this.deveExistir = deveExistir;
	}

	// RN - Modelo de dados
	// ???Deve ser único dentro da mesma empresa + produto para
	// vendas ativas. É possível duplicidade desde que tenhamos apenas uma
	// venda ativa. Se erro retornar MSG0001.
	@Override
	public void run() throws DLOException {
		
		boolean codigoVendaValido = false;
			codigoVendaValido = vendaDLO.isCodigoValido(codigoVenda);
		
		if (codigoVendaValido) {
			venda = vendaDLO.obterVendaAtivaPorProdutoEmpresaCodigo(produto, empresa, codigoVenda);
			codigoVendaValido = (deveExistir) ? (venda != null) : venda == null;
		}
		
		if (!codigoVendaValido) {
			if (deveExistir) {
				helper.setValorRetorno(
						CodigoMensagem.DPH0029,
						"codigoVenda");
				
			} else {
				helper.setValorRetorno(
						CodigoMensagem.DPH0019,
						"codigoVenda");
			}
			codigoVendaValido = false;	
		}
		
		setValida(codigoVendaValido, helper.getRetorno().getMensagemRetorno());
	}

	public Venda getVenda() {
		return venda;
	}

}
