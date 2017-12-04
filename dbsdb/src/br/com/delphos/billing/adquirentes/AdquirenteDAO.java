package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.billing.persistencia.DAOEntidade;

@Local
public interface AdquirenteDAO extends DAOEntidade<Adquirente> {
	
	public List<Adquirente> listarPorCriterio(String codigoConvenio, String nome, String codigoAfiliacao);
	
	public List<Adquirente> listarAdquirenteOrdenado();
	
}
