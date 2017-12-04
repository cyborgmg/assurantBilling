package br.com.delphos.billing.cobrancas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.persistencia.DLOEntidade;
import br.com.delphos.billing.tentativas.Tentativa;

@Remote
public interface CobrancaDLO extends DLOEntidade<Cobranca> {
	
	List<Cobranca> listarPrimeirasCobrancasPendentes();
	
	List<Cobranca> listarCobrancasParcelaUnicaPendentes();
	
	String gerarIdRequisicao();
	
	List<Cobranca> listarCobrancasPorVenda(Long vendaId) ;
	
	Tentativa obterUltimaTentativa(Cobranca cobranca, TipoTentativa... tiposTentativa) ;

	List<Cobranca> listarCobrancasNaoConciliadas(Cobranca cobranca, double diasAtraso)
	;
	
	List<Cobranca>  listarCobrancasConciliadasInterface(Cobranca cobranca,Date dataInicial,Date dataFinal,StatusConciliacaoInterface situacaoConciliacao)
	;
	
	List<Cobranca>  listarCobrancasConciliadasInterface(Cobranca cobranca,Date dataInicial,Date dataFinal,ItemLista itemLista)
			;

	List<Cobranca>  listarCobrancasConciliacao(Cobranca cobranca,Date dataInicial,Date dataFinal,ItemLista itemLista)
			;

	Integer obterUltimoNumeroParcela(Cobranca cobranca) throws DLOException;
	
	boolean isMovimentoConciliado(Cobranca cobranca, Integer numeroParcelaLoja) throws DLOException;

	BigDecimal calcularValorPrimeiraParcela(Cobranca cobranca) throws DLOException;
	
	BigDecimal calcularValorParcelasSeguintes(Cobranca cobranca) throws DLOException;
	
	boolean isValorNaMargemAceitacao(Cobranca cobranca, BigDecimal valorConciliado, Integer numeroParcela) throws DLOException;
	
	boolean isValorNaMargemAceitacao(Cobranca cobranca, BigDecimal valorConciliado) throws DLOException;

	BigDecimal obterValorConciliado(Cobranca cobranca, Integer numeroParcela) throws DLOException;

	ProcConciliacaoEvento obterEventoProcessamento(Cobranca cobranca, Integer numeroParcela) throws DLOException;
	
}
