package br.com.delphos.util;

/**
 * Classe genérica utilizada nas telas para filtrar registros.
 */
public class Filtro implements Comparable<Filtro> {

   /**
    * Referência ao critério começa com.
    */
   public static final int COMECA_COM = 0;
   /**
    * Referência ao critério temrina com.
    */
   public static final int TERMINA_COM = 1;
   /**
    * Referência ao critério contém.
    */
   public static final int CONTEM = 2;
   /**
    * Referência ao critério não contém.
    */
   public static final int NAO_CONTEM = 3;
   /**
    * Referência ao critério igual a.
    */
   public static final int IGUAL_A = 4;
   /**
    * Referência ao critério diferente de.
    */
   public static final int DIFERENTE_DE = 5;
   /**
    * Referência ao critério maior que.
    */
   public static final int MAIOR_QUE = 6;
   /**
    * Referência ao critério menor que.
    */
   public static final int MENOR_QUE = 7;
   /**
    * Referência ao critério maior ou igual a.
    */
   public static final int MAIOR_OU_IGUAL_A = 8;
   /**
    * Referência ao critério menor ou igual a.
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
    * Construtor de inicializalção de atributos.
    *
    * @param campo Nome do campo do JavaBean a ser filtrado.
    * @param criterio Critério definido por uma das constantes relacionadas.
    * @param parametro Argumento do critério.
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
    * Retorna o critério definido por uma das constantes relacionadas.
    *
    * @return critério.
    */
   public int getCriterio() {
      return criterio;
   }

   /**
    * Altera o critério definido por uma das constantes relacionadas.
    *
    * @param novo critério (use as constantes).
    */
   public void setCriterio(int criterio) {
      this.criterio = criterio;
   }

   /**
    * Retorna o argumento do critério.
    *
    * @return parâmetro.
    */
   public String getParametro() {
      return parametro;
   }

   /**
    * Altera o argumento do critério.
    *
    * @param novo parâmetro.
    */
   public void setParametro(String parametro) {
      this.parametro = parametro;
   }

   /*
    * Implementação do critério de ordenação de filtros a ser utilizado quando
    * houver um agrupamento de filtros em uma coleção.
    *
    * O critério implementado é comparar primeiro o campo e depois o parâmetro.
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
