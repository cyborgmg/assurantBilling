package br.com.delphos.util.configuracoes;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Resolvedores {

	private static final Resolvedor resolvedorGlobal = sincronizado(padrao());
	
	public static Resolvedor sincronizado(Resolvedor resolvedor) {
		return new ResolvedorSincronizado(resolvedor);
	}
	
	public static Resolvedor global() {
		return resolvedorGlobal;
	}
	
	public static Resolvedor padrao() {
		return paraBundle(true, "configuracoes");
	}
	
	public static Resolvedor paraBundle(boolean usandoCache, ResourceBundle... bundles) {
		ResolvedorSimples resolvedor = new ResolvedorSimples();
		resolvedor.setUsandoCache(usandoCache);
		resolvedor.addBundle(bundles);
		return resolvedor;
	}

	public static Resolvedor paraBundle(boolean usandoCache, String... nomesBundle) {
		ResolvedorSimples resolvedor = new ResolvedorSimples();
		resolvedor.setUsandoCache(usandoCache);
		for (int i = 0; i < nomesBundle.length; ++i) {
			String nomeBundle = nomesBundle[i];
			ResourceBundle bundle = ResourceBundles.getBundle(nomeBundle);
			resolvedor.addBundle(bundle);
		}
		return resolvedor;
	}

	public static Resolvedor paraBundle(boolean usandoCache, ResourceBundles.Chave... chavesBundle) {
		ResolvedorSimples resolvedor = new ResolvedorSimples();
		resolvedor.setUsandoCache(usandoCache);
		for (int i = 0; i < chavesBundle.length; ++i) {
			ResourceBundles.Chave chave = chavesBundle[i];
			ResourceBundle bundle = ResourceBundles.getBundle(chave);
			resolvedor.addBundle(bundle);
		}
		return resolvedor;
	}
	
	public static Resolvedor paraBundle(ResourceBundle... bundles) {
		ResolvedorSimples resolvedor = new ResolvedorSimples();
		resolvedor.addBundle(bundles);
		return resolvedor;
	}

	public static Resolvedor paraBundle(String... nomesBundle) {
		ResolvedorSimples resolvedor = new ResolvedorSimples();
		for (int i = 0; i < nomesBundle.length; ++i) {
			String nomeBundle = nomesBundle[i];
			ResourceBundle bundle = ResourceBundles.getBundle(nomeBundle);
			resolvedor.addBundle(bundle);
		}
		return resolvedor;
	}

	public static Resolvedor paraBundle(ResourceBundles.Chave... chavesBundle) {
		ResolvedorSimples resolvedor = new ResolvedorSimples();
		for (int i = 0; i < chavesBundle.length; ++i) {
			ResourceBundles.Chave chave = chavesBundle[i];
			ResourceBundle bundle = ResourceBundles.getBundle(chave);
			resolvedor.addBundle(bundle);
		}
		return resolvedor;
	}
	
	public static String getString(String nomePropriedade) {
		return global().getString(nomePropriedade);
	}
	
	public static String getStringOuPropriedade(String nomePropriedade) {
		String retorno;
		try {
			retorno = getString(nomePropriedade);
		} catch (MissingResourceException ex) {
			retorno = nomePropriedade;
		}
		return retorno;
	}
}
