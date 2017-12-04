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
	var codEmpresa = '${codEmpresa}';
	var idMeioPagamento = '${idMeioPagamento}';
	var idSistema = '${idSistema}';

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

		if (codEmpresa != null && myTrim(codEmpresa).length > 0) {
			$("select[name=empresa]").val(codEmpresa);
			if (idProduto != null && myTrim(idProduto).length > 0) {
				$("select[name=produto]").val(idProduto);
				if (idSistema != null && myTrim(idSistema).length > 0) {
					$("select[name=sistema]").val(idSistema);
				}
			}
		}

		if($("select[name=tipoOperacao]").val() == ""){
			$("select[name=tipoObjetoOperacao], select[name=identificadorObjetoOperacao]").attr("disabled","disabled");
		}
		
		if($("select[name=tipoObjetoOperacao]").val() == ""){
			$("select[name=identificadorObjetoOperacao]").attr("disabled","disabled");
		}	

		$(".menuReq").hide();

		$("#limiteDiasRetentativa").setMask('tres-digitos');
	});

</script>
<dbs:dialogosLista />

</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
        <jsp:useBean id="MeioPagamentoVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="MeioPagamentoVH" property="idMeioPagamento" value="${param.idMeioPagamento}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.meio.pagamento"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.meio.pagamento" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaMeioPagamentoForm" id="pesquisaMeioPagamento"
				action="../configuracao/resultadoMeioPagamento.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="idMeioPagamento" name="idMeioPagamento" value="${ConfiguracaoActionForm.idMeioPagamento}">
				<input type="hidden" id="opcao" name="opcao" value="meioPagamento">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CODIGO MEIO DE PAGAMENTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message key="label.codigoMeioPagamento" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoMeioPagamento" id="codigoMeioPagamento" alt="codigoMeioPagamento" 
									value="${ConfiguracaoActionForm.codigoMeioPagamento}" maxlength="5"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRICAO DO MEIO DE PAGAMENTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.descricaoMeioPagamento" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoMeioPagamento" id="descricaoMeioPagamento" alt="descricaoMeioPagamento" 
									value="${ConfiguracaoActionForm.descricaoMeioPagamento}" maxlength="100"
									class="parametros"/>									
								</td>
							</tr>
							<%-- LIMITE DE DIAS PARA RETENTATIVA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.limiteDiasRetentativa" /></label></td>
								<td colspan="3">
									<input type="text" name="limiteDiasRetentativa" id="limiteDiasRetentativa" alt="limiteDiasRetentativa" 
									value="${ConfiguracaoActionForm.limiteDiasRetentativa}" maxlength="3"
									class="parametros" onkeypress="return SomenteNumero(event);"/>
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
		
		function pesquisar() {

			//document.pesquisaLogsForm.submit();
			exibirRetornoPesquisaLog();
		}
		
		function limpar(origem) {

			if (document.pesquisaMeioPagamentoForm.idMeioPagamento.value) {
				$("form[name=pesquisaMeioPagamentoForm] input[type=text]:not([readonly]):not([disabled])").val("");

			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterMeioPagamento.jsp";
			}
		}
		
        function exibirRetornoPesquisaLog(){
        	$("#divHidden").show();
        }
			
        function exibirDetalheMeioPagamento(meioPagamento){

        	document.location = "../configuracao/editarMeioPagamento.do?idPesquisa="+meioPagamento;

        }

        function confirmarExclusaoMeioPagamento(meioPagamento){
        	prosseguirExclusao(meioPagamento);
        }

        function prosseguirExclusao(meioPagamento) {

        	idMeioPagamento = meioPagamento;
            
            confirmar("<bean:message key="msg.confirma.exclusao.meioPagamento" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.meioPagamento.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaMeioPagamentoForm.method = "post";
                //document.pesquisaMeioPagamentoForm.idMeioPagamento.value=idMeioPagamento;
                document.pesquisaMeioPagamentoForm.action = "../configuracao/manterMeioPagamento.do?operacao=3";
                document.pesquisaMeioPagamentoForm.submit();      
            }
        }
        
	    function prosseguir() {

            confirmar("<bean:message key="msg.confirma.meioPagamento" />", "dialogoPergunta", 400, prosseguirCB);	
	    }

	    function prosseguirCB(retorno) {                
	        if (retorno){                  
	        	//mostrarMensagem("<bean:message key="msg.meioPagamento.incluido.sucesso"/>", "dialogoSucesso", 400);       
	            document.pesquisaMeioPagamentoForm.method = "post";
	            //document.pesquisaMeioPagamentoForm.idMeioPagamento.value=idMeioPagamento;
	            document.pesquisaMeioPagamentoForm.action = "../configuracao/manterMeioPagamento.do?operacao=2";
	            document.pesquisaMeioPagamentoForm.submit();  
	        }
	    }

	    function SomenteNumero(e){
	    	var tecla=(window.event)?event.keyCode:e.which;   
	    	if((tecla>47 && tecla<58)) return true;
	    	else{
	    		if (tecla==8 || tecla==0) return true;
	    		else  return false;
	    		}
	    	}

    	function voltar(){
             document.pesquisaMeioPagamentoForm.method = "post";
             document.pesquisaMeioPagamentoForm.opcao.value = "listarMeioPagamento";
             document.pesquisaMeioPagamentoForm.action = "../configuracao/resultadoMeioPagamento.do";
             document.pesquisaMeioPagamentoForm.submit();   
        }

	</script>
</body>
</html>