package br.com.delphos.billing.regras;

import java.util.List;

import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.util.AbstractRegraNegocio;
import br.com.delphos.billing.util.ServiceHelper;

public class RegraVendaCodigoProduto extends AbstractRegraNegocio {

	private final String codigoProduto;
	private final Empresa empresa;
	private final ServiceHelper<? extends RetornoBillingService> helper;
	private final ProdutoDLO produtoDLO;
	private Produto produto;

	public RegraVendaCodigoProduto(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoProduto, 
			Empresa empresa,
			ProdutoDLO produtoDLO) {
		super();
		this.codigoProduto = codigoProduto;
		this.empresa = empresa;
		this.helper = helper;
		this.produtoDLO = produtoDLO;
	}

	// RN - Modelo de dados
	// ??? Deve existir na entidade PRODUTO. Se erro retornar MSG0001. Deve
	// estar associado à empresa. Se erro retornar MSG0002.
	@Override
	public void run() {

		boolean codigoProdutoValido = produtoDLO.isCodigoValido(codigoProduto);
		List<Produto> listaProdutos = null;
		
		if (codigoProdutoValido) {
			listaProdutos = produtoDLO.listarPorCodigo(codigoProduto);
		}
		
		if (!codigoProdutoValido || listaProdutos == null || listaProdutos.isEmpty()) {
			helper.setCodigoRetornoParaParametro(
					CodigoMensagem.DPH0001,
					"codigoProduto");
			codigoProdutoValido = false;
			
		} else {
			for (Produto produtoCandidato : listaProdutos) {
				if (produtoCandidato.getEmpresa().equals(empresa)) {
					produto = produtoCandidato;
					break;
				}
			}
			
			if (produto == null) {
				helper.setValorRetorno(CodigoMensagem.DPH0002);
				codigoProdutoValido = false;
			}
		}

		setValida(codigoProdutoValido, helper.getRetorno().getMensagemRetorno());
	}

	public Produto getProduto() {
		return produto;
	}

}
