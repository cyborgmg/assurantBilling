package br.com.delphos.sca.gruposInterfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDLOBean;
import br.com.delphos.persistencia.validacoes.Identidade;
import br.com.delphos.persistencia.validacoes.Identificacao;
import br.com.delphos.persistencia.validacoes.Validacoes;
import br.com.delphos.sca.constantes.CodigoParametro;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.Validador;

@Stateless
public class InterfaceDLOBean extends AbstractDLOBean<Interface, Long>implements InterfaceDLO {

	@EJB
	private InterfaceDAO dao;

	@EJB
	private UsuarioDLO usuarioDLO;
	
	@EJB
	private GrupoInterfaceDAO grupoInterfaceDao;
	
	@EJB
	private GrupoInterfaceDLO grupoInterfaceDLO;

	public InterfaceDLOBean() {
	}

	@Override
	protected InterfaceDAO getDAOEntidade() {
		return dao;
	}
	
	@Override
	protected void validarCampoEntidade(Interface entidade, String nomeAtributo, Class<?>... validacoes)
			throws ValidacaoException {
		CodigoParametro codigoParametro = CodigoParametro.Propriedade;
		String complemento = nomeAtributo;
		boolean valido = true;

		if (nomeAtributo.equals(Interface_.id.getName()) && Validacoes.contem(Identificacao.class, validacoes)) {
			valido = entidade.hasId();

		} else if (nomeAtributo.equals(Interface_.descricao.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getDescricao()) && entidade.getDescricao().length() <= 255;
		}

		if (!valido) {
			throw new ParametroInvalidoException(codigoParametro, complemento);
		}
	}

	@Override
	public boolean isAutorizado(Usuario usuario, String uri) throws DelphosException {
		boolean retorno = false;
		usuario = usuarioDLO.obterOuFalhar(usuario);
		retorno = dao.isAutorizado(usuario.getId(), uri);
		return retorno;
	}

	@Override
	public Interface obterInterfacePorUri(String descricao) throws DelphosException {
		Interface interfaci = new Interface();
		interfaci.setDescricao(descricao);
		validar(interfaci, Interface_.descricao);
		return dao.obterInterfacePorDescricao(descricao);
	}

	@Override
	public List<Interface> listarInterfacePorCriterio(String descricaoInterface, Long idGrupoInterface) throws DelphosException {
	List<Interface> retorno = new ArrayList<Interface>();
		
		boolean isdescricaoInterface			=  (descricaoInterface!=null &&    !descricaoInterface.isEmpty());
		boolean isIdGrupoInterface 	    		=  (idGrupoInterface!=null);
		
		if (!isdescricaoInterface && !isIdGrupoInterface)
			throw new DelphosException("erro.codigo.descricao.interface.vazio");
	
		
		if(isIdGrupoInterface){
			GrupoInterface grupoInterface =null;
			grupoInterface=grupoInterfaceDao.obterPorId(idGrupoInterface);
			retorno =dao.listarInterfacePorCriterio(descricaoInterface, grupoInterface.getDescricao());	
		}else
			retorno =dao.listarInterfacePorCriterio(descricaoInterface,"");
		
		
		return retorno;
	}

	@Override
	public List<Interface> listarInterfacePorGrupo(Long idGrupoInterface)
			throws DelphosException {
		GrupoInterface grupoInterface=null;
		grupoInterface =grupoInterfaceDao.obterPorId(idGrupoInterface);
		
		
		if(grupoInterface!=null&& (grupoInterface.getInterfaces()!=null&&!grupoInterface.getInterfaces().isEmpty()))
		grupoInterface.getInterfaces().size();
		
		return grupoInterface.getInterfaces(); 
	}

	@Override
	public List<Interface> listarInterfacePorDescricao(String descricaoInterface)
			throws DelphosException {
		return dao.listarInterfacePorDescricao(descricaoInterface);
	}
	
	public void reassociarGrupoInterface(Interface interf, List<GrupoInterface> listGrupoInterface) throws DelphosException {
		interf = obterOuFalhar(interf);
		interf.getGruposInterface().clear();
		for (Iterator iterator = listGrupoInterface.iterator(); iterator.hasNext();) {
			GrupoInterface grupoInterfaceIt = grupoInterfaceDLO.atualizarOuFalhar((GrupoInterface) iterator.next());
			interf.addGrupoInterface(grupoInterfaceIt);
		}
		interf = completar(interf, Interface_.gruposInterface.getName());
		salvar(interf);
	}


}
