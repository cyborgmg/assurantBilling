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
		$("#quantidadeRetentativas").setMask();
		$("#intervaloEntreRetentativas").setMask();
		
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

		var idRespostaAutorizacao = '${idRespostaAutorizacao}'; // TODO VER DEPOIS

		$("#tipoIntervaloRetentativas").attr("disabled","disabled");
		$("#intervaloEntreRetentativas").attr("disabled","disabled");

		$(".menuReq").hide();

		validaQtdRetentativas();
		validaTipoIntervaloRetentativas("");

	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
        <jsp:useBean id="RespostaAutorizacaoVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="RespostaAutorizacaoVH" property="idRespostaAutorizacao" value="${param.idRespostaAutorizacao}" />
        <jsp:setProperty name="RespostaAutorizacaoVH" property="idContratoCobranca" value="${ConfiguracaoActionForm.idContratoCobranca ne '' ? ConfiguracaoActionForm.idContratoCobranca : param.idRespostaAutorizacao}" />

        <jsp:setProperty name="RespostaAutorizacaoVH" property="codigoRespostaAutorizacao" value="${ConfiguracaoActionForm.codigoRespostaAutorizacao}" />
        <jsp:setProperty name="RespostaAutorizacaoVH" property="descricaoRespostaAutorizacaoProvedor" value="${ConfiguracaoActionForm.descricaoRespostaAutorizacaoProvedor}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
                   <a href="javascript:voltarPesquisar();"><bean:message key="label.breadcrumb.pesquisar.contratoCobranca" />&nbsp;>&nbsp;</a>
                   <a href="javascript:voltar(${ConfiguracaoActionForm.idContratoCobranca ne '' ? ConfiguracaoActionForm.idContratoCobranca : param.idContrato});"><bean:message key="label.contratoCobranca" />&nbsp;>&nbsp;</a>
                   <bean:message key="label.respostaAutorizacao" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaRespostaAutorizacaoForm" id="pesquisaRespostaAutorizacao"
				action="../configuracao/resultadoRespostaAutorizacao.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idRespostaAutorizacao" name="idRespostaAutorizacao" value="${ConfiguracaoActionForm.idRespostaAutorizacao}">
				<input type="hidden" id="idContratoCobranca" name="idContratoCobranca" value="${ConfiguracaoActionForm.idContratoCobranca ne '' ? ConfiguracaoActionForm.idContratoCobranca : param.idContrato}">
				<input type="hidden" id="opcao" name="opcao" value="manterRespostaAutorizacao" />
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CONTRATO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.contrato" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoContratoRespostaAutorizacao" id="codigoContratoRespostaAutorizacao" alt="codigoContratoRespostaAutorizacao" 
									value="${ConfiguracaoActionForm.codigoContratoRespostaAutorizacao}" 
									maxlength="14" class="parametros" readonly/>
								</td>
							</tr>
							<%-- Código da Empresa  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.empresa" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoEmpresaRespostaAutorizacao" id="codigoEmpresaRespostaAutorizacao" alt="codigoEmpresaRespostaAutorizacao" 
									value="${ConfiguracaoActionForm.codigoEmpresaRespostaAutorizacao}" maxlength="5"
									class="parametros" readonly/>
								</td>
							</tr>
							<%-- Código do Meio de Pagamento  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.meio.pagamento" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoMeioPagamentoRespostaAutorizacao" id="codigoMeioPagamentoRespostaAutorizacao" alt="codigoMeioPagamentoRespostaAutorizacao" 
									value="${ConfiguracaoActionForm.codigoMeioPagamentoRespostaAutorizacao}" maxlength="5"
									class="parametros" readonly/>
								</td>
							</tr>
							<%-- Código do Provedor  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.provedor" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoProvedorRespostaAutorizacao" id="codigoProvedorRespostaAutorizacao" alt="codigoProvedorRespostaAutorizacao" 
									value="${ConfiguracaoActionForm.codigoProvedorRespostaAutorizacao}" maxlength="5"
									class="parametros" readonly/>
								</td>
							</tr>
							<%-- Código da resposta  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.respostaAutorizacao" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoRespostaAutorizacao" id="codigoRespostaAutorizacao" alt="codigoRespostaAutorizacao" 
									value="${ConfiguracaoActionForm.codigoRespostaAutorizacao}" maxlength="5"
									class="parametros"/>
								</td>
							</tr>
							<%-- Descrição da resposta de autorização do provedor  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.provedor.respostaAutorizacao" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoRespostaAutorizacaoProvedor" id="descricaoRespostaAutorizacaoProvedor" alt="descricaoRespostaAutorizacaoProvedor" 
									value="${ConfiguracaoActionForm.descricaoRespostaAutorizacaoProvedor}" maxlength="100"
									class="parametros"/>
								</td>
							</tr>
							<%-- Descrição da resposta de autorização para consulta ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.consulta.respostaAutorizacao" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoRespostaAutorizacaoConsulta" id="descricaoRespostaAutorizacaoConsulta" alt="descricaoRespostaAutorizacaoConsulta" 
									value="${ConfiguracaoActionForm.descricaoRespostaAutorizacaoConsulta}" maxlength="100"
									class="parametros"/>
								</td>
							</tr>
							<%-- Quantidade de retentativas  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.quantidade.retentativas.respostaAutorizacao" /></label></td>
								<td colspan="3">
									<input type="text" name="quantidadeRetentativas" id="quantidadeRetentativas" alt="tres-digitos" 
									value="${ConfiguracaoActionForm.quantidadeRetentativas}" maxlength="3"
									class="parametros" onblur="validaQtdRetentativas();"/>
								</td>
							</tr>
							<%-- Tipo de intervalo de retentativas (dias ou horas) ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.tipo.intervalo.respostaAutorizacao" /></label></td>
								<td colspan="3">
									<div id="divStatus" class="formataCampoSelectForm divSelectConsulta" style="width: 30%;">
										<select name="tipoIntervaloRetentativas" id="tipoIntervaloRetentativas" alt="tipoIntervaloRetentativas" 
										style="width: 100%;" onchange="javascript: validaTipoIntervaloRetentativas(this.value);">
												<option value=""
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.tipoIntervaloRetentativas == ''}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 > - Selecione - </option>
												<option value="DIAS"
												<c:choose> 
						                        <c:when test="${ConfiguracaoActionForm.tipoIntervaloRetentativas == 'DIAS'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												 >DIAS</option>
												<option value="HORAS" 
						                        <c:choose>
						                        <c:when test="${ConfiguracaoActionForm.tipoIntervaloRetentativas == 'HORAS'}">
						                            selected
						                        </c:when>
						                        </c:choose>
												>HORAS</option>
											
										</select>
									</div>
								</td>
							</tr>
							<%-- Intervalo entre retentativas (de acordo com o atributo anterior) ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.valor.intervalo.respostaAutorizacao" /></label></td>
								<td colspan="3">
									<input type="text" name="intervaloEntreRetentativas" id="intervaloEntreRetentativas" 
									 alt="tres-digitos" 
									value="${ConfiguracaoActionForm.intervaloEntreRetentativas}" maxlength="3"
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
				<td class="botoesRetornar">
                    <a href="javascript:voltar(${ConfiguracaoActionForm.idContratoCobranca ne '' ? ConfiguracaoActionForm.idContratoCobranca : param.idContrato});">
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
	<c:choose> 
    <c:when test="${RespostaAutorizacaoVH.listarRespostaAutorizacaoPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
            	<div id="aba3">
                	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${RespostaAutorizacaoVH.listarRespostaAutorizacaoPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
								<display:column property="codigoRespostaAutorizacao" 					titleKey="label.codigo.respostaAutorizacao" style="text-align:center; width:8%; word-break: break-all;" />
								<display:column property="descricaoRespostaProvedorRespostaAutorizacao" titleKey="label.descricao.provedor.respostaAutorizacao" style="text-align:left; width:14%; word-break: break-all;" />
								<display:column property="descricaoRespostaConsultaRespostaAutorizacao" titleKey="label.descricao.consulta.respostaAutorizacao" style="text-align:left; width:14%; word-break: break-all;" />
								<display:column property="quantidadeRetentativaRespostaAutorizacao" 	titleKey="label.quantidade.retentativas.respostaAutorizacao" style="text-align:right; width:9%; word-break: break-all;" />
								<display:column property="tipoIntervaloRespostaAutorizacao" 			titleKey="label.tipo.intervalo.respostaAutorizacao" style="text-align:left; width:10%; word-break: break-all;" />
								<display:column property="valorIntervaloRespostaAutorizacao" 			titleKey="label.valor.intervalo.respostaAutorizacao" style="text-align:right; width:8%; word-break: break-all;" />
								<display:column property="acaoRespostaAutorizacao"    					titleKey="label.acao" style="text-align:center; width:5%" />
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

			//document.pesquisaLogsForm.submit();
			exibirRetornoPesquisa();
		}
		
		function limpar(origem) {

			if (document.pesquisaRespostaAutorizacaoForm.idRespostaAutorizacao.value) {
				$("form[name=pesquisaRespostaAutorizacaoForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaRespostaAutorizacaoForm] select:not([readonly]):not([disabled])").val("");
				$("select[name=tipoIntervaloRetentativas], input[name=intervaloEntreRetentativas]").attr('disabled', 'disabled');
				
			} else {
				document.location = "../configuracao/associarAdquirente.do?idContrato="+document.pesquisaRespostaAutorizacaoForm.idContratoCobranca.value+"&opcao=respostaAutorizacao";
				//document.location = "../configuracao/manterRespostaAutorizacao.jsp";
			}
			
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(contratoCobranca){

        	document.location = "../configuracao/editarRespostaAutorizacao.do?idPesquisa="+contratoCobranca;

        }

        function confirmarExclusao(contratoCobranca){
        	prosseguirExclusao(contratoCobranca);
        }

        function prosseguirExclusao(contratoCobranca) {

        	idRespostaAutorizacao = contratoCobranca;
            
            confirmar("<bean:message key="msg.confirma.exclusao.respostaAutorizacao" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.respostaAutorizacao.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaRespostaAutorizacaoForm.method = "post";
                document.pesquisaRespostaAutorizacaoForm.idRespostaAutorizacao.value=idRespostaAutorizacao;
                document.pesquisaRespostaAutorizacaoForm.opcao.value = "respostaAutorizacao";
                document.pesquisaRespostaAutorizacaoForm.action = "../configuracao/manterRespostaAutorizacao.do?operacao=3";
                document.pesquisaRespostaAutorizacaoForm.submit();      
            }
        }

        function prosseguir() {

            confirmar("<bean:message key="msg.confirma.respostaAutorizacao" />", "dialogoPergunta", 400, prosseguirCB);
            
        }

        function prosseguirCB(retorno) {                
            if (retorno){                  
            	//mostrarMensagem("<bean:message key="msg.respostaAutorizacao.incluido.sucesso"/>", "dialogoSucesso", 400);       
                 document.pesquisaRespostaAutorizacaoForm.method = "post";
                 document.pesquisaRespostaAutorizacaoForm.action = "../configuracao/manterRespostaAutorizacao.do?operacao=2";
                 document.pesquisaRespostaAutorizacaoForm.submit();  
            }
        }

    	function voltar(contrato){
            //window.location = "../configuracao/prepararContratoCobranca.do?idContrato="+contrato;
    		document.location = "../configuracao/editarContratoCobranca.do?idPesquisa="+contrato;
        }  

    	function voltarPesquisar(){
            //window.location = "../pesquisa/pesquisarContratoCobranca.jsp";

            document.pesquisaRespostaAutorizacaoForm.method = "post";
            document.pesquisaRespostaAutorizacaoForm.opcao.value = "prepararVoltar";
            document.pesquisaRespostaAutorizacaoForm.action = "../configuracao/prepararContratoCobranca.do";
            document.pesquisaRespostaAutorizacaoForm.submit();  
        }              

		function validaQtdRetentativas() {
			var qtdRetentativas = $("#quantidadeRetentativas").val();
			if (qtdRetentativas != "" && myTrim(qtdRetentativas).length > 0) {
				if ((qtdRetentativas*1) == 0) {
					$("#tipoIntervaloRetentativas").val("");
					$("#tipoIntervaloRetentativas").attr("disabled","disabled");
					$("#intervaloEntreRetentativas").val("");
					$("#intervaloEntreRetentativas").attr("disabled","disabled");
				} else {
					$("#tipoIntervaloRetentativas").removeAttr("disabled");
				}
			} else {
				$("#tipoIntervaloRetentativas").val("");
				$("#tipoIntervaloRetentativas").attr("disabled","disabled");
				$("#intervaloEntreRetentativas").val("");
				$("#intervaloEntreRetentativas").attr("disabled","disabled");
			}
		}

		function validaTipoIntervaloRetentativas(tipo) {
			if ($("#tipoIntervaloRetentativas").val() != null && $("#tipoIntervaloRetentativas").val() != "") {
				$("#intervaloEntreRetentativas").removeAttr("disabled");
			} else {
				if (tipo == "") {
					$("#intervaloEntreRetentativas").val("");
					$("#intervaloEntreRetentativas").attr("disabled","disabled");
					
				} else {
					$("#intervaloEntreRetentativas").removeAttr("disabled");
				}
			}
			
		}
	        
	</script>
</body>
</html>