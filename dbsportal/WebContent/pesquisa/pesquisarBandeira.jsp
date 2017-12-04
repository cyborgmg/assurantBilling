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

	var idBandeira = '${idBandeira}'; // TODO VER DEPOIS
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="BandeiraVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="BandeiraVH" property="idBandeira" value="${param.idBandeira}" />
    <jsp:setProperty name="BandeiraVH" property="codigoBandeira" value="${ConfiguracaoActionForm.codigoBandeira}" />
    <jsp:setProperty name="BandeiraVH" property="nomeBandeira" value="${ConfiguracaoActionForm.nomeBandeira}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.bandeira" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaBandeiraForm" id="pesquisaBandeira"
				action="../configuracao/resultadoBandeira.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idBandeira" name="idBandeira">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaBandeira">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO DO CONVÊNIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.bandeira" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoBandeira" id="codigoBandeira" alt="codigoBandeira" 
									value="${ConfiguracaoActionForm.codigoBandeira}" maxlength="10"
									class="parametros"/>
								</td>
							</tr>
							<%-- NOME DO ADQUIRENTE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.nome" /></label></td>
								<td colspan="3">
									<input type="text" name="nomeBandeira" id="nomeBandeira" alt="nomeBandeira" 
									value="${ConfiguracaoActionForm.nomeBandeira}" maxlength="100"
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
    <c:when test="${BandeiraVH.listarBandeirasPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${BandeiraVH.listarBandeirasPorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>
		<div class="barras">
			<div class="tabelas">
	        	<div id="divHidden"> <!-- style="display: none" -->
		            <div id="aba3">
		            	<div>
	              			<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${BandeiraVH.listarBandeirasPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
		                            <display:column property="codigoBandeira" titleKey="label.codigo.bandeira" style="text-align:center; width:20%; word-break: break-all;" />
		                            <display:column property="nomeBandeira" titleKey="label.nome" style="text-align:left; width:70%; word-break: break-all;" />
		                            <display:column property="acaoBandeira"    titleKey="label.acao"    style="text-align:center; width:10%" />
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
			document.pesquisaBandeiraForm.novaBusca.value = 'true';	
			document.pesquisaBandeiraForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/resultadoBandeira.do?opcao=1";
			
		}

		function exibirDetalhe(bandeira){

        	document.location = "../configuracao/editarBandeira.do?idPesquisa="+bandeira;

        }

		function incluir(){

//         	dcument.location = "../configuracao/manterBandeira.jsp";
        	document.location = "../configuracao/prepararBandeira.do?opcao=incluir";
        }

        function confirmarExclusao(bandeira){
        	prosseguirExclusao(bandeira);
        }

        function prosseguirExclusao(bandeira) {

        	idBandeira = bandeira;
            
            confirmar("<bean:message key="msg.confirma.exclusao.bandeira" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.bandeira.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaBandeiraForm.method = "post";
                document.pesquisaBandeiraForm.idBandeira.value=idBandeira;
                document.pesquisaBandeiraForm.action = "../configuracao/manterBandeira.do?operacao=3";
                document.pesquisaBandeiraForm.submit();      
            }
        }
	</script>
</body>
</html>