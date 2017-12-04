package br.com.delphos.sca.constantes;

public enum OperacaoUsuario {

	ESQUECI_SENHA("R"),
	CONFIRMAR_EMAIL("A");
	
	public static final String NOME_PROPRIEDADE_TOKEN = "t";
	public static final String NOME_PROPRIEDADE_OPERACAO = "o";
	
	private final String valor;

	private OperacaoUsuario(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	public static OperacaoUsuario buscarPorValor(String valor) {
		for (OperacaoUsuario elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
}
