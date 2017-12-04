package br.com.delphos.sca.test;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.EntidadeInexistenteException;
import br.com.delphos.excecoes.PersistenciaException;
import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.sistemas.Sistema;
/**** 
 * IMPORTANTE: ESTA (SOURCE FOLDER )"TEST" COM OS PACOTES NELA INCLUÍDOS,"JAMAIS" DEVERÁ SER,
 * EMPACOTADA PARA O CLIENTE , ESTÁ COMMITADO NO PROJETO PARA QUE OUTROS DESENVOLVEDORES DESENVOLVAM
 * COM RESPECTIVA SEPARAÇÃO DE ASPECTO , OS TESTES DE SEUS DLOS. 
 *  a url :http://<host>:<porta>/sca-ejb/SCADLOServiceTest?wsdl jamais poderá estar disponível para o cliente 
 *  e jamais criado client para ela 
 * ****/

@Remote
@WebService(
	name = "SCADLOServiceTest",
	targetNamespace = ConstantesSca.NAMESPACE_SCA
)
public interface SCADLOServiceTest {
	/***************************************************************************************************************************************************************/
	/*************************************************************EMPRESA*******************************************************************************************/
	/***************************************************************************************************************************************************************/
	//ESPECIFICOS PARA TESTAR EmpresaDLO
	@WebMethod
	boolean isAssociadoTest() throws DelphosException;
	@WebMethod
	void associarTest() throws DelphosException;
	@WebMethod
	void desassociarTest() throws DelphosException;
	@WebMethod
	List<Empresa>prepararParaSerializacaoXMLTest1() throws DelphosException;
	@WebMethod
	Empresa prepararParaSerializacaoXMLTest2() throws DelphosException;
	//ESPECIFICO PARA TESTAR OS HERDADOS DE AbstractDLO HERDADOS POR EmpresaDLO
	@WebMethod
	Empresa instanciarTestEmpresa1();
	@WebMethod
	Empresa instanciarTestEmpresa2();
	@WebMethod
	boolean isPersistidoPorIdTestEmpresa() throws DelphosException;
	@WebMethod
	boolean isPersistidoTestEmpresa() throws DelphosException;
	@WebMethod
	Long manterTestEmpresa() throws DelphosException;
	@WebMethod
	void salvarTestEmpresa() throws DelphosException, PersistenciaException;
	@WebMethod
	void excluirPorIdTestEmpresa() throws DelphosException;
	@WebMethod
	void excluirTestEmpresa() throws DelphosException;
	@WebMethod
	Empresa obterPorIdTestEmpresa() throws DelphosException;
	@WebMethod
	Empresa obterTestEmpresa() throws DelphosException;
	@WebMethod
	Empresa obterOuFalharTestEmpresa() throws DelphosException, EntidadeInexistenteException;
	@WebMethod
	Empresa atualizarTestEmpresa() throws DelphosException;
	@WebMethod
	Empresa atualizarOuFalharTestEmpresa() throws DelphosException, EntidadeInexistenteException;
	@WebMethod
	List<Empresa> listarTestEmpresa();
	@WebMethod
	long contarTestEmpresa();
	@WebMethod
	List<Empresa> listarPorFaixaTestEmpresa() throws DelphosException;
	//estes métodos ABAIXO retornam um objeto com as coleçoes carregadas
	@WebMethod
	Empresa completarTestEmpresa() throws DelphosException;
	@WebMethod
	Empresa completarTestEmpresaListaSistemas() throws DelphosException;
	@WebMethod
	Empresa completarTestEmpresaListaUsuarios() throws DelphosException;
	/***************************************************************************************************************************************************************/
	/*************************************************************SISTEMA*******************************************************************************************/
	/***************************************************************************************************************************************************************/
	//ESPECIFICO PARA TESTAR OS HERDADOS DE AbstractDLO HERDADOS POR SistemaDLO
	@WebMethod
	Sistema instanciarTestSistema1();
	@WebMethod
	Sistema instanciarTestSistema2();
	@WebMethod
	boolean isPersistidoPorIdTestSistema() throws DelphosException;
	@WebMethod
	boolean isPersistidoTestSistema() throws DelphosException;
	@WebMethod
	Long manterTestSistema() throws DelphosException;
	@WebMethod
	void salvarTestSistema() throws DelphosException, PersistenciaException;
	@WebMethod
	void excluirPorIdTestSistema() throws DelphosException;
	@WebMethod
	void excluirTestSistema() throws DelphosException;
	@WebMethod
	Sistema obterPorIdTestSistema() throws DelphosException;
	@WebMethod
	Sistema obterTestSistema() throws DelphosException;
	@WebMethod
	Sistema obterOuFalharTestSistema() throws DelphosException, EntidadeInexistenteException;
	@WebMethod
	Sistema atualizarTestSistema() throws DelphosException;
	@WebMethod
	Sistema atualizarOuFalharTestSistema() throws DelphosException, EntidadeInexistenteException;
	@WebMethod
	List<Sistema> listarTestSistema();
	@WebMethod
	long contarTestSistema();
	@WebMethod
	List<Sistema> listarPorFaixaTestSistema() throws DelphosException;
	@WebMethod
	Sistema completarTestSistema1() throws DelphosException;
	@WebMethod
	Sistema completarTestSistema2() throws DelphosException;
}
