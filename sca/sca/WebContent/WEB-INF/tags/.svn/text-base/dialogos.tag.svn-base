<%@tag description="controle e apresentacao dos quadros de dialogos" pageEncoding="windows-1252"%>

<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<logic:messagesPresent>
    <script type="text/javascript" >
        $(function(){                

            mostrarMensagem("<html:errors property="dialogoErro" />", "dialogoErro", 400);
        });
    </script>
</logic:messagesPresent>
<logic:messagesPresent message="true" property="dialogoUsuario">
    <script type="text/javascript" >
        var msgs = "";
        <html:messages id="msg" message="true">
            msgs += "<bean:write name="msg"/></p>";
        </html:messages>               
            $(function(){                
                mostrarMensagem(msgs, "dialogoUsuario", 400);
            });
    </script>
</logic:messagesPresent>
<logic:messagesPresent message="true" property="dialogoAlerta">
    <script type="text/javascript" >
        var msgs = "";
        <html:messages id="msg" message="true">
            msgs += "<bean:write name="msg"/></p>";
        </html:messages>               
            $(function(){                
                mostrarMensagem(msgs, "dialogoAlerta", 400);
            });
    </script>
</logic:messagesPresent>    
<logic:messagesPresent message="true" property="dialogoSucesso">
    <script type="text/javascript" >
        var msgs = "";
        <html:messages id="msg" message="true">
            msgs += " <bean:write name="msg"/></p>";
        </html:messages>               
            $(function(){                
                mostrarMensagem(msgs, "dialogoSucesso", 400);
            });
    </script>
</logic:messagesPresent>    

<logic:messagesPresent property="usuario">                                
    <script> 
        $(function(){ 
            $("input[name=usuario]").addClass("CampoVazio"); 
        });
    </script>
</logic:messagesPresent>

<logic:messagesPresent property="email">                                
    <script> 
        $(function(){ 
            $("input[name=email]").addClass("CampoVazio"); 
        });
    </script>
</logic:messagesPresent>

