package br.com.delphos.util.configuracoes;

import java.util.Arrays;
import java.util.List;

public class Propriedades {

	public static Propriedade porNome(String nomePropriedade, Object... args) {
		return new PropriedadeSimples(nomePropriedade, Arrays.asList(args));
	}

	public static Propriedade porNome(String nomePropriedade, List<Object> args) {
		return new PropriedadeSimples(nomePropriedade, args);
	}
	
	public static Propriedade porNome(String nomePropriedade, Conversor conversor, Object... args) {
		return new PropriedadeSimples(nomePropriedade, Arrays.asList(args), conversor);
	}
	
	public static Propriedade porNome(String nomePropriedade, Conversor conversor, List<Object> args) {
		return new PropriedadeSimples(nomePropriedade, args, conversor);
	}
	
	public static Propriedade copia(Propriedade propriedade, Object... args) {
		return new PropriedadeSimples(propriedade, Arrays.asList(args));
	}

	public static Propriedade copia(Propriedade propriedade, List<Object> args) {
		return new PropriedadeSimples(propriedade, args);
	}
}
