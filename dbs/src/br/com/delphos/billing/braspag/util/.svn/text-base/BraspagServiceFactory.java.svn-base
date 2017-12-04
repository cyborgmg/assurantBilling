package br.com.delphos.billing.braspag.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.enumeracoes.TipoRecuperacaoFalhaServico;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;
import br.com.delphos.billing.util.Mensagens;

public abstract class BraspagServiceFactory<ST, PT> {
	public static Logger LOGGER = LoggerFactory.getLogger(BraspagServiceFactory.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);

	protected ST servico;
	protected PT porta;

	protected abstract ST newServico();
	protected abstract PT newPortaServico();

	public static interface Runnable<T> {
		public T run();
	}
	
	public boolean iniciarServico() {
		if (servico == null) {
			logger.debug("Iniciando serviço: " + this.getClass().getSimpleName() + ".");
			servico = newServico();
		}
		return servico != null;
	}
	
	public boolean iniciarPortaServico() {
		if (porta == null) {
			logger.debug("Iniciando porta de serviço: " + this.getClass().getSimpleName() + ".");
			porta = newPortaServico();
		}
		return this.porta != null;
	}
	
	public boolean reiniciarServico() {
		servico = null;
		return iniciarServico();
	}
	
	public boolean reiniciarPortaServico() {
		porta = null;
		return iniciarPortaServico();
	}

	public void verificarServico() throws BillingWebServiceException {

		if (!iniciarServico()) {
			logger.error("Não foi possível iniciar o serviço PagadorQuery para comunicação com a adquirente.");
			throw logger.lancando(new BillingWebServiceException(Mensagens.get(CodigoMensagem.DPH0011)), null);
		}
		
		if (!iniciarPortaServico()) {
			logger.error("Não foi possível iniciar porta de serviço PagadorQuerySoap para comunicação com a adquirente.");
			throw logger.lancando(new BillingWebServiceException(Mensagens.get(CodigoMensagem.DPH0011)), null);
		}
	}
	
	protected long getTempoEspera() {
		Long tempo = Configuracoes.getObjetoOuNulo(PropriedadeConfiguracao.TempoEsperaQuandoFalhaJaxWs, Long.class);
		if (tempo == null) {
			tempo = 0l;
		}
		return tempo;
	}
	
	protected List<TipoRecuperacaoFalhaServico> getPassosRecuperacaoFalha() {
		return Configuracoes.getListaOuVazio(PropriedadeConfiguracao.AcaoRecuperacaoFalhaJaxWs, TipoRecuperacaoFalhaServico.class);
	}
	
	protected void esperar() {
		long tempoEspera = getTempoEspera();
		if (tempoEspera > 0) {
			try {
				Thread.sleep(tempoEspera);
			} catch (InterruptedException e) {
			}
		}
	}
	
	protected <T> T chamarServico(Runnable<T> runnable) {
		iniciarPortaServico();
		try {
			return runnable.run();
			
		} catch (Exception ex) {
			logger.warn("Não foi possível realizar chamada ao método do webservice na primeira tentativa. Passando para as rotinas de reuperação...");
			List<TipoRecuperacaoFalhaServico> passos = getPassosRecuperacaoFalha();
			
			for (TipoRecuperacaoFalhaServico passo : passos) {				
				try {
					logger.info("Tentando passo de recuperação de chamada à método webservice: {}", passo);
					executarPassoRecuperacao(passo, ex);
					return runnable.run();
					
				} catch (Exception e) {
					logger.warn("Passo de recuperação de chamada à método webservice {} falhou. Tentando próximo passo...", passo);
					ex = e;
				}
			}

			logger.error("Procedimento de recuperação de falha em chamada à metodo de webservice falhou!");
			throw new RuntimeException(ex);
		}
	}
	
	protected <T extends Throwable> void executarPassoRecuperacao(TipoRecuperacaoFalhaServico passo, T t) throws T {
		switch (passo) {
		case Esperar:
			esperar();
			break;
			
		case ReiniciarPorta:
			reiniciarPortaServico();
			break;
			
		case ReiniciarServico:
			reiniciarServico();
			reiniciarPortaServico();
			break;
			
		case Nenhum:
		default:
			if (t != null) {
				throw t;
			}
		}
	}
}
