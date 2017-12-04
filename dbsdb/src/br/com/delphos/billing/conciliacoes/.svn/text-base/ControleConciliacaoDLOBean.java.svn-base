package br.com.delphos.billing.conciliacoes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.StatusControleConciliacao;
import br.com.delphos.billing.enumeracoes.TipoMensagemConciliacao;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.parametros.Parametro;
import br.com.delphos.billing.parametros.ParametroDLO;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

@Stateless
public class ControleConciliacaoDLOBean extends AbstractDLO<ControleConciliacao> implements ControleConciliacaoDLO {
	public static Logger LOGGER = LoggerFactory
			.getLogger(ControleConciliacaoDLOBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);

	@EJB
	private ControleConciliacaoDAO dao;
	
	@EJB
	private ParametroDLO parametroDLO;

	@EJB
	private AdquirenteDLO adquirenteDLO;
	
	@Override
	protected ControleConciliacaoDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isEditavel(ControleConciliacao entidade)
			throws DLOException {
		// TODO UPDATE: Pode ser editado, desde que não haja arquivo importado (status Pendente). Se rejeitado, apenas pode ser solicitado o reprocessamento.
		return entidade != null 
				&& entidade.isArquivoImportado()
				&& (entidade.getStatus() == StatusControleConciliacao.Pendente
					|| entidade.getStatus() == StatusControleConciliacao.ReprocessamentoSolicitado);
	}
	
	public boolean validarValorConciliacaoTela(ControleConciliacao conciliacaoNovo) throws DLOException{
		
		if (conciliacaoNovo.getValorDeposito() != null 
				&& conciliacaoNovo.getValorArquivo() != null
				&& conciliacaoNovo.getValorDeposito().compareTo(BigDecimal.ZERO) >= 0
				&& conciliacaoNovo.getValorArquivo().compareTo(BigDecimal.ZERO) >= 0) {
				
				conciliacaoNovo.setStatus(StatusControleConciliacao.Liberado);
				conciliacaoNovo.setTipoMensagemConciliacao(TipoMensagemConciliacao.ValorDefault);
				
				if (conciliacaoNovo.getId() != null) {
					salvar(conciliacaoNovo);
					
					return true;
				}
				
			} 
			
		return false;
	}
	
	
	@Override
	public boolean validarValorArquivoConciliacao(Adquirente adquirente, Date dataMovimento, ControleConciliacao conciliacaoNovo) throws DLOException {
		return validarValor(conciliacaoNovo);
	}

	@Override
	public ControleConciliacao buscarPorAdquirenteDataMovimento(Adquirente adquirente, Date dataMovimento)
			throws DLOException {
		if (adquirente == null 
				|| (adquirente.getId() == null && adquirente.getNome() == null)
				|| dataMovimento == null) {
			throw new ValidacaoDLOException();
		}
		if (adquirente.getNome() == null) {
			adquirente = adquirenteDLO.obter(adquirente.getId());
		}
		return dao.buscarPorAdquirenteDataMovimento(adquirente, dataMovimento);
	}

	@Override
	public boolean validarValor(ControleConciliacao conciliacao) throws DLOException {
		conciliacao = validarPorValor(conciliacao);
		StatusControleConciliacao status = conciliacao.getStatus();
		return status != null && status.isValidoParaProcesso();
	}
	
	@Override
	public ControleConciliacao validarPorValor(ControleConciliacao conciliacao) throws DLOException {
		if (conciliacao == null) {
			throw new ValidacaoDLOException();
		}
		
		boolean valido = (conciliacao.getValorArquivo() == null 
				          || conciliacao.getValorArquivo().compareTo(BigDecimal.ZERO) >= 0)
						  && (conciliacao.getValorDeposito() == null 
						  || conciliacao.getValorDeposito().compareTo(BigDecimal.ZERO) >= 0);
				
		if (valido) {
			if (conciliacao.getValorDeposito() != null && conciliacao.getValorArquivo() != null) {
				if (conciliacao.getValorArquivo().compareTo(conciliacao.getValorDeposito()) == 0) {
					conciliacao.setStatus(StatusControleConciliacao.Liberado);
					
				} else {
					valido = false;
					conciliacao.setStatus(StatusControleConciliacao.ErroValor);
					conciliacao.setTipoMensagemConciliacao(
							TipoMensagemConciliacao.ValorArquivoDiferenteDoValorDeposito);
				}
			}
			
		} else {
			conciliacao.setStatus(StatusControleConciliacao.ErroValor);
		}

		if (conciliacao.getId() != null) {
			salvar(conciliacao);
		}
		
		return conciliacao;
	}
	
	@Override
	public List<Adquirente> listarAdquirentesAtivosConciliacao(Date dataHoje) throws DLOException {
		Parametro parametro = parametroDLO.obterUltimosParametros();
		return dao.listarAdquirentesAtivosConciliacao(DateUtils.getDataHoraAtual(), parametro.getLimiteAdquirenteAtivo());
	}

	@Override
	public List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente)
			throws DLOException {
		if (adquirente == null 
				|| (adquirente.getId() == null && adquirente.getNome() == null)) {
			throw new ValidacaoDLOException();
		}
		if (adquirente.getNome() == null) {
			adquirente = adquirenteDLO.obter(adquirente.getId());
		}
		return dao.listarMarcadosParaReprocessamentoPorAdquirente(adquirente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContratoCobranca obterContratoCobrancaAssociado(ControleConciliacao controle) throws DLOException {
		// Veja a documentação do método na interface antes de fazer qualquer coisa!
		if (controle == null
				|| controle.getId() == null) {
			throw new ValidacaoDLOException();
		}
		ContratoCobranca contratoCobranca = dao.obterContratoCobrancaAssociado(controle);
		if (contratoCobranca == null) {
			logger.error("Não há contrato de cobrança associado ao controle de conciliação {{}}.", controle);
			throw new DLOException();
		}
		return contratoCobranca;
	}

	@Override
	public Date obterUltimaDataMovimentoImportada(Adquirente adquirente) throws DLOException {
		if (adquirente == null 
				|| (adquirente.getId() == null && adquirente.getNome() == null)) {
			throw new ValidacaoDLOException();
		}
		if (adquirente.getNome() == null) {
			adquirente = adquirenteDLO.obter(adquirente.getId());
		}
		Date ultimaData = dao.obterUltimaDataMovimentoImportada(adquirente);
		return ultimaData;
	}

	@Override
	public ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente, Date dataMovimento)
			throws DLOException {
		ControleConciliacao controle = buscarPorAdquirenteDataMovimento(adquirente, dataMovimento);
		if (controle == null) {
			throw new DLOException();
		}
		return obterContratoCobrancaAssociado(controle);
	}
	
	public List<ControleConciliacao> listarPorCriterio(String nomeAdquirente, String periodoDe, String periodoAte) {
		return dao.listarPorCriterio(nomeAdquirente, periodoDe, periodoAte);
	}

	@Override
	public List<Adquirente> listarAdquirentesParaProcessamento(Date dataHoraAtual) throws DLOException {
//		if (dataHoraAtual == null) {
//			throw new ValidacaoDLOException();
//		}
//		Parametro parametro = parametroDLO.obterUltimosParametros();
//		Integer limiteAdquirenteAtivo = parametro.getLimiteAdquirenteAtivo();
//		Calendar cal = DateUtils.getCalendarDataHoraAtual();
//		cal.setTime(dataHoraAtual);
//		cal.add(Calendar.DAY_OF_YEAR, -limiteAdquirenteAtivo);
//		List<Adquirente> retorno = dao.listarAdquirentesParaProcessamento(cal.getTime());
//		return retorno;
		return listarAdquirentesAtivosConciliacao(dataHoraAtual);
	}

	@Override
	public ControleConciliacao buscarProximoArquivoParaProcessamento(Adquirente adquirente) throws DLOException {
		if (adquirente == null
				|| adquirente.getId() == null) {
			throw new ValidacaoDLOException();
		}
		ControleConciliacao retorno = dao.buscarProximoArquivoParaProcessamento(adquirente);
		return retorno;
	}

	public void marcarParaReprocessamento(ControleConciliacao cc) {
		dao.marcarParaReprocessamento(cc);
	}

	@Override
	public ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente) throws DLOException {
		if (adquirente == null || adquirente.getId() == null) {
			throw new ValidacaoDLOException();
		}
		ContratoCobranca retorno = dao.obterContratoCobrancaAssociado(adquirente);
		return retorno;
	}

}
