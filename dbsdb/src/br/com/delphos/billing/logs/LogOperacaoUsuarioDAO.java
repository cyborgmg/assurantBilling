package br.com.delphos.billing.logs;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.enumeracoes.StatusLogOperacaoUsuario;
import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface LogOperacaoUsuarioDAO extends DAOEntidade<LogOperacaoUsuario> {

	public void registrarLogOperacoes(LogOperacaoUsuario logOperacaoUsuario,TipoOperacaoUsuario tipoOperacaoUsuario,Class classeEntidadeOperacaoUsuario)throws ParseException;
	
	public List<LogOperacaoUsuario> listarPorCriterio(String tipoOperacao, String tipoObjetoOperacao, String identificadorObjetoOperacao,
			String usuario, String periodoDe, String periodoAte);

}
