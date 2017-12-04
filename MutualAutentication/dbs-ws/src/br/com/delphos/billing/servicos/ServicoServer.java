package br.com.delphos.billing.servicos;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.delphos.billing.excecoes.BillingException;
import br.com.delphos.billing.servicos.retornos.RetornoCancelarVenda;
import br.com.delphos.billing.servicos.retornos.RetornoIniciarVendaCartaoCredito;

@WebService(name = "ServicoServer", targetNamespace = "http://servicos.billing.delphos.com.br/")
public interface ServicoServer {
	
	@WebMethod(operationName = "iniciarVendaCartaoCredito", action = "urn:IniciarVendaCartaoCredito")
	@WebResult(name = "return")
	public RetornoIniciarVendaCartaoCredito iniciarVendaCartaoCredito(
			@WebParam(name = "token")                String token,               
			@WebParam(name = "codigoEmpresa")        String codigoEmpresa,       
			@WebParam(name = "codigoProduto")        String codigoProduto,       
			@WebParam(name = "codigoSistema")        String codigoSistema,       
			@WebParam(name = "cpf")                  String cpf,                 
			@WebParam(name = "nome")                 String nome,                
			@WebParam(name = "email")                String email,               
			@WebParam(name = "dddCelular")           String dddCelular,          
			@WebParam(name = "telefoneCelular")      String telefoneCelular,     
			@WebParam(name = "dddContato")           String dddContato,          
			@WebParam(name = "telefoneContato")      String telefoneContato,     
			@WebParam(name = "codigoVenda")          String codigoVenda,         
			@WebParam(name = "dataVenda")            String dataVenda,           
			@WebParam(name = "bandeiraCartao")       String bandeiraCartao,      
			@WebParam(name = "cpfPortadorCartao")    String cpfPortadorCartao,   
			@WebParam(name = "nomePortadorCartao")   String nomePortadorCartao,  
			@WebParam(name = "numeroCartao")         String numeroCartao,        
			@WebParam(name = "vencimentoCartao")     String vencimentoCartao,    
			@WebParam(name = "cvvCartao")            String cvvCartao,           
			@WebParam(name = "tipoCobranca")         String tipoCobranca,        
			@WebParam(name = "quantidadeParcelas")   String quantidadeParcelas,  
			@WebParam(name = "valorCobranca")        String valorCobranca,       
			@WebParam(name = "dataPrimeiraCobranca") String dataPrimeiraCobranca,
			@WebParam(name = "dataFimVigencia")      String dataFimVigencia      
			)throws BillingException;
	
	@WebMethod(operationName = "cancelarVenda", action = "urn:CancelarVenda")
	@WebResult(name = "return")
	public RetornoCancelarVenda cancelarVenda(
			@WebParam(name = "codigoEmpresa") String codigoEmpresa,
			@WebParam(name = "codigoProduto") String codigoProduto, 
			@WebParam(name = "codigoSistema") String codigoSistema, 
			@WebParam(name = "token") String token,
			@WebParam(name = "cpf") String cpf, 
			@WebParam(name = "codigoVenda") String codigoVenda,
			@WebParam(name = "codigoMotivoCancelamento") String codigoMotivoCancelamento, 
			@WebParam(name = "valorEstorno") String valorEstorno,
			@WebParam(name = "flagCancelamentoGateway") String flagCancelamentoGateway
			) throws BillingException;

}
