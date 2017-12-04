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
		
		$("#codigoAfiliacao").setMask('tres-digitos');	
	});

	var idAdquirente = '${idAdquirente}'; // TODO VER DEPOIS
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="AdquirenteVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="AdquirenteVH" property="idAdquirente" value="${param.idAdquirente}" />
    <jsp:setProperty name="AdquirenteVH" property="codigoConvenioAdquirente" value="${ConfiguracaoActionForm.codigoConvenioAdquirente}" />
    <jsp:setProperty name="AdquirenteVH" property="codigoAfiliacao" value="${ConfiguracaoActionForm.codigoAfiliacao}" />
    <jsp:setProperty name="AdquirenteVH" property="nomeAdquirente" value="${ConfiguracaoActionForm.nomeAdquirente}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.adquirente" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaAdquirenteForm" id="pesquisaAdquirente"
				action="../configuracao/resultadoAdquirente.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idAdquirente" name="idAdquirente">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaAdquirente">
				
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO DO CONVÊNIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.convenio.adquirente" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoConvenioAdquirente" id="codigoConvenioAdquirente" alt="codigoConvenioAdquirente" 
									value="${ConfiguracaoActionForm.codigoConvenioAdquirente}" maxlength="50"
									class="parametros"/>
								</td>
							</tr>
							<%-- CODIGO FILIACAO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.codigoAfiliacao"/></label></td>
								<td>
                                    <input 
                                        type="text" 
                                        name="codigoAfiliacao"
                                        id="codigoAfiliacao"
                                        class="parametros"
                                        value="${ConfiguracaoActionForm.codigoAfiliacao}"
                                        maxlength="3"
                                		alt="codigoAfiliacao"
                                     />
								</td>
							</tr>
							<%-- NOME DO ADQUIRENTE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.nome" /></label></td>
								<td colspan="3">
									<input type="text" name="nomeAdquirente" id="nomeAdquirente" alt="nomeAdquirente" 
									value="${ConfiguracaoActionForm.nomeAdquirente}" maxlength="100"
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
    <c:when test="${AdquirenteVH.listarAdquirentePorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${AdquirenteVH.listarAdquirentePorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>
		<div class="barras">
			<div class="tabelas">
	        	<div id="divHidden"> <!-- style="display: none" -->
		            <div id="aba3">
		            	<div>
	              			<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${AdquirenteVH.listarAdquirentePorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
		                            <display:column property="codigoConvenioAdquirente" titleKey="label.codigo.convenio.adquirente" style="text-align:center; width:40%; word-break: break-all;" />
		                            <display:column property="nomeAdquirente" titleKey="label.nome" style="text-align:left; width:40%; word-break: break-all;" />
		                            <display:column property="codigoAfiliacaoConciliador" titleKey="label.codigoAfiliacao" style="text-align:center; width:10%; word-break: break-all;" />
		                            <display:column property="acaoAdquirente"    titleKey="label.acao"    style="text-align:center; width:10%" />
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
			document.pesquisaAdquirenteForm.novaBusca.value = 'true';
			document.pesquisaAdquirenteForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/resultadoAdquirente.do?opcao=1";
			
		}

		function exibirDetalhe(adquirente){

        	document.location = "../configuracao/editarAdquirente.do?idPesquisa="+adquirente;

        }

		function incluir(){

        	//document.location = "../configuracao/manterAdquirente.jsp";
        	document.location = "../configuracao/prepararAdquirente.do?opcao=incluir";
        }

        function confirmarExclusao(adquirente){
        	prosseguirExclusao(adquirente);
        }

        function prosseguirExclusao(adquirente) {

        	idAdquirente = adquirente;
            
            confirmar("<bean:message key="msg.confirma.exclusao.adquirente" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.adquirente.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaAdquirenteForm.method = "post";
                document.pesquisaAdquirenteForm.idAdquirente.value=idAdquirente;
                document.pesquisaAdquirenteForm.action = "../configuracao/manterAdquirente.do?operacao=3";
                document.pesquisaAdquirenteForm.submit();      
            }
        }
	</script>
</body>
</html>