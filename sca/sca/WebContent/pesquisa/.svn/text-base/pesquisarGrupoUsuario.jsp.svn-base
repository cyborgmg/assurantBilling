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
	});

	var idGrupoUsuario = '${idGrupoUsuario}';
	
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="GrupoUsuarioVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="GrupoUsuarioVH" property="idGrupoUsuario" value="${param.idUsuario}" />
    
    <jsp:setProperty name="GrupoUsuarioVH" property="tipoGrupo" value="${ConfiguracaoActionForm.tipoGrupo}" />
    <jsp:setProperty name="GrupoUsuarioVH" property="descricaoGrupoUsuario" value="${ConfiguracaoActionForm.descricaoGrupoUsuario}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.grupoUsuario" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaGrupoUsuarioForm" id="pesquisaGrupoUsuario"
				action="../configuracao/resultadoGrupoUsuario.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idGrupoUsuario" name="idGrupoUsuario" value="${ConfiguracaoActionForm.idGrupoUsuario}">
				
				<input type="hidden" id="opcao" name="opcao" value="pesquisaGrupoUsuario">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- TIPO GRUPO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.tipo.grupo" /></label></td>
								<td colspan="3">
									<input type="text" name="tipoGrupo" id="tipoGrupo" alt="tipoGrupo" 
									value="${ConfiguracaoActionForm.tipoGrupo}" maxlength="10"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRIÇÃO DO GRUPO USUÁRIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.grupoUsuario" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoGrupoUsuario" id="descricaoGrupoUsuario" alt="descricaoGrupoUsuario" 
									value="${ConfiguracaoActionForm.descricaoGrupoUsuario}" maxlength="255"
									class="parametros"/>
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
	<logic:messagesNotPresent>
	<c:choose> 
    <c:when test="${GrupoUsuarioVH.listarGrupoUsuarioPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${GrupoUsuarioVH.listarGrupoUsuarioPorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>	
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${GrupoUsuarioVH.listarGrupoUsuarioPorCriterio()}" export="false" excludedParams="*" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="descricaoGrupoUsuario" titleKey="label.descricao.grupoUsuario" style="text-align:left; width:58%; word-break: break-all;" />
	                            <display:column property="acaoGrupoUsuario"    titleKey="label.acao"    style="text-align:center; width:12%" />
	                        </display:table>
              			</div>
					</div>
           		</div>
     		</div>
		</div>
	</div>
	</c:when>
	</c:choose>
	</logic:messagesNotPresent>
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
			document.pesquisaGrupoUsuarioForm.novaBusca.value = 'true';
			document.pesquisaGrupoUsuarioForm.submit();
		}
		
		function limpar() {
		
			//document.location = "../pesquisa/pesquisarGrupoUsuario.jsp";
			document.location = "../configuracao/prepararGrupoUsuario.do";
			
		}

		function exibirDetalhe(grupoUsuario){

        	document.location = "../configuracao/editarGrupoUsuario.do?idPesquisa="+grupoUsuario;

        }

		function incluir(){

        	//document.location = "../configuracao/manterGrupoUsuario.jsp";
        	document.location = "../configuracao/prepararGrupoUsuario.do?opcao=incluir";

        }

        function confirmarExclusao(grupoUsuario){
        	prosseguirExclusao(grupoUsuario);
        }

        function prosseguirExclusao(grupoUsuario) {

        	idGrupoUsuario = grupoUsuario;
            
            confirmar("<bean:message key="msg.confirma.exclusao.grupoUsuario" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.grupoUsuario.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaGrupoUsuarioForm.method = "post";
                document.pesquisaGrupoUsuarioForm.idGrupoUsuario.value=idGrupoUsuario;
                document.pesquisaGrupoUsuarioForm.action = "../configuracao/manterGrupoUsuario.do?operacao=3";
                document.pesquisaGrupoUsuarioForm.submit();      
            }
        }
	</script>
</body>
</html>