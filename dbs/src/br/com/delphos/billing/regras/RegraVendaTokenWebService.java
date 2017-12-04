package br.com.delphos.billing.regras;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.util.AbstractRegraNegocio;
import br.com.delphos.billing.util.GerarToken;
import br.com.delphos.billing.util.ServiceHelper;

public class RegraVendaTokenWebService extends AbstractRegraNegocio {

	private String token;
	private ServiceHelper<? extends RetornoBillingService> helper;
		
	public RegraVendaTokenWebService(
			ServiceHelper<? extends RetornoBillingService> helper,
			String token) {
		super();
		this.token = token;
		this.helper = helper;
	}

	@Override
	public void run() {
		boolean tokenValido = true;
		String chave = GerarToken.CHAVE_CLIENTE;
		if (!GerarToken.encriptar(chave).equals(token)) {
			tokenValido = false;
			helper.setCodigoRetornoParaParametro(
					CodigoMensagem.DPH0001,
					"token");
		}
		setValida(tokenValido, helper.getRetorno().getMensagemRetorno());
	}
}
