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
 * Session Bean implementation class AdquirenteDLOBean
 */
@Stateless
public class AdquirenteDLOBean extends AbstractDLO<Adquirente> implements AdquirenteDLO {
	
	@EJB
	private AdquirenteDAO dao;

	@Override
	protected AdquirenteDAO getDAOEntidade() {
		return dao;
	}
	
	@EJB
	private ContratoCobrancaDLO contratoCobrancaDLO;
	
	protected ContratoCobrancaDLO getContratoCobrancaDLO() {
		return contratoCobrancaDLO;
	}
	
	public void alterarAdquirente(Adquirente adquirente) {
		dao.alterar(adquirente);
	}

	public void excluirAdquirente(Adquirente adquirente) {
		dao.excluir(adquirente);
	}

	public void incluirAdquirente(Adquirente adquirente) {
		dao.incluir(adquirente);
	}
	
	public List<Adquirente> listarPorCriterio(String codigoConvenio, String nome, String codigoAfiliacao) {
		return dao.listarPorCriterio(codigoConvenio, nome, codigoAfiliacao);
	}
	
	//TODO VERIFICAR SE AGORA TEREMOS DE PASSAR O CODIGO DE FILIACAO PARA BUSCAR.
	public Adquirente obterPorCodigoAfiliacaoConciliador(String nome) {
		return dao.listarPorCriterio(null, nome, null).get(0);
	}

	@Override
	public boolean isEditavel(Adquirente adquirente) throws DLOException {
		
		List<ContratoCobranca> listaContratos = contratoCobrancaDLO
				.listarPorAdquirente(adquirente);
		listaContratos = EntidadeUtils.prepararPropriedades(contratoCobrancaDLO, listaContratos,
				ContratoCobranca_.vendas);
		
		boolean temVenda = verificaVendaPorAdquirente(listaContratos);
		
		return contarReferencias(adquirente, Adquirente_.adquirentesBandeira) == 0 && !temVenda;
	}
	
	private boolean verificaVendaPorAdquirente(List<ContratoCobranca> listaContratos) throws DLOException {
		boolean temVenda = false;

		for (Iterator<ContratoCobranca> iterator = listaContratos.iterator(); iterator.hasNext();) {
			ContratoCobranca contratoCobranca = (ContratoCobranca) iterator.next();
			if (contratoCobrancaDLO.contarReferencias(contratoCobranca, ContratoCobranca_.vendas) > 0) {
				temVenda = true;
				break;
			}
		}
		return temVenda;
	}
	
	public List<Adquirente> listarAdquirenteOrdenado() {
		return dao.listarAdquirenteOrdenado();
	}
	
}