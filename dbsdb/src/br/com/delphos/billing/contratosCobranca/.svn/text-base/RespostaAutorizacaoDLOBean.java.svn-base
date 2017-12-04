package br.com.delphos.billing.contratosCobranca;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.adquirentes.Bandeira;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class RespostaAutorizacaoDLOBean
 */
@Stateless
public class RespostaAutorizacaoDLOBean extends AbstractDLO<RespostaAutorizacao> implements RespostaAutorizacaoDLO {
	
	@EJB
	private RespostaAutorizacaoDAO dao;

	@Override
	protected RespostaAutorizacaoDAO getDAOEntidade() {
		return dao;
	}
	
	@EJB
	private ContratoCobrancaDLO contratoCobrancaDLO;
	
	protected ContratoCobrancaDLO getContratoCobrancaDLO() {
		return contratoCobrancaDLO;
	}
	
	public void alterarRespostaAutorizacao(RespostaAutorizacao respostaAutorizacao) {
		dao.alterar(respostaAutorizacao);
	}

	public void excluirRespostaAutorizacao(RespostaAutorizacao respostaAutorizacao) {
		dao.excluir(respostaAutorizacao);
	}

	public void incluirRespostaAutorizacao(RespostaAutorizacao respostaAutorizacao) {
		dao.incluir(respostaAutorizacao);
	}
	
	public List<RespostaAutorizacao> listarPorCriterio(String idContratoCobranca, String codigoRespostaAutorizacao, String descricaoRespostaAutorizacaoProvedor) {
		return dao.listarPorCriterio(idContratoCobranca, codigoRespostaAutorizacao, descricaoRespostaAutorizacaoProvedor);
	}

	@Override
	public boolean isEditavel(RespostaAutorizacao respostaAutorizacao) throws DLOException {
		ContratoCobranca cc = contratoCobrancaDLO.completar(respostaAutorizacao.getContratoCobranca(), ContratoCobranca_.vendas.getName());
		boolean temVenda = cc.getVendas().size() > 0;
		
		return !temVenda;
	}
	
}
