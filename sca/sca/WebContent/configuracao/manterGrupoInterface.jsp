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

		var idGrupoInterface = '${idGrupoInterface}'; // TODO VER DEPOIS

		$(".menuReq").hide();

	});

    function prosseguir() {
          confirmar("<bean:message key="msg.confirma.grupoInterface" />", "dialogoPergunta", 400, prosseguirCB);
                
    }

    function prosseguirCB(retorno) {                
        if (retorno){                  
        	//mostrarMensagem("<bean:message key="msg.grupoInterface.incluido.sucesso"/>", "dialogoSucesso", 400);    
        	//document.location = "../configuracao/manterSistema.jsp";   
        	
     	   var options2 = Array();
    	    $('select[name=associacao2sel2] option').each(function(index){
    	    	options2[index] = $(this).val();
    	    });

    	    $('#gruposUsuariosAssociados').val(options2.join(','));
        	
            document.pesquisaGrupoInterfaceForm.method = "post";
            document.pesquisaGrupoInterfaceForm.opcao.value="grupoInterface";
            document.pesquisaGrupoInterfaceForm.action = "../configuracao/manterGrupoInterface.do?operacao=2";
            document.pesquisaGrupoInterfaceForm.submit();  
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
	
        <jsp:useBean id="GrupoInterfaceVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"/>
        <jsp:setProperty name="GrupoInterfaceVH" property="idGrupoInterface" value="${param.idGrupoInterface}" />
        
	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
			    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.pesquisar.grupoInterface" />&nbsp;>&nbsp;</a>
                <bean:message key="label.breadcrumb.grupoInterface" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaGrupoInterfaceForm" id="pesquisaGrupoInterface"
				action="#" method="post">
				
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="idGrupoInterface" name="idGrupoInterface" value="${ConfiguracaoActionForm.idGrupoInterface}">
				<input type="hidden" id="opcao" name="opcao" value="grupoInterface">
				<input type="hidden" id="gruposUsuariosAssociados" name="gruposUsuariosAssociados" value="${ConfiguracaoActionForm.gruposUsuariosAssociados}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DA INTERFACE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message
											key="label.descricao.grupoInterface" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoGrupoInterface" id="descricaoGrupoInterface" alt="descricaoGrupoInterface" 
									value="${ConfiguracaoActionForm.descricaoGrupoInterface}" maxlength="255"
									class="parametros"/>	
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
				                                    idFrom="associacaoFrom"
				                                    idTo="associacaoTo"
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

			if (document.pesquisaGrupoInterfaceForm.idGrupoInterface.value) {
				$("form[name=pesquisaGrupoInterfaceForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaGrupoInterfaceForm] select:not([readonly]):not([disabled]):not([name=codigoEmpresaContratoCobranca])").val("");
				$("#associacaoTo option").each(function () {
					$("#associacaoFrom").append($(this));
				});
				$("#associacaoTo").html("");
								
			} else {
				//document.location = "../configuracao/manterGrupoInterface.jsp";
				document.location = "../configuracao/prepararGrupoInterface.do?opcao=incluir";
			}
		}
		
        function exibirRetornoPesquisa(){
        	$("#divHidden").show();
        }

        function exibirDetalhe(grupoInterface){

        	document.location = "../configuracao/editarGrupoInterface.do?idPesquisa="+grupoInterface;

        }

        function confirmarExclusao(grupoInterface){
        	prosseguirExclusao(grupoInterface);
        }

        function prosseguirExclusao(grupoInterface) {

        	idGrupoInterface = grupoInterface;
            
            confirmar("<bean:message key="msg.confirma.exclusao.grupoInterface" />", "dialogoPergunta", 400, executarExclusao);
        }

        function executarExclusao(retorno) {                
            if (retorno){                  
            	//$('#myTable tr:last').remove();
            	//$("div[id=divHidden] > div > div > div > table > tbody tr:last").remove();
            	//mostrarMensagem("<bean:message key="msg.grupoInterface.excluido.sucesso"/>", "dialogoSucesso", 400);    
                document.pesquisaGrupoInterfaceForm.method = "post";
                document.pesquisaGrupoInterfaceForm.idGrupoInterface.value=idGrupoInterface;
                document.pesquisaGrupoInterfaceForm.action = "../configuracao/manterGrupoInterface.do?operacao=3";
                document.pesquisaGrupoInterfaceForm.submit();
            }
        }

    	function voltar(){
            //document.location = "../configuracao/prepararGrupoInterface.do";
            document.pesquisaGrupoInterfaceForm.method = "post";
            document.pesquisaGrupoInterfaceForm.opcao.value="listarGrupoInterface";
            document.pesquisaGrupoInterfaceForm.action = "../configuracao/prepararGrupoInterface.do";
            document.pesquisaGrupoInterfaceForm.submit();
        }

	</script>
</body>
</html>