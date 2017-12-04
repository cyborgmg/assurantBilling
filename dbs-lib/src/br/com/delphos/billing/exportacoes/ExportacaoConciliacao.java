package br.com.delphos.billing.exportacoes;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.cobrancas.Cobranca;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EXPORTACAO_CONCILIACAO database table.
 * 
 */
//@Entity
@Table(name="EXPORTACAO_CONCILIACAO")
@NamedQuery(name="ExportacaoConciliacao.findAll", query="SELECT e FROM ExportacaoConciliacao e")
@SequenceGenerator(sequenceName = "SEQ_ID_EXPORTACAO_CONCILIACAO", name = "SEQ_ID_EXPORTACAO_CONCILIACAO", allocationSize = 1)
public class ExportacaoConciliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_EXPORTACAO_CONCILIACAO")
	@Column(name="ID_EXPORTACAO_CONCILIACAO")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_GERACAO")
	private Date dataGeracao;

	@Column(name="NOME_ARQUIVO")
	private String nomeArquivo;

	//bi-directional many-to-one association to Cobranca
	@OneToMany(mappedBy="exportacaoConciliacao")
	private List<Cobranca> cobrancas;

	public ExportacaoConciliacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataGeracao() {
		return this.dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public List<Cobranca> getCobrancas() {
		return this.cobrancas;
	}

	public void setCobrancas(List<Cobranca> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public Cobranca addCobranca(Cobranca cobranca) {
		getCobrancas().add(cobranca);
//		cobranca.setExportacaoConciliacao(this);

		return cobranca;
	}

	public Cobranca removeCobranca(Cobranca cobranca) {
		getCobrancas().remove(cobranca);
//		cobranca.setExportacaoConciliacao(null);

		return cobranca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ExportacaoConciliacao other = (ExportacaoConciliacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExportacaoConciliacao [id=" + id + "]";
	}

}