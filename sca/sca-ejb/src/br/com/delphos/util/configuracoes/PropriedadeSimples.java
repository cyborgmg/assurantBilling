package br.com.delphos.util.configuracoes;

import java.util.List;

public class PropriedadeSimples implements Propriedade {

	private String nomePropriedade;
	private List<Object> argumentos;
	private Conversor conversor;
	private Propriedade propriedadeOriginal;
	
	public PropriedadeSimples() {
	}
	
	public PropriedadeSimples(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}
	
	public PropriedadeSimples(String nomePropriedade, List<Object> argumentos) {
		this.nomePropriedade = nomePropriedade;
		this.argumentos = argumentos;
	}
	
	public PropriedadeSimples(String nomePropriedade, List<Object> argumentos, Conversor conversor) {
		this.nomePropriedade = nomePropriedade;
		this.argumentos = argumentos;
		this.conversor = conversor;
	}

	public PropriedadeSimples(Propriedade propriedadeOriginal) {
		this.propriedadeOriginal = propriedadeOriginal;
	}

	public PropriedadeSimples(Propriedade propriedadeOriginal, List<Object> argumentos) {
		this.propriedadeOriginal = propriedadeOriginal;
		this.argumentos = argumentos;
	}

	public PropriedadeSimples(Propriedade propriedadeOriginal, String nomePropriedade, List<Object> argumentos) {
		this.propriedadeOriginal = propriedadeOriginal;
		this.nomePropriedade = nomePropriedade;
		this.argumentos = argumentos;
	}
	
	public String getNomePropriedade() {
		return nomePropriedade != null
				? nomePropriedade
				: propriedadeOriginal != null
					? propriedadeOriginal.getNomePropriedade()
					: null
				;
	}
	
	public void setNomePropriedade(String nomePropriedade) {
		this.nomePropriedade = nomePropriedade;
	}
	
	public List<Object> getArgumentos() {
		return argumentos != null
				? argumentos
				: propriedadeOriginal != null
					? propriedadeOriginal.getArgumentos()
					: null
				;
	}
	
	public void setArgumentos(List<Object> argumentos) {
		this.argumentos = argumentos;
	}
	
	public Conversor getConversor() {
		return conversor != null
				? conversor
				: propriedadeOriginal != null
					? propriedadeOriginal.getConversor()
					: null
				;
	}
	
	public void setConversor(Conversor conversor) {
		this.conversor = conversor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomePropriedade == null) ? 0 : nomePropriedade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropriedadeSimples other = (PropriedadeSimples) obj;
		if (nomePropriedade == null) {
			if (other.nomePropriedade != null)
				return false;
		} else if (!nomePropriedade.equals(other.nomePropriedade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PropriedadeSimples [nomePropriedade=" + nomePropriedade + "]";
	}
	
}
