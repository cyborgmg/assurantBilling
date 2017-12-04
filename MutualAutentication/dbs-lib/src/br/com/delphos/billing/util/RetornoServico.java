package br.com.delphos.billing.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;


public interface RetornoServico extends Serializable {

	@XmlTransient
	boolean isSucesso();
	void setValorRetorno(ValorRetorno valorRetorno, Object... args);
}
