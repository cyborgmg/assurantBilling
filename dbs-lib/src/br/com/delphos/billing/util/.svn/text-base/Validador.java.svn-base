package br.com.delphos.billing.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe de validaÁ„o de dados.
 */
public final class Validador {

	/**
	 * Testa se uma String È nula ou est· vazia.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se estiver vazio.
	 */
	public static boolean vazio(final String campo) {

		return campo == null || campo.length() == 0;
	}
	
	/**
	 * Testa se uma String apÛs a retirada de espaÁos em branco È nula ou est· vazia.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se estiver vazio.
	 */
	public static boolean vazioComTrim(final String campo) {
		
//		return campo == null || campo.trim().length() == 0;
		return campo == null || campo.trim().length() == 0;
	}

    /**
     * Testa se uma String contÈm um n˙mero real.
     *
     * @param campo String a ser testada.
     * @return true se o conte˙do da String for um n˙mero real.
     */
    public static boolean real(final String campo) {

        return !Validador.vazio(campo) && campo.matches("[0-9]*\\.[0-9]*");
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
			Integer digito2 = calcularDigito(nCpf.substring(0, 9) + digito1,
					pesoCPF);
			retorno = nCpf.equals(nCpf.substring(0, 9) + digito1.toString()
					+ digito2.toString());
		}
		return retorno;
	}

	/**
	 * Verifica se o nome passado se adequa aos requisitos do projeto BILLING.
	 * 
	 * @param nome
	 * @return
	 */
	public static boolean nomeCliente(String nome) {
		boolean retorno = false;
		if (!Validador.vazio(nome)) {
			retorno = Validador.maxChars(nome, 255);
		}
		return retorno;
	}

	/**
	 * Verifica se o nome passado se adequa aos requisitos do projeto BILLING.
	 * 
	 * @param nome
	 * @return
	 */
	public static boolean nomePortadorCartao(String nome) {
		boolean retorno = false;
		if (!Validador.vazio(nome)) {
			retorno = Validador.maxChars(nome, 50);
		}
		return retorno;
	}

	public static boolean numeroCartaoCredito(String campo) {
		return !Validador.vazio(campo) && Validador.inteiro(campo)
				&& campo.length() >= 4 && campo.length() <= 30;
	}

	public static boolean dataVencimentoCartaoCredito(String campo) {
		String regex = "\\d{2}/\\d{4}";
		return !Validador.vazio(campo) && campo.matches(regex) && Validador.data(campo, "MM/yyyy");
	}

	/**
	 * Verifica se o DDD passado se adequa aos requisitos do projeto BILLING.
	 * 
	 * @param nome
	 * @return
	 */
	public static boolean ddd(String campo) {
		return !Validador.vazio(campo) && campo.length() == 2
				&& Validador.inteiro(campo);
	}

	/**
	 * Verifica se o telefone passado se adequa aos requisitos do projeto
	 * BILLING.
	 * 
	 * @param nome
	 * @return
	 */
	public static boolean telefone(String campo) {
		return !Validador.vazio(campo) && campo.length() >= 8
				&& campo.length() <= 9 && Validador.inteiro(campo);
	}

	public static boolean dataPadrao(String campo) {
		String regex = "\\d{2}/\\d{2}/\\d{4}";
		return !Validador.vazio(campo) && campo.matches(regex) && Validador.data(campo, "dd/MM/yyyy");
	}

	/**
	 *
	 * @param string
	 * @param preenche
	 * @param tamanho
	 * @return
	 */
	public static String preencherDireita(String string, char preenche,
			int tamanho) {

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
	 * Testa se a String n√£o excede a quandidade de bytes informada.
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
	 * Testa se uma String contÈm um email v·lido e È do tamanho esperado para o
	 * projeto BILLING.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se o conte˙do da String contiver um email v·lido.
	 */
	public static boolean email(final String campo) {

		String regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/="
				+ "?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
				+ "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
		return !Validador.vazio(campo) && campo.matches(regexp);
	}
	
	public static boolean emailCliente(String campo) {
		return !Validador.vazio(campo) && Validador.maxChars(campo, 255) && email(campo);
	}

	/**
	 * Testa se uma String contÈm um n˙mero inteiro.
	 *
	 * @param campo
	 *            String a ser testada.
	 * @return true se o conte˙do da String for um n˙mero inteiro.
	 */
	public static boolean inteiro(final String campo) {

		return !Validador.vazio(campo) && campo.matches("[0-9]*");
	}
	
	public static Integer integerOuNulo(final String campo) {
		Integer retorno = null;
		try {
			retorno = Integer.valueOf(campo);
		} catch (Exception ex) {
		}
		return retorno;
	}
	
	public static BigDecimal bigDecimalOuNulo(final String campo) {
		BigDecimal retorno = null;
		try {
			retorno = new BigDecimal(campo);
		} catch (Exception ex) {
		}
		return retorno;
	}
	
	/**
	 * Verifica se o campo possui um formato de data v·lida. E necessario passar
	 * a mascara a ser validado.
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
				// Poss√≠vel ParseException
			}
		}
		return retorno;
	}

	/**
	 * Formata campo do tipo string para date.
	 *
	 * @param data
	 * @param mascara
	 *            Mascara a ser usada para formataÁ„o do campo.
	 * @return Date com a data formatada.
	 * @throws ParseException
	 */
	public static Date obterData(final String data, final String mascara)
			throws ParseException {

		Date retorno = null;
		if (data != null && !Validador.vazio(mascara)) {

			SimpleDateFormat sdf = new SimpleDateFormat(mascara);
			retorno = sdf.parse(data);
		}
		return retorno;
	}

	public static Date obterDataPadrao(String data) throws ParseException {
		return obterData(data, "dd/MM/yyyy");
	}

	public static Date obterDataVencimentoCartaoCredito(String data, Calendar calendario) throws ParseException {
		Date dataPreliminar = obterData("01/" + data, "dd/MM/yyyy");
		calendario.setTime(dataPreliminar);
		calendario.set(Calendar.DAY_OF_MONTH, calendario.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendario.getTime();
	}
	
	public static boolean cvvCartaoCredito(String campo) {
		return !Validador.vazio(campo) && Validador.inteiro(campo)
				&& campo.length() >= 1 && campo.length() <= 4;
	}

	public static boolean valorCobrancaComoInteiro(String campo) {
		return !Validador.vazio(campo) && Validador.inteiro(campo)
				&& Validador.minChars(campo, 3)
				&& Validador.maxChars(campo, 14) && Long.parseLong(campo) >= 100;
	}

	public static boolean valorEstornoComoDecimal(String campo) {
		return !Validador.vazio(campo)
				&& Validador.minChars(campo, 1)
				&& Validador.maxChars(campo, 15)
				&& campo.matches("(0{1,10}|[+-]?[0-9][0-9]{0,9})(\\.(0{1,2}|[0-9][0-9]{0,1}))?");
	}
	
	public static boolean valorEstornoComoInteiro(String campo) {
		boolean retorno = !Validador.vazio(campo) && Validador.inteiro(campo)
				&& Validador.minChars(campo, 1)
				&& Validador.maxChars(campo, 13);
		return retorno;
	}
	
	public static String obterUltimosDigitosCartao(String numeroCartao) {
		String retorno = null;
		if (Validador.numeroCartaoCredito(numeroCartao)) {
			retorno = numeroCartao.substring(numeroCartao.length() - 4, numeroCartao.length());
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