package br.com.delphos.billing.braspag.util;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.braspag.conciliador.cliente.DownloadTransactionFileResponse;
import br.com.delphos.billing.braspag.conciliador.cliente.GetExportedFileRequest;
import br.com.delphos.billing.braspag.conciliador.cliente.GetExportedFileResponse2;
import br.com.delphos.billing.braspag.conciliador.cliente.GetExportedFileV2Request;
import br.com.delphos.billing.braspag.conciliador.cliente.GetExportedFileV2Response2;
import br.com.delphos.billing.braspag.conciliador.cliente.GetTransactionFileRequest;
import br.com.delphos.billing.braspag.conciliador.cliente.ReconciliationFilesWebService;
import br.com.delphos.billing.braspag.conciliador.cliente.ReconciliationFilesWebServiceSoap;
import br.com.delphos.billing.braspag.conciliador.cliente.SendTransactionFileRequest;
import br.com.delphos.billing.braspag.conciliador.cliente.StoreTransactionFileResponse;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

public class PagadorReconciliationFactory extends BraspagServiceFactory<ReconciliationFilesWebService, ReconciliationFilesWebServiceSoap> {
	public static Logger LOGGER = LoggerFactory.getLogger(PagadorReconciliationFactory.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);


	public ReconciliationFilesWebService newServico() {
		URL urlWsdl = Configuracoes.getObjetoOuNulo(PropriedadeConfiguracao.UrlWsdlPagadorReconciliation, URL.class);
		if (urlWsdl == null) {
			return new ReconciliationFilesWebService();
		}
		return new ReconciliationFilesWebService(urlWsdl);
	}
	
	public ReconciliationFilesWebServiceSoap newPortaServico() {
		iniciarServico();
		return servico.getReconciliationFilesWebServiceSoap();
	}

	public GetExportedFileRequest newGetExportedFileRequest() {
		return new GetExportedFileRequest();
	}
	
	public GetExportedFileResponse2 getExportedFile(final GetExportedFileRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<GetExportedFileResponse2>() {
				public GetExportedFileResponse2 run() {
					return porta.getExportedFile(req);
				}
			}
		);
	}

	public GetExportedFileV2Request newGetExportedFileV2Request() {
		return new GetExportedFileV2Request();
	}
	
	public GetExportedFileV2Response2 getExportedFileV2(final GetExportedFileV2Request req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<GetExportedFileV2Response2>() {
				public GetExportedFileV2Response2 run() {
					return porta.getExportedFileV2(req);
				}
			}
		);
	}

	public SendTransactionFileRequest newSendTransactionFileRequest() {
		return new SendTransactionFileRequest();
	}
	
	public StoreTransactionFileResponse sendTransactionFile(final SendTransactionFileRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<StoreTransactionFileResponse>() {
				public StoreTransactionFileResponse run() {
					return porta.sendTransactionFile(req);
				}
			}
		);
	}

	public GetTransactionFileRequest newGetTransactionFileRequest() {
		return new GetTransactionFileRequest();
	}
	
	public DownloadTransactionFileResponse getTransactionFile(final GetTransactionFileRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<DownloadTransactionFileResponse>() {
				public DownloadTransactionFileResponse run() {
					return porta.getTransactionFile(req);
				}
			}
		);
	}
	
}
