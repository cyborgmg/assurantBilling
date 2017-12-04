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

	<div class="barras centro">
		<div class="BoxModal" style="margin: 0 auto;">
			<h2>
				<a href="javascript:voltar()"><bean:message key="label.breadcrumb.pesquisar.empresa"/>&nbsp;>&nbsp;</a>
				<bean:message key="label.empresa" />
			</h2>
		</div>

		<div class="BoxModal">
			<form name="pesquisaEmpresaForm" id="pesquisaEmpresa"
				action="../configuracao/resultadoEmpresa.do" method="post">
				
				<input type="hidden" id="idEmpresa" name="idEmpresa" value="${ConfiguracaoActionForm.idEmpresa}">
				<input type="hidden" id="listaAtiva" name="listaAtiva" value="${ConfiguracaoActionForm.listaAtiva}"/>
				<input type="hidden" id="opcao" name="opcao" value="empresa">
				<input type="hidden" id="novaBusca" name="novaBusca" value="false">
				
				<div class="tabelas">
					<table class="tabelaReadWrite">
						<tbody>
							<%-- CODIGO EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header" style="width: 25%;"><label><bean:message key="label.codigoEmpresa" /></label></td>
								<td colspan="3">
									<input type="text" name="codigoEmpresa" id="codigoEmpresa" alt="codigoEmpresa" 
									value="${ConfiguracaoActionForm.codigoEmpresa}" maxlength="3"
									class="parametros"/>									
								</td>
							</tr>
							<%-- DESCRICAO EMPRESA ================================================================== --%>
							<tr>
								<td class="label ui-widget-header"><label><bean:message key="label.descricaoEmpresa" /></label></td>
								<td colspan="3">
									<input type="text" name="descricaoEmpresa" id="descricaoEmpresa" alt="descricaoEmpresa" 
									value="${ConfiguracaoActionForm.descricaoEmpresa}" maxlength="255"
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

			if (document.pesquisaEmpresaForm.idEmpresa.value) {
				$("form[name=pesquisaEmpresaForm] input[type=text]:not([readonly]):not([disabled])").val("");				

			} else {
				document.location = "../configuracao/manterEmpresa.jsp";
			}
		}

	    function prosseguir() {
	    	confirmar("<bean:message key="msg.confirma.empresa" />", "dialogoPergunta", 400, prosseguirCB);	
	    }

	    function prosseguirCB(retorno) {                
	        if (retorno){                  
	        	//mostrarMensagem("<bean:message key="msg.empresa.incluido.sucesso"/>", "dialogoSucesso", 400);       
 	            document.pesquisaEmpresaForm.method = "post";
 	            document.pesquisaEmpresaForm.action = "../configuracao/manterEmpresa.do?operacao=2";
	            document.pesquisaEmpresaForm.submit();  
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
            document.pesquisaEmpresaForm.method = "post";
            document.pesquisaEmpresaForm.opcao.value = "prepararVoltar";
            document.pesquisaEmpresaForm.action = "../configuracao/prepararEmpresa.do";
            document.pesquisaEmpresaForm.submit();   
        }

	</script>
</body>
</html>