package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface AdquirenteBandeiraDLO extends DLOEntidade<AdquirenteBandeira> {
	
	public List<AdquirenteBandeira> listarPorContrato(ContratoCobranca contratoCobranca);
	
	public void alterarAdquirenteBandeira(AdquirenteBandeira adquirenteBandeira);

	public void excluirAdquirenteBandeira(AdquirenteBandeira adquirenteBandeira);

	public void incluirAdquirenteBandeira(AdquirenteBandeira adquirenteBandeira) throws DLOException;
	
	public List<AdquirenteBandeira> listarPorCriterio(String idAdquirente, String idBandeira, String idContratoCobranca, 
			String adquirentePadrao, String codigoMetodoPagamento);

}
