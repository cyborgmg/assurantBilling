package br.com.delphos.sca.gruposInterfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.persistencia.AbstractDAO;
import br.com.delphos.sca.usuarios.GrupoUsuario;

@Local
public interface GrupoInterfaceDAO extends AbstractDAO<GrupoInterface, Long> {
	
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacao(Long idGrupoUsuario);
	
	public List<GrupoInterface> listarPorCriterio (String descricaoGrupoUsuario) ;
	
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idGrupoInterface);
	
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacaoComInterface(Long interfac);
	
}
