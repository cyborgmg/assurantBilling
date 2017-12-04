package br.com.delphos.billing.cobrancas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.configuracoes.Constantes;
import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.parametros.Parametro;
import br.com.delphos.billing.parametros.ParametroDLO;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.util.CurrencyUtils;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.vendas.Venda;
import br.com.delphos.billing.vendas.VendaDAO;

@Stateless
public class CobrancaDLOBean extends AbstractDLO<Cobranca> implements CobrancaDLO {
	
	@EJB
	private CobrancaDAO dao;
	
	@EJB
	private ParametroDLO parametroDLO;
	
	@EJB
	private VendaDAO vendaDAO;
	
	@Override
	protected CobrancaDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public Object manter(Cobranca entidade) throws DLOException {
		if (entidade != null) {
			entidade.setDataUltimaAlteracao(DateUtils.getDataHoraAtual());
		}
		return super.manter(entidade);
	}
	
	@Override
	public List<Cobranca> listarPrimeirasCobrancasPendentes() {
		Parametro parametro =parametroDLO.obter(new Long(1));
		return dao.listarPrimeirasCobrancasPendentes(parametro);
	}
	
	@Override
	public List<Cobranca> listarCobrancasParcelaUnicaPendentes() {
		Parametro parametro = parametroDLO.obter(1l);
		return dao.listarCobrancasSinglePremiumPendentes(parametro);
	}
	
	@Override
	public List<Cobranca> listarCobrancasNaoConciliadas(Cobranca cobranca, double diasAtraso)
			{
//		if(diasAtraso<=0)
//			throw new DLOException(Mensagens.get("parametro.invalido"     , Mensagens.get("parametro.validacaoDLO.venda.cobranca.dias.atraso")));
		
		return dao.listarCobrancasNaoConciliadas(cobranca, diasAtraso);
	}

	@Override
	public String gerarIdRequisicao() {
		String retorno = null;
		do {
			SecureRandom random = new SecureRandom();
			long lo = random.nextLong();
			long hi = random.nextLong();
			UUID uuid = new UUID(hi, lo);
			if (!dao.isIdRequisicaoExistente(uuid.toString())) {
				retorno = uuid.toString();
			}
		} while (retorno == null);
		return retorno;
	}
	
	public List<Cobranca> listarCobrancasPorVenda(Long vendaId) {
		
//		if (vendaId == null) {
//			throw new DLOException(Mensagens.get("parametro.nulo", "id da Venda"));
//		}
		
		Venda venda = vendaDAO.obterVendaPorId(String.valueOf(vendaId));
		
		List<Cobranca> listaCobrancas = dao.listarCobrancasPorVenda(venda);
		return listaCobrancas;
	}
	
	@Override
	public Tentativa obterUltimaTentativa(Cobranca cobranca, TipoTentativa... tiposTentativa)  {
		
//		if (cobranca == null || cobranca.getId() == null) {
//			throw new ValidacaoDLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.cobranca")));
//		}
		
		Tentativa retorno = dao.obterUltimaTentativa(cobranca, tiposTentativa);

//		if (retorno == null) {
//			throw new ValidacaoDLOException(Mensagens.get("parametro.validacaoDLO.naoHaTentativas"));
//		}
				
		return retorno;
	}

	@Override
	public List<Cobranca> listarCobrancasConciliadasInterface(
			Cobranca cobranca, Date dataInicial, Date dataFinal,
			StatusConciliacaoInterface situacaoConciliacao) {
		
		List<Cobranca> listaCobrancasConciliadasInterface=null;
		
		boolean	hasCobranca=	(cobranca!=null?true:false);
		
		if(hasCobranca)		
			listaCobrancasConciliadasInterface=  dao.listarCobrancasConciliadasInterface(cobranca, dataInicial, dataFinal, situacaoConciliacao);
//		else
//			throw new ValidacaoDLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.cobranca")));
			
		return listaCobrancasConciliadasInterface;
		
	}

	@Override
	public List<Cobranca> listarCobrancasConciliadasInterface(
			Cobranca cobranca, Date dataInicial, Date dataFinal,
			ItemLista itemLista) {
	return dao.listarCobrancasConciliadasInterface(cobranca, dataInicial, dataFinal, itemLista);
	}

	@Override
	public List<Cobranca> listarCobrancasConciliacao(Cobranca cobranca,
			Date dataInicial, Date dataFinal, ItemLista itemLista)
			 {
		return dao.listarCobrancasConciliacao(cobranca, dataInicial, dataFinal, itemLista);
	}

	@Override
	public boolean isEditavel(Cobranca entidade) throws DLOException {
		// Cobranças não são editáveis pelo usuário.
		return false;
	}

	@Override
	public boolean isMovimentoConciliado(Cobranca cobranca, Integer numeroParcelaLoja) throws DLOException {
		if (cobranca == null
				|| cobranca.getId() == null
				|| numeroParcelaLoja == null) {
			throw new ValidacaoDLOException();
		}
		boolean retorno = dao.isMovimentoConciliado(cobranca, numeroParcelaLoja);
		return retorno;
	}

	@Override
	public BigDecimal calcularValorPrimeiraParcela(Cobranca cobranca) throws DLOException {
		if (cobranca == null
				|| cobranca.getValorCobranca() == null
				|| cobranca.getVenda() == null) {
			throw new ValidacaoDLOException();
		}
		int quantidadeParcelas = cobranca.getVenda().getQuantidadeParcelas();
		BigDecimal valorParcela = calcularValorParcelasSeguintes(cobranca);
		BigDecimal retorno = cobranca.getValorCobranca();
		retorno = retorno.subtract(valorParcela.multiply(BigDecimal.valueOf(quantidadeParcelas - 1)));
		return retorno;
	}

	@Override
	public BigDecimal calcularValorParcelasSeguintes(Cobranca cobranca) throws DLOException {
		if (cobranca == null
				|| cobranca.getValorCobranca() == null
				|| cobranca.getVenda() == null) {
			throw new ValidacaoDLOException();
		}
		BigDecimal retorno = cobranca.getValorCobranca();
		retorno = retorno.divide(BigDecimal.valueOf(cobranca.getVenda().getQuantidadeParcelas()));
		return retorno.setScale(Constantes.EscalaValorMonetario, RoundingMode.HALF_EVEN);
	}

	@Override
	public boolean isValorNaMargemAceitacao(Cobranca cobranca, BigDecimal valorConciliado, Integer numeroParcela) throws DLOException {
		if (cobranca == null
				|| cobranca.getValorCobranca() == null
				|| cobranca.getVenda() == null) {
			throw new ValidacaoDLOException();
		}
		
		if (numeroParcela == null) {
			numeroParcela = obterUltimoNumeroParcela(cobranca);
		}
		
		if (valorConciliado == null) {
			valorConciliado = obterValorConciliado(cobranca, numeroParcela);
		}
		
		if (numeroParcela == null || valorConciliado == null) {
			// CASO numeroParcela == null
			// - Valor de número de parcela não consta na cobrança, ou nenhuma
			//   conciliação ocorreu para a cobrança.
			// CASO valorConciliado == null
			// - Valor não conciliado, e nenhum valor foi fornecido pelo usuário.
			//   Não é possível verificar.
			throw new DLOException(CodigoMensagem.Falha);
		}

		Parametro parametro = parametroDLO.obterUltimosParametros();
		BigDecimal valorParcela;
		
		if (numeroParcela == 1) {
			valorParcela = calcularValorPrimeiraParcela(cobranca);
			
		} else {
			valorParcela = calcularValorParcelasSeguintes(cobranca);
		}
		
		BigDecimal valorComparacao = valorConciliado.subtract(valorParcela).abs();
		return valorComparacao.compareTo(parametro.getMargemAceitacaoConciliacao()) <= 0;
	}

	@Override
	public boolean isValorNaMargemAceitacao(Cobranca cobranca, BigDecimal valorConciliado) throws DLOException {
		return isValorNaMargemAceitacao(cobranca, valorConciliado, null);
	}

	@Override
	public Integer obterUltimoNumeroParcela(Cobranca cobranca) throws DLOException {
		if (cobranca == null
				|| cobranca.getId() == null
				|| cobranca.getVenda() == null) {
			throw new ValidacaoDLOException();
		}
		
		Integer numeroParcela = cobranca.getNumeroParcela();
		
		if (cobranca.getVenda().getTipoCobranca().isAutorizacaoUnica()
				&& cobranca.getVenda().isPagamentoParcelado()) {
			numeroParcela = dao.obterNumeroUltimaParcelaConciliada(cobranca);
		}
		
		return numeroParcela;
	}

	@Override
	public BigDecimal obterValorConciliado(Cobranca cobranca, Integer numeroParcela) throws DLOException {
		if (cobranca == null
				|| cobranca.getId() == null
				|| cobranca.getVenda() == null) {
			throw new ValidacaoDLOException();
		}
		
		ProcConciliacaoEvento eventoProcessamento = obterEventoProcessamento(cobranca, numeroParcela);
		BigDecimal retorno = null;
		
		if (eventoProcessamento != null) {
			retorno = eventoProcessamento.getGrossAmount();
		}
		
		return retorno;
	}
	
	@Override
	public ProcConciliacaoEvento obterEventoProcessamento(Cobranca cobranca, Integer numeroParcela) throws DLOException {
		if (cobranca == null
				|| cobranca.getId() == null) {
			throw new ValidacaoDLOException();
		}
		
		if (numeroParcela == null) {
			numeroParcela = obterUltimoNumeroParcela(cobranca);
		}
		
		ProcConciliacaoEvento retorno = dao.obterEventoProcessamento(cobranca, numeroParcela);
		return retorno;
	}
}
