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
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
    <jsp:useBean id="EmpresaVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="EmpresaVH" property="codigoEmpresa" value="${ConfiguracaoActionForm.codigoEmpresa}" />
        <jsp:setProperty name="EmpresaVH" property="descricaoEmpresa" value="${ConfiguracaoActionForm.descricaoEmpresa}" />
        <jsp:setProperty name="EmpresaVH" property="listEmpresasUsuarioLogado" value="${empresas_usuario_logado}" />
        
	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.empresa" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaEmpresaForm" id="pesquisaEmpresa"
				action="../configuracao/resultadoEmpresa.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idEmpresa" name="idEmpresa" 
				value="${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.idEmpresa : ''}">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaEmpresa">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigoEmpresa" /></label></td>
								<td colspan="3">
									<!-- ${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.codigoEmpresa : ''} -->
									<input type="text" name="codigoEmpresa" id="codigoEmpresa" alt="codigoEmpresa" 
									value="${ConfiguracaoActionForm.codigoEmpresa}" maxlength="3" class="parametros"/>
								</td>
							</tr>
							<%-- DESCRIÇÃO EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricaoEmpresa" /></label></td>
								<td colspan="3">
								<!-- ${operacao ne 'inclusaoSucesso' ? ConfiguracaoActionForm.descricaoEmpresa : ''} -->
									<input type="text" name="descricaoEmpresa" id="descricaoEmpresa" alt="descricaoEmpresa" 
									value="${ConfiguracaoActionForm.descricaoEmpresa}" maxlength="100" class="parametros"/>
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
	<logic:messagesNotPresent>
		<c:choose> 
	    <c:when test="${EmpresaVH.listarEmpresaPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
		<c:if test="${EmpresaVH.listarEmpresaPorCriterio().size() gt 0}">
			<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
		</c:if>		
			<div class="barras">
				<div class="tabelas">
		           	<div id="aba3">
		               	<div>
							<div class="DisplayTag AjusteDisplay rateio">
								<display:table name="${EmpresaVH.listarEmpresaPorCriterio()}" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
									<display:column property="codigoEmpresa" titleKey="label.codigoEmpresa" style="text-align:center; width:15%; word-break: break-all;" />
									<display:column property="descricaoEmpresa" titleKey="label.descricaoEmpresa" style="text-align:left; width:75%; word-break: break-all;" />
									<display:column property="acaoEmpresa"    titleKey="label.acao"    style="text-align:center; width:10%" />
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
		
			mostrarAjuda(
					<bean:message key="ajuda.caixaDialogo.solicitacao.pesquisar"/>,
					"dialogoInformacao", 400);
		}
		
		function pesquisar() {

			//$(".divHidden").show();	
			document.pesquisaEmpresaForm.novaBusca.value = 'true';
			document.pesquisaEmpresaForm.submit();
			
		}
		
		function limpar() {
		
			document.location = "../configuracao/prepararEmpresa.do?opcao=1";
			
		}

		function exibirDetalhe(empresa){

        	document.location = "../configuracao/editarEmpresa.do?idPesquisa="+empresa;

        }

		function incluir(){

			document.pesquisaEmpresaForm.opcao.value = 'incluirEmpresa';
        	//document.location = "../configuracao/prepararEmpresa.do";
            document.pesquisaEmpresaForm.action = "../configuracao/prepararEmpresa.do?opcao=incluirEmpresa";
            document.pesquisaEmpresaForm.submit();    

        }

        function confirmarExclusao(empresa){
        	prosseguirExclusao(empresa);
        }

        function prosseguirExclusao(empresa) {

        	idEmpresa = empresa;
            
            confirmar("<bean:message key="msg.confirma.exclusao.empresa" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
                document.pesquisaEmpresaForm.method = "post";
                document.pesquisaEmpresaForm.opcao.value="excluirEmpresa";
                document.pesquisaEmpresaForm.idEmpresa.value=idEmpresa;
                document.pesquisaEmpresaForm.action = "../configuracao/editarEmpresa.do?operacao=3";
                document.pesquisaEmpresaForm.submit();      
            }
        }
	</script>
</body>
</html>