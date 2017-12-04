package br.com.delphos.sca.gruposInterfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDLO;
import br.com.delphos.sca.usuarios.Usuario;

@Local
public interface InterfaceDLO extends AbstractDLO<Interface, Long> {
	public Interface obterInterfacePorUri(String descricao) throws DelphosException;
	public boolean isAutorizado(Usuario usuario, String uri) throws DelphosException;
	public List<Interface> listarInterfacePorCriterio(String codigoInterface, Long idGrupoInterface) throws DelphosException;
	public List<Interface> listarInterfacePorGrupo(Long idGrupoInterface)throws DelphosException;
	public List<Interface> listarInterfacePorDescricao(String descricaoInterface)throws DelphosException;
	public void reassociarGrupoInterface(Interface interf, List<GrupoInterface> listGrupoInterface) throws DelphosException;
}
