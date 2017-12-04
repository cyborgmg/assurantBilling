package br.com.delphos.billing.regras;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.util.AbstractRegraNegocio;
import br.com.delphos.billing.util.ServiceHelper;
import br.com.delphos.billing.util.Validador;

public class RegraVendaCpf extends AbstractRegraNegocio {

	private final ServiceHelper<? extends RetornoBillingService> helper;
	private final String cpf;
	private final String nomeParametro;
	
	public RegraVendaCpf(
			ServiceHelper<? extends RetornoBillingService> helper,
			String cpf,
			String nomeParametro) {
		super();
		this.helper = helper;
		this.cpf = cpf;
		this.nomeParametro = nomeParametro;
	}

	@Override
	public void run() {
		boolean cpfValido = true;
		
		if (Validador.vazio(cpf) || cpf.length() != 11 || !Validador.cpf(cpf)) {
			helper.setCodigoRetornoParaParametro(
					CodigoMensagem.DPH0001,
					nomeParametro);
			cpfValido = false;
		}
		
		setValida(cpfValido, helper.getRetorno().getMensagemRetorno());
	}

	
	
}
