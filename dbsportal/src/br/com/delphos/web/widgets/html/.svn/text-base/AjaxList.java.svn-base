package br.com.delphos.web.widgets.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AjaxList extends SimpleTagSupport {

    private String url;
    private String width = "300";
    private String multiple = "false";
    private String formatItem;
    private String id = "delphos_ajax_list";
    private String keyValue = "delphos_ajax_list_value";
    private String selectedItemHandler = "delphos_selected_item_handler";
    private boolean writeFunctionSelectedItemHandler = true;

    public String getSelectedItemHandler() {
        return selectedItemHandler;
    }

    public void setSelectedItemHandler(String selectedItemHandler) {
        this.selectedItemHandler = selectedItemHandler;
        writeFunctionSelectedItemHandler = false;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getFormatItem() {
        return formatItem;
    }

    public void setFormatItem(String formatItem) {
        this.formatItem = formatItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        StringBuffer options = new StringBuffer();

        if( writeFunctionSelectedItemHandler )
            out.write("".format("<script>function delphos_selected_item_handler(li){if (li != null){$(\"#%s\").val(li.extra[0]);}}</script>", keyValue ));
        out.write("<script src=\"js/jquery.js\" type=\"text/javascript\"></script>");
        out.write("<script type=\"text/javascript\" src=\"js/jquery.autocomplete.js\"></script>");
        out.write("<script> $().ready(function() { ");
        out.write("".format("$(\"#%s\").autocomplete('%s', {%s});})</script>", id, url, "".format("onItemSelect:%s", getSelectedItemHandler())));
    }
}
