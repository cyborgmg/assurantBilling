package br.com.delphos.billing.enumeracoes;


public enum Entidade {
	
		ADQUIRENTE				("Adquirente", "Adquirente".toUpperCase()),	
    	BANDEIRA				("Bandeira", "Bandeira".toUpperCase()),
    	CONTRATOCOBRANCA		("ContratoCobranca", "Contrato de Cobranca".toUpperCase()),
    	EMPRESA 				("Empresa", "Empresa".toUpperCase()),
    	ITEMLISTA				("ItemLista", "Item da Lista de Valor".toUpperCase()),
    	LISTAVALOR				("ListaValor", "Lista de Valor".toUpperCase()),
    	MEIOPAGAMENTO			("MeioPagamento", "Meio de Pagamento".toUpperCase()),
	    PRODUTO					("Produto", "Produto".toUpperCase()),
	    PROVEDOR				("Provedor", "Provedor".toUpperCase()),
	    RESPOSTAAUTORIZACAO		("RespostaAutorizacao", "Resposta de Autorizacao".toUpperCase()),
	    SISTEMA 				("Sistema", "Sistema".toUpperCase());
	   
	    private final String valor;
	    
	    private final String descricao;
	   
		private Entidade(String valor, String descricao) {
			this.valor = valor;
			this.descricao = descricao;
		}

		public String getValor() {
			return valor;
		}

		public String getDescricao() {
			return descricao;
		}
	 
}
