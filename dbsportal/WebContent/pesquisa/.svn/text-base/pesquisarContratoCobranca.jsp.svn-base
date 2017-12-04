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
                $("#codigoEmpresaContratoCobranca").val(ui.item.id);
        
            }
        });		
        
        $("#provedorContratoCobranca").autocomplete({            
            source: function(request, response) {  
                var descProvedor = $("#provedorContratoCobranca").val();
                $.ajax({
                    type: "GET",
                    url: "../dbs/autocomplete",
                    data: "op=4&descProvedor=" + descProvedor,
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
                $("#codigoProvedorContratoCobranca").val(ui.item.id);
        
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
	
    <jsp:useBean id="ContratoCobrancaVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
    <jsp:setProperty name="ContratoCobrancaVH" property="idContratoCobranca" value="${param.idContratoCobranca}" />
    <jsp:setProperty name="ContratoCobrancaVH" property="codigoEmpresaContratoCobranca" 
    value="${ConfiguracaoActionForm.empresa ne null ? ConfiguracaoActionForm.codigoEmpresaContratoCobranca : ''}" />
    <jsp:setProperty name="ContratoCobrancaVH" property="codigoProvedorContratoCobranca" 
    value="${ConfiguracaoActionForm.provedorContratoCobranca ne null ? ConfiguracaoActionForm.codigoProvedorContratoCobranca : ''}" />
    <jsp:setProperty name="ContratoCobrancaVH" property="descricaoContratoCobranca" value="${ConfiguracaoActionForm.descricaoContratoCobranca}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.contratoCobranca" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaContratoCobrancaForm" id="pesquisaContratoCobranca"
				action="../configuracao/resultadoContratoCobranca.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idContratoCobranca" name="idContratoCobranca" value="${ConfiguracaoActionForm.idContratoCobranca}">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaContratoCobranca">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DO CONTRATO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.contratoCobranca" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoContratoCobranca" id="descricaoContratoCobranca" alt="descricaoContratoCobranca" 
									value="${ConfiguracaoActionForm.descricaoContratoCobranca}" maxlength="500"
									class="parametros"/>
								</td>
							</tr>
							<%-- EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.descricao.empresa" /></label></td>
								<td colspan="3">
							        <input 
                                    type="hidden" 
                                    name="codigoEmpresaContratoCobranca" 
                                    id="codigoEmpresaContratoCobranca" 
                                    value="${ConfiguracaoActionForm.codigoEmpresaContratoCobranca}"
                                    />
									<input type="text" name="empresa" id="empresa" alt="empresa" 
									value="${ConfiguracaoActionForm.empresa}" maxlength="100" class="parametros"/>
                                    <span class="IconeObs tooltipTop" title="<bean:message key="ajuda.autoComplete.empresa"/>" >
                                        <span class="Icone16x16 IconeAjuda" ></span>                           
                                    </span>
								</td>
							</tr>
							<%-- PROVEDOR ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descr.provedor" /></label></td>
								<td colspan="3">
							        <input 
                                    type="hidden" 
                                    name="codigoProvedorContratoCobranca" 
                                    id="codigoProvedorContratoCobranca" 
                                    value="${ConfiguracaoActionForm.codigoProvedorContratoCobranca}"
                                    />
									<input type="text" name="provedorContratoCobranca" id="provedorContratoCobranca" alt="provedorContratoCobranca" 
									value="${ConfiguracaoActionForm.provedorContratoCobranca}" maxlength="100"
									class="parametros"/>
                                    <span class="IconeObs tooltipTop" title="<bean:message key="ajuda.autoComplete.provedor"/>" >
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
    <c:when test="${ContratoCobrancaVH.listarContratoCobrancaPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
	<c:if test="${ContratoCobrancaVH.listarContratoCobrancaPorCriterio().size() gt 0}">
		<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
	</c:if>		
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${ContratoCobrancaVH.listarContratoCobrancaPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="codigoEmpresaContratoCobranca" titleKey="label.codigoEmpresa.displaytag" style="text-align:center; width:10%; word-break: break-all;" />
	                            <display:column property="codigoMeioPagamentoContratoCobranca" titleKey="label.codigoMeioPagamento.displaytag" style="text-align:center; width:9%; word-break: break-all;" />
	                            <display:column property="codigoProvedorContratoCobranca" titleKey="label.codigoProvedor.displaytag" style="text-align:center; width:9%; word-break: break-all;" />
	                            <display:column property="descricaoContratoCobranca" titleKey="label.descricao.contratoCobranca" style="text-align:left; width:19%; word-break: break-all;" />
	                            <display:column property="prazoPagamentoDiasContratoCobranca" titleKey="label.prazo.pagamento.dias.contratoCobranca" style="text-align:right; width:10%; word-break: break-all;" />
	                            <display:column property="tipoTransacaoProvedorContratoCobranca" titleKey="label.tipo.transacao.provedor.contratoCobranca" style="text-align:left; width:10%; word-break: break-all;" />
	                            <display:column property="inicioVigenciaContratoCobranca" titleKey="label.inicio.vigencia.contratoCobranca" style="text-align:center; width:7%; word-break: break-all;" />
	                            <display:column property="fimVigenciaContratoCobranca" titleKey="label.fim.vigencia.contratoCobranca" style="text-align:center; width:7%; word-break: break-all;" />
	                            <display:column property="acaoContratoCobranca"    titleKey="label.acao"    style="text-align:center; width:9%" />
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

			if (myTrim($("#empresa").val()).length == 0) {
				$("#codigoEmpresaContratoCobranca").val("")
			}
			if (myTrim($("#provedorContratoCobranca").val()).length == 0) {
				$("#codigoProvedorContratoCobranca").val("")
			}

			//$(".divHidden").show();	
			document.pesquisaContratoCobrancaForm.submit();
		}

		function associarAdquirente(contrato) {
			document.location = "../configuracao/associarAdquirente.do?idContrato="+ contrato + "&opcao=adquirenteBandeiraSemValidacao";
		}

		function configurarAutorizacao(contrato) {
			//document.location = "../configuracao/associarAdquirente.do?idContrato="+ contrato;
						document.location = "../configuracao/associarAdquirente.do?idContrato="+ 
						contrato + 
			"&opcao=respostaAutorizacao";
			//document.location = "../configuracao/manterRespostaAutorizacao.jsp";
		}
		
		function limpar() {
		
			document.location = "../configuracao/prepararContratoCobranca.do?opcao=1";
			
		}

		function exibirDetalhe(contratoCobranca){

        	document.location = "../configuracao/editarContratoCobranca.do?idPesquisa="+contratoCobranca;

        }

		function incluir(){

        	document.location = "../configuracao/prepararContratoCobranca.do?opcao=incluir";

        }

        function confirmarExclusao(contratoCobranca){
        	prosseguirExclusao(contratoCobranca);
        }

        function prosseguirExclusao(contratoCobranca) {

        	idContratoCobranca = contratoCobranca;
            
            confirmar("<bean:message key="msg.confirma.exclusao.contratoCobranca" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.contratoCobranca.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaContratoCobrancaForm.method = "post";
                document.pesquisaContratoCobrancaForm.idContratoCobranca.value=idContratoCobranca;
                document.pesquisaContratoCobrancaForm.action = "../configuracao/manterContratoCobranca.do?operacao=3";
                document.pesquisaContratoCobrancaForm.submit();      
            }
        }
	</script>
</body>
</html>