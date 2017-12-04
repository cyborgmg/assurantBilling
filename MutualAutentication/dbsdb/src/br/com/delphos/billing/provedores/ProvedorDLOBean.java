package br.com.delphos.billing.provedores;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.meiosPagamento.MeioPagamento_;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class ProvedorDLOBean
 */
@Stateless
public class ProvedorDLOBean extends AbstractDLO<Provedor> implements ProvedorDLO {
	
	@EJB
	private ProvedorDAO dao;

	@Override
	protected ProvedorDAO getDAOEntidade() {
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
	public Provedor obterPorCodigo(String codigo) {
		Provedor retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.obterPorCodigo(codigo);
		}
		return retorno;
	}
	
	public List<Provedor> listarPorCriterio(String codigo, String descricao) {
		return dao.listarPorCriterio(codigo, descricao);
	}

	@Override
	public boolean isEditavel(Provedor provedor) throws DLOException {
		
		if (contarReferencias(provedor, Provedor_.contratosCobranca) == 0) {
			return true;
		} else {
			provedor = completar(provedor, Provedor_.contratosCobranca);
        	List<ContratoCobranca> contratos = provedor.getContratosCobranca();
        	for (Iterator<ContratoCobranca> it = contratos.iterator(); it.hasNext();) {
				if (contratoCobrancaDLO.contarReferencias(it.next(), ContratoCobranca_.vendas) > 0) {
					return false;
				}
			}
		}
		
		return true;
	}

}
