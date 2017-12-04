package br.com.delphos.billing.empresas;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.vendas.Venda;


/**
 * The persistent class for the EMPRESA database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
@SequenceGenerator(sequenceName = "SEQ_ID_EMPRESA", name = "SEQ_ID_EMPRESA", allocationSize = 1)
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_EMPRESA")
	@Column(name="ID_EMPRESA")
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "USUARIO_CONCILIADOR")
	private String usuarioConciliador;

	@Column(name = "SENHA_CONCILIADOR")
	private String senhaConciliador;
	
	//bi-directional many-to-one association to ContratoCobranca
	@OneToMany(mappedBy="empresa")
	private List<ContratoCobranca> contratosCobranca;

	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="empresa")
	private List<Produto> produtos;

	//bi-directional many-to-one association to Venda
	@OneToMany(mappedBy="empresa")
	private List<Venda> vendas;

	public Empresa() {
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

	public String getUsuarioConciliador() {
		return usuarioConciliador;
	}

	public void setUsuarioConciliador(String usuarioConciliador) {
		this.usuarioConciliador = usuarioConciliador;
	}

	public String getSenhaConciliador() {
		return senhaConciliador;
	}

	public void setSenhaConciliador(String senhaConciliador) {
		this.senhaConciliador = senhaConciliador;
	}

	public List<ContratoCobranca> getContratosCobranca() {
		return this.contratosCobranca;
	}

	public void setContratosCobranca(List<ContratoCobranca> contratosCobranca) {
		this.contratosCobranca = contratosCobranca;
	}

	public ContratoCobranca addContratosCobranca(ContratoCobranca contratosCobranca) {
		getContratosCobranca().add(contratosCobranca);
		contratosCobranca.setEmpresa(this);

		return contratosCobranca;
	}

	public ContratoCobranca removeContratosCobranca(ContratoCobranca contratosCobranca) {
		getContratosCobranca().remove(contratosCobranca);
		contratosCobranca.setEmpresa(null);

		return contratosCobranca;
	}

	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto addProduto(Produto produto) {
		getProdutos().add(produto);
		produto.setEmpresa(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getProdutos().remove(produto);
		produto.setEmpresa(null);

		return produto;
	}

	public List<Venda> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Venda addVenda(Venda venda) {
		getVendas().add(venda);
		venda.setEmpresa(this);

		return venda;
	}

	public Venda removeVenda(Venda venda) {
		getVendas().remove(venda);
		venda.setEmpresa(null);

		return venda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Empresa other = (Empresa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", codigo=" + codigo + "]";
	}

}