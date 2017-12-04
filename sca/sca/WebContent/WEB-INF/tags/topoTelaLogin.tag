<%@tag description="topo das telas da aplicacao" pageEncoding="windows-1252"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="awp" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<table class="topo" >
    <tr>
        <td rowspan="2" >                       
            <img src="../img/logos/logo_billing.png" alt="Assurant" />
            <!--img src="../img/logos/logo_assurant.png" alt="Assurant" />-->
        </td>
        <td class="botoes" > &nbsp; </td>
    </tr>
    <tr>              
        <td class="versao"><bean:message key="label.versao"/></td>
    </tr>
</table>