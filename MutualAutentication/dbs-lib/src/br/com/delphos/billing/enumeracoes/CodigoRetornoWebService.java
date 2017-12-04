package br.com.delphos.billing.enumeracoes;

import br.com.delphos.billing.util.ValorRetorno;

public enum CodigoRetornoWebService implements ValorRetorno {

	Sucesso("0"),
	Falha("1");
	
	private final String valor;
	
	private CodigoRetornoWebService(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static CodigoRetornoWebService buscarPorValor(String valor) {
		for (CodigoRetornoWebService elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
}
