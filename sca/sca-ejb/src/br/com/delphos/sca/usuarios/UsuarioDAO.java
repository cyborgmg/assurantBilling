package br.com.delphos.sca.usuarios;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.persistencia.AbstractDAO;
@Local
public interface UsuarioDAO extends AbstractDAO<Usuario, Long> {
	
	public List<Usuario> listarUsuariosAtivos();
	
	public List<Usuario> listarPorParteDescricao(String string);

	public Usuario obterPorToken(String token);
	
	public List<Usuario> listarPorCriterio (String codigoUsuario, String descricaoUsuario, String idEmpresa);
}
