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
		$("#prazoPagamentoDiasContratoCobranca").setMask();
		
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

		var idContratoCobranca = '${idContratoCobranca}'; // TODO VER DEPOIS

		$(".menuReq").hide();

        var idCalendario = $( "#inicioVigenciaContratoCobranca, #fimVigenciaContratoCobranca" );             
        idCalendario.datepicker({
            showOn: "button",
            changeMonth: true,
            changeYear: true,
            buttonImage: "../img/icones/calendario_22x22.png",
            buttonImageOnly: true,
            showAnim: 'slideDown' 
        });
        
        $(".ui-datepicker-trigger").tipTip({
            maxWidth: "auto",
            edgeOffset: 10,
            defaultPosition: "bottom"
        });

 		if (document.pesquisaContratoCobrancaForm.codigoEmpresaContratoCobranca.value != null) {
 			filtrarProdutosEdicaoComDiferenca(document.pesquisaContratoCobrancaForm.codigoEmpresaContratoCobranca.value);
 		}

 		empresa = $("#codigoEmpresaContratoCobranca").val();
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />
	
        <jsp:useBean id="ContratoCobrancaVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="ContratoCobrancaVH" property="idContratoCobranca" value="${param.idContratoCobranca}" />

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.contratoCobranca"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.contratoCobranca" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaContratoCobrancaForm" id="pesquisaContratoCobranca"
				action="../configuracao/resultadoContratoCobranca.do" method="post">
				
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idContratoCobranca" name="idContratoCobranca" value="${ConfiguracaoActionForm.idContratoCobranca}">
				<input type="hidden" id="produtosAssociados" name="produtosAssociados" value="${ConfiguracaoActionForm.produtosAssociados}">
				<input type="hidden" id="opcao" name="opcao" value="manterContratoCobranca" />
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO DA EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.empresa" /></label></td>
								<td colspan="3">
								<div class="formataCampoSelectForm divSelectConsulta">
								<!-- options="${empresas_usuario_logado}" -->
									<dph:dropdown name="codigoEmpresaContratoCobranca" id="codigoEmpresaContratoCobranca" style="width: 100%;"
										options="${ContratoCobrancaVH.listarEmpresa()}" optionText="descricao"
										optionValue="codigo" value="${ConfiguracaoActionForm.codigoEmpresaContratoCobranca}"
										onchange="javascript: filtrarProdutos(this.value);"
										/>	
								</div>
								</td>
							</tr>
							<%-- CÓDIGO DO MEIO DE PAGAMENTO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.meio.pagamento" /></label></td>
								<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="codigoMeioPagamentoContratoCobranca" id="codigoMeioPagamentoContratoCobranca" style="width: 100%;"
											options="${ContratoCobrancaVH.listarMeioPagamento()}" optionText="descricao"
											optionValue="codigo" value="${ConfiguracaoActionForm.codigoMeioPagamentoContratoCobranca}"
											/>
									</div>
								</td>
							</tr>
							<%-- CÓDIGO DO PROVEDOR ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.provedor" /></label></td>
								<td colspan="3">
									<div class="formataCampoSelectForm divSelectConsulta">
										<dph:dropdown name="codigoProvedorContratoCobranca" id="codigoProvedorContratoCobranca" style="width: 100%;"
											options="${ContratoCobrancaVH.listarProvedorMeioPagamento()}" optionText="descricaoProvedor"
											optionValue="codigoProvedor" value="${ConfiguracaoActionForm.codigoProvedorContratoCobranca}"
											/>
									</div>
								</td>
							</tr>
							<%-- DESCRIÇÃO DO CONTRATO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.contratoCobranca" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoContratoCobranca" id="descricaoContratoCobranca" alt="descricaoContratoCobranca" 
									value="${ConfiguracaoActionForm.descricaoContratoCobranca}" maxlength="500"
									class="parametros"/>
								</td>
							</tr>
							<%-- CÓDIGO ACESSO AO SERVIÇO DO PROVEDOR  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.cod.acesso.servico.provedor" /></label></td>
								<td colspan="3">
									<input type="text" name="codEmpresaProvMeioPagamentoContratoCobranca" id="codEmpresaProvMeioPagamentoContratoCobranca" alt="codEmpresaProvMeioPagamentoContratoCobranca" 
									value="${ConfiguracaoActionForm.codEmpresaProvMeioPagamentoContratoCobranca}" maxlength="36"
									class="parametros"/>
								</td>
							</tr>
							<%-- Prazo de pagamento em dias  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.prazo.pagamento.dias.contratoCobranca" /></label></td>
								<td colspan="3">
									<input type="text" name="prazoPagamentoDiasContratoCobranca" id="prazoPagamentoDiasContratoCobranca" 
									alt="tres-digitos" 
									value="${ConfiguracaoActionForm.prazoPagamentoDiasContratoCobranca}" maxlength="3"
									class="parametros"/>
								</td>
							</tr>
							<%-- Tipo transação provedor  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.tipo.transacao.provedor.contratoCobranca" /></label></td>
								<td colspan="3">
									<input type="text" name="tipoTransacaoProvedorContratoCobranca" id="tipoTransacaoProvedorContratoCobranca" alt="tipoTransacaoProvedorContratoCobranca" 
									value="${ConfiguracaoActionForm.tipoTransacaoProvedorContratoCobranca}" maxlength="3"
									class="parametros"/>
									<a href="#" class="IconeObs tooltipTop" title="<bean:message key="msg.info.tipoTransacaoProvedor" />" >
                                        <span class="Icone16x16 IconeAjuda" ></span>                           
                                    </a>
								</td>
							</tr>
							<%-- Data de início de vigência do contrato  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.inicio.vigencia.contratoCobranca" /></label></td>
								<td colspan="3">
                                        <input 
                                            type="text" 
                                            name="inicioVigenciaContratoCobranca"
                                            id="inicioVigenciaContratoCobranca"
                                            class="periodoDe"
                                            value="${ConfiguracaoActionForm.inicioVigenciaContratoCobranca}"
                                            readonly
                                            />
								</td>
							</tr>
							<%-- Data final de vigência do contrato  ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.fim.vigencia.contratoCobranca" /></label></td>
								<td colspan="3">
                                        <input 
                                            type="text" 
                                            name="fimVigenciaContratoCobranca"
                                            id="fimVigenciaContratoCobranca"
                                            class="periodoAte"
                                            value="${ConfiguracaoActionForm.fimVigenciaContratoCobranca}"
                                            readonly
                                            />
								</td>
							</tr>
							<%-- ASSOCIAR PRODUTOS ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarProduto" /></label></td>
								<td colspan="3">
	                              	<table class="TabelaPrin dataTable multiSelectTable" style="width: 94%" cellspacing="1">
				                        <tr>
				                            <td>
				                                <dph:multiselect
				                                	idFrom="associacaoFrom"
				                                	idTo="associacaoTo"
				                                    name="associacao"
				                                    fromList="${produtosNaoAssociados}"
				                                    fromPropertyText="descricao"
				                                    fromPropertyValue="id"
				                                    fromTitle="Sem Associação"
				                                    toList="${produtosAssociados}"
				                                    toPropertyText="descricao"
				                                    toPropertyValue="id"
				                                    toTitle="Associados"
				                                    height="10"
				                                    width="200px" />
				                            </td>
				                        </tr>
				                    </table>
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
						<bean:message key="label.botao.limpar" /><br><br> &nbsp;
					</a> 
                    <a href="javascript:prosseguir()">
                        <span class="Icone22x22 IconeConfirmar" ></span>
                        <bean:message key="label.botao.gravar"/><br><br> &nbsp;
                    </a>
					<c:choose> 
				    <c:when test="${ConfiguracaoActionForm.opcao ne 'incluir'}">
					    <logic:messagesNotPresent>
							<a href="javascript:associarAdquirente()">
								<span class="Icone22x22 IconeAssociar"></span> 
								<bean:message key="label.botao.associacao.adquirente" /><br> &nbsp;
							</a> 
							<a href="javascript:configurarAutorizacao()">
								<span class="Icone22x22 IconeConfigurar"></span> 
								<bean:message key="label.botao.configurar.autorizacao" /><br> &nbsp;
							</a> 
						</logic:messagesNotPresent>
					</c:when>
					</c:choose>
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
	        document.pesquisaContratoCobrancaForm.method = "post";
	        document.pesquisaContratoCobrancaForm.opcao.value = "prepararVoltar";
	        document.pesquisaContratoCobrancaForm.action = "../configuracao/prepararContratoCobranca.do";
	        document.pesquisaContratoCobrancaForm.submit();   
	    }

		var empresa = "";

		function filtrarProdutos(codEmpresa) {

			if ($("select[name=associacaosel2] option").length > 0) {
				confirmar("<bean:message key="msg.confirma.exclusao.produtos.contratoCobranca" />", "dialogoPergunta", 400, confirmarAlteracaoEmpresaCB(codEmpresa));
			} else {
				empresa = codEmpresa;
				$("select[name=associacaosel1], select[name=associacaosel2]").removeAttr("disabled");
				$("select[name=associacaosel1], select[name=associacaosel2]").html("");
				executaFiltroProdutos(codEmpresa);
			}
			
		}

		function filtrarProdutosEdicaoComDiferenca(codEmpresa) {
			if (codEmpresa == null || codEmpresa == "" || codEmpresa == true) {
				if (empresa != null || empresa != "") {
					codEmpresa = empresa;
				}
			}
			if (codEmpresa == "" || codEmpresa == "0") {
	            $("select[name=associacaosel1], select[name=associacaosel2]").html("");
	            $("select[name=associacaosel1], select[name=associacaosel2]").attr("disabled","disabled");  
			} else {
				$.ajax({
			        url:"../dbs/listas",
			        data:"op=1&tela=contratoCobranca&cod="+codEmpresa,
			        success:function(produtos){
			        	if (produtos.indexOf("option") !=-1) {
			                $("select[name=associacaosel1]").removeAttr("disabled");
			            	//$("select[name=produto]").attr("onchange","javascript: filtrarSistemas(this.value)");
			                $("select[name=associacaosel1]").html(produtos);   
		              	    $('select[name=associacaosel2] option').each(function(index){
		              	    	$("select[name=associacaosel1] option[value=" + $(this).val() + "]").remove();
		              	    });

			            } else {
			                $("select[name=associacaosel1], select[name=associacaosel2]").html("");
			                $("select[name=associacaosel1], select[name=associacaosel2]").attr("disabled","disabled");
			            }
			        }
			    });
			}
		}

		function confirmarAlteracaoEmpresaCB(codigoEmpresa) {
			return function(mudandoEmpresa) {
				if (mudandoEmpresa) {
					empresa = codigoEmpresa;
					executaFiltroProdutos(codigoEmpresa);
				} else {
					$("#codigoEmpresaContratoCobranca").val(empresa);
				}
			};
		}
		
		function executaFiltroProdutos(codEmpresa) {
			if (codEmpresa == null || codEmpresa == "" || codEmpresa == true) {
				if (empresa != null || empresa != "") {
					codEmpresa = empresa;
				}
			}
			if (codEmpresa == "" || codEmpresa == "0") {
	            $("select[name=associacaosel1], select[name=associacaosel2]").html("");
	            $("select[name=associacaosel1], select[name=associacaosel2]").attr("disabled","disabled");  
			} else {
				$.ajax({
			        url:"../dbs/listas",
			        data:"op=1&tela=contratoCobranca&cod="+codEmpresa,
			        success:function(produtos){
			        	if (produtos.indexOf("option") !=-1) {
			        		$("select[name=associacaosel2]").html("");
			                $("select[name=associacaosel1]").removeAttr("disabled");
			            	//$("select[name=produto]").attr("onchange","javascript: filtrarSistemas(this.value)");
			                $("select[name=associacaosel1]").html(produtos);                    
			            } else {
			                $("select[name=associacaosel1], select[name=associacaosel2]").html("");
			                $("select[name=associacaosel1], select[name=associacaosel2]").attr("disabled","disabled");
			            }
			        }
			    });
			}
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

		function associarAdquirente() {
			document.pesquisaContratoCobrancaForm.opcao.value = "adquirenteBandeiraSemValidacao";
			document.location = "../configuracao/associarAdquirente.do?idContrato="+ document.pesquisaContratoCobrancaForm.idContratoCobranca.value
			+"&opcao="+document.pesquisaContratoCobrancaForm.opcao.value;
			//document.location = "../configuracao/associarAdquirente.do?idContrato=1";
		}

		function configurarAutorizacao() {
			document.location = "../configuracao/associarAdquirente.do?idContrato="+ 
			document.pesquisaContratoCobrancaForm.idContratoCobranca.value + 
			"&opcao=respostaAutorizacao";
			//document.location = "../configuracao/manterRespostaAutorizacao.jsp";
		}
		
		function limpar(origem) {

			if (document.pesquisaContratoCobrancaForm.idContratoCobranca.value) {
				$("form[name=pesquisaContratoCobrancaForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaContratoCobrancaForm] select:not([readonly]):not([disabled]):not([name=codigoEmpresaContratoCobranca])").val("");
				$("#inicioVigenciaContratoCobranca, #fimVigenciaContratoCobranca").each(function() {
					$.datepicker._clearDate(this);
				});
				$("#associacaoTo option").each(function () {
					$("#associacaoFrom").append($(this));
				});
				$("#associacaoTo").html("");

				limparSessao();
								
			} else {
				document.location = "../configuracao/prepararContratoCobranca.do?opcao=incluir";
				//document.location = "../configuracao/manterContratoCobranca.jsp";
			}
		}

		function limparSessao() {

			$.ajax({
		        url:"../dbs/limparsessao",
		        data:"opcao=limparSessao",
		        success:function(retorno){
		        	if (retorno.indexOf("sucesso") !=-1) {
		            } else {
		            }
		        }
		    });
			
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(contratoCobranca){

        	document.location = "../configuracao/editarContratoCobranca.do?idPesquisa="+contratoCobranca;

        }

        function confirmarExclusao(contratoCobranca){
        	prosseguirExclusao(contratoCobranca);
        }

        function prosseguirExclusao(contratoCobranca) {

        	idContratoCobranca = contratoCobranca;
            
            confirmar("<bean:message key="msg.confirma.exclusao.contratoCobranca" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.contratoCobranca.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaContratoCobrancaForm.method = "post";
                //document.pesquisaContratoCobrancaForm.idContratoCobranca.value=idContratoCobranca;
                document.pesquisaContratoCobrancaForm.action = "../configuracao/manterContratoCobranca.do?operacao=3";
                document.pesquisaContratoCobrancaForm.submit();      
            }
        }
        
        function prosseguir() {

            confirmar("<bean:message key="msg.confirma.contratoCobranca" />", "dialogoPergunta", 400, prosseguirCB);
    		        
        }

        function prosseguirCB(retorno) {                
            if (retorno){                  
            	//mostrarMensagem("<bean:message key="msg.contratoCobranca.incluido.sucesso"/>", "dialogoSucesso", 400);    
            	
           	   var options = Array();
           	    $('select[name=associacaosel2] option').each(function(index){
           	        options[index] = $(this).val();
           	    });

           	    $('#produtosAssociados').val(options.join(','));
            	   
                 document.pesquisaContratoCobrancaForm.method = "post";
                 document.pesquisaContratoCobrancaForm.opcao.value="manterContratoCobranca";
                 document.pesquisaContratoCobrancaForm.action = "../configuracao/manterContratoCobranca.do?operacao=2";
                 document.pesquisaContratoCobrancaForm.submit();  
            }
        }
	</script>
</body>
</html>