package br.com.delphos.billing.conciliacoes;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ControleConciliacaoDAO extends DAOEntidade<ControleConciliacao> {
	
	public List<ControleConciliacao> listarPorCriterio(String nomeAdquirente, String periodoDe, String periodoAte);

	ControleConciliacao buscarPorAdquirenteDataMovimento(Adquirente adquirente, Date dataMovimento);

	List<Adquirente> listarAdquirentesAtivosConciliacao(Date dataHoje, int intervaloVigenciaEmMeses);

	List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente);

	/**
	 * Obtém o contrato de cobrança associado ao controle de conciliação passado.
	 * 
	 * <p>Veja {@link ControleConciliacaoDLO#obterContratoCobrancaAssociado(ControleConciliacao)}.
	 * </p>
	 * 
	 * @see ControleConciliacaoDLO#obterContratoCobrancaAssociado(ControleConciliacao)
	 * @param controle
	 * @return
	 */
	// Veja a documentação do método no DLO antes de fazer qualquer coisa!
	ContratoCobranca obterContratoCobrancaAssociado(ControleConciliacao controle);

	Date obterUltimaDataMovimentoImportada(Adquirente adquirente);

	public void marcarParaReprocessamento(ControleConciliacao cc);

	ControleConciliacao buscarProximoArquivoParaProcessamento(Adquirente adquirente);

	public ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente);

}
