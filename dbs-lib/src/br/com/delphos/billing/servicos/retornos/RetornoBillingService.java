package br.com.delphos.billing.servicos.retornos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.enumeracoes.CodigoRetornoWebService;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.RetornoServico;
import br.com.delphos.billing.util.ValorRetorno;

@XmlType(propOrder = {
		"retorno",
		"mensagemRetorno",
		"codigoErro",
		"mensagemErro",
})
public abstract class RetornoBillingService implements Serializable, RetornoServico {
	private static final long serialVersionUID = 1L;
	
	private CodigoRetornoWebService codigoRetorno;
	private CodigoMensagem codigoMensagemErro;
	private String mensagemRetorno;
	private String codigoErro;
	private String mensagemErro;

	@XmlTransient
	public abstract String getNomeMetodoWebService();
		
	@Override
	@XmlTransient
	public boolean isSucesso() {
		return codigoRetorno != null ? codigoRetorno == CodigoRetornoWebService.Sucesso : false;
	}
	
	@Override
	public void setValorRetorno(ValorRetorno valorRetorno, Object... args) {
		if (valorRetorno instanceof CodigoMensagem) {
			this.codigoMensagemErro = (CodigoMensagem) valorRetorno;
			this.codigoRetorno = this.codigoMensagemErro.getRetornoWebServicePadrao();
			if (this.mensagemRetorno == null) {
				this.mensagemRetorno = Mensagens.get(this.codigoMensagemErro.getNomePropriedadeErro(), args);
			}
			
		} else if (valorRetorno instanceof CodigoRetornoWebService) {
			this.codigoRetorno = (CodigoRetornoWebService) valorRetorno;
		}
	}
	
	@XmlTransient
	public CodigoRetornoWebService getCodigoRetorno() {
		return codigoRetorno;
	}
	
	public void setCodigoRetorno(CodigoRetornoWebService codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	
	public void setCodigoMensagemRetorno(CodigoMensagem codigoMensagem) {
		this.codigoRetorno = codigoMensagem.getRetornoWebServicePadrao();
		this.mensagemRetorno = Mensagens.get(codigoMensagem.getNomePropriedadeErro());
	}
	
	@XmlTransient
	public CodigoMensagem getCodigoMensagemErro() {
		return this.codigoMensagemErro;
	}
	
	public void setCodigoMensagemErro(CodigoMensagem codigoMensagem) {
		this.codigoMensagemErro = codigoMensagem;
		this.codigoErro = codigoMensagem.getCodigo();
		this.mensagemErro = Mensagens.get(codigoMensagem.getNomePropriedadeErro());
	}
	
	public String getRetorno() {
		return codigoRetorno.getValor();
	}
	
	public void setRetorno(String retorno) {
		this.codigoRetorno = CodigoRetornoWebService.buscarPorValor(retorno);
	}
	
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	
	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}
	
	public String getCodigoErro() {
		return codigoErro;
	}
	
	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
		this.codigoMensagemErro = CodigoMensagem.buscarPorValor(codigoErro);
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	
	
}
