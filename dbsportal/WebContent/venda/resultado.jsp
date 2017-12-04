<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <awp:head/>

        <script type="text/javascript" >            
            $(function(){                
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
            });
            
            function voltar() {
                window.location = "../venda/vendas.jsp";
            }
        </script>
    </head>
    <body>
        <div class="barras norte" >         
            <awp:topoTela />
        </div>

        <awp:menu />
        <jsp:useBean id="VendaVH" class="br.com.delphos.web.vendas.VendaViewHelper" scope="page"/>
        <jsp:setProperty name="VendaVH" property="codEmpresa" value="${codEmpresa}" />
        <jsp:setProperty name="VendaVH" property="idProduto" value="${idProduto}" />
        <jsp:setProperty name="VendaVH" property="idSistema" value="${idSistema}" />
        <jsp:setProperty name="VendaVH" property="cpf" value="${cpf}" />
        <jsp:setProperty name="VendaVH" property="certificado" value="${certificado}" />

        <div class="barras centro" >
            <div class="BoxModal" style="margin: 0 auto;">
                <h2>
                    <a href="javascript:voltar();"><bean:message key="label.breadcrumb.consultar.vendas"/>&nbsp;>&nbsp;</a>
                    <bean:message key="label.breadcrumb.resultado"/>
                </h2>
            </div>

            <div class="BoxModal">
                <form name="pesquisaSolicitacaoForm" id="pesquisaSolicitacao" action="#" method="post">
                
                    <div class="tabelas" > 
                        <div class="DisplayTag AjusteDisplay rateio">
                            <%-- PARA UTILIZAR A DISPLAY ORIGINAL UTILIZE A CLASSE = "TabelaDisplayTag" NA DISPLAYTAG --%>
                            <display:table name="${VendaVH.listarVendas()}" decorator="br.com.delphos.web.vendas.VendaDecorator" requestURI="">
                                <display:column property="empresa" titleKey="label.empresa" style="text-align:left; width:4%; word-break: break-word;" />
                                <display:column property="produto" titleKey="label.produto" style="text-align:left; width:4%; word-break: break-word;" />
                                <display:column property="sistema" titleKey="label.sistema" style="text-align:left; width:4%; word-break: break-word;" />
                                <display:column property="nome"    titleKey="label.nome"    style="text-align:left; width:28%; word-break: break-word;" />
                                <display:column property="codigoVendaOrigem" titleKey="label.certificado" style="text-align:center; width:14%; word-break: break-all;" />
                                <display:column property="cpf"     titleKey="label.cpf"    style="text-align:left; width:10%;" />
                                <display:column property="valorCobranca" titleKey="label.valor.cobranca" style="text-align:right; width:13%;" />
                                <display:column property="status"  titleKey="label.status" style="text-align:left; width:11%;" />
                                <display:column property="dataVendaOrigem" titleKey="label.data.venda" style="text-align:center; width:8%;" />
                                <display:column property="acao"    titleKey="label.acao"    style="text-align:center; width:4%" />
                            </display:table>
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
        	function detalharVenda(id) {
        		pesquisaSolicitacaoForm.method = "POST";
        		pesquisaSolicitacaoForm.action = "detalharVenda.do?idVenda=" + id;
        		pesquisaSolicitacaoForm.submit();
            }
        </script>
    </body>
</html>