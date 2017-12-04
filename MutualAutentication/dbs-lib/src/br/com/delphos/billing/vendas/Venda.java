package br.com.delphos.billing.vendas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delphos.billing.cobrancas.Cobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.MotivoCancelamento;
import br.com.delphos.billing.enumeracoes.StatusVenda;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.sistemas.Sistema;
import br.com.delphos.billing.util.DateUtils;


/**
 * The persistent class for the VENDA database table.
 * 
 * <p>Igualdade entre objetos venda:
 * </p>
 * <pre><code>
 * FUNCTION equals(this, other)
 *    IF this.codigoVenda != other.codigoVenda
 *    OR this.empresa != other.empresa
 *    OR this.produto != other.codigoProduto
 *        RETURN FALSE
 *    ELSE
 *        IF  this.status IN (PENDENTE, EFETIVADA)
 *        AND other.status IN (PENDENTE, EFETIVADA)
 *            IF  vigente(this.status)
 *            AND vigente(other.status)
 *                RETURN TRUE
 *            ELSE
 *                RETURN FALSE
 *        ELSE
 *            RETURN this.id == other.id
 * </code></pre>
 */
@Entity
@NamedQuery(name="Venda.findAll", query="SELECT v FROM Venda v")
@SequenceGenerator(sequenceName = "SEQ_ID_VENDA", name = "SEQ_ID_VENDA", allocationSize = 1)
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_VENDA")
	@Column(name="ID_VENDA")
	private Long id;

	private String celular;

	@Column(name="CODIGO_BANDEIRA_CARTAO")
	private String codigoBandeiraCartao;

	@Column(name="CODIGO_MOTIVO_CANCELAMENTO")
	private String codigoMotivoCancelamento;

	@Column(name="CODIGO_VENDA_ORIGEM")
	private String codigoVendaOrigem;

	private String cpf;

	@Column(name="CPF_PORTADOR_CARTAO")
	private String cpfPortadorCartao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_CANCELAMENTO")
	private Date dataCancelamento;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FIM_VIGENCIA")
	private Date dataFimVigencia;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PRIMEIRA_COBRANCA")
	private Date dataPrimeiraCobranca;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_VENDA_ORIGEM")
	private Date dataVendaOrigem;

	@Column(name="DDD_CELULAR")
	private String dddCelular;

	@Column(name="DDD_TELEFONE")
	private String dddTelefone;

	private String email;

	private String nome;

	@Column(name="NOME_IMPRESSO_CARTAO")
	private String nomeImpressoCartao;

	@Column(name="QUANTIDADE_PARCELAS")
	private int quantidadeParcelas;

	private String status;

	private String telefone;

	@Column(name="TIPO_COBRANCA")
	private String tipoCobranca;

	@Column(name="TOKEN_CARTAO")
	private String tokenCartao;

	@Column(name="ULTIMOS_DIGITOS_CARTAO")
	private String ultimosDigitosCartao;

	@Column(name="VALOR_COBRANCA")
	private BigDecimal valorCobranca;

	@Column(name="VENCIMENTO_CARTAO")
	private String vencimentoCartao;

	//bi-directional many-to-one association to Cobranca
	@OneToMany(mappedBy="venda")
	private List<Cobranca> cobrancas;

	//bi-directional many-to-one association to ContratoCobranca
	@ManyToOne
	@JoinColumn(name="ID_CONTRATO_COBRANCA")
	private ContratoCobranca contratoCobranca;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA")
	private Empresa empresa;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_PRODUTO")
	private Produto produto;

	//bi-directional many-to-one association to Sistema
	@ManyToOne
	@JoinColumn(name="ID_SISTEMA")
	private Sistema sistema;

	public Venda() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCodigoBandeiraCartao() {
		return this.codigoBandeiraCartao;
	}

	public void setCodigoBandeiraCartao(String codigoBandeiraCartao) {
		this.codigoBandeiraCartao = codigoBandeiraCartao;
	}

	public String getCodigoMotivoCancelamento() {
		return this.codigoMotivoCancelamento;
	}

	public void setCodigoMotivoCancelamento(String codigoMotivoCancelamento) {
		this.codigoMotivoCancelamento = codigoMotivoCancelamento;
	}

	public void setCodigoMotivoCancelamento(MotivoCancelamento motivoCancelamento) {
		this.codigoMotivoCancelamento = motivoCancelamento.getValor();
	}
	
	public String getCodigoVendaOrigem() {
		return this.codigoVendaOrigem;
	}

	public void setCodigoVendaOrigem(String codigoVendaOrigem) {
		this.codigoVendaOrigem = codigoVendaOrigem;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpfPortadorCartao() {
		return this.cpfPortadorCartao;
	}

	public void setCpfPortadorCartao(String cpfPortadorCartao) {
		this.cpfPortadorCartao = cpfPortadorCartao;
	}

	public Date getDataCancelamento() {
		return this.dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Date getDataFimVigencia() {
		return this.dataFimVigencia;
	}

	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	public Date getDataPrimeiraCobranca() {
		return this.dataPrimeiraCobranca;
	}

	public void setDataPrimeiraCobranca(Date dataPrimeiraCobranca) {
		this.dataPrimeiraCobranca = dataPrimeiraCobranca;
	}

	public Date getDataVendaOrigem() {
		return this.dataVendaOrigem;
	}

	public void setDataVendaOrigem(Date dataVendaOrigem) {
		this.dataVendaOrigem = dataVendaOrigem;
	}

	public String getDddCelular() {
		return this.dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getDddTelefone() {
		return this.dddTelefone;
	}

	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeImpressoCartao() {
		return this.nomeImpressoCartao;
	}

	public void setNomeImpressoCartao(String nomeImpressoCartao) {
		this.nomeImpressoCartao = nomeImpressoCartao;
	}

	public int getQuantidadeParcelas() {
		return this.quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public StatusVenda getStatus() {
		return StatusVenda.buscarPorValor(this.status);
	}

	public void setStatus(StatusVenda status) {
		this.status = status.getValor();
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoCobranca getTipoCobranca() {
		return TipoCobranca.buscarPorValor(this.tipoCobranca);
	}

	public void setTipoCobranca(TipoCobranca tipoCobranca) {
		this.tipoCobranca = tipoCobranca.getValor();
	}

	public String getTokenCartao() {
		return this.tokenCartao;
	}

	public void setTokenCartao(String tokenCartao) {
		this.tokenCartao = tokenCartao;
	}

	public String getUltimosDigitosCartao() {
		return this.ultimosDigitosCartao;
	}

	public void setUltimosDigitosCartao(String ultimosDigitosCartao) {
		this.ultimosDigitosCartao = ultimosDigitosCartao;
	}

	public BigDecimal getValorCobranca() {
		return this.valorCobranca;
	}

	public void setValorCobranca(BigDecimal valorCobranca) {
		this.valorCobranca = valorCobranca;
	}

	public String getVencimentoCartao() {
		return this.vencimentoCartao;
	}

	public void setVencimentoCartao(String vencimentoCartao) {
		this.vencimentoCartao = vencimentoCartao;
	}

	public List<Cobranca> getCobrancas() {
		return this.cobrancas;
	}

	public void setCobrancas(List<Cobranca> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public Cobranca addCobranca(Cobranca cobranca) {
		getCobrancas().add(cobranca);
		cobranca.setVenda(this);

		return cobranca;
	}

	public Cobranca removeCobranca(Cobranca cobranca) {
		getCobrancas().remove(cobranca);
		cobranca.setVenda(null);

		return cobranca;
	}

	public ContratoCobranca getContratoCobranca() {
		return this.contratoCobranca;
	}

	public void setContratoCobranca(ContratoCobranca contratoCobranca) {
		this.contratoCobranca = contratoCobranca;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	/**
	 * Verifica se a venda é vigente para a data de hoje, considerando
	 * apenas suas datas de cancelamento e fim de vigência.
	 * 
	 * @see #isVigente(Date)
	 * @return
	 */
	public boolean isVigente() {
		return isVigente(DateUtils.getDataAtual());
	}
	
	/**
	 * Verifica se a venda atual é vigente para a data passada, considerando
	 * apenas suas datas de cancelamento e fim de vigência.
	 * 
	 * Para verificar se a venda é ativa (
	 * 
	 * @param dataCorrente
	 * @return
	 */
	public boolean isVigente(Date dataCorrente) {
		boolean retorno = false;
		if (this.dataFimVigencia != null
				&& dataCorrente != null
				&& this.dataFimVigencia.compareTo(DateUtils.removerHoras(dataCorrente)) >= 0
				&& this.dataCancelamento == null) {
			retorno = true;
		}
		return retorno;
	}
	
	public boolean isAtiva() {
		return isAtiva(DateUtils.getDataAtual());
	}
	
	/**
	 * Verifica se a venda está ativa na data passada.
	 * 
	 * Uma venda é ativa apenas se o seu status for pendente ou efetivado.
	 * Satisfeita esta precondição, a venda também deverá estar vigente para
	 * a {@code dataCorrente}. 
	 * 
	 * @param dataCorrente A data usada para a verificação de período de 
	 * vigência.
	 * @return {@code true} se a venda está ativa, {@code false} caso contrário
	 * ou caso a venda 
	 */
	public boolean isAtiva(Date dataCorrente) {
		boolean retorno = false;
		StatusVenda statusVenda = getStatus();
		if (status != null
				&& (statusVenda == StatusVenda.Pendente 
					|| statusVenda == StatusVenda.Efetivada)
				&& isVigente(dataCorrente)) {
			retorno = true;
		}
		return retorno;
	}

	public boolean isPagamentoVista() {
		TipoCobranca tipoCobranca = getTipoCobranca();
		boolean vista = false;
		
		if (tipoCobranca != null) {
			vista = tipoCobranca.isPagamentoVista();
			
			if (vista && quantidadeParcelas != 1) {
				vista = false;
			}
		}
		
		return vista;
	}
	
	public boolean isPagamentoParcelado() {
		TipoCobranca tipoCobranca = getTipoCobranca();
		boolean parcelada = false;
		
		if (tipoCobranca != null) {
			parcelada = tipoCobranca.isPagamentoParcelado();
			
			if (parcelada && quantidadeParcelas <= 1) {
				parcelada = false;
			}
		}
		
		return parcelada;
	}
	
	public boolean isRecorrencia() {
		TipoCobranca tipoCobranca = getTipoCobranca();
		boolean recorrencia = false;
		
		if (tipoCobranca != null) {
			recorrencia = tipoCobranca.isRecorrencia();
		}
		
		return recorrencia;
	}
	
	public boolean isPremioUnico() {
		TipoCobranca tipoCobranca = getTipoCobranca();
		boolean premioUnico = false;
		
		if (tipoCobranca != null) {
			premioUnico = tipoCobranca.isPremioUnico();
		}
		
		return premioUnico;
	}
	
	public boolean isVigenciaAberta() {
		TipoCobranca tipoCobranca = getTipoCobranca();
		boolean vigenciaAberta = false;
		
		if (tipoCobranca != null) {
			vigenciaAberta = tipoCobranca.isVigenciaAberta();
		}
		
		return vigenciaAberta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoVendaOrigem == null) ? 0 : codigoVendaOrigem
						.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((dataFimVigencia == null) ? 0 : dataFimVigencia.hashCode());
		result = prime * result + ((dataCancelamento == null) ? 0 : dataCancelamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public boolean equals(Object obj, Date dataCorrente) {
		// Validações óbvias
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		
		Venda other = (Venda) obj;
		
		// Incondicionais
		// Código, empresa e produto devem ser únicos e não dependentes de
		// outras condições.
		
		if (codigoVendaOrigem == null) {
			if (other.codigoVendaOrigem != null)
				return false;
		} else if (!codigoVendaOrigem.equals(other.codigoVendaOrigem))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;

		// Condicionais
		
		if (!isAtiva(dataCorrente)) {
			if (other.isAtiva(dataCorrente))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else {
				if (!id.equals(other.id))
					return false;
			}
		} else if (!other.isAtiva(dataCorrente)) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return equals(obj, DateUtils.getDataAtual());
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", codigoVendaOrigem=" + codigoVendaOrigem
				+ ", dataCancelamento=" + dataCancelamento
				+ ", dataFimVigencia=" + dataFimVigencia + ", status=" + status
				+ ", empresa=" + empresa + ", produto=" + produto
				+ ", sistema=" + sistema + "]";
	}

}