package br.com.delphos.billing.regras;

import java.util.List;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.util.AbstractRegraNegocio;
import br.com.delphos.billing.util.ServiceHelper;

public class RegraVendaCodigoSistema extends AbstractRegraNegocio {

	private final String codigoSistema;
	private final Produto produto;
	private final SistemaDLO sistemaDLO;
	private final ServiceHelper<? extends RetornoBillingService> helper;
	private Sistema sistema;	
	
	public RegraVendaCodigoSistema(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoSistema, 
			Produto produto,
			SistemaDLO sistemaDLO) {
		super();
		this.codigoSistema = codigoSistema;
		this.produto = produto;
		this.sistemaDLO = sistemaDLO;
		this.helper = helper;
	}

	// RN - Modelo de dados
	// ??? Deve existir na entidade SISTEMA. Se erro retornar MSG0001. Deve
	// estar associado ao produto. Se erro retornar MSG0003.
	@Override
	public void run() throws DLOException {

		boolean codigoSistemaValido = false;
		try {
			codigoSistemaValido = sistemaDLO.isCodigoValido(codigoSistema);
		} catch (DLOException ex) {
		}
		
		Sistema sistemaCandidato = null;
		
		if (codigoSistemaValido) {
			sistemaCandidato = sistemaDLO.obterPorCodigo(codigoSistema);
		} 
		
		if (!codigoSistemaValido || sistemaCandidato == null) {
			helper.setCodigoRetornoParaParametro(
					CodigoMensagem.DPH0001,
					"codigoSistema");
			codigoSistemaValido = false;
			
		} else {
			
			sistema = sistemaCandidato;
		}
		
		setValida(codigoSistemaValido, helper.getRetorno().getMensagemRetorno());
	}

	public Sistema getSistema() {
		return sistema;
	}

}
