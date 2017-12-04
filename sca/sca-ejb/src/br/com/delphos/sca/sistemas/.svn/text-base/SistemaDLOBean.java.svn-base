package br.com.delphos.sca.sistemas;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.ParametroInvalidoException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDLOBean;
import br.com.delphos.persistencia.validacoes.Identidade;
import br.com.delphos.persistencia.validacoes.Identificacao;
import br.com.delphos.persistencia.validacoes.Integridade;
import br.com.delphos.persistencia.validacoes.Validacoes;
import br.com.delphos.sca.constantes.CodigoParametro;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.util.Validador;

@Stateless
public class SistemaDLOBean extends AbstractDLOBean<Sistema, Long> implements SistemaDLO {

	@EJB
	private SistemaDAO dao;

	@EJB
	private EmpresaDLO empresaDLO;
	
	@Override
	protected SistemaDAO getDAOEntidade() {
		return dao;
	}

	@Override
	protected void validarCampoEntidade(Sistema entidade, String nomeAtributo, Class<?>... validacoes)
			throws ValidacaoException {
		CodigoParametro codigoParametro = CodigoParametro.Propriedade;
		String complemento = nomeAtributo;
		boolean valido = true;

		if (nomeAtributo.equals(Sistema_.id.getName()) && Validacoes.contem(Identificacao.class, validacoes)) {
			valido = entidade.hasId();

		} else if (nomeAtributo.equals(Sistema_.sigla.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			valido = !Validador.vazio(entidade.getSigla()) && entidade.getSigla().length() <= 10;
			
		} else if (nomeAtributo.equals(Sistema_.empresa.getName()) && Validacoes.contem(Identidade.class, validacoes)) {
			empresaDLO.validar(entidade.getEmpresa(), validacoes);
			
		} else if (nomeAtributo.equals(Sistema_.descricao.getName()) && Validacoes.contem(Integridade.class, validacoes)) {
			valido = Validador.vazio(entidade.getDescricao()) || entidade.getDescricao().length() <= 255;
		}

		if (!valido) {
			throw new ParametroInvalidoException(codigoParametro, complemento);
		}
	}
	
	@Override
	public List<Sistema> listarPorCriterio(String siglaSistema, String descricaoSistema, String idEmpresa) {
		
		List<Sistema> retorno = null;
		retorno = dao.listarPorCriterio(siglaSistema, descricaoSistema, idEmpresa);
		
		return retorno;
	}

}
