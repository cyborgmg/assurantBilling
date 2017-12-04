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

	var idProvedorMeioPagamento = '${idProvedorMeioPagamento}';

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

	});

</script>
<dbs:dialogosLista />

</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
        <jsp:useBean id="ProvedorMeioPagamentoVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="ProvedorMeioPagamentoVH" property="idProvedorMeioPagamento" value="${param.idProvedorMeioPagamento}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.provedor"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.provedor.meio.pagamento" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaProvedorMeioPagamentoForm" id="pesquisaProvedorMeioPagamento"
				action="../configuracao/resultadoProvedorMeioPagamento.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="idProvedorMeioPagamento" name="idProvedorMeioPagamento" value="${ConfiguracaoActionForm.idProvedorMeioPagamento}">
				<input type="hidden" id="opcao" name="opcao" value="provedorMeioPagamento">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CODIGO PROVEDOR ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message key="label.codigoProvedor" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoProvedor" id="codigoProvedor" alt="codigoProvedor" 
									value="${ConfiguracaoActionForm.codigoProvedor}" maxlength="5"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRIÇÃO DO PROVEDOR ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.descricaoProvedor" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoProvedor" id="descricaoProvedor" alt="descricaoProvedor" 
									value="${ConfiguracaoActionForm.descricaoProvedor}" maxlength="100"
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
		function voltar(){
	        //document.pesquisaProvedorMeioPagamentoForm.method = "post";
	        //document.pesquisaProvedorMeioPagamentoForm.opcao.value = "listarProvedor";
	        //document.pesquisaProvedorMeioPagamentoForm.action = "../configuracao/resultadoProvedorMeioPagamento.do";
	        //document.pesquisaProvedorMeioPagamentoForm.submit();   

			document.pesquisaProvedorMeioPagamentoForm.method = "post";
	        document.pesquisaProvedorMeioPagamentoForm.opcao.value = "prepararVoltar";
	        document.pesquisaProvedorMeioPagamentoForm.action = "../configuracao/prepararProvedorMeioPagamento.do";
	        document.pesquisaProvedorMeioPagamentoForm.submit();
	        
	    }
	
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

			if (document.pesquisaProvedorMeioPagamentoForm.idProvedorMeioPagamento.value) {
				$("form[name=pesquisaProvedorMeioPagamentoForm] input[type=text]:not([readonly]):not([disabled])").val("");

			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterProvedorMeioPagamento.jsp";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(provedor){

        	document.location = "../configuracao/editarProvedorMeioPagamento.do?idPesquisa="+provedor;

        }

        function confirmarExclusao(provedor){
        	prosseguirExclusao(provedor);
        }

        function prosseguirExclusao(provedor) {

        	idProvedorMeioPagamento = provedor;
            
            confirmar("<bean:message key="msg.confirma.exclusao.provedorMeioPagamento" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.provedorMeioPagamento.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaProvedorMeioPagamentoForm.method = "post";
                document.pesquisaProvedorMeioPagamentoForm.action = "../configuracao/manterMeioPagamento.do?operacao=3";
                document.pesquisaProvedorMeioPagamentoForm.submit();      
            }
        }

        function prosseguir() {

        	confirmar("<bean:message key="msg.confirma.provedorMeioPagamento" />", "dialogoPergunta", 400, prosseguirCB);
        }

        function prosseguirCB(retorno) {                
            if (retorno){                  
            	//mostrarMensagem("<bean:message key="msg.provedorMeioPagamento.incluido.sucesso"/>", "dialogoSucesso", 400);       
                document.pesquisaProvedorMeioPagamentoForm.method = "post";
                document.pesquisaProvedorMeioPagamentoForm.action = "../configuracao/manterProvedorMeioPagamento.do?operacao=2";
                document.pesquisaProvedorMeioPagamentoForm.submit();  
            }
        }
	</script>
</body>
</html>