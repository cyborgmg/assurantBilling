package br.com.delphos.sca.gruposInterfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.delphos.persistencia.Entidade;
import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.usuarios.GrupoUsuario;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(namespace = ConstantesSca.NAMESPACE_SCA)
@XmlType
@Entity
@Table(name="INTERFACE")
public class Interface implements Serializable, Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="SEQ_ID_INTERFACE",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ_ID_INTERFACE",name="SEQ_ID_INTERFACE",allocationSize=1)
	@Column(name="ID_INTERFACE")
	private Long id;

	@Column(name="DESCRICAO")
	private String descricao;

	@ManyToMany
	@JoinTable(name = "INTERFACE_GRUPO_INTERFACE",
	joinColumns = @JoinColumn(name = "ID_INTERFACE"),
	inverseJoinColumns = @JoinColumn(name = "ID_GRUPO_INTERFACE"))
	private List<GrupoInterface> gruposInterface = new ArrayList<GrupoInterface>();
	
	public Interface() {
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

	@XmlElement(required = false, name = "grupoInterface")
	public List<GrupoInterface> getGruposInterface() {
		return this.gruposInterface;
	}

	public void setGruposInterface(List<GrupoInterface> grupoInterfaces) {
		this.gruposInterface = grupoInterfaces;
	}
	
	public GrupoInterface addGrupoInterface(GrupoInterface gi) {
		if (this.gruposInterface == null) {
			this.gruposInterface = new ArrayList<GrupoInterface>();
		}
		this.gruposInterface.add(gi);
		return gi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Interface other = (Interface) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interface [id=" + id + ", descricao=" + descricao + "]";
	}

}