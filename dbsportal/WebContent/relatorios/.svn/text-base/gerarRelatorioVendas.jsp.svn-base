<%@page import="br.com.delphos.billing.enumeracoes.StatusCobranca"%>
<%@page import="br.com.delphos.billing.enumeracoes.TipoListaValor"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="dbs"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.delphos.com.br/delphos-html" prefix="dph"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<dbs:head />

<script type="text/javascript">            
            $(function(){                
                $("#cpf").setMask();
                $("#diasAtraso").setMask("tres-digitos");
				
                if("${verificaForm}" != "S"){
                	$(".configPeriodo"   ).hide();
               	 	$(".configStatus"    ).hide();
               	 	$(".configEmpresas"  ).hide();
               	 	$(".configSistemas"  ).hide();
               	 	$(".configProdutos"  ).hide();
               	 	$(".configDiasAtraso").hide();
                }

                $( ".tooltipTop" ).tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "top"
                });

                $( ".tooltipBottom" ).tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "bottom"
                });

                var dateToday = new Date();
                var yrRange = "2015:" + dateToday.getFullYear();
				
                var idCalendario = $( "#periodoDe, #periodoAte" );             
                idCalendario.datepicker({
                    showOn: "button",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: yrRange,
                    minDate: new Date(2015, 0, 1),
                    maxDate: '0',
                    buttonImage: "../img/icones/calendario_22x22.png",
                    buttonImageOnly: true,
                    showAnim: 'slideDown' 
                });
                
                $(".ui-datepicker-trigger").tipTip({
                    maxWidth: "auto",
                    edgeOffset: 10,
                    defaultPosition: "bottom"
                });

        		var codEmpresa = '${codEmpresa}';
        		var idProduto = '${idProduto}';
        		var idSistema = '${idSistema}';
        		var relatorio = '${relatorio}';
        		var periodoDe = "${periodoDe ne '' ? periodoDe : GerarRelatorioVendasActionForm.periodoDe}";
        		var periodoAte = "${periodoAte ne '' ? periodoAte : GerarRelatorioVendasActionForm.periodoAte}";

        		if (codEmpresa != null && myTrim(codEmpresa).length > 0) {
        			$("select[name=empresa]").val(codEmpresa);
        			if (idProduto != null && myTrim(idProduto).length > 0) {
        				$("select[name=produto]").val(idProduto);
        				if (idSistema != null && myTrim(idSistema).length > 0) {
        					$("select[name=sistema]").val(idSistema);
        				}
        			}
        		}
        		var relatorioSelecionado = "${relatorio_selecionado}";
        		if (relatorio != null && myTrim(relatorio).length > 0) {
        			$("select[name=relatorio]").val(relatorio);
        		} else if (relatorioSelecionado) {
        			$("select[name=relatorio]").val(relatorioSelecionado);
            	}
        		if (periodoDe != null && myTrim(periodoDe).length > 0) {
        			$("input[name=periodoDe]").val(periodoDe);
        		}
        		if (periodoAte != null && myTrim(periodoAte).length > 0) {
        			$("input[name=periodoAte]").val(periodoAte);
        		}

        		if($("select[name=empresa]").val() == ""){
        			$("select[name=produto], select[name=sistema]").attr("disabled","disabled");
        		}
        		
        		if($("select[name=produto]").val() == ""){
        			$("select[name=sistema]").attr("disabled","disabled");
        		}	

        		var pesquisaVazia = "${pesquisaVazia}";
                if (pesquisaVazia || relatorioSelecionado) {
//         			$("select[name=relatorio]").trigger('change');
					configurar();
                }
            });

            function ajuda() {
                mostrarAjuda(<bean:message key="ajuda.caixaDialogo.solicitacao.pesquisar"/>, "dialogoInformacao", 400);
            }


			function validar(){

				var erros = 0;
		                
		            	if($("select[name='relatorio']").val() == 0){erros = 1;}
		
		          		else if($("#periodoDe").val() != "" && $("#periodoDe").val() != null && $("#periodoAte").val() != "" && $("#periodoAte").val() != null){}
		
		              	else if( $("#op").val()=="10" && ( $("#diasAtraso").val()==null || $("#diasAtraso").val()=="" )){erros=3;}
		
		
		                 else if( ($("#op").val()=="6"||$("#op").val()=="7") &&($("#periodoDe").val() == "" || $("#periodoDe").val() == null || $("#periodoAte").val() == "" || $("#periodoAte").val() == null) ){erros = 2;}
		            
		            if(erros == 1){
		                $("select[name='relatorio']").addClass("formataCampoSelectErroForm");
		                $("select[name='relatorio']").parent().addClass("CampoVazio");
		                mostrarMensagem("<bean:message key="erro.relatorio.vazio"/>", "dialogoErro", 400);
		                
		            } else if(erros == 2){
		                $(function(){
		                    $("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
		                    $("select[name='relatorio']").parent().removeClass("CampoVazio");
		                    $("#periodoDe, #periodoAte").addClass("CampoVazio");
		                    $("").addClass("CampoVazio");
		                    mostrarMensagem("<bean:message key="erro.periodo.vazio"/>", "dialogoErro", 400);
		                });
		            } 
		            else if(erros == 3){
		                $(function(){
		                    $("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
		                    $("select[name='relatorio']").parent().removeClass("CampoVazio");
		                     $("#diasAtraso").addClass("CampoVazio");
		                    $("").addClass("CampoVazio");
		                    mostrarMensagem("<bean:message key="erro.dias.vazio"/>", "dialogoErro", 400);
		                });
		            } 

		            if(erros==0)
			            return true;
		            else
		                return false;
			}
            
            
            function imprimir() {
            
                $("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
                $("select[name='relatorio']").parent().removeClass("CampoVazio");
                $("#periodoDe, #periodoAte").removeClass("CampoVazio");
                document.GerarRelatorioVendasActionForm.action = "gerarRelatorioVendas.do";
                document.GerarRelatorioVendasActionForm.submit();
//                 window.location.reload();
                 }


			function configurarRelatorioPrimeiraCobranca(){

				 $("#diasAtraso").val("");
				 $(".configDiasAtraso").hide();

				 $(".configEmpresas"	).show();
	           	 $(".configSistemas"	).show();
	           	 $(".configProdutos"	).show();
	           	 $(".configPeriodo"		).show();
	           	 $(".configStatus"		).show();
	           					
					
 					filtrarStatus('<%=TipoListaValor.Status.getValor()%>');
					 
					 
					   	 $(".configEmpresas"	).show();
			           	 $(".configSistemas"	).show();
			           	 $(".configProdutos"	).show();
			           	 $(".configPeriodo"		).show();
			           	 $(".configStatus"		).show();				
			}

			function configurarRelatorioVendasEstornadas(){

				 $("#diasAtraso").val("");
				 $(".configDiasAtraso").hide();

	           	 $(".configEmpresas"  ).show();
	           	 $(".configSistemas"  ).show();
	           	 $(".configProdutos"  ).show();
	           	 $(".configPeriodo"   ).show();
	           	 $(".configStatus"    ).show();

				
					filtrarStatus('<%=TipoListaValor.Status.getValor()%>');
						

					 $("#diasAtraso").val("");
					 $(".configDiasAtraso").hide();

		           	 $(".configEmpresas"  ).show();
		           	 $(".configSistemas"  ).show();
		           	 $(".configProdutos"  ).show();
		           	 $(".configPeriodo"   ).show();
		           	 $(".configStatus"    ).show();
				
			}

			function configurarRelatorioCobrancaConciliacaoInterface(){
				 $("#diasAtraso").val("");
				 $(".configDiasAtraso").hide();

	           	 $(".configEmpresas"  ).show();
	           	 $(".configSistemas"  ).show();
	           	 $(".configProdutos"  ).show();
	           	 $(".configPeriodo"   ).show();
	           	 $(".configStatus"    ).show();

				
	           		filtrarStatus('<%=TipoListaValor.SituacaoConciliacao.getValor()%>');


					 $("#diasAtraso").val("");
					 $(".configDiasAtraso").hide();

		           	 $(".configEmpresas"  ).show();
		           	 $(".configSistemas"  ).show();
		           	 $(".configProdutos"  ).show();
		           	 $(".configPeriodo"   ).show();
		           	 $(".configStatus"    ).show();

			}

			function configurarRelatorioConciliacao(){
				 $("#diasAtraso").val("");
				 $(".configDiasAtraso").hide();

	           	 $(".configEmpresas"  ).show();
	           	 $(".configSistemas"  ).show();
	           	 $(".configProdutos"  ).show();
	           	 $(".configPeriodo"   ).show();
	           	 $(".configStatus"    ).show();

				
	           	filtrarStatus('<%=TipoListaValor.EventoConciliacao.getValor()%>');

		$("#diasAtraso").val("");
		$(".configDiasAtraso").hide();

		$(".configEmpresas").show();
		$(".configSistemas").show();
		$(".configProdutos").show();
		$(".configPeriodo").show();
		$(".configStatus").show();

	}

	function configurarRelatorioCobrancaNaoConciliadas() {

		$("#periodoDe").val("");

		$("#periodoAte").val("");
		$(".configPeriodo").hide();

		$(".configStatus").hide();

		$(".configEmpresas").show();
		$(".configSistemas").show();
		$(".configProdutos").show();
		$(".configDiasAtraso").show();
	}

	function removePeriodoCampoVazio() {
		$("#periodoDe, #periodoAte").removeClass("CampoVazio");
		$("#periodoDe, #periodoAte").val("");
	}

	function configurar() {

		$("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
		$("select[name='relatorio']").parent().removeClass("CampoVazio");
		//$("#periodoDe, #periodoAte").removeClass("CampoVazio");
		$("#diasAtraso").removeClass("CampoVazio");
		
		if ($("select[name='relatorio']").val() == 0)
			limpar();

		if ($("select[name='relatorio']").val() == 1) {
			configurarRelatorioPrimeiraCobranca();
		}

		if ($("select[name='relatorio']").val() == 2) {
			configurarRelatorioVendasEstornadas();
		}

		if ($("select[name='relatorio']").val() == 3) {
			configurarRelatorioCobrancaNaoConciliadas();
		}

		if ($("select[name='relatorio']").val() == 4) {
			configurarRelatorioCobrancaConciliacaoInterface();
		}

		if ($("select[name='relatorio']").val() == 5) {
			configurarRelatorioConciliacao();
		}

	}

	function limpar() {

		$('#GerarRelatorioVendasActionForm').each(function() {
// 			this.reset();

			if("${verificaForm}" == "S"){
				paginaAnterior = document.referrer;
				window.location.href = paginaAnterior;
			}else{
				window.location.reload();
			}

		});

		$(".configEmpresas").hide();
		$(".configSistemas").hide();
		$(".configProdutos").hide();
		$(".configPeriodo").hide();
		$(".configStatus").hide();
		$(".configDiasAtraso").hide();

		$("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
		$("select[name='relatorio']").parent().removeClass("CampoVazio");
		$("#periodoDe, #periodoAte").removeClass("CampoVazio");

		$("select[name=produto]").attr("disabled", "disabled");
		$("select[name=produto]")
				.html(
						"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");

		$("select[name=sistema]").attr("disabled", "disabled");
		$("select[name=sistema]")
				.html(
						"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");

	}

	function doAjaxCount() {
		var status = "";
		if ($("select[name='relatorio']").val() == 1)
			$("#op").val("6");
		if ($("select[name='relatorio']").val() == 2)
			$("#op").val("7");
		if ($("select[name='relatorio']").val() == 3)
			$("#op").val("10");
		if ($("select[name='relatorio']").val() == 4)
			$("#op").val("11");
		if ($("select[name='relatorio']").val() == 5)
			$("#op").val("16");

		status = $("select[name='status']").val();
		$("#statusSel").val(status);

		imprimir();

		/* if(  ($("#op").val()=="10"&& $("#diasAtraso").val()=="")||($("select[name='relatorio']").val() == 0)  ){
		    imprimir();
		}
		else{
		$.ajax({
		        url:"../dbs/listas",
		      	type: "POST",
		       data:$('#GerarRelatorioVendasActionForm').serialize(),
		       success:
		            function(retorno){
		        	 	 var size = myTrim(retorno);
		        	  		 if(size=='0'){
		        				  mostrarMensagem("<bean:message key="msg.info.dados.pesquisa.vazia"/>", "dialogoAjuda", 400);
		            	 	 }
		        	  		  else{
		       			  		imprimir();
		        	   			  }
		             },
		       error:
		            	function(e){
		           		alert('Error: ' + e);
		       		}
		   });

		}	  */
	}

	function filtrarProdutos(codEmpresa) {
		if (codEmpresa == "" || codEmpresa == "0") {
			$("select[name=produto], select[name=sistema]").attr("disabled",
					"disabled");
			$("select[name=produto], select[name=sistema]")
					.html(
							"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
		} else {
			$
					.ajax({
						url : "../dbs/listas",
						data : "op=1&cod=" + codEmpresa,
						success : function(produtos) {
							if (produtos.indexOf("option") != -1) {
								$("select[name=produto]")
										.removeAttr("disabled");
								$("select[name=produto]")
										.attr("onchange",
												"javascript: filtrarSistemas(this.value)");
								$("select[name=produto]").html(produtos);
							} else {
								$("select[name=produto], select[name=sistema]")
										.attr("disabled", "disabled");
								$("select[name=produto], select[name=sistema]")
										.html(
												"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
							}
						}
					});
		}
	}

	function filtrarSistemas(idProduto) {
		if (idProduto == "" || idProduto == "0") {
			$("select[name=sistema]").attr("disabled", "disabled");
			$("select[name=sistema]")
					.html(
							"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
		} else {
			$
					.ajax({
						url : "../dbs/listas",
						data : "op=2&id=" + idProduto,
						success : function(sistemas) {
							if (sistemas.indexOf("option") != -1) {
								$("select[name=sistema]")
										.removeAttr("disabled");
								$("select[name=sistema]").html(sistemas);
							} else {
								$("select[name=sistema]").attr("disabled",
										"disabled");
								$("select[name=sistema]")
										.html(
												"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
							}
						}
					});
		}
	}

	function filtrarStatus(codListaValor) {

		if ($("select[name='relatorio']").val() == 1)
			op = '12';
		if ($("select[name='relatorio']").val() == 2)
			op = '13';
		if ($("select[name='relatorio']").val() == 4)
			op = '14';
		if ($("select[name='relatorio']").val() == 5)
			op = '15';

		$
				.ajax({
					url : "../dbs/listas",
					data : "op=" + op + "&codListaValor=" + codListaValor,
					success : function(statuses) {
						if (statuses.indexOf("option") != -1) {
							$("select[name=status]").removeAttr("disabled");
							$("select[name=status]").html(statuses);
						} else {
							$("select[name=status]").attr("disabled",
									"disabled");
							$("select[name=status]")
									.html(
											"<option value='0'>- <bean:message key="msg.selecione"/> -</option>");
						}
					}
				});
	}
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
				<bean:message key="label.breadcrumb.relatorios" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="GerarRelatorioVendasActionForm"
				id="GerarRelatorioVendasActionForm" action="#" method="post">
				<input type="hidden" name="op" id="op"> <input
					type="hidden" name="statusSel" id="statusSel">
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- RELATÓRIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.relatorio" /></label></td>
								<td colspan="3">
									<div id="divTipoSolicitacao"
										class="formataCampoSelectForm divSelectConsulta">
										<select name="relatorio" id="relatorio" class="selectConsulta"
											onchange="javascript:removePeriodoCampoVazio();configurar();">
											<option value="0">-
												<bean:message key="msg.relatorio.selecione" /> -
											</option>
											<option value="1"><bean:message
													key="relatorio.primeira.cobranca.capturada.nao.autorizada" /></option>
											<option value="2"><bean:message
													key="relatorio.cobranca.estornada.cancelada" /></option>
											 
											<option value="3"><bean:message
													key="relatorio.cobranca.nao.conciliada" /></option>
											<option value="4"><bean:message
													key="relatorio.cobranca.conciliada.interface" /></option>
											<option value="5"><bean:message
													key="relatorio.conciliacao.planilha.aba.nome" /></option>
											

										</select>
									</div>
								</td>
							</tr>
							<%-- EMPRESA ================================================================== --%>
							<tr class="configEmpresas">
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
							<tr class="configProdutos">
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
							<tr class="configSistemas">
								<td class="label ui-widget-header"><label><bean:message
											key="label.sistema" /></label></td>
								<td colspan="3">
									<div id="divTipoSolicitacao"
										class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="sistema" id="sistema" style="width: 100%;"
											options="${produtos_filtro_sistema}" optionText="descricao"
											optionValue="id" />


									</div>
								</td>
							</tr>
							<%-- STATUS ================================================================== --%>
							<tr class="configStatus">
								<td class="label ui-widget-header"><label><bean:message
											key="label.status.cobranca" /></label></td>
								<td colspan="3">
									<div id="divTipoSolicitacao"
										class="formataCampoSelectForm divSelectConsulta">
										<%-- <dph:dropdown name="status" id="status" style="width: 100%;"
											options="${lista_item_lista}" optionText="descricao"
											optionValue="id"
											/> --%>

										<select name="status" class="selectConsulta">
											<option value="0"><bean:message key="msg.todos" /></option>
										</select>

									</div>
								</td>
							</tr>
							<%-- PERÍODO ====================================================== --%>
							<tr class="configPeriodo">
								<td class="label ui-widget-header"><label><bean:message
											key="label.periodo" /></label></td>
								<td><input type="text" name="periodoDe" id="periodoDe"
									class="periodoDe" readonly />
									&nbsp; a &nbsp; <input type="text" name="periodoAte"
									id="periodoAte" class="periodoAte" readonly />
								</td>
							</tr>
							<%-- DIAS ATRASO ====================================================== --%>
							<tr class="configDiasAtraso">
								<td class="label ui-widget-header"><label><bean:message
											key="cobranca.nao.conciliada.dias.atraso" /></label></td>
								<td><input type="text" name="diasAtraso" id="diasAtraso" />
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
				<td class="botoesAvancar"><a href="javascript:limpar()"> <span
						class="Icone22x22 IconeLimpar"></span> <bean:message
							key="label.botao.limpar" /><br> &nbsp;
				</a> <a style="width: 70px;" href="javascript: doAjaxCount()"> <span
						class="Icone22x22 IconeGerarPlanilha"></span> <bean:message
							key="label.botao.gerar.planilha" /><br> &nbsp;
				</a></td>
			</tr>
		</table>
	</div>
	<div class="barras sul">
		<p>
			<bean:message key="label.rodape" />
		</p>
	</div>
	<% 
	request.getSession().removeAttribute("verificaForm");
	request.getSession().removeAttribute("relatorio_selecionado");
	request.getSession().removeAttribute("status_selecionado");

	request.getSession().removeAttribute("codEmpresa");
	request.getSession().removeAttribute("idProduto");
	request.getSession().removeAttribute("idSistema");
	request.getSession().removeAttribute("status");
	request.getSession().removeAttribute("relatorio");
	request.getSession().removeAttribute("periodoDe");
	request.getSession().removeAttribute("periodoAte");
	request.getSession().removeAttribute("periodoAte");
	request.getSession().removeAttribute("pesquisaVazia");
	%>
</body>
</html>