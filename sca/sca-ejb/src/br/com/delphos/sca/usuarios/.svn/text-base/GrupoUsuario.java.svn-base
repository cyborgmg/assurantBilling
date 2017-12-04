package br.com.delphos.sca.usuarios;

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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.delphos.persistencia.Entidade;
import br.com.delphos.sca.constantes.ConstantesSca;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(namespace = ConstantesSca.NAMESPACE_SCA)
@XmlType(name = "grupoUsuario", propOrder = {
	    "descricao",
	    "id",
	    "tipoGrupo"
	})
@Entity
@Table(name = "GRUPO_USUARIO")
public class GrupoUsuario implements Entidade<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "SEQ_ID_GRUPO_USUARIO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName = "SEQ_ID_GRUPO_USUARIO", name = "SEQ_ID_GRUPO_USUARIO",allocationSize=1)
	@Column(name = "ID_GRUPO_USUARIO")
	private Long id;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "TIPO_GRUPO")
	private String tipoGrupo;

	@ManyToMany
	@JoinTable(name = "USUARIO_GRUPO_USUARIO",
		joinColumns = @JoinColumn(name = "ID_GRUPO_USUARIO"),
		inverseJoinColumns = @JoinColumn(name = "ID_USUARIO")
	)
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@ManyToMany
	@JoinTable(name = "GRUPO_USUARIO_GRUPO_INTERFACE",
		joinColumns = @JoinColumn(name = "ID_GRUPO_USUARIO"),
		inverseJoinColumns = @JoinColumn(name = "ID_GRUPO_INTERFACE")
	)
	private List<GrupoInterface> gruposInterface = new ArrayList<GrupoInterface>();
	
	public GrupoUsuario() {
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

	public String getTipoGrupo() {
		return this.tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	@XmlTransient
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		if (this.usuarios == null) {
			this.usuarios = new ArrayList<Usuario>();
		}
		this.usuarios.add(usuario);
		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		if (this.usuarios != null) {
			this.usuarios.remove(usuario);
		}
		return usuario;
	}

	@XmlTransient
	public List<GrupoInterface> getGruposInterface() {
		return gruposInterface;
	}

	public void setGruposInterface(List<GrupoInterface> gruposInterface) {
		this.gruposInterface = gruposInterface;
	}

	public GrupoInterface addGrupoInterface(GrupoInterface usuario) {
		if (this.gruposInterface == null) {
			this.gruposInterface = new ArrayList<GrupoInterface>();
		}
		this.gruposInterface.add(usuario);
		return usuario;
	}

	public GrupoInterface removeGrupoInterface(GrupoInterface usuario) {
		if (this.gruposInterface != null) {
			this.gruposInterface.remove(usuario);
		}
		return usuario;
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
		GrupoUsuario other = (GrupoUsuario) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoUsuario [id=" + id + ", descricao=" + descricao + "]";
	}

}