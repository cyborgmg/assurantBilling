package br.com.delphos.sca.servicos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.gruposInterfaces.InterfaceDLO;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.Validador;

@Local
@Stateless
public class ControleAcessoBean implements ControleAcesso {

	@EJB
	private UsuarioDLO usuarioDLO;

	@EJB
	private InterfaceDLO interfaceDLO;

	@EJB
	private EmpresaDLO empresaDLO;

	@EJB
	private GrupoUsuarioDLO grupoUsuarioDLO;

	@Override
	public boolean autorizar(String uid, String token, String uri) {
		boolean retorno = false;
		Usuario usuario = new Usuario();
		usuario.setCodigo(uid);
		usuario.setToken(token);
		
		try {
			retorno = interfaceDLO.isAutorizado(usuario, uri);
			
		} catch (DelphosException e) {
		}
		
		return retorno;
	}

	@Override
	public Usuario autenticar(String login, String senha) {
		Usuario usuario = new Usuario();
		usuario.setCodigo(login);
		Usuario retorno = null;
		
		try {
			retorno = usuarioDLO.prepararParaSerializacaoXML(
					usuarioDLO.autenticar(usuario, senha)
				);
			
		} catch (DelphosException e) {
		}
		
		return retorno;
	}

	@Override
	public boolean alterarSenha(String uid, String atual, String nova) {
		boolean retorno = false;
		Usuario usuario = new Usuario();
		usuario.setCodigo(uid);
	
		try {
			usuarioDLO.alterarSenha(usuario, atual, nova);
			retorno = true;
			
		} catch (DelphosException e) {
		}
		
		return retorno;
	}

	@Override
	public List<Empresa> obterEmpresasUsuario(String uid, String token) {
		List<Empresa> retorno = null;
		Usuario usuario = new Usuario();
		usuario.setCodigo(uid);
		
		try {
			retorno = usuarioDLO.listarEmpresasPorUsuarioParaXml(usuario, token);
			
		} catch (DelphosException e) {
		}
		
		return retorno;
	}

	@Override
	@Deprecated
	public String sincronizar(String uid) {
		String retorno = null;
		Usuario usuario = new Usuario();
		usuario.setCodigo(uid);
		// TODO Possibilidade de verificar o token durante a sincronização
		
		try {
			usuario = usuarioDLO.sincronizar(usuario);
			retorno = usuario.getToken();
			
		} catch (DelphosException e) {
		}
		
		return retorno;
	}
	
	@Override
	public String sincronizar(String uid, String token) {
		String retorno = null;
		Usuario usuario = new Usuario();
		usuario.setCodigo(uid);
		usuario.setToken(token);
		// TODO Possibilidade de verificar o token durante a sincronização
		
		try {
			usuario = usuarioDLO.sincronizar(usuario);
			retorno = usuario.getToken();
			
		} catch (DelphosException e) {
		}
		
		return retorno;
	}

	@Override
	public String obterNomeUsuario(String idUsuario) {
		String retorno = null;
		
		if (Validador.inteiro(idUsuario)) {
			Usuario usuario = new Usuario();
			usuario.setId(Long.parseLong(idUsuario));
			
			try {
				usuario = usuarioDLO.atualizarOuFalhar(usuario);
				// TODO Código ou descrição?
				retorno = usuario.getDescricao();
				
			} catch (DelphosException e) {
			}
		}
		
		return retorno;
	}

	@Override
	public Usuario obterUsuarioPorId(String idUsuario) {
		Usuario retorno = null;
		
		if (Validador.inteiro(idUsuario)) {
			Usuario usuario = new Usuario();
			usuario.setId(Long.parseLong(idUsuario));
			
			try {
				retorno = usuarioDLO.limparDadosSessao(usuarioDLO.prepararParaSerializacaoXML(usuario));
				
			} catch (DelphosException e) {
			}
		}
		
		return retorno;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		try {
			List<Usuario> usuarios = usuarioDLO.prepararParaSerializacaoXML(usuarioDLO.listar());
			return usuarioDLO.limparDadosSessao(usuarios);
		} catch (DelphosException e) {
			return null;
		}
	}

	@Override
	public List<Usuario> listarUsuariosPorParteDescricao(String parteDescricao) {
		try {
			List<Usuario> usuarios = usuarioDLO.prepararParaSerializacaoXML(
					usuarioDLO.listarPorParteDescricao(parteDescricao)
				);
			return usuarioDLO.limparDadosSessao(usuarios);
		} catch (DelphosException e) {
			return null;
		}
	}

	@Override
	public Usuario obterPorNomeLogin(String nomeLogin) {
		Usuario usuario = new Usuario();
		usuario.setCodigo(nomeLogin);
		
		try {
			usuario = usuarioDLO.limparDadosSessao(usuarioDLO.prepararParaSerializacaoXML(usuario));
			
		} catch (DelphosException e) {
		}
		
		return usuario;
	}

	@Override
    public boolean iniciarEsqueceuSenha(String login, String email, String nomeServidor) {
		boolean retorno = false;
		
		try {
			Usuario usuario = new Usuario();
			usuario.setCodigo(login);
			usuarioDLO.iniciarEsqueciSenha(usuario, email, new URL(nomeServidor));
			retorno = true;
			
		} catch (DelphosException ex) {
		} catch (MalformedURLException ex) {
		}
		
		return retorno;
	}

	@Override
	public Usuario obterUsuarioPorToken(String token) {
		Usuario retorno = null;
		
		try {
			retorno = usuarioDLO.obterPorToken(token);
		} catch (DelphosException e) {
		}
		
		return retorno;
	}

	@Override
	public Usuario finalizarEsqueciSenha(String token, String hashNovaSenha) {
		Usuario retorno = null;
		
		try {
			retorno = usuarioDLO.finalizarEsqueciSenha(token, hashNovaSenha);
		} catch (DelphosException e) {
		}
		
		return retorno;
	}
	

}
