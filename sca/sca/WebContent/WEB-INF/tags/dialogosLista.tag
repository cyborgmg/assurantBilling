<%@tag description="controle e apresentacao dos quadros de dialogos"
	pageEncoding="windows-1252"%>

<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<logic:messagesPresent>
	<script type="text/javascript">
		$(function() {

			mostrarMensagem("<html:errors />", "dialogoErro", 400);
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent message="true" property="dialogoUsuario">
	<script type="text/javascript">
		var msgs = "";
		<html:messages id="msg" message="true">
		msgs += "<p><bean:write name="msg"/></p>";
		</html:messages>
		$(function() {
			mostrarMensagem(msgs, "dialogoUsuario", 400);
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent message="true" property="dialogoAlerta">
	<script type="text/javascript">
		var msgs = "";
		<html:messages id="msg" message="true">
		msgs += "<p><bean:write name="msg"/></p>";
		</html:messages>
		$(function() {
			mostrarMensagem(msgs, "dialogoAlerta", 400);
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent message="true" property="dialogoSucesso">
	<script type="text/javascript">
		var msgs = "";
		<html:messages id="msg" message="true">
		msgs += "<bean:write name="msg"/>";
		</html:messages>
		$(function() {
			mostrarMensagem(msgs, "dialogoSucesso", 400);
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="produto">
	<script>
		$(function() {
			$("select[name='produto']").addClass("formataCampoSelectErroForm");
            $("select[name='produto']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="sistema">
	<script>
		$(function() {
			$("select[name='sistema']").addClass("formataCampoSelectErroForm");
            $("select[name='sistema']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="cpf">
	<script>
		$(function() {
			$("input[name=cpf]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="certificado">
	<script>
		$(function() {
			$("input[name=certificado]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Provedor Meio de Pagamento -->
<logic:messagesPresent property="codigoProvedor">
	<script>
		$(function() {
			$("input[name=codigoProvedor]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoProvedor">
	<script>
		$(function() {
			$("input[name=descricaoProvedor]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Meio de Pagamento -->

<logic:messagesPresent property="codigoMeioPagamento">
	<script>
		$(function() {
			$("input[name=codigoMeioPagamento]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoMeioPagamento">
	<script>
		$(function() {
			$("input[name=descricaoMeioPagamento]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="limiteDiasRetentativa">
	<script>
		$(function() {
			$("input[name=limiteDiasRetentativa]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Sistemas -->

<logic:messagesPresent property="codigoProduto">
	<script>
		$(function() {
			$("select[name='codigoProduto']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoProduto']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="siglaSistema">
	<script>
		$(function() {
			$("input[name=siglaSistema]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoSistema">
	<script>
		$(function() {
			$("input[name=descricaoSistema]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="sistemaEnvioInformacoesSistema">
	<script>
		$(function() {
			$("select[name='sistemaEnvioInformacoesSistema']").addClass("formataCampoSelectErroForm");
            $("select[name='sistemaEnvioInformacoesSistema']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Produtos -->

<logic:messagesPresent property="codigoEmpresa">
	<script>
		$(function() {
			$("select[name='codigoEmpresa']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoEmpresa']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoProduto">
	<script>
		$(function() {
			$("input[name=codigoProduto]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoProduto">
	<script>
		$(function() {
			$("input[name=descricaoProduto]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoEmpresaVendaProduto">
	<script>
		$(function() {
			$("select[name='codigoEmpresaVendaProduto']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoEmpresaVendaProduto']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoSistemaVendaProduto">
	<script>
		$(function() {
			$("select[name='codigoSistemaVendaProduto']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoSistemaVendaProduto']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="tipoCobrancaProduto">
	<script>
		$(function() {
			$("select[name='tipoCobrancaProduto']").addClass("formataCampoSelectErroForm");
            $("select[name='tipoCobrancaProduto']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="numeroMaximoParcelasProduto">
	<script>
		$(function() {
			$("input[name=numeroMaximoParcelasProduto]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoEmpresa">
	<script>
		$(function() {
			$("input[name=codigoEmpresa]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Empresa -->

<logic:messagesPresent property="descricaoEmpresa">
	<script>
		$(function() {
			$("input[name=descricaoEmpresa]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Adquirente -->

<logic:messagesPresent property="codigoConvenioAdquirente">
	<script>
		$(function() {
			$("input[name=codigoConvenioAdquirente]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="nomeAdquirente">
	<script>
		$(function() {
			$("input[name=nomeAdquirente]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Logs -->
			
<logic:messagesPresent property="tipoOperacao">
	<script>
		$(function() {
			$("input[name=tipoOperacao]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="tipoObjetoOperacao">
	<script>
		$(function() {
			$("input[name=tipoObjetoOperacao]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="identificadorObjetoOperacao">
	<script>
		$(function() {
			$("input[name=identificadorObjetoOperacao]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="usuario">
	<script>
		$(function() {
			$("input[name=usuario]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="periodoDe">
	<script>
		$(function() {
			$("input[name=periodoDe]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="periodoAte">
	<script>
		$(function() {
			$("input[name=periodoAte]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Lista de Valor -->
			
<logic:messagesPresent property="codigoListaValor">
	<script>
		$(function() {
			$("input[name=codigoListaValor]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoListaValor">
	<script>
		$(function() {
			$("input[name=descricaoListaValor]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="tipoDeUsoListaValor">
	<script>
		$(function() {
			$("select[name='tipoDeUsoListaValor']").addClass("formataCampoSelectErroForm");
            $("select[name='tipoDeUsoListaValor']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="statusListaValor">
	<script>
		$(function() {
			$("select[name='statusListaValor']").addClass("formataCampoSelectErroForm");
            $("select[name='statusListaValor']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Item da Lista de Valor -->
			
<logic:messagesPresent property="codigoItemLista">
	<script>
		$(function() {
			$("input[name=codigoItemLista]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoItemLista">
	<script>
		$(function() {
			$("input[name=descricaoItemLista]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="statusItemLista">
	<script>
		$(function() {
			$("input[name=statusItemLista]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Bandeira -->
			
<logic:messagesPresent property="codigoBandeira">
	<script>
		$(function() {
			$("input[name=codigoBandeira]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="nomeBandeira">
	<script>
		$(function() {
			$("input[name=nomeBandeira]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Contrato de cobrança -->
			
<logic:messagesPresent property="codigoEmpresaContratoCobranca">
	<script>
		$(function() {
			$("input[name=codigoEmpresaContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoProvedorContratoCobranca">
	<script>
		$(function() {
			$("input[name=codigoProvedorContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="descricaoContratoCobranca">
	<script>
		$(function() {
			$("input[name=descricaoContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Adquirente x Bandeira -->
			
<logic:messagesPresent property="idAdquirente">
	<script>
		$(function() {
			$("select[name=idAdquirente]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="idBandeira">
	<script>
		$(function() {
			$("select[name=idBandeira]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="padraoAssociarContrato">
	<script>
		$(function() {
			$("select[name=padraoAssociarContrato]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="codigoMetodoPagamento">
	<script>
		$(function() {
			$("input[name=codigoMetodoPagamento]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Resposta de Autorização -->
			
<logic:messagesPresent property="codigoRespostaAutorizacao">
	<script>
		$(function() {
			$("input[name=codigoRespostaAutorizacao]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="descricaoRespostaAutorizacaoProvedor">
	<script>
		$(function() {
			$("input[name=descricaoRespostaAutorizacaoProvedor]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="descricaoRespostaAutorizacaoConsulta">
	<script>
		$(function() {
			$("input[name=descricaoRespostaAutorizacaoConsulta]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="quantidadeRetentativas">
	<script>
		$(function() {
			$("input[name=quantidadeRetentativas]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="tipoIntervaloRetentativas">
	<script>
		$(function() {
			$("select[name=tipoIntervaloRetentativas]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="intervaloEntreRetentativas">
	<script>
		$(function() {
			$("input[name=intervaloEntreRetentativas]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="descricaoInterface">
	<script>
		$(function() {
			$("input[name=descricaoInterface]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="grupoInterface">
	<script>
		$(function() {
			$("input[name=grupoInterface]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<!-- SCA: Usuário -->
<logic:messagesPresent property="codigoUsuario">
	<script>
		$(function() {
			$("input[name=codigoUsuario]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="descricaoUsuario">
	<script>
		$(function() {
			$("input[name=descricaoUsuario]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="usuario">
	<script>
		$(function() {
			$("input[name=usuario]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="email">
	<script>
		$(function() {
			$("input[name=email]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- SCA: Grupo Usuário -->
<logic:messagesPresent property="tipoGrupo">
	<script>
		$(function() {
			$("input[name=tipoGrupo]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="descricaoGrupoUsuario">
	<script>
		$(function() {
			$("input[name=descricaoGrupoUsuario]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- SCA: Grupo de Interface -->
<logic:messagesPresent property="descricaoGrupoInterface">
	<script>
		$(function() {
			$("input[name=descricaoGrupoInterface]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!--  SCA: Sistema -->

<logic:messagesPresent property="idEmpresa">
	<script>
		$(function() {
			$("select[name='idEmpresa']").addClass("formataCampoSelectErroForm");
	        $("select[name='idEmpresa']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
