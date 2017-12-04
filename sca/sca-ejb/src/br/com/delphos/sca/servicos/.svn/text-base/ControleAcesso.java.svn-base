package br.com.delphos.sca.servicos;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.usuarios.Usuario;

@Local
public interface ControleAcesso {

	boolean autorizar(String uid, String token, String uri);

	Usuario autenticar(String login, String senha);

	boolean alterarSenha(String uid, String atual, String nova);

	List<Empresa> obterEmpresasUsuario(String uid, String token);

	String sincronizar(String uid);
	
	public String sincronizar(String uid, String token);

	String obterNomeUsuario(String idUsuario);

	Usuario obterUsuarioPorId(String idUsuario);

	List<Usuario> listarUsuarios();

	List<Usuario> listarUsuariosPorParteDescricao(String parteDescricao);

	Usuario obterPorNomeLogin(String nomeLogin);

	boolean iniciarEsqueceuSenha(String login, String email, String nomeServidor);

	Usuario obterUsuarioPorToken(String token);

	Usuario finalizarEsqueciSenha(String token, String hashNovaSenha);
	
}
