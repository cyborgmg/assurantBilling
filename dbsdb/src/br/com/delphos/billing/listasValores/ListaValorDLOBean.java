package br.com.delphos.billing.listasValores;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class ListaValorDLOBean
 */
@Stateless
public class ListaValorDLOBean extends AbstractDLO<ListaValor> implements ListaValorDLO {
	
	@EJB
	private ListaValorDAO dao;

	@Override
	protected ListaValorDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isCodigoValido(String codigo) throws DLOException {
		
		if (codigo == null || Validador.vazio(codigo)) {
			throw new DLOException(Mensagens.get("parametro.nulo", "parametro.validacaoDLO.codigoListaValor"));
		}
		
		return codigo != null && codigo.length() >= 1 && codigo.length() <= 8;
	}

	@Override
	public ListaValor obterPorCodigo(String codigo) throws DLOException {
		ListaValor retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.obterPorCodigo(codigo);
		} else {
			throw new DLOException(Mensagens.get("parametro.invalido", "parametro.validacaoDLO.codigoListaValor"));
		}
		return retorno;
	}
	
	public void alterarListaValor(ListaValor listaValor) {
		dao.alterar(listaValor);
	}

	public void excluirListaValor(ListaValor listaValor) {
		dao.excluir(listaValor);
	}

	public void incluirListaValor(ListaValor listaValor) {
		dao.incluir(listaValor);
	}
	
	public List<ListaValor> listarPorCriterio(String codigo, String descricao, String status, String tipoUso) {
		return dao.listarPorCriterio(codigo, descricao, status, tipoUso);
	}

	@Override
	public boolean isEditavel(ListaValor entidade) throws DLOException {
		return false;
	}
	
}
