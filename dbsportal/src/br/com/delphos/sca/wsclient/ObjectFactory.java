
package br.com.delphos.sca.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.delphos.sca.wsclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ObterPorNomeLogin_QNAME = new QName("http://delphos.com.br/sca", "obterPorNomeLogin");
    private final static QName _ListarUsuariosPorParteDescricao_QNAME = new QName("http://delphos.com.br/sca", "listarUsuariosPorParteDescricao");
    private final static QName _FinalizarEsqueciSenhaResponse_QNAME = new QName("http://delphos.com.br/sca", "finalizarEsqueciSenhaResponse");
    private final static QName _AutenticarResponse_QNAME = new QName("http://delphos.com.br/sca", "autenticarResponse");
    private final static QName _ObterEmpresasUsuario_QNAME = new QName("http://delphos.com.br/sca", "obterEmpresasUsuario");
    private final static QName _IniciarEsqueceuSenhaResponse_QNAME = new QName("http://delphos.com.br/sca", "iniciarEsqueceuSenhaResponse");
    private final static QName _AlterarSenha_QNAME = new QName("http://delphos.com.br/sca", "alterarSenha");
    private final static QName _GrupoUsuario_QNAME = new QName("http://delphos.com.br/sca", "grupoUsuario");
    private final static QName _Sincronizar_QNAME = new QName("http://delphos.com.br/sca", "sincronizar");
    private final static QName _ListarUsuariosPorParteDescricaoResponse_QNAME = new QName("http://delphos.com.br/sca", "listarUsuariosPorParteDescricaoResponse");
    private final static QName _Usuario_QNAME = new QName("http://delphos.com.br/sca", "usuario");
    private final static QName _ObterUsuarioPorToken_QNAME = new QName("http://delphos.com.br/sca", "obterUsuarioPorToken");
    private final static QName _ObterUsuarioPorId_QNAME = new QName("http://delphos.com.br/sca", "obterUsuarioPorId");
    private final static QName _ObterUsuarioPorIdResponse_QNAME = new QName("http://delphos.com.br/sca", "obterUsuarioPorIdResponse");
    private final static QName _AlterarSenhaResponse_QNAME = new QName("http://delphos.com.br/sca", "alterarSenhaResponse");
    private final static QName _ObterNomeUsuarioResponse_QNAME = new QName("http://delphos.com.br/sca", "obterNomeUsuarioResponse");
    private final static QName _Sistema_QNAME = new QName("http://delphos.com.br/sca", "sistema");
    private final static QName _SincronizarResponse_QNAME = new QName("http://delphos.com.br/sca", "sincronizarResponse");
    private final static QName _ObterEmpresasUsuarioResponse_QNAME = new QName("http://delphos.com.br/sca", "obterEmpresasUsuarioResponse");
    private final static QName _ListarUsuarios_QNAME = new QName("http://delphos.com.br/sca", "listarUsuarios");
    private final static QName _Autenticar_QNAME = new QName("http://delphos.com.br/sca", "autenticar");
    private final static QName _Empresa_QNAME = new QName("http://delphos.com.br/sca", "empresa");
    private final static QName _FinalizarEsqueciSenha_QNAME = new QName("http://delphos.com.br/sca", "finalizarEsqueciSenha");
    private final static QName _ObterNomeUsuario_QNAME = new QName("http://delphos.com.br/sca", "obterNomeUsuario");
    private final static QName _ObterUsuarioPorTokenResponse_QNAME = new QName("http://delphos.com.br/sca", "obterUsuarioPorTokenResponse");
    private final static QName _ObterPorNomeLoginResponse_QNAME = new QName("http://delphos.com.br/sca", "obterPorNomeLoginResponse");
    private final static QName _Autorizar_QNAME = new QName("http://delphos.com.br/sca", "autorizar");
    private final static QName _ListarUsuariosResponse_QNAME = new QName("http://delphos.com.br/sca", "listarUsuariosResponse");
    private final static QName _IniciarEsqueceuSenha_QNAME = new QName("http://delphos.com.br/sca", "iniciarEsqueceuSenha");
    private final static QName _AutorizarResponse_QNAME = new QName("http://delphos.com.br/sca", "autorizarResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.delphos.sca.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListarUsuariosPorParteDescricaoResponse }
     * 
     */
    public ListarUsuariosPorParteDescricaoResponse createListarUsuariosPorParteDescricaoResponse() {
        return new ListarUsuariosPorParteDescricaoResponse();
    }

    /**
     * Create an instance of {@link Usuario }
     * 
     */
    public Usuario createUsuario() {
        return new Usuario();
    }

    /**
     * Create an instance of {@link Sincronizar }
     * 
     */
    public Sincronizar createSincronizar() {
        return new Sincronizar();
    }

    /**
     * Create an instance of {@link GrupoUsuario }
     * 
     */
    public GrupoUsuario createGrupoUsuario() {
        return new GrupoUsuario();
    }

    /**
     * Create an instance of {@link AlterarSenha }
     * 
     */
    public AlterarSenha createAlterarSenha() {
        return new AlterarSenha();
    }

    /**
     * Create an instance of {@link ObterUsuarioPorToken }
     * 
     */
    public ObterUsuarioPorToken createObterUsuarioPorToken() {
        return new ObterUsuarioPorToken();
    }

    /**
     * Create an instance of {@link ObterUsuarioPorId }
     * 
     */
    public ObterUsuarioPorId createObterUsuarioPorId() {
        return new ObterUsuarioPorId();
    }

    /**
     * Create an instance of {@link AlterarSenhaResponse }
     * 
     */
    public AlterarSenhaResponse createAlterarSenhaResponse() {
        return new AlterarSenhaResponse();
    }

    /**
     * Create an instance of {@link ObterUsuarioPorIdResponse }
     * 
     */
    public ObterUsuarioPorIdResponse createObterUsuarioPorIdResponse() {
        return new ObterUsuarioPorIdResponse();
    }

    /**
     * Create an instance of {@link ObterPorNomeLogin }
     * 
     */
    public ObterPorNomeLogin createObterPorNomeLogin() {
        return new ObterPorNomeLogin();
    }

    /**
     * Create an instance of {@link ListarUsuariosPorParteDescricao }
     * 
     */
    public ListarUsuariosPorParteDescricao createListarUsuariosPorParteDescricao() {
        return new ListarUsuariosPorParteDescricao();
    }

    /**
     * Create an instance of {@link ObterEmpresasUsuario }
     * 
     */
    public ObterEmpresasUsuario createObterEmpresasUsuario() {
        return new ObterEmpresasUsuario();
    }

    /**
     * Create an instance of {@link FinalizarEsqueciSenhaResponse }
     * 
     */
    public FinalizarEsqueciSenhaResponse createFinalizarEsqueciSenhaResponse() {
        return new FinalizarEsqueciSenhaResponse();
    }

    /**
     * Create an instance of {@link AutenticarResponse }
     * 
     */
    public AutenticarResponse createAutenticarResponse() {
        return new AutenticarResponse();
    }

    /**
     * Create an instance of {@link IniciarEsqueceuSenhaResponse }
     * 
     */
    public IniciarEsqueceuSenhaResponse createIniciarEsqueceuSenhaResponse() {
        return new IniciarEsqueceuSenhaResponse();
    }

    /**
     * Create an instance of {@link Autorizar }
     * 
     */
    public Autorizar createAutorizar() {
        return new Autorizar();
    }

    /**
     * Create an instance of {@link IniciarEsqueceuSenha }
     * 
     */
    public IniciarEsqueceuSenha createIniciarEsqueceuSenha() {
        return new IniciarEsqueceuSenha();
    }

    /**
     * Create an instance of {@link AutorizarResponse }
     * 
     */
    public AutorizarResponse createAutorizarResponse() {
        return new AutorizarResponse();
    }

    /**
     * Create an instance of {@link ListarUsuariosResponse }
     * 
     */
    public ListarUsuariosResponse createListarUsuariosResponse() {
        return new ListarUsuariosResponse();
    }

    /**
     * Create an instance of {@link Sistema }
     * 
     */
    public Sistema createSistema() {
        return new Sistema();
    }

    /**
     * Create an instance of {@link ObterNomeUsuarioResponse }
     * 
     */
    public ObterNomeUsuarioResponse createObterNomeUsuarioResponse() {
        return new ObterNomeUsuarioResponse();
    }

    /**
     * Create an instance of {@link SincronizarResponse }
     * 
     */
    public SincronizarResponse createSincronizarResponse() {
        return new SincronizarResponse();
    }

    /**
     * Create an instance of {@link ObterEmpresasUsuarioResponse }
     * 
     */
    public ObterEmpresasUsuarioResponse createObterEmpresasUsuarioResponse() {
        return new ObterEmpresasUsuarioResponse();
    }

    /**
     * Create an instance of {@link ListarUsuarios }
     * 
     */
    public ListarUsuarios createListarUsuarios() {
        return new ListarUsuarios();
    }

    /**
     * Create an instance of {@link Empresa }
     * 
     */
    public Empresa createEmpresa() {
        return new Empresa();
    }

    /**
     * Create an instance of {@link Autenticar }
     * 
     */
    public Autenticar createAutenticar() {
        return new Autenticar();
    }

    /**
     * Create an instance of {@link FinalizarEsqueciSenha }
     * 
     */
    public FinalizarEsqueciSenha createFinalizarEsqueciSenha() {
        return new FinalizarEsqueciSenha();
    }

    /**
     * Create an instance of {@link ObterUsuarioPorTokenResponse }
     * 
     */
    public ObterUsuarioPorTokenResponse createObterUsuarioPorTokenResponse() {
        return new ObterUsuarioPorTokenResponse();
    }

    /**
     * Create an instance of {@link ObterPorNomeLoginResponse }
     * 
     */
    public ObterPorNomeLoginResponse createObterPorNomeLoginResponse() {
        return new ObterPorNomeLoginResponse();
    }

    /**
     * Create an instance of {@link ObterNomeUsuario }
     * 
     */
    public ObterNomeUsuario createObterNomeUsuario() {
        return new ObterNomeUsuario();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterPorNomeLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterPorNomeLogin")
    public JAXBElement<ObterPorNomeLogin> createObterPorNomeLogin(ObterPorNomeLogin value) {
        return new JAXBElement<ObterPorNomeLogin>(_ObterPorNomeLogin_QNAME, ObterPorNomeLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuariosPorParteDescricao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "listarUsuariosPorParteDescricao")
    public JAXBElement<ListarUsuariosPorParteDescricao> createListarUsuariosPorParteDescricao(ListarUsuariosPorParteDescricao value) {
        return new JAXBElement<ListarUsuariosPorParteDescricao>(_ListarUsuariosPorParteDescricao_QNAME, ListarUsuariosPorParteDescricao.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinalizarEsqueciSenhaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "finalizarEsqueciSenhaResponse")
    public JAXBElement<FinalizarEsqueciSenhaResponse> createFinalizarEsqueciSenhaResponse(FinalizarEsqueciSenhaResponse value) {
        return new JAXBElement<FinalizarEsqueciSenhaResponse>(_FinalizarEsqueciSenhaResponse_QNAME, FinalizarEsqueciSenhaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutenticarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "autenticarResponse")
    public JAXBElement<AutenticarResponse> createAutenticarResponse(AutenticarResponse value) {
        return new JAXBElement<AutenticarResponse>(_AutenticarResponse_QNAME, AutenticarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterEmpresasUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterEmpresasUsuario")
    public JAXBElement<ObterEmpresasUsuario> createObterEmpresasUsuario(ObterEmpresasUsuario value) {
        return new JAXBElement<ObterEmpresasUsuario>(_ObterEmpresasUsuario_QNAME, ObterEmpresasUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarEsqueceuSenhaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "iniciarEsqueceuSenhaResponse")
    public JAXBElement<IniciarEsqueceuSenhaResponse> createIniciarEsqueceuSenhaResponse(IniciarEsqueceuSenhaResponse value) {
        return new JAXBElement<IniciarEsqueceuSenhaResponse>(_IniciarEsqueceuSenhaResponse_QNAME, IniciarEsqueceuSenhaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlterarSenha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "alterarSenha")
    public JAXBElement<AlterarSenha> createAlterarSenha(AlterarSenha value) {
        return new JAXBElement<AlterarSenha>(_AlterarSenha_QNAME, AlterarSenha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GrupoUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "grupoUsuario")
    public JAXBElement<GrupoUsuario> createGrupoUsuario(GrupoUsuario value) {
        return new JAXBElement<GrupoUsuario>(_GrupoUsuario_QNAME, GrupoUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sincronizar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "sincronizar")
    public JAXBElement<Sincronizar> createSincronizar(Sincronizar value) {
        return new JAXBElement<Sincronizar>(_Sincronizar_QNAME, Sincronizar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuariosPorParteDescricaoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "listarUsuariosPorParteDescricaoResponse")
    public JAXBElement<ListarUsuariosPorParteDescricaoResponse> createListarUsuariosPorParteDescricaoResponse(ListarUsuariosPorParteDescricaoResponse value) {
        return new JAXBElement<ListarUsuariosPorParteDescricaoResponse>(_ListarUsuariosPorParteDescricaoResponse_QNAME, ListarUsuariosPorParteDescricaoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Usuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "usuario")
    public JAXBElement<Usuario> createUsuario(Usuario value) {
        return new JAXBElement<Usuario>(_Usuario_QNAME, Usuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterUsuarioPorToken }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterUsuarioPorToken")
    public JAXBElement<ObterUsuarioPorToken> createObterUsuarioPorToken(ObterUsuarioPorToken value) {
        return new JAXBElement<ObterUsuarioPorToken>(_ObterUsuarioPorToken_QNAME, ObterUsuarioPorToken.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterUsuarioPorId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterUsuarioPorId")
    public JAXBElement<ObterUsuarioPorId> createObterUsuarioPorId(ObterUsuarioPorId value) {
        return new JAXBElement<ObterUsuarioPorId>(_ObterUsuarioPorId_QNAME, ObterUsuarioPorId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterUsuarioPorIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterUsuarioPorIdResponse")
    public JAXBElement<ObterUsuarioPorIdResponse> createObterUsuarioPorIdResponse(ObterUsuarioPorIdResponse value) {
        return new JAXBElement<ObterUsuarioPorIdResponse>(_ObterUsuarioPorIdResponse_QNAME, ObterUsuarioPorIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AlterarSenhaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "alterarSenhaResponse")
    public JAXBElement<AlterarSenhaResponse> createAlterarSenhaResponse(AlterarSenhaResponse value) {
        return new JAXBElement<AlterarSenhaResponse>(_AlterarSenhaResponse_QNAME, AlterarSenhaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterNomeUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterNomeUsuarioResponse")
    public JAXBElement<ObterNomeUsuarioResponse> createObterNomeUsuarioResponse(ObterNomeUsuarioResponse value) {
        return new JAXBElement<ObterNomeUsuarioResponse>(_ObterNomeUsuarioResponse_QNAME, ObterNomeUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sistema }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "sistema")
    public JAXBElement<Sistema> createSistema(Sistema value) {
        return new JAXBElement<Sistema>(_Sistema_QNAME, Sistema.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SincronizarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "sincronizarResponse")
    public JAXBElement<SincronizarResponse> createSincronizarResponse(SincronizarResponse value) {
        return new JAXBElement<SincronizarResponse>(_SincronizarResponse_QNAME, SincronizarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterEmpresasUsuarioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterEmpresasUsuarioResponse")
    public JAXBElement<ObterEmpresasUsuarioResponse> createObterEmpresasUsuarioResponse(ObterEmpresasUsuarioResponse value) {
        return new JAXBElement<ObterEmpresasUsuarioResponse>(_ObterEmpresasUsuarioResponse_QNAME, ObterEmpresasUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuarios }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "listarUsuarios")
    public JAXBElement<ListarUsuarios> createListarUsuarios(ListarUsuarios value) {
        return new JAXBElement<ListarUsuarios>(_ListarUsuarios_QNAME, ListarUsuarios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Autenticar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "autenticar")
    public JAXBElement<Autenticar> createAutenticar(Autenticar value) {
        return new JAXBElement<Autenticar>(_Autenticar_QNAME, Autenticar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Empresa }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "empresa")
    public JAXBElement<Empresa> createEmpresa(Empresa value) {
        return new JAXBElement<Empresa>(_Empresa_QNAME, Empresa.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinalizarEsqueciSenha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "finalizarEsqueciSenha")
    public JAXBElement<FinalizarEsqueciSenha> createFinalizarEsqueciSenha(FinalizarEsqueciSenha value) {
        return new JAXBElement<FinalizarEsqueciSenha>(_FinalizarEsqueciSenha_QNAME, FinalizarEsqueciSenha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterNomeUsuario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterNomeUsuario")
    public JAXBElement<ObterNomeUsuario> createObterNomeUsuario(ObterNomeUsuario value) {
        return new JAXBElement<ObterNomeUsuario>(_ObterNomeUsuario_QNAME, ObterNomeUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterUsuarioPorTokenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterUsuarioPorTokenResponse")
    public JAXBElement<ObterUsuarioPorTokenResponse> createObterUsuarioPorTokenResponse(ObterUsuarioPorTokenResponse value) {
        return new JAXBElement<ObterUsuarioPorTokenResponse>(_ObterUsuarioPorTokenResponse_QNAME, ObterUsuarioPorTokenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterPorNomeLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "obterPorNomeLoginResponse")
    public JAXBElement<ObterPorNomeLoginResponse> createObterPorNomeLoginResponse(ObterPorNomeLoginResponse value) {
        return new JAXBElement<ObterPorNomeLoginResponse>(_ObterPorNomeLoginResponse_QNAME, ObterPorNomeLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Autorizar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "autorizar")
    public JAXBElement<Autorizar> createAutorizar(Autorizar value) {
        return new JAXBElement<Autorizar>(_Autorizar_QNAME, Autorizar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuariosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "listarUsuariosResponse")
    public JAXBElement<ListarUsuariosResponse> createListarUsuariosResponse(ListarUsuariosResponse value) {
        return new JAXBElement<ListarUsuariosResponse>(_ListarUsuariosResponse_QNAME, ListarUsuariosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarEsqueceuSenha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "iniciarEsqueceuSenha")
    public JAXBElement<IniciarEsqueceuSenha> createIniciarEsqueceuSenha(IniciarEsqueceuSenha value) {
        return new JAXBElement<IniciarEsqueceuSenha>(_IniciarEsqueceuSenha_QNAME, IniciarEsqueceuSenha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutorizarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://delphos.com.br/sca", name = "autorizarResponse")
    public JAXBElement<AutorizarResponse> createAutorizarResponse(AutorizarResponse value) {
        return new JAXBElement<AutorizarResponse>(_AutorizarResponse_QNAME, AutorizarResponse.class, null, value);
    }

}
