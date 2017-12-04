package br.com.delphos.billing.enumeracoes;

import br.com.delphos.billing.util.Mensagens;

public enum IdentificadorProcesso {

	TratarCobrancasPendentes("COBPEN", "processo.COBPEN"),
	CancelarCobranca("CANCOB", "processo.CANCOB"),
	EstornarCobranca("ESTCOB", "processo.ESTCOB"),
	CancelarCobrancaInvalida("CANCBI", "processo.CANCBI"),
	DownloadArquivosConciliacao("IMPARQCON", "processo.DLDCNC"),
	ProcessamentoArquivosConciliacao("PRCARQCON", "processo.PRCCNC");
	
	private final String valor;
	private final String nomePropriedade;
	
	private IdentificadorProcesso(String valor, String nomePropriedade) {
		this.valor = valor;
		this.nomePropriedade = nomePropriedade;
	}
	
	public static IdentificadorProcesso buscarPorValor(String valor) {
		for (IdentificadorProcesso elem : values()) {
			if (elem.valor.equals(valor)) {
				return elem;
			}
		}
		return null;
	}
	
	public String getValor() {
		return valor;
	}
	
	public String getNomePropriedade() {
		return nomePropriedade;
	}
	
	public String getMensagem() {
		return Mensagens.get(nomePropriedade);
	}
}
