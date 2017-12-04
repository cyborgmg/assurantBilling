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

		var idBandeira = '${idBandeira}';

		$(".menuReq").hide();
				
	});

    function prosseguir() {

    	confirmar("<bean:message key="msg.confirma.bandeira" />", "dialogoPergunta", 400, prosseguirCB);
        
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.bandeira.incluido.sucesso"/>", "dialogoSucesso", 400);       
             document.pesquisaBandeiraForm.method = "post";
             document.pesquisaBandeiraForm.action = "../configuracao/manterBandeira.do?operacao=2";
             document.pesquisaBandeiraForm.submit();  
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
	
        <jsp:useBean id="BandeiraVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="BandeiraVH" property="idBandeira" value="${param.idBandeira}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.bandeira"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.bandeira" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaBandeiraForm" id="pesquisaBandeira"
				action="../configuracao/resultadoBandeira.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idBandeira" name="idBandeira" value="${ConfiguracaoActionForm.idBandeira}">
				<input type="hidden" id="opcao" name="opcao" value="bandeira">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO DA BANDEIRA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.bandeira" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoBandeira" id="codigoBandeira" alt="codigoBandeira" 
									value="${ConfiguracaoActionForm.codigoBandeira}" maxlength="10"
									class="parametros"/>
								</td>
							</tr>
							<%-- NOME DA BANDEIRA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.nome" /></label></td>
								<td colspan="3">
									<input type="text" name="nomeBandeira" id="nomeBandeira" alt="nomeBandeira" 
									value="${ConfiguracaoActionForm.nomeBandeira}" maxlength="100"
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
			document.pesquisaBandeiraForm.method = "post";
	        document.pesquisaBandeiraForm.opcao.value = "prepararVoltar";
	        document.pesquisaBandeiraForm.action = "../configuracao/prepararBandeira.do";
	        document.pesquisaBandeiraForm.submit();
// 	        document.pesquisaBandeiraForm.method = "post";
// 	        document.pesquisaBandeiraForm.opcao.value = "listarBandeira";
// 	        document.pesquisaBandeiraForm.action = "../configuracao/resultadoBandeira.do";
// 	        document.pesquisaBandeiraForm.submit();   
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

			if (document.pesquisaBandeiraForm.idBandeira.value) {
				$("form[name=pesquisaBandeiraForm] input[type=text]:not([readonly]):not([disabled])").val("");

			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterBandeira.jsp";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(bandeira){

        	document.location = "../configuracao/editarBandeira.do?idPesquisa="+bandeira;

        }

        function confirmarExclusao(bandeira){
        	prosseguirExclusao(bandeira);
        }

        function prosseguirExclusao(bandeira) {

        	idBandeira = bandeira;
            
            confirmar("<bean:message key="msg.confirma.exclusao.bandeira" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.bandeira.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaBandeiraForm.method = "post";
                document.pesquisaBandeiraForm.idBandeira.value=idBandeira;
                document.pesquisaBandeiraForm.action = "../configuracao/manterBandeira.do?operacao=3";
                document.pesquisaBandeiraForm.submit();      
            }
        }

	</script>
</body>
</html>