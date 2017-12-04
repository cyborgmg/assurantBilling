package br.com.delphos.billing.logs;

import java.io.Serializable;

import javax.persistence.*;

import br.com.delphos.billing.enumeracoes.StatusLogProcesso;

import java.util.Date;


/**
 * The persistent class for the LOG_PROCESSO_BATCH database table.
 * 
 */
@Entity
@Table(name="LOG_PROCESSO_BATCH")
@NamedQuery(name="LogProcessoBatch.findAll", query="SELECT l FROM LogProcessoBatch l")
@SequenceGenerator(sequenceName = "SEQ_ID_LOG_PROCESSO_BATCH", name = "SEQ_ID_LOG_PROCESSO_BATCH", allocationSize = 1)
public class LogProcessoBatch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_LOG_PROCESSO_BATCH")
	@Column(name="ID_LOG_PROCESSO_BATCH")
	private Long id;

	private String complemento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_FIM")
	private Date dataFim;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_INICIO")
	private Date dataInicio;

	private String processo;

	private String status;

	public LogProcessoBatch() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getProcesso() {
		return this.processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public StatusLogProcesso getStatus() {
		return StatusLogProcesso.buscarPorValor(this.status);
	}

	public void setStatus(StatusLogProcesso status) {
		this.status = status.getValor();
	}

	public void concatenarComplemento(String valor) {
		if (complemento == null) {
			complemento = valor;
			
		} else {
			complemento += " | " + valor;
		}
		
		// TODO remover
		if (complemento.length() > 4000) {
			complemento = complemento.substring(0, 4000);
		}
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
		LogProcessoBatch other = (LogProcessoBatch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogProcessoBatch [id=" + id + "]";
	}

}