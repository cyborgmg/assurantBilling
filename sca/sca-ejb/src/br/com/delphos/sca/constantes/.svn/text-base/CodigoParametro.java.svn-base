package br.com.delphos.sca.constantes;

import java.util.List;

import br.com.delphos.util.configuracoes.Conversor;
import br.com.delphos.util.configuracoes.Conversores;
import br.com.delphos.util.configuracoes.Propriedade;

public enum CodigoParametro implements Propriedade {

	Id("id", "sca.parametro.id", Conversores.paraString()),
	Entidade("entidade", "sca.parametro.entidade", Conversores.paraString()),
	Propriedade("propriedade", "sca.parametro.propriedade", Conversores.paraString()),
	LoginUsuario("loginUsuario", "sca.parametro.loginUsuario", Conversores.paraString()),
	FaixaIndices("faixaIndices", "sca.parametro.faixaIndices", Conversores.paraString()),
	Descricao("descricao", "sca.parametro.descricao", Conversores.paraString()),
	Email("email", "sca.parametro.email", Conversores.paraString()),
	;
	
	private final String nomePropriedade;
	private final String codigo;
	private final Conversor conversor;
	
	private CodigoParametro(String codigo, String nomePropriedade, Conversor conversor) {
		this.nomePropriedade = nomePropriedade;
		this.codigo = codigo;
		this.conversor = conversor;
	}

	@Override
	public String getNomePropriedade() {
		return nomePropriedade;
	}

	public String getCodigo() {
		return codigo;
	}

	public static CodigoParametro buscarPorValor(String valor) {
		for (CodigoParametro elem : values()) {
			if (elem != null && elem.codigo.equals(valor)) {
				return elem;
			}
		}
		return null;
	}

	@Override
	public List<Object> getArgumentos() {
		return null;
	}

	@Override
	public Conversor getConversor() {
		return conversor;
	}
}
