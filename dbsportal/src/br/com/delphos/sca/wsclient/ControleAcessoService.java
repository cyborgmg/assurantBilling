package br.com.delphos.sca.wsclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11.redhat-3
 * 2015-08-11T17:04:21.913-03:00
 * Generated source version: 2.7.11.redhat-3
 * 
 */
@WebService(targetNamespace = "http://delphos.com.br/sca", name = "ControleAcesso")
@XmlSeeAlso({ObjectFactory.class})
public interface ControleAcessoService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "autenticar", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.Autenticar")
    @WebMethod
    @ResponseWrapper(localName = "autenticarResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.AutenticarResponse")
    public br.com.delphos.sca.wsclient.Usuario autenticar(
        @WebParam(name = "usuario", targetNamespace = "")
        java.lang.String usuario,
        @WebParam(name = "senha", targetNamespace = "")
        java.lang.String senha
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obterUsuarioPorToken", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterUsuarioPorToken")
    @WebMethod
    @ResponseWrapper(localName = "obterUsuarioPorTokenResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterUsuarioPorTokenResponse")
    public br.com.delphos.sca.wsclient.Usuario obterUsuarioPorToken(
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obterEmpresasUsuario", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterEmpresasUsuario")
    @WebMethod
    @ResponseWrapper(localName = "obterEmpresasUsuarioResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterEmpresasUsuarioResponse")
    public java.util.List<br.com.delphos.sca.wsclient.Empresa> obterEmpresasUsuario(
        @WebParam(name = "uid", targetNamespace = "")
        java.lang.String uid,
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "iniciarEsqueceuSenha", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.IniciarEsqueceuSenha")
    @WebMethod
    @ResponseWrapper(localName = "iniciarEsqueceuSenhaResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.IniciarEsqueceuSenhaResponse")
    public boolean iniciarEsqueceuSenha(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "email", targetNamespace = "")
        java.lang.String email,
        @WebParam(name = "nomeServidor", targetNamespace = "")
        java.lang.String nomeServidor
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obterUsuarioPorId", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterUsuarioPorId")
    @WebMethod
    @ResponseWrapper(localName = "obterUsuarioPorIdResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterUsuarioPorIdResponse")
    public br.com.delphos.sca.wsclient.Usuario obterUsuarioPorId(
        @WebParam(name = "idUsuario", targetNamespace = "")
        java.lang.String idUsuario
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "listarUsuarios", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ListarUsuarios")
    @WebMethod
    @ResponseWrapper(localName = "listarUsuariosResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ListarUsuariosResponse")
    public java.util.List<br.com.delphos.sca.wsclient.Usuario> listarUsuarios();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obterNomeUsuario", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterNomeUsuario")
    @WebMethod
    @ResponseWrapper(localName = "obterNomeUsuarioResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterNomeUsuarioResponse")
    public java.lang.String obterNomeUsuario(
        @WebParam(name = "idUsuario", targetNamespace = "")
        java.lang.String idUsuario
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "alterarSenha", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.AlterarSenha")
    @WebMethod
    @ResponseWrapper(localName = "alterarSenhaResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.AlterarSenhaResponse")
    public boolean alterarSenha(
        @WebParam(name = "uid", targetNamespace = "")
        java.lang.String uid,
        @WebParam(name = "atual", targetNamespace = "")
        java.lang.String atual,
        @WebParam(name = "nova", targetNamespace = "")
        java.lang.String nova
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "obterPorNomeLogin", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterPorNomeLogin")
    @WebMethod
    @ResponseWrapper(localName = "obterPorNomeLoginResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ObterPorNomeLoginResponse")
    public br.com.delphos.sca.wsclient.Usuario obterPorNomeLogin(
        @WebParam(name = "nomeLogin", targetNamespace = "")
        java.lang.String nomeLogin
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "listarUsuariosPorParteDescricao", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ListarUsuariosPorParteDescricao")
    @WebMethod
    @ResponseWrapper(localName = "listarUsuariosPorParteDescricaoResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ListarUsuariosPorParteDescricaoResponse")
    public java.util.List<br.com.delphos.sca.wsclient.Usuario> listarUsuariosPorParteDescricao(
        @WebParam(name = "parteDescricao", targetNamespace = "")
        java.lang.String parteDescricao
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "autorizar", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.Autorizar")
    @WebMethod
    @ResponseWrapper(localName = "autorizarResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.AutorizarResponse")
    public boolean autorizar(
        @WebParam(name = "uid", targetNamespace = "")
        java.lang.String uid,
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token,
        @WebParam(name = "uri", targetNamespace = "")
        java.lang.String uri
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "finalizarEsqueciSenha", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.FinalizarEsqueciSenha")
    @WebMethod
    @ResponseWrapper(localName = "finalizarEsqueciSenhaResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.FinalizarEsqueciSenhaResponse")
    public br.com.delphos.sca.wsclient.Usuario finalizarEsqueciSenha(
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token,
        @WebParam(name = "hashNovaSenha", targetNamespace = "")
        java.lang.String hashNovaSenha
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sincronizar", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.Sincronizar")
    @WebMethod
    @ResponseWrapper(localName = "sincronizarResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.SincronizarResponse")
    public java.lang.String sincronizar(
        @WebParam(name = "uid", targetNamespace = "")
        java.lang.String uid,
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token
    );
    
    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "confirmarEmailUsuario", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ConfirmarEmailUsuario")
    @WebMethod
    @ResponseWrapper(localName = "confirmarEmailUsuarioResponse", targetNamespace = "http://delphos.com.br/sca", className = "br.com.delphos.sca.wsclient.ConfirmarEmailUsuarioResponse")
    public br.com.delphos.sca.wsclient.Usuario confirmarEmailUsuario(
        @WebParam(name = "token", targetNamespace = "")
        java.lang.String token,
        @WebParam(name = "hashNovaSenha", targetNamespace = "")
        java.lang.String hashNovaSenha
    );
}