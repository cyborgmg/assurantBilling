package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.empresas.Empresa_;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class AdquirenteBandeiraDLOBean
 */
@Stateless
public class AdquirenteBandeiraDLOBean extends AbstractDLO<AdquirenteBandeira> implements AdquirenteBandeiraDLO {
	
	@EJB
	private AdquirenteBandeiraDAO dao;

	@Override
	protected AdquirenteBandeiraDAO getDAOEntidade() {
		return dao;
	}
	
	@EJB
	private EmpresaDLO empresaDLO;
	
	protected EmpresaDLO getEmpresaDLO() {
		return empresaDLO;
	}
	
	public List<AdquirenteBandeira> listarPorContrato(ContratoCobranca contratoCobranca) {
		return dao.listarPorContrato(contratoCobranca);
	}
	
	public void alterarAdquirenteBandeira(AdquirenteBandeira adquirenteBandeira) {
		dao.alterar(adquirenteBandeira);
	}

	public void excluirAdquirenteBandeira(AdquirenteBandeira adquirenteBandeira) {
		dao.excluir(adquirenteBandeira);
	}

	public void incluirAdquirenteBandeira(AdquirenteBandeira adquirenteBandeira) {
		dao.incluir(adquirenteBandeira);
	}
	
	public List<AdquirenteBandeira> listarPorCriterio(String idAdquirente, String idBandeira, String idContratoCobranca, 
			String adquirentePadrao, String codigoMetodoPagamento) {
		return dao.listarPorCriterio(idAdquirente, idBandeira, idContratoCobranca, adquirentePadrao, codigoMetodoPagamento);
	}
	
	@Override
	public boolean isIdEntidadeValido(Object id) {
		boolean valido = false;
		if (id instanceof AdquirenteBandeiraPK) {
			AdquirenteBandeiraPK pk = (AdquirenteBandeiraPK) id;
			valido = pk.getIdAdquirente() != null && pk.getIdBandeira() != null && pk.getIdContratoCobranca() != null;
		}
		return valido;
	}
	
	@Override
	public boolean isEntidadeIdentificada(AdquirenteBandeira entidade) {
		return entidade.getBandeira() != null 
				&& entidade.getAdquirente() != null 
				&& entidade.getContratoCobranca() != null;
	}

	@Override
	public boolean isEditavel(AdquirenteBandeira adquirenteBandeira) throws DLOException {
/*		Empresa empresaConsultaPrevia = adquirenteBandeira.getContratoCobranca().getEmpresa();
		empresaConsultaPrevia = empresaDLO.completar(empresaConsultaPrevia, Empresa_.vendas);
		Long contagemVendasParaEmpresa = Long.valueOf(empresaConsultaPrevia.getVendas().size());
		
		boolean temVenda = contagemVendasParaEmpresa > 0;
		
		return !temVenda;*/
		return true;
	}
}
