package br.com.delphos.billing.enumeracoes;

import java.util.ResourceBundle;

public enum StatusLogOperacaoUsuario {
	
	INICIADA		("INI","msg.statusLogOperacaoUsuario.iniciada"  ),
	ERRO			("ERR","msg.statusLogOperacaoUsuario.erro"      ),
	FINALIZADA		("FIN","msg.statusLogOperacaoUsuario.finalizada"),
	SUCESSO         ("SUC","msg.statusLogOperacaoUsuario.sucesso"   );
	

	private final String valor;

	private final String descricao;

	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("mensagens");

	private StatusLogOperacaoUsuario(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusLogOperacaoUsuario buscarPorValor(String valor) {
		for (StatusLogOperacaoUsuario elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}

}
