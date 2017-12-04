package br.com.delphos.billing.enumeracoes;

import java.io.Serializable;

import br.com.delphos.billing.util.ValorRetorno;

public enum CodigoRetornoUtilitario implements Serializable, ValorRetorno {
	Sucesso("0"),
	Falha("1"),
	ErroValidacao("2"),
	Excecao(null);
	
	private final String valor;
	
	private CodigoRetornoUtilitario(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public static CodigoRetornoUtilitario buscarPorValor(String valor) {
		if (valor == null) {
			return Excecao;
		}
		for (CodigoRetornoUtilitario codigo : values()) {
			if (codigo.valor != null && codigo.getValor().equals(valor)) {
				return codigo;
			}
		}
		return null;
	}
}