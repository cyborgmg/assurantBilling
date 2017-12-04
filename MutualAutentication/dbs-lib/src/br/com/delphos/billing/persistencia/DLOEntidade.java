package br.com.delphos.billing.persistencia;

import java.util.List;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;

import br.com.delphos.billing.excecoes.DLOException;

public interface DLOEntidade<T> {

	/**
	 * Persiste a entidade no banco de dados.
	 * 
	 * A verifica��o do modo de persist�ncia � determinado pelo ID da entidade.
	 * <ul>
	 * 		<li>Se a entidade possuir um ID n�o-nulo, a opera��o ser� de
	 *          atualiza��o (e.g. {@code update} no SQL ou {@code find+merge} no
	 *          JPA)</li>
	 *      <li>Se a entidade possuir um ID nulo, a opera��o ser� de inser��o.
	 *      	</li>
	 * </ul>
	 * 
	 * Anterior � persist�ncia, a entidade ser� validada. Caso a entidade n�o
	 * tenha um estado v�lido para persist�ncia, a chamada ser� ignorada. A
	 * defini��o do que � um estado v�lido para uma entidade � dada pela
	 * implementa��o do DLO e do tipo da entidade.
	 *  	
	 * @param entidade A entidade a ser persistida.
	 * @return O ID da entidade inserida ou alterada em caso de sucesso, ou
	 * nulo em caso de erro.
	 */
	Object manter(T entidade) throws DLOException ;
	
	/**
	 * Persiste a entidade no banco de dados.
	 * 
	 * <p>Tem a mesma sem�ntica de {@link #manter(Object)}, com a diferen�a de
	 * que uma exce��o � lan�ada em caso de qualquer erro. A chamada � este
	 * m�todo seria (talvez grosseiramente) equivalente ao seguinte idioma:
	 * <pre><code>
	 * if (manter(entidade) == null) {
	 *     throw new <? extends DLOException>(...);
	 * }
	 * </code></pre>
	 * </p>
	 * 
	 * @param entidade
	 * @throws DLOException
	 */
	void salvar(T entidade) throws DLOException;
	
	
	/**
	 * Exclui uma entidade do banco de dados.
	 * 
	 * Se o ID da entidade n�o for nulo, a exclus�o da entidade se dar� pelo seu
	 * ID. Caso contr�rio, o DLO <b>poder�</b> tentar excluir a entidade pela
	 * sua identidade, caso seja v�lida (e.g. excluir usu�rio por login, ao
	 * inv�s de por ID).
	 * 
	 * @param entidade A entidade a ser exclu�da.
	 */
	void excluir(T entidade);
	
	/**
	 * Obt�m uma entidade da unidade de persist�ncia com o ID passado.
	 * 
	 * @param id O ID da entidade a ser recuperada.
	 * @return A entidade, ou nulo em caso de erro.
	 */
	T obter(Object id);
	
	T obterPorIdentidade(T entidade);
	
	T obterEntidadeAtualizada(T entidade);
	
	/**
	 * Lista todas as entidades persistidas.
	 * @return
	 */
	List<T> listar();
	
	/**
	 * @see {@link #listar()}
	 * @param range
	 * @return
	 */
	List<T> listarPorFaixa(int... range);
	
	/**
	 * 
	 * @return
	 */
	long contar();
	
	T completar(T entidade, String... propriedades);
	
	T completar(T entidade, Attribute<T, ?> propriedade);

	long contarReferencias(T entidade, PluralAttribute<T, ?, ?> atributo) throws DLOException;
	
	boolean isEditavel(T entidade) throws DLOException;
}
