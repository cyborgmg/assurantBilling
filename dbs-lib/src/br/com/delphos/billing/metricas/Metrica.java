package br.com.delphos.billing.metricas;

import java.math.BigDecimal;
import java.util.Date;

import br.com.delphos.billing.enumeracoes.TipoMetrica;

public class Metrica {

	private Long id;
	
	private Date diaMedicao;
	
	private String tipo;
	
	private String identificador;
	
	private String nomeAlvo;
	
	private Long quantidadeOcorrencias;
	
	private BigDecimal mediaAritmetica;
	
	private BigDecimal maiorValor;
	
	private BigDecimal menorValor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDiaMedicao() {
		return diaMedicao;
	}

	public void setDiaMedicao(Date diaMedicao) {
		this.diaMedicao = diaMedicao;
	}

	public TipoMetrica getTipo() {
		return TipoMetrica.buscarPorValor(tipo);
	}

	public void setTipo(TipoMetrica tipo) {
		this.tipo = tipo.getValor();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNomeAlvo() {
		return nomeAlvo;
	}

	public void setNomeAlvo(String nomeAlvo) {
		this.nomeAlvo = nomeAlvo;
	}

	public Long getQuantidadeOcorrencias() {
		return quantidadeOcorrencias;
	}

	public void setQuantidadeOcorrencias(Long quantidadeOcorrencias) {
		this.quantidadeOcorrencias = quantidadeOcorrencias;
	}

	public BigDecimal getMediaAritmetica() {
		return mediaAritmetica;
	}

	public void setMediaAritmetica(BigDecimal mediaAritmetica) {
		this.mediaAritmetica = mediaAritmetica;
	}

	public BigDecimal getMaiorValor() {
		return maiorValor;
	}

	public void setMaiorValor(BigDecimal maiorValor) {
		this.maiorValor = maiorValor;
	}

	public BigDecimal getMenorValor() {
		return menorValor;
	}

	public void setMenorValor(BigDecimal menorValor) {
		this.menorValor = menorValor;
	}
	
	
}
