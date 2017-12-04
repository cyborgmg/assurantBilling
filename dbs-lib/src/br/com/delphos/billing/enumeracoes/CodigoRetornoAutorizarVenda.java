package br.com.delphos.billing.enumeracoes;

import br.com.delphos.billing.util.ValorRetorno;

public enum CodigoRetornoAutorizarVenda implements ValorRetorno {
	Excecao(null),
	Autorizado("0"),
	NaoAutorizado("1"),
	NaoConfirmado("2"),
	NaoChegouNaAdquirente("3"),
	TimeoutAdquirente("4");

	private final String valor;

	private CodigoRetornoAutorizarVenda(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public static CodigoRetornoAutorizarVenda buscarPorValor(String valor) {
		if (valor == null) {
			return Excecao;
		}
		for (CodigoRetornoAutorizarVenda codigo : values()) {
			if (codigo.valor != null && codigo.getValor().equals(valor)) {
				return codigo;
			}
		}
		return null;
	}
}