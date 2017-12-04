<%--
DELPHOS SERVIÇOS TÉCNICOS S/A
PROJETO == DPS ==
--%>

<%--
//Legenda
//@ ONDE_ESTOU
//# UTILIZA
//& ENTRADA
//$ SAIDA
--%>

<%-- BIBLIOTECAS DE TAGS --%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="delphos" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- IMPORTS DA PÁGINA --%>

<%-- CÓDIGO JAVA --%>

<%-- PÁGINA HTML --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

    <head>
        <awp:head/>
        <%-- =========================================================== --%>   

        <script type="text/javascript" >
            $(function(){
                
                // - Inicia Ajuda (Tooltip jQuery) --------------------------------------------
                $(".tooltipTop").tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "top"
                });                
                $(".tooltipBottom").tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "bottom"
                });
                
                // - Monta os botões jQuery --------------------------------------------------- 
                $(".btn").button();    
                
            });
        </script>
        <awp:dialogosLista/>     
    </head>

    <body>
        <div id="ttt"></div>
        <form action="esqueceuSenha.do" method="POST">

            <div class="barras norte" >
                <awp:topoTelaLogin/>
            </div>

            <div class="barras centro" >

                <div class="BoxModal" style="margin: 0 auto;"> 
                    <h2><bean:message key="label.esquecer.senha"/></h2>
                </div>
                <div class="BoxModal" style="margin: 0 auto;">                    
                    <div class="tabelas" >
                        <table >
                            <tbody>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.loginUsuario"/></label></td>
                                    <td>
                                        <input type="text" 
                                               name="usuario" 
                                               maxlength="30" 
                                               class="usuario"
                                               value="${EsqueceuSenhaActionForm.usuario}" />   
                                        <logic:messagesPresent property="usuario">
                                            <script> $(function(){ $("input[name=usuario]").addClass("CampoVazio"); }); </script>                                     
                                        </logic:messagesPresent>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.email"/></label></td>
                                    <td>
                                        <input type="text" 
                                               name="email" 
                                               maxlength="50" 
                                               class="email minuscula"
                                               value="${EsqueceuSenhaActionForm.email}" />
                                        <logic:messagesPresent property="email">
                                            <script> $(function(){ $("input[name=email]").addClass("CampoVazio"); }); </script>                                     
                                        </logic:messagesPresent>
                                    </td>
                                </tr>                           		
                            </tbody>
                        </table>
                        <div style="display:block; width:100%; margin-top:20px; text-align:right;" >
                            <input type="submit" value="Confirmar" class="btn" /> 
                            <input type="button" value="Cancelar" class="btn" onclick="redirecionar('../principal/acessar.do')" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="barras sul" >
                <p>DELPHOS - Tecnologia em Seguros</p>
            </div>

        </form>
    </body>
</html>