<%@taglib tagdir="/WEB-INF/tags/" prefix="dbs"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.delphos.com.br/delphos-html" prefix="dph"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<dbs:head />

<script type="text/javascript">
	$(function() {
		$("#cpf").setMask();

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

		var codEmpresa = '${codEmpresa}';
		var idProduto = '${idProduto}';
		var idSistema = '${idSistema}';

		if (codEmpresa != null && myTrim(codEmpresa).length > 0) {
			$("select[name=empresa]").val(codEmpresa);
			if (idProduto != null && myTrim(idProduto).length > 0) {
				$("select[name=produto]").val(idProduto);
				if (idSistema != null && myTrim(idSistema).length > 0) {
					$("select[name=sistema]").val(idSistema);
				}
			}
		}

		if($("select[name=empresa]").val() == ""){
			$("select[name=produto], select[name=sistema]").attr("disabled","disabled");
		}
		
		if($("select[name=produto]").val() == ""){
			$("select[name=sistema]").attr("disabled","disabled");
		}	

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

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<bean:message key="label.breadcrumb.consultar.vendas" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaVendasForm" id="pesquisaVendas"
				action="../venda/resultado.do" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="true">

				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.empresa" /></label></td>
								<td colspan="3">
									<div id="divTipoSolicitacao"
										class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="empresa" id="empresa" style="width: 100%;"
											options="${empresas_usuario_logado}" optionText="descricao"
											optionValue="codigo"
											onchange="javascript: filtrarProdutos(this.value);" />
									</div>
								</td>
							</tr>
							<%-- PRODUTO =========================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.produto" /></label></td>
								<td colspan="3">
									<div id="divTipoSolicitacao"
										class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="produto" id="produto" style="width: 100%;"
											options="${produtos_filtro_venda}" optionText="descricao"
											optionValue="id"
											onchange="javascript: filtrarSistemas(this.value);" />
									</div>
								</td>
							</tr>
							<%-- SISTEMA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.sistema" /></label></td>
								<td colspan="3">
									<div id="divTipoSolicitacao"
										class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="sistema" id="sistema" style="width: 100%;"
											options="${produtos_filtro_sistema}" optionText="descricao"
											optionValue="id"
											/>										
									</div>
								</td>
							</tr>
							<%-- CPF ====================================================== --%>
							<tr>
								<td class="label ui-widget-header">
									<label><bean:message key="label.cpf" /></label>
								</td>
								<td>
									<input type="text" name="cpf" id="cpf" class="cpf" alt="cpf" value="${cpf}"/>
								</td>
								<td class="label ui-widget-header">
									<label><bean:message key="label.certificado" /></label>
								</td>
								<td>
									<input type="text" name="certificado" id="certificado" value="${certificado}" maxlength="36"/>
									<a href="#" class="IconeObs tooltipTop" title="<bean:message key="msg.info.certificado" />" >
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
				<td class="botoesRetornar">&nbsp;</td>
				<td class="botoesCentro">&nbsp;</td>
				<td class="botoesAvancar">
					<a href="javascript:limpar('c')">
						<span class="Icone22x22 IconeLimpar"></span> 
						<bean:message key="label.botao.limpar" /><br> &nbsp;
					</a> 
					<a href="javascript:pesquisar()"> 
						<span class="Icone22x22 IconePesquisar"></span>
						<bean:message key="label.botao.pesquisa" /><br> &nbsp;
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

			document.pesquisaVendasForm.submit();
		}
		
		function limpar(origem) {
		
			document.location = "../venda/prepararVenda.do";
			
		}
		
		function filtrarProdutos(codEmpresa) {
			if (codEmpresa == "" || codEmpresa == "0") {
            	$("select[name=produto], select[name=sistema]").attr("disabled","disabled");
                $("select[name=produto], select[name=sistema]").html("<option value='0'>- <bean:message key="msg.selecione"/> -</option>");  
			} else {
				$.ajax({
			        url:"../dbs/listas",
			        data:"op=1&cod="+codEmpresa,
			        success:function(produtos){
			        	if (produtos.indexOf("option") !=-1) {
			                $("select[name=produto]").removeAttr("disabled");
			            	$("select[name=produto]").attr("onchange","javascript: filtrarSistemas(this.value)");
			                $("select[name=produto]").html(produtos);                    
			            } else {
			            	$("select[name=produto], select[name=sistema]").attr("disabled","disabled");
			                $("select[name=produto], select[name=sistema]").html("<option value='0'>- <bean:message key="msg.selecione"/> -</option>");    
					    }
			        }
			    });
			}
		}
		
		function filtrarSistemas(idProduto) {
			if (idProduto != "" && idProduto != "0") {
				$.ajax({
			        url:"../dbs/listas",
			        data:"op=2&id="+idProduto,
			        success:function(sistemas){
			        	if (sistemas.indexOf("option") !=-1) {
			                $("select[name=sistema]").removeAttr("disabled");
			                $("select[name=sistema]").html(sistemas);                    
			            } else {
			            	$("select[name=sistema]").attr("disabled","disabled");
			                $("select[name=sistema]").html("<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
					    }
			        }
			    });
			} else {
            	$("select[name=sistema]").attr("disabled","disabled");
                $("select[name=sistema]").html("<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
		    }
		}
	</script>
</body>
</html>