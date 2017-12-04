package br.com.delphos.billing.empresas;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.metamodel.PluralAttribute;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface EmpresaDAO extends DAOEntidade<Empresa> {

	/**
	 * Busca por uma empresa que possua o código de empresa passado.
	 * 
	 * @param codigoEmpresa O código da empresa a ser buscada.
	 * @return A empresa com o código passado, ou {@code null} caso
	 * não exista uma empresa com este código.
	 */
	Empresa obterPorCodigo(String codigoEmpresa);
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa);
	
}
