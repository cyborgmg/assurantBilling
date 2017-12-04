package br.com.delphos.sca.constantes;

import java.util.List;

import br.com.delphos.util.configuracoes.Conversor;
import br.com.delphos.util.configuracoes.Conversores;

public enum CodigoMensagem {
	
	SCA0001("SCA0001", "sca.mensagem.SCA0001.campoInvalido", Conversores.paraString()),
	SCA0002("SCA0002", "sca.mensagem.SCA0002.erroProcedimento", Conversores.paraString()),
	Sucesso(null, null, Conversores.paraString()),
	Falha  ("SCA0002", "sca.mensagem.SCA0002.erroProcedimento", Conversores.paraString()),
	;
	
	private final String codigo;
	private final String nomePropriedade;
	private final Conversor conversor;
	
	private CodigoMensagem(String codigo, String nomePropriedade, Conversor conversor) {
		this.codigo = codigo;
		this.nomePropriedade = nomePropriedade;
		this.conversor = conversor;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public String getNomePropriedadeErro() {
		return nomePropriedade;
	}
	
	public String getValor() {
		return codigo;
	}
	
	public static CodigoMensagem buscarPorValor(String valor) {
		if (valor == null) {
			return Sucesso;
		}
		for (CodigoMensagem elem : values()) {
			if (elem != null && elem.codigo.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
	public List<Object> getArgumentos() {
		return null;
	}

	public Conversor getConversor() {
		return conversor;
	}
}
