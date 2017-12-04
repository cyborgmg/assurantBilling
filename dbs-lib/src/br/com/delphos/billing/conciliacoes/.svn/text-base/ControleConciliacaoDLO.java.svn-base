package br.com.delphos.billing.conciliacoes;

import java.util.Date;
import java.util.List;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DLOEntidade;

public interface ControleConciliacaoDLO extends DLOEntidade<ControleConciliacao> {
	
	
	public boolean validarValorConciliacaoTela(ControleConciliacao conciliacaoNovo) throws DLOException;

	public boolean validarValorArquivoConciliacao(Adquirente adquirente, Date dataMovimento, ControleConciliacao conciliacaoNovo) throws DLOException;
	
	/**
	 * Retorna o controle de conciliação por último na sequência de arquivos referente ao adquirente e
	 * data de conciliação informados.
	 * @param adquirente
	 * @param dataConciliacao
	 * @return
	 * @throws BillingException
	 */
	public ControleConciliacao buscarPorAdquirenteDataMovimento(Adquirente adquirente, Date dataConciliacao) throws DLOException;
	
	/**
	 * Valida o valor do arquivo de conciliação armazenado no controle de conciliação passado.  
	 * @param conciliacao
	 * @return
	 * @throws BillingException
	 */
	public boolean validarValor(ControleConciliacao conciliacao) throws DLOException;
	
	/**
	 * Busca o próximo arquivo de conciliação a ser processado para esta adquirente.
	 * Usado na rotina de processamento de conciliação, item 6.1.2 da especificação técnica.
	 * @param adquirente
	 * @return
	 */
	public ControleConciliacao buscarProximoArquivoParaProcessamento(Adquirente adquirente) throws DLOException;
	
	/**
	 * Lista as adquirentes a serem consideradas no processamento de arquivos de conciliação.
	 * 
	 * <p>Apenas as adquirentes associadas a pelo menos um contrato de cobrança cuja vigência esteja
	 * ativa ou expirada de acordo com o tempo informado pela propriedade
	 * PARAMETRO.LIMITE_ADQUIRENTE_ATIVO (um valor de tempo dado em meses) serão retornadas.
	 * </p>
	 *  
	 * @return
	 */
	public List<Adquirente> listarAdquirentesAtivosConciliacao(Date dataHoje) throws DLOException;
	
	/**
	 * Retorna todos os controles de conciliação da adquirente passada marcados para reprocessamento
	 * (com status REP).
	 * 
	 * @param adquirente
	 * @return
	 * @throws BillingException
	 */
	public List<ControleConciliacao> listarMarcadosParaReprocessamentoPorAdquirente(Adquirente adquirente) throws DLOException;

	/**
	 * Obtém o contrato de cobrança associado ao controle de conciliação passado.
	 * 
	 * <p>A pesquisa pelo contrato de cobrança começa em Adquirente: a partir do nome da adquirente
	 * armazenado no controle, o método pesquisa todas as associações AdquirenteBandeira existentes
	 * para a adquirente com tal nome. Mais de uma associação AdquirenteBandeira pode ser retornada, e é
	 * possível que elas tenham contratos de cobrança diferentes. Para sanar esta ambiguidade,
	 * este método de busca <strong>sempre retorna a adquirente com a maior data de início de vigência
	 * </strong>.
	 * </p>
	 * 
	 * <p>Este método é necessário para efetuar o download dos arquivos de conciliação, pois os serviços
	 * de conciliação precisam de usuário e senha, e estas informações estão na entidade Empresa. Como
	 * a única informação de cadastro presente em ControleConciliacao é o nome da adquirente,
	 * há a necessidade de fazer uma busca que passa não só por Adquirente, mas por AdquirenteBandeira
	 * e ContratoCobranca, até chegar em Empresa. 
	 * </p>
	 * 
	 * @param controle
	 * @return Um valor não-nulo.
	 * @throws BillingException Em caso de erro de validação ou caso não seja encontrado um 
	 * contrato de cobrança para o controle de conciliação em questão.
	 */
	public ContratoCobranca obterContratoCobrancaAssociado(ControleConciliacao controle) throws DLOException;

	/**
	 * Obtém a data de movimento do último controle de conciliação para o adquirente informado.
	 *  
	 * @param adquirente
	 * @return
	 * @throws BillingException
	 */
	public Date obterUltimaDataMovimentoImportada(Adquirente adquirente) throws DLOException;

	public ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente, Date dataMovimento) throws DLOException;
	
	public List<ControleConciliacao> listarPorCriterio(String nomeAdquirente, String periodoDe, String periodoAte);

	public List<Adquirente> listarAdquirentesParaProcessamento(Date dataHoraAtual) throws DLOException;
	
	public void marcarParaReprocessamento(ControleConciliacao cc);

	public ContratoCobranca obterContratoCobrancaAssociado(Adquirente adquirente) throws DLOException;
	
	ControleConciliacao validarPorValor(ControleConciliacao conciliacao) throws DLOException;
}
