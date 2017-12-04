package br.com.delphos.billing.contratosCobranca;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface RespostaAutorizacaoDLO extends DLOEntidade<RespostaAutorizacao> {
	
	public void alterarRespostaAutorizacao(RespostaAutorizacao respostaAutorizacao);

	public void excluirRespostaAutorizacao(RespostaAutorizacao respostaAutorizacao);

	public void incluirRespostaAutorizacao(RespostaAutorizacao respostaAutorizacao);
	
	public List<RespostaAutorizacao> listarPorCriterio(String idContratoCobranca, String codigoRespostaAutorizacao, String descricaoRespostaAutorizacaoProvedor);

}
