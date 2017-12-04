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
            });           
        </script>
        <awp:dialogosLista />
    </head>

    <body>
        <form>
            <div class="barras norte" >
                <awp:topoTelaLogin/>
            </div>

            <div class="barras centro" >

                <div class="BoxModal" style="margin: 0 auto;">
                	<h2><bean:message key="${ReiniciarSenhaActionForm.chaveTitulo}"/></h2> 
                </div>
                <div class="BoxModal" style="margin: 0 auto;">                    
                    <div class="tabelas" >
                        <input type="hidden" name="origem" value="${ReiniciarSenhaActionForm.origem}"/>
                        <input type="hidden" name="id" value="${ReiniciarSenhaActionForm.id}"/>
                        <input type="hidden" name="token" value="${ReiniciarSenhaActionForm.token}"/>
                        <table >
                            <tbody>                            
                                <tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.loginUsuario"/></label></td>
                                    <td style="padding-top: 8px; padding-bottom: 8px;">                                        
                                        <input type="hidden" name="usuario" value="${ReiniciarSenhaActionForm.usuario}"/>
                                        <label class="labelReadOnly">${ReiniciarSenhaActionForm.usuario}</label>                                        
                                    </td>
                                </tr><tr>   
                                    <td class="label ui-widget-header" ><label><bean:message key="label.email"/></label></td>
                                    <td style="padding-top: 8px; padding-bottom: 8px;">
                                        <input type="hidden" name="email" value="${ReiniciarSenhaActionForm.email}"/>
                                        <label class="labelReadOnly">${ReiniciarSenhaActionForm.email}</label>
                                    </td>
                                </tr><tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha"/></label></td>
                                    <td>
                                        <input type="password" name="senhaNova" maxlength="30" class="senha" />
                                        <logic:messagesPresent property="senhaNova">
                                            <script> $(function(){ $("input[name=senhaNova]").addClass("CampoVazio"); }); </script>
                                        </logic:messagesPresent>
                                    </td>
                                </tr><tr>
                                    <td class="label ui-widget-header" ><label><bean:message key="label.senha.confirmar"/></label></td>
                                    <td>
                                        <input type="password" name="senhaConfirmacao" maxlength="30" class="senha" />
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
                        <td class="botoesRetornar" >&nbsp;</td>
                        <td class="botoesCentro" >&nbsp;</td>
                        <td class="botoesAvancar" >
                            <a href="javascript:submeter('../login/reiniciarSenha.do')" >
                                <span class="Icone22x22 IconeConfirmar" ></span>
                                <bean:message key="label.botao.gravar"/><br> &nbsp;
                            </a>
                            <a href="javascript:redirecionar('/dbsportal')" >
                                <span class="Icone22x22 IconeCancelar" ></span>
                                <bean:message key="label.botao.cancelar"/><br> &nbsp;
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