package br.com.delphos.sca.empresas;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.persistencia.AbstractDAO;
import br.com.delphos.sca.usuarios.Usuario;


@Local
public interface EmpresaDAO extends AbstractDAO<Empresa, Long> {
	
	public List<Empresa> obterEmpresasUsuario(Usuario usuario);
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa);
	
	public List<Empresa> obterEmpresasSemAssociacao(String idUsuario) ;

}
