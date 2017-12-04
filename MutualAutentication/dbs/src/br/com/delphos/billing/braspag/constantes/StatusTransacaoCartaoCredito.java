package br.com.delphos.billing.braspag.constantes;

public enum StatusTransacaoCartaoCredito {
	
	Invalida(-1),
	Capturada(0),
	Autorizada(1),
	NaoAutorizada(2),
	ErroDesqualificante(3),
	AguardandoResposta(4);
	
	private final short valor;

	private StatusTransacaoCartaoCredito(int valor) {
		this.valor = (short) valor;
	}

	public short getValor() {
		return valor;
	}
	
	public static final StatusTransacaoCartaoCredito buscarPorValor(int valor) {
		if (valor < Short.MIN_VALUE || valor > Short.MAX_VALUE) {
			return Invalida;
		}
		return buscarPorValor((short) valor);
	}
	
	public static final StatusTransacaoCartaoCredito buscarPorValor(short valor) {
		StatusTransacaoCartaoCredito retorno = Invalida;
		for (StatusTransacaoCartaoCredito elem : values()) {
			if (elem.valor == valor) {
				retorno = elem;
				break;
			}
		}
		return retorno;
	}
}
