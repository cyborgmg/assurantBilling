package br.com.delphos.billing.logs;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.billing.enumeracoes.TipoOperacaoUsuario;
import br.com.delphos.billing.excecoes.DLOException;
import br.com.delphos.billing.persistencia.AbstractDLO;

/**
 * Session Bean implementation class LogOperacaoUsuarioDLOBean
 */
@Stateless
public class LogOperacaoUsuarioDLOBean extends AbstractDLO<LogOperacaoUsuario> implements LogOperacaoUsuarioDLO {

	@EJB
	private LogOperacaoUsuarioDAO dao;

	@Override
	protected LogOperacaoUsuarioDAO getDAOEntidade() {
		return dao;
	}
	

	@Override
	public void registrarLogOperacoes(	LogOperacaoUsuario logOperacaoUsuario,
			TipoOperacaoUsuario tipoOperacaoUsuario,
			Class classeEntidadeOperacaoUsuario)
	{

		boolean hasLogOperacaoUsuario 		 	= ( logOperacaoUsuario		  										   !=null?true:false );
		
		boolean hasTipoOperacaoUsuario        	= ( tipoOperacaoUsuario       										   !=null?true:false );
		boolean hasClasseEntidadeOperacaoUsuario= ( classeEntidadeOperacaoUsuario       							   !=null?true:false );
		boolean hasUsuarioOperacao              = ( hasLogOperacaoUsuario && logOperacaoUsuario.getUsuario()           !=null?true:false );
		boolean hasComplementoOperacaoUsuario   = ( hasLogOperacaoUsuario && logOperacaoUsuario.getComplemento()       !=null && !"".equals( logOperacaoUsuario.getComplemento() )?true:false );

		if(hasLogOperacaoUsuario  && hasTipoOperacaoUsuario && hasClasseEntidadeOperacaoUsuario && hasUsuarioOperacao&& hasComplementoOperacaoUsuario)

			try {
				dao.registrarLogOperacoes(logOperacaoUsuario, tipoOperacaoUsuario, classeEntidadeOperacaoUsuario);
			} catch (ParseException e) {

			}
	}

	@Override
	public void registrarLogOperacoes( LogOperacaoUsuario logOperacaoUsuario  ,
									   TipoOperacaoUsuario tipoOperacaoUsuario,
									   Class classeEntidadeOperacaoUsuario    ,
									   Object antigo, Object novo				){
		
		boolean hasLogOperacaoUsuario 		 	= ( logOperacaoUsuario		  										   !=null?true:false );
		boolean hasTipoOperacaoUsuario        	= ( tipoOperacaoUsuario       										   !=null?true:false );
		boolean hasClasseEntidadeOperacaoUsuario= ( classeEntidadeOperacaoUsuario       							   !=null?true:false );
		boolean hasUsuarioOperacao              = ( hasLogOperacaoUsuario && logOperacaoUsuario.getUsuario()           !=null?true:false );
		boolean isInclusaoExclusao = 				(
				tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.INCLUSAO.getValor()) ||
				tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.EXCLUSAO.getValor()) ||
				tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.ATIVACAO.getValor()) ||
				tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.DESATIVACAO.getValor())
				);
		boolean hasAntigoNovo                   = ( (antigo!=null&&novo!=null) 
				|| 
				isInclusaoExclusao
				? true	: false );
		
		String diffAntigoNovo = null; 
		
		if(hasLogOperacaoUsuario  && hasTipoOperacaoUsuario && hasClasseEntidadeOperacaoUsuario 
				&& hasUsuarioOperacao && hasAntigoNovo){
			if (!isInclusaoExclusao) {
				diffAntigoNovo = comparadorObjetos(antigo, novo);
			} else {
				if (tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.INCLUSAO.getValor())) {
					diffAntigoNovo = gerarMensagemComplementoInclusao(novo);
				}
				if (tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.EXCLUSAO.getValor())) {
					diffAntigoNovo = gerarMensagemComplementoExclusao(antigo);
				}
				
				if (tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.ATIVACAO.getValor())) {
					if (novo == null) {
						diffAntigoNovo = gerarMensagemComplementoAtivacao(antigo);
					} else {
						diffAntigoNovo = gerarMensagemComplementoAtivacao(antigo, novo);
					}
				}
				
				if (tipoOperacaoUsuario.getValor().equals(TipoOperacaoUsuario.DESATIVACAO.getValor())) {
					if (novo == null) {
						diffAntigoNovo = gerarMensagemComplementoDesativacao(antigo);
					} else {
						diffAntigoNovo = gerarMensagemComplementoDesativacao(antigo, novo);
					}
				}
				
			}
			
			if((diffAntigoNovo!=null&&!diffAntigoNovo.isEmpty())){
				logOperacaoUsuario.setComplemento(diffAntigoNovo);
				try {
					dao.registrarLogOperacoes(logOperacaoUsuario, tipoOperacaoUsuario, classeEntidadeOperacaoUsuario);
				} catch (ParseException e) {
	
				}
			}
	   }
		
	}
	
	public List<LogOperacaoUsuario> listarPorCriterio(String tipoOperacao, String tipoObjetoOperacao, String identificadorObjetoOperacao,
			String usuario, String periodoDe, String periodoAte) {
		return dao.listarPorCriterio(tipoOperacao, tipoObjetoOperacao, identificadorObjetoOperacao, usuario, periodoDe, periodoAte);
	}


	@Override
	public boolean isEditavel(LogOperacaoUsuario entidade) throws DLOException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
