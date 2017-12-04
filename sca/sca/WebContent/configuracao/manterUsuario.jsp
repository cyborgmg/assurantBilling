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

		var idUsuario = '${idUsuario}'; // TODO VER DEPOIS

		$(".menuReq").hide();

	});

    function prosseguir() {

        confirmar("<bean:message key="msg.confirma.usuario" />", "dialogoPergunta", 400, prosseguirCB);
                
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.usuario.incluido.sucesso"/>", "dialogoSucesso", 400);    
        	//document.location = "../configuracao/manterSistema.jsp";   
        	
       	   var options = Array();
       	    $('select[name=associacao1sel2] option').each(function(index){
       	        options[index] = $(this).val();
       	    });

       	    $('#empresasAssociadas').val(options.join(','));

     	   var options2 = Array();
    	    $('select[name=associacao2sel2] option').each(function(index){
    	    	options2[index] = $(this).val();
    	    });

    	    $('#gruposUsuariosAssociados').val(options2.join(','));
        	
            document.pesquisaUsuarioForm.method = "post";
            document.pesquisaUsuarioForm.opcao.value="manterUsuario";
            document.pesquisaUsuarioForm.action = "../configuracao/manterUsuario.do?operacao=2";
            document.pesquisaUsuarioForm.submit();  
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
	
        <jsp:useBean id="UsuarioVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="UsuarioVH" property="idSistema" value="${param.idUsuario}" />
        
	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
			    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.usuario" />&nbsp;>&nbsp;</a>
                <bean:message key="label.breadcrumb.usuario" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaUsuarioForm" id="pesquisaUsuario"
				action="#" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idUsuario" name="idUsuario" value="${ConfiguracaoActionForm.idUsuario}">
				<input type="hidden" id="empresasAssociadas" name="empresasAssociadas" value="${ConfiguracaoActionForm.empresasAssociadas}">
				<input type="hidden" id="gruposUsuariosAssociados" name="gruposUsuariosAssociados" value="${ConfiguracaoActionForm.gruposUsuariosAssociados}">
				<input type="hidden" id="opcao" name="opcao" value="usuario">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CÓDIGO DO USUARIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message
											key="label.codigo.usuario" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoUsuario" id="codigoUsuario" alt="codigoUsuario" 
									value="${ConfiguracaoActionForm.codigoUsuario}" maxlength="50"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRIÇÃO DO USUARIO ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.usuario" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoUsuario" id="descricaoUsuario" alt="descricaoUsuario" 
									value="${ConfiguracaoActionForm.descricaoUsuario}" maxlength="255"
									class="parametros"/>	
								</td>
							</tr>
							<%-- EMAIL ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.email" /></label></td>
								<td colspan="3">
									<input type="text" name="email" id="email" alt="email" 
									value="${ConfiguracaoActionForm.email}" maxlength="255"
									class="parametros"/>	
								</td>
							</tr>
							<%-- ASSOCIAR EMPRESAS ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarEmpresa" /></label></td>
								<td colspan="3">
	                              	<table class="TabelaPrin dataTable multiSelectTable" style="width: 94%" cellspacing="1">
				                        <tr>
				                            <td>
				                                <dph:multiselect
				                                    name="associacao1"
				                                    idFrom="associacaoEmpresaFrom"
				                                    idTo="associacaoEmpresaTo"
				                                    fromList="${empresasNaoAssociadas}"
				                                    fromPropertyText="descricao"
				                                    fromPropertyValue="id"
				                                    fromTitle="Sem Associação"
				                                    toList="${empresasAssociadas}"
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
							<%-- ASSOCIAR GRUPOS DE USUÁRIOS ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.associarGrupoUsuario" /></label></td>
								<td colspan="3">
	                              	<table class="TabelaPrin dataTable multiSelectTable" style="width: 94%" cellspacing="1">
				                        <tr>
				                            <td>
				                                <dph:multiselect
				                                    name="associacao2"
				                                    idFrom="associacaoGrupoFrom"
				                                    idTo="associacaoGrupoTo"
				                                    fromList="${gruposUsuariosNaoAssociados}"
				                                    fromPropertyText="descricao"
				                                    fromPropertyValue="id"
				                                    fromTitle="Sem Associação"
				                                    toList="${gruposUsuariosAssociados}"
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
			
			if (document.pesquisaUsuarioForm.idUsuario.value) {
				$("form[name=pesquisaUsuarioForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaUsuarioForm] select:not([readonly]):not([disabled])").val("");
				$("#associacaoEmpresaTo option").each(function () {
					$("#associacaoEmpresaFrom").append($(this));
				});
				$("#associacaoEmpresaTo").html("");
				$("#associacaoGrupoTo option").each(function () {
					$("#associacaoGrupoFrom").append($(this));
				});
				$("#associacaoGrupoTo").html("");
								
			} else {
				//document.pesquisaUsuarioForm.method = "post";
	            //document.pesquisaUsuarioForm.action = "../configuracao/editarUsuario.do";
	            //document.pesquisaUsuarioForm.opcao.value = "limparUsuario";
	            //document.pesquisaUsuarioForm.submit();
				document.location = "../configuracao/prepararUsuario.do?opcao=incluir";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(itemLista){

        	document.location = "../configuracao/editarUsuario.do?idPesquisa="+itemLista;

        }

        function confirmarExclusao(itemLista){
        	prosseguirExclusao(itemLista);
        }

        function prosseguirExclusao(itemLista) {

        	idSistema = itemLista;
            
            confirmar("<bean:message key="msg.confirma.exclusao.usuario" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	mostrarMensagem("<bean:message key="msg.usuario.excluido.sucesso"/>", "dialogoSucesso", 400);    
                //document.pesquisaUsuarioForm.method = "post";
                document.pesquisaUsuarioForm.idUsuario.value=idUsuario;
                //document.pesquisaUsuarioForm.action = "../configuracao/manterMeioPagamento.do?operacao=3";
                //document.pesquisaUsuarioForm.submit();
            }
        }

    	function voltar(){
            //window.location = "../pesquisa/pesquisarUsuario.jsp";
            document.pesquisaUsuarioForm.method = "post";
            document.pesquisaUsuarioForm.action = "../configuracao/prepararUsuario.do";
            document.pesquisaUsuarioForm.submit();
        }

	</script>
</body>
</html>