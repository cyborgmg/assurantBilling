package br.com.delphos.billing.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public final class GerarToken {

    public static final String CHAVE_CLIENTE = "AssurantBrasil";

    //IMPEDE QUE SE INSTANCIE A CLASSE
    private GerarToken() {
    }

    public static String encriptar(String chave) {

        String retorno = "";

        if (chave.trim().length() > 0) {

            try {
                
                Calendar calendar = new GregorianCalendar();
                int ano = calendar.get(Calendar.YEAR);
                int mes = calendar.get(Calendar.MONTH) + 1; //MES NO CALENDAR COMECA EM 0
                int dia = calendar.get(Calendar.DAY_OF_MONTH);

                String strBase = chave + ano + mes + dia;
                String strOriginal = " ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÃÕÑÂÊÎÔÛÄËÏÖÜÀÈÌÒÙabcdefghijklmnopqrstuvwxyz0123456789áéíóúãõñâêîôûäëïöüàèìòùÇç/";
                String strEncripto = "89áéíxyz01ìòùÇç67óúãÒÙabcvw23lmJKLM ABCD45õñâpqrstuêîÜÀÈÌôûäëïöüÃÕÑàèÏÖdeÉÍÔÛÄYZÁÓÚÂfgOPQ/XÊÎËINSTVWhijkGHUnEFoR";

                int posicao = 0;
                char caracter = '\n';

                for (int i = 0; i < strBase.length(); i++) {
                    posicao = strOriginal.indexOf(strBase.substring(i, i + 1));

                    if (posicao > 0) {

                        caracter = strEncripto.substring(posicao, posicao + 1).charAt(0);

                    } else {

                        caracter = strBase.substring(i, i + 1).charAt(0);
                    }

                    retorno = retorno + charParaAsc(caracter, dia);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return retorno;

    }

    public static void main(String[] args) {
    	System.out.println(GerarToken.encriptar(CHAVE_CLIENTE));
    }
    
    private static int charParaAsc(char caracter, int incremento) {
        return (int) caracter + incremento;
    }
}
