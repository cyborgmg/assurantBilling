<%@taglib tagdir="/WEB-INF/tags/" prefix="dbs"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.delphos.com.br/delphos-html" prefix="dph"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<dbs:head />

<script type="text/javascript">
	$(function() {
		$(".tooltipTop").tipTip({
			maxWidth : "auto",
			edgeOffset : 10,
			defaultPosition : "top"
		});

		$(".tooltipBottom").tipTip({
			maxWidth : "auto",
			edgeOffset : 10,
			defaultPosition : "bottom"
		});
		
		$(".menuReq").hide();		
		$(".divHidden").hide();		

        $("#usuario").autocomplete({            
            source: function(request, response) {  
                var descUsuario = $("#usuario").val();
                $.ajax({
                    type: "GET",
                    url: "../autocomplete",
                    data: "op=1&descUsuario=" + descUsuario,
                    dataType: "json", 
                    delay:500,   
                    position: {  collision: "flip"  },
                    success: function(data) {                            
                        response(data);
                    }
                }); 
            },
            minLength: 3,
            select: function(event, ui) {                                   
                $("#descricaoUsuario").val(ui.item.value);
        
            }
        });
        	
        $("#empresa").autocomplete({            
            source: function(request, response) {  
                var descEmpresa = $("#empresa").val();
                $.ajax({
                    type: "GET",
                    url: "../autocomplete",
                    data: "op=2&descEmpresa=" + descEmpresa,
                    dataType: "json", 
                    delay:500,   
                    position: {  collision: "flip"  },
                    success: function(data) {                            
                        response(data);
                    }
                }); 
            },
            minLength: 3,
            select: function(event, ui) {                                   
                $("#descricaoEmpresa").val(ui.item.value);
        
            }
        });	
        		
	});

	var idUsuario = '${idUsuario}';
	
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="UsuarioVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="UsuarioVH" property="idUsuario" value="${param.idUsuario}" />
    <jsp:setProperty name="UsuarioVH" property="descricaoEmpresa" value="${ConfiguracaoActionForm.descricaoEmpresa}" />
    <jsp:setProperty name="UsuarioVH" property="descricaoUsuario" value="${ConfiguracaoActionForm.descricaoUsuario}" />
    <jsp:setProperty name="UsuarioVH" property="codigoUsuario" value="${ConfiguracaoActionForm.codigoUsuario}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.usuario" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaUsuarioForm" id="pesquisaUsuario"
				action="../configuracao/resultadoUsuario.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="opcao" name="opcao" value="">
				<input type="hidden" id="idUsuario" name="idUsuario" value="${ConfiguracaoActionForm.idUsuario}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- Código do Usuário ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.usuario" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoUsuario" id="codigoUsuario" alt="codigoUsuario" 
									value="${ConfiguracaoActionForm.codigoUsuario}" maxlength="50"
									class="parametros"/>									
								</td>
							</tr>
							<%-- Descrição do Usuário ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.usuario" /></label></td>
								<td colspan="3">
							        <input 
                                    type="hidden" 
                                    name="descricaoUsuario" 
                                    id="descricaoUsuario" 
                                    value="${ConfiguracaoActionForm.descricaoUsuario}"
                                    />
									<input type="text" name="usuario" id="usuario" alt="usuario" 
									value="${ConfiguracaoActionForm.usuario}" maxlength="255"
									class="parametros"/>
									<span class="IconeObs tooltipTop" title="<bean:message key="ajuda.autoComplete.usuario"/>" >
                                        <span class="Icone16x16 IconeAjuda" ></span>                           
                                    </span>		
								</td>
							</tr>
							<%-- Empresa ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricaoEmpresa.usuario" /></label></td>
								<td colspan="3">
							        <input 
                                    type="hidden" 
                                    name="descricaoEmpresa" 
                                    id="descricaoEmpresa" 
                                    value="${ConfiguracaoActionForm.descricaoEmpresa}"
                                    />
									<input type="text" name="empresa" id="empresa" alt="empresa" 
									value="${ConfiguracaoActionForm.empresa}" maxlength="100"
									class="parametros"/>
									<span class="IconeObs tooltipTop" title="<bean:message key="ajuda.autoComplete.empresa"/>" >
                                        <span class="Icone16x16 IconeAjuda" ></span>                           
                                    </span>								
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
	<div class="barras">
		<table class="navegacao">
			<tr>
				<td class="botoesRetornar">&nbsp;</td>
				<td class="botoesCentro">&nbsp;</td>
				<td class="botoesAvancar">
					<a href="javascript:limpar()">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br> &nbsp;
					</a> 
					<a href="javascript:pesquisar()"> 
						<span class="Icone22x22 IconePesquisar"></span>
						<bean:message key="label.botao.pesquisa" /><br> &nbsp;
					</a>
					<a href="javascript:incluir()"> 
						<span class="Icone22x22 IconeAdicionar"></span>
						<bean:message key="label.botao.incluir" /><br> &nbsp;
					</a>
				</td>
			</tr>
		</table>
	</div>
	<c:choose> 
    <c:when test="${UsuarioVH.listarUsuariosPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
			<c:if test="${UsuarioVH.listarUsuariosPorCriterio().size() gt 0}">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
			</c:if>	
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${UsuarioVH.listarUsuariosPorCriterio()}" export="false" excludedParams="*" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="codigoUsuario" titleKey="label.codigo.usuario" style="text-align:center; width:20%; word-break: break-all;" />
	                            <display:column property="descricaoUsuario" titleKey="label.descricao.usuario" style="text-align:left; width:48%; word-break: break-all;" />
	                            <display:column property="email" titleKey="label.email" style="text-align:center; width:20%; word-break: break-all;" />
		                        <display:column property="acaoUsuario"    titleKey="label.acao"    style="text-align:center; width:12%" />
	                        </display:table>
              			</div>
					</div>
           		</div>
     		</div>
		</div>
	</div>
	</c:when>
	</c:choose>
	<div class="barras sul">
		<p>
			<bean:message key="label.rodape" />
		</p>
	</div>
	<script>
		function ajuda() {
		
			mostrarAjuda(
					<bean:message key="ajuda.caixaDialogo.solicitacao.pesquisar"/>,
					"dialogoInformacao", 400);
		}
		
		function pesquisar() {

			//$(".divHidden").show();	
			document.pesquisaUsuarioForm.novaBusca.value = 'true';
			document.pesquisaUsuarioForm.opcao.value = 'pesquisaUsuario';
			document.pesquisaUsuarioForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/prepararUsuario.do?opcao=1";
			
		}

		function exibirDetalhe(usuario){

        	document.location = "../configuracao/editarUsuario.do?idPesquisa="+usuario;

        }

		function incluir(){

        	//document.location = "../configuracao/manterUsuario.jsp";
			document.location = "../configuracao/prepararUsuario.do?opcao=incluir";

        }

        function confirmarExclusao(usuario){
        	prosseguirExclusao(usuario);
        }

        function prosseguirExclusao(usuario) {

        	idUsuario = usuario;
            
            confirmar("<bean:message key="msg.confirma.exclusao.usuario" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.usuario.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaUsuarioForm.method = "post";
                document.pesquisaUsuarioForm.idUsuario.value=idUsuario;
                document.pesquisaUsuarioForm.action = "../configuracao/manterUsuario.do?operacao=3";
                document.pesquisaUsuarioForm.submit();      
            }
        }
	</script>
</body>
</html>