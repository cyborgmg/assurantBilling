package br.com.delphos.billing.contratosCobranca;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ContratoCobrancaDAO extends DAOEntidade<ContratoCobranca> {
	
	public List<ContratoCobranca> listarPorAdquirente(Adquirente adquirente);
	
	public List<ContratoCobranca> listarPorBandeira(Bandeira bandeira);
	
	public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte);
	
	public List<ContratoCobranca> listarPorCriterioDescricao(String descricaoEmpresa, String descricaoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte);
	
	public boolean existeContratoNoPeriodo(String codigoEmpresa, String codigoProvedor, String codigoMeioPagamento, String periodoDe, String periodoAte);
	
	public boolean isExcluivelPorId(Long idContratoCobranca) throws DLOException;
	
	public boolean existeContratoNoPeriodo(ContratoCobranca contratoCobranca);

	public long contarRelacionamentos(ContratoCobranca contratoCobranca,
			String nomePropriedade);
}
