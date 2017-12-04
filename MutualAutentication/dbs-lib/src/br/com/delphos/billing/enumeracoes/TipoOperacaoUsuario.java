package br.com.delphos.billing.enumeracoes;

import java.util.ResourceBundle;

public enum TipoOperacaoUsuario {
   INCLUSAO 				("INC","msg.tipoOperacao.incluir"  					),
   ALTERACAO				("ALT","msg.tipoOperacao.alterar"  					),
   EXCLUSAO 				("EXC","msg.tipoOperacao.excluir"  					),
   CONFIG_COBRANCA			("CCB","msg.tipoOperacao.config.cobranca"  			),
   ALTERACAO_CARTAO_CREDITO	("ACC","msg.tipoOperacao.alteracao.cartao.credito"  ),
   ATIVACAO					("ATV","msg.tipoOperacao.ativar"  ),
   DESATIVACAO				("DTV","msg.tipoOperacao.desativar"  );
   
   private final String valor;
   
   private final String descricao;
	
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("mensagens");
	
	private TipoOperacaoUsuario(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}
	
	@Deprecated
	public String getDescricao() {
		return bundle.getString(descricao).toUpperCase();
	}	

	public static TipoOperacaoUsuario buscarPorValor(String valor) {
		for (TipoOperacaoUsuario elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
   
}