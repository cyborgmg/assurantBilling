package br.com.delphos.util.configuracoes;

public class ResolvedorSincronizado implements Resolvedor {

	private final Object lock = new Object();
	private final Resolvedor resolvedor;
	
	public ResolvedorSincronizado() {
		this.resolvedor = null;
	}
	
	public ResolvedorSincronizado(Resolvedor resolvedor) {
		this.resolvedor = resolvedor;
	}

	@Override
	public void limparCache() {
		synchronized(lock) {
			this.resolvedor.limparCache();
		}
	}

	@Override
	public String getString(String nomePropriedade) {
		synchronized(lock) {
			return this.resolvedor.getString(nomePropriedade);
		}
	}

}
