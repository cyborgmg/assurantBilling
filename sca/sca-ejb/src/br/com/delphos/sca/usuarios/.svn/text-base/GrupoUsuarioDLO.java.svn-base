package br.com.delphos.sca.usuarios;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDLO;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;

@Local
public interface GrupoUsuarioDLO extends AbstractDLO<GrupoUsuario, Long> {

	boolean isAssociado(GrupoUsuario grupoUsuario, Usuario usuario) throws DelphosException;
	void associar(GrupoUsuario grupoUsuario, Usuario... usuarios) throws DelphosException;
	void desassociar(GrupoUsuario grupoUsuario, Usuario... usuarios) throws DelphosException;

	boolean isGrupoInterfaceAssociado(GrupoUsuario grupoUsuario, GrupoInterface grupoInterface) throws DelphosException;
	void associarGrupoInterface(GrupoUsuario grupoUsuario, GrupoInterface... gruposInterface) throws DelphosException;
	void desassociarGrupoInterface(GrupoUsuario grupoUsuario, GrupoInterface... gruposInterface) throws DelphosException;
	
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idUsuario);
	
	public void reassociar(Usuario usuario, List<GrupoUsuario> listGrupoUsuario) throws DelphosException;
	
	public List<Usuario> obterUsuariosSemAssociacao(String idGrupoUsuario);
	
	public List<GrupoUsuario> listarPorCriterio (String tipoGrupo, String descricaoGrupoUsuario);
	
	public void reassociarUsuarios(GrupoUsuario grupoUsuario, List<Usuario> listUsuario) throws DelphosException;
	
	public void reassociarGrupoInterface(GrupoUsuario grupoUsuario, List<GrupoInterface> listGrupoInterface) throws DelphosException;
	
}
