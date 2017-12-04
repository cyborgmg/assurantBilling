package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class ItemListaDLOBean
 */
@Stateless
public class ItemListaDLOBean extends AbstractDLO<ItemLista> implements ItemListaDLO {
	
	@EJB
	private ItemListaDAO dao;

	@Override
	protected ItemListaDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public ItemLista obterPorCodigo(ItemLista itemLista) {
		if( itemLista!=null && !itemLista.getCodigo().isEmpty() )
			return dao.obterPorCodigo(itemLista);
		else
			return null;
	}
	
	@Override
	public ItemLista obterPorCodigo(String codItemLista, String codListaValor) {
		
		if(!Validador.vazio(codItemLista) || !Validador.vazio(codListaValor) )
			return dao.obterPorCodigo(codItemLista, codListaValor);
		else
			return null;
	}
	
	public void alterarItemLista(ItemLista itemLista) {
		dao.alterar(itemLista);
	}

	public void excluirItemLista(ItemLista itemLista) {
		dao.excluir(itemLista);
	}

	public void incluirItemLista(ItemLista itemLista) {
		dao.incluir(itemLista);
	}

	public List<ItemLista> listarItemListaPorCriterio (String codigoItemLista, String descricaoItemLista, String idListaValor, String status) {
		return dao.listarItemListaPorCriterio(codigoItemLista, descricaoItemLista, idListaValor, status);
	}

	@Override
	public List<ItemLista> listarItemListaPorListaValor(String codListaValor) {
		return dao.listarItemListaPorListaValor(codListaValor);
	}

	@Override
	public boolean isEditavel(ItemLista entidade) throws DLOException {
		return false;
	}
}
