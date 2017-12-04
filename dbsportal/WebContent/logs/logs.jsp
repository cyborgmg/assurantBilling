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
	var identificadorObjetoOperacao = "";
	var idLog = "";

	var tipoOperacao = "";
	var tipoObjetoOperacao = "";

	var codEmpresa = "";
	var idProduto = "";
	var idSistema = "";

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

        var idCalendario = $( "#periodoDe, #periodoAte" );             
        idCalendario.datepicker({
            showOn: "button",
            changeMonth: true,
            changeYear: true,
            minDate: '-30d',
            maxDate: '0',
            buttonImage: "../img/icones/calendario_22x22.png",
            buttonImageOnly: true,
            showAnim: 'slideDown' 
        });
        
        $(".ui-datepicker-trigger").tipTip({
            maxWidth: "auto",
            edgeOffset: 10,
            defaultPosition: "bottom"
        });

		codEmpresa = '${codEmpresa}';
		idProduto = '${idProduto}';
		idSistema = '${idSistema}';

		if (codEmpresa != null && myTrim(codEmpresa).length > 0) {
			$("select[name=empresa]").val(codEmpresa);
			if (idProduto != null && myTrim(idProduto).length > 0) {
				$("select[name=produto]").val(idProduto);
				if (idSistema != null && myTrim(idSistema).length > 0) {
					$("select[name=sistema]").val(idSistema);
				}
			}
		}

		$(".menuReq").hide();

		idLog = '${idLog}';

		tipoOperacao = '${tipoOperacao}';
		tipoObjetoOperacao = '${tipoObjetoOperacao}';
		identificadorObjetoOperacao = '${identificadorObjetoOperacao}';

		if (tipoOperacao.value != null && myTrim(tipoOperacao.value).length > 0) {
			$("select[name=tipoOperacao]").val(tipoOperacao.value);
			if (tipoObjetoOperacao.value != null && myTrim(tipoObjetoOperacao.value).length > 0) {
				verificarTipoObjeto();
				$("select[name=tipoObjetoOperacao]").val(tipoObjetoOperacao.value);
				if (identificadorObjetoOperacao != null && myTrim(identificadorObjetoOperacao).length > 0) {
					//$("input[name=identificadorObjetoOperacao]").val(identificadorObjetoOperacao);
					$("input[name=identificadorObjetoOperacao]").val(identificadorObjetoOperacao);
				}
			}
		}

		if($("select[name=tipoOperacao]").val() == ""){
			$("select[name=tipoObjetoOperacao], input[name=identificadorObjetoOperacao]").attr("disabled","disabled");
		}
		
		if($("select[name=tipoObjetoOperacao]").val() == ""){
			$("input[name=identificadorObjetoOperacao]").attr("disabled","disabled");
		}

	});


</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="LogVH" class="br.com.delphos.web.logs.LogViewHelper" scope="page"/>
    <jsp:setProperty name="LogVH" property="idLog" value="${param.idLog}" />
    <jsp:setProperty name="LogVH" property="tipoOperacao" value="${LogsActionForm.tipoOperacao}" />
    <jsp:setProperty name="LogVH" property="tipoObjetoOperacao" value="${LogsActionForm.tipoObjetoOperacao}" />
    <jsp:setProperty name="LogVH" property="identificadorObjetoOperacao" value="${LogsActionForm.identificadorObjetoOperacao}" />
    <jsp:setProperty name="LogVH" property="usuario" value="${LogsActionForm.usuario}" />
    <jsp:setProperty name="LogVH" property="periodoDe" value="${LogsActionForm.periodoDe}" />
    <jsp:setProperty name="LogVH" property="periodoAte" value="${LogsActionForm.periodoAte}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.consultar.logs" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaLogsForm" id="pesquisaLogs"
				action="../logs/resultado.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idLog" name="idLog">
				
                <div id="divHidden2" style="display: none">
                    <div class="DisplayTag AjusteDisplay rateio">
                        <display:table name="${LogVH.detalheLog()}" decorator="br.com.delphos.web.logs.LogDecorator" requestURI="">
                            <display:column property="dataOperacao" titleKey="label.dataOperacao" style="text-align:center; width:10%; word-break: break-all;" />
                            <display:column property="usuario" titleKey="label.nome.usuario" style="text-align:left; width:30%; word-break: break-all;" />
                            <display:column property="tipoOperacao" titleKey="label.tipo.operacao" style="text-align:left; width:15%; word-break: break-word;" />
                            <display:column property="tipoObjetoOperacao" titleKey="label.tipo.objeto.operacao" style="text-align:left; width:15%; word-break: break-word;" />
                            <display:column property="identificadorObjetoOperacao" titleKey="label.identificadorObjetoOperacao" style="text-align:left; width:15%; word-break: break-word;" />
                        </display:table>
                    </div>
                </div>
                

				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- TIPO DE OPERACAO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.tipo.operacao" /></label></td>
								<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta" id="divTipoSolicitacao">
										<select name="tipoOperacao" id="tipoOperacao" alt="tipoOperacao"
										onchange="javascript: habilitarTipoObjeto();" style="width: 100%">
										<option value=""
										<c:choose> 
				                        <c:when test="${LogsActionForm.tipoOperacao eq ''}">
				                            selected
				                        </c:when>
				                        </c:choose>
										 > - Selecione - </option>
										<logic:iterate id="tipoOperacao" name="listaTipoOperacao" scope="request">  
										    <option 
										    <c:out value="${tipoOperacao.codigo eq LogsActionForm.tipoOperacao ? 'selected' : ''}"></c:out>
										    value="<jsp:getProperty name="tipoOperacao" property="codigo"/>"><jsp:getProperty name="tipoOperacao" property="descricao"/></option>  
										</logic:iterate>
										</select>  
									</div>
								</td>
							</tr>
							<%-- TIPO DE OBJETO DA OPERACAO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.tipo.objeto.operacao" /></label></td>
								<td colspan="3">
									
									<div class="formataCampoSelectForm divSelectConsulta" id="divTipoSolicitacao">
										<select name="tipoObjetoOperacao" id="tipoObjetoOperacao" alt="tipoObjetoOperacao"
										onchange="javascript: verificarTipoObjeto();" style="width: 100%">
										<option value=""
										<c:choose> 
				                        <c:when test="${LogsActionForm.tipoObjetoOperacao eq ''}">
				                            selected
				                        </c:when>
				                        </c:choose>
										 > - Selecione - </option>
										<logic:iterate id="objetoOperacao" name="listaObjeto" scope="request">  
										    <option 
										    <c:out value="${objetoOperacao.valor eq LogsActionForm.tipoObjetoOperacao ? 'selected' : ''}"></c:out>
										    value="<jsp:getProperty name="objetoOperacao" property="valor"/>"><jsp:getProperty name="objetoOperacao" property="descricao"/></option>  
										</logic:iterate>
										</select>  
									</div>
									
								</td>
							</tr>
							<%-- IDENTIFICADOR DO OBJETO DA OPERACAO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.identificador.objeto.operacao" /></label></td>
								<td colspan="3">
									<input type="text" name="identificadorObjetoOperacao" id="identificadorObjetoOperacao" 
									alt="identificadorObjetoOperacao" value="${LogsActionForm.identificadorObjetoOperacao}" maxlength="500"
									class="parametros"/>
								</td>
							</tr>

							<%-- USUARIO ====================================================== --%>
							<tr>
								<td class="label ui-widget-header">
									<label><bean:message key="label.nome.usuario" /></label>
								</td>
								<td>
									<input type="text" name="usuario" id="usuario" alt="usuario" maxlength="255"
									class="parametros" value="${LogsActionForm.usuario}"/>
								</td>
							</tr>
                            <%-- PERÍODO ====================================================== --%>
                            <tr class="config">
                                <td class="label ui-widget-header"><label><bean:message key="label.periodo"/></label></td>
                                <td>
                                    <input 
                                        type="text" 
                                        name="periodoDe"
                                        id="periodoDe"
                                        class="periodoDe"
                                        readonly
                                         value="${LogsActionForm.periodoDe}"
                                        />
                                    &nbsp; a &nbsp;
                                    <input 
                                        type="text" 
                                        name="periodoAte"
                                        id="periodoAte"
                                        class="periodoAte"
                                        readonly
                                         value="${LogsActionForm.periodoAte}"
                                        />
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
					<a href="javascript:limpar('c')">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br> &nbsp;
					</a> 
					<a href="javascript:pesquisar()"> 
						<span class="Icone22x22 IconePesquisar"></span>
						<bean:message key="label.botao.pesquisa" /><br> &nbsp;
					</a>
				</td>
			</tr>
		</table>
	</div>
	<logic:messagesNotPresent>
	<c:choose> 
    <c:when test="${LogVH.listarLogOperacaoUsuarioPorCriterio().size() gt 0 or LogsActionForm.novaBusca eq 'true'}">
		<div class="barras">
			<div class="tabelas">
	                               <hr style="margin: 2% 0%"/>
	                               <div id="divHidden">
	                                   <div id="aba3">
	                                       <div>
						                    <div class="DisplayTag AjusteDisplay rateio">
						                        <display:table name="${LogVH.listarLogOperacaoUsuarioPorCriterio()}" pagesize="10" decorator="br.com.delphos.web.logs.LogDecorator" requestURI="">
						                            <display:column property="dataOperacao" titleKey="label.dataOperacao" style="text-align:center; width:14%; word-break: break-all;" />
						                            <display:column property="usuario" titleKey="label.nome.usuario" style="text-align:left; width:20%; word-break: break-all;" />
						                            <display:column property="tipoOperacao" titleKey="label.tipo.operacao" style="text-align:left; width:9%; word-break: break-word;" />
						                            <display:column property="tipoObjetoOperacao" titleKey="label.tipo.objeto.operacao" style="text-align:left; width:16%; word-break: break-word;" />
						                            <display:column property="identificadorObjetoOperacao" titleKey="label.identificadorObjetoOperacao" style="text-align:left; width:20%; word-break: break-word;" />
						                            <display:column property="acaoLog"    titleKey="label.acao"    style="text-align:center; width:5%" />
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
			
			document.pesquisaLogsForm.novaBusca.value = 'true';	
			document.pesquisaLogsForm.submit();
		}
		
		function limpar(origem) {
		
			document.location = "../logs/prepararConsultaLogs.do";
			
		}

        function exibirDetalheLog(log){

        	popularLogs(log);
            
            $("#divHidden2").dialog({
                closeOnEscape: false,
                open: function(event, ui) {
                    $(".ui-dialog-titlebar-close").hide();
                },
                title: "Logs",
                resizable: false,
                height:300,
                modal: true,
                width:800,
                draggable: false,
                buttons: {
                    Fechar: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });
        }

		function popularLogs(idLog) {

			$("div[id=divHidden2] > div > table > tbody").html("");
			
			$.ajax({
		        url:"../dbs/listas",
		        data:"op=8&cod="+idLog,
		        success:function(logs){
		        	$("div[id=divHidden2] > div > table > tbody").html(logs);
		        }
		    });
		}

		function popularIdentificadorObjetoOperacao(idObjetoOperacao) {
			$.ajax({
		        url:"../dbs/listas",
		        data:"op=9&objeto="+idObjetoOperacao,
		        success:function(logs){
		        	if (logs.indexOf("option") !=-1) {
		        		$("input[name=identificadorObjetoOperacao]").html(logs);
		        	} else {
		            	$("input[name=identificadorObjetoOperacao]").attr("disabled","disabled");
				    }
					if (identificadorObjetoOperacao != null) {
						$("input[name=identificadorObjetoOperacao]").val(identificadorObjetoOperacao);
					}
		        }
		    });
		}

		function habilitarTipoObjeto() {

			if (myTrim($("select[name=tipoOperacao]").val()).length == 0) {
				$("select[name=tipoObjetoOperacao]").removeAttr("disabled");
				$("select[name=tipoObjetoOperacao]").val("");
				$("select[name=tipoObjetoOperacao]").attr("disabled","disabled");
				$("input[name=identificadorObjetoOperacao]").removeAttr("disabled");
				$("input[name=identificadorObjetoOperacao]").val("");
				$("input[name=identificadorObjetoOperacao]").attr("disabled","disabled");
			} else {
				$("select[name=tipoObjetoOperacao]").removeAttr("disabled");
			}
		}

		function verificarTipoObjeto() {
			if (myTrim($("select[name=tipoObjetoOperacao]").val()).length > 1) {
				$("input[name=identificadorObjetoOperacao]").removeAttr("disabled");

			//	popularIdentificadorObjetoOperacao(myTrim($("select[name=tipoObjetoOperacao]").val()));

			} else {
				$("input[name=identificadorObjetoOperacao]").removeAttr("disabled");
				$("input[name=identificadorObjetoOperacao]").val("");
				$("input[name=identificadorObjetoOperacao]").attr("disabled","disabled");
			}
		}
		
		function habilitarIdentificadorTipoObjeto() {
			$("input[name=identificadorObjetoOperacao]").removeAttr("disabled");
		}
		
	</script>
</body>
</html>