package br.com.delphos.sca.servicos;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.usuarios.Usuario;

@Remote
@WebService(
	name = "ControleAcesso",
	targetNamespace = ConstantesSca.NAMESPACE_SCA
)
public interface ControleAcessoService {

	@WebMethod
	public boolean autorizar(@WebParam(name = "uid") String uid,
			@WebParam(name = "token") String token,
			@WebParam(name = "uri") String uri);

	@WebMethod
	public Usuario autenticar(@WebParam(name = "usuario") String usuario,
			@WebParam(name = "senha") String senha);

	@WebMethod
	public boolean alterarSenha(@WebParam(name = "uid") String uid,
			@WebParam(name = "atual") String atual,
			@WebParam(name = "nova") String nova);

	@WebMethod
	public List<Empresa> obterEmpresasUsuario(
			@WebParam(name = "uid") String uid,
			@WebParam(name = "token") String token);

	@WebMethod
	public String sincronizar(
			@WebParam(name = "uid") String uid,
			@WebParam(name = "token") String token
			);

	@WebMethod
	public String obterNomeUsuario(@WebParam(name = "idUsuario") String idUsuario);

	@WebMethod
	public Usuario obterUsuarioPorId(
			@WebParam(name = "idUsuario") String idUsuario);
	
	@WebMethod
	public List<Usuario> listarUsuarios();

	@WebMethod
	public List<Usuario> listarUsuariosPorParteDescricao(
			@WebParam(name = "parteDescricao") String parteDescricao
			);
		
	@WebMethod
	public Usuario obterPorNomeLogin(@WebParam(name = "nomeLogin"	) String nomeLogin);
	
	@WebMethod
	public Usuario obterUsuarioPorToken(
			@WebParam(name = "token") String token
			);
	
	@WebMethod
    public boolean iniciarEsqueceuSenha(
    		@WebParam(name = "login") String login,
    		@WebParam(name = "email") String email,
    		@WebParam(name = "nomeServidor") String nomeServidor
    		);

	@WebMethod
	public Usuario finalizarEsqueciSenha(
			@WebParam(name = "token") String token,
			@WebParam(name = "hashNovaSenha") String hashNovaSenha
			);

	@WebMethod
	public Usuario confirmarEmailUsuario(
			@WebParam(name = "token") String token,
			@WebParam(name = "hashSenha") String hashSenha
			);
}
