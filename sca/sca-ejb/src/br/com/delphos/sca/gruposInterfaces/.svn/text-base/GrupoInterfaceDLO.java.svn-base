package br.com.delphos.sca.gruposInterfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario;

@Local
public interface GrupoInterfaceDLO extends AbstractDLO<GrupoInterface, Long> {

	boolean isAssociado(GrupoInterface grupoInterface, Interface interfaci) throws DelphosException;
	void associar(GrupoInterface grupoInterface, Interface... interfacis) throws DelphosException;
	void desassociar(GrupoInterface grupoInterface, Interface... interfacis) throws DelphosException;
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacao(Long idGrupoUsuario);
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacaoComInterface(Long interfac);
	public List<GrupoInterface> listarPorCriterio (String descricaoGrupoUsuario);
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idGrupoInterface);
	public void reassociar(GrupoInterface grupoInterface, List<GrupoUsuario> listGrupoUsuario) throws DelphosException;
}
