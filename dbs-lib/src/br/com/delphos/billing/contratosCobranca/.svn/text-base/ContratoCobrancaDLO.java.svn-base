package br.com.delphos.billing.contratosCobranca;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.metamodel.Attribute;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ContratoCobrancaDLO extends DLOEntidade<ContratoCobranca> {
	
	/**
	 * Verifica se o contrato de cobrança passado está em vigência.
	 * 
	 * @param contratoCobranca O contrato de cobrança a ser verificado.
	 * @return {@code true} se estiver em vigência, e {@code false} caso
	 * contrário.
	 */
	boolean isVigente(ContratoCobranca contratoCobranca);
	
	public void alterarContratoCobranca(ContratoCobranca contratoCobranca);

	public void excluirContratoCobranca(ContratoCobranca contratoCobranca);

	public Long incluirContratoCobranca(ContratoCobranca contratoCobranca);
	
	public List<ContratoCobranca> listarPorAdquirente(Adquirente adquirente);
	
	public List<ContratoCobranca> listarPorBandeira(Bandeira bandeira);
	
	public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao);
	
	public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte);
	
	public List<ContratoCobranca> listarPorCriterioDescricao(String descricaoEmpresa, String descricaoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte);
	
	public boolean existeContratoNoPeriodo(String codigoEmpresa, String codigoProvedor, String codigoMeioPagamento, String periodoDe, 
			String periodoAte);
	
	public boolean isExcluivel(ContratoCobranca contratoCobranca) throws DLOException;

	public boolean existeContratoNoPeriodo(ContratoCobranca contratoCobranca) throws DLOException;
	
	public long contarRelacionamentos(ContratoCobranca contratoCobranca, Attribute<ContratoCobranca, ?> nomePropriedade) throws DLOException;
}
