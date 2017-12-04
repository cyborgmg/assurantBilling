package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface ItemListaDAO extends DAOEntidade<ItemLista> {
	
	ItemLista obterPorCodigo(ItemLista itemLista);
	
	List<ItemLista> listarItemListaPorListaValor(String codListaValor);
	
	ItemLista obterPorCodigo(String codItemLista, String codListaValor);
	
	public List<ItemLista> listarItemListaPorCriterio (String codigoItemLista, String descricaoItemLista, String idListaValor, String status);
}
