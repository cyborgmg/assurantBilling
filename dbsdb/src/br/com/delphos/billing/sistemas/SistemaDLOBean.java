package br.com.delphos.billing.sistemas;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.produtos.Produto;
import br.com.delphos.billing.produtos.ProdutoDLO;
import br.com.delphos.billing.util.Mensagens;
import br.com.delphos.billing.util.Validador;

/**
 * Session Bean implementation class SistemaDLOBean
 */
@Stateless
public class SistemaDLOBean extends AbstractDLO<Sistema> implements SistemaDLO {
	
	@EJB
	private SistemaDAO dao;

	@EJB private ProdutoDLO produtoDLO;
	
	@Override
	protected SistemaDAO getDAOEntidade() {
		return dao;
	}

	@Override
	public boolean isCodigoValido(String codigoSistema) throws DLOException {
		
		if (codigoSistema == null) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.codigoSistema")));
		}
		
		return codigoSistema != null && codigoSistema.length() >= 1 && codigoSistema.length() <= 10;
	}

	@Override
	public Sistema obterPorProdutoCodigo(Produto produto, String codigoSistema) throws DLOException {
		Sistema retorno = null;
		produto = produtoDLO.obterEntidadeAtualizada(produto);
		if (isCodigoValido(codigoSistema) && produto != null) {
			retorno = dao.obterPorProdutoCodigo(produto, codigoSistema);
		} else {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.produtoCodigoSistema")));
		}
		return retorno;
	}

	@Override
	public List<Sistema> listarPorCodigo(String codigo) throws DLOException {
		
		if (codigo == null || Validador.vazio(codigo)) {
			throw new DLOException(Mensagens.get("parametro.nulo", Mensagens.get("parametro.validacaoDLO.codigoSistema")));
		}
		
		List<Sistema> retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.listarPorCodigo(codigo);
		}
		return retorno;
	}
	
	@Override
	public List<Sistema> listarPorCriterio(String codigo, String descricao) {
		
		List<Sistema> retorno = null;
		retorno = dao.listarPorCriterio(codigo, descricao);
		
		return retorno;
	}

	@Override
	public Sistema obterPorCodigo(String codigo) throws DLOException {
		Sistema retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.obterPorCodigo(codigo);
		} else {
			throw new DLOException(Mensagens.get("parametro.invalido", Mensagens.get("parametro.validacaoDLO.codigoSistema")));
		}
		return retorno;
	}

	public void alterarSistema(Sistema sistema) {
		dao.alterar(sistema);
	}

	public void excluirSistema(Sistema sistema) {
		dao.excluir(sistema);
	}

	public void incluirSistema(Sistema sistema) {
		dao.incluir(sistema);
	}

	@Override
	public Object incluir(Sistema sistema) throws DLOException {
		return dao.incluir(sistema);
	}

	@Override
	public void alterar(Sistema sistema) throws DLOException {
		dao.alterar(sistema);
	}

	@Override
	public boolean isEditavel(Sistema sistema) throws DLOException {
		return contarReferencias(sistema, Sistema_.vendas) == 0;
	}
}
