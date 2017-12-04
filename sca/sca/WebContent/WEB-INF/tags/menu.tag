<%@tag description="topo das telas" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%@attribute name="entrada"%>
<%@attribute name="selecionaMenu"%>
<%@attribute name="selecionaSubMenu"%>

<div class="barrasMenu">

    <div class="menu" id="menu">
        <span>
            <a href="../principal/index.jsp">
                <ul id="principal" class="menuSetup"><bean:message key="label.menu.principal"/></ul>
            </a>
        </span>

        <span id="empresas">
            <a href="../configuracao/prepararEmpresa.do?opcao=1">
                <ul id="empresas" class="menuSetup"><bean:message key="label.menu.empresas"/></ul>
            </a>
        </span>

        <span id="sistemas">
            <a href="../configuracao/prepararSistema.do?opcao=1">
                <ul id="sistemas" class="menuSetup"><bean:message key="label.menu.sistemas"/></ul>
            </a>
        </span>

        <span id="usuarios">
            <a href="../configuracao/prepararUsuario.do">
                <ul id="usuarios" class="menuSetup"><bean:message key="label.menu.usuarios"/></ul>
            </a>
        </span>

        <span id="interfaces">
            <a href="../configuracao/prepararInterface.do?opcao=1">
                <ul id="interfaces" class="menuSetup"><bean:message key="label.menu.interfaces"/></ul>
            </a>
        </span>

        <span id="gruposUsuarios">
            <a href="../configuracao/prepararGrupoUsuario.do?opcao=1">
                <ul id="gruposUsuarios" class="menuSetup"><bean:message key="label.menu.grupos.usuarios"/></ul>
            </a>
        </span>

        <span id="gruposInterfaces">
            <a href="../configuracao/prepararGrupoInterface.do?opcao=1">
                <ul id="gruposInterfaces" class="menuSetup"><bean:message key="label.menu.grupos.interfaces"/></ul>
            </a>
        </span>
    </div>
</div>