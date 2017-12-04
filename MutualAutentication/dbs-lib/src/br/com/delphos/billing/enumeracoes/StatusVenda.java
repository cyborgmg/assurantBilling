package br.com.delphos.billing.enumeracoes;

import java.util.ArrayList;
import java.util.List;

public enum StatusVenda {

	Pendente("PEN", false ),
	NaoAutorizada("NAU", false),
	Cancelada("CAN", true ),
	Efetivada("EFE", true );
	
	private final String valor;
	private final boolean terminal;
	

	private StatusVenda(String valor, boolean terminal) {
		this.valor = valor;
		this.terminal = terminal;
	}

	public String getValor() {
		return valor;
	}
	
	public boolean isTerminal() {
		return terminal;
	}

	public static StatusVenda buscarPorValor(String valor) {
		for (StatusVenda elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
	public static List<StatusVenda> getStatusTerminais() {
		List<StatusVenda> retorno = new ArrayList<StatusVenda>();
		for (StatusVenda elem : values()) {
			if (elem.terminal) {
				retorno.add(elem);
			}
		}
		return retorno;
	}
}
