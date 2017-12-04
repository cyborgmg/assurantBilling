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
	var listaValor = "";

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

		listaValor = "${idListaValor}"; // TODO VER DEPOIS
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="ListaValorVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="ListaValorVH" property="idListaValor" value="${param.idListaValor}" />
    <jsp:setProperty name="ListaValorVH" property="codigoListaValor" value="${ConfiguracaoActionForm.codigoListaValor}" />
    <jsp:setProperty name="ListaValorVH" property="descricaoListaValor" value="${ConfiguracaoActionForm.descricaoListaValor}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
                <bean:message key="label.breadcrumb.pesquisar.listaValor" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaListaValorForm" id="pesquisaListaValor"
				action="../configuracao/resultadoListaValor.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idListaValor" name="idListaValor" value="${ConfiguracaoActionForm.idListaValor}">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaListaValor">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- Código da lista de valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.listaValor" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoListaValor" id="codigoListaValor" alt="codigoListaValor "
									value="${ConfiguracaoActionForm.codigoListaValor}" maxlength="8"
									class="parametros"/>									
								</td>
							</tr>
							<%-- Descrição da lista de valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.listaValor" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoListaValor" id="descricaoListaValor" alt="descricaoListaValor" 
									value="${ConfiguracaoActionForm.descricaoListaValor}" maxlength="500"
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
    <c:when test="${ListaValorVH.listarListaValorPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
	<c:if test="${ListaValorVH.listarListaValorPorCriterio().size() gt 0}">
		<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
	</c:if>
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${ListaValorVH.listarListaValorPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="codigoListaValor" titleKey="label.codigo.listaValor" style="text-align:center; width:12%; word-break: break-all;" />
	                            <display:column property="descricaoListaValor" titleKey="label.descricao.listaValor" style="text-align:left; width:55%; word-break: break-all;" />
	                            <display:column property="tipoDeUsoListaValor" titleKey="label.tipoDeUso.listaValor" style="text-align:left; width:12%; word-break: break-all;" />
	                            <display:column property="statusListaValor" titleKey="label.status" style="text-align:left; width:14%; word-break: break-all;" />
	                            <display:column property="acaoListaValor"    titleKey="label.acao"    style="text-align:center; width:7%" />
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
			document.pesquisaListaValorForm.novaBusca.value = 'true';	
			document.pesquisaListaValorForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/resultadoListaValor.do?opcao=ignorarValidacao";
			
		}

		function exibirDetalhe(listaValor){

        	document.location = "../configuracao/editarListaValor.do?idPesquisa="+listaValor;

        }

		function incluir(){

        	document.location = "../configuracao/prepararListaValor.do?opcao=incluir";
            //document.pesquisaListaValorForm.method = "post";
            //document.pesquisaListaValorForm.idListaValor.value=listaValor;
            //document.pesquisaListaValorForm.action = "../configuracao/manterListaValor.do";
            //document.pesquisaListaValorForm.submit();  

        }

		function confirmarAtivar(listaValor){
			prosseguirAtivacao(listaValor);
        }

        function prosseguirAtivacao(idListaValor) {

        	listaValor = idListaValor;
            
            confirmar("<bean:message key="msg.confirma.ativacao.listaValor" />", "dialogoPergunta", 400, executarExclusao);
        }
        
		function confirmarDesativar(listaValor){
			prosseguirDesativacao(listaValor);
        }

        function prosseguirDesativacao(idListaValor) {

        	listaValor = idListaValor;
            
            confirmar("<bean:message key="msg.confirma.desativacao.listaValor" />", "dialogoPergunta", 400, executarExclusao);
        }

        function confirmarExclusao(listaValor){
        	prosseguirExclusao(listaValor);
        }

        function prosseguirExclusao(idListaValor) {

        	listaValor = idListaValor;
            
            confirmar("<bean:message key="msg.confirma.exclusao.listaValor" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.contratoCobranca.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaListaValorForm.method = "post";
                document.pesquisaListaValorForm.idListaValor.value=listaValor;
                document.pesquisaListaValorForm.action = "../configuracao/manterListaValor.do?operacao=3";
                document.pesquisaListaValorForm.submit();      
            }
        }

        function associarItemLista(listaValor){

        	//document.location = "../configuracao/manterItemLista.jsp";
            document.pesquisaListaValorForm.method = "post";
            document.pesquisaListaValorForm.idListaValor.value=listaValor;
            document.pesquisaListaValorForm.opcao.value="associarItemListaPrimeiroAcesso";
            document.pesquisaListaValorForm.action = "../configuracao/associarItemLista.do";
            document.pesquisaListaValorForm.submit();  

        }        
	</script>
</body>
</html>