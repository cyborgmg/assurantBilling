package br.com.delphos.servicos;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.IdentificadorProcesso;
import br.com.delphos.billing.enumeracoes.StatusLogProcesso;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.logs.LogProcessoBatch;
import br.com.delphos.billing.logs.LogProcessoBatchDLO;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

public class AbstractTimerTasks implements TimerTasks {
	public static Logger LOGGER = LoggerFactory
			.getLogger(CancelamentoTimerTasksBean.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);

	@EJB(lookup = "java:global/dbsdb/LogProcessoBatchDLOBean")
	protected LogProcessoBatchDLO logProcessoBatchDLO;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public LogProcessoBatch iniciarLogProcessoBatch(IdentificadorProcesso identificadorProcesso) {
		LogProcessoBatch logProcesso = null;
		try {
			Long idLogProcesso = logProcessoBatchDLO.gravarLogProcessoBatch(
					StatusLogProcesso.Iniciado,
					identificadorProcesso,
					DateUtils.getDataHoraAtual(), 
					null, null);
			
			logProcesso = idLogProcesso != null 
					? logProcessoBatchDLO.obter(idLogProcesso) 
					: null;
					
			if (idLogProcesso == null || logProcesso == null) {
				logger.error("Erro ao criar nova entrada de log de processo.");
				throw logger.lancando(new BillingWebServiceException(CodigoMensagem.Falha),
						null);
			}
			
			logger.debug("Criada nova entrada de log de processo: \\{{}\\}",
					logProcesso);
			
		} catch (Exception ex) {
			logger.error("Erro ao inicializar LogProcessoBatch {{}}", logProcesso, ex);
		}
		
		return logProcesso;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void finalizarLogProcessoBatch(LogProcessoBatch logProcesso, StatusLogProcesso status) {
		try {
			logProcesso.setDataFim(DateUtils.getDataHoraAtual());
			logProcesso.setStatus(status);
			
			if (logProcessoBatchDLO.manter(logProcesso) == null) {
				logger.error("Não foi possível persistir o log de processo {{}}.", logProcesso);
			}
			
		} catch (Exception ex) {
			logger.error("Erro ao finalizar LogProcessoBatch {{}}", logProcesso, ex);
		}
	}
}
