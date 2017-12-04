<%@taglib tagdir="/WEB-INF/tags/" prefix="dbs"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.delphos.com.br/delphos-html" prefix="dph"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
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

		var idListaValor = '${idListaValor}';
		var idItemLista = '';
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="ItemListaVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="ItemListaVH" property="idListaValor" value="${ConfiguracaoActionForm.idListaValor ne null ? ConfiguracaoActionForm.idListaValor : ConfiguracaoActionForm.idListaValorItemLista }" />
        <jsp:setProperty name="ItemListaVH" property="codigoItemLista" value="${ConfiguracaoActionForm.codigoItemLista}" />
        <jsp:setProperty name="ItemListaVH" property="descricaoItemLista" value="${ConfiguracaoActionForm.descricaoItemLista}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.listaValor" />&nbsp;>&nbsp;</a>
				<bean:message key="label.breadcrumb.associar.itemLista" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaItemListaForm" id="pesquisaItemLista"
				action="../configuracao/resultadoItemLista.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idItemLista" name="idItemLista" 
				value="${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.idItemLista : ''}">
				<input type="hidden" id="idListaValor" name="idListaValor" 
				value="${ConfiguracaoActionForm.idListaValor ne null ? ConfiguracaoActionForm.idListaValor : ConfiguracaoActionForm.idListaValorItemLista }">				
				<input type="hidden" id="opcao" name="opcao" value="pesquisaContratoCobranca">
				<input type="hidden" id="codigoListaValor" name="codigoListaValor" value="${ConfiguracaoActionForm.codigoListaValor}" />
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- Descrição Lista Valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.listaValores" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoListaValor" id="descricaoListaValor" alt="descricaoListaValor" 
									value="${ConfiguracaoActionForm.descricaoListaValorItemLista}" maxlength="500" class="parametros"
									readonly="readonly"/>
								</td>
							</tr>
							<%-- CÓDIGO ItemLista ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.itemLista" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoItemLista" id="codigoItemLista" alt="codigoItemLista" 
									value="${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.codigoItemLista : ''}" maxlength="3" class="parametros"/>
								</td>
							</tr>
							<%-- DESCRIÇÃO ItemLista ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.itemLista" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoItemLista" id="descricaoItemLista" alt="descricaoItemLista" 
									value="${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.descricaoItemLista : ''}" maxlength="100" class="parametros"/>
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
				<td class="botoesRetornar" >
	                <a href="#" onclick="javascript:voltar();">
	                    <span class="Icone22x22 IconeNavegacaoRetornar" ></span>
	                    <bean:message key="label.botao.retornar"/><br> &nbsp;
	                </a>
                </td>
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
    <c:when test="${ItemListaVH.listarItemListaPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<div class="barras">
			<div class="tabelas">
	           	<div id="aba3">
	               	<div>
						<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${ItemListaVH.listarItemListaPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="codigoItemLista" titleKey="label.codigo.itemLista" style="text-align:center; width:14%; word-break: break-all;" />
	                            <display:column property="descricaoItemLista" titleKey="label.descricao.itemLista" style="text-align:left; width:55%; word-break: break-all;" />
	                            <display:column property="statusItemLista" titleKey="label.status" style="text-align:left; width:25%; word-break: break-all;" />
	                            <display:column property="acaoItemLista"    titleKey="label.acao"    style="text-align:center; width:6%" />
							</display:table>
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

			document.pesquisaItemListaForm.novaBusca.value = 'true';
			document.pesquisaItemListaForm.opcao.value = "pesquisaItemLista";
			document.pesquisaItemListaForm.submit();
			
		}

		function limpar() {
			
			//document.location = "../configuracao/associarItemLista.jsp";
            document.pesquisaItemListaForm.method = "post";
            //document.pesquisaItemListaForm.idListaValor.value=idListaValor;
            document.pesquisaItemListaForm.novaBusca.value = 'true';
            document.pesquisaItemListaForm.opcao.value = "limparItemLista";
            document.pesquisaItemListaForm.codigoItemLista.value = '';
            document.pesquisaItemListaForm.descricaoItemLista.value = '';
            document.pesquisaItemListaForm.action = "../configuracao/associarItemLista.do";
            document.pesquisaItemListaForm.submit(); 
			
		}
		
		function exibirDetalhe(itemLista){

            document.pesquisaItemListaForm.method = "post";
            document.pesquisaItemListaForm.opcao.value = "exibirDetalheItemLista";
            document.pesquisaItemListaForm.action = "../configuracao/editarItemLista.do?idPesquisa="+itemLista;
            document.pesquisaItemListaForm.submit();  

        }

		function incluir(){

            document.pesquisaItemListaForm.method = "post";
            document.pesquisaItemListaForm.opcao.value = "incluirItemLista";
            document.pesquisaItemListaForm.action = "../configuracao/manterItemLista.do?operacao=4";
            document.pesquisaItemListaForm.submit();  

        }

		function confirmarAtivar(itemLista){
        	prosseguirAtivacao(itemLista);
        }

        function prosseguirAtivacao(itemLista) {

        	idItemLista = itemLista;
            
            confirmar("<bean:message key="msg.confirma.ativacao.itemLista" />", "dialogoPergunta", 400, executarAtivacao);
        }

        function executarAtivacao(retorno) {                
            if (retorno){                  
            	//mostrarMensagem("<bean:message key="msg.itemLista.ativado.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaItemListaForm.method = "post";
                document.pesquisaItemListaForm.idItemLista.value=idItemLista;
                document.pesquisaItemListaForm.opcao.value = "ativarItemLista";
                document.pesquisaItemListaForm.action = "../configuracao/manterItemLista.do?operacao=5";
                document.pesquisaItemListaForm.submit();      
            }
        }

		function confirmarDesativar(itemLista){
        	prosseguirDesativacao(itemLista);
        }

        function prosseguirDesativacao(itemLista) {

        	idItemLista = itemLista;
            
            confirmar("<bean:message key="msg.confirma.desativacao.itemLista" />", "dialogoPergunta", 400, executarDesativacao);
        }

        function executarDesativacao(retorno) {                
            if (retorno){                  
            	//mostrarMensagem("<bean:message key="msg.itemLista.desativado.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaItemListaForm.method = "post";
                document.pesquisaItemListaForm.idItemLista.value=idItemLista;
                document.pesquisaItemListaForm.opcao.value = "desativarItemLista";
                document.pesquisaItemListaForm.action = "../configuracao/manterItemLista.do?operacao=6";
                document.pesquisaItemListaForm.submit();      
            }
        }

        function confirmarExclusao(itemLista){
        	prosseguirExclusao(itemLista);
        }

        function prosseguirExclusao(itemLista) {

        	idItemLista = itemLista;
            
            confirmar("<bean:message key="msg.confirma.exclusao.itemLista" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	mostrarMensagem("<bean:message key="msg.itemLista.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaItemListaForm.method = "post";
                document.pesquisaItemListaForm.idItemLista.value=idItemLista;
                document.pesquisaItemListaForm.action = "../configuracao/manterItemLista.do?operacao=3";
                document.pesquisaItemListaForm.submit();      
            }
        }

        function voltar() {
            document.pesquisaItemListaForm.method = "post";
            document.pesquisaItemListaForm.opcao.value = "voltarItemLista";
            document.pesquisaItemListaForm.action = "../configuracao/resultadoListaValor.do";
            document.pesquisaItemListaForm.submit();   
        }
	</script>
</body>
</html>