<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <awp:head/>        
        <script type="text/javascript" >
            $(function(){                
                // - Monta os bot�es jQuery --------------------------------------------------- 
                $(".btn").button(); 
            });
        </script>
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
                            Acesso N�o Permitido
                        </p>
                        <p>
                            A Tela solicitada est� em uma �rea segura e n�o pode ser acessada 
                            atrav�s da sua conta ou atrav�s desta m�quina.
                        </p>
                        <p>
                            Caso seja necess�rio o acesso a �rea solicitada, por favor contacte o 
                            suporte t�cnico para maiores esclarecimentos.
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