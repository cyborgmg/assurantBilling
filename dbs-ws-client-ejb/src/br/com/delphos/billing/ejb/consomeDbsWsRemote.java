package br.com.delphos.billing.ejb;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import br.com.delphos.billing.services.BillingException;
import br.com.delphos.billing.services.BillingException_Exception;
import br.com.delphos.billing.services.RetornoCancelarVenda;
import br.com.delphos.billing.services.RetornoIniciarVendaCartaoCredito;


@Remote
@WebService(
	    name = "billing",
	    targetNamespace="http://delphos.com.br/billing"
	)	
public interface consomeDbsWsRemote {
/*	
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
			throws BillingException;

	public RetornoCancelarVenda cancelarVenda(
			String codigoEmpresa,
			String codigoProduto,
			String codigoSistema,
			String token,
			String cpf,
			String codigoVenda,
			String codigoMotivoCancelamento,
			String valorEstorno,
			String flagCancelamentoGateway
			) throws BillingException;
*/
	@WebMethod
	RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
		@WebParam(name = "token") String token,
		@WebParam(name = "codigoEmpresa") String codigoEmpresa,
		@WebParam(name = "codigoProduto") String codigoProduto,
		@WebParam(name = "codigoSistema") String codigoSistema,
		@WebParam(name = "cpf") String cpf,
		@WebParam(name = "nome") String nome,
		@WebParam(name = "email") String email,
		@WebParam(name = "dddCelular") String dddCelular,
		@WebParam(name = "telefoneCelular") String telefoneCelular,
		@WebParam(name = "dddContato") String dddContato,
		@WebParam(name = "telefoneContato") String telefoneContato,
		@WebParam(name = "codigoVenda") String codigoVenda,
		@WebParam(name = "dataVenda") String dataVenda,
		@WebParam(name = "bandeiraCartao") String bandeiraCartao,
		@WebParam(name = "cpfPortadorCartao") String cpfPortadorCartao,
		@WebParam(name = "nomePortadorCartao") String nomePortadorCartao,
		@WebParam(name = "numeroCartao") String numeroCartao,
		@WebParam(name = "vencimentoCartao") String vencimentoCartao,
		@WebParam(name = "cvvCartao") String cvvCartao,
		@WebParam(name = "tipoCobranca") String tipoCobranca,
		@WebParam(name = "quantidadeParcelas") String quantidadeParcelas,
		@WebParam(name = "valorCobranca") String valorCobranca,
		@WebParam(name = "dataPrimeiraCobranca") String dataPrimeiraCobranca,
		@WebParam(name = "dataFimVigencia") String dataFimVigencia
	) throws BillingException_Exception;

	@WebMethod
	RetornoCancelarVenda cancelarVenda(
		@WebParam(name = "codigoEmpresa") String codigoEmpresa,
		@WebParam(name = "codigoProduto") String codigoProduto,
		@WebParam(name = "codigoSistema") String codigoSistema,
		@WebParam(name = "token") String token,
		@WebParam(name = "cpf") String cpf,
		@WebParam(name = "codigoVenda") String codigoVenda,
		@WebParam(name = "codigoMotivoCancelamento") String codigoMotivoCancelamento,
		@WebParam(name = "valorEstorno")String valorEstorno,
		@WebParam(name = "flagCancelamentoGateway")String flagCancelamentoGateway
	) throws BillingException_Exception;
}
