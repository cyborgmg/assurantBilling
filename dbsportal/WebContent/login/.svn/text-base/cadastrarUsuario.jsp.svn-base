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
                
                // - Monta os botões jQuery --------------------------------------------------- 
                $(".btn").button();
                
                // - Inicia Ajuda (Tooltip jQuery) --------------------------------------------
                $( ".tooltipTop" ).tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "top"
                });
                
            });                 
        </script>
        <awp:dialogosLista />
    </head>

    <body>
        <form action="cadastrar.do" method="post" >

            <div class="barras norte" >
                <table class="topo" >
                    <tr>
                        <td rowspan="2" >                       
                            <img src="../img/logos/logo_billing.png" alt="Assurant" />
                        </td>
                        <td class="botoes" >                        
                            <a href="../ajuda/Guia_Usuario_1050.0257.0102.pdf" class="tooltipBottom" title="Ajuda" target="_blank" >
                                <span class="Icone16x16 IconeAjuda" ></span>                     
                            </a> 
                        </td>
                    </tr>
                    <tr>              
                        <td class="versao"><bean:message key="label.versao"/></td>
                    </tr>
                </table>
            </div>

            <div class="barras centro" >

                <div class="BoxModal" style="margin: 0 auto;"> 
                    <h2><bean:message key="label.cadastro.usuario"/></h2>
                </div>

                <div class="BoxModal" style="margin: 0 auto;">                    
                    <div class="tabelas" >
                        <table>
                            <tbody>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.nome.completo"/></label></td>
                                    <td>
                                        <input type="text" 
                                               name="nome" 
                                               maxlength="80" 
                                               value="${UsuarioActionForm.nome}" 
                                               class="nome"  />
                                        <logic:messagesPresent property="nome">
                                            <script> $(function(){ $("input[name=nome]").addClass("CampoVazio"); }); </script>
                                        </logic:messagesPresent>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.identificacao"/></label></td>
                                    <td>
                                        <input type="text" 
                                               name="matricula" 
                                               maxlength="7" 
                                               value="${UsuarioActionForm.matricula}" 
                                               class="senha maiuscula"  />

                                        <logic:messagesPresent property="matricula">
                                            <script> $(function(){ $("input[name=matricula]").addClass("CampoVazio"); }); </script>                                     
                                        </logic:messagesPresent>
                                    </td>
                                </tr>                                                              		
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha"/></label></td>
                                    <td>
                                        <input type="password" 
                                               name="senha" 
                                               maxlength="30" 
                                               class="senha" />
                                        <logic:messagesPresent property="senha">
                                            <script> $(function(){ $("input[name=senha]").addClass("CampoVazio"); }); </script>                                    
                                        </logic:messagesPresent>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha.confirmar"/></label></td>
                                    <td>
                                        <input type="password" 
                                               name="senhaConfirmacao" 
                                               maxlength="30" 
                                               class="senha" />
                                        <logic:messagesPresent property="senhaConfirmacao">
                                            <script> $(function(){ $("input[name=senhaConfirmacao]").addClass("CampoVazio"); }); </script>                                    
                                        </logic:messagesPresent>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.email"/></label></td>
                                    <td>
                                        <input type="text" 
                                               name="email" 
                                               maxlength="50" 
                                               class="nome minuscula" 
                                               value="${UsuarioActionForm.email}" />
                                        <logic:messagesPresent property="email">
                                            <script> $(function(){ $("input[name=email]").addClass("CampoVazio"); }); </script>                                    
                                        </logic:messagesPresent>
                                    </td>
                                </tr>                           
                            </tbody>
                        </table>
                        <div style="display:block; width:100%; margin-top:20px; text-align:right;" >
                            <input type="submit" value="Cadastrar" class="btn" /> 
                            <input type="button" value="Cancelar" class="btn" onclick="redirecionar('login.jsp')" />
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