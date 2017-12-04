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

	var idGrupoInterface = '${idGrupoInterface}';
	
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="GrupoInterfaceVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="GrupoInterfaceVH" property="idGrupoInterface" value="${param.idGrupoInterface}" />
    
    <jsp:setProperty name="GrupoInterfaceVH" property="descricaoGrupoInterface" value="${ConfiguracaoActionForm.descricaoGrupoInterface}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.grupoInterface" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaGrupoInterfaceForm" id="pesquisaGrupoInterface"
				action="../configuracao/resultadoGrupoInterface.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="opcao" name="opcao" value="grupoInterface">
				<input type="hidden" id="idGrupoInterface" name="idGrupoInterface" value="${ConfiguracaoActionForm.idGrupoInterface}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DO GRUPO INTERFACE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.grupoInterface" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoGrupoInterface" id="descricaoGrupoInterface" alt="descricaoGrupoInterface" 
									value="${ConfiguracaoActionForm.descricaoGrupoInterface}" maxlength="255"
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
	<c:choose> 
    <c:when test="${GrupoInterfaceVH.listarGrupoInterfacePorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${GrupoInterfaceVH.listarGrupoInterfacePorCriterio()}" export="false" excludedParams="*" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="descricaoGrupoInterface" titleKey="label.descricao.grupoInterface" style="text-align:left; width:80%; word-break: break-all;" />
	                            <display:column property="acaoGrupoInterface"    titleKey="label.acao"    style="text-align:center; width:20%" />
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

			document.pesquisaGrupoInterfaceForm.novaBusca.value = 'true';
			document.pesquisaGrupoInterfaceForm.submit();
		}
		
		function limpar() {
		
			//document.location = "../pesquisa/pesquisarGrupoInterface.jsp";
			document.location = "../configuracao/prepararGrupoInterface.do";
			
		}

		function exibirDetalhe(grupoInterface){

        	document.location = "../configuracao/editarGrupoInterface.do?idPesquisa="+grupoInterface;

        }

		function incluir(){

        	//document.location = "../configuracao/manterGrupoInterface.jsp";
			document.location = "../configuracao/prepararGrupoInterface.do?opcao=incluir";

        }

        function confirmarExclusao(grupoInterface){
        	prosseguirExclusao(grupoInterface);
        }

        function prosseguirExclusao(grupoInterface) {

        	idGrupoInterface = grupoInterface;
            
            confirmar("<bean:message key="msg.confirma.exclusao.grupoInterface" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.grupoInterface.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaGrupoInterfaceForm.method = "post";
                document.pesquisaGrupoInterfaceForm.idGrupoInterface.value=idGrupoInterface;
                document.pesquisaGrupoInterfaceForm.action = "../configuracao/manterGrupoInterface.do?operacao=3";
                document.pesquisaGrupoInterfaceForm.submit();      
            }
        }
	</script>
</body>
</html>