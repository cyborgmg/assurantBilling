<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <awp:head/>
        <script type="text/javascript" >
        </script>  
        <awp:dialogos />
    </head>
    <body>
           <div class="barras norte" >
               <awp:topoTelaLogin/>
           </div>
           <div class="barras centro" >
               <div class="BoxModal" >
                   <div id="loginBox">
	                    <div id="msgLoginAlerta">
	                        <p style="font-size: 25px; text-align: center">
	                            <bean:message key="msg.titulo.pagina.erro"/>
	                        </p>
	                        <p>
								<bean:message key="msg.pagina.erro"/>
	                        </p>
	                    </div>   
                   </div>
               </div>
           </div>
           <div class="barras sul" >
               <p>DELPHOS - Tecnologia em Seguros</p>
           </div>
    </body>
</html>
