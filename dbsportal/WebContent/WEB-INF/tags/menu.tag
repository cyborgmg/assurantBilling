<%@tag description="topo das telas" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@attribute name="entrada"%>
<%@attribute name="selecionaMenu"%>
<%@attribute name="selecionaSubMenu"%>

<script>
    $(function(){
        
        // Menus
        $(".menuReq").hide();
        $(".menuConf").hide();
        $(".subMenuCad").hide();
            
        $("#requisicao").hover(
	        function(){
	            $(".menuReq").show();
	        },
	        function(){
	            $(".menuReq").hide();
	        }
        );
            
        $("#configuracao").hover(
	        function(){
	            $(".menuConf").show();
	        },
	        function(){
	            $(".menuConf").hide();
	        }
        );                
    });     
</script>

<div class="barrasMenu">

    <div class="menu" id="menu">
        <span>
            <a href="../principal/index.jsp">
                <ul id="principal" class="menuSetup"><bean:message key="label.menu.principal"/></ul>
            </a>
        </span>

        <span id="requisicao">
            <ul id="consultas" class="menuSetup"><bean:message key="label.menu.consultas"/> <img id="downArrow" src="../img/menu/setaBaixo_8x4.png" />
                <li class="menuReq">
                    <ul>
                    	<a href="../venda/prepararVenda.do" id="vendas">
                            <li id="vendas">
                                <bean:message key="label.menu.consultas.vendas"/>
                            </li>
                        </a>
                    	<a href="../conciliacao/prepararConciliacao.do?opcao=1" id="conciliacao">
                            <li id="conciliacao">
                                <bean:message key="label.menu.consultas.conciliacao"/>
                            </li>
                        </a>
                    </ul>
                </li>
            </ul>
        </span>

        <span id="relatorios">
            <a href="../relatorios/gerarRelatorioVendas.jsp">
                <ul id="relatorios" class="menuSetup"><bean:message key="label.menu.relatorios"/></ul>
            </a>
        </span>
        <!--
		<span id="configuracao">
            <ul id="configuracoes" class="menuSetup"><bean:message key="label.menu.configuracoes"/> <img id="downArrow" src="../img/menu/setaBaixo_8x4.png" />
                <li class="menuConf">
                    <ul>
                    	<a href="../configuracao/prepararEmpresa.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.empresa"/>
                            </li>
                        </a>
                    	<a href="../configuracao/prepararProduto.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.produto"/>
                            </li>
                        </a>
                    	<a href="../configuracao/prepararSistema.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.sistema"/>
                            </li>
                        </a>
                    	<a href="../configuracao/resultadoMeioPagamento.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.meio.pagamento"/>
                            </li>
                        </a>
                    	<a href="../configuracao/resultadoProvedorMeioPagamento.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.provedor.meio.pagamento"/>
                            </li>
                        </a>
                    	<a href="../configuracao/resultadoAdquirente.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.adquirente"/>
                            </li>
                        </a>
                    	<a href="../configuracao/resultadoBandeira.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.bandeira"/>
                            </li>
                        </a>
                    	<a href="../configuracao/prepararContratoCobranca.do?opcao=1">
                            <li>
                                <bean:message key="label.menu.configuracoes.contrato.cobrança"/>
                            </li>
                        </a>
                    	<a href="../configuracao/resultadoListaValor.do?opcao=ignorarValidacao">
                            <li>
                                <bean:message key="label.menu.configuracoes.lista.valores"/>
                            </li>
                        </a>
                    </ul>
                </li>
            </ul>
        </span>
        <span id="logs">
            <a href="../logs/prepararConsultaLogs.do">
                <ul id="logs" class="menuSetup"><bean:message key="label.menu.logs"/></ul>
            </a>
        </span>
         -->
    </div>
</div>