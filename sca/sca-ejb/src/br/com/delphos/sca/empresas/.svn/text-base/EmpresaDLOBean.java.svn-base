package br.com.delphos.sca.empresas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDLOBean;
import br.com.delphos.persistencia.validacoes.Identidade;
import br.com.delphos.persistencia.validacoes.Identificacao;
import br.com.delphos.persistencia.validacoes.Integridade;
import br.com.delphos.persistencia.validacoes.Validacoes;
import br.com.delphos.sca.constantes.CodigoParametro;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.Validador;

@Stateless
public class EmpresaDLOBean extends AbstractDLOBean<Empresa, Long>implements EmpresaDLO {

	@EJB
	private EmpresaDAO dao;
	
	@EJB
	private UsuarioDLO usuarioDLO;

	@Override
	protected EmpresaDAO getDAOEntidade() {
		return dao;
	}

	@Override
	protected void validarCampoEntidade(Empresa entidade, String nomeAtributo, Class<?>... validacoes)
			throws ValidacaoException {
		CodigoParametro codigoParametro = CodigoParametro.Propriedade;
		String complemento = nomeAtributo;
		boolean valido = true;

		if (nomeAtributo.equals(Empresa_.id.getName()) && Validacoes.contem(Identificacao.class, validacoes)) {
			valido = entidade.hasId();
					
		} else if (nomeAtributo.equals(Empresa_.codigo.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getCodigo()) && entidade.getCodigo().length() <= 3;

		} else if (nomeAtributo.equals(Empresa_.descricao.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getDescricao()) && entidade.getDescricao().length() <= 255;
		}

		if (!valido) {
			throw new ParametroInvalidoException(codigoParametro, complemento);
		}
	}

	@Override
	public boolean isAssociado(Empresa empresa, Usuario usuario) throws DelphosException {
		boolean retorno = false;
		empresa = obterOuFalhar(empresa);
		usuario = usuarioDLO.atualizarOuFalhar(usuario);
		
		if (empresa.getUsuarios() != null && empresa.getUsuarios().contains(usuario)) {
			retorno = true;
		}
		
		return retorno;
	}

	@Override
	public void associar(Empresa empresa, Usuario... usuarios) throws DelphosException {
		empresa = obterOuFalhar(empresa);
		for (int i = 0; i < usuarios.length; ++i) {
			Usuario usuario = usuarioDLO.atualizarOuFalhar(usuarios[i]);
			empresa.addUsuario(usuario);
		}
		salvar(empresa);
	}
	
	public void reassociar(Usuario usuario, List<Empresa> listEmpresa) throws DelphosException {
		usuario = usuarioDLO.obterOuFalhar(usuario);
		usuario.getEmpresas().clear();
		for (Iterator iterator = listEmpresa.iterator(); iterator.hasNext();) {
			Empresa empresa = atualizarOuFalhar((Empresa) iterator.next());
			usuario.addEmpresa(empresa);
		}
		usuarioDLO.salvar(usuario);
	}

	@Override
	public void desassociar(Empresa empresa, Usuario... usuarios) throws DelphosException {
		empresa = obterOuFalhar(empresa);
		for (int i = 0; i < usuarios.length; ++i) {
			Usuario usuario = usuarioDLO.atualizarOuFalhar(usuarios[i]);
			empresa.removeUsuario(usuario);
		}
		salvar(empresa);
	}

	@Override
	public <C extends Collection<Empresa>> C prepararParaSerializacaoXML(C empresas) throws DelphosException {
		
		if (empresas != null) {
			List<Empresa> empresasNaoPreparadas = new ArrayList<Empresa>(empresas);
			empresas.clear();
			for (Empresa empresa : empresasNaoPreparadas) {
				empresas.add(prepararParaSerializacaoXML(empresa));
			}
		}
		
		return empresas;
	}
	
	@Override
	public Empresa prepararParaSerializacaoXML(Empresa empresa) throws DelphosException {
		empresa = obterOuFalhar(empresa);
		
		empresa.getSistemas().size();
		
		return empresa;
	}
	
	public List<Empresa> listarEmpresasPorCriterio(String codigoEmpresa, String descricaoEmpresa) throws DelphosException {
		List<Empresa> retorno = new ArrayList<Empresa>();
		
		boolean isCodigoEmpresa 			=  (codigoEmpresa!=null && !codigoEmpresa.isEmpty());
		boolean isDescricaoEmpresa 	=  (descricaoEmpresa!=null && !descricaoEmpresa.isEmpty());
		
		if (!isCodigoEmpresa && !isDescricaoEmpresa) {
			throw new DelphosException("erro.codigo.descricao.empresa.vazio");
//			throw new DLOException("erro.codigo.descricao.empresa.vazio");
		}
		
		retorno = dao.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa);
		
		return retorno;
	}

	public List<Empresa> obterEmpresasSemAssociacao(String idUsuario) {
		return dao.obterEmpresasSemAssociacao(idUsuario);
	}
	
}
