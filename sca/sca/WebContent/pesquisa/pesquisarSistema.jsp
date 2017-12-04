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

        $("#empresa").autocomplete({            
            source: function(request, response) {  
                var descEmpresa = $("#empresa").val();
                $.ajax({
                    type: "GET",
                    url: "../autocomplete",
                    data: "op=2&descEmpresa=" + descEmpresa,
                    dataType: "json", 
                    delay:500,   
                    position: {  collision: "flip"  },
                    success: function(data) {                            
                        response(data);
                    }
                }); 
            },
            minLength: 3,
            select: function(event, ui) {                                   
                $("#idEmpresa").val(ui.item.id);
        
            }
        });
		
	});

	var idSistema = '${idSistema}';
	
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
        <jsp:setProperty name="SistemaVH" property="siglaSistema" value="${ConfiguracaoActionForm.siglaSistema}" />
        <jsp:setProperty name="SistemaVH" property="descricaoSistema" value="${ConfiguracaoActionForm.descricaoSistema}" />
        <jsp:setProperty name="SistemaVH" property="idEmpresa" value="${ConfiguracaoActionForm.idEmpresa}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.pesquisar.sistema" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaSistemaForm" id="pesquisaSistema"
				action="../configuracao/resultadoSistema.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idSistema" name="idSistema" value="${ConfiguracaoActionForm.idSistema}">
				<input type="hidden" id="opcao" name="opcao" value="pesquisaSistema">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
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
							<%-- Empresa ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.empresa" /></label></td>
								<td colspan="3">
							        <input 
                                    type="hidden" 
                                    name="idEmpresa" 
                                    id="idEmpresa" 
                                    value="${ConfiguracaoActionForm.idEmpresa}"
                                    />
									<input type="text" name="empresa" id="empresa" alt="empresa" 
									value="${ConfiguracaoActionForm.empresa}" maxlength="255"
									class="parametros"/>
									<span class="IconeObs tooltipTop" title="<bean:message key="ajuda.autoComplete.empresa"/>" >
                                        <span class="Icone16x16 IconeAjuda" ></span>                           
                                    </span>								
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
    <c:when test="${SistemaVH.listarSistemasPorCriterio().size() gt 0 or ConfiguracaoActionForm.novaBusca eq 'true'}">
	<c:if test="${SistemaVH.listarSistemasPorCriterio().size() gt 0}">
		<input type="hidden" id="listaAtiva" name="listaAtiva" value="true"/>
	</c:if>	
	<div class="barras">
		<div class="tabelas">
        	<div id="divHidden"> <!-- style="display: none" -->
	            <div id="aba3">
	            	<div>
              			<div class="DisplayTag AjusteDisplay rateio">
							<display:table name="${SistemaVH.listarSistemasPorCriterio()}" pagesize="10" export="false" excludedParams="*" decorator="br.com.delphos.web.configuracao.ConfiguracaoDecorator" requestURI="">
	                            <display:column property="siglaSistema" titleKey="label.sigla.sistema" style="text-align:center; width:14%; word-break: break-all;" />
	                            <display:column property="descricaoSistema" titleKey="label.descricao.sistema" style="text-align:left; width:42%; word-break: break-all;" />
	                            <display:column property="codigoEmpresaSistema" titleKey="label.empresa" style="text-align:center; width:14%; word-break: break-all;" />
		                        <display:column property="acaoSistema"    titleKey="label.acao"    style="text-align:center; width:6%" />
	                        </display:table>
              			</div>
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

			if (myTrim(document.pesquisaSistemaForm.empresa.value).length == 0) {
				document.pesquisaSistemaForm.idEmpresa.value = "";
			}
			
			document.pesquisaSistemaForm.novaBusca.value = 'true';
			document.pesquisaSistemaForm.submit();
		}
		
		function limpar() {
		
			document.location = "../configuracao/prepararSistema.do?opcao=1";
			
		}

		function exibirDetalhe(idSistema){

        	document.location = "../configuracao/editarSistema.do?idPesquisa="+idSistema;

        }

		function incluir(){

			document.pesquisaSistemaForm.method = "post";
			document.pesquisaSistemaForm.opcao.value = 'incluir';
			document.pesquisaSistemaForm.action = "../configuracao/prepararSistema.do";
        	document.pesquisaSistemaForm.submit();  

        }

        function confirmarExclusao(idSistema){
        	prosseguirExclusao(idSistema);
        }

        function prosseguirExclusao(sistema) {

        	idSistema = sistema;
            
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
	</script>
</body>
</html>