package br.com.delphos.billing.servicos;

import java.util.Date;

import javax.ejb.Remote;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.servicos.retornos.RetornoBaixarArquivoConciliacao;

@Remote
public interface ConciliacaoService {
	
	public RetornoBaixarArquivoConciliacao baixarArquivoConciliacao(
			ContratoCobranca contratoCobranca,
			Adquirente adquirente,
			Date dataMovimento,
			ControleConciliacao controleConciliacao) throws BillingException;
	
	public ControleConciliacao validarArquivoConciliacao(ControleConciliacao controle) throws BillingException;
	public void processarArquivoConciliacao(ControleConciliacao controle) throws BillingException;
}
