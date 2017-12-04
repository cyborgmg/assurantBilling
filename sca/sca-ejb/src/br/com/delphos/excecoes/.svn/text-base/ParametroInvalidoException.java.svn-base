package br.com.delphos.excecoes;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import br.com.delphos.sca.constantes.CodigoMensagem;
import br.com.delphos.sca.constantes.CodigoParametro;

public class ParametroInvalidoException extends ValidacaoException {
	private static final long serialVersionUID = 1L;

	private final CodigoParametro codigoParametro;
	private final String nomeParametro;
	
	public ParametroInvalidoException() {
		super();
		this.codigoParametro = null;
		this.nomeParametro = null;
	}

	public ParametroInvalidoException(CodigoMensagem codigoRetorno, Object... args) {
		super(codigoRetorno, args);
		this.codigoParametro = null;
		this.nomeParametro = null;
	}

	public ParametroInvalidoException(String m, Throwable t) {
		super(m, t);
		this.codigoParametro = null;
		this.nomeParametro = null;
	}

	public ParametroInvalidoException(String m) {
		super(m);
		this.codigoParametro = null;
		this.nomeParametro = null;
	}

	public ParametroInvalidoException(Throwable t) {
		super(t);
		this.codigoParametro = null;
		this.nomeParametro = null;
	}

	//  -----
	
	public ParametroInvalidoException(CodigoParametro codigoParametro) {
		super(CodigoMensagem.SCA0001, new Object[]{codigoParametro});
		this.codigoParametro = codigoParametro;
		this.nomeParametro = null;
	}

	public ParametroInvalidoException(CodigoParametro codigoParametro, String nomeParametro) {
		super(CodigoMensagem.SCA0001, new Object[]{codigoParametro, nomeParametro});
		this.codigoParametro = codigoParametro;
		this.nomeParametro = nomeParametro;
	}
	
	public ParametroInvalidoException(CodigoParametro codigoParametro, CodigoMensagem codigoMensagem, Object... args) {
		super(codigoMensagem, addParametro(codigoParametro, null, args));
		this.codigoParametro = codigoParametro;
		this.nomeParametro = null;
	}

	public ParametroInvalidoException(CodigoParametro codigoParametro, String nomeParametro, CodigoMensagem codigoMensagem, Object... args) {
		super(codigoMensagem, addParametro(codigoParametro, nomeParametro, args));
		this.codigoParametro = codigoParametro;
		this.nomeParametro = nomeParametro;
	}
	
	@XmlTransient
	public CodigoParametro getCodigoParametro() {
		return codigoParametro;
	}
	
	public String getNomeParametro() {
		return nomeParametro;
	}
	
	private static Object[] addParametro(CodigoParametro parametro, String nomeParametro, Object[] argumentos) {
		List<Object> lista = Arrays.asList(argumentos);
		if (nomeParametro != null) {
			lista.add(0, parametro);
		}
		lista.add(0, parametro);
		return lista.toArray(new Object[lista.size()]);
	}
}
