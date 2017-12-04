package br.com.delphos.billing.adquirentes;

import java.util.List;

import javax.ejb.Remote;

import br.com.delphos.billing.persistencia.DLOEntidade;

@Remote
public interface AdquirenteDLO extends DLOEntidade<Adquirente> {
	
	public void alterarAdquirente(Adquirente adquirente) ;

	public void excluirAdquirente(Adquirente adquirente) ;

	public void incluirAdquirente(Adquirente adquirente) ;
	
	public List<Adquirente> listarPorCriterio(String codigoConvenio, String nome, String codigoAfiliacao);
	
	public List<Adquirente> listarAdquirenteOrdenado();
	
}
