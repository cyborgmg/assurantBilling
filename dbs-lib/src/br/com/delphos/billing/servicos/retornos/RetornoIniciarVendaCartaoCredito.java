package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {
		"codigoProduto",
		"codigoVenda",
		"codigoVendaBilling"
})
public class RetornoIniciarVendaCartaoCredito extends RetornoBillingService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoProduto;
	private String codigoVenda;
	private String codigoVendaBilling;

	@Override
	public String getNomeMetodoWebService() {
		return "iniciarVendaCartaoCredito";
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	public String getCodigoVendaBilling() {
		return codigoVendaBilling;
	}

	public void setCodigoVendaBilling(String codigoVendaBilling) {
		this.codigoVendaBilling = codigoVendaBilling;
	}

}
