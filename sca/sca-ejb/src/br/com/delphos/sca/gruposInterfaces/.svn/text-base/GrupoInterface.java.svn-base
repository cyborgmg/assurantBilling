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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
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
@Table(name="GRUPO_INTERFACE")
public class GrupoInterface implements Serializable, Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="SEQ_ID_GRUPO_INTERFACE",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ_ID_GRUPO_INTERFACE",name="SEQ_ID_GRUPO_INTERFACE",allocationSize=1)
	@Column(name="ID_GRUPO_INTERFACE")
	private Long id;
	
	@Column(name="DESCRICAO")
	private String descricao;

	@ManyToMany
	@JoinTable(name = "GRUPO_USUARIO_GRUPO_INTERFACE",
	joinColumns = @JoinColumn(name = "ID_GRUPO_INTERFACE"),
	inverseJoinColumns = @JoinColumn(name = "ID_GRUPO_USUARIO")
	)
	private List<GrupoUsuario> gruposUsuario = new ArrayList<GrupoUsuario>();

	@ManyToMany
	@JoinTable(
		name="INTERFACE_GRUPO_INTERFACE",
		joinColumns = @JoinColumn(name="ID_GRUPO_INTERFACE"),
		inverseJoinColumns = @JoinColumn(name="ID_INTERFACE")
	)
	private List<Interface> interfaces;

	public GrupoInterface() {
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

	@XmlTransient
	public List<GrupoUsuario> getGruposUsuario() {
		return this.gruposUsuario;
	}

	public void setGruposUsuario(List<GrupoUsuario> grupoUsuarios) {
		this.gruposUsuario = grupoUsuarios;
	}

	@XmlTransient
	public List<Interface> getInterfaces() {
		return this.interfaces;
	}

	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	public Interface addInterface(Interface interfac) {
		if (this.interfaces == null) {
			this.interfaces = new ArrayList<Interface>();
		}
		this.interfaces.add(interfac);
		return interfac;
	}

	public Interface removeInterface(Interface interfac) {
		if (this.interfaces != null) {
			this.interfaces.remove(interfac);
		}
		return interfac;
	}
	
	public GrupoUsuario addGrupoUsuario(GrupoUsuario grupoUsuario) {
		if (this.gruposUsuario == null) {
			this.gruposUsuario = new ArrayList<GrupoUsuario>();
		}
		this.gruposUsuario.add(grupoUsuario);
		return grupoUsuario;
	}
	
	public GrupoUsuario removeGrupoUsuario(GrupoUsuario grupoUsuario) {
		if (this.gruposUsuario != null) {
			this.gruposUsuario.remove(grupoUsuario);
		}
		return grupoUsuario;
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
		GrupoInterface other = (GrupoInterface) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoInterface [id=" + id + ", descricao=" + descricao + "]";
	}

}