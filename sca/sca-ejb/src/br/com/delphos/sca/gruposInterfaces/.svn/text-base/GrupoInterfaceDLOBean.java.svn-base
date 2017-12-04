package br.com.delphos.sca.gruposInterfaces;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDAO;
import br.com.delphos.persistencia.AbstractDLOBean;
import br.com.delphos.persistencia.validacoes.Identidade;
import br.com.delphos.persistencia.validacoes.Identificacao;
import br.com.delphos.persistencia.validacoes.Validacoes;
import br.com.delphos.sca.constantes.CodigoParametro;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.util.Validador;

@Stateless
public class GrupoInterfaceDLOBean extends AbstractDLOBean<GrupoInterface, Long> implements GrupoInterfaceDLO {

	@EJB
	private GrupoInterfaceDAO dao;

	@EJB
	private InterfaceDLO interfaceDLO;
	
	@EJB
	private GrupoUsuarioDLO grupoUsuarioDLO;
	
	@Override
	protected AbstractDAO<GrupoInterface, Long> getDAOEntidade() {
		return dao;
	}

	@Override
	protected void validarCampoEntidade(GrupoInterface entidade, String nomeAtributo, Class<?>... validacoes)
			throws ValidacaoException {
		CodigoParametro codigoParametro = CodigoParametro.Propriedade;
		String complemento = nomeAtributo;
		boolean valido = true;

		if (nomeAtributo.equals(GrupoInterface_.id.getName()) && Validacoes.contem(Identificacao.class, validacoes)) {
			valido = entidade.hasId();

		} else if (nomeAtributo.equals(GrupoInterface_.descricao.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getDescricao()) && entidade.getDescricao().length() <= 255;
		}

		if (!valido) {
			throw new ParametroInvalidoException(codigoParametro, complemento);
		}
	}

	@Override
	public boolean isAssociado(GrupoInterface grupoInterface, Interface interfaci) throws DelphosException {
		boolean retorno = false;
		grupoInterface = obterOuFalhar(grupoInterface);
		interfaci = interfaceDLO.atualizarOuFalhar(interfaci);
		
		if (grupoInterface.getInterfaces() != null && grupoInterface.getInterfaces().contains(interfaci)) {
			retorno = true;
		}
		
		return retorno;
	}

	@Override
	public void associar(GrupoInterface grupoInterface, Interface... interfacis) throws DelphosException {
		grupoInterface = obterOuFalhar(grupoInterface);
		for (int i = 0; i < interfacis.length; ++i) {
			Interface usuario = interfaceDLO.atualizarOuFalhar(interfacis[i]);
			grupoInterface.addInterface(usuario);
		}
		salvar(grupoInterface);
	}

	@Override
	public void desassociar(GrupoInterface grupoInterface, Interface... interfacis) throws DelphosException {
		grupoInterface = obterOuFalhar(grupoInterface);
		for (int i = 0; i < interfacis.length; ++i) {
			Interface usuario = interfaceDLO.atualizarOuFalhar(interfacis[i]);
			grupoInterface.removeInterface(usuario);
		}
		salvar(grupoInterface);
	}
	
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacao(Long idGrupoUsuario) {
		return dao.obterGrupoInterfaceSemAssociacao(idGrupoUsuario);
	}
	
	public List<GrupoInterface> obterGrupoInterfaceSemAssociacaoComInterface(Long interfac) {
		return dao.obterGrupoInterfaceSemAssociacaoComInterface(interfac);
	}

	public List<GrupoInterface> listarPorCriterio (String descricaoGrupoUsuario) {
		return dao.listarPorCriterio(descricaoGrupoUsuario);
	}

	public List<GrupoUsuario> obterGrupoUsuariosSemAssociacao(String idGrupoInterface) {
		return dao.obterGrupoUsuariosSemAssociacao(idGrupoInterface);
	}
	
	public void reassociar(GrupoInterface grupoInterface, List<GrupoUsuario> listGrupoUsuario) throws DelphosException {
		grupoInterface = obterOuFalhar(grupoInterface);
		grupoInterface.getGruposUsuario().clear();
		for (Iterator iterator = listGrupoUsuario.iterator(); iterator.hasNext();) {
			GrupoUsuario grupoUsuario = grupoUsuarioDLO.atualizarOuFalhar((GrupoUsuario) iterator.next());
			grupoInterface.addGrupoUsuario(grupoUsuario);
		}
		salvar(grupoInterface);
	}
	
}
