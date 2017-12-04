package br.com.delphos.sca.parametrosSmtp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.delphos.excecoes.DelphosException;
import br.com.delphos.excecoes.ValidacaoException;
import br.com.delphos.persistencia.AbstractDLOBean;

@Stateless
public class ParametroSmtpDLOBean extends AbstractDLOBean<ParametroSmtp, Long>implements ParametroSmtpDLO {

	@EJB
	private ParametroSmtpDAO dao;

	public ParametroSmtpDLOBean() {
	}

	@Override
	protected ParametroSmtpDAO getDAOEntidade() {
		return dao;
	}

	@Override
	protected void validarCampoEntidade(ParametroSmtp entidade,
			String nomeAtributo, Class<?>... validacoes)
			throws ValidacaoException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ParametroSmtp obterUltimosParametros() throws DelphosException {
		ParametroSmtp parametro = dao.obterUltimosParametros();
		if (parametro == null) {
			throw new DelphosException();
		}
		return parametro;
	}


}
