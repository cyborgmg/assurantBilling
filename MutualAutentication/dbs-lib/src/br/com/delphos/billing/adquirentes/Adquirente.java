package br.com.delphos.billing.adquirentes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.delphos.billing.conciliacoes.ControleConciliacao;


/**
 * The persistent class for the ADQUIRENTE database table.
 * 
 */
@Entity
@NamedQuery(name="Adquirente.findAll", query="SELECT a FROM Adquirente a")
@SequenceGenerator(sequenceName = "SEQ_ID_ADQUIRENTE", name = "SEQ_ID_ADQUIRENTE", allocationSize = 1)
public class Adquirente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_ADQUIRENTE")
	@Column(name="ID_ADQUIRENTE")
	private Long id;

	@Column(name="CODIGO_CONVENIO")
	private String codigoConvenio;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "CODIGO_AFILIACAO_CONCILIADOR")
	private Integer codigoAfiliacaoConciliador;
	
	@JoinColumn(name = "ID_ADQUIRENTE")
    @OneToMany(fetch = FetchType.EAGER)
	private List<AdquirenteBandeira> adquirentesBandeira;

//	@OneToMany(mappedBy = "adquirenteCadastrada")
//	private List<ControleConciliacao> controlesConciliacao;
	
	public Adquirente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoConvenio() {
		return this.codigoConvenio;
	}

	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AdquirenteBandeira> getAdquirentesBandeira() {
		return adquirentesBandeira;
	}

	public void setAdquirentesBandeira(List<AdquirenteBandeira> adquirentesBandeira) {
		this.adquirentesBandeira = adquirentesBandeira;
	}
	
	public Integer getCodigoAfiliacaoConciliador() {
		return codigoAfiliacaoConciliador;
	}

	public void setCodigoAfiliacaoConciliador(Integer codigoAfiliacaoConciliador) {
		this.codigoAfiliacaoConciliador = codigoAfiliacaoConciliador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Adquirente other = (Adquirente) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adquirente [id=" + id + ", nome=" + nome + "]";
	}

}