package br.com.delphos.sca.empresas;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDLO;
import br.com.delphos.sca.usuarios.Usuario;

@Local
public interface EmpresaDLO extends AbstractDLO<Empresa, Long> {

	boolean isAssociado(Empresa empresa, Usuario usuario) throws DelphosException;
	void associar(Empresa empresa, Usuario... usuarios) throws DelphosException;
	void desassociar(Empresa empresa, Usuario... usuarios) throws DelphosException;

	<C extends Collection<Empresa>> C prepararParaSerializacaoXML(C empresas) throws DelphosException;
	Empresa prepararParaSerializacaoXML(Empresa empresa) throws DelphosException;
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa) throws DelphosException;
	
	public List<Empresa> obterEmpresasSemAssociacao(String idUsuario);
	
	public void reassociar(Usuario usuario, List<Empresa> listEmpresa) throws DelphosException;
}
