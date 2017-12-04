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

<logic:messagesPresent property="idProduto">
	<script>
		$(function() {
			$("select[name='idProduto']").addClass("formataCampoSelectErroForm");
            $("select[name='idProduto']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoSistema">
	<script>
		$(function() {
			$("input[name=codigoSistema]").addClass("CampoVazio");
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

<logic:messagesPresent property="codigoAfiliacao">
	<script>
		$(function() {
			$("input[name=codigoAfiliacao]").addClass("CampoVazio");
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

<logic:messagesPresent property="periodoDeLogs">
	<script>
		$(function() {
			$("input[name=periodoDe]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="periodoAteLogs">
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
			$("select[name='statusItemLista']").addClass("formataCampoSelectErroForm");
            $("select[name='statusItemLista']").parent().addClass("CampoVazio");
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

<!-- Pesquisa -->

<logic:messagesPresent property="codigoEmpresaContratoCobranca">
	<script>
		$(function() {
			$("input[name=empresa]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoMeioPagamentoContratoCobranca">
	<script>
		$(function() {
			$("select[name='codigoMeioPagamentoContratoCobranca']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoMeioPagamentoContratoCobranca']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoProvedorContratoCobranca">
	<script>
		$(function() {
			$("input[name=provedorContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!--  Edição -->			
<logic:messagesPresent property="codigoEmpresaContratoCobranca">
	<script>
		$(function() {
			$("select[name='codigoEmpresaContratoCobranca']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoEmpresaContratoCobranca']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoMeioPagamentoContratoCobranca">
	<script>
		$(function() {
			$("select[name='codigoMeioPagamentoContratoCobranca']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoMeioPagamentoContratoCobranca']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codigoProvedorContratoCobranca">
	<script>
		$(function() {
			$("select[name='codigoProvedorContratoCobranca']").addClass("formataCampoSelectErroForm");
            $("select[name='codigoProvedorContratoCobranca']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<logic:messagesPresent property="codEmpresaProvMeioPagamentoContratoCobranca">
	<script>
		$(function() {
			$("input[name=codEmpresaProvMeioPagamentoContratoCobranca]").addClass("CampoVazio");
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
<logic:messagesPresent property="prazoPagamentoDiasContratoCobranca">
	<script>
		$(function() {
			$("input[name=prazoPagamentoDiasContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="tipoTransacaoProvedorContratoCobranca">
	<script>
		$(function() {
			$("input[name=tipoTransacaoProvedorContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="inicioVigenciaContratoCobranca">
	<script>
		$(function() {
			$("input[name=inicioVigenciaContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="fimVigenciaContratoCobranca">
	<script>
		$(function() {
			$("input[name=fimVigenciaContratoCobranca]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>

<!-- Adquirente x Bandeira -->
			
<logic:messagesPresent property="idAdquirente">
	<script>
		$(function() {
			$("select[name='idAdquirente']").addClass("formataCampoSelectErroForm");
            $("select[name='idAdquirente']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="idBandeira">
	<script>
		$(function() {
			$("select[name='idBandeira']").addClass("formataCampoSelectErroForm");
            $("select[name='idBandeira']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="padraoAssociarContrato">
	<script>
		$(function() {
			$("select[name='padraoAssociarContrato']").addClass("formataCampoSelectErroForm");
            $("select[name='padraoAssociarContrato']").parent().addClass("CampoVazio");
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
			$("select[name='tipoIntervaloRetentativas']").addClass("formataCampoSelectErroForm");
            $("select[name='tipoIntervaloRetentativas']").parent().addClass("CampoVazio");
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
<logic:messagesPresent property="relatorio">
	<script>
		$(function() {
			  $("select[name='relatorio']").addClass("formataCampoSelectErroForm");
              $("select[name='relatorio']").parent().addClass("CampoVazio");
              $(".configDiasAtraso").hide();
              $(".configEmpresas").hide();
              $(".configSistemas").hide();
              $(".configProdutos").hide();
              $(".configPeriodo").hide();
              $(".configStatus").hide();
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="periodoDe">
	<script>
		$(function() {
		 	 $("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
             $("select[name='relatorio']").parent().removeClass("CampoVazio");
             $("#periodoDe").addClass("CampoVazio");
             $("").addClass("CampoVazio");
             $(".configDiasAtraso").hide();
             $("select[name='relatorio']").val("${relatorio_selecionado}");
             configurar();
             $("#periodoDe").addClass("CampoVazio");
  		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="periodoAte">
	<script>
		$(function() {
		 	 $("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
             $("select[name='relatorio']").parent().removeClass("CampoVazio");
             $("#periodoAte").addClass("CampoVazio");
             $("").addClass("CampoVazio");
             $(".configDiasAtraso").hide(); 
             $("select[name='relatorio']").val("${relatorio_selecionado}");
             configurar();
             $("#periodoAte").addClass("CampoVazio");
   			});
	</script>
</logic:messagesPresent>
	<logic:messagesPresent property="diasAtraso">
	<script>
		$(function() {
			 $(".configPeriodo").hide();
	         $(".configStatus").hide();
	         $("select[name='relatorio']").val("${relatorio_selecionado}");

	            
			$("select[name='relatorio']").removeClass("formataCampoSelectErroForm");
		    $("select[name='relatorio']").parent().removeClass("CampoVazio");
		    $("#diasAtraso").addClass("CampoVazio");
		    $("").addClass("CampoVazio");


		    
		});
	</script>
</logic:messagesPresent>

<!-- Movimento Conciliação -->
<logic:messagesPresent property="nomeAdquirente">
	<script>
		$(function() {
			$("select[name='nomeAdquirente']").addClass("formataCampoSelectErroForm");
            $("select[name='nomeAdquirente']").parent().addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="dataMovimentoArquivo">
	<script>
		$(function() {
			$("#dataMovimentoArquivo").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>
<logic:messagesPresent property="valorDeposito">
	<script>
		$(function() {
			$("input[name=valorDeposito]").addClass("CampoVazio");
		});
	</script>
</logic:messagesPresent>