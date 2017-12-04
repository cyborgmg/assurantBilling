package br.com.delphos.billing.empresas;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.metamodel.PluralAttribute;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface EmpresaDAO extends DAOEntidade<Empresa> {

	/**
	 * Busca por uma empresa que possua o c�digo de empresa passado.
	 * 
	 * @param codigoEmpresa O c�digo da empresa a ser buscada.
	 * @return A empresa com o c�digo passado, ou {@code null} caso
	 * n�o exista uma empresa com este c�digo.
	 */
	Empresa obterPorCodigo(String codigoEmpresa);
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa);
	
}
