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

		var idGrupoUsuario = '${idGrupoUsuario}'; // TODO VER DEPOIS

		$(".menuReq").hide();

	});

    function prosseguir() {

            confirmar("<bean:message key="msg.confirma.grupoUsuario" />", "dialogoPergunta", 400, prosseguirCB);
                
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.grupoUsuario.incluido.sucesso"/>", "dialogoSucesso", 400);    
        	//document.location = "../configuracao/manterSistema.jsp";   
        	
       	   var options = Array();
       	    $('select[name=associacao1sel2] option').each(function(index){
       	        options[index] = $(this).val();
       	    });

       	    $('#usuariosAssociados').val(options.join(','));

     	   var options2 = Array();
    	    $('select[name=associacao2sel2] option').each(function(index){
    	    	options2[index] = $(this).val();
    	    });

    	    $('#gruposInterfaceAssociados').val(options2.join(','));
        	
            document.pesquisaGrupoUsuarioForm.method = "post";
            document.pesquisaGrupoUsuarioForm.opcao.value="manterGrupoUsuario";
            document.pesquisaGrupoUsuarioForm.action = "../configuracao/manterGrupoUsuario.do?operacao=2";
            document.pesquisaGrupoUsuarioForm.submit();  
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
	
        <jsp:useBean id="GrupoUsuarioVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="GrupoUsuarioVH" property="idGrupoUsuario" value="${param.idGrupoUsuario}" />
        
	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
			    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.grupoUsuario" />&nbsp;>&nbsp;</a>
                <bean:message key="label.breadcrumb.grupoUsuario" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaGrupoUsuarioForm" id="pesquisaGrupoUsuario"
				action="#" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idGrupoUsuario" name="idGrupoUsuario" value="${ConfiguracaoActionForm.idGrupoUsuario}">
				<input type="hidden" id="opcao" name="opcao" value="grupoUsuario">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
								
				<input type="hidden" id="usuariosAssociados" name="usuariosAssociados" value="${ConfiguracaoActionForm.usuariosAssociados}">
				<input type="hidden" id="gruposInterfaceAssociados" name="gruposInterfaceAssociados" value="${ConfiguracaoActionForm.gruposInterfaceAssociados}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DO GRUPO USUÁRIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.grupoUsuario" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoGrupoUsuario" id="descricaoGrupoUsuario" alt="descricaoGrupoUsuario" 
									value="${ConfiguracaoActionForm.descricaoGrupoUsuario}" maxlength="255"
									class="parametros"/>	
								</td>
							</tr>
							<%-- TIPO GRUPO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.tipo.grupo" /></label></td>
								<td colspan="3">
									<input type="text" name="tipoGrupo" id="tipoGrupo" alt="tipoGrupo" 
									value="${ConfiguracaoActionForm.tipoGrupo}" maxlength="10"
									class="parametros"/>									
								</td>
							</tr>
							<%-- ASSOCIAR USUÁRIOS ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarUsuario" /></label></td>
								<td colspan="3">
	                              	<table class="TabelaPrin dataTable multiSelectTable" style="width: 94%" cellspacing="1">
				                        <tr>
				                            <td>
				                                <dph:multiselect
				                                    name="associacao1"
				                                    fromList="${usuariosNaoAssociados}"
				                                    fromPropertyText="descricao"
				                                    fromPropertyValue="id"
				                                    fromTitle="Sem Associação"
				                                    toList="${usuariosAssociados}"
				                                    toPropertyText="descricao"
				                                    toPropertyValue="id"
				                                    toTitle="Associados"
				                                    height="10"
				                                    width="190px" />
				                            </td>
				                        </tr>
				                    </table>
								</td>
							</tr>
							<%-- ASSOCIAR GRUPOS DE INTERFACES ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarGrupoInterface" /></label></td>
								<td colspan="3">
	                              	<table class="TabelaPrin dataTable multiSelectTable" style="width: 94%" cellspacing="1">
				                        <tr>
				                            <td>
				                                <dph:multiselect
				                                    name="associacao2"
				                                    idFrom="associacaoFrom"
				                                    idTo="associacaoTo"
				                                    fromList="${gruposInterfaceNaoAssociados}"
				                                    fromPropertyText="descricao"
				                                    fromPropertyValue="id"
				                                    fromTitle="Sem Associação"
				                                    toList="${gruposInterfaceAssociados}"
				                                    toPropertyText="descricao"
				                                    toPropertyValue="id"
				                                    toTitle="Associados"
				                                    height="10"
				                                    width="190px" />
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

			if (document.pesquisaGrupoUsuarioForm.idGrupoUsuario.value) {
				$("form[name=pesquisaGrupoUsuarioForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaGrupoUsuarioForm] select:not([readonly]):not([disabled]):not([name=codigoEmpresaContratoCobranca])").val("");
				$("#associacaoTo option").each(function () {
					$("#associacaoFrom").append($(this));
				});
				$("#associacaoTo").html("");
								
			} else {
				//document.location = "../configuracao/manterGrupoUsuario.jsp";
				document.location = "../configuracao/prepararGrupoUsuario.do?opcao=incluir";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(itemLista){

        	document.location = "../configuracao/editarGrupoUsuario.do?idPesquisa="+itemLista;

        }

        function confirmarExclusao(itemLista){
        	prosseguirExclusao(itemLista);
        }

        function prosseguirExclusao(itemLista) {

        	idSistema = itemLista;
            
            confirmar("<bean:message key="msg.confirma.exclusao.grupoUsuario" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	mostrarMensagem("<bean:message key="msg.grupoUsuario.excluido.sucesso"/>", "dialogoSucesso", 400);    
                //document.pesquisaSistemaForm.method = "post";
                document.pesquisaGrupoUsuarioForm.idGrupoUsuario.value=idGrupoUsuario;
                //document.pesquisaSistemaForm.action = "../configuracao/manterMeioPagamento.do?operacao=3";
                //document.pesquisaSistemaForm.submit();
            }
        }

    	function voltar(){
            //window.location = "../pesquisa/pesquisarGrupoUsuario.jsp";
            document.pesquisaGrupoUsuarioForm.method = "post";
            document.pesquisaGrupoUsuarioForm.opcao.value="listarGrupoUsuario";
            document.pesquisaGrupoUsuarioForm.action = "../configuracao/prepararGrupoUsuario.do";
            document.pesquisaGrupoUsuarioForm.submit();
        }

	</script>
</body>
</html>