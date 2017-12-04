package br.com.delphos.web.logs;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.delphos.billing.adquirentes.Adquirente;
import br.com.delphos.billing.logs.LogOperacaoUsuario;
import br.com.delphos.billing.logs.LogOperacaoUsuarioDLO;
import br.com.delphos.billing.persistencia.DLOEntidade;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.StringUtils;
import br.com.delphos.billing.util.Validador;
import br.com.delphos.util.ServiceLocator;

public class LogViewHelper {
	
	private String codEmpresa;
	private String idProduto;
	private String idSistema;
	private String cpf;
	private String certificado;	
	private String idLog;
	private String idTentativa;	
	private String idCobranca;	
	
	private String tipoOperacao;
	private String tipoObjetoOperacao;
	private String identificadorObjetoOperacao;
	private String usuario;
	private String periodoDe;
	private String periodoAte;
	
    public List<LogOperacaoUsuario> listarLogs(){
    	
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
		
    	List<LogOperacaoUsuario> retorno = null;
    	
		try {
			
			retorno = logOperacaoUsuarioDLO.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return retorno;
    }
    
    public List<LogOperacaoUsuario> listarLogOperacaoUsuarioPorCriterio() {
    	
		LogOperacaoUsuarioDLO logOperacaoUsuarioDLO = (LogOperacaoUsuarioDLO) ServiceLocator.lookup("java:/global/dbsdb/LogOperacaoUsuarioDLOBean");
    	
    	List<LogOperacaoUsuario> retorno = null;
    	
    	if ((getTipoOperacao() != null && !Validador.vazio(getTipoOperacao()))
    			|| (getTipoObjetoOperacao() != null && !Validador.vazio(getTipoObjetoOperacao()))
    			|| (getIdentificadorObjetoOperacao() != null && !Validador.vazio(getIdentificadorObjetoOperacao()))
    			|| (getUsuario() != null && !Validador.vazio(getUsuario()))
    			|| ((getPeriodoDe() != null && !Validador.vazio(getPeriodoDe()))
    			&& (getPeriodoAte() != null && !Validador.vazio(getPeriodoAte())))
    			) {
    		
    		retorno = logOperacaoUsuarioDLO.listarPorCriterio(tipoOperacao, tipoObjetoOperacao, 
    				identificadorObjetoOperacao, usuario, periodoDe, periodoAte);
    		
    	}
    	
    	return retorno;  	
    }
    
    public String retornaValoresEntidade() {
    	
    	String nomeEntidade = tipoObjetoOperacao;
    	
    	if (nomeEntidade != null && !Validador.vazio(nomeEntidade)) {
    	
	    	String retorno = preparaOptionsEntidade(nomeEntidade);
	    	
	    	return retorno.toString();
    	
    	} else {
    		return "";
    	}
    }
    
    public String recuperaDescricaoIdentificadorObjetoOperacao(String nomeEntidade, String idObjeto) {
		    	
		    	// Pacote de entidade 
    			String pacote = "br.com.delphos.billing.";
		    	
		    	nomeEntidade = nomeEntidade.toLowerCase();
		    	
		    	String nomeEntidadeCapitalizada = StringUtils.capitaliza(nomeEntidade);
		    	
		    	String nomeEntidadeQualificado = "";
		    	
		    	if (nomeEntidade.equals("meiopagamento")) {
		    		nomeEntidadeQualificado = pacote+"meiosPagamento"+"."+"MeioPagamento";
		    		nomeEntidadeCapitalizada = "MeioPagamento";
		    	} else if (nomeEntidade.equals("contratocobranca")) {
		    		nomeEntidadeQualificado = pacote+"contratosCobranca."+"ContratoCobranca";
		    		nomeEntidadeCapitalizada = "ContratoCobranca";
		    	} else if (nomeEntidade.equals("respostaautorizacao")) {
		    		nomeEntidadeQualificado = pacote+"contratosCobranca."+"RespostaAutorizacao";
		    		nomeEntidadeCapitalizada = "RespostaAutorizacao";
		    	} else if (nomeEntidade.equals("bandeira")) {
		    		nomeEntidadeQualificado = pacote+"adquirentes."+nomeEntidadeCapitalizada;
		    	} else if (nomeEntidade.equals("adquirente_bandeira")) {
		    			nomeEntidadeCapitalizada = "AdquirenteBandeira";
			    		nomeEntidadeQualificado = pacote+"adquirentes."+ nomeEntidadeCapitalizada;
		    	} else if (nomeEntidade.equals("lista_valor")) {
		    		nomeEntidadeQualificado = pacote+"listasValores."+"ListaValor";
		    		nomeEntidadeCapitalizada = "ListaValor";
		    	} else if (nomeEntidade.equals("item_lista")) {
		    		nomeEntidadeQualificado = pacote+"listasValores."+"ItemLista";
		    		nomeEntidadeCapitalizada = "ItemLista";
		    	} else if (nomeEntidade.equals("provedor")) {
		    		nomeEntidadeQualificado = pacote+nomeEntidade+"es."+nomeEntidadeCapitalizada;
		    	} else {
		    		// preparação do nome da Entidade qualificado para instanciamento da mesma
		    		nomeEntidadeQualificado = pacote+nomeEntidade+"s."+nomeEntidadeCapitalizada;
		    	}
		    	
		    	
		    	StringBuffer retorno = new StringBuffer();
		    	
		    	try {
		    		
		        	Class clsEntidade = Class.forName(nomeEntidadeQualificado);
					
		        	// Precisaremos também instancia o DLO da entidade para acesso dos valores do método listar
					Class clsDLO = Class.forName(nomeEntidadeQualificado+"DLO");
		        	
		        	// instanciando o DLO para acesso mais a frente....
					DLOEntidade<?> clsInstanceDLO = (DLOEntidade<?>) ServiceLocator
							 .lookup("java:global/dbsdb/"+nomeEntidadeCapitalizada+"DLOBean");   
		        	
		        	// temos a lista de objetos do retorno do método listar do DLO
					List<Object> lista = (List<Object>) clsInstanceDLO.listar();
		        	
		        	// pegando todos os campos da entidade pois precisaremos do campo nome e valor (ou respectivo)
		        	// para população do select
		        	Field[] campos = clsEntidade.getDeclaredFields();
		        	
		            boolean temCampoDescritivo = false;
		            String nomeCampoNome = "";
		            
		            Method metodoNome = null;
		            Method metodoId = null;
		            Object[] parametros = new Object[0];
		            Class[] classes = new Class[0];
			    	Long valorOption = 0L;
			    	Adquirente adquirente = null;
		            
			    	// iteração especial para o caso de AdquirenteBandeira
			    	if (nomeEntidadeCapitalizada.equals("AdquirenteBandeira")) {
			    		
			        	// vamos iterar agora pelos campos da entidade
				    	for (Field field : campos) {
				    		// vamos verificar se a entidade possui o atributo 'nome', se tiver vamos
				    		// guardar essa informação para invocar esse método (no caso o getter) mais abaixo
			        		if (field.getName().equals("adquirente")) {
				        		nomeCampoNome = field.getName();
				        		temCampoDescritivo = true;
				        		break;
			        		}
			            }
			    		
			    	} else {
		            
			        	// vamos iterar agora pelos campos da entidade
				    	for (Field field : campos) {
				    		// vamos verificar se a entidade possui o atributo 'nome', se tiver vamos
				    		// guardar essa informação para invocar esse método (no caso o getter) mais abaixo
			        		if (field.getName().contains("nome") || field.getName().contains("descricao")
			        				|| field.getName().contains("nome"+nomeEntidadeCapitalizada) || 
			        				field.getName().contains("descricao"+nomeEntidadeCapitalizada)) {
				        		nomeCampoNome = field.getName();
				        		temCampoDescritivo = true;
				        		break;
			        		}
			            }
			    	
			    	}
		        	
		        	// se existe o campo descritivo (por exemplo pode ser 'nome', 'descricao', etc)
		        	if (temCampoDescritivo) {
			        	for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
							Object object = (Object) iterator.next();
							
								metodoId = object.getClass().getDeclaredMethod("getId", classes);		
								if (metodoId.invoke(object, parametros).getClass().isInstance(valorOption)) { // Se for Long
									valorOption = (Long) metodoId.invoke(object, parametros);
								}
							
								//metodo = object.getClass().getMethod(nomeCampoNome);
								metodoNome = object.getClass().getDeclaredMethod("get" + nomeCampoNome.substring(0, 1).toUpperCase().concat(nomeCampoNome.substring(1)), classes);
								
//								if (idObjeto.equals(valorOption)) {
//									return (String) metodoNome.invoke(object, parametros);
//								}
						    	if (nomeEntidadeCapitalizada.equals("AdquirenteBandeira")) {
			    		        	if (idObjeto != null && idObjeto.trim().equals(object.toString())) {
			    		        		return ((Adquirente) metodoNome.invoke(object, parametros)).getNome();
			    		        	}						    		
						    	} else {
			    		        	if (idObjeto != null && idObjeto.trim().equals(valorOption.toString().trim())) {
			    		        		return (String) metodoNome.invoke(object, parametros);
			    		        	}
						    	}
								
								System.out.println("Descrição: "+metodoNome.invoke(object, parametros));
							
						}
		        	}
		        	
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return retorno.toString();    	
    }

    /**
     * 5.4.	Consulta do log de operações do usuário
     * Método que acessa a classe do Objeto (Objeto Operação) selecionado do Log e popula os 
     * valores (identificador Objeto Operação) do mesmo
     * @param nomeEntidade
     * @return String com os options populados da entidade
     */
    public String preparaOptionsEntidade(String nomeEntidade) {
    	
		    	// Pacote de entidade 
    			String pacote = "br.com.delphos.billing.";
		    	
		    	nomeEntidade = nomeEntidade.toLowerCase();
		    	
		    	String nomeEntidadeCapitalizada = StringUtils.capitaliza(nomeEntidade);
		    	
		    	String nomeEntidadeQualificado = "";
		    	
		    	if (nomeEntidade.equals("meiopagamento")) {
		    		nomeEntidadeQualificado = pacote+"meiosPagamento"+"."+"MeioPagamento";
		    		nomeEntidadeCapitalizada = "MeioPagamento";
		    	} else if (nomeEntidade.equals("contratocobranca")) {
		    		nomeEntidadeQualificado = pacote+"contratosCobranca."+"ContratoCobranca";
		    		nomeEntidadeCapitalizada = "ContratoCobranca";
		    	} else if (nomeEntidade.equals("respostaautorizacao")) {
		    		nomeEntidadeQualificado = pacote+"contratosCobranca."+"RespostaAutorizacao";
		    		nomeEntidadeCapitalizada = "RespostaAutorizacao";
		    	} else if (nomeEntidade.equals("bandeira")) {
		    		nomeEntidadeQualificado = pacote+"adquirentes."+nomeEntidadeCapitalizada;
		    	} else if (nomeEntidade.equals("adquirentebandeira")) {
		    		nomeEntidadeCapitalizada = "AdquirenteBandeira";
		    		nomeEntidadeQualificado = pacote+"adquirentes."+nomeEntidadeCapitalizada;
		    	} else if (nomeEntidade.equals("listavalor")) {
		    		nomeEntidadeQualificado = pacote+"listasValores."+"ListaValor";
		    		nomeEntidadeCapitalizada = "ListaValor";
		    	} else if (nomeEntidade.equals("itemlista")) {
		    		nomeEntidadeQualificado = pacote+"listasValores."+"ItemLista";
		    		nomeEntidadeCapitalizada = "ItemLista";
		    	} else if (nomeEntidade.equals("provedor")) {
		    		nomeEntidadeQualificado = pacote+nomeEntidade+"es."+nomeEntidadeCapitalizada;
		    	} else {
		    		// preparação do nome da Entidade qualificado para instanciamento da mesma
		    		nomeEntidadeQualificado = pacote+nomeEntidade+"s."+nomeEntidadeCapitalizada;
		    	}
		    	
		    	
		    	StringBuffer retorno = new StringBuffer();
		    	
		    	try {
		    		
		        	Class clsEntidade = Class.forName(nomeEntidadeQualificado);
//					Object clsInstanceEntidade = (Object) clsEntidade.newInstance();
					
		        	// Precisaremos também instancia o DLO da entidade para acesso dos valores do método listar
					Class clsDLO = Class.forName(nomeEntidadeQualificado+"DLO");
		        	
		        	// instanciando o DLO para acesso mais a frente....
					DLOEntidade<?> clsInstanceDLO = (DLOEntidade<?>) ServiceLocator
							 .lookup("java:global/dbsdb/"+nomeEntidadeCapitalizada+"DLOBean");   
		        	
		        	// temos a lista de objetos do retorno do método listar do DLO
					List<Object> lista = (List<Object>) clsInstanceDLO.listar();
		        	
		        	// pegando todos os campos da entidade pois precisaremos do campo nome e valor (ou respectivo)
		        	// para população do select
		        	Field[] campos = clsEntidade.getDeclaredFields();
		        	
		            boolean temCampoDescritivo = false;
		            String nomeCampoNome = "";
		            
		            Method metodoNome = null;
		            Method metodoId = null;
		            Object[] parametros = new Object[0];
		            Class[] classes = new Class[0];
			    	Long valorOption = 0L;
			    	String valorOptionString = "";
			    	
			    	// iteração especial para o caso de AdquirenteBandeira
			    	if (nomeEntidadeCapitalizada.equals("AdquirenteBandeira")) {
			    		
			        	// vamos iterar agora pelos campos da entidade
				    	for (Field field : campos) {
				    		// vamos verificar se a entidade possui o atributo 'nome', se tiver vamos
				    		// guardar essa informação para invocar esse método (no caso o getter) mais abaixo
			        		if (field.getName().equals("adquirente")) {
				        		nomeCampoNome = field.getName();
				        		temCampoDescritivo = true;
				        		break;
			        		}
			            }
			    		
			    	} else {
		            
			        	// vamos iterar agora pelos campos da entidade
				    	for (Field field : campos) {
				    		// vamos verificar se a entidade possui o atributo 'nome', se tiver vamos
				    		// guardar essa informação para invocar esse método (no caso o getter) mais abaixo
			        		if (field.getName().contains("nome") || field.getName().contains("descricao")
			        				|| field.getName().contains("nome"+nomeEntidadeCapitalizada) || 
			        				field.getName().contains("descricao"+nomeEntidadeCapitalizada)) {
				        		nomeCampoNome = field.getName();
				        		temCampoDescritivo = true;
				        		break;
			        		}
			            }
			    	
			    	}
		        	
		        	// no jsp, na parte jsp:setProperty do logs.jsp, atribuímos os paramêtros do actionForm
			    	// em variáveis usadas aqui, para podemos deixar selecionado o campo marcado anteriormente
			    	// e submetido
			    	retorno.append("<option value=\"\" ");
		        	if (identificadorObjetoOperacao != null && identificadorObjetoOperacao.trim().equals("")) {
		        		retorno.append(" selected ");
		        	}
		        	retorno.append(" >- "+Mensagens.get("msg.selecione")+" -</option>");
		        	
		        	// se existe o campo descritivo (por exemplo pode ser 'nome', 'descricao', etc)
		        	// ele inicia a preparação dos campos <option> que popularão o select
		        	if (temCampoDescritivo) {
			        	for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
							Object object = (Object) iterator.next();
							
								metodoNome = object.getClass().getDeclaredMethod("get" + nomeCampoNome.substring(0, 1).toUpperCase().concat(nomeCampoNome.substring(1)), classes);
								if (nomeEntidadeCapitalizada.equals("AdquirenteBandeira")) {
									metodoId = object.getClass().getDeclaredMethod("toString", classes);
									valorOptionString = (String) metodoId.invoke(object, parametros);
					            	retorno.append("<option value=\"" + valorOptionString + "\"");
			    		        	if (identificadorObjetoOperacao != null && identificadorObjetoOperacao.trim().equals(valorOptionString.toString().trim())) {
			    		        		retorno.append(" selected ");
			    		        	}
								} else {
									metodoId = object.getClass().getDeclaredMethod("getId", classes);		
									if (metodoId.invoke(object, parametros).getClass().isInstance(valorOption)) { // Se for Long
										valorOption = (Long) metodoId.invoke(object, parametros);
									}
					            	retorno.append("<option value=\"" + valorOption + "\"");
			    		        	if (identificadorObjetoOperacao != null && identificadorObjetoOperacao.trim().equals(valorOption.toString().trim())) {
			    		        		retorno.append(" selected ");
			    		        	}
								}

				    		    if (nomeEntidadeCapitalizada.equals("AdquirenteBandeira")) {
				    		    	retorno.append(">" + ((Adquirente)metodoNome.invoke(object, parametros)).getNome() + "</option>");
				    		    } else {
				    		    	retorno.append(">" + metodoNome.invoke(object, parametros) + "</option>");
				    		    }
								
//								System.out.println("Nome: "+metodoNome.invoke(object, parametros));
//								System.out.println("Id: "+metodoId.invoke(object, parametros));
							
						}
		        	}
		        	
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		return retorno.toString();
	}
    
    public List<LogOperacaoUsuario> detalheLog(){
    	
    	// Soh eh usado para preparar o table (nunca vai aparecer realmente, soh vai preparar 
    	// o terreno montando o table que serah substituido pelo ajax do detalhar log
    	// *** NAO APAGAR ***
    	List<LogOperacaoUsuario> retorno = new ArrayList<LogOperacaoUsuario>();
    		
        	LogOperacaoUsuario logOperacaoUsuario = new LogOperacaoUsuario();
        	logOperacaoUsuario.setId(1L);
        	logOperacaoUsuario.setUsuario(1L);
        	logOperacaoUsuario.setOperacao("ALT");
        	logOperacaoUsuario.setTabela("SISTEMA");
        	logOperacaoUsuario.setIdObjeto("102");
        	logOperacaoUsuario.setComplemento("ALT");
        	
        	retorno.add(logOperacaoUsuario);
    	
    	return retorno;
    }
    
    public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getIdTentativa() {
		return idTentativa;
	}

	public void setIdTentativa(String idTentativa) {
		this.idTentativa = idTentativa;
	}

	public String getIdCobranca() {
		return idCobranca;
	}

	public void setIdCobranca(String idCobranca) {
		this.idCobranca = idCobranca;
	}

	public String getIdLog() {
		return idLog;
	}

	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getTipoObjetoOperacao() {
		return tipoObjetoOperacao;
	}

	public void setTipoObjetoOperacao(String tipoObjetoOperacao) {
		this.tipoObjetoOperacao = tipoObjetoOperacao;
	}

	public String getIdentificadorObjetoOperacao() {
		return identificadorObjetoOperacao;
	}

	public void setIdentificadorObjetoOperacao(String identificadorObjetoOperacao) {
		this.identificadorObjetoOperacao = identificadorObjetoOperacao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPeriodoDe() {
		return periodoDe;
	}

	public void setPeriodoDe(String periodoDe) {
		this.periodoDe = periodoDe;
	}

	public String getPeriodoAte() {
		return periodoAte;
	}

	public void setPeriodoAte(String periodoAte) {
		this.periodoAte = periodoAte;
	}
	
}