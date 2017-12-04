package br.com.delphos.billing.servicos;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarVenda;
import br.com.delphos.billing.servicos.retornos.RetornoIniciarVendaCartaoCredito;

@WebService(targetNamespace = "http://servicos.billing.delphos.com.br/", endpointInterface = "br.com.delphos.billing.servicos.Ws", portName = "WsImplPort", serviceName = "WsImplService")
public class WsImpl implements Ws {
	
	private BillingService getService() {			
		
		try {
			InitialContext ic = new InitialContext();
			return (BillingService)ic.lookup("java:global/dbs/BillingServiceBean");	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
		
	}

	@Override
	public RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
			String token, String codigoEmpresa, String codigoProduto,
			String codigoSistema, String cpf, String nome, String email,
			String dddCelular, String telefoneCelular, String dddContato,
			String telefoneContato, String codigoVenda, String dataVenda,
			String bandeiraCartao, String cpfPortadorCartao,
			String nomePortadorCartao, String numeroCartao,
			String vencimentoCartao, String cvvCartao, String tipoCobranca,
			String quantidadeParcelas, String valorCobranca,
			String dataPrimeiraCobranca, String dataFimVigencia)
			throws BillingException {
		// TODO Auto-generated method stub
		return getService().iniciarVendaCartaoCredito(token, codigoEmpresa, codigoProduto, 
				codigoSistema, cpf, nome, email, dddCelular, telefoneCelular, dddContato, 
				telefoneContato, codigoVenda, dataVenda, bandeiraCartao, cpfPortadorCartao, 
				nomePortadorCartao, numeroCartao, vencimentoCartao, cvvCartao, tipoCobranca, 
				quantidadeParcelas, valorCobranca, dataPrimeiraCobranca, dataFimVigencia);
	}

	@Override
	public RetornoCancelarVenda cancelarVenda(String codigoEmpresa,
			String codigoProduto, String codigoSistema, String token,
			String cpf, String codigoVenda, String codigoMotivoCancelamento,
			String valorEstorno, String flagCancelamentoGateway)
			throws BillingException {
		// TODO Auto-generated method stub
		return getService().cancelarVenda(codigoEmpresa, codigoProduto, codigoSistema, 
				token, cpf, codigoVenda, codigoMotivoCancelamento, valorEstorno, flagCancelamentoGateway);
	}

}
