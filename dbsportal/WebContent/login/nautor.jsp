<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
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
                            Acesso Não Permitido
                        </p>
                        <p>
                            A Tela solicitada está em uma área segura e não pode ser acessada 
                            através da sua conta ou através desta máquina.
                        </p>
                        <p>
                            Caso seja necessário o acesso a área solicitada, por favor contacte o 
                            suporte técnico para maiores esclarecimentos.
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