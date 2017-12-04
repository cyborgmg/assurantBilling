package br.com.delphos.web.configuracao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.adquirentes.AdquirenteBandeiraDLO;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;

public class ConfiguracaoActionForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4736818910379175249L;

	//Flag para aferir se é uma nova busca
	private String novaBusca;
	
	//Flag para mostrar listas pesquisadas
	private String listaAtiva;
	
	// Flag pra aferir de qual CRUD vai validar
	private String opcao;
	
	// Meio de Pagamento
	private String idMeioPagamento;
	private String codigoMeioPagamento;
	private String descricaoMeioPagamento;
	private String limiteDiasRetentativa;
	
	// Provedor do meio de pagamento
	private String codigoProvedor;
	private String dataUltimaAlteracao;
	private String descricaoProvedor;	
	private String idProvedorMeioPagamento;
	private String provedorContratoCobranca;
	
	// Empresa
	private String descricaoEmpresa;
	private String produtosAssociados;
	
	// Adquirente
	private String idAdquirente;
	private String nomeAdquirente;
	private String codigoConvenioAdquirente;
	private String codigoAfiliacao;
	
	// Bandeira
	private String idBandeira;
	private String codigoBandeira;
	private String nomeBandeira;
	
	// Associacao Adquirente/Bandeira/Contrato Cobranca
	private String idAdquirenteBandeira;
	private String padraoAssociarContrato;
	
	// Contrato de Cobrança
	private String idContratoCobranca;
	private String codigoEmpresaContratoCobranca;
	private String codigoMeioPagamentoContratoCobranca;
	private String codigoProvedorContratoCobranca;	
	private String descricaoContratoCobranca;
	private String codEmpresaProvMeioPagamentoContratoCobranca;
	private String prazoPagamentoDiasContratoCobranca;
	private String tipoTransacaoProvedorContratoCobranca;	
	private String inicioVigenciaContratoCobranca;
	private String fimVigenciaContratoCobranca;
	private String codigoMetodoPagamento;
	
	// Resposta de Autorização
	private String idRespostaAutorizacao;
	private String codigoEmpresaRespostaAutorizacao;
	private String codigoMeioPagamentoRespostaAutorizacao;
	private String codigoProvedorRespostaAutorizacao;
	private String codigoContratoRespostaAutorizacao;
	private String codigoRespostaAutorizacao;
	private String descricaoRespostaAutorizacaoProvedor;
	private String descricaoRespostaAutorizacaoConsulta;
	private String quantidadeRetentativas;
	private String tipoIntervaloRetentativas;
	private String intervaloEntreRetentativas;
	
	// Lista de Valores
	private String idListaValor;
	private String codigoListaValor;
	private String descricaoListaValor;
	private String tipoDeUsoListaValor;
	private String statusListaValor;
	
	// Item Lista
	private String idItemLista;
	private String descricaoListaValorItemLista;
	private String idListaValorItemLista;
	private String codigoItemLista;
	private String descricaoItemLista;
	private String statusItemLista;
	
	// Produto
	private String idProduto;
	private String codigoProduto;
	private String codigoEmpresa;
	private String descricaoProduto;
	private String numeroMaximoParcelasProduto;
	private String tipoCobrancaProduto;
	private String contratosAssociados;
	private String produto;
	
	// Sistema
	private String idSistema;
	private String codigoSistema;
	private String descricaoSistema;
	private String sistemaEnvioInformacoesSistema;
	
	// Empresa
	private String idEmpresa;
	private String empresa;
	
	// AssociacaoAdquirente
	private String padrao;
	private String idAdquirenteSelecionado;
	private String idBandeiraSelecionado;
	private String idAdquirenteOld;
	private String idBandeiraOld;
	private String padraoAssociarContratoOld;
	private String codigoMetodoPagamentoOld;
	
	
	// Log
	private String tipoOperacao;
	private String tipoObjetoOperacao;
	private String identificadorObjetoOperacao;
	private String usuario;
	private String periodoDe;
	private String periodoAte;
	
	private AdquirenteBandeiraDLO dloAdquirenteBandeira = (AdquirenteBandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteBandeiraDLOBean");
	private ListaValorDLO dloListaValor = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
	
	public String getCodigoAfiliacao() {
		return codigoAfiliacao;
	}

	public void setCodigoAfiliacao(String codigoAfiliacao) {
		this.codigoAfiliacao = codigoAfiliacao;
	}

	
	public String getIdAdquirenteSelecionado() {
		return idAdquirenteSelecionado;
	}

	public void setIdAdquirenteSelecionado(String idAdquirenteSelecionado) {
		this.idAdquirenteSelecionado = idAdquirenteSelecionado;
	}

	public String getIdBandeiraSelecionado() {
		return idBandeiraSelecionado;
	}

	public void setIdBandeiraSelecionado(String idBandeiraSelecionado) {
		this.idBandeiraSelecionado = idBandeiraSelecionado;
	}

	public String getCodigoMeioPagamento() {
		return codigoMeioPagamento;
	}

	public void setCodigoMeioPagamento(String codigoMeioPagamento) {
		this.codigoMeioPagamento = codigoMeioPagamento;
	}

	public String getDescricaoMeioPagamento() {
		return descricaoMeioPagamento;
	}

	public void setDescricaoMeioPagamento(String descricaoMeioPagamento) {
		this.descricaoMeioPagamento = descricaoMeioPagamento;
	}

	public String getLimiteDiasRetentativa() {
		return limiteDiasRetentativa;
	}

	public void setLimiteDiasRetentativa(String limiteDiasRetentativa) {
		this.limiteDiasRetentativa = limiteDiasRetentativa;
	}
	
	public String getIdMeioPagamento() {
		return idMeioPagamento;
	}

	public void setIdMeioPagamento(String idMeioPagamento) {
		this.idMeioPagamento = idMeioPagamento;
	}

	public String getCodigoProvedor() {
		return codigoProvedor;
	}

	public void setCodigoProvedor(String codigoProvedor) {
		this.codigoProvedor = codigoProvedor;
	}

	public String getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(String dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricaoProvedor() {
		return descricaoProvedor;
	}

	public void setDescricaoProvedor(String descricaoProvedor) {
		this.descricaoProvedor = descricaoProvedor;
	}

	public String getIdAdquirente() {
		return idAdquirente;
	}

	public void setIdAdquirente(String idAdquirente) {
		this.idAdquirente = idAdquirente;
	}

	public String getNomeAdquirente() {
		return nomeAdquirente;
	}

	public void setNomeAdquirente(String nomeAdquirente) {
		this.nomeAdquirente = nomeAdquirente;
	}

	public String getCodigoConvenioAdquirente() {
		return codigoConvenioAdquirente;
	}

	public void setCodigoConvenioAdquirente(String codigoConvenioAdquirente) {
		this.codigoConvenioAdquirente = codigoConvenioAdquirente;
	}
	
	public String getIdBandeira() {
		return idBandeira;
	}

	public void setIdBandeira(String idBandeira) {
		this.idBandeira = idBandeira;
	}

	public String getCodigoBandeira() {
		return codigoBandeira;
	}

	public void setCodigoBandeira(String codigoBandeira) {
		this.codigoBandeira = codigoBandeira;
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public String getIdContratoCobranca() {
		return idContratoCobranca;
	}

	public void setIdContratoCobranca(String idContratoCobranca) {
		this.idContratoCobranca = idContratoCobranca;
	}

	public String getCodigoEmpresaContratoCobranca() {
		return codigoEmpresaContratoCobranca;
	}

	public void setCodigoEmpresaContratoCobranca(
			String codigoEmpresaContratoCobranca) {
		this.codigoEmpresaContratoCobranca = codigoEmpresaContratoCobranca;
	}

	public String getCodigoMeioPagamentoContratoCobranca() {
		return codigoMeioPagamentoContratoCobranca;
	}

	public void setCodigoMeioPagamentoContratoCobranca(
			String codigoMeioPagamentoContratoCobranca) {
		this.codigoMeioPagamentoContratoCobranca = codigoMeioPagamentoContratoCobranca;
	}

	public String getCodigoProvedorContratoCobranca() {
		return codigoProvedorContratoCobranca;
	}

	public void setCodigoProvedorContratoCobranca(
			String codigoProvedorContratoCobranca) {
		this.codigoProvedorContratoCobranca = codigoProvedorContratoCobranca;
	}

	public String getDescricaoContratoCobranca() {
		return descricaoContratoCobranca;
	}

	public void setDescricaoContratoCobranca(String descricaoContratoCobranca) {
		this.descricaoContratoCobranca = descricaoContratoCobranca;
	}

	public String getCodEmpresaProvMeioPagamentoContratoCobranca() {
		return codEmpresaProvMeioPagamentoContratoCobranca;
	}

	public void setCodEmpresaProvMeioPagamentoContratoCobranca(
			String codEmpresaProvMeioPagamentoContratoCobranca) {
		this.codEmpresaProvMeioPagamentoContratoCobranca = codEmpresaProvMeioPagamentoContratoCobranca;
	}

	public String getPrazoPagamentoDiasContratoCobranca() {
		return prazoPagamentoDiasContratoCobranca;
	}

	public void setPrazoPagamentoDiasContratoCobranca(
			String prazoPagamentoDiasContratoCobranca) {
		this.prazoPagamentoDiasContratoCobranca = prazoPagamentoDiasContratoCobranca;
	}

	public String getTipoTransacaoProvedorContratoCobranca() {
		return tipoTransacaoProvedorContratoCobranca;
	}

	public void setTipoTransacaoProvedorContratoCobranca(
			String tipoTransacaoProvedorContratoCobranca) {
		this.tipoTransacaoProvedorContratoCobranca = tipoTransacaoProvedorContratoCobranca;
	}

	public String getInicioVigenciaContratoCobranca() {
		return inicioVigenciaContratoCobranca;
	}

	public void setInicioVigenciaContratoCobranca(
			String inicioVigenciaContratoCobranca) {
		this.inicioVigenciaContratoCobranca = inicioVigenciaContratoCobranca;
	}

	public String getFimVigenciaContratoCobranca() {
		return fimVigenciaContratoCobranca;
	}

	public void setFimVigenciaContratoCobranca(String fimVigenciaContratoCobranca) {
		this.fimVigenciaContratoCobranca = fimVigenciaContratoCobranca;
	}
	
	public String getIdRespostaAutorizacao() {
		return idRespostaAutorizacao;
	}

	public void setIdRespostaAutorizacao(String idRespostaAutorizacao) {
		this.idRespostaAutorizacao = idRespostaAutorizacao;
	}

	public String getCodigoEmpresaRespostaAutorizacao() {
		return codigoEmpresaRespostaAutorizacao;
	}

	public void setCodigoEmpresaRespostaAutorizacao(
			String codigoEmpresaRespostaAutorizacao) {
		this.codigoEmpresaRespostaAutorizacao = codigoEmpresaRespostaAutorizacao;
	}

	public String getCodigoMeioPagamentoRespostaAutorizacao() {
		return codigoMeioPagamentoRespostaAutorizacao;
	}

	public void setCodigoMeioPagamentoRespostaAutorizacao(
			String codigoMeioPagamentoRespostaAutorizacao) {
		this.codigoMeioPagamentoRespostaAutorizacao = codigoMeioPagamentoRespostaAutorizacao;
	}

	public String getCodigoProvedorRespostaAutorizacao() {
		return codigoProvedorRespostaAutorizacao;
	}

	public void setCodigoProvedorRespostaAutorizacao(
			String codigoProvedorRespostaAutorizacao) {
		this.codigoProvedorRespostaAutorizacao = codigoProvedorRespostaAutorizacao;
	}

	public String getCodigoContratoRespostaAutorizacao() {
		return codigoContratoRespostaAutorizacao;
	}

	public void setCodigoContratoRespostaAutorizacao(
			String codigoContratoRespostaAutorizacao) {
		this.codigoContratoRespostaAutorizacao = codigoContratoRespostaAutorizacao;
	}

	public String getCodigoRespostaAutorizacao() {
		return codigoRespostaAutorizacao;
	}

	public void setCodigoRespostaAutorizacao(String codigoRespostaAutorizacao) {
		this.codigoRespostaAutorizacao = codigoRespostaAutorizacao;
	}

	public String getDescricaoRespostaAutorizacaoProvedor() {
		return descricaoRespostaAutorizacaoProvedor;
	}

	public void setDescricaoRespostaAutorizacaoProvedor(
			String descricaoRespostaAutorizacaoProvedor) {
		this.descricaoRespostaAutorizacaoProvedor = descricaoRespostaAutorizacaoProvedor;
	}

	public String getDescricaoRespostaAutorizacaoConsulta() {
		return descricaoRespostaAutorizacaoConsulta;
	}

	public void setDescricaoRespostaAutorizacaoConsulta(
			String descricaoRespostaAutorizacaoConsulta) {
		this.descricaoRespostaAutorizacaoConsulta = descricaoRespostaAutorizacaoConsulta;
	}

	public String getQuantidadeRetentativas() {
		return quantidadeRetentativas;
	}

	public void setQuantidadeRetentativas(String quantidadeRetentativas) {
		this.quantidadeRetentativas = quantidadeRetentativas;
	}

	public String getTipoIntervaloRetentativas() {
		return tipoIntervaloRetentativas;
	}

	public void setTipoIntervaloRetentativas(String tipoIntervaloRetentativas) {
		this.tipoIntervaloRetentativas = tipoIntervaloRetentativas;
	}

	public String getIntervaloEntreRetentativas() {
		return intervaloEntreRetentativas;
	}

	public void setIntervaloEntreRetentativas(String intervaloEntreRetentativas) {
		this.intervaloEntreRetentativas = intervaloEntreRetentativas;
	}

	public String getIdListaValor() {
		return idListaValor;
	}

	public void setIdListaValor(String idListaValor) {
		this.idListaValor = idListaValor;
	}

	public String getCodigoListaValor() {
		return codigoListaValor;
	}

	public void setCodigoListaValor(String codigoListaValor) {
		this.codigoListaValor = codigoListaValor;
	}

	public String getDescricaoListaValor() {
		return descricaoListaValor;
	}

	public void setDescricaoListaValor(String descricaoListaValor) {
		this.descricaoListaValor = descricaoListaValor;
	}

	public String getTipoDeUsoListaValor() {
		return tipoDeUsoListaValor;
	}

	public void setTipoDeUsoListaValor(String tipoDeUsoListaValor) {
		this.tipoDeUsoListaValor = tipoDeUsoListaValor;
	}

	public String getStatusListaValor() {
		return statusListaValor;
	}

	public void setStatusListaValor(String statusListaValor) {
		this.statusListaValor = statusListaValor;
	}
	
	public String getIdItemLista() {
		return idItemLista;
	}

	public void setIdItemLista(String idItemLista) {
		this.idItemLista = idItemLista;
	}

	public String getIdListaValorItemLista() {
		return idListaValorItemLista;
	}

	public void setIdListaValorItemLista(String idListaValorItemLista) {
		this.idListaValorItemLista = idListaValorItemLista;
	}

	public String getCodigoItemLista() {
		return codigoItemLista;
	}

	public void setCodigoItemLista(String codigoItemLista) {
		this.codigoItemLista = codigoItemLista;
	}

	public String getDescricaoItemLista() {
		return descricaoItemLista;
	}

	public void setDescricaoItemLista(String descricaoItemLista) {
		this.descricaoItemLista = descricaoItemLista;
	}

	public String getStatusItemLista() {
		return statusItemLista;
	}

	public void setStatusItemLista(String statusItemLista) {
		this.statusItemLista = statusItemLista;
	}
	
	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getNumeroMaximoParcelasProduto() {
		return numeroMaximoParcelasProduto;
	}

	public void setNumeroMaximoParcelasProduto(String numeroMaximoParcelasProduto) {
		this.numeroMaximoParcelasProduto = numeroMaximoParcelasProduto;
	}

	public String getTipoCobrancaProduto() {
		return tipoCobrancaProduto;
	}

	public void setTipoCobrancaProduto(String tipoCobrancaProduto) {
		this.tipoCobrancaProduto = tipoCobrancaProduto;
	}
	
	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}

	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	public String getDescricaoSistema() {
		return descricaoSistema;
	}

	public void setDescricaoSistema(String descricaoSistema) {
		this.descricaoSistema = descricaoSistema;
	}

	public String getSistemaEnvioInformacoesSistema() {
		return sistemaEnvioInformacoesSistema;
	}

	public void setSistemaEnvioInformacoesSistema(
			String sistemaEnvioInformacoesSistema) {
		this.sistemaEnvioInformacoesSistema = sistemaEnvioInformacoesSistema;
	}

	public String getNovaBusca() {
		return novaBusca;
	}

	public void setNovaBusca(String novaBusca) {
		this.novaBusca = novaBusca;
	}
	
	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	
	public String getCodigoMetodoPagamento() {
		return codigoMetodoPagamento;
	}

	public void setCodigoMetodoPagamento(String codigoMetodoPagamento) {
		this.codigoMetodoPagamento = codigoMetodoPagamento;
	}
	
	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}
	
	public String getProdutosAssociados() {
		return produtosAssociados;
	}

	public void setProdutosAssociados(String produtosAssociados) {
		this.produtosAssociados = produtosAssociados;
	}
	
	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
	
	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getTipoObjetoOperacao() {
		return tipoObjetoOperacao;
	}

	public void setTipoObjetoOperacao(String tipoObjetoOperacao) {
		this.tipoObjetoOperacao = tipoObjetoOperacao;
	}

	public String getIdentificadorObjetoOperacao() {
		return identificadorObjetoOperacao;
	}

	public void setIdentificadorObjetoOperacao(String identificadorObjetoOperacao) {
		this.identificadorObjetoOperacao = identificadorObjetoOperacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(String periodoDe) {
		this.periodoDe = periodoDe;
	}

	public String getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(String periodoAte) {
		this.periodoAte = periodoAte;
	}
	
	public String getDescricaoListaValorItemLista() {
		return descricaoListaValorItemLista;
	}

	public void setDescricaoListaValorItemLista(String descricaoListaValorItemLista) {
		this.descricaoListaValorItemLista = descricaoListaValorItemLista;
	}
	
	public String getIdProvedorMeioPagamento() {
		return idProvedorMeioPagamento;
	}

	public void setIdProvedorMeioPagamento(String idProvedorMeioPagamento) {
		this.idProvedorMeioPagamento = idProvedorMeioPagamento;
	}
	
	public String getContratosAssociados() {
		return contratosAssociados;
	}

	public void setContratosAssociados(String contratosAssociados) {
		this.contratosAssociados = contratosAssociados;
	}

	public String getIdAdquirenteBandeira() {
		return idAdquirenteBandeira;
	}

	public void setIdAdquirenteBandeira(String idAdquirenteBandeira) {
		this.idAdquirenteBandeira = idAdquirenteBandeira;
	}

	public String getPadraoAssociarContrato() {
		return padraoAssociarContrato;
	}

	public void setPadraoAssociarContrato(String padraoAssociarContrato) {
		this.padraoAssociarContrato = padraoAssociarContrato;
	}

	public String getIdAdquirenteOld() {
		return idAdquirenteOld;
	}

	public void setIdAdquirenteOld(String idAdquirenteOld) {
		this.idAdquirenteOld = idAdquirenteOld;
	}

	public String getIdBandeiraOld() {
		return idBandeiraOld;
	}

	public void setIdBandeiraOld(String idBandeiraOld) {
		this.idBandeiraOld = idBandeiraOld;
	}

	public String getPadraoAssociarContratoOld() {
		return padraoAssociarContratoOld;
	}

	public void setPadraoAssociarContratoOld(String padraoAssociarContratoOld) {
		this.padraoAssociarContratoOld = padraoAssociarContratoOld;
	}

	public String getCodigoMetodoPagamentoOld() {
		return codigoMetodoPagamentoOld;
	}

	public void setCodigoMetodoPagamentoOld(String codigoMetodoPagamentoOld) {
		this.codigoMetodoPagamentoOld = codigoMetodoPagamentoOld;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getProvedorContratoCobranca() {
		return provedorContratoCobranca;
	}

	public void setProvedorContratoCobranca(String provedorContratoCobranca) {
		this.provedorContratoCobranca = provedorContratoCobranca;
	}

	public String getListaAtiva() {
		return listaAtiva;
	}

	public void setListaAtiva(String listaAtiva) {
		this.listaAtiva = listaAtiva;
	}

	@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
        HttpSession session = request.getSession();
        
        ActionErrors errors = new ActionErrors();
        boolean campoVazio = false;
        boolean validouAlgumCampo = false;
        boolean semMensagemCamposEmVermelho = false;
        
        if (getOpcao() != null && (getOpcao().equals("empresa") || getOpcao().equals("pesquisaEmpresa"))) {
            // EMPRESA
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
	          if (Validador.vazioComTrim(this.codigoEmpresa)) {
	        	  campoVazio = true;
	        	  errors.add("codigoEmpresa", new ActionMessage("", "codigoEmpresa"));
//	        	  errors.add("", new ActionMessage("erro.codigo.empresa.vazio", ""));
	          } else {
	        	  validouAlgumCampo = true;
	        	  this.codigoEmpresa = this.codigoEmpresa.trim();
	          }
	          if (Validador.vazioComTrim(this.descricaoEmpresa)) {
	        	  campoVazio = true;
	        	  errors.add("descricaoEmpresa", new ActionMessage("", "descricaoEmpresa"));
//	        	  errors.add("", new ActionMessage("erro.descricao.empresa.vazio", ""));
	          } else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoEmpresa = this.descricaoEmpresa.trim();
	          }     
        }
        if (getOpcao() != null && (getOpcao().equals("produto") || getOpcao().equals("pesquisaProduto"))) {
        	
//            produto.setCodigo(vaf.getCodigoProduto());
//            produto.setEmpresa(empresaDLO.obter(vaf.getCodigoEmpresa()));
////            produto.setEmpresa(cvh.listarEmpresa().get(0));
//            produto.setDescricao(vaf.getDescricaoProduto());
//            produto.setCodigoEmpresaVenda(vaf.getCodigoEmpresaVendaProduto());
//            produto.setCodigoSistemaVenda(vaf.getCodigoSistemaVendaProduto());
//            produto.setNumeroMaximoParcelas(Integer.valueOf(vaf.getNumeroMaximoParcelasProduto()));
//            produto.setTipoCobranca(TipoCobranca.buscarPorValor(vaf.getTipoCobrancaProduto()));
        	
        	// PRODUTO
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoEmpresa)) {
        		campoVazio = true;
        		errors.add("codigoEmpresa", new ActionMessage("", "codigoEmpresa"));
//        		errors.add("", new ActionMessage("erro.codigo.empresa.vazio", ""));
        	} else {
        		if (!Validador.vazioComTrim(this.codigoProduto) || !Validador.vazioComTrim(this.descricaoProduto)) {
	        	  validouAlgumCampo = true;
	        	  this.codigoProduto = this.codigoProduto.trim();
	        	  this.descricaoProduto = this.descricaoProduto.trim();
        		}
        		this.codigoEmpresa = this.codigoEmpresa.trim();
	        }      	
        	if (Validador.vazioComTrim(this.codigoProduto)) {
        		campoVazio = true;
        		errors.add("codigoProduto", new ActionMessage("", "codigoProduto"));
//        		errors.add("", new ActionMessage("erro.codigo.produto.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoProduto = this.codigoProduto.trim();
	        }      	
        	if (Validador.vazioComTrim(this.descricaoProduto)) {
        		campoVazio = true;
        		errors.add("descricaoProduto", new ActionMessage("", "descricaoProduto"));
//        		errors.add("", new ActionMessage("erro.descricao.produto.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoProduto = this.descricaoProduto.trim();
	        }
        	
        	if (!getOpcao().equals("pesquisaProduto")) {
        	
	        	if (Validador.vazioComTrim(this.tipoCobrancaProduto)) {
	        		campoVazio = true;
	        		errors.add("tipoCobrancaProduto", new ActionMessage("", "tipoCobrancaProduto"));
	//        		errors.add("", new ActionMessage("erro.tipo.cobranca.produto.vazio", ""));
	        	} else {
		        	  validouAlgumCampo = true;
		        	  this.tipoCobrancaProduto = this.tipoCobrancaProduto.trim();
		        }     
	        	if (Validador.vazioComTrim(this.numeroMaximoParcelasProduto)) {
	        		campoVazio = true;
	        		errors.add("numeroMaximoParcelasProduto", new ActionMessage("", "numeroMaximoParcelasProduto"));
	//        		errors.add("", new ActionMessage("erro.numero.maximo.parcelas.produto.vazio", ""));
	        	} else {
	        		if (Integer.valueOf(this.numeroMaximoParcelasProduto).intValue() == 0) {
	        			errors.add("numeroMaximoParcelasProduto", new ActionMessage("", "numeroMaximoParcelasProduto"));
	        			errors.add("", new ActionMessage("erro.numero.maximo.parcelas.produto.igual.zero", ""));
	        		} else {
	       	        	validouAlgumCampo = true;
	       	        	this.numeroMaximoParcelasProduto = this.numeroMaximoParcelasProduto.trim();
	        		}
	        	}
        	}
        }
        
        if (getOpcao() != null && (getOpcao().equals("sistema") || getOpcao().equals("pesquisaSistema"))) {
        	// SISTEMA
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoSistema)) {
        		campoVazio = true;
        		errors.add("codigoSistema", new ActionMessage("", "codigoSistema"));
//        		errors.add("", new ActionMessage("erro.codigo.sistema.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoSistema = this.codigoSistema.trim();
	        }    	
        	if (Validador.vazioComTrim(this.descricaoSistema)) {
        		campoVazio = true;
        		errors.add("descricaoSistema", new ActionMessage("", "descricaoSistema"));
//        		errors.add("", new ActionMessage("erro.descricao.sistema.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoSistema = this.descricaoSistema.trim();
	        }
        	if (!getOpcao().equals("pesquisaSistema")) {
	        	if (Validador.vazioComTrim(this.sistemaEnvioInformacoesSistema)) {
	        		campoVazio = true;
	        		errors.add("sistemaEnvioInformacoesSistema", new ActionMessage("", "sistemaEnvioInformacoesSistema"));
	//        		errors.add("", new ActionMessage("erro.sistemaEnvioInformacoesSistema.sistema.vazio", ""));
	        	} else {
		        	  validouAlgumCampo = true;
		        	  this.sistemaEnvioInformacoesSistema = this.sistemaEnvioInformacoesSistema.trim();
		        }
        	}
        }
        
        if (getOpcao() != null && (getOpcao().equals("meioPagamento")  || getOpcao().equals("pesquisaMeioPagamento"))) {
        	// MEIO DE PAGAMENTO
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoMeioPagamento)) {
        		campoVazio = true;
        		errors.add("codigoMeioPagamento", new ActionMessage("", "codigoMeioPagamento"));
//        		errors.add("", new ActionMessage("erro.codigo.meioPagamento.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoMeioPagamento = this.codigoMeioPagamento.trim();
	        }   
        	if (Validador.vazioComTrim(this.descricaoMeioPagamento)) {
        		campoVazio = true;
        		errors.add("descricaoMeioPagamento", new ActionMessage("", "descricaoMeioPagamento"));
//        		errors.add("", new ActionMessage("erro.descricao.meioPagamento.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoMeioPagamento = this.descricaoMeioPagamento.trim();
	        }     
        	if (!getOpcao().equals("pesquisaMeioPagamento")) {
	        	if (!Validador.vazioComTrim(this.limiteDiasRetentativa)) {
	        		if (Integer.valueOf(this.limiteDiasRetentativa).intValue() == 0) {
//	        			errors.add("limiteDiasRetentativa", new ActionMessage("", "limiteDiasRetentativa"));
	        			errors.add("", new ActionMessage("erro.limiteDiasRetentativa.igual.zero", ""));
	        			semMensagemCamposEmVermelho = true;
	        		} else {
			        	  validouAlgumCampo = true;
			        	  this.limiteDiasRetentativa = this.limiteDiasRetentativa.trim();
			        }
	        	} else {
	        		campoVazio = true;
        			errors.add("limiteDiasRetentativa", new ActionMessage("", "limiteDiasRetentativa"));
	        	}
        	}
        }
        
        if (getOpcao() != null && (getOpcao().equals("provedorMeioPagamento") || getOpcao().equals("pesquisaProvedorMeioPagamento"))) {
        	// PROVEDOR DO MEIO DE PAGAMENTO
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoProvedor)) {
        		campoVazio = true;
        		errors.add("codigoProvedor", new ActionMessage("", "codigoProvedor"));
//        		errors.add("", new ActionMessage("erro.codigo.provedorMeioPagamento.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoProvedor = this.codigoProvedor.trim();
	        }
        	if (Validador.vazioComTrim(this.descricaoProvedor)) {
        		campoVazio = true;
        		errors.add("descricaoProvedor", new ActionMessage("", "descricaoProvedor"));
//        		errors.add("", new ActionMessage("erro.descricao.provedorMeioPagamento.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoProvedor = this.descricaoProvedor.trim();
	        }
        }
        
        
//        //CODIGO DE AFILIACAO SOH EH VALIDADO EM PESQUISA        
        if(getOpcao() != null && getOpcao().equals("pesquisaAdquirente")){
        	if (Validador.vazioComTrim(this.codigoAfiliacao)) {
        		campoVazio = true;
        		errors.add("codigoAfiliacao", new ActionMessage("", "codigoAfiliacao"));
//        		errors.add("", new ActionMessage("erro.codigoConvenioAdquirente.adquirente.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoAfiliacao = this.codigoAfiliacao.trim();
	        } 
        	
        }
        
        
        if (getOpcao() != null && (getOpcao().equals("adquirente") || getOpcao().equals("pesquisaAdquirente"))) {
        	// ADQUIRENTE
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------   
        	if (Validador.vazioComTrim(this.codigoConvenioAdquirente)) {
        		campoVazio = true;
        		errors.add("codigoConvenioAdquirente", new ActionMessage("", "codigoConvenioAdquirente"));
//        		errors.add("", new ActionMessage("erro.codigoConvenioAdquirente.adquirente.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoConvenioAdquirente = this.codigoConvenioAdquirente.trim();
	        }   
        	if (Validador.vazioComTrim(this.nomeAdquirente)) {
        		campoVazio = true;
        		errors.add("nomeAdquirente", new ActionMessage("", "nomeAdquirente"));
//        		errors.add("", new ActionMessage("erro.nomeAdquirente.adquirente.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.nomeAdquirente = this.nomeAdquirente.trim();
	        }     
        }
        
        if (getOpcao() != null && (getOpcao().equals("bandeira") || getOpcao().equals("pesquisaBandeira"))) {
        	// BANDEIRA
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoBandeira)) {
        		campoVazio = true;
        		errors.add("codigoBandeira", new ActionMessage("", "codigoBandeira"));
//        		errors.add("", new ActionMessage("erro.codigoBandeira.bandeira.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoBandeira = this.codigoBandeira.trim();
	        }  
        	if (Validador.vazioComTrim(this.nomeBandeira)) {
        		campoVazio = true;
        		errors.add("nomeBandeira", new ActionMessage("", "nomeBandeira"));
//        		errors.add("", new ActionMessage("erro.nomeBandeira.bandeira.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.nomeBandeira = this.nomeBandeira.trim();
	        } 
        }
        
        if (getOpcao() != null && (getOpcao().equals("listaValor") || getOpcao().equals("pesquisaListaValor"))) {
        	
        	// LISTA DE VALOR
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoListaValor)) {
        		campoVazio = true;
        		errors.add("codigoListaValor", new ActionMessage("", "codigoListaValor"));
//        		errors.add("", new ActionMessage("erro.codigo.listaValor.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoListaValor = this.codigoListaValor.trim();
	        }    
        	if (Validador.vazioComTrim(this.descricaoListaValor)) {
        		campoVazio = true;
        		errors.add("descricaoListaValor", new ActionMessage("", "descricaoListaValor"));
//        		errors.add("", new ActionMessage("erro.descricao.listaValor.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoListaValor = this.descricaoListaValor.trim();
	        }  
        	if (Validador.vazioComTrim(this.tipoDeUsoListaValor)) {
        		campoVazio = true;
        		errors.add("tipoDeUsoListaValor", new ActionMessage("", "tipoDeUsoListaValor"));
//        		errors.add("", new ActionMessage("erro.tipoDeUsoListaValor.listaValor.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.tipoDeUsoListaValor = this.tipoDeUsoListaValor.trim();
	        }  
        	if (Validador.vazioComTrim(this.statusListaValor)) {
        		campoVazio = true;
        		errors.add("statusListaValor", new ActionMessage("", "statusListaValor"));
//        		errors.add("", new ActionMessage("erro.statusListaValor.listaValor.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.statusListaValor = this.statusListaValor.trim();
        	}  
        	
        }
        
        if (getOpcao() != null && (getOpcao().equals("itemLista") || getOpcao().equals("pesquisaItemLista")) && 
        		!getOpcao().equals("limparItemLista") && !getOpcao().equals("exibirDetalheItemLista") &&
        		!getOpcao().equals("voltarItemLista") && !getOpcao().equals("incluirItemLista") &&
        		!getOpcao().equals("ativarItemLista") && !getOpcao().equals("desativarItemLista")) {
        	// ITEM DA LISTA DE VALOR
        	// - CODIGO e DESCRIÇÃO -------------------------------------------------------------------------------------  
        	if (Validador.vazioComTrim(this.codigoItemLista)) {
        		campoVazio = true;
        		errors.add("codigoItemLista", new ActionMessage("", "codigoItemLista"));
//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.codigoItemLista = this.codigoItemLista.trim();
	        }           	
        	if (Validador.vazioComTrim(this.descricaoItemLista)) {
        		campoVazio = true;
        		errors.add("descricaoItemLista", new ActionMessage("", "descricaoItemLista"));
//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
        	} else {
	        	  validouAlgumCampo = true;
	        	  this.descricaoItemLista = this.descricaoItemLista.trim();
	        }
        	if (getOpcao().equals("itemLista")) {
	        	if (Validador.vazioComTrim(this.statusItemLista)) {
	        		campoVazio = true;
	        		errors.add("statusItemLista", new ActionMessage("", "statusItemLista"));
	//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.statusItemLista = this.statusItemLista.trim();
	        	}
        	}
        	if (campoVazio && idListaValor != null && !Validador.vazio(idListaValor)) {
        		descricaoListaValorItemLista = dloListaValor.obter(Long.valueOf(idListaValor)).getDescricao();
        	}
        }
        
        if (getOpcao() != null && (getOpcao().equals("contratoCobranca") || getOpcao().equals("pesquisaContratoCobranca")
        		|| getOpcao().endsWith("manterContratoCobranca"))) {
        	// CONTRATO DE COBRANCA
        	
        	// TODO VER DETALHE DAS DATAS
        	// Data de início de vigência do contrato:
        	// Deve ser posterior a data de fim de vigência anterior do contrato
        	// Data de fim de vigência do contrato
        	// Posterior a data de início de vigência
        	
        	// Todas a telas
        	if (Validador.vazioComTrim(this.descricaoContratoCobranca)) {
        		campoVazio = true;
        		errors.add("descricaoContratoCobranca", new ActionMessage("", "descricaoContratoCobranca"));
//            		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.descricaoContratoCobranca = this.descricaoContratoCobranca.trim();
        	}  
        	
        	if (Validador.vazioComTrim(this.codigoEmpresaContratoCobranca)) {
        		campoVazio = true;
        		errors.add("codigoEmpresaContratoCobranca", new ActionMessage("", "codigoEmpresaContratoCobranca"));
//            		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.codigoEmpresaContratoCobranca = this.codigoEmpresaContratoCobranca.trim();
        	} 
        	
        	if (Validador.vazioComTrim(this.codigoProvedorContratoCobranca)) {
        		campoVazio = true;
        		errors.add("codigoProvedorContratoCobranca", new ActionMessage("", "codigoProvedorContratoCobranca"));
//            		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.codigoProvedorContratoCobranca = this.codigoProvedorContratoCobranca.trim();
        	}              	
            	
        	// Somente pra tela de edicao
        	if ((getOpcao().equals("contratoCobranca") || getOpcao().endsWith("manterContratoCobranca"))) {
        	
	        	if (Validador.vazioComTrim(this.codigoMeioPagamentoContratoCobranca)) {
	        		campoVazio = true;
	        		errors.add("codigoMeioPagamentoContratoCobranca", new ActionMessage("", "codigoMeioPagamentoContratoCobranca"));
	//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.codigoMeioPagamentoContratoCobranca = this.codigoMeioPagamentoContratoCobranca.trim();
	        	}           	
	        	
	        	if (Validador.vazioComTrim(this.codEmpresaProvMeioPagamentoContratoCobranca)) {
	        		campoVazio = true;
	        		errors.add("codEmpresaProvMeioPagamentoContratoCobranca", new ActionMessage("", "codEmpresaProvMeioPagamentoContratoCobranca"));
	//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.codEmpresaProvMeioPagamentoContratoCobranca = this.codEmpresaProvMeioPagamentoContratoCobranca.trim();
	        	}           	
	        	
	        	if (Validador.vazioComTrim(this.prazoPagamentoDiasContratoCobranca)) {
	        		campoVazio = true;
	        		errors.add("prazoPagamentoDiasContratoCobranca", new ActionMessage("", "prazoPagamentoDiasContratoCobranca"));
	//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.prazoPagamentoDiasContratoCobranca = this.prazoPagamentoDiasContratoCobranca.trim();
	        	}   
	        	
	        	if (Validador.vazioComTrim(this.tipoTransacaoProvedorContratoCobranca)) {
	        		campoVazio = true;
	        		errors.add("tipoTransacaoProvedorContratoCobranca", new ActionMessage("", "tipoTransacaoProvedorContratoCobranca"));
	//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.tipoTransacaoProvedorContratoCobranca = this.tipoTransacaoProvedorContratoCobranca.trim();
	        	}           	
	        	
	        	if (Validador.vazioComTrim(this.inicioVigenciaContratoCobranca)) {
	        		campoVazio = true;
	        		errors.add("inicioVigenciaContratoCobranca", new ActionMessage("", "inicioVigenciaContratoCobranca"));
	//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.inicioVigenciaContratoCobranca = this.inicioVigenciaContratoCobranca.trim();
	        	}           	
	        	
	        	if (Validador.vazioComTrim(this.fimVigenciaContratoCobranca)) {
	        		campoVazio = true;
	        		errors.add("fimVigenciaContratoCobranca", new ActionMessage("", "fimVigenciaContratoCobranca"));
	//        		errors.add("", new ActionMessage("erro.descricao.itemLista.vazio", ""));
	        	} else {
	        		validouAlgumCampo = true;
	        		this.fimVigenciaContratoCobranca = this.fimVigenciaContratoCobranca.trim();
	        	}  
	        	
	        	if (!Validador.vazioComTrim(this.inicioVigenciaContratoCobranca) && !Validador.vazioComTrim(this.fimVigenciaContratoCobranca)) {
					SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
					sdf.setLenient(false);
		
					Date dataInicial=null;
					Date dataFinal=null;
		
					try {
						dataInicial= sdf.parse(inicioVigenciaContratoCobranca); 
						dataFinal  = sdf.parse(fimVigenciaContratoCobranca);
						
						if (dataInicial.after(dataFinal)) {
							//errors.add("inicioVigenciaContratoCobranca", new ActionMessage("", "inicioVigenciaContratoCobranca"));
							//errors.add("fimVigenciaContratoCobranca", new ActionMessage("", "fimVigenciaContratoCobranca"));
			        		errors.add("", new ActionMessage("erro.periodoInicioVigencia.contratoCobranca.depois.periodoFimVigencia", ""));
						} else if (dataFinal.before(dataInicial)) {
							//errors.add("inicioVigenciaContratoCobranca", new ActionMessage("", "inicioVigenciaContratoCobranca"));
							//errors.add("fimVigenciaContratoCobranca", new ActionMessage("", "fimVigenciaContratoCobranca"));
			        		errors.add("", new ActionMessage("erro.periodoFimVigencia.contratoCobranca.depois.periodoInicioVigencia", ""));							
						}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
	        	}
        	
        	}
        	
        }
        
        if (getOpcao() != null && (getOpcao().equals("adquirenteBandeira") 
        		|| getOpcao().equals("editarAssociacaoAdquirente")
        		|| getOpcao().equals("prosseguirAdquirenteBandeira"))) {
        	// Associação Adquirente 
        	
        	AdquirenteBandeira adquirenteBandeiraConsultaPrevia = null;
        	boolean isMesmoAdquirente = false;
        	
        	if (getOpcao().equals("editarAssociacaoAdquirente")
        			&& !Validador.vazio(this.idAdquirenteSelecionado)
        			&& !Validador.vazio(this.idBandeiraSelecionado)) {
        		this.idAdquirente = this.idAdquirenteSelecionado;
        		this.idBandeira = this.idBandeiraSelecionado;
        	}
        	
        	if (!getOpcao().equals("editarAssociacaoAdquirente")){
            	if (Validador.vazioComTrim(this.padraoAssociarContrato)) {
            		campoVazio = true;
            		semMensagemCamposEmVermelho = false;
            		errors.add("padraoAssociarContrato", new ActionMessage("", "padraoAssociarContrato"));
//            		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
            	} else {
            		validouAlgumCampo = true;
            		this.padraoAssociarContrato = this.padraoAssociarContrato.trim();
            	}  
            	
            	if (Validador.vazioComTrim(this.codigoMetodoPagamento)) {
            		campoVazio = true;
            		semMensagemCamposEmVermelho = false;
            		errors.add("codigoMetodoPagamento", new ActionMessage("", "codigoMetodoPagamento"));
//            		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
            	} else {
            		validouAlgumCampo = true;
            		this.codigoMetodoPagamento = this.codigoMetodoPagamento.trim();
            	}
        	}

        	if (Validador.vazioComTrim(this.idAdquirente)) {
        		campoVazio = true;
        		semMensagemCamposEmVermelho = false;
        		errors.add("idAdquirente", new ActionMessage("", "idAdquirente"));
//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.idAdquirente = this.idAdquirente.trim();
        	}         	
        	
        	if (Validador.vazioComTrim(this.idBandeira)) {
        		campoVazio = true;
        		semMensagemCamposEmVermelho = false;
        		errors.add("idBandeira", new ActionMessage("", "idBandeira"));
//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.idBandeira = this.idBandeira.trim();
        	}
        	
        	if (!getOpcao().equals("editarAssociacaoAdquirente") 
        			&& !Validador.vazioComTrim(this.idBandeira)
        			&& !Validador.vazioComTrim(this.idAdquirente)
        			&& this.padraoAssociarContrato.equals("S")) {
            	
	        	List<AdquirenteBandeira> listaAssociacaoAdquirente = 
	        			dloAdquirenteBandeira.listarPorCriterio(idAdquirente, idBandeira, idContratoCobranca, "S", "");
	        	
	        	// && !getOpcao().equals("prosseguirAdquirenteBandeira")
	        	
	        	if (!listaAssociacaoAdquirente.isEmpty()) {
	        		adquirenteBandeiraConsultaPrevia = listaAssociacaoAdquirente.get(0);
	        		
		        	if (idAdquirente.equals(adquirenteBandeiraConsultaPrevia.getAdquirente().getId().toString())
		        			&& idBandeira.equals(adquirenteBandeiraConsultaPrevia.getBandeira().getId().toString())
		        			&& adquirenteBandeiraConsultaPrevia.getAdquirentePadrao().equals("S")) {
		        		isMesmoAdquirente = true;
		        	}
	        	}
	        	
	        	if (!isMesmoAdquirente ) {
		        	List<AdquirenteBandeira> listaAssociacaoAdquirenteSomentePorBandeira = 
		        			dloAdquirenteBandeira.listarPorCriterio("", idBandeira, idContratoCobranca, "S", "");
		        	
		        	if (!listaAssociacaoAdquirenteSomentePorBandeira.isEmpty()) {
		        		errors.add("", new ActionMessage("erro.adquirente.bandeira.existe.padrao.associacaoAdquirente", ""));
		        		campoVazio = false;
		        		semMensagemCamposEmVermelho = true;
		        		if (!Validador.vazio(this.idAdquirenteSelecionado) && !Validador.vazio(this.idBandeiraSelecionado)) {
		        			this.opcao = "editarAssociacaoAdquirente";
		        		}
		        	}
	        	}
        	
        	}
        	
        }
        
        if (getOpcao() != null && (getOpcao().equals("manterRespostaAutorizacao"))) {
        	// Resposta de Autorização
        	
        	if (Validador.vazioComTrim(this.codigoRespostaAutorizacao)) {
        		campoVazio = true;
        		errors.add("codigoRespostaAutorizacao", new ActionMessage("", "codigoRespostaAutorizacao"));
//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.codigoRespostaAutorizacao = this.codigoRespostaAutorizacao.trim();
        	}            	
        	if (Validador.vazioComTrim(this.descricaoRespostaAutorizacaoProvedor)) {
        		campoVazio = true;
        		errors.add("descricaoRespostaAutorizacaoProvedor", new ActionMessage("", "descricaoRespostaAutorizacaoProvedor"));
//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.descricaoRespostaAutorizacaoProvedor = this.descricaoRespostaAutorizacaoProvedor.trim();
        	}
        	
        	if (Validador.vazioComTrim(this.quantidadeRetentativas)) {
        		campoVazio = true;
    			errors.add("quantidadeRetentativas", new ActionMessage("", "quantidadeRetentativas"));
    			
        	} else {
   	        	validouAlgumCampo = true;
   	        	this.quantidadeRetentativas = this.quantidadeRetentativas.trim();
        		int valorQuantidadeRetentativas = Integer.parseInt(this.quantidadeRetentativas);
   	        	
        		if (valorQuantidadeRetentativas > 0) {
	            	if (Validador.vazioComTrim(this.tipoIntervaloRetentativas)) {
	            		campoVazio = true;
	            		errors.add("tipoIntervaloRetentativas", new ActionMessage("", "tipoIntervaloRetentativas"));
	//                		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
	            	} else {
	            		validouAlgumCampo = true;
	            		this.tipoIntervaloRetentativas = this.tipoIntervaloRetentativas.trim();
	            	}   
	            	if (Validador.vazioComTrim(this.intervaloEntreRetentativas)) {
	            		campoVazio = true;
	            		errors.add("intervaloEntreRetentativas", new ActionMessage("", "intervaloEntreRetentativas"));
	//                		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
	            	} else {
	            		validouAlgumCampo = true;
	            		this.intervaloEntreRetentativas = this.intervaloEntreRetentativas.trim();
	            	}
        		}
        	} 
        	if (Validador.vazioComTrim(this.descricaoRespostaAutorizacaoConsulta)) { // TODO AO CORRIGIR DOCUMENTAÇÃO OU NO BANCO REVER
        		campoVazio = true;
        		errors.add("descricaoRespostaAutorizacaoConsulta", new ActionMessage("", "descricaoRespostaAutorizacaoConsulta"));
//        		errors.add("", new ActionMessage("erro.codigo.itemLista.vazio", ""));
        	} else {
        		validouAlgumCampo = true;
        		this.descricaoRespostaAutorizacaoConsulta = this.descricaoRespostaAutorizacaoConsulta.trim();
        	}         	
         	
        	
        }
        
        
        // Se a consulta é feita a partir da opção voltar do Detalhar venda....
        if (!Boolean.valueOf(getNovaBusca()).booleanValue()) {
//	        if (session.getAttribute("codEmpresa") != null && !Validador.vazioComTrim((String)session.getAttribute("codEmpresa"))) {
//	        	this.codEmpresa = (String) session.getAttribute("codEmpresa");
//	        }
//	        if (session.getAttribute("idProduto") != null && !Validador.vazioComTrim((String)session.getAttribute("idProduto"))) {
//	        	this.codProduto = (String) session.getAttribute("idProduto");
//	        }
//	        if (session.getAttribute("idSistema") != null && !Validador.vazioComTrim((String)session.getAttribute("idSistema"))) {
//	        	this.codSistema = (String) session.getAttribute("idSistema");
//	        }
//	        if (session.getAttribute("cpf") != null && !Validador.vazioComTrim((String)session.getAttribute("cpf"))) {
//	        	this.cpf = (String) session.getAttribute("cpf");
//	        }
//	        if (session.getAttribute("certificado") != null && !Validador.vazioComTrim((String)session.getAttribute("certificado"))) {
//	        	this.certificado = (String) session.getAttribute("certificado");
//	        }
        }

        // - CPF e CERTIFICADO -------------------------------------------------------------------------------------  
//        if (Validador.vazioComTrim(this.cpf)) {
//        	if (Validador.vazioComTrim(this.certificado)) {
//	            errors.add("cpf", new ActionMessage("", "cpf"));
//	            errors.add("certificado", new ActionMessage("", "certificado"));
//	            errors.add("", new ActionMessage("erro.cpf.certificado.vazio", ""));
//        	}
//        } else {
//        	if (!Validador.cpf(Texto.tiraMascaraCpf(this.cpf))) {
//            	campoVazio = true;
//        		errors.add("cpf", new ActionMessage("", "cpf"));
//        	}
//        }

        if (!semMensagemCamposEmVermelho) {
	        if ((validouAlgumCampo && getOpcao().contains("pesquisa")) || errors.size() == 0) {
	        	errors = new ActionErrors();
        	// TODO verificar se todos os new ActionMessage("", ...) são realmente necessários.
	        } else if (errors.size() > 2 || (errors.size() > 1 && errors.get("") == null)) {
	        	if (!getOpcao().contains("pesquisa")) {
	        		errors.add("", new ActionMessage("erro.campoVermelho", ""));
	        	} else {
	        		errors.add("", new ActionMessage("erro.campoVermelho.configuracao", ""));
	        	}
	        } else if (campoVazio) {
	        	if (!getOpcao().contains("pesquisa")) {
	        		errors.add("", new ActionMessage("erro.campoVermelho", ""));
	        	} else {
	        		errors.add("", new ActionMessage("erro.campoVermelho.configuracao", ""));
	        	}
	        }
        }
        
/*        if (Boolean.valueOf(getNovaBusca()).booleanValue()) {
        	if (errors.size() > 0) {
        		
        		
        		
//        		session.removeAttribute("codEmpresa");
//		        if (errors.get("produto").hasNext() == true) {
//		        	session.removeAttribute("idProduto");
//		        }
//		        if (errors.get("sistema").hasNext() == true) {
//		        	session.removeAttribute("idSistema");
//		        }
//		        if (errors.get("cpf").hasNext() == true) {
//		        	session.removeAttribute("cpf");
//		        }
//		        if (errors.get("certificado").hasNext() == true) {
//		        		session.removeAttribute("certificado");
//		        }
        	} else {
//    	        session.removeAttribute("codEmpresa");
//    	        session.removeAttribute("idProduto");
//    	        session.removeAttribute("idSistema");
//    	        session.removeAttribute("cpf");  
//    	        session.removeAttribute("certificado");
        	}
        }*/

        return errors;
    }

}