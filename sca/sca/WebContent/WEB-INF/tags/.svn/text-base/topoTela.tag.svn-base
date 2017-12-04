<%@tag description="topo das telas da aplicacao" pageEncoding="windows-1252"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<script type="text/javascript" >
    var src = "";
    function logout() {
        
        //if(window.frames[0] != null) {
        //    
        //    src = window.frames[0].location.href;
        //}
        confirmar("<bean:message key="caixaDialogo.saida.sistema"/>", "dialogoPergunta", 400, logoutCB);
    }
            
    function logoutCB(retorno) {
                
        if (retorno){
            
            //window.location = "../login/logout.do";
                    
        } else {
            
            //window.frames[0].location = src;
        }
    }

	function myTrim(x) {
	    return x.replace(/^\s+|\s+$/gm,'');
	}    

    function retornaValoresSelectCSV(select) {
    	var values = $.map(select ,function(option) {
    	    return option.value;
    	}).join(',');
    	return values;
	}
	
</script>
<table class="topo" >
    <tr>
        <td rowspan="2" >
            <img src="../img/logos/logo_billing.png" alt="DBS" />
        </td>
        <td class="botoes" >
            <a href="../meuCadastro/obterMeuCadastro.do?opcao=2" class="tooltipBottom" title="<bean:message key="label.botao.manual"/>" >
                <span class="Icone16x16 IconeAjuda" ></span>
            </a>
            <a href="../login/trocarSenha.jsp" class="tooltipBottom" title="<bean:message key="label.botao.alterar.senha"/>" >
                <span class="Icone16x16 IconeTrocaSenha" ></span>
            </a>
            <a href="../login/logout.jsp" class="tooltipBottom" title="<bean:message key="label.botao.sair"/>" >
                <span class="Icone16x16 IconeSair" ></span>
            </a>
        </td>
    </tr>
    <tr>
        <td class="versao"><label class="identificaoUsuario">${corporativo_usuario}</label> &nbsp;|&nbsp; <bean:message key="label.versao"/></td>
    </tr>
</table>