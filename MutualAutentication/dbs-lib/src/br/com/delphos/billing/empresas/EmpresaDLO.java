package br.com.delphos.billing.empresas;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.metamodel.PluralAttribute;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface EmpresaDLO extends DLOEntidade<Empresa> {

	/**
	 * Valida um código de empresa e verifica se este se adequa
	 * aos requisitos da entidade.
	 * 
	 * Este método realiza apenas validações básicas e de natureza
	 * sintática. Não está contemplada a verificação de existência de
	 * uma empresa com o código passado. Para isto, utilize os métodos
	 * de busca por código.
	 * 
	 * @param codigoEmpresa O código da empresa a ser validado.
	 * @return {@code true} se for válido, e {@code false} caso contrário.
	 */
	public Empresa obterPorCodigo(String codigoEmpresa);
	
	/**
	 * Verifica se a empresa possui contratos de cobrança vigentes.
	 * 
	 * @param empresa A empresa a ser verificada..
	 * @return {@code true} se a empresa possui contratos vigentes, e
	 * {@code false} caso contrário, ou caso a empresa não seja válida.
	 * @throws DLOException 
	 */
	List<ContratoCobranca> listarContratosVigentes(Empresa empresa) throws DLOException;
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa)  throws DLOException;
	
	public Object incluir(Empresa empresa) throws DLOException;
	
	public void alterar(Empresa empresa) throws DLOException;
	
	public void alterarEmpresa(Empresa empresa);

	public void excluirEmpresa(Empresa empresa);

	public void incluirEmpresa(Empresa empresa);
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa, List<Empresa> listEmpresasUsuarioLogado) throws DLOException;
	public boolean isCodigoValido(String codigoEmpresa);
	
	public long contarReferencias(Empresa empresa, PluralAttribute<Empresa, ?, ?> atributo) throws DLOException;
}