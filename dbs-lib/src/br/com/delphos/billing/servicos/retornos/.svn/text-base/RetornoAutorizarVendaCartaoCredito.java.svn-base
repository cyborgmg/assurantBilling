package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.CodigoRetornoAutorizarVenda;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.RetornoServico;
import br.com.delphos.billing.util.ValorRetorno;

public class RetornoAutorizarVendaCartaoCredito implements Serializable, RetornoServico {
	private static final long serialVersionUID = 1L;

	private CodigoRetornoAutorizarVenda codigoRetorno;
	private CodigoMensagem codigoMensagemRetorno;
	private String mensagemRetorno;
	private String idTransacaoAdquirente;
	private String codigoAutorizacao;
	private String numeroComprovanteVenda;
	private String retornoAdquirente;
	private String mensagemAdquirente;
	private String provedorOrderId;
	private String provedorTransactionId;
	private String retornoProvedor;
	private String mensagemProvedor;
	private String tipoException;
	private String mensagemException;
	private String tokenCartao;

	@XmlTransient
	public CodigoRetornoAutorizarVenda getCodigoRetorno() {
		return codigoRetorno;
	}
	
	public void setCodigoRetorno(CodigoRetornoAutorizarVenda codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	
	public String getRetorno() {
		return codigoRetorno.getValor();
	}

	public String getMensagemRetorno() {
		return mensagemRetorno;
	}

	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}

	public void setMensagemRetorno(CodigoMensagem codigoMensagem) {
		setValorRetorno(codigoMensagem);
	}
	
	public String getIdTransacaoAdquirente() {
		return idTransacaoAdquirente;
	}

	public void setIdTransacaoAdquirente(String idTransacaoAdquirente) {
		this.idTransacaoAdquirente = idTransacaoAdquirente;
	}

	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}

	public String getNumeroComprovanteVenda() {
		return numeroComprovanteVenda;
	}

	public void setNumeroComprovanteVenda(String numeroComprovanteVenda) {
		this.numeroComprovanteVenda = numeroComprovanteVenda;
	}

	public String getRetornoAdquirente() {
		return retornoAdquirente;
	}

	public void setRetornoAdquirente(String retornoAdquirente) {
		this.retornoAdquirente = retornoAdquirente;
	}

	public String getMensagemAdquirente() {
		return mensagemAdquirente;
	}

	public void setMensagemAdquirente(String mensagemAdquirente) {
		this.mensagemAdquirente = mensagemAdquirente;
	}

	public String getProvedorOrderId() {
		return provedorOrderId;
	}

	public void setProvedorOrderId(String provedorOrderId) {
		this.provedorOrderId = provedorOrderId;
	}

	public String getProvedorTransactionId() {
		return provedorTransactionId;
	}

	public void setProvedorTransactionId(String provedorTransactionId) {
		this.provedorTransactionId = provedorTransactionId;
	}

	public String getRetornoProvedor() {
		return retornoProvedor;
	}

	public void setRetornoProvedor(String retornoProvedor) {
		this.retornoProvedor = retornoProvedor;
	}

	public String getMensagemProvedor() {
		return mensagemProvedor;
	}

	public void setMensagemProvedor(String mensagemProvedor) {
		this.mensagemProvedor = mensagemProvedor;
	}

	public String getTipoException() {
		return tipoException;
	}

	public void setTipoException(String tipoException) {
		this.tipoException = tipoException;
	}

	public String getMensagemException() {
		return mensagemException;
	}

	public void setMensagemException(String mensagemException) {
		this.mensagemException = mensagemException;
	}

	public String getTokenCartao() {
		return tokenCartao;
	}

	public void setTokenCartao(String tokenCartao) {
		this.tokenCartao = tokenCartao;
	}
	
	@Override
	@XmlTransient
	public boolean isSucesso() {
		return codigoMensagemRetorno != null ? codigoMensagemRetorno == CodigoMensagem.Sucesso : false;
	}
	
	@Override
	public void setValorRetorno(ValorRetorno valorRetorno, Object... args) {
		if (valorRetorno instanceof CodigoMensagem) {
			this.codigoMensagemRetorno = (CodigoMensagem) valorRetorno;
			if (this.mensagemRetorno == null) {
				this.mensagemRetorno = Mensagens.get(this.codigoMensagemRetorno.getNomePropriedadeErro(), args);
			}
			
		}
	}

}
