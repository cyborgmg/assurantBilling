package br.com.delphos.sca.usuarios;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.DelphosRuntimeException;
import br.com.delphos.excecoes.EntidadeInexistenteException;
import br.com.delphos.excecoes.EntidadeInvalidaException;
import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.SenhaExpiradaException;
import br.com.delphos.excecoes.SenhaIncorretaException;
import br.com.delphos.excecoes.SessaoInexistenteException;
import br.com.delphos.excecoes.SessaoInvalidaException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDLOBean;
import br.com.delphos.persistencia.validacoes.Alteracao;
import br.com.delphos.persistencia.validacoes.ConsultaPorIdentidade;
import br.com.delphos.persistencia.validacoes.Identidade;
import br.com.delphos.persistencia.validacoes.Identificacao;
import br.com.delphos.persistencia.validacoes.Integridade;
import br.com.delphos.persistencia.validacoes.Validacoes;
import br.com.delphos.sca.constantes.CodigoMensagem;
import br.com.delphos.sca.constantes.CodigoParametro;
import br.com.delphos.sca.constantes.OperacaoUsuario;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.spool.SpoolEmailDLO;
import br.com.delphos.util.Criptografia;
import br.com.delphos.util.Data;
import br.com.delphos.util.Mensagens;
import br.com.delphos.util.Validador;
import br.com.delphos.util.configuracoes.Configuracoes;
import br.com.delphos.util.configuracoes.Propriedades;
import br.com.delphos.util.email.Email;
import br.com.delphos.util.email.EmailException;

@Stateless
public class UsuarioDLOBean extends AbstractDLOBean<Usuario, Long>implements UsuarioDLO {

	@EJB
	private UsuarioDAO dao;

	@EJB
	private EmpresaDLO empresaDLO;
	
	@EJB
	private SpoolEmailDLO spoolEmailDLO;

	private SecureRandom random = new SecureRandom();
	
	public UsuarioDLOBean() {
	}

	@Override
	public List<Usuario> listarUsuariosAtivos() {
		return dao.listarUsuariosAtivos();
	}

	@Override
	protected UsuarioDAO getDAOEntidade() {
		return dao;
	}

	@Override
	protected Usuario prepararEntidadeParaManter(Usuario usuario) throws DelphosException {
		usuario = super.prepararEntidadeParaManter(usuario);
		usuario.setDataUltimaAlteracao(new Date());
		usuario.setDataExpiracao(new Date());
		prepararPropriedadesParaExecucao(usuario);
		if (!usuario.hasId() && usuario.getSenha() == null) {
			usuario.setSenha(gerarSenhaAleatoria());
		}
		return usuario;
	}
	
	@Override
	protected Usuario prepararParaExclusao(Usuario usuario) {
		prepararPropriedadesParaExecucao(usuario);
		return usuario;
	}
	
	private void prepararPropriedadesParaExecucao(Usuario usuario) {
		usuario.setCodigo(usuario.getCodigo() != null ? usuario.getCodigo().toUpperCase() : null);
		usuario.setDescricao(usuario.getDescricao() != null ? usuario.getDescricao().toUpperCase() : null);
	}
	
	@Override
	public List<Usuario> listarPorParteDescricao(String descricao) throws DelphosException {
		Usuario usuario = new Usuario();
		usuario.setDescricao(descricao);
		validar(usuario, Usuario_.descricao);
		List<Usuario> usuarios = dao.listarPorParteDescricao(descricao);
		return usuarios;
	}

	@Override
	public Usuario autenticar(Usuario usuario, String hashSenha) throws DelphosException {
		Usuario usuarioPersistido = obterOuFalhar(usuario);
//		String hashSenha = gerarSenhaCriptografada(senha);
		
		if (!isUsuarioAtivo(usuarioPersistido)) {
			throw new EntidadeInvalidaException();			
		}
		
		if (isSenhaUsuarioExpirada(usuarioPersistido)) {
			throw new SenhaExpiradaException();
		}
		
		if (!usuarioPersistido.getSenha().equals(hashSenha)) {
			throw new SenhaIncorretaException();
		}

		// TODO verificar o data_expiracao
		String token = gerarTokenSessao(usuarioPersistido);
		usuarioPersistido.setToken(token);
		salvar(usuarioPersistido);
		
		return usuarioPersistido;
	}

	@Override
	public <C extends Collection<Usuario>> C prepararParaSerializacaoXML(C usuarios) throws DelphosException {
		
		if (usuarios != null) {
			List<Usuario> usuariosNaoPreparados = new ArrayList<Usuario>(usuarios);
			usuarios.clear();
			for (Usuario usuario : usuariosNaoPreparados) {
				usuarios.add(prepararParaSerializacaoXML(usuario));
			}
		}
		
		return usuarios;
	}
	
	@Override
	public Usuario prepararParaSerializacaoXML(Usuario usuario) throws DelphosException {
		usuario = obterOuFalhar(usuario);
		
		usuario.getGruposUsuario().size();
		
		for (Empresa empresa : usuario.getEmpresas()) {
			empresa.getSistemas().size();
		}
		
		return usuario;
	}
	
	@Override
	public Usuario sincronizar(Usuario usuario) throws DelphosException {		
		if (usuario == null) {
			throw new EntidadeInvalidaException();
		}
		
		String tokenInformado = usuario.getToken();
		Usuario usuarioPersistido = obterOuFalhar(usuario);
		verificarSessaoUsuario(usuarioPersistido, tokenInformado);
		
		return usuarioPersistido;
	}

	@Override
	public void alterarSenha(Usuario usuario, String hashSenhaAnterior, String hashNovaSenha) throws DelphosException {
		Usuario usuarioPersistido = obterOuFalhar(usuario);

		if (!isUsuarioAtivo(usuarioPersistido)) {
			throw new EntidadeInvalidaException();			
		}
		
		if (!usuarioPersistido.getSenha().equals(hashSenhaAnterior)) {
			throw new SenhaIncorretaException();
		}

		// TODO Adicionar verificação da última senha
//		if (usuarioPersistido.getUltimaSenha() != null && usuarioPersistido.getUltimaSenha().equals(hashNovaSenha)) {
//			throw new SenhaInvalidaException();
//		}
		
		usuarioPersistido.setUltimaSenha(hashSenhaAnterior);
		usuarioPersistido.setSenha(hashNovaSenha);
		salvar(usuarioPersistido);
	}

	@Override
	public void inativar(Usuario usuario) throws DelphosException {
		Usuario usuarioPersistido = obterOuFalhar(usuario);
		usuarioPersistido.setDataExclusao(new Date());
		salvar(usuarioPersistido);
	}

	@Override
	public void ativar(Usuario usuario) throws DelphosException {
		Usuario usuarioPersistido = obterOuFalhar(usuario);
		usuarioPersistido.setDataExclusao(null);
		salvar(usuarioPersistido);
	}

	@Override
	public boolean isAtivo(Usuario usuario) throws DelphosException {
		usuario = obterOuFalhar(usuario);
		return isUsuarioAtivo(usuario);
	}
	
	@Override
	protected void validarCampoEntidade(Usuario entidade, String nomeAtributo, Class<?>... validacoes)
			throws ValidacaoException {
		boolean valido = true;
		
		if (nomeAtributo.equals(Usuario_.id.getName()) && Validacoes.contem(Identificacao.class, validacoes)) {
			valido = entidade.hasId();

		} else if (nomeAtributo.equals(Usuario_.codigo.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getCodigo()) && entidade.getCodigo().length() <= 50;
			
		} else if (nomeAtributo.equals(Usuario_.descricao.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getDescricao()) && entidade.getDescricao().length() <= 255;
			
		} else if (nomeAtributo.equals(Usuario_.email.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getEmail()) && entidade.getEmail().length() <= 255;
			
		} else if (nomeAtributo.equals(Usuario_.senha.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getSenha()) && entidade.getSenha().length() <= 50;
			
		} else if (nomeAtributo.equals(Usuario_.token.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = Validador.vazio(entidade.getToken()) || entidade.getToken().length() <= 100;
			
		} else if (nomeAtributo.equals(Usuario_.dataExpiracao.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = true;
			
		} else if (nomeAtributo.equals(Usuario_.dataExclusao.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = entidade.getDataExclusao() == null || entidade.getDataExclusao().compareTo(new Date()) <= 0;
			
		} else if (nomeAtributo.equals(Usuario_.dataUltimaAlteracao.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = entidade.getDataUltimaAlteracao() == null || entidade.getDataUltimaAlteracao().compareTo(new Date()) <= 0;;
			
		}
		
		if (!valido) {
			throw new ParametroInvalidoException(CodigoParametro.Propriedade, nomeAtributo);
		}
	}

	public List<Empresa> listarEmpresasPorUsuarioParaXml(Usuario usuario, String token) throws DelphosException {
		usuario = obterOuFalhar(usuario);

		verificarSessaoUsuario(usuario, usuario.getToken());
		usuario.getEmpresas().size(); // :/
		
		List<Empresa> empresas = usuario.getEmpresas();
		empresas = empresaDLO.prepararParaSerializacaoXML(empresas);
		return empresas;
	}
	
	public Usuario limparDadosSessao(Usuario usuario) throws DelphosException {
		if (usuario == null) {
			throw new EntidadeInvalidaException();
		}
		usuario = dao.destacar(usuario);
		usuario.setToken(null);
		return usuario;
	}

	public <C extends Collection<Usuario>> C limparDadosSessao(C usuarios) throws DelphosException {
		if (usuarios == null) {
			throw new EntidadeInvalidaException();
		}
		
		List<Usuario> usuariosNaoTratados = new ArrayList<Usuario>(usuarios);
		usuarios.clear();
		
		for (Usuario usuario : usuariosNaoTratados) {
			if (usuario != null) {
				usuario = limparDadosSessao(usuario);
			}
			usuarios.add(usuario);
		}
		
		return usuarios;
	}
	
	private String gerarSenhaCriptografada(String senha) {
		try {
			return Criptografia.geraHash(Criptografia.MD5, senha, false);
			
		} catch (NoSuchAlgorithmException e) {
			throw new DelphosRuntimeException(e);
		}
	}

	private boolean isUsuarioAtivo(Usuario usuario) throws DelphosException {
		return usuario.getDataExclusao() == null;
	}

	private void verificarSessaoUsuario(Usuario usuario) throws DelphosException {

		if (usuario.getToken() == null) {
			throw new SessaoInexistenteException();
		}
	}
	
	private void verificarSessaoUsuario(Usuario usuario, String token) throws DelphosException  {
		verificarSessaoUsuario(usuario);

		if (!usuario.getToken().equals(token)) {
			throw new SessaoInvalidaException();
		}
	}
	
//	private boolean isSessaoUsuarioExpirada(Usuario usuario) throws ScaException {
//		return usuario.getDataExpiracao() != null && usuario.getDataExpiracao().compareTo(new Date()) >= 0;
//	}

	@SuppressWarnings("unused")
	private boolean isSenhaUsuarioExpirada(Usuario usuario) throws DelphosException {
		// TODO Verificar data de expiração da senha.
		return false && usuario.getDataExpiracao() != null && usuario.getDataExpiracao().compareTo(new Date()) < 0;
	}
	
	private String gerarTokenSessao(Usuario usuario) {
		String data = Data.formatar(new Date(), "yyyyMMddhhmmss");
		String retorno;
		
		try {
			retorno = Criptografia.geraHash(Criptografia.MD5, usuario.getCodigo() + data, false);
			
		} catch (NoSuchAlgorithmException e) {
			throw new DelphosRuntimeException(e);
		}
		
		return retorno;
	}

	private String gerarTokenEsqueciSenha(Usuario usuario, String email) {
		String data = Data.formatar(new Date(), "yyyyMMddhhmmss");
		String retorno;
		
		try {
			retorno = Criptografia.geraHash(Criptografia.MD5, usuario.getCodigo() + email + data, true);
			
		} catch (NoSuchAlgorithmException e) {
			throw new DelphosRuntimeException(e);
		}
		
		return retorno;
	}
	
	private String gerarTokenConfirmacao(Usuario usuario) {
		return gerarTokenEsqueciSenha(usuario, usuario.getEmail());
	}
	
	private String gerarSenhaAleatoria() {
		String senha = new BigInteger(130, random).toString(32);
		return gerarSenhaCriptografada(senha);
	}
	
	private void prepararAlteracaoSenhaPorToken(Usuario usuario, String hashSenha) throws DelphosException {
		Usuario usuarioParaValidacao = new Usuario();
		usuarioParaValidacao.setSenha(hashSenha);
		validar(usuarioParaValidacao, Usuario_.senha, Alteracao.class);
		
		usuario.setToken(null);
		usuario.setUltimaSenha(usuario.getUltimaSenha());
		usuario.setSenha(hashSenha);
	}
	
	@Override
	public Usuario obterPorToken(String token) throws DelphosException {
		Usuario usuario = new Usuario();
		usuario.setToken(token);
		validar(usuario, Usuario_.token, ConsultaPorIdentidade.class);
		usuario = dao.obterPorToken(token);
		return usuario;
	}

	@Override
	public void iniciarEsqueciSenha(Usuario usuario, String emailDestino, URL urlServidor) throws DelphosException {
		usuario = obterOuFalhar(usuario);
		
		if (!usuario.getEmail().equalsIgnoreCase(emailDestino) || urlServidor == null) {
			throw new ParametroInvalidoException(CodigoParametro.Email);
		}
		
		String token = gerarTokenEsqueciSenha(usuario, emailDestino);
		String query = urlServidor.getQuery();
		String path = urlServidor.getPath();
		URL url = null;
		
		if (path == null) {
			path = "";
		}
		
		if (query == null) {
			query = "?";
		}
		
		try {
			url = new URL(
					urlServidor.getProtocol(),
					urlServidor.getHost(),
					urlServidor.getPort(),
					path + query + "t=" + URLEncoder.encode(token, "UTF-8") + "&o=" + OperacaoUsuario.ESQUECI_SENHA.getValor()
					);
		} catch (MalformedURLException e) {
			throw new DelphosRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new DelphosRuntimeException(e);
		}
		
		Email email = Email.novoEmail("ISO-8859-1")
				.addRemetente("gcd@delphos.com.br")
				.addDestinatario(emailDestino)
				.addAssunto("SCA - Redefinição de e-mail")
				.addMensagemHtml("<a href=\"" + url.toString() + "\">" + url.toString() + "</a>");
		
		try {
			spoolEmailDLO.enviar(email);
			
		} catch (EmailException e) {
			// TODO Nova exceção para o caso de erro no envio de e-mail.
			throw new DelphosException(e);
		}
		
		usuario.setToken(token);
		salvar(usuario);
	}
	
	@Override
	public Usuario finalizarEsqueciSenha(String token, String novaSenhaEmHash) throws DelphosException {
		Usuario usuarioPersistido = obterPorToken(token);
		
		if (usuarioPersistido == null) {
			throw new EntidadeInexistenteException();
		}
		
		prepararAlteracaoSenhaPorToken(usuarioPersistido, novaSenhaEmHash);
		salvar(usuarioPersistido);
		
		return usuarioPersistido;
	}

	@Override
	public void enviarEmailConfirmacao(Usuario usuario, URL urlServidor) throws DelphosException {
		usuario = obterOuFalhar(usuario);
		String token = gerarTokenConfirmacao(usuario);
		String query = urlServidor.getQuery();
		String path = urlServidor.getPath();
		URL url = null;
		
		if (path == null) {
			path = "";
		}
		
		if (query == null) {
			query = "?";
		}

		try {
			url = new URL(
					urlServidor.getProtocol(),
					urlServidor.getHost(),
					urlServidor.getPort(),
					path + query + "t=" + URLEncoder.encode(token, "UTF-8") + "&o=" + OperacaoUsuario.CONFIRMAR_EMAIL.getValor()
					);
		} catch (MalformedURLException e) {
			throw new DelphosRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new DelphosRuntimeException(e);
		}
		
		Email email = Email.novoEmail("ISO-8859-1")
				.addRemetente(Configuracoes.getString("email.remetente"))
				.addDestinatario(usuario.getEmail())
				.addAssunto("SCA - E-mail de confirmação de cadastro")
				.addMensagemHtml("<a href=\"" + url.toString() + "\">" + url.toString() + "</a>");
		
		try {
			spoolEmailDLO.enviar(email);
		} catch (EmailException e) {
			// TODO Nova exceção para o caso de erro no envio de e-mail.
			throw new DelphosException(e);
		}

		usuario.setToken(token);
		salvar(usuario);
		inativar(usuario);
	}
	
	@Override
	public Usuario confirmarEmailUsuario(String token, String hashSenha) throws DelphosException {
		Usuario usuarioPersistido = obterPorToken(token);
		
		if (usuarioPersistido == null) {
			throw new EntidadeInexistenteException();
		}

		prepararAlteracaoSenhaPorToken(usuarioPersistido, hashSenha);
		usuarioPersistido.setSenha(hashSenha);
		
		salvar(usuarioPersistido);
		ativar(usuarioPersistido);
		
		return usuarioPersistido;
	}
	
	public List<Usuario> listarPorCriterio (String codigoUsuario, String descricaoUsuario, String descricaoEmpresa) {
		
		return dao.listarPorCriterio (codigoUsuario, descricaoUsuario, descricaoEmpresa);
		
	}
	
}
