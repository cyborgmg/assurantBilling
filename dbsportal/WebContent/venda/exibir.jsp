<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <awp:head/>
        <style>
        	.hidden { display: none;}
        </style>
        <script type="text/javascript" >            
            $(function(){

                //abas
                $("#abas, #abas2").tabs();

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

            	function getUrlVars() {
                    var vars = [], hash;
                    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
                    for(var i = 0; i < hashes.length; i++)
                    {
                        hash = hashes[i].split('=');
                        vars.push(hash[0]);
                        vars[hash[0]] = hash[1];
                    }
                    return vars;
                }

                //OBTENDO ID DA VENDA 
        		var idVenda = getUrlVars()["idVenda"];
        		$("#idVenda").val(idVenda);

        		$.ajax({
    		        url:"../dbs/listas",
    		        data:"op=4&id="+idVenda,
    		        success:function(cobrancas){
    		        	if (cobrancas.indexOf("option") !=-1) {
    		                $("select[name=cobranca]").html(cobrancas);                    
    		            } else {
    		            	$("select[name=cobranca]").attr("disabled","disabled");
    		                $("select[name=cobranca]").html("<option value='0'>- <bean:message key="msg.selecione"/> -</option>");    
    				    }
    		        }
    		    });
    		    
        		if("${vaf.tipoCobranca}" == "SINGLE PREMIUM A VISTA"){
					$(".numMaxTentativas").hide();
                }

                if(("${vaf.dataCancelamento}" == null || "${vaf.dataCancelamento}" == "") && ("${vaf.motCancelamento}" == null || "${vaf.motCancelamento}" == "")){
                	$(".cancelamento").hide();
                }
                
                //CONTROLE DAS CORES E CLICKs DAS LINHAS DA DISPLAYTAG
                $("div.DisplayTag.AjusteDisplay.rateio table tbody tr.odd").hover(
                function(){
                    $(this).css("background-color", "#EEEEEE");
                }, function(){
                    $(this).css("background-color", "#FFFFFF");
                });
                $("div.DisplayTag.AjusteDisplay.rateio table tbody tr.even").hover(
                function(){
                    $(this).css("background-color", "#EEEEEE");
                }, function(){
                    $(this).css("background-color", "#F9F9F9");
                });
                
                $("div.DisplayTag.AjusteDisplay.rateio.dadosVenda table tbody tr").hover(
                function(){
                    $(this).css("cursor", "pointer");
                    $(this).click(function(){
                        $("#idCobranca").val($("input[name=idCob]").val());
                        pesquisaSolicitacaoForm.action = "exibir.do?idVenda=" + idVenda + "&#aba2";
                        pesquisaSolicitacaoForm.submit();
                    });               
                }, function(){});          
            });   
        </script>
    </head>
    <body>
        <div class="barras norte" >         
            <awp:topoTela />
        </div>

        <awp:menu />

        <jsp:useBean id="VendaVH" class="br.com.delphos.web.vendas.VendaViewHelper" scope="page"/>
        <jsp:setProperty name="VendaVH" property="idVenda" value="${param.idVenda}" />
        <jsp:setProperty name="VendaVH" property="idCobranca" value="${vaf.idCobranca}" />
        <jsp:setProperty name="VendaVH" property="idTentativa" value="${param.idVenda}" />
        
        <div class="barras centro" >
            <div class="BoxModal" style="margin: 0 auto;">
                <h2>
                    <a href="vendas.jsp"><bean:message key="label.breadcrumb.consultar.vendas"/>&nbsp;>&nbsp;</a>
                    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.resultado"/>&nbsp;>&nbsp;</a>
                    <bean:message key="label.breadcrumb.exibir.venda"/>
                </h2>
            </div>

            <div class="BoxModal">
                <form name="pesquisaSolicitacaoForm" id="pesquisaSolicitacao" action="#" method="post">
                    <div id="divHidden" style="display: none">
                        <div class="DisplayTag AjusteDisplay rateio">
                            <display:table name="${VendaVH.listarEventosPorTentativa()}" decorator="br.com.delphos.web.vendas.EventoDecorator" requestURI="">
                                <display:column property="dataEvento" titleKey="label.dataEvento" style="text-align:center; width:20%;" />
                                <display:column property="tipo" titleKey="label.tipo" style="text-align:left; width:50%;" />
                                <display:column property="complemento" titleKey="label.complemento" style="text-align:left; width:30%; word-break: break-word;" />
                            </display:table>
                        </div>
                    </div>
                    <div id="abas">
                        <ul>
                            <li><a href="#aba1"><bean:message key="label.dados.venda"/></a></li>
                            <li><a href="#aba2"><bean:message key="label.dados.cobranca"/></a></li>
                        </ul>

                        <div id="aba1">
                            <div>
                                <div class="tabelas" >

                                    <h3 class="exibirH3"><bean:message key="label.breadcrumb.identificacao.venda"/></h3>
                                    <hr/>
                                    <table class="tabelaReadWrite2" >
                                        <tbody>
                                            <%-- CÓDIGO VENDA / DATA VENDA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.certificado"/></label></td>
                                                <td>
                                                    <input type="text" name="certificado" id="certificado" class="dadosVenda70" value="${vaf.certificado}" readonly/>
                                                </td>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.identificador.venda"/></label></td>
                                                <td>
                                                    <input type="text" name="idVenda" id="idVenda" class="dadosVenda70" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- ID EMPRESA - DESC EMPRESA / ID SISTEMA - DESC SISTEMA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.empresa"/></label></td>
                                                <td>
                                                    <input type="text" name="cdEmpresa" id="cdEmpresa" class="dadosVendaMod7" value="${vaf.codEmpresa}" readonly/> - 
                                                    <input type="text" name="descEmpresa" id="descEmpresa" class="dadosVendaMod78" value="${vaf.empresa}" readonly/>
                                                </td>  
                                                <td class="label ui-widget-header"><label><bean:message key="label.sistema"/></label></td>
                                                <td>
                                                    <input type="text" name="cdSistema" id="cdSistema" class="dadosVendaMod20" value="${vaf.codSistema}" readonly/> - 
                                                    <input type="text" name="descSistema" id="descSistema" class="dadosVendaMod65" value="${vaf.sistema}" readonly/>
                                                </td> 
                                            </tr>
                                            <%-- ID PRODUTO - DESC PRODUTO e DATA VENDA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.produto"/></label></td>
                                                <td>
                                                    <input type="text" name="cdProduto" id="cdProduto" class="dadosVendaMod20" value="${vaf.codProduto}" readonly/> - 
                                                    <input type="text" name="descProduto" id="descProduto" class="dadosVendaMod65" value="${vaf.produto}" readonly/>
                                                </td>
                                                <td class="label ui-widget-header"><label><bean:message key="label.data.venda"/></label></td>
                                                <td>
                                                    <input type="text" name="dataVenda" id="dataVenda" class="dadosVenda70" value="${vaf.dataVenda}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- STATUS VENDA e DATA FIM VIGÊNCIA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.status.venda"/></label></td>
                                                <td>
                                                    <input type="text" name="statusVenda" id="statusVenda" class="dadosVenda70" value="${vaf.statusVenda}" readonly/>
                                                </td>
                                                <td class="label ui-widget-header"><label><bean:message key="label.data.fim.vigencia"/></label></td>
                                                <td>
                                                    <input type="text" name="dataFimVigencia" id="dataFimVigencia" class="dadosVenda70" value="${vaf.dataFimVig}" readonly/>
                                                </td> 
                                            </tr>
                                        </tbody>  
                                    </table> 
                                    <br>
                                    <h3 class="exibirH3"><bean:message key="label.breadcrumb.dados.cliente"/></h3>
                                    <hr/>
                                    <table class="tabelaReadWrite2" >
                                        <tbody>
                                            <%-- NOME ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.nome"/></label></td>
                                                <td colspan="3">
                                                    <input type="text" name="nome" id="nome" class="dadosVenda88" value="${vaf.nome}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- CPF / EMAIL ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.cpf"/></label></td>
                                                <td>
                                                    <input type="text" name="cpf" id="cpf" class="dadosVenda93" value="${vaf.cpf}" readonly/>
                                                </td>                             
                                                <td class="label ui-widget-header"><label><bean:message key="label.email"/></label></td>
                                                <td>
                                                    <input type="text" name="email" id="email" class="dadosVenda93" value="${vaf.email}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- TELEFONE / CELULAR ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.telefone"/></label></td>
                                                <td>
                                                    <span style="font-size: 16px;">(</span>
                                                    <input type="text" name="dddTel" id="dddTel" class="dadosVenda5" value="${vaf.dddTel}" readonly/>
                                                    <span style="font-size: 16px;">)</span>
                                                    <input type="text" name="telefone" id="telefone" class="dadosVenda30" value="${vaf.tel}" readonly/>
                                                </td>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.celular"/></label></td>
                                                <td>
                                                    <span style="font-size: 16px;">(</span>
                                                    <input type="text" name="dddCel" id="dddCel" class="dadosVenda5" value="${vaf.dddCel}" readonly/>
													<span style="font-size: 16px;">)</span>
                                                    <input type="text" name="celular" id="celular" class="dadosVenda30" value="${vaf.cel}" readonly/>
                                                </td>
                                            </tr>
                                        </tbody>  
                                    </table> 
                                    <br>
                                    <h3 class="exibirH3"><bean:message key="label.breadcrumb.dados.pagamento"/></h3>
                                    <hr/>
                                    <table class="tabelaReadWrite2" >
                                        <tbody>
                                            <%-- VALOR COBRANÇA / QUANTIDADE DE PARCELAS ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.valor.cobranca"/></label></td>
                                                <td>
                                                    <input type="text" name="valorCobranca" id="valorCobranca" class="dadosVenda93" value="${vaf.valorCob}" readonly/>
                                                </td>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.quantidade.parcelas"/></label></td>
                                                <td>
                                                    <input type="text" name="qtdParcelas" id="qtdParcelas" class="dadosVenda93" value="${vaf.qtdParcelas}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- MEIO DE COBRANÇA / TIPO DE COBRANÇA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.meio.cobranca"/></label></td>
                                                <td>
                                                    <input type="text" name="meioCobranca" id="meioCobranca" class="dadosVenda93" value="${vaf.meioCobranca}" readonly/>
                                                </td>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.tipo.cobranca"/></label></td>
                                                <td>
                                                    <input type="text" name="tipoCobranca" id="tipoCobranca" class="dadosVenda93" value="${vaf.tipoCobranca}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- BANDEIRA DO CARTÃO / VENCIMENTO DO CARTÃO ================================================================== --%>
                                            <tr>
                                                <td class="label ui-widget-header"><label><bean:message key="label.bandeira.ultimos.digitos"/></label></td>
                                                <td width="34.1%">
                                                    <input type="text" name="bandeiraAtual" id="bandeiraAtual" class="dadosVendaMod65" value="${vaf.bandeiraCartao}" readonly/> / 
                                                    <input type="text" name="ultimosDigVenda" id="ultimosDigVenda" class="dadosVendaMod20" value="${vaf.ultimosDigVenda}" readonly/>
                                                </td>
                                                <td class="label ui-widget-header"><label><bean:message key="label.vencimento.cartao"/></label></td>
                                                <td>
                                                    <input type="text" name="vencimentoCartao" id="vencimentoCartao" class="dadosVenda30" value="${vaf.vencCartao}" readonly/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <br class="cancelamento">
                                    <h3 class="exibirH3 cancelamento"><bean:message key="label.breadcrumb.info.cancelamento"/></h3>
                                    <hr class="cancelamento"/>
                                    <table class="tabelaReadWrite2 cancelamento" >
                                        <tbody>
                                            <%-- DATA CANCELAMENTO / MOTIVO CANCELAMENTO ================================================================== --%>
                                            <tr>
                                                <td class="label ui-widget-header"><label><bean:message key="label.data.cancelamento"/></label></td>
                                                <td>
                                                    <input type="text" name="dataCancelamento" id="dataCancelamento" class="dadosVenda30" value="${vaf.dataCancelamento}" readonly/>
                                                </td>
                                                <td class="label ui-widget-header"><label><bean:message key="label.motivo.cancelamento"/></label></td>
                                                <td>
                                                    <input type="text" name="motivoCancelamento" id="motivoCancelamento" class="dadosVenda93" value="${vaf.motCancelamento}" readonly/>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <hr style="margin: 2% 0%">
                                    <h3 style="color: #09445D; margin-bottom: 5px; margin-top: -10px;"><bean:message key="label.breadcrumb.cobrancas"/></h3>
                                    <input type="hidden" id="idCobranca" name="idCobranca" value="${vaf.idCobranca}"/>
                                    <div class="DisplayTag AjusteDisplay rateio dadosVenda">
                                        <%-- PARA UTILIZAR A DISPLAY ORIGINAL UTILIZE A CLASSE = "TabelaDisplayTag" NA DISPLAYTAG --%>
                                        <display:table name="${VendaVH.listarCobrancasPorVenda()}" id="cobrancasList" pagesize="5" export="false" excludedParams="*" decorator="br.com.delphos.web.vendas.CobrancaDecorator" requestURI="">
                                            <display:column property="numeroParcela" titleKey="label.numero.parcela" style="text-align:right; width:25%;" /> 
                                            <display:column property="dataCobranca" titleKey="label.data.cobranca" style="text-align:center; width:20%;" />
                                            <display:column property="valor" titleKey="label.valor" style="text-align:right; width:25%;" />
                                            <display:column property="status" titleKey="label.status" style="text-align:left; width:30%;" />
                                            <display:column title="" class="hidden" headerClass="hidden">
						                        <a href="#"><input type="text" name="idCob" value="${cobrancasList.id}"></a>
						                    </display:column>
                                        </display:table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="aba2">
                            <div>
                                <div class="tabelas" >
                                    <table class="tabelaReadWrite2" >
                                        <tbody>
                                            <%-- COBRANÇA =========================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.cobranca"/></label></td>
                                                <td colspan="3">
                                                    <div id="divTipoSolicitacao" class="formataCampoSelectForm divSelectDetalhes" >
                                                        <select name="cobranca" id="cobranca" class="selectConsulta" onchange="Javascript: selecionarCobranca(this.value);">
                                                            <option value="0"> - <bean:message key="msg.selecione"/> - </option>
                                                        </select>
                                                    </div>
                                                </td>                            
                                            </tr>
                                            <%-- CÓDIGO COBRANÇA ================================================================== --%>
                                            <tr>                           
                                                <td class="label ui-widget-header"><label><bean:message key="label.identificador.cobranca"/></label></td>
                                                <td>
                                                    <input type="text" name="idCobranca" id="idCobranca" class="dadosInfo" value="${vaf.idCobranca}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- DATA COBRANÇA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.data.cobranca"/></label></td>
                                                <td>
                                                    <input type="text" name="dataCobranca" id="dataCobranca" class="dadosInfo" value="${vaf.dataCobranca}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- VALOR ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.valor"/></label></td>
                                                <td>
                                                    <input type="text" name="valor" id="valor" class="dadosInfo" value="${vaf.valor}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- MEIO DE PAGAMENTO ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.meio.pagamento"/></label></td>
                                                <td>
                                                    <input type="text" name="meioPagamento" id="meioPagamento" class="dadosInfo" value="${vaf.meioPagamento}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- BANDEIRA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.bandeira.ultimos.digitos"/></label></td>
                                                <td>
                                                    <input type="text" name="bandeira" id="bandeira" class="dadosVendaMod20" value="${vaf.bandeira}" readonly/> / 
                                                    <input type="text" name="ultimosDigCobranca" id="ultimosDigCobranca" style="width: 6.7%;" value="${vaf.ultimosDigCobranca}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- COD. AUTORIZAÇÃO ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.codigo.autorizacao"/></label></td>
                                                <td>
                                                    <input type="text" name="cdAutorizacao" id="cdAutorizacao" class="dadosInfo" value="${vaf.cdAutorizacao}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- NÚMERO COMPROVANTE DE VENDA ================================================================== --%>
                                            <tr>                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.num.comprovante.venda"/></label></td>
                                                <td>
                                                    <input type="text" name="numComprovante" id="numComprovante" class="dadosInfo" value="${vaf.numComprovante}" readonly/>
                                                </td>                            
                                            </tr>
                                            <%-- NÚMERO MÁXIMO DE TENTATIVAS (NÃO EXIBE SE FOR SINGLE PREMIUM) ================================================================== --%>
                                            <tr class="numMaxTentativas">                            
                                                <td class="label ui-widget-header"><label><bean:message key="label.num.max.tentativas"/></label></td>
                                                <td>
                                                    <input type="text" name="numMaxTentativas" id="numMaxTentativas" class="dadosInfo" value="${vaf.numMaxTentativas}" readonly/>
                                                </td>                            
                                            </tr>
                                        </tbody>  
                                    </table> 
                                    <hr style="margin: 2% 0%"/>
                                    <div id="abas2">
                                        <ul>
                                            <li><a href="#aba3"><bean:message key="label.tentativas"/></a></li>
                                            <li><a href="#aba4"><bean:message key="label.conciliação"/></a></li>
                                        </ul>

                                        <div id="aba3">
                                            <div>
                                                <div class="DisplayTag AjusteDisplay rateio">
                                                    <%-- PARA UTILIZAR A DISPLAY ORIGINAL UTILIZE A CLASSE = "TabelaDisplayTag" NA DISPLAYTAG --%>
                                                    <display:table name="${VendaVH.listarTentativasPorCobranca()}" decorator="br.com.delphos.web.vendas.TentativaDecorator" requestURI="">
                                                        <display:column property="numeroTentativa" titleKey="label.numero" style="text-align:right; width:10%;" class="clickValido"/>
                                                        <display:column property="dataTentativa" titleKey="label.data.tentativa" style="text-align:center; width:20%;" class="clickValido"/>
                                                        <display:column property="codRetorno" titleKey="label.codigo.retorno" style="text-align:left; width:20%;" class="clickValido"/>
                                                        <display:column property="mensagem"    titleKey="label.mensagem"    style="text-align:left; width:44%; word-break: break-all;" />
                                                        <display:column property="acaoTentativa"    titleKey="label.acao"    style="text-align:center; width:6%" />
                                                    </display:table>
                                                </div>
                                            </div>
                                        </div>
                                        <div id="aba4">
                                            <div>
                                                <div class="DisplayTag AjusteDisplay rateio">
                                                    <%-- PARA UTILIZAR A DISPLAY ORIGINAL UTILIZE A CLASSE = "TabelaDisplayTag" NA DISPLAYTAG --%>
                                                    <display:table name="${VendaVH.listarConciliacoesPorCobranca()}" decorator="br.com.delphos.web.vendas.VendaDecorator" requestURI="">
                                                        <display:column property="dataConciliacao"   titleKey="label.dataConciliacao"	style="text-align:center; width:10%;" class="clickValido"/>
                                                        <display:column property="statusConciliacao" titleKey="label.status"         	style="text-align:left; width:16%;" class="clickValido"/>
                                                        <display:column property="evento"            titleKey="label.evento"         	style="text-align:left; width:42%;"   class="clickValido"/>
                                                        <display:column property="parcela"           titleKey="label.parcela"        	style="text-align:right; width:8%" />
                                                        <display:column property="totalParcela"      titleKey="label.totalParcelas" 	style="text-align:right; width:10%" />
                                                        <display:column property="valorTarifa"       titleKey="label.valorTarifa"   	style="text-align:right; width:11%" />
<%--                                                         <display:column property="nsu"               titleKey="label.nsu"            	style="text-align:right; width:12%" /> --%>
                                                    </display:table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="barras" >  
            <table class="navegacao" >
                <tr>
                    <td class="botoesRetornar" >
                        <a href="javascript:voltar();">
                            <span class="Icone22x22 IconeNavegacaoRetornar" ></span>
                            <bean:message key="label.botao.retornar"/><br> &nbsp;
                        </a>
                    </td>
                    <td class="botoesCentro" >&nbsp;</td>
                    <td class="botoesAvancar" >&nbsp;</td>
                </tr>
            </table>
        </div>
        <div class="barras sul" >
            <p><bean:message key="label.rodape"/></p>
        </div>
        
        <script>            
        	function voltar(){
                window.location = "../venda/resultado.do";
            }
            
            function exibirEventos(tentativa){

            	popularEventos(tentativa);
                
                $("#divHidden").dialog({
                    closeOnEscape: false,
                    open: function(event, ui) {
                        $(".ui-dialog-titlebar-close").hide();
                    },
                    title: "Eventos",
                    resizable: false,
                    height:300,
                    modal: true,
                    width:800,
                    draggable: false,
                    buttons: {
                        Fechar: function() {
                            $( this ).dialog( "close" );
                        }
                    }
                });
            }

    		function popularEventos(idTentativa) {
    			$.ajax({
    		        url:"../dbs/listas",
    		        data:"op=5&cod="+idTentativa,
    		        success:function(eventos){
    		        	$("div[id=divHidden] > div > table > tbody").html(eventos);
    		        }
    		    });
    		}

    		function selecionarCobranca(id){
        		
    			var idVenda = getUrlVars()["idVenda"];
        		
    			$("#idCobranca").val(id);
                pesquisaSolicitacaoForm.action = "exibir.do?idVenda=" + idVenda + "&#aba2";
                pesquisaSolicitacaoForm.submit();
            }  
        </script>
    </body>
</html>