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
	 * Destaca a entidade do gerenciamento em um contexto de persist�ncia,
	 * se houver.
	 * 
	 * <p>Para implementa��es da camada de persist�ncia baseadas no JPA, esta
	 * chamada pode ser considerada como o equivalente a:
	 * <pre>
	 * if (entityManager.contains(entidade)) {
	 *     // o flush � importante, para garantir que os dados da entidade sejam
	 *     // persistidos caso a mesma tenha sido usada para inclus�o ou altera��o.
	 *     // Do contr�rio, esta garantia n�o existe e as altera��es podem se
	 *     // perder, pois altera��es em entidades "detached" n�o s�o persistidas.
	 *     // Neste caso, deve-se chamar flush em algum momento antes do detach.
	 *     entityManager.flush();
	 *     entityManager.detach(entidade);
	 * }
	 * return entidade;
	 * </pre>
	 * Para implementa��es que n�o possuam um conceito de entidade gerenciada, ou
	 * caso o conceito n�o se aplique, estas implementa��es devem simplesmente retornar
	 * a entidade ou uma c�pia dela, sem mudan�as, e.g.:
	 * <pre>
	 * return entidade;
	 * </pre>
	 * <p>
	 * 
	 * <p>As implementa��es s�o livres para retornar c�pias dos objetos.
	 * No caso de implementa��es JPA, retornar uma c�pia pode ser mais eficiente
	 * do que efetuar flushes, mas � aconselh�vel acertar o correto modo de c�pia
	 * (superficial, densa, etc) e certificar-se de que o custo da c�pia � de fato
     * menor do que o custo do flush. E, especificamente para interfaces EJB remotas,
     * c�pias <b>sempre</b> ser�o retornadas, por conta da sem�ntica
     * <em>pass-by-value</em> aplicada �s chamadas feitas a estas interfaces.
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
