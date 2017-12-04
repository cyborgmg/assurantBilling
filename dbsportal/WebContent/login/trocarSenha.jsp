<%--
DELPHOS SERVIÇOS TÉCNICOS S/A
PROJETO == DPS ==
--%>

<%@page import="br.com.delphos.web.usuarios.AlterarSenhaViewHelper"%>
<%@page import="br.com.delphos.web.usuarios.SenhaUsuarioActionForm"%>
<%@page import="br.com.delphos.billing.util.Validador"%>
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
<jsp:useBean id="AlterarSenhaVH" class="br.com.delphos.web.usuarios.AlterarSenhaViewHelper" scope="page" />

<c:if test="${not empty corporativo_uid && empty SenhaUsuarioActionForm}" >
    <c:set var="SenhaUsuarioActionForm" scope="request" value="${AlterarSenhaVH.senhaUsuarioActionForm}" />
</c:if>

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
            });      
            
            function gravar() {
                
                document.form.submit();
            }
        </script>
        <awp:dialogosLista />
    </head>

    <body>         
        <form action="alterarSenha.do" name="form" method="POST">

            <div class="barras norte" >
                <awp:topoTela />
            </div>

            <awp:menu />

            <div class="barras centro" >

                <div class="BoxModal" style="margin: 0 auto;"> 
                    <h2><bean:message key="label.alterar.senha"/></h2>
                </div>
                <div class="BoxModal" style="margin: 0 auto; width: 650px">                    
                    <div class="tabelas" >
                        <table >
                            <tbody>
                                <%-- USUÁRIO ================================================= --%>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.loginUsuario"/></label></td>
                                    <td>
                                        <input type="text" 
                                               name="usuario" 
                                               maxlength="50" size="35" 
                                               value="${corporativo_usuario}" 
                                               class="CampoBloqueado senha" 
                                               readonly />
                                        <span class="IconeObs tooltipTop" title="Campo Bloqueado." >
                                            <span class="Icone16x16 IconeCampoBloqueado" ></span>                     
                                        </span> 
                                    </td>
                                </tr>
                                <%-- SENHA ATUAL ============================================= --%>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha.atual"/></label></td>
                                    <td>
                                        <input type="password" 
                                               name="senha" 
                                               maxlength="50"
                                               size="35" 
                                               class="senha" />
                                        <logic:messagesPresent property="senha">
                                            <script> $(function(){ $("input[name=senha]").addClass("CampoVazio"); }); </script>
                                        </logic:messagesPresent>
                                    </td>
                                </tr>  
                                <%-- SENHA NOVA ============================================== --%>                                
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha.nova"/></label></td>
                                    <td>
                                        <input type="password" name="senhaNova" maxlength="50" size="35" class="senha" />
                                        <logic:messagesPresent property="senhaNova">
                                            <script> $(function(){ $("input[name=senhaNova]").addClass("CampoVazio"); }); </script>
                                        </logic:messagesPresent>
                                    </td>
                                </tr>
                                <%-- CONFIRMAR NOVA SENHA ==================================== --%>
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha.confirmar"/></label></td>
                                    <td>
                                        <input type="password" name="senhaConfirmacao" maxlength="50" size="35" class="senha" />
                                        <logic:messagesPresent property="senhaConfirmacao">
                                            <script> $(function(){ $("input[name=senhaConfirmacao]").addClass("CampoVazio"); }); </script>
                                        </logic:messagesPresent>
                                    </td>
                                </tr>                         		
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="barras" >  
                <table class="navegacao" >
                    <tr>
                        <td class="botoesRetornar" >
                            <a href="../principal/index.jsp" >
                                <span class="Icone22x22 IconeNavegacaoRetornar" ></span>
                                <bean:message key="label.botao.retornar"/><br> &nbsp;
                            </a>
                        </td>
                        <td class="botoesCentro" >&nbsp;</td>
                        <td class="botoesAvancar" >
                            <a href="javascript:gravar()" >
                                <span class="Icone22x22 IconeConfirmar" ></span>
                                <bean:message key="label.botao.gravar"/><br> &nbsp;
                            </a>
                        </td>
                    </tr>
                </table>
            </div>

            <div class="barras sul" >
                <p>DELPHOS - Tecnologia em Seguros</p>
            </div>

        </form>
    </body>
</html>