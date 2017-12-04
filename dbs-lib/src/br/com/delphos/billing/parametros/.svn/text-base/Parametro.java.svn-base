package br.com.delphos.billing.parametros;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
@Entity
@NamedQuery(name="Parametro.findAll", query="SELECT p FROM Parametro p")
@SequenceGenerator(sequenceName = "SEQ_ID_PARAMETRO", name = "SEQ_ID_PARAMETRO", allocationSize = 1)
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_PARAMETRO")
	@Column(name="ID_PARAMETRO")
	private Long id;
	
	@Column(name="INTERVALO_PENDENCIA")
	private Long intervaloPendencia;
	
	@Column(name="INTERVALO_ESTORNO_SOLICITADO")
	private Long intervaloEstornoSolicitado;
	
	@Column(name="LIMITE_ADQUIRENTE_ATIVO")
	private Integer limiteAdquirenteAtivo;
	
	@Column(name = "MARGEM_ACEITACAO_CONCILIACAO")
	private BigDecimal margemAceitacaoConciliacao;
	
	public Parametro(){		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIntervaloPendencia() {
		return intervaloPendencia;
	}

	public void setIntervaloPendencia(Long intervaloPendencia) {
		this.intervaloPendencia = intervaloPendencia;
	}

	public Integer getLimiteAdquirenteAtivo() {
		return limiteAdquirenteAtivo;
	}

	public void setLimiteAdquirenteAtivo(Integer limiteAdquirenteAtivo) {
		this.limiteAdquirenteAtivo = limiteAdquirenteAtivo;
	}

	public BigDecimal getMargemAceitacaoConciliacao() {
		return margemAceitacaoConciliacao;
	}

	public void setMargemAceitacaoConciliacao(BigDecimal margemAceitacaoConciliacao) {
		this.margemAceitacaoConciliacao = margemAceitacaoConciliacao;
	}

	@Override
	public String toString() {
		return "Parametro [id=" + id + ", intervaloPendencia="
				+ intervaloPendencia + "]";
	}

	public Long getIntervaloEstornoSolicitado() {
		return intervaloEstornoSolicitado;
	}

	public void setIntervaloEstornoSolicitado(Long intervaloEstornoSolicitado) {
		this.intervaloEstornoSolicitado = intervaloEstornoSolicitado;
	}

}
