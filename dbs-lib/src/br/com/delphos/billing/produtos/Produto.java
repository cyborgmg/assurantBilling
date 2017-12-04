package br.com.delphos.billing.produtos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.empresas.Empresa;
import br.com.delphos.billing.enumeracoes.TipoCobranca;
import br.com.delphos.billing.vendas.Venda;


/**
 * The persistent class for the PRODUTO database table.
 * 
 */
@Entity
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
@SequenceGenerator(sequenceName = "SEQ_ID_PRODUTO", name = "SEQ_ID_PRODUTO", allocationSize = 1)
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PRODUTO")
	@Column(name="ID_PRODUTO")
	private Long id;

	private String codigo;
	
	private String descricao;

	@Column(name="NUMERO_MAXIMO_PARCELAS")
	private int numeroMaximoParcelas;

	@Column(name="TIPO_COBRANCA")
	private String tipoCobranca;

	//bi-directional many-to-one association to Empresa
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA")
	private Empresa empresa;

	//bi-directional many-to-many association to ContratoCobranca
	@ManyToMany
	@JoinTable(
		name="PRODUTO_CONTRATO_COBRANCA"
		, joinColumns={
			@JoinColumn(name="ID_PRODUTO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_CONTRATO_COBRANCA")
			}
		)
	private List<ContratoCobranca> contratosCobranca;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="produto")
	private List<Venda> vendas;

	public Produto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumeroMaximoParcelas() {
		return this.numeroMaximoParcelas;
	}

	public void setNumeroMaximoParcelas(int numeroMaximoParcelas) {
		this.numeroMaximoParcelas = numeroMaximoParcelas;
	}

	public TipoCobranca getTipoCobranca() {
		return TipoCobranca.buscarPorValor(this.tipoCobranca);
	}

	public void setTipoCobranca(TipoCobranca tipoCobranca) {
		this.tipoCobranca = tipoCobranca.getValor();
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<ContratoCobranca> getContratosCobranca() {
		return this.contratosCobranca;
	}

	public void setContratosCobranca(List<ContratoCobranca> contratosCobranca) {
		this.contratosCobranca = contratosCobranca;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setProduto(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setProduto(null);

		return venda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", codigo=" + codigo + ", empresa="
				+ empresa + "]";
	}

}