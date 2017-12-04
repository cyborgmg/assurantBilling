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
	});

	var idInterface = '${idInterface}';
	
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
	 	<jsp:useBean id="InterfaceVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"					/>
        <jsp:setProperty name="InterfaceVH" property="descricaoInterface"      value="${ConfiguracaoActionForm.descricaoInterface}" />
 		<jsp:setProperty name="InterfaceVH" property="grupoInterfaceId" value="${ConfiguracaoActionForm.grupoInterface}"/> 
 
    
	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.interface" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaInterfaceForm" id="pesquisaInterface"
				action="../configuracao/resultadoInterface.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">
				<input type="hidden" id="idInterface" name="idInterface" value="${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.idInterface : ''}">
				<input type="hidden" id="idGrupoInterface" name="idGrupoInterface">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaInterface">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DO INTERFACE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.interface" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoInterface" id="descricaoInterface" alt="descricaoInterface" 
									value="${ConfiguracaoActionForm.descricaoInterface}" maxlength="255"
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
    <c:when test="${InterfaceVH.listarInterface().size() gt 0 and ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${InterfaceVH.listarInterface().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>	
		<div class="barras">
			<div class="tabelas">
	           	<div id="aba3">
	               	<div>
						<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${InterfaceVH.listarInterfacePorCriterio()}" 		decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" 
							requestURI="" pagesize="15">
								  	 <display:column property="descricaoInterface" 	  titleKey="label.descricao.interface" 	     style="text-align:left; width:60%; word-break: break-all;" />
								     <display:column property="acaoInterface"    		titleKey="label.acao"    				style="text-align:center; width:20%; word-break: break-all;" />
							</display:table>
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
			document.pesquisaInterfaceForm.novaBusca.value = 'true';
			document.pesquisaInterfaceForm.submit();
			}
		
		function limpar() {
			document.location = "../configuracao/resultadoInterface.do?opcao=1";
		}

		function exibirDetalhe(intrface){
        	//document.location = "../configuracao/editarInterface.do?idPesquisa="+intrface;
			document.location = "../configuracao/editarInterface.do?idPesquisa="+intrface+"&operacao=exibirDetalhe";
        }

		function incluir(){
        	//document.location = "../configuracao/manterInterface.jsp";
			document.location = "../configuracao/editarInterface.do";
        }

        function confirmarExclusao(provedor){
        	prosseguirExclusao(provedor);
        }

        function prosseguirExclusao(provedor) {
        	idInterface = provedor;
            confirmar("<bean:message key="msg.confirma.exclusao.interfaci" />", "dialogoPergunta", 400, executarExclusao);
        }
        
        function executarExclusao(retorno) {
	        if (retorno){                  
	        	//mostrarMensagem("<bean:message key="msg.interfaci.excluido.sucesso"/>", "dialogoSucesso", 400);  
	            document.pesquisaInterfaceForm.method = "post";
	            document.pesquisaInterfaceForm.opcao.value="excluirInterface";
	            document.pesquisaInterfaceForm.idInterface.value=idInterface;
	            document.pesquisaInterfaceForm.action = "../configuracao/editarInterface.do?operacao=3";
	            document.pesquisaInterfaceForm.submit();      
	        }
      } 
	</script>
</body>
</html>