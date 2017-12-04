package br.com.delphos.sca.empresas;

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
import javax.persistence.OneToMany;
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
import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.usuarios.Usuario;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(namespace = ConstantesSca.NAMESPACE_SCA)
@Entity
@Table(name="EMPRESA")
@XmlType(name = "empresa", propOrder = {
	    "codigo",
	    "descricao",
	    "id",
	    "sistemas"
	})
public class Empresa implements Serializable, Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="SEQ_ID_EMPRESA",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ_ID_EMPRESA",name="SEQ_ID_EMPRESA",allocationSize=1)
	@Column(name="ID_EMPRESA")
	private Long id;

	@Column(name="CODIGO")
	private String codigo;

	@Column(name="DESCRICAO")
	private String descricao;

	@OneToMany
	@JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
	private List<Sistema> sistemas = new ArrayList<Sistema>();

	@ManyToMany
	@JoinTable(name = "EMPRESA_USUARIO",
		joinColumns = @JoinColumn(name = "ID_EMPRESA"),
		inverseJoinColumns = @JoinColumn(name = "ID_USUARIO")
	)
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Empresa(){
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
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "ID")
	@XmlAttribute(required = false)
	public String getXmlID() {
		return this.id != null ? this.id.toString() : null;
	}
	
	public void setXmlID(String xmlId) {
		this.id = xmlId != null ? Long.parseLong(xmlId) : null;
	}
	
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    @XmlElement(required = false, name = "sistema")
	public List<Sistema> getSistemas() {
		return this.sistemas;
	}

	public void setSistemas(List<Sistema> sistemas) {
		this.sistemas = sistemas;
	}

	@XmlTransient
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> sistemas) {
		this.usuarios = sistemas;
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
		Empresa other = (Empresa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", codigo=" + codigo + "]";
	}

}