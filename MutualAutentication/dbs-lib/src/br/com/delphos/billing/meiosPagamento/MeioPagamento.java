package br.com.delphos.billing.meiosPagamento;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.enumeracoes.TipoMeioPagamento;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the MEIO_PAGAMENTO database table.
 * 
 */
@Entity
@Table(name="MEIO_PAGAMENTO")
@NamedQuery(name="MeioPagamento.findAll", query="SELECT m FROM MeioPagamento m")
@SequenceGenerator(sequenceName = "SEQ_ID_MEIO_PAGAMENTO", name = "SEQ_ID_MEIO_PAGAMENTO", allocationSize = 1)
public class MeioPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_MEIO_PAGAMENTO")
	@Column(name="ID_MEIO_PAGAMENTO")
	private Long id;

	private String codigo;

	private String descricao;

	@Column(name="LIMITE_DIAS_GERAR_RETENTATIVA")
	private int limiteDiasGerarRetentativa;

	//bi-directional many-to-one association to ContratoCobranca
	@OneToMany(mappedBy="meioPagamento")
	private List<ContratoCobranca> contratosCobranca;

	public MeioPagamento() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	// TODO Verificar se TipoMeioPagamento pode substituir a atribuição de códigos como literais
	public String getCodigo() {
		return this.codigo;
	}

	// TODO Verificar se TipoMeioPagamento pode substituir a atribuição de códigos como literais
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public TipoMeioPagamento getTipo() {
		return TipoMeioPagamento.buscarPorValor(this.codigo);
	}
	
	public void setTipo(TipoMeioPagamento tipo) {
		this.codigo = tipo.getValor();
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getLimiteDiasGerarRetentativa() {
		return this.limiteDiasGerarRetentativa;
	}

	public void setLimiteDiasGerarRetentativa(int limiteDiasGerarRetentativa) {
		this.limiteDiasGerarRetentativa = limiteDiasGerarRetentativa;
	}

	public List<ContratoCobranca> getContratosCobranca() {
		return this.contratosCobranca;
	}

	public void setContratosCobranca(List<ContratoCobranca> contratosCobranca) {
		this.contratosCobranca = contratosCobranca;
	}

	public ContratoCobranca addContratosCobranca(ContratoCobranca contratosCobranca) {
		getContratosCobranca().add(contratosCobranca);
		contratosCobranca.setMeioPagamento(this);

		return contratosCobranca;
	}

	public ContratoCobranca removeContratosCobranca(ContratoCobranca contratosCobranca) {
		getContratosCobranca().remove(contratosCobranca);
		contratosCobranca.setMeioPagamento(null);

		return contratosCobranca;
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
		MeioPagamento other = (MeioPagamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeioPagamento [id=" + id + ", codigo=" + codigo + "]";
	}

}