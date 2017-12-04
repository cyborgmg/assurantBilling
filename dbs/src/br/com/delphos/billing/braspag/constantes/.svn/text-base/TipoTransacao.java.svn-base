package br.com.delphos.billing.braspag.constantes;

public enum TipoTransacao {
	
	Invalido(0),
	PreAutorizacao(1),
	CapturaAutomatica(2),
	PreAutorizacaoComAutenticacao(3),
	CapturaAutomaticaComAutenticacao(4),
	PreAutorizacaoRecorrente(5),
	CapturaAutomaticaRecorrente(6);
	
	private final short valor;

	private TipoTransacao(int valor) {
		this.valor = (short) valor;
	}

	public short getValor() {
		return valor;
	}
	
	public static TipoTransacao buscarPorValor(String valor) {
		if (valor == null || !valor.matches("[0-9]+")) {
			return null;
		}
		short valorShort = Short.parseShort(valor);
		for (TipoTransacao elem : values()) {
			if (elem.valor == valorShort) {
				return elem;
			}
		}
		return null;
	}
}
