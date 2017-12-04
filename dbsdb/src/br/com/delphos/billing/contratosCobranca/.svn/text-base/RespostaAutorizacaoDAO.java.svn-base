package br.com.delphos.billing.contratosCobranca;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface RespostaAutorizacaoDAO extends DAOEntidade<RespostaAutorizacao> {
	
	public List<RespostaAutorizacao> listarPorCriterio(String idContratoCobranca, String codigoRespostaAutorizacao, String descricaoRespostaAutorizacaoProvedor);
	
}
