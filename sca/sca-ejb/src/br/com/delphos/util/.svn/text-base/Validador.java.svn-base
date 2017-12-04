package br.com.delphos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Validador {

	/**
	 * Testa se uma String é nula ou está vazia.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se estiver vazio.
	 */
	public static boolean vazio(final String campo) {

		return campo == null || campo.length() == 0;
	}

	/**
	 * Realiza a validacao do CPF e indica-o se e valido.
	 *
	 * @param cpf
	 * @return True se o CPF e valido.
	 */
	public static boolean cpf(String cpf) {

		boolean retorno = false;
		if (!Validador.vazio(cpf)) {

			int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
			if (Validador.vazio(cpf) || !Validador.maxChars(cpf, 11)) {

				return false;
			}
			String nCpf = preencherDireita(cpf, '0', 11);
			Integer digito1 = calcularDigito(nCpf.substring(0, 9), pesoCPF);
			Integer digito2 = calcularDigito(nCpf.substring(0, 9) + digito1, pesoCPF);
			retorno = nCpf.equals(nCpf.substring(0, 9) + digito1.toString() + digito2.toString());
		}
		return retorno;
	}

	/**
	 *
	 * @param string
	 * @param preenche
	 * @param tamanho
	 * @return
	 */
	public static String preencherDireita(String string, char preenche, int tamanho) {

		char[] array = new char[tamanho];
		int len = tamanho - string.length();
		for (int i = 0; i < len; i++) {
			array[i] = preenche;
		}
		string.getChars(0, string.length(), array, tamanho - string.length());
		return String.valueOf(array);
	}

	private static int calcularDigito(String string, int[] peso) {

		int soma = 0;
		if (!Validador.vazio(string) && peso != null) {

			for (int indice = string.length() - 1, digito; indice >= 0; indice--) {

				digito = Integer.parseInt(string.substring(indice, indice + 1));
				soma += digito * peso[peso.length - string.length() + indice];
			}
			soma = 11 - soma % 11;
		}
		return soma > 9 ? 0 : soma;
	}

	/**
	 * Testa se a String tem pelo menos a quandidade de bytes informada.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @param qtde
	 *            Quantidade de bytes que se espera encontrar no campo.
	 * @return true se a String tiver pelo menos a quantidade de bytes
	 *         informada.
	 */
	public static boolean minChars(final String campo, final int qtde) {

		return !Validador.vazio(campo) && campo.length() >= qtde;
	}

	/**
	 * Testa se a String não excede a quandidade de bytes informada.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @param qtde
	 *            Quantidade de bytes limite que o campo pode ter.
	 * @return true se a String tiver menos bytes que a quantidade informada.
	 */
	public static boolean maxChars(final String campo, final int qtde) {

		return !Validador.vazio(campo) && campo.length() <= qtde;
	}

	/**
	 * Testa se uma String contém um email válido.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se o conteúdo da String contiver um email válido.
	 */
	public static boolean email(final String campo) {

		String regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/="
				+ "?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
		return !Validador.vazio(campo) && campo.matches(regexp);
	}

	/**
	 * Testa se uma String contém um número inteiro.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se o conteúdo da String for um número inteiro.
	 */
	public static boolean inteiro(final String campo) {

		return !Validador.vazio(campo) && campo.matches("[0-9]*");
	}

	/**
	 * Verifica se o campo possui um formato de data válida. E necessario
	 * passar a mascara a ser validado.
	 *
	 * @param campo
	 *            String a ser validado.
	 * @param mascara
	 *            Formato a ser verificado.
	 * @return True ou False.
	 */
	public static boolean data(final String campo, final String mascara) {

		boolean retorno = false;
		if (!Validador.vazio(campo) && !Validador.vazio(mascara)) {

			try {

				SimpleDateFormat sdf = new SimpleDateFormat(mascara);
				sdf.setLenient(false);
				Date data = sdf.parse(campo);
				if (data != null) {

					retorno = true;
				}
			} catch (Exception e) {
				// Possível ParseException
			}
		}
		return retorno;
	}

	/**
	 * Formata campo do tipo string para date.
	 *
	 * @param data
	 * @param mascara
	 *            Mascara a ser usada para formataÃ§Ã£o do campo.
	 * @return Date com a data formatada.
	 * @throws ParseException
	 */
	public static Date obterData(final String data, final String mascara) throws ParseException {

		Date retorno = null;
		if (data != null && !Validador.vazio(mascara)) {

			SimpleDateFormat sdf = new SimpleDateFormat(mascara);
			retorno = sdf.parse(data);
		}
		return retorno;
	}

	public static boolean trueString(String mensagem) {
		String regex = "([tT][rR][uU][eE]|[yY][eE][sS]|[oO][nN])";
		return !Validador.vazio(mensagem) && mensagem.matches(regex);
	}

	public static boolean falseString(String mensagem) {
		String regex = "([fF][aA][lL][sS][eE]|[nN][oO]|[oO][fF][fF])";
		return !Validador.vazio(mensagem) && mensagem.matches(regex);
	}
}