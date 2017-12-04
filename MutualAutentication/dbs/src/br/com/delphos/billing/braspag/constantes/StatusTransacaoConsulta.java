package br.com.delphos.billing.braspag.constantes;

import br.com.delphos.billing.enumeracoes.StatusAutorizacao;
import br.com.delphos.billing.enumeracoes.StatusCobranca;

public enum StatusTransacaoConsulta {

	Invalido(-1, null, StatusAutorizacao.Desconhecido),
	Indefinida(0, null, StatusAutorizacao.Desconhecido),
	Capturada(1, StatusCobranca.CobradaConciliada, StatusAutorizacao.Capturado),
	Autorizada(2, StatusCobranca.ParcialmenteCobrada, StatusAutorizacao.Autorizado),
	NaoAutorizada(3, StatusCobranca.NaoAutorizada, StatusAutorizacao.NaoAutorizado),
	Cancelada(4, StatusCobranca.Cancelada, StatusAutorizacao.Cancelado),
	Estornada(5, StatusCobranca.Estornada, StatusAutorizacao.Estornado),
	Aguardando(6, StatusCobranca.EstornoSolicitado, StatusAutorizacao.AguardandoResposta),
	Desqualificada(7, null, StatusAutorizacao.NaoAutorizado);
	
	private final short valor;
	private final StatusCobranca statusCobrancaEquivalente;
	private final StatusAutorizacao statusAutorizacaoCobranca;

	private StatusTransacaoConsulta(
			int valor, 
			StatusCobranca statusCobrancaEquivalente, 
			StatusAutorizacao statusAutorizacaoCobranca) {
		this.valor = (short) valor;
		this.statusCobrancaEquivalente = statusCobrancaEquivalente;
		this.statusAutorizacaoCobranca = statusAutorizacaoCobranca;
	}

	public short getValor() {
		return valor;
	}
	
	public StatusCobranca getStatusCobrancaEquivalente() {
		return statusCobrancaEquivalente;
	}

	public StatusAutorizacao getStatusAutorizacaoEfetiva() {
		return statusAutorizacaoCobranca;
	}

	public static final StatusTransacaoConsulta buscarPorValor(int valor) {
		if (valor < Short.MIN_VALUE || valor > Short.MAX_VALUE) {
			return Invalido;
		}
		return buscarPorValor((short) valor);
	}
	
	public static final StatusTransacaoConsulta buscarPorValor(short valor) {
		StatusTransacaoConsulta retorno = Invalido;
		for (StatusTransacaoConsulta elem : values()) {
			if (elem.valor == valor) {
				retorno = elem;
				break;
			}
		}
		return retorno;
	}
}
