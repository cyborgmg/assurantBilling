function mostrarPdf(arquivo, largura, altura) {

    var textoDoHtml = "<iframe src='"+ arquivo + "' style='width: 100%; height: " + altura + "px' /><br />";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        title: "Mensagem do Sistema",
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        resizable: false,
        minWidth: 800,
        minHeight: 35,
        modal: true,
        position: ['center', 'center'],
        buttons: {
            'OK': function() {
                $(this).dialog('close');
            }
        }
    });
    $dialog.dialog('open');
}

function mostrarMensagem(mensagem, tipo, largura) {

    var textoDoHtml = "<div class='" + tipo + "' style='width: " + largura + "px'>" + mensagem + "</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        title: "Mensagem do Sistema",
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        resizable: false,
        minWidth: largura + 100,
        minHeight: 35,
        modal: true,
        position: ['center', 'center'],
        buttons: {
            'OK': function() {
                $(this).dialog('close');
            }
        }
    });
    $dialog.dialog('open');
}

function confirmar(mensagem, tipoDialogo, largura, funcaoCallback) {    
    
    var textoDoHtml = "<div class='" + tipoDialogo + "' style='width: " + largura + "px'>" + mensagem + "</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        title: "Mensagem do Sistema",
        resizable: false,
        minWidth: largura + 100,
        modal: true,
        position: ['center', 'center'],
        buttons: {
            'Sim': function() {
                $(this).dialog('close');
                funcaoCallback.call(this, true);
            },
            'Não': function() {
                $(this).dialog('close');
                funcaoCallback.call(this, false);
            }
        }
    });
    $dialog.dialog('open');
}

function mostrarAguardeAutomatico() {
    
    var textoDoHtml = "<div class='dialogoAjax'>Aguarde...</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        title: "Mensagem do Sistema",
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        resizable: false,
        minWidth: 400,
        minHeight: 35,
        modal: true,
        position: ['center', 'center']
    });
    $dialog.dialog('open');
    $(window).load(function(){
        $dialog.dialog('close');
    });
}

function mostrarAguarde() {
    
    var textoDoHtml = "<div class='dialogoAjax'>Aguarde...</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        title: "Mensagem do Sistema",
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        resizable: false,
        minWidth: 400,
        minHeight: 35,
        modal: true,
        position: ['center', 'center']
    });
    $dialog.dialog('open');
    return $dialog;
}

function dialogoCallBack(mensagem, tipoDialogo, largura, funcaoCallback) {    
    
    var textoDoHtml = "<div class='" + tipoDialogo + "' style='width: " + largura + "px'>" + mensagem + "</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        title: "Mensagem do Sistema",
        resizable: false,
        minWidth: largura + 100,
        modal: true,
        position: ['center', 'center'],
        buttons: {
            'OK': function() {
                $(this).dialog('close');
                funcaoCallback.call(this, true);
            }
        }
    });
    $dialog.dialog('open');
}

function mostrarAjuda(mensagem, tipo, largura) {

    var textoDoHtml = "<div class='" + tipo + "' style='width: " + largura + "px'>" + mensagem + "</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        title: "Ajuda do Sistema",
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        resizable: false,
        minWidth: largura + 100,
        minHeight: 35,
        modal: true,
        position: ['center', 'center'],
        buttons: {
            'OK': function() {
                $(this).dialog('close');
            }
        }
    });
    $dialog.dialog('open');
}

function mostrarAjudaCallBack(mensagem, tipo, largura, funcaoCallback) {

    var textoDoHtml = "<div class='" + tipo + "' style='width: " + largura + "px'>" + mensagem + "</div>";

    var $dialog = $('<div></div>').html(textoDoHtml).dialog({
        autoOpen: false,
        closeOnEscape: false,
        title: "Ajuda do Sistema",
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        resizable: false,
        minWidth: largura + 100,
        minHeight: 35,
        modal: true,
        position: ['center', 'center'],
        buttons: {
            'OK': function() {
                $(this).dialog('close');
                funcaoCallback.call(this, true);
            }
        }
    });
    $dialog.dialog('open');
}
