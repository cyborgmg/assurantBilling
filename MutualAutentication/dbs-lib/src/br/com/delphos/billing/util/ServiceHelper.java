package br.com.delphos.billing.util;

import javax.ejb.EJBContext;
import javax.ejb.EJBException;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;

public interface ServiceHelper<T extends RetornoServico> extends LoggerWrapper {

	T getRetorno();
	T retorno();
	T retorno(boolean imprimirRetorno);
	void setValorRetorno(
			CodigoMensagem codigoRetorno,
			Object... args);
	void setCodigoRetornoParaParametro(
			CodigoMensagem codigoRetorno,
			String nomeParametro);
	void sucesso();
	void falha();
	boolean isSucesso();
	void setMensagemRetornoPadrao(String mensagemRetorno);
	String getMensagemRetornoPadrao();
	void begin(EJBContext ejbContext, boolean lancandoExcecao) throws EJBException;
	void commit() throws EJBException;
	void rollback() throws EJBException;
	void setRollbackOnly() throws EJBException;
}
