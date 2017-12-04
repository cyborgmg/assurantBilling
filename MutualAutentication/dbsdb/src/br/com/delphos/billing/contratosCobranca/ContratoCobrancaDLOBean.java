package br.com.delphos.billing.contratosCobranca;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.metamodel.Attribute;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class ContratoCobrancaDLOBean
 */
@Stateless
public class ContratoCobrancaDLOBean extends AbstractDLO<ContratoCobranca> implements ContratoCobrancaDLO {
	
	@EJB
	private ContratoCobrancaDAO dao;

	@Override
	protected ContratoCobrancaDAO getDAOEntidade() {
		return dao;
	}
	
	@Override
	public boolean isEntidadeValida(ContratoCobranca entidade, Class<?>... validacoes) {
		boolean retorno = super.isEntidadeValida(entidade, validacoes);
		if (retorno) {
			retorno = entidade.getDataInicioVigencia() != null;
			retorno = retorno && entidade.getDataFimVigencia() != null;
			retorno = retorno && entidade.getDataInicioVigencia().compareTo(entidade.getDataFimVigencia()) <= 0;
//			retorno = retorno && entidade.getAdquirentesBandeiras() != null;
			retorno = retorno && entidade.getEmpresa() != null;
			retorno = retorno && entidade.getMeioPagamento() != null;
			retorno = retorno && entidade.getProvedor() != null;
		}
		return retorno;
	}
	
	@Override
	public boolean isVigente(ContratoCobranca contratoCobranca) {
		boolean retorno = false;
		if (isEntidadeValida(contratoCobranca)) {
			Date dataHoje = new Date();
			Date dataInicio = contratoCobranca.getDataInicioVigencia();
			Date dataFim = contratoCobranca.getDataFimVigencia();
			retorno = dataHoje.equals(dataInicio) || dataHoje.after(dataInicio);
			retorno = retorno && (dataHoje.equals(dataFim) || dataHoje.before(dataFim));
		}
		return retorno;
	}
	
	public void alterarContratoCobranca(ContratoCobranca contratoCobranca) {
		dao.alterar(contratoCobranca);
	}

	public void excluirContratoCobranca(ContratoCobranca contratoCobranca) {
		dao.excluir(contratoCobranca);
	}

	public Long incluirContratoCobranca(ContratoCobranca contratoCobranca) {
		return (Long) dao.incluir(contratoCobranca);
	}
	
	public List<ContratoCobranca> listarPorAdquirente(Adquirente adquirente) {
		return dao.listarPorAdquirente(adquirente);
	}
	
	public List<ContratoCobranca> listarPorBandeira(Bandeira bandeira) {
		return dao.listarPorBandeira(bandeira);
	}
	
	public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao) {
		
//		public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao, 
//				String codigoMeioPagamento, String periodoDe, String periodoAte);
		
		return dao.listarPorCriterio(codigoEmpresa, codigoProvedor, descricao, "", "", "");
	}
	
	public List<ContratoCobranca> listarPorCriterio(String codigoEmpresa, String codigoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte) {
		
		return dao.listarPorCriterio(codigoEmpresa, codigoProvedor, descricao, codigoMeioPagamento, periodoDe, periodoAte);
		
	}
	public List<ContratoCobranca> listarPorCriterioDescricao(String descricaoEmpresa, String descricaoProvedor, String descricao, 
			String codigoMeioPagamento, String periodoDe, String periodoAte) {
		
		return dao.listarPorCriterioDescricao(descricaoEmpresa, descricaoProvedor, descricao, codigoMeioPagamento, periodoDe, periodoAte);
		
	}
	
	public boolean existeContratoNoPeriodo(String codigoEmpresa, String codigoProvedor, String codigoMeioPagamento, String periodoDe, String periodoAte) {
		return dao.existeContratoNoPeriodo(codigoEmpresa, codigoProvedor, codigoMeioPagamento, periodoDe, periodoAte);
	}
	
	public boolean isExcluivel(ContratoCobranca contratoCobranca) throws DLOException {
		if (contratoCobranca == null) {
			throw new ValidacaoDLOException(CodigoMensagem.Falha);
		}
		Long id = contratoCobranca.getId();
		if (id == null) {
			contratoCobranca = obterEntidadePorIdentidade(contratoCobranca);
			if (contratoCobranca == null) {
				throw new DLOException(CodigoMensagem.Falha);
			}
			id = contratoCobranca.getId();
		}
		return dao.isExcluivelPorId(id);
	}
	
	public boolean existeContratoNoPeriodo(ContratoCobranca contratoCobranca) throws DLOException {
		if (contratoCobranca == null) {
			throw new ValidacaoDLOException(CodigoMensagem.Falha);
		}
		return dao.existeContratoNoPeriodo(contratoCobranca);
	}

	public long contarRelacionamentos(ContratoCobranca contratoCobranca, Attribute<ContratoCobranca, ?> nomePropriedade) throws DLOException {
		long retorno = 0;
		if (contratoCobranca == null || nomePropriedade == null || !nomePropriedade.isCollection()) {
			throw new ValidacaoDLOException(CodigoMensagem.Falha);
		}
		Long id = contratoCobranca.getId();
		if (id == null) {
			contratoCobranca = obterEntidadePorIdentidade(contratoCobranca);
			if (contratoCobranca == null) {
				throw new DLOException(CodigoMensagem.Falha);
			}
			id = contratoCobranca.getId();
		}
		retorno = dao.contarRelacionamentos(contratoCobranca, nomePropriedade.getName());
		return retorno;
	}

	@Override
	public boolean isEditavel(ContratoCobranca contratoCobranca) throws DLOException {
		return contarReferencias(contratoCobranca, ContratoCobranca_.vendas) == 0;
	}
}
