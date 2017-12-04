package br.com.delphos.billing.enumeracoes;

import br.com.delphos.billing.util.ValorRetorno;

public enum CodigoMensagem implements ValorRetorno {
	
	DPH0001("DPH0001", CodigoRetornoWebService.Falha, "erro.campo.invalido"),
	DPH0002("DPH0002", CodigoRetornoWebService.Falha, "erro.produto.naoAssociado.empresa"),
	DPH0003("DPH0003", CodigoRetornoWebService.Falha, "erro.sistema.naoAssociado.produto"),
	DPH0004("DPH0004", CodigoRetornoWebService.Falha, "erro.dataVenda.maior.hoje"),
	DPH0005("DPH0005", CodigoRetornoWebService.Falha, "erro.vencimentoCartao.maior.hoje"),
	DPH0006("DPH0006", CodigoRetornoWebService.Falha, "erro.numeroParcelas.maior.limite.produto"),
	DPH0007("DPH0007", CodigoRetornoWebService.Falha, "erro.dataPrimeiraCobranca.invalida"),
	DPH0008("DPH0008", CodigoRetornoWebService.Falha, "erro.dataFimVigencia.invalida"),
	DPH0009("DPH0009", CodigoRetornoWebService.Falha, "erro.procedimento"),
	DPH0010("DPH0010", CodigoRetornoWebService.Falha, "msg.transacaoNaoAutorizada.adquirente"),
	DPH0011("DPH0011", CodigoRetornoWebService.Falha, "msg.transacaoNaoConcluida.adquirente"),
	DPH0012("DPH0012", CodigoRetornoWebService.Falha, "erro.contratoCobranca.vigencia"),
	DPH0013("DPH0013", CodigoRetornoWebService.Falha, "erro.contratoCobranca.empresa"),
	DPH0014("DPH0014", CodigoRetornoWebService.Falha, "erro.contratoCobranca.produto"),
	DPH0015("DPH0015", CodigoRetornoWebService.Falha, "erro.contratoCobranca.tipoMeioPagamento"),
	DPH0016("DPH0016", CodigoRetornoWebService.Falha, "erro.contratoCobrancaAssociacaoBandeira"),
	DPH0017("DPH0017", CodigoRetornoWebService.Falha, "erro.bandeiraAdquirenteDefault"),
	DPH0018("DPH0018", CodigoRetornoWebService.Falha, "msg.vendaNaoLocalizada"),
	DPH0019("DPH0019", CodigoRetornoWebService.Falha, "msg.codigoVendaInvalido"),
	DPH0020("DPH0020", CodigoRetornoWebService.Falha, "erro.quantidadeParcelaNaoPermitida"),
	DPH0021("DPH0021", CodigoRetornoWebService.Falha, "erro.dataInvalida"),
	DPH0022("DPH0022", CodigoRetornoWebService.Falha, "erro.dataCancelamentoForaVigencia"),
	DPH0023("DPH0023", CodigoRetornoWebService.Falha, "msg.solicitacaoCancelamentoNegado"),
	DPH0024("DPH0024", CodigoRetornoWebService.Falha, "msg.erroAcesso"),
	DPH0025("DPH0025", CodigoRetornoWebService.Falha, "erro.quantidadeParcelasInvalido"),
	DPH0026("DPH0026", CodigoRetornoWebService.Falha, "erro.tipoCobranca"),
	DPH0027("DPH0027", CodigoRetornoWebService.Falha, "erro.ProcessamentoEstorno"),
	DPH0028("DPH0028", CodigoRetornoWebService.Falha, "erro.ValorEstornoInvalido"),
	DPH0029("DPH0029", CodigoRetornoWebService.Falha, "erro.codigoVendaInvalidoCancelamento"),
	DPH0030("DPH0030", CodigoRetornoWebService.Falha, "erro.conciliacao.tipoEventoNaoTratadoPeloDBS"),
	DPH0031("DPH0031", CodigoRetornoWebService.Falha, "erro.conciliacao.cobrancaNaoEncontrada"),
	DPH0032("DPH0032", CodigoRetornoWebService.Falha, "erro.conciliacao.aceleracaoJaConciliada"),
	DPH0033("DPH0033", CodigoRetornoWebService.Falha, "erro.conciliacao.estornoJaConciliado"),
	DPH0034("DPH0034", CodigoRetornoWebService.Falha, "erro.conciliacao.valorForaMargemAceitacao"),
	DPH0035("DPH0035", CodigoRetornoWebService.Falha, "erro.conciliacao.statusCobrancaInvalido"),
	DPH0036("DPH0036", CodigoRetornoWebService.Falha, "erro.conciliacao.cobrancaJaConciliada"),
	Sucesso(null, CodigoRetornoWebService.Sucesso, null),
	Falha  ("DPH0009", CodigoRetornoWebService.Falha, "erro.procedimento"),
	;
	
	private final String codigo;
	private final CodigoRetornoWebService retornoWebServicePadrao;
	private final String nomePropriedade;
	
	private CodigoMensagem(String codigo, CodigoRetornoWebService retornoPadrao, String nomePropriedade) {
		this.codigo = codigo;
		this.retornoWebServicePadrao = retornoPadrao;
		this.nomePropriedade = nomePropriedade;
	}

	public String getCodigo() {
		return codigo;
	}

	public CodigoRetornoWebService getRetornoWebServicePadrao() {
		return retornoWebServicePadrao;
	}

	public String getNomePropriedadeErro() {
		return nomePropriedade;
	}
	
	@Override
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
}
