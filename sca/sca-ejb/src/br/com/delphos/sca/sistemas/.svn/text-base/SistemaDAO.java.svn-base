package br.com.delphos.sca.sistemas;

import java.util.List;

import javax.ejb.Local;

import br.com.delphos.persistencia.AbstractDAO;

@Local
public interface SistemaDAO extends AbstractDAO<Sistema, Long> {
	
	public List<Sistema> listarPorCriterio(String siglaSistema, String descricaoSistema, String idEmpresa);
}
