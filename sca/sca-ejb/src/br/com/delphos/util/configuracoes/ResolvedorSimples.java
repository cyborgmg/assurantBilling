package br.com.delphos.util.configuracoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResolvedorSimples implements Resolvedor {

	private boolean verificandoPropriedadesSistema = true;
	private boolean usandoCache = true;
	private List<ResourceBundle> bundles = new ArrayList<ResourceBundle>();
	private Map<String, String> cache = new HashMap<String, String>();
	
	public boolean isVerificandoPropriedadesSistema() {
		return verificandoPropriedadesSistema;
	}
	
	public void setVerificandoPropriedadesSistema(boolean verificandoPropriedadesSistema) {
		this.verificandoPropriedadesSistema = verificandoPropriedadesSistema;
	}
	
	public boolean isUsandoCache() {
		return usandoCache;
	}
	
	public void setUsandoCache(boolean usandoCache) {
		this.usandoCache = usandoCache;
	}
	
	public List<ResourceBundle> getBundles() {
		return bundles;
	}
	
	public void setBundles(List<ResourceBundle> bundles) {
		this.bundles = bundles;
	}
	
	public void addBundle(ResourceBundle... bundles) {
		this.bundles.addAll(Arrays.asList(bundles));
	}
	
	public Map<String, String> getCache() {
		return cache;
	}
	
	public void setCache(Map<String, String> cache) {
		this.cache = cache;
	}
	
	@Override
	public void limparCache() {
		cache.clear();
	}
	
	@Override
	public String getString(String nomePropriedade) {
		String retorno = null;
		
		if (usandoCache) {
			retorno = cache.get(nomePropriedade);
		}
		
		if (retorno == null && verificandoPropriedadesSistema) {
			retorno = System.getProperty(nomePropriedade);
		}
		
		if (retorno == null) {
			for (ResourceBundle bundle : bundles) {
				try {
					retorno = bundle.getString(nomePropriedade);
				} catch (MissingResourceException ex) {
				}
			}
		}
		
		if (retorno == null) {
			throw new MissingResourceException("", this.getClass().getName(), nomePropriedade);
			
		} else if (usandoCache) {
			cache.put(nomePropriedade, retorno);
		}
		
		return retorno;
	}
}
