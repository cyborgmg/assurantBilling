package br.com.delphos.web.widgets.html;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import br.com.delphos.billing.util.Validador;

public class DropDown extends SimpleTagSupport {

   private List<Object> options;
   private String optionValue;
   private String optionText;
   private String value;
   private String name;
   private String readOnly;
   private String style;
   private String onblur;
   private String onchange;
   private String onclick;
   private String ondblclick;
   private String onfocus;
   private String onmousedown;
   private String onmousemove;
   private String onmouseout;
   private String onmouseover;
   private String onmouseup;
   private String onkeydown;
   private String onkeypress;
   private String onkeyup;
   private String id;
   private String valorInicial;
   private String descricaoInicial;
   private StringBuffer htmlAttributes = new StringBuffer();

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getOnblur() {
      return onblur;
   }

   public void setOnblur(String onblur) {
      this.onblur = onblur;
      htmlAttributes.append("".format(" onblur=\"%s\"", onblur));
   }

   public String getOnchange() {
      return onchange;
   }

   public void setOnchange(String onchange) {
      this.onchange = onchange;
      htmlAttributes.append("".format(" onchange=\"%s\"", onchange));
   }

   public String getOnclick() {
      return onclick;
   }

   public void setOnclick(String onclick) {
      this.onclick = onclick;
      htmlAttributes.append("".format(" onclick=\"%s\"", onclick));
   }

   public String getOndblclick() {
      return ondblclick;
   }

   public void setOndblclick(String ondblclick) {
      this.ondblclick = ondblclick;
      htmlAttributes.append("".format(" ondblclick=\"%s\"", ondblclick));
   }

   public String getOnfocus() {
      return onfocus;
   }

   public void setOnfocus(String onfocus) {
      this.onfocus = onfocus;
      htmlAttributes.append("".format(" onfocus=\"%s\"", onfocus));
   }

   public String getOnkeydown() {
      return onkeydown;
   }

   public void setOnkeydown(String onkeydown) {
      this.onkeydown = onkeydown;
      htmlAttributes.append("".format(" onkeydown=\"%s\"", onkeydown));
   }

   public String getOnkeypress() {
      return onkeypress;
   }

   public void setOnkeypress(String onkeypress) {
      this.onkeypress = onkeypress;
      htmlAttributes.append("".format(" onkeypress=\"%s\"", onkeypress));
   }

   public String getOnkeyup() {
      return onkeyup;
   }

   public void setOnkeyup(String onkeyup) {
      this.onkeyup = onkeyup;
      htmlAttributes.append("".format(" onkeyup=\"%s\"", onkeyup));
   }

   public String getOnmousedown() {
      return onmousedown;
   }

   public void setOnmousedown(String onmousedown) {
      this.onmousedown = onmousedown;
      htmlAttributes.append("".format(" onmousedown=\"%s\"", onmousedown));
   }

   public String getOnmousemove() {
      return onmousemove;
   }

   public void setOnmousemove(String onmousemove) {
      this.onmousemove = onmousemove;
      htmlAttributes.append("".format(" onmousemove=\"%s\"", onmousemove));
   }

   public String getOnmouseout() {
      return onmouseout;
   }

   public void setOnmouseout(String onmouseout) {
      this.onmouseout = onmouseout;
      htmlAttributes.append("".format(" onmouseout=\"%s\"", onmouseout));
   }

   public String getOnmouseover() {
      return onmouseover;
   }

   public void setOnmouseover(String onmouseover) {
      this.onmouseover = onmouseover;
      htmlAttributes.append("".format(" onmouseover=\"%s\"", onmouseover));
   }

   public String getOnmouseup() {
      return onmouseup;
   }

   public void setOnmouseup(String onmouseup) {
      this.onmouseup = onmouseup;
      htmlAttributes.append("".format(" onmouseup=\"%s\"", onmouseup));
   }

   public String getStyle() {
      return style;
   }

   public void setStyle(String style) {
      this.style = style;
      htmlAttributes.append("".format(" style=\"%s\"", style));
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setOptionText(String optionText) {
      this.optionText = optionText;
   }

   public void setOptionValue(String optionValue) {
      this.optionValue = optionValue;
   }

   public void setOptions(List<Object> options) {
      this.options = options;
   }

   public String getOptionText() {
      return optionText;
   }

   public String getOptionValue() {
      return optionValue;
   }

   public List<Object> getOptions() {
      return options;
   }

   @SuppressWarnings("static-access")
   public void doTag() throws JspException {

      PageContext ctx = (PageContext) this.getJspContext();
      JspWriter out = getJspContext().getOut();

      try {

         out.write("".format("<select name=\"%s\" id=\"%s\" %s>", this.name, this.id, this.htmlAttributes.toString()));

         String strValorInicial = this.valorInicial;
         String strDescricaoInicial = this.descricaoInicial;

         if (strValorInicial == null && strDescricaoInicial == null) {
            out.write("<option value=\"\">- Selecione -</option>");
         } else {
            out.write("<option value=\"" + strValorInicial + "\">" + strDescricaoInicial + "</option>");
         }

         String paramValue = ctx.getRequest().getParameter(this.name);
         String selection = value != null ? value : paramValue;
         if (this.options != null) {

            for (Object object : options) {
               if (selection != null && selection.equals(getValor(object, optionValue).toString())) {
                  out.write("".format("<option selected value=\"%s\">%s</option>", getValor(object, optionValue), getValor(object, optionText)));
               } else {
                  if (this.readOnly == null || !this.readOnly.equalsIgnoreCase("true")) {
                     out.write("".format("<option value=\"%s\">%s</option>", getValor(object, optionValue), getValor(object, optionText)));
                  }
               }
            }
         }
         out.write("</select>");

      } catch (Exception ex) {
         throw new JspException("Error in DropDown tag", ex);
      }
   }

   private Object getValor(Object o, String prop) {

      Object retorno = " - ";
      try {
         
         if (o != null && !Validador.vazio(prop)) {

            Field field = o.getClass().getDeclaredField(prop);
            field.setAccessible(true);
            Object object = (Object) field.get(o);
            if (object != null) {

               retorno = object;
            }
         }
      } catch (Exception e) {
      }
      return retorno;

   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getReadOnly() {
      return readOnly;
   }

   public void setReadOnly(String readOnly) {
      this.readOnly = readOnly;
   }

   /**
    * @return the valorInicial
    */
   public String getValorInicial() {
      return valorInicial;
   }

   /**
    * @param valorInicial the valorInicial to set
    */
   public void setValorInicial(String valorInicial) {
      this.valorInicial = valorInicial;
   }

   /**
    * @return the descricaoInicial
    */
   public String getDescricaoInicial() {
      return descricaoInicial;
   }

   /**
    * @param descricaoInicial the descricaoInicial to set
    */
   public void setDescricaoInicial(String descricaoInicial) {
      this.descricaoInicial = descricaoInicial;
   }
}
