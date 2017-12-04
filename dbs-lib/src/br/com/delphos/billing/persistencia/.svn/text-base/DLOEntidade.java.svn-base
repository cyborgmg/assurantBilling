package br.com.delphos.billing.persistencia;

import java.util.List;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;

import br.com.delphos.billing.excecoes.DLOException;

public interface DLOEntidade<T> {

	/**
	 * Persiste a entidade no banco de dados.
	 * 
	 * A verificação do modo de persistência é determinado pelo ID da entidade.
	 * <ul>
	 * 		<li>Se a entidade possuir um ID não-nulo, a operação será de
	 *          atualização (e.g. {@code update} no SQL ou {@code find+merge} no
	 *          JPA)</li>
	 *      <li>Se a entidade possuir um ID nulo, a operação será de inserção.
	 *      	</li>
	 * </ul>
	 * 
	 * Anterior à persistência, a entidade será validada. Caso a entidade não
	 * tenha um estado válido para persistência, a chamada será ignorada. A
	 * definição do que é um estado válido para uma entidade é dada pela
	 * implementação do DLO e do tipo da entidade.
	 *  	
	 * @param entidade A entidade a ser persistida.
	 * @return O ID da entidade inserida ou alterada em caso de sucesso, ou
	 * nulo em caso de erro.
	 */
	Object manter(T entidade) throws DLOException ;
	
	/**
	 * Persiste a entidade no banco de dados.
	 * 
	 * <p>Tem a mesma semântica de {@link #manter(Object)}, com a diferença de
	 * que uma exceção é lançada em caso de qualquer erro. A chamada à este
	 * método seria (talvez grosseiramente) equivalente ao seguinte idioma:
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
	 * Se o ID da entidade não for nulo, a exclusão da entidade se dará pelo seu
	 * ID. Caso contrário, o DLO <b>poderá</b> tentar excluir a entidade pela
	 * sua identidade, caso seja válida (e.g. excluir usuário por login, ao
	 * invés de por ID).
	 * 
	 * @param entidade A entidade a ser excluída.
	 */
	void excluir(T entidade);
	
	/**
	 * Obtém uma entidade da unidade de persistência com o ID passado.
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
