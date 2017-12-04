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
		$("#valorDeposito").setMask();
		
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

		<c:if test="${empty ConciliacaoActionForm.statusDeposito || (ConciliacaoActionForm.statusDeposito eq 'PEN' && ConciliacaoActionForm.dataProcessamentoArquivo eq null) }" >

			var idCalendario = $( "#dataMovimentoArquivo" );             
	        idCalendario.datepicker({
	            showOn: "button",
	            maxDate: '0',
	            changeMonth: true,
	            changeYear: true,
				beforeShowDay: $.datepicker.noWeekends,
	            buttonImage: "../img/icones/calendario_22x22.png",
	            buttonImageOnly: true,
	            showAnim: 'slideDown' 
	        });

        </c:if>

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
	
    <jsp:useBean id="ConciliacaoVH" class="br.com.delphos.web.conciliacao.ConciliacaoViewHelper" scope="page"/>

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.movimento"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.breadcrumb.movimento" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="conciliacaoForm" id="conciliacaoForm"
				action="../conciliacao/resultadoConciliacao.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConciliacaoActionForm.listaAtiva}"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idControleConciliacao" name="idControleConciliacao" value="${ConciliacaoActionForm.idControleConciliacao}">
				<input type="hidden" id="opcao" name="opcao" value="manterConciliacao" />
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConciliacaoActionForm.listaAtiva}"/>
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.adquirente" /></label></td>
									<td colspan="3">
									<c:if test="${empty ConciliacaoActionForm.statusDeposito || (ConciliacaoActionForm.statusDeposito eq 'PEN' && ConciliacaoActionForm.dataProcessamentoArquivo eq null) }" >
										<div class="formataCampoSelectForm divSelectConsulta">
											<dph:dropdown name="nomeAdquirente" id="nomeAdquirente" style="width: 100%;"
												options="${ConciliacaoVH.listarAdquirentesAtivosConciliacao()}" optionText="nome"
												optionValue="nome" value="${ConciliacaoActionForm.nomeAdquirente}"
												/>
										</div>	
									</c:if>
									<c:if test="${(not empty ConciliacaoActionForm.statusDeposito && ConciliacaoActionForm.statusDeposito ne 'PEN') || ConciliacaoActionForm.dataProcessamentoArquivo ne null }" >
	                                    <input 
	                                        type="text" 
	                                        name="nomeAdquirente"
	                                        id="nomeAdquirente"
	                                        class="parametros"
	                                        value="${ConciliacaoActionForm.nomeAdquirente}"
											readonly
	                                     />
									</c:if>	

								</td>
							</tr>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.dataMovimentoArquivo"/></label></td>
								<td>
                                    <input 
                                        type="text" 
                                        name="dataMovimentoArquivo"
                                        id="dataMovimentoArquivo"
                                        class="periodoDe"
                                        value="${ConciliacaoActionForm.dataMovimentoArquivo}"
										readonly
                                     />
								</td>
							</tr>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.valorDeposito"/></label></td>
								<td>
									<input type="text" name="valorDeposito" id="valorDeposito" class="parametros" 
									style="width: 160px;" value="${ConciliacaoActionForm.valorDeposito}" alt="decimal"
									<c:if test="${ConciliacaoActionForm.statusDeposito ne null && ConciliacaoActionForm.statusDeposito ne 'PEN' && ConciliacaoActionForm.statusDeposito ne 'RVL' }" >
									readonly
									</c:if>
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
				<td class="botoesRetornar" >
	                <a href="javascript:voltar();">
	                    <span class="Icone22x22 IconeNavegacaoRetornar" ></span>
	                    <bean:message key="label.botao.retornar"/><br> &nbsp;
	                </a>
                </td>
				<td class="botoesCentro">&nbsp;</td>
				<td class="botoesAvancar">
				<c:if test="${empty ConciliacaoActionForm.idControleConciliacao}" >
					<a href="javascript:limpar('c')">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br> &nbsp;
					</a> 
				</c:if>
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

			//document.pesquisaLogsForm.submit();
			//exibirRetornoPesquisa();
		}

		function limpar(origem) {

			if (document.conciliacaoForm.idControleConciliacao.value) {
				$("form[name=conciliacaoForm] input[type=text]:not([readonly]):not([disabled])").val("");
				document.conciliacaoForm.nomeAdquirente.value="";
				$("#dataMovimentoArquivo").removeAttr("readonly");
				$("#dataMovimentoArquivo").val("");
				$("#dataMovimentoArquivo").attr("readonly","readonly");
			} else {
				document.location = "../conciliacao/manterConciliacao.jsp";
			}
			
		}

        function prosseguir() {

            confirmar("<bean:message key="msg.confirma.inclusao.deposito" />", "dialogoPergunta", 400, prosseguirCB);
    		        
        }

        function prosseguirCB(retorno) {                
            if (retorno){                  
                 document.conciliacaoForm.method = "post";
                 document.conciliacaoForm.opcao.value="manterConciliacao";
                 document.conciliacaoForm.action = "../conciliacao/manterConciliacao.do?operacao=2";
                 document.conciliacaoForm.submit();  
            }
        }

        function voltar() {
            document.conciliacaoForm.method = "post";
            document.conciliacaoForm.opcao.value = "listarConciliacao";
            document.conciliacaoForm.novaBusca.value = "false";
            document.conciliacaoForm.action = "../conciliacao/resultadoConciliacao.do";
            document.conciliacaoForm.submit();   
        }

	</script>
</body>
</html>