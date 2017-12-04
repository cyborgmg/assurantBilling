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

		var idItemLista = '${idItemLista}';

		$(".menuReq").hide();

	});

    function prosseguir() {

        confirmar("<bean:message key="msg.confirma.itemLista" />", "dialogoPergunta", 400, prosseguirCB);
        
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
            document.pesquisaItemListaForm.method = "post";
            document.pesquisaItemListaForm.idListaValor.value = document.pesquisaItemListaForm.idListaValorItemLista.value;
            document.pesquisaItemListaForm.action = "../configuracao/manterItemLista.do?operacao=2";
            document.pesquisaItemListaForm.submit();  
        }
    }

</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
        <jsp:useBean id="ItemListaVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="ItemListaVH" property="idItemLista" value="${param.idItemLista}" />
        
        <jsp:useBean id="ListaValorVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
			    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.listaValor" />&nbsp;>&nbsp;</a>
			    <a href="javascript:associarItemLista();"><bean:message key="label.breadcrumb.pesquisar.itemLista" />&nbsp;>&nbsp;</a>
                <bean:message key="label.itemLista" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaItemListaForm" id="pesquisaItemLista"
				action="../configuracao/resultadoItemLista.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idItemLista" name="idItemLista" value="${ConfiguracaoActionForm.idItemLista}">
				<input type="hidden" id="idListaValorItemLista" name="idListaValorItemLista" value="${ConfiguracaoActionForm.idListaValor ne null ? ConfiguracaoActionForm.idListaValor : ConfiguracaoActionForm.idListaValorItemLista}">
				<input type="hidden" id="idListaValor" name="idListaValor" value="" />
				<input type="hidden" id="codigoListaValor" name="codigoListaValor" value="${ConfiguracaoActionForm.codigoListaValor}" />
				<input type="hidden" id="descricaoListaValor" name="descricaoListaValor" value="${ConfiguracaoActionForm.descricaoListaValor}" />
				<input type="hidden" id="opcao" name="opcao" value="itemLista">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- Descricao Lista de valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.listaValor" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoListaValorItemLista" id="descricaoListaValorItemLista" alt="descricaoListaValorItemLista" 
										value="${ConfiguracaoActionForm.descricaoListaValorItemLista}" maxlength="100"
										class="parametros" readonly/>
								</td>
							</tr>
							<%-- Código do item da lista de valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.itemLista" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoItemLista" id="codigoItemLista"
									value="${ConfiguracaoActionForm.codigoItemLista}" maxlength="3"
									class="parametros"/>									
								</td>
							</tr>
							<%-- Descrição da lista de valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.itemLista" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoItemLista" id="descricaoItemLista" alt="descricaoItemLista" 
									value="${ConfiguracaoActionForm.descricaoItemLista}" maxlength="100"
									class="parametros"/>
								</td>
							</tr>
							<%-- Status ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.status" /></label></td>
								<td colspan="3">
									
									<div class="formataCampoSelectForm divSelectConsulta">
										<select name="statusItemLista" id="statusItemLista" 
										style="width: 100%;">
												<option value=""
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.statusItemLista == ''}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 > - Selecione - </option>
												<option value="A"
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.statusItemLista == 'A'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 >ATIVO</option>
												<option value="I" 
						                        <c:choose>
						                        <c:when test="${ConfiguracaoActionForm.statusItemLista == 'I'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												>INATIVO</option>
											
										</select>
								</div>
									
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
				<td class="botoesRetornar">
                    <a href="javascript:associarItemLista();">
                        <span class="Icone22x22 IconeNavegacaoRetornar" ></span>
                        <bean:message key="label.botao.retornar"/><br> &nbsp;
                    </a>
				</td>
				<td class="botoesCentro">&nbsp;</td>
				<td class="botoesAvancar">
					<a href="javascript:limpar('c')">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br> &nbsp;
					</a> 
                    <a href="javascript:prosseguir()">
                        <span class="Icone22x22 IconeConfirmar" ></span>
                        <bean:message key="label.botao.gravar"/><br> &nbsp;
                    </a>
				</td>
			</tr>
		</table>
	</div>
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

			exibirRetornoPesquisa();
		}
		
		function limpar(origem) {

			if (document.pesquisaItemListaForm.idItemLista.value) {
				$("form[name=pesquisaItemListaForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaItemListaForm] select:not([readonly]):not([disabled])").val("");

			} else {
	            //document.pesquisaItemListaForm.method = "post";
	            //document.pesquisaItemListaForm.opcao.value = "ignorarValidacao";
	            //document.pesquisaItemListaForm.action = "../configuracao/editarItemLista.do";
	            //document.pesquisaItemListaForm.submit();   
	
				document.pesquisaItemListaForm.idItemLista.value = "";
				document.pesquisaItemListaForm.codigoItemLista.value = "";
				document.pesquisaItemListaForm.descricaoItemLista.value = "";
				//document.pesquisaItemListaForm.statusItemLista.value = "";
				$("#statusItemLista").val("");
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(itemLista){

        	document.location = "../configuracao/editarItemLista.do?idPesquisa="+itemLista;

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
            	$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	mostrarMensagem("<bean:message key="msg.itemLista.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaItemListaForm.idItemLista.value=idItemLista;
            }
        }

    	function voltar(){
            document.pesquisaItemListaForm.method = "post";
            document.pesquisaItemListaForm.opcao.value = "ignorarValidacao";
            document.pesquisaItemListaForm.action = "../configuracao/resultadoListaValor.do";
            document.pesquisaItemListaForm.submit();   
        }

        function associarItemLista(){

            var idListaValor = document.pesquisaItemListaForm.idListaValorItemLista.value;

        	//document.location = "../configuracao/manterItemLista.jsp";
            document.pesquisaItemListaForm.method = "post";
            document.pesquisaItemListaForm.idListaValor.value=idListaValor;
            document.pesquisaItemListaForm.opcao.value="associarItemLista";
            document.pesquisaItemListaForm.action = "../configuracao/associarItemLista.do";
            document.pesquisaItemListaForm.submit();  

        } 

	</script>
</body>
</html>