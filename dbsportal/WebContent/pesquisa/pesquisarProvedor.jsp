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

	var idProvedor = '${idProvedorMeioPagamento}'; // TODO VER DEPOIS
	
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="ProvedorMeioPagamentoVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="ProvedorMeioPagamentoVH" property="idProvedorMeioPagamento" value="${param.idProvedorMeioPagamento}" />
    <jsp:setProperty name="ProvedorMeioPagamentoVH" property="codigoProvedor" value="${ConfiguracaoActionForm.codigoProvedor}" />
    <jsp:setProperty name="ProvedorMeioPagamentoVH" property="descricaoProvedor" value="${ConfiguracaoActionForm.descricaoProvedor}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.provedor" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaProvedorForm" id="pesquisaProvedor"
				action="../configuracao/resultadoProvedorMeioPagamento.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idProvedorMeioPagamento" name="idProvedorMeioPagamento" value="${ConfiguracaoActionForm.idProvedorMeioPagamento}">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaProvedorMeioPagamento">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CODIGO PROVEDOR ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message key="label.codigoProvedor" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoProvedor" id="codigoProvedor" alt="codigoProvedor" 
									value="${ConfiguracaoActionForm.codigoProvedor}" maxlength="5"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRICAO DO PROVEDOR ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.descricaoProvedor" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoProvedor" id="descricaoProvedor" alt="descricaoProvedor" 
									value="${ConfiguracaoActionForm.descricaoProvedor}" maxlength="100"
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
    <c:when test="${ProvedorMeioPagamentoVH.listarProvedorPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${ProvedorMeioPagamentoVH.listarProvedorPorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>	
		<div class="barras">
			<div class="tabelas">
	        	<div id="divHidden"> <!-- style="display: none" -->
					<div id="aba3">
						<div>
							<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${ProvedorMeioPagamentoVH.listarProvedorPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
									<display:column property="codigoProvedor" titleKey="label.codigoProvedor" style="text-align:center; width:14%; word-break: break-all;" />
									<display:column property="descricaoProvedor" titleKey="label.descricaoProvedor" style="text-align:left; width:80%; word-break: break-word;" />
									<display:column property="acaoProvedorMeioPagamento"    titleKey="label.acao"    style="text-align:center; width:6%" />
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
			document.pesquisaProvedorForm.novaBusca.value = 'true';
			document.pesquisaProvedorForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/resultadoProvedorMeioPagamento.do?opcao=1";
			
		}

		function exibirDetalhe(provedor){

        	document.location = "../configuracao/editarProvedorMeioPagamento.do?idPesquisa="+provedor;

        }

		function incluir(){

        	document.location = "../configuracao/manterProvedorMeioPagamento.jsp";

        }

        function confirmarExclusao(provedor){
        	prosseguirExclusao(provedor);
        }

        function prosseguirExclusao(provedor) {

        	idProvedor = provedor;
            
            confirmar("<bean:message key="msg.confirma.exclusao.provedorMeioPagamento" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.provedorMeioPagamento.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaProvedorForm.method = "post";
                document.pesquisaProvedorForm.idProvedorMeioPagamento.value=idProvedor;
                document.pesquisaProvedorForm.action = "../configuracao/manterProvedorMeioPagamento.do?operacao=3";
                document.pesquisaProvedorForm.submit();      
            }
        }
	</script>
</body>
</html>