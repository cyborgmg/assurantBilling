package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface AdquirenteBandeiraDAO extends DAOEntidade<AdquirenteBandeira> {
	
	public List<AdquirenteBandeira> listarPorContrato(ContratoCobranca contratoCobranca);
	
	public List<AdquirenteBandeira> listarPorCriterio(String idAdquirente, String idBandeira, String idContratoCobranca, 
			String adquirentePadrao, String codigoMetodoPagamento);
	
}
