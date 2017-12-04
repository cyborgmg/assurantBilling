package br.com.delphos.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.Attribute;

import br.com.delphos.excecoes.EntidadeInexistenteException;
import br.com.delphos.excecoes.PersistenciaException;
import br.com.delphos.excecoes.DelphosException;

public interface AbstractDLO<T extends Entidade<Id>, Id extends Serializable> extends ValidadorEntidade<T, Id> {

	T instanciar();
	
	T instanciar(Id id);
	
	T copiar(T entidade) throws DelphosException ;

	boolean isPersistidoPorId(Id id) throws DelphosException;
	
	boolean isPersistido(T entidade) throws DelphosException;

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
	 * @return O ID da entidade inserida ou alterada.
	 * @throws DelphosException Em caso de falha.
	 */
	Id manter(T entidade) throws DelphosException;
	
	/**
	 * Persiste a entidade no banco de dados.
	 * 
	 * <p>Tem a mesma semântica de {@link #manter(Object)}, com a diferença de
	 * que uma exceção é lançada em caso de qualquer erro. A chamada à este
	 * método seria (talvez grosseiramente) equivalente ao seguinte idioma:
	 * <pre><code>
	 * if (manter(entidade) == null) {
	 *     throw new PersistenciaException(...);
	 * }
	 * </code></pre>
	 * </p>
	 * 
	 * @param entidade
	 * @throws DelphosException Em caso de falha.
	 */
	void salvar(T entidade) throws DelphosException, PersistenciaException;

	/**
	 * Exclui uma entidade do banco de dados, a partir do seu ID.
	 * 
	 * @param id O ID da entidade a ser excluída.
	 * @throws DelphosException Em caso de falha.
	 */
	void excluirPorId(Id id) throws DelphosException;
	
	/**
	 * Exclui uma entidade do banco de dados.
	 * 
	 * Se o ID da entidade não for nulo, a exclusão da entidade se dará pelo seu
	 * ID, e neste caso a chamada será semanticamente equivalente a {@code excluirPorId(entidade.getId())}.
	 * Caso contrário, o DLO <b>poderá</b> tentar excluir a entidade pela
	 * sua identidade, caso seja válida (e.g. excluir usuário por login, ao
	 * invés de por ID).
	 * 
	 * @param entidade A entidade a ser excluída.
	 * @throws DelphosException Em caso de falha.
	 */
	void excluir(T entidade) throws DelphosException;

	/**
	 * Obtém uma entidade da unidade de persistência com o ID passado.
	 * 
	 * @param id O ID da entidade a ser recuperada.
	 * @return A entidade, assim como persistida no banco de dados,
	 * ou {@code null} caso a entidade não exista.
	 * @throws DelphosException Em caso de falha.
	 */
	T obterPorId(Id id) throws DelphosException;
	
	/**
	 * Obtém uma entidade da unidade de persistência.
	 * 
	 * <p>Se o ID da entidade não for nulo, a chamada será semanticamente
	 * equivalente a {@code obterPorId(entidade.getId())}.
	 * Caso contrário, o DLO <b>poderá</b> tentar obter a entidade pela
	 * sua identidade, caso seja válida (e.g. obter usuário por login, ao
	 * invés de por ID). Neste último caso, se a busca por identidade
	 * não for implementada neste método pelo DLO, a implementação do método
	 * <b>não</b> retornará nulo, e notificará o chamador do método
	 * através de uma exceção.
	 * </p>
	 * 
	 * <p>Não há garantias quanto ao retorno da mesma entidade de entrada
	 * (o mesmo objeto passado como {@code entidade}, e.g.
	 * {@code entidade == obter(entidade)}. No entanto, deve-se levar em
	 * consideração que, caso a entidade seja gerenciada pela camada de
	 * persistência (e.g. uma entidade JPA gerenciada ("attached") por
	 * um EntityManager), as implementações são livres para retornar o
	 * mesmo objeto de entrada (e.g. não haverá busca no banco de dados).
	 * </p>
	 * 
	 * @param entidade
	 * @return
	 * @throws DelphosException
	 */
	T obter(T entidade) throws DelphosException;

	/**
	 * Obtém uma entidade da unidade de persistência, e falha se a
	 * entidade não existir.
	 * 
	 * <p>A chamada deverá ser semanticamente equivalente a:
	 * <pre>
	 *   if (obter(entidade) == null) {
	 *       throw new EntidadeInexistenteException();
	 *   }
	 * </pre>
	 * </p>
	 * 
	 * @param entidade Os dados da entidade a ser buscada.
	 * @return Uma nova entidade, assim como persistida no banco de dados.
	 * @throws DelphosException Em caso de falha.
	 * @throws EntidadeInexistenteException Caso a entidade não exista no
	 * banco de dados.
	 */
	T obterOuFalhar(T entidade) throws DelphosException, EntidadeInexistenteException;

	/**
	 * Obtém uma entidade da unidade de persistência, com possível
	 * atualização se a entidade estiver sendo gerenciada.
	 * 
	 * <p>A chamada à este método tem exatamente a mesma semântica de
	 * {@link #obter(Entidade)}, com uma diferença:
	 * caso a entidade seja gerenciada pela camada de persistência (e.g.
	 * uma entidade JPA gerenciada ("attached") por um EntityManager),
	 * as implementações são livres para atualizar a entidade de entrada
	 * em si (e.g. {@code EntityManager.refresh(entidade)} no JPA).
	 * </p>
	 * 
	 * <p>Atenção deve ser dada ao contexto no qual este método é
	 * usado, pois há a possibilidade de introdução de efeitos
	 * colaterais (e.g. perda de campos alterados na entidade de entrada).
	 * Se a sua entidade de entrada não pode ser alterada (e.g. sendo usada
	 * em uma tela de cadastro para armazenar alterações feitas pelo usuário),
	 * é sugerido que sejam utilizados outros métodos de busca, como o 
	 * {@link #obter(Entidade)} ou {@link #obterPorId(Serializable)}. 
	 * </p>
	 * 
	 * @param entidade Os dados da entidade a ser buscada.
	 * @return Uma nova entidade, assim como persistida no banco de dados,
	 * ou {@code null} caso a entidade não exista.
	 * @throws DelphosException Em caso de falha.
	 */
	T atualizar(T entidade) throws DelphosException;
	
	/**
	 * Obtém uma entidade da unidade de persistência, e falha se a
	 * entidade não existir.
	 * 
	 * <p>A chamada deverá ser semanticamente equivalente a:
	 * <pre>
	 *   if (obterAtualizado(entidade) == null) {
	 *       throw new EntidadeInexistenteException();
	 *   }
	 * </pre>
	 * </p>
	 * 
	 * @param entidade Os dados da entidade a ser buscada.
	 * @return Uma nova entidade, assim como persistida no banco de dados.
	 * @throws DelphosException Em caso de falha.
	 * @throws EntidadeInexistenteException Caso a entidade não exista no
	 * banco de dados.
	 */
	T atualizarOuFalhar(T entidade) throws DelphosException, EntidadeInexistenteException;
	
	/**
	 * Lista todas as entidades persistidas.
	 * 
	 * @return A lista de entidades persistidas.
	 */
	List<T> listar();
	
	/**
	 * @see {@link #listar()}
	 * @param range
	 * @return
	 */
	List<T> listarPorFaixa(long... range) throws DelphosException;
	
	/**
	 * Conta quantas entidades existem no banco de dados.
	 * 
	 * @return O número de entidades persistidas.
	 */
	long contar();
	
	/**
	 * 
	 * @param entidade
	 * @param propriedades
	 * @return
	 * @throws DelphosException
	 */
	T completar(T entidade, String... propriedades) throws DelphosException;
	
	/**
	 * @see {@link #completar(Entidade, String...)}
	 * @param entidade
	 * @param propriedade
	 * @return
	 * @throws DelphosException
	 */
	T completar(T entidade, Attribute<T, ?> propriedade) throws DelphosException;
}
