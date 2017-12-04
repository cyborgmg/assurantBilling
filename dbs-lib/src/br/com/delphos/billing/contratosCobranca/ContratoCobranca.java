package br.com.delphos.billing.contratosCobranca;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.adquirentes.AdquirenteBandeira;
import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.meiosPagamento.MeioPagamento;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.provedores.Provedor;
import br.com.delphos.billing.util.DateUtils;
import br.com.delphos.billing.vendas.Venda;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CONTRATO_COBRANCA database table.
 * 
 */
@Entity
@Table(name="CONTRATO_COBRANCA")
@NamedQuery(name="ContratoCobranca.findAll", query="SELECT c FROM ContratoCobranca c")
@SequenceGenerator(sequenceName = "SEQ_ID_CONTRATO_COBRANCA", name = "SEQ_ID_CONTRATO_COBRANCA", allocationSize = 1)
public class ContratoCobranca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_CONTRATO_COBRANCA")
	@Column(name="ID_CONTRATO_COBRANCA")
	private Long id;

	@Column(name="CODIGO_EMPRESA_NO_PROVEDOR")
	private String codigoEmpresaNoProvedor;
	
	@Column(name = "CODIGO_EMPRESA_CONCILIADOR")
	private Integer codigoEmpresaConciliador;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM_VIGENCIA")
	private Date dataFimVigencia;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO_VIGENCIA")
	private Date dataInicioVigencia;

	@Column(name="DESCRICAO_CONTRATO")
	private String descricaoContrato;

	@Column(name="PRAZO_PAGAMENTO")
	private int prazoPagamento;

	@Column(name="TIPO_TRANSACAO_PROVEDOR")
	private String tipoTransacaoProvedor;

	@Column(name = "VERSAO_CONTRATO_WS")
	private String versaoContratoWs;
	
	//bi-directional many-to-one association to AdquirenteBandeira
	@OneToMany(mappedBy="contratoCobranca", orphanRemoval = true)
	private List<AdquirenteBandeira> adquirentesBandeiras;

	//bi-directional many-to-one association to Cobranca
	@OneToMany(mappedBy="contratoCobranca")
	private List<Cobranca> cobrancas;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA")
	private Empresa empresa;

	//bi-directional many-to-one association to MeioPagamento
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_MEIO_PAGAMENTO")
	private MeioPagamento meioPagamento;

	//bi-directional many-to-one association to Provedor
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_PROVEDOR")
	private Provedor provedor;

	//bi-directional many-to-many association to Produto
	@ManyToMany
	@JoinTable(
		name="PRODUTO_CONTRATO_COBRANCA"
		, joinColumns={
			@JoinColumn(name="ID_CONTRATO_COBRANCA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PRODUTO")
			}
		)
	private List<Produto> produtos;

	//bi-directional many-to-one association to RespostaAutorizacao
	@OneToMany(mappedBy="contratoCobranca", orphanRemoval = true)
	private List<RespostaAutorizacao> respostasAutorizacao;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="contratoCobranca")
	private List<Venda> vendas;

	public ContratoCobranca() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoEmpresaNoProvedor() {
		return this.codigoEmpresaNoProvedor;
	}

	public void setCodigoEmpresaNoProvedor(String codigoEmpresaNoProvedor) {
		this.codigoEmpresaNoProvedor = codigoEmpresaNoProvedor;
	}

	public Integer getCodigoEmpresaConciliador() {
		return codigoEmpresaConciliador;
	}

	public void setCodigoEmpresaConciliador(Integer codigoEmpresaConciliador) {
		this.codigoEmpresaConciliador = codigoEmpresaConciliador;
	}

	public Date getDataFimVigencia() {
		return this.dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public Date getDataInicioVigencia() {
		return this.dataInicioVigencia;
	}

	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public String getDescricaoContrato() {
		return this.descricaoContrato;
	}

	public void setDescricaoContrato(String descricaoContrato) {
		this.descricaoContrato = descricaoContrato;
	}

	public int getPrazoPagamento() {
		return this.prazoPagamento;
	}

	public void setPrazoPagamento(int prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}

	public String getTipoTransacaoProvedor() {
		return this.tipoTransacaoProvedor;
	}

	public void setTipoTransacaoProvedor(String tipoTransacaoProvedor) {
		this.tipoTransacaoProvedor = tipoTransacaoProvedor;
	}

	public List<AdquirenteBandeira> getAdquirentesBandeiras() {
		return this.adquirentesBandeiras;
	}

	public void setAdquirentesBandeiras(List<AdquirenteBandeira> adquirentesBandeiras) {
		this.adquirentesBandeiras = adquirentesBandeiras;
	}

	public AdquirenteBandeira addAdquirentesBandeira(AdquirenteBandeira adquirentesBandeira) {
		getAdquirentesBandeiras().add(adquirentesBandeira);
		adquirentesBandeira.setContratoCobranca(this);

		return adquirentesBandeira;
	}

	public AdquirenteBandeira removeAdquirentesBandeira(AdquirenteBandeira adquirentesBandeira) {
		getAdquirentesBandeiras().remove(adquirentesBandeira);
		adquirentesBandeira.setContratoCobranca(null);

		return adquirentesBandeira;
	}

	public List<Cobranca> getCobrancas() {
		return this.cobrancas;
	}

	public void setCobrancas(List<Cobranca> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public Cobranca addCobranca(Cobranca cobranca) {
		getCobrancas().add(cobranca);
		cobranca.setContratoCobranca(this);

		return cobranca;
	}

	public Cobranca removeCobranca(Cobranca cobranca) {
		getCobrancas().remove(cobranca);
		cobranca.setContratoCobranca(null);

		return cobranca;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public MeioPagamento getMeioPagamento() {
		return this.meioPagamento;
	}

	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public Provedor getProvedor() {
		return this.provedor;
	}

	public void setProvedor(Provedor provedor) {
		this.provedor = provedor;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<RespostaAutorizacao> getRespostasAutorizacao() {
		return this.respostasAutorizacao;
	}

	public void setRespostasAutorizacao(List<RespostaAutorizacao> respostasAutorizacao) {
		this.respostasAutorizacao = respostasAutorizacao;
	}

	public RespostaAutorizacao addRespostasAutorizacao(RespostaAutorizacao respostasAutorizacao) {
		getRespostasAutorizacao().add(respostasAutorizacao);
		respostasAutorizacao.setContratoCobranca(this);

		return respostasAutorizacao;
	}

	public RespostaAutorizacao removeRespostasAutorizacao(RespostaAutorizacao respostasAutorizacao) {
		getRespostasAutorizacao().remove(respostasAutorizacao);
		respostasAutorizacao.setContratoCobranca(null);

		return respostasAutorizacao;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setContratoCobranca(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setContratoCobranca(null);

		return venda;
	}

	public String getVersaoContratoWs() {
		return versaoContratoWs;
	}

	public void setVersaoContratoWs(String versaoContratoWs) {
		this.versaoContratoWs = versaoContratoWs;
	}

	public boolean isAposInicioVigencia(Date dataAtual) {
		boolean vigente = false;
		if (this.dataInicioVigencia != null && dataAtual != null) {
			vigente = dataAtual.compareTo(this.dataInicioVigencia) >= 0;
		}
		return vigente;
	}
	
	public boolean isAposInicioVigencia() {
		return isAposInicioVigencia(DateUtils.getDataHoraAtual());
	}
	
	public boolean isAntesFimVigencia(Date dataAtual) {
		boolean vigente = false;
		if (this.dataFimVigencia != null && dataAtual != null) {
			vigente = dataAtual.compareTo(this.dataFimVigencia) <= 0;
		}
		return vigente;
	}

	public boolean isAntesFimVigencia() {
		return isAntesFimVigencia(DateUtils.getDataHoraAtual());
	}
	
	public boolean isVigente() {
		return isVigente(DateUtils.getDataHoraAtual());
	}
	
	public boolean isVigente(Date dataAtual) {
		return isAntesFimVigencia(dataAtual) && isAntesFimVigencia(dataAtual);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFimVigencia == null) ? 0 : dataFimVigencia.hashCode());
		result = prime * result + ((dataInicioVigencia == null) ? 0 : dataInicioVigencia.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((meioPagamento == null) ? 0 : meioPagamento.hashCode());
		result = prime * result + ((provedor == null) ? 0 : provedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratoCobranca other = (ContratoCobranca) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (meioPagamento == null) {
			if (other.meioPagamento != null)
				return false;
		} else if (!meioPagamento.equals(other.meioPagamento))
			return false;
		if (provedor == null) {
			if (other.provedor != null)
				return false;
		} else if (!provedor.equals(other.provedor))
			return false;
		
		// VIGÊNCIA
		if (isAntesFimVigencia(other.dataFimVigencia)) {
			if (isAposInicioVigencia(other.dataFimVigencia)) {
				return true;
			}
		}
		if (isAposInicioVigencia(other.dataInicioVigencia)) {
			if (isAntesFimVigencia(other.dataInicioVigencia)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "ContratoCobranca [id=" + id + ", dataFimVigencia=" + dataFimVigencia + ", dataInicioVigencia="
				+ dataInicioVigencia + ", empresa=" + empresa + ", meioPagamento=" + meioPagamento + ", provedor="
				+ provedor + "]";
	}

	

}