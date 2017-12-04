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

		var idSistema = '${idSistema}';

		$(".menuReq").hide();

	});

    function prosseguir() {

        confirmar("<bean:message key="msg.confirma.sistema" />", "dialogoPergunta", 400, prosseguirCB);
                
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.sistema.incluido.sucesso"/>", "dialogoSucesso", 400);    
        	//document.location = "../configuracao/manterSistema.jsp";   
            document.pesquisaSistemaForm.method = "post";
            document.pesquisaSistemaForm.action = "../configuracao/manterSistema.do?operacao=2";
            document.pesquisaSistemaForm.submit();  
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
	
        <jsp:useBean id="SistemaVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="SistemaVH" property="idSistema" value="${param.idSistema}" />
        
        <jsp:useBean id="ListaValorVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
			    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.sistema" />&nbsp;>&nbsp;</a>
                <bean:message key="label.sistema" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaSistemaForm" id="pesquisaSistema"
				action="../configuracao/resultadoSistema.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idSistema" name="idSistema" value="${ConfiguracaoActionForm.idSistema}">
				<input type="hidden" id="opcao" name="opcao" value="sistema">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- ASSOCIAR EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarEmpresa" /></label></td>
								<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="idEmpresa" id="idEmpresa" style="width: 100%;"
											options="${SistemaVH.listarEmpresa()}" optionText="descricao"
											optionValue="id" value="${ConfiguracaoActionForm.idEmpresa}"
											/>
									</div>
								</td>
							</tr>
							<%-- Sigla do sistema ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.sigla.sistema" /></label></td>
								<td colspan="3">
									<input type="text" name="siglaSistema" id="siglaSistema" alt="siglaSistema" 
									value="${ConfiguracaoActionForm.siglaSistema}" maxlength="10"
									class="parametros"/>									
								</td>
							</tr>
							<%-- Descrição do sistema ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.sistema" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoSistema" id="descricaoSistema" alt="descricaoSistema" 
									value="${ConfiguracaoActionForm.descricaoSistema}" maxlength="255"
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
                    <a href="javascript:voltar();">
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
			exibirRetornoPesquisa();
		}
		
		function limpar(origem) {

			if (document.pesquisaSistemaForm.idSistema.value) {
				$("form[name=pesquisaSistemaForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaSistemaForm] select:not([readonly]):not([disabled])").val("");
								
			} else {
				//document.location = "../logs/preparaMeioPagamento.do";
				document.location = "../configuracao/manterSistema.jsp";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(itemLista){

        	document.location = "../configuracao/editarSistema.do?idPesquisa="+itemLista;

        }

        function confirmarExclusao(itemLista){
        	prosseguirExclusao(itemLista);
        }

        function prosseguirExclusao(itemLista) {

        	idSistema = itemLista;
            
            confirmar("<bean:message key="msg.confirma.exclusao.sistema" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.sistema.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaSistemaForm.method = "post";
                document.pesquisaSistemaForm.idSistema.value=idSistema;
                document.pesquisaSistemaForm.action = "../configuracao/manterSistema.do?operacao=3";
                document.pesquisaSistemaForm.submit();
            }
        }

    	function voltar(){
            document.pesquisaSistemaForm.method = "post";
            document.pesquisaSistemaForm.opcao.value = "prepararVoltar";
            document.pesquisaSistemaForm.action = "../configuracao/prepararSistema.do";
            document.pesquisaSistemaForm.submit();   
        }

	</script>
</body>
</html>