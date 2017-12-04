package br.com.delphos.servicos;

import javax.ejb.Local;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.logs.LogProcessoBatch;

@Local
public interface CancelamentoTimerTasks extends TimerTasks {
	
	ResultadoCancelamento tratarCobrancaPendente(Cobranca cobranca, LogProcessoBatch logProcesso) throws BillingException;

	
	public static enum TipoResultadoCancelamento {
		Sucesso, Falha, Excecao;
	}
	
	public static class ResultadoCancelamento {
		
		private TipoResultadoCancelamento resultado;
		
		public TipoResultadoCancelamento getResultado() {
			return resultado;
		}
		
		public void setResultado(TipoResultadoCancelamento resultado) {
			this.resultado = resultado;
		}
		
	}
}
