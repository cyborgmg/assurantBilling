package br.com.delphos.persistencia;

import java.io.Serializable;
import java.util.List;

import br.com.delphos.excecoes.DelphosException;

public interface AbstractDAO<T extends Entidade<Id>, Id extends Serializable> {

	Class<T> getClasseEntidade();
	
	Class<Id> getClasseId();
	
	Id incluir(T entity);
	
	void alterar(T entity);
		
	void excluirPorIdentidade(T entity);
	
	void excluirPorId(Id id);
	
	boolean isGerenciado(T entidade);

	T clonar(T entidade);
	
	/**
	 * Destaca a entidade do gerenciamento em um contexto de persistência,
	 * se houver.
	 * 
	 * <p>Para implementações da camada de persistência baseadas no JPA, esta
	 * chamada pode ser considerada como o equivalente a:
	 * <pre>
	 * if (entityManager.contains(entidade)) {
	 *     // o flush é importante, para garantir que os dados da entidade sejam
	 *     // persistidos caso a mesma tenha sido usada para inclusão ou alteração.
	 *     // Do contrário, esta garantia não existe e as alterações podem se
	 *     // perder, pois alterações em entidades "detached" não são persistidas.
	 *     // Neste caso, deve-se chamar flush em algum momento antes do detach.
	 *     entityManager.flush();
	 *     entityManager.detach(entidade);
	 * }
	 * return entidade;
	 * </pre>
	 * Para implementações que não possuam um conceito de entidade gerenciada, ou
	 * caso o conceito não se aplique, estas implementações devem simplesmente retornar
	 * a entidade ou uma cópia dela, sem mudanças, e.g.:
	 * <pre>
	 * return entidade;
	 * </pre>
	 * <p>
	 * 
	 * <p>As implementações são livres para retornar cópias dos objetos.
	 * No caso de implementações JPA, retornar uma cópia pode ser mais eficiente
	 * do que efetuar flushes, mas é aconselhável acertar o correto modo de cópia
	 * (superficial, densa, etc) e certificar-se de que o custo da cópia é de fato
     * menor do que o custo do flush. E, especificamente para interfaces EJB remotas,
     * cópias <b>sempre</b> serão retornadas, por conta da semântica
     * <em>pass-by-value</em> aplicada às chamadas feitas a estas interfaces.
	 * </p> 
	 * 
	 * @param entidade
	 * @return
	 * @throws DelphosException
	 */
	T destacar(T entidade);
	
	void atualizar(T entidade);
	
	T obterPorId(Id id);

    T obterPorIdentidade(T entidade);
    
	List<T> listar();
	
	List<T> listarPorFaixa(long... range);
	
	long contar();
	
	T completar(T entidadeOriginal, String... nomesPropriedade);
}
