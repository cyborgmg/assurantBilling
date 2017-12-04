package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;
import java.util.Date;

import br.com.delphos.billing.enumeracoes.StatusAutorizacao;
import br.com.delphos.billing.enumeracoes.StatusCobranca;

public class RetornoConsultarStatusCobranca extends RetornoUtilitario implements Serializable {
	private static final long serialVersionUID = 1L;

	private StatusCobranca statusCobranca;
	private StatusAutorizacao statusAutorizacaoEfetiva;
	private String mensagemProvedor;
	private String codigoErroAdqirente;
	private String mensagemErroAdquirente;
	private Date dataRecebimentoProvedor;
	private Date dataCaptura;
	private Date dataCancelamento;
	
	public StatusCobranca getStatusCobranca() {
		return statusCobranca;
	}
	
	public void setStatusCobranca(StatusCobranca statusCobranca) {
		this.statusCobranca = statusCobranca;
	}

	public Date getDataRecebimentoProvedor() {
		return dataRecebimentoProvedor;
	}

	public void setDataRecebimentoProvedor(Date dataRecebimentoProvedor) {
		this.dataRecebimentoProvedor = dataRecebimentoProvedor;
	}

	public Date getDataCaptura() {
		return dataCaptura;
	}

	public void setDataCaptura(Date dataCaptura) {
		this.dataCaptura = dataCaptura;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public StatusAutorizacao getStatusAutorizacaoEfetiva() {
		return statusAutorizacaoEfetiva;
	}

	public void setStatusAutorizacaoEfetiva(
			StatusAutorizacao statusAutorizacaoCobranca) {
		this.statusAutorizacaoEfetiva = statusAutorizacaoCobranca;
	}

	public String getCodigoErroAdqirente() {
		return codigoErroAdqirente;
	}

	public void setCodigoErroAdqirente(String codigoErroAdqirente) {
		this.codigoErroAdqirente = codigoErroAdqirente;
	}

	public String getMensagemErroAdquirente() {
		return mensagemErroAdquirente;
	}

	public void setMensagemErroAdquirente(String mensagemErroAdquirente) {
		this.mensagemErroAdquirente = mensagemErroAdquirente;
	}

	public String getMensagemProvedor() {
		return mensagemProvedor;
	}

	public void setMensagemComplemento(String mensagemProvedor) {
		this.mensagemProvedor = mensagemProvedor;
	}
	
}
