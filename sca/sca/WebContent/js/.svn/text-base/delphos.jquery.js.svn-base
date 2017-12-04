$(function(){
    
    // - Converte minusculas para maiusculas --------------------------------------------------
    $(".maiuscula").bind("blur", function(){
        $(this).get(0).value = $(this).get(0).value.toUpperCase();
    });
    
    // - Converte maiusculas para minusculas --------------------------------------------------
    $(".minuscula").bind("blur", function(){
        $(this).get(0).value = $(this).get(0).value.toLowerCase();
    });
                  
});

// - Recebe a URL ou Action como parâmetro e redireciona utilizando a mesma janela ------------
function redirecionar(url){   
    $(location).attr("href",url);
}

// - Recebe o Action como parâmetro e submete o formulário ------------------------------------
function submeter(acao) {
    document.forms[0].method = "post";
    document.forms[0].action = acao;
    document.forms[0].submit();
}  

