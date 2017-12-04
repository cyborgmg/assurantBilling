package br.com.delphos.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
 * Classe utilitária para o uso de algoritmos criptográficos.
 *
 * Exemplo de uso para hash:
 *
 * public static void main(String[] args) {
 *    try {
 *       System.out.println("HEX " + geraHash(Criptografia.MD5, "curso de java", true));
 *       System.out.println("B64 " + geraHash(Criptografia.MD5 , "curso de java", false));
 *    } catch (NoSuchAlgorithmException ex) {
 *       ex.printStackTrace();
 *    }
 * }
 */
public final class Criptografia {

    /**
     * Facilita o uso do tipo MD5 como parâmetro para geraHash.
     */
    public static final String MD5 = "MD5";
    /**
     * Facilita o uso do tipo SHA-1 como parâmetro para geraHash.
     */
    public static final String SHA1 = "SHA1";

    private Criptografia() {
        //construtor privado para que a classe não seja instanciada
    }

    /**
     * Gera o hash da string no padrão informado em hexadecimal.
     *
     * @param tipo
     * @param frase
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String geraHash(final String tipo, final String frase)
            throws NoSuchAlgorithmException {

        return geraHash(tipo, frase, true);
    }


    /**
     * Gera o hash da string no padrão informado.
     *
     * @param tipo
     * @param frase
     * @param hex se true retorna em hexa,
     *            senão retorna na base 64.
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String geraHash(final String tipo, final String frase,
            boolean hex) throws NoSuchAlgorithmException {

        String resultado = null;
        if (tipo != null && frase != null) {

            MessageDigest md = MessageDigest.getInstance(tipo);
            md.update(frase.getBytes());
            if(hex) {

                resultado = hexa(md.digest());
            } else {

                resultado = base64(md.digest()).trim();
            }
        }
        return resultado;
    }

    private static String hexa(final byte[] bytes) {

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {

            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {

                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    private static String base64(byte [] dados) {

       return new String(Base64.encodeBase64(dados));
    }
}
