package br.com.delphos.billing.adquirentes;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.contratosCobranca.ContratoCobranca;
import br.com.delphos.billing.contratosCobranca.ContratoCobrancaDLO;
import br.com.delphos.billing.contratosCobranca.ContratoCobranca_;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;
import br.com.delphos.billing.util.EntidadeUtils;

/**
 * Session Bean implementation class BandeiraDLOBean
 */
@Stateless
public class BandeiraDLOBean extends AbstractDLO<Bandeira> implements BandeiraDLO {
	
	@EJB
	private BandeiraDAO dao;

	@Override
	protected BandeiraDAO getDAOEntidade() {
		return dao;
	}
	
	@EJB
	private ContratoCobrancaDLO contratoCobrancaDLO;
	
	protected ContratoCobrancaDLO getContratoCobrancaDLO() {
		return contratoCobrancaDLO;
	}

	@Override
	public boolean isCodigoValido(String codigo) {
		return codigo != null && codigo.length() >= 1 && codigo.length() <= 10;
	}

	@Override
	public Bandeira obterPorCodigo(String codigo) {
		Bandeira retorno = null;
		if (isCodigoValido(codigo)) {
			retorno = dao.obterPorCodigo(codigo);
		}
		return retorno;
	}
	
	public void alterarBandeira(Bandeira bandeira) {
		dao.alterar(bandeira);
	}

	public void excluirBandeira(Bandeira bandeira) {
		dao.excluir(bandeira);
	}

	public void incluirBandeira(Bandeira bandeira) {
		dao.incluir(bandeira);
	}
	
	public List<Bandeira> listarPorCriterio(String codigo, String nome) {
		return dao.listarPorCriterio(codigo, nome);
	}

	@Override
	public boolean isEditavel(Bandeira bandeira) throws DLOException {
		List<ContratoCobranca> listaContratos = contratoCobrancaDLO.listarPorBandeira(bandeira);
		listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
				ContratoCobranca_.vendas);
		
		boolean temVenda = verificaVendaPorBandeira(listaContratos);
		
		return !temVenda && contarReferencias(bandeira, Bandeira_.adquirentesBandeira) == 0;
	}
	
	private boolean verificaVendaPorBandeira(List<ContratoCobranca> listaContratos) throws DLOException {
		boolean temVenda = false;

		for (Iterator<ContratoCobranca> iterator = listaContratos.iterator(); iterator.hasNext();) {
			ContratoCobranca contratoCobranca = (ContratoCobranca) iterator.next();
//			contratoCobranca = contratoCobrancaDLO.completar(contratoCobranca, ContratoCobranca_.vendas.getName());
			if (contratoCobrancaDLO.contarReferencias(contratoCobranca, ContratoCobranca_.vendas) > 0) {
				temVenda = true;
				break;
			}
		}
		return temVenda;
	}
	
}
