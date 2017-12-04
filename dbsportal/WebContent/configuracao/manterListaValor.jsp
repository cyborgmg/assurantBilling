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

		var idListaValor = '${idListaValor}';

		$(".menuReq").hide();

	});

    function prosseguir() {

        confirmar("<bean:message key="msg.confirma.listaValor" />", "dialogoPergunta", 400, prosseguirCB);

    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
             document.pesquisaListaValorForm.method = "post";
             document.pesquisaListaValorForm.action = "../configuracao/manterListaValor.do?operacao=2";
             document.pesquisaListaValorForm.submit();  
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
	
        <jsp:useBean id="ListaValorVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="ListaValorVH" property="idListaValor" value="${param.idListaValor}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
			    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.listaValor" />&nbsp;>&nbsp;</a>
                <bean:message key="label.listaValor" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaListaValorForm" id="pesquisaListaValor"
				action="../configuracao/resultadoListaValor.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idListaValor" name="idListaValor" value="${ConfiguracaoActionForm.idListaValor}">
				<input type="hidden" id="opcao" name="opcao" value="listaValor">
				
				<input type="hidden" name="tipoDeUsoListaValor" value="G" />
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- Código da lista de valor ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.listaValor" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoListaValor" id="codigoListaValor"  
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
							<%-- TODO Remover por completo a seleção do tipo da lista de valor (considerar todas as listas como gerais) --%>
							<%-- Tipo de uso ================================================================== --%>
							<%-- <tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.tipoDeUso.listaValor" /></label></td>
								<td colspan="3">
								<div class="formataCampoSelectForm divSelectConsulta">
										<select name="tipoDeUsoListaValor" id="tipoDeUsoListaValor" 
										style="width: 100%;">
												<option value=""
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.tipoDeUsoListaValor == ''}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 > - Selecione - </option>
												<option value="S"
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.tipoDeUsoListaValor == 'S'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 >SISTEMA</option>
												<option value="G" 
						                        <c:choose>
						                        <c:when test="${ConfiguracaoActionForm.tipoDeUsoListaValor == 'G'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												>GERAL</option>
											
										</select>
								</div>
								</td>
							</tr>--%>
							<%-- Status ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.status" /></label></td>
								<td colspan="3">
									
									<div class="formataCampoSelectForm divSelectConsulta">
										<select name="statusListaValor" id="statusListaValor" 
										style="width: 100%;">
												<option value=""
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.statusListaValor == ''}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 > - Selecione - </option>
												<option value="A"
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.statusListaValor == 'A'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 >ATIVO</option>
												<option value="I" 
						                        <c:choose>
						                        <c:when test="${ConfiguracaoActionForm.statusListaValor == 'I'}">
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
                    <a href="javascript:voltar();">
                        <span class="Icone22x22 IconeNavegacaoRetornar" ></span>
                        <bean:message key="label.botao.retornar"/><br><br> &nbsp;
                    </a>
				</td>
				<td class="botoesCentro">&nbsp;</td>
				<td class="botoesAvancar">
					<a href="javascript:limpar('c')">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br><br> &nbsp;
					</a> 
                    <a href="javascript:prosseguir()">
                        <span class="Icone22x22 IconeConfirmar" ></span>
                        <bean:message key="label.botao.gravar"/><br><br> &nbsp;
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

			//document.pesquisaLogsForm.submit();
			exibirRetornoPesquisa();
		}
		
		function limpar(origem) {

			if (document.pesquisaListaValorForm.idListaValor.value) {
				$("form[name=pesquisaListaValorForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaListaValorForm] select:not([readonly]):not([disabled])").val("");

			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterListaValor.jsp";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(listaValor){

        	document.location = "../configuracao/editarListaValor.do?idPesquisa="+listaValor;

        }

        function confirmarExclusao(listaValor){
        	prosseguirExclusao(listaValor);
        }

        function prosseguirExclusao(listaValor) {

        	idListaValor = listaValor;
            
            confirmar("<bean:message key="msg.confirma.exclusao.listaValor" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	mostrarMensagem("<bean:message key="msg.listaValor.excluido.sucesso"/>", "dialogoSucesso", 400);    
                //document.pesquisaListaValorForm.method = "post";
                document.pesquisaListaValorForm.idListaValor.value=idListaValor;
                //document.pesquisaListaValorForm.action = "../configuracao/manterMeioPagamento.do?operacao=3";
                //document.pesquisaListaValorForm.submit();      
            }
        }

        function voltar() {
			document.pesquisaListaValorForm.method = "post";
	        document.pesquisaListaValorForm.opcao.value = "prepararVoltar";
	        document.pesquisaListaValorForm.action = "../configuracao/prepararListaValor.do";
	        document.pesquisaListaValorForm.submit();
//             document.pesquisaListaValorForm.method = "post";
//             document.pesquisaListaValorForm.opcao.value = "ignorarValidacao";
//             document.pesquisaListaValorForm.action = "../configuracao/resultadoListaValor.do";
//             document.pesquisaListaValorForm.submit();   
        }

        function associarItemLista(listaValor){

        	idListaValor = listaValor;

        	//document.location = "../configuracao/manterItemLista.jsp";
            document.pesquisaListaValorForm.method = "post";
            document.pesquisaListaValorForm.idListaValor.value=idListaValor;
            document.pesquisaListaValorForm.action = "../configuracao/associarItemLista.do";
            if (document.pesquisaListaValorForm.idListaValor.value != 'undefined' && myTrim(document.pesquisaListaValorForm.idListaValor.value).length != 0 ) {
            	document.pesquisaListaValorForm.submit();  
            }

        }
	</script>
</body>
</html>