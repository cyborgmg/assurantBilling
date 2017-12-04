package br.com.delphos.billing.braspag.util;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.delphos.billing.braspag.consulta.cliente.OrderIdDataRequest;
import br.com.delphos.billing.braspag.consulta.cliente.OrderIdDataResponse;
import br.com.delphos.billing.braspag.consulta.cliente.PagadorQuery;
import br.com.delphos.billing.braspag.consulta.cliente.PagadorQuerySoap;
import br.com.delphos.billing.braspag.consulta.cliente.TransactionDataRequest;
import br.com.delphos.billing.braspag.consulta.cliente.TransactionDataResponse;
import br.com.delphos.billing.enumeracoes.PropriedadeConfiguracao;
import br.com.delphos.billing.util.Configuracoes;
import br.com.delphos.billing.util.LoggerWrapper;
import br.com.delphos.billing.util.LoggerWrappers;

public class PagadorQueryFactory extends BraspagServiceFactory<PagadorQuery, PagadorQuerySoap> {
	public static Logger LOGGER = LoggerFactory.getLogger(PagadorQueryFactory.class);
	private final LoggerWrapper logger = LoggerWrappers.getWrapper(LOGGER, this);
	
	public PagadorQuery newServico() {
		URL urlWsdl = Configuracoes.getObjetoOuNulo(PropriedadeConfiguracao.UrlWsdlPagadorQuery, URL.class);
		if (urlWsdl == null) {
			return new PagadorQuery();
		}
		return new PagadorQuery(urlWsdl);
	}
	
	public PagadorQuerySoap newPortaServico() {
		return servico.getPagadorQuerySoap();
	}
	
	public TransactionDataRequest newTransactionDataRequest() {
		return new TransactionDataRequest();
	}
	
	public TransactionDataResponse getTransactionData(final TransactionDataRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<TransactionDataResponse>() {
				public TransactionDataResponse run() {
					return porta.getTransactionData(req);
				}
			}
		);
	}

	public OrderIdDataRequest newOrderIdData() {
		return new OrderIdDataRequest();
	}
	
	public OrderIdDataResponse getOrderIdData(final OrderIdDataRequest req) {
		return chamarServico(
			new BraspagServiceFactory.Runnable<OrderIdDataResponse>() {
				public OrderIdDataResponse run() {
					return porta.getOrderIdData(req);
				}
			}
		);
	}
}
