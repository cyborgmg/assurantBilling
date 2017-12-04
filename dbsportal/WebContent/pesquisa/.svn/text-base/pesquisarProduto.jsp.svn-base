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

        $("#empresa").autocomplete({            
            source: function(request, response) {  
                var descEmpresa = $("#empresa").val();
                $.ajax({
                    type: "GET",
                    url: "../dbs/autocomplete",
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
                $("#codigoEmpresa").val(ui.item.id);
        
            }
        });
        		
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="ProdutoVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="ProdutoVH" property="idProduto" value="${param.idProduto}" />
        <jsp:setProperty name="ProdutoVH" property="codigoProduto" value="${ConfiguracaoActionForm.codigoProduto}" />
        <jsp:setProperty name="ProdutoVH" property="descricaoProduto" value="${ConfiguracaoActionForm.descricaoProduto}" />
        <jsp:setProperty name="ProdutoVH" property="codigoEmpresa" value="${ConfiguracaoActionForm.codigoEmpresa}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.produto" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaProdutoForm" id="pesquisaProdutoForm"
				action="../configuracao/resultadoProduto.do" method="post">
				
				<input type="hidden" id="idProduto" name="idProduto" value="${ConfiguracaoActionForm.idProduto}">
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaProduto">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO PRODUTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.produto" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoProduto" id="codigoProduto" alt="codigoProduto" 
									value="${ConfiguracaoActionForm.codigoProduto}" maxlength="10" class="parametros"/>
								</td>
							</tr>
							<%-- DESCRIÇÃO PRODUTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.produto" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoProduto" id="descricaoProduto" alt="descricaoProduto" 
									value="${ConfiguracaoActionForm.descricaoProduto}" maxlength="100" class="parametros"/>
								</td>
							</tr>
							<%-- EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.empresa" /></label></td>
								<td colspan="3">
							        <input 
                                    type="hidden" 
                                    name="codigoEmpresa" 
                                    id="codigoEmpresa" 
                                    value="${ConfiguracaoActionForm.codigoEmpresa}"
                                    />
									<input type="text" name="empresa" id="empresa" alt="empresa" 
									value="${ConfiguracaoActionForm.empresa}" maxlength="100" class="parametros"/>
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
	    <c:when test="${ProdutoVH.listarProdutosPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${ProdutoVH.listarProdutosPorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>	
		<div class="barras">
			<div class="tabelas">
	        	<div id="divHidden"> <!-- style="display: none" -->
	            	<div id="aba3">
	                	<div>
							<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${ProdutoVH.listarProdutosPorCriterio()}" pagesize="10" export="false" excludedParams="*" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
									<display:column property="codigoProduto" titleKey="label.codigo.produto" style="text-align:center; width:9%; word-break: break-all;" />
									<display:column property="descricaoProduto" titleKey="label.descricao.produto" style="text-align:left; width:20%; word-break: break-all;" />
									<display:column property="codigoProdutoEmpresa" titleKey="label.codigoEmpresa.produto" style="text-align:center; width:9%; word-break: break-all;" />
									<display:column property="numeroMaximoParcelasProduto" titleKey="label.numero.maximo.parcelas.produto" style="text-align:right; width:15%; word-break: break-all;" />
									<display:column property="tipoCobrancaProduto" titleKey="label.tipo.cobranca.produto" style="text-align:left; width:24%; word-break: break-all;" />
									<display:column property="acaoProduto"    titleKey="label.acao"    style="text-align:center; width:5%" />
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

			if (myTrim(document.pesquisaProdutoForm.empresa.value).length == 0) {
				document.pesquisaProdutoForm.codigoEmpresa.value = "";
			}

			//$(".divHidden").show();	
			document.pesquisaProdutoForm.novaBusca.value = 'true';
			document.pesquisaProdutoForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/prepararProduto.do?opcao=1";
			
		}

		function exibirDetalhe(itemLista){

        	document.location = "../configuracao/editarProduto.do?idPesquisa="+itemLista;

        }

		function incluir(){

            document.pesquisaProdutoForm.method = "post";
            document.pesquisaProdutoForm.opcao.value="incluirProduto";
            document.pesquisaProdutoForm.action = "../configuracao/prepararProduto.do";
            document.pesquisaProdutoForm.submit();

        }

        function confirmarExclusao(itemLista){
        	prosseguirExclusao(itemLista);
        }

        function prosseguirExclusao(itemLista) {

        	idProduto = itemLista;
            
            confirmar("<bean:message key="msg.confirma.exclusao.produto" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.produto.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaProdutoForm.method = "post";
                document.pesquisaProdutoForm.idProduto.value=idProduto;
                document.pesquisaProdutoForm.action = "../configuracao/manterProduto.do?operacao=3";
                document.pesquisaProdutoForm.submit();
            }
        }
	</script>
</body>
</html>