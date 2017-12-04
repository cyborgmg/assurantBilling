package br.com.delphos.web.widgets.decoradores;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;
import org.displaytag.exception.ObjectLookupException;
import org.displaytag.model.HeaderCell;
import org.displaytag.model.Row;
import org.displaytag.model.TableModel;

import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.Filtro;

public class DelphosDecorator extends TableDecorator {

   private String pagina = null;
   private boolean filtro = true;

   public void setFiltro(boolean valor) {
      this.filtro = valor;
   }

   @Override
   protected Object evaluate(String propertyName) {
      return tableModel.getId();
   }

   public String getRadio() {
      Long id = 0L;
      String retorno = null;

      try {
         id = getObjectId();
         retorno = String.format("<input type=\"radio\" name=\"id\" id=\"id_%d\" value=\"%d\"", id, id);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return retorno;
   }

   public String getActionDelete() {
      Long id = 0L;
      String retorno = null;
      try {
         String path = getPageContext().getServletContext().getContextPath();
         id = getObjectId();
         retorno = String.format("<a href=\"#\" title=\"deletar\" onCLick=\"delphos_delete(%d)\" class=\"actions\"><img src=\"%s/img/delete.png\" border=0></a>", id, path);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return retorno;
   }

   public String getActionEdit() {
      Long id = 0L;
      String retorno = null;
      try {
         String path = getPageContext().getServletContext().getContextPath();
         id = getObjectId();
         retorno = String.format("<a href=\"#\" title=\"editar\" onCLick=\"delphos_edit(%d)\" class=\"actions\"><img  src=\"%s/img/edit.png\" border=0 ></a>", id, path);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return retorno;
   }

   public String getCheckbox() {
      Long id = 0L;
      String retorno = null;
      try {
         id = getObjectId();
         retorno = String.format("<input type=\"checkbox\" name=\"id\" id=\"id_%d\" value=\"%d\">", id, id);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return retorno;
   }

   private Long getObjectId() throws NoSuchFieldException, IllegalAccessException {
      Object o = getCurrentRowObject();
      Field field = o.getClass().getDeclaredField("id");
      field.setAccessible(true);
      return (Long) field.get(o);
   }

   //----- Filtros -----
   @Override
   public void init(PageContext pageContext, Object decorated, TableModel tableModel) {

      super.init(pageContext, decorated, tableModel);

      if (filtro) {

         HttpSession sessao = pageContext.getSession();
         HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
         //fundamental para o submit do filtro
         //this.pagina = request.getServletPath();
         this.pagina = request.getRequestURI();

         List<Filtro> filtros = (List<Filtro>) sessao.getAttribute("filtros");
         String local = (String) sessao.getAttribute("local");
         if (filtros == null || local == null || !local.equals(this.pagina)) {
            filtros = new ArrayList<Filtro>();
            sessao.setAttribute("filtros", filtros);
            sessao.setAttribute("local", this.pagina);
         }
         String queryString = request.getQueryString();
         queryString = queryString != null ? "?" + queryString : "";
         this.pagina += queryString;

         this.manterListaFiltros(request, filtros);

         try {
            String filtro = this.montarStringFiltro(tableModel, filtros);
            pageContext.getOut().write(filtro);
         } catch (IOException ex) {
            ex.printStackTrace(); 
         }

         if (filtros != null && filtros.size() > 0) {
            try {
               this.filtrarRegistros(filtros, this.tableModel.getRowListFull());
            } catch (ObjectLookupException ex) {
            }
         }
      }
   }

   private void manterListaFiltros(ServletRequest request, List<Filtro> filtros) {
      //obter os parâmetros de execução
      String operacao = request.getParameter("filtro_operacao");
      String campo = request.getParameter("filtro_campo");
      String criterio = request.getParameter("filtro_criterio");
      String parametro = request.getParameter("filtro_parametro");
      if (parametro == null) {
         parametro = "";
      }
      //validar os parâmetros
      if (!Validador.vazio(campo) && Validador.inteiro(criterio)) {
         Filtro filtro = new Filtro();
         filtro.setCampo(campo);
         filtro.setCriterio(Integer.parseInt(criterio));
         filtro.setParametro(parametro);
         if (operacao != null && operacao.equals("inserir")) {
            filtros.add(filtro);
         }
      }
      if (operacao != null && operacao.startsWith("remover_")) {
         for (int i = 0; i < filtros.size(); i++) {
            String remover = "remover_" + i;
            if (remover.equals(operacao)) {
               filtros.remove(i);
            }
         }
      }
   }

   private String montarStringFiltro(TableModel tableModel, List<Filtro> filtros) {
      String filtro = "";
      filtro += "<script>\n";
      filtro += "animatedcollapse.addDiv('Filtro', 'fade=1,speed=350,group=dph,hide=1,persist=1');\n";
      filtro += "animatedcollapse.ontoggle=function($, divobj, state){};\n";
      filtro += "animatedcollapse.init();\n";
      filtro += "function aplicarFiltro(operacao) {\n";
      filtro += "   var f=document.forms[0];\n";
      filtro += "   f.filtro_operacao.value=operacao;\n";
      filtro += "   f.action = \"" + this.pagina.substring(this.pagina.lastIndexOf("/") + 1) + "\";\nf.submit();\n}\n</script>\n";
      //filtro += "   f.action = \"" + this.pagina + "\";f.submit();}</script>";
      filtro += "<div id=\"Filtro\">\n<fieldset>\n<legend>Filtros</legend>\n";
      filtro += "<input type=\"hidden\" name=\"filtro_operacao\">\n";
      filtro += "<table border=\"0\">\n<tr><th>Coluna</th><th>Critério</th><th>Parâmetro</th><th>&nbsp;</th></tr>\n";
      filtro += "<tr><td>\n<select name=\"filtro_campo\">\n<option></option>\n";
      //lista de rótulos da displaytag
      List<HeaderCell> headers = tableModel.getHeaderCellList();
      for (HeaderCell hc : headers) {
         String titulo = hc.getTitle();
         String propriedade = hc.getBeanPropertyName();
         if (!Validador.vazio(titulo)) {
            filtro += "<option value=\"" + propriedade + "\">" + titulo + "</option>\n";
         }
      }
      filtro += "</select></td><td>\n";
      filtro += "<select name=\"filtro_criterio\"><option value=\"\"></option><option value=\"0\">Começa com</option>";
      filtro += "<option value=\"1\">Termina com</option><option value=\"2\">Contém</option><option value=\"3\">Não contém</option>";
      filtro += "<option value=\"4\">Igual a</option><option value=\"5\">Diferente de</option>";
      //filtro += "<option value=\"6\">Maior que</option>";
      //filtro += "<option value=\"7\">Menor que</option><option value=\"8\">Maior ou igual a</option><option value=\"9\">Menor ou igual a</option>";
      filtro += "</select></td><td>\n<input type=\"text\" name=\"filtro_parametro\" />\n</td><td>\n<input type=\"button\" onclick=\"aplicarFiltro('inserir')\" value=\"+\">\n</td>";
      filtro += "</tr>";
      //lista de filtros
      if (filtros == null || filtros.size() == 0) {
         filtro += "<tr><td colspan=\"4\">Não existem Filtros</td></tr>";
      } else {
         int i = 0;
         for (Filtro f : filtros) {
            for (HeaderCell hc : headers) {
               String titulo = hc.getTitle();
               String propriedade = hc.getBeanPropertyName();
               String parametro = f.getParametro().equals("") ? "<i>vazio</i>" : f.getParametro();
               if (f.getCampo().equals(propriedade)) {
                  filtro += "<tr><td>" + titulo + "</td>";
                  filtro += "<td>" + this.getCriterio(f.getCriterio()) + "</td>";
                  filtro += "<td>" + parametro + "</td>";
                  filtro += "<td><input type=\"button\" onclick=\"aplicarFiltro('remover_" + i + "')\" value=\"-\"></td></tr>";
                  i++;
                  break;
               }
            }
         }
      }
      filtro += "</table></fieldset></div>";
      return filtro;
   }

   private String getCriterio(int criterio) {
      String retorno = "";
      switch (criterio) {
         case Filtro.COMECA_COM:
            retorno = "Começa com";
            break;
         case Filtro.TERMINA_COM:
            retorno = "Termina com";
            break;
         case Filtro.CONTEM:
            retorno = "Contém";
            break;
         case Filtro.NAO_CONTEM:
            retorno = "Não contém";
            break;
         case Filtro.IGUAL_A:
            retorno = "Igual a";
            break;
         case Filtro.DIFERENTE_DE:
            retorno = "Diferente de";
            break;
         /*
         case Filtro.MAIOR_QUE:
         retorno = "Maior que";
         break;
         case Filtro.MENOR_QUE:
         retorno = "Menor que";
         break;
         case Filtro.MAIOR_OU_IGUAL_A:
         retorno = "Maior ou igual a";
         break;
         case Filtro.MENOR_OU_IGUAL_A:
         retorno = "Menor ou igual a";
         break;
          */
      }
      return retorno;
   }

   private void filtrarRegistros(List<Filtro> filtros, List<Row> todosRegistros) throws ObjectLookupException {
      Object[] params = null;
      for (Filtro f : filtros) {
         for (int i = 0; i < todosRegistros.size(); i++) {
            Row row = todosRegistros.get(i);
            Object object = row.getObject();
            this.initRow(object, 0, 0);

            Method[] realObjMethods = object.getClass().getDeclaredMethods();
            Method[] decoObjMethods = this.getClass().getDeclaredMethods();

            String metodo = "get" + f.getCampo().substring(0, 1).toUpperCase() + f.getCampo().substring(1);
            Method method = null;
            String conteudo = null;
            String tipo = "java.lang.String";
            try {
               //para os métodos do decorator
               for (int j = 0; j < decoObjMethods.length; j++) {
                  if (decoObjMethods[j].getName().equals(metodo)) {
                     method = decoObjMethods[j];
                     conteudo = (String) method.invoke(this, params);
                     if (conteudo == null) {
                        conteudo = "";
                     } else {
                        conteudo = conteudo.toUpperCase();
                     }
                     break;
                  }
               }
               //para os métodos reais
               if (conteudo == null || conteudo.length() == 0) {
                  for (int j = 0; j < realObjMethods.length; j++) {
                     if (realObjMethods[j].getName().equals(metodo)) {
                        method = realObjMethods[j];
                        Object retorno = method.invoke(object, params);
                        tipo = retorno.getClass().getName();
                        conteudo = (String) retorno;
                        if (conteudo == null) {
                           conteudo = "";
                        } else {
                           conteudo = conteudo.toUpperCase();
                        }
                        break;
                     }
                  }
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
            boolean fica = false;
            if (conteudo != null && conteudo.length() > 0) {
               String parametro = f.getParametro().toUpperCase();
               switch (f.getCriterio()) {
                  case Filtro.COMECA_COM:
                     if (conteudo.startsWith(parametro)) {
                        fica = true;
                     }
                     break;
                  case Filtro.TERMINA_COM:
                     if (conteudo.endsWith(parametro)) {
                        fica = true;
                     }
                     break;
                  case Filtro.CONTEM:
                     if (conteudo.indexOf(parametro) >= 0) {
                        fica = true;
                     }
                     break;
                  case Filtro.NAO_CONTEM:
                     if (conteudo.indexOf(parametro) == -1) {
                        fica = true;
                     }
                     break;
                  case Filtro.IGUAL_A:
                     if (conteudo.equals(parametro)) {
                        fica = true;
                     }
                     break;
                  case Filtro.DIFERENTE_DE:
                     if (!conteudo.equals(parametro)) {
                        fica = true;
                     }
                     break;
                  /*
                  case Filtro.MAIOR_QUE:

                  break;
                  case Filtro.MENOR_QUE:

                  break;
                  case Filtro.MAIOR_OU_IGUAL_A:

                  break;
                  case Filtro.MENOR_OU_IGUAL_A:

                  break;
                   */
               }
            }
            if (!fica) {
               todosRegistros.remove(i);
               i--;
            }
         }
      }
   }
}
