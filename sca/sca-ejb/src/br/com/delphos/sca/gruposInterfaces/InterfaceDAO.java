package br.com.delphos.sca.gruposInterfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDAO;

@Local
public interface InterfaceDAO extends AbstractDAO<Interface, Long> {
	
	Interface obterInterfacePorDescricao(String descricao);
	public boolean isAutorizado(Long idUsuario, String uri) throws DelphosException;
	public List<Interface> listarInterfacePorDescricao(String descricaoInterface);
	public List<Interface> listarInterfacePorCriterio(String descricaoInterface, String descricaoGrupoInterface);
}
