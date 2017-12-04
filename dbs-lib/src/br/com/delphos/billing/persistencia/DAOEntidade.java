package br.com.delphos.billing.persistencia;

import java.util.List;

public interface DAOEntidade<T> {

	Object incluir(T entidade);

	void alterar(T entidade);

	void excluir(T entidade);

	void excluirPorId(Object id);
	
	T obter(Object id);
	
	T obterEntidadeAtualizada(T entidade);
	
	T obterPorIdentidade(T entidade);
	
	List<T> listar();
	
	List<T> listarPorFaixa(int[] range);
	
	long contar();
	
	T completar(T entidade, String... propriedades);
	
	long contarReferencias(T entidade, String atributo);
}
