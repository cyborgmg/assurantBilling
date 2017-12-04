package br.com.delphos.billing.cobrancas;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.conciliacoes.ProcConciliacaoEvento;
import br.com.delphos.billing.enumeracoes.StatusConciliacaoInterface;
import br.com.delphos.billing.enumeracoes.TipoTentativa;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.parametros.Parametro;
import br.com.delphos.billing.persistencia.DAOEntidade;
import br.com.delphos.billing.tentativas.Tentativa;
import br.com.delphos.billing.vendas.Venda;

@Local
public interface CobrancaDAO extends DAOEntidade<Cobranca> {	
	List<Cobranca> listarPrimeirasCobrancasPendentes(Parametro parametro);
	
	List<Cobranca> listarCobrancasSinglePremiumPendentes(Parametro parametro);
	
	boolean isIdRequisicaoExistente(String idRequisicao);
	
	public List<Cobranca> listarCobrancasPorVenda(Venda venda);

	public Tentativa obterUltimaTentativa(Cobranca cobranca, TipoTentativa... tiposTentativa); 
	
	public List<Tentativa> listarTentativas(Cobranca cobranca);

	List<Cobranca> listarCobrancasNaoConciliadas(Cobranca cobranca,double diasAtraso); 
	
	List<Cobranca> listarCobrancasConciliadasInterface( Cobranca cobranca,Date dataInicial,Date dataFinal,StatusConciliacaoInterface situacaoConciliacao);
	
	List<Cobranca> listarCobrancasConciliadasInterface( Cobranca cobranca,Date dataInicial,Date dataFinal,ItemLista itemLista);

	List<Cobranca>  listarCobrancasConciliacao(Cobranca cobranca,Date dataInicial,Date dataFinal,ItemLista itemLista)
			;

	boolean isMovimentoConciliado(Cobranca cobranca, Integer numeroParcelaLoja);

	Integer obterNumeroUltimaParcelaConciliada(Cobranca cobranca);

	ProcConciliacaoEvento obterEventoProcessamento(Cobranca cobranca, Integer numeroParcela);
}
