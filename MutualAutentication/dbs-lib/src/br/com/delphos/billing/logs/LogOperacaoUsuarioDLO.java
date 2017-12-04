package br.com.delphos.billing.logs;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface LogOperacaoUsuarioDLO extends DLOEntidade<LogOperacaoUsuario> {

	public void registrarLogOperacoes(LogOperacaoUsuario logOperacaoUsuario,
			TipoOperacaoUsuario tipoOperacaoUsuario,Class classeEntidadeOperacaoUsuario);
	
	public void registrarLogOperacoes(LogOperacaoUsuario  logOperacaoUsuario,
									  TipoOperacaoUsuario tipoOperacaoUsuario,
									  Class classeEntidadeOperacaoUsuario,
									  Object antigo, Object novo);
	
	public List<LogOperacaoUsuario> listarPorCriterio(String tipoOperacao, String tipoObjetoOperacao, String identificadorObjetoOperacao,
			String usuario, String periodoDe, String periodoAte);
					
}
