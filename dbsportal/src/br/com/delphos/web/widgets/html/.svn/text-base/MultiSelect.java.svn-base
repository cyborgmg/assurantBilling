package br.com.delphos.web.widgets.html;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.delphos.util.Texto;

public class MultiSelect extends SimpleTagSupport {

   private String name;
   private List fromList;
   private String fromPropertyValue;
   private String fromPropertyText;
   private List toList;
   private String toPropertyValue;
   private String toPropertyText;
   private String limit;
   private String limitMark = "...";
   private String fromTitle;
   private String toTitle;
   private boolean doAbbreviate = false;
   private String DEFAULT_WIDTH = "20";
   private String width;
   private String DEFAULT_HEIGHT = "5";
   private String height;
   private String idFrom;
   private String idTo;
   private String readOnly;

   public String getIdFrom() {
      return idFrom;
   }

   public void setIdFrom(String idFrom) {
      this.idFrom = idFrom;
   }

   public String getIdTo() {
      return idTo;
   }

   public void setIdTo(String idTo) {
      this.idTo = idTo;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getHeight() {
      return height;
   }

   public void setHeight(String height) {
      try {
         Integer.parseInt(height);
         this.height = height;
      } catch (Exception e) {
         this.width = DEFAULT_HEIGHT;
      }
   }

   public String getWidth() {
      return width;
   }

   public void setWidth(String width) {
      try {
         Integer.parseInt(width);
         this.width = width;
      } catch (Exception e) {
         this.width = DEFAULT_WIDTH;
      }
   }

   public List getToList() {
      return toList;
   }

   public void setToList(List toList) {
      this.toList = toList;
   }

   public String getToPropertyText() {
      return toPropertyText;
   }

   public void setToPropertyText(String toPropertyText) {
      this.toPropertyText = toPropertyText;
   }

   public String getToPropertyValue() {
      return toPropertyValue;
   }

   public void setToPropertyValue(String toPropertyValue) {
      this.toPropertyValue = toPropertyValue;
   }

   public String getLimit() {
      return limit;
   }

   public void setLimit(String limit) {
      this.limit = limit;
      doAbbreviate = true;
   }

   public String getLimitMark() {
      return limitMark;
   }

   public void setLimitMark(String limitMark) {
      this.limitMark = limitMark;
   }

   public String getFromPropertyText() {
      return fromPropertyText;
   }

   public void setFromPropertyText(String fromPropertyText) {
      this.fromPropertyText = fromPropertyText;
   }

   public String getFromPropertyValue() {
      return fromPropertyValue;
   }

   public void setFromPropertyValue(String fromPropertyValue) {
      this.fromPropertyValue = fromPropertyValue;
   }

   public List getFromList() {
      return fromList;
   }

   public void setFromList(List fromList) {
      this.fromList = fromList;
   }

   public String getFromTitle() {
      return fromTitle;
   }

   public void setFromTitle(String fromTitle) {
      this.fromTitle = fromTitle;
   }

   public String getToTitle() {
      return toTitle;
   }

   public void setToTitle(String toTitle) {
      this.toTitle = toTitle;
   }

   @Override
   public void doTag() throws JspException, IOException {

      JspWriter out = getJspContext().getOut();

      try {
         String refresh = "document.forms[0]." + this.getName() + "sel1, document.forms[0]." + this.getName() + "sel2, document.forms[0]." + this.getName() + "_from, document.forms[0]." + this.getName() + "_to";
         out.write("		<script language=\"JavaScript\" type=\"text/javascript\">\n");
         out.write("		var NS4 = (navigator.appName == \"Netscape\" && parseInt(navigator.appVersion) < 5);\n");
         out.write("		function addOption(theSel, theText, theValue) {\n");
         out.write("		  var newOpt = new Option(theText, theValue);\n");
         out.write("		  var selLength = theSel.length;\n");
         out.write("		  theSel.options[selLength] = newOpt;\n");
         out.write("		}");
         out.write("		function deleteOption(theSel, theIndex) {\n");
         out.write("		  var selLength = theSel.length;\n");
         out.write("		  if(selLength>0)\n");
         out.write("		  {\n");
         out.write("			theSel.options[theIndex] = null;\n");
         out.write("		  }\n");
         out.write("		}\n");
         out.write("		function moveOptions(theSelFrom, theSelTo) {\n");
         out.write("		  var selLength = theSelFrom.length;\n");
         out.write("		  var selectedText = new Array();\n");
         out.write("		  var selectedValues = new Array();\n");
         out.write("		  var selectedCount = 0;\n");
         out.write("		  var i;\n");
         out.write("		  for(i=selLength-1; i>=0; i--) {\n");
         out.write("			if(theSelFrom.options[i].selected) {\n");
         out.write("			  selectedText[selectedCount] = theSelFrom.options[i].text;\n");
         out.write("			  selectedValues[selectedCount] = theSelFrom.options[i].value;\n");
         out.write("			  deleteOption(theSelFrom, i);\n");
         out.write("			  selectedCount++;\n");
         out.write("			}\n");
         out.write("		  }\n	");
         out.write("		  for(i=selectedCount-1; i>=0; i--) {\n");
         out.write("			addOption(theSelTo, selectedText[i], selectedValues[i]);\n");
         out.write("		  }\n");
         out.write("		  if(NS4) history.go(0);\n");
         out.write("		}\n");
         out.write("		function moveAll(theSelFrom, theSelTo) {\n");
         out.write("			var selLength = theSelFrom.length;\n");
         out.write("			var selectedText = new Array();\n");
         out.write("			var selectedValues = new Array();\n");
         out.write("			var selectedCount = 0;\n");
         out.write("			var i;\n");
         out.write("			for(i=selLength-1; i>=0; i--) {\n");
         out.write("			  selectedText[selectedCount] = theSelFrom.options[i].text;\n");
         out.write("			  selectedValues[selectedCount] = theSelFrom.options[i].value;\n");
         out.write("			  deleteOption(theSelFrom, i);\n");
         out.write("			  selectedCount++;\n");
         out.write("			}		  \n");
         out.write("			for(i=selectedCount-1; i>=0; i--) {\n");
         out.write("			addOption(theSelTo, selectedText[i], selectedValues[i]);\n");
         out.write("			}\n");
         out.write("			if(NS4) history.go(0);		\n");
         out.write("		}\n");
         out.write("		function refresh(lst1, lst2, txt1, txt2) {\n");
         out.write("			txt1.value = \"\";\n");
         out.write("			txt2.value = \"\";\n");
         out.write("			var i;\n");
         out.write("			for(i=0; i < lst1.options.length; i++) {\n");
         out.write("				txt1.value += lst1.options[i].value;\n");
         out.write("				if(i < lst1.options.length - 1) {\n");
         out.write("					txt1.value += \"^\";\n");
         out.write("				}\n");
         out.write("			}\n");
         out.write("			for(i=0; i < lst2.options.length; i++) {\n");
         out.write("				txt2.value += lst2.options[i].value;\n");
         out.write("				if(i < lst2.options.length - 1) {\n");
         out.write("					txt2.value += \"^\";\n");
         out.write("				}\n");
         out.write("			}\n");
         out.write("		}\n");
         out.write("		</script>\n");
         out.write("			<input type=\"hidden\" name=\"" + this.getName() + "_from\"><input type=\"hidden\" name=\"" + this.getName() + "_to\">\n");
         out.write("			<table border=\"0\">\n");

         if (this.readOnly == null || this.readOnly.equalsIgnoreCase("false")) {
            if (this.getFromTitle() != null && this.getToTitle() != null) {
               out.write("<tr><th>" + this.getFromTitle() + "</th><th>&nbsp;</th><th>" + this.getToTitle() + "</th></tr>\n");
            }
            out.write("				<tr>\n");
            out.write("					<td>\n");
            out.write(String.format("<select class=\"multiSelect\" id=\"" + idFrom + "\" name=\"" + this.getName() + "sel1\" multiple=\"multiple\" style=\"width:%s\" size=\"%s\">", width != null ? width : DEFAULT_WIDTH, height != null ? height : DEFAULT_HEIGHT));
            out.write(buildOptions(fromList, fromPropertyText, fromPropertyValue));
            out.write("						</select>\n");
            out.write("					</td>\n");
            out.write("					<td align=\"center\" valign=\"middle\">						\n");

            out.write("						<a href=\"#\" onclick=\"moveAll(document.forms[0]." + this.getName() + "sel2, document.forms[0]." + this.getName() + "sel1);refresh(" + refresh + ");\"><img src=\"../img/lista_esquerda_todo.png\"></a>\n");
            out.write("						&nbsp;&nbsp;");
            out.write("						<a href=\"#\" onclick=\"moveOptions(document.forms[0]." + this.getName() + "sel2, document.forms[0]." + this.getName() + "sel1);refresh(" + refresh + ");\"><img src=\"../img/lista_esquerda.png\"></a>\n");
            out.write("						&nbsp;&nbsp;						");
            out.write("						<a href=\"#\" onclick=\"moveOptions(document.forms[0]." + this.getName() + "sel1, document.forms[0]." + this.getName() + "sel2);refresh(" + refresh + ");\"><img src=\"../img/lista_direita.png\"></a>\n");
            out.write("						&nbsp;&nbsp;						");
            out.write("						<a href=\"#\" onclick=\"moveAll(document.forms[0]." + this.getName() + "sel1, document.forms[0]." + this.getName() + "sel2);refresh(" + refresh + ");\"><img src=\"../img/lista_direita_todo.png\"></a>\n");
         }
         out.write("					</td>\n");
         out.write("					<td>\n");
         out.write(String.format("<select class=\"multiSelect\" id=\"" + idTo + "\"  name=\"" + this.getName() + "sel2\" multiple=\"multiple\" style=\"width:%s\" size=\"%s\">", width != null ? width : DEFAULT_WIDTH, height != null ? height : DEFAULT_HEIGHT));
         if (toList != null) {
            out.write(buildOptions(toList, toPropertyText, toPropertyValue));
         }
         out.write("						</select>\n");
         out.write("					</td>\n");
         out.write("				</tr>\n");
         out.write("			</table>\n");
         out.write("       <script>refresh(" + refresh + ");</script>\n");
      } catch (Exception e) {
         throw new JspException(e);
      }
   }

   private String buildOptions(List list, String text, String value) throws NoSuchFieldException, IllegalAccessException {

      StringBuffer retorno = new StringBuffer();
      if (list != null) {

         for (Object object : list) {
            //retorno.append(String.format("<option  title =\"%s\" value=\"%s\">%s</option>", getValor(object, text), getValor(object, value), valor.length() > Integer.valueOf(width != null ? width : DEFAULT_WIDTH) ? valor.substring(0, Integer.valueOf(width != null ? width : DEFAULT_WIDTH) - getLimitMark().length()) + getLimitMark() : valor));
            if (text != null && value != null) {

               String texto = (String) getValor(object, text);
               int tamanho = this.width != null ? Integer.parseInt(this.width) : texto.length();
               String abrev = Texto.abrevia(texto, tamanho);
               long valor = (Long) getValor(object, value);
               retorno.append(String.format("<option  title =\"%s\" value=\"%s\">%s</option>", texto, valor, abrev));

            } else {

               int tamanho = this.width != null ? Integer.parseInt(this.width) : object.toString().length();
               String abrev = Texto.abrevia(object.toString(), tamanho);
               retorno.append(String.format("<option  title =\"%s\" value=\"%s\">%s</option>", object.toString(), object.toString(), abrev));
            }
         }
      }
      return retorno.toString();
   }

   private Object getValor(Object o, String prop) throws NoSuchFieldException, IllegalAccessException {

      Object retorno = null;
      if (o != null && prop != null) {
         Field field = o.getClass().getDeclaredField(prop);
         field.setAccessible(true);
         retorno = (Object) field.get(o);
      }
      return retorno;
   }

   /**
    * @return the readOnly
    */
   public String getReadOnly() {
      return readOnly;
   }

   /**
    * @param readOnly the readOnly to set
    */
   public void setReadOnly(String readOnly) {
      this.readOnly = readOnly;
   }
}
