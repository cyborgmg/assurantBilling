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
		$("#codigoMetodoPagamento").setMask();
		
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

    function prosseguir() {

        confirmar("<bean:message key="msg.confirma.associacaoAdquirente" />", "dialogoPergunta", 400, prosseguirCB);
        		
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.associacaoAdquirente.associado.sucesso"/>", "dialogoSucesso", 400);      
        	
        	 document.pesquisaAssociacaoAdquirenteForm.opcao.value = "prosseguirAdquirenteBandeira";
        	 
             document.pesquisaAssociacaoAdquirenteForm.method = "post";
             document.pesquisaAssociacaoAdquirenteForm.action = "../configuracao/associarAdquirente.do?operacao=2";
             document.pesquisaAssociacaoAdquirenteForm.submit();  
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

        <jsp:useBean id="AssociacaoAdquirenteVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="AssociacaoAdquirenteVH" property="idContratoCobranca" value="${param.idContrato ne null ? param.idContrato : ConfiguracaoActionForm.idContratoCobranca}" />
    <jsp:setProperty name="AssociacaoAdquirenteVH" property="idAdquirente" value="${ConfiguracaoActionForm.idAdquirente}" />
    <jsp:setProperty name="AssociacaoAdquirenteVH" property="idBandeira" value="${ConfiguracaoActionForm.idBandeira}" />
    <jsp:setProperty name="AssociacaoAdquirenteVH" property="padraoAssociarContrato" value="${ConfiguracaoActionForm.padraoAssociarContrato}" />
    <jsp:setProperty name="AssociacaoAdquirenteVH" property="codigoMetodoPagamento" value="${ConfiguracaoActionForm.codigoMetodoPagamento}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
                    <a href="javascript:voltarPesquisar();"><bean:message key="label.breadcrumb.pesquisar.contratoCobranca" />&nbsp;>&nbsp;</a>
                    <a href="javascript:voltar();"><bean:message key="label.contratoCobranca" />&nbsp;>&nbsp;</a>
                    <bean:message key="label.associacao.adquirente" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaAssociacaoAdquirenteForm" id="pesquisaContratoCobranca"
				action="#" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idContratoCobranca" name="idContratoCobranca" value="${param.idContrato ne null ? param.idContrato : ConfiguracaoActionForm.idContratoCobranca}">
				<input type="hidden" id="opcao" name="opcao" value="${ConfiguracaoActionForm.opcao ne null ? ConfiguracaoActionForm.opcao : 'adquirenteBandeira'}">
				<c:choose> 
    			<c:when test="${ConfiguracaoActionForm.opcao eq 'editarAssociacaoAdquirente'}">
					<input type="hidden" id="idAdquirente" name="idAdquirente" value="${ConfiguracaoActionForm.idAdquirente}" >
					<input type="hidden" id="idBandeira" name="idBandeira" value="${ConfiguracaoActionForm.idBandeira}" >
				</c:when>
				</c:choose>
				
				<input type="hidden" id="idAdquirenteSelecionado" name="idAdquirenteSelecionado" value="${ConfiguracaoActionForm.idAdquirenteSelecionado}">
				<input type="hidden" id="idBandeiraSelecionado" name="idBandeiraSelecionado" value="${ConfiguracaoActionForm.idBandeiraSelecionado}">
				
				<input type="hidden" id="idAdquirenteOld" name="idAdquirenteOld" value="${ConfiguracaoActionForm.idAdquirenteOld eq ''? ConfiguracaoActionForm.idAdquirenteOld : ConfiguracaoActionForm.idAdquirente}">
				<input type="hidden" id="idBandeiraOld" name="idBandeiraOld" value="${ConfiguracaoActionForm.idBandeiraOld eq '' ? ConfiguracaoActionForm.idBandeiraOld : ConfiguracaoActionForm.idBandeira}">
				<input type="hidden" id="padraoAssociarContratoOld" name="padraoAssociarContratoOld" value="${ConfiguracaoActionForm.padraoAssociarContratoOld eq '' ? ConfiguracaoActionForm.padraoAssociarContratoOld : ConfiguracaoActionForm.padraoAssociarContrato}">
				<input type="hidden" id="codigoMetodoPagamentoOld" name="codigoMetodoPagamentoOld" value="${ConfiguracaoActionForm.codigoMetodoPagamentoOld eq '' ? ConfiguracaoActionForm.codigoMetodoPagamentoOld : ConfiguracaoActionForm.codigoMetodoPagamento}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DO CONTRATO DE COBRANÇA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.contratoCobranca" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoContratoCobranca" id="descricaoContratoCobranca" 
									alt="descricaoContratoCobranca" 
									maxlength="14" readonly="readonly"
									class="parametros" value="${ConfiguracaoActionForm.descricaoContratoCobranca}"/>
								</td>
							</tr>
							<c:choose> 
    						<c:when test="${ConfiguracaoActionForm.opcao ne 'editarAssociacaoAdquirente'}">
								<%-- CÓDIGO DA BANDEIRA ================================================================== --%>
								<tr>
									<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
												key="label.bandeira" /></label></td>
									<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="idBandeira" id="idBandeira" style="width: 100%;"
											options="${AssociacaoAdquirenteVH.listarBandeira()}" optionText="nomeBandeira"
											optionValue="id" value="${ConfiguracaoActionForm.idBandeira}"
											/>	
									</div>
									</td>
								</tr>
								<%-- CÓDIGO DO ADQUIRENTE ================================================================== --%>
								<tr>
									<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
												key="label.adquirente" /></label></td>
									<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="idAdquirente" id="idAdquirente" style="width: 100%;"
											options="${AssociacaoAdquirenteVH.listarAdquirente()}" optionText="nome"
											optionValue="id" value="${ConfiguracaoActionForm.idAdquirente}"
											/>	
									</div>
									</td>
								</tr>
							</c:when>
							</c:choose>
							
							<c:choose> 
    						<c:when test="${ConfiguracaoActionForm.opcao eq 'editarAssociacaoAdquirente'}">
    						
								<%-- CÓDIGO DA BANDEIRA ================================================================== --%>
								<tr>
									<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
												key="label.bandeira" /></label></td>
									<td colspan="3">
										<input type="text" name="nomeBandeira" id="nomeBandeira" 
										alt="nomeBandeira" 
										maxlength="14" readonly="readonly"
										class="parametros" value="${ConfiguracaoActionForm.nomeBandeira}"/>
									</td>
								</tr>
								<%-- CÓDIGO DO ADQUIRENTE ================================================================== --%>
								<tr>
									<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
												key="label.adquirente" /></label></td>
									<td colspan="3">
										<input type="text" name="nomeAdquirente" id="nomeAdquirente" 
										alt="nomeAdquirente" 
										maxlength="14" readonly="readonly"
										class="parametros" value="${ConfiguracaoActionForm.nomeAdquirente}"/>
									</td>
								</tr>
    						
    						</c:when>
    						</c:choose>
							
							<%-- Padrão  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.padrao" /></label></td>
								<td colspan="3">
									<div id="divStatus" class="formataCampoSelectForm divSelectConsulta" style="width: 30%;">
										
											<dph:dropdown name="padraoAssociarContrato" id="padraoAssociarContrato" style="width: 100%;"
											options="${AssociacaoAdquirenteVH.listarSimNao()}" optionText="descricao"
											optionValue="codigo" value="${ConfiguracaoActionForm.padraoAssociarContrato}"
											readOnly="${(ConfiguracaoActionForm.padraoAssociarContrato ne 'S' && ConfiguracaoActionForm.padraoAssociarContrato ne '') && AssociacaoAdquirenteVH.getTemAdquirentePadrao() eq 'true'}"
											/>	
										
										
									</div>
								</td>
							</tr>
							<%-- Código método pagamento  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.codigo.metodo.pagamento" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoMetodoPagamento" id="codigoMetodoPagamento" alt="catorze-digitos" 
									maxlength="9"
									class="parametros" value="${ConfiguracaoActionForm.codigoMetodoPagamento}"/>
									<a href="#" class="IconeObs tooltipTop" title="<bean:message key="msg.info.codigoMetodoPagamento" />" >
                                        <span class="Icone16x16 IconeAjuda" ></span>                           
                                    </a>
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
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${AssociacaoAdquirenteVH.listarAssociacaoAdquirente()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="bandeiraAssociacaoAdquirente" titleKey="label.bandeira" style="text-align:center; width:10%; word-break: break-all;" />
	                            <display:column property="adquirenteAssociacaoAdquirente" titleKey="label.adquirente" style="text-align:center; width:10%; word-break: break-all;" />
	                            <display:column property="padraoAssociacaoAdquirente" titleKey="label.padrao" style="text-align:center; width:5%; word-break: break-all;" />
	                            <display:column property="codigoMetodoPagamento" titleKey="label.codigo.metodo.pagamento" style="text-align:left; width:19%; word-break: break-all;" />
		                        <display:column property="acaoAssociacaoAdquirente"    titleKey="label.acao"    style="text-align:center; width:6%" />
	                        </display:table>
              			</div>
					</div>
           		</div>
     		</div>
		</div>
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

		function associar() {

			//document.pesquisaLogsForm.submit();
			prosseguir();
		}
		
		function limpar(origem) {

			if (document.pesquisaAssociacaoAdquirenteForm.idAdquirente.value) {
				$("form[name=pesquisaAssociacaoAdquirenteForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaAssociacaoAdquirenteForm] select:not([readonly]):not([disabled]):not([multiple])").val("");				

			} else {
				document.location = "../configuracao/associarAdquirente.do?idContrato="+document.pesquisaAssociacaoAdquirenteForm.idContratoCobranca.value+"&opcao=adquirenteBandeiraSemValidacao";
				//document.location = "../configuracao/manterContratoCobranca.jsp";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(idAdquirente, idBandeira, idContratoCobranca, adquirentePadrao, codigoMetodoPagamento){
            // javascript: exibirDetalhe("1", "4", "3", "N", "997");

			//document.location = "../configuracao/editarAssociacaoAdquirente.do";
			
			document.pesquisaAssociacaoAdquirenteForm.idAdquirenteSelecionado.value = idAdquirente;
			document.pesquisaAssociacaoAdquirenteForm.idBandeiraSelecionado.value = idBandeira;
			document.pesquisaAssociacaoAdquirenteForm.idContratoCobranca.value = idContratoCobranca;
// 			document.pesquisaAssociacaoAdquirenteForm.padraoAssociarContrato.value = adquirentePadrao;
// 			document.pesquisaAssociacaoAdquirenteForm.codigoMetodoPagamento.value = codigoMetodoPagamento;

			document.pesquisaAssociacaoAdquirenteForm.opcao.value = "editarAssociacaoAdquirente";

            document.pesquisaAssociacaoAdquirenteForm.method = "post";
            document.pesquisaAssociacaoAdquirenteForm.action = "../configuracao/editarAssociacaoAdquirente.do";
            document.pesquisaAssociacaoAdquirenteForm.submit();      			
        }

        function confirmarExclusao(idAdquirente, idBandeira, idContratoCobranca, adquirentePadrao, codigoMetodoPagamento){
        	prosseguirExclusao(idAdquirente, idBandeira, idContratoCobranca, adquirentePadrao, codigoMetodoPagamento);
        }

        function prosseguirExclusao(idAdquirente, idBandeira, contratoCobranca, adquirentePadrao, codigoMetodoPagamento) {

        	idContratoCobranca = contratoCobranca;
			document.pesquisaAssociacaoAdquirenteForm.idAdquirente.value = idAdquirente;
			document.pesquisaAssociacaoAdquirenteForm.idBandeira.value = idBandeira;
			document.pesquisaAssociacaoAdquirenteForm.idContratoCobranca.value = idContratoCobranca;
			document.pesquisaAssociacaoAdquirenteForm.padraoAssociarContrato.value = adquirentePadrao;
			document.pesquisaAssociacaoAdquirenteForm.codigoMetodoPagamento.value = codigoMetodoPagamento;
            
            confirmar("<bean:message key="msg.confirma.exclusao.associacaoAdquirente" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.associacaoAdquirente.excluido.sucesso"/>", "dialogoSucesso", 400);
            	
            	document.pesquisaAssociacaoAdquirenteForm.opcao.value = "excluirAssociacaoAdquirente";
            	
                document.pesquisaAssociacaoAdquirenteForm.method = "post";
                document.pesquisaAssociacaoAdquirenteForm.idContratoCobranca.value=idContratoCobranca;
                document.pesquisaAssociacaoAdquirenteForm.action = "../configuracao/associarAdquirente.do?operacao=3";
                document.pesquisaAssociacaoAdquirenteForm.submit();      
            }
        }

    	function voltar(){
            window.location = "../configuracao/editarContratoCobranca.do?idPesquisa="+document.pesquisaAssociacaoAdquirenteForm.idContratoCobranca.value;
        }
        
    	function voltarPesquisar(){
            //window.location = "../pesquisa/pesquisarContratoCobranca.jsp";

            document.pesquisaAssociacaoAdquirenteForm.method = "post";
            document.pesquisaAssociacaoAdquirenteForm.opcao.value = "prepararVoltar";
            document.pesquisaAssociacaoAdquirenteForm.action = "../configuracao/prepararContratoCobranca.do";
            document.pesquisaAssociacaoAdquirenteForm.submit();  
        }  

	</script>
</body>
</html>