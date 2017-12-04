package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.CodigoRetornoUtilitario;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.RetornoServico;
import br.com.delphos.billing.util.ValorRetorno;

public class RetornoUtilitario implements Serializable, RetornoServico {
	private static final long serialVersionUID = 1L;
	
	private CodigoRetornoUtilitario codigoRetorno;
	private String mensagemRetorno;
	private String codigoErroProvedor;
	private String mensagemErroProvedor;
	private String tipoException;
	private String mensagemException;

	@XmlTransient
	public CodigoRetornoUtilitario getCodigoRetorno() {
		return codigoRetorno;
	}
	
	public void setCodigoRetorno(CodigoRetornoUtilitario codigoRetorno) {
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

	public void setMensagemRetorno(CodigoMensagem mensagem, Object... args) {
		this.mensagemRetorno = Mensagens.get(mensagem.getNomePropriedadeErro(), args);
	}
	
	public String getCodigoErroProvedor() {
		return codigoErroProvedor;
	}
	
	public void setCodigoErroProvedor(String codigoErroProvedor) {
		this.codigoErroProvedor = codigoErroProvedor;
	}
	
	public String getMensagemErroProvedor() {
		return mensagemErroProvedor;
	}
	
	public void setMensagemErroProvedor(String mensagemErroProvedor) {
		this.mensagemErroProvedor = mensagemErroProvedor;
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

	@Override
	@XmlTransient
	public boolean isSucesso() {
		return codigoRetorno != null ? codigoRetorno == CodigoRetornoUtilitario.Sucesso : false;
	}
	
	@Override
	public void setValorRetorno(ValorRetorno valorRetorno, Object... args) {
		if (valorRetorno instanceof CodigoMensagem) {
			CodigoMensagem codigoMensagemRetorno = (CodigoMensagem) valorRetorno;
			if (this.mensagemRetorno != null) {
				this.mensagemRetorno = Mensagens.get(codigoMensagemRetorno.getNomePropriedadeErro(), args);
			}
			
		} else if (valorRetorno instanceof CodigoRetornoUtilitario) {
			this.codigoRetorno = (CodigoRetornoUtilitario) valorRetorno;
		}
	}
	
}
