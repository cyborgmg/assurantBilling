package br.com.delphos.billing.empresas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.Mensagens;

/**
 * Session Bean implementation class EmpresaDLOBean
 */
@Stateless
public class EmpresaDLOBean extends AbstractDLO<Empresa> implements EmpresaDLO {
	
	@EJB
	private EmpresaDAO dao;

	@EJB ContratoCobrancaDLO contratoCobrancaDLO;
	
	@Override
	protected EmpresaDAO getDAOEntidade() {
		return dao;
	}

	public boolean isCodigoValido(String codigoEmpresa) {
		
//		if (codigoEmpresa == null) {
//			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.codigoEmpresa")));
//		}
		return codigoEmpresa != null && codigoEmpresa.length() >= 1 && codigoEmpresa.length() <= 3;
	}
	
	@Override
	public Empresa obterPorCodigo(String codigoEmpresa) {
		Empresa retorno = null;
//		if (isCodigoValido(codigoEmpresa)) {
			retorno = dao.obterPorCodigo(codigoEmpresa);
//		}
		return retorno;
	}
	
	@Override
	public List<ContratoCobranca> listarContratosVigentes(Empresa empresa) throws DLOException {
		List<ContratoCobranca> retorno = null;
		if (isEntidadeValida(empresa)) {
			retorno = new ArrayList<ContratoCobranca>();
			empresa = dao.obterEntidadeAtualizada(empresa);
			for (ContratoCobranca contrato : empresa.getContratosCobranca()) {
				if (contratoCobrancaDLO.isVigente(contrato)) {
					retorno.add(contrato);
				}
			}
		} else {
			throw new DLOException(Mensagens.get("parametro.invalido", Mensagens.get("parametro.validacaoDLO.empresa")));			
		}
		return retorno;
	}
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa)  throws DLOException {
		List<Empresa> retorno = new ArrayList<Empresa>();
		
		boolean isCodigoEmpresa 			=  (codigoEmpresa!=null && !codigoEmpresa.isEmpty());
		boolean isDescricaoEmpresa 	=  (descricaoEmpresa!=null && !descricaoEmpresa.isEmpty());
		
        if (!isCodigoEmpresa && !isDescricaoEmpresa) {
	        throw new DLOException("erro.codigo.descricao.empresa.vazio");
        }
        
        retorno = dao.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa);
		
		return retorno;
	}
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa, List<Empresa> listEmpresasUsuarioLogado) throws DLOException {
		List<Empresa> retorno = new ArrayList<Empresa>();
		
		boolean isCodigoEmpresa 			=  (codigoEmpresa!=null && !codigoEmpresa.isEmpty());
		boolean isDescricaoEmpresa 	=  (descricaoEmpresa!=null && !descricaoEmpresa.isEmpty());
		
		if (!isCodigoEmpresa && !isDescricaoEmpresa) {
			throw new DLOException("erro.codigo.descricao.empresa.vazio");
		}
		
		retorno = dao.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa);
		
		return retorno;
	}
	
	public void alterarEmpresa(Empresa empresa) {
		dao.alterar(empresa);
	}

	public void excluirEmpresa(Empresa empresa) {
		dao.excluir(empresa);
	}

	public void incluirEmpresa(Empresa empresa) {
		dao.incluir(empresa);
	}

	@Override
	public Object incluir(Empresa empresa) throws DLOException {
		return dao.incluir(empresa);
	}

	@Override
	public void alterar(Empresa empresa) throws DLOException {
		dao.alterar(empresa);
	}

	@Override
	public boolean isEditavel(Empresa empresa) throws DLOException {
		
    	boolean permiteAlteracao = contarReferencias(empresa, Empresa_.vendas) == 0;
		
		return permiteAlteracao;
	}
	
}
