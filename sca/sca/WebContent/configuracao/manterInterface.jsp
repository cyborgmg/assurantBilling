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
	});
</script>
<dbs:dialogosLista />
</head>
<body>
	<div class="barras norte">
		<dbs:topoTela />
	</div>

	<dbs:menu />

	 	<jsp:useBean id="InterfaceVH" class="br.com.delphos.web.configuracao.ConfiguracaoViewHelper" scope="page"					/>


	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.interface"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.interface" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaInterfaceForm" id="pesquisaInterface"
				action="../configuracao/resultadoInterface.do" method="post">
				
				<input type="hidden" id="idInterface" name="idInterface" value="${ConfiguracaoActionForm.idInterface}">
		
				<input type="hidden" id="opcao" name="opcao" value="interface">
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="gruposInterfaceAssociados" name="gruposInterfaceAssociados" value="${ConfiguracaoActionForm.gruposInterfaceAssociados}">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- DESCRIÇÃO DO INTERFACE ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message	key="label.descricao.interface" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoInterface" id="descricaoInterface" alt="descricaoInterface" 
									value="${ConfiguracaoActionForm.descricaoInterface}" maxlength="255" class="parametros"/>	
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
		function limpar(origem) {
			
			if (document.pesquisaInterfaceForm.idInterface.value) {
				$("form[name=pesquisaInterfaceForm] input[type=text]:not([readonly]):not([disabled])").val("");
				$("form[name=pesquisaInterfaceForm] select:not([readonly]):not([disabled]):not([name=codigoEmpresaContratoCobranca])").val("");
				$("#associacaoTo option").each(function () {
					$("#associacaoFrom").append($(this));
				});
				$("#associacaoTo").html("");
								
			} else {
				//document.location = "../configuracao/manterInterface.jsp";
				document.location = "../configuracao/editarInterface.do";
			}
		}

	    function prosseguir() {
	    	confirmar("<bean:message key="msg.confirma.interfaci" />", "dialogoPergunta", 400, prosseguirCB);	
	    }

	    function prosseguirCB(retorno) {                
	        if (retorno){                  

	      	   var options2 = Array();
	    	    $('select[name=associacao2sel2] option').each(function(index){
	    	    	options2[index] = $(this).val();
	    	    });

	    	    $('#gruposInterfaceAssociados').val(options2.join(','));
	    	    		        
	        	//mostrarMensagem("<bean:message key="msg.interface.incluido.sucesso"/>", "dialogoSucesso", 400);       
 	            document.pesquisaInterfaceForm.method = "post";
 	            document.pesquisaInterfaceForm.action = "../configuracao/editarInterface.do?operacao=2";
	            document.pesquisaInterfaceForm.submit();  
	        }
	    }

	    function SomenteNumero(e){
	    	var tecla=(window.event)?event.keyCode:e.which;   
	    	if((tecla>47 && tecla<58)) return true;
	    	else{
	    		if (tecla==8 || tecla==0) return true;
	    		else  return false;
	    		}
	    	}

    	function voltar(){
            //window.location = "../pesquisa/pesquisarUsuario.jsp";
            document.pesquisaInterfaceForm.method = "post";
            document.pesquisaInterfaceForm.opcao.value = "listarInterface";
            document.pesquisaInterfaceForm.action = "../configuracao/resultadoInterface.do";
            document.pesquisaInterfaceForm.submit();
        }

	</script>
</body>
</html>