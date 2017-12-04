package br.com.delphos.web.configuracao;

import java.util.ArrayList;
import java.util.List;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.adquirentes.AdquirenteBandeiraDLO;
import br.com.delphos.billing.adquirentes.AdquirenteDLO;
import br.com.delphos.billing.adquirentes.Adquirente_;
import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.adquirentes.BandeiraDLO;
import br.com.delphos.billing.adquirentes.Bandeira_;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacao;
import br.com.delphos.billing.contratosCobranca.RespostaAutorizacaoDLO;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.empresas.EmpresaDLO;
import br.com.delphos.billing.empresas.Empresa_;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.enumeracoes.TipoListaValor;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.listasValores.ItemLista;
import br.com.delphos.billing.listasValores.ItemListaDLO;
import br.com.delphos.billing.listasValores.ListaValor;
import br.com.delphos.billing.listasValores.ListaValorDLO;
import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.meiosPagamento.MeioPagamentoDLO;
import br.com.delphos.billing.meiosPagamento.MeioPagamento_;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.produtos.Produto_;
import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.provedores.ProvedorDLO;
import br.com.delphos.billing.provedores.Provedor_;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.sistemas.SistemaDLO;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;

public class ConfiguracaoViewHelper {
	
	private MeioPagamentoDLO meioPagamentoDLO = (MeioPagamentoDLO) ServiceLocator.lookup("java:/global/dbsdb/MeioPagamentoDLOBean");
	private ProvedorDLO provedorDLO = (ProvedorDLO) ServiceLocator.lookup("java:/global/dbsdb/ProvedorDLOBean");
	private AdquirenteDLO adquirenteDLO = (AdquirenteDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteDLOBean");
	private BandeiraDLO bandeiraDLO = (BandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/BandeiraDLOBean");
	private ContratoCobrancaDLO contratoCobrancaDLO = (ContratoCobrancaDLO) ServiceLocator.lookup("java:/global/dbsdb/ContratoCobrancaDLOBean");
	private RespostaAutorizacaoDLO respostaAutorizacaoDLO = (RespostaAutorizacaoDLO) ServiceLocator.lookup("java:/global/dbsdb/RespostaAutorizacaoDLOBean");
	private ListaValorDLO listaValorDLO = (ListaValorDLO) ServiceLocator.lookup("java:/global/dbsdb/ListaValorDLOBean");
	private ItemListaDLO itemListaDLO = (ItemListaDLO) ServiceLocator.lookup("java:/global/dbsdb/ItemListaDLOBean");
	private ProdutoDLO produtoDLO = (ProdutoDLO) ServiceLocator.lookup("java:/global/dbsdb/ProdutoDLOBean");
	private SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:/global/dbsdb/SistemaDLOBean");
	private EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/dbsdb/EmpresaDLOBean");
	private AdquirenteBandeiraDLO dloAdquirenteBandeira = (AdquirenteBandeiraDLO) ServiceLocator.lookup("java:/global/dbsdb/AdquirenteBandeiraDLOBean");
	
	private String codigoMeioPagamento;
	private String descricaoMeioPagamento;
	private String limiteDiasRetentativa;
	private String idMeioPagamento;
	private String idProvedorMeioPagamento;
	private String codigoProvedor;
	private String dataUltimaAlteracao;
	private String descricaoProvedor;
	private String idAdquirente;
	private String codigoAfiliacao;
	private String nomeAdquirente;
	private String codigoConvenioAdquirente;
	private String idBandeira;
	private String nomeBandeira;
	private String codigoBandeira;
	private String idContratoCobranca;
	private String idRespostaAutorizacao;
	private String idListaValor;
	private String idItemLista;
	private String idProduto;
	private String idSistema;
	private String codigoEmpresa;
	private String descricaoEmpresa;	
	private String idEmpresa;
	private String codigoProduto;
	private String descricaoProduto;
	private String codigoSistema;
	private String descricaoSistema;
	private String codigoListaValor;
	private String descricaoListaValor;
	private String statusListaValor;
	private String tipoUsoListaValor;
	private String codigoItemLista;
	private String descricaoItemLista;
	private String statusItemLista;
	private String codigoEmpresaContratoCobranca;
	private String codigoProvedorContratoCobranca;
	private String descricaoContratoCobranca;
	private String padraoAssociarContrato;
	private String codigoMetodoPagamento;
	private String temAdquirentePadrao;
	private String codigoRespostaAutorizacao;
	private String descricaoRespostaAutorizacaoProvedor;
	List<Empresa> listEmpresasUsuarioLogado;
	
	private List<AdquirenteBandeira> listarAssociacaoAdquirente = new ArrayList<AdquirenteBandeira>();
	
    public List<MeioPagamento> listarMeioPagamento(){

    	List<MeioPagamento> retorno = null;
    	
//		try {
			
			retorno = meioPagamentoDLO.listar();
			retorno = EntidadeUtils.prepararPropriedades(meioPagamentoDLO, retorno, MeioPagamento_.contratosCobranca);
			
//		} catch (DLOException e) {
//			e.printStackTrace();
//		}
      
//    	List<MeioPagamento> retorno = new ArrayList<MeioPagamento>();
//    	
//    	MeioPagamento meioPagamento = new MeioPagamento();
//    	meioPagamento.setId(1L);
//    	meioPagamento.setCodigo("CC");
//    	meioPagamento.setDescricao("CARTAO DE CREDITO");
//    	meioPagamento.setLimiteDiasGerarRetentativa(5);
//    	meioPagamento.setDataUltimaAlteracao(new Date());
//    	meioPagamento.setUsuario(1L);
//    	retorno.add(meioPagamento);
    	
        return retorno;
    }
    
    public List<MeioPagamento> listarMeioPagamentoPorCriterio() {
    	
    	List<MeioPagamento> retorno = null;
    	
    	if ((getCodigoMeioPagamento() != null && !Validador.vazioComTrim(getCodigoMeioPagamento()))
    			|| (getDescricaoMeioPagamento() != null && !Validador.vazioComTrim(getDescricaoMeioPagamento()))
    			) {
    		
    		retorno = meioPagamentoDLO.listarPorCriterio(codigoMeioPagamento, descricaoMeioPagamento);
			retorno = EntidadeUtils.prepararPropriedades(meioPagamentoDLO, retorno, MeioPagamento_.contratosCobranca);    		
    	}
    	
    	return retorno;
    	
    }
    
    public List<Provedor> listarProvedorMeioPagamento() {
    	List<Provedor> retorno = null;
    	
//		try {
			
			retorno = provedorDLO.listar(); 
			retorno = EntidadeUtils.prepararPropriedades(provedorDLO, retorno, Provedor_.contratosCobranca);
			
//			} catch (DLOException e) {
//			e.printStackTrace();
//		}
      
//    	List<Provedor> retorno = new ArrayList<Provedor>();
//    	Provedor provedor = new Provedor();
//    	provedor.setId(1L);
//    	provedor.setCodigoProvedor("BRG");
//    	provedor.setDescricaoProvedor("BRASPAG");
//    	retorno.add(provedor);
    	
        return retorno;			
    }
    
    public List<Provedor> listarProvedorPorCriterio() {
    	List<Provedor> retorno = null;
    	
    	if ((getCodigoProvedor() != null && !Validador.vazioComTrim(getCodigoProvedor()))
    			|| (getDescricaoProvedor() != null && !Validador.vazioComTrim(getDescricaoProvedor()))
    			) {
    		
    		retorno = provedorDLO.listarPorCriterio(codigoProvedor, descricaoProvedor);
			retorno = EntidadeUtils.prepararPropriedades(provedorDLO, retorno, Provedor_.contratosCobranca);
    		
    	}
    	
    	return retorno;  	
    }
    
    public List<Adquirente> listarAdquirente() {
    	List<Adquirente> retorno = null;
    	
//		try {
			
//			retorno = adquirenteDLO.listar(); 
			retorno = adquirenteDLO.listarAdquirenteOrdenado();
			retorno = EntidadeUtils.prepararPropriedades(adquirenteDLO, retorno, Adquirente_.adquirentesBandeira);
			
//			} catch (DLOException e) {
//			e.printStackTrace();
//		}

 
/*    	List<Adquirente> retorno = new ArrayList<Adquirente>();
    	Adquirente adquirente = new Adquirente();
    	adquirente.setId(1L);
    	adquirente.setNome("CIELO");
    	adquirente.setCodigoConvenio("123");
    	retorno.add(adquirente);
    	adquirente = new Adquirente();
    	adquirente.setId(2L);
    	adquirente.setNome("REDECARD");
    	adquirente.setCodigoConvenio("321");
    	retorno.add(adquirente);*/
    	
        return retorno;			
    }
    
    public List<Adquirente> listarAdquirentePorCriterio() {
    	List<Adquirente> retorno = null;
    	if ((getCodigoConvenioAdquirente() != null && !Validador.vazioComTrim(getCodigoConvenioAdquirente()))
    			|| (getNomeAdquirente() != null && !Validador.vazioComTrim(getNomeAdquirente()))
    			|| (getCodigoAfiliacao() != null && !Validador.vazioComTrim(getCodigoAfiliacao()))) {
    		retorno = adquirenteDLO.listarPorCriterio(codigoConvenioAdquirente, nomeAdquirente, codigoAfiliacao); 
			retorno = EntidadeUtils.prepararPropriedades(adquirenteDLO, retorno, Adquirente_.adquirentesBandeira);
    	}
    	return retorno;
    }
    
    public List<Bandeira> listarBandeira() {
    	List<Bandeira> retorno = null;
    	
//		try {
    	
    	retorno = bandeiraDLO.listar(); 
		retorno = EntidadeUtils.prepararPropriedades(bandeiraDLO, retorno, Bandeira_.adquirentesBandeira);
		
//			} catch (DLOException e) {
//			e.printStackTrace();
//		}
    	
//    	List<Bandeira> retorno = new ArrayList<Bandeira>();
//    	Bandeira bandeira = new Bandeira();
//    	bandeira.setId(1L);
//    	bandeira.setCodigoBandeira("01");
//    	bandeira.setNomeBandeira("VISA");
//    	retorno.add(bandeira);
//    	bandeira = new Bandeira();
//    	bandeira.setId(2L);
//    	bandeira.setCodigoBandeira("02");
//    	bandeira.setNomeBandeira("MASTERCARD");
//    	retorno.add(bandeira);   	
//    	bandeira = new Bandeira();
//    	bandeira.setId(6L);
//    	bandeira.setCodigoBandeira("06");
//    	bandeira.setNomeBandeira("HIPERCARD");
//    	retorno.add(bandeira);   	
//    	bandeira = new Bandeira();
//    	bandeira.setId(7L);
//    	bandeira.setCodigoBandeira("07");
//    	bandeira.setNomeBandeira("AMEX");
//    	retorno.add(bandeira);   	
    	
    	return retorno;			
    }
    
	public List<Bandeira> listarBandeirasPorCriterio() {
		List<Bandeira> retorno = null;
		if ((getCodigoBandeira() != null && !Validador.vazioComTrim(getCodigoBandeira()))
				|| (getNomeBandeira() != null && !Validador.vazioComTrim(getNomeBandeira()))
				) {
			retorno = bandeiraDLO.listarPorCriterio(codigoBandeira, nomeBandeira);
			retorno = EntidadeUtils.prepararPropriedades(bandeiraDLO, retorno, Bandeira_.adquirentesBandeira);
		}
		return retorno;
	}
    
    public List<ContratoCobranca> listarContratoCobranca() {
    	List<ContratoCobranca> retorno = null;
    	
//		try {
    	
    	retorno = contratoCobrancaDLO.listar(); 
    	
//			} catch (DLOException e) {
//			e.printStackTrace();
//		}
    	
    	
/*    	List<ContratoCobranca> retorno = new ArrayList<ContratoCobranca>();
    	ContratoCobranca contratoCobranca = new ContratoCobranca();
    	contratoCobranca.setId(1L);
    	contratoCobranca.setDescricaoContrato("PORTAL PU A VISTA");
    	contratoCobranca.setCodigoEmpresaNoProvedor("87D423F9-93F2-E411-9408-0026B939D54B");
    	contratoCobranca.setPrazoPagamento(5);
    	contratoCobranca.setProvedor(listarProvedorMeioPagamento().get(0));
    	contratoCobranca.setMeioPagamento(listarMeioPagamento().get(0));
    	contratoCobranca.setEmpresa(listarEmpresa().get(0));
    	contratoCobranca.setDataInicioVigencia(new Date());
    	contratoCobranca.setDataFimVigencia(new Date());
    	contratoCobranca.setTipoTransacaoProvedor("2");
    	retorno.add(contratoCobranca);*/
    	
    	return retorno;			
    }
    
    public List<ContratoCobranca> listarContratoCobrancaPorCriterio() {
    	List<ContratoCobranca> retorno = null;
    	if ((getCodigoEmpresaContratoCobranca() != null && !Validador.vazioComTrim(getCodigoEmpresaContratoCobranca()))
    			|| (getCodigoProvedorContratoCobranca() != null && !Validador.vazioComTrim(getCodigoProvedorContratoCobranca()))
    			|| (getDescricaoContratoCobranca() != null && !Validador.vazioComTrim(getDescricaoContratoCobranca()))
    		) {
    		
    		retorno = contratoCobrancaDLO.listarPorCriterio(codigoEmpresaContratoCobranca, codigoProvedorContratoCobranca, descricaoContratoCobranca, "", "", "");
    		
    	}
    	return retorno;    	
    }
    
    public List<RespostaAutorizacao> listarRespostaAutorizacao() {
    	List<RespostaAutorizacao> retorno = null;
    	
//		try {
    	
    	retorno = respostaAutorizacaoDLO.listar(); 
    	
//			} catch (DLOException e) {
//			e.printStackTrace();
//		}
    	
    	
/*    	List<RespostaAutorizacao> retorno = new ArrayList<RespostaAutorizacao>();
    	
    	RespostaAutorizacao respostaAutorizacao = new RespostaAutorizacao();
    	respostaAutorizacao.setId(3L);
    	respostaAutorizacao.setCodigoResposta("ABCDE");
    	respostaAutorizacao.setDescricaoRespostaConsulta("DESCRIÇÃO TESTE RESPOSTA");
    	respostaAutorizacao.setDescricaoRespostaProvedor("DESCRIÇÃO TESTE AUTORIZADO 2");
    	respostaAutorizacao.setQuantidadeRetentativa(3);
    	respostaAutorizacao.setTipoIntervalo("DIAS");
    	respostaAutorizacao.setValorIntervalo(2);
    	respostaAutorizacao.setContratoCobranca(listarContratoCobranca().get(0));
    	retorno.add(respostaAutorizacao);
    	respostaAutorizacao = new RespostaAutorizacao();
    	respostaAutorizacao.setId(4L);
    	respostaAutorizacao.setCodigoResposta("FGHIJ");
    	respostaAutorizacao.setDescricaoRespostaConsulta("DESCRIÇÃO TESTE RESPOSTA 2");
    	respostaAutorizacao.setDescricaoRespostaProvedor("DESCRIÇÃO TESTE AUTORIZADO 3");
    	respostaAutorizacao.setQuantidadeRetentativa(4);
    	respostaAutorizacao.setTipoIntervalo("HORAS");
    	respostaAutorizacao.setValorIntervalo(3);
    	respostaAutorizacao.setContratoCobranca(listarContratoCobranca().get(0));
    	retorno.add(respostaAutorizacao);*/
    	
    	return retorno;			
    }
    
    public List<RespostaAutorizacao> listarRespostaAutorizacaoPorCriterio() {
    	List<RespostaAutorizacao> retorno = null;
    	if ((getIdContratoCobranca() != null && !Validador.vazioComTrim(getIdContratoCobranca()))
    			|| (getCodigoRespostaAutorizacao() != null && !Validador.vazioComTrim(getCodigoRespostaAutorizacao()))
    			|| (getDescricaoRespostaAutorizacaoProvedor() != null && !Validador.vazioComTrim(getDescricaoRespostaAutorizacaoProvedor()))
//    			|| (getDescricaoRespostaAutorizacaoConsulta() != null && !Validador.vazioComTrim(getDescricaoRespostaAutorizacaoConsulta()))
//    			|| (getQuantidadeRetentativas() != null && !Validador.vazioComTrim(getQuantidadeRetentativas()))
//    			|| (getTipoIntervaloRetentativas() != null && !Validador.vazioComTrim(getTipoIntervaloRetentativas()))
    		) {
    		
    		retorno = respostaAutorizacaoDLO.listarPorCriterio(idContratoCobranca, null, null);
    		
    	}
    	return retorno;    	
    }
    
    public List<ListaValor> listarListaValor() {
    	List<ListaValor> retorno = null;
    	
    	retorno = listaValorDLO.listar(); 
    	
/*    	List<ListaValor> retorno = new ArrayList<ListaValor>();
    	ListaValor listaValor = new ListaValor();
    	listaValor.setId(1L);
    	listaValor.setCodigo("TPCOB");
    	listaValor.setDescricao("Tipo de Cobrança");
    	listaValor.setTipoUso("S");
    	listaValor.setStatus("A");
    	retorno.add(listaValor);
    	listaValor.setId(2L);
    	listaValor.setCodigo("TPEVT");
    	listaValor.setDescricao("TIPO DE EVENTOS DE HISTÓRICO");
    	listaValor.setTipoUso("S");
    	listaValor.setStatus("A");
    	retorno.add(listaValor);
    	listaValor.setId(3L);
    	listaValor.setCodigo("MOTCAN");
    	listaValor.setDescricao("MOTIVO DE CANCELAMENTO");
    	listaValor.setTipoUso("S");
    	listaValor.setStatus("A");
    	retorno.add(listaValor);
    	listaValor.setId(1L);
    	listaValor.setCodigo("TPSTA");
    	listaValor.setDescricao("TIPOS DE STATUS DE VENDA");
    	listaValor.setTipoUso("S");
    	listaValor.setStatus("A");
    	retorno.add(listaValor);*/
    	
    	return retorno;			
    }
    
    public List<ListaValor> listarListaValorPorCriterio () {
    	List<ListaValor> retorno = null;
		if ((getCodigoListaValor() != null && !Validador.vazioComTrim(getCodigoListaValor()))
				|| (getDescricaoListaValor() != null && !Validador.vazioComTrim(getDescricaoListaValor()))
				|| (getCodigoEmpresa() != null && !Validador.vazioComTrim(getCodigoEmpresa()))
				) {
				
				retorno = listaValorDLO.listarPorCriterio(codigoListaValor, descricaoListaValor, statusListaValor, tipoUsoListaValor);
			
		}
		return retorno;
    }
    
    public List<ItemLista> listarItemListaPorCriterio () {
    	List<ItemLista> retorno = null;
    	if ((getCodigoItemLista() != null && !Validador.vazioComTrim(getCodigoItemLista()))
    			|| (getDescricaoItemLista() != null && !Validador.vazioComTrim(getDescricaoItemLista()))
    			|| (getIdListaValor() != null && !Validador.vazioComTrim(getIdListaValor()))
    		|| (getStatusItemLista() != null && !Validador.vazioComTrim(getStatusItemLista()))) {
    		
    		retorno = itemListaDLO.listarItemListaPorCriterio(codigoItemLista, descricaoItemLista, idListaValor, statusItemLista);
    		
    	}
    	return retorno;
    }
    
    public List<ItemLista> listarItemLista() {
    	List<ItemLista> retorno = null;
    	
//		try {
    	
    	retorno = itemListaDLO.listar(); 
    	
//			} catch (DLOException e) {
//			e.printStackTrace();
//		}
    	
/*    	List<ItemLista> retorno = new ArrayList<ItemLista>();
    	ItemLista itemLista = new ItemLista();
    	itemLista.setId(1L);
    	itemLista.setCodigo("001");
    	itemLista.setDescricao("SINGLE PREMIUM A VISTA");
    	itemLista.setListaValor(listarListaValor().get(0));
    	retorno.add(itemLista);
    	itemLista = new ItemLista();
    	itemLista.setId(2L);
    	itemLista.setCodigo("002");
    	itemLista.setDescricao("SINGLE PREMIUM PARCELAMENTO LOJA");
    	itemLista.setListaValor(listarListaValor().get(0));
    	retorno.add(itemLista);
    	itemLista = new ItemLista();
    	itemLista.setId(3L);
    	itemLista.setCodigo("003");
    	itemLista.setDescricao("SINGLE PREMIUM PARCELAMENTO CARTÃO");
    	itemLista.setListaValor(listarListaValor().get(0));
    	retorno.add(itemLista);*/
    	
    	
    	
    	return retorno;			
    }
    
    public List<Produto> listarProdutosPorCriterio () {
    	List<Produto> retorno = null;
		if (((getCodigoProduto() != null && !Validador.vazioComTrim(getCodigoProduto()))
				|| (getDescricaoProduto() != null && !Validador.vazioComTrim(getDescricaoProduto())))
				) {
				String codigoEmpresaParaPesquisa = codigoEmpresa != null && !codigoEmpresa.isEmpty() ? codigoEmpresa : null;
				retorno = produtoDLO.listarProdutosPorCriterio(codigoProduto, descricaoProduto, codigoEmpresaParaPesquisa);
				retorno = EntidadeUtils.prepararPropriedades(produtoDLO, retorno, Produto_.contratosCobranca.getName());
			
		}
		return retorno;
    }
    
    public List<Produto> listarProduto() {
    	List<Produto> retorno = null;
//    	List<Produto> retorno = new ArrayList<Produto>();
    	
    	
			retorno = produtoDLO.listar(); 
			retorno = EntidadeUtils.prepararPropriedades(produtoDLO, retorno, Produto_.contratosCobranca.getName());
    	
    	
/*    	private Long id;

    	private String codigo;

    	@Column(name="CODIGO_EMPRESA_VENDA")
    	private String codigoEmpresaVenda;

    	@Column(name="CODIGO_SISTEMA_VENDA")
    	private String codigoSistemaVenda;

    	@Temporal(TemporalType.DATE)
    	@Column(name="DATA_ULTIMA_ALTERACAO")
    	private Date dataUltimaAlteracao;

    	private String descricao;

    	@Column(name="NUMERO_MAXIMO_PARCELAS")
    	private int numeroMaximoParcelas;

    	@Column(name="TIPO_COBRANCA")
    	private String tipoCobranca;*/
    	
//    	Empresa empresa = new Empresa();
//    	empresa.setId(1L);
//    	empresa.setCodigo("AIZ");
//    	empresa.setDescricao("ASSURANT");
//    	empresa.setUsuario(1L);
//    	empresa.setDataUltimaAlteracao(new Date());
    	
/*    	Produto produto = new Produto();
    	produto.setId(1L);
    	produto.setCodigo("BMS");
    	produto.setDescricao("BEM MAIS SEGURO");
    	produto.setCodigoEmpresaVenda("PORTA");
    	produto.setCodigoSistemaVenda("PORTA");
    	produto.setNumeroMaximoParcelas(12);
    	produto.setUsuario(1L);
    	produto.setDataUltimaAlteracao(new Date());
    	produto.setTipoCobranca(TipoCobranca.buscarPorValor("002"));
    	produto.setEmpresa(listarEmpresa().get(0));
    	
    	retorno.add(produto);*/
    	
    	return retorno;			
    }
    
    public List<AdquirenteBandeira> listarAssociacaoAdquirente() {
    	List<AdquirenteBandeira> retorno = new ArrayList<AdquirenteBandeira>();
//    	
//    	for (int i = 0; i < 2; i++) {
//    		AssociacaoAdquirente associacaoAdquirente = new AssociacaoAdquirente();
//        	associacaoAdquirente.setAdquirente(listarAdquirente().get(i).getNome());
//        	associacaoAdquirente.setBandeira(listarBandeira().get(i).getNomeBandeira());
//        	associacaoAdquirente.setPadrao("SIM");
//        	associacaoAdquirente.setCodigoMetodoPagamento("1234");
//    		
//    		retorno.add(associacaoAdquirente);
//    		
//		}
    	
    	if (listarAssociacaoAdquirente.isEmpty() ) {
    		
    		if (idContratoCobranca != null && !Validador.vazioComTrim(idContratoCobranca)) {
    	
    			retorno = dloAdquirenteBandeira.listarPorContrato(contratoCobrancaDLO.obter(Long.valueOf(idContratoCobranca)));
    		
    		}
    		// && !Validador.vazioComTrim(idBandeira)
    		//retorno = dloAdquirenteBandeira.listarPorCriterio("", idBandeira, idContratoCobranca, "", "");
    	
    	} else {
    		retorno.clear();
    		retorno.addAll(listarAssociacaoAdquirente);
    	}
    	
    	// verificando se há alguma associação padrão...
    	
/*    	for (Iterator iterator = retorno.iterator(); iterator.hasNext();) {
			AdquirenteBandeira adquirenteBandeira = (AdquirenteBandeira) iterator
					.next();
			
			if (adquirenteBandeira.getAdquirentePadrao().equalsIgnoreCase("S")) {
				temAdquirentePadrao = "true";
				break;
			}
			
		}*/
    	
    	return retorno;
    	
    }
    
    public TipoCobranca[] listarTipoCobranca() { 
    	TipoCobranca[] arrTipoCobranca = TipoCobranca.values();
    	return arrTipoCobranca;
    }
    
    public List<Sistema> listarSistema() {
    	List<Sistema> retorno = null;
    	
		retorno = sistemaDLO.listar(); 
    	
//    	List<Sistema> retorno = new ArrayList<Sistema>();
//    	
//    	Sistema sistema = new Sistema();
//    	sistema.setId(1L);
//    	sistema.setCodigo("PYB1");
//    	sistema.setDescricao("SISTEMA PYB");
//    	sistema.setCodigoSistemaEnvio("ACSEL");
//    	sistema.setUsuario(1L);
//    	sistema.setDataUltimaAlteracao(new Date());
//    	Produto produto = listarProduto().get(0); 
//    	sistema.setProduto(produto);
//    	retorno.add(sistema);
    	
    	return retorno;			
    }
    
    public List<Sistema> listarSistemasPorCriterio() {
    	
    	List<Sistema> retorno = null;
    	
		if ((getCodigoSistema() != null && !Validador.vazioComTrim(getCodigoSistema()))
				|| (getDescricaoSistema() != null && !Validador.vazioComTrim(getDescricaoSistema()))
				) {
    	
			retorno = sistemaDLO.listarPorCriterio(codigoSistema, descricaoSistema);
		
		}
		
		return retorno;
    	
    }
    
    public List<Empresa> listarEmpresa() {
    	List<Empresa> retorno = null;
    	
    		retorno = dloEmpresa.listar();
			retorno = EntidadeUtils.prepararPropriedades(dloEmpresa, retorno, Empresa_.produtos.getName(), Empresa_.contratosCobranca.getName());
    	
    	return retorno;			
    }
    public List<Empresa> listarEmpresaPorCriterio() {
    	List<Empresa> retorno = null;
//    	List<Empresa> listEmpresasSCAResultado = null;
//    	List<Empresa> listEmpresasResultado = null;
    	
    	if ((getCodigoEmpresa() != null && !Validador.vazioComTrim(getCodigoEmpresa()))
    			|| (getDescricaoEmpresa() != null && !Validador.vazioComTrim(getDescricaoEmpresa()))) {
    		
    		try {
    			/*     			
    			List<Empresa> listEmpresas = new ArrayList<Empresa>();
    			listEmpresas.addAll(listEmpresasUsuarioLogado);
   			
    			listEmpresasSCAResultado = new ArrayList<Empresa>();
    			listEmpresasResultado = new ArrayList<Empresa>();
    			Empresa empresa = new Empresa();*/
    			
//    			retorno = dloEmpresa.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa, listEmpresas);
    			retorno = dloEmpresa.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa);
    			retorno = EntidadeUtils.prepararPropriedades(dloEmpresa, retorno, Empresa_.produtos.getName(), Empresa_.contratosCobranca.getName());
    			
/*    			for (Iterator iterator = listEmpresas.iterator(); iterator
						.hasNext();) {
    				Empresa empresaSCA = (Empresa) iterator.next();
					if (empresaSCA.getCodigo().contains(codigoEmpresa) || empresaSCA.getDescricao().contains(descricaoEmpresa)) {
						empresa = dloEmpresa.obter(empresaSCA.getId());
						listEmpresasResultado.add(empresa);
					}
				}*/
    			
    		} catch (DLOException e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	return retorno;
//    	return listEmpresasResultado;			
    }
    
    public List<ItemLista> listarSimNao() {
    	
    	List<ItemLista> listItemLista;
		try {
			listItemLista = listaValorDLO.obterPorCodigo(TipoListaValor.SimNao.getValor()).getItensLista();
	    	return listItemLista;
		} catch (DLOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return null;
    }
    
	public MeioPagamentoDLO getMeioPagamentoDLO() {
		return meioPagamentoDLO;
	}

	public void setMeioPagamentoDLO(MeioPagamentoDLO meioPagamentoDLO) {
		this.meioPagamentoDLO = meioPagamentoDLO;
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

	public String getIdProvedorMeioPagamento() {
		return idProvedorMeioPagamento;
	}

	public void setIdProvedorMeioPagamento(String idProvedorMeioPagamento) {
		this.idProvedorMeioPagamento = idProvedorMeioPagamento;
	}

	public ProvedorDLO getProvedorDLO() {
		return provedorDLO;
	}

	public void setProvedorDLO(ProvedorDLO provedorDLO) {
		this.provedorDLO = provedorDLO;
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

	public AdquirenteDLO getAdquirenteDLO() {
		return adquirenteDLO;
	}

	public void setAdquirenteDLO(AdquirenteDLO adquirenteDLO) {
		this.adquirenteDLO = adquirenteDLO;
	}

	public String getCodigoAfiliacao() {
		return codigoAfiliacao;
	}

	public void setCodigoAfiliacao(String codigoAfiliacao) {
		this.codigoAfiliacao = codigoAfiliacao;
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

	public BandeiraDLO getBandeiraDLO() {
		return bandeiraDLO;
	}

	public void setBandeiraDLO(BandeiraDLO bandeiraDLO) {
		this.bandeiraDLO = bandeiraDLO;
	}

	public String getIdBandeira() {
		return idBandeira;
	}

	public void setIdBandeira(String idBandeira) {
		this.idBandeira = idBandeira;
	}

	public String getNomeBandeira() {
		return nomeBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		this.nomeBandeira = nomeBandeira;
	}

	public String getCodigoBandeira() {
		return codigoBandeira;
	}

	public void setCodigoBandeira(String codigoBandeira) {
		this.codigoBandeira = codigoBandeira;
	}

	public ContratoCobrancaDLO getContratoCobrancaDLO() {
		return contratoCobrancaDLO;
	}

	public void setContratoCobrancaDLO(ContratoCobrancaDLO contratoCobrancaDLO) {
		this.contratoCobrancaDLO = contratoCobrancaDLO;
	}

	public String getIdContratoCobranca() {
		return idContratoCobranca;
	}

	public void setIdContratoCobranca(String idContratoCobranca) {
		this.idContratoCobranca = idContratoCobranca;
	}

	public RespostaAutorizacaoDLO getRespostaAutorizacaoDLO() {
		return respostaAutorizacaoDLO;
	}

	public void setRespostaAutorizacaoDLO(
			RespostaAutorizacaoDLO respostaAutorizacaoDLO) {
		this.respostaAutorizacaoDLO = respostaAutorizacaoDLO;
	}

	public String getIdRespostaAutorizacao() {
		return idRespostaAutorizacao;
	}

	public void setIdRespostaAutorizacao(String idRespostaAutorizacao) {
		this.idRespostaAutorizacao = idRespostaAutorizacao;
	}

	public ListaValorDLO getListaValorDLO() {
		return listaValorDLO;
	}

	public void setListaValorDLO(ListaValorDLO listaValorDLO) {
		this.listaValorDLO = listaValorDLO;
	}

	public String getIdListaValor() {
		return idListaValor;
	}

	public void setIdListaValor(String idListaValor) {
		this.idListaValor = idListaValor;
	}

	public ItemListaDLO getItemListaDLO() {
		return itemListaDLO;
	}

	public void setItemListaDLO(ItemListaDLO itemListaDLO) {
		this.itemListaDLO = itemListaDLO;
	}

	public String getIdItemLista() {
		return idItemLista;
	}

	public void setIdItemLista(String idItemLista) {
		this.idItemLista = idItemLista;
	}

	public ProdutoDLO getProdutoDLO() {
		return produtoDLO;
	}

	public void setProdutoDLO(ProdutoDLO produtoDLO) {
		this.produtoDLO = produtoDLO;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public SistemaDLO getSistemaDLO() {
		return sistemaDLO;
	}

	public void setSistemaDLO(SistemaDLO sistemaDLO) {
		this.sistemaDLO = sistemaDLO;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
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

	public EmpresaDLO getDloEmpresa() {
		return dloEmpresa;
	}

	public void setDloEmpresa(EmpresaDLO dloEmpresa) {
		this.dloEmpresa = dloEmpresa;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public String getStatusListaValor() {
		return statusListaValor;
	}

	public void setStatusListaValor(String statusListaValor) {
		this.statusListaValor = statusListaValor;
	}

	public String getTipoUsoListaValor() {
		return tipoUsoListaValor;
	}

	public void setTipoUsoListaValor(String tipoUsoListaValor) {
		this.tipoUsoListaValor = tipoUsoListaValor;
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

	public String getCodigoEmpresaContratoCobranca() {
		return codigoEmpresaContratoCobranca;
	}

	public void setCodigoEmpresaContratoCobranca(
			String codigoEmpresaContratoCobranca) {
		this.codigoEmpresaContratoCobranca = codigoEmpresaContratoCobranca;
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

	public String getPadraoAssociarContrato() {
		return padraoAssociarContrato;
	}

	public void setPadraoAssociarContrato(String padraoAssociarContrato) {
		this.padraoAssociarContrato = padraoAssociarContrato;
	}

	public String getCodigoMetodoPagamento() {
		return codigoMetodoPagamento;
	}

	public void setCodigoMetodoPagamento(String codigoMetodoPagamento) {
		this.codigoMetodoPagamento = codigoMetodoPagamento;
	}

	public String getTemAdquirentePadrao() {
		
		if (temAdquirentePadrao == null) {
			listarAssociacaoAdquirente = listarAssociacaoAdquirente();
		}
		
		return temAdquirentePadrao;
	}

	public void setTemAdquirentePadrao(String temAdquirentePadrao) {
		this.temAdquirentePadrao = temAdquirentePadrao;
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

	public List<Empresa> getListEmpresasUsuarioLogado() {
		return listEmpresasUsuarioLogado;
	}

	public void setListEmpresasUsuarioLogado(List<Empresa> listEmpresasUsuarioLogado) {
		this.listEmpresasUsuarioLogado = listEmpresasUsuarioLogado;
	} 
}