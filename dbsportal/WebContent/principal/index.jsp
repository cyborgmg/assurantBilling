<%
    //lista da pesquisa de usuarios
    session.removeAttribute("pesquisaSessao");
%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <awp:head/>

        <script type="text/javascript" >

            $(function(){
                
                $( ".tooltipTop" ).tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "top"
                });

                $( ".tooltipBottom" ).tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "bottom"
                });

            });

        </script>
    </head>
    <body>
        <div class="barras norte" >         
            <awp:topoTela />
        </div>

        <awp:menu />

        <div class="barras centro" >
            <div class="BoxModal" style="margin: 0 auto;">
                <br/><br/><br/><br/><br/>
                <center>
                    <img align="center" src="../img/logos/logo_assurant_principal_billing.jpg" width="50%" alt="Billing" />
                </center>                
                <br/><br/><br/><br/><br/>
            </div>

            <div class="BoxModal" style="width: 650px">
            </div>
        </div>
        <div class="barras sul" >
            <p><bean:message key="label.rodape"/></p>
        </div>
    </body>
</html>