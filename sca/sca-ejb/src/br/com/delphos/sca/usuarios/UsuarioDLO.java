package br.com.delphos.sca.usuarios;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.persistencia.AbstractDLO;
import br.com.delphos.sca.empresas.Empresa;

@Local
public interface UsuarioDLO extends AbstractDLO<Usuario, Long> {

	public List<Usuario> listarUsuariosAtivos();
	
	public List<Usuario> listarPorParteDescricao(String descricao) throws DelphosException;
	
	public List<Empresa> listarEmpresasPorUsuarioParaXml(Usuario usuario, String token) throws DelphosException;
	
	public Usuario autenticar(Usuario usuario, String senha) throws DelphosException;
	
	public Usuario sincronizar(Usuario usuario) throws DelphosException;
	
	public void alterarSenha(Usuario usuario, String senhaAnterior, String novaSenha) throws DelphosException;
	
	public void inativar(Usuario usuario) throws DelphosException;

	public void ativar(Usuario usuario) throws DelphosException;
	
	public boolean isAtivo(Usuario usuario) throws DelphosException;
	
	public Usuario prepararParaSerializacaoXML(Usuario usuario) throws DelphosException;
	
	public <C extends Collection<Usuario>> C prepararParaSerializacaoXML(C usuarios) throws DelphosException;
	
	public Usuario limparDadosSessao(Usuario usuario) throws DelphosException;
	
	public <C extends Collection<Usuario>> C limparDadosSessao(C usuarios) throws DelphosException;
	
	public Usuario obterPorToken(String token) throws DelphosException;
	
	public void iniciarEsqueciSenha(Usuario usuario, String email, URL urlServidor) throws DelphosException;
	
	public Usuario finalizarEsqueciSenha(String token, String novaSenha) throws DelphosException;
	
	public void enviarEmailConfirmacao(Usuario usuario, URL urlServidor) throws DelphosException;

	public Usuario confirmarEmailUsuario(String token, String hashSenha) throws DelphosException;
	
	public List<Usuario> listarPorCriterio (String codigoUsuario, String descricaoUsuario, String descricaoEmpresa);
	
//	void associar(Usuario usuario, Empresa[] empresas) throws DelphosException;
}
