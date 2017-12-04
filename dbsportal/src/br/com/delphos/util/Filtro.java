package br.com.delphos.util;

/**
 * Classe gen�rica utilizada nas telas para filtrar registros.
 */
public class Filtro implements Comparable<Filtro> {

   /**
    * Refer�ncia ao crit�rio come�a com.
    */
   public static final int COMECA_COM = 0;
   /**
    * Refer�ncia ao crit�rio temrina com.
    */
   public static final int TERMINA_COM = 1;
   /**
    * Refer�ncia ao crit�rio cont�m.
    */
   public static final int CONTEM = 2;
   /**
    * Refer�ncia ao crit�rio n�o cont�m.
    */
   public static final int NAO_CONTEM = 3;
   /**
    * Refer�ncia ao crit�rio igual a.
    */
   public static final int IGUAL_A = 4;
   /**
    * Refer�ncia ao crit�rio diferente de.
    */
   public static final int DIFERENTE_DE = 5;
   /**
    * Refer�ncia ao crit�rio maior que.
    */
   public static final int MAIOR_QUE = 6;
   /**
    * Refer�ncia ao crit�rio menor que.
    */
   public static final int MENOR_QUE = 7;
   /**
    * Refer�ncia ao crit�rio maior ou igual a.
    */
   public static final int MAIOR_OU_IGUAL_A = 8;
   /**
    * Refer�ncia ao crit�rio menor ou igual a.
    */
   public static final int MENOR_OU_IGUAL_A = 9;
   private String campo;
   private int criterio;
   private String parametro;

   /**
    * Construtor default.
    */
   public Filtro() {

      //deixado propositalmente em branco.
   }

   /**
    * Construtor de inicializal��o de atributos.
    *
    * @param campo Nome do campo do JavaBean a ser filtrado.
    * @param criterio Crit�rio definido por uma das constantes relacionadas.
    * @param parametro Argumento do crit�rio.
    */
   public Filtro(String campo, int criterio, String parametro) {
      this.campo = campo;
      this.criterio = criterio;
      this.parametro = parametro;
   }

   /**
    * Retorna o nome do campo do JavaBean a ser filtrado.
    *
    * @return campo.
    */
   public String getCampo() {
      return campo;
   }

   /**
    * Altera o nome do campo do JavaBean a ser filtrado.
    *
    * @param novo nome de campo.
    */
   public void setCampo(String campo) {
      this.campo = campo;
   }

   /**
    * Retorna o crit�rio definido por uma das constantes relacionadas.
    *
    * @return crit�rio.
    */
   public int getCriterio() {
      return criterio;
   }

   /**
    * Altera o crit�rio definido por uma das constantes relacionadas.
    *
    * @param novo crit�rio (use as constantes).
    */
   public void setCriterio(int criterio) {
      this.criterio = criterio;
   }

   /**
    * Retorna o argumento do crit�rio.
    *
    * @return par�metro.
    */
   public String getParametro() {
      return parametro;
   }

   /**
    * Altera o argumento do crit�rio.
    *
    * @param novo par�metro.
    */
   public void setParametro(String parametro) {
      this.parametro = parametro;
   }

   /*
    * Implementa��o do crit�rio de ordena��o de filtros a ser utilizado quando
    * houver um agrupamento de filtros em uma cole��o.
    *
    * O crit�rio implementado � comparar primeiro o campo e depois o par�metro.
    *
    * @param filtro a ser comparado com o atual.
    */
   @Override
   public int compareTo(Filtro filtro) {
      int retorno = 0;
      if (filtro != null && this.getCampo() != null && filtro.getCampo() != null) {
         retorno = this.getCampo().compareTo(filtro.getCampo());
      }
      if (retorno == 0 && this.getParametro() != null && filtro.getParametro() != null) {
         retorno = this.getParametro().compareTo(filtro.getParametro());
      }

      if (retorno > 0) {
         retorno = 1;
      } else if (retorno < 0) {
         retorno = -1;
      }

      return retorno;
   }
}
