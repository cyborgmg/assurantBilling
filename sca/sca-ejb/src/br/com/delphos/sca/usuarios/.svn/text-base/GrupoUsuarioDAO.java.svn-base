package br.com.delphos.sca.usuarios;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.persistencia.AbstractDAO;

@Local
public interface GrupoUsuarioDAO extends AbstractDAO<GrupoUsuario, Long>{
	
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idUsuario);
	
	public List<Usuario> obterUsuariosSemAssociacao(String idGrupoUsuario);
	
	public List<GrupoUsuario> listarPorCriterio (String tipoGrupo, String descricaoGrupoUsuario);
}
