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

		var idAdquirente = '${idAdquirente}';

		$(".menuReq").hide();

		$("#codigoAfiliacao").setMask('tres-digitos');

	});

    function prosseguir() {

       confirmar("<bean:message key="msg.confirma.adquirente" />", "dialogoPergunta", 400, prosseguirCB);

    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.adquirente.incluido.sucesso"/>", "dialogoSucesso", 400);       
             document.pesquisaAdquirenteForm.method = "post";
             document.pesquisaAdquirenteForm.action = "../configuracao/manterAdquirente.do?operacao=2";
             document.pesquisaAdquirenteForm.submit();  
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
	
        <jsp:useBean id="AdquirenteVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="AdquirenteVH" property="idAdquirente" value="${param.idAdquirente}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.adquirente"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.adquirente" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaAdquirenteForm" id="pesquisaAdquirente"
				action="../configuracao/resultadoAdquirente.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idAdquirente" name="idAdquirente" value="${ConfiguracaoActionForm.idAdquirente}">
				<input type="hidden" id="opcao" name="opcao" value="adquirente">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO DO CONVÊNIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.convenio.adquirente" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoConvenioAdquirente" id="codigoConvenioAdquirente" alt="codigoConvenioAdquirente" 
									value="${ConfiguracaoActionForm.codigoConvenioAdquirente}" maxlength="50"
									class="parametros"/>
								</td>
							</tr>
							<%-- CODIGO AFILIACAO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.codigoAfiliacao"/></label></td>
								<td>
                                    <input 
                                        type="text" 
                                        name="codigoAfiliacao"
                                        id="codigoAfiliacao"
                                        class="parametros"
                                        value="${ConfiguracaoActionForm.codigoAfiliacao}"
                                        maxlength="3"
                                		alt="codigoAfiliacao"
                                     />
								</td>
							</tr>
							<%-- NOME DO ADQUIRENTE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.nome" /></label></td>
								<td colspan="3">
									<input type="text" name="nomeAdquirente" id="nomeAdquirente" alt="nomeAdquirente" 
									value="${ConfiguracaoActionForm.nomeAdquirente}" maxlength="100"
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
			document.pesquisaAdquirenteForm.method = "post";
	        document.pesquisaAdquirenteForm.opcao.value = "prepararVoltar";
	        document.pesquisaAdquirenteForm.action = "../configuracao/prepararAdquirente.do";
	        document.pesquisaAdquirenteForm.submit();
// 	        document.pesquisaAdquirenteForm.method = "post";
// 	        document.pesquisaAdquirenteForm.opcao.value = "listarAdquirente";
// 	        document.pesquisaAdquirenteForm.action = "../configuracao/resultadoAdquirente.do";
// 	        document.pesquisaAdquirenteForm.submit();   
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

			if (document.pesquisaAdquirenteForm.idAdquirente.value) {
				$("form[name=pesquisaAdquirenteForm] input[type=text]:not([readonly]):not([disabled])").val("");

			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterAdquirente.jsp";
			}
			
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(provedor){

        	document.location = "../configuracao/editarAdquirente.do?idPesquisa="+provedor;

        }

        function confirmarExclusao(provedor){
        	prosseguirExclusao(provedor);
        }

        function prosseguirExclusao(provedor) {

        	idAdquirente = provedor;
            
            confirmar("<bean:message key="msg.confirma.exclusao.adquirente" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.adquirente.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaAdquirenteForm.method = "post";
                document.pesquisaAdquirenteForm.idAdquirente.value=idAdquirente;
                document.pesquisaAdquirenteForm.action = "../configuracao/manterAdquirente.do?operacao=3";
                document.pesquisaAdquirenteForm.submit();      
            }
        }

	</script>
</body>
</html>