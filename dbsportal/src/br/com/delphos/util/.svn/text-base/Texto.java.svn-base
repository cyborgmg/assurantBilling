package br.com.delphos.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;

import org.apache.commons.codec.binary.Base64;

import br.com.delphos.billing.util.Validador;

/**
 *
 */
public final class Texto {

    private Texto() {
    }

    /**
     * Preenche uma string até o tamanho desejado e com o caracter informado.
     *
     * Parâmetro de entrada: texto a ser complementado :: String tamanho :: int
     * caracter que deve ser utilizado para completar o texto :: String tipo de
     * preenchimento "I" Início // "F" Final :: String
     *
     * @param texto
     * @param tamanho
     * @param charPreencher
     * @param tipoPreenchimento
     * @return
     */
    public static String preencheString(String texto, int tamanho, String charPreencher, String tipoPreenchimento) {
        String retorno = "";
        int tamanhoPreencher = 0;
        String textoComplementar = "";

        if (tamanho > texto.length()) {
            tamanhoPreencher = tamanho - texto.length();
        } else {
            tamanhoPreencher = 0;
        }

        for (int i = 0; i < tamanhoPreencher; i++) {
            textoComplementar += charPreencher;
        }

        if ("I".equals(tipoPreenchimento)) {

            retorno = textoComplementar + texto;
        } else {
            retorno = texto + textoComplementar;
        }

        return retorno;
    }

    /**
     *
     * @param frase
     * @return
     */
    public static String tiraAcentos(String frase) {
        String retorno = frase;
        if (!Validador.vazio(frase)) {
            retorno = Normalizer.normalize(retorno, Normalizer.Form.NFD);
            retorno = retorno.replaceAll("[^\\p{ASCII}]", "");
        }
        return retorno;
    }

    public static String converteParaAsciiUnix(String frase) {

        String retorno = frase;
        if (!Validador.vazio(frase)) {

            retorno = tiraAcentos(retorno);
            retorno = retorno.replace("\\", "/");
            retorno = retorno.replace(" ", "_");
            retorno = retorno.replace(";", "_");
            retorno = retorno.replace("\'", "");
            retorno = retorno.replace("\"", "");
        }
        return retorno;
    }

    /**
     * Abrevia uma string até o tamanho desejado.
     *
     * Parâmetro de entrada: texto a ser complementado :: String tamanho :: int
     *
     * @param frase
     * @param tamanho
     * @return
     */
    public static String abrevia(String frase, int tamanho) {
        String retorno = null;
        if (!Validador.vazio(frase)) {
            if (tamanho < frase.length()) {
                retorno = frase.substring(0, tamanho - 3) + "...";
            } else {
                retorno = frase;
            }
        }
        return retorno;
    }

    /**
     *
     * @param b
     * @return
     * @throws Exception
     */
    public static byte[] toBase64(byte[] b) throws Exception {
        return Base64.encodeBase64(b, false);
    }

    /**
     *
     * @param b
     * @return
     * @throws Exception
     */
    public static byte[] fromBase64(byte[] b) throws Exception {
        return Base64.decodeBase64(b);
    }
    
    /**
 	* 
	* @param valor
	* @param mascara
	* @return
	* @throws ParseException
	*/
   public static BigDecimal moedaToDecimal(String valor, String mascara) throws ParseException {
       DecimalFormat df = new DecimalFormat(mascara);
       df.setParseBigDecimal(true);
       return (BigDecimal) df.parse(valor);
   }

   /**
	*
	* @param valor
	* @param mascara
	* @return
	*/
   public static String decimalToMoeda(BigDecimal valor, String mascara) {
       DecimalFormat df = new DecimalFormat(mascara);
       df.setParseBigDecimal(true);
       return df.format(valor);
   }

    /**
     *
     * @param valor
     * @param mascara
     * @return
     * @throws ParseException
     */
    public static double moedaToDouble(String valor, String mascara) throws ParseException {
        DecimalFormat df = new DecimalFormat(mascara);
        return df.parse(valor).doubleValue();
    }

    /**
     *
     * @param valor
     * @param mascara
     * @return
     */
    public static String doubleToMoeda(double valor, String mascara) {
        DecimalFormat df = new DecimalFormat(mascara);
        return df.format(valor);
    }

    /**
     *
     * @param valor
     * @param mascara
     * @return
     */
    public static String stringToMoeda(String valor, String mascara) {
        String retorno = null;
        if (Validador.real(valor)) {
            DecimalFormat df = new DecimalFormat(mascara);
            double d = Double.parseDouble(valor);
            retorno = df.format(d);
        }
        return retorno;
    }

    /**
     *
     * @param valor
     * @return
     */
    public static double moedaToDouble(String moeda) {
        double retorno = 0;
        if (!Validador.vazio(moeda)) {

            String novoValor = moeda.replace(".", "");
            novoValor = novoValor.replace(",", ".");
            if (Validador.real(novoValor)) {

                BigDecimal bd = new BigDecimal(novoValor);
                retorno = bd.doubleValue();
            }
        }
        return retorno;
    }

    /**
     *
     * @param nome
     * @return
     */
    public static String nomeProprio(String nome) {
        String[] conjunto = nome.toUpperCase().split(" ");
        String retorno = "";
        for (int i = 0; i < conjunto.length; i++) {
            String token = conjunto[i];
            String upper = " " + token.charAt(0);
            if (token.length() > 1) {
                retorno += upper + token.substring(1).toLowerCase();
            }
        }
        return retorno.trim();
    }

    public static String formataCnpj(String cnpj) {
        String cnpjFormatado = "";
        if (cnpj != null) {

            int tamanho = cnpj.length();
            if (tamanho < 14) {

                //completa com zeros a esquerda
                for (int i = 0; i < 14 - tamanho; i++) {

                    cnpj = "0" + cnpj;
                }
            }
            tamanho = 14;
            String caracter = "";
            for (int i = 0; i < tamanho; i++) {

                caracter = cnpj.substring(i, i + 1);
                if (i == 12) {
                    cnpjFormatado = cnpjFormatado + "-";
                }
                if (i == 8) {
                    cnpjFormatado = cnpjFormatado + "/";
                }
                if (i == 5) {
                    cnpjFormatado = cnpjFormatado + ".";
                }
                if (i == 2) {
                    cnpjFormatado = cnpjFormatado + ".";
                }
                cnpjFormatado = cnpjFormatado + caracter;
            }
        }
        return cnpjFormatado;
    }

    public static String formataCpf(String cpf) {

        String cpfFormatado = "";
        if (cpf != null) {
            int tamanho = cpf.length();
            if (tamanho < 11) {
                //completa com zeros a esquerda
                for (int i = 0; i
                        < 11 - tamanho; i++) {
                    cpf = "0" + cpf;
                }
            }
            tamanho = 11;
            String caracter = "";
            for (int i = 0; i
                    < tamanho; i++) {
                caracter = cpf.substring(i, i + 1);
                if (i == 9) {
                    cpfFormatado = cpfFormatado + "-";
                }
                if (i == 6) {
                    cpfFormatado = cpfFormatado + ".";
                }
                if (i == 3) {
                    cpfFormatado = cpfFormatado + ".";
                }
                cpfFormatado = cpfFormatado + caracter;
            }
        }
        return cpfFormatado;
    }

    public static String tiraMascaraCpf(String cpf) {

        String retorno = null;
        if (!Validador.vazio(cpf)) {

            retorno = cpf.replace(".", "");
            retorno = retorno.replace("-", "");
        }
        return retorno;
    }

    public static String tiraMascaraCep(String cep) {

        String retorno = null;
        if (!Validador.vazio(cep)) {

            retorno = cep.replace("-", "");
        }
        return retorno;
    }
}
