package br.com.delphos.servicos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.conciliacoes.ControleConciliacao;
import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.logs.LogProcessoBatch;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

@Stateless
public class ConciliacaoTimerBean implements ConciliacaoTimer {
	public static Logger LOGGER = LoggerFactory
			.getLogger(CancelamentoTimerBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER,
			this, "[CONCILIATION] ");

	@EJB
	private ConciliacaoTimerTasks taskBean;
	
	@Schedule(hour = "*", minute = "*/3", second = "0", persistent = false)
	@Override
	public void obterArquivosConciliacao() {
		IdentificadorProcesso identificadorProcesso = IdentificadorProcesso.DownloadArquivosConciliacao;
		LogProcessoBatch logProcesso = taskBean.iniciarLogProcessoBatch(identificadorProcesso);
		boolean sucesso = logProcesso != null;
		LoggerWrapper log = logger.entrando("obterArquivosConciliacao");
		log.info("Iniciando downloads de arquivos de conciliação, data: {}", DateUtils.getDataHoraAtual());

		boolean debug = Configuracoes.isDebug();
		boolean umAdquirentePorVez = Configuracoes.getBooleanOuFalse(
				PropriedadeConfiguracao.ImportandoUmAdquirenteConciliacaoPorVez);
		
		try {
			if (sucesso) {
				List<Adquirente> adquirentesParaProcessamento = taskBean.listarAdquirentesParaDownload();
				Iterator<Adquirente> it = adquirentesParaProcessamento.iterator();
				boolean finalizado = !it.hasNext();
				
				while (!finalizado) {
					Adquirente adquirente = it.next();
					
					if (adquirente.getCodigoAfiliacaoConciliador() == null) {
						logger.error("Adquirente {{}} não possui código de afiliação."
								+ " A validação do arquivo poderá falhar, por favor verifique e continue.", adquirente);
					}
					
					boolean sucessoProcesso = 
							reprocessarDownloadArquivos(logProcesso, adquirente)
							& efetuarDownloadArquivos(logProcesso, adquirente);
					
					if (sucessoProcesso) {
						log.warn("Alguns arquivos não puderam ser baixados ou falharam na validação,"
								+ " favor verificar os controles de conciliação para a adquirente {{}}"
								+ " e o log de processo {{}}.", adquirente, logProcesso);
					}

					finalizado = !it.hasNext() || (debug && umAdquirentePorVez);
				}
			}
			
		} catch (Exception ex) {
			sucesso = false;
			log.error("Erro ao executar processo de download de arquivos de conciliação.", ex);
		}
		
		if (sucesso) {
			taskBean.finalizarLogProcessoBatch(logProcesso, StatusLogProcesso.Processado);
			
		} else if (logProcesso != null) {
			taskBean.finalizarLogProcessoBatch(logProcesso, StatusLogProcesso.Erro);
		}
		
		log.info("Downloads de arquivos de conciliação finalizado {}.", sucesso ? "com sucesso" : "com erros");
	}

	@Schedule(hour = "*", minute = "*/5", second = "30", persistent = false)
	@Override
	public void processarArquivosConciliacao() {

		IdentificadorProcesso identificadorProcesso = IdentificadorProcesso.ProcessamentoArquivosConciliacao;
		LogProcessoBatch logProcesso = taskBean.iniciarLogProcessoBatch(identificadorProcesso);
		boolean sucesso = logProcesso != null;
		LoggerWrapper log = logger.entrando("processarArquivosConciliacao");
		log.info("Iniciando processamento de arquivos de conciliação, data: {}", DateUtils.getDataHoraAtual());

		boolean debug = Configuracoes.isDebug();
		boolean umAdquirentePorVez = Configuracoes.getBooleanOuFalse(
				PropriedadeConfiguracao.ProcessandoUmAdquirenteConciliacaoPorVez);
		
		try {
			if (sucesso) {
				List<Adquirente> adquirentesParaProcessamento = taskBean.listarAdquirentesParaProcessamento();
				Iterator<Adquirente> it = adquirentesParaProcessamento.iterator();
				boolean finalizado = !it.hasNext();

				while (!finalizado) {
					Adquirente adquirente = it.next();
					ControleConciliacao proximoControle = taskBean.buscarProximoArquivoParaProcessamento(logProcesso, adquirente);
					
					// OBS: O Status Liberado é ignorado em buscarProximoArquivoParaProcessamento 
					if (proximoControle != null) {
						switch (proximoControle.getStatus()) {
						case Liberado:
							// OBS.: "Non-short-circuit" Boolean Logical Operator "&"
							// (veja http://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.22.2)
							sucesso = sucesso & taskBean.processarArquivo(logProcesso, proximoControle);
							
						default:
							break;
						}						
						
					} else {
						log.info("Não há controles de conciliação a serem processados para a adquirente {{}}.", adquirente);
					}
					
					finalizado = !it.hasNext() || (debug && umAdquirentePorVez);
				}
			}
			
		} catch (Exception ex) {
			sucesso = false;
			logProcesso.concatenarComplemento(ex.getMessage());
			log.error("Erro ao processar arquivos de conciliação.", ex);
		}
		
		if (sucesso) {
			taskBean.finalizarLogProcessoBatch(logProcesso, StatusLogProcesso.Processado);
			
		} else if (logProcesso != null) {
			taskBean.finalizarLogProcessoBatch(logProcesso, StatusLogProcesso.Erro);
		}
		
		log.info("Processamento de arquivos de conciliação finalizado {}.", sucesso ? "com sucesso" : "com erros");
	}

	private boolean efetuarDownloadArquivos(LogProcessoBatch logProcesso, Adquirente adquirente) throws BillingException {
		Date dataMovimento = null;
		
		boolean sucesso = true;
		Calendar calMovimento = getCalendarioParaHoje();
		Calendar calAtual = getCalendarioParaHoje();
		Date ultimaDataMovimento = taskBean.obterUltimaDataMovimentoImportada(adquirente);
		
		if (ultimaDataMovimento != null) {
			calMovimento.setTime(ultimaDataMovimento);
			calMovimento = adicionarDia(calMovimento);
			
		} else {
			logger.warn("Não há nenhum controle de conciliação no banco de dados,"
					+ " não sendo possível determinar a última data de movimento."
					+ " Por isto, não será possível baixar arquivos de conciliação"
					+ " até que algum controle de conciliação seja inserido.");
		}
		
		while (sucesso && ultimaDataMovimento != null && calAtual.compareTo(calMovimento) > 0) {
			dataMovimento = obterData(calMovimento);
			
			try {
				sucesso = sucesso & taskBean.efetuarDownloadArquivo(logProcesso, adquirente, dataMovimento);
				
			} catch (Exception ex) {
				sucesso = false;
				logger.error("Erro ao efetuar download de arquivo de conciliação"
						+ " para o adquirente {{}}, data de movimento {}.",
						adquirente, dataMovimento, ex);
			}
			
			calMovimento = adicionarDia(calMovimento);
		}
		
		return sucesso;
	}

	private Calendar getCalendarioParaHoje() {
		Calendar retorno = Calendar.getInstance();
		return retorno;
	}
	
	private Date obterData(Calendar cal) {
		cal = DateUtils.removerHoras(cal);
		Date data = cal.getTime();
//		if (cal.get(Calendar.HOUR_OF_DAY) != 0
//				|| cal.get(Calendar.MINUTE) != 0
//				|| cal.get(Calendar.SECOND) != 0) {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			String strData = String.format(
//					"%02d/%02d/%04d", 
//					cal.get(Calendar.DATE),
//					cal.get(Calendar.MONTH + 1),
//					cal.get(Calendar.YEAR));
//			try {
//				data = sdf.parse(strData);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		return data;
	}
	
	private Calendar adicionarDia(Calendar cal) {
		cal.add(Calendar.DATE, 1);
		cal = DateUtils.removerHoras(cal);
		return cal;
	}
	
	private boolean reprocessarDownloadArquivos(LogProcessoBatch logProcesso, Adquirente adquirente) throws BillingException {
		boolean sucesso = true;
		List<ControleConciliacao> marcadosParaReprocessamento = 
				taskBean.listarMarcadosParaReprocessamentoPorAdquirente(adquirente);
		Iterator<ControleConciliacao> iterador = marcadosParaReprocessamento.iterator();
		boolean finalizado = !sucesso || !iterador.hasNext();

		boolean debug = Configuracoes.isDebug();
		boolean umAdquirentePorVez = Configuracoes.getBooleanOuFalse(
				PropriedadeConfiguracao.ImportandoUmArquivoConciliacaoPorVez);
		
		while (!finalizado) {
			ControleConciliacao controle = iterador.next();
			
			try {
				sucesso = sucesso & taskBean.reprocessarDownloadArquivo(logProcesso, adquirente, controle);
				
			} catch (Exception ex) {
				sucesso = false;
				logger.error("Erro ao reprocessar download de arquivo de conciliação"
						+ " para o adquirente {{}}, controle de conciliação {{}}.",
						adquirente, controle);
				break;
			}
			
			finalizado = !sucesso || !iterador.hasNext() || (debug && umAdquirentePorVez);
		}
		
		return sucesso;
	}
}
