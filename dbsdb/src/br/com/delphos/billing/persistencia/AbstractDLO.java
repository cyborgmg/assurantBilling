package br.com.delphos.billing.persistencia;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.delphos.billing.enumeracoes.CodigoMensagem;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.excecoes.EntidadeExistenteDLOException;
import br.com.delphos.billing.excecoes.ValidacaoDLOException;
import br.com.delphos.billing.util.EntidadeUtils;
import br.com.delphos.billing.util.Validador;


public abstract class AbstractDLO<T> implements DLOEntidade<T> {

	protected ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	protected abstract DAOEntidade<T> getDAOEntidade(); 
	
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle("mensagens");
	
	protected boolean isEntidadeValida(T entidade, Class<?>... validacoes) {
		
		boolean retorno = false;
		
		if (entidade != null) {
			Validator validador = validatorFactory.getValidator();
			Set<ConstraintViolation<T>> violacoes = validador.validate(entidade, validacoes);
			if (violacoes.isEmpty()) {
				retorno = true;
			}
		}
		
		return retorno;
	}
	
	@ChamadaLogada
	@Override
	public Object manter(T entidade) throws DLOException {
		Object id = null;
		if (isEntidadeValida(entidade)) {
			entidade = prepararEntidadeParaManter(entidade);
			if (!isEntidadeIdentificada(entidade)) {
				if (isEntidadePersistidaPorIdentidade(entidade)) {
					throw new EntidadeExistenteDLOException(CodigoMensagem.DPH0009);
				}
				id = getDAOEntidade().incluir(entidade);
			} else {
				id = EntidadeUtils.getId(entidade);
				T entidadePersistida = obterEntidadePorIdentidade(entidade);
				if (entidadePersistida != null) {
					Object idPersistido = EntidadeUtils.getId(entidadePersistida);
					if (!idPersistido.equals(id)) {
						throw new EntidadeExistenteDLOException(CodigoMensagem.DPH0009);
					}
				}
				getDAOEntidade().alterar(entidade);
			}
		}
		return id;
	}

	@Override
	public void salvar(T entidade) throws DLOException {
		Object id = manter(entidade);
		if (!isIdEntidadeValido(id)) {
			throw new DLOException(CodigoMensagem.DPH0009);
		}
	}
	
	@Override
	public void excluir(T entidade) {
		if (isEntidadeValida(entidade)) {
			Object id = EntidadeUtils.getId(entidade);
			if (isEntidadeIdentificada(entidade)) {
				getDAOEntidade().excluirPorId(id);
			} else {
				getDAOEntidade().excluir(entidade);
			}
		}
	}

	@Override
	public T obter(Object id) {
		T retorno = null;
		if (isIdEntidadeValido(id)) {
			retorno = getDAOEntidade().obter(id);
		}
		return retorno;
	}
	
	@Override
	public T obterPorIdentidade(T entidade) {
		T retorno = null;
		if (entidade != null) {
			retorno = obterEntidadePorIdentidade(entidade);
		}
		return retorno;
	}

	@Override
	public T obterEntidadeAtualizada(T entidade) {
		T retorno = null;
		if (isEntidadeIdentificada(entidade)) {
			retorno = getDAOEntidade().obterEntidadeAtualizada(entidade);
		}
		return retorno;
	}
	
	@Override
	public List<T> listar() {
		return getDAOEntidade().listar();
	}

	@Override
	public List<T> listarPorFaixa(int[] range) {
		List<T> retorno = null;
		if (range != null && range.length >= 2 && range[0] < range[1]) {
			retorno = getDAOEntidade().listarPorFaixa(range);
		}
		return retorno;
	}

	@Override
	public long contar() {
		return getDAOEntidade().contar();
	}
	
	@Override
	public T completar(T entidade, String... nomesPropriedade) {
		T retorno = null;
		if (entidade != null && nomesPropriedade != null) {
			retorno = getDAOEntidade().completar(entidade, nomesPropriedade);
		}
		return retorno;
	}
	
	@Override
	public T completar(T entidade, Attribute<T, ?> propriedade) {
		T retorno = null;
		if (entidade != null && propriedade != null) {
			retorno = getDAOEntidade().completar(entidade, propriedade.getName());
		}
		return retorno;
	}
	
	@Override
	public long contarReferencias(T entidade, PluralAttribute<T, ?, ?> atributo) throws DLOException {
		if (entidade == null || atributo == null || !isEntidadeIdentificada(entidade)) {
			throw new ValidacaoDLOException();
		}
		long contagem = getDAOEntidade().contarReferencias(entidade, atributo.getName());
		return contagem;
	}
	
	public String gerarMensagemComplementoInclusao(Object novo) {
    	StringBuilder retorno = new StringBuilder();

        try {

            Class<? extends Object> classe = novo.getClass();
            Field[] campos = classe.getDeclaredFields();

            Class[] classes = new Class[0];
            Method metodo = null;

            Object[] objetos = new Object[0];

            for (Field field : campos) {

            	String metodoNome =("get" + field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1)));
            	
            	if(!metodoNome.equals("getSerialVersionUID")){
            		metodo = classe.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1)), classes);

                    if (
                    		( metodo.invoke(novo, objetos) instanceof java.util.Collection==false )
                    		) {
                    	    retorno.append(" "+bundle.getString("lbl.parametro"  )+" "+field.getName().toUpperCase()).
                        			append(" "+bundle.getString("lbl.novoValor"  )+" ").append(metodo.invoke(novo, objetos  ));
                    }else
                    	if( metodo.invoke(novo, objetos) instanceof java.util.Collection ){
                    		//tratar as diferenças entre collections
                    	}
	
            	}
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
		retorno.insert(0, (bundle.getString("lbl.inclusao.objeto")+" ")) ;
        
        return retorno.toString();
    	
	}
	
	public String gerarMensagemComplementoExclusao(Object antigo) {
		StringBuilder retorno = new StringBuilder();
		
		preparaRetornoComparadorObjetos(antigo, retorno);

		retorno.insert(0, (bundle.getString("lbl.exclusao.objeto")+" ")) ;
		
		return retorno.toString();
		
	}
	
	public String gerarMensagemComplementoAtivacao(Object antigo) {
		StringBuilder retorno = new StringBuilder();
		
		preparaRetornoComparadorObjetos(antigo, retorno);
		
		retorno.insert(0, (bundle.getString("lbl.ativacao.objeto")+" ")) ;
		
		return retorno.toString();
		
	}
	
	public String gerarMensagemComplementoAtivacao(Object antigo, Object novo) {
		StringBuilder retorno = new StringBuilder();

		retorno.append(bundle.getString("lbl.ativacao.objeto")+" ");
		retorno.append(comparadorObjetos(antigo, novo));		
		
		return retorno.toString();
	}
	
	public String gerarMensagemComplementoDesativacao(Object antigo, Object novo) {
		StringBuilder retorno = new StringBuilder();

		retorno.append(bundle.getString("lbl.desativacao.objeto")+" ");
		retorno.append(comparadorObjetos(antigo, novo));		
		
		return retorno.toString();
	}
	
	public String gerarMensagemComplementoDesativacao(Object antigo) {
		StringBuilder retorno = new StringBuilder();
		
		preparaRetornoComparadorObjetos(antigo, retorno);
		
		retorno.insert(0, (bundle.getString("lbl.desativacao.objeto")+" ")) ;
		
		return retorno.toString();
		
	}

	private void preparaRetornoComparadorObjetos(Object antigo,
			StringBuilder retorno) {
		try {
			
			Class<? extends Object> classe = antigo.getClass();
			Field[] campos = classe.getDeclaredFields();
			
			Class[] classes = new Class[0];
			Method metodo = null;
			
			Object[] objetos = new Object[0];
			
			for (Field field : campos) {
				
				String metodoNome =("get" + field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1)));
				
				if(!metodoNome.equals("getSerialVersionUID")){
					metodo = classe.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1)), classes);
					
					if (
							( metodo.invoke(antigo, objetos) instanceof java.util.Collection==false )
							) {
						
						 retorno.append(" "+bundle.getString("lbl.parametro"  )+" "+field.getName().toUpperCase()).
						append(" "+bundle.getString("lbl.valorAntigo"     )+" ").append(metodo.invoke(antigo, objetos));
					}else
						if( metodo.invoke(antigo, objetos) instanceof java.util.Collection ){
							//tratar as diferenças entre collections
						}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @SuppressWarnings("rawtypes")
	public String comparadorObjetos(Object antigo, Object novo)  {
    	
    	StringBuilder retorno = new StringBuilder();

        try {

            Class<? extends Object> classe = antigo.getClass();
            Field[] campos = classe.getDeclaredFields();

            Class[] classes = new Class[0];
            Method metodo = null;

            Object[] objetos = new Object[0];

            for (Field field : campos) {

            	String metodoNome =("get" + field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1)));
            	
            	if(!metodoNome.equals("getSerialVersionUID")){
            		metodo = classe.getDeclaredMethod("get" + field.getName().substring(0, 1).toUpperCase().concat(field.getName().substring(1)), classes);

                    if (!metodo.invoke(novo, objetos).equals(metodo.invoke(antigo, objetos))
                    		&&	( metodo.invoke(antigo, objetos) instanceof java.util.Collection==false && metodo.invoke(novo, objetos) instanceof java.util.Collection==false )
                    		) {
                    	
                    		retorno.append(" "+bundle.getString("lbl.parametro"  )+" "+field.getName().toUpperCase()).
                        			append(" "+bundle.getString("lbl.novoValor"  )+" ").append(metodo.invoke(novo, objetos  )).
                        			append(" "+bundle.getString("lbl.valorAntigo")+" ").append(metodo.invoke(antigo, objetos));
                    }else
                    	if( metodo.invoke(antigo, objetos) instanceof java.util.Collection && metodo.invoke(novo, objetos) instanceof java.util.Collection ){
                    		//tratar as diferenças entre collections
                    	}
	
            	}
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (Validador.vazio(retorno.toString())) {
            return retorno.toString();
        } 
        
       retorno.insert(0, (bundle.getString("lbl.diferenca.objeto")+" ")) ;
        
        return retorno.toString();
    }
	
    protected boolean isEntidadePersistidaPorIdentidade(T entidade) {
    	return obterEntidadePorIdentidade(entidade) != null;
    }
	
	protected T obterEntidadePorIdentidade(T entidade) {
		return getDAOEntidade().obterPorIdentidade(entidade);
	}
	
	protected boolean isIdEntidadeValido(Object id) {
		return id != null;
	}
	
	protected boolean isEntidadeIdentificada(T entidade) {
		Object id = EntidadeUtils.getId(entidade);
		return isIdEntidadeValido(id);
	}
	
	protected T prepararEntidadeParaManter(T entidade) {
		return entidade;
	}
}
