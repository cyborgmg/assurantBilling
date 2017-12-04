<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <awp:head/>
        <script type="text/javascript" >
            $(function(){                
                // - Monta os botões jQuery --------------------------------------------------- 
                $(".btn").button();                  
            });
        </script>  
        <awp:dialogos />
    </head>
    <body>

        <form action="autenticar.do" method="post">
            <div class="barras norte" >
                <awp:topoTelaLogin/>
            </div>
            <div class="barras centro" >
                <div class="BoxModal" >
                    <div id="loginBox">
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <label><bean:message key="label.usuario"/></label>
                                        <input type="text" name="usuario" />
                                    </td><td>
                                        <label><bean:message key="label.senha"/></label>
                                        <input type="password" name="senha" />
                                    </td>
                                </tr>                                		
                            </tbody>
                        </table>
                        <div class="LoginBtn">                             
                            <input type="submit" class="btn" value="Login" />                            
                            <c:if test="${acessoPorAgencia == true}">
                                <input type="button" value="Criar Usuário" class="btn" onclick="redirecionar('../login/cadastrarUsuario.jsp')" />
                            </c:if>
                            <br />
                            <a href="javascript:redirecionar('../login/esqueceuSenha.jsp')" style="display: inline-block; font-size: 11px; font-weight: normal; margin-top: 12px; margin-right: 24px;"><bean:message key="label.botao.esqueceuSenha"/></a>
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
