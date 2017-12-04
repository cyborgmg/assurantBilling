package br.com.delphos.billing.braspag.constantes;

import br.com.delphos.billing.enumeracoes.StatusCancelamento;

public enum StatusTransacao {

	ErroInterno(null, null),
	Confirmado((short) 0, StatusCancelamento.Cancelado),
	Negado((short) 1, StatusCancelamento.Negado),
	TransacaoInvalida((short) 2, StatusCancelamento.Cancelado),
	EstornoAceito((short) 3, StatusCancelamento.Aceito)
	;
	
	private final Short valor;
	private final StatusCancelamento statusCancelamento;
	
	private StatusTransacao(Short valor, StatusCancelamento statusCancelamento) {
		this.valor = valor;
		this.statusCancelamento = statusCancelamento;
	}

	public short getValor() {
		return valor;
	}
	
	public StatusCancelamento getStatusCancelamento() {
		return statusCancelamento;
	}

	public static StatusTransacao buscarPorValor(Integer valor) {
		if (valor == null) {
			return ErroInterno;
		}
		if (valor >= Short.MIN_VALUE && valor <= Short.MAX_VALUE) {
			return buscarPorValor(valor.shortValue());
		}
		return null;
	}
	
	public static StatusTransacao buscarPorValor(Short valor) {
		if (valor == null) {
			return ErroInterno;
		}
		for (StatusTransacao elem : values()) {
			if (elem.valor != null && elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
