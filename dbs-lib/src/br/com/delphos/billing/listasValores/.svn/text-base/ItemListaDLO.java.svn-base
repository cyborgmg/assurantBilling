package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface ItemListaDLO extends DLOEntidade<ItemLista> {
	
	ItemLista obterPorCodigo(ItemLista itemLista);
	
	ItemLista obterPorCodigo(String codItemLista, String codListaValor);
	
	List<ItemLista> listarItemListaPorListaValor(String codListaValor);
	
	public void alterarItemLista(ItemLista itemLista);

	public void excluirItemLista(ItemLista itemLista);

	public void incluirItemLista(ItemLista itemLista);
	
	public List<ItemLista> listarItemListaPorCriterio (String codigoItemLista, String descricaoItemLista, String idListaValor, String status);
}
