package br.com.delphos.web.configuracao;

import java.util.List;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.sca.empresas.Empresa;
import br.com.delphos.sca.empresas.EmpresaDLO;
import br.com.delphos.sca.gruposInterfaces.GrupoInterface;
import br.com.delphos.sca.gruposInterfaces.GrupoInterfaceDLO;
import br.com.delphos.sca.gruposInterfaces.Interface;
import br.com.delphos.sca.gruposInterfaces.InterfaceDLO;
import br.com.delphos.sca.sistemas.Sistema;
import br.com.delphos.sca.sistemas.SistemaDLO;
import br.com.delphos.sca.usuarios.GrupoUsuario;
import br.com.delphos.sca.usuarios.GrupoUsuarioDLO;
import br.com.delphos.sca.usuarios.Usuario;
import br.com.delphos.sca.usuarios.UsuarioDLO;
import br.com.delphos.util.ServiceLocator;
import br.com.delphos.util.Validador;

public class ConfiguracaoViewHelper {
	
	private String idSistema;
	private String codigoEmpresa;
	private String descricaoEmpresa;	
	private String idEmpresa;
	private String descricaoUsuario;	
	private String idUsuario;
	private String idInterface;
	private String idGrupoUsuario;
	private String idGrupoInterface;
	private String siglaSistema;
	private String descricaoSistema;
	private String codigoUsuario;
	private String tipoGrupo;
	private String descricaoGrupoUsuario;
	private String descricaoGrupoInterface;
	
	EmpresaDLO dloEmpresa = (EmpresaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/EmpresaDLOBean");
	SistemaDLO sistemaDLO = (SistemaDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/SistemaDLOBean");
	UsuarioDLO usuarioDLO = (UsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/UsuarioDLOBean");
	GrupoUsuarioDLO grupoUsuarioDLO = (GrupoUsuarioDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoUsuarioDLOBean");
	GrupoInterfaceDLO grupoInterfaceDLO = (GrupoInterfaceDLO) ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/GrupoInterfaceDLOBean");
    
	private String descricaoInterface;
	
	private String grupoInterfaceDescricao;
	
	private String grupoInterfaceId;
	
	InterfaceDLO 		interfaceDLO   			= (InterfaceDLO) 	  ServiceLocator.lookup("java:/global/sca-ear/sca-ejb/InterfaceDLOBean");
	
    public List<Sistema> listarSistema() {
    	List<Sistema> retorno = null;
    	
		retorno = sistemaDLO.listar(); 
   	
    	return retorno;			
    }
	
    public List<Sistema> listarSistemasPorCriterio() {
    	
    	List<Sistema> retorno = null;
    	
		if ((getSiglaSistema() != null && !Validador.vazio(getSiglaSistema()))
				|| (getDescricaoSistema() != null && !Validador.vazio(getDescricaoSistema()))
//				|| (getIdEmpresa() != null && !Validador.vazio(getIdEmpresa()))
				) {
    	
			retorno = sistemaDLO.listarPorCriterio(siglaSistema, descricaoSistema, idEmpresa);
		
		}
		
		return retorno;
    	
    }
    
    
    public List<Empresa> listarEmpresa() {
    	List<Empresa> retorno = null;
    	
    		retorno = dloEmpresa.listar();
    	
    	return retorno;			
    }

    public List<Usuario> listarUsuariosPorCriterio() {
    	
    	List<Usuario> retorno = null;
    	
		if ((getCodigoUsuario() != null && !Validador.vazio(getCodigoUsuario()))
				|| (getDescricaoUsuario() != null && !Validador.vazio(getDescricaoUsuario()))
				|| (getDescricaoEmpresa() != null && !Validador.vazio(getDescricaoEmpresa()))
				) {
    	
    	retorno = usuarioDLO.listarPorCriterio(codigoUsuario, descricaoUsuario, descricaoEmpresa);
    	
		}
    	
    	return retorno;			
    }
    
    public List<Usuario> listarUsuario() {
    	
    	List<Usuario> retorno = null;
    	
    	retorno = usuarioDLO.listar();
    	
    	return retorno;			
    }
    
    public List<Interface> listarInterface() {
    	return interfaceDLO.listar();			
    }
    
    public List<Interface> listarInterfacePorCriterio() {
    	List<Interface> retorno=null;
//    	Long id=null;
    		
    		try {
//        			if(!grupoInterfaceId.isEmpty()){
//	    	    			id =new Long(grupoInterfaceId);
//	    	    		 	retorno= interfaceDLO.listarInterfacePorCriterio(descricaoInterface, id);
//	    			}else
//	    					retorno=interfaceDLO.listarInterfacePorCriterio(descricaoInterface, null);
    				retorno=interfaceDLO.listarInterfacePorCriterio(descricaoInterface, null);
    		
    			} catch (DelphosException e) {
    				e.printStackTrace();
    			}  
    		
		return retorno;			
    }
    
    public List<GrupoUsuario> listarGrupoUsuario() {
    	
    	List<GrupoUsuario> retorno = grupoUsuarioDLO.listar();
    	
    	return retorno;			
    }
    
    public List<GrupoUsuario> listarGrupoUsuarioPorCriterio() {
    	
    	List<GrupoUsuario> retorno = null;
    	
		if ((getTipoGrupo() != null && !Validador.vazio(getTipoGrupo()))
				|| (getDescricaoGrupoUsuario() != null && !Validador.vazio(getDescricaoGrupoUsuario()))
				) {
    	
			retorno = grupoUsuarioDLO.listarPorCriterio(tipoGrupo, descricaoGrupoUsuario);
    	
		}
    	
    	return retorno;			
    }
    							
    public List<GrupoInterface> listarGrupoInterfacePorCriterio() {
    	
    	List<GrupoInterface> retorno = null;
    	
    	if ((getDescricaoGrupoInterface() != null && !Validador.vazio(getDescricaoGrupoInterface()))
    			) {
    		
    		retorno = grupoInterfaceDLO.listarPorCriterio(descricaoGrupoInterface);
    		
    	}
    	
    	return retorno;			
    }
    
    public List<GrupoInterface> listarGrupoInterface() {
    	List<GrupoInterface> retorno = grupoInterfaceDLO.listar();
    	return retorno;			
    }
    
    public List<Empresa> listarEmpresaPorCriterio() {
    	List<Empresa> retorno = null;
//    	List<Empresa> listEmpresasSCAResultado = null;
//    	List<Empresa> listEmpresasResultado = null;
    	
    	if ((getCodigoEmpresa() != null && !Validador.vazio(getCodigoEmpresa()))
    			|| (getDescricaoEmpresa() != null && !Validador.vazio(getDescricaoEmpresa()))) {
    		
    		try {
    			/*     			
    			List<Empresa> listEmpresas = new ArrayList<Empresa>();
    			listEmpresas.addAll(listEmpresasUsuarioLogado);
   			
    			listEmpresasSCAResultado = new ArrayList<Empresa>();
    			listEmpresasResultado = new ArrayList<Empresa>();
    			Empresa empresa = new Empresa();*/
    			
//    			retorno = dloEmpresa.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa, listEmpresas);
    			retorno = dloEmpresa.listarEmpresasPorCriterio(codigoEmpresa, descricaoEmpresa);
    			
/*    			for (Iterator iterator = listEmpresas.iterator(); iterator
						.hasNext();) {
    				Empresa empresaSCA = (Empresa) iterator.next();
					if (empresaSCA.getCodigo().contains(codigoEmpresa) || empresaSCA.getDescricao().contains(descricaoEmpresa)) {
						empresa = dloEmpresa.obter(empresaSCA.getId());
						listEmpresasResultado.add(empresa);
					}
				}*/
    			
    		} catch (DelphosException e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	return retorno;
//    	return listEmpresasResultado;			
    }

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	
	public String getDescricaoUsuario() {
		return descricaoUsuario;
	}

	public void setDescricaoUsuario(String descricaoUsuario) {
		this.descricaoUsuario = descricaoUsuario;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdInterface() {
		return idInterface;
	}

	public void setIdInterface(String idInterface) {
		this.idInterface = idInterface;
	}

	public String getIdGrupoUsuario() {
		return idGrupoUsuario;
	}

	public void setIdGrupoUsuario(String idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}

	public String getIdGrupoInterface() {
		return idGrupoInterface;
	}

	public void setIdGrupoInterface(String idGrupoInterface) {
		this.idGrupoInterface = idGrupoInterface;
	}

	public String getSiglaSistema() {
		return siglaSistema;
	}

	public void setSiglaSistema(String siglaSistema) {
		this.siglaSistema = siglaSistema;
	}

	public String getDescricaoSistema() {
		return descricaoSistema;
	}

	public void setDescricaoSistema(String descricaoSistema) {
		this.descricaoSistema = descricaoSistema;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public String getDescricaoGrupoUsuario() {
		return descricaoGrupoUsuario;
	}

	public void setDescricaoGrupoUsuario(String descricaoGrupoUsuario) {
		this.descricaoGrupoUsuario = descricaoGrupoUsuario;
	}

	public String getDescricaoInterface() {
		return descricaoInterface;
	}

	public void setDescricaoInterface(String descricaoInterface) {
		this.descricaoInterface = descricaoInterface;
	}

	public String getDescricaoGrupoInterface() {
		return descricaoGrupoInterface;
	}

	public void setDescricaoGrupoInterface(String descricaoGrupoInterface) {
		this.descricaoGrupoInterface = descricaoGrupoInterface;
	}

	public String getGrupoInterfaceDescricao() {
		return grupoInterfaceDescricao;
	}

	public void setGrupoInterfaceDescricao(String grupoInterfaceDescricao) {
		this.grupoInterfaceDescricao = grupoInterfaceDescricao;
	}

	public String getGrupoInterfaceId() {
		return grupoInterfaceId;
	}

	public void setGrupoInterfaceId(String grupoInterfaceId) {
		this.grupoInterfaceId = grupoInterfaceId;
	}
	
	
	
}