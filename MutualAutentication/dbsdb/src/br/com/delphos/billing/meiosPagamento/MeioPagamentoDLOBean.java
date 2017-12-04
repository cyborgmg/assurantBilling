package br.com.delphos.billing.meiosPagamento;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class MeioPagamentoDLOBean
 */
@Stateless
public class MeioPagamentoDLOBean extends AbstractDLO<MeioPagamento> implements MeioPagamentoDLO {
	
	@EJB
	private MeioPagamentoDAO dao;

	@Override
	protected MeioPagamentoDAO getDAOEntidade() {
		return dao;
	}
	
	@EJB
	private ContratoCobrancaDLO contratoCobrancaDLO;
	
	protected ContratoCobrancaDLO getContratoCobrancaDLO() {
		return contratoCobrancaDLO;
	}
	
	@Override
	public boolean isCodigoValido(String codigo) {
		return codigo != null && codigo.length() >= 1 && codigo.length() <= 5;
	}

	@Override
	public MeioPagamento obterPorCodigo(String codigo) {
		MeioPagamento retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.obterPorCodigo(codigo);
		}
		return retorno;
	}
	
	public List<MeioPagamento> listarPorCriterio(String codigo, String descricao) {
		return dao.listarPorCriterio(codigo, descricao);
	}

	@Override
	public boolean isEditavel(MeioPagamento meioPagamento) throws DLOException {
		
		if (contarReferencias(meioPagamento, MeioPagamento_.contratosCobranca) == 0) {
			return true;
		} else {
			meioPagamento = completar(meioPagamento, MeioPagamento_.contratosCobranca);
        	List<ContratoCobranca> contratos = meioPagamento.getContratosCobranca();
        	for (Iterator<ContratoCobranca> it = contratos.iterator(); it.hasNext();) {
				if (contratoCobrancaDLO.contarReferencias(it.next(), ContratoCobranca_.vendas) > 0) {
					return false;
				}
			}
		}
		
		return true;
	}
}
