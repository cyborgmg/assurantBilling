package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;

import br.com.delphos.billing.enumeracoes.StatusCancelamento;

public class RetornoCancelarCobrancaPendente extends RetornoUtilitario implements Serializable {
	private static final long serialVersionUID = 1L;

	private StatusCancelamento status;
	private String retorno;
	private boolean estorno;
	private String codigoRetornoAdquirente;
	private String mensagemRetornoAdquirente;
	
	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public StatusCancelamento getStatus() {
		return status;
	}
	
	public void setStatus(StatusCancelamento status) {
		this.status = status;
	}
	
	public boolean isEstorno() {
		return estorno;
	}
	
	public boolean isCancelamento() {
		return !estorno;
	}
	
	public void setEstorno(boolean estorno) {
		this.estorno = estorno;
	}
	
	public String getCodigoRetornoAdquirente() {
		return codigoRetornoAdquirente;
	}
	
	public void setCodigoRetornoAdquirente(String codigoRetornoAdquirente) {
		this.codigoRetornoAdquirente = codigoRetornoAdquirente;
	}
	
	public String getMensagemRetornoAdquirente() {
		return mensagemRetornoAdquirente;
	}
	
	public void setMensagemRetornoAdquirente(String mensagemRetornoAdquirente) {
		this.mensagemRetornoAdquirente = mensagemRetornoAdquirente;
	}
	
}
