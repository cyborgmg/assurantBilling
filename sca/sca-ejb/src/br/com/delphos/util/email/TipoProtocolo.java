package br.com.delphos.util.email;

public enum TipoProtocolo {

	SMTP("smtp", 25, "SMTP"),
	SMTPS("smtp", 465, "SMTPS"),
	TLS("smtp", 465, "TLS");
	
	private final String tipo;
	private final int portaPadrao;
	private String valor;
	

	private TipoProtocolo(String tipo, int portaPadrao, String valor) {
		this.tipo = tipo;
		this.portaPadrao = portaPadrao;
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public int getPortaPadrao() {
		return portaPadrao;
	}
	
	public static TipoProtocolo buscarPorValor(String valor) {
		for (TipoProtocolo elem : values()) {
			if (elem == null) {
				if (valor == null) {
					return elem;
				}
			} else if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}	
	
}
