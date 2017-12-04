package br.com.delphos.web.configuracao;

import java.util.Iterator;
import java.util.List;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteBandeiraDLO;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacao;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacaoDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.empresas.Empresa_;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.produtos.Produto_;
import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.util.Data;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.web.widgets.decoradores.DelphosDecorator;

public class ConfiguracaoDecorator extends DelphosDecorator {
	
	private ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
	private ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
	private EmpresaDLO empresaDLO = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
	private SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:/global/dbsdb/SistemaDLOBean");
	private MeioPagamentoDLO meioPagamentoDLO = (MeioPagamentoDLO) ServiceLocator.lookup("java:/global/dbsdb/MeioPagamentoDLOBean");
	private ProvedorDLO provedorMeioPagamentoDLO = (ProvedorDLO) ServiceLocator.lookup("java:/global/dbsdb/ProvedorDLOBean");
	private ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
	private AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
	private BandeiraDLO bandeiraDLO = (BandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/BandeiraDLOBean");
	private AdquirenteBandeiraDLO adquirenteBandeiraDLO = (AdquirenteBandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteBandeiraDLOBean");
	private RespostaAutorizacaoDLO respostaAutorizacaoDLO = (RespostaAutorizacaoDLO) ServiceLocator.lookup("java:/global/dbsdb/RespostaAutorizacaoDLOBean");
	
	public ConfiguracaoDecorator() {
        this.setFiltro(false);
    }
	
    public String getCodigoMeioPagamento() {
    	MeioPagamento meioPagamento = (MeioPagamento) this.getCurrentRowObject();
    	return meioPagamento.getCodigo();
    }
    
    public String getDescricaoMeioPagamento() {
    	MeioPagamento meioPagamento = (MeioPagamento) this.getCurrentRowObject();
        return meioPagamento.getDescricao();
    }
    
    public String getLimiteDiasRetentativa() {
    	MeioPagamento meioPagamento = (MeioPagamento) this.getCurrentRowObject();
    	return String.valueOf(meioPagamento.getLimiteDiasGerarRetentativa());
    }
    
    public String getAcaoMeioPagamento() throws DLOException {
    	MeioPagamento meioPagamento = (MeioPagamento) this.getCurrentRowObject();
    	boolean permiteAlteracao = meioPagamentoDLO.isEditavel(meioPagamento);
    	boolean permiteExclusao = permiteAlteracao;
        String html = "";
    	if (permiteAlteracao) {
	        html += "<div class='iconeDisplaytag' >";
	        html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+meioPagamento.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	        html += "</div>";
    	}
    	if (permiteExclusao) {
	        html += "<div class='iconeDisplaytag' >";
	        html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+meioPagamento.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	        html += "</div>";
    	}
        return html;

    }
    
    public String getCodigoProvedor() {
    	Provedor provedor = (Provedor) this.getCurrentRowObject();
    	return provedor.getCodigoProvedor();
    }
    
    public String getDescricaoProvedor() {
    	Provedor provedor = (Provedor) this.getCurrentRowObject();
    	return provedor.getDescricaoProvedor();
    }
    
    public String getAcaoProvedorMeioPagamento() throws DLOException {
    	Provedor provedor = (Provedor) this.getCurrentRowObject();
    	boolean permiteAlteracao = provedorMeioPagamentoDLO.isEditavel(provedor);
    	boolean permiteExclusao = permiteAlteracao;
    	String html = "";
		if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+provedor.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
		}
		if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+provedor.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
		}
    	return html;
    	
    }
    
    public String getNomeAdquirente() {
    	Adquirente adquirente = (Adquirente) this.getCurrentRowObject();
    	return adquirente.getNome();
    }  
    
    public String getCodigoAfiliacao() {
    	Adquirente adquirente = (Adquirente) this.getCurrentRowObject();
    	if(adquirente.getCodigoAfiliacaoConciliador() != null){
    		return Integer.toString(adquirente.getCodigoAfiliacaoConciliador());
    	}else{
    		return null;
    	}
    		
    }
    public String getCodigoConvenioAdquirente() {
    	Adquirente adquirente = (Adquirente) this.getCurrentRowObject();
    	return adquirente.getCodigoConvenio();
    }
    
    public String getAcaoAdquirente() throws DLOException {
    	Adquirente adquirente = (Adquirente) this.getCurrentRowObject();
    	String html = "";
    	
    	boolean permiteAlteracao = adquirenteDLO.isEditavel(adquirente);
    	boolean permiteExclusao = permiteAlteracao;
		if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+adquirente.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
		}
		if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+adquirente.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
		}
    	return html;
    	
    }
    
    public String getNomeBandeira() {
    	Bandeira bandeira = (Bandeira) this.getCurrentRowObject();
    	return bandeira.getNomeBandeira();
    }  
    
    public String getCodigoBandeira() {
    	Bandeira bandeira = (Bandeira) this.getCurrentRowObject();
    	return bandeira.getCodigoBandeira();
    }
    
    public String getAcaoBandeira() throws DLOException {
    	Bandeira bandeira = (Bandeira) this.getCurrentRowObject();
    	String html = "";
    	
    	boolean permiteAlteracao = bandeiraDLO.isEditavel(bandeira);
    	
		List<ContratoCobranca> listaContratos = contratoCobrancaDLO.listarPorBandeira(bandeira);
		listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
				ContratoCobranca_.vendas);
		boolean temVenda = verificaVendaPorBandeira(listaContratos);
    	boolean permiteExclusao = !temVenda;
    	
		if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+bandeira.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
		}
		if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+bandeira.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
		}
    	return html;
    	
    }
    
	private boolean verificaVendaPorBandeira(List<ContratoCobranca> listaContratos) throws DLOException {
		boolean temVenda = false;

		for (Iterator<ContratoCobranca> iterator = listaContratos.iterator(); iterator.hasNext();) {
			ContratoCobranca contratoCobranca = (ContratoCobranca) iterator.next();
//			contratoCobranca = contratoCobrancaDLO.completar(contratoCobranca, ContratoCobranca_.vendas.getName());
			if (contratoCobrancaDLO.contarReferencias(contratoCobranca, ContratoCobranca_.vendas) > 0) {
				temVenda = true;
				break;
			}
		}
		return temVenda;
	}
    
    public String getCodigoEmpresaContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return contratoCobranca.getEmpresa().getCodigo();
    }
    
    public String getCodigoMeioPagamentoContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return contratoCobranca.getMeioPagamento().getCodigo();
    }  
    
    public String getCodigoProvedorContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return contratoCobranca.getProvedor().getCodigoProvedor();
    }  
    
    public String getDescricaoContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return contratoCobranca.getDescricaoContrato();
    }  
    
    public String getCodEmpresaProvMeioPagamentoContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return contratoCobranca.getCodigoEmpresaNoProvedor();
    }  
    
    public String getPrazoPagamentoDiasContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return String.valueOf(contratoCobranca.getPrazoPagamento());
    }  
    
    public String getTipoTransacaoProvedorContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return contratoCobranca.getTipoTransacaoProvedor();
    }  
    
    public String getInicioVigenciaContratoCobranca() {
    	br.com.delphos.billing.contratosCobranca.ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return Data.formatar(contratoCobranca.getDataInicioVigencia(), Data.MASCARA_SEM_HORA);
    }  
    
    public String getFimVigenciaContratoCobranca() {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	return Data.formatar(contratoCobranca.getDataFimVigencia(), Data.MASCARA_SEM_HORA);
    }  
    
    public String getAcaoContratoCobranca() throws DLOException {
    	ContratoCobranca contratoCobranca = (ContratoCobranca) this.getCurrentRowObject();
    	boolean permiteAlteracao = contratoCobrancaDLO.isEditavel(contratoCobranca);
    	boolean permiteExclusao = permiteAlteracao 
    			&& (contratoCobrancaDLO.contarReferencias(contratoCobranca, ContratoCobranca_.cobrancas) == 0)
    			&& (contratoCobrancaDLO.contarReferencias(contratoCobranca, ContratoCobranca_.produtos) == 0);
    	String html = "";
    	if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+contratoCobranca.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
    	}
    	if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+contratoCobranca.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
    	}
    	html += "<div class='iconeDisplaytag' >";
    	html += "<img title='Associação Adquirente' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%; width: 16px' class='tooltipBottom' onClick='javascript:associarAdquirente("+contratoCobranca.getId()+");' src=\"../img/icones/estrutura_24x24.png\"/>";
    	html += "</div>";
    	html += "<div class='iconeDisplaytag' >";
    	html += "<img title='Configurar Autorização' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript:configurarAutorizacao("+contratoCobranca.getId()+");' src=\"../img/icones/engrenagem_16x16.png\"/>";
    	html += "</div>";
    	return html;
    	
    }
    
    public String getDescricaoEmpresa() {
    	Empresa empresa = (Empresa) this.getCurrentRowObject();
    	return empresa.getDescricao();
    }
    
    public String getCodigoEmpresaRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return "AIZ";
    }  
    
    public String getCodigoMeioPagamentoRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return "CC";
    }  
    
    public String getCodigoProvedorRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return "BRG";
    }  
    
    public String getCodigoContratoRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return respostaAutorizacao.getContratoCobranca().getId().toString();
    }  
    
    public String getCodigoRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return respostaAutorizacao.getCodigoResposta();
    }  
    
    public String getDescricaoRespostaProvedorRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return respostaAutorizacao.getDescricaoRespostaProvedor();
    }  
    
    public String getDescricaoRespostaConsultaRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return respostaAutorizacao.getDescricaoRespostaConsulta();
    }  
    
    public String getQuantidadeRetentativaRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return String.valueOf(respostaAutorizacao.getQuantidadeRetentativa());
    }  
    
    public String getTipoIntervaloRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return respostaAutorizacao.getTipoIntervalo();
    }  
    
    public String getValorIntervaloRespostaAutorizacao() {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	return String.valueOf(respostaAutorizacao.getValorIntervalo());
    }  
    
    public String getAcaoRespostaAutorizacao() throws DLOException {
    	RespostaAutorizacao respostaAutorizacao = (RespostaAutorizacao) this.getCurrentRowObject();
    	
    	boolean permiteAlteracao = respostaAutorizacaoDLO.isEditavel(respostaAutorizacao);
    	boolean permiteExclusao = permiteAlteracao;
    	
    	String html = "";
    	if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+respostaAutorizacao.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
    	}
    	if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+respostaAutorizacao.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
    	}
    	return html;
    	
    }
    
    public String getCodigoListaValor() {
    	ListaValor listaValor = (ListaValor) this.getCurrentRowObject();
    	return String.valueOf(listaValor.getCodigo());
    }  
    
    public String getDescricaoListaValor() {
    	ListaValor listaValor = (ListaValor) this.getCurrentRowObject();
    	return listaValor.getDescricao();
    }  
    
    public String getTipoDeUsoListaValor() {
    	ListaValor listaValor = (ListaValor) this.getCurrentRowObject();
    	
    	if (listaValor.getTipoUso().equals("S")) 
    		return "SISTEMA";
    	else if (listaValor.getTipoUso().equals("G"))
    		return "GERAL";
    	
    	return "SISTEMA ou GERAL";
    	
    } 
    
    public String getStatusListaValor() {
    	ListaValor listaValor = (ListaValor) this.getCurrentRowObject();
    	
    	if (!Validador.vazio(listaValor.getStatus())) {
    	
	    	if (listaValor.getStatus().equals("A")) 
	    		return "ATIVO";
	    	else if (listaValor.getStatus().equals("I"))
	    		return "INATIVO";
    	
    	}
    	
    	return "";
    }  
    
    public String getAcaoListaValor() {
    	ListaValor listaValor = (ListaValor) this.getCurrentRowObject();
    	String html = "";
    	if (listaValor.getTipoUso().equals("G")) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+listaValor.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
	    	if (listaValor.getStatus().equals("A")) {
		    	html += "<div class='iconeDisplaytag' >";
		    	html += "<img title='Desativar (Ativado)' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarDesativar("+listaValor.getId()+");' src=\"../img/icones/desativar_16x16.png\"/>";
		    	html += "</div>";
	    	} else if (listaValor.getStatus().equals("I")) {
		    	html += "<div class='iconeDisplaytag' >";
		    	html += "<img title='Ativar (Desativado)' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarAtivar("+listaValor.getId()+");' src=\"../img/icones/ativar_16x16.png\"/>";
		    	html += "</div>";	    		
	    	}
//	    	html += "<div class='iconeDisplaytag' >";
//	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%; ' class='tooltipBottom' onClick='javascript: confirmarExclusao("+listaValor.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
//	    	html += "</div>";
    	}
    	html += "<div class='iconeDisplaytag' >";
    	html += "<img title='Associar Item' style='margin-bottom: 2px; cursor: pointer; width: 19px;' class='tooltipBottom' onClick='javascript: associarItemLista("+listaValor.getId()+");' src=\"../img/icones/estrutura_24x24.png\"/>";
    	html += "</div>";
    	return html;
    	
    }
    
    public String getCodigoItemLista() {
    	ItemLista itemLista = (ItemLista) this.getCurrentRowObject();
    	return String.valueOf(itemLista.getCodigo());
    }  
    
    public String getIdListaValorItemLista() {
    	ItemLista itemLista = (ItemLista) this.getCurrentRowObject();
    	return String.valueOf(itemLista.getListaValor().getId());
    }  
    
    public String getDescricaoItemLista() {
    	ItemLista itemLista = (ItemLista) this.getCurrentRowObject();
    	return itemLista.getDescricao();
    }  
    
    public String getStatusItemLista() {
    	ItemLista itemLista = (ItemLista) this.getCurrentRowObject();
    	
    	if (!Validador.vazio(itemLista.getStatus())) {
    	
	    	if (itemLista.getStatus().equals("A")) 
	    		return "ATIVO";
	    	else if (itemLista.getStatus().equals("I"))
	    		return "INATIVO";
    	
    	}
    	
    	return "";
    }  
    
    public String getAcaoItemLista() {
    	ItemLista itemLista = (ItemLista) this.getCurrentRowObject();
    	String html = "";
    	if (itemLista.getListaValor().getTipoUso().equals("G")) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+itemLista.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
	    	if (itemLista.getStatus().equals("A")) {
		    	html += "<div class='iconeDisplaytag' >";
		    	html += "<img title='Desativar (Ativado)' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarDesativar("+itemLista.getId()+");' src=\"../img/icones/desativar_16x16.png\"/>";
		    	html += "</div>";
	    	} else if (itemLista.getStatus().equals("I")) {
		    	html += "<div class='iconeDisplaytag' >";
		    	html += "<img title='Ativar (Desativado)' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarAtivar("+itemLista.getId()+");' src=\"../img/icones/ativar_16x16.png\"/>";
		    	html += "</div>";	    		
	    	}
    	}
//    	html += "<div class='iconeDisplaytag' >";
//    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+itemLista.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
//    	html += "</div>";
    	return html;
    	
    }
    
    public String getCodigoProduto() {
    	br.com.delphos.billing.produtos.Produto produto = (br.com.delphos.billing.produtos.Produto) this.getCurrentRowObject();
    	return produto.getCodigo();
    }  
    
    public String getCodigoEmpresa() {
    	Empresa empresa = (Empresa) this.getCurrentRowObject();
    	return empresa.getCodigo();
    }  
    
    public String getCodigoProdutoEmpresa() {
    	br.com.delphos.billing.produtos.Produto produto = (br.com.delphos.billing.produtos.Produto) this.getCurrentRowObject();
    	return produto.getEmpresa().getCodigo();
    }  
    
    public String getDescricaoProduto() {
    	br.com.delphos.billing.produtos.Produto produto = (br.com.delphos.billing.produtos.Produto) this.getCurrentRowObject();
    	return produto.getDescricao();
    }
    
    public String getNumeroMaximoParcelasProduto() {
    	br.com.delphos.billing.produtos.Produto produto = (br.com.delphos.billing.produtos.Produto) this.getCurrentRowObject();
    	return String.valueOf(produto.getNumeroMaximoParcelas());
    }  
    
    public String getTipoCobrancaProduto() {
    	br.com.delphos.billing.produtos.Produto produto = (br.com.delphos.billing.produtos.Produto) this.getCurrentRowObject();
    	//return TipoCobranca.buscarPorValor(produto.getTipoCobranca().getValor()).toString();
    	switch (Integer.valueOf(TipoCobranca.buscarPorValor(produto.getTipoCobranca().getValor()).getValor())) {
		case 1:
			return "SINGLE PREMIUM À VISTA";
		case 2:
			return "SINGLE PREMIUM PARCELADO";

		default:
			return "SINGLE PREMIUM À VISTA OU PARCELADO";
		}
    	
    }  
    
    public String getAcaoProduto() throws DLOException {
    	br.com.delphos.billing.produtos.Produto produto = (br.com.delphos.billing.produtos.Produto) this.getCurrentRowObject();
//    	produto = produtoDLO.completar(produto, Produto_.vendas);
    	boolean permiteAlteracao = produtoDLO.isEditavel(produto);
    	boolean permiteExclusao = permiteAlteracao 
    			&& (produtoDLO.contarReferencias(produto, Produto_.contratosCobranca) == 0);
    	String html = "";
    	if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+produto.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
    	}
    	if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+produto.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
    	}
    	return html;
    	
    }
    
    
    public String getCodigoSistema() {
    	br.com.delphos.billing.sistemas.Sistema sistema = (br.com.delphos.billing.sistemas.Sistema) this.getCurrentRowObject();
    	return sistema.getCodigo();
    }  
    
    public String getDescricaoSistema() {
    	br.com.delphos.billing.sistemas.Sistema sistema = (br.com.delphos.billing.sistemas.Sistema) this.getCurrentRowObject();
    	return sistema.getDescricao();
    } 
    
    public String getSistemaEnvioInformacoesSistema() {
    	br.com.delphos.billing.sistemas.Sistema sistema = (br.com.delphos.billing.sistemas.Sistema) this.getCurrentRowObject();
    	return sistema.getCodigoSistemaEnvio();
    } 
    
    public String getAcaoSistema() throws DLOException {
    	br.com.delphos.billing.sistemas.Sistema sistema = (br.com.delphos.billing.sistemas.Sistema) this.getCurrentRowObject();
    	
    	boolean permiteAlteracao = sistemaDLO.isEditavel(sistema);
    	boolean permiteExclusao = permiteAlteracao;
    	
    	String html = "";
    	if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+sistema.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
    	}
    	if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+sistema.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
    	}
    	return html;
    	
    }
    
    public String getAcaoEmpresa() throws DLOException {
    	Empresa empresa = (Empresa) this.getCurrentRowObject();
//    	boolean permiteAlteracao = empresaDLO.contarReferencias(empresa, Empresa_.vendas) == 0;
    	boolean permiteAlteracao = empresaDLO.isEditavel(empresa);
    	boolean permiteExclusao = permiteAlteracao 
    			&& (empresaDLO.contarReferencias(empresa, Empresa_.contratosCobranca) == 0)
    			&& (empresaDLO.contarReferencias(empresa, Empresa_.produtos) == 0);
    	String html = "";
    	if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag'>";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' onClick='javascript: exibirDetalhe("+empresa.getId()+");' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
    	}
    	if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag'>";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao("+empresa.getId()+");' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
    	}
    	return html;
    	
    }
    
    public String getAdquirenteAssociacaoAdquirente() {
    	br.com.delphos.billing.adquirentes.AdquirenteBandeira adquirenteBandeira = (br.com.delphos.billing.adquirentes.AdquirenteBandeira) this.getCurrentRowObject();
    	return adquirenteBandeira.getAdquirente().getNome();
    } 
    public String getBandeiraAssociacaoAdquirente() {
    	br.com.delphos.billing.adquirentes.AdquirenteBandeira adquirenteBandeira = (br.com.delphos.billing.adquirentes.AdquirenteBandeira) this.getCurrentRowObject();
    	return adquirenteBandeira.getBandeira().getNomeBandeira();
    } 
    public String getPadraoAssociacaoAdquirente() {
    	br.com.delphos.billing.adquirentes.AdquirenteBandeira adquirenteBandeira = (br.com.delphos.billing.adquirentes.AdquirenteBandeira) this.getCurrentRowObject();
    	
		ListaValor listaValorTipoOperacao;
		try {
			listaValorTipoOperacao = listaValorDLO.obterPorCodigo(TipoListaValor.SimNao.getValor());
			
			List<ItemLista> listItemLista = listaValorTipoOperacao.getItensLista();
			
			for (Iterator iterator = listItemLista.iterator(); iterator
					.hasNext();) {
				ItemLista itemLista = (ItemLista) iterator.next();
				
				if (itemLista.getCodigo().equalsIgnoreCase(adquirenteBandeira.getAdquirentePadrao())) {
					return itemLista.getDescricao();
				}
				
			}
			
		} catch (DLOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

    	
    	return adquirenteBandeira.getAdquirentePadrao();
    } 
    public String getCodigoMetodoPagamento() {
    	br.com.delphos.billing.adquirentes.AdquirenteBandeira adquirenteBandeira = (br.com.delphos.billing.adquirentes.AdquirenteBandeira) this.getCurrentRowObject();
    	return Long.valueOf(adquirenteBandeira.getCodigoMetodoPagamento()).toString();
    } 
    
    public String getAcaoAssociacaoAdquirente() throws DLOException {
    	br.com.delphos.billing.adquirentes.AdquirenteBandeira adquirenteBandeira = (br.com.delphos.billing.adquirentes.AdquirenteBandeira) this.getCurrentRowObject();
    	
    	boolean permiteAlteracao = adquirenteBandeiraDLO.isEditavel(adquirenteBandeira);
    	boolean permiteExclusao = permiteAlteracao;
    	
    	String html = "";
    	if (permiteAlteracao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Editar' style='margin-bottom: 3px; cursor: pointer; margin-right: 5%;' class='tooltipBottom' ";
	    	html		+= "onClick='javascript: exibirDetalhe";
	    	html	+= "(";
	    	// dao.listarPorCriterio(idAdquirente, idBandeira, idContratoCobranca, adquirentePadrao, codigoMetodoPagamento);
	    	html	+= "\"" + adquirenteBandeira.getAdquirente().getId().toString() + "\", ";
	    	html	+= "\"" + adquirenteBandeira.getBandeira().getId().toString() + "\", ";
	    	html	+= "\"" + adquirenteBandeira.getContratoCobranca().getId() + "\", ";
	    	html	+= "\"" + adquirenteBandeira.getAdquirentePadrao() + "\", ";
	    	html	+= "\"" + Long.valueOf(adquirenteBandeira.getCodigoMetodoPagamento()).toString() + "\"";
	    	html	+= ");";
	    	html	+= "' src=\"../img/icones/editar_16x16.png\"/>";
	    	html += "</div>";
    	}
    	if (permiteExclusao) {
	    	html += "<div class='iconeDisplaytag' >";
	    	html += "<img title='Excluir' style='margin-bottom: 3px; cursor: pointer;' class='tooltipBottom' onClick='javascript: confirmarExclusao";
	    	html+= "(";
	    	// dao.listarPorCriterio(idAdquirente, idBandeira, idContratoCobranca, adquirentePadrao, codigoMetodoPagamento);
	    	html+= "\"" + adquirenteBandeira.getAdquirente().getId().toString() + "\", ";
	    	html+= "\"" + adquirenteBandeira.getBandeira().getId().toString() + "\", ";
	    	html+= "\"" + adquirenteBandeira.getContratoCobranca().getId() + "\", ";
	    	html+= "\"" + adquirenteBandeira.getAdquirentePadrao() + "\", ";
	    	html+= "\"" + Long.valueOf(adquirenteBandeira.getCodigoMetodoPagamento()).toString() + "\"";
	    	html+= ");";
	    	html+= "' src=\"../img/icones/lixeira_16x16.png\"/>";
	    	html += "</div>";
    	}
    	return html;
    	
    }
    
}