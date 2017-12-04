package br.com.delphos.sca.sistemas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.delphos.persistencia.Entidade;
import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.empresas.Empresa;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(namespace = ConstantesSca.NAMESPACE_SCA)
@XmlType(name = "sistema", propOrder = {
	    "descricao",
	    "empresa",
	    "id",
	    "sigla"
	})
@Entity
@Table(name="SISTEMA")
public class Sistema implements Serializable, Entidade<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="SEQ_ID_SISTEMA",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ_ID_SISTEMA",name="SEQ_ID_SISTEMA",allocationSize=1)
	@Column(name="ID_SISTEMA")
	private Long id;

	@Column(name="DESCRICAO")
	private String descricao;

	@Column(name="SIGLA")
	private String sigla;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMPRESA")
	private Empresa empresa;
	
	public Sistema(){
	}

	public boolean hasId() {
		return this.id != null;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlID
	@XmlAttribute(required = false)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "ID")
	public String getXmlID() {
		return this.id != null ? this.id.toString() : null;
	}
	
	public void setXmlID(String xmlId) {
		this.id = xmlId != null ? Long.parseLong(xmlId) : null;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@XmlIDREF
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Sistema other = (Sistema) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sistema [id=" + id + ", sigla=" + sigla + ", empresa=" + empresa + "]";
	}

}