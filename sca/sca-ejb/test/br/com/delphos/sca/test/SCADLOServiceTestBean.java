package br.com.delphos.sca.test;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ListAttribute;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.EntidadeInexistenteException;
import br.com.delphos.excecoes.PersistenciaException;
import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.empresas.Empresa_;
import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.sistemas.SistemaDLO;
import br.com.delphos.sca.sistemas.Sistema_;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;

/**** 
 * IMPORTANTE: ESTA (SOURCE FOLDER )"TEST" COM OS PACOTES NELA INCLUÍDOS,"JAMAIS" DEVERÁ SER,
 * EMPACOTADA PARA O CLIENTE , ESTÁ COMMITADO NO PROJETO PARA QUE OUTROS DESENVOLVEDORES DESENVOLVAM
 * COM RESPECTIVA SEPARAÇÃO DE ASPECTO , OS TESTES DE SEUS DLOS. 
 *  a url :http://<host>:<porta>/sca-ejb/SCADLOServiceTest?wsdl jamais poderá estar disponível para o cliente 
 *  e jamais criado client para ela 
 * ****/



@Stateless
@WebService(
	name = "SCADLOServiceTest",
	portName = "SCADLOServiceTestPort",
	targetNamespace = ConstantesSca.NAMESPACE_SCA,
	endpointInterface = "br.com.delphos.sca.test.SCADLOServiceTest")
public class SCADLOServiceTestBean implements SCADLOServiceTest {

	@EJB
	private EmpresaDLO empresaDLO;
	
	@EJB
	private SistemaDLO sistemaDLO;
	
	@EJB
	private UsuarioDLO usuarioDLO;
	

	@Override
	public boolean isAssociadoTest()
			throws DelphosException {
		Empresa empresa=new Empresa();
		empresa.setId(new Long(1));
		
		Usuario scaTeste01 =usuarioDLO.obterPorId(new Long(81));
		return empresaDLO.isAssociado(empresa, scaTeste01);
	}

	@Override
	public void associarTest()
			throws DelphosException {
		
		Empresa empresa=new Empresa();
		empresa.setId(new Long(1));
		
		Usuario scaTeste01 =usuarioDLO.obterPorId(new Long(81));
		Usuario scaTeste02 =usuarioDLO.obterPorId(new Long(82));
		
		empresaDLO.associar(empresa, scaTeste01,scaTeste02);
		
	}

	@Override
	public void desassociarTest()
			throws DelphosException {
		Empresa empresa=new Empresa();
		empresa.setId(new Long(1));
		
		Usuario scaTeste01 =usuarioDLO.obterPorId(new Long(81));
		Usuario scaTeste02 =usuarioDLO.obterPorId(new Long(82));
		
		empresaDLO.desassociar(empresa, scaTeste01,scaTeste02);
	}

	@Override
	public List<Empresa> prepararParaSerializacaoXMLTest1()
			throws DelphosException {
		return empresaDLO.prepararParaSerializacaoXML(empresaDLO.listar());
	}

	@Override
	public Empresa prepararParaSerializacaoXMLTest2()
			throws DelphosException {
		return empresaDLO.prepararParaSerializacaoXML(empresaDLO.obterPorId(new Long(1)));
	}

	@Override
	public Empresa instanciarTestEmpresa1() {
		return empresaDLO.instanciar();
	}

	@Override
	public Empresa instanciarTestEmpresa2() {
		return empresaDLO.instanciar(new Long(1));
	}

	@Override
	public boolean isPersistidoPorIdTestEmpresa()
			throws DelphosException {
		return empresaDLO.isPersistidoPorId(new Long(1));
	}

	@Override
	public boolean isPersistidoTestEmpresa()
			throws DelphosException {
		
		Empresa empresa=new Empresa();
		empresa.setId(new Long(1));
		
		return empresaDLO.isPersistido(empresa);
	}

	@Override
	public Long manterTestEmpresa() throws DelphosException {
		
		Empresa empresa=empresaDLO.obterPorId(new Long(21));
		
		empresa.setDescricao("TES99TE");
		empresa.setCodigo("099");
		
		return empresaDLO.manter(empresa);
	}

	@Override
	public void salvarTestEmpresa() throws DelphosException,
			PersistenciaException {
		Empresa empresa=new Empresa();
		empresa.setCodigo("T03");
		empresa.setDescricao("TESTE03");
		empresaDLO.salvar(empresa);
	}

	@Override
	public void excluirPorIdTestEmpresa() throws DelphosException {
		empresaDLO.excluirPorId(new Long(-9));		
	}

	@Override
	public void excluirTestEmpresa() throws DelphosException {
	 Empresa empresa=	empresaDLO.obterPorId(new Long(22));
		empresaDLO.excluir(empresa);
	}

	@Override
	public Empresa obterPorIdTestEmpresa() throws DelphosException {
		return empresaDLO.prepararParaSerializacaoXML(empresaDLO.obterPorId(new Long(22)));
	}

	@Override
	public Empresa obterTestEmpresa() throws DelphosException {
		Empresa empresa=new Empresa();
		empresa.setId(new Long(1));
		return empresaDLO.prepararParaSerializacaoXML(empresaDLO.obter(empresa)) ;
	}

	@Override
	public Empresa obterOuFalharTestEmpresa()
			throws DelphosException, EntidadeInexistenteException {
		Empresa empresa=new Empresa();
		empresa.setId(new Long(1));
		return	empresaDLO.prepararParaSerializacaoXML(empresaDLO.obterOuFalhar(empresa));
		
	}

	@Override
	public Empresa atualizarTestEmpresa()
			throws DelphosException {
		Empresa empresa= empresaDLO.obterPorId(new Long(1));
		return empresaDLO.prepararParaSerializacaoXML(empresaDLO.atualizar(empresa));
	}

	@Override
	public Empresa atualizarOuFalharTestEmpresa()
			throws DelphosException, EntidadeInexistenteException {
		Empresa empresa= empresaDLO.obterPorId(new Long(1));
		return empresaDLO.prepararParaSerializacaoXML(empresaDLO.atualizarOuFalhar(empresa));
	}

	@Override
	public List<Empresa> listarTestEmpresa() {
		List<Empresa> empresas=empresaDLO.listar();
	
		try {
			empresaDLO.prepararParaSerializacaoXML(empresas);
		} catch (DelphosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return empresas;
	}

	@Override
	public long contarTestEmpresa() {
		return empresaDLO.contar();
	}

	@Override
	public Sistema instanciarTestSistema1() {
		return sistemaDLO.instanciar();
	}

	@Override
	public Sistema instanciarTestSistema2() {
		return sistemaDLO.instanciar(new Long(21));
	}

	@Override
	public boolean isPersistidoPorIdTestSistema()
			throws DelphosException {
		return sistemaDLO.isPersistidoPorId(new Long(21));
	}

	@Override
	public boolean isPersistidoTestSistema()
			throws DelphosException {
		Sistema sistema = new Sistema();
		sistema.setId(new Long(21));
		return sistemaDLO.isPersistido(sistema);
	}

	@Override
	public Long manterTestSistema() throws DelphosException {
		Sistema sistema = new Sistema();
		sistema.setDescricao("ST2");
		sistema.setSigla("ST2");
		sistema.setEmpresa(empresaDLO.obterPorId(new Long(21)));
		return sistemaDLO.manter(sistema);
	}

	@Override
	public void salvarTestSistema() throws DelphosException,
			PersistenciaException {
		
		Sistema sistema = new Sistema();
		sistema.setDescricao("ST2");
		sistema.setSigla("ST2");
		sistema.setEmpresa(empresaDLO.obterPorId(new Long(21)));
		
		sistemaDLO.salvar(sistema);
	}

	@Override
	public void excluirPorIdTestSistema() throws DelphosException {
		sistemaDLO.excluirPorId((new Long(-9)));
	}

	@Override
	public void excluirTestSistema() throws DelphosException {
		sistemaDLO.excluir(sistemaDLO.obterPorId(new Long(-8)));
	}

	@Override
	public Sistema obterPorIdTestSistema() throws DelphosException {
		return sistemaDLO.obterPorId(new Long(21));
	}

	@Override
	public Sistema obterTestSistema() throws DelphosException {
		Sistema sistema =new Sistema();
		sistema.setId(new Long(21));
		return sistemaDLO.obter(sistema);
	}

	@Override
	public Sistema obterOuFalharTestSistema()
			throws DelphosException, EntidadeInexistenteException {
		Sistema sistema =new Sistema();
		sistema.setId(new Long(21));
		return sistemaDLO.obterOuFalhar(sistema);
	}

	@Override
	public Sistema atualizarTestSistema()
			throws DelphosException {
     Sistema sistema=		sistemaDLO.obterPorId(new Long(21));
 			

     return sistemaDLO.atualizar(sistema);
	}

	@Override
	public Sistema atualizarOuFalharTestSistema()
			throws DelphosException, EntidadeInexistenteException {
		Sistema sistema=		sistemaDLO.obterPorId(new Long(21));
		return   sistemaDLO.atualizarOuFalhar(sistema);
	}

	@Override
	public List<Sistema> listarTestSistema() {
		return sistemaDLO.listar();
	}

	@Override
	public long contarTestSistema() {
		return sistemaDLO.contar();
	}

	@Override
	public List<Empresa> listarPorFaixaTestEmpresa()
			throws DelphosException {
		
		 List<Empresa> empresas =empresaDLO.listarPorFaixa(1,23);
		
		for(Empresa empresa:empresas)
		     empresaDLO.completar(empresa, "sistemas");
				
		return empresas;
	}

	@Override
	public Empresa completarTestEmpresa()
			throws DelphosException {
		return empresaDLO.completar(empresaDLO.obterPorId(new Long(21)), "sistemas");
	}

	@Override
	public List<Sistema> listarPorFaixaTestSistema()
			throws DelphosException {
		return sistemaDLO.listarPorFaixa(1,22);
	}

	@Override
	public Sistema completarTestSistema1()
			throws DelphosException {
		return sistemaDLO.completar(sistemaDLO.obterPorId(new Long(21)), "empresa");
	}

	@Override
	public Sistema completarTestSistema2() throws DelphosException {
		Attribute<Sistema, Empresa> proriedades = null;
		proriedades=Sistema_.empresa;
		return sistemaDLO.completar(sistemaDLO.obterPorId(new Long(21)), proriedades);
	}

	@Override
	public Empresa completarTestEmpresaListaSistemas()
			throws DelphosException {
		ListAttribute<Empresa,Sistema> proriedades = null;
		proriedades =Empresa_.sistemas;
		return empresaDLO.completar(empresaDLO.obterPorId(new Long(22)), proriedades);
	}

	@Override
	public Empresa completarTestEmpresaListaUsuarios()
			throws DelphosException {
		ListAttribute<Empresa,Usuario> proriedades = null;
		proriedades =Empresa_.usuarios;
		return empresaDLO.completar(empresaDLO.obterPorId(new Long(22)), proriedades);
	}
	
}
