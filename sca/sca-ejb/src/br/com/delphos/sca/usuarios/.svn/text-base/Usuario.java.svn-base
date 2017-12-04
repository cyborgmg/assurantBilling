package br.com.delphos.sca.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
import br.com.delphos.sca.empresas.Empresa;

@Entity
@Table(name="USUARIO")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@XmlRootElement
@XmlType(name = "usuario", propOrder = {
	    "codigo",
	    "descricao",
	    "email",
	    "empresas",
	    "gruposUsuario",
	    "id",
	    "token"
	})
public class Usuario implements Serializable, Entidade<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="SEQ_ID_USUARIO",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ_ID_USUARIO",name="SEQ_ID_USUARIO",allocationSize=1)
	@Column(name="ID_USUARIO")
	private Long id;

	@Column(name="CODIGO", nullable=false)
	private String codigo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EXCLUSAO")
	private Date dataExclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_EXPIRACAO")
	private Date dataExpiracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_ULTIMA_ALTERACAO")
	private Date dataUltimaAlteracao;

	@Column(name="DESCRICAO",nullable=false)
	private String descricao;

	@Column(name="EMAIL",nullable=false)
	private String email;

	@Column(name="SENHA",nullable=false)
	private String senha;

	@Column(name="TOKEN")
	private String token;

	@Column(name="ULTIMA_SENHA")
	private String ultimaSenha;

	@ManyToMany
	@JoinTable(name = "EMPRESA_USUARIO",
	joinColumns = @JoinColumn(name = "ID_USUARIO"),
	inverseJoinColumns = @JoinColumn(name = "ID_EMPRESA")
	)
	private List<Empresa> empresas = new ArrayList<Empresa>();

	@ManyToMany
	@JoinTable(name = "USUARIO_GRUPO_USUARIO",
	joinColumns = @JoinColumn(name = "ID_USUARIO"),
	inverseJoinColumns = @JoinColumn(name = "ID_GRUPO_USUARIO")
	)
	private List<GrupoUsuario> gruposUsuario = new ArrayList<GrupoUsuario>();
	
	public Usuario(){
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
	
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlTransient
	public Date getDataExclusao() {
		return this.dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	@XmlTransient
	public Date getDataExpiracao() {
		return this.dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	@XmlTransient
	public Date getDataUltimaAlteracao() {
		return this.dataUltimaAlteracao;
	}

	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlTransient
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@XmlTransient
	public String getUltimaSenha() {
		return this.ultimaSenha;
	}

	public void setUltimaSenha(String ultimaSenha) {
		this.ultimaSenha = ultimaSenha;
	}

	@XmlElement(required = false, name = "empresa")
	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	
	@XmlElement(required = false, name = "grupoUsuario")
	public List<GrupoUsuario> getGruposUsuario() {
		return this.gruposUsuario;
	}

	public void setGruposUsuario(List<GrupoUsuario> grupoUsuario) {
		this.gruposUsuario = grupoUsuario;
	}
	
	public Empresa addEmpresa(Empresa empresa) {
		if (this.empresas == null) {
			this.empresas = new ArrayList<Empresa>();
		}
		this.empresas.add(empresa);
		return empresa;
	}
	
	public GrupoUsuario addGrupoUsuario(GrupoUsuario grupoUsuario) {
		if (this.gruposUsuario == null) {
			this.gruposUsuario = new ArrayList<GrupoUsuario>();
		}
		this.gruposUsuario.add(grupoUsuario);
		return grupoUsuario;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", codigo=" + codigo + "]";
	}
	
}