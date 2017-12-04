package br.com.delphos.billing.servicos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarVenda;
import br.com.delphos.billing.servicos.retornos.RetornoIniciarVendaCartaoCredito;

@WebService(name = "Ws", targetNamespace = "http://servicos.billing.delphos.com.br/")
public interface Ws {
	
	@WebMethod(operationName = "iniciarVendaCartaoCredito", action = "urn:IniciarVendaCartaoCredito")
	@WebResult(name = "return")
	public RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
			@WebParam(name = "arg0") String token, @WebParam(name = "arg1") String codigoEmpresa, @WebParam(name = "arg2") String codigoProduto,
			@WebParam(name = "arg3") String codigoSistema, @WebParam(name = "arg4") String cpf, @WebParam(name = "arg5") String nome, @WebParam(name = "arg6") String email,
			@WebParam(name = "arg7") String dddCelular, @WebParam(name = "arg8") String telefoneCelular, @WebParam(name = "arg9") String dddContato,
			@WebParam(name = "arg10") String telefoneContato, @WebParam(name = "arg11") String codigoVenda, @WebParam(name = "arg12") String dataVenda,
			@WebParam(name = "arg13") String bandeiraCartao, @WebParam(name = "arg14") String cpfPortadorCartao,
			@WebParam(name = "arg15") String nomePortadorCartao, @WebParam(name = "arg16") String numeroCartao,
			@WebParam(name = "arg17") String vencimentoCartao, @WebParam(name = "arg18") String cvvCartao, @WebParam(name = "arg19") String tipoCobranca,
			@WebParam(name = "arg20") String quantidadeParcelas, @WebParam(name = "arg21") String valorCobranca,
			@WebParam(name = "arg22") String dataPrimeiraCobranca, @WebParam(name = "arg23") String dataFimVigencia)
			throws BillingException;
	
	@WebMethod(operationName = "cancelarVenda", action = "urn:CancelarVenda")
	@WebResult(name = "return")
	public RetornoCancelarVenda cancelarVenda(@WebParam(name = "arg0") String codigoEmpresa,
			@WebParam(name = "arg1") String codigoProduto, @WebParam(name = "arg2") String codigoSistema, @WebParam(name = "arg3") String token,
			@WebParam(name = "arg4") String cpf, @WebParam(name = "arg5") String codigoVenda,
			@WebParam(name = "arg6") String codigoMotivoCancelamento, @WebParam(name = "arg7") String valorEstorno,
			@WebParam(name = "arg8") String flagCancelamentoGateway) throws BillingException;

}