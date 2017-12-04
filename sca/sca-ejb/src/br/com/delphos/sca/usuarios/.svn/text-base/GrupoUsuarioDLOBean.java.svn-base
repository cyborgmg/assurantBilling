package br.com.delphos.sca.usuarios;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDLOBean;
import br.com.delphos.persistencia.validacoes.Identidade;
import br.com.delphos.persistencia.validacoes.Identificacao;
import br.com.delphos.persistencia.validacoes.Integridade;
import br.com.delphos.persistencia.validacoes.Validacoes;
import br.com.delphos.sca.constantes.CodigoParametro;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import br.com.delphos.sca.gruposInterfaces.GrupoInterfaceDLO;
import br.com.delphos.util.Validador;

@Stateless
public class GrupoUsuarioDLOBean extends AbstractDLOBean<GrupoUsuario, Long> implements GrupoUsuarioDLO {

	@EJB
	private GrupoUsuarioDAO dao;

	@EJB
	private UsuarioDLO usuarioDLO;
	
	@EJB
	private GrupoInterfaceDLO grupoInterfaceDLO;
	
	@Override
	protected GrupoUsuarioDAO getDAOEntidade() {
		return dao;
	}

	@Override
	protected void validarCampoEntidade(GrupoUsuario entidade, String nomeAtributo, Class<?>... validacoes) throws ValidacaoException {
		boolean valido = true;
		
		if (nomeAtributo.equals(GrupoUsuario_.id.getName()) && Validacoes.contem(Identificacao.class, validacoes)) {
			valido = entidade.hasId();

		} else if (nomeAtributo.equals(GrupoUsuario_.descricao.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getDescricao()) && entidade.getDescricao().length() <= 255;
			
		} else if (nomeAtributo.equals(GrupoUsuario_.tipoGrupo.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = Validador.vazio(entidade.getTipoGrupo()) || entidade.getTipoGrupo().length() <= 10;
		}
		
		if (!valido) {
			throw new ParametroInvalidoException(CodigoParametro.Propriedade, nomeAtributo);
		}
	}

	@Override
	public boolean isAssociado(GrupoUsuario grupoUsuario, Usuario usuario) throws DelphosException {
		boolean retorno = false;
		grupoUsuario = obterOuFalhar(grupoUsuario);
		usuario = usuarioDLO.atualizarOuFalhar(usuario);
		
		if (grupoUsuario.getUsuarios() != null && grupoUsuario.getUsuarios().contains(usuario)) {
			retorno = true;
		}
		
		return retorno;
	}

	@Override
	public void associar(GrupoUsuario grupoUsuario, Usuario... usuarios) throws DelphosException {
		grupoUsuario = obterOuFalhar(grupoUsuario);
		for (int i = 0; i < usuarios.length; ++i) {
			Usuario usuario = usuarioDLO.atualizarOuFalhar(usuarios[i]);
			grupoUsuario.addUsuario(usuario);
		}
		salvar(grupoUsuario);
	}
	
	public void reassociar(Usuario usuario, List<GrupoUsuario> listGrupoUsuario) throws DelphosException {
		usuario = usuarioDLO.obterOuFalhar(usuario);
		usuario.getGruposUsuario().clear();
		for (Iterator iterator = listGrupoUsuario.iterator(); iterator.hasNext();) {
			GrupoUsuario grupoUsuario = atualizarOuFalhar((GrupoUsuario) iterator.next());
			usuario.addGrupoUsuario(grupoUsuario);
		}
		usuarioDLO.salvar(usuario);
	}
	
	public void reassociarUsuarios(GrupoUsuario grupoUsuario, List<Usuario> listUsuario) throws DelphosException {
		grupoUsuario = obterOuFalhar(grupoUsuario);
		grupoUsuario.getUsuarios().clear();
		for (Iterator iterator = listUsuario.iterator(); iterator.hasNext();) {
			Usuario usuarioIt = usuarioDLO.atualizarOuFalhar((Usuario) iterator.next());
			grupoUsuario.addUsuario(usuarioIt);
		}
		salvar(grupoUsuario);
	}
	
	public void reassociarGrupoInterface(GrupoUsuario grupoUsuario, List<GrupoInterface> listGrupoInterface) throws DelphosException {
		grupoUsuario = obterOuFalhar(grupoUsuario);
		grupoUsuario.getGruposInterface().clear();
		for (Iterator iterator = listGrupoInterface.iterator(); iterator.hasNext();) {
			GrupoInterface grupoInterfaceIt = grupoInterfaceDLO.atualizarOuFalhar((GrupoInterface) iterator.next());
			grupoUsuario.addGrupoInterface(grupoInterfaceIt);
		}
		salvar(grupoUsuario);
	}
	
	@Override
	public void desassociar(GrupoUsuario grupoUsuario, Usuario... usuarios) throws DelphosException {
		grupoUsuario = obterOuFalhar(grupoUsuario);
		for (int i = 0; i < usuarios.length; ++i) {
			Usuario usuario = usuarioDLO.atualizarOuFalhar(usuarios[i]);
			grupoUsuario.removeUsuario(usuario);
		}
		salvar(grupoUsuario);
	}

	@Override
	public boolean isGrupoInterfaceAssociado(GrupoUsuario grupoUsuario, GrupoInterface grupoInterface)
			throws DelphosException {
		boolean retorno = false;
		grupoUsuario = obterOuFalhar(grupoUsuario);
		grupoInterface = grupoInterfaceDLO.atualizarOuFalhar(grupoInterface);
		
		if (grupoUsuario.getUsuarios() != null && grupoUsuario.getUsuarios().contains(grupoInterface)) {
			retorno = true;
		}
		
		return retorno;
	}

	@Override
	public void associarGrupoInterface(GrupoUsuario grupoUsuario, GrupoInterface... gruposInterface)
			throws DelphosException {
		grupoUsuario = obterOuFalhar(grupoUsuario);
		for (int i = 0; i < gruposInterface.length; ++i) {
			GrupoInterface grupoInterface = grupoInterfaceDLO.atualizarOuFalhar(gruposInterface[i]);
			grupoUsuario.addGrupoInterface(grupoInterface);
		}
		salvar(grupoUsuario);
	}

	@Override
	public void desassociarGrupoInterface(GrupoUsuario grupoUsuario, GrupoInterface... gruposInterface)
			throws DelphosException {
		grupoUsuario = obterOuFalhar(grupoUsuario);
		for (int i = 0; i < gruposInterface.length; ++i) {
			GrupoInterface grupoInterface = grupoInterfaceDLO.atualizarOuFalhar(gruposInterface[i]);
			grupoUsuario.removeGrupoInterface(grupoInterface);
		}
		salvar(grupoUsuario);
	}
	
	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idUsuario) {
		return dao.obterGrupoUsuariosSemAssociacao(idUsuario);
	}
	
	public List<Usuario> obterUsuariosSemAssociacao(String idGrupoUsuario) {
		return dao.obterUsuariosSemAssociacao(idGrupoUsuario);
	}
	
	public List<GrupoUsuario> listarPorCriterio (String tipoGrupo, String descricaoGrupoUsuario) {
		return dao.listarPorCriterio(tipoGrupo, descricaoGrupoUsuario);
	}

}
