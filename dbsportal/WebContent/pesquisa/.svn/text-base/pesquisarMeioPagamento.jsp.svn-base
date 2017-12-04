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

		var idMeioPagamento = '${idMeioPagamento}'; // TODO VER DEPOIS
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="MeioPagamentoVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="MeioPagamentoVH" property="idMeioPagamento" value="${param.idMeioPagamento}" />
    <jsp:setProperty name="MeioPagamentoVH" property="codigoMeioPagamento" value="${ConfiguracaoActionForm.codigoMeioPagamento}" />
    <jsp:setProperty name="MeioPagamentoVH" property="descricaoMeioPagamento" value="${ConfiguracaoActionForm.descricaoMeioPagamento}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.meio.pagamento" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaMeioPagamentoForm" id="pesquisaMeioPagamento"
				action="../configuracao/resultadoMeioPagamento.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idMeioPagamento" name="idMeioPagamento" value="${ConfiguracaoActionForm.idMeioPagamento}">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaMeioPagamento">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CODIGO MEIO DE PAGAMENTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message key="label.codigoMeioPagamento" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoMeioPagamento" id="codigoMeioPagamento" alt="codigoMeioPagamento" 
									value="${ConfiguracaoActionForm.codigoMeioPagamento}" maxlength="5"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRICAO DO MEIO DE PAGAMENTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.descricaoMeioPagamento" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoMeioPagamento" id="descricaoMeioPagamento" alt="descricaoMeioPagamento" 
									value="${ConfiguracaoActionForm.descricaoMeioPagamento}" maxlength="100"
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
    <c:when test="${MeioPagamentoVH.listarMeioPagamentoPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${MeioPagamentoVH.listarMeioPagamentoPorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>	
		<div class="barras">
			<div class="tabelas">
	        	<div id="divHidden"> <!-- style="display: none" -->
	             	<div id="aba3">
	                	<div>
							<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${MeioPagamentoVH.listarMeioPagamentoPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
									<display:column property="codigoMeioPagamento" titleKey="label.codigoMeioPagamento" style="text-align:center; width:15%; word-break: break-all;" />
									<display:column property="descricaoMeioPagamento" titleKey="label.descricaoMeioPagamento" style="text-align:left; width:64%; word-break: break-all;" />
									<display:column property="limiteDiasRetentativa" titleKey="label.limiteDiasRetentativa" style="text-align:left; width:15%; word-break: break-word;" />
									<display:column property="acaoMeioPagamento"    titleKey="label.acao"    style="text-align:center; width:6%" />
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
			document.pesquisaMeioPagamentoForm.novaBusca.value = 'true';
			document.pesquisaMeioPagamentoForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/resultadoMeioPagamento.do?opcao=1";
			
		}

		function exibirDetalhe(meioPagamento){

        	document.location = "../configuracao/editarMeioPagamento.do?idPesquisa="+meioPagamento;

        }

		function incluir(){

         	document.location = "../configuracao/manterMeioPagamento.jsp";

        }

        function confirmarExclusao(meioPagamento){
        	prosseguirExclusao(meioPagamento);
        }

        function prosseguirExclusao(meioPagamento) {

        	idMeioPagamento = meioPagamento;
            
            confirmar("<bean:message key="msg.confirma.exclusao.meioPagamento" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.meioPagamento.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaMeioPagamentoForm.method = "post";
                document.pesquisaMeioPagamentoForm.idMeioPagamento.value=idMeioPagamento;
                document.pesquisaMeioPagamentoForm.action = "../configuracao/manterMeioPagamento.do?operacao=3";
                document.pesquisaMeioPagamentoForm.submit();      
            }
        }
	</script>
</body>
</html>