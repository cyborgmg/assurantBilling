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

		var idCalendario = $( "#periodoDe, #periodoAte" );             
        idCalendario.datepicker({
            showOn: "button",
            changeMonth: true,
            changeYear: true,
            buttonImage: "../img/icones/calendario_22x22.png",
            buttonImageOnly: true,
            showAnim: 'slideDown' 
        });
		
		$(".menuReq").hide();		
		$(".divHidden").hide();
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
	<jsp:useBean id="AssociacaoAdquirenteVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
	
    <jsp:useBean id="ConciliacaoVH" class="br.com.delphos.web.conciliacao.ConciliacaoViewHelper" scope="page"/>
	<jsp:setProperty name="ConciliacaoVH" property="nomeAdquirente" value="${ConciliacaoActionForm.nomeAdquirente}" />
	<jsp:setProperty name="ConciliacaoVH" property="periodoDe" value="${ConciliacaoActionForm.periodoDe}" />
	<jsp:setProperty name="ConciliacaoVH" property="periodoAte" value="${ConciliacaoActionForm.periodoAte}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.movimento" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="conciliacaoForm" id="conciliacaoForm"
				action="../conciliacao/resultadoConciliacao.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idControleConciliacao" name="idControleConciliacao" value="${ConciliacaoActionForm.idControleConciliacao}" >
				<input type="hidden" id="opcao" name="opcao" value="pesquisarConciliacao" />
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- ADQUIRENTE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.adquirente" /></label></td>
									<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="nomeAdquirente" id="nomeAdquirente" style="width: 100%;"
											options="${AssociacaoAdquirenteVH.listarAdquirente()}" optionText="nome"
											optionValue="nome" value="${ConciliacaoActionForm.nomeAdquirente}"
											/>	
									</div>
									</td>
							</tr>
							<%-- PERÍODO ====================================================== --%>
                            <tr>
                                <td class="label ui-widget-header" style="width: 25%;"><label><bean:message key="label.periodo"/></label></td>
                                <td>
                                    <input 
                                        type="text" 
                                        name="periodoDe"
                                        id="periodoDe"
                                        class="periodoDe"
                                        readonly
                                        value="${ConciliacaoActionForm.periodoDe}"
                                        />
                                    &nbsp; a &nbsp;
                                    <input 
                                        type="text" 
                                        name="periodoAte"
                                        id="periodoAte"
                                        class="periodoAte"
                                        readonly
                                        value="${ConciliacaoActionForm.periodoAte}"
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
					<a href="javascript:limpar()">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br> &nbsp;
					</a> 
					<a href="javascript:pesquisar()"> 
						<span class="Icone22x22 IconePesquisar"></span>
						<bean:message key="label.botao.pesquisa" /><br> &nbsp;
					</a>
					<a href="javascript:incluirConciliacao()"> 
						<span class="Icone22x22 IconeAdicionar"></span>
						<bean:message key="label.botao.incluir" /><br> &nbsp;
					</a>
				</td>
			</tr>
		</table>
	</div>
	<logic:messagesNotPresent>
		<c:choose> 
	    <c:when test="${ConciliacaoVH.listarConciliacaoInterfacePorCriterio().size() gt 0 or ConciliacaoActionForm.novaBusca eq 'true'}">
		<c:if test="${ConciliacaoVH.listarConciliacaoInterfacePorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>	
			<div class="barras">
				<div class="tabelas">
		            <div id="aba3">
		            	<div>
	              			<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${ConciliacaoVH.listarConciliacaoInterfacePorCriterio()}" pagesize="10" export="false" excludedParams="*" decorator="br.com.delphos.web.conciliacao.ConciliacaoDecorator" requestURI="">
		                            <display:column property="descricaoAdquirente" 	 titleKey="label.adquirente" style="text-align:left; width:16%; word-break: break-all;" />
		                            <display:column property="dataMovimentoArquivo" 	 titleKey="label.dataMovimentoArquivo" style="text-align:center; width:8%;" />
		                            <display:column property="dataImportacaoArquivo" titleKey="label.dataImportacaoArquivo" style="text-align:center; width:10%;" />
		                            <display:column property="dataProcessamentoArquivo"  titleKey="label.dataProcessamentoArquivo" style="text-align:center; width:8%;" />
		                            <display:column property="valorDeposito" 		 titleKey="label.valorDeposito" style="text-align:right; width:8%;" />
		                            <display:column property="numeroReprocessamento" titleKey="label.numeroReprocessamento" style="text-align:right; width:10%;" />
		                            <display:column property="statusDeposito" 		 titleKey="label.statusDeposito" style="text-align:left; width:9%;" />
		                            <display:column property="mensagemDeposito" 	 titleKey="label.mensagemDeposito" style="text-align:left; width:14%; word-break: break-all;" />
		                            <display:column property="acaoDeposito"    		 titleKey="label.acao" style="text-align:center; width:8%" />
		                        </display:table>
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
		
			mostrarAjuda(<bean:message key="ajuda.caixaDialogo.solicitacao.pesquisar"/>, "dialogoInformacao", 400);
		}
		
		function pesquisar() {

			//$(".divHidden").show();	
			document.conciliacaoForm.novaBusca.value = 'true';
			document.conciliacaoForm.submit();
		}
		
		function limpar() {
		
			document.location = "../conciliacao/prepararConciliacao.do?opcao=1";
			
		}

		function editarConciliacao(conciliacao){

			//document.location = "../conciliacao/manterConciliacao.jsp";
			document.location = "../conciliacao/editarConciliacao.do?idPesquisa="+conciliacao;
			
		}

		function incluirConciliacao(){

			document.location = "../conciliacao/editarConciliacao.do";
			
		}

		function iniciarCalendario(){

			var idCalendario = $( "#dataMovimento, #dataDeposito" );             
            idCalendario.datepicker({
                showOn: "button",
                changeMonth: true,
                changeYear: true,
                buttonImage: "../img/icones/calendario_22x22.png",
                buttonImageOnly: true,
                showAnim: 'slideDown' 
            });			
		}

		function excluirValor(idControleConciliacao) {

			controleConciliacao = idControleConciliacao;
            
            confirmar("<bean:message key="msg.confirma.exclusao.deposito" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){
            	//mostrarMensagem("<bean:message key="msg.deposito.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.conciliacaoForm.method = "post";
                document.conciliacaoForm.idControleConciliacao.value=controleConciliacao;
                document.conciliacaoForm.action = "../conciliacao/manterConciliacao.do?operacao=3";
                document.conciliacaoForm.submit();      
            }
        }

		function solicitarReprocessamento(idControleConciliacao) {

			controleConciliacao = idControleConciliacao;
            
            confirmar("<bean:message key="msg.confirma.execucao.reprocessamento" />", "dialogoPergunta", 400, executarReprocessamento);
        }

        function executarReprocessamento(retorno) {                
            if (retorno){
            	//mostrarMensagem("<bean:message key="msg.execucao.reprocessamento.sucesso"/>", "dialogoSucesso", 400);    
                document.conciliacaoForm.method = "post";
                document.conciliacaoForm.idControleConciliacao.value = controleConciliacao;
                document.conciliacaoForm.action = "../conciliacao/manterConciliacao.do?operacao=4";
                document.conciliacaoForm.submit();      
            }
        }
		
	</script>
</body>
</html>