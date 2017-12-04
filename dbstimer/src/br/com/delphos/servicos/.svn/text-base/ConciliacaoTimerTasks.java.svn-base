package br.com.delphos.servicos;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.logs.LogProcessoBatch;

@Local
public interface ConciliacaoTimerTasks extends TimerTasks {
	
	boolean processarArquivo(LogProcessoBatch logProcesso, ControleConciliacao controle) throws BillingException;
	
	// Métodos utilitários
	
	List<Adquirente> listarAdquirentesParaDownload() throws BillingException;
	List<Adquirente> listarAdquirentesParaProcessamento() throws BillingException;
	ControleConciliacao buscarProximoArquivoParaProcessamento(LogProcessoBatch logProcesso, Adquirente adquirente) throws BillingException;
//	RetornoBaixarArquivoConciliacao baixarArquivoConciliacao(ContratoCobranca contratoCobranca, Adquirente adquirente, Date dataMovimento, ControleConciliacao controle) throws BillingException;
//	List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente) throws BillingException;
//	ContratoCobranca obterContratoCobrancaAssociado(ControleConciliacao controle) throws BillingException;
//	boolean validarArquivoConciliacao(ControleConciliacao controle) throws BillingException;
//	boolean validarValorArquivoConciliacao(ControleConciliacao controle) throws BillingException;
//	Date obterUltimaDataMovimento() throws BillingException;
//	ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente, Date dataMovimento) throws BillingException;
//	void salvarControleConciliacao(ControleConciliacao controle) throws BillingException;
	boolean efetuarDownloadArquivo(LogProcessoBatch logProcesso, Adquirente adquirente, Date dataMovimento) throws BillingException;
	Date obterUltimaDataMovimentoImportada(Adquirente adquirente) throws BillingException;
	boolean reprocessarDownloadArquivo(LogProcessoBatch logProcesso, Adquirente adquirente,
			ControleConciliacao controle) throws BillingException;
	List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente) throws BillingException;
}
