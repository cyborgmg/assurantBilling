package br.com.delphos.util.configuracoes;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundles {

	public static class Chave {
		
		private final String nomeBundle;
		private final ResourceBundle bundle;
		private final Locale locale;
		
		public Chave(String nomeBundle, ResourceBundle bundle, Locale locale) {
			this.nomeBundle = nomeBundle;
			this.bundle = bundle;
			this.locale = locale;
		}

		public String getNomeBundle() {
			return nomeBundle;
		}
		
		public ResourceBundle getBundle() {
			return bundle;
		}
		
		public Locale getLocale() {
			return locale;
		}

		public String getCodigoIdioma() {
			return locale != null ? locale.getLanguage() : null;
		}
		
		public String getCodigoPais() {
			return locale != null ? locale.getCountry() : null;
		}
		
		public String getVariante() {
			return locale != null ? locale.getVariant() : null;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((bundle == null) ? 0 : bundle.hashCode());
			result = prime * result + ((locale == null) ? 0 : locale.hashCode());
			result = prime * result + ((nomeBundle == null) ? 0 : nomeBundle.hashCode());
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
			Chave other = (Chave) obj;
			if (bundle == null) {
				if (other.bundle != null)
					return false;
			} else if (!bundle.equals(other.bundle))
				return false;
			if (locale == null) {
				if (other.locale != null)
					return false;
			} else if (!locale.equals(other.locale))
				return false;
			if (nomeBundle == null) {
				if (other.nomeBundle != null)
					return false;
			} else if (!nomeBundle.equals(other.nomeBundle))
				return false;
			return true;
		}
		
	}
	
	public static Chave chave(String nomeBundle) {
		return new Chave(nomeBundle, null, null);
	}

	public static Chave chave(String nomeBundle, Locale locale) {
		return new Chave(nomeBundle, null, locale);
	}

	public static Chave chave(String nomeBundle, ResourceBundle bundle) {
		return new Chave(nomeBundle, bundle, null);
	}

	public static Chave chave(String nomeBundle, ResourceBundle bundle, Locale locale) {
		return new Chave(nomeBundle, bundle, locale);
	}
	
	public static ResourceBundle getBundle(String nomeBundle) {
		return getBundle(nomeBundle, Locale.getDefault());
	}
	
	public static ResourceBundle getBundle(String nomeBundle, Locale locale) {
		return ResourceBundle.getBundle(nomeBundle, locale);
	}
	
	public static ResourceBundle getBundle(Chave chave) {
		return ResourceBundle.getBundle(chave.getNomeBundle(), chave.getLocale());
	}
}
