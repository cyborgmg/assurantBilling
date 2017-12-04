package br.com.delphos.billing.regras;

import java.util.List;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.servicos.retornos.RetornoBillingService;
import br.com.delphos.billing.util.AbstractRegraNegocio;
import br.com.delphos.billing.util.ServiceHelper;

public class RegraVendaCodigoEmpresa extends AbstractRegraNegocio {

	private final EmpresaDLO empresaDLO;
	private final String codigoEmpresa;
	private final ServiceHelper<? extends RetornoBillingService> helper;
	private Empresa empresa;
	private List<ContratoCobranca> contratosVigentes;
	
	public RegraVendaCodigoEmpresa(
			ServiceHelper<? extends RetornoBillingService> helper,
			String codigoEmpresa,
			EmpresaDLO empresaDLO) {
		super();
		this.empresaDLO = empresaDLO;
		this.codigoEmpresa = codigoEmpresa;
		this.helper = helper;
	}

	// RN - Modelo de dados
	// ??? Deve existir na entidade EMPRESA. Se erro retornar MSG0001.
	// EMPRESA deve estar associada a um CONTRATO_COBRANCA vigente. Se erro
	// retornar MSG0012.
	@Override
	public void run() throws DLOException {
	
		boolean codigoEmpresaValido = false;
			codigoEmpresaValido = empresaDLO.isCodigoValido(codigoEmpresa);
		
		if (codigoEmpresaValido) {
			empresa = empresaDLO.obterPorCodigo(codigoEmpresa);
		}
		
		if (!codigoEmpresaValido || empresa == null) {
			helper.setCodigoRetornoParaParametro(
				CodigoMensagem.DPH0001,
				"codigoEmpresa");
			codigoEmpresaValido = false;
			
		} else {
			contratosVigentes = empresaDLO.listarContratosVigentes(empresa);
			
			if (contratosVigentes == null || contratosVigentes.isEmpty()) {
				helper.setValorRetorno(CodigoMensagem.DPH0012);
				codigoEmpresaValido = false;
			}
		}
		
		setValida(codigoEmpresaValido, helper.getRetorno().getMensagemRetorno());
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public List<ContratoCobranca> getContratosVigentes() {
		return contratosVigentes;
	}
	
}
