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
		$("#numeroMaximoParcelasProduto").setMask();
		
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

		var idProduto = '${idProduto}';

		$(".menuReq").hide();

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
    
	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.produto"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.produto" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaProdutoForm" id="pesquisaProduto"
				action="../configuracao/resultadoProduto.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="idProduto" name="idProduto" value="${ConfiguracaoActionForm.idProduto}">
				<input type="hidden" id="opcao" name="opcao" value="produto">
				<input type="hidden" id="contratosAssociados" name="contratosAssociados" value="${ConfiguracaoActionForm.contratosAssociados}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- Código da empresa ==================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.empresa" /></label></td>
								<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="codigoEmpresa" id="codigoEmpresa" style="width: 100%;"
											options="${ProdutoVH.listarEmpresa()}" optionText="descricao"
											optionValue="codigo" value="${ConfiguracaoActionForm.codigoEmpresa}"
											/>
									</div>
								</td>
							</tr>
							<%-- Código do produto ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.produto" /></label></td>
								<td colspan="10">
									<input type="text" name="codigoProduto" id="codigoProduto" alt="codigoProduto" 
									value="${ConfiguracaoActionForm.codigoProduto}" maxlength="10"
									class="parametros"/>									
								</td>
							</tr>
							<%-- Descrição do produto =============================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.produto" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoProduto" id="descricaoProduto" alt="descricaoProduto" 
									value="${ConfiguracaoActionForm.descricaoProduto}" maxlength="100"
									class="parametros"/>
								</td>
							</tr>
							<%-- Tipo de cobrança  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.tipo.cobranca.produto" /></label></td>
								<td colspan="3">
								<div class="formataCampoSelectForm divSelectConsulta">
										<select name="tipoCobrancaProduto" id="tipoCobrancaProduto" 
										style="width: 100%;">
												<option value=""
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.tipoCobrancaProduto == ''}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 > - Selecione - </option>
												<option value="001"
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.tipoCobrancaProduto == '001'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 >SINGLE PREMIUM À VISTA</option>
												<option value="002" 
						                        <c:choose>
						                        <c:when test="${ConfiguracaoActionForm.tipoCobrancaProduto == '002'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												>SINGLE PREMIUM  PARCELADO</option>
											
										</select>
								</div>
								</td>
							</tr>
							<%-- Número máximo de parcelas para cobrança parcelada (se Prêmio Único) ou número máximo de cobranças (se MOB)  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.numero.maximo.parcelas.produto" /></label></td>
								<td colspan="3">
									<input type="text" name="numeroMaximoParcelasProduto" id="numeroMaximoParcelasProduto" 
									value="${ConfiguracaoActionForm.numeroMaximoParcelasProduto}" maxlength="3"
									class="parametros" alt="tres-digitos"/>
								</td>
							</tr>
							<%-- ASSOCIAR PRODUTOS COM CONTRATOS DE COBRANCA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarContratosCobranca" /></label></td>
								<td colspan="3">
	                              	<table class="TabelaPrin dataTable multiSelectTable" style="width: 94%" cellspacing="1">
				                        <tr>
				                            <td>
				                                <dph:multiselect
				                                	idFrom="associacaoFrom"
				                                	idTo="associacaoTo"
				                                    name="associacao2"
				                                    fromList="${contratosCobrancaNaoAssociados}"
				                                    fromPropertyText="descricaoContrato"
				                                    fromPropertyValue="id"
				                                    fromTitle="Sem Associação"
				                                    toList="${contratosCobrancaAssociados}"
				                                    toPropertyText="descricaoContrato"
				                                    toPropertyValue="id"
				                                    toTitle="Associados"
				                                    height="10"
				                                    width="190px" />
				                            </td>
				                        </tr>
				                    </table>
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
	                <a href="javascript:voltar()">
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
		
		function limpar(origem) {

			if (document.pesquisaProdutoForm.idProduto.value) {
				$("form[name=pesquisaProdutoForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaProdutoForm] select:not([readonly]):not([disabled]):not([name=codigoEmpresa])").val("");
				$("#associacaoTo option").each(function () {
					$("#associacaoFrom").append($(this));
				});
				$("#associacaoTo").html("");
								
			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterProduto.jsp";
			}
		}

        function prosseguir() {
            confirmar("<bean:message key="msg.confirma.produto" />", "dialogoPergunta", 400, prosseguirCB);
        }

        function prosseguirCB(retorno) {                
            if (retorno){  

           	   var options = Array();
           	    $('select[name=associacao2sel2] option').each(function(index){
           	        options[index] = $(this).val();
           	    });

           	    $('#contratosAssociados').val(options.join(','));
            	    
            	//mostrarMensagem("<bean:message key="msg.produto.incluido.sucesso"/>", "dialogoSucesso", 400);    
            	//document.location = "../configuracao/manterProduto.jsp";   
                document.pesquisaProdutoForm.method = "post";
                document.pesquisaProdutoForm.action = "../configuracao/manterProduto.do?operacao=2";
                document.pesquisaProdutoForm.submit();  
            }
        }

    	function voltar(){
            document.pesquisaProdutoForm.method = "post";
            document.pesquisaProdutoForm.opcao.value = "listarProduto";
            document.pesquisaProdutoForm.action = "../configuracao/prepararProduto.do";
            document.pesquisaProdutoForm.submit();   
        }
        
	</script>
</body>
</html>