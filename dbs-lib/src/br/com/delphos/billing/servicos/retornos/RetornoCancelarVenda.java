package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;

public class RetornoCancelarVenda extends RetornoBillingService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoVenda;
	private String codigoProduto;

	@Override
	public String getNomeMetodoWebService() {
		return "cancelarVenda";
	}
	
	public String getCodigoVenda() {
		return codigoVenda;
	}
	
	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	
	public String getCodigoProduto() {
		return codigoProduto;
	}
	
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
}
