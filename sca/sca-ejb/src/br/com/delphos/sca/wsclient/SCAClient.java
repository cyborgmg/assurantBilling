package br.com.delphos.sca.wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.servicos.ControleAcessoService;
import br.com.delphos.sca.usuarios.Usuario;

public class SCAClient implements ControleAcessoService {
	
	public static class Endpoints {
	
		public static final String CONTEXT_PATH = "/sca-ejb/ControleAcesso?wsdl";
		public static final String LOCALHOST = "http://localhost:8080" + CONTEXT_PATH;
		public static final String LOCALHOST_DELPHOS = "http://localhost:80" + CONTEXT_PATH; // TODO TESTAR POIS APARENTEMENTE O PORTAL NÃO CONSEGUE CRIAR PROXY
		public static final String JBDES = "http://jbdes.delphos.mtz" + CONTEXT_PATH;
		public static final String JBTESTE = "http://jbteste.delphos.mtz" + CONTEXT_PATH;
		public static final String JBHOMOLOG = "http://jbhomolog.delphos.com.br" + CONTEXT_PATH;
	}
	
	public static final URL URL_WSDL;
	
	static {
		URL url = null;
		try {
			url = new URL(Endpoints.LOCALHOST);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		URL_WSDL = url;
	}

	private ControleAcessoServiceClient service = new ControleAcessoServiceClient(URL_WSDL);
	private ControleAcessoService port1 = service.getControleAcessoPort();

	public ControleAcessoServiceClient getService() {
		return service;
	}

	public void setService(ControleAcessoServiceClient service) {
		this.service = service;
	}

	public ControleAcessoService getPort1() {
		return port1;
	}

	public void setPort1(ControleAcessoService port1) {
		this.port1 = port1;
	}

	@Override
	public Usuario autenticar(String usuario, String senha) {
		try {
			return getPort1().autenticar(usuario, senha);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<Empresa> obterEmpresasUsuario(String uid, String token) {
		return getPort1().obterEmpresasUsuario(uid, token);
	}

	@Override
	public Usuario obterUsuarioPorId(String idUsuario) {
		return getPort1().obterUsuarioPorId(idUsuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return getPort1().listarUsuarios();
	}

	@Override
	public String obterNomeUsuario(String idUsuario) {
		return getPort1().obterNomeUsuario(idUsuario);
	}

	@Override
	public boolean alterarSenha(String uid, String atual, String nova) {
		return getPort1().alterarSenha(uid, atual, nova);
	}

	@Override
	public Usuario obterPorNomeLogin(String nomeLogin) {
		return getPort1().obterPorNomeLogin(nomeLogin);
	}

	@Override
	public boolean autorizar(String uid, String token, String uri) {
		return getPort1().autorizar(uid, token, uri);
	}

	// @Override
	// public boolean inativarUsuario(String id) {
	// return getPort1().inativarUsuario(id);
	// }
	//
	// @Override
	// public Interface criarNovaInteface(String descricao) {
	// return getPort1().criarNovaInteface(descricao);
	// }
	//
	//
	// @Override
	// public Usuario alterarUsuario(String id, String codigo, String descricao,
	// String email, String dataExpiracao) {
	// return getPort1().alterarUsuario(id, codigo, descricao, email,
	// dataExpiracao);
	// }
	//
	// @Override
	// public Usuario criarNovoUsuario(String codigo, String descricao,
	// String email, String senha, String dataExpiracao) {
	// return getPort1().criarNovoUsuario(codigo, descricao, email, senha,
	// dataExpiracao);
	// }

	@Override
	public String sincronizar(String uid, String token) {
		return getPort1().sincronizar(uid, token);
	}

	@Override
	public List<Usuario> listarUsuariosPorParteDescricao(String parteDescricao) {
		return getPort1().listarUsuariosPorParteDescricao(parteDescricao);
	}

	@Override
	public boolean iniciarEsqueceuSenha(String login, String email, String nomeServidor) {
		return getPort1().iniciarEsqueceuSenha(login, email, nomeServidor);
	}

	@Override
	public Usuario obterUsuarioPorToken(String token) {
		return getPort1().obterUsuarioPorToken(token);
	}

	@Override
	public Usuario finalizarEsqueciSenha(String token, String hashNovaSenha) {
		return getPort1().finalizarEsqueciSenha(token, hashNovaSenha);
	}
	
	@Override
	public Usuario confirmarEmailUsuario(String token, String hashNovaSenha) {
		return getPort1().confirmarEmailUsuario(token, hashNovaSenha);
	}

}
