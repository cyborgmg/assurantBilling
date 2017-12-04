package br.com.delphos.billing.servicos;

import javax.ejb.Remote;

import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.excecoes.BillingWebServiceException;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarVenda;
import br.com.delphos.billing.servicos.retornos.RetornoIniciarVendaCartaoCredito;

@Remote
public interface BillingService {

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

	//public String printHello();
}
